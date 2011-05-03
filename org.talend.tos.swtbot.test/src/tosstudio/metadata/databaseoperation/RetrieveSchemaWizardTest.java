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
public class RetrieveSchemaWizardTest extends TalendSwtBotForTos {

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
    public void retrieveSchema() {
        SWTBotShell schemaShell = null;
        int rowCount = 0;
        try {
            treeNode.getNode(DBNAME + " 0.1").contextMenu("Retrieve Schema").click();
            schemaShell = gefBot.shell("Schema").activate();
            gefBot.button("Next >").click();
            gefBot.tree(0).expandNode(System.getProperty("mysql.dataBase")).getNode(TABLENAME).check();
            gefBot.button("Next >").click();
            gefBot.button("Cancel").click();

            rowCount = treeNode.expandNode(DBNAME + " 0.1").getNode("Table schemas").rowCount();
        } catch (WidgetNotFoundException wnfe) {
            schemaShell.close();
            Assert.fail(wnfe.getCause().getMessage());
        } catch (Exception e) {
            schemaShell.close();
            Assert.fail(e.getMessage());
        } finally {
            Assert.assertEquals("schemas be retrieved even cancel the wizard of retrieving schema", 0, rowCount);
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        String sql = "drop table " + TABLENAME;
        Utilities.executeSQL(gefBot, treeNode.expandNode(DBNAME + " 0.1"), sql);
        Utilities.cleanUpRepository(treeNode);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
