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

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CreateMysqlTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotShell shell;

    private SWTBotView view;

    private static String DBTYPE = "MySQL"; //$NON-NLS-1$

    private static String DBNAME = "test_mysql"; //$NON-NLS-1$

    private static String DBLOGIN = "root"; //$NON-NLS-1$

    private static String DBPASSWORD = "123456"; //$NON-NLS-1$

    private static String DBSERVER = "localhost"; //$NON-NLS-1$

    private static String DB = "test"; //$NON-NLS-1$

    @Before
    public void InitialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
    }

    @Test
    public void createMySQL() {
        tree.setFocus();

        tree.expandNode("Metadata").getNode("Db Connections").contextMenu("Create connection").click();
        gefBot.waitUntil(Conditions.shellIsActive("Database Connection"));
        gefBot.shell("Database Connection").activate();

        gefBot.textWithLabel("Name").setText(DBNAME);
        gefBot.button("Next >").click();
        gefBot.comboBox(0).setSelection(DBTYPE);
        gefBot.textWithLabel("Login").setText(DBLOGIN);
        gefBot.textWithLabel("Password").setText(DBPASSWORD);
        gefBot.textWithLabel("Server").setText(DBSERVER);
        gefBot.textWithLabel("DataBase").setText(DB);
        gefBot.button("Check").click();

        shell = gefBot.shell("Check Connection ");
        shell.activate();
        gefBot.waitUntil(Conditions.shellIsActive("Check Connection "));
        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));

        gefBot.button("Finish").click();

        SWTBotTreeItem newMysqlItem = tree.expandNode("Metadata", "Db Connections").select(DBNAME + " 0.1");
        Assert.assertNotNull(newMysqlItem);
    }

    @After
    public void removePreviouslyCreateItems() {
        tree.expandNode("Metadata", "Db Connections").getNode(DBNAME + " 0.1").contextMenu("Delete").click();
        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
