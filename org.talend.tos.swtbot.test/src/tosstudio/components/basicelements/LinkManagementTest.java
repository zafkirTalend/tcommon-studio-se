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
public class LinkManagementTest extends TalendSwtBotForTos {

    public SWTBotTree tree;

    public SWTBotShell shell;

    public SWTBotView view;

    private SWTBotGefEditor gefEditor;

    public static String JOBNAME = "linkManagement"; //$NON-NLS-1$

    @Before
    public void createJob() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        tree.select("Job Designs").contextMenu("Create job").click();

        gefBot.waitUntil(Conditions.shellIsActive("New job"));
        shell = gefBot.shell("New job");
        shell.activate();

        gefBot.textWithLabel("Name").setText(JOBNAME);

        gefBot.button("Finish").click();
    }

    @Test
    public void useComponentInJob() {
        gefEditor = gefBot.gefEditor("Job " + JOBNAME + " 0.1");

        gefEditor.activateTool("tRowGenerator").click(100, 100);
        gefEditor.activateTool("tLogRow").click(300, 100);

        SWTBotGefEditPart rowGen = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        rowGen.doubleClick();
        shell = gefBot.shell("Talend Data Quality Enterprise Edition MPX - tRowGenerator - tRowGenerator_1");
        shell.activate();
        gefBot.buttonWithTooltip("Add").click();
        gefBot.buttonWithTooltip("Add").click();
        gefBot.button("OK").click();

        gefEditor.select(rowGen);
        gefEditor.clickContextMenu("Row").clickContextMenu("Main");
        SWTBotGefEditPart logRow = getTalendComponentPart(gefEditor, "tLogRow_1");
        gefEditor.click(logRow);

        gefBot.viewByTitle("Component").show();
        logRow.click();
        gefBot.buttonWithLabel("Schema").click(); // label and button do not correspond
        shell = gefBot.shell("Schema oftLogRow_1");
        shell.activate();
        gefBot.waitUntil(Conditions.shellIsActive("Schema oftLogRow_1"));
        Assert.assertEquals("newColumn", gefBot.tableWithLabel("tLogRow_1 (Output)").cell(0, "Column"));
        Assert.assertEquals("newColumn1", gefBot.tableWithLabel("tLogRow_1 (Output)").cell(1, "Column"));
        gefBot.button("OK").click();

        gefEditor.saveAndClose();
    }

    @After
    public void removePreviousCreateItems() {
        tree.expandNode("Job Designs").getNode(JOBNAME + " 0.1").contextMenu("Delete").click();
        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
