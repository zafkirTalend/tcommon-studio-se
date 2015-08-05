// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.tracer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;

/**
 * DOC amaumont class global comment. Detailled comment
 */
public class StatisticsTracerTest {

    private static final String MY_TRACER_TEST1 = "myTracerTest1";

    private static final String MY_TRACER_TEST2 = "myTracerTest2";

    StatisticsTracer myTracerTest1 = null;

    Set<Long> ids = new HashSet<Long>();

    /**
     * 
     * DOC Marvin Comment method "testGetTracer".
     */
    @Test
    public void testGetTracer() {

    }

    /**
     * @throws InterruptedException
     * 
     */
    @Test
    public void testStart() throws InterruptedException {
        int concurrencyThreadNum = 500;
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(50);
        myTracerTest1 = StatisticsTracer.getTracer(MY_TRACER_TEST1);

        final StartTracerCommand[] commands = new StartTracerCommand[concurrencyThreadNum];

        for (int i = 0; i < concurrencyThreadNum; i++) {
            StartTracerCommand command = new StartTracerCommand();
            commands[i] = command;
        }

        // To execute all commands to get the free ports concurrently, every command is regard as a client.
        for (int i = 0; i < concurrencyThreadNum; i++) {
            threadPool.execute(commands[i]);
        }

        threadPool.shutdown();
        try {
            // In order to execute all the commands, if the value of concurrencyThreadNum is changed, the sleep time
            // also needs to change manually.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(concurrencyThreadNum == ids.size());

        if (ids != null && ids.size() > 0) {
            Iterator<Long> it = ids.iterator();
            while (it.hasNext()) {
                Long value = it.next();
                myTracerTest1.stop(value.longValue());
            }
        }
        StatisticsTracer.removeTracer(MY_TRACER_TEST1);
    }

    class StartTracerCommand implements Runnable {

        @Override
        public void run() {
            long id = myTracerTest1.start();
            ids.add(id);
        }

    }

    @Test
    public void testStatisticTracer() throws InterruptedException {

        StatisticsTracer myTracerTest1 = StatisticsTracer.getTracer(MY_TRACER_TEST1);

        int sleepTime = 100;
        int executionsCount = 10;

        for (int i = 0; i < 10; i++) {
            long id = myTracerTest1.start();
            Thread.sleep(sleepTime);
            myTracerTest1.stop(id);
        }

        long averageWorkTime = myTracerTest1.getAverageWorkTime();
        assertTrue(averageWorkTime >= sleepTime);

        long exepectedElapsedTimeSinceFirstStart = sleepTime * executionsCount;
        long elapsedTimeSinceFirstStart = myTracerTest1.getElapsedTimeSinceFirstStart();
        assertTrue(elapsedTimeSinceFirstStart >= exepectedElapsedTimeSinceFirstStart);

        long countExecutions = myTracerTest1.getCountExecutions();
        assertEquals(executionsCount, countExecutions);

        StatisticsTracer.removeTracer(MY_TRACER_TEST1);

    }

    @Test
    public void testStatisticTracerAndTraceToFile() throws InterruptedException {

        StatisticsTracer myTracerTest1 = StatisticsTracer.getTracer(MY_TRACER_TEST1);

        String userDirStr = System.getProperty("user.dir");

        // URL resource = StatisticsTracer.class.getResource(StatisticsTracer.class.getSimpleName() + ".class");
        // File refClassFile = null;
        // try {
        // refClassFile = new File(new URI(resource.toString()));
        // } catch (URISyntaxException e) {
        // fail(e.getMessage());
        // }
        // File folder = refClassFile.getParentFile();

        // String pathFile = folder.getAbsolutePath() + "/myTracerTest3";
        String pathFile = userDirStr + "/myTracerTest3.test";
        myTracerTest1.traceToFile(pathFile, false);

        int sleepTime = 100;
        int executionsCount = 10;
        String firstRowStr = "";
        for (int i = 0; i < 10; i++) {
            long id = myTracerTest1.start();
            if (i == 1)
                firstRowStr = myTracerTest1.toDataRow();
            Thread.sleep(sleepTime);
            myTracerTest1.stop(id);
            myTracerTest1.print();
        }

        long averageWorkTime = myTracerTest1.getAverageWorkTime();
        long totalTime = myTracerTest1.getElapsedTime();
        // Changed by Marvin Wang on Feb.15 for TDI-19166, refer to the method from
        // StatisticsTracer.getAverageWorkTime() that uses "/"
        assertTrue(averageWorkTime * executionsCount <= totalTime);
        // assertTrue(averageWorkTime >= sleepTime && averageWorkTime < sleepTime + 10);

        int exepectedElapsedTimeSinceFirstStart = sleepTime * executionsCount;
        long elapsedTimeSinceFirstStart = myTracerTest1.getElapsedTimeSinceFirstStart();
        assertTrue(elapsedTimeSinceFirstStart >= exepectedElapsedTimeSinceFirstStart);

        long countExecutions = myTracerTest1.getCountExecutions();
        assertEquals(executionsCount, countExecutions);

        File file = new File(pathFile);
        System.out.println("File: " + file.getAbsolutePath());
        assertTrue(file.canRead());
        // assertTrue(file.length() > 550); Commentted by Marvin Wang on Feb.15.
        // Changed by Marvin Wang on Feb. 15 for TDI-19166, the "1" stands for wrap character.
        int fistRowByteLength = firstRowStr.getBytes().length;
        assertTrue(file.length() >= (fistRowByteLength + 1) * executionsCount);

        StatisticsTracer.removeTracer(MY_TRACER_TEST1);
        if (file.delete())
            System.out.println("File Deleted!");
    }

    @Test
    public void testStatisticTracerWithConcurrency() throws InterruptedException {

        final StatisticsTracer myTracerTest1 = StatisticsTracer.getTracer(MY_TRACER_TEST2);

        final int sleepTime = 100;
        int executionsCount = 10;
        int concurrentExecutionsCount = 5;
        final CyclicBarrier barrierStart = new CyclicBarrier(concurrentExecutionsCount);
        final CyclicBarrier barrierEnd = new CyclicBarrier(concurrentExecutionsCount + 1);

        ThreadPoolExecutor threadPool = intializePool(concurrentExecutionsCount);

        Runnable command = new Runnable() {

            public void run() {
                try {
                    barrierStart.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 10; i++) {
                    long id = myTracerTest1.start();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    myTracerTest1.stop(id);
                }

                // next reached barrierEnd
                try {
                    barrierEnd.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < executionsCount; i++) {
            threadPool.execute(command);
        }

        // first reached barrierEnd
        try {
            barrierEnd.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long averageWorkTime = myTracerTest1.getAverageWorkTime();
        boolean testAverageWorkTime = averageWorkTime >= sleepTime && averageWorkTime < sleepTime + 100;
        // log.info("averageWorkTime=" + averageWorkTime);
        // System.out.println("averageWorkTime=" + averageWorkTime);
        assertTrue(testAverageWorkTime);

        int expectedElapsedTimeSinceFirstStart = sleepTime * executionsCount;
        long elapsedTimeSinceFirstStart = myTracerTest1.getElapsedTimeSinceFirstStart();
        // log.info("elapsedTimeSinceFirstStart=" + elapsedTimeSinceFirstStart);
        // System.out.println("elapsedTimeSinceFirstStart=" + elapsedTimeSinceFirstStart);
        assertEquals(expectedElapsedTimeSinceFirstStart, elapsedTimeSinceFirstStart, 1500);

        // log.info("executionsCount=" + myTracerTest1.getCountExecutions());
        // System.out.println("executionsCount=" + myTracerTest1.getCountExecutions());
        assertEquals(executionsCount * concurrentExecutionsCount, myTracerTest1.getCountExecutions());

        StatisticsTracer.removeTracer(MY_TRACER_TEST2);
    }

    /**
     * DOC amaumont Comment method "intializePool".
     * 
     * @return
     */
    private ThreadPoolExecutor intializePool(int poolSize) {

        return (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize, new ThreadFactory() {

            ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();

            /*
             * (non-Javadoc)
             * 
             * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
             */
            public Thread newThread(Runnable r) {
                Thread newThread = defaultThreadFactory.newThread(r);
                newThread.setName(StatisticsTracerTest.class.getSimpleName() + "_" + newThread.getName());
                return newThread;
            }

        });

    }
}
