// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;
import org.talend.swtbot.helpers.JobHelper;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ExternalComponentsTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotShell shell;

    private SWTBotTree tree;

    private SWTBotGefEditor gefEditor;

    private SWTBotTreeItem treeNode;

    private static final String JOBNAME = "ExternalComponentsTesting"; //$NON-NLS-1$

    @Before
    public void createJob() {
        view = Utilities.getRepositoryView(gefBot);
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.JOB_DESIGNS);
        Utilities.createJob(JOBNAME, treeNode, gefBot);
    }

    @Test
    public void useComponentInJob() throws IOException, URISyntaxException {
        gefEditor = gefBot.gefEditor("Job " + JOBNAME + " 0.1");

        Utilities.dndPaletteToolOntoJob(gefBot, gefEditor, "tRowGenerator", new Point(100, 100));
        Utilities.dndPaletteToolOntoJob(gefBot, gefEditor, "tMap", new Point(300, 100));
        Utilities.dndPaletteToolOntoJob(gefBot, gefEditor, "tFileOutputDelimited", new Point(500, 100));

        /* Edit tRowGenerator */
        SWTBotGefEditPart rowGen = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        Assert.assertNotNull("can not get component 'tRowGenerator'", rowGen);
        rowGen.doubleClick();
        shell = gefBot.shell("Talend ESB Enterprise Edition - tRowGenerator - tRowGenerator_1");
        shell.activate();
        /* Add column "id" */
        gefBot.buttonWithTooltip("Add").click();
        gefBot.table(0).click(0, 2);
        gefBot.text("newColumn").setText("id");
        gefBot.table(0).click(0, 4);
        gefBot.ccomboBox("String").setSelection("int | Integer");
        gefBot.table(0).click(0, 10);
        gefBot.ccomboBox("COUNT").setSelection("sequence");
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
        Assert.assertNotNull("can not get component 'tMap'", map);
        gefEditor.click(map);
        SWTBotGefEditPart rowMain = gefEditor.getEditPart("row1 (Main)");
        Assert.assertNotNull("can not draw row line", rowMain);

        /* Edit tMap */
        map.doubleClick();
        shell = gefBot.shell("Talend ESB Enterprise Edition - tMap - tMap_1");
        shell.activate();
        gefBot.waitUntil(Conditions.shellIsActive("Talend ESB Enterprise Edition - tMap - tMap_1"));

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
        Assert.assertNotNull("can not get component 'tFileOutputDelimited'", outputDeliFile);
        gefEditor.click(outputDeliFile);
        SWTBotGefEditPart outMain = gefEditor.getEditPart("out1 (Main)");
        Assert.assertNotNull("can not draw row line", outMain);

        gefEditor.save();

        /* Run the job */
        JobHelper.runJob(gefEditor);
    }

    @After
    public void removePreviousCreateItems() {
        gefEditor.saveAndClose();
        Utilities.delete(tree, treeNode, JOBNAME, "0.1", null);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
