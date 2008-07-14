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
package org.talend.repository.localprovider.imports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.model.properties.Project;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.ui.images.CoreImageProvider;
import org.talend.core.ui.images.ECoreImage;

/**
 * DOC hcw class global comment. Detailled comment
 */
public class TreeBuilder {

    static final ERepositoryObjectType ALL_TYPES[] = { ERepositoryObjectType.BUSINESS_PROCESS, ERepositoryObjectType.PROCESS,
            ERepositoryObjectType.JOBLET, ERepositoryObjectType.CONTEXT, ERepositoryObjectType.ROUTINES,
            ERepositoryObjectType.SQLPATTERNS, ERepositoryObjectType.METADATA_CONNECTIONS,
            ERepositoryObjectType.METADATA_FILE_DELIMITED, ERepositoryObjectType.METADATA_FILE_POSITIONAL,
            ERepositoryObjectType.METADATA_FILE_REGEXP, ERepositoryObjectType.METADATA_FILE_XML,
            ERepositoryObjectType.METADATA_FILE_EXCEL, ERepositoryObjectType.METADATA_FILE_LDIF,
            ERepositoryObjectType.METADATA_LDAP_SCHEMA, ERepositoryObjectType.METADATA_GENERIC_SCHEMA,
            ERepositoryObjectType.METADATA_SALESFORCE_SCHEMA, ERepositoryObjectType.METADATA_WSDL_SCHEMA };

    static Map<ERepositoryObjectType, Integer> typeOrders = new HashMap<ERepositoryObjectType, Integer>();

    static {
        for (int i = 0; i < ALL_TYPES.length; i++) {
            typeOrders.put(ALL_TYPES[i], i);
        }
    }

    Set<Project> inputs = new HashSet<Project>();

    Map<String, ProjectNode> projects = new HashMap<String, ProjectNode>();

    TreeBuilder() {
    }

    public static int compare(ProjectNode o1, ProjectNode o2) {
        return o1.project.getLabel().compareTo(o2.project.getLabel());
    }

    public static int compare(FolderNode o1, FolderNode o2) {
        return typeOrders.get(o1.type) - typeOrders.get(o2.type);
    }

    public static int compare(ItemRecord o1, ItemRecord o2) {
        int res = o1.getProperty().getLabel().compareTo(o2.getProperty().getLabel());
        if (res == 0) {
            // item with same name, should compare version
            return VersionUtils.compareTo(o1.getProperty().getVersion(), o2.getProperty().getVersion());
        } else {
            return res;
        }
    }

    public static ViewerSorter createSorter() {
        return new ViewerSorter() {

            @Override
            public int compare(Viewer v, Object o1, Object o2) {
                if (o1 instanceof ProjectNode) {
                    return TreeBuilder.compare((ProjectNode) o1, (ProjectNode) o2);
                } else if (o1 instanceof FolderNode) {
                    return TreeBuilder.compare((FolderNode) o1, (FolderNode) o2);
                } else if (o1 instanceof ItemRecord) {
                    return TreeBuilder.compare((ItemRecord) o1, (ItemRecord) o2);
                }
                return super.compare(v, o1, o2);
            }

        };
    }

    public void addItem(Project project, ItemRecord itemRecord) {
        ProjectNode node = projects.get(project.getTechnicalLabel());
        if (node == null) {
            node = new ProjectNode(project);
            projects.put(project.getTechnicalLabel(), node);
        }
        node.addItem(itemRecord);
    }

    public List<ProjectNode> getInput() {
        List<ProjectNode> nodes = new ArrayList<ProjectNode>(projects.values());

        // sort the project by label
        Collections.sort(nodes, new Comparator<ProjectNode>() {

            public int compare(ProjectNode o1, ProjectNode o2) {
                return TreeBuilder.compare(o1, o2);
            }
        });

        return nodes;
    }

    /**
     * DOC hcw Comment method "clear".
     */
    public void clear() {
        inputs.clear();
        projects.clear();
    }

    /**
     * 
     * DOC hcw TreeBuilder class global comment. Detailled comment
     */
    interface IContainerNode {

        public String getLabel();

        public boolean hasChildren();

        public List getChildren();

        public Image getImage();
    }

    /**
     * 
     * DOC hcw ImportItemUtil class global comment. Detailled comment
     */
    class ProjectNode implements IContainerNode {

        boolean sorted = false;

        Project project;

        Set<ERepositoryObjectType> types = new HashSet<ERepositoryObjectType>();

        Map<ERepositoryObjectType, FolderNode> typeMap = new HashMap<ERepositoryObjectType, FolderNode>();

        ProjectNode(Project project) {
            this.project = project;
        }

        public String getLabel() {
            return project.getLabel();
        }

        public Image getImage() {
            return ImageProvider.getImage(ECoreImage.PROJECT_ICON);
        }

        public void addItem(ItemRecord itemRecord) {
            sorted = false;
            ERepositoryObjectType type = itemRecord.getType();
            types.add(type);
            FolderNode folder = typeMap.get(type);
            if (folder == null) {
                folder = new FolderNode(type);
                typeMap.put(type, folder);
            }
            folder.add(itemRecord);
        }

        private void sort(List<FolderNode> nodes) {
            if (sorted == false) {

                Collections.sort(nodes, new Comparator<FolderNode>() {

                    public int compare(FolderNode o1, FolderNode o2) {
                        return typeOrders.get(o1.type).intValue() - typeOrders.get(o2.type).intValue();
                    }
                });

                sorted = true;
            }
        }

        public List<FolderNode> getChildren() {
            List<FolderNode> nodes = new ArrayList<FolderNode>(typeMap.values());
            // sort the folder by type
            sort(nodes);
            return nodes;
        }

        public boolean hasChildren() {
            return types.size() > 0;
        }
    }

    /**
     * 
     * DOC hcw ImportItemUtil class global comment. Detailled comment
     */
    class FolderNode implements IContainerNode {

        ERepositoryObjectType type;

        List<ItemRecord> items = new ArrayList<ItemRecord>();

        boolean sorted = false;

        public FolderNode(ERepositoryObjectType type) {
            this.type = type;
        }

        public Image getImage() {
            return CoreImageProvider.getImage(type);
        }

        public String getLabel() {
            return type.toString();
        }

        public void add(ItemRecord itemRecord) {
            items.add(itemRecord);
            sorted = false;
        }

        public boolean hasChildren() {
            return items.size() > 0;
        }

        /**
         * 
         * Sort item by label and version.
         */
        private void sort() {
            if (sorted == false) {

                Collections.sort(items, new Comparator<ItemRecord>() {

                    public int compare(ItemRecord o1, ItemRecord o2) {
                        int res = o1.getProperty().getLabel().compareTo(o2.getProperty().getLabel());
                        if (res == 0) {
                            // item with same name, should compare version
                            return VersionUtils.compareTo(o1.getProperty().getVersion(), o2.getProperty().getVersion());
                        } else {
                            return res;
                        }
                    }
                });

                sorted = true;
            }
        }

        public List<ItemRecord> getChildren() {
            sort();
            return items;
        }
    }
}
