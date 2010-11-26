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
package tosstudio.components.basicelements;

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CopyComponentsBetweenJob extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotShell shell;

    private SWTBotTree tree;

    private SWTBotGefEditor gefEditor;

    private static String JOBNAME1 = "CopyComponentsBetweenJob1"; //$NON-NLS-1$

    private static String JOBNAME2 = "CopyComponentsBetweenJob2"; //$NON-NLS-1$

    @Before
    public void createJob() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        /* Create job1 */
        tree.select("Job Designs").contextMenu("Create job").click();

        gefBot.waitUntil(Conditions.shellIsActive("New job"));
        shell = gefBot.shell("New job");
        shell.activate();

        gefBot.textWithLabel("Name").setText(JOBNAME1);

        gefBot.button("Finish").click();
        /* Create job2 */
        tree.select("Job Designs").contextMenu("Create job").click();

        gefBot.waitUntil(Conditions.shellIsActive("New job"));
        shell = gefBot.shell("New job");
        shell.activate();

        gefBot.textWithLabel("Name").setText(JOBNAME2);

        gefBot.button("Finish").click();

        /* Use components in job1 */
        gefEditor = gefBot.gefEditor("Job " + JOBNAME1 + " 0.1");
        gefEditor.show();

        gefEditor.activateTool("tRowGenerator").click(100, 100);
        gefEditor.activateTool("tLogRow").click(300, 100);

        SWTBotGefEditPart rowGen = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        rowGen.doubleClick();
        shell = gefBot.shell("JasperETL Powered by Talend - tRowGenerator - tRowGenerator_1");
        shell.activate();
        gefBot.buttonWithTooltip("Add").click();
        gefBot.buttonWithTooltip("Add").click();
        gefBot.button("OK").click();

        gefEditor.select(rowGen);
        gefEditor.clickContextMenu("Row").clickContextMenu("Main");
        SWTBotGefEditPart logRow = getTalendComponentPart(gefEditor, "tLogRow_1");
        gefEditor.click(logRow);
    }

    @Test
    public void copyAndPasteComponents() {
        /* Copy and paste in own job */
        SWTBotGefEditPart rowGen1 = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        SWTBotGefEditPart logRow1 = getTalendComponentPart(gefEditor, "tLogRow_1");
        gefEditor.select(rowGen1, logRow1);
        gefEditor.clickContextMenu("Copy");
        gefEditor.click(100, 300);
        gefEditor.clickContextMenu("Paste");

        SWTBotGefEditPart rowGen2 = getTalendComponentPart(gefEditor, "tRowGenerator_2");
        Assert.assertNotNull(rowGen2);
        SWTBotGefEditPart logRow2 = getTalendComponentPart(gefEditor, "tLogRow_2");
        Assert.assertNotNull(logRow2);
        SWTBotGefEditPart rowMain2 = gefEditor.getEditPart("row2 (Main)");
        Assert.assertNotNull(rowMain2);
        gefEditor.saveAndClose();

        /* Copy and paste in another job */
        gefEditor = gefBot.gefEditor("Job " + JOBNAME2 + " 0.1");
        gefEditor.setFocus();
        gefEditor.click(100, 100);
        gefEditor.clickContextMenu("Paste");

        SWTBotGefEditPart rowGen1_2 = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        Assert.assertNotNull(rowGen1_2);
        SWTBotGefEditPart logRow1_2 = getTalendComponentPart(gefEditor, "tLogRow_1");
        Assert.assertNotNull(logRow1_2);
        SWTBotGefEditPart rowMain1_2 = gefEditor.getEditPart("row1 (Main)");
        Assert.assertNotNull(rowMain1_2);
        gefEditor.saveAndClose();
    }

    @After
    public void removePreviouslyCreateItems() {
        tree.expandNode("Job Designs").getNode(JOBNAME1 + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Job Designs").getNode(JOBNAME2 + " 0.1").contextMenu("Delete").click();
        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
