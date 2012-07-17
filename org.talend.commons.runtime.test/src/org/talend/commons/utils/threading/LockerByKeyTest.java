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
package org.talend.commons.utils.threading;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.talend.commons.utils.threading.LockerByKey.LockerValue;
import org.talend.commons.utils.threading.lockerbykeyoperators.AbstractLockerByKeyOperator;
import org.talend.commons.utils.threading.lockerbykeyoperators.LockThenUnlockOperator;
import org.talend.commons.utils.threading.lockerbykeyoperators.ResultContainer;
import org.talend.commons.utils.threading.lockerbykeyoperators.TryLockThenUnlockOperator;
import org.talend.commons.utils.threading.lockerbykeyoperators.TryLockWithTimeoutThenUnlockOperator;
import org.talend.commons.utils.threading.threadsafetester.AbstractThreadSafetyTester;

public class LockerByKeyTest {

    private static final int WAIT_THREAD_STARTED = 100;

    private static final int WAIT_JOIN = 100;

    protected LockerByKey createLockerInstance() {
        // default implementation when running this TestCase
        return new LockerByKey();
    }

    class LockInterruptiblyThread extends Thread {

        private LockerByKey locker;

        Integer key;

        public Throwable throwable;

        public LockInterruptiblyThread(LockerByKey locker, Integer key) {
            super();
            this.locker = locker;
            this.key = key;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            try {
                locker.lockInterruptibly(key);
            } catch (Throwable e) {
                throwable = e;
            }
        }

    };

    class LockInterruptiblyTwiceThread extends Thread {

        private LockerByKey locker;

        Integer key;

        public Throwable throwable;

        public LockInterruptiblyTwiceThread(LockerByKey locker, Integer key) {
            super();
            this.locker = locker;
            this.key = key;
        }

        @Override
        public void run() {
            try {
                locker.lockInterruptibly(key);
                locker.lockInterruptibly(key);
            } catch (Throwable e) {
                throwable = e;
            }
        }

    };

    class UnlockThread extends Thread {

        private LockerByKey locker;

        Integer key;

        public Throwable throwable;

        public UnlockThread(LockerByKey locker, Integer key) {
            super();
            this.locker = locker;
            this.key = key;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            try {
                locker.unlock(key);
            } catch (Throwable e) {
                throwable = e;
            }
        }

    };

    @SuppressWarnings("unchecked")
    @Test(timeout = 2000)
    // @Test()
    public void testIsLocked() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;

        boolean isLocked = locker.isLocked(keyOne);
        assertThat(isLocked, is(false));

        locker.lockInterruptibly(keyOne);

        isLocked = locker.isLocked(keyOne);
        assertThat(isLocked, is(true));

        boolean hasUnlocked = locker.unlock(keyOne);
        assertThat(hasUnlocked, is(true));

