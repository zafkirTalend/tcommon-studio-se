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
package tosstudio.projectmanagement.statusmanagement;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
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
public class ChangeAllTechnicalItemsTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private SWTBotShell shell;

    private static final String SAMPLE_RELATIVE_FILEPATH = "items.zip"; //$NON-NLS-1$

    private String[] treeNodes = { "Business Models", "Job Designs", "Joblet Designs", "Contexts", "Code", "SQL Templates",
            "Metadata", "Documentation" };

    private String[] treeItems = { "businessTest", "jobTest", "jobletTest", "contextTest", "routineTest", "jobscriptsTest",
            "mysqlTest", "sapTest", "delimitedFileTest", "positionalFileTest", "regexFileTest", "xmlFileTest", "excelFileTest",
            "ldifTest", "ldapTest", "salesforceTest", "genericSchemaTest", "copybookTest", "HL7Test", "webserviceTest" };

    private String[] codeNodes = { "Routines", "Job Scripts" };

    private String[] metadataNodes = { "Db Connections", "SAP Connections", "File delimited", "File positional", "File regex",
            "File xml", "File Excel", "File ldif", "LDAP", "Salesforce", "Generic schemas", "Copybook", "HL7", "Web Service" };

    private String[] metadataContextMenu = { "connection", "SAP connection", "file delimited", "file positional", "file regex",
            "file xml", "file Excel", "file ldif", "LDAP schema", "Salesforce schema", "generic schema", "EBCDIC", "HL7",
            "WSDL schema" };

    private String[] metadataShellTitle = { "Database Connection", "SAP Connection", "Edit an existing Delimited File",
            "Edit an existing Positional File", "Edit an existing RegEx File", "Edit an existing Xml File",
            "Edit an existing Excel File", "Edit an existing Ldif File", "Update LDAP schema", "Edit an exist Salesforce",
            "Update generic schema", "EBCDIC Connection", "New HL7 File", "Update WSDL schema" };

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
    }

    @Test
    public void changeAllTechnicalItems() {
        gefBot.toolbarButtonWithTooltip("Project settings").click();
        gefBot.shell("Project Settings").activate();
        gefBot.tree().expandNode("General").select("Status Management").click();
        for (int i = 0; i < treeNodes.length; i++) {
            gefBot.tree(1).getTreeItem(treeNodes[i]).check();
        }
        gefBot.comboBox().setSelection("testing");
        gefBot.button("OK").click();
        gefBot.shell("Confirm").activate();
        gefBot.button("OK").click();

        for (int i = 0; i < treeNodes.length; i++) {
            if (i == 0) {
                tree.expandNode(treeNodes[i]).getNode(treeItems[i] + " 0.1").contextMenu("Edit properties").click();
                shell = gefBot.shell("Edit properties");
                shell.activate();
                Assert.assertEquals(treeNodes[i] + " status", "", gefBot.ccomboBoxWithLabel("Status").getText());
                shell.close();
            } else if (i == 1) {
                tree.expandNode(treeNodes[i]).getNode(treeItems[i] + " 0.1").contextMenu("Edit properties").click();
                shell = gefBot.shell("Edit properties");
                shell.activate();
                Assert.assertEquals(treeNodes[i] + " status", "testing", gefBot.ccomboBoxWithLabel("Status").getText());
                shell.close();
            } else if (i == 2) {
                tree.expandNode(treeNodes[i]).getNode(treeItems[i] + " 0.1").contextMenu("Edit Properties").click();
                shell = gefBot.shell("!!!PropertiesWizard.EditPropertiesPageTitle!!!");
                shell.activate();
                Assert.assertEquals(treeNodes[i] + " status", "testing", gefBot.ccomboBoxWithLabel("Status").getText());
                shell.close();
            } else if (i == 3) {
                tree.expandNode(treeNodes[i]).getNode(treeItems[i] + " 0.1").contextMenu("Edit context group").click();
                shell = gefBot.shell("Create / Edit a context group");
                shell.activate();
                Assert.assertEquals(treeNodes[i] + " status", "testing", gefBot.ccomboBoxWithLabel("Status").getText());
                shell.close();
            } else if (i == 4) {
                tree.expandNode(treeNodes[i], codeNodes[0]).getNode(treeItems[i] + " 0.1").contextMenu("Edit properties").click();
                shell = gefBot.shell("Edit properties");
                shell.activate();
                Assert.assertEquals(treeNodes[i] + " status", "testing", gefBot.ccomboBoxWithLabel("Status").getText());
                shell.close();
            } else if (i == 6) {
                for (int k2 = 0; k2 < metadataNodes.length; k2++) {
                    // System.out.println("k2=" + k2 + "---" + treeNodes[i] + "-" + treeItems[i + k2] + "-"
                    // + metadataContextMenu[k2] + "-" + metadataShellTitle[k2]);
                    tree.expandNode(treeNodes[i], metadataNodes[k2]).getNode(treeItems[i + k2] + " 0.1")
                            .contextMenu("Edit " + metadataContextMenu[k2]).click();
                    shell = gefBot.shell(metadataShellTitle[k2]);
                    shell.activate();
                    Assert.assertEquals(treeNodes[i] + " status", "testing", gefBot.ccomboBoxWithLabel("Status").getText());
                    shell.close();
                }
            }
        }
    }

    @After
    public void removePreviouslyCreateItems() {
        shell.close();
        for (int i = 0; i < treeNodes.length; i++) {
            if (i >= 0 && i <= 3) {
                tree.expandNode(treeNodes[i]).getNode(treeItems[i] + " 0.1").contextMenu("Delete").click();
            } else if (i == 4) {
                for (int k1 = 0; k1 < codeNodes.length; k1++) {
                    tree.expandNode(treeNodes[i], codeNodes[k1]).getNode(treeItems[i + k1] + " 0.1").contextMenu("Delete")
                            .click();
                }
            } else if (i == 6) {
                for (int k2 = 0; k2 < metadataNodes.length; k2++) {
                    tree.expandNode(treeNodes[i], metadataNodes[k2]).getNode(treeItems[i + k2] + " 0.1").contextMenu("Delete")
                            .click();
                }
            }
        }

        Utilities.emptyRecycleBin(gefBot, tree);
    }
}
