// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.List;

import javax.xml.rpc.soap.SOAPFaultException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.mdm.webservice.WSDataModel;
import org.talend.mdm.webservice.WSDataModelPK;
import org.talend.mdm.webservice.WSGetDataModel;
import org.talend.mdm.webservice.WSGetUniversePKs;
import org.talend.mdm.webservice.WSPing;
import org.talend.mdm.webservice.WSRegexDataModelPKs;
import org.talend.mdm.webservice.WSUniversePK;
import org.talend.mdm.webservice.XtentisBindingStub;
import org.talend.mdm.webservice.XtentisPort;
import org.talend.mdm.webservice.XtentisServiceLocator;
import org.talend.repository.ui.swt.utils.AbstractXmlStepForm;
import org.talend.repository.ui.utils.OtherConnectionContextUtils.EParamName;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.treeNode.FOXTreeNode;

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
        XtentisBindingStub stub = null;
        String userName = mdmConn.getUsername();
        String password = mdmConn.getPassword();
        String server = mdmConn.getServer();
        String port = mdmConn.getPort();
        String universe = mdmConn.getUniverse();
        String datamodel = mdmConn.getDatamodel();
        WSUniversePK[] universes = null;
        WSUniversePK universePK = null;
        WSDataModelPK modelPK = null;
        XtentisServiceLocator xtentisService = new XtentisServiceLocator();
        xtentisService.setXtentisPortEndpointAddress("http://" + server + ":" + port + "/talend/TalendPort"); //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        try {
            XtentisPort xtentisWS = xtentisService.getXtentisPort();
            stub = (XtentisBindingStub) xtentisWS;
            stub.setUsername(userName);
            stub.setPassword(password);
            stub.ping(new WSPing());
            try {
                universes = stub.getUniversePKs(new WSGetUniversePKs("")); //$NON-NLS-1$
            } catch (SOAPFaultException e) {
                // @FIXME
                universes = null;
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        if (universes == null) {
            return;
        }
        for (int i = 0; i < universes.length; i++) {
            if (universes[i].getPk().equals(universe)) {
                universePK = universes[i];
                break;
            }
        }
        //        if (universePK != null && universe != null && !"".equals(universe)) { //$NON-NLS-1$
        if (universe != null && !"".equals(universe)) { //$NON-NLS-1$
            stub.setUsername(universe + "/" + userName); //$NON-NLS-1$
            stub.setPassword(password);
        } else {
            stub.setUsername(userName);
            stub.setPassword(password);
        }
        try {
            WSDataModelPK[] models = stub.getDataModelPKs(new WSRegexDataModelPKs(""));//$NON-NLS-1$
            if (models == null) {
                return;
            }
            for (int i = 0; i < models.length; i++) {
                if (models[i].getPk().equals(datamodel)) {
                    modelPK = models[i];
                    break;
                }
            }
            if (modelPK == null) {
                return;
            }

            WSDataModel model = stub.getDataModel(new WSGetDataModel(modelPK));
            if (model == null) {
                return;
            }
            writeInFile(model.getXsdSchema());
            // List<String> list = MDMUtil.getConcepts(MDMUtil.getXSDSchema(model.getXsdSchema()));
            // concepts.addAll(list);
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        // return concepts;
    }

    private void writeInFile(String schema) {
        IPath tempPath = new Path(System.getProperty("user.dir")).append("temp"); //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-2$
        File tempFile = tempPath.toFile();
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        String parentPath = tempPath.toOSString();
        File file = new File(parentPath + "\\template.xsd"); //$NON-NLS-1$
        xsdFilePath = file.getAbsolutePath();
        StringReader reader = new StringReader(schema);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
            char[] c = new char[1024];
            int l = 0;
            while ((l = reader.read(c)) != -1) {
                writer.write(c, 0, l);
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
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

}
