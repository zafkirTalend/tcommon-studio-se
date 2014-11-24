// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.talend.core.runtime.i18n.Messages;

/**
 * created by cmeng on Nov 21, 2014 Detailled comment
 *
 */
public abstract class AProgressMonitorDialogWithCancel extends ProgressMonitorDialog {

    private ARunnableWithProgressCancel runnableWithCancel;

    private boolean isUserCancelled = false;

    /**
     * DOC cmeng ProgressMonitorDialogWithCancel constructor comment.
     * 
     * @param parent
     */
    public AProgressMonitorDialogWithCancel(Shell parent) {
        super(parent);
    }

    abstract protected Object runWithCancel(IProgressMonitor monitor) throws Exception;

    @Override
    @Deprecated
    public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InvocationTargetException,
            InterruptedException {
        throw new InvocationTargetException(new Throwable("Can not use this method, you should use another run method")); //$NON-NLS-1$
    }

    public void run() throws InvocationTargetException, InterruptedException {
        run(null, null, true, -1);
    }

    public void run(String executeMessage, String waitingFinishMessage, boolean needWaitingProgressJob)
            throws InvocationTargetException, InterruptedException {
        run(executeMessage, waitingFinishMessage, needWaitingProgressJob, -1);
    }

    public void run(String executeMessage, String waitingFinishMessage, boolean needWaitingProgressJob, int timeout)
            throws InvocationTargetException, InterruptedException {
        runnableWithCancel = new ARunnableWithProgressCancel() {

            @Override
            protected Object runnableWithCancel(IProgressMonitor monitor) throws Exception {
                return runWithCancel(monitor);
            }
        };
        if (executeMessage != null) {
            runnableWithCancel.setExecuteMessage(executeMessage);
        }
        if (waitingFinishMessage != null) {
            runnableWithCancel.setWaitingFinishMessage(waitingFinishMessage);
        }
        runnableWithCancel.setNeedWaitingProgressJob(needWaitingProgressJob);
        if (0 < timeout) {
            runnableWithCancel.setTimeout(timeout);
        }
        super.run(true, true, runnableWithCancel);
    }

    @Override
    protected void cancelPressed() {
        isUserCancelled = true;
        if (runnableWithCancel != null) {
            runnableWithCancel.kill();
        }
        super.cancelPressed();
    }

    public boolean isUserCanncelled() {
        return isUserCancelled;
    }

    public Object getExecuteResult() {
        if (runnableWithCancel != null) {
            return runnableWithCancel.getExecuteResult();
        } else {
            return null;
        }
    }

    public Exception getExecuteException() {
        if (runnableWithCancel != null) {
            return runnableWithCancel.getExecuteException();
        } else {
            return null;
        }
    }

    private static abstract class ARunnableWithProgressCancel implements IRunnableWithProgress {

        protected FutureTask<Object> futureTask;

        protected Thread executeThread;

        protected ThreadGroup threadGroup;

        protected Object executeResult;

        protected Exception executeException;

        protected String executeMessage = Messages.getString("ProgressMonitorDialogWithCancel.executeMessage.default"); //$NON-NLS-1$

        protected String waitingFinishMessage = ""; //$NON-NLS-1$

        protected int timeout = 30;

        volatile boolean kill = false;

        boolean needWaitingProgressJob = true;

        public ARunnableWithProgressCancel() {
        }

        abstract protected Object runnableWithCancel(IProgressMonitor monitor) throws Exception;

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
         */
        @Override
        public final void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
            futureTask = new FutureTask<Object>(new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    Object result = null;
                    try {
                        result = runnableWithCancel(monitor);
                    } catch (Exception e) {
                        executeException = e;
                    }
                    return result;
                }
            });
            int iTimeout = timeout * 2;
            monitor.beginTask(executeMessage, iTimeout);
            threadGroup = new ThreadGroup("ARunnableWithProgressCancel"); //$NON-NLS-1$
            executeThread = new Thread(threadGroup, futureTask);
            executeThread.start();
            for (int i = 0; i < iTimeout; i++) {
                try {
                    if (kill) {
                        break;
                    }
                    monitor.worked(1);
                    executeResult = futureTask.get(500, TimeUnit.MILLISECONDS);
                    break;
                } catch (TimeoutException timeoutException) {
                    continue;
                } catch (Exception e) {
                    executeException = e;
                    break;
                }
            }
            if (!kill && executeException == null && executeResult == null) {
                executeException = new TimeoutException(Messages.getString("ProgressMonitorDialogWithCancel.executeTimeout")); //$NON-NLS-1$
                kill();
            }
            monitor.done();
        }

        /**
         * DOC cmeng Comment method "addJob".
         * 
         * @throws InterruptedException
         */
        private void addJob() {
            if (!needWaitingProgressJob) {
                return;
            }
            StackTraceElement stElement = null;
            StackTraceElement stackTraceElements[] = executeThread.getStackTrace();
            if (stackTraceElements != null && 0 < stackTraceElements.length) {
                stElement = stackTraceElements[0];
            }
            String currentMethod;
            String title = ""; //$NON-NLS-1$
            if (stElement != null) {
                currentMethod = stElement.getClassName() + "." + stElement.getMethodName(); //$NON-NLS-1$
                title = waitingFinishMessage
                        + Messages
                                .getString(
                                        "ProgressMonitorDialogWithCancel.CheckingConnectionJob.waitingFinish", new Object[] { currentMethod }); //$NON-NLS-1$
            } else {
                title = waitingFinishMessage
                        + Messages.getString("ProgressMonitorDialogWithCancel.CheckingConnectionJob.emptyWaitingfinish"); //$NON-NLS-1$
            }
            CheckingConnectionJob checkingConnectionJob = new CheckingConnectionJob(title, futureTask);
            checkingConnectionJob.setUser(false);
            checkingConnectionJob.setPriority(Job.DECORATE);
            checkingConnectionJob.schedule();
        }

        public void setNeedWaitingProgressJob(boolean need) {
            needWaitingProgressJob = need;
        }

        public void setExecuteMessage(String exeMsg) {
            executeMessage = exeMsg;
        }

        public void setWaitingFinishMessage(String waitingMsg) {
            waitingFinishMessage = waitingMsg;
        }

        public Object getExecuteResult() {
            return executeResult;
        }

        public Exception getExecuteException() {
            return executeException;
        }

        public void setTimeout(int _timeout) {
            timeout = _timeout;
        }

        public void kill() {
            threadGroup.interrupt();
            kill = true;
            addJob();
        }
    }

    private static class CheckingConnectionJob extends Job {

        protected FutureTask<Object> futureTask;

        public CheckingConnectionJob(String name, FutureTask<Object> _futureTask) {
            super(name);
            this.futureTask = _futureTask;
        }

        @Override
        protected IStatus run(IProgressMonitor monitor) {
            try {
                futureTask.get();
            } catch (Exception e) {
                // nothing need to do since all will be done in ARunnableWithProgressCancel
            }
            return Status.OK_STATUS;
        }

    }
}
