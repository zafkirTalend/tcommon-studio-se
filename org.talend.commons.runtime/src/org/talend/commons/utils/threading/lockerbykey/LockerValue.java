package org.talend.commons.utils.threading.lockerbykey;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.talend.commons.utils.StringUtils;

/**
 * 
 * LockerValue.<br/>
 * 
 * @param <VKP> key
 */
public class LockerValue<VKP> {

    private CustomReentrantLock lock;

    private VKP key;

    private List<Future<Boolean>> futureTasks;

    /**
     * LockerValue constructor.
     * 
     * @param thread
     * @param contextInfo
     * @param fair
     */
    public LockerValue(VKP key, boolean fair) {
        this.lock = new CustomReentrantLock(fair);
        this.key = key;
    }

    public String toString() {
        return StringUtils.replacePrms("LockerValue: key={0}, lock={1}", String.valueOf(key), lock.toString()); //$NON-NLS-1$
    }

    /**
     * Getter for key.
     * 
     * @return the key
     */
    public VKP getKey() {
        return key;
    }

    /**
     * Getter for lock.
     * 
     * @return the lock
     */
    public CustomReentrantLock getLock() {
        return lock;
    }

    public synchronized void addFuture(Future<Boolean> futureTask) {
        if (futureTasks == null) {
            futureTasks = new ArrayList<Future<Boolean>>();
        }
        futureTasks.add(futureTask);
    }

    public synchronized Future<Boolean> getFuture() {
        if (futureTasks.size() > 0) {
            return futureTasks.get(0);
        }
        return null;
    }

}
