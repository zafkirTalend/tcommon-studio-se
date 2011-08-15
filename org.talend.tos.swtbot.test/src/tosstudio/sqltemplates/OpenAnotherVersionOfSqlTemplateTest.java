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
public class OpenAnotherVersionOfSqlTemplateTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotView view;

    private SWTBotTreeItem treeNode;

    private static final String SQLTEMPLATE_NAME = "sqlTemplateTest"; //$NON-NLS-1$

    private static final String FOLDERPATH = "Generic/UserDefined"; //$NON-NLS-1$

    @Before
    public void createAJob() {
        view = Utilities.getRepositoryView();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(Utilities.TalendItemType.SQL_TEMPLATES).expandNode("Generic", "UserDefined");
        Utilities.createSqlTemplate(SQLTEMPLATE_NAME, treeNode);
        gefBot.editorByTitle(SQLTEMPLATE_NAME + " 0.1").saveAndClose();
    }

    @Test
    public void openAnotherVersionOfSqlTemplate() {
        treeNode.getNode(SQLTEMPLATE_NAME + " 0.1").contextMenu("Open an other version").click();

        gefBot.shell("New job").activate();
        gefBot.checkBox("Create new version and open it?").click();
        gefBot.button("M").click();
        gefBot.button("m").click();
        gefBot.button("Finish").click();

        SWTBotCTabItem newTabItem1 = gefBot.cTabItem(SQLTEMPLATE_NAME + " 1.1");
        Assert.assertNotNull("sql template tab is not opened", newTabItem1);
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.editorByTitle(SQLTEMPLATE_NAME + " 1.1").saveAndClose();
        Utilities.delete(treeNode, SQLTEMPLATE_NAME, "1.1", FOLDERPATH);
        Utilities.emptyRecycleBin();
    }
}
