import i18n.Messages;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class tFileInputDelimited {

    private static SWTGefBot bot;

    private static SWTBotShell shell;

    private static SWTBotView view;

    private static SWTBotTree tree;

    private static SWTBotEditor botEditor;

    private static SWTBotGefEditor gefEditor;

    private static String JOBNAME = "test01";

    private static String FILENAME = "\"E:/testdata/delimited1.csv\"";

    @BeforeClass
    public static void setup() {
        bot = new SWTGefBot();

        bot.waitUntil(Conditions.shellIsActive(Messages.getString("CodeGeneratorEmittersPoolFactory.initMessage")));

        shell = bot.shell(Messages.getString("CodeGeneratorEmittersPoolFactory.initMessage"));
        shell.activate();

        bot.waitUntil(Conditions.shellCloses(shell), 60000 * 5);

        bot.viewByTitle("Welcome").close();
    }

    @Test
    public void createJob() {
        view = bot.viewByTitle(Messages.getString("repository.title"));
        view.setFocus();

        tree = new SWTBotTree((Tree) bot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        tree.select(Messages.getString("repository.process")).contextMenu(Messages.getString("CreateProcess.createJob")).click();

        bot.waitUntil(Conditions.shellIsActive(Messages.getString("NewProcessWizard.title")));
        shell = bot.shell(Messages.getString("NewProcessWizard.title"));
        shell.activate();

        bot.textWithLabel(Messages.getString("PropertiesWizardPage.Name")).setText(JOBNAME);

        bot.button("Finish").click();
    }

    @Test
    public void useComponents() {
        bot.viewByTitle("Palette").close();
        botEditor = bot.activeEditor();
        gefEditor = bot.gefEditor(botEditor.getTitle());
        gefEditor.activateTool("tFileInputDelimited");
        gefEditor.click(100, 100);
        gefEditor.activateTool("tLogRow");
        gefEditor.click(300, 100);

        gefEditor.click(110, 110);
        bot.viewByTitle(Messages.getString("UpdatesConstants.Component")).show();
        // label and text do not correspond
        bot.textWithLabel(Messages.getString("EParameterName.propertyType")).setText(FILENAME);
        // label and button do not correspond
        bot.buttonWithLabel(Messages.getString("EParameterName.schemaDb")).click();
        shell = bot.shell(Messages.getString("SchemaController.schemaOf") + "tFileInputDelimited_1");
        shell.activate();
        for (int i = 0; i < 4; i++) {
            bot.buttonWithTooltip(Messages.getString("AddPushButton.AddButton.Tip")).click();
        }
        bot.button("OK").click();

        gefEditor.click(110, 110);
        gefEditor.clickContextMenu(Messages.getString("TalendEditorContextMenuProvider.Row")).clickContextMenu(
                Messages.getString("EConnectionType.mainMenu"));
        gefEditor.click(310, 110);

        bot.viewByTitle(Messages.getString("ProblemsView.problems.defaultTitle")).close();
        bot.viewByTitle(Messages.getString("ProcessView.title", "Job " + JOBNAME)).setFocus();
        bot.buttonWithTooltip(Messages.getString("ProcessComposite.execHint")).click();

    }

    @AfterClass
    public static void clearDown() {

    }
}
