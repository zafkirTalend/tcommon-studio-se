package org.talend.commons.utils.threading.lockerbykeyoperators;

import org.talend.commons.utils.threading.threadsafetester.IThreadSafetyOperator;

public class TryLockThenUnlockOperator extends AbstractLockerByKeyOperator implements IThreadSafetyOperator {

    @SuppressWarnings("unchecked")
    public void doOperations() throws Exception {
        int sumThreadSafeOperations = 0;
        for (int i = 0; i < nOperationsByOperator; i++) {
            if (locker.tryLock(i)) {
                incrementNotThreadSafeCounter(i);
                sumThreadSafeOperations++;
                try {
                    System.out.println(System.currentTimeMillis() + ": TryLock Timeout Locked " + i);
                    Thread.sleep(10);
                } finally {
                    System.out.println(System.currentTimeMillis() + ": TryLock Timeout Unlocking " + i);
                    try {
                        locker.unlock(i);
                    } catch (Exception e) {
                        System.out.println(System.currentTimeMillis() + ": Error when Unlocking TryLock Timeout " + i);
                        throw e;
                    }
                }
            } else {
                System.out.println(System.currentTimeMillis() + ": TryLock Timeout Failed " + i);
            }
        }
        resultContainer.sumThreadSafeOperations.getAndAdd(sumThreadSafeOperations);
    }

}
