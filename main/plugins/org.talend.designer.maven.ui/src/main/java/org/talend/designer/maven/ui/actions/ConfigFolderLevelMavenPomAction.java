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
package org.talend.designer.maven.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.ui.images.RepositoryImageProvider;
import org.talend.designer.maven.ui.dialog.RepositoryMavenSettingDialog;
import org.talend.designer.maven.ui.dialog.model.RepositoryMavenSettingManager;
import org.talend.designer.maven.ui.i18n.Messages;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IRepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.ui.actions.AContextualAction;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ConfigFolderLevelMavenPomAction extends AContextualAction {

    public static final List<ERepositoryObjectType> supportedTypes;
    static {
        supportedTypes = new ArrayList<ERepositoryObjectType>(10);
        ERepositoryObjectType processType = ERepositoryObjectType.PROCESS;
        if (processType != null) {
            supportedTypes.add(processType);
        }
        ERepositoryObjectType mrType = ERepositoryObjectType.valueOf("PROCESS_MR"); //$NON-NLS-1$
        if (mrType != null) {
            supportedTypes.add(mrType);
        }
        ERepositoryObjectType stormType = ERepositoryObjectType.valueOf("PROCESS_STORM"); //$NON-NLS-1$
        if (stormType != null) {
            supportedTypes.add(stormType);
        }
    }

    @Override
    public void init(TreeViewer viewer, IStructuredSelection selection) {
        boolean canWork = false;
        if (!selection.isEmpty()) {
            Object o = selection.getFirstElement();
            if (o instanceof RepositoryNode) {
                RepositoryNode node = (RepositoryNode) o;
                ENodeType nodeType = node.getType();
                switch (nodeType) {
                case SIMPLE_FOLDER:
                case STABLE_SYSTEM_FOLDER: // for standard job node
                case SYSTEM_FOLDER: // root of type
                    if (supportedTypes != null && supportedTypes.contains(node.getContentType())) {
                        /*
                         * FIXME, add the hidden id in OverridedJobDesignsRepositoryActionProvider, so no need check any
                         * more.
                         */

                        //
                        // if (nodeType.equals(ENodeType.SYSTEM_FOLDER)) {
                        // List<IRepositoryNode> children = node.getChildren();
                        // if (children.isEmpty()) {
                        // canWork = true;
                        // } else { // have "Standard Job" node sometime
                        // // FIXME, maybe this checking is not good.
                        // boolean found = false;
                        // for (IRepositoryNode child : children) {
                        // // "Standard Job" node
                        // if (child instanceof StableRepositoryNode
                        // || child.getType().equals(ENodeType.STABLE_SYSTEM_FOLDER)) {
                        // found = true;
                        // break;
                        // }
                        // }
                        // if (!found) {
                        // canWork = true;
                        // }
                        // }
                        // } else {
                        canWork = true;
                        // }
                    }
                    break;
                default:
                    break;
                }

                if (canWork && !ProjectManager.getInstance().isInCurrentMainProject(node)) {
                    canWork = false;
                }

                if (canWork) {
                    this.setText(Messages.getString("ConfigFolderLevelMavenPomAction_ActionTitle"));//$NON-NLS-1$
                    this.setToolTipText(this.getText());
                    // use context type icon first.
                    this.setImageDescriptor(ImageProvider.getImageDesc(RepositoryImageProvider.getIcon(node.getContentType())));
                }
            }
        }
        setEnabled(canWork);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.actions.AContextualAction#doRun()
     */
    @Override
    protected void doRun() {
        ISelection selection = getSelection();
        Object obj = ((IStructuredSelection) selection).getFirstElement();
        RepositoryNode node = (RepositoryNode) obj;

        RepositoryMavenSettingDialog dialog = new RepositoryMavenSettingDialog(PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getShell(), new RepositoryMavenSettingManager());
        dialog.setNode(node);
        dialog.open();
    }

}
