package org.talend.commons.utils.threading.lockerbykey;

import java.util.ArrayList;
import java.util.List;

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

    private List<LockerValueHandler> handlers;

    private StackTraceElement[] stackTraceOfLocker;

    private long lockedTime;

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

    /**
     * 
     * Method "addHandler". Add handler to internal list.
     * 
     * @param handler
     */
    public synchronized void addHandler(LockerValueHandler handler) {
        if (handlers == null) {
            handlers = new ArrayList<LockerValueHandler>();
        }
        handlers.add(handler);
    }

    /**
     * 
     * Method "getHandlerAndRemove".
     * 
     * Return the next available handler then remove it from internal list, else null if not exist.
     * 
     * @return the next available handler, else null if not exist
     */
    public synchronized LockerValueHandler getHandler() {
        if (handlers != null && handlers.size() > 0) {
            LockerValueHandler lockerValueHandler = handlers.get(0);
            return lockerValueHandler;
        }
        return null;
    }

    /**
     * 
     * Method "getHandlerAndRemove".
     * 
     * Return the next available handler then remove it from internal list, else null if not exist.
     * 
     * @return the next available handler, else null if not exist
     */
    public synchronized LockerValueHandler getHandlerAndRemove() {
        if (handlers != null && handlers.size() > 0) {
            LockerValueHandler lockerValueHandler = handlers.get(0);
            handlers.remove(0);
            return lockerValueHandler;
        }
        return null;
    }

    /**
     * Getter for stackTraceElements.
     * 
     * @return the stackTraceElements
     */
    public StackTraceElement[] getStackTraceOfLocker() {
        return stackTraceOfLocker;
    }

    /**
     * Sets the stackTraceElements.
     * 
     * @param stackTraceElements the stackTraceElements to set
     */
    public void setStackTraceOfLocker(StackTraceElement[] stackTraceElements) {
        this.stackTraceOfLocker = stackTraceElements;
    }

    /**
     * Getter for lockedTime.
     * 
     * @return the lockedTime
     */
    public long getLockedTime() {
        return lockedTime;
    }

    /**
     * Sets the lockedTime.
     * 
     * @param lockedTime the lockedTime to set
     */
    public void setLockedTime(long lockedTime) {
        this.lockedTime = lockedTime;
    }

}
