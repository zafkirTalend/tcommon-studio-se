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

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ExternalComponentsTesting extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotShell shell;

    private SWTBotTree tree;

    private SWTBotGefEditor gefEditor;

    private static String JOBNAME = "ExternalComponentsTesting"; //$NON-NLS-1$

    @Before
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
    public void useComponentInJob() throws IOException, URISyntaxException {
        gefEditor = gefBot.gefEditor("Job " + JOBNAME + " 0.1");
        gefEditor.show();

        gefEditor.activateTool("tRowGenerator").click(100, 100);
        gefEditor.activateTool("tMap").click(300, 100);
        gefEditor.activateTool("tFileOutputDelimited").click(500, 100);

        /* Edit tRowGenerator */
        SWTBotGefEditPart rowGen = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        Assert.assertNotNull(rowGen);
        rowGen.doubleClick();
        shell = gefBot.shell("JasperETL Powered by Talend - tRowGenerator - tRowGenerator_1");
        shell.activate();
        /* Add column "id" */
        gefBot.buttonWithTooltip("Add").click();
        gefBot.table(0).click(0, 2);
        gefBot.text("newColumn").setText("id");
        gefBot.table(0).click(0, 4);
        gefBot.ccomboBox("String").setSelection("int | Integer");
        gefBot.table(0).click(0, 10);
        gefBot.ccomboBox("XTD").setSelection("sequence");
        gefBot.table(0).select(0);
        /* Add column "name" */
        gefBot.buttonWithTooltip("Add").click();
        gefBot.table(0).click(1, 2);
        gefBot.text("newColumn").setText("name");
        gefBot.table(0).click(1, 6);
        gefBot.text().setText("6");
        gefBot.table(0).select(1);

        gefBot.button("OK").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));

        gefEditor.select(rowGen);
        gefEditor.clickContextMenu("Row").clickContextMenu("Main");
        SWTBotGefEditPart map = getTalendComponentPart(gefEditor, "tMap_1");
        Assert.assertNotNull(map);
        gefEditor.click(map);
        SWTBotGefEditPart rowMain = gefEditor.getEditPart("row1 (Main)");
        Assert.assertNotNull(rowMain);

        /* Edit tMap */
        map.doubleClick();
        shell = gefBot.shell("JasperETL Powered by Talend - tMap - tMap_1");
        shell.activate();
        gefBot.waitUntil(Conditions.shellIsActive("JasperETL Powered by Talend - tMap - tMap_1"));

        gefBot.toolbarButtonWithTooltip("Add output table").click();
        gefBot.shell("Add a output").activate();
        gefBot.button("OK").click();

        gefBot.cTabItem("Schema editor").setFocus();
        gefBot.tableWithLabel("row1").select(0, 1);
        gefBot.buttonWithTooltip("Copy selected items", 0).click();
        gefBot.buttonWithTooltip("Paste", 1).click();

        gefBot.tableWithLabel("out1").click(0, 0);
        gefBot.text().setText("row1.id");
        gefBot.tableWithLabel("out1").click(1, 0);
        gefBot.text().setText("row1.name");
        gefBot.button("Apply").click();
        gefBot.button("Ok").click();
        gefBot.waitUntil(Conditions.shellCloses(shell));

        /* Connect tMap and tFileOutputDelimited */
        gefEditor.click(map);
        gefEditor.clickContextMenu("Row").clickContextMenu("out1");
        SWTBotGefEditPart outputDeliFile = getTalendComponentPart(gefEditor, "tFileOutputDelimited_1");
        Assert.assertNotNull(outputDeliFile);
        gefEditor.click(outputDeliFile);
        SWTBotGefEditPart outMain = gefEditor.getEditPart("out1 (Main)");
        Assert.assertNotNull(outMain);

        gefEditor.save();

        /* Run the job */
        gefBot.viewByTitle("Run (Job " + JOBNAME + ")").setFocus();
        gefBot.button(" Run").click();

        gefBot.waitUntil(Conditions.shellIsActive("Launching " + JOBNAME + " 0.1"));
        gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Launching " + JOBNAME + " 0.1")));
    }

    @After
    public void removePreviousCreateItems() {
        gefBot.cTabItem("Job " + JOBNAME + " 0.1").close();
        tree.expandNode("Job Designs").getNode(JOBNAME + " 0.1").contextMenu("Delete").click();
        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
