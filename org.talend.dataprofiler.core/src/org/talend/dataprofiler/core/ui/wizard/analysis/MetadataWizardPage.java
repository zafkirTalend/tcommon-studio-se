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
package org.talend.dataprofiler.core.ui.wizard.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
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
import org.talend.cwm.constants.DevelopmentStatus;
import org.talend.cwm.management.api.FolderProvider;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.manager.DQStructureManager;
import org.talend.dataprofiler.core.ui.dialog.FolderSelectionDialog;
import org.talend.dataprofiler.core.ui.dialog.filter.TypedViewerFilter;
import org.talend.dq.analysis.parameters.IAnalysisParameterConstant;

/**
 * @author zqin
 * 
 */
public class MetadataWizardPage extends AbstractAnalysisWizardPage {


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
    
    private Text typeText;
    
    private Text pathText;
    /** Status text. */
    // protected Text statusText;
    private CCombo statusText;

    /** Version upgrade major button. */
    private Button versionMajorBtn;

    /** Version upgrade minor button. */
    private Button versionMinorBtn;
    
    private String path;

    private boolean readOnly;

    private boolean editPath = true;

    private IFolder defaultFolderProviderRes;
    
    private HashMap<String, String> analysisMetadate;
    
    public MetadataWizardPage() {
        setTitle("New Analysis");
        setDescription("Adds an analysis in the repository.");
        setPageComplete(false);
        
        analysisMetadate = new HashMap<String, String>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataprofiler.core.ui.wizard.PropertiesWizardPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        // TODO Auto-generated method stub
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout gdLayout = new GridLayout(2, false);
        container.setLayout(gdLayout);

        GridData data;

        // Name
        Label nameLab = new Label(container, SWT.NONE);
        nameLab.setText("Name");

        nameText = new Text(container, SWT.BORDER);
        nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        nameText.setEditable(!readOnly);

        // Purpose
        Label purposeLab = new Label(container, SWT.NONE);
        purposeLab.setText("Purpose");

        purposeText = new Text(container, SWT.BORDER);
        purposeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        purposeText.setEditable(!readOnly);

        // Description
        Label descriptionLab = new Label(container, SWT.NONE);
        descriptionLab.setText("Description");
        descriptionLab.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        descriptionText = new Text(container, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.heightHint = 60;
        descriptionText.setLayoutData(data);
        descriptionText.setEditable(!readOnly);

        // Author
        Label authorLab = new Label(container, SWT.NONE);
        authorLab.setText("Author");

        authorText = new Text(container, SWT.BORDER);
        authorText.setEnabled(!readOnly);
        authorText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // Version
        Label versionLab = new Label(container, SWT.NONE);
        versionLab.setText("Version");

        Composite versionContainer = new Composite(container, SWT.NONE);
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
        Label statusLab = new Label(container, SWT.NONE);
        statusLab.setText("Status"); //$NON-NLS-1$

        statusText = new CCombo(container, SWT.BORDER);
        // statusText.setItems(toArray(statusList));
        statusText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        statusText.setEditable(!readOnly);
        statusText.setEnabled(!readOnly);
        
        for (DevelopmentStatus status : DevelopmentStatus.values()) {
            statusText.add(status.getName());
        }

        // Path:
        Label pathLab = new Label(container, SWT.NONE);
        pathLab.setText("Path"); //$NON-NLS-1$

        Composite pathContainer = new Composite(container, SWT.NONE);
        pathContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout pathLayout = new GridLayout(2, false);
        pathLayout.marginHeight = 0;
        pathLayout.marginWidth = 0;
        pathLayout.horizontalSpacing = 0;
        pathContainer.setLayout(pathLayout);

        pathText = new Text(pathContainer, SWT.BORDER);
        pathText.setEnabled(false);
        pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // MODSCA 2008-03-10 use DQStructureManager.DB_CONNECTIONS constant instead of hard coded "Db Connections"

        if (editPath) {
            Button button = new Button(pathContainer, SWT.PUSH);
            button.setText("Select..");

            button.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {

                    openFolderSelectionDialog(DQStructureManager.DATA_PROFILING, DQStructureManager.ANALYSIS);
                }
            });
        }
        
