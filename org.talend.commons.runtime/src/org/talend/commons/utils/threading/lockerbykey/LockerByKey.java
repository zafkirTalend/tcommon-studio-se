// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.threading.lockerbykey;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.talend.commons.utils.StringUtils;

/**
 * This class is useful to lock some part of code from the provided key.
 * 
 * This class has the same behaviour that <code>java.util.concurrent.locks.ReentrantLock</code> except additionally it
 * expect keys to lock the parts of code.
 * 
 * It uses internally a <code>java.util.concurrent.ConcurrentHashMap</code> to store locks from keys <code>KP</code> and
 * the <code>java.util.concurrent.locks.ReentrantLock</code> as properties of a value wrapper. <br/>
 * 
 * @see java.util.concurrent.locks.ReentrantLock
 * 
 * @param <KP> type of the key
 */
public class LockerByKey<KP> implements ILockerByKey<KP> {

    private static Logger log = Logger.getLogger(LockerByKey.class);

    ConcurrentHashMap<InternalKeyLock<KP>, LockerValue<KP>> mapKeyLockToValueLock = new ConcurrentHashMap<InternalKeyLock<KP>, LockerValue<KP>>();

    private final Object lockAllOperations = new Object();

    private AtomicInteger counter = new AtomicInteger();

    private AtomicInteger runningOperations = new AtomicInteger();

    private final static int DEFAULT_CLEAN_PERIOD = 500;

    private final static boolean DEFAULT_FAIR = true;

    private int cleanPeriod;

    private boolean fair;

    private volatile boolean blockAllOperations;

    private volatile boolean shuttingDown;

    private volatile boolean stopped;

    /**
     * LockerByKey constructor.
     */
    public LockerByKey() {
        this(DEFAULT_FAIR, DEFAULT_CLEAN_PERIOD);
    }

    /**
     * 
     * Constructor LockerByKey.
     * 
     * @param fair {@code true} if this lock should use a fair ordering policy
     */
    public LockerByKey(boolean fair) {
        this(fair, DEFAULT_CLEAN_PERIOD);
    }

    /**
     * 
     * Constructor LockerByKey.
     * 
     * @param cleanPeriod in number of operations, it means that an automatic clean will be done for each
     * <code>cleanPeriod</code> number of unlock operation.
     */
    public LockerByKey(int cleanPeriod) {
        this(DEFAULT_FAIR, cleanPeriod);
    }

    /**
     * 
     * Constructor LockerByKey.
     * 
     * @param fair {@code true} if this lock should use a fair ordering policy
     * @param cleanPeriod in number of operations, it means that an automatic clean will be done after each
     * <code>cleanPeriod</code> number of unlock operation.
     */
    public LockerByKey(boolean fair, int cleanPeriod) {
        super();
        this.fair = fair;
        if (cleanPeriod <= 0) {
            throw new IllegalArgumentException("The cleanPeriod value has to be greater than 0");
        }
        this.cleanPeriod = cleanPeriod;
    }

    /**
     * 
     * Constructor LockerByKey.
     * 
     * @param fair {@code true} if this lock should use a fair ordering policy
     * @param cleanDisabled true to disable the clean completely <code>cleanPeriod</code> number of unlock operation.
     */
    protected LockerByKey(boolean fair, boolean cleanDisabled) {
        super();
        this.fair = fair;
        if (cleanDisabled) {
            this.cleanPeriod = 0;
        } else {
            this.cleanPeriod = DEFAULT_CLEAN_PERIOD;
        }
    }

    /**
     * Method "isLocked".
     * 
     * @param key
     * @return true if any thread holds this lock and false otherwise
     * 
     * @see java.util.concurrent.locks.ReentrantLock#isLocked()
     */
    @Override
    public boolean isLocked(KP key) {
        checkKey(key);
        LockerValue<KP> locker = getLockerValue(key);
        return locker != null && locker.getLock().isLocked();
    }

