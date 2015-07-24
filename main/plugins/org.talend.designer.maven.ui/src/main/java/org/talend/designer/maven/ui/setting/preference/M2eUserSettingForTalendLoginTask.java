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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Settings;
import org.eclipse.core.internal.net.ProxyType;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.osgi.util.tracker.ServiceTracker;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.runtime.projectsetting.IProjectSettingTemplateConstants;
import org.talend.designer.maven.DesignerMavenPlugin;
import org.talend.designer.maven.template.MavenTemplateManager;
import org.talend.designer.maven.ui.DesignerMavenUiPlugin;
import org.talend.login.AbstractLoginTask;
import org.talend.utils.io.FilesUtils;
import org.xml.sax.SAXException;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * set the preference for MavenSettingsPreferencePage.
 */
public class M2eUserSettingForTalendLoginTask extends AbstractLoginTask {

    private static final String MAVEN_SETTING_HAVE_SET = "Maven_Setting_Have_Set"; //$NON-NLS-1$

    private ServiceTracker proxyTracker;

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

        File studioUserSettingsFile = configPath.append(IProjectSettingTemplateConstants.MAVEN_USER_SETTING_TEMPLATE_FILE_NAME)
                .toFile();

        IPreferenceStore mvnUiStore = DesignerMavenUiPlugin.getDefault().getPreferenceStore();

        try {
            boolean set = mvnUiStore.getBoolean(MAVEN_SETTING_HAVE_SET);
            if (!set) {// first time to set
                if (!studioUserSettingsFile.exists()) {
                    InputStream inputStream = MavenTemplateManager.getBundleTemplateStream(DesignerMavenPlugin.PLUGIN_ID,
                            IProjectSettingTemplateConstants.PATH_RESOURCES_TEMPLATES + '/'
                                    + IProjectSettingTemplateConstants.MAVEN_USER_SETTING_TEMPLATE_FILE_NAME);
                    if (inputStream != null) {
                        FilesUtils.copyFile(inputStream, studioUserSettingsFile);
                    }
                }
                // re-check
                if (studioUserSettingsFile.exists()) {
                    MavenPlugin.getMavenConfiguration().setUserSettingsFile(studioUserSettingsFile.getAbsolutePath());
                    mvnUiStore.setValue(MAVEN_SETTING_HAVE_SET, true);
                } else { // not set, set default
                    MavenPlugin.getMavenConfiguration().setUserSettingsFile(null);
                }
            } else if (!studioUserSettingsFile.exists()) { // not first time, but deleted by manually.
                // FIXME, try use system default one?
                MavenPlugin.getMavenConfiguration().setUserSettingsFile(null);
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }

        try {
            final IMaven maven = MavenPlugin.getMaven();
            maven.reloadSettings();
            final Settings settings = maven.getSettings();

            /*
             * FIXME, only deal with for special settings in studio.
             * 
             * If use other user setting, nothing to do.
             * 
             * Just keep the problem for user, because we should change the setting for Studio in configuration folder
             * only.
             */
            final boolean isStudioUserSetting = studioUserSettingsFile.getAbsolutePath().equals(
                    MavenPlugin.getMavenConfiguration().getUserSettingsFile());

            if (isStudioUserSetting) {
                boolean modified = false;

                // update the local repository
                if (checkLocalRepository(monitor, maven, settings, configPath, studioUserSettingsFile)) {
                    modified = true;
                }
                // update for default repository
                if (updateProfileSettings(monitor, maven, settings, configPath, studioUserSettingsFile)) {
                    modified = true;
                }

                if (modified) { // save changes
                    maven.writeSettings(settings, new FileOutputStream(studioUserSettingsFile));
                    maven.reloadSettings();
                }
            }

            // update the proxies
            updateProxiesPreference(monitor, maven, settings);
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }

    }

    private IProxyService getProxyService() {
        if (proxyTracker == null) {
            proxyTracker = new ServiceTracker(DesignerMavenUiPlugin.getDefault().getBundle().getBundleContext(),
                    IProxyService.class.getName(), null);
            proxyTracker.open();
        }
        return (IProxyService) proxyTracker.getService();
    }

