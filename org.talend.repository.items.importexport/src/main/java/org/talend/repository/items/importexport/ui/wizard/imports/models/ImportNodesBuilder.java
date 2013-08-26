// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.ui.wizard.imports.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Project;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ImportNodesBuilder {

    /**
     * technicalLabel name with project nodes.
     */
    private Map<String, ProjectImportNode> projectNodesMap = new HashMap<String, ProjectImportNode>();

    private List<ItemRecord> allImportItemRecords = new ArrayList<ItemRecord>();

    public List<ProjectImportNode> getProjectNodes() {
        List<ProjectImportNode> list = new ArrayList(this.projectNodesMap.values());
        // sort by the project name.
        Collections.sort(list);
        return list;
    }

    public ItemRecord[] getAllImportItemRecords() {
        return this.allImportItemRecords.toArray(new ItemRecord[0]);
    }

    public void clear() {
        this.allImportItemRecords.clear();
        this.projectNodesMap.clear();
    }

    public void addItems(List<ItemRecord> items) {
        if (items != null) {
            for (ItemRecord ir : items) {
                addItem(ir);
            }
        }
    }

    public void addItem(ItemRecord itemRecord) {
        if (itemRecord != null) {
            this.allImportItemRecords.add(itemRecord);

            final Project project = itemRecord.getItemProject();
            if (project == null) {
                return; // must have project
            }
            final String technicalLabel = project.getTechnicalLabel();

            ProjectImportNode projectImportNode = this.projectNodesMap.get(technicalLabel);
            if (projectImportNode == null) {
                projectImportNode = new ProjectImportNode(project);
                this.projectNodesMap.put(technicalLabel, projectImportNode);
            }
            final Item item = itemRecord.getItem();

            final ERepositoryObjectType itemType = ERepositoryObjectType.getItemType(item);

            // set for type
            TypeImportNode typeImportNode = findAndCreateParentTypeNode(projectImportNode, itemType);

            //
            String path = item.getState().getPath();
            FolderImportNode folderImportNode = typeImportNode; // by default, in under type node.
            if (StringUtils.isNotEmpty(path)) { // if has path, will find the real path node.
                folderImportNode = findAndCreateFolderNode(typeImportNode, new Path(path));
            }
            ItemImportNode itemNode = new ItemImportNode(itemRecord);
            folderImportNode.addChild(itemNode);
        }
    }

    private FolderImportNode findAndCreateFolderNode(FolderImportNode parentNode, IPath path) {
        if (path.segmentCount() > 0) {
            String first = path.segment(0);
            FolderImportNode subFolderImportNode = parentNode.getSubFolders().get(first);
            if (subFolderImportNode == null) {
                subFolderImportNode = new FolderImportNode(first);
                parentNode.addChild(subFolderImportNode);
            }
            return findAndCreateFolderNode(subFolderImportNode, path.removeFirstSegments(1));
        } else { // the last one
            return parentNode;
        }
    }

    private TypeImportNode findAndCreateParentTypeNode(ProjectImportNode projectNode, ERepositoryObjectType curType) {

        ERepositoryObjectType parentParentType = ERepositoryObjectType.findParentType(curType);
        if (parentParentType == null) { // is root type, try to find from project node
            TypeImportNode typeImportNode = findAndCreateTypeNode(projectNode, curType, true);
            return typeImportNode;
        }

        // try to find parent parent node from project node
        TypeImportNode findParentParentTypeNode = findAndCreateTypeNode(projectNode, parentParentType, false);
        if (findParentParentTypeNode == null) {
            findParentParentTypeNode = findAndCreateParentTypeNode(projectNode, parentParentType);
        }
        TypeImportNode typeImportNode = findAndCreateTypeNode(findParentParentTypeNode, curType, true);
        return typeImportNode;

    }

    private TypeImportNode findAndCreateTypeNode(ImportNode parentNode, ERepositoryObjectType curType, boolean creatingInParent) {
        if (parentNode != null && curType != null) {
            Map<ERepositoryObjectType, TypeImportNode> typeNodesChildrenMap = null;
            if (parentNode instanceof ProjectImportNode) {
                typeNodesChildrenMap = ((ProjectImportNode) parentNode).getTypeNodesChildrenMap();
            } else if (parentNode instanceof TypeImportNode) {
                typeNodesChildrenMap = ((TypeImportNode) parentNode).getTypeNodesChildrenMap();
            }
            if (typeNodesChildrenMap == null) {
                return null;
            }

            TypeImportNode typeImportNode = typeNodesChildrenMap.get(curType);
            if (typeImportNode != null) {
                return typeImportNode;
            } else {
                if (creatingInParent) {
                    // not found, create new one.
                    typeImportNode = new TypeImportNode(curType);
                    typeNodesChildrenMap.put(curType, typeImportNode);
                    parentNode.addChild(typeImportNode);
                    return typeImportNode;
                } else { // try the all type nodes
                    for (ERepositoryObjectType type : typeNodesChildrenMap.keySet()) {
                        TypeImportNode childTypeNode = typeNodesChildrenMap.get(type);
                        TypeImportNode findTypeImportNode = findAndCreateTypeNode(childTypeNode, curType, false);
                        if (findTypeImportNode != null) {
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }
}
