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
package tosstudio.recyclebin;

import org.eclipse.swt.widgets.Tree;
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
import org.talend.swtbot.Utilities;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class RestoreItemsFromRecycleBinTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private SWTBotTreeItem recycleBinNode;

    private static final String JOBNAME = "jobTest";

    @Before
    public void initialisePrivateField() {
        view = Utilities.getRepositoryView();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(Utilities.TalendItemType.JOB_DESIGNS);
        Utilities.createJob(JOBNAME, treeNode);
        gefBot.cTabItem("Job " + JOBNAME + " 0.1").close();
        Utilities.delete(treeNode, JOBNAME, "0.1", null);
    }

    @Test
    public void restoreItemsFromRecycleBin() {
        recycleBinNode = Utilities.getTalendItemNode(Utilities.TalendItemType.RECYCLE_BIN);
        recycleBinNode.getNode(JOBNAME + " 0.1 ()").contextMenu("Restore").click();

        SWTBotTreeItem jobItem = treeNode.expand().select(JOBNAME + " 0.1");
        Assert.assertNotNull("item did not restore from recycle bin", jobItem);
    }

    @After
    public void removePreviouslyCreateItems() {
        Utilities.delete(treeNode, JOBNAME, "0.1", null);
        Utilities.emptyRecycleBin();
    }
}
