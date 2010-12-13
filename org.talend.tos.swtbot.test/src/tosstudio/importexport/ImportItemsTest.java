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
public class ImportItemsTest extends TalendSwtBotForTos {

    private SWTBotView view;

    private SWTBotTree tree;

    private static String SAMPLE_RELATIVE_FILEPATH = "items.zip";

    @Before
    public void initialisePrivateFields() {
        view = gefBot.viewByTitle("Repository");
        tree = new SWTBotTree((Tree) gefBot.widget(WidgetOfType.widgetOfType(Tree.class), view.getWidget()));
    }

    @Test
    public void importItems() throws IOException, URISyntaxException {
        gefBot.toolbarButtonWithTooltip("Import Items").click();

        gefBot.shell("Import items").activate();
        gefBot.radio("Select archive file:").click();
        gefBot.text(1).setText(Utilities.getFileFromCurrentPluginSampleFolder(SAMPLE_RELATIVE_FILEPATH).getAbsolutePath());
        gefBot.tree().setFocus();
        gefBot.button("Select All").click();
        gefBot.button("Finish").click();
        gefBot.waitUntil(Conditions.shellCloses(gefBot.shell("Progress Information")));

        view.setFocus();
        tree.setFocus();
        SWTBotTreeItem newBusinessItem = tree.expandNode("Business Models").select("businessTest" + " 0.1");
        Assert.assertNotNull(newBusinessItem);
        SWTBotTreeItem newJobItem = tree.expandNode("Job Designs").select("jobTest" + " 0.1");
        Assert.assertNotNull(newJobItem);
        SWTBotTreeItem newJobletItem = tree.expandNode("Joblet Designs").select("jobletTest" + " 0.1");
        Assert.assertNotNull(newJobletItem);
        SWTBotTreeItem newContextItem = tree.expandNode("Contexts").select("contextTest" + " 0.1");
        Assert.assertNotNull(newContextItem);
        SWTBotTreeItem newRoutineItem = tree.expandNode("Code", "Routines").select("routineTest" + " 0.1");
        Assert.assertNotNull(newRoutineItem);
        SWTBotTreeItem newJobScriptItem = tree.expandNode("Code", "Job Scripts").select("jobscriptsTest" + " 0.1");
        Assert.assertNotNull(newJobScriptItem);
        SWTBotTreeItem newMysqlItem = tree.expandNode("Metadata", "Db Connections").select("mysqlTest" + " 0.1");
        Assert.assertNotNull(newMysqlItem);
        SWTBotTreeItem newSapItem = tree.expandNode("Metadata", "SAP Connections").select("sapTest" + " 0.1");
        Assert.assertNotNull(newSapItem);
        SWTBotTreeItem newDelimitedFileItem = tree.expandNode("Metadata", "File delimited").select("delimitedFileTest" + " 0.1");
        Assert.assertNotNull(newDelimitedFileItem);
        SWTBotTreeItem newPositionalFileItem = tree.expandNode("Metadata", "File positional").select(
                "positionalFileTest" + " 0.1");
        Assert.assertNotNull(newPositionalFileItem);
        SWTBotTreeItem newRegexFileItem = tree.expandNode("Metadata", "File regex").select("regexFileTest" + " 0.1");
        Assert.assertNotNull(newRegexFileItem);
        SWTBotTreeItem newXmlFileItem = tree.expandNode("Metadata", "File xml").select("xmlFileTest" + " 0.1");
        Assert.assertNotNull(newXmlFileItem);
        SWTBotTreeItem newExcelFileItem = tree.expandNode("Metadata", "File Excel").select("excelFileTest" + " 0.1");
        Assert.assertNotNull(newExcelFileItem);
        SWTBotTreeItem newLdifFileItem = tree.expandNode("Metadata", "File ldif").select("ldifTest" + " 0.1");
        Assert.assertNotNull(newLdifFileItem);
        SWTBotTreeItem newLDAPItem = tree.expandNode("Metadata", "LDAP").select("ldapTest" + " 0.1");
        Assert.assertNotNull(newLDAPItem);
        SWTBotTreeItem newSalesforceItem = tree.expandNode("Metadata", "Salesforce").select("salesforceTest" + " 0.1");
        Assert.assertNotNull(newSalesforceItem);
        SWTBotTreeItem newGenericSchemaItem = tree.expandNode("Metadata", "Generic schemas").select("genericSchemaTest" + " 0.1");
        Assert.assertNotNull(newGenericSchemaItem);
        SWTBotTreeItem newCopybookItem = tree.expandNode("Metadata", "Copybook").select("copybookTest" + " 0.1");
        Assert.assertNotNull(newCopybookItem);
        SWTBotTreeItem newHL7Item = tree.expandNode("Metadata", "HL7").select("HL7Test" + " 0.1");
        Assert.assertNotNull(newHL7Item);
        SWTBotTreeItem newWebServiceItem = tree.expandNode("Metadata", "Web Service").select("webserviceTest" + " 0.1");
        Assert.assertNotNull(newWebServiceItem);
    }

    @After
    public void removePreviouslyCreateItems() {
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
