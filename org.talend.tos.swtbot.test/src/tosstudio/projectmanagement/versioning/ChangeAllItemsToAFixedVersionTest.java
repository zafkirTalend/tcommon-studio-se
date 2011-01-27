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
package tosstudio.projectmanagement.versioning;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
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

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ChangeAllItemsToAFixedVersionTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private static String SAMPLE_RELATIVE_FILEPATH = "items.zip"; //$NON-NLS-1$

    private String[] treeNodes = { "Business Models", "Job Designs", "Joblet Designs", "Contexts", "Code", "SQL Templates",
            "Metadata", "Documentation" };

    private String[] treeItems = { "businessTest", "jobTest", "jobletTest", "contextTest", "routineTest", "jobscriptsTest",
            "mysqlTest", "sapTest", "delimitedFileTest", "positionalFileTest", "regexFileTest", "xmlFileTest", "excelFileTest",
            "ldifTest", "ldapTest", "salesforceTest", "genericSchemaTest", "copybookTest", "HL7Test", "webserviceTest" };

    private String[] codeNodes = { "Routines", "Job Scripts" };

    private String[] documentationNodes = { "generated", "jobs", "joblets" };

    private String[] metadataNodes = { "Db Connections", "SAP Connections", "File delimited", "File positional", "File regex",
            "File xml", "File Excel", "File ldif", "LDAP", "Salesforce", "Generic schemas", "Copybook", "HL7", "Web Service" };

    @Before
    public void initialisePrivateFields() throws IOException, URISyntaxException {
        view = gefBot.viewByTitle("Repository");
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
        gefBot.toolbarButtonWithTooltip("Import Items").click();

        gefBot.shell("Import items").activate();
        gefBot.radio("Select archive file:").click();
        gefBot.text(1).setText(Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_FILEPATH).getAbsolutePath());
        gefBot.tree().setFocus();
        gefBot.button("Select All").click();
        gefBot.button("Finish").click();
        gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Progress Information")));

        // Generate all documentation
        tree.expandNode("Documentation").getNode("generated").contextMenu("Generate all projects documentation").click();
        gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Progress Information")), 30000);
        gefBot.waitUntil(Conditions.shellIsActive("Talend Open Studio"), 30000);
        gefBot.shell("Talend Open Studio").activate();
        gefBot.button("OK").click();
    }

    @Test
    public void changeAllItemsToAFixedVersion() {
        gefBot.toolbarButtonWithTooltip("Project settings").click();
        gefBot.shell("Project Settings").activate();
        gefBot.tree().expandNode("General").select("Version Management").click();
        for (int i = 0; i < treeNodes.length; i++) {
            gefBot.tree(1).getTreeItem(treeNodes[i]).check();
        }
        gefBot.button("m").click();
        gefBot.button("OK").click();
        gefBot.shell("Confirm").activate();
        gefBot.button("OK").click();

        for (int i = 0; i < treeNodes.length; i++) {
            if (i >= 0 && i <= 3) {
                SWTBotTreeItem newTreeItem = tree.expandNode(treeNodes[i]).select(treeItems[i] + " 0.2");
                Assert.assertNotNull(treeItems[i] + " not exist", newTreeItem);
            } else if (i == 4) {
                for (int k1 = 0; k1 < codeNodes.length; k1++) {
                    SWTBotTreeItem newCodeItem = tree.expandNode(treeNodes[i], codeNodes[k1]).select(treeItems[i + k1] + " 0.2");
                    Assert.assertNotNull(treeItems[i] + " not exist", newCodeItem);
                }
            } else if (i == 6) {
                for (int k2 = 0; k2 < metadataNodes.length; k2++) {
                    SWTBotTreeItem newMetadataItem = tree.expandNode(treeNodes[i], metadataNodes[k2]).select(
                            treeItems[i + k2] + " 0.2");
                    Assert.assertNotNull(treeItems[i] + " not exist", newMetadataItem);
                }
            } else if (i == 7) {
                for (int k3 = 0; k3 < documentationNodes.length - 1; k3++) {
                    SWTBotTreeItem newDocItem = tree.expandNode(treeNodes[i], documentationNodes[0], documentationNodes[1 + k3])
                            .select(treeItems[k3 + 1] + " 0.2");
                    Assert.assertNotNull(treeItems[i] + " not exist", newDocItem);
                }
            }
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        for (int i = 0; i < treeNodes.length; i++) {
            if (i >= 0 && i <= 3) {
                tree.expandNode(treeNodes[i]).getNode(treeItems[i] + " 0.2").contextMenu("Delete").click();
            } else if (i == 4) {
                for (int k1 = 0; k1 < codeNodes.length; k1++) {
                    tree.expandNode(treeNodes[i], codeNodes[k1]).getNode(treeItems[i + k1] + " 0.2").contextMenu("Delete")
                            .click();
                }
            } else if (i == 6) {
                for (int k2 = 0; k2 < metadataNodes.length; k2++) {
                    tree.expandNode(treeNodes[i], metadataNodes[k2]).getNode(treeItems[i + k2] + " 0.2").contextMenu("Delete")
                            .click();
                }
            }
        }
        tree.getTreeItem("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
