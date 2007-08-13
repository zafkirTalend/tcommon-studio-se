// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.repository.ui.wizards;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListDialog;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.i18n.Messages;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.repository.model.RepositoryConstants;
import org.talend.repository.ui.properties.StatusHelper;

/**
 * Wizard page contains common properties fields.<br/>
 * 
 * $Id: PropertiesWizardPage.java 914 2006-12-08 08:28:53 +0000 (星期五, 08 十二月 2006) bqian $
 * 
 */
public abstract class PropertiesWizardPage extends WizardPage {

    /** Name text. */
    protected Text nameText;

    /** Purpose text. */
    protected Text purposeText;

    /** Comment text. */
    protected Text descriptionText;

    /** Author text. */
    protected Text authorText;

    /** Version text. */
    protected Text versionText;

    /** Status text. */
    // protected Text statusText;
    private CCombo statusText;

    /** Version upgrade major button. */
    private Button versionMajorBtn;

    /** Version upgrade minor button. */
    private Button versionMinorBtn;

    private Text pathText;

    private IStatus nameStatus;

    private IStatus purposeStatus;

    private IStatus commentStatus;

    private boolean nameModifiedByUser = false;

    private boolean update = false;

    protected Property property;

    private IPath destinationPath;

    private String path;

    private boolean readOnly;

    private StatusHelper statusHelper = null;

    private boolean editPath = true;

    protected PropertiesWizardPage(String pageName, Property property, IPath destinationPath) {
        this(pageName, property, destinationPath, false, true);
    }

    protected PropertiesWizardPage(String pageName, Property property, IPath destinationPath, boolean readOnly,
            boolean editPath) {
        super(pageName);
        IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(
                IRepositoryService.class);
        statusHelper = new StatusHelper(service.getProxyRepositoryFactory());
        this.destinationPath = destinationPath;

        this.readOnly = readOnly;
        this.editPath = editPath;

        nameStatus = createOkStatus();
        purposeStatus = createOkStatus();
        commentStatus = createOkStatus();

        this.property = property;
    }

    /**
     * Getter for editPath.
     * 
     * @return the editPath
     */
    public boolean isEditPath() {
        return this.editPath;
    }

    /**
     * Sets the editPath.
     * 
     * @param editPath the editPath to set
     */
    public void setEditPath(boolean editPath) {
        this.editPath = editPath;
    }

    /**
     * Getter for isUpdate.
     * 
     * @return the isUpdate
     */
    public boolean isUpdate() {
        return this.update;
    }

    /**
     * Sets the isUpdate.
     * 
     * @param isUpdate the isUpdate to set
     */
    public void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * Getter for nameModifiedByUser.
     * 
     * @return the nameModifiedByUser
     */
    public boolean isNameModifiedByUser() {
        return this.nameModifiedByUser;
    }

    /**
     * Sets the nameModifiedByUser.
     * 
     * @param nameModifiedByUser the nameModifiedByUser to set
     */
    public void setNameModifiedByUser(boolean nameModifiedByUser) {
        this.nameModifiedByUser = nameModifiedByUser;
    }