        isLocked = locker.isLocked(keyOne);
        assertThat(isLocked, is(false));
    }

    @SuppressWarnings("unchecked")
    @Test(timeout = 2000)
    // @Test()
    public void testCleanDisabled() throws Throwable {
        final LockerByKey locker = new LockerByKey(true, 0);

        final int keyOne = 1;

        boolean isLocked = locker.isLocked(keyOne);
        assertThat(isLocked, is(false));

        for (int i = 0; i < 1000; i++) {
            locker.lockInterruptibly(i);

            isLocked = locker.isLocked(i);
            assertThat(isLocked, is(true));

            boolean hasUnlocked = locker.unlock(i);
            assertThat(hasUnlocked, is(true));

            isLocked = locker.isLocked(i);
            assertThat(isLocked, is(false));
        }

        for (int i = 0; i < 1000; i++) {
            assertThat(locker.getLocker(i), is(notNullValue()));
        }

    }

    @SuppressWarnings("unchecked")
    @Test(timeout = 2000)
    // @Test()
    public void testCleanEnabledDefault() throws Throwable {
        final LockerByKey locker = new LockerByKey();

        final int keyOne = 1;

        boolean isLocked = locker.isLocked(keyOne);
        assertThat(isLocked, is(false));

        for (int i = 0; i < 1000; i++) {
            locker.lockInterruptibly(i);

            isLocked = locker.isLocked(i);
            assertThat(isLocked, is(true));

            boolean hasUnlocked = locker.unlock(i);
            assertThat(hasUnlocked, is(true));

            isLocked = locker.isLocked(i);
            assertThat(isLocked, is(false));
        }

        /*
         * Be warn, the last is not removed by the auto clean, need to call clean again to clear all.
         */
        for (int i = 0; i < 999; i++) {
            assertThat(locker.getLocker(i), is(nullValue()));
        }

    }

    @SuppressWarnings("unchecked")
    @Test(timeout = 2000)
    // @Test()
    public void testCleanEnabledProvidedPeriod() throws Throwable {
        final LockerByKey locker = new LockerByKey(true, 600);

        final int keyOne = 1;

        boolean isLocked = locker.isLocked(keyOne);
        assertThat(isLocked, is(false));

        for (int i = 0; i < 1000; i++) {
            locker.lockInterruptibly(i);

            isLocked = locker.isLocked(i);
            assertThat(isLocked, is(true));

            boolean hasUnlocked = locker.unlock(i);
            assertThat(hasUnlocked, is(true));

            isLocked = locker.isLocked(i);
            assertThat(isLocked, is(false));
        }

        /*
         * Be warn, the last is not removed by the auto clean, need to call clean again to clear all.
         */
        for (int i = 0; i < 1000; i++) {
            if (i < 599) {
                assertThat(locker.getLocker(i), is(nullValue()));
            } else {
                assertThat(locker.getLocker(i), is(notNullValue()));
            }
        }

    }

    @SuppressWarnings("unchecked")
    @Test(timeout = 2000)
    // @Test()
    public void testUnlock_withGetLockerValue() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;

        LockerValue lockerValue = locker.getLocker(keyOne);
        assertThat(lockerValue, is(nullValue()));

        locker.lockInterruptibly(keyOne);

        LockerValue lockerValue1 = locker.getLocker(keyOne);
        assertThat(lockerValue1, is(notNullValue()));

        boolean hasUnlocked = locker.unlock(keyOne);
        assertThat(hasUnlocked, is(true));

        LockerValue lockerValue2 = locker.getLocker(keyOne);
        assertThat(lockerValue2, is(lockerValue1));

        locker.clean();
        LockerValue lockerValue3 = locker.getLocker(keyOne);
        assertThat(lockerValue3, is(nullValue()));

    }

    @Test(timeout = 2000)
    // @Test()
    public void testTryLock_LockTwiceWithSameKey() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;
        LockInterruptiblyThread threadWaitForLockKeyOne_first = new LockInterruptiblyThread(locker, keyOne);
        threadWaitForLockKeyOne_first.start();
        threadWaitForLockKeyOne_first.join(WAIT_JOIN);
        assertThat(threadWaitForLockKeyOne_first.isAlive(), is(false));
        if (threadWaitForLockKeyOne_first.throwable != null) {
            throw threadWaitForLockKeyOne_first.throwable;
        }

        LockInterruptiblyThread threadWaitForLockKeyOne_second = new LockInterruptiblyThread(locker, keyOne);
        threadWaitForLockKeyOne_second.start();
        Thread.sleep(WAIT_THREAD_STARTED);
        assertThat(threadWaitForLockKeyOne_second.isAlive(), is(true));
        threadWaitForLockKeyOne_second.interrupt();
        threadWaitForLockKeyOne_second.join(WAIT_JOIN);
        assertThat(threadWaitForLockKeyOne_second.isAlive(), is(false));
        assertThat(threadWaitForLockKeyOne_second.throwable, is(notNullValue()));
        assertThat(threadWaitForLockKeyOne_second.throwable, is(instanceOf(InterruptedException.class)));
    }

    @Test(timeout = 2000)
    // @Test()
    public void testTryLock_LockTwiceWithSamePrimitiveValueButDifferentInteger() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;
        final Integer keyOneInteger1 = new Integer(keyOne);
        LockInterruptiblyThread threadWaitForLockKeyOne_first = new LockInterruptiblyThread(locker, keyOneInteger1);
        threadWaitForLockKeyOne_first.start();
        threadWaitForLockKeyOne_first.join(WAIT_JOIN);
        assertThat(threadWaitForLockKeyOne_first.isAlive(), is(false));
        if (threadWaitForLockKeyOne_first.throwable != null) {
            throw threadWaitForLockKeyOne_first.throwable;
        }

        final Integer keyOneInteger2 = new Integer(keyOne);
        LockInterruptiblyThread threadWaitForLockKeyOne_second = new LockInterruptiblyThread(locker, keyOneInteger2);
        threadWaitForLockKeyOne_second.start();
        Thread.sleep(WAIT_THREAD_STARTED);
        assertThat(threadWaitForLockKeyOne_second.isAlive(), is(true));
        threadWaitForLockKeyOne_second.interrupt();
        threadWaitForLockKeyOne_second.join(WAIT_JOIN);
        assertThat(threadWaitForLockKeyOne_second.isAlive(), is(false));
        assertThat(threadWaitForLockKeyOne_second.throwable, is(notNullValue()));
        assertThat(threadWaitForLockKeyOne_second.throwable, is(instanceOf(InterruptedException.class)));
    }

    @Test(timeout = 2000)
    public void testTryLock_LockTwiceWithDifferentKeys() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;
        LockInterruptiblyThread threadWaitForLockKeyOne_first = new LockInterruptiblyThread(locker, keyOne);
        threadWaitForLockKeyOne_first.start();
        threadWaitForLockKeyOne_first.join(WAIT_JOIN);
        assertThat(threadWaitForLockKeyOne_first.isAlive(), is(false));
        if (threadWaitForLockKeyOne_first.throwable != null) {
            throw threadWaitForLockKeyOne_first.throwable;
        }

        final int keyTwo = 2;
        LockInterruptiblyThread threadLockKeyOne_second = new LockInterruptiblyThread(locker, keyTwo);
        threadLockKeyOne_second.start();
        threadLockKeyOne_second.join(WAIT_JOIN);
        assertThat(threadLockKeyOne_second.isAlive(), is(false));
        if (threadWaitForLockKeyOne_first.throwable != null) {
            throw threadWaitForLockKeyOne_first.throwable;
        }

    }

    @Test(timeout = 2000)
    // @Test
    public void testTryLock_ThenUnlockFromOtherThread() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;
        LockInterruptiblyThread threadWaitForLockKeyOne = new LockInterruptiblyThread(locker, keyOne);
        threadWaitForLockKeyOne.start();
        threadWaitForLockKeyOne.join(WAIT_JOIN);
        assertThat(threadWaitForLockKeyOne.isAlive(), is(false));
        if (threadWaitForLockKeyOne.throwable != null) {
            throw threadWaitForLockKeyOne.throwable;
        }

        UnlockThread threadUnlockKeyOne = new UnlockThread(locker, keyOne);
        threadUnlockKeyOne.start();
        threadUnlockKeyOne.join(WAIT_JOIN);
        assertThat(threadUnlockKeyOne.isAlive(), is(false));
        assertThat(threadUnlockKeyOne.throwable, is(instanceOf(IllegalMonitorStateException.class)));
    }

    @Test(timeout = 2000)
    // @Test
    public void testTryLock_waitForLockTwiceFromFirstLockerThread() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;
        LockInterruptiblyTwiceThread threadWaitForLockTwiceKeyOne = new LockInterruptiblyTwiceThread(locker, keyOne);
        threadWaitForLockTwiceKeyOne.start();
        threadWaitForLockTwiceKeyOne.join(WAIT_JOIN);
        assertThat(threadWaitForLockTwiceKeyOne.isAlive(), is(false));
        if (threadWaitForLockTwiceKeyOne.throwable != null) {
            throw threadWaitForLockTwiceKeyOne.throwable;
        }

    }

    @Test(timeout = 2000)
    // @Test()
    public void testTryLock_waitForLockTwiceFromDifferentThread() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;
        LockInterruptiblyThread threadWaitForLock_threadOne = new LockInterruptiblyThread(locker, keyOne);
        threadWaitForLock_threadOne.start();
        threadWaitForLock_threadOne.join(WAIT_JOIN);
        assertThat(threadWaitForLock_threadOne.isAlive(), is(false));
        if (threadWaitForLock_threadOne.throwable != null) {
            throw threadWaitForLock_threadOne.throwable;
        }

        LockInterruptiblyThread threadWaitForLock_threadTwo = new LockInterruptiblyThread(locker, keyOne);
        threadWaitForLock_threadTwo.start();
        Thread.sleep(WAIT_THREAD_STARTED);
        threadWaitForLock_threadTwo.interrupt();
        assertThat(threadWaitForLock_threadTwo.isAlive(), is(true));
        threadWaitForLock_threadTwo.join(WAIT_JOIN);
        assertThat(threadWaitForLock_threadTwo.isAlive(), is(false));
        assertThat(threadWaitForLock_threadTwo.throwable, is(notNullValue()));
        assertThat(threadWaitForLock_threadTwo.throwable, is(instanceOf(InterruptedException.class)));
    }

    @Test(timeout = 2000)
    // @Test()
    public void testTryLock_waitForLockTwiceFromSameThread_forbidReentrantLockFromOtherThread() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;
        LockInterruptiblyTwiceThread threadWaitForLockTwiceKeyOne = new LockInterruptiblyTwiceThread(locker, keyOne);
        threadWaitForLockTwiceKeyOne.start();
        Thread.sleep(WAIT_THREAD_STARTED);
        assertThat(threadWaitForLockTwiceKeyOne.isAlive(), is(false));
        assertThat(threadWaitForLockTwiceKeyOne.throwable, is(nullValue()));

        // assertThat(threadWaitForLockTwiceKeyOne.throwable, is(notNullValue()));
        // assertThat(threadWaitForLockTwiceKeyOne.throwable, is(instanceOf(InterruptedException.class)));
    }

    @Test(timeout = 2000)
    // @Test()
    public void testTryLock_waitForLockTwiceFromDifferentThread_forbidReentrantLockFromLockerThread() throws Throwable {
        final LockerByKey locker = createLockerInstance();

        final int keyOne = 1;
        LockInterruptiblyThread threadWaitForLock_threadOne = new LockInterruptiblyThread(locker, keyOne);
        threadWaitForLock_threadOne.start();
        threadWaitForLock_threadOne.join(WAIT_JOIN);
        assertThat(threadWaitForLock_threadOne.isAlive(), is(false));
        if (threadWaitForLock_threadOne.throwable != null) {
            throw threadWaitForLock_threadOne.throwable;
        }

        LockInterruptiblyThread threadWaitForLock_threadTwo = new LockInterruptiblyThread(locker, keyOne);
        threadWaitForLock_threadTwo.start();
        Thread.sleep(WAIT_THREAD_STARTED);
        assertThat(threadWaitForLock_threadTwo.isAlive(), is(true));
        threadWaitForLock_threadTwo.interrupt();
        threadWaitForLock_threadTwo.join(WAIT_JOIN);
        assertThat(threadWaitForLock_threadTwo.isAlive(), is(false));
        assertThat(threadWaitForLock_threadTwo.throwable, is(notNullValue()));
        assertThat(threadWaitForLock_threadTwo.throwable, is(instanceOf(InterruptedException.class)));
    }

    @Test(timeout = 20000)
    // @Test
    public void testThreadSafetyWithWaitForLockThenUnlock() throws Exception {
        final LockerByKey locker = createLockerInstance();
        final int nOperatorsByClassOperator = 30;
        final int nOperationsByOperator = 500;
        launchThreadSafetyTestWithWaitForLockThenUnlock(locker, nOperatorsByClassOperator, nOperationsByOperator);
    }

    @Test(timeout = 30000)
    // @Test
    public void testThreadSafetyWithWaitForLockThenUnlock_2_operators() throws Exception {
        final LockerByKey locker = createLockerInstance();
        final int nOperatorsByClassOperator = 2;
        final int nOperationsByOperator = 1;
        launchThreadSafetyTestWithWaitForLockThenUnlock(locker, nOperatorsByClassOperator, nOperationsByOperator);
    }

    @Test(timeout = 30000)
    // @Test
    public void testThreadSafetyWithWaitForLockThenUnlock_3_operators() throws Exception {
        final LockerByKey locker = createLockerInstance();
        final int nOperatorsByClassOperator = 3;
        final int nOperationsByOperator = 1;
        launchThreadSafetyTestWithWaitForLockThenUnlock(locker, nOperatorsByClassOperator, nOperationsByOperator);
    }

    @Test(timeout = 30000)
    // @Test
    public void testThreadSafetyWithWaitForLockThenUnlock_5_operators() throws Exception {
        final LockerByKey locker = createLockerInstance();
        final int nOperatorsByClassOperator = 5;
        final int nOperationsByOperator = 500;
        launchThreadSafetyTestWithWaitForLockThenUnlock(locker, nOperatorsByClassOperator, nOperationsByOperator);
    }

    private void launchThreadSafetyTestWithWaitForLockThenUnlock(final LockerByKey locker, final int nOperatorsByClassOperator,
            final int nOperationsByOperator) throws Exception {
        final ResultContainer resultContainer = new ResultContainer();

        class LockerThreadSafetyTester extends AbstractThreadSafetyTester<AbstractLockerByKeyOperator> {

            public LockerThreadSafetyTester(int nOperatorsByClassOperator,
                    Class<? extends AbstractLockerByKeyOperator>... classOperators) {
                super(nOperatorsByClassOperator, classOperators);
            }

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.talend.commons.utils.threading.AbstractThreadSafetyTester#initInstance(org.talend.commons.utils.threading
             * .IThreadSafetyOperator)
             */
            protected void initInstance(AbstractLockerByKeyOperator operatorInstance) {
                operatorInstance.setLocker(locker);
                operatorInstance.setnOperationsByOperator(nOperationsByOperator);
                operatorInstance.setResultContainer(resultContainer);
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.commons.utils.threading.AbstractThreadSafetyTester#assertFinal()
             */
            @Override
            protected void assertFinal() {
                int actualSumLockedAtEnd = 0;
                int actualSumLockersAtEnd = 0;
                for (int i = 0; i < nOperationsByOperator; i++) {
                    LockerValue lockerValue = locker.getLocker(i);
                    if (lockerValue != null) {
                        int queueLength = lockerValue.getLock().getQueueLength();
                        if (lockerValue != null) {
                            actualSumLockedAtEnd += queueLength;
                        }
                        actualSumLockersAtEnd++;
                    }
                }
                assertThat(actualSumLockedAtEnd, is(0));
                assertTrue(actualSumLockersAtEnd < locker.getCleanPeriod());

                locker.clean();
                actualSumLockedAtEnd = 0;
                actualSumLockersAtEnd = 0;
                for (int i = 0; i < nOperationsByOperator; i++) {
                    LockerValue lockerValue = locker.getLocker(i);
                    if (lockerValue != null) {
                        int queueLength = lockerValue.getLock().getQueueLength();
                        if (lockerValue != null) {
                            actualSumLockedAtEnd += queueLength;
                        }
                        actualSumLockersAtEnd++;
                    }
                }
                assertThat(actualSumLockedAtEnd, is(0));
                assertThat(actualSumLockersAtEnd, is(0));

                assertThat(AbstractLockerByKeyOperator.getNotThreadSafeCounter(),
                        is(resultContainer.sumThreadSafeOperations.get()));
            }

        }

        @SuppressWarnings("unchecked")
        LockerThreadSafetyTester lockerThreadSafetyTester = new LockerThreadSafetyTester(nOperatorsByClassOperator,
                LockThenUnlockOperator.class, TryLockWithTimeoutThenUnlockOperator.class, TryLockThenUnlockOperator.class);
        lockerThreadSafetyTester.start();
    }
}
