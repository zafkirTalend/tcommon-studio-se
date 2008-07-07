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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SearchPattern;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileManipulations;
import org.eclipse.ui.internal.wizards.datatransfer.DataTransferMessages;
import org.eclipse.ui.internal.wizards.datatransfer.TarException;
import org.eclipse.ui.internal.wizards.datatransfer.TarFile;
import org.eclipse.ui.internal.wizards.datatransfer.TarLeveledStructureProvider;
import org.eclipse.ui.internal.wizards.datatransfer.ZipLeveledStructureProvider;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.designer.codegen.ICodeGeneratorService;
import org.talend.designer.codegen.ITalendSynchronizer;
import org.talend.repository.documentation.IDocumentationService;
import org.talend.repository.localprovider.i18n.Messages;

/**
 * Initialy copied from org.eclipse.ui.internal.wizards.datatransfer.WizardProjectsImportPage.
 */
class ImportItemWizardPage extends WizardPage {

    private ImportItemUtil repositoryUtil = new ImportItemUtil();

    private Button itemFromDirectoryRadio;

    private Text directoryPathField;

    private Button browseDirectoriesButton;

    private Button itemFromArchiveRadio;

    private Text archivePathField;

    private Button browseArchivesButton;

    private String previouslyBrowsedDirectory = ""; //$NON-NLS-1$

    private Object lastPath;

    private ItemRecord[] selectedItems = new ItemRecord[0];

    private CheckboxTreeViewer itemsList;

    private String previouslyBrowsedArchive = ""; //$NON-NLS-1$

    protected boolean allVersions = false;

    private static final String[] FILE_IMPORT_MASK = { "*.jar;*.zip;*.tar;*.tar.gz;*.tgz", "*.*" }; //$NON-NLS-1$ //$NON-NLS-2$

    private Collection<ItemRecord> items = new ArrayList<ItemRecord>();

    private Label itemListInfo;

    private TableViewer errorsList;

    private List<String> errors = new ArrayList<String>();

    private ResourcesManager manager;

    private Text nameFilter;

    private Combo typeFilter;

    private Button overwriteButton;

    boolean overwrite = false;

    protected ImportItemWizardPage(String pageName) {
        super(pageName);
    }

