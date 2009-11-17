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
package org.talend.repository.mdm.ui.wizard;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.swt.formtools.LabelledCombo;
import org.talend.commons.ui.swt.formtools.LabelledText;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.mdm.webservice.WSDataClusterPK;
import org.talend.mdm.webservice.WSDataModelPK;
import org.talend.mdm.webservice.WSRegexDataClusterPKs;
import org.talend.mdm.webservice.WSRegexDataModelPKs;
import org.talend.mdm.webservice.WSUniversePK;
import org.talend.mdm.webservice.XtentisBindingStub;
import org.talend.repository.mdm.i18n.Messages;
import org.talend.repository.ui.swt.utils.AbstractForm;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class UniverseForm extends AbstractForm {

    private LabelledCombo universeCombo = null;

    private LabelledText modelText = null;

    private LabelledText clusterText = null;

    // private MDMWizardPage mdmWizardPage;

    private List<String> list = new ArrayList<String>();

    private Map<String, WSUniversePK> map = new HashMap<String, WSUniversePK>();

    private String username;

    private XtentisBindingStub stub;

    private ConnectionItem connectionItem;

    /**
     * DOC Administrator UniverseForm constructor comment.
     * 
     * @param parent
     * @param style
     * @param existingNames
     */
    protected UniverseForm(Composite parent, int style, String[] existingNames, ConnectionItem connectionItem) {
        super(parent, style, existingNames);
        this.connectionItem = connectionItem;
        setupForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#adaptFormToReadOnly()
     */
    @Override
    protected void adaptFormToReadOnly() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addFields()
     */
    @Override
    protected void addFields() {
        Group mdmParameterGroup = new Group(this, SWT.NULL);
        mdmParameterGroup.setText(Messages.getString("UniverseForm.SELECT")); //$NON-NLS-1$
        GridLayout layoutGroup = new GridLayout();
        layoutGroup.numColumns = 2;
        mdmParameterGroup.setLayout(layoutGroup);

        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        mdmParameterGroup.setLayoutData(gridData);
        universeCombo = new LabelledCombo(mdmParameterGroup, Messages.getString("UniverseForm.UNIVERSE"), "", list, true); //$NON-NLS-1$ //$NON-NLS-2$
        modelText = new LabelledText(mdmParameterGroup, Messages.getString("UniverseForm.MODLE"), true); //$NON-NLS-1$
        clusterText = new LabelledText(mdmParameterGroup, Messages.getString("UniverseForm.CLUSTER"), true); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addFieldsListeners()
     */
    @Override
    protected void addFieldsListeners() {
        universeCombo.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (stub == null) {
                    return;
                }
                String universeValue = universeCombo.getText();
                if (universeValue.equals("[HEAD]")) { //$NON-NLS-1$
                    modelText.setText("");//$NON-NLS-1$
                    clusterText.setText("");//$NON-NLS-1$
                    return;
                }
                if (universeValue == null || universeValue.trim().length() == 0) {
                    stub.setUsername(username);
                } else {
                    stub.setUsername(universeValue + "/" + username);//$NON-NLS-1$
                }
                try {
                    WSDataModelPK[] model = stub.getDataModelPKs(new WSRegexDataModelPKs(""));//$NON-NLS-1$
                    WSDataClusterPK[] cluster = stub.getDataClusterPKs(new WSRegexDataClusterPKs(""));//$NON-NLS-1$
                    modelText.setText(model[0].getPk());
                    clusterText.setText(cluster[0].getPk());
                    checkFieldsValue();
                } catch (RemoteException e1) {
                    modelText.setText("");//$NON-NLS-1$
                    clusterText.setText("");//$NON-NLS-1$
                    ExceptionHandler.process(e1);

                }
            }

        });

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addUtilsButtonListeners()
     */
    @Override
    protected void addUtilsButtonListeners() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#checkFieldsValue()
     */
    @Override
    protected boolean checkFieldsValue() {
        getConnection().setUniverse(universeCombo.getText());
        getConnection().setDatamodel(modelText.getText());
        getConnection().setDatacluster(clusterText.getText());
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#initialize()
     */
    @Override
    protected void initialize() {
        universeCombo.setText(getConnection().getUniverse());
        universeCombo.getCombo().setData(getConnection().getUniverse());
        modelText.setText(getConnection().getDatamodel());
        clusterText.setText(getConnection().getDatacluster());
        if ("".equals(getConnection().getUniverse()) || getConnection().getUniverse() == null) {//$NON-NLS-1$
            if (list.size() > 1) {
                universeCombo.getCombo().setData(list.get(1));
            } else {
                universeCombo.getCombo().setData("[HEAD]");//$NON-NLS-1$
            }
        }
    }

    public List<String> getList() {
        return this.list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public XtentisBindingStub getStub() {
        return this.stub;
    }

    public void setStub(XtentisBindingStub stub) {
        this.stub = stub;
    }

    public void refreshCombo(List<String> list) {
        String universeValue = (String) universeCombo.getCombo().getData();
        universeCombo.removeAll();
        for (String universe : list) {
            universeCombo.add(universe);
        }
        universeCombo.setText(universeValue);
    }

    public MDMConnection getConnection() {
        return (MDMConnection) connectionItem.getConnection();
    }

}
