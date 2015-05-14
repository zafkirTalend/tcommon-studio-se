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
package org.talend.designer.maven.ui.dialog.model;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.ui.dialog.model.nodes.RepositoryFolderCreatorNode;
import org.talend.designer.maven.ui.dialog.model.nodes.RepositoryMavenAssemblyNode;
import org.talend.designer.maven.ui.dialog.model.nodes.RepositoryMavenPomNode;
import org.talend.designer.maven.ui.dialog.model.nodes.RepositoryPreferenceNode;
import org.talend.designer.maven.ui.utils.DesignerMavenUiHelper;
import org.talend.repository.model.IRepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * the path is splitted via "." by default
 */
public class RepositoryMavenSettingManager extends PreferenceManager {

    public RepositoryMavenSettingManager() {
        super();
    }

    public void init(TreeViewer treeViewer) {
        // from tree item
        TreeItem[] items = treeViewer.getTree().getItems();
        createNodes(this.getRoot(), treeViewer, items);

    }

    private void createNodes(IPreferenceNode parentNode, TreeViewer viewer, TreeItem[] items) {
        for (TreeItem item : items) {
            Object data = item.getData();
            //
            if (data instanceof RepositoryNode) {
                RepositoryNode node = ((RepositoryNode) data);

                // FIXME, seems the standard job with job designs have same id??
                ERepositoryObjectType contentType = node.getContentType();
                ILabelProvider labelProvider = (ILabelProvider) viewer.getLabelProvider();

                ImageDescriptor imageDesc = null;
                Image image = labelProvider.getImage(node);
                if (image != null) {
                    imageDesc = ImageDescriptor.createFromImageData(image.getImageData());
                }
                String label = labelProvider.getText(node);
                // label= node.getLabel(); //there is on bug for this label, so use provider directly.

                RepositoryPreferenceNode repoSettingNode = null;
                boolean needMavenFiles = false;

                String parentId = parentNode.getId();
                String id = DesignerMavenUiHelper.buildRepositoryPreferenceNodeId(parentId, contentType.getType());
                if (!contentType.isResouce()) { // if not resource type
                    repoSettingNode = new RepositoryPreferenceNode(id, label, imageDesc, node);
                } else if (DesignerMavenUiHelper.isFakeProcessRootNode(node)) {// fake process root ndoe
                    repoSettingNode = new RepositoryPreferenceNode(id + "_fake", label, imageDesc, node); //$NON-NLS-1$
                } else if (node.getType() == ENodeType.SYSTEM_FOLDER) {
                    repoSettingNode = new RepositoryFolderCreatorNode(id, label, imageDesc, node);
                    needMavenFiles = true;
                } else { // should be other ENodeType.SIMPLE_FOLDER
                    if (parentId != null && parentId.length() > 0) {
                        id = DesignerMavenUiHelper.buildRepositoryPreferenceNodeId(parentId, label);
                    } else {
                        id = DesignerMavenUiHelper.buildRepositoryPreferenceNodeId(contentType.getType(), label);
                    }
                    repoSettingNode = new RepositoryFolderCreatorNode(id, label, imageDesc, node);

                    needMavenFiles = true;
                }
                // must be front, when add other nodes. make sure the build id is right.
                parentNode.add(repoSettingNode);

                if (needMavenFiles) {
                    IFolder nodeFolder = DesignerMavenUiHelper.getNodeFolder(node);
                    if (nodeFolder != null) {
                        // if have existed the pom and assembly
                        if (DesignerMavenUiHelper.existMavenSetting(nodeFolder)) {
                            // maven nodes
                            IFile pomFile = nodeFolder.getFile(MavenConstants.POM_FILE_NAME);
                            IFile assemblyFile = nodeFolder.getFile(MavenConstants.ASSEMBLY_FILE_NAME);

                            // RepositoryMavenScriptCategoryNode autonomousJobNode = new
                            // RepositoryMavenScriptCategoryNode(
                            // DesignerMavenUiHelper.buildRepositoryPreferenceNodeId(repoSettingNode.getId(), pomFile),
                            // EMavenScriptCategory.AutonomousJob);

                            // repoSettingNode.add(autonomousJobNode);
                            //
                            String pomId = DesignerMavenUiHelper
                                    .buildRepositoryPreferenceNodeId(repoSettingNode.getId(), pomFile);
                            String assemblyId = DesignerMavenUiHelper.buildRepositoryPreferenceNodeId(repoSettingNode.getId(),
                                    assemblyFile);

                            RepositoryMavenPomNode pomNode = new RepositoryMavenPomNode(pomId, pomFile);
                            RepositoryMavenAssemblyNode assemblyNode = new RepositoryMavenAssemblyNode(assemblyId, assemblyFile);

                            repoSettingNode.add(pomNode);
                            repoSettingNode.add(assemblyNode);
                        }
                    }
                }

                // children nodes
                createNodes(repoSettingNode, viewer, item.getItems());

            }
        }
    }

}
