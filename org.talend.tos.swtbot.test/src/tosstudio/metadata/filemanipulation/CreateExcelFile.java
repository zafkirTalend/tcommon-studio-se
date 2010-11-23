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
package tosstudio.metadata.filemanipulation;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class CreateExcelFile extends TalendSwtBotForTos {

    private static SWTBotTree tree;

    private static SWTBotView view;

    private static String FILENAME = "test_excel"; //$NON-NLS-1$

    private static String SAMPLE_RELATIVE_FILEPATH = "test.xls"; //$NON-NLS-1$

    @Test
    public void createExcelFile() throws IOException, URISyntaxException {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();

        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        tree.setFocus();

        tree.expandNode("Metadata").getNode("File Excel").contextMenu("Create file Excel").click();
        gefBot.waitUntil(Conditions.shellIsActive("New Excel File"));
        gefBot.shell("New Excel File").activate();

        gefBot.textWithLabel("Name").setText(FILENAME);
        gefBot.button("Next >").click();
        gefBot.textWithLabel("File").setText(
                Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_FILEPATH).getAbsolutePath());
        gefBot.treeWithLabel("Set sheets parameters").getTreeItem("All sheets/DSelect sheet").check();
        gefBot.button("Next >").click();
        while (!"Refresh Preview".equals(gefBot.button(0).getText())) {
            // wait for previewing
        }
        gefBot.button("Next >").click();
        gefBot.button("Finish").click();
        SWTBotTreeItem newXlsItem = tree.getTreeItem(FILENAME);
        Assert.assertNotNull(newXlsItem);
    }
}
