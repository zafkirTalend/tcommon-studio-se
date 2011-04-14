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
package tisstudio.jobscript;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
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
public class DuplicateJobScriptTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotTreeItem treeNode;

    private static final String JOBSCRIPT_NAME = "jobscript1"; //$NON-NLS-1$

    private static final String NEW_JOBSCRIPT_NAME = "dup_jobscript1"; //$NON-NLS-1$

    @Before
    public void initialisePrivateFields() {
        view = Utilities.getRepositoryView(gefBot);
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        treeNode = Utilities.getTalendItemNode(tree, Utilities.TalendItemType.JOBSCRIPTS);
        Utilities.createJobScript(JOBSCRIPT_NAME, treeNode, gefBot);
    }

    @Test
    public void duplicateJobScript() {
        Utilities.duplicate(gefBot, treeNode, JOBSCRIPT_NAME, "0.1", NEW_JOBSCRIPT_NAME);
    }

    @After
    public void removePreviousCreateItems() {
        gefBot.cTabItem(JOBSCRIPT_NAME + "_0.1.jobscript").close();
        Utilities.delete(tree, treeNode, JOBSCRIPT_NAME, "0.1", null);
        Utilities.delete(tree, treeNode, NEW_JOBSCRIPT_NAME, "0.1", null);
        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
