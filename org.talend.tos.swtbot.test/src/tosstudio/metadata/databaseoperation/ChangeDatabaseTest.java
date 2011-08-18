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
package tosstudio.metadata.databaseoperation;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
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
public class ChangeDatabaseTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private static final String DBNAME = "mysql";

    private static final String DATABASE_NAME = "autotest2";

    @Before
    public void createDBConnection() {
        view = Utilities.getRepositoryView();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(Utilities.TalendItemType.DB_CONNECTIONS);
        Utilities.createDbConnection(treeNode, Utilities.DbConnectionType.MYSQL, DBNAME);
        String sql = "create database " + DATABASE_NAME;
        Utilities.executeSQL(treeNode.getNode(DBNAME + " 0.1"), sql);
    }

    @Test
    public void changeDatabase() {
        SWTBotShell schemaShell = null;
        SWTBotTreeItem treeItem = null;
        try {
            treeNode.getNode(DBNAME + " 0.1").doubleClick();
            schemaShell = gefBot.shell("Database Connection").activate();
            gefBot.button("Next >").click();
            gefBot.textWithLabel("DataBase").setText(DATABASE_NAME);
            gefBot.button("Finish").click();
            gefBot.shell("Modification").activate();
            gefBot.button("No").click();

            treeNode.getNode(DBNAME + " 0.1").contextMenu("Retrieve Schema").click();
            schemaShell = gefBot.shell("Schema").activate();
            gefBot.button("Next >").click();
            treeItem = gefBot.treeInGroup("Select Schema to create").getTreeItem(DATABASE_NAME);
            schemaShell.close();
            Assert.assertNotNull("database did not change", treeItem);
        } catch (WidgetNotFoundException wnfe) {
            schemaShell.close();
            Assert.fail(wnfe.getCause().getMessage());
        } catch (Exception e) {
            schemaShell.close();
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        String sql = "drop database " + DATABASE_NAME;
        Utilities.executeSQL(treeNode.getNode(DBNAME + " 0.1"), sql);
        Utilities.cleanUpRepository(treeNode);
        Utilities.emptyRecycleBin();
    }
}
