// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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

    private int timeBeforeNewExecution;

    private long startTime;

    private boolean finalExecute;

    private FinalExecution finalExecution;

    // private List<FinalExecution> finalThreadsList = new Vector<FinalExecution>();

    // private Object[] finalThreadsListLock = new Object[0];

    public ExecutionLimiter() {
        super();
    }

    public ExecutionLimiter(int timeBeforeNewExecute) {
        this.timeBeforeNewExecution = timeBeforeNewExecute;
    }

    public ExecutionLimiter(int timeBeforeNewExecute, boolean finalExecute) {
        this.timeBeforeNewExecution = timeBeforeNewExecute;
        this.finalExecute = finalExecute;
    }

    public boolean startIfExecutable() {
        return startIfExecutable(false);
    }

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
                            Thread.sleep(timeBeforeNewExecution);
                        } catch (InterruptedException e) {
                            // nothing to do
                        }
                        // //System.out.println( "Call executed: executeAtEndOfTime");
                        callExecute();
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
        if (executable) {
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
                // synchronized (finalThreadsListLock) {
                // for (Iterator<FinalExecution> iter = finalThreadsList.iterator(); iter.hasNext();) {
                // FinalExecution currentFinalThread = iter.next();
                // if (currentFinalThread != null && !currentFinalThread.isInterrupted()) {
                // currentFinalThread.interrupt();
                // iter.remove();
                // }
                // }
                // finalThreadsList.add(finalThread);
                // }
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
                // System.out.println("Interrupted");
                return;
            }
            // System.out.println("Not Interrupted");
            // System.out.println("Final thread executed");
            execute(true);
            // removeThread();
        }

        // private void removeThread() {
        // // (new Thread() {
        // //
        // // @Override
        // // public void run() {
        // // synchronized (finalThreadsListLock) {
        // finalThreadsList.remove(this);
        // // }
        // // }
        // //
        // // }).start();
        // }

    }

    protected abstract void execute(boolean isFinalExecution);

    private boolean isExecutable(boolean executeAtEndOfTime) {
        boolean returnValue = false;
        if (timeBeforeNewExecution == 0) {
            returnValue = !inExecution;
        } else {
            returnValue = System.currentTimeMillis() - startTime >= timeBeforeNewExecution;
            // System.out.println(System.currentTimeMillis() - startTime + " >= " + timeBeforeNewExecution + " " +
            // returnValue);
        }
        return returnValue;
    }

    public int getTimeBeforeNewExecution() {
        return timeBeforeNewExecution;
    }

    public void setTimeBeforeNewExecution(int timeBeforeNewExecute) {
        this.timeBeforeNewExecution = timeBeforeNewExecute;
    }

}
