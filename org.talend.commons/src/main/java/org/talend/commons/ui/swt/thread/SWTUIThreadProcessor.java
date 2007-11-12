// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.commons.ui.swt.thread;

import org.eclipse.swt.widgets.Display;

/**
 * bqian This class can start a thread to execute the backgroud job that user spcified, and have the ability to refresh
 * the SWT UI after finishing the job. Class must extend this class to provide specific funtionality.<br/>
 * 
 * $Id: SWTUIThreadProcessor.java 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) bqian $
 * 
 */
public abstract class SWTUIThreadProcessor {

    private volatile boolean isStopped = true;

    Thread thread = null;

    Exception exception = null;

    public boolean preProcessStart() {
        return true;
    }

    public void nonUIProcessInThread() {
    }

    public void updateUIInThreadIfThreadIsCanceled() {
    }

    public void updateUIInThreadIfThreadIsNotCanceled() {
    }

    public void updateUIInThreadIfThreadFinally() {
    }

    public void postProcessCancle() {
    }

    public void execute() {

        if (isStopped) {
            isStopped = false;
            exception = null;
            if (!preProcessStart()) {
                isStopped = true;
                return;
            }

            thread = new Thread() {

                public void run() {
                    // load data
                    nonUIProcessInThread();

                    Display.getDefault().asyncExec(new Runnable() {

                        public void run() {
                            if (!isStopped) {
                                updateUIInThreadIfThreadIsNotCanceled();
                            } else {
                                updateUIInThreadIfThreadIsCanceled();
                            }
                            updateUIInThreadIfThreadFinally();
                            isStopped = true;
                        }
                    });

                }
            };
            thread.start();
        } else {
            isStopped = true;
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
            postProcessCancle();
        }
    }

    /**
     * Getter for exception.
     * 
     * @return the exception
     */
    public Exception getException() {
        return this.exception;
    }

    /**
     * Sets the exception.
     * 
     * @param exception the exception to set
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Getter for isStopped.
     * 
     * @return the isStopped
     */
    public boolean isStopped() {
        return this.isStopped;
    }

}
