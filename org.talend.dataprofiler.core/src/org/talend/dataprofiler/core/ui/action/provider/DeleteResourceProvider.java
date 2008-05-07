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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.helper.AnaResourceFileHelper;
import org.talend.dataprofiler.core.helper.PrvResourceFileHelper;
import org.talend.dataquality.analysis.Analysis;
import org.talend.utils.sugars.TypedReturnCode;
import orgomg.cwm.objectmodel.core.Dependency;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * DOC rli class global comment. Detailled comment
 */
public class DeleteResourceProvider extends CommonActionProvider {

    private IFile selectedFile;

    public DeleteResourceProvider() {
    }

    private DeleteDataResourceAction deleteResourceAction;

    public void init(ICommonActionExtensionSite anExtensionSite) {

        if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            deleteResourceAction = new DeleteDataResourceAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
    }

    /**
     * Adds a submenu to the given menu with the name "New Component".
     */
    public void fillContextMenu(IMenuManager menu) {
        IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
        Object firstElement = selection.getFirstElement();
        if (firstElement instanceof IFile) {
            selectedFile = (IFile) firstElement;
        }
        deleteResourceAction.selectionChanged(selection);
        menu.add(deleteResourceAction);
    }

    /**
     * DOC rli DeleteResourceProvider class global comment. Detailled comment
     */
    class DeleteDataResourceAction extends DeleteResourceAction {

        public DeleteDataResourceAction(Shell shell) {
            super(shell);
            setImageDescriptor(ImageLib.getImageDescriptor(ImageLib.ACTION_DELETE));
        }

        /*
         * (non-Javadoc) Method declared on IAction.
         */
        public void run() {
            List<String> impactNames = new ArrayList<String>();
            if (selectedFile.getName().endsWith(PluginConstant.PRV_SUFFIX)) {
                TypedReturnCode<TdDataProvider> returnValue = PrvResourceFileHelper.getInstance().readFromFile(selectedFile);
                TdDataProvider provider = returnValue.getObject();
                EList<Dependency> supplierDependencies = provider.getSupplierDependency();
                for (Dependency dependency : supplierDependencies) {
                    EList<ModelElement> clients = dependency.getClient();
                    for (ModelElement modelElement : clients) {
                        if (impactNames.contains(modelElement.getName())) {
                            continue;
                        }
                        impactNames.add(modelElement.getName());
                    }
                }
                if (impactNames.size() != 0) {
                    MessageDialog.openWarning(null, "Impacted  analyses", "The following analyses will be unusable!\n"
                            + impactNames);
                }
                PrvResourceFileHelper.getInstance().clear();
            } else if (selectedFile.getName().endsWith(PluginConstant.ANA_SUFFIX)) {
                Analysis findAnalysis = AnaResourceFileHelper.getInstance().findAnalysis(selectedFile);
                EList<Dependency> clientDependency = findAnalysis.getSupplierDependency();
                for (Dependency dependency : clientDependency) {
                    EList<ModelElement> clients = dependency.getClient();
                    for (ModelElement modelElement : clients) {
                        if (impactNames.contains(modelElement.getName()) || modelElement.getName() == null) {
                            continue;
                        }
                        impactNames.add(modelElement.getName());
                    }
                }
                if (impactNames.size() != 0) {
                    MessageDialog.openWarning(null, "Impacted  reports", "The following reports will be unusable!\n"
                            + impactNames);
                }
                AnaResourceFileHelper.getInstance().clear();

            }
            super.run();
        }
    }
}
