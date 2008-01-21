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
package org.talend.repository.documentation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.Project;
import org.talend.core.model.genhtml.IHTMLDocConstants;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.repository.documentation.generation.DocumentationPathProvider;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryConstants;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode.EProperties;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC ftang class global comment. Detailled comment <br/>
 * 
 */
public class DocumentationHelper {

    /**
     * ftang Comment method "isSyncWithDocumentation".
     * 
     * @return
     */
    public static boolean isSyncWithDocumentation() {
        boolean isSync = CorePlugin.getDefault().getPreferenceStore().getBoolean(ITalendCorePrefConstants.DOC_GENERATION);
        return isSync;
    }

    /**
     * ftang Comment method "isFolderExisting".
     * 
     * @param type
     * @param path
     * @param folderName
     * @return
     */
    public static boolean isPathValid(ERepositoryObjectType type, IPath path, String folderName) {
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        try {
            return repositoryFactory.isPathValid(type, path, folderName);
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
            return false;
        }
    }

    /**
     * ftang Comment method "getExportFileResources".
     * 
     * @param nodes
     * @return
     */
    public static ExportFileResource[] getExportFileResources(RepositoryNode node) {

        List<ExportFileResource> list = new ArrayList<ExportFileResource>();
        if (node.getType() == ENodeType.SYSTEM_FOLDER || node.getType() == ENodeType.SIMPLE_FOLDER) {
            String folderName = "";
            if (!node.getProperties(EProperties.LABEL).toString().equals(ERepositoryObjectType.PROCESS.toString())
                    && !node.getProperties(EProperties.LABEL).toString().equals(ERepositoryObjectType.JOBLET.toString())) {
                folderName = node.getProperties(EProperties.LABEL).toString();
            }
            addTreeNode(node, folderName, list);
        }
        if (node.getType() == ENodeType.REPOSITORY_ELEMENT) {
            IRepositoryObject repositoryObject = node.getObject();
            if ((repositoryObject.getProperty().getItem() instanceof Item)) {
                Item processItem = (Item) repositoryObject.getProperty().getItem();
                ExportFileResource resource = new ExportFileResource(processItem, processItem.getProperty().getLabel());
                list.add(resource);
            }
        }

        ExportFileResource[] resourceArray = list.toArray(new ExportFileResource[list.size()]);
        return resourceArray;
    }

    /**
     * DOC Administrator Comment method "addTreeNode".
     * 
     * @param node
     * @param path
     * @param list
     */
    private static void addTreeNode(RepositoryNode node, String path, List<ExportFileResource> list) {
        if (node != null && node.getType() == ENodeType.REPOSITORY_ELEMENT) {
            IRepositoryObject repositoryObject = node.getObject();
            if (repositoryObject.getProperty().getItem() instanceof Item) {
                Item processItem = (Item) repositoryObject.getProperty().getItem();
                ExportFileResource resource = new ExportFileResource(processItem, path);
                list.add(resource);
            }
        }
        Object[] nodes = node.getChildren().toArray();
        if (nodes.length <= 0) {
            return;
        }
        for (int i = 0; i < nodes.length; i++) {

            String label = ((RepositoryNode) nodes[i]).getProperties(EProperties.LABEL).toString();
            String version = "";
            IRepositoryObject object = ((RepositoryNode) nodes[i]).getObject();
            if (((RepositoryNode) nodes[i]).getType() != ENodeType.SIMPLE_FOLDER && object != null) {
                version = object.getProperty().getVersion();
            }

            if (version.equals("")) {
                addTreeNode((RepositoryNode) nodes[i], path + "/" //$NON-NLS-1$
                        + label, list);
            } else {
                addTreeNode((RepositoryNode) nodes[i], path + "/" //$NON-NLS-1$
                        + label + "_" + version, list);
            }
        }
    }

    /**
     * ftang Comment method "getHTMLFilePath".
     * 
     * @param currentNode
     * @return
     */
    public static File getHTMLFilePath(RepositoryNode currentNode, String docRootPath) {
        String jobNodeDocRootPath = getJobNodeDocumentationRoot(docRootPath);
        if (currentNode.getObject() != null) {
            String jobName = currentNode.getObject().getProperty().getLabel();

            // Gets the related path of current node
            String currentJobPath = currentNode.getObject().getProperty().getItem().getState().getPath();
            currentJobPath = currentJobPath == null ? "" : currentJobPath;

            jobNodeDocRootPath = jobNodeDocRootPath + IPath.SEPARATOR + currentJobPath + IPath.SEPARATOR + jobName;
        }

        java.io.File folder = new File(jobNodeDocRootPath);
        return folder;
    }

