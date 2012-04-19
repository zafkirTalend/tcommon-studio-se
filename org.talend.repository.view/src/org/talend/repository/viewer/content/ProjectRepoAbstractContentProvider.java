// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.content;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.image.ECoreImage;
import org.talend.commons.utils.data.container.Container;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.process.Problem;
import org.talend.core.model.process.Problem.ProblemStatus;
import org.talend.core.model.properties.Information;
import org.talend.core.model.properties.InformationLevel;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.Folder;
import org.talend.core.model.repository.IRepositoryPrefConstants;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.IRepositoryNode.ENodeType;
import org.talend.repository.model.IRepositoryNode.EProperties;
import org.talend.repository.model.ProjectRepositoryNode;
import org.talend.repository.model.RepositoryConstants;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.StableRepositoryNode;

/**
 * this handle content that root node is of type ProjectRepositoryNode
 * */
public abstract class ProjectRepoAbstractContentProvider extends FolderListenerSingleTopContentProvider {

    private AdapterImpl deleteFolderListener;

    private Project project;

    private final class DeletedFolderListener extends AdapterImpl {

        @Override
        public void notifyChanged(Notification notification) {
            if (notification.getFeature() == PropertiesPackage.eINSTANCE.getProject_DeletedFolders()) {
                if (viewer != null && viewer.getControl() != null) {
                    viewer.getControl().getDisplay().asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            refreshTopLevelNode();

                        }
                    });
                }// else the viewer as not control so not displayed
            }// else no a change in deleted Folders

        }
    }

    /*
     * only called with a ProjectRepoNode as a parent. register a listener to the current project to be notified of the
     * deleted folders
     * 
     * @see
     * org.talend.repository.example.viewer.content.LegacyRepositoryContentProvider#getRootNode(org.talend.repository
     * .model.ProjectRepositoryNode)
     */
    @Override
    protected RepositoryNode getInitialTopLevelNode(RepositoryNode element) {
        // this is called only if element is of type ProjectRepositoryNode
        ProjectRepositoryNode projRepo = getProjectRepositoryNode(element);
        setupDeleteFolderListener(projRepo);
        return getTopLevelNodeFromProjectRepositoryNode(projRepo);
    }

    /**
     * DOC sgandon Comment method "setupDeleteFolderListener".
     * 
     * @param projRepo
     */
    protected void setupDeleteFolderListener(ProjectRepositoryNode projRepo) {

        if (projRepo.getProject() != null) {
            // add a lister for the removed folders.
            if (deleteFolderListener == null) {
                project = projRepo.getProject().getEmfProject();
                deleteFolderListener = new DeletedFolderListener();
                project.eAdapters().add(deleteFolderListener);
            }// else listener already attached
        }// no project set so no need to listen

    }

    /**
     * return the project repository from the element, never null
     * 
     * @param element
     */
    abstract protected ProjectRepositoryNode getProjectRepositoryNode(RepositoryNode element);

    /**
     * DOC sgandon Comment method "getTopLevelNodeFromProjectRepositoryNode".
     * 
     * @param element
     * @return
     */
    abstract protected RepositoryNode getTopLevelNodeFromProjectRepositoryNode(ProjectRepositoryNode projectNode);

    // /**
    // *
    // * DOC ggu Comment method "getTopLevelNodeType".
    // *
    // * @return
    // */
    // abstract protected ERepositoryObjectType getTopLevelNodeType();

    @Override
    protected boolean isRootNodeType(Object element) {
        return (element instanceof ProjectRepositoryNode)
                || Platform.getAdapterManager().getAdapter(element, ProjectRepositoryNode.class) != null;
    }

    @Override
    protected RepositoryNode extractPotentialRootNode(Object element) {
        RepositoryNode potentialRootNode = null;
        if (element instanceof ProjectRepositoryNode) {
            potentialRootNode = (ProjectRepositoryNode) element;
        } else {
            potentialRootNode = (ProjectRepositoryNode) Platform.getAdapterManager().getAdapter(element,
                    ProjectRepositoryNode.class);
        }
        return potentialRootNode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.viewer.content.FolderListenerSingleTopContentProvider#dispose()
     */
    @Override
    public void dispose() {
        if (deleteFolderListener != null) {
            project.eAdapters().remove(deleteFolderListener);
        }// else nothing to remove
        super.dispose();
    }

    protected boolean isMergeRefProject() {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IDesignerCoreService.class)) {
            IDesignerCoreService service = (IDesignerCoreService) GlobalServiceRegister.getDefault().getService(
                    IDesignerCoreService.class);
            IPreferenceStore preferenceStore = service.getDesignerCorePreferenceStore();
            return preferenceStore.getBoolean(IRepositoryPrefConstants.MERGE_REFERENCE_PROJECT);
        }
        return false;

    }

    // @Override
    // protected Object[] getRepositoryNodeChildren(RepositoryNode repositoryNode) {
    // if (!repositoryNode.isInitialized()) {
    // if (repositoryNode.getParent() instanceof ProjectRepositoryNode) {
    // // initialize repository from main project
    // try {
    // Project currentProject = ProjectManager.getInstance().getCurrentProject();
    //
    // RootContainer<String, IRepositoryViewObject> jobsContainer = getFactory().getMetadata(currentProject,
    // getTopLevelNodeType(), true);
    //
    // convert(currentProject, jobsContainer, repositoryNode, getTopLevelNodeType(), (RepositoryNode) repositoryNode
    // .getRoot().getRecBinNode());
    // } catch (PersistenceException e) {
    // ExceptionHandler.process(e);
    // }
    // } // else sub sub node so no need to initialize
    // repositoryNode.setInitialized(true);
    // } // else already initialised
    // return repositoryNode.getChildren().toArray();
    // }

    protected void convertChildren(org.talend.core.model.general.Project newProject, Container fromModel, RepositoryNode parent,
            ERepositoryObjectType type, RepositoryNode recBinNode) {

        if (parent == null || fromModel == null) {
            return;
        }

        processFolders(newProject, fromModel, parent, type, recBinNode);

        processItems(fromModel, parent, type, recBinNode);
    }

    private void processFolders(org.talend.core.model.general.Project newProject, Container fromModel, RepositoryNode parent,
            ERepositoryObjectType type, RepositoryNode recBinNode) {
        for (Object obj : fromModel.getSubContainer()) {
            Container container = (Container) obj;
            Folder oFolder = new Folder((Property) container.getProperty(), type);
            if (oFolder.getProperty() == null) {
                continue;
            }

            String pathLabel = container.getLabel();

            if (ignoreFolders(newProject, container, parent, type, recBinNode)) {
                continue;
            } else {
                RepositoryNode folder = null;
                if (isMergeRefProject()) {
                    if (newProject != parent.getRoot().getProject() && !hasTalendItems(container)) {
                        continue;
                    }
                    folder = new RepositoryNode(oFolder, parent, ENodeType.SIMPLE_FOLDER);
                    if (getFactory().getStatus(oFolder) != ERepositoryStatus.DELETED) {
                        parent.getChildren().add(folder);
                    }
                } else {
                    folder = new RepositoryNode(oFolder, parent, ENodeType.SIMPLE_FOLDER);
                    if (getFactory().getStatus(oFolder) != ERepositoryStatus.DELETED) {
                        parent.getChildren().add(folder);
                    }
                }
                if (folder != null) {
                    folder.setProperties(EProperties.LABEL, pathLabel);
                    folder.setProperties(EProperties.CONTENT_TYPE, type);
                    convertChildren(newProject, container, folder, type, recBinNode);
                }
            }
        }
    }

    protected boolean ignoreFolders(org.talend.core.model.general.Project newProject, Container container, RepositoryNode parent,
            ERepositoryObjectType type, RepositoryNode recBinNode) {
        String pathLabel = container.getLabel();
        if (pathLabel.equals("bin") || pathLabel.startsWith(".")) { //$NON-NLS-1$ //$NON-NLS-2$
            return true;
        } else
        // for system folder
        if (RepositoryConstants.SYSTEM_DIRECTORY.equals(container.getLabel())) {
            if (!processSystemFolder(newProject, container, parent, type, recBinNode)) {
                return true;
            }
        }
        return false;
    }

    protected boolean processSystemFolder(org.talend.core.model.general.Project newProject, Container container,
            RepositoryNode parent, ERepositoryObjectType type, RepositoryNode recBinNode) {
        RepositoryNode folder = null;
        String pathLabel = container.getLabel();
        if (isMergeRefProject()) {
            boolean existSystemFolder = false;
            for (IRepositoryNode node : parent.getChildren()) {
                if (RepositoryConstants.SYSTEM_DIRECTORY.equalsIgnoreCase(node.getLabel())) {
                    existSystemFolder = true;
                    break;
                }
            }
            IBrandingService breaningService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                    IBrandingService.class);

            if (!existSystemFolder && !breaningService.isPoweredOnlyCamel()) {
                folder = new StableRepositoryNode((RepositoryNode) parent, RepositoryConstants.SYSTEM_DIRECTORY,
                        ECoreImage.FOLDER_CLOSE_ICON);
                parent.getChildren().add(folder);
            } else {
                return false;
            }
        } else {
            folder = new StableRepositoryNode((RepositoryNode) parent, RepositoryConstants.SYSTEM_DIRECTORY,
                    ECoreImage.FOLDER_CLOSE_ICON);
            parent.getChildren().add(folder);
        }
        if (folder != null) {
            folder.setProperties(EProperties.LABEL, pathLabel);
            folder.setProperties(EProperties.CONTENT_TYPE, type);
            convertChildren(newProject, container, folder, type, recBinNode);
        }
        return true;
    }

    protected void processItems(Container fromModel, RepositoryNode parent, ERepositoryObjectType type, RepositoryNode recBinNode) {
        // not folder or folders have no subFolder
        for (Object obj : fromModel.getMembers()) {
            IRepositoryViewObject repositoryObject = (IRepositoryViewObject) obj;
            try {
                if (!repositoryObject.isDeleted()) {
                    addElementNode(parent, type, recBinNode, repositoryObject);
                }
            } catch (Exception e) {
                ExceptionHandler.log("Item not valid: [{0}] {1}]" + repositoryObject.getRepositoryObjectType()
                        + repositoryObject.getLabel());
                //                ExceptionHandler.log(Messages.getString("ProjectRepositoryNode.itemInvalid",//$NON-NLS-1$
                // repositoryObject.getRepositoryObjectType(), repositoryObject.getLabel()));

                if (repositoryObject.getProperty().getInformations().isEmpty()) {
                    Information info = PropertiesFactory.eINSTANCE.createInformation();
                    info.setLevel(InformationLevel.ERROR_LITERAL);
                    info.setText("Invalid item");
                    Property property = repositoryObject.getProperty();
                    property.getInformations().add(info);
                    try {
                        getFactory().save(parent.getRoot().getProject(), property);
                    } catch (PersistenceException e1) {
                        ExceptionHandler.process(e1);
                    }
                    repositoryObject.getProperty(); // call getProperty to update since it's a RepositoryViewObject.
                }
            }
        }
    }

    private boolean hasTalendItems(Container container) {
        if (!container.getMembers().isEmpty()) {
            return true;
        }
        for (Object obj : container.getSubContainer()) {
            Container subContainer = (Container) obj;
            boolean hasTalendItems = hasTalendItems(subContainer);
            if (!hasTalendItems) {
                continue;
            }
            return hasTalendItems;
        }

        return false;

    }

    protected void addElementNode(RepositoryNode parent, ERepositoryObjectType type, RepositoryNode recBinNode,
            IRepositoryViewObject repositoryObject) {

        RepositoryNode node = new RepositoryNode(repositoryObject, parent, ENodeType.REPOSITORY_ELEMENT);

        node.setProperties(EProperties.CONTENT_TYPE, type);
        node.setProperties(EProperties.LABEL, repositoryObject.getLabel());

        if ((repositoryObject.isDeleted() && parent.getObject() == null)
                || (repositoryObject.isDeleted() && !parent.getObject().isDeleted())) {
            // recBinNode.getChildren().add(node);
            // node.setParent(recBinNode);
        } else {
            if (GlobalServiceRegister.getDefault().isServiceRegistered(IDesignerCoreService.class)) {
                IDesignerCoreService designerCoreService = (IDesignerCoreService) GlobalServiceRegister.getDefault().getService(
                        IDesignerCoreService.class);
                if (designerCoreService != null) {
                    for (IRepositoryNode repositoryNode : parent.getChildren()) {
                        if (repositoryNode.getObject() != null) {
                            if (repositoryNode.getObject().getId().equals(repositoryObject.getId())) {
                                Problem problem = new Problem();
                                problem.setDescription(type.name() + " " + repositoryNode.getObject().getLabel() + " " //$NON-NLS-1$ //$NON-NLS-2$
                                        + repositoryNode.getObject().getVersion() + " and " + repositoryObject.getLabel() + " " //$NON-NLS-1$ //$NON-NLS-2$
                                        + repositoryObject.getVersion() + " have the same ID."); //$NON-NLS-1$
                                problem.setStatus(ProblemStatus.WARNING);
                                designerCoreService.addProblems(problem);
                            }
                        }
                    }
                }
            }
            parent.getChildren().add(node);
        }
        addElementChildrenNodes(node, type, recBinNode, repositoryObject);
    }

    protected void addElementChildrenNodes(RepositoryNode parent, ERepositoryObjectType type, RepositoryNode recBinNode,
            IRepositoryViewObject repositoryObject) {

    }
}
