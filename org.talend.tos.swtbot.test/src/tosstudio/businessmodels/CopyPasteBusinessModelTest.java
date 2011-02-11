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
package tosstudio.businessmodels;

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
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

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CopyPasteBusinessModelTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotShell shell;

    private SWTBotView view;

    private static String BUSINESSMODELNAME = "businessModel1"; //$NON-NLS-1$

    @Before
    public void InitialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();
        tree.select("Business Models").contextMenu("Create Business Model").click();

        gefBot.waitUntil(Conditions.shellIsActive("New Business Model"));
        shell = gefBot.shell("New Business Model");
        shell.activate();

        gefBot.textWithLabel("Name").setText(BUSINESSMODELNAME);

        gefBot.button("Finish").click();
    }

    @Test
    public void copyAndPasteBusinessModel() {
        tree.expandNode("Business Models").getNode(BUSINESSMODELNAME + " 0.1").contextMenu("Copy").click();
        tree.select("Business Models").contextMenu("Paste").click();

        SWTBotTreeItem newBusinessModelItem = tree.expandNode("Business Models").select("Copy_of_" + BUSINESSMODELNAME + " 0.1");
        Assert.assertNotNull(newBusinessModelItem);
    }

    @After
    public void removePreviouslyCreateItems() {
        gefBot.cTabItem("Model " + BUSINESSMODELNAME).close();
        tree.expandNode("Business Models").getNode(BUSINESSMODELNAME + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Business Models").getNode("Copy_of_" + BUSINESSMODELNAME + " 0.1").contextMenu("Delete").click();

        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
