// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.swtbot.helpers;

import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.Assert;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class JobHelper implements Helper {

    /**
     * DOC fzhong Comment method "filterStatistics". Filter all statistics for the execution result.
     * 
     * @param execution The result string in execution console
     * @return A string between statistics tags
     */
    public static String filterStatistics(String execution) {
        int statConnected = execution.indexOf("[statistics] connected");
        int statDisconnected = execution.indexOf("[statistics] disconnected");
        if (statConnected == -1 || statDisconnected == -1)
            Assert.fail(execution);
        return execution.substring(statConnected + "[statistics] connected".length(), statDisconnected).trim();
    }

    public static void runJob(SWTBotGefEditor jobEditor) {
        String[] array = jobEditor.getTitle().split(" ");
        String jobName = array[1];
        // String jobVersion = array[2];
        runJob(jobName);
    }

    public static void runJob(String jobName) {
        GEFBOT.viewByTitle("Run (Job " + jobName + ")").setFocus();
        GEFBOT.button(" Run").click();

        // gefBot.waitUntil(Conditions.shellIsActive("Launching " + jobName + " " + jobVersion));
        // gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Launching " + jobName + " " + jobVersion)));

        GEFBOT.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                return GEFBOT.button(" Run").isEnabled();
            }

            public String getFailureMessage() {
                return "job did not finish running";
            }
        }, 60000);
    }
}
