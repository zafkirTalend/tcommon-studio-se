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
package tisstudio.jobscript;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CopyPasteJobScriptTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private static String JOBSCRIPT_NAME = "jobscript1"; //$NON-NLS-1$

    @Before
    public void InitialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        tree.expandNode("Code").getNode("Job Scripts").contextMenu("Create JobScript").click();

        gefBot.textWithLabel("Name").setText(JOBSCRIPT_NAME);
        gefBot.button("Finish").click();
    }

    @Test
    public void copyAndPasteJobScript() {
        tree.expandNode("Code", "Job Scripts").getNode(JOBSCRIPT_NAME + " 0.1").contextMenu("Copy").click();
        tree.select("Code", "Job Scripts").contextMenu("Paste").click();

        SWTBotTreeItem newJobScriptItem = tree.expandNode("Code", "Job Scripts").select("Copy_of_" + JOBSCRIPT_NAME + " 0.1");
        Assert.assertNotNull(newJobScriptItem);
    }

    @After
    public void removePreviousCreateItems() {
        gefBot.cTabItem(JOBSCRIPT_NAME + "_0.1.jobscript").close();
        tree.expandNode("Code", "Job Scripts").getNode(JOBSCRIPT_NAME + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Code", "Job Scripts").getNode("Copy_of_" + JOBSCRIPT_NAME + " 0.1").contextMenu("Delete").click();

        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
