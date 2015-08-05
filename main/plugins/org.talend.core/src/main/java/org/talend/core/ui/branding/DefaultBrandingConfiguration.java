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
package org.talend.core.ui.branding;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.osgi.framework.Bundle;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.service.ICorePerlService;
import org.talend.core.tis.ICoreTisService;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DefaultBrandingConfiguration is as TIS BrandingConfiguration(don't hide repository Nodes or Components),all other
 * brandingConfigure should subclass of this one.
 */
public class DefaultBrandingConfiguration implements IBrandingConfiguration {

    private static Logger log = Logger.getLogger(DefaultBrandingConfiguration.class);

    protected IActionBarHelper helper;

    protected IActionBarConfigurer actionBarConfigurer;

    private String title = ""; //$NON-NLS-1$

    private boolean useMainLoginCheck = true;

    private boolean useProductRegistration = true;

    public IActionBarHelper getHelper() {
        return this.helper;
    }

    public void setHelper(IActionBarHelper helper) {
        this.helper = helper;
    }

    public IActionBarConfigurer getActionBarConfigurer() {
        return this.actionBarConfigurer;
    }

    public void setActionBarConfigurer(IActionBarConfigurer actionBarConfigurer) {
        this.actionBarConfigurer = actionBarConfigurer;
    }

    public void fillMenuBar(IMenuManager menuBar) {
        if (helper != null) {
            helper.fillMenuBar(menuBar);
        }
    }

    public void fillCoolBar(ICoolBarManager coolBar) {
        if (helper != null) {
            helper.fillCoolBar(coolBar);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#getHiddenRepositoryCategory()
     */
    public List<IRepositoryNode> getHiddenRepositoryCategory(IRepositoryNode parent, String type) {

        List<IRepositoryNode> nodes = new ArrayList<IRepositoryNode>();

        return nodes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#initPerspective(org.eclipse.ui.IPageLayout)
     */
    public void initPerspective(IPageLayout layout) {
        try {
            String componentSettingViewerId = "org.talend.designer.core.ui.views.properties.ComponentSettingsView";//$NON-NLS-1$
            //        String navigatorId = "org.eclipse.ui.views.ResourceNavigator"; //$NON-NLS-1$
            String outlineId = "org.eclipse.ui.views.ContentOutline"; //$NON-NLS-1$
            String codeId = "org.talend.designer.core.codeView"; //$NON-NLS-1$
            String repositoryId = IRepositoryView.VIEW_ID;

            String runProcessViewId = "org.talend.designer.runprocess.ui.views.processview"; //$NON-NLS-1$
            //        String problemsViewId = "org.talend.designer.core.ui.views.ProblemsView"; //$NON-NLS-1$
            String modulesViewId = "org.talend.designer.codegen.perlmodule.ModulesView"; //$NON-NLS-1$
            //String ecosystemViewId = "org.talend.designer.components.ecosystem.ui.views.EcosystemView"; //$NON-NLS-1$
            //        String schedulerViewId = "org.talend.scheduler.views.Scheduler"; //$NON-NLS-1$
            String contextsViewId = "org.talend.designer.core.ui.views.ContextsView"; //$NON-NLS-1$
            String gefPaletteViewId = "org.eclipse.gef.ui.palette_view"; //$NON-NLS-1$
            String jobSettingsViewId = "org.talend.designer.core.ui.views.jobsettings.JobSettingsView"; //$NON-NLS-1$
            //        String jobHierarchyViewId = "org.talend.designer.core.ui.hierarchy.JobHierarchyViewPart"; //$NON-NLS-1$
            //            String exchangeViewId = "org.talend.designer.components.exchange.ui.views.ExchangeView"; //$NON-NLS-1$

            // leftTopLayout
            IFolderLayout leftTopLayout = layout.createFolder("navigatorLayout", IPageLayout.LEFT, new Float(0.3), //$NON-NLS-1$
                    IPageLayout.ID_EDITOR_AREA);
            leftTopLayout.addView(repositoryId);
            // leftTopLayout.addView(navigatorId);

            // leftBottomLayout
            IFolderLayout leftBottomLayout = layout.createFolder("outlineCodeLayout", IPageLayout.BOTTOM, new Float(0.6), //$NON-NLS-1$
                    repositoryId);
            leftBottomLayout.addView(outlineId);
            leftBottomLayout.addView(codeId);
            // leftBottomLayout.addView(jobHierarchyViewId);

            IFolderLayout rightTopLayout = layout.createFolder("paletteLayout", IPageLayout.RIGHT, new Float(0.8), //$NON-NLS-1$
                    IPageLayout.ID_EDITOR_AREA);
            rightTopLayout.addView(gefPaletteViewId);

            // bottomLayout
            IFolderLayout bottomLayout = layout.createFolder("bottomLayout", IPageLayout.BOTTOM, new Float(0.6), //$NON-NLS-1$
                    IPageLayout.ID_EDITOR_AREA);
            // bottomLayout.addView(propertyId);
            bottomLayout.addView(jobSettingsViewId);
            bottomLayout.addView(contextsViewId);
            bottomLayout.addView(componentSettingViewerId);

            bottomLayout.addView(runProcessViewId);
            // bottomLayout.addView(problemsViewId);
            // bottomLayout.addView(modulesViewId);
            // bottomLayout.addView(ecosystemViewId);
            // bottomLayout.addView(exchangeViewId);
            //
            // bottomLayout.addView(schedulerViewId);

            bottomLayout.addPlaceholder("*");
        } catch (Throwable t) {
            log.warn(t, t);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#getAvailableComponents()
     */
    public String[] getAvailableComponents() {
        return null;
    }

    private int getIndex(List<PaletteEntry> children, String label) {
        int ret = -1;
        for (int i = 0; i < children.size(); i++) {
            PaletteEntry n = children.get(i);
            if (label.equals(n.getLabel())) {
                ret = i;
            }
        }
        return ret;
    }

    /**
     * 
     * DOC achen Comment method "hideComponents".
     * 
     * @param container
     * @param label
     */
    protected void hideComponents(PaletteEntry container, String label) {
        if (container instanceof PaletteContainer) {
            List<PaletteEntry> children = ((PaletteContainer) container).getChildren();
            int index = getIndex(children, label);
            if (index != -1) {
                children.remove(index);
            } else {
                for (PaletteEntry n : children) {
                    hideComponents(n, label);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#getAvailableLanguages()
     */
    public String[] getAvailableLanguages() {
        String[] languages;

        String enablePerl = "false";
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ICorePerlService.class)) {
            Properties properties = new Properties();
            Bundle b = Platform.getBundle("org.talend.resources.perl"); //$NON-NLS-1$ 

            try {
                File setupFile = new File(FileLocator.resolve(b.getEntry("resources/config.properties")).getFile());
                FileInputStream fileReader = new FileInputStream(setupFile);
                properties.load(fileReader);
                fileReader.close();
            } catch (Exception e) {
                ExceptionHandler.process(new Exception("Perl setup file not found !"));
            }

            enablePerl = properties.getProperty("enablePerl");
        }

        if ("true".equals(enablePerl)) {
            languages = new String[] { ECodeLanguage.JAVA.getName(), ECodeLanguage.PERL.getName() };
        } else {
            languages = new String[] { ECodeLanguage.JAVA.getName() };
        }
        return languages;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#getJobEditorSettings()
     */
    public Map<String, Object> getJobEditorSettings() {
        // no specific settings by default.
        return new HashMap<String, Object>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#isUseMailLoginCheck()
     */
    public boolean isUseMailLoginCheck() {
        return useMainLoginCheck;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#isUseProductRegistration()
     */
    public boolean isUseProductRegistration() {
        return useProductRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#isAllowDebugMode()
     */
    public boolean isAllowDebugMode() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#isUseDemoProjects()
     */
    public boolean isUseDemoProjects() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#getAdditionalTitle()
     */
    public String getAdditionalTitle() {
        return title;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#setAdditionalTitle(java.lang.String)
     */
    public void setAdditionalTitle(String title) {
        this.title = title;
    }

    public String getInitialWindowPerspectiveId() {
        return "org.talend.rcp.perspective"; //$NON-NLS-1$
    }

    public String getTISProductNameForWelcome() {
        return "Integration Suite Studio"; //$NON-NLS-1$
    }

    public void generateWelcomeHeaderImage() {
        if (PluginChecker.isCoreTISPluginLoaded()) {
            ICoreTisService service = (ICoreTisService) GlobalServiceRegister.getDefault().getService(ICoreTisService.class);
            service.drawWelcomeLogo(VersionUtils.getVersion());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#setUseMailLoginCheck(boolean)
     */
    public void setUseMailLoginCheck(boolean useMainLoginCheck) {
        this.useMainLoginCheck = useMainLoginCheck;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.branding.IBrandingConfiguration#setUseProductRegistration(boolean)
     */
    public void setUseProductRegistration(boolean useProductRegistration) {
        this.useProductRegistration = useProductRegistration;
    }

    public boolean isOnlyRemoteConnection() {
        return false;
    }

    public boolean isAllowChengeVersion() {
        return true;
    }
}