    public void createControl(Composite parent) {
        Composite workArea = new Composite(parent, SWT.NONE);
        setControl(workArea);

        workArea.setLayout(new GridLayout());
        workArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        createItemRoot(workArea);
        // see feature 4395: Add an items filter in import/export job Dialog
        createFilter(workArea);
        createItemList(workArea);
        createErrorsList(workArea);

        // see feature 3949
        overwriteButton = new Button(workArea, SWT.CHECK);
        overwriteButton.setText("overwrite existing items");
        overwriteButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                overwrite = overwriteButton.getSelection();
                if (StringUtils.isNotEmpty(directoryPathField.getText()) || StringUtils.isNotEmpty(archivePathField.getText())) {
                    populateItems();
                }
            }

        });
    }

    /**
     * DOC hcw Comment method "createFilter".
     * 
     * @param workArea
     */
    private void createFilter(Composite workArea) {
        Composite filterComposite = new Composite(workArea, SWT.NONE);
        GridLayoutFactory.swtDefaults().numColumns(3).margins(0, 0).applyTo(filterComposite);
        GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(filterComposite);

        Label label = new Label(filterComposite, SWT.NONE);
        label.setText("Items Filter:");
        GridDataFactory.swtDefaults().applyTo(label);

        nameFilter = new Text(filterComposite, SWT.BORDER);
        nameFilter.setToolTipText("Enter type name prefix or pattern(*,?,or camel case).");
        nameFilter.setEditable(true);
        GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, true).applyTo(nameFilter);

        nameFilter.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                applyFilter();
            }
        });

        typeFilter = new Combo(filterComposite, SWT.READ_ONLY);
        GridDataFactory.swtDefaults().applyTo(typeFilter);
        typeFilter.setItems(getAvailableItems());
        typeFilter.select(0);
        typeFilter.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                applyFilter();
            }
        });
    }

    /**
     * DOC hcw Comment method "getAvailableItems".
     * 
     * @return
     */
    private String[] getAvailableItems() {
        boolean jobTemplateEnabled = (Platform.getBundle("org.talend.designer.jobtemplate") == null ? false : true);
        ERepositoryObjectType types[] = { ERepositoryObjectType.BUSINESS_PROCESS, ERepositoryObjectType.PROCESS,
                ERepositoryObjectType.JOBLET, ERepositoryObjectType.CONTEXT, ERepositoryObjectType.METADATA_CONNECTIONS,
                ERepositoryObjectType.METADATA_FILE_DELIMITED, ERepositoryObjectType.METADATA_FILE_POSITIONAL,
                ERepositoryObjectType.METADATA_FILE_REGEXP, ERepositoryObjectType.METADATA_FILE_XML,
                ERepositoryObjectType.METADATA_FILE_EXCEL, ERepositoryObjectType.METADATA_FILE_LDIF,
                ERepositoryObjectType.METADATA_LDAP_SCHEMA, ERepositoryObjectType.METADATA_GENERIC_SCHEMA,
                ERepositoryObjectType.METADATA_SALESFORCE_SCHEMA, ERepositoryObjectType.METADATA_WSDL_SCHEMA,
                ERepositoryObjectType.ROUTINES, ERepositoryObjectType.SQLPATTERNS };
        List<String> list = new ArrayList<String>(types.length + 1);
        list.add("All");
        for (ERepositoryObjectType type : types) {
            list.add(type.toString());
        }
        if (!jobTemplateEnabled) {
            // remove joblet
            list.remove(ERepositoryObjectType.JOBLET.toString());
        }
        return list.toArray(new String[list.size()]);
    }

    private void applyFilter() {
        itemsList.refresh(true);
        itemsList.setCheckedElements(checkValidItems());
    }

    private void createErrorsList(Composite workArea) {
        Composite composite = new Composite(workArea, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.makeColumnsEqualWidth = false;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
        gridData.heightHint = 100;
        composite.setLayoutData(gridData);

        Label title = new Label(composite, SWT.NONE);
        title.setText(Messages.getString("ImportItemWizardPage.ErrorsAndWarnings")); //$NON-NLS-1$
        title.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        errorsList = new TableViewer(composite, SWT.BORDER);
        errorsList.getControl().setLayoutData(gridData);

        errorsList.setContentProvider(new IStructuredContentProvider() {

            public void dispose() {
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            public Object[] getElements(Object inputElement) {
                return errors.toArray();
            }
        });

        errorsList.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object element) {
                return element.toString();
            }
        });

        errorsList.setInput(this);
        errorsList.setSorter(new ViewerSorter());
    }

    private void createItemList(Composite workArea) {
        Composite labelComposite = new Composite(workArea, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 0;
        layout.makeColumnsEqualWidth = false;
        labelComposite.setLayout(layout);

        Label title = new Label(labelComposite, SWT.NONE);
        title.setText(Messages.getString("ImportItemWizardPage.ItemsList")); //$NON-NLS-1$

        itemListInfo = new Label(labelComposite, SWT.NONE);
        itemListInfo.setForeground(new Color(null, 255, 0, 0)); // red
        itemListInfo.setText(Messages.getString("ImportItemWizardPage.NoValidItems")); //$NON-NLS-1$
        itemListInfo.setVisible(false);

        Composite listComposite = new Composite(workArea, SWT.NONE);
        GridLayout layout2 = new GridLayout();
        layout2.numColumns = 2;
        layout2.marginWidth = 0;
        layout2.makeColumnsEqualWidth = false;
        listComposite.setLayout(layout2);

        GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
        gridData.heightHint = 100;
        listComposite.setLayoutData(gridData);

        itemsList = new CheckboxTreeViewer(listComposite, SWT.BORDER);
        GridData listData = gridData;
        itemsList.getControl().setLayoutData(listData);

        itemsList.setContentProvider(new ITreeContentProvider() {

            public Object[] getChildren(Object parentElement) {
                return null;
            }

            public Object[] getElements(Object inputElement) {
                return getValidItems();
            }

            public boolean hasChildren(Object element) {
                return false;
            }

            public Object getParent(Object element) {
                return null;
            }

            public void dispose() {

            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

        });

        itemsList.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object element) {
                return ((ItemRecord) element).getItemName();
            }
        });

        itemsList.setInput(this);
        itemsList.setSorter(new ViewerSorter());
        createSelectionButtons(listComposite);

    }

    private void createSelectionButtons(Composite listComposite) {
        Composite buttonsComposite = new Composite(listComposite, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        buttonsComposite.setLayout(layout);

        buttonsComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        Button selectAll = new Button(buttonsComposite, SWT.PUSH);
        selectAll.setText(DataTransferMessages.DataTransfer_selectAll);
        selectAll.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                itemsList.setCheckedElements(selectedItems);
            }
        });
        Dialog.applyDialogFont(selectAll);
        setButtonLayoutData(selectAll);

        Button deselectAll = new Button(buttonsComposite, SWT.PUSH);
        deselectAll.setText(DataTransferMessages.DataTransfer_deselectAll);
        deselectAll.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                itemsList.setCheckedElements(new Object[0]);
            }
        });
        Dialog.applyDialogFont(deselectAll);
        setButtonLayoutData(deselectAll);

        Button refresh = new Button(buttonsComposite, SWT.PUSH);
        refresh.setText(DataTransferMessages.DataTransfer_refresh);
        refresh.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (itemFromDirectoryRadio.getSelection()) {
                    updateItemsList(directoryPathField.getText().trim());
                } else {
                    updateItemsList(archivePathField.getText().trim());
                }
            }
        });
        Dialog.applyDialogFont(refresh);
        setButtonLayoutData(refresh);
    }

    private void createItemRoot(Composite workArea) {
        Composite projectGroup = new Composite(workArea, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        layout.makeColumnsEqualWidth = false;
        layout.marginWidth = 0;
        projectGroup.setLayout(layout);
        projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        itemFromDirectoryRadio = new Button(projectGroup, SWT.RADIO);
        itemFromDirectoryRadio.setText(DataTransferMessages.WizardProjectsImportPage_RootSelectTitle);

        this.directoryPathField = new Text(projectGroup, SWT.BORDER);

        this.directoryPathField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

        browseDirectoriesButton = new Button(projectGroup, SWT.PUSH);
        browseDirectoriesButton.setText(DataTransferMessages.DataTransfer_browse);
        setButtonLayoutData(browseDirectoriesButton);

        // new project from archive radio button
        itemFromArchiveRadio = new Button(projectGroup, SWT.RADIO);
        itemFromArchiveRadio.setText(DataTransferMessages.WizardProjectsImportPage_ArchiveSelectTitle);

        // project location entry field
        archivePathField = new Text(projectGroup, SWT.BORDER);

        archivePathField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
        // browse button
        browseArchivesButton = new Button(projectGroup, SWT.PUSH);
        browseArchivesButton.setText(DataTransferMessages.DataTransfer_browse);
        setButtonLayoutData(browseArchivesButton);

        itemFromDirectoryRadio.setSelection(true);
        archivePathField.setEnabled(false);
        browseArchivesButton.setEnabled(false);

        browseDirectoriesButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                handleLocationDirectoryButtonPressed();
            }
        });

        browseArchivesButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                handleLocationArchiveButtonPressed();
            }
        });

        directoryPathField.addTraverseListener(new TraverseListener() {

            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                    updateItemsList(directoryPathField.getText().trim());
                }
            }

        });

        directoryPathField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(org.eclipse.swt.events.FocusEvent e) {
                updateItemsList(directoryPathField.getText().trim());
            }

        });

        archivePathField.addTraverseListener(new TraverseListener() {

            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                    updateItemsList(archivePathField.getText().trim());
                }
            }
        });

        archivePathField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(org.eclipse.swt.events.FocusEvent e) {
                updateItemsList(archivePathField.getText().trim());
            }
        });

        itemFromDirectoryRadio.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                directoryRadioSelected();
            }
        });

        itemFromArchiveRadio.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                archiveRadioSelected();
            }
        });
    }

    private void archiveRadioSelected() {
        if (itemFromArchiveRadio.getSelection()) {
            directoryPathField.setEnabled(false);
            browseDirectoriesButton.setEnabled(false);
            archivePathField.setEnabled(true);
            browseArchivesButton.setEnabled(true);
            updateItemsList(archivePathField.getText());
            archivePathField.setFocus();
        }
    }

    protected void handleLocationDirectoryButtonPressed() {

        DirectoryDialog dialog = new DirectoryDialog(directoryPathField.getShell());
        dialog.setMessage(DataTransferMessages.WizardProjectsImportPage_SelectDialogTitle);

        String dirName = directoryPathField.getText().trim();
        if (dirName.length() == 0) {
            dirName = previouslyBrowsedDirectory;
        }

        if (dirName.length() == 0) {
            dialog.setFilterPath(IDEWorkbenchPlugin.getPluginWorkspace().getRoot().getLocation().toOSString());
        } else {
            File path = new File(dirName);
            if (path.exists()) {
                dialog.setFilterPath(new Path(dirName).toOSString());
            }
        }

        String selectedDirectory = dialog.open();
        if (selectedDirectory != null) {
            previouslyBrowsedDirectory = selectedDirectory;
            directoryPathField.setText(previouslyBrowsedDirectory);
            updateItemsList(selectedDirectory);
        }

    }

    /**
     * The browse button has been selected. Select the location.
     */
    protected void handleLocationArchiveButtonPressed() {

        FileDialog dialog = new FileDialog(archivePathField.getShell());
        dialog.setFilterExtensions(FILE_IMPORT_MASK);
        dialog.setText(DataTransferMessages.WizardProjectsImportPage_SelectArchiveDialogTitle);

        String fileName = archivePathField.getText().trim();
        if (fileName.length() == 0) {
            fileName = previouslyBrowsedArchive;
        }

        if (fileName.length() == 0) {
            dialog.setFilterPath(IDEWorkbenchPlugin.getPluginWorkspace().getRoot().getLocation().toOSString());
        } else {
            File path = new File(fileName);
            if (path.exists()) {
                dialog.setFilterPath(new Path(fileName).toOSString());
            }
        }

        String selectedArchive = dialog.open();
        if (selectedArchive != null) {
            previouslyBrowsedArchive = selectedArchive;
            archivePathField.setText(previouslyBrowsedArchive);
            updateItemsList(selectedArchive);
        }

    }

    private void directoryRadioSelected() {
        if (itemFromDirectoryRadio.getSelection()) {
            directoryPathField.setEnabled(true);
            browseDirectoriesButton.setEnabled(true);
            archivePathField.setEnabled(false);
            browseArchivesButton.setEnabled(false);
            updateItemsList(directoryPathField.getText());
            directoryPathField.setFocus();
        }
    }

    public void updateItemsList(final String path) {

        if (path.equals(lastPath)) {
            return;
        }

        lastPath = path;

        if (path == null || path.length() == 0) {
            selectedItems = new ItemRecord[0];
            itemsList.refresh(true);
            itemsList.setCheckedElements(selectedItems);
            checkValidItems();
            return;
        }

        items.clear();

        final boolean dirSelected = this.itemFromDirectoryRadio.getSelection();
        try {
            getContainer().run(true, true, new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) {

                    monitor.beginTask(DataTransferMessages.WizardProjectsImportPage_SearchingMessage, 100);
                    File directory = new File(path);
                    monitor.worked(10);
                    if (!dirSelected && ArchiveFileManipulations.isTarFile(path)) {
                        TarFile sourceTarFile = getSpecifiedTarSourceFile(path);
                        if (sourceTarFile == null) {
                            return;
                        }

                        TarLeveledStructureProvider provider = ArchiveFileManipulations.getTarStructureProvider(sourceTarFile,
                                getContainer().getShell());
                        manager = ResourcesManagerFactory.getInstance().createResourcesManager(provider);

                        if (!manager.collectPath2Object(provider.getRoot())) {
                            return;
                        }
                    } else if (!dirSelected && ArchiveFileManipulations.isZipFile(path)) {
                        ZipFile sourceFile = getSpecifiedZipSourceFile(path);
                        if (sourceFile == null) {
                            return;
                        }
                        ZipLeveledStructureProvider provider = ArchiveFileManipulations.getZipStructureProvider(sourceFile,
                                getContainer().getShell());
                        manager = ResourcesManagerFactory.getInstance().createResourcesManager(provider);

                        if (!manager.collectPath2Object(provider.getRoot())) {
                            return;
                        }
                    } else if (dirSelected && directory.isDirectory()) {
                        manager = ResourcesManagerFactory.getInstance().createResourcesManager();

                        if (!manager.collectPath2Object(directory)) {
                            return;
                        }
                    } else {
                        monitor.worked(60);
                    }
                    monitor.done();
                }

            });
        } catch (InvocationTargetException e) {
            IDEWorkbenchPlugin.log(e.getMessage(), e);
        } catch (InterruptedException e) {
            // Nothing to do if the user interrupts.
        }

        populateItems();
    }

    /**
     * DOC hcw Comment method "populateItems".
     */
    private void populateItems() {
        items = repositoryUtil.populateItems(manager, overwrite);

        errors.clear();
        for (ItemRecord itemRecord : items) {
            for (String error : itemRecord.getErrors()) {
                errors.add("'" + itemRecord.getItemName() + "' " + error);
            }
        }
        if (errorsList != null) {
            errorsList.refresh();
        }

        selectedItems = new ItemRecord[items.size()];
        int index = 0;
        for (ItemRecord itemRecord : items) {
            selectedItems[index++] = itemRecord;
        }

        itemsList.refresh(true);
        itemsList.setCheckedElements(checkValidItems());
    }

    private ItemRecord[] checkValidItems() {
        ItemRecord[] validItems = getValidItems();
        boolean hasValidItems = validItems.length > 0;

        setPageComplete(hasValidItems);
        itemListInfo.setVisible(!hasValidItems);
        return validItems;
    }

    protected ZipFile getSpecifiedZipSourceFile() {
        return getSpecifiedZipSourceFile(archivePathField.getText());
    }

    private ZipFile getSpecifiedZipSourceFile(String fileName) {
        if (fileName.length() == 0) {
            return null;
        }

        try {
            return new ZipFile(fileName);
        } catch (ZipException e) {
            displayErrorDialog(DataTransferMessages.ZipImport_badFormat);
        } catch (IOException e) {
            displayErrorDialog(DataTransferMessages.ZipImport_couldNotRead);
        }

        archivePathField.setFocus();
        return null;
    }

    protected void displayErrorDialog(String message) {
        MessageDialog.openError(getContainer().getShell(), getErrorDialogTitle(), message);
    }

    protected String getErrorDialogTitle() {
        return IDEWorkbenchMessages.WizardExportPage_internalErrorTitle;
    }

    protected TarFile getSpecifiedTarSourceFile() {
        return getSpecifiedTarSourceFile(archivePathField.getText());
    }

    private TarFile getSpecifiedTarSourceFile(String fileName) {
        if (fileName.length() == 0) {
            return null;
        }

        try {
            return new TarFile(fileName);
        } catch (TarException e) {
            displayErrorDialog(DataTransferMessages.TarImport_badFormat);
        } catch (IOException e) {
            displayErrorDialog(DataTransferMessages.ZipImport_couldNotRead);
        }

        archivePathField.setFocus();
        return null;
    }

    public ItemRecord[] getValidItems() {
        String type = typeFilter.getItem(typeFilter.getSelectionIndex());

        String name = nameFilter.getText();
        SearchPattern matcher = new SearchPattern();
        matcher.setPattern(name);

        List validItems = new ArrayList();
        for (int i = 0; i < selectedItems.length; i++) {
            ItemRecord itemRecord = selectedItems[i];
            if (itemRecord.isValid() && matcher.matches(itemRecord.getProperty().getLabel())) {
                String itemType = ERepositoryObjectType.getItemType(itemRecord.getItem()).toString();
                if (type.equals("All") || type.equals(itemType)) {
                    validItems.add(itemRecord);
                }
            }
        }
        return (ItemRecord[]) validItems.toArray(new ItemRecord[validItems.size()]);
    }

    public boolean performFinish() {

        final Object[] checkedElements = itemsList.getCheckedElements();

        try {
            IRunnableWithProgress op = new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.getString("ImportItemWizardPage.ImportSelectedItems"), checkedElements.length + 1); //$NON-NLS-1$

                    repositoryUtil.setErrors(false);
                    repositoryUtil.clear();

                    ITalendSynchronizer routineSynchronizer = getRoutineSynchronizer();

                    for (int i = 0; i < checkedElements.length; i++) {
                        if (!monitor.isCanceled()) {
                            ItemRecord itemRecord = (ItemRecord) checkedElements[i];

                            monitor.subTask(Messages.getString("ImportItemWizardPage.Importing") + itemRecord.getItemName()); //$NON-NLS-1$

                            try {
                                Item importItem = repositoryUtil.importItemRecord(manager, itemRecord, overwrite);

                                // Generated documentaiton for imported item.
                                if (importItem != null && PluginChecker.isDocumentationPluginLoaded()) {
                                    IDocumentationService service = (IDocumentationService) GlobalServiceRegister.getDefault()
                                            .getService(IDocumentationService.class);
                                    if (importItem instanceof ProcessItem) {
                                        service.saveDocumentNode(importItem);
                                    } else if (importItem instanceof JobletProcessItem && PluginChecker.isJobLetPluginLoaded()) {
                                        service.saveDocumentNode(importItem);
                                    }

                                }

                                if (importItem != null && importItem instanceof RoutineItem) {
                                    RoutineItem item = (RoutineItem) importItem;
                                    routineSynchronizer.syncRoutine(item, true);
                                    routineSynchronizer.getFile(item);
                                }

                            } catch (Exception e) {
                                throw new InvocationTargetException(e);
                            }

                            monitor.worked(1);
                        }
                    }

                    monitor.done();

                    if (repositoryUtil.hasErrors()) {
                        throw new InvocationTargetException(new PersistenceException("")); //$NON-NLS-1$
                    }
                }

            };
            new ProgressMonitorDialog(getShell()).run(true, true, op);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof PersistenceException) {
                MessageDialog.openWarning(getShell(), Messages.getString("ImportItemWizardPage.ImportSelectedItems"), //$NON-NLS-1$
                        Messages.getString("ImportItemWizardPage.ErrorsOccured")); //$NON-NLS-1$
            }
        } catch (InterruptedException e) {
            //
        }

        ArchiveFileManipulations.clearProviderCache(getContainer().getShell());
        return true;
    }

    private ITalendSynchronizer getRoutineSynchronizer() {

        ICodeGeneratorService service = (ICodeGeneratorService) GlobalServiceRegister.getDefault().getService(
                ICodeGeneratorService.class);

        ECodeLanguage lang = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getProject().getLanguage();
        ITalendSynchronizer routineSynchronizer = null;
        switch (lang) {
        case JAVA:
            routineSynchronizer = service.createJavaRoutineSynchronizer();
            break;
        case PERL:
            routineSynchronizer = service.createPerlRoutineSynchronizer();
            break;
        default:
            throw new UnsupportedOperationException("Unknow language: " + lang);
        }

        return routineSynchronizer;

    }

    public boolean performCancel() {
        ArchiveFileManipulations.clearProviderCache(getContainer().getShell());
        return true;
    }
}
