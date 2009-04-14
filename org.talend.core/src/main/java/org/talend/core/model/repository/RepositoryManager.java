// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.SystemException;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.model.properties.Property;
import org.talend.core.ui.IJobletProviderService;
import org.talend.designer.codegen.ICodeGeneratorService;
import org.talend.repository.editor.RepositoryEditorInput;
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
        if (CommonsPlugin.isHeadless()) {
            return null;
        }

        IViewPart part = null;
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null) {
            IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
            if (activePage != null) {
                part = activePage.findView(IRepositoryView.VIEW_ID);

                if (part == null) {
                    try {
                        part = activePage.showView(IRepositoryView.VIEW_ID);
                    } catch (Exception e) {
                        ExceptionHandler.process(e);
                    }
                }
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
            IRepositoryView repositoryView = getRepositoryView();
            if (repositoryView != null) {
                repositoryView.refresh();
            }
        }
        // qli modified to fix the bug 6659.
        if (type != null) {
            syncRoutineAndJoblet(type);
        }
    }

    public static void refreshCreatedNode(IProjectRepositoryNode projectNode, ERepositoryObjectType type) {
        IRepositoryView repositoryView = getRepositoryView();
        if (repositoryView != null) {
            if ((isRefreshManually() || !isRefreshCreated()) && !type.isSubItem()) {
                if (projectNode != null) {
                    RepositoryNode rootNode = projectNode.getRootRepositoryNode(type);
                    repositoryView.refreshAllChildNodes(rootNode);
                } else {
                    // main project
                    refresh(type);
                }
            } else {
                repositoryView.refresh();
            }
            // qli modified to fix the bug 6659.
            if (type != null) {
                syncRoutineAndJoblet(type);
            }
        }

    }

    /**
     * 
     * for delete
     */
    public static void refreshDeletedNode(Set<ERepositoryObjectType> types) {
        IRepositoryView repositoryView = getRepositoryView();
        if (repositoryView != null) {
            if (isRefreshManually() || !isRefreshDeleted()) {

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
                repositoryView.refresh();
            }
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
        IRepositoryView repositoryView = getRepositoryView();
        if (repositoryView != null) {
            if (isRefreshManually() || !isRefreshSaved()) {
                repositoryView.refresh(node);
            } else {
                repositoryView.refresh();
            }
        }
    }

    /**
     * 
     * 
     */
    public static void refresh(ERepositoryObjectType type) {
        if (type != null) {
            IRepositoryView repositoryView = getRepositoryView();
            if (repositoryView != null) {
                repositoryView.refresh(type);
            }
        }
    }

    public static void refresh(Set<ERepositoryObjectType> types) {
        IRepositoryView repositoryView = getRepositoryView();
        if (types != null && repositoryView != null) {
            for (ERepositoryObjectType type : types) {
                repositoryView.refresh(type);
            }
        }
    }

    /**
     * 
     * qli Comment method "syncRoutineAndJoblet".
     * 
     * synchronize the routines and the joblets created by other users.
     */
    public static void syncRoutineAndJoblet(ERepositoryObjectType type) {
        if (type.equals(ERepositoryObjectType.ROUTINES)) {
            ICodeGeneratorService codeGenService = (ICodeGeneratorService) GlobalServiceRegister.getDefault().getService(
                    ICodeGeneratorService.class);
            try {
                codeGenService.createRoutineSynchronizer().syncAllRoutines();
            } catch (SystemException e) {
                ExceptionHandler.process(e);
            }
        } else if (type.equals(ERepositoryObjectType.JOBLET)) {
            if (PluginChecker.isJobLetPluginLoaded()) {
                IJobletProviderService jobletService = (IJobletProviderService) GlobalServiceRegister.getDefault().getService(
                        IJobletProviderService.class);
                if (jobletService != null) {
                    jobletService.loadComponentsFromProviders();
                }
            }
        }
    }

    /**
     * 
     * ggu Comment method "isOpenedItemInEditor".
     * 
     * for jobs/joblets/business diagrams/routines/sql patterns
     */
    public static boolean isOpenedItemInEditor(IRepositoryObject objectToMove) {
        try {
            if (objectToMove != null) {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null) {
                    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                    if (activePage != null) {
                        IEditorReference[] editorReferences = activePage.getEditorReferences();
                        if (editorReferences != null) {
                            Property property = objectToMove.getProperty().getItem().getProperty();
                            //
                            for (IEditorReference editorReference : editorReferences) {
                                IEditorInput editorInput = editorReference.getEditorInput();
                                if (editorInput != null && editorInput instanceof RepositoryEditorInput) {
                                    RepositoryEditorInput rInput = (RepositoryEditorInput) editorInput;
                                    Property openedProperty = rInput.getItem().getProperty();
                                    if (openedProperty.getId().equals(property.getId())
                                            && VersionUtils.compareTo(openedProperty.getVersion(), property.getVersion()) == 0) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (PartInitException e) {
            ExceptionHandler.process(e);
        }
        return false;
    }

    /**
     * 
     * ggu Comment method "isEditableItemInEditor".
     * 
     * it's editable also.
     * 
     * for jobs/joblets/business diagrams/routines/sql patterns
     */
    public static boolean isEditableItemInEditor(IRepositoryObject objectToMove) {
        try {
            if (objectToMove != null) {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null) {
                    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                    if (activePage != null) {
                        IEditorReference[] editorReferences = activePage.getEditorReferences();
                        if (editorReferences != null) {
                            Property property = objectToMove.getProperty().getItem().getProperty();

                            for (IEditorReference editorReference : editorReferences) {
                                IEditorInput editorInput = editorReference.getEditorInput();
                                if (editorInput != null) {
                                    RepositoryEditorInput rInput = null;
                                    // for business/routine/sql pattern
                                    IPersistableElement persistableElement = editorInput.getPersistable();
                                    if (persistableElement != null && persistableElement instanceof RepositoryEditorInput) {
                                        rInput = (RepositoryEditorInput) persistableElement;
                                    }
                                    // for job/joblet/routine/sql pattern
                                    if (editorInput instanceof RepositoryEditorInput) {
                                        rInput = (RepositoryEditorInput) editorInput;
                                    }
                                    if (rInput != null) {
                                        Property openedProperty = rInput.getItem().getProperty();
                                        if (openedProperty.getId().equals(property.getId())
                                                && VersionUtils.compareTo(openedProperty.getVersion(), property.getVersion()) == 0
                                                && !rInput.isReadOnly()) {

                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (PartInitException e) {
            ExceptionHandler.process(e);
        }
        return false;
    }
}
