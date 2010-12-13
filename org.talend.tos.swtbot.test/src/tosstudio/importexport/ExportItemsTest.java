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

        tree.expandNode("Business Models").getNode("businessTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Job Designs").getNode("jobTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Joblet Designs").getNode("jobletTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Contexts").getNode("contextTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Code", "Routines").getNode("routineTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Code", "Job Scripts").getNode("jobscriptsTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "Db Connections").getNode("mysqlTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "SAP Connections").getNode("sapTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "File delimited").getNode("delimitedFileTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "File positional").getNode("positionalFileTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "File regex").getNode("regexFileTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "File xml").getNode("xmlFileTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "File Excel").getNode("excelFileTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "File ldif").getNode("ldifTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "LDAP").getNode("ldapTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "Salesforce").getNode("salesforceTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "Generic schemas").getNode("genericSchemaTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "Copybook").getNode("copybookTest" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "HL7").getNode("HL7Test" + " 0.1").contextMenu("Delete").click();
        tree.expandNode("Metadata", "Web Service").getNode("webserviceTest" + " 0.1").contextMenu("Delete").click();

        tree.getTreeItem("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }
}
