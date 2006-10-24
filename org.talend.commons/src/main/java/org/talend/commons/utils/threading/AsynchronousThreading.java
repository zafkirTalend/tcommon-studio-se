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

import org.eclipse.swt.widgets.Display;


/**
 * This class is useful to execute code after a given time.
 * <br/>
 *
 *  Samples:
 *
 * new AsynchronousThread(50, false, dataMapTableView.getDisplay(), new Runnable() {
 *     public void run() {
 *
 *         // calls of Widget methods
 *                          
 *      }
 *  }).start();
 *
 * new AsynchronousThread(50, new Runnable() {
 *     public void run() {
 *
 *         // calls of methods except Widget methods  
 *                          
 *      }
 *  }).start();
 *
 *
 * $Id$
 *
 */
public class AsynchronousThreading {

    private int sleepingTime;
    private boolean synchronousDisplayExecution;
    private Runnable target;
    private Display display;

    /**
     * DOC amaumont AsynchronousDisplayThread constructor comment.
     */
    public AsynchronousThreading(int sleepingTime, boolean synchronousDisplayExecution, Display display, Runnable target) {
        this.sleepingTime = sleepingTime;
        this.synchronousDisplayExecution = synchronousDisplayExecution; 
        this.target = target;
        this.display = display;
    }

    /**
     * DOC amaumont AsynchronousDisplayThread constructor comment.
     */
    public AsynchronousThreading(int sleepingTime, Runnable target) {
        this.sleepingTime = sleepingTime;
        this.target = target;
    }
    
    public void start() {
        
        (new Thread() {

            @Override
            public void run() {
                if (sleepingTime > 0) {
                    try {
                        Thread.sleep(sleepingTime);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                if (display == null) {
                    target.run(); 
                } else {
                    if (display.isDisposed()) {
                        return;
                    }
                    if (synchronousDisplayExecution) {
                        display.syncExec(new Runnable() {
                            public void run() {
                                target.run(); 
                            }
                        });
                    } else {
                        display.asyncExec(new Runnable() {
                            public void run() {
                                target.run(); 
                            }
                        });
                    }
                }
            }

        }).start();
 
        
    }


    
    
}