    /**
     * 
     * Method "lockInterruptibly".
     * 
     * @param key
     * @throws InterruptedException
     * @see java.util.concurrent.locks.ReentrantLock#lockInterruptibly()
     */
    @Override
    public void lockInterruptibly(KP key) throws InterruptedException {
        checkStopped();
        checkKey(key);
        blockOperationIfRequired();
        incrementRunningOperations();
        LockerValue<KP> locker = prepareInternalLock(key);
        decrementRunningOperations();
        locker.getLock().lockInterruptibly();
    }

    /**
     * Method "tryLock".
     * 
     * @param key
     * @return true if the lock was free and was acquired by the current thread, or the lock was already held by the
     * current thread; and false if the waiting time elapsed before the lock could be acquired
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     * @see java.util.concurrent.locks.ReentrantLock#tryLock()
     */
    @Override
    public boolean tryLock(KP key) {
        if (stopped || shuttingDown) {
            return false;
        }
        checkKey(key);
        blockOperationIfRequired();
        incrementRunningOperations();
        LockerValue<KP> locker = prepareInternalLock(key);
        decrementRunningOperations();
        return locker.getLock().tryLock();
    }

    /**
     * Method "tryLock".
     * 
     * @param key
     * @param timeout the time to wait for the lock in milliseconds
     * @return true if the lock was free and was acquired by the current thread, or the lock was already held by the
     * current thread; and false if the waiting time elapsed before the lock could be acquired
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     * @see java.util.concurrent.locks.ReentrantLock#tryLock(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public boolean tryLock(KP key, long timeout) throws InterruptedException {
        return tryLock(key, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * Method "tryLock".
     * 
     * @param key
     * @param timeout the time to wait for the lock
     * @param unit the time unit of the timeout argument
     * @return true if the lock was free and was acquired by the current thread, or the lock was already held by the
     * current thread; and false if the waiting time elapsed before the lock could be acquired
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     * 
     * @see java.util.concurrent.locks.ReentrantLock#tryLock(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public boolean tryLock(KP key, long timeout, TimeUnit unit) throws InterruptedException {
        checkStopped();
        checkKey(key);
        blockOperationIfRequired();
        incrementRunningOperations();
        LockerValue<KP> locker = prepareInternalLock(key);
        decrementRunningOperations();
        interruptIfStopping();
        return locker.getLock().tryLock(timeout, unit);
    }

    private LockerValue<KP> prepareInternalLock(KP key) {
        InternalKeyLock<KP> internalKeyLock = new InternalKeyLock<KP>(key);
        LockerValue<KP> lockerValue = new LockerValue<KP>(key, fair);
        LockerValue<KP> previousLockerValue = null;
        previousLockerValue = mapKeyLockToValueLock.putIfAbsent(internalKeyLock, lockerValue);
        if (previousLockerValue != null) {
            lockerValue = previousLockerValue;
        }
        return lockerValue;
    }

    private void interruptIfStopping() throws InterruptedException {
        if (shuttingDown) {
            throw new InterruptedException("This LockerByKey is shutting down...");
        }
    }

    /**
     * Method "unlock". Unlock the operations with the provided key.
     * 
     * @param key
     * @return true if the key has been found to release the lock; and false otherwise
     * @see java.util.concurrent.locks.ReentrantLock#unlock()
     */
    @Override
    public boolean unlock(KP key) {
        checkKey(key);
        blockOperationIfRequired();
        incrementRunningOperations();
        LockerValue<KP> lockerValue = getLockerValue(key);
        boolean returnValue = false;
        if (lockerValue != null) {
            lockerValue.getLock().unlock();
            returnValue = true;
        }
        decrementRunningOperations();
        cleanAccordingOperations();
        return returnValue;
    }

    private void cleanAccordingOperations() {
        synchronized (lockAllOperations) {
            if (cleanPeriod > 0 && counter.incrementAndGet() % cleanPeriod == 0) {
                clean();
            }
        }
    }

