// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 
 * Limit the execution of threads by verifying at call of <code>startIfExecutable</code> if the process can be
 * executable at this moment. If not the current process is aborted and the <code>execute</code> method is not called.
 * If yes the current process is executed by call the <code>execute</code> method. <br/>
 * 
 * $Id: ExecutionLimiter.java 6924 2007-11-12 13:16:18Z plegall $
 * 
 */
public abstract class ExecutionLimiterImproved extends ExecutionLimiter {

    private boolean inExecution;

    private long timeBeforeNewExecution;

    private long startTime;

    private boolean finalExecute;

    private Thread executeAtEndOfTimeThread;

    private FinalExecution finalExecutionThreadWait;

    private Executor executor = Executors.newCachedThreadPool();

    private Locker locker = new Locker();

    private boolean atLeastOneCallRefused;

    public ExecutionLimiterImproved() {
        super();
    }

    public ExecutionLimiterImproved(long timeBeforeNewExecute) {
        this.timeBeforeNewExecution = timeBeforeNewExecute;
    }

    /**
     * 
     * DOC amaumont ExecutionLimiter constructor comment.
     * 
     * @param timeBeforeNewExecute time max between executions
     * @param finalExecute execute at end of time the treatment to ensure it is executed a least one time after last
     * call of startIfExecutable()
     */
    public ExecutionLimiterImproved(long timeBeforeNewExecute, boolean finalExecute) {
        this.timeBeforeNewExecution = timeBeforeNewExecute;
        this.finalExecute = finalExecute;
    }

    public boolean startIfExecutable() {
        return startIfExecutable(false);
    }

    /**
     * Start execution if executable, after <code>timeBeforeNewExecute</code> is elapsed if
     * <code>executeAtEndOfTime</code> is true.
     * 
     * @param executeAtEndOfTime if true call <code>execute()</code> now, else call <code>execute()</code> at end of
     * <code>timeBeforeNewExecute</code>
     * @return true if executable, false else
     */
    @SuppressWarnings("unchecked")
    public boolean startIfExecutable(final boolean executeAtEndOfTime) {

        boolean locked = false;

        synchronized (this) {
            locked = locker.lockIfUnlocked(ExecutionLimiterImproved.this);
            if (locked) {
                // System.out.println("locked");
                atLeastOneCallRefused = false;
            }

        }

        if (locked) {

            final boolean executable = isExecutable(executeAtEndOfTime);
            if (executable) {
                executor.execute(new Runnable() {

                    public void run() {
                        try {
                            inExecution = true;
                            if (executeAtEndOfTime) {

                                try {
                                    // System.out.println("1 HASHCODE = " + ExecutionLimiter.this.hashCode() + " " +
                                    // this.hashCode());
                                    // Thread.sleep(timeBeforeNewExecution);
                                    synchronized (this) {
                                        // System.out.println("2 HASHCODE = " + ExecutionLimiter.this.hashCode() + " " +
                                        // this.hashCode());
                                        executeAtEndOfTimeThread = Thread.currentThread();
                                        this.wait(timeBeforeNewExecution);
                                    }
                                    // System.out.println("Call executed: executeAtEndOfTime" +
                                    // ExecutionLimiter.this.hashCode() + " " + this.hashCode());
                                    callExecute();
                                    callFinalExecute();
                                } catch (InterruptedException e) {
                                    // System.out.println("=======> executeAtEndOfTime interrupted" +
                                    // ExecutionLimiter.this.hashCode() + " " + this.hashCode());
                                    return;
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                // System.out.println( "Call executed : now");
                                callExecute();
                                callFinalExecute();
                            }
                            inExecution = false;
                        } finally {
                            // System.out.println("UNlocked");
                            locker.unlock(ExecutionLimiterImproved.this);
                        }
                    }
                });
                return true;
            } else {
                // System.out.println("Execution rejected: not executable");
                atLeastOneCallRefused = true;
                return false;
            }
        } else {
            // System.out.println("Execution rejected: locked");
            atLeastOneCallRefused = true;
            return false;
        }
    }

    /**
     * DOC amaumont Comment method "callFinalExecute".
     */
    private void callFinalExecute() {
        // System.out.println("before test final execute");
        if (finalExecute) {
            // System.out.println("try to final execution");

            while (true) {
                try {
                    synchronized (this) {
                        this.wait(timeBeforeNewExecution);
                    }
                } catch (InterruptedException e) {
                    return;
                }
                if (!atLeastOneCallRefused) {
                    // System.out.println("Final execution");
                    execute(true);
                    atLeastOneCallRefused = false;
                    break;
                }
                atLeastOneCallRefused = false;
                // System.out.println("Intermediate execution");
                execute(false);
            }
        }

    }

    private void callExecute() {
        startTime = System.currentTimeMillis();
        execute(false);
    }

    protected abstract void execute(boolean isFinalExecution);

    private boolean isExecutable(boolean executeAtEndOfTime) {
        boolean returnValue = false;
        if (executeAtEndOfTime) {
            returnValue = !inExecution;
        } else {
            if (timeBeforeNewExecution == 0) {
                returnValue = !inExecution;
            } else {
                returnValue = System.currentTimeMillis() - startTime >= timeBeforeNewExecution;
                // System.out.println(System.currentTimeMillis() - startTime + " >= " + timeBeforeNewExecution + " " +
                // returnValue);
            }
        }
        return returnValue;
    }

    public long getTimeBeforeNewExecution() {
        return timeBeforeNewExecution;
    }

    public void setTimeBeforeNewExecution(long timeBeforeNewExecute) {
        this.timeBeforeNewExecution = timeBeforeNewExecute;
    }

    public void resetTimer() {
        // System.out.println("############### RESET timer");
        startTime = System.currentTimeMillis();
        if (executeAtEndOfTimeThread != null && !executeAtEndOfTimeThread.isInterrupted()) {
            executeAtEndOfTimeThread.interrupt();
        }
        if (finalExecutionThreadWait != null && !finalExecutionThreadWait.isInterrupted()) {
            finalExecutionThreadWait.interrupt();
        }
    }
}
