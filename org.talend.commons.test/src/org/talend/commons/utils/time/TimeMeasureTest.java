package org.talend.commons.utils.time;

import junit.framework.TestCase;

import org.talend.commons.utils.time.TimeMeasure;

public class TimeMeasureTest extends TestCase {

    private static final int TIME_A = 20;

    private static final int TIME_B = 30;

    private static final int TIME_C = 70;

    private static final int TIME_D = 110;

    private static final int TIME_E = 140;

    private static final int TIME_F = 20;

    public void test() throws InterruptedException {

        TimeMeasure.begin("a"); //$NON-NLS-1$
        // TimeMeasure.end("b");
        Thread.sleep(TIME_A);

        long step1 = TimeMeasure.step("a", "1"); //$NON-NLS-1$ //$NON-NLS-2$

        assertTrue(step1 + " expected is " + TIME_A, isNear(step1, TIME_A));

        Thread.sleep(TIME_B);

        TimeMeasure.pause("a"); //$NON-NLS-1$

        Thread.sleep(TIME_C);

        long step2 = TimeMeasure.step("a", "2"); //$NON-NLS-1$ //$NON-NLS-2$

        assertTrue(step2 + " expected is " + TIME_B, isNear(step2, TIME_B));

        Thread.sleep(TIME_D);

        TimeMeasure.resume("a"); //$NON-NLS-1$

        Thread.sleep(TIME_E);

        long step3 = TimeMeasure.step("a", "3"); //$NON-NLS-1$ //$NON-NLS-2$

        assertTrue(step3 + " expected is " + TIME_E, isNear(step3, TIME_E));

        Thread.sleep(TIME_F);

        long end = TimeMeasure.end("a"); //$NON-NLS-1$

        assertTrue(end + " expected is " + (TIME_A + TIME_B + TIME_E + TIME_F), isNear(end, TIME_A + TIME_B + TIME_E + TIME_F));

    }

    private boolean isNear(long time, long refTime) {
        return time >= refTime - 10 && time < refTime + 15;
    }

}
