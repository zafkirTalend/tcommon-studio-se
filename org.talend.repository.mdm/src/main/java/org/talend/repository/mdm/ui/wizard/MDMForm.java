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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.swt.formtools.Form;
import org.talend.commons.ui.swt.formtools.LabelledText;
import org.talend.commons.ui.swt.formtools.UtilsButton;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.mdm.webservice.WSGetUniversePKs;
import org.talend.mdm.webservice.WSPing;
import org.talend.mdm.webservice.WSUniversePK;
import org.talend.mdm.webservice.XtentisBindingStub;
import org.talend.mdm.webservice.XtentisPort;
import org.talend.mdm.webservice.XtentisServiceLocator;
import org.talend.repository.mdm.i18n.Messages;
import org.talend.repository.ui.swt.utils.AbstractForm;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class MDMForm extends AbstractForm {

    // control fields
    private LabelledText mdmUsernameText;

    private LabelledText mdmPasswordText;

    private LabelledText mdmServer;

    private LabelledText mdmHostnameText;

    private UtilsButton checkButton;

    // other variables
    private boolean readOnly;

    private boolean verified = false;

    private MDMWizardPage page;

    private XtentisBindingStub stub;

    /**
     * DOC hwang MDMForm constructor comment.
     * 
     * @param parent
     * @param style
     * @param existingNames
     */
    protected MDMForm(Composite parent, ConnectionItem connectionItem, String[] existingNames, MDMWizardPage page) {
        super(parent, SWT.NONE, existingNames);
        this.connectionItem = connectionItem;
        this.page = page;
        setConnectionItem(connectionItem); // must be first.
        setupForm(false);
        layoutForm();
    }

    private void layoutForm() {
        GridLayout layout = (GridLayout) getLayout();
        layout.marginHeight = 0;
        setLayout(layout);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#adaptFormToReadOnly()
     */
    @Override
    protected void adaptFormToReadOnly() {
        readOnly = isReadOnly();
        mdmUsernameText.setReadOnly(readOnly);
        mdmPasswordText.setReadOnly(readOnly);
        mdmServer.setReadOnly(readOnly);
        mdmHostnameText.setReadOnly(readOnly);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addFields()
     */
    @Override
    protected void addFields() {
        Group mdmParameterGroup = new Group(this, SWT.NULL);
        mdmParameterGroup.setText(Messages.getString("MDMForm.PARAMETER")); //$NON-NLS-1$
        GridLayout layoutGroup = new GridLayout();
        layoutGroup.numColumns = 2;
        mdmParameterGroup.setLayout(layoutGroup);

        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        mdmParameterGroup.setLayoutData(gridData);

        mdmUsernameText = new LabelledText(mdmParameterGroup, Messages.getString("MDMForm.USERNAME"), true); //$NON-NLS-1$

        mdmPasswordText = new LabelledText(mdmParameterGroup,
                Messages.getString("MDMForm.PASSWORD"), 1, SWT.BORDER | SWT.PASSWORD); //$NON-NLS-1$

        mdmServer = new LabelledText(mdmParameterGroup, Messages.getString("MDMForm.SERVER"), true); //$NON-NLS-1$
        mdmServer.setText("localhost");//$NON-NLS-1$

        mdmHostnameText = new LabelledText(mdmParameterGroup, Messages.getString("MDMForm.PORT"), true); //$NON-NLS-1$

        addCheckButton(mdmParameterGroup);
        checkFieldsValue();
        if (!verified) {
            page.setPageComplete(false);
        }
    }

    private void addCheckButton(Composite parent) {
        Composite compositeCheckButton = Form.startNewGridLayout(parent, 1, false, SWT.CENTER, SWT.TOP);

        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 2;
        data.horizontalAlignment = SWT.CENTER;
        compositeCheckButton.setLayoutData(data);

        GridLayout layout2 = (GridLayout) compositeCheckButton.getLayout();
        layout2.marginHeight = 0;
        layout2.marginTop = 0;
        layout2.marginBottom = 0;
        checkButton = new UtilsButton(compositeCheckButton,
                Messages.getString("MDMForm.CHECK"), WIDTH_BUTTON_PIXEL, HEIGHT_BUTTON_PIXEL); //$NON-NLS-1$
        checkButton.setEnabled(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#addFieldsListeners()
     */
    @Override
    protected void addFieldsListeners() {
        mdmUsernameText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                checkFieldsValue();
            }

        });
        mdmPasswordText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                checkFieldsValue();
            }

        });
        mdmServer.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                checkFieldsValue();
            }

        });
        mdmHostnameText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                checkFieldsValue();
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
        checkButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse .swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                checkMDMConnection();
            }

        });

    }

    private void checkMDMConnection() {
        XtentisServiceLocator xtentisService = new XtentisServiceLocator();
        xtentisService.setXtentisPortEndpointAddress("http://" + mdmServer.getText() + ":" + mdmHostnameText.getText()//$NON-NLS-1$//$NON-NLS-1$ //$NON-NLS-2$
                + "/talend/TalendPort");//$NON-NLS-1$
        String username = mdmUsernameText.getText();
        String pass = mdmPasswordText.getText();
        if (username == null || pass == null) {
            page.setPageComplete(false);
            return;
        }
        try {
            XtentisPort xtentisWS = xtentisService.getXtentisPort();
            stub = (XtentisBindingStub) xtentisWS;
            stub.setUsername(mdmUsernameText.getText());
            stub.setPassword(mdmPasswordText.getText());
            stub.ping(new WSPing());
            verified = true;
        } catch (Exception e) {
            verified = false;
            stub = null;
            ExceptionHandler.process(e);
        }

        if (verified) {
            page.setPageComplete(true);
            MessageBox box = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_WORKING | SWT.OK | SWT.CANCEL);
            box.setText(Messages.getString("MDMForm.Success")); //$NON-NLS-1$
            box.setMessage(Messages.getString("MDMForm.successfully")); //$NON-NLS-1$
            box.open();
        } else {
            page.setPageComplete(false);
            MessageBox box = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_WORKING | SWT.OK | SWT.CANCEL);
            box.setText(Messages.getString("MDMForm.Unsuccessful")); //$NON-NLS-1$
            box.setMessage(Messages.getString("MDMForm.Unsiccess_Mess")); //$NON-NLS-1$
            box.open();
        }
        initUniverse();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#checkFieldsValue()
     */
    @Override
    protected boolean checkFieldsValue() {
        if (isContextMode()) {
            return true;
        }

        if (mdmUsernameText.getCharCount() == 0) {
            updateStatus(IStatus.ERROR, Messages.getString("MDMForm.NAME_NULL")); //$NON-NLS-1$
            checkButton.setEnabled(false);
            return false;
        }

        if (mdmPasswordText.getCharCount() == 0) {
            updateStatus(IStatus.ERROR, Messages.getString("MDMForm.PASSWORD_NULL")); //$NON-NLS-1$
            checkButton.setEnabled(false);
            return false;
        }

        if (mdmServer.getCharCount() == 0) {
            updateStatus(IStatus.ERROR, Messages.getString("MDMForm.SERVER_NULL")); //$NON-NLS-1$
            checkButton.setEnabled(false);
            return false;
        }

        if (mdmHostnameText.getCharCount() == 0) {
            updateStatus(IStatus.ERROR, Messages.getString("MDMForm.PORT_NULL")); //$NON-NLS-1$
            checkButton.setEnabled(false);
            return false;
        }
        getConnection().setUsername(mdmUsernameText.getText());
        getConnection().setPassword(mdmPasswordText.getText());
        getConnection().setServer(mdmServer.getText());
        getConnection().setPort(mdmHostnameText.getText());
        checkButton.setEnabled(true);
        updateStatus(IStatus.OK, null);
        if (!verified) {
            page.setPageComplete(false);
        }
        return true;
    }

    private void updateCheckButton() {
        if (isContextMode()) {
            checkButton.setEnabled(true);
        } else {
            boolean enable = mdmHostnameText.getCharCount() != 0 && mdmUsernameText.getCharCount() != 0
                    && mdmPasswordText.getCharCount() != 0 && mdmServer.getCharCount() != 0;
            checkButton.setEnabled(enable);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#initialize()
     */
    @Override
    protected void initialize() {
        String username = getConnection().getUsername();
        String pass = getConnection().getPassword();
        String server = getConnection().getServer();
        String port = getConnection().getPort();
        mdmUsernameText.setText(username);
        mdmPasswordText.setText(pass);
        if (server == null || "".equals(server)) { //$NON-NLS-1$
            mdmServer.setText("localhost"); //$NON-NLS-1$
        } else
            mdmServer.setText(server);
        mdmHostnameText.setText(port);
        // if (username != null && pass != null && server != null && port != null) {
        // updateStatus(IStatus.ERROR, "Please check the connection");
        // } else {
        // updateStatus(IStatus.ERROR, "Please set properties");
        // }

    }

    protected MDMConnection getConnection() {
        return (MDMConnection) connectionItem.getConnection();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Control#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        // updateCheckButton();

        if (isContextMode()) {
            adaptFormToEditable();
        }

        if (isReadOnly() != readOnly) {
            adaptFormToReadOnly();
        }
        checkFieldsValue();
    }

    public XtentisBindingStub getXtentisBindingStub() {
        return this.stub;
    }

    public String getUserName() {
        return mdmUsernameText.getText();
    }

    public String getPassword() {
        return mdmPasswordText.getText();
    }

    protected List<String> initUniverse() {
        List<String> list = new ArrayList<String>();
        list.add(0, "[HEAD]");//$NON-NLS-1$
        XtentisBindingStub stub = getXtentisBindingStub();
        if (stub == null) {
            return list;
        }
        try {
            WSUniversePK[] universe = stub.getUniversePKs(new WSGetUniversePKs(""));//$NON-NLS-1$
            for (int i = 0; i < universe.length; i++) {
                list.add(universe[i].getPk());
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        return list;
    }
}
