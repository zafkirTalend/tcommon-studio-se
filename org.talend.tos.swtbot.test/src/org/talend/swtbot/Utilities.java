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

package org.talend.swtbot;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.Assert;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * DOC sgandon class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
/**
 * DOC Administrator class global comment. Detailled comment
 */
public class Utilities {

    private static final String SAMPLE_FOLDER_PATH = "/sample/"; //$NON-NLS-1$

    /**
     * return a File instance pointing to the file or folder located below this plugin sample folder
     * 
     * @param subSamplePath, path located below /sample/, so subpath should never start with a /, example : test.xsl
     * @return the File instance pointing to the obsolute path of the subSamplePath
     * @throws IOException
     * @throws URISyntaxException
     */
    public static File getFileFromCurrentPluginSampleFolder(String subSamplePath) throws IOException, URISyntaxException {
        URL url = Utilities.class.getResource(SAMPLE_FOLDER_PATH + subSamplePath);
        URI escapedURI = getFileUrl(url);
        return URIUtil.toFile(escapedURI);
    }

    /**
     * Convert any URL to a file URL
     * 
     * @param url, may be a eclipse URL
     * @return a file:/ url
     * @throws IOException
     * @throws URISyntaxException
     */
    public static URI getFileUrl(URL url) throws IOException, URISyntaxException {
        // bundleresource://875.fwk22949069:4/top400/
        URL unescapedURL = FileLocator.toFileURL(url);// bug 145096
        // file:/E:sv n/org.talend.model.migration.test/samples/top400/
        URI escapedURI = new URI(unescapedURL.getProtocol(), unescapedURL.getPath(), unescapedURL.getQuery());
        // file:/E:sv%20n/org.talend.model.migration.test/samples/top400/
        return escapedURI;
    }

    /**
     * Empty Recycle bin
     * 
     * @author fzhong
     * @param gefBot, SWTGefBot
     * @param tree, tree from repository
     * @return void
     */
    public static void emptyRecycleBin(SWTGefBot gefBot, SWTBotTree tree) {
        tree.select("Recycle bin").contextMenu("Empty recycle bin").click();
        gefBot.waitUntil(Conditions.shellIsActive("Empty recycle bin"));
        gefBot.button("Yes").click();
    }

    /**
     * Create item in repository
     * 
     * @author fzhong
     * @param itemType, item type
     * @param itemName, item name
     * @param tree, tree in repository
     * @param gefBot, SWTGefBot
     * @param shell, creating wizard shell
     */
    private static void create(String itemType, String itemName, SWTBotTree tree, SWTGefBot gefBot, SWTBotShell shell) {
        tree.setFocus();
        if ("Joblet".equals(itemType))
            tree.select("Joblet Designs").contextMenu("Create Joblet").click();
        else if ("SQLTemplate".equals(itemType))
            tree.expandNode("SQL Templates", "Generic").getNode("UserDefined").contextMenu("Create SQLTemplate").click();
        else if ("Business Model".equals(itemType))
            tree.select("Business Models").contextMenu("Create Business Model").click();
        else if ("job".equals(itemType))
            tree.select("Job Designs").contextMenu("Create job").click();
        else if ("routine".equals(itemType))
            tree.expandNode("Code").getNode("Routines").contextMenu("Create routine").click();

        gefBot.waitUntil(Conditions.shellIsActive("New " + itemType));
        shell = gefBot.shell("New " + itemType);
        shell.activate();

        gefBot.textWithLabel("Name").setText(itemName);

        boolean finishButtonIsEnabled = gefBot.button("Finish").isEnabled();
        if (finishButtonIsEnabled) {
            gefBot.button("Finish").click();
        } else {
            shell.close();
            Assert.assertTrue("finish button is not enabled, " + itemType + " created fail", finishButtonIsEnabled);
        }

        SWTBotTreeItem newTreeItem = null;
        try {
            if ("Joblet".equals(itemType)) {
                gefBot.toolbarButtonWithTooltip("Save (Ctrl+S)").click();
                newTreeItem = tree.expandNode("Joblet Designs").select(itemName + " 0.1");
            } else if ("SQLTemplate".equals(itemType))
                newTreeItem = tree.expandNode("SQL Templates", "Generic", "UserDefined").select(itemName + " 0.1");
            else if ("Business Model".equals(itemType))
                newTreeItem = tree.expandNode("Business Models").select(itemName + " 0.1");
            else if ("job".equals(itemType))
                newTreeItem = tree.expandNode("Job Designs").select(itemName + " 0.1");
            else if ("routine".equals(itemType))
                newTreeItem = tree.expandNode("Code", "Routines").select(itemName + " 0.1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertNotNull(itemType + " item is not created", newTreeItem);
        }
    }

    public static void createJoblet(String jobletName, SWTBotTree tree, SWTGefBot gefBot, SWTBotShell shell) {
        create("Joblet", jobletName, tree, gefBot, shell);
    }

    public static void createSqlTemplate(String sqlTemplateName, SWTBotTree tree, SWTGefBot gefBot, SWTBotShell shell) {
        create("SQLTemplate", sqlTemplateName, tree, gefBot, shell);
    }

    public static void createBusinessModel(String businessModelName, SWTBotTree tree, SWTGefBot gefBot, SWTBotShell shell) {
        create("Business Model", businessModelName, tree, gefBot, shell);
    }

    public static void createJob(String jobName, SWTBotTree tree, SWTGefBot gefBot, SWTBotShell shell) {
        create("job", jobName, tree, gefBot, shell);
    }

    public static void createRoutine(String routineName, SWTBotTree tree, SWTGefBot gefBot, SWTBotShell shell) {
        create("routine", routineName, tree, gefBot, shell);
    }

}
