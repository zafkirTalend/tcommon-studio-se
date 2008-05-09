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

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.manager.DQStructureManager;
import org.talend.dataprofiler.core.ui.wizard.analysis.CreateNewAnalysisWizard;


/**
 * @author rli
 *
 */
public class NewAnalysisActionProvider extends CommonActionProvider {
    
    public NewAnalysisActionProvider() {
    }

    private IAction createAnalysisAction;

    private String selectedFolderName;

    public void init(ICommonActionExtensionSite anExtensionSite) {

        if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            createAnalysisAction = new CreateNewAnalysisAction();
        }
    }

    /**
     * Adds a submenu to the given menu with the name "New Component".
     */
    public void fillContextMenu(IMenuManager menu) {
        Object obj = ((TreeSelection) this.getContext().getSelection()).getFirstElement();
        if (obj instanceof IFolder) {
            selectedFolderName = ((IFolder) obj).getName();
            if (selectedFolderName.equals(DQStructureManager.ANALYSIS)) {
                menu.add(createAnalysisAction);
            }
        }
    }

    /**
     * @author rli
     * 
     */
    private class CreateNewAnalysisAction extends Action {

        public CreateNewAnalysisAction() {
            super("New Analysis");
            setImageDescriptor(ImageLib.getImageDescriptor(ImageLib.ACTION_NEW_ANALYSIS));
        }

        /*
         * (non-Javadoc) Method declared on IAction.
         */
        public void run() {
//            IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getProject(DQStructureManager.DATA_PROFILING).getFolder(
//                    DQStructureManager.ANALYSIS);
//            IFile newFile = folder.getFile("sample." + FactoriesUtil.ANA);
//            if (newFile.exists()) {
//                return;
//            }
//            try {
//                newFile.create(new ByteArrayInputStream(new byte[0]), false, null);
//            } catch (CoreException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
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
