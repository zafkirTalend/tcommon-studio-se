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
package org.talend.commons.utils.performance;

/**
 * 
 * Evaluate performance of cpu with a reference time of 300 ms for good performance. <br/>
 * 
 * $Id$
 * 
 */
public class PerformanceEvaluator {

    public static final int GOOD_PERFORMANCE_INDICE = 300;

    public static final int TIME_TO_LAST = 100000000;

    public static final int INIT_TIME = 1000;

    private IPerformanceEvaluatorListener listener;

    /**
     * Warning : Now, only the last registered listener is active at the same time.
     * 
     */
    public void addListener(IPerformanceEvaluatorListener newListener) {
        this.listener = newListener;
    }

    /**
     * 
     * 
     * 
     */
    public void evaluate() {

        Thread t = new Thread() {

            public void run() {
                long timeStart = System.currentTimeMillis();
                for (int i = 0; i < TIME_TO_LAST; i++) {
                    // do nothing
                }
                long timeSpent = System.currentTimeMillis() - timeStart;
                PerformanceEvaluatorEvent event = new PerformanceEvaluatorEvent((int) timeSpent);
                // //System.out.println("timeSpent=" + (int) timeSpent);
                if (listener != null) {
                    listener.handleEvent(event);
                }
            }
        };
        t.start();

    }

    public static void main(String[] args) {
        final PerformanceEvaluator performanceEvaluator = new PerformanceEvaluator();
        (new Object() {

            void init() {
                performanceEvaluator.addListener(new IPerformanceEvaluatorListener() {

                    public void handleEvent(PerformanceEvaluatorEvent event) {
                        // System.out.println(event.getIndicePerformance());
                    }

                });
            }
        }).init();
        (new Thread() {

            @Override
            public void run() {
                try {
                    // to start evaluation after application loaded
                    Thread.sleep(INIT_TIME);
                } catch (InterruptedException e) {
                    // nothing
                }
                performanceEvaluator.evaluate();
            }
        }).start();

    }

}
