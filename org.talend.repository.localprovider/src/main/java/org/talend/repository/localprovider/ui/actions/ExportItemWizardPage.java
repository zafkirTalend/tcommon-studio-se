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
package org.talend.repository.localprovider.ui.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.wizards.datatransfer.DataTransferMessages;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.IDesignerCoreService;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.repository.local.ExportItemUtil;
import org.talend.repository.localprovider.imports.FilteredCheckboxTree;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNodeUtilities;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.ui.views.CheckboxRepositoryTreeViewer;
import org.talend.repository.ui.views.IRepositoryView;
import org.talend.repository.ui.views.RepositoryContentProvider;
import org.talend.repository.ui.views.RepositoryView;

/**
 * Initialy copied from org.eclipse.ui.internal.wizards.datatransfer.WizardProjectsImportPage.
 */
class ExportItemWizardPage extends WizardPage {

    private Button itemFromDirectoryRadio;

    private Text directoryPathField;

    private Button browseDirectoriesButton;

    private Button itemFromArchiveRadio;

    private Text archivePathField;

    private Button browseArchivesButton;

    private String previouslyBrowsedDirectory = ""; //$NON-NLS-1$

    private String previouslyBrowsedArchive = ""; //$NON-NLS-1$

    private static final String[] FILE_EXPORT_MASK = { "*.zip;*.tar;*.tar.gz", "*.*" }; //$NON-NLS-1$ //$NON-NLS-2$

    private String lastPath;

    private CheckboxRepositoryView exportItemsTreeViewer;

    private IRepositoryView repositoryView;

    private IStructuredSelection selection;

    private FilteredCheckboxTree filteredCheckboxTree;

    private Button exportDependencies;

    Collection<RepositoryNode> repositoryNodes = new ArrayList<RepositoryNode>();

