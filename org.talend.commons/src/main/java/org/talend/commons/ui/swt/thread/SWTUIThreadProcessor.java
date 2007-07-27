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

    private boolean isStopped = true;

    Thread thread = null;

    Exception exception = null;

    public boolean preProcessStart() {
        return true;
    }

    public void nonUIProcessInThread() {
    }

    public void updateUIInThreadIfThreadIsCancled() {
    }

    public void updateUIInThreadIfThreadIsNotCancled() {
    }

    public void updateUIInThreadIfThreadFinally() {
    }

    public void postProcessCancle() {
    }

    public void execute() {

        if (isStopped) {
            isStopped = false;
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
                                updateUIInThreadIfThreadIsNotCancled();
                            } else {
                                updateUIInThreadIfThreadIsCancled();
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
