import i18n.Messages;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class Test01 {

    private static SWTWorkbenchBot bot;

    private static SWTBotShell shell;

    private static SWTBotView view;

    private static SWTBotTree tree;

    @BeforeClass
    public static void before() {
        /**
         * Initialization
         */

        bot = new SWTWorkbenchBot();

        bot.waitUntil(Conditions.shellIsActive(Messages.getString("CodeGeneratorEmittersPoolFactory.initMessage")));

        shell = bot.shell(Messages.getString("CodeGeneratorEmittersPoolFactory.initMessage"));
        shell.activate();

        bot.waitUntil(Conditions.shellCloses(shell), 60000 * 5);

        bot.viewByTitle("Welcome").close();
        bot.menu(Messages.getString("ApplicationActionBarAdvisor.menuWindowLabel")).menu(
                Messages.getString("ShowViewAction.actionLabel")).click();
        bot.shell("Show View").activate();
        SWTBotTree tree = new SWTBotTree((Tree) bot.widget(WidgetOfType.widgetOfType(Tree.class)));
        tree.expandNode("General").select("Error Log", "Progress");
        bot.button("OK").click();
        bot.viewByTitle("Error Log").setFocus();
    }

    @Test
    public void CreateJob() {

        /**
         * Create a new job ----------------------------------------------------
         */

        view = bot.viewByTitle(Messages.getString("repository.title"));
        view.setFocus();

        tree = new SWTBotTree((Tree) bot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        tree.select(Messages.getString("repository.process")).contextMenu(Messages.getString("CreateProcess.createJob")).click();

        bot.waitUntil(Conditions.shellIsActive(Messages.getString("NewProcessWizard.title")));
        shell = bot.shell(Messages.getString("NewProcessWizard.title"));
        shell.activate();

        bot.textWithLabel(Messages.getString("PropertiesWizardPage.Name")).setText("test01");

        bot.button("Finish").click();

        bot.cTabItem(Messages.getString("MultiPageTalendEditor.Job", "test01", "0.1")).close();
    }

    @Test
    public void JobCopyPasteDelete() {

        /**
         * Copy job ----------------------------------------------------
         */

        tree.expandNode(Messages.getString("repository.process")).getNode("test01 0.1").contextMenu(
                Messages.getString("CopyAction.thisText.copy")).click();

        /**
         * Paste job ----------------------------------------------------
         */

        tree.select(Messages.getString("repository.process")).contextMenu(Messages.getString("PasteAction.thisText.paste"))
                .click();

        /**
         * Delete job ----------------------------------------------------
         */

        tree.expandNode(Messages.getString("repository.process")).getNode("test01 0.1").contextMenu(
                Messages.getString("DeleteAction.action.logicalTitle")).click();

        tree.expandNode(Messages.getString("repository.recyclebin")).getNode("test01 0.1 ()").contextMenu(
                Messages.getString("DeleteAction.action.foreverTitle")).click();

        shell = bot.shell(Messages.getString("DeleteAction.action.foreverTitle"));
        shell.activate();
        bot.button("Yes").click();
    }

    @Test
    public void CreateConnection() {

        /**
         * Create a new connection ----------------------------------------------------
         */

        bot.toolbarDropDownButtonWithTooltip("Create").menuItem(Messages.getString("CreateConnectionAction.action.createTitle"))
                .click();
        bot.waitUntil(Conditions.shellIsActive(Messages.getString("PropertiesWizardPage.SelectfolderTitle")));

        bot.shell(Messages.getString("PropertiesWizardPage.SelectfolderTitle")).activate();

        bot.button("OK").click();
        bot.shell(Messages.getString("DatabaseWizard.windowTitle")).activate();

        bot.textWithLabel(Messages.getString("PropertiesWizardPage.Name")).setText("test_mssql");

        bot.button("Next >").click();

        // bot.comboBoxWithLabel(Messages.getString("MetadataTableEditorView.DBTypeTitle")).setSelection("MySQL");
        // bot.comboBoxWithLabel("必须选择 DB 类型").setSelection("MySQL");
        bot.comboBox(0).setSelection("MySQL");

        bot.textWithLabel(Messages.getString("DatabaseForm.login")).setText("root");

        bot.textWithLabel(Messages.getString("DatabaseForm.password")).setText("123456");

        bot.textWithLabel(Messages.getString("DatabaseForm.server")).setText("localhost");

        bot.textWithLabel("DataBase").setText("mytest");

        bot.button(Messages.getString("DatabaseForm.check")).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.getString("DatabaseForm.checkConnectionTitle")));

        if (bot.activeShell().getText().equals(Messages.getString("DatabaseForm.checkConnectionTitle"))) {
            bot.button("OK").click();
        }

        bot.button("Finish").click();

        tree.expandNode(Messages.getString("repository.metadata")).expandNode(
                Messages.getString("repository.metadataConnections")).expandNode("test_mssql 0.1");
    }

    @Test
    public void OpenJob() {
        tree.expandNode(Messages.getString("repository.process")).getNode("Copy_of_test01 0.1").doubleClick();

    }

    @AfterClass
    public static void after() {
        bot.sleep(3000);
    }

}
