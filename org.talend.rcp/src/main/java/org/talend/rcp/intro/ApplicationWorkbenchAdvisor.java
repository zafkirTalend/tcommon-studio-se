// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.rcp.intro;

import java.lang.management.ManagementFactory;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.talend.commons.exception.BusinessException;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.designer.codegen.CodeGeneratorActivator;
import org.talend.designer.codegen.ICodeGeneratorService;
import org.talend.designer.runprocess.RunProcessPlugin;
import org.talend.rcp.Activator;
import org.talend.repository.i18n.Messages;
import org.talend.repository.registeruser.RegisterManagement;
import org.talend.repository.ui.wizards.register.RegisterWizard;
import org.talend.repository.ui.wizards.register.RegisterWizardDialog;

/**
 * DOC ccarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

    /*
     * @Override public void preStartup() { WorkbenchAdapterBuilder.registerAdapters(); super.preStartup(); }
     */

    private static final String PERSPECTIVE_ID = "org.talend.rcp.perspective"; //$NON-NLS-1$

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
        // Start Code Generation Init
        ICodeGeneratorService codeGenService = (ICodeGeneratorService) GlobalServiceRegister.getDefault().getService(
                ICodeGeneratorService.class);
        codeGenService.initializeTemplates();

        // Start Web Service Registration
        try {
            if (!RegisterManagement.isProductRegistered()) {
                RegisterWizard registerWizard = new RegisterWizard();
                Shell shell = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
                WizardDialog dialog = new RegisterWizardDialog(shell, registerWizard);
                dialog.setTitle(Messages.getString("RegisterWizard.windowTitle")); //$NON-NLS-1$
                if (dialog.open() == WizardDialog.OK) {

                    // project language
                    RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                            Context.REPOSITORY_CONTEXT_KEY);
                    ECodeLanguage codeLanguage = repositoryContext.getProject().getLanguage();
                    String projectLanguage = codeLanguage.getName();

                    // OS
                    String osName = System.getProperty("os.name");
                    String osVersion = System.getProperty("os.version");

                    // Java version
                    String javaVersion = System.getProperty("java.version");

                    // Java Memory
                    long totalMemory = Runtime.getRuntime().totalMemory();

                    // RAM
                    com.sun.management.OperatingSystemMXBean composantSystem = (com.sun.management.OperatingSystemMXBean) ManagementFactory
                            .getOperatingSystemMXBean();
                    Long memRAM = new Long(composantSystem.getTotalPhysicalMemorySize() / 1024);

                    // CPU
                    int nbProc = Runtime.getRuntime().availableProcessors();

                    RegisterManagement.register(registerWizard.getEmail(), registerWizard.getCountry(), registerWizard
                            .isProxyEnabled(), registerWizard.getProxyHost(), registerWizard.getProxyPort(),
                            org.talend.core.CorePlugin.getDefault().getBundle().getHeaders().get(
                                    org.osgi.framework.Constants.BUNDLE_VERSION).toString(), projectLanguage, osName,
                            osVersion, javaVersion, totalMemory, memRAM, nbProc);
                } else {
                    RegisterManagement.decrementTry();
                }
            }
        } catch (BusinessException e) {
            // Do nothing : registration web service error is not a problem
        }
    }

}