        defaultFolderProviderRes = ResourcesPlugin.getWorkspace().getRoot().getProject(PluginConstant.DATA_PROFILING_PROJECTNAME).getFolder(
                DQStructureManager.ANALYSIS);
        pathText.setText(defaultFolderProviderRes.getFullPath().toString());

        Label typeLabel = new Label(container, SWT.NONE);
        typeLabel.setText("Type");

        typeText = new Text(container, SWT.BORDER);

        GridData dataForTypeText = new GridData();
        dataForTypeText.widthHint = 200;
        typeText.setLayoutData(dataForTypeText);
        typeText.setEnabled(false);

        setControl(container);
        addListeners();
    }
    
    @SuppressWarnings("unchecked")
    private void openFolderSelectionDialog(String projectName , String folderName) {
        
        final Class[] acceptedClasses = new Class[] { IProject.class, IFolder.class }; 
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        ArrayList rejectedElements = new ArrayList();
        
        if (projectName != null) {
            IProject theProject = root.getProject(projectName);
            IProject[] allProjects = root.getProjects();
            for (int i = 0; i < allProjects.length; i++) {          
                if (!allProjects[i].equals(theProject)) {
                    rejectedElements.add(allProjects[i]);
                }
            }
            
            if (folderName != null) {
                try {
                    IResource[] resourse = theProject.members();
                    for (IResource one : resourse) {
                        if (one.getType() == IResource.FOLDER && !one.getName().equals(folderName)) {
                            rejectedElements.add(one);
                        }
                    }
                } catch (Exception e) { }
            }
        }

        ViewerFilter filter = new TypedViewerFilter(acceptedClasses, rejectedElements.toArray());

        ILabelProvider lp = new WorkbenchLabelProvider();
        ITreeContentProvider cp = new WorkbenchContentProvider();

        FolderSelectionDialog dialog = new FolderSelectionDialog(getShell(), lp, cp);
        // dialog.setValidator(validator);
        dialog.setTitle("Select folder");
        dialog.setMessage("Select the folder in which the item will be created");
        dialog.setInput(root);
        dialog.addFilter(filter);
        dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));

        if (dialog.open() == Window.OK) {
            Object elements = dialog.getResult()[0];
            IResource elem = (IResource) elements;
            if (elem instanceof IFolder) {
                pathText.setText(elem.getLocation().toString());

                FolderProvider provider = new FolderProvider();
                provider.setFolder(new File(elem.getLocationURI()));
                getConnectionParams().setFolderProvider(provider);
            }
        }
    }
    
    protected void addListeners() {
        nameText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                analysisMetadate.put(IAnalysisParameterConstant.ANALYSIS_NAME, ((Text) e.getSource()).getText());
                getConnectionParams().setAnalysisMetadate(analysisMetadate);
                
                setPageComplete(true);
            }
        });

        purposeText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (purposeText.getText().length() == 0) {
                    analysisMetadate.put(IAnalysisParameterConstant.ANALYSIS_PURPOSE, ((Text) e.getSource()).getText());
                    getConnectionParams().setAnalysisMetadate(analysisMetadate);
                }
            }
        });

        descriptionText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (descriptionText.getText().length() == 0) {
                    analysisMetadate.put(IAnalysisParameterConstant.ANALYSIS_DESCRIPTION, ((Text) e.getSource()).getText());
                    getConnectionParams().setAnalysisMetadate(analysisMetadate);
                }

            }
        });

        versionMajorBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });

        versionMinorBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });

        statusText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                String selected = ((CCombo) e.getSource()).getText();
                analysisMetadate.put(IAnalysisParameterConstant.ANALYSIS_STATUS, selected);
                getConnectionParams().setAnalysisMetadate(analysisMetadate);
            }

        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {

        String typeName = getConnectionParams().getAnalysisTypeName();

        if (typeName != null) {
            typeText.setText(typeName);
        }
        
        FolderProvider defaultFolder = new FolderProvider();
        defaultFolder.setFolder(new File(defaultFolderProviderRes.getLocationURI()));
        getConnectionParams().setFolderProvider(defaultFolder);

        super.setVisible(visible);
    }
    
}
