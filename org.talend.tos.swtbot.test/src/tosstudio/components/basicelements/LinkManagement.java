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

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class LinkManagement extends TalendSwtBotForTos {

    public static SWTBotTree tree;

    public static SWTBotShell shell;

    public static SWTBotView view;

    private static SWTBotEditor botEditor;

    private static SWTBotGefEditor gefEditor;

    public static String JOBNAME = "linkManagement";

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

        gefEditor.activateTool("tRowGenerator");
        gefEditor.click(100, 100);
        gefEditor.activateTool("tLogRow");
        gefEditor.click(300, 100);

        gefEditor.doubleClick(110, 110);
        shell = gefBot.shell("JasperETL Powered by Talend - tRowGenerator - tRowGenerator_1");
        shell.activate();
        gefBot.buttonWithTooltip("Add").click();
        gefBot.buttonWithTooltip("Add").click();
        gefBot.button("OK").click();

        gefEditor.click(110, 110);
        gefEditor.clickContextMenu("Row").clickContextMenu("Main");
        gefEditor.click(310, 110);

        gefBot.viewByTitle("Component").show();
        gefEditor.click(310, 110);
        gefBot.buttonWithLabel("Schema").click(); // label and button do not correspond
        shell = gefBot.shell("Schema oftLogRow_1");
        shell.activate();
        gefBot.waitUntil(Conditions.shellIsActive("Schema oftLogRow_1"));
        gefBot.button("OK").click();

        gefBot.toolbarButtonWithTooltip("Save (Ctrl+S)").click();
    }

}
