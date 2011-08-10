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

import junit.framework.Assert;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CopyComponentsBetweenJobTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotShell shell;

    private SWTBotTree tree;

    private SWTBotGefEditor gefEditor;

    private SWTBotTreeItem treeNode;

    private static final String JOBNAME1 = "CopyComponentsBetweenJob1"; //$NON-NLS-1$

    private static final String JOBNAME2 = "CopyComponentsBetweenJob2"; //$NON-NLS-1$

    @Before
    public void createJob() {
        view = Utilities.getRepositoryView(gefBot);
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.JOB_DESIGNS);
        /* Create job1 */
        Utilities.createJob(JOBNAME1, treeNode, gefBot);
        /* Create job2 */
        Utilities.createJob(JOBNAME2, treeNode, gefBot);
        /* Use components in job1 */
        gefEditor = gefBot.gefEditor("Job " + JOBNAME1 + " 0.1");

        Utilities.dndPaletteToolOntoJob(gefBot, gefEditor, "tRowGenerator", new Point(100, 100));
        Utilities.dndPaletteToolOntoJob(gefBot, gefEditor, "tLogRow", new Point(300, 100));

        SWTBotGefEditPart rowGen = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        Assert.assertNotNull("can not get component 'tRowGenerator'", rowGen);
        rowGen.doubleClick();
        shell = gefBot.shell("Talend ESB Enterprise Edition - tRowGenerator - tRowGenerator_1");
        shell.activate();
        gefBot.buttonWithTooltip("Add").click();
        gefBot.buttonWithTooltip("Add").click();
        gefBot.button("OK").click();

        gefEditor.select(rowGen);
        gefEditor.clickContextMenu("Row").clickContextMenu("Main");
        SWTBotGefEditPart logRow = getTalendComponentPart(gefEditor, "tLogRow_1");
        Assert.assertNotNull("can not get component 'tLogRow'", logRow);
        gefEditor.click(logRow);
        SWTBotGefEditPart rowMain = gefEditor.getEditPart("row1 (Main)");
        Assert.assertNotNull("can not draw row line", rowMain);
    }

    @Test
    public void copyAndPasteComponents() {
        /* Copy and paste in own job */
        SWTBotGefEditPart rowGen1 = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        SWTBotGefEditPart logRow1 = getTalendComponentPart(gefEditor, "tLogRow_1");
        gefEditor.select(rowGen1, logRow1);
        gefEditor.clickContextMenu("Copy");
        gefEditor.click(100, 300);
        gefEditor.clickContextMenu("Paste");

        SWTBotGefEditPart rowGen2 = getTalendComponentPart(gefEditor, "tRowGenerator_2");
        Assert.assertNotNull("no copy the component 'tRowGenerator' in own job", rowGen2);
        SWTBotGefEditPart logRow2 = getTalendComponentPart(gefEditor, "tLogRow_2");
        Assert.assertNotNull("no copy the component 'tLogRow in own job", logRow2);
        SWTBotGefEditPart rowMain2 = gefEditor.getEditPart("row2 (Main)");
        Assert.assertNotNull("no copy the row line in own job", rowMain2);

        /* Copy and paste in another job */
        gefEditor = gefBot.gefEditor("Job " + JOBNAME2 + " 0.1");
        gefEditor.setFocus();
        gefEditor.click(100, 100);
        gefEditor.clickContextMenu("Paste");

        SWTBotGefEditPart rowGen3 = getTalendComponentPart(gefEditor, "tRowGenerator_1");
        Assert.assertNotNull("no copy the component 'tRowGenerator' in another job", rowGen3);
        SWTBotGefEditPart logRow3 = getTalendComponentPart(gefEditor, "tLogRow_1");
        Assert.assertNotNull("no copy the component 'tLogRow in another job", logRow3);
        SWTBotGefEditPart rowMain3 = gefEditor.getEditPart("row1 (Main)");
        Assert.assertNotNull("no copy the row line in another job", rowMain3);
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.gefEditor("Job " + JOBNAME1 + " 0.1").saveAndClose();
        gefBot.gefEditor("Job " + JOBNAME2 + " 0.1").saveAndClose();
        Utilities.cleanUpRepository(treeNode);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
