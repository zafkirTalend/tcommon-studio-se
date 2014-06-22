// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.collections.list.SynchronizedList;
import org.apache.log4j.Logger;
import org.talend.commons.i18n.internal.Messages;
import org.talend.commons.utils.data.bean.IGetterPropertyAccessor;
import org.talend.commons.utils.data.map.MultiLazyValuesMap;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * @param <B> bean which contains the property id
 * @param <KP> type of the key/property
 */
public class Locker<B, KP> {

    private static final String UNDEFINED_CONTEXT_INFO = "UNDEFINED"; //$NON-NLS-1$

    private static Logger log = Logger.getLogger(Locker.class);

    private Map<InternalKeyLock, LockerValue> lockKeyToThreadsMap = Collections
            .synchronizedMap(new HashMap<InternalKeyLock, LockerValue>());

    private InternalKeyLock<KP> matchingKey = new InternalKeyLock<KP>();

    private Executor treadsPool;

    /**
     * 
     * DOC amaumont Locker class global comment. Detailled comment <br/>
     * 
     * @param <KP>
     */
    class InternalKeyLock<KP> {

        private String contextInfo;

        private KP key;

        /**
         * DOC amaumont InternalKeyLock constructor comment.
         * 
         * @param key2
         */
        public InternalKeyLock(KP key) {
            this.key = key;
        }

        /**
         * DOC amaumont InternalKeyLock constructor comment.
         * 
         * @param key2
         * @param contextInfo2
         */
        public InternalKeyLock(KP key, String contextInfo) {
            this(key);
            this.contextInfo = contextInfo;
        }

        /**
         * DOC amaumont InternalKeyLock constructor comment.
         */
        public InternalKeyLock() {
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

        public String toString() {
            return Messages.getString("Locker.keyContext", String.valueOf(key), contextInfo); //$NON-NLS-1$
        }

    }

    /**
     * 
     * DOC amaumont Locker class global comment. Detailled comment <br/>
     * 
     * @param <KP>
     */
    public class LockerValue {

        private String contextInfo;

        private Thread thread;

        private KP key;

        /**
         * DOC amaumont InternalKeyLock constructor comment.
         * 
         * @param thread
         * @param contextInfo
         */
        public LockerValue(Thread thread, KP key, String contextInfo) {
            this.thread = thread;
            this.key = key;
            this.contextInfo = contextInfo;
        }

        public String toString() {
            return Messages.getString("Locker.LockerValue", thread.getName(), String.valueOf(key), contextInfo); //$NON-NLS-1$
        }

        /**
         * Getter for contextInfo.
         * 
         * @return the contextInfo
         */
        public String getContextInfo() {
            return contextInfo;
        }

        /**
         * Getter for thread.
         * 
         * @return the thread
         */
        public Thread getThread() {
            return thread;
        }

        /**
         * Getter for key.
         * 
         * @return the key
         */
        public KP getKey() {
            return key;
        }

    }

    private MultiLazyValuesMap waitingThreadsByKey = new MultiLazyValuesMap(new Hashtable()) {

        @Override
        public Collection instanciateNewCollection() {
            return SynchronizedList.decorate(new ArrayList());
        }

    };

    private IGetterPropertyAccessor<B, KP> getterId;

    private static boolean verbose = false;

    private boolean allowLockWithSameThread = true;

    /**
     * DOC amaumont Locker constructor comment.
     */
    public Locker() {
        super();
    }

    /**
     * 
     * DOC amaumont Locker constructor comment.
     * 
     * @param allowLockWithSameThread default is true
     */
    public Locker(boolean allowLockWithSameThread) {
        super();
        this.allowLockWithSameThread = allowLockWithSameThread;
    }

    /**
     * DOC amaumont Locker constructor comment.
     */
    public Locker(IGetterPropertyAccessor<B, KP> getterId) {
        this();
        this.getterId = getterId;
        if (getterId == null) {
            throw new IllegalArgumentException(Messages.getString("Locker.getterIdNull")); //$NON-NLS-1$
        }
    }

    /**
     * 
     * DOC amaumont Locker constructor comment.
     * 
     * @param getterId
     * @param allowLockWithSameThread default is true
     */
    public Locker(boolean allowLockWithSameThread, IGetterPropertyAccessor<B, KP> getterId) {
        this();
        this.getterId = getterId;
        if (getterId == null) {
            throw new IllegalArgumentException("getterId can't be null");
        }
        this.allowLockWithSameThread = allowLockWithSameThread;
    }

