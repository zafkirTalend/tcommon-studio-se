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
package tosstudio.metadata.copybook;

import java.io.IOException;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
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
public class CreateCopybookTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotView view;

    private static String COPYBOOKNAME = "copybook1"; //$NON-NLS-1$

    private static String SAMPLE_RELATIVE_FILEPATH = "cobocurs.copybook"; //$NON-NLS-1$

    private static String SAMPLE_RELATIVE_DATAFILE = "datafil.dat"; //$NON-NLS-1$

    @Before
    public void InitialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
    }

    @Test
    public void createCopybook() throws IOException, URISyntaxException {
        tree.setFocus();

        tree.expandNode("Metadata").getNode("Copybook").contextMenu("Create EBCDIC").click();
        gefBot.waitUntil(Conditions.shellIsActive("EBCDIC Connection"));
        gefBot.shell("EBCDIC Connection").activate();

        /* step 1 of 3 */
        gefBot.textWithLabel("Name").setText(COPYBOOKNAME);
        gefBot.button("Next >").click();

        /* step 2 of 3 */
        gefBot.textWithLabel("File").setText(
                Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_FILEPATH).getAbsolutePath());
        gefBot.textWithLabel("Data file").setText(
                Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_DATAFILE).getAbsolutePath());
        gefBot.button("Generate").click();
        gefBot.button("Next >").click();

        /* step 3 of 3 */
        gefBot.button("Finish").click();

        SWTBotTreeItem newCopybookItem = tree.expandNode("Metadata", "Copybook").select(COPYBOOKNAME + " 0.1");
        Assert.assertNotNull(newCopybookItem);
    }

    @After
    public void removePreviouslyCreateItems() {
        tree.expandNode("Metadata", "Copybook").getNode(COPYBOOKNAME + " 0.1").contextMenu("Delete").click();
        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
