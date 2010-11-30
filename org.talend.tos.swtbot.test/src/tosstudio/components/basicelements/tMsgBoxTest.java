package tosstudio.components.basicelements;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

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

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class tMsgBoxTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotShell shell;

    private SWTBotView view;

    private SWTBotGefEditor gefEditor;

    private static String JOBNAME = "tMsgBoxTesting"; //$NON-NLS-1$

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

        gefEditor.activateTool("tMsgBox").click(100, 100);
        SWTBotGefEditPart msgBox = getTalendComponentPart(gefEditor, "tMsgBox_1");
        Assert.assertNotNull(msgBox);

        // gefBot.viewByTitle("Component").setFocus();
        // gefBot.text("\"Hello world!\"").setText("\"abcdefg\"");

        gefEditor.save();

        /* Run the job */
        gefBot.viewByTitle("Run (Job " + JOBNAME + ")").setFocus();
        gefBot.button(" Run").click();

        gefBot.waitUntil(Conditions.shellIsActive("Launching " + JOBNAME + " 0.1"));
        gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Launching " + JOBNAME + " 0.1")));
    }

    @After
    public void removePreviousCreateItems() {
        gefBot.cTabItem("Job " + JOBNAME + " 0.1").close();
        tree.expandNode("Job Designs").getNode(JOBNAME + " 0.1").contextMenu("Delete").click();
        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }

}
