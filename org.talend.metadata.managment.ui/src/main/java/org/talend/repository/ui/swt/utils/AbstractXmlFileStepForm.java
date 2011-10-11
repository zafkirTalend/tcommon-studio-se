// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.swt.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.XMLFileNode;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.repository.ui.utils.FileConnectionContextUtils.EFileParamName;
import org.talend.repository.ui.utils.OtherConnectionContextUtils.EParamName;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.XmlFileWizard;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.treeNode.Element;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.treeNode.FOXTreeNode;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.util.TreeUtil;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 * 
 * $Id: AbstractXmlFileStepForm.java 48226 2010-09-14 10:04:12Z hywang $
 * 
 */
public abstract class AbstractXmlFileStepForm extends AbstractXmlStepForm {

    protected XmlFileConnection connection;

    protected int order = 1;

    protected Map<String, Integer> orderMap = new HashMap<String, Integer>();

    /**
     * DOC cantoine AbstractXmlFileStepForm constructor comment. Use to step1
     */
    public AbstractXmlFileStepForm(Composite parent, ConnectionItem connectionItem, String[] existingNames) {
        super(parent, SWT.NONE, existingNames);
        setConnectionItem(connectionItem);
    }

    /**
     * DOC cantoine AbstractXmlFileStepForm constructor comment. Use to step2
     * 
     * @param parent
     * @param connection2
     */
    public AbstractXmlFileStepForm(Composite parent, ConnectionItem connectionItem) {
        this(parent, connectionItem, null);
    }

    /**
     * DOC cantoine AbstractDelimitedFileStepForm constructor comment. Use to step1
     */
    public AbstractXmlFileStepForm(Composite parent, ConnectionItem connectionItem, MetadataTable metadataTable,
            String[] existingNames) {
        super(parent, SWT.NONE, existingNames);
        setConnectionItem(connectionItem);
    }

    protected XmlFileConnection getConnection() {
        return (XmlFileConnection) connectionItem.getConnection();
    }

    @Override
    protected void exportAsContext() {
        collectConnParams();
        super.exportAsContext();
    }

    protected void collectConnParams() {
        if (getConnection().isInputModel()) {
            addContextParams(EFileParamName.File, true);
            addContextParams(EFileParamName.Encoding, true);
            addContextParams(EParamName.XPathQuery, true);
        } else {
            addContextParams(EParamName.OutputFilePath, true);
        }

    }

    protected void initMetadataTable(List<FOXTreeNode> list, EList columnList) {
        for (FOXTreeNode node : list) {
            if (node instanceof Element) {
                String label = node.getLabel();
                if (label != null && !label.equals("")) {
                    String dataType = node.getDataType();
                    MetadataColumn metadataColumn = ConnectionFactory.eINSTANCE.createMetadataColumn();
                    metadataColumn.setLabel(label);
                    metadataColumn.setOriginalField(label);
                    metadataColumn.setTalendType(dataType);
                    columnList.add(metadataColumn);
                }
            }
            if (node.hasChildren()) {
                List<FOXTreeNode> children = node.getChildren();
                initMetadataTable(children, columnList);
            }
        }
    }

    protected void initNodeOrder(FOXTreeNode node) {
        if (node == null) {
            return;
        }
        FOXTreeNode parent = node.getParent();
        if (parent == null) {
            node.setOrder(1);
            String path = TreeUtil.getPath(node);
            orderMap.put(path, order);
            order++;
        }
        List<FOXTreeNode> childNode = node.getChildren();
        for (FOXTreeNode child : childNode) {
            child.setOrder(order);
            String path = TreeUtil.getPath(child);
            orderMap.put(path, order);
            order++;
            if (child.getChildren().size() > 0) {
                initNodeOrder(child);
            }
        }
    }

    protected int getNodeOrder(FOXTreeNode node) {
        if (node != null) {
            String path = TreeUtil.getPath(node);
            return orderMap.get(path);
        }
        return 0;
    }

