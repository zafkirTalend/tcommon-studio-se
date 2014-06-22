package org.talend.commons.utils.time;

import junit.framework.TestCase;

public class TimeMeasureTest extends TestCase {

    private static final int TIME_A = 20;

    private static final int TIME_B = 300;

    private static final int TIME_C = 200;

    private static final int TIME_D = 110;

    private static final int TIME_E = 140;

    public static final String TIMER_TEST_1 = "TIMER_TEST_1";

    public static final String TIMER_TEST_2 = "TIMER_TEST_2";

    public static final String TIMER_TEST_3 = "TIMER_TEST_3";

    public static final String TIMER_TEST_4 = "TIMER_TEST_4";

    public static final String TIMER_STEP_1 = "TIMER_STEP_1";

    public static final String TIMER_STEP_2 = "TIMER_STEP_2";

    public static final String TIMER_STEP_3 = "TIMER_STEP_3";

    public static final String TIMER_STEP_4 = "TIMER_STEP_4";

    /**
     * Added by Marvin Wang on Mar.22, 2012 for testing "begin --> step1 --> end".
     * 
     * @throws InterruptedException
     */
    private void caseOneStep() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        TimeMeasure.begin(TIMER_TEST_1);
        Thread.sleep(TIME_A);
        long elapsedTime = TimeMeasure.step(TIMER_TEST_1, TIMER_STEP_1);

        long endTime = System.currentTimeMillis();

        assertTrue(endTime - startTime >= elapsedTime);

        long totalElapsedTime = TimeMeasure.end(TIMER_TEST_1);
        endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime >= totalElapsedTime);
    }

    /**
     * Added by Marvin Wang on Mar.22, 2012 for testing "begin --> step1 --> pause --> step2 --> end".
     * 
     * @throws InterruptedException
     */
    private void caseStepPauseStep() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        TimeMeasure.begin(TIMER_TEST_2);
        Thread.sleep(TIME_A);
        long elapsedTime1 = TimeMeasure.step(TIMER_TEST_2, TIMER_STEP_1);
        Thread.sleep(TIME_B);
        TimeMeasure.pause(TIMER_TEST_2);
        Thread.sleep(TIME_C);
        long elapsedTime2 = TimeMeasure.step(TIMER_TEST_2, TIMER_STEP_2);

        long endTime = System.currentTimeMillis();

        assertTrue(endTime - startTime - TIME_C >= elapsedTime1 + elapsedTime2);

        long totalElapsedTime = TimeMeasure.end(TIMER_TEST_2);
        endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime - TIME_C >= totalElapsedTime);
    }

    /**
     * Added by Marvin Wang on Mar.22, 2012 for testing "begin --> step1 --> pause --> step2 --> resume --> end".
     * 
     * @throws InterruptedException
     */
    private void caseStepPauseStepResumeStep() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        TimeMeasure.begin(TIMER_TEST_3);
        Thread.sleep(TIME_A);
        long elapsedTime1 = TimeMeasure.step(TIMER_TEST_3, TIMER_STEP_1);
        Thread.sleep(TIME_B);
        TimeMeasure.pause(TIMER_TEST_3);
        Thread.sleep(TIME_C);
        long elapsedTime2 = TimeMeasure.step(TIMER_TEST_3, TIMER_STEP_2);
        Thread.sleep(TIME_D);
        TimeMeasure.resume(TIMER_TEST_3);
        Thread.sleep(TIME_E);
        long elapsedTime3 = TimeMeasure.step(TIMER_TEST_3, TIMER_STEP_3);

        long endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime - TIME_C - TIME_D >= elapsedTime1 + elapsedTime2 + elapsedTime3);

        long totalElapsedTime = TimeMeasure.end(TIMER_TEST_3);
        endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime - TIME_C - TIME_D >= totalElapsedTime);
    }

    /**
     * Changed by Marvin Wang on Mar.22, 2012. Just test sevaral main flows, if need to verify others, add them as
     * required.
     * 
     * @throws InterruptedException
     */
    public void test() throws InterruptedException {
        caseOneStep();
        caseStepPauseStep();
        caseStepPauseStepResumeStep();
    }
}