    /**
     * ftang Comment method "getJobNodeDocumentationRoot".
     * 
     * @return
     */
    public static String getJobNodeDocumentationRoot(String docRootPath) {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getTechnicalLabel());
        java.io.File file = project.getLocation().toFile();
        String jobNodeDocRootPath = file.toString() + IPath.SEPARATOR + docRootPath;
        return jobNodeDocRootPath;
    }

    /**
     * ftang Comment method "getJobletNodeDocumentationRoot".
     * 
     * @return
     */
    public static String getJobletNodeDocumentationRoot() {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getTechnicalLabel());
        java.io.File file = project.getLocation().toFile();
        String jobletNodeDocRootPath = file.toString() + IPath.SEPARATOR + IHTMLDocConstants.JOBLET_NODE_DOCUMENTATION_ROOT_PATH;
        return jobletNodeDocRootPath;
    }

    /**
     * Get the current project.
     * 
     * @return an instance of <code>Project</code>
     */
    public static Project getProject() {
        return ((org.talend.core.context.RepositoryContext) CorePlugin.getContext().getProperty(
                org.talend.core.context.Context.REPOSITORY_CONTEXT_KEY)).getProject();
    }

    /**
     * Gets the documentation node which in the Recycle bin for current job node.
     * 
     * @param sourceNode
     * @return
     */
    public static RepositoryNode getDocumentationNodeInRecycleBin(RepositoryNode sourceNode) {
        String documentationNodeName = sourceNode.getObject().getProperty().getLabel() + "_"
                + sourceNode.getObject().getProperty().getVersion();
        IRepositoryView repositoryView = (IRepositoryView) (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView(IRepositoryView.VIEW_ID));
        RepositoryNode root = repositoryView.getRoot();
        for (RepositoryNode node : root.getChildren()) {
            if (node == null) {
                continue;
            }
            if (node.isBin()) {
                for (RepositoryNode subNode : node.getChildren()) {
                    String nodeName = subNode.getObject().getProperty().getLabel() + "_"
                            + subNode.getObject().getProperty().getVersion();
                    if (nodeName.equals(documentationNodeName)) {
                        return subNode;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Get the documentation node for the job node.
     * 
     * @param selectedJobNode
     * @return
     */
    public static RepositoryNode getCurrentDocumentationNode(final RepositoryNode selectedJobNode) {
        IRepositoryView viewPart = getViewPart();
        RepositoryNode root = viewPart.getRoot();

        RepositoryNode documentationNode = null;
        for (RepositoryNode node : root.getChildren()) {
            if (node.getContentType() == ERepositoryObjectType.DOCUMENTATION) {
                documentationNode = node;
                break;
            }
        }

        if (documentationNode == null) {
            return null;
        }

        for (RepositoryNode node : documentationNode.getChildren()) {
            // Goto the Node "Generated";
            if (node.getContentType() != ERepositoryObjectType.GENERATED) {
                continue;
            }

            // Goto the Node "Jobs";
            for (RepositoryNode subNode : node.getChildren()) {
                if (subNode.getContentType() != ERepositoryObjectType.JOBS) {
                    continue;
                }

                for (RepositoryNode grandChildNode : subNode.getChildren()) {

                    IRepositoryObject object = selectedJobNode.getObject();
                    String path = "";
                    if (object != null) {
                        path = object.getProperty().getItem().getState().getPath();
                    }

                    // Under the first layer of root node
                    if (path.equals("")) {
                        String label = grandChildNode.getObject().getProperty().getLabel();
                        String version = grandChildNode.getObject().getProperty().getVersion();
                        if (label.equals(object.getProperty().getLabel()) && version.equals(object.getProperty().getVersion())) {
                            return grandChildNode;
                        }
                    } else { // a/b/c
                        String[] pathArray = path.split("/");
                        int layerCount = pathArray.length;
                        for (RepositoryNode repositoryNode : grandChildNode.getChildren()) {

                        }

                    }
                }
            }
        }

        return null;
    }

    /**
     * 
     * Returns the repository view..
     * 
     * @return - the repository biew
     */
    public static IRepositoryView getViewPart() {
        IViewPart viewPart = (IViewPart) (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage())
                .findView(IRepositoryView.VIEW_ID);
        return (IRepositoryView) viewPart;
    }

    /**
     * ftang Comment method "deleteDocumentationFileAndNode".
     * 
     * @param docFolder
     */
    public static void deleteDocumentationFiles(File docFolder) {
        // Delete all files;
        deleteFiles(docFolder);

        // Delete all folders;
        deleteFolders(docFolder);

        // delete the root folder of job
        docFolder.delete();
    }

    /**
     * ftang Comment method "deleteFiles".
     * 
     * @param docFolder
     */
    private static void deleteFiles(File docFolder) {
        if (docFolder.listFiles() == null || docFolder.listFiles().length == 0) {
            docFolder.delete();
        } else {
            for (File file : docFolder.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    deleteFiles(file);
                }
            }
        }

    }

    /**
     * ftang Comment method "deleteFolders".
     * 
     * @param docFolder
     */
    private static void deleteFolders(File docFolder) {
        if (docFolder.listFiles() == null || docFolder.listFiles().length == 0) {
            docFolder.delete();
        } else {
            for (File file : docFolder.listFiles()) {
                if (file.listFiles() != null && file.listFiles().length > 0) {
                    deleteFolders(file);
                } else {
                    file.delete();
                }
            }
        }
    }

    /**
     * Deletes preview pictures of job.
     * 
     * @param jobName
     * @param versionList
     */
    public static void deletePreviewPictures(String jobName, List<String> versionList) {

        if (versionList == null || versionList.size() == 0) {
            return;
        }

        String picName = "";
        for (String version : versionList) {
            picName = jobName + "_" + version + IHTMLDocConstants.JOB_PREVIEW_PIC_SUFFIX;
            IPath filePath = DocumentationPathProvider.getPathFileName(RepositoryConstants.IMG_DIRECTORY_OF_JOB_OUTLINE, picName);
            if (filePath == null) {
                return;
            }

            String filePathStr = filePath.toOSString();
            File file = new File(filePathStr);
            if (file.exists()) {
                boolean delete = file.delete();
                System.out.println(delete);
            }
        }
    }

}
