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
package org.talend.repository.mdm.ui.wizard.concept;

import java.io.File;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.xsd.XSDSchema;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.datatools.xml.utils.ATreeNode;
import org.talend.datatools.xml.utils.OdaException;
import org.talend.datatools.xml.utils.XSDPopulationUtil2;
import org.talend.repository.mdm.util.MDMUtil;
import org.talend.repository.ui.swt.utils.AbstractXmlStepForm;
import org.talend.repository.ui.utils.OtherConnectionContextUtils.EParamName;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.treeNode.FOXTreeNode;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.util.TreeUtil;

/**
 * DOC hwang class global comment. Detailled comment
 */
public abstract class AbstractMDMFileStepForm extends AbstractXmlStepForm {

    protected MDMConnection connection;

    protected String xsdFilePath;

    /**
     * DOC Administrator AbstractMDMFileStepForm constructor comment.
     * 
     * @param parent
     * @param style
     */
    protected AbstractMDMFileStepForm(Composite parent, ConnectionItem connectionItem, String[] existingNames) {
        super(parent, SWT.NONE, existingNames);
        setConnectionItem(connectionItem);
    }

    public AbstractMDMFileStepForm(Composite parent, ConnectionItem connectionItem) {
        this(parent, connectionItem, null);
    }

    public AbstractMDMFileStepForm(Composite parent, ConnectionItem connectionItem, MetadataTable metadataTable,
            String[] existingNames) {
        super(parent, SWT.NONE, existingNames);
        setConnectionItem(connectionItem);
    }

    public AbstractMDMFileStepForm(Composite parent, int style, String[] existingNames) {
        super(parent, style, existingNames);
        setConnectionItem(connectionItem);
    }

    protected MDMConnection getConnection() {
        return (MDMConnection) connectionItem.getConnection();
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
    protected void exportAsContext() {
        collectConnParams();
        super.exportAsContext();
    }

    protected void collectConnParams() {
        addContextParams(EParamName.UNIVERSE, true);
        addContextParams(EParamName.DATACLUSTER, true);
        addContextParams(EParamName.DATAMODEL, true);
        addContextParams(EParamName.MDMURL, true);
    }

    @Override
    protected void initialize() {
        initConcepts();
    }

    private void initConcepts() {
        // IPath temp = new Path(System.getProperty("user.dir")).append("temp");
        // xsdFilePath = temp.toOSString() + "\\template.xsd";
        MDMConnection mdmConn = (MDMConnection) connectionItem.getConnection();
        File file = MDMUtil.getTempTemplateXSDFile();
        xsdFilePath = file.getAbsolutePath();
        try {
            MDMUtil.initConcepts(mdmConn);
        } catch (RemoteException e) {
            ExceptionHandler.process(e);
        } catch (ServiceException e) {
            ExceptionHandler.process(e);
        }
    }

    protected void createTable() {

    }

    @Override
    public MetadataTable getMetadataOutputTable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void adaptFormToReadOnly() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void addFields() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void addFieldsListeners() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void addUtilsButtonListeners() {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean checkFieldsValue() {
        // TODO Auto-generated method stub
        return false;
    }

    public ATreeNode getSelectedTreeNode(String xsdFilePath, String selectedEntity) {
        try {
            CreateConceptWizard wizard = ((CreateConceptWizard) getPage().getWizard());
            XSDSchema xsdSchema = wizard.getXSDSchema();
            List<ATreeNode> rootNodes = wizard.getRootNodes();
            if (xsdSchema == null) {
                xsdSchema = TreeUtil.getXSDSchema(xsdFilePath);
                rootNodes = new XSDPopulationUtil2().getAllRootNodes(xsdSchema);
                wizard.setRootNodes(rootNodes);
                wizard.setXsModel(xsdSchema);
            }
            ATreeNode selectedTreeNode = null;
            for (ATreeNode root : rootNodes) {
                if (root.getLabel() != null && root.getLabel().equals(selectedEntity)) {
                    selectedTreeNode = root;
                    break;
                }
            }
            return selectedTreeNode;
        } catch (OdaException e) {
            return null;
        }
    }

}
