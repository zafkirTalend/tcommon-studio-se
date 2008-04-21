// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.action.provider;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.ui.wizard.analysis.CreateNewAnalysisWizard;

/**
 * DOC rli class global comment. Detailled comment
 */
public class RunAnalysisActionProvider extends CommonActionProvider {

    public RunAnalysisActionProvider() {
    }

    private IAction runAnalysisAction;

    public void init(ICommonActionExtensionSite anExtensionSite) {

        if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            runAnalysisAction = new RunAnalysisAction();
        }
    }

    /**
     * Adds a submenu to the given menu with the name "New Component".
     */
    public void fillContextMenu(IMenuManager menu) {
        menu.add(runAnalysisAction);
    }

    /**
     * @author rli
     * 
     */
    private class RunAnalysisAction extends Action {

        public RunAnalysisAction() {
            super("Run");
            setImageDescriptor(ImageLib.getImageDescriptor(ImageLib.REFRESH_IMAGE));
        }

        /*
         * (non-Javadoc) Method declared on IAction.
         */
        public void run() {
            try {
                CreateNewAnalysisWizard wizard = new CreateNewAnalysisWizard(PlatformUI.getWorkbench(), true, null, null);
                wizard.setForcePreviousAndNextButtons(true);

                WizardDialog dialog = new WizardDialog(null, wizard);
                dialog.setPageSize(500, 340);
                dialog.create();

                dialog.open();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
