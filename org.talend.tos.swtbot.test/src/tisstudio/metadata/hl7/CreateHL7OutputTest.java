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
package tisstudio.metadata.hl7;

import java.io.IOException;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
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
public class CreateHL7OutputTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private static String HL7NAME = "hl7_2"; //$NON-NLS-1$

    private static String SAMPLE_RELATIVE_FILEPATH = "HL7.txt"; //$NON-NLS-1$

    private static String SAMPLE_RELATIVE_OUTPUT_FILEPATH = "outputHL7.txt"; //$NON-NLS-1$

    @Before
    public void InitialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        view.setFocus();
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
    }

    @Test
    public void createHL7Output() throws IOException, URISyntaxException {
        tree.setFocus();

        tree.expandNode("Metadata").getNode("HL7").contextMenu("Create HL7").click();
        gefBot.waitUntil(Conditions.shellIsActive("New HL7 File"));
        gefBot.shell("New HL7 File").activate();

        /* step 1 of 5 */
        gefBot.textWithLabel("Name").setText(HL7NAME);
        gefBot.button("Next >").click();

        /* step 2 of 5 */
        gefBot.radio("HL7Output").click();
        gefBot.button("Next >").click();

        /* step 3 of 5 */
        gefBot.radio("Create from a file").click();
        gefBot.textWithLabel("File path:").setText(
                Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_FILEPATH).getAbsolutePath());
        gefBot.textWithLabel("Output File Path").setText(
                Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_OUTPUT_FILEPATH).getAbsolutePath());
        gefBot.button("Next >").click();

        /* step 4 of 5 */
        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {

                return gefBot.button("Next >").isEnabled();
            }

            public String getFailureMessage() {
                gefBot.shell("New HL7 File").close();
                return "next button was never enabled";
            }
        });
        gefBot.button("Next >").click();

        /* step 5 of 5 */
        gefBot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {

                return gefBot.button("Finish").isEnabled();
            }

            public String getFailureMessage() {
                gefBot.shell("New HL7 File").close();
                return "finish button was never enabled";
            }
        });
        gefBot.button("Finish").click();

        SWTBotTreeItem newHl7Item = tree.expandNode("Metadata", "HL7").select(HL7NAME + " 0.1");
        Assert.assertNotNull(newHl7Item);
    }

    @After
    public void removePreviouslyCreateItems() {
        tree.expandNode("Metadata", "HL7").getNode(HL7NAME + " 0.1").contextMenu("Delete").click();

        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