    protected void tableLoader(Element element, String parentPath, List<XMLFileNode> table, String defaultValue) {
        XMLFileNode xmlFileNode = ConnectionFactory.eINSTANCE.createXMLFileNode();
        String currentPath = parentPath + "/" + element.getLabel();
        xmlFileNode.setXMLPath(currentPath);
        xmlFileNode.setRelatedColumn(element.getColumnLabel());
        xmlFileNode.setAttribute(element.isMain() ? "main" : "branch");
        xmlFileNode.setDefaultValue(defaultValue);
        xmlFileNode.setType(element.getDataType());
        xmlFileNode.setOrder(getNodeOrder(element));
        table.add(xmlFileNode);
        for (FOXTreeNode att : element.getAttributeChildren()) {
            xmlFileNode = ConnectionFactory.eINSTANCE.createXMLFileNode();
            xmlFileNode.setXMLPath(att.getLabel());
            xmlFileNode.setRelatedColumn(att.getColumnLabel());
            xmlFileNode.setAttribute("attri");
            xmlFileNode.setDefaultValue(att.getDefaultValue());
            xmlFileNode.setType(att.getDataType());
            xmlFileNode.setOrder(getNodeOrder(att));
            table.add(xmlFileNode);
        }
        for (FOXTreeNode att : element.getNameSpaceChildren()) {
            xmlFileNode = ConnectionFactory.eINSTANCE.createXMLFileNode();
            xmlFileNode.setXMLPath(att.getLabel());
            xmlFileNode.setRelatedColumn(att.getColumnLabel());
            xmlFileNode.setAttribute("ns");
            xmlFileNode.setDefaultValue(att.getDefaultValue());
            xmlFileNode.setType(att.getDataType());
            xmlFileNode.setOrder(getNodeOrder(att));
            table.add(xmlFileNode);
        }
        List<FOXTreeNode> children = element.getElementChildren();
        for (FOXTreeNode child : children) {
            if (!child.isGroup() && !child.isLoop()) {
                tableLoader((Element) child, currentPath, table, child.getDefaultValue());
            }
        }

    }

    protected void updateConnectionProperties(FOXTreeNode foxTreeNode) {
        EList root = getConnection().getRoot();
        EList loop = getConnection().getLoop();
        EList group = getConnection().getGroup();
        root.clear();
        loop.clear();
        group.clear();
        if (foxTreeNode != null) {
            initNodeOrder(foxTreeNode);
            tableLoader((Element) foxTreeNode, "", root, foxTreeNode.getDefaultValue());
            Element loopNode = (Element) TreeUtil.getLoopNode(foxTreeNode);
            if (loopNode != null) {
                String path = TreeUtil.getPath(loopNode);
                tableLoader(loopNode, path.substring(0, path.lastIndexOf("/")), loop, loopNode.getDefaultValue());
            }
            Element groupNode = (Element) TreeUtil.getGroupNode(foxTreeNode);
            if (groupNode != null) {
                String path = TreeUtil.getPath(groupNode);
                tableLoader(groupNode, path.substring(0, path.lastIndexOf("/")), group, groupNode.getDefaultValue());
            }
        }
    }

    public List<FOXTreeNode> updateRootFoxTreeNodes(String file) {
        if (file == null) {
            return new ArrayList<FOXTreeNode>();
        }
        List<FOXTreeNode> rootFoxTreeNodes = TreeUtil.getAllFoxTreeNodes(file);
        ((XmlFileWizard) getPage().getWizard()).setRootFoxTreeNodes(rootFoxTreeNodes);
        return rootFoxTreeNodes;
    }

    public void redrawLinkers() {
    }

    public void updateConnection() {
    }

    public void updateStatus() {
    }

    public List<FOXTreeNode> getTreeData() {
        return null;
    }

    public void setSelectedText(String label) {
    }

    public MetadataTable getMetadataTable() {
        return null;
    }

    public TableViewer getSchemaViewer() {
        return null;
    }

    @Override
    public MetadataTable getMetadataOutputTable() {
        return null;
    }

}
