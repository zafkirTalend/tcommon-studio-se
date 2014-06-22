package org.talend.commons.utils.threading.lockerbykey;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * CustomReentrantLock class.
 * 
 * @see java.util.concurrent.locks.ReentrantLock
 */
public class CustomReentrantLock extends ReentrantLock {

    private static final long serialVersionUID = 3730576759454516775L;

    public CustomReentrantLock() {
        super();
    }

    public CustomReentrantLock(boolean fair) {
        super(fair);
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.locks.ReentrantLock#getQueuedThreads()
     */
    @Override
    public Collection<Thread> getQueuedThreads() {
        return super.getQueuedThreads();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.concurrent.locks.ReentrantLock#getOwner()
     */
    @Override
    public Thread getOwner() {
        return super.getOwner();
    }
    
}

