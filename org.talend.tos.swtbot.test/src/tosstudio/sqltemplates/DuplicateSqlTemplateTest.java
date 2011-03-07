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
package tosstudio.sqltemplates;

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
public class DuplicateSqlTemplateTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private static final String SQLTEMPLATENAME = "sqlTemplate1"; //$NON-NLS-1$

    private static final String NEW_SQLTEMPLATENAME = "duplicate_sqlTemplate1"; //$NON-NLS-1$

    private static final String FOLDERPATH = "Generic/UserDefined"; //$NON-NLS-1$

    @Before
    public void createSqlTemplate() {
        view = Utilities.getRepositoryView(gefBot);
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.SQL_TEMPLATES).expandNode("Generic", "UserDefined");
        Utilities.createSqlTemplate(SQLTEMPLATENAME, treeNode, gefBot);
    }

    @Test
    public void duplicateSqlTemplate() {
        Utilities.duplicate(gefBot, treeNode, SQLTEMPLATENAME, "0.1", NEW_SQLTEMPLATENAME);
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.cTabItem(SQLTEMPLATENAME + " 0.1").close();
        Utilities.delete(tree, treeNode, SQLTEMPLATENAME, "0.1", FOLDERPATH);
        Utilities.delete(tree, treeNode, NEW_SQLTEMPLATENAME, "0.1", FOLDERPATH);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
