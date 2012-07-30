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
package org.talend.commons.utils.threading.threadsafetester;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * DOC amaumont class global comment. Detailled comment
 * 
 * @param T
 */
public abstract class AbstractThreadSafetyTester<T extends IThreadSafetyOperator> {

    private final ExecutorService pool = Executors.newCachedThreadPool();

    private final CyclicBarrier barrier;

    protected int nOperatorsByClassOperator;

    protected Class<? extends T>[] classOperators;

    private long duration;

    public AbstractThreadSafetyTester(int nOperatorsByClassOperator, Class<? extends T>... classOperators) {
        this.nOperatorsByClassOperator = nOperatorsByClassOperator;
        this.classOperators = classOperators;
        this.barrier = new CyclicBarrier(nOperatorsByClassOperator * classOperators.length + 1);
    }

    public void start() throws Exception {
        List<Future<Object>> operatorsHandlerList = new ArrayList<Future<Object>>();
        try {
            for (int i = 0; i < nOperatorsByClassOperator; i++) {
                for (int j = 0; j < classOperators.length; j++) {
                    Class<? extends T> classOperator = classOperators[j];
                    String className = classOperator.getName();
                    Class<? extends T> operator = (Class<? extends T>) Class.forName(className).asSubclass(
                            IThreadSafetyOperator.class);
                    T operatorInstance = operator.newInstance();
                    initInstance(operatorInstance);
                    ThreadSafetyOperatorHandler threadSafetyOperatorHandler = new ThreadSafetyOperatorHandler(operatorInstance);
                    Future futureTask = pool.submit(threadSafetyOperatorHandler);
                    operatorsHandlerList.add(futureTask);
                }
            }
            barrier.await(); // wait for all threads to be ready
            long start = System.currentTimeMillis();
            barrier.await(); // wait for all threads to finish
            duration = System.currentTimeMillis() - start;
            System.out.println("ThreadSafetyTester duration: " + duration + " ms");
        } catch (Exception e) {
            throw e;
        }
        try {
            int operatorsHandlerListListSize = operatorsHandlerList.size();
            for (int i = 0; i < operatorsHandlerListListSize; i++) {
                Future<Object> futureTask = operatorsHandlerList.get(i);
                try {
                    futureTask.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            pool.shutdown();
        }
        assertFinal();
    }

    /**
     * Getter for duration.
     * 
     * @return the duration
     */
    public long getDuration() {
        return duration;
    }

    protected abstract void initInstance(T operatorInstance);

    protected abstract void assertFinal();

    public class ThreadSafetyOperatorHandler<A extends IThreadSafetyOperator> implements Callable<Object> {

        private A operatorInstance;

        public ThreadSafetyOperatorHandler(A operatorInstance) {
            super();
            this.operatorInstance = operatorInstance;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public Object call() throws Exception {
            try {
                barrier.await();
                operatorInstance.doOperations();
            } finally {
                barrier.await();
            }
            return null;
        }
    }

}
