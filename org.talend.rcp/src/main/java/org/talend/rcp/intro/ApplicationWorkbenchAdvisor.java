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
package org.talend.rcp.intro;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.PerspectiveBarContributionItem;
import org.eclipse.ui.internal.PerspectiveBarManager;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.ide.application.IDEWorkbenchAdvisor;
import org.talend.commons.CommonsPlugin;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ui.branding.IBrandingConfiguration;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.designer.codegen.CodeGeneratorActivator;
import org.talend.designer.runprocess.RunProcessPlugin;
import org.talend.repository.registeruser.RegisterManagement;

/**
 * DOC ccarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ApplicationWorkbenchAdvisor extends IDEWorkbenchAdvisor {

    /*
     * @Override public void preStartup() { WorkbenchAdapterBuilder.registerAdapters(); super.preStartup(); }
     */

    private static final String PERSPECTIVE_ID = "org.talend.rcp.perspective"; //$NON-NLS-1$

    private static final String PERSPECTIVE_DQ_ID = "org.talend.dataprofiler.DataProfilingPerspective"; //$NON-NLS-1$

    private static final String PERSPECTIVE_MDM_ID = "org.talend.mdm.perspective"; //$NON-NLS-1$

    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

    @Override
    public void initialize(IWorkbenchConfigurer configurer) {
        super.initialize(configurer);
        configurer.setSaveAndRestore(true);

        PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.CLOSE_EDITORS_ON_EXIT, true);
        PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
        PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR,
                IWorkbenchPreferenceConstants.TOP_RIGHT);
    }

    @Override
    public String getInitialWindowPerspectiveId() {
        IBrandingService brandingService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                IBrandingService.class);
        if (brandingService != null) {
            IBrandingConfiguration brandingConfiguration = brandingService.getBrandingConfiguration();
            if (brandingConfiguration != null) {
                String perspectiveId = brandingConfiguration.getInitialWindowPerspectiveId();
                if (perspectiveId != null) {
                    //
                    IPerspectiveDescriptor pd = PlatformUI.getWorkbench().getPerspectiveRegistry()
                            .findPerspectiveWithId(perspectiveId);
                    if (pd != null) {
                        return perspectiveId;
                    }
                }
            }
        }
        return PERSPECTIVE_ID;
    }

    @Override
    public void preStartup() {
        super.preStartup();

        // Fix bug 329,control the startup sequence of the plugin.
        // Promise the following plugin register themselves before system loaded.
        RunProcessPlugin.getDefault();
        CodeGeneratorActivator.getDefault();
        // FIXME SML Remove that
        // PerlModuleActivator.getDefault();
    }

    @Override
    public void postStartup() {
        super.postStartup();

        if (!ArrayUtils.contains(Platform.getApplicationArgs(), "--disableLoginDialog")) {
            RegisterManagement.getInstance().validateRegistration();
        }
        if (!CommonsPlugin.isHeadless()) {
            CorePlugin.getDefault().getCodeGeneratorService().initializeTemplates();
        }

        // feature 19053 add
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        PerspectiveBarManager barManager = ((WorkbenchWindow) activeWorkbenchWindow).getPerspectiveBar();
        if (barManager != null && (barManager instanceof PerspectiveBarManager)) {

            IContributionItem iconItem = barManager.find(PERSPECTIVE_ID);
            if (null == iconItem) {
                IPerspectiveDescriptor diMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
                        .findPerspectiveWithId(PERSPECTIVE_ID);
                if (null != diMailPerspective && (diMailPerspective instanceof IPerspectiveDescriptor)) {
                    PerspectiveBarContributionItem diItem = new PerspectiveBarContributionItem(diMailPerspective,
                            activeWorkbenchWindow.getActivePage());
                    if (null != diItem && (diItem instanceof PerspectiveBarContributionItem)) {
                        barManager.addItem(diItem);
                    }
                }
            }

            iconItem = barManager.find(PERSPECTIVE_DQ_ID);
            if (null == iconItem) {
                IPerspectiveDescriptor dqMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
                        .findPerspectiveWithId(PERSPECTIVE_DQ_ID);
                if (null != dqMailPerspective && (dqMailPerspective instanceof IPerspectiveDescriptor)) {
                    PerspectiveBarContributionItem dqItem = new PerspectiveBarContributionItem(dqMailPerspective,
                            activeWorkbenchWindow.getActivePage());
                    if (null != dqItem && (dqItem instanceof PerspectiveBarContributionItem)) {
                        barManager.addItem(dqItem);
                    }
                }
            }

            iconItem = barManager.find(PERSPECTIVE_MDM_ID);
            if (null == iconItem) {
                IPerspectiveDescriptor mdmMailPerspective = WorkbenchPlugin.getDefault().getPerspectiveRegistry()
                        .findPerspectiveWithId(PERSPECTIVE_MDM_ID);
                if (null != mdmMailPerspective && (mdmMailPerspective instanceof IPerspectiveDescriptor)) {
                    PerspectiveBarContributionItem mdmItem = new PerspectiveBarContributionItem(mdmMailPerspective,
                            activeWorkbenchWindow.getActivePage());
                    if (null != mdmItem && (mdmItem instanceof PerspectiveBarContributionItem)) {
                        barManager.addItem(mdmItem);
                    }
                }
            }
        }

    }
}
