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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class CopyComponentsBetweenJob extends TalendSwtBotForTos {

    private static SWTBotView view;

    private static SWTBotShell shell;

    private static SWTBotTree tree;

    private static SWTBotGefEditor gefEditor;

    private static String JOBNAME1 = "CopyComponentsBetweenJob1";

    private static String JOBNAME2 = "CopyComponentsBetweenJob2";

    @Test
    public void createJob() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        /* Create job1 */
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        tree.select("Job Designs").contextMenu("Create job").click();

        gefBot.waitUntil(Conditions.shellIsActive("New job"));
        shell = gefBot.shell("New job");
        shell.activate();

        gefBot.textWithLabel("Name").setText(JOBNAME1);

        gefBot.button("Finish").click();
        /* Create job2 */
        tree.select("Job Designs").contextMenu("Create job").click();

        gefBot.waitUntil(Conditions.shellIsActive("New job"));
        shell = gefBot.shell("New job");
        shell.activate();

        gefBot.textWithLabel("Name").setText(JOBNAME2);

        gefBot.button("Finish").click();
    }

    @Test
    public void useComponentInJob() {
        gefBot.viewByTitle("Palette").close();
        gefEditor = gefBot.gefEditor("Job CopyComponentsBetweenJob1 0.1");
        gefEditor.show();

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
    }

    @Test
    public void copyPasteInOwnJob() {
        gefEditor.click(200, 100);
        gefEditor.clickContextMenu("Copy");
        gefEditor.click(100, 300);
        gefEditor.clickContextMenu("Paste");

        gefEditor.save();
    }

    @Test
    public void copyPasteInAnotherJob() {
        gefEditor = gefBot.gefEditor("Job CopyComponentsBetweenJob2 0.1");
        gefEditor.setFocus();

        gefEditor.click(100, 100);
        gefEditor.clickContextMenu("Paste");

        gefEditor.save();
    }

}