    public synchronized boolean isLockedBean(B bean) {
        checkBean(bean);
        KP key = getterId.get(bean);
        return isLocked(key);
    }

    /**
     * DOC amaumont Comment method "isLocked".
     * 
     * @param key
     * @return
     */
    public synchronized boolean isLocked(KP key) {
        check(key);
        matchingKey.key = key;
        LockerValue lockingValue = lockKeyToThreadsMap.get(matchingKey);
        if (lockingValue == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Lock.
     * 
     * @param bean
     * @return previous lock state, true was locked
     * @throws IllegalArgumentException if bean is null
     */
    public synchronized boolean lockBean(B bean) {
        return lockBean(bean, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * Lock.
     * 
     * @param bean
     * @return previous lock state, true was locked
     * @throws IllegalArgumentException if bean is null
     */
    public synchronized boolean lockBean(B bean, String contextInfo) {
        checkBean(bean);
        KP key = getterId.get(bean);
        return lock(key, contextInfo);
    }

    /**
     * DOC amaumont Comment method "lock".
     * 
     * @param key
     * @return
     */
    public boolean lock(KP key) {
        return lock(key, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * DOC amaumont Comment method "lock".
     * 
     * @param key
     * @return
     */
    public boolean lock(KP key, String contextInfo) {
        check(key);
        if (Locker.verbose) {
            log.info(Messages.getString("Locker.lockKeyContext", Thread.currentThread().toString(), key, contextInfo)); //$NON-NLS-1$
        }
        LockerValue valueLock = lockKeyToThreadsMap.put(new InternalKeyLock<KP>(key, contextInfo), new LockerValue(Thread
                .currentThread(), key, contextInfo));
        if (valueLock == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Lock if it is already unlocked.
     * 
     * @param bean
     * @return true if lock has been done or current thread has already locked the same bean, else false.
     * @throws IllegalArgumentException if bean is null
     */
    public synchronized boolean lockIfUnlockedBean(B bean) {
        return lockIfUnlockedBean(bean, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * Lock if it is already unlocked.
     * 
     * @param bean
     * @return true if lock has been done or current thread has already locked the same bean, else false.
     * @throws IllegalArgumentException if bean is null
     */
    public synchronized boolean lockIfUnlockedBean(B bean, String contextInfo) {
        checkBean(bean);
        if (!isLockedBean(bean)) {
            lockBean(bean, contextInfo);
            return true;
        } else {
            KP key = getterId.get(bean);
            matchingKey.key = key;
            LockerValue valueLock = lockKeyToThreadsMap.get(matchingKey);
            if (allowLockWithSameThread && valueLock != null && Thread.currentThread() == valueLock.thread) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Lock if it is already unlocked.
     * 
     * @param bean
     * @return true if lock has been done or current thread has already locked the same bean, else false.
     * @throws IllegalArgumentException if bean is null
     */
    public synchronized boolean lockIfUnlocked(KP key) {
        return lockIfUnlocked(key, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * Lock if it is already unlocked.
     * 
     * @param bean
     * @return true if lock has been done or current thread has already locked the same bean, else false.
     * @throws IllegalArgumentException if bean is null
     */
    public synchronized boolean lockIfUnlocked(KP key, String contextInfo) {
        check(key);
        if (!isLocked(key)) {
            lock(key, contextInfo);
            return true;
        } else {
            matchingKey.key = key;
            LockerValue valueLock = lockKeyToThreadsMap.get(matchingKey);
            if (allowLockWithSameThread && valueLock != null && Thread.currentThread() == valueLock.thread) {
                // System.out.println("Same thread");
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * DOC amaumont Comment method "check".
     * 
     * @param bean
     */
    private void checkBean(B bean) {
        if (bean == null) {
            throw new IllegalArgumentException(Messages.getString("Locker.beanNull")); //$NON-NLS-1$
        }
    }

    /**
     * DOC amaumont Comment method "check".
     * 
     * @param key
     */
    private void check(KP key) {
        if (key == null) {
            throw new IllegalArgumentException(Messages.getString("Locker.keyNull")); //$NON-NLS-1$
        }
    }

    /**
     * Unlock.
     * 
     * @param bean
     * @return true unlock has been done, else false if no lock was exist
     * @throws IllegalArgumentException if bean is null
     */
    public boolean unlockBean(B bean) {
        return unlockBean(bean, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * Unlock.
     * 
     * @param bean
     * @return true unlock has been done, else false if no lock was exist
     * @throws IllegalArgumentException if bean is null
     */
    public synchronized boolean unlockBean(B bean, String contextInfo) {
        if (bean == null) {
            return false;
        }
        checkBean(bean);
        KP key = getterId.get(bean);
        return unlock(key);
    }

    /**
     * DOC amaumont Comment method "unlock".
     * 
     * @param key
     * @return
     */
    public boolean unlock(KP key) {
        return unlock(key, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * DOC amaumont Comment method "unlock".
     * 
     * @param key
     * @return
     */
    public synchronized boolean unlock(KP key, String contextInfo) {
        check(key);
        if (Locker.verbose) {
            log.info(Messages.getString("Locker.unlockeyContext", Thread.currentThread().toString(), key, contextInfo)); //$NON-NLS-1$
        }
        matchingKey.key = key;
        LockerValue valueLock = lockKeyToThreadsMap.remove(matchingKey);
        List<Thread> waitingThreads = (List<Thread>) waitingThreadsByKey.getCollection(key);
        if (waitingThreads != null && waitingThreads.size() > 0) {
            synchronized (waitingThreads.get(0)) {
                waitingThreads.get(0).notify();
            }
        }
        if (valueLock == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * DOC amaumont Comment method "waitForLockBean".
     * 
     * @param executionTask
     * @param idLockForceInitCheck
     */
    public boolean waitForLockBean(B bean) throws InterruptedException {
        return waitForLockBean(bean, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * Lock if it is unlocked, else waiting for unlocked to lock.
     * 
     * @param bean
     * @return true if thread has wait a time, else false.
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     */
    public boolean waitForLockBean(B bean, String contextInfo) throws InterruptedException {
        checkBean(bean);
        if (!lockIfUnlockedBean(bean, contextInfo)) {
            synchronized (Thread.currentThread()) {

                waitingThreadsByKey.put(getterId.get(bean), Thread.currentThread());
                try {
                    if (Locker.verbose) {
                        log.info(Messages.getString("Locker.waitForUnlock1", Thread.currentThread().toString() //$NON-NLS-1$
                                , getterId.get(bean), contextInfo));
                    }
                    Thread.currentThread().wait();
                    if (Locker.verbose) {
                        log.info(Messages.getString("Locker.waitEndForUnlock1", Thread.currentThread().toString() //$NON-NLS-1$
                                , getterId.get(bean), contextInfo));
                    }
                    waitForLockBean(bean);
                } catch (InterruptedException e) {
                    throw e;
                } finally {
                    waitingThreadsByKey.removeValue(getterId.get(bean), Thread.currentThread());
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Lock if it is unlocked, else waiting for unlocked to lock.
     * 
     * @param bean
     * @return true if thread has wait a time, else false.
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     */
    public boolean waitForLock(KP key) throws InterruptedException {
        return waitForLock(key, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * Lock if it is unlocked, else waiting for unlocked to lock.
     * 
     * @param key
     * @param waitTimeMax the maximum time to wait in milliseconds.
     * @return true if thread has wait a time, else false.
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     */
    public boolean waitForLock(KP key, Long waitTimeMax) throws InterruptedException {
        return waitForLock(key, waitTimeMax, UNDEFINED_CONTEXT_INFO);
    }

    /**
     * Lock if it is unlocked, else waiting for unlocked to lock.
     * 
     * @param bean
     * @return true if thread has wait a time, else false.
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     */
    public boolean waitForLock(KP key, String contextInfo) throws InterruptedException {
        return waitForLock(key, null, contextInfo);
    }

    /**
     * Get locker.
     * 
     * @param bean
     * @return locker value.
     */
    public LockerValue getLocker(KP key) {
        check(key);
        matchingKey.key = key;
        LockerValue lockingValue = lockKeyToThreadsMap.get(matchingKey);
        return lockingValue;
    }

    /**
     * Lock if it is unlocked, else waiting for unlocked to lock.
     * 
     * @param key
     * @param waitTimeMax the maximum time to wait in milliseconds.
     * @return true if thread has wait a time, else false.
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     */
    public boolean waitForLock(KP key, final Long waitTimeMax, String contextInfo) throws InterruptedException {
        check(key);
        if (!lockIfUnlocked(key, contextInfo)) {

            waitingThreadsByKey.put(key, Thread.currentThread());
            try {
                if (Locker.verbose) {
                    log.info(Messages.getString("Locker.waitForUnlock2", Thread.currentThread().toString() //$NON-NLS-1$
                            , key, contextInfo));
                }
                final Thread mainThread = Thread.currentThread();
                if (waitTimeMax != null) {
                    if (treadsPool == null) {
                        initThreadsPool();
                    }

                    final Thread[] threadInterruptor = new Thread[1];
                    final boolean[] threadAlreadyNotified = new boolean[1];

                    treadsPool.execute(new Runnable() {

                        public void run() {
                            Thread internalThread = Thread.currentThread();
                            threadInterruptor[0] = internalThread;
                            synchronized (Thread.currentThread()) {
                                try {
                                    Thread.currentThread().wait(waitTimeMax);
                                    if (!threadAlreadyNotified[0]) {
                                        mainThread.interrupt();
                                    }
                                } catch (InterruptedException e) {
                                }
                            }
                        }

                    });
                    try {
                        synchronized (mainThread) {
                            mainThread.wait();
                            if (threadInterruptor[0] != null) {
                                threadInterruptor[0].interrupt();
                            }
                        }
                    } catch (InterruptedException e) {
                        throw e;
                    } finally {
                        threadAlreadyNotified[0] = true;
                    }
                } else {
                    synchronized (mainThread) {
                        mainThread.wait();
                    }
                }
                if (Locker.verbose) {
                    log.info(Messages.getString("Locker.waitEndForUnlock2", Thread.currentThread().toString(), key //$NON-NLS-1$
                            , contextInfo));
                }
                waitForLock(key, contextInfo);
            } catch (InterruptedException e) {
                throw e;
            } finally {
                waitingThreadsByKey.removeValue(key, Thread.currentThread());
            }
            return true;
        }
        return false;
    }

    private void initThreadsPool() {
        treadsPool = Executors.newCachedThreadPool(new ThreadFactory() {

            public Thread newThread(Runnable r) {
                Thread newThread = Executors.defaultThreadFactory().newThread(r);
                newThread.setName(newThread.getName() + "_" + Locker.class.getSimpleName()); //$NON-NLS-1$
                return newThread;
            }

        });
    }

    public synchronized void shutdown() {
        Object[] values = waitingThreadsByKey.values().toArray(new Object[0]);
        for (int j = 0; j < values.length; j++) {
            Object object = values[j];
            if (object instanceof List) {
                List<Thread> list = (List<Thread>) object;
                for (Thread thread : list) {
                    try {
                        ((Thread) thread).interrupt();
                    } catch (SecurityException e) {
                        log.warn(e.getMessage(), e);
                    }
                }
            } else {
                try {
                    ((Thread) object).interrupt();
                } catch (SecurityException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    }

    public static void setVerbose(boolean verbose) {
        Locker.verbose = verbose;
    }

    public String toString() {
        return Messages.getString("Locker.lockKeyThreadMap", String.valueOf(lockKeyToThreadsMap) //$NON-NLS-1$
                , String.valueOf(waitingThreadsByKey));
    }

    public static void main(String[] args) {

        /**
         * 
         * DOC amaumont Locker class global comment. Detailled comment <br/>
         * 
         */
        class LabelValue {

            private Integer id;

            private String label;

            /**
             * DOC amaumont LabelValue constructor comment.
             * 
             * @param id
             * @param label
             */
            public LabelValue(Integer id, String label) {
                super();
                this.id = id;
                this.label = label;
            }

            /**
             * Getter for id.
             * 
             * @return the id
             */
            public Integer getId() {
                return this.id;
            }

            /**
             * Sets the id.
             * 
             * @param id the id to set
             */
            public void setId(Integer id) {
                this.id = id;
            }

            /**
             * Getter for label.
             * 
             * @return the label
             */
            public String getLabel() {
                return this.label;
            }

            /**
             * Sets the label.
             * 
             * @param label the label to set
             */
            public void setLabel(String label) {
                this.label = label;
            }

        }

        IGetterPropertyAccessor<LabelValue, Integer> getterPropertyAccessor = new IGetterPropertyAccessor<LabelValue, Integer>() {

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.commons.utils.data.bean.IGetterPropertyAccessor#get(java.lang.Object)
             */
            public Integer get(LabelValue bean) {
                return bean.getId();
            }

        };

        final Locker<LabelValue, Integer> locker = new Locker<LabelValue, Integer>(getterPropertyAccessor);

        final LabelValue lb1 = new LabelValue(1, "LableValue1"); //$NON-NLS-1$

        new Thread() {

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                locker.lockBean(lb1);
                try {
                    Thread.sleep(10000);
                    locker.unlockBean(lb1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();

        new Thread() {

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    locker.waitForLockBean(lb1);
                    Thread.sleep(10000);
                    locker.unlockBean(lb1);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

        }.start();

    }

}
