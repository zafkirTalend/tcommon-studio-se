package tosstudio.components.basicelements;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
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

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TMsgBoxTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotView view;

    private SWTBotTreeItem treeNode;

    private SWTBotGefEditor gefEditor;

    private static final String JOBNAME = "tMsgBoxTesting"; //$NON-NLS-1$

    @Before
    public void createJob() {
        view = Utilities.getRepositoryView();
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(Utilities.TalendItemType.JOB_DESIGNS);
        Utilities.createJob(JOBNAME, treeNode);
    }

    @Test
    public void useComponentInJob() {
        gefEditor = gefBot.gefEditor("Job " + JOBNAME + " 0.1");

        Utilities.dndPaletteToolOntoJob(gefBot, gefEditor, "tMsgBox", new Point(100, 100));
        SWTBotGefEditPart msgBox = getTalendComponentPart(gefEditor, "tMsgBox_1");
        Assert.assertNotNull("can not get component 'tMsgBox'", msgBox);

        // gefBot.viewByTitle("Component").setFocus();
        // gefBot.text("\"Hello world!\"").setText("\"abcdefg\"");

        gefEditor.save();

        /* Run the job */
        JobHelper.runJob(gefEditor);
    }

    @After
    public void removePreviousCreateItems() {
        gefEditor.saveAndClose();
        Utilities.delete(treeNode, JOBNAME, "0.1", null);
        Utilities.emptyRecycleBin();
    }
}
