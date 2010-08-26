// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class tMySQLInput extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotShell shell;

    private static SWTBotView view;

    private static String DBTYPE = "MySQL";

    private static String DBNAME = "test_mysql";

    private static String DBLOGIN = "root";

    private static String DBPASSWORD = "123456";

    private static String DBSERVER = "localhost";

    private static String DB = "test";

    private static String VERSION = "0.1";

    private static String JOBNAME = "UsetMySQLInput";

    @Test
    public void createConnection() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
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

        gefBot.waitUntil(Conditions.shellIsActive("Check Connection"));
        shell = gefBot.shell("Check Connection");
        shell.activate();
        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));

        gefBot.button("Finish").click();
    }

    @Test
    public void retrieveSchema() {
        tree.expandNode("Metadata", "Db Connections").getNode(DBNAME + " " + VERSION).contextMenu("Retrieve Schema").click();

        gefBot.waitUntil(Conditions.shellIsActive("Schema"));
        shell = gefBot.shell("Schema");
        shell.activate();
        gefBot.button("Next >").click();
        gefBot.table(0).getTableItem(0).check();
        gefBot.button("Next >").click();
        gefBot.button("Finish").click();
    }

    @Test
    public void createJob() {
        tree.select("Job Designs").contextMenu("Create job").click();

        gefBot.waitUntil(Conditions.shellIsActive("New job"));
        shell = gefBot.shell("New job");
        shell.activate();

        gefBot.textWithLabel("Name").setText(JOBNAME);

        gefBot.button("Finish").click();
    }

    @Test
    public void useDataInJob() {
        // can not drag&drop the schema on job now
    }

}
