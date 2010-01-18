// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.runprocess.data;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.talend.designer.runprocess.IPerformanceData;

/**
 * DOC zwei class global comment. Detailled comment
 */
public class IteratePerformance {

    private static final String COLOR_FINISHED = "#229922"; //$NON-NLS-1$

    private static final String COLOR_RUNNING = "#AA3322"; //$NON-NLS-1$

    /**
     * store the ids of exec that have already stopped.
     */
    private Set<String> stoppeddExecutionId = new HashSet<String>();

    /**
     * store the ids of exec that are running now.
     */
    private Set<String> runningExecutionId = new HashSet<String>();

    public String getLabel(String msg) {
        if (StringUtils.isEmpty(msg)) {
            // handle by super class
            // super.setLabel(msg);
            return new CommonPerformance().getBaseLabel(msg);
        }
        String[] part = msg.split("\\|"); //$NON-NLS-1$
        if (part != null && part.length == 3) {
            // update process status
            if (part[2].equals(IPerformanceData.ACTION_START)) {
                runningExecutionId.add(part[1]);
            } else if (part[2].equals(IPerformanceData.ACTION_STOP)) {
                stoppeddExecutionId.add(part[1]);
                runningExecutionId.remove(part[1]);
            }

            return createHtmlText();

        } else if (part != null && part.length == 2) { // iterate1.0|exec0, it means running.
            runningExecutionId.add(part[1]);

            return createHtmlText();
        }
        return null;
    }

    public String createHtmlText() {
        StringBuilder html = new StringBuilder(150);

        String pattern = "<font color='%1$s'>%2$s %3$s</font><br>"; //$NON-NLS-1$
        if (runningExecutionId.size() > 0) {
            if (runningExecutionId.size() == 1) {
                html.append(String.format(pattern, COLOR_RUNNING, runningExecutionId.size(), "exec running")); //$NON-NLS-1$
            } else {
                // plural forms
                html.append(String.format(pattern, COLOR_RUNNING, runningExecutionId.size(), "execs running")); //$NON-NLS-1$
            }
        }

        if (stoppeddExecutionId.size() > 0) {
            if (stoppeddExecutionId.size() == 1) {
                html.append(String.format(pattern, COLOR_FINISHED, stoppeddExecutionId.size(), "exec finished")); //$NON-NLS-1$
            } else {
                // plural forms
                html.append(String.format(pattern, COLOR_FINISHED, stoppeddExecutionId.size(), "execs finished")); //$NON-NLS-1$
            }
        }

        return html.toString();
    }

    public void resetStatus() {
        stoppeddExecutionId.clear();
        runningExecutionId.clear();
    }
}
