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
package metadata.databaseoperation;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class tMySQLInput {

    private static SWTGefBot gefBot;

    private static SWTBotShell shell;

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String DBTYPE = "MySQL";

    private static String DBNAME = "test_mysql";

    private static String DBLOGIN = "root";

    private static String DBPASSWORD = "123456";

    private static String DBSERVER = "localhost";

    private static String DB = "test";

    private static String VERSION = "0.1";

    private static String JOBNAME = "UsetMySQLInput";

    @BeforeClass
    public static void setUp() {
        /**
         * Initialization
         */

        gefBot = new SWTGefBot();

        gefBot.waitUntil(Conditions.shellIsActive(org.talend.designer.codegen.i18n.Messages
                .getString("CodeGeneratorEmittersPoolFactory.initMessage")));

        shell = gefBot.shell(org.talend.designer.codegen.i18n.Messages.getString("CodeGeneratorEmittersPoolFactory.initMessage"));
        shell.activate();

        gefBot.waitUntil(Conditions.shellCloses(shell), 60000 * 5);

        gefBot.viewByTitle("Welcome").close();
    }

    @Test
    public void createConnection() {
        view = gefBot.viewByTitle(org.talend.repository.i18n.Messages.getString("repository.title"));
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode(org.talend.core.i18n.Messages.getString("repository.metadata")).getNode(
                org.talend.core.i18n.Messages.getString("repository.metadataConnections")).contextMenu(
                org.talend.repository.i18n.Messages.getString("CreateConnectionAction.action.createTitle")).click();
        gefBot.waitUntil(Conditions.shellIsActive(org.talend.repository.i18n.Messages.getString("DatabaseWizard.windowTitle")));
        gefBot.shell(org.talend.repository.i18n.Messages.getString("DatabaseWizard.windowTitle")).activate();

        gefBot.textWithLabel(org.talend.core.i18n.Messages.getString("PropertiesWizardPage.Name")).setText(DBNAME);
        gefBot.button("Next >").click();
        gefBot.comboBox(0).setSelection(DBTYPE);
        gefBot.textWithLabel(org.talend.repository.i18n.Messages.getString("DatabaseForm.login")).setText(DBLOGIN);
        gefBot.textWithLabel(org.talend.repository.i18n.Messages.getString("DatabaseForm.password")).setText(DBPASSWORD);
        gefBot.textWithLabel(org.talend.repository.i18n.Messages.getString("DatabaseForm.server")).setText(DBSERVER);
        gefBot.textWithLabel("DataBase").setText(DB);
        gefBot.button(org.talend.repository.i18n.Messages.getString("DatabaseForm.check")).click();

        gefBot.waitUntil(Conditions.shellIsActive(org.talend.repository.i18n.Messages
                .getString("DatabaseForm.checkConnectionTitle")));
        shell = gefBot.shell(org.talend.repository.i18n.Messages.getString("DatabaseForm.checkConnectionTitle"));
        shell.activate();
        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));

        gefBot.button("Finish").click();
    }

    @Test
    public void retrieveSchema() {
        tree.expandNode(org.talend.core.i18n.Messages.getString("repository.metadata"),
                org.talend.core.i18n.Messages.getString("repository.metadataConnections")).getNode(DBNAME + " " + VERSION)
                .contextMenu(org.talend.repository.i18n.Messages.getString("CreateTableAction.action.createTitle")).click();

        gefBot.waitUntil(Conditions.shellIsActive(org.talend.repository.i18n.Messages.getString("TableWizard.windowTitle")));
        shell = gefBot.shell(org.talend.repository.i18n.Messages.getString("TableWizard.windowTitle"));
        shell.activate();
        gefBot.button("Next >").click();
        gefBot.table(0).getTableItem(0).check();
        gefBot.button("Next >").click();
        gefBot.button("Finish").click();
    }

    @Test
    public void createJob() {
        tree.select(org.talend.core.i18n.Messages.getString("repository.process")).contextMenu(
                org.talend.designer.core.i18n.Messages.getString("CreateProcess.createJob")).click();

        gefBot.waitUntil(Conditions.shellIsActive(org.talend.designer.core.i18n.Messages.getString("NewProcessWizard.title")));
        shell = gefBot.shell(org.talend.designer.core.i18n.Messages.getString("NewProcessWizard.title"));
        shell.activate();

        gefBot.textWithLabel(org.talend.core.i18n.Messages.getString("PropertiesWizardPage.Name")).setText(JOBNAME);

        gefBot.button("Finish").click();
    }

    @Test
    public void useDataInJob() {
        // can not drag&drop the schema on job now
    }

    @AfterClass
    public static void clearDown() {

    }
}
