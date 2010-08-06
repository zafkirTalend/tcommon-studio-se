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
package tosstudio.projectmanagement.performance;

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
public class DeleteJob {

    private static SWTGefBot gefBot;

    private static SWTBotShell shell;

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String JOBNAME = "test01";

    private static String DEFAULT_VERSION = "0.1";

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
    public void deleteJob() {
        view = gefBot.viewByTitle(org.talend.repository.i18n.Messages.getString("repository.title"));
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode(org.talend.core.i18n.Messages.getString("repository.process")).getNode(JOBNAME + " " + DEFAULT_VERSION)
                .contextMenu(org.talend.repository.i18n.Messages.getString("DeleteAction.action.logicalTitle")).click();

        tree.expandNode(org.talend.repository.i18n.Messages.getString("BinRepositoryNode.label")).getNode(
                JOBNAME + " " + DEFAULT_VERSION + " ()").contextMenu(
                org.talend.repository.i18n.Messages.getString("DeleteAction.action.foreverTitle")).click();

        shell = gefBot.shell(org.talend.repository.i18n.Messages.getString("DeleteAction.action.foreverTitle"));
        shell.activate();
        gefBot.button("Yes").click();
    }

    @AfterClass
    public static void clearDown() {

    }
}
