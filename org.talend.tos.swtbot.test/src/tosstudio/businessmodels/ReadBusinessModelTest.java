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
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
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
public class ReadBusinessModelTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private static final String BUSINESS_MODEL_NAME = "businessTest";

    @Before
    public void createBusinessModel() {
        view = Utilities.getRepositoryView(gefBot);
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.BUSINESS_MODEL);
        Utilities.createBusinessModel(BUSINESS_MODEL_NAME, treeNode, gefBot);
        gefBot.editorByTitle("Model " + BUSINESS_MODEL_NAME).close();
    }

    @Test
    public void readBusinessModel() {
        treeNode.getNode(BUSINESS_MODEL_NAME + " 0.1").contextMenu("Read Business Model").click();

        boolean isEditorActive = false;
        try {
            isEditorActive = gefBot.editorByTitle("Model " + BUSINESS_MODEL_NAME).isActive();
        } catch (WidgetNotFoundException wnfe) {
            wnfe.printStackTrace();
        } finally {
            Assert.assertTrue("business model did not open", isEditorActive);
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.editorByTitle("Model " + BUSINESS_MODEL_NAME).close();
        Utilities.cleanUpRepository(treeNode);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