    /**
     * 
     * Method "clean".
     * 
     * Clean the map which contains the lock wrappers.
     * 
     * Removed lock wrappers are these where lock is not locked by a thread and no one thread is waiting to obtain the
     * lock.
     * 
     * The default clean will do an automatic clean all 1000 unlock operation, you can disable or change this value from
     * the constructor.
     */
    public void clean() {
        synchronized (lockAllOperations) {
            waitForRunningOperationsEnded();
            Collection<LockerValue<KP>> values = mapKeyLockToValueLock.values();
            if (log.isTraceEnabled()) {
                log.trace("Cleaning " + this.toString() + " : " + values.size() + " keys/values ...");
            }
            InternalKeyLock<KP> internalKeyLock = new InternalKeyLock<KP>();
            for (LockerValue<KP> lockerValue : values) {
                ReentrantLock lock = lockerValue.getLock();
                LockerValueHandler handler = lockerValue.getHandler();
                if (!lock.hasQueuedThreads() && !lock.isLocked() && handler == null) {
                    internalKeyLock.setKey(lockerValue.getKey());
                    mapKeyLockToValueLock.remove(internalKeyLock);
                }
            }
            resumeAllOperations();
        }
    }

    private void checkStopped() {
        if (stopped || shuttingDown) {
            throw new IllegalStateException("This locker is already stopped or is shutting down !");
        }
    }

    /**
     * Method "check". Check if the key is not null.
     * 
     * @param key
     */
    private void checkKey(KP key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null"); //$NON-NLS-1$
        }
    }

    private void waitForRunningOperationsEnded() {
        blockAllOperations();
        while (runningOperations.get() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void resumeAllOperations() {
        this.blockAllOperations = false;
        lockAllOperations.notifyAll();
    }

    private void blockAllOperations() {
        this.blockAllOperations = true;
    }

    private void blockOperationIfRequired() {
        if (blockAllOperations) {
            synchronized (lockAllOperations) {
                if (blockAllOperations) {
                    try {
                        lockAllOperations.wait();
                    } catch (InterruptedException e) {
                        log.warn(e.getMessage(), e);
                    }
                }
            }
        }
    }

    private void decrementRunningOperations() {
        runningOperations.decrementAndGet();
    }

    private void incrementRunningOperations() {
        runningOperations.incrementAndGet();
    }

    /**
     * Get locker.
     * 
     * @param bean
     * @return locker value.
     */
    @Override
    public LockerValue<KP> getLockerValue(KP key) {
        checkKey(key);
        InternalKeyLock<KP> internalKeyLock = new InternalKeyLock<KP>(key);
        return mapKeyLockToValueLock.get(internalKeyLock);
    }

    @Override
    public synchronized void shutdown() {
        shuttingDown = true;
        waitForRunningOperationsEnded();
        Collection<LockerValue<KP>> values = mapKeyLockToValueLock.values();
        for (LockerValue<KP> lockerValue : values) {
            Collection<Thread> queuedThreads = lockerValue.getLock().getQueuedThreads();
            for (Thread thread : queuedThreads) {
                thread.interrupt();
            }
        }
        clean();
        stopped = true;
    }

    public String toString() {
        return "LockerByKey:" + super.toString();
    }

    /**
     * Getter for cleanFrequency.
     * 
     * @return the cleanFrequency
     */
    @Override
    public int getCleanPeriod() {
        return cleanPeriod;
    }

    /**
     * 
     * LockerByKey class.<br/>
     * 
     * @param <IKP> key
     */
    class InternalKeyLock<IKP> {

        private IKP key;

        public InternalKeyLock() {
        }

        /**
         * InternalKeyLock constructor comment.
         * 
         * @param key2
         */
        public InternalKeyLock(IKP key) {
            this.key = key;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.key == null) ? 0 : this.key.hashCode());
            return result;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final InternalKeyLock other = (InternalKeyLock) obj;
            if (this.key == null) {
                if (other.key != null) {
                    return false;
                }
            } else if (!this.key.equals(other.key)) {
                return false;
            }
            return true;
        }

        public void setKey(IKP key) {
            this.key = key;
        }

        public String toString() {
            return StringUtils.replacePrms(InternalKeyLock.class.getSimpleName() + ": key={0}", key); //$NON-NLS-1$
        }
    }

}
