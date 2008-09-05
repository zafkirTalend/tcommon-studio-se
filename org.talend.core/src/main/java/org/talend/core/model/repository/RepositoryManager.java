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
package org.talend.core.model.repository;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.nodes.IProjectRepositoryNode;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * ggu class global comment. Detailled comment
 */
public final class RepositoryManager {

    public static IPreferenceStore getPreferenceStore() {
        return CorePlugin.getDefault().getRepositoryService().getRepositoryPreferenceStore();
    }

    public static boolean isRefreshManually() {
        return getPreferenceStore().getBoolean(IRepositoryPrefConstants.MANUALLY_REFRESH);
    }

    public static boolean isRefreshCreated() {
        return getPreferenceStore().getBoolean(IRepositoryPrefConstants.CREATING_REFRESH);
    }

    public static boolean isRefreshSaved() {
        return getPreferenceStore().getBoolean(IRepositoryPrefConstants.SAVING_REFRESH);
    }

    public static boolean isRefreshDeleted() {
        return getPreferenceStore().getBoolean(IRepositoryPrefConstants.DELETING_REFRESH);
    }

    public static IRepositoryView getRepositoryView() {
        IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(IRepositoryView.VIEW_ID);
        if (part == null) {
            try {
                part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(IRepositoryView.VIEW_ID);
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }

        return (IRepositoryView) part;
    }

    /**
     * 
     *for create
     */
    public static void refreshCreatedNode(ERepositoryObjectType type) {
        if (isRefreshManually() || !isRefreshCreated()) {
            refresh(type);
        } else {
            getRepositoryView().refresh();
        }

    }

    public static void refreshCreatedNode(IProjectRepositoryNode projectNode, ERepositoryObjectType type) {
        if ((isRefreshManually() || !isRefreshCreated()) && !type.isSubItem()) {
            if (projectNode != null) {
                RepositoryNode rootNode = projectNode.getRootRepositoryNode(type);
                getRepositoryView().refreshAllChildNodes(rootNode);
            } else {
                // main project
                refresh(type);
            }
        } else {
            getRepositoryView().refresh();
        }

    }

    /**
     * 
     * for delete
     */
    public static void refreshDeletedNode(Set<ERepositoryObjectType> types) {
        if (isRefreshManually() || !isRefreshDeleted()) {
            IRepositoryView repositoryView = getRepositoryView();

            RepositoryNode root = repositoryView.getRoot();
            if (root != null && root instanceof IProjectRepositoryNode) {
                RepositoryNode recBinNode = ((IProjectRepositoryNode) root).getRecBinNode();

                Set<ERepositoryObjectType> existedTypes = new HashSet<ERepositoryObjectType>();
                for (RepositoryNode child : recBinNode.getChildren()) {
                    ERepositoryObjectType objectType = child.getObjectType();
                    if (objectType.isSubItem()) {
                        RepositoryNode parent = child.getParent();
                        if (parent.getObject() == null) { // for db connection
                            parent = parent.getParent();
                        }
                        existedTypes.add(parent.getObjectType());
                    }
                }
                repositoryView.refreshAllChildNodes(recBinNode);

                if (types != null) {
                    refresh(types);
                    existedTypes.removeAll(types);
                }
                refresh(existedTypes);

                repositoryView.refresh(recBinNode);
            }

        } else {
            getRepositoryView().refresh();
        }
    }

    /**
     * 
     * for save
     */
    public static void refreshSavedNode(RepositoryNode node) {
        if (node == null) {
            return;
        }
        if (isRefreshManually() || !isRefreshSaved()) {
            getRepositoryView().refresh(node);
        } else {
            getRepositoryView().refresh();
        }
    }

    /**
     * 
     * 
     */
    public static void refresh(ERepositoryObjectType type) {
        if (type != null) {
            getRepositoryView().refresh(type);
        }
    }

    public static void refresh(Set<ERepositoryObjectType> types) {
        IRepositoryView repositoryView = getRepositoryView();
        if (types != null) {
            for (ERepositoryObjectType type : types) {
                repositoryView.refresh(type);
            }
        }
    }
}
