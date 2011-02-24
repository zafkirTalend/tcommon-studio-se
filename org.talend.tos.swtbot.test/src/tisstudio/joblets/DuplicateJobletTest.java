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
package tisstudio.joblets;

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
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
public class DuplicateJobletTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotShell shell;

    private SWTBotView view;

    private static final String JOBLETNAME = "joblet1"; //$NON-NLS-1$

    private static final String NEW_JOBLETNAME = "duplicate_joblet1"; //$NON-NLS-1$

    @Before
    public void createJoblet() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        Utilities.createJoblet(JOBLETNAME, tree, gefBot, shell);
    }

    @Test
    public void duplicateJoblet() {
        tree.expandNode("Joblet Designs").getNode(JOBLETNAME + " 0.1").contextMenu("Duplicate").click();
        gefBot.shell("Please input new name ").activate();
        gefBot.text("Copy_of_" + JOBLETNAME).setText(NEW_JOBLETNAME);
        gefBot.button("OK").click();

        SWTBotTreeItem newJobletItem = null;
        try {
            newJobletItem = tree.expandNode("Joblet Designs").select(NEW_JOBLETNAME + " 0.1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertNotNull("joblet item is not duplicated", newJobletItem);
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.cTabItem("Joblet " + JOBLETNAME + " 0.1").close();
        tree.expandNode("Joblet Designs").getNode(JOBLETNAME + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Joblet Designs").getNode(NEW_JOBLETNAME + " 0.1").contextMenu("Delete").click();

        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
