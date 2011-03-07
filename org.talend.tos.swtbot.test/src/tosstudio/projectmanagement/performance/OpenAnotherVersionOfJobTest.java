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
package tosstudio.projectmanagement.performance;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCTabItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class OpenAnotherVersionOfJobTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotView view;

    private SWTBotTreeItem treeNode;

    private static final String JOBNAME = "test01"; //$NON-NLS-1$

    @Before
    public void createAJob() {
        view = Utilities.getRepositoryView(gefBot);
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.JOB_DESIGNS);
        Utilities.createJob(JOBNAME, treeNode, gefBot);
    }

    @Test
    public void openAnotherVersionOfJob() {
        gefBot.cTabItem("Job " + JOBNAME + " 0.1").close();
        treeNode.getNode(JOBNAME + " 0.1").contextMenu("Open an other version").click();

        gefBot.shell("New job").activate();
        gefBot.checkBox("Create new version and open it?").click();
        gefBot.button("M").click();
        gefBot.button("m").click();
        gefBot.button("Finish").click();

        SWTBotCTabItem newJobTabItem1 = gefBot.cTabItem("Job " + JOBNAME + " 1.1");
        Assert.assertNotNull("job tab is not opened", newJobTabItem1);

        tree.expandNode("Job Designs").getNode(JOBNAME + " 1.1").contextMenu("Open an other version").click();
        gefBot.shell("New job").activate();
        gefBot.table().select(0);
        gefBot.button("Finish").click();

        SWTBotCTabItem newJobTabItem2 = gefBot.cTabItem("Job " + JOBNAME + " 0.1");
        Assert.assertNotNull("job tab is not opened", newJobTabItem2);
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.cTabItem("Job " + JOBNAME + " 0.1").close();
        gefBot.toolbarButtonWithTooltip("Save (Ctrl+S)").click();
        gefBot.cTabItem("Job " + JOBNAME + " 1.1").close();
        Utilities.delete(tree, treeNode, JOBNAME, "1.1", null);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
