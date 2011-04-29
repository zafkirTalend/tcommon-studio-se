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
package tosstudio.businessmodels;

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
 * DOC fzhong class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class NotCreateNewItemInRecycleBinTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private static final String FOLDERNAME = "folderTest";

    private static final String BUSINESS_MODEL_NAME = "businessTest";

    @Before
    public void initialisePrivateField() {
        view = Utilities.getRepositoryView(gefBot);
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.BUSINESS_MODEL);
        Utilities.createFolder(FOLDERNAME, treeNode, gefBot);
        Utilities.createBusinessModel(BUSINESS_MODEL_NAME, treeNode.getNode(FOLDERNAME), gefBot);
        gefBot.editorByTitle("Model " + BUSINESS_MODEL_NAME).close();
        Utilities.delete(tree, treeNode, FOLDERNAME, null, null);
    }

    @Test
    public void createNewItemInRecycleBin() {
        SWTBotTreeItem recycleBinNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.RECYCLE_BIN);

        boolean isCreateEnable = false;
        try {
            recycleBinNode.getNode(FOLDERNAME + " ()").contextMenu("Create Business Model").isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertFalse("can create new item in recycle bin", isCreateEnable);
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
