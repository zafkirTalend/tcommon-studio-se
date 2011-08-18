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

import org.eclipse.swt.graphics.Point;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.Assert;
import org.talend.swtbot.Utilities;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class JobHelper implements Helper {

    private static String executionResult;

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

        executionResult = GEFBOT.styledText().getText();
    }

    public static String getExecutionResult() {
        return executionResult;
    }

    /**
     * DOC fzhong Comment method "useTLogRow". Link input component to tLogRow.
     * 
     * @param jobEditor
     * @param component the input component
     * @param rowName The name of row in the context menu of component. "Main" as default.
     */
    public static void useTLogRow(SWTBotGefEditor jobEditor, SWTBotGefEditPart component, String rowName) {
        Utilities.dndPaletteToolOntoJob(jobEditor, "tLogRow", new Point(300, 100));
        jobEditor.select(component).setFocus();
        jobEditor.clickContextMenu("Row").clickContextMenu(rowName);
        SWTBotGefEditPart tlogRow = UTIL.getTalendComponentPart(jobEditor, "tLogRow_1");
        Assert.assertNotNull("can not get component 'tLogRow'", tlogRow);
        jobEditor.click(tlogRow);
        jobEditor.save();
    }

    public static void useTLogRow(SWTBotGefEditor jobEditor, SWTBotGefEditPart component) {
        useTLogRow(jobEditor, component, "Main");
    }

}
