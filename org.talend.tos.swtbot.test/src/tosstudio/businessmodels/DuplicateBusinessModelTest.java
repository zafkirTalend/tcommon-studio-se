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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DuplicateBusinessModelTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotView view;

    private SWTBotTreeItem treeNode;

    private static final String BUSINESSMODELNAME = "businessModel1"; //$NON-NLS-1$

    private static final String NEW_BUSINESSMODELNAME = "duplicate_businessModel1"; //$NON-NLS-1$

    @Before
    public void initialisePrivateFields() {
        view = Utilities.getRepositoryView(gefBot);
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.BUSINESS_MODEL);
        Utilities.createBusinessModel(BUSINESSMODELNAME, treeNode, gefBot);
    }

    @Test
    public void duplicateBusinessModel() {
        Utilities.duplicate(gefBot, treeNode, BUSINESSMODELNAME, "0.1", NEW_BUSINESSMODELNAME);
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.cTabItem("Model " + BUSINESSMODELNAME).close();
        Utilities.delete(tree, treeNode, BUSINESSMODELNAME, "0.1", null);
        Utilities.delete(tree, treeNode, NEW_BUSINESSMODELNAME, "0.1", null);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
