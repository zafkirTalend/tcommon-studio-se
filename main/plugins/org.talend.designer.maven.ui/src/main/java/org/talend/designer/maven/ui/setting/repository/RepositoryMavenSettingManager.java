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
package org.talend.designer.maven.ui.setting.repository;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.designer.maven.ui.setting.repository.node.RepositoryPreferenceNode;
import org.talend.designer.maven.ui.setting.repository.registry.MavenSettingPagesRegistryReader;
import org.talend.designer.maven.ui.setting.repository.tester.IRepositorySettingTester;
import org.talend.designer.maven.ui.utils.DesignerMavenUiHelper;
import org.talend.repository.model.IRepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.viewer.filter.PerspectiveFilterHelper;
import org.talend.repository.viewer.ui.provider.RepoCommonViewerProvider;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * the path is splitted via "." by default
 */
public class RepositoryMavenSettingManager extends PreferenceManager {

    public final static MavenSettingPagesRegistryReader REGISTRY = new MavenSettingPagesRegistryReader();

    public RepositoryMavenSettingManager() {
        super();
    }

    public void init(TreeViewer treeViewer) {
        // from tree item
        TreeItem[] items = treeViewer.getTree().getItems();
        createNodes(this.getRoot(), (ILabelProvider) treeViewer.getLabelProvider(), items);

    }

    public void createNodes(IPreferenceNode parentNode, ILabelProvider labelProvider, TreeItem[] items) {
        for (TreeItem item : items) {
            RepositoryPreferenceNode repoSettingNode = createNode(parentNode, item.getData(), labelProvider);
            if (repoSettingNode != null) {
                // children nodes
                createNodes(repoSettingNode, labelProvider, item.getItems());
            }
        }
    }

    protected RepositoryPreferenceNode createNode(IPreferenceNode parentNode, Object data, ILabelProvider labelProvider) {
        RepositoryPreferenceNode repoSettingNode = null;
        if (data instanceof RepositoryNode) {
            RepositoryNode node = ((RepositoryNode) data);

            ImageDescriptor imageDesc = null;
            Image image = labelProvider.getImage(node);
            if (image != null) {
                imageDesc = ImageDescriptor.createFromImageData(image.getImageData());
            }
            String label = labelProvider.getText(node);
            // label= node.getLabel(); //there is on bug for this label, so use provider directly.

            ERepositoryObjectType contentType = node.getContentType();
            // mabye it's not good for some sub nodes, for example, if for routines, supported type must containd
            // CODE.
            List<ERepositoryObjectType> supportedTypes = Arrays.asList(REGISTRY.getSupportTypes());
            if (!supportedTypes.contains(contentType)) {
                return null;
            }

            boolean needMavenFiles = false;

            String parentId = parentNode.getId();
            String id = DesignerMavenUiHelper.buildRepositoryPreferenceNodeId(parentId, contentType.getType());
            if (!contentType.isResouce()) { // if not resource type
                repoSettingNode = new RepositoryPreferenceNode(id, label, imageDesc, node);
            } else if (DesignerMavenUiHelper.isFakeProcessRootNode(node)) {// fake process root ndoe
                repoSettingNode = new RepositoryPreferenceNode(id + "_fake", label, imageDesc, node); //$NON-NLS-1$
            } else if (node.getType() == ENodeType.SYSTEM_FOLDER) {
                repoSettingNode = new RepositoryPreferenceNode(id, label, imageDesc, node);
                needMavenFiles = true;
            } else { // should be other ENodeType.SIMPLE_FOLDER
                if (parentId != null && parentId.length() > 0) {
                    id = DesignerMavenUiHelper.buildRepositoryPreferenceNodeId(parentId, label);
                } else {
                    id = DesignerMavenUiHelper.buildRepositoryPreferenceNodeId(contentType.getType(), label);
                }
                repoSettingNode = new RepositoryPreferenceNode(id, label, imageDesc, node);

                needMavenFiles = true;
            }
            // must be front, when add other nodes. make sure the build id is right.
            parentNode.add(repoSettingNode);

            if (needMavenFiles) {
                IFolder nodeFolder = DesignerMavenUiHelper.getNodeFolder(node);
                if (nodeFolder != null) {
                    RepositoryMavenSetting[] settings = REGISTRY.getSettings();
                    for (RepositoryMavenSetting setting : settings) {
                        IRepositorySettingTester tester = setting.getTester();
                        if (tester != null && !tester.valid(node)) {
                            continue; // if tester set, and valid
                        }
                        //
                        setting.create(repoSettingNode, node);
                    }
                }
            }
        }
        return repoSettingNode;
    }

    public void init(String viewId) {
        if (viewId == null) {
            return;
        }

        Composite parent = null;
        try {
            // create on fake shell for parent of viewer
            parent = new Shell();

            RepositoryContextTypesFilter filter = new RepositoryContextTypesFilter(
                    Arrays.asList(RepositoryMavenSettingManager.REGISTRY.getSupportTypes()));

            RepoCommonViewerProvider provider = new RepoCommonViewerProvider() {

                protected void doPerspecitiveFilterForCommonViewer(TreeViewer treeViewer, String specifiedPerspectiveId) {
                    // don't filter for perspective, so the active false first.
                    boolean oldValue = PerspectiveFilterHelper.isActivedPerspectiveFilter();
                    PerspectiveFilterHelper.setActivedPerspectiveFilter(false);
                    try {
                        // call do filter to make sure it disable the perspective filter.
                        super.doPerspecitiveFilterForCommonViewer(treeViewer, specifiedPerspectiveId);
                    } finally {
                        PerspectiveFilterHelper.setActivedPerspectiveFilter(oldValue);
                    }
                }
            };
            provider.setViewId(viewId);
            TreeViewer fakeTreeViewer = provider.createViewer(parent);
            fakeTreeViewer.addFilter(filter);
            // FIXME, maybe should enhance the performance more here to init the nodes, when expand the preference
            // node.
            fakeTreeViewer.expandAll(); // in order to load all nodes
            init(fakeTreeViewer);
        } finally {
            if (parent != null) {
                parent.dispose();
            }
        }
    }

}
