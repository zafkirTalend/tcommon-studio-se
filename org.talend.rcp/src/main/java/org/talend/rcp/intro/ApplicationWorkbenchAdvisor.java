// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.ide.application.IDEWorkbenchAdvisor;
import org.talend.commons.CommonsPlugin;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ui.branding.IBrandingConfiguration;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.designer.codegen.CodeGeneratorActivator;
import org.talend.designer.runprocess.RunProcessPlugin;
import org.talend.rcp.Activator;
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

    private static final String COOLITEM_LINKS_ID = Activator.PLUGIN_ID + ".CoolItemLinks"; //$NON-NLS-1$

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
                    return perspectiveId;
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

        // add feature:15174
        createLinksToolbarItem();
        RegisterManagement.validateRegistration();
        if (!CommonsPlugin.isHeadless()) {
            CorePlugin.getDefault().getCodeGeneratorService().initializeTemplates();
        }

    }

    /**
     * DOC xtan, add Links ToolItem on postStartup, because need to keep it at last position.
     */
    private void createLinksToolbarItem() {
        IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);

        boolean isPoweredbyTalend = service.isPoweredbyTalend();

        // for talend product
        if (isPoweredbyTalend) {
            WorkbenchWindow workbenchWindow = (WorkbenchWindow) this.getWorkbenchConfigurer().getWorkbench()
                    .getActiveWorkbenchWindow();
            ICoolBarManager coolBarManager2 = workbenchWindow.getCoolBarManager2();

            IContributionItem linksCoolItem = coolBarManager2.find(COOLITEM_LINKS_ID);
            // means: load from .metadata/.plugins/org.eclipse.ui.workbench/workbench.xml
            if (linksCoolItem != null) {
                coolBarManager2.remove(linksCoolItem);
            }
            IToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
            toolBarManager.add(new LinksToolbarItem());
            coolBarManager2.add(new ToolBarContributionItem(toolBarManager, COOLITEM_LINKS_ID));
        }
    }
}
