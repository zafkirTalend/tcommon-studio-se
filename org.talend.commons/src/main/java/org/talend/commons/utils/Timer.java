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
package org.talend.commons.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class Timer {
    
    private static Map<String, Timer> timers = new HashMap<String, Timer>();
    
    private String name;
    private long startTime;
    private long stopTime;
    private ArrayList<Long> times  = new ArrayList<Long>();
    private long timeDelta;

    private Timer(String name) {
        this.name = name;
    }

    public static Timer getTimer(String name) {
        if (timers.keySet().contains(name)) {
            return timers.get(name);
        } else {
            Timer timer = new Timer(name);
            timers.put(name, timer);
            return timer;
        }
    }
    
    public void start() {
        startTime = new Date().getTime();
    }
    
    public void stop() {
        stopTime = new Date().getTime();
        timeDelta = stopTime - startTime;
        times.add(timeDelta);
    }
    
    public void print() {
        long averageTime = computeAverageTime();
        System.out.println("timer " + name + " : " + timeDelta + " avg : " + averageTime); //$NON-NLS-1$
    }

    private long computeAverageTime() {
        long totalTime = 0;
        for (Long time : times) {
            totalTime += time;
        }
        return totalTime / times.size();
    }
}
