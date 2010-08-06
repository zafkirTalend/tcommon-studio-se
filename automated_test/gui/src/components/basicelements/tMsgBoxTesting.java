package components.basicelements;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

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
@RunWith(SWTBotJunit4ClassRunner.class)
public class tMsgBoxTesting {

    private static SWTGefBot gefBot;

    private static SWTBotShell shell;

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static SWTBotEditor botEditor;

    private static SWTBotGefEditor gefEditor;

    private static String JOBNAME = "tMsgBoxTesting";

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
    public void createJob() {
        view = gefBot.viewByTitle(org.talend.repository.i18n.Messages.getString("repository.title"));
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        tree.select(org.talend.core.i18n.Messages.getString("repository.process")).contextMenu(
                org.talend.designer.core.i18n.Messages.getString("CreateProcess.createJob")).click();

        gefBot.waitUntil(Conditions.shellIsActive(org.talend.designer.core.i18n.Messages.getString("NewProcessWizard.title")));
        shell = gefBot.shell(org.talend.designer.core.i18n.Messages.getString("NewProcessWizard.title"));
        shell.activate();

        gefBot.textWithLabel(org.talend.core.i18n.Messages.getString("PropertiesWizardPage.Name")).setText(JOBNAME);

        gefBot.button("Finish").click();
    }

    @Test
    public void useComponentInJob() {
        gefBot.viewByTitle("Palette").close();
        botEditor = gefBot.activeEditor();
        gefEditor = gefBot.gefEditor(botEditor.getTitle());
        gefEditor.activateTool("tMsgBox");
        gefEditor.click(100, 100);

        gefBot.viewByTitle(
                org.talend.designer.runprocess.i18n.Messages.getString("ProcessView.title",
                        org.talend.designer.runprocess.i18n.Messages.getString("ProcessView.jobName") + " " + JOBNAME))
                .setFocus();
        gefBot.buttonWithTooltip(org.talend.designer.runprocess.i18n.Messages.getString("ProcessComposite.execHint")).click();
    }

    @AfterClass
    public static void clearDown() {

    }
}