    protected ExportItemWizardPage(String pageName, IStructuredSelection selection) {
        super(pageName);
        repositoryView = RepositoryView.show();
        this.selection = selection;
        setDescription("Export items to an archive file or directory.");
        setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_EXPORT_WIZ));
    }

    public void createControl(Composite parent) {
        Composite workArea = new Composite(parent, SWT.NONE);
        setControl(workArea);

        workArea.setLayout(new GridLayout());
        workArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        createItemRoot(workArea);
        createItemList(workArea);
    }

    /**
     * DOC hcw Comment method "createItemList".
     * 
     * @param workArea
     */
    private void createItemList(Composite workArea) {
        Composite itemComposite = new Composite(workArea, SWT.NONE);
        GridLayoutFactory.swtDefaults().numColumns(2).applyTo(itemComposite);
        GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).hint(500, 300).applyTo(itemComposite);

        Label label = new Label(itemComposite, SWT.NONE);
        label.setText("Select the items to export:");
        GridDataFactory.swtDefaults().span(2, 1).applyTo(label);

        createTreeViewer(itemComposite);

        createSelectionButton(itemComposite);

        exportItemsTreeViewer.refresh();
        // force loading all nodes
        exportItemsTreeViewer.getViewer().expandAll();
        exportItemsTreeViewer.getViewer().collapseAll();
        // expand to level of metadata connection
        exportItemsTreeViewer.getViewer().expandToLevel(3);

        // if user has select some items in repository view, mark them as checked
        if (!selection.isEmpty()) {
            ((CheckboxTreeViewer) exportItemsTreeViewer.getViewer()).setCheckedElements(selection.toArray());
            repositoryNodes.addAll(selection.toList());
        }
    }

    private void createTreeViewer(Composite itemComposite) {
        filteredCheckboxTree = new FilteredCheckboxTree(itemComposite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI) {

            @Override
            protected CheckboxTreeViewer doCreateTreeViewer(Composite parent, int style) {
                exportItemsTreeViewer = new CheckboxRepositoryView();
                try {
                    exportItemsTreeViewer.init(repositoryView.getViewSite());
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
                exportItemsTreeViewer.createPartControl(parent);
                return (CheckboxTreeViewer) exportItemsTreeViewer.getViewer();
            }

            @Override
            protected void refreshCompleted() {
                getViewer().expandToLevel(3);
                restoreCheckedElements();
            }

            @Override
            protected boolean isNodeCollectable(TreeItem item) {
                Object obj = item.getData();
                if (obj instanceof RepositoryNode) {
                    RepositoryNode node = (RepositoryNode) obj;
                    if (node.getObjectType() == ERepositoryObjectType.METADATA_CONNECTIONS) {
                        return true;
                    }
                }
                return false;
            }
        };
        filteredCheckboxTree.getViewer();
    }

    /**
     * DOC hcw Comment method "createSelectionButton".
     * 
     * @param itemComposite
     */
    private void createSelectionButton(Composite itemComposite) {
        Composite buttonComposite = new Composite(itemComposite, SWT.NONE);
        GridLayoutFactory.swtDefaults().margins(0, 25).applyTo(buttonComposite);
        GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(buttonComposite);

        Button selectAll = new Button(buttonComposite, SWT.PUSH);
        selectAll.setText(DataTransferMessages.DataTransfer_selectAll);
        selectAll.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                ((CheckboxTreeViewer) exportItemsTreeViewer.getViewer()).setAllChecked(true);
            }
        });

        setButtonLayoutData(selectAll);

        Button deselectAll = new Button(buttonComposite, SWT.PUSH);
        deselectAll.setText(DataTransferMessages.DataTransfer_deselectAll);
        deselectAll.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                ((CheckboxTreeViewer) exportItemsTreeViewer.getViewer()).setAllChecked(false);
            }
        });

        setButtonLayoutData(deselectAll);

    }

    @SuppressWarnings("restriction")
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
                    lastPath = directoryPathField.getText().trim();
                }
            }

        });

        directoryPathField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(org.eclipse.swt.events.FocusEvent e) {
                lastPath = directoryPathField.getText().trim();
            }

        });

        archivePathField.addTraverseListener(new TraverseListener() {

            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                    lastPath = archivePathField.getText().trim();
                }
            }
        });

        archivePathField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(org.eclipse.swt.events.FocusEvent e) {
                lastPath = archivePathField.getText().trim();
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

        exportDependencies = new Button(workArea, SWT.CHECK);
        exportDependencies.setText("Export Dependencies");
        exportDependencies.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                exportDependenciesSelected();
            }

        });
    }

    /**
     * DOC qwei Comment method "exportDependenciesSelected".
     */
    private void exportDependenciesSelected() {
        List<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        Collection<IRepositoryObject> repContextObjects = getContextRepositoryObject(getSelectedItems());
        if (repContextObjects != null) {
            repositoryObjects.addAll(repContextObjects);
        }
        Collection<IRepositoryObject> repMetadataObjects = getMetadataRepositoryObject(getSelectedItems());
        if (repMetadataObjects != null) {
            repositoryObjects.addAll(repMetadataObjects);
        }
        Collection<IRepositoryObject> repChildProcessObjects = getChildPorcessRepositoryObject(getSelectedItems());
        if (repChildProcessObjects != null) {
            repositoryObjects.addAll(repChildProcessObjects);
        }
        if (exportDependencies.getSelection()) {
            for (IRepositoryObject repositoryObject : repositoryObjects) {
                repositoryNodes.add(RepositoryNodeUtilities.getRepositoryNode(repositoryObject));
            }
            ((CheckboxTreeViewer) exportItemsTreeViewer.getViewer()).setCheckedElements(repositoryNodes.toArray());
        } else {
            for (IRepositoryObject repositoryObject : repositoryObjects) {
                if (repositoryNodes.contains(RepositoryNodeUtilities.getRepositoryNode(repositoryObject))) {
                    repositoryNodes.remove(RepositoryNodeUtilities.getRepositoryNode(repositoryObject));
                }
            }
            ((CheckboxTreeViewer) exportItemsTreeViewer.getViewer()).setCheckedElements(repositoryNodes.toArray());
        }
    }

    private Collection<IRepositoryObject> getContextRepositoryObject(Collection<Item> items) {
        Collection<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        for (Item item : items) {
            if (item == null) {
                return null;
            }
            //
            ProcessType process = null;
            if (item instanceof ProcessItem) {
                process = ((ProcessItem) item).getProcess();
            } else if (item instanceof JobletProcessItem) {
                process = ((JobletProcessItem) item).getJobletProcess();
            }
            if (process != null) {
                ContextType contextType = (ContextType) process.getContext().get(0);
                for (ContextParameterType param : (List<ContextParameterType>) contextType.getContextParameter()) {
                    String repositoryContextId = param.getRepositoryContextId();
                    if (repositoryContextId != null && !"".equals(repositoryContextId)) {
                        try {
                            IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                            IRepositoryObject lastVersion = factory.getLastVersion(repositoryContextId);
                            if (lastVersion != null) {
                                if (!repositoryObjects.contains(lastVersion)) {
                                    repositoryObjects.add(lastVersion);
                                }
                            }

                        } catch (PersistenceException e) {
                            ExceptionHandler.process(e);
                        }

                    }
                }

            }
        }
        return repositoryObjects;
    }

    /**
     * DOC qwei Comment method "getMetadataRepositoryObject".
     */
    private Collection<IRepositoryObject> getMetadataRepositoryObject(Collection<Item> items) {
        Collection<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        for (Item item : items) {
            if (item == null) {
                return null;
            }
            IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
            if (designerCoreService == null) {
                return null;
            }
            IProcess process = null;
            if (item instanceof ProcessItem) {
                process = designerCoreService.getProcessFromProcessItem((ProcessItem) item);
            } else if (item instanceof JobletProcessItem) {
                process = designerCoreService.getProcessFromJobletProcessItem((JobletProcessItem) item);
            }
            if (process != null) {
                List<INode> nodes = (List<INode>) process.getGraphicalNodes();
                for (INode node : nodes) {
                    List<IElementParameter> eleParams = (List<IElementParameter>) node.getElementParameters();
                    for (IElementParameter elementParameter : eleParams) {
                        String repositoryMetadataId = "";
                        if (elementParameter.getName().equals("PROPERTY")) {
                            repositoryMetadataId = (String) elementParameter.getChildParameters().get("REPOSITORY_PROPERTY_TYPE")
                                    .getValue();
                        }
                        if (elementParameter.getName().equals("SCHEMA")) {
                            repositoryMetadataId = (String) elementParameter.getChildParameters().get("REPOSITORY_SCHEMA_TYPE")
                                    .getValue();
                        }
                        if (elementParameter.getName().equals("QUERYSTORE")) {
                            repositoryMetadataId = (String) elementParameter.getChildParameters().get(
                                    "REPOSITORY_QUERYSTORE_TYPE").getValue();
                        }
                        String[] id = repositoryMetadataId.split("-");
                        if (id.length > 0) {

                            if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) {
                                try {
                                    IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                                    IRepositoryObject lastVersion = factory.getLastVersion(id[0].trim());
                                    if (lastVersion != null) {
                                        if (!repositoryObjects.contains(lastVersion)) {
                                            repositoryObjects.add(lastVersion);
                                        }
                                    }
                                } catch (PersistenceException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            }
                        }

                    }

                }

            }

        }
        return repositoryObjects;

    }

    /**
     * DOC qwei Comment method "getChildPorcessRepositoryObject".
     */
    private Collection<IRepositoryObject> getChildPorcessRepositoryObject(Collection<Item> items) {
        List<IRepositoryObject> returnListObject = new ArrayList<IRepositoryObject>();
        Map<String, Item> returnItems = new HashMap<String, Item>();
        Collection<IRepositoryObject> repositoryObjects = new ArrayList<IRepositoryObject>();
        for (Item item : items) {
            if (item == null) {
                return null;
            }
            IDesignerCoreService designerCoreService = CorePlugin.getDefault().getDesignerCoreService();
            if (designerCoreService == null) {
                return null;
            }
            IProcess process = null;
            if (item instanceof ProcessItem) {
                process = designerCoreService.getProcessFromProcessItem((ProcessItem) item);
            } else if (item instanceof JobletProcessItem) {
                process = designerCoreService.getProcessFromJobletProcessItem((JobletProcessItem) item);
            }

            if (process != null) {
                List<INode> nodes = (List<INode>) process.getGraphicalNodes();
                for (INode node : nodes) {
                    List<IElementParameter> eleParams = (List<IElementParameter>) node.getElementParameters();
                    for (IElementParameter elementParameter : eleParams) {

                        if (elementParameter.getName().equals("PROCESS")) {
                            String repositoryMetadataId = (String) elementParameter.getChildParameters().get(
                                    "PROCESS_TYPE_PROCESS").getValue();
                            if (repositoryMetadataId != null && !repositoryMetadataId.equals("")) {
                                try {
                                    IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
                                    IRepositoryObject lastVersion = factory.getLastVersion(repositoryMetadataId);
                                    if (lastVersion != null) {
                                        if (!repositoryObjects.contains(lastVersion)) {
                                            repositoryObjects.add(lastVersion);
                                            returnListObject.add(lastVersion);
                                        }
                                        Item item2 = lastVersion.getProperty().getItem();
                                        if (item2 != null) {
                                            Item foundItem = returnItems.get(item2.getProperty().getId());
                                            if (foundItem == null) {
                                                returnItems.put(item2.getProperty().getId(), item2);
                                                returnListObject.addAll(getContextRepositoryObject(returnItems.values()));
                                                returnListObject.addAll(getMetadataRepositoryObject(returnItems.values()));
                                                returnListObject.addAll(getChildPorcessRepositoryObject(returnItems.values()));
                                            }
                                        }

                                    }
                                } catch (PersistenceException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            }
                        }
                    }

                }

            }

        }
        return returnListObject;

    }

    private void archiveRadioSelected() {
        if (itemFromArchiveRadio.getSelection()) {
            directoryPathField.setEnabled(false);
            browseDirectoriesButton.setEnabled(false);
            archivePathField.setEnabled(true);
            browseArchivesButton.setEnabled(true);
            lastPath = archivePathField.getText().trim();
            archivePathField.setFocus();
        }
    }

    @SuppressWarnings("restriction")
    protected void handleLocationDirectoryButtonPressed() {

        DirectoryDialog dialog = new DirectoryDialog(directoryPathField.getShell());
        dialog.setMessage(DataTransferMessages.FileExport_selectDestinationTitle);

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
            lastPath = directoryPathField.getText().trim();
        }

    }

    /**
     * The browse button has been selected. Select the location.
     */
    @SuppressWarnings("restriction")
    protected void handleLocationArchiveButtonPressed() {

        FileDialog dialog = new FileDialog(archivePathField.getShell(), SWT.SAVE);
        dialog.setFilterExtensions(FILE_EXPORT_MASK);
        dialog.setText(DataTransferMessages.ArchiveExport_selectDestinationTitle);

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
            lastPath = archivePathField.getText().trim();
        }

    }

    private void directoryRadioSelected() {
        if (itemFromDirectoryRadio.getSelection()) {
            directoryPathField.setEnabled(true);
            browseDirectoriesButton.setEnabled(true);
            archivePathField.setEnabled(false);
            browseArchivesButton.setEnabled(false);
            lastPath = directoryPathField.getText().trim();
            directoryPathField.setFocus();
        }
    }

    protected void displayErrorDialog(String message) {
        MessageDialog.openError(getContainer().getShell(), getErrorDialogTitle(), message);
    }

    @SuppressWarnings("restriction")
    protected String getErrorDialogTitle() {
        return IDEWorkbenchMessages.WizardExportPage_internalErrorTitle;
    }

    public boolean performFinish() {
        Collection<Item> selectedItems = getSelectedItems();
        try {
            ExportItemUtil exportItemUtil = new ExportItemUtil();
            Collection<Item> allItems = exportItemUtil.getAllVersions(selectedItems);
            exportItemUtil.exportItems(new File(lastPath), allItems);
        } catch (Exception e) {
            MessageBoxExceptionHandler.process(e);
        }

        return true;
    }

    private static boolean isRepositoryFolder(RepositoryNode node) {
        final ENodeType type = node.getType();
        if (type == ENodeType.SIMPLE_FOLDER || type == ENodeType.STABLE_SYSTEM_FOLDER || type == ENodeType.SYSTEM_FOLDER) {
            return true;
        }
        return false;
    }

    /**
     * Get all selected items to export.
     * 
     * @return
     */
    private Collection<Item> getSelectedItems() {
        // add this if user use filter
        Set checkedElements = new HashSet();
        for (Object obj : filteredCheckboxTree.getCheckedLeafNodes()) {
            checkedElements.add(obj);
        }

        // add this if user does not use filter
        for (Object obj : filteredCheckboxTree.getViewer().getCheckedElements()) {
            RepositoryNode repositoryNode = (RepositoryNode) obj;
            if (!isRepositoryFolder(repositoryNode)) {
                checkedElements.add(obj);
            }
        }

        Object[] elements = checkedElements.toArray();

        Map<String, Item> items = new HashMap<String, Item>();
        collectNodes(items, elements);
        return items.values();
    }

    @SuppressWarnings("unchecked")
    private void collectNodes(Map<String, Item> items, Object[] objects) {
        for (int i = 0; i < objects.length; i++) {
            RepositoryNode repositoryNode = (RepositoryNode) objects[i];
            collectNodes(items, repositoryNode);
        }
    }

    private void collectNodes(Map<String, Item> items, RepositoryNode repositoryNode) {
        IRepositoryObject repositoryObject = repositoryNode.getObject();
        if (repositoryObject != null) {
            if (repositoryObject.getType().isResourceItem()) {
                Item item = repositoryObject.getProperty().getItem();
                items.put(item.getProperty().getId(), item);
            }
        } else {
            if (repositoryNode.getParent() != null && repositoryNode.getParent().getObject() != null) {
                Item item = repositoryNode.getParent().getObject().getProperty().getItem();
                items.put(item.getProperty().getId(), item);
            }
        }
        RepositoryContentProvider repositoryContentProvider = (RepositoryContentProvider) RepositoryView.show().getViewer()
                .getContentProvider();
        collectNodes(items, repositoryContentProvider.getChildren(repositoryNode));
    }

    public boolean performCancel() {
        return true;
    }

    /**
     * 
     * A repository view with checkbox on the left.
     */
    class CheckboxRepositoryView extends RepositoryView {

        @Override
        protected TreeViewer createTreeViewer(Composite parent) {
            return new CheckboxRepositoryTreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.repository.ui.views.RepositoryView#createPartControl(org.eclipse.swt.widgets.Composite)
         */
        @Override
        public void createPartControl(Composite parent) {
            super.createPartControl(parent);
            CorePlugin.getDefault().getRepositoryService().removeRepositoryChangedListener(this);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.repository.ui.views.RepositoryView#refresh(java.lang.Object)
         */
        @Override
        public void refresh(Object object) {
            refresh();
            if (object != null) {
                getViewer().expandToLevel(object, AbstractTreeViewer.ALL_LEVELS);
            }
        }

        @Override
        protected void makeActions() {
        }

        @Override
        protected void hookContextMenu() {
        }

        @Override
        protected void contributeToActionBars() {
        }

        @Override
        protected void initDragAndDrop() {
        }

        @Override
        protected void hookDoubleClickAction() {
        }

    }
}
