// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.ui.setting.preference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenConfiguration;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.runtime.projectsetting.IProjectSettingTemplateConstants;
import org.talend.designer.maven.DesignerMavenPlugin;
import org.talend.designer.maven.template.MavenTemplateManager;
import org.talend.designer.maven.ui.DesignerMavenUiPlugin;
import org.talend.login.AbstractLoginTask;
import org.talend.utils.io.FilesUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * set the preference for MavenSettingsPreferencePage.
 */
public class M2eUserSettingForTalendLoginTask extends AbstractLoginTask {

    private static final String MAVEN_SETTING_HAVE_SET = "Maven_Setting_Have_Set"; //$NON-NLS-1$

    @Override
    public boolean isCommandlineTask() {
        return true; // also enable support for commandline, so set true.
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.login.ILoginTask#getOrder()
     */
    @Override
    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2015, 6, 17, 12, 0, 0);
        return gc.getTime();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.login.ILoginTask#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Path configPath = new Path(Platform.getConfigurationLocation().getURL().getPath());

        File userSettingsFile = configPath.append(IProjectSettingTemplateConstants.MAVEN_USER_SETTING_TEMPLATE_FILE_NAME)
                .toFile();

        IPreferenceStore mvnUiStore = DesignerMavenUiPlugin.getDefault().getPreferenceStore();

        final IMavenConfiguration mavenConfiguration = MavenPlugin.getMavenConfiguration();
        try {
            boolean set = mvnUiStore.getBoolean(MAVEN_SETTING_HAVE_SET);
            if (!set) {// first time to set
                if (!userSettingsFile.exists()) {
                    InputStream inputStream = MavenTemplateManager.getBundleTemplateStream(DesignerMavenPlugin.PLUGIN_ID,
                            IProjectSettingTemplateConstants.PATH_RESOURCES_TEMPLATES + '/'
                                    + IProjectSettingTemplateConstants.MAVEN_USER_SETTING_TEMPLATE_FILE_NAME);
                    if (inputStream != null) {
                        FilesUtils.copyFile(inputStream, userSettingsFile);
                    }
                }
                // re-check
                if (userSettingsFile.exists()) {
                    mavenConfiguration.setUserSettingsFile(userSettingsFile.getAbsolutePath());
                    mvnUiStore.setValue(MAVEN_SETTING_HAVE_SET, true);
                } else { // not set, set default
                    mavenConfiguration.setUserSettingsFile(null);
                }
            } else if (!userSettingsFile.exists()) { // not first time, but deleted by manually.
                // FIXME, try use system default one?
                mavenConfiguration.setUserSettingsFile(null);
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }

        // update the local repository
        try {
            final IMaven maven = MavenPlugin.getMaven();
            maven.reloadSettings();
            File oldRepoFolder = new File(maven.getLocalRepositoryPath());
            if (!oldRepoFolder.canRead() || !oldRepoFolder.canWrite()) {
                // need change the repo setting
                File studioDefaultRepoFolder = new File(configPath.toFile(), ".m2/repository"); //$NON-NLS-1$
                if (!studioDefaultRepoFolder.exists()) {
                    studioDefaultRepoFolder.mkdirs();
                }

                // FIXME, only deal with for special settings in studio, if use use other setting, nothing to do, just
                // keep the problem for user, because we should change the setting for Studio in configuration folder
                // only.
                if (mavenConfiguration.getUserSettingsFile().equals(userSettingsFile.getAbsolutePath())) {
                    // make sure the setting file can be changed.
                    if (userSettingsFile.canRead() && userSettingsFile.canWrite()) {
                        // modify the setting file for "localRepository"

                        setNewLocalRepository(userSettingsFile, studioDefaultRepoFolder);

                        // should same as MavenSettingsPreferencePage.updateSettings update index?
                        MavenPlugin.getIndexManager().getWorkspaceIndex().updateIndex(true, monitor);

                    }
                }

            }

        } catch (Exception e) {
            ExceptionHandler.process(e);
        }

    }

    private void setNewLocalRepository(File settingFile, File repoFolder) throws ParserConfigurationException, SAXException,
            IOException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(settingFile);
        // find
        Node localRepositoryNode = null;
        NodeList childrenNodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < childrenNodeList.getLength(); i++) {
            Node child = childrenNodeList.item(i);
            if (child != null && child.getNodeType() == Node.ELEMENT_NODE) {
                if (child.getNodeName().equals("localRepository")) { //$NON-NLS-1$
                    localRepositoryNode = child;
                    break;
                }
            }
        }
        if (localRepositoryNode == null) {
            localRepositoryNode = document.createElement("localRepository"); //$NON-NLS-1$
            document.getDocumentElement().appendChild(localRepositoryNode);
        }

        localRepositoryNode.setTextContent(repoFolder.getAbsolutePath());

        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transFormer = transFactory.newTransformer();
        transFormer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
        transFormer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(settingFile)));
    }

}
