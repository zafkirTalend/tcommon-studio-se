// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.utils.threading;

import org.talend.commons.exception.ExceptionHandler;

/**
 * 
 * Limit the execution of threads by verifying at call of <code>startIfExecutable</code> if the process can be
 * executable at this moment. If not the current process is aborted and the <code>execute</code> method is not called.
 * If yes the current process is executed by call the <code>execute</code> method. <br/>
 * 
 * $Id$
 * 
 */
public abstract class ExecutionLimiter {

    private boolean inExecution;

    private long timeBeforeNewExecution;

    private long startTime;

    private boolean finalExecute;

    private FinalExecution finalExecution;

    public ExecutionLimiter() {
        super();
    }

    public ExecutionLimiter(long timeBeforeNewExecute) {
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
    public ExecutionLimiter(long timeBeforeNewExecute, boolean finalExecute) {
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
    public boolean startIfExecutable(boolean executeAtEndOfTime) {
        boolean executable = false;
        executable = isExecutable(executeAtEndOfTime);
        if (executable) {
            inExecution = true;
            if (executeAtEndOfTime) {
                (new Thread() {

                    @Override
                    public void run() {
                        try {
                            try {
                                Thread.sleep(timeBeforeNewExecution);
                            } catch (InterruptedException e) {
                                // nothing to do
                            }
                            // //System.out.println( "Call executed: executeAtEndOfTime");
                            callExecute();
                        } catch (Exception e) {
                            ExceptionHandler.process(e);
                        } finally {
                            inExecution = false;
                        }
                    }
                }).start();
            } else {
                // //System.out.println( "Call executed : now");
                callExecute();
            }
        } else {
            // //System.out.println( "Call rejected");
        }
        if (finalExecute) {
            startThreadForFinalExecution();
        }
        if (executable && !executeAtEndOfTime) {
            inExecution = false;
        }
        return executable;
    }

    private void callExecute() {
        startTime = System.currentTimeMillis();
        execute(false);
    }

    /**
     * DOC amaumont Comment method "startThreadForFinalExecution".
     */
    private void startThreadForFinalExecution() {
        (new Thread() {

            @Override
            public void run() {
                FinalExecution finalThread = new FinalExecution();
                if (finalExecution != null && !finalExecution.isInterrupted()) {
                    finalExecution.interrupt();
                }
                finalExecution = finalThread;
                finalThread.start();
            }

        }).start();
    }

    /**
     * 
     * DOC amaumont ExecutionLimiter class global comment. Detailled comment <br/>
     * 
     * $Id$
     * 
     */
    class FinalExecution extends Thread {

        public void run() {
            try {
                Thread.sleep(timeBeforeNewExecution);
            } catch (InterruptedException e) {
//                System.out.println("Interrupted");
                return;
            }
//            System.out.println("Not Interrupted");
//            System.out.println("Final thread executed");
            execute(true);
        }

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

    public void setTimeBeforeNewExecution(int timeBeforeNewExecute) {
        this.timeBeforeNewExecution = timeBeforeNewExecute;
    }

}
