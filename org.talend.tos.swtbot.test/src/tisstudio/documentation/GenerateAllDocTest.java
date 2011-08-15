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
package tisstudio.documentation;

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
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
public class GenerateAllDocTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem jobNode;

    private SWTBotTreeItem jobletNode;

    private SWTBotTreeItem docNode;

    private static final String JOBNAME = "job1"; //$NON-NLS-1$

    private static final String JOBLETNAME = "joblet1"; //$NON-NLS-1$

    @Before
    public void initialisePrivateFields() {
        view = Utilities.getRepositoryView();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        jobNode = Utilities.getTalendItemNode(Utilities.TalendItemType.JOB_DESIGNS);
        jobletNode = Utilities.getTalendItemNode(Utilities.TalendItemType.JOBLET_DESIGNS);
        Utilities.createJob(JOBNAME, jobNode);
        Utilities.createJoblet(JOBLETNAME, jobletNode);
    }

    @Test
    public void generateAllDoc() {
        docNode = Utilities.getTalendItemNode(Utilities.TalendItemType.DOCUMENTATION);
        docNode.getNode("generated").contextMenu("Generate all projects documentation").click();
        gefBot.waitUntil(Conditions.shellIsActive("Progress Information"));
        gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Progress Information")), 30000);
        gefBot.waitUntil(Conditions.shellIsActive("Talend Open Studio"));
        gefBot.button("OK").click();

        SWTBotTreeItem newDocItemForJob = null;
        SWTBotTreeItem newDocItemForJoblet = null;
        try {
            newDocItemForJob = docNode.expandNode("generated", "jobs").select(JOBNAME + " 0.1");
            newDocItemForJoblet = docNode.expandNode("generated", "joblets").select(JOBLETNAME + " 0.1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertNotNull("documentation item for job is not created", newDocItemForJob);
            Assert.assertNotNull("documentation item for joblet is not created", newDocItemForJoblet);
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.cTabItem("Job " + JOBNAME + " 0.1").close();
        gefBot.cTabItem("Joblet " + JOBLETNAME + " 0.1").close();
        Utilities.delete(jobNode, JOBNAME, "0.1", null);
        Utilities.delete(jobletNode, JOBLETNAME, "0.1", null);
        Utilities.emptyRecycleBin();
    }
}
