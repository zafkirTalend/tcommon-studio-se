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
package tosstudio.importexport;

import java.io.IOException;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
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
public class ExportItemsTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private static String SAMPLE_RELATIVE_FILEPATH = "items.zip";

    private static boolean isExportAsZipFile = false;

    private String[] treeNodes = { "Business Models", "Job Designs", "Joblet Designs", "Contexts", "Code", "SQL Templates",
            "Metadata", "Documentation" };

    private String[] treeItems = { "businessTest", "jobTest", "jobletTest", "contextTest", "routineTest", "jobscriptsTest",
            "mysqlTest", "sapTest", "delimitedFileTest", "positionalFileTest", "regexFileTest", "xmlFileTest", "excelFileTest",
            "ldifTest", "ldapTest", "salesforceTest", "genericSchemaTest", "copybookTest", "HL7Test", "webserviceTest" };

    private String[] codeNodes = { "Routines", "Job Scripts" };

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
    }

    @Test
    public void exportItems() throws IOException, URISyntaxException {
        gefBot.toolbarButtonWithTooltip("Export Items").click();
        gefBot.shell("Export items").activate();
        gefBot.radio("Select archive file:").click();
        gefBot.text(1).setText(
                Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_FILEPATH).getParent() + "\\output.zip");
        gefBot.tree().setFocus();
        gefBot.button("Select All").click();
        gefBot.button("Finish").click();

        isExportAsZipFile = Utilities.getFileFromCurrentPluginSampleFolder("output.zip").exists();
        Assert.assertEquals(true, isExportAsZipFile);
    }

    @After
    public void removePreviouslyCreateItems() throws IOException, URISyntaxException {
        Utilities.getFileFromCurrentPluginSampleFolder("output.zip").delete();

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
        tree.getTreeItem("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
