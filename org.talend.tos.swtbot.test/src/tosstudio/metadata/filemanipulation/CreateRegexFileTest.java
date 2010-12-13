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
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
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
public class CreateRegexFileTest extends TalendSwtBotForTos {

    private SWTBotTree tree;

    private SWTBotView view;

    private static String FILENAME = "test_regex"; //$NON-NLS-1$

    private static String SAMPLE_RELATIVE_FILEPATH = "test.txt"; //$NON-NLS-1$

    @Before
    public void InitialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
    }

    @Test
    public void createRegexFile() throws IOException, URISyntaxException {
        tree.setFocus();

        tree.expandNode("Metadata").getNode("File regex").contextMenu("Create file regex").click();
        gefBot.waitUntil(Conditions.shellIsActive("New RegEx File"));
        gefBot.shell("New RegEx File").activate();

        gefBot.textWithLabel("Name").setText(FILENAME);
        gefBot.button("Next >").click();
        gefBot.textWithLabel("File").setText(
                Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_FILEPATH).getAbsolutePath());
        gefBot.button("Next >").click();
        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                return gefBot.button("Next >").isEnabled();
            }

            public String getFailureMessage() {
                gefBot.shell("New RegEx File").close();
                return "next button was never enabled";
            }
        }, 60000);
        gefBot.button("Next >").click();
        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {

                return gefBot.button("Finish").isEnabled();
            }

            public String getFailureMessage() {
                gefBot.shell("New RegEx File").close();
                return "finish button was never enabled";
            }
        });
        gefBot.button("Finish").click();

        SWTBotTreeItem newRegexItem = tree.expandNode("Metadata").expandNode("File regex").getNode(FILENAME + " 0.1");
        Assert.assertNotNull(newRegexItem);
    }

    @After
    public void removePreviouslyCreateItems() {
        tree.expandNode("Metadata").expandNode("File regex").getNode(FILENAME + " 0.1").contextMenu("Delete").click();
        tree.getTreeItem("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
