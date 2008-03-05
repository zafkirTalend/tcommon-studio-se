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
package org.talend.dataprofiler.core.ui.wizard;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
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
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;
import org.talend.cwm.db.connection.ConnectionParameters;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.ui.dialog.FolderSelectionDialog;
import org.talend.dataprofiler.core.ui.dialog.TypedViewerFilter;

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

    private IPath destinationPath;

    private String path;

    private boolean readOnly;

    private boolean editPath = true;

    protected PropertiesWizardPage(String pageName, ConnectionParameters property, IPath destinationPath) {
        this(pageName, property, destinationPath, false, true);
    }

    protected PropertiesWizardPage(String pageName, ConnectionParameters property, IPath destinationPath, boolean readOnly,
            boolean editPath) {
        super(pageName);
        // IRepositoryService service = (IRepositoryService)
        // GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        this.destinationPath = destinationPath;

        this.readOnly = readOnly;
        this.editPath = editPath;

        nameStatus = createOkStatus();
        purposeStatus = createOkStatus();
        commentStatus = createOkStatus();
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
        nameLab.setText("Name");

        nameText = new Text(parent, SWT.BORDER);
        nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        nameText.setEditable(!readOnly);

        // Purpose
        Label purposeLab = new Label(parent, SWT.NONE);
        purposeLab.setText("Purpose");

        purposeText = new Text(parent, SWT.BORDER);
        purposeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        purposeText.setEditable(!readOnly);

        // Description
        Label descriptionLab = new Label(parent, SWT.NONE);
        descriptionLab.setText("Description");
        descriptionLab.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        descriptionText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.heightHint = 60;
        descriptionText.setLayoutData(data);
        descriptionText.setEditable(!readOnly);

        // Author
        Label authorLab = new Label(parent, SWT.NONE);
        authorLab.setText("Author");

        authorText = new Text(parent, SWT.BORDER);
        authorText.setEnabled(false);
        authorText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // Version
        Label versionLab = new Label(parent, SWT.NONE);
        versionLab.setText("Version");

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
        versionMajorBtn.setText("M");
        versionMajorBtn.setEnabled(!readOnly);

        versionMinorBtn = new Button(versionContainer, SWT.PUSH);
        versionMinorBtn.setText("m"); //$NON-NLS-1$
        versionMinorBtn.setEnabled(!readOnly);

        // Status
        Label statusLab = new Label(parent, SWT.NONE);
        statusLab.setText("Status"); //$NON-NLS-1$

        statusText = new CCombo(parent, SWT.BORDER);
        // statusText.setItems(toArray(statusList));
        statusText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        statusText.setEditable(!readOnly);
        statusText.setEnabled(!readOnly);

        // Path:
        Label pathLab = new Label(parent, SWT.NONE);
        pathLab.setText("Path"); //$NON-NLS-1$

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
            button.setText("Select.."); 

            button.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    openFolderSelectionDialog();
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    private void openFolderSelectionDialog() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        final Class[] acceptedClasses = new Class[] { IProject.class, IFolder.class };
        IProject[] allProjects = root.getProjects();
        ArrayList rejectedElements = new ArrayList(allProjects.length);
        for (int i = 0; i < allProjects.length; i++) {
            if (!allProjects[i].equals(ResourcesPlugin.getWorkspace().getRoot().getProject("Metadata"))) {
                rejectedElements.add(allProjects[i]);
            }
        }
        ViewerFilter filter = new TypedViewerFilter(acceptedClasses, rejectedElements.toArray());
        
        
        ILabelProvider lp = new WorkbenchLabelProvider();
        ITreeContentProvider cp = new WorkbenchContentProvider();

        FolderSelectionDialog dialog = new FolderSelectionDialog(getShell(), lp, cp);
        // dialog.setValidator(validator);
        dialog.setTitle("Select folder");
        dialog.setMessage("Select the folder in which the item will be created");
        // dialog.addFilter(filter);
        dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
        dialog.addFilter(filter);
        dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));

        if (dialog.open() == Window.OK) {
            Object elements = dialog.getResult()[0];
            IResource elem = (IResource) elements;
            String name = elem.getName();
            pathText.setText(name);
            this.path = name;
        }

    }

    // protected void updateContent() {
    // if (property != null) {
    // nameText.setText(StringUtils.trimToEmpty(property.getLabel()));
    // purposeText.setText(StringUtils.trimToEmpty(property.getPurpose()));
    // descriptionText.setText(StringUtils.trimToEmpty(property.getDescription()));
    // authorText.setText(StringUtils.trimToEmpty(property.getAuthor().getLogin()));
    // versionText.setText(property.getVersion());
    // statusText.setText(statusHelper.getStatusLabel(property.getStatusCode()));
    // if (destinationPath != null) {
    // pathText.setText(destinationPath.toString());
    // }
    // }
    //
    // evaluateFields();
    // }

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
                    purposeStatus = createStatus(IStatus.WARNING, "Empty purpose is discouraged.");
                } else {
                    purposeStatus = createOkStatus();
                }
                // property.setPurpose(StringUtils.trimToNull(purposeText.getText()));
                updatePageStatus();
            }
        });

        descriptionText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (descriptionText.getText().length() == 0) {
                    commentStatus = createStatus(IStatus.WARNING, "Empty description is discouraged.");
                } else {
                    commentStatus = createOkStatus();
                }
                // property.setDescription(StringUtils.trimToNull(descriptionText.getText()));
                updatePageStatus();
            }
        });

        versionMajorBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // String version = property.getVersion();
                // version = VersionUtils.upMajor(version);
                versionText.setText("");
                // property.setVersion(version);
                updatePageStatus();
            }
        });

        versionMinorBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // String version = property.getVersion();
                // version = VersionUtils.upMinor(version);
                // versionText.setText(version);
                // property.setVersion(version);
                updatePageStatus();
            }
        });

        statusText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                // property.setStatusCode(statusHelper.getStatusCode(statusText.getText()));
                updatePageStatus();
            }

        });
    }

    protected void evaluateFields() {
        evaluateTextField();
    }

    protected void evaluateTextField() {
        if (nameText.getText().length() == 0) {
            nameStatus = createStatus(IStatus.ERROR, "Name is empty.");
            // } else if (!Pattern.matches(RepositoryConstants.getPattern(getRepositoryObjectType()),
            // nameText.getText())) {
            // nameStatus = createStatus(IStatus.ERROR, "Name contains incorrect characters.");
            // } else if (isKeywords(nameText.getText())) {
            // nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.KeywordsError"));
            // } else if (!isValid(nameText.getText()) && nameModifiedByUser) {
            // String str = Messages.getString("PropertiesWizardPage.ItemExistsError");
            // nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.ItemExistsError"));
        } else {
            nameStatus = createOkStatus();
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

        // IRepositoryService service = (IRepositoryService)
        // GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        // IProxyRepositoryFactory repositoryFactory = service.getProxyRepositoryFactory();
        // try {
        // return repositoryFactory.isNameAvailable(property.getItem(), itemName);
        // } catch (PersistenceException e) {
        // ExceptionHandler.process(e);
        // return false;
        // }
        return true;
    }

    public IPath getPathForSaveAsGenericSchema() {
        if (this.path != null && path.length() > 0) {
            return new Path(path);
        }
        return null;

    }
}
