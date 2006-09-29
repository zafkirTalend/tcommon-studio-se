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
package org.talend.commons.utils.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Timer to measure elapsed time of any process or between steps.
 * 
 * $Id$
 * 
 */
public class TimeMeasure {

    // PTODO create junit test class
    private static HashMap<String, List<Long>> timers;

    private static List<Long> times;

    private static int indent = 0;

    /**
     * measureActive is true by default. A true value means that all methods calls are processed else no one. 
     */
    public static boolean measureActive = true;

    /**
     * display is true by default. A true value means that all informations are displayed. 
     */
    public static boolean display = true;

    /**
     * 
     * DOC amaumont Comment method "start".
     * 
     * @param idTimer
     */
    public static void start(String idTimer) {
        if (!measureActive)
            return;
        init();
        if (timers.containsKey(idTimer) && display) {
            System.out.println(indent(indent) + "Warning (start): timer " + idTimer + " already exists");
        }
        indent++;
        if (display) {
            System.out.println(indent(indent) + "Start '" + idTimer + "' ...");
        }
        times = new ArrayList<Long>();
        times.add(System.currentTimeMillis());
        timers.put(idTimer, times);
    }

    /**
     * 
     * DOC amaumont Comment method "end".
     * 
     * @param idTimer
     * @return total elapsed time since start in ms
     */
    public static long end(String idTimer) {
        if (!measureActive)
            return 0;
        init();
        if (!timers.containsKey(idTimer) && display) {
            System.out.println(indent(indent) + "Warning (end): timer " + idTimer + " does'nt exist");
        } else {
            times = timers.get(idTimer);
            timers.remove(idTimer);
            if (times.size() > 1) {
                long elapsedTimeSinceLastRequest = System.currentTimeMillis() - times.get(times.size() - 1);
                if (display) {
                    System.out.println(indent(indent) + "End '" + idTimer + "', elapsed time since last request: "
                            + elapsedTimeSinceLastRequest + " ms ");
                }
            }
            long totalElapsedTime = System.currentTimeMillis() - times.get(0);
            if (display) {
                System.out.println(indent(indent) + "End '" + idTimer + "', total elapsed time: " + totalElapsedTime + " ms ");
            }
            indent--;
            return totalElapsedTime;
        }
        return 0;
    }

    /**
     * 
     * DOC amaumont Comment method "timeSinceStart".
     * 
     * @param idTimer
     * @return total elapsed time since start in ms
     */
    public static long timeSinceStart(String idTimer) {
        if (!measureActive)
            return 0;
        init();
        if (!timers.containsKey(idTimer)) {
            System.out.println(indent(indent) + "Warning (end): timer " + idTimer + " does'nt exist");
        } else {
            long time1 = timers.get(idTimer).get(0);
            long time = System.currentTimeMillis() - time1;
            if (display) {
                System.out.println(indent(indent) + "-> '" + idTimer + "', elapsed time since start: " + time + " ms ");
            }
            return time;
        }
        return 0;
    }

    /**
     * 
     * DOC amaumont Comment method "timeStep".
     * 
     * @param idTimer
     * @return elapsed time since previous step in ms
     */
    public static long step(String idTimer, String stepName) {
        if (!measureActive)
            return 0;
        init();
        if (!timers.containsKey(idTimer)) {
            if (display) {
                System.out.println(indent(indent) + "Warning (end): timer " + idTimer + " does'nt exist");
            }
        } else {
            times = timers.get(idTimer);
            long lastTime = times.get(times.size() - 1);
            long currentTime = System.currentTimeMillis();
            times.add(currentTime);
            long time = currentTime - lastTime;
            if (display) {
                System.out.println(indent(indent) + "-> '" + idTimer + "', step name '" + stepName
                        + "', elapsed time since previous step: " + time + " ms ");
            }
            return time;
        }
        return 0;
    }

    /**
     * DOC amaumont Comment method "init".
     */
    private static void init() {
        if (timers == null) {
            timers = new HashMap<String, List<Long>>();
        }
    }

    public static String indent(final int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < i; j++) {
            stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }

}
