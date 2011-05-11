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
public class RetrieveSchemaInContextModeTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private static final String DBNAME = "mysql";

    private static final String TABLENAME = "autotest";

    @Before
    public void createDBConnection() {
        view = Utilities.getRepositoryView(gefBot);
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.DB_CONNECTIONS);
        Utilities.createDbConnection(gefBot, treeNode, Utilities.DbConnectionType.MYSQL, DBNAME);
        String sql = "create table " + TABLENAME + "(id int, name varchar(20))";
        Utilities.executeSQL(gefBot, treeNode.getNode(DBNAME + " 0.1"), sql);
    }

    @Test
    public void retrieveSchemaInContextMode() {
        SWTBotShell tempShell = null;
        SWTBotTreeItem tableItem = null;
        try {
            treeNode.getNode(DBNAME + " 0.1").doubleClick();
            tempShell = gefBot.shell("Database Connection").activate();
            gefBot.button("Next >").click();
            gefBot.button("Export as context").click();
            gefBot.shell("Create / Edit a context group").activate();
            gefBot.button("Finish").click();
            gefBot.button("Finish").click();
            gefBot.shell("Modification").activate();
            gefBot.button("No").click();
        } catch (WidgetNotFoundException wnfe) {
            tempShell.close();
            Assert.fail(wnfe.getCause().getMessage());
        } catch (Exception e) {
            tempShell.close();
            Assert.fail(e.getMessage());
        }
        Utilities.retrieveDbSchema(gefBot, treeNode, DBNAME, TABLENAME);

        tableItem = treeNode.expandNode(DBNAME + " 0.1", "Table schemas").getNode(TABLENAME);
        Assert.assertNotNull("schemas did not retrieve", tableItem);

    }

    @After
    public void removePreviouslyCreateItems() {
        String sql = "drop table " + TABLENAME;
        Utilities.executeSQL(gefBot, treeNode.expandNode(DBNAME + " 0.1"), sql);
        Utilities.cleanUpRepository(treeNode);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
