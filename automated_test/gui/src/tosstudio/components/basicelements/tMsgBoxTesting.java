package tosstudio.components.basicelements;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

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
public class tMsgBoxTesting extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotShell shell;

    private static SWTBotView view;

    private static SWTBotEditor botEditor;

    private static SWTBotGefEditor gefEditor;

    private static String JOBNAME = "tMsgBoxTesting";

    @Test
    public void createJob() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        tree.select("Job Designs").contextMenu("Create job").click();

        gefBot.waitUntil(Conditions.shellIsActive("New job"));
        shell = gefBot.shell("New job");
        shell.activate();

        gefBot.textWithLabel("Name").setText(JOBNAME);

        gefBot.button("Finish").click();
    }

    @Test
    public void useComponentInJob() {
        gefBot.viewByTitle("Palette").close();
        botEditor = gefBot.activeEditor();
        gefEditor = gefBot.gefEditor(botEditor.getTitle());

        gefEditor.activateTool("tMsgBox");
        gefEditor.click(100, 100);

        gefBot.viewByTitle("Component").setFocus();
        gefBot.text("\"Hello world!\"").setText("\"abcdefg\"");

        gefEditor.save();
    }

    @Test
    public void runTheJob() {
        gefBot.viewByTitle("Run (Job " + JOBNAME + ")").setFocus();
        gefBot.button(" Run").click();

        gefBot.waitUntil(Conditions.shellIsActive("Launching " + JOBNAME + " 0.1"));
        gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Launching " + JOBNAME + " 0.1")));
    }

}
