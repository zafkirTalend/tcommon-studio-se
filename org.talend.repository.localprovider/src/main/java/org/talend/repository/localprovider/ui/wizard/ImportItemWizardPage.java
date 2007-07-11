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
package org.talend.repository.localprovider.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.ide.IDEApplication;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileManipulations;
import org.eclipse.ui.internal.wizards.datatransfer.DataTransferMessages;
import org.eclipse.ui.internal.wizards.datatransfer.TarException;
import org.eclipse.ui.internal.wizards.datatransfer.TarFile;
import org.eclipse.ui.internal.wizards.datatransfer.TarLeveledStructureProvider;
import org.eclipse.ui.internal.wizards.datatransfer.ZipLeveledStructureProvider;
import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;
import org.talend.commons.exception.PersistenceException;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.localprovider.ui.wizard.ResourcesManagerFactory.ResourcesManager;

/**
 * Initialy copied from org.eclipse.ui.internal.wizards.datatransfer.WizardProjectsImportPage.
 */
class ImportItemWizardPage extends WizardPage {

    private RepositoryUtil repositoryUtil = new RepositoryUtil();

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

    protected ImportItemWizardPage(String pageName) {
        super(pageName);
    }

    public void createControl(Composite parent) {
        Composite workArea = new Composite(parent, SWT.NONE);
        setControl(workArea);

        workArea.setLayout(new GridLayout());
        workArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        createItemRoot(workArea);
        createItemList(workArea);
        createErrorsList(workArea);
    }