    private boolean checkLocalRepository(IProgressMonitor monitor, IMaven maven, Settings settings, Path configPath,
            File userSettingsFile) throws CoreException, ParserConfigurationException, SAXException, IOException,
            TransformerException {
        File oldRepoFolder = new File(maven.getLocalRepositoryPath());
        try {
            if (!oldRepoFolder.exists()) { // TUP-3301
                oldRepoFolder.mkdirs(); // try to create the root folder first.
            }
        } catch (Throwable e) {
            // if can't create, continue.
        }
        if (!oldRepoFolder.exists() || !oldRepoFolder.canRead() || !oldRepoFolder.canWrite()) {
            // need change the repo setting
            File studioDefaultRepoFolder = new File(configPath.toFile(), ".m2/repository"); //$NON-NLS-1$
            if (!studioDefaultRepoFolder.exists()) {
                studioDefaultRepoFolder.mkdirs();
            }

            // make sure the setting file can be changed.
            if (userSettingsFile.canRead() && userSettingsFile.canWrite()) {
                // modify the setting file for "localRepository"
                settings.setLocalRepository(studioDefaultRepoFolder.getAbsolutePath());
                // should same as MavenSettingsPreferencePage.updateSettings update index?
                MavenPlugin.getIndexManager().getWorkspaceIndex().updateIndex(true, monitor);

                return true;
            }
        }
        return false;
    }

    private void updateProxiesPreference(IProgressMonitor monitor, IMaven maven, Settings settings) throws CoreException {
        List<Proxy> proxiesList = settings.getProxies();
        if (proxiesList != null && !proxiesList.isEmpty()) {
            IProxyService proxyService = getProxyService();
            if (proxyService != null) {
                proxyService.setSystemProxiesEnabled(false); // not NATIVE_PROVIDER
                if (!proxyService.isProxiesEnabled()) { // is DIRECT_PROVIDER
                    proxyService.setProxiesEnabled(true);// try to change to ECLIPSE_PROVIDER
                }

                // set proxy
                for (Proxy p : proxiesList) {
                    if (p.isActive()) {
                        setProxy(monitor, proxyService, p);
                        break; // only set for first active proxy.
                    }
                }
            }
        }
    }

    @SuppressWarnings("restriction")
    private void setProxy(IProgressMonitor monitor, IProxyService proxyService, Proxy p) throws CoreException {
        String protocol = p.getProtocol();
        if (protocol == null) {
            return;
        }
        String host = p.getHost();
        // must set host and port
        if (host == null || host.trim().length() == 0 || p.getPort() <= 0) {
            return;
        }

        IProxyData proxyData = proxyService.getProxyData(protocol.toUpperCase());
        if (proxyData == null) {
            return;
        }
        // have set another one in proxy preference specially, nothing to do.
        if (proxyData.getHost() != null && !host.equals(proxyData.getHost())) {
            return;
        }

        proxyData.setHost(p.getHost());
        proxyData.setPort(p.getPort());
        // if need authenticate for the proxy.
        String username = p.getUsername();
        if (username != null && username.trim().length() > 0) {
            proxyData.setUserid(username);
            String password = p.getPassword();
            if (password != null && password.trim().length() > 0) {
                proxyData.setPassword(password);
            }
        }
        proxyService.setProxyData(new IProxyData[] { proxyData });

        String nonProxyHosts = p.getNonProxyHosts();
        if (nonProxyHosts != null && nonProxyHosts.trim().length() > 0) {
            List<String> bypassHosts = new ArrayList<String>();
            String[] nonProxiedHosts = proxyService.getNonProxiedHosts();
            if (nonProxiedHosts != null) {
                bypassHosts.addAll(Arrays.asList(nonProxiedHosts));
            }
            boolean addNew = false;
            String[] convertPropertyStringToHosts = ProxyType.convertPropertyStringToHosts(nonProxyHosts);
            for (String nonProxy : convertPropertyStringToHosts) {
                if (!bypassHosts.contains(nonProxy)) { // if not existed
                    addNew = true;
                    bypassHosts.add(nonProxy);
                }
            }
            if (addNew) {
                proxyService.setNonProxiedHosts(bypassHosts.toArray(new String[bypassHosts.size()]));
            }
        }
    }

    private boolean updateProfileSettings(IProgressMonitor monitor, IMaven maven, Settings settings, Path configPath,
            File userSettingsFile) {
        Profile profile = settings.getProfilesAsMap().get("provider-repository"); //$NON-NLS-1$
        if (profile != null) {
            String mavenRepoPath = configPath.append("maven_repository").toString(); //$NON-NLS-1$
            // update properties
            profile.getProperties().setProperty("default.repository.path", mavenRepoPath); //$NON-NLS-1$
            profile.getActivation().getFile().setExists(mavenRepoPath);
            return true;
        }
        return false;
    }

}
