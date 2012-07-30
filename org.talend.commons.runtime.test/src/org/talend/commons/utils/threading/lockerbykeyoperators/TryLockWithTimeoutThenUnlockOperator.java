package org.talend.commons.utils.threading.lockerbykeyoperators;

import org.talend.commons.utils.threading.threadsafetester.IThreadSafetyOperator;

public class TryLockWithTimeoutThenUnlockOperator extends AbstractLockerByKeyOperator implements IThreadSafetyOperator {

    @SuppressWarnings("unchecked")
    public void doOperations() throws Exception {
        int sumThreadSafeOperations = 0;
        for (int i = 0; i < nOperationsByOperator; i++) {
            if (locker.tryLock(i, i)) {
                incrementNotThreadSafeCounter(i);
                sumThreadSafeOperations++;
                try {
                    System.out.println(System.currentTimeMillis() + ": TryLock Locked " + i);
                    Thread.sleep(10);
                } finally {
                    System.out.println(System.currentTimeMillis() + ": TryLock Unlocking " + i);
                    try {
                        locker.unlock(i);
                    } catch (Exception e) {
                        System.out.println(System.currentTimeMillis() + ": Error when Unlocking TryLock " + i);
                        throw e;
                    }
                }
            } else {
                System.out.println(System.currentTimeMillis() + ": TryLock Failed " + i);
            }
        }
        resultContainer.sumThreadSafeOperations.getAndAdd(sumThreadSafeOperations);
    }

}