    private void createErrorsList(Composite workArea) {
        Composite composite = new Composite(workArea, SWT.NONE);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        composite.setLayout(rowLayout);

        Label title = new Label(composite, SWT.NONE);
        title.setText(Messages.getString("ImportItemWizardPage.ErrorsAndWarnings")); //$NON-NLS-1$
        title.setLayoutData(new RowData(500, 20));

        errorsList = new TableViewer(composite, SWT.BORDER);
        errorsList.getControl().setLayoutData(new RowData(500, 100));

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

        listComposite
                .setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH));

        itemsList = new CheckboxTreeViewer(listComposite, SWT.BORDER);
        GridData listData = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
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

            public void widgetSelected(SelectionEvent e) {
                itemsList.setCheckedElements(selectedItems);
            }
        });
        Dialog.applyDialogFont(selectAll);
        setButtonLayoutData(selectAll);

        Button deselectAll = new Button(buttonsComposite, SWT.PUSH);
        deselectAll.setText(DataTransferMessages.DataTransfer_deselectAll);
        deselectAll.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                itemsList.setCheckedElements(new Object[0]);
            }
        });
        Dialog.applyDialogFont(deselectAll);
        setButtonLayoutData(deselectAll);

        Button refresh = new Button(buttonsComposite, SWT.PUSH);
        refresh.setText(DataTransferMessages.DataTransfer_refresh);
        refresh.addSelectionListener(new SelectionAdapter() {

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

            public void widgetSelected(SelectionEvent e) {
                handleLocationDirectoryButtonPressed();
            }
        });

        browseArchivesButton.addSelectionListener(new SelectionAdapter() {

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

            public void focusLost(org.eclipse.swt.events.FocusEvent e) {
                updateItemsList(archivePathField.getText().trim());
            }
        });

        itemFromDirectoryRadio.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                directoryRadioSelected();
            }
        });

        itemFromArchiveRadio.addSelectionListener(new SelectionAdapter() {

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
            hasValidItems();
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

                        TarLeveledStructureProvider provider = ArchiveFileManipulations.getTarStructureProvider(
                                sourceTarFile, getContainer().getShell());
                        Object child = provider.getRoot();

                        manager = ResourcesManagerFactory.getInstance().createResourcesManager(provider);

                        if (!collectItemFilesFromProvider(items, provider, child, 0, monitor, manager)) {
                            return;
                        }
                    } else if (!dirSelected && ArchiveFileManipulations.isZipFile(path)) {
                        ZipFile sourceFile = getSpecifiedZipSourceFile(path);
                        if (sourceFile == null) {
                            return;
                        }
                        ZipLeveledStructureProvider provider = ArchiveFileManipulations.getZipStructureProvider(
                                sourceFile, getContainer().getShell());
                        Object child = provider.getRoot();

                        manager = ResourcesManagerFactory.getInstance().createResourcesManager(provider);

                        if (!collectItemFilesFromProvider(items, provider, child, 0, monitor, manager)) {
                            return;
                        }
                    } else if (dirSelected && directory.isDirectory()) {
                        manager = ResourcesManagerFactory.getInstance().createResourcesManager();

                        if (!collectItemFilesFromDirectory(items, directory, monitor, manager)) {
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

        errors.clear();
        items = repositoryUtil.populateItems(manager, errors);
        if (errorsList != null) {
            errorsList.refresh();
        }

        selectedItems = new ItemRecord[items.size()];
        int index = 0;
        for (ItemRecord itemRecord : items) {
            selectedItems[index++] = itemRecord;
        }

        itemsList.refresh(true);
        itemsList.setCheckedElements(getValidItems());
        hasValidItems();
    }

    private void hasValidItems() {
        boolean hasValidItems = getValidItems().length > 0;

        setPageComplete(hasValidItems);
        itemListInfo.setVisible(!hasValidItems);
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
        List validItems = new ArrayList();
        for (int i = 0; i < selectedItems.length; i++) {
            ItemRecord itemRecord = selectedItems[i];
            repositoryUtil.populateValidItems(validItems, itemRecord, errors);
        }
        if (errorsList != null) {
            errorsList.refresh();
        }
        return (ItemRecord[]) validItems.toArray(new ItemRecord[validItems.size()]);
    }

    public boolean performFinish() {
        final Object[] checkedElements = itemsList.getCheckedElements();

        try {
            IRunnableWithProgress op = new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(
                            Messages.getString("ImportItemWizardPage.ImportSelectedItems"), checkedElements.length + 1); //$NON-NLS-1$

                    repositoryUtil.setErrors(false);

                    for (int i = 0; i < checkedElements.length; i++) {
                        if (!monitor.isCanceled()) {
                            ItemRecord itemRecord = (ItemRecord) checkedElements[i];

                            monitor
                                    .subTask(Messages.getString("ImportItemWizardPage.Importing") + itemRecord.getItemName()); //$NON-NLS-1$

                            repositoryUtil.importItemRecord(manager, itemRecord);

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

    public boolean performCancel() {
        ArchiveFileManipulations.clearProviderCache(getContainer().getShell());
        return true;
    }

    private boolean collectItemFilesFromDirectory(Collection files, File directory, IProgressMonitor monitor,
            ResourcesManager collector) {

        if (monitor.isCanceled()) {
            return false;
        }
        monitor.subTask(NLS.bind(DataTransferMessages.WizardProjectsImportPage_CheckingMessage, directory.getPath()));
        File[] contents = directory.listFiles();

        if (contents != null) {
            for (int i = 0; i < contents.length; i++) {
                File file = contents[i];

                if (file.isFile()) {
                    collector.add(file.getAbsolutePath(), file);
                }
                if (file.isDirectory()) {
                    if (!contents[i].getName().equals(IDEApplication.METADATA_FOLDER)) {
                        collectItemFilesFromDirectory(files, contents[i], monitor, collector);
                    }
                }
            }
        }
        return true;
    }

    private boolean collectItemFilesFromProvider(Collection files, IImportStructureProvider provider, Object entry,
            int level, IProgressMonitor monitor, ResourcesManager collector) {

        if (monitor.isCanceled()) {
            return false;
        }
        monitor.subTask(NLS.bind(DataTransferMessages.WizardProjectsImportPage_CheckingMessage, provider
                .getLabel(entry)));
        List children = provider.getChildren(entry);
        if (children == null) {
            children = new ArrayList(1);
        }
        Iterator childrenEnum = children.iterator();
        while (childrenEnum.hasNext()) {
            Object child = childrenEnum.next();
            if (provider.isFolder(child)) {
                collectItemFilesFromProvider(files, provider, child, level + 1, monitor, collector);
            } else {
                collector.add(provider.getFullPath(child), child);
            }
        }
        return true;
    }
}
