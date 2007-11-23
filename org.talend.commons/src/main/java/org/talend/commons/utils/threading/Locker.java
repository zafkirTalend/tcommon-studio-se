// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.collections.list.SynchronizedList;
import org.apache.log4j.Logger;
import org.talend.commons.utils.data.bean.IGetterPropertyAccessor;
import org.talend.commons.utils.data.map.MultiLazyValuesMap;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * @param <B> bean which contains the property id
 * @param <KP> type of the key/property
 */
public class Locker<B, KP> {

    private static Logger log = Logger.getLogger(Locker.class);

    private HashMap<KP, Thread> lockKeyToThreadsMap = new HashMap<KP, Thread>();

    private MultiLazyValuesMap waitingThreadsByKey = new MultiLazyValuesMap(new Hashtable()) {

        @Override
        public Collection instanciateNewCollection() {
            return SynchronizedList.decorate(new ArrayList());
        }

    };

    private IGetterPropertyAccessor<B, KP> getterId;

    private boolean verbose;

    private boolean detectSameThread;

    /**
     * DOC amaumont Locker constructor comment.
     */
    public Locker(boolean detectSameThread) {
        super();
        this.detectSameThread = detectSameThread;
    }

    /**
     * DOC amaumont Locker constructor comment.
     */
    public Locker() {
        super();
    }

    /**
     * DOC amaumont Locker constructor comment.
     */
    public Locker(boolean detectSameThread, IGetterPropertyAccessor<B, KP> getterId) {
        this(getterId);
        this.detectSameThread = detectSameThread;
    }

    /**
     * DOC amaumont Locker constructor comment.
     */
    public Locker(IGetterPropertyAccessor<B, KP> getterId) {
        this();
        this.getterId = getterId;
        if (getterId == null) {
            throw new IllegalArgumentException("getterId can't be null");
        }
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
        Thread lockingThread = lockKeyToThreadsMap.get(key);
        if (lockingThread == null) {
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
        checkBean(bean);
        KP key = getterId.get(bean);
        return lock(key);
    }

    /**
     * DOC amaumont Comment method "lock".
     * 
     * @param key
     * @return
     */
    public boolean lock(KP key) {
        check(key);
        if (verbose) {
            log.info("Locking (" + Thread.currentThread().toString() + ") key=" + key + "...");
        }
        Thread thread = lockKeyToThreadsMap.put(key, Thread.currentThread());
        if (thread == null) {
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
        checkBean(bean);
        if (!isLockedBean(bean)) {
            lockBean(bean);
            return true;
        } else {
            KP key = getterId.get(bean);
            Thread thread = lockKeyToThreadsMap.get(key);
            if (detectSameThread && Thread.currentThread() == thread) {
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
        check(key);
        if (!isLocked(key)) {
            lock(key);
            return true;
        } else {
            Thread thread = lockKeyToThreadsMap.get(key);
            if (detectSameThread && Thread.currentThread() == thread) {
                System.out.println("Same thread");
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
            throw new IllegalArgumentException("bean can't be null");
        }
    }

    /**
     * DOC amaumont Comment method "check".
     * 
     * @param key
     */
    private void check(KP key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
    }

    /**
     * Unlock.
     * 
     * @param bean
     * @return true unlock has been done, else false if no lock was exist
     * @throws IllegalArgumentException if bean is null
     */
    public synchronized boolean unlockBean(B bean) {
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
        check(key);
        if (verbose) {
            log.info("Unlocking (" + Thread.currentThread().toString() + ") key=" + key + "...");
        }
        Thread thread = lockKeyToThreadsMap.remove(key);
        List<Thread> waitingThreads = (List<Thread>) waitingThreadsByKey.getCollection(key);
        if (waitingThreads != null && waitingThreads.size() > 0) {
            synchronized (waitingThreads.get(0)) {
                waitingThreads.get(0).notify();
            }
        }
        if (thread == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Lock if it is unlocked, else waiting for unlocked to lock.
     * 
     * @param bean
     * @return true if thread has wait a time, else false.
     * @throws InterruptedException
     * @throws IllegalArgumentException if bean is null
     */
    public boolean waitForLockBean(B bean) throws InterruptedException {
        checkBean(bean);
        if (!lockIfUnlockedBean(bean)) {
            synchronized (Thread.currentThread()) {

                waitingThreadsByKey.put(getterId.get(bean), Thread.currentThread());
                try {
                    if (verbose) {
                        log.info("Waiting for unlocked (" + Thread.currentThread().toString() + ") key="
                                + getterId.get(bean) + "...");
                    }
                    Thread.currentThread().wait();
                    if (verbose) {
                        log.info("Waiting ended (" + Thread.currentThread().toString() + ") key=" + getterId.get(bean)
                                + "...");
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
        check(key);
        if (!lockIfUnlocked(key)) {
            synchronized (Thread.currentThread()) {

                waitingThreadsByKey.put(key, Thread.currentThread());
                try {
                    if (verbose) {
                        log.info("Waiting for unlocked (" + Thread.currentThread().toString() + ") key=" + key + "...");
                    }
                    Thread.currentThread().wait();
                    if (verbose) {
                        log.info("Waiting ended (" + Thread.currentThread().toString() + ") key=" + key + "...");
                    }
                    waitForLock(key);
                } catch (InterruptedException e) {
                    throw e;
                } finally {
                    waitingThreadsByKey.removeValue(key, Thread.currentThread());
                }
            }
            return true;
        }
        return false;
    }

    public synchronized void shutdown() {
        Collection<Thread> values = waitingThreadsByKey.values();
        for (Object object : values) {
            if (object instanceof List) {
                List<Thread> list = (List<Thread>) object;
                for (Thread thread : list) {
                    try {
                        ((Thread) object).interrupt();
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

        final LabelValue lb1 = new LabelValue(1, "LableValue1");

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