    /**
     * Getter for destinationPath.
     * 
     * @return the destinationPath
     */
    public IPath getDestinationPath() {
        return new Path(pathText.getText());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        GridData data;

        // Name
        Label nameLab = new Label(parent, SWT.NONE);
        nameLab.setText(Messages.getString("PropertiesWizardPage.Name")); //$NON-NLS-1$

        nameText = new Text(parent, SWT.BORDER);
        nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        nameText.setEditable(!readOnly);

        // Purpose
        Label purposeLab = new Label(parent, SWT.NONE);
        purposeLab.setText(Messages.getString("PropertiesWizardPage.Purpose")); //$NON-NLS-1$

        purposeText = new Text(parent, SWT.BORDER);
        purposeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        purposeText.setEditable(!readOnly);

        // Description
        Label descriptionLab = new Label(parent, SWT.NONE);
        descriptionLab.setText(Messages.getString("PropertiesWizardPage.Description")); //$NON-NLS-1$
        descriptionLab.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        descriptionText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.heightHint = 60;
        descriptionText.setLayoutData(data);
        descriptionText.setEditable(!readOnly);

        // Author
        Label authorLab = new Label(parent, SWT.NONE);
        authorLab.setText(Messages.getString("PropertiesWizardPage.Author")); //$NON-NLS-1$

        authorText = new Text(parent, SWT.BORDER);
        authorText.setEnabled(false);
        authorText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // Version
        Label versionLab = new Label(parent, SWT.NONE);
        versionLab.setText(Messages.getString("PropertiesWizardPage.Version")); //$NON-NLS-1$

        Composite versionContainer = new Composite(parent, SWT.NONE);
        versionContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout versionLayout = new GridLayout(3, false);
        versionLayout.marginHeight = 0;
        versionLayout.marginWidth = 0;
        versionLayout.horizontalSpacing = 0;
        versionContainer.setLayout(versionLayout);

        versionText = new Text(versionContainer, SWT.BORDER);
        versionText.setEnabled(false);
        versionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        versionMajorBtn = new Button(versionContainer, SWT.PUSH);
        versionMajorBtn.setText(Messages.getString("PropertiesWizardPage.Version.Major")); //$NON-NLS-1$
        versionMajorBtn.setEnabled(!readOnly);

        versionMinorBtn = new Button(versionContainer, SWT.PUSH);
        versionMinorBtn.setText(Messages.getString("PropertiesWizardPage.Version.Minor")); //$NON-NLS-1$
        versionMinorBtn.setEnabled(!readOnly);

        // Status
        Label statusLab = new Label(parent, SWT.NONE);
        statusLab.setText(Messages.getString("PropertiesWizardPage.Status")); //$NON-NLS-1$

        statusText = new CCombo(parent, SWT.BORDER);
        List<org.talend.core.model.properties.Status> statusList;
        try {
            if (property != null) {
                statusList = statusHelper.getStatusList(property);
                statusText.setItems(toArray(statusList));
                statusText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                statusText.setEditable(!readOnly);
                statusText.setEnabled(!readOnly);
            }
        } catch (PersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Path:
        Label pathLab = new Label(parent, SWT.NONE);
        pathLab.setText(Messages.getString("PropertiesWizardPage.Path")); //$NON-NLS-1$

        Composite pathContainer = new Composite(parent, SWT.NONE);
        pathContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout pathLayout = new GridLayout(2, false);
        pathLayout.marginHeight = 0;
        pathLayout.marginWidth = 0;
        pathLayout.horizontalSpacing = 0;
        pathContainer.setLayout(pathLayout);

        pathText = new Text(pathContainer, SWT.BORDER);
        pathText.setEnabled(false);
        pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        if (editPath) {
            Button button = new Button(pathContainer, SWT.PUSH);
            button.setText(Messages.getString("PropertiesWizardPage.Select")); //$NON-NLS-1$

            button.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    openFolderSelectionDialog();
                }
            });
        }
    }

    /**
     * Provides all user folders for a given type.<br/>
     * 
     * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
     * 
     */
    private class FoldersContentProvider implements IStructuredContentProvider {

        private static final String DEFAULT = "(default)"; //$NON-NLS-1$

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            ERepositoryObjectType type = (ERepositoryObjectType) inputElement;
            IProxyRepositoryFactory factory = ((IRepositoryService) GlobalServiceRegister.getDefault().getService(
                    IRepositoryService.class)).getProxyRepositoryFactory();
            try {
                List<String> folders = factory.getFolders(type);
                folders.add(DEFAULT);
                Collections.sort(folders);
                String[] toReturn = folders.toArray(new String[0]);
                return toReturn;
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
                return new String[0];
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose() {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         * java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // TODO Auto-generated method stub

        }

    }

    private void openFolderSelectionDialog() {
        ListDialog dlg = new ListDialog(getShell());
        dlg.setInput(getRepositoryObjectType());
        dlg.setContentProvider(new FoldersContentProvider());
        dlg.setLabelProvider(new LabelProvider());
        dlg.setTitle(Messages.getString("PropertiesWizardPage.SelectfolderTitle")); //$NON-NLS-1$
        dlg.setMessage(Messages.getString("PropertiesWizardPage.SelectfolderMessage")); //$NON-NLS-1$

        String defaultValue = (pathText.getText().equals("") ? FoldersContentProvider.DEFAULT : pathText.getText()); //$NON-NLS-1$
        dlg.setInitialSelections(new String[] { defaultValue });

        if (dlg.open() == Window.OK) {
            String string = (String) dlg.getResult()[0];
            if (string.equals(FoldersContentProvider.DEFAULT)) {
                pathText.setText(""); //$NON-NLS-1$
            } else {
                pathText.setText(string);
                this.path = string;
            }
        }

    }

    public String[] toArray(List<org.talend.core.model.properties.Status> status) {
        String[] res = new String[status.size()];
        int i = 0;
        for (org.talend.core.model.properties.Status s : status) {
            res[i++] = s.getLabel();
        }
        return res;
    }

    protected void updateContent() {
        if (property != null) {
            nameText.setText(StringUtils.trimToEmpty(property.getLabel()));
            purposeText.setText(StringUtils.trimToEmpty(property.getPurpose()));
            descriptionText.setText(StringUtils.trimToEmpty(property.getDescription()));
            authorText.setText(property.getAuthor().getLogin());
            versionText.setText(property.getVersion());
            statusText.setText(statusHelper.getStatusLabel(property.getStatusCode()));
            if (destinationPath != null) {
                pathText.setText(destinationPath.toString());
            }
        }

        evaluateFields();
    }

    protected void addListeners() {
        nameText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                // if (!update) {
                if (nameText.getText().length() == 0) {
                    nameModifiedByUser = false;
                } else {
                    nameModifiedByUser = true;
                }
                // }
                evaluateTextField();
            }
        });

        purposeText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (purposeText.getText().length() == 0) {
                    purposeStatus = createStatus(IStatus.WARNING, Messages
                            .getString("PropertiesWizardPage.EmptyPurposeWarning")); //$NON-NLS-1$
                } else {
                    purposeStatus = createOkStatus();
                }
                property.setPurpose(StringUtils.trimToNull(purposeText.getText()));
                updatePageStatus();
            }
        });

        descriptionText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (descriptionText.getText().length() == 0) {
                    commentStatus = createStatus(IStatus.WARNING, Messages
                            .getString("PropertiesWizardPage.EmptyDescWarning")); //$NON-NLS-1$
                } else {
                    commentStatus = createOkStatus();
                }
                property.setDescription(StringUtils.trimToNull(descriptionText.getText()));
                updatePageStatus();
            }
        });

        versionMajorBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                String version = property.getVersion();
                version = VersionUtils.upMajor(version);
                versionText.setText(version);
                property.setVersion(version);
                updatePageStatus();
            }
        });

        versionMinorBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                String version = property.getVersion();
                version = VersionUtils.upMinor(version);
                versionText.setText(version);
                property.setVersion(version);
                updatePageStatus();
            }
        });

        statusText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                property.setStatusCode(statusHelper.getStatusCode(statusText.getText()));
                updatePageStatus();
            }

        });
    }

    protected void evaluateFields() {
        evaluateTextField();
    }

    protected void evaluateTextField() {
        if (nameText.getText().length() == 0) {
            nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.NameEmptyError")); //$NON-NLS-1$
        } else if (!Pattern.matches(RepositoryConstants.getPattern(getRepositoryObjectType()), nameText.getText())) {
            nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.NameFormatError")); //$NON-NLS-1$
        } else if (!isValid(nameText.getText()) && nameModifiedByUser) {
            nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.ItemExistsError")); //$NON-NLS-1$
        } else {
            nameStatus = createOkStatus();
        }
        if (property != null) {
            property.setLabel(StringUtils.trimToNull(nameText.getText()));
        }
        updatePageStatus();
    }

    protected IStatus[] getStatuses() {
        return new IStatus[] { nameStatus, purposeStatus, commentStatus };
    }

    protected static IStatus createOkStatus() {
        return new Status(IStatus.OK, CorePlugin.PLUGIN_ID, IStatus.OK, "", null); //$NON-NLS-1$
    }

    protected static IStatus createStatus(int severity, String message) {
        return new Status(severity, CorePlugin.PLUGIN_ID, IStatus.OK, message, null);
    }

    protected void updatePageStatus() {
        setMessage(findMostSevere());
        updatePageComplete();
    }

    protected void updatePageComplete() {
        setMessage(findMostSevere());
        setPageComplete(findMostSevere().getSeverity() != IStatus.ERROR);
    }

    protected IStatus findMostSevere() {
        IStatus[] statuses = getStatuses();
        IStatus severeStatus = statuses[0];
        for (IStatus status : statuses) {
            if (status.getSeverity() > severeStatus.getSeverity()) {
                severeStatus = status;
            }
        }
        return severeStatus;
    }

    protected void setMessage(IStatus status) {
        if (IStatus.ERROR == status.getSeverity()) {
            setErrorMessage(status.getMessage());
            // setMessage(""); //$NON-NLS-1$
        } else {
            if (StringUtils.isNotEmpty(status.getMessage())) {
                setMessage(status.getMessage(), status.getSeverity());
            } else {
                setMessage(getDescription());
            }
            setErrorMessage(null);
        }
    }

    public boolean isValid(String itemName) {

        IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(
                IRepositoryService.class);
        IProxyRepositoryFactory repositoryFactory = service.getProxyRepositoryFactory();
        try {
            return repositoryFactory.isNameAvailable(property.getItem(), itemName);
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
            return false;
        }
    }

    public abstract ERepositoryObjectType getRepositoryObjectType();

    public IPath getPathForSaveAsGenericSchema() {        
        if (this.path != null && path.length()> 0) {
            return new Path(path);
        }
        return null;

    }
}
