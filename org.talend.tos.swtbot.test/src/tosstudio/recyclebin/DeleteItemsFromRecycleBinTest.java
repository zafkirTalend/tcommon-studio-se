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
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DeleteItemsFromRecycleBinTest extends TalendSwtBotForTos {

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
    public void deleteItemsFromRecycleBin() {
        recycleBinNode = Utilities.getTalendItemNode(Utilities.TalendItemType.RECYCLE_BIN);
        recycleBinNode.getNode(JOBNAME + " 0.1 ()").contextMenu("Delete forever").click();
        gefBot.shell("Delete forever").activate();
        gefBot.button("Yes").click();
        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                return (recycleBinNode.rowCount() == 0);
            }

            public String getFailureMessage() {
                Utilities.emptyRecycleBin();
                return "items did not delete forever";
            }
        });
    }
}
