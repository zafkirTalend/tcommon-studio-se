// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.ui.wizard.imports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.talend.core.PluginChecker;
import org.talend.core.model.utils.TalendPropertiesUtil;
import org.talend.repository.items.importexport.i18n.Messages;

/**
 * 
 * DOC ggu class global comment. Detailled comment
 */
public class ImportItemsWizardPage extends WizardPage {

    private Button itemFromDirectoryRadio, itemFromArchiveRadio;

    private Text directoryPathField, archivePathField;

    private Button browseDirectoriesButton, browseArchivesButton, fromExchangeButton;

    private TableViewer errorsListViewer;

    private final List<String> errors = new ArrayList<String>();

    private Button overwriteButton;

    /*
     * 
     */
    private String previouslyBrowsedDirectory;

    /**
     * 
     * DOC ggu ImportItemsWizardPage constructor comment.
     * 
     * @param pageName
     */
    @SuppressWarnings("restriction")
    public ImportItemsWizardPage(String pageName) {

        super(pageName);
        setDescription(Messages.getString("ImportItemsWizardPage_importDescription")); //$NON-NLS-1$
        setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_IMPORT_WIZ));
    }

    @Override
    public void createControl(Composite parent) {
        Composite workArea = new Composite(parent, SWT.NONE);
        setControl(workArea);

        workArea.setLayout(new GridLayout());
        workArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

        createSelectionArea(workArea);

        createItemListArea(workArea);
        createErrorsListArea(workArea);

        createAdditionArea(workArea);

        Dialog.applyDialogFont(workArea);
    }

    private void createSelectionArea(Composite parent) {
        Composite selectionArea = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 4;
        layout.makeColumnsEqualWidth = false;
        layout.marginWidth = 0;
        selectionArea.setLayout(layout);
        selectionArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        // from directory
        createDirectorySelectionArea(selectionArea);

        // from archive
        createArchiveSelectionArea(selectionArea);

        // from directory by default.
        this.itemFromDirectoryRadio.setSelection(true);
        updateSelectionFields(this.itemFromDirectoryRadio.getSelection());
    }

    /**
     * DOC ggu Comment method "createDirectorySelectionArea".
     * 
     * @param selectionArea
     */
    private void createDirectorySelectionArea(Composite selectionArea) {
        this.itemFromDirectoryRadio = new Button(selectionArea, SWT.RADIO);
        this.itemFromDirectoryRadio.setText(Messages.getString("ImportItemsWizardPage_selectDirectoryText")); //$NON-NLS-1$
        setButtonLayoutData(this.itemFromDirectoryRadio);

        this.itemFromDirectoryRadio.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                handleDirectoryRadioSelected();
            }
        });

        this.directoryPathField = new Text(selectionArea, SWT.BORDER);
        this.directoryPathField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

        this.directoryPathField.addTraverseListener(new TraverseListener() {

            @Override
            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                    updateItemsList(directoryPathField.getText().trim(), false);
                }
            }

        });
        this.directoryPathField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(org.eclipse.swt.events.FocusEvent e) {
                updateItemsList(directoryPathField.getText().trim(), false);
            }

        });

        this.browseDirectoriesButton = new Button(selectionArea, SWT.PUSH);
        this.browseDirectoriesButton.setText(Messages.getString("ImportItemsWizardPage_browseText")); //$NON-NLS-1$
        setButtonLayoutData(this.browseDirectoriesButton);
        this.browseDirectoriesButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                handleBrowseDirectoryButtonPressed();
            }
        });
    }

    /**
     * DOC ggu Comment method "createArchiveSelectionArea".
     * 
     * @param selectionArea
     */
    private void createArchiveSelectionArea(Composite selectionArea) {
        this.itemFromArchiveRadio = new Button(selectionArea, SWT.RADIO);
        this.itemFromArchiveRadio.setText(Messages.getString("ImportItemsWizardPage_selectArchiveText")); //$NON-NLS-1$
        setButtonLayoutData(this.itemFromArchiveRadio);

        this.itemFromArchiveRadio.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                handleArchiveRadioSelected();
            }
        });

        this.archivePathField = new Text(selectionArea, SWT.BORDER);
        this.archivePathField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

        this.archivePathField.addTraverseListener(new TraverseListener() {

            @Override
            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                    updateItemsList(archivePathField.getText().trim(), false);
                }
            }
        });

        this.archivePathField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(org.eclipse.swt.events.FocusEvent e) {
                updateItemsList(archivePathField.getText().trim(), false);
            }
        });

        this.browseArchivesButton = new Button(selectionArea, SWT.PUSH);
        this.browseArchivesButton.setText("Browse...");
        setButtonLayoutData(this.browseArchivesButton);

        this.browseArchivesButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // handleLocationArchiveButtonPressed();
            }
        });

        if (isEnableForExchange()) {
            this.fromExchangeButton = new Button(selectionArea, SWT.PUSH);
            this.fromExchangeButton.setText(Messages.getString("ImportItemsWizardPage_fromExchangeText")); //$NON-NLS-1$
            this.fromExchangeButton.setToolTipText(Messages.getString("ImportItemsWizardPage_fromExchangeToolTipText")); //$NON-NLS-1$
            setButtonLayoutData(fromExchangeButton);

            this.fromExchangeButton.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {

                    // archivePathField.setEditable(false);
                    //
                    // IExchangeService service = (IExchangeService) GlobalServiceRegister.getDefault().getService(
                    // IExchangeService.class);
                    //
                    // selectedArchive = service.openExchangeDialog();
                    // if (selectedArchive != null) {
                    // previouslyBrowsedArchive = selectedArchive;
                    // archivePathField.setText(previouslyBrowsedArchive);
                    // updateItemsList(selectedArchive, false);
                    // }

                }
            });
        }

    }

    private void updateSelectionFields(boolean fromDir) {
        this.directoryPathField.setEnabled(fromDir);
        this.browseDirectoriesButton.setEnabled(fromDir);

        this.archivePathField.setEnabled(!fromDir);
        this.browseArchivesButton.setEnabled(!fromDir);
        if (isEnableForExchange()) {
            this.fromExchangeButton.setEnabled(!fromDir);

        }
    }

    private void createItemListArea(Composite workArea) {
        Composite listComposite = new Composite(workArea, SWT.NONE);
        GridLayout layout2 = new GridLayout();
        layout2.numColumns = 2;
        layout2.marginWidth = 0;
        layout2.makeColumnsEqualWidth = false;
        listComposite.setLayout(layout2);

        GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
        gridData.heightHint = 250;
        gridData.widthHint = 600;
        listComposite.setLayoutData(gridData);

        // checkTreeViewer = (CheckboxTreeViewer) createTreeViewer(listComposite);

        createItemListButtonsArea(listComposite);

    }

    private void createItemListButtonsArea(Composite listComposite) {
        Composite buttonsComposite = new Composite(listComposite, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 25;
        buttonsComposite.setLayout(layout);

        buttonsComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        Button selectAll = new Button(buttonsComposite, SWT.PUSH);
        selectAll.setText(Messages.getString("ImportItemsWizardPage_selectButtonText")); //$NON-NLS-1$
        selectAll.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // if (checkTreeViewer.getTree().getItemCount() > 0) {
                // for (int i = 0; i < checkTreeViewer.getTree().getItemCount(); i++) {
                // TreeItem topItem = checkTreeViewer.getTree().getItem(i)/* .getTopItem() */;
                // if (topItem != null) {
                // checkTreeViewer.setSubtreeChecked(topItem.getData(), true);
                // filteredCheckboxTree.calculateCheckedLeafNodes();
                // updateFinishStatus();
                // }
                // }
                // }
            }
        });
        setButtonLayoutData(selectAll);

        Button deselectAll = new Button(buttonsComposite, SWT.PUSH);
        deselectAll.setText(Messages.getString("ImportItemsWizardPage_deselectAllButtonText")); //$NON-NLS-1$
        deselectAll.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // checkTreeViewer.setCheckedElements(new Object[0]);
                // filteredCheckboxTree.calculateCheckedLeafNodes();
                // updateFinishStatus();
            }
        });
        setButtonLayoutData(deselectAll);

        Button refresh = new Button(buttonsComposite, SWT.PUSH);
        refresh.setText(Messages.getString("ImportItemsWizardPage_refreshButtonText")); //$NON-NLS-1$
        refresh.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // if (itemFromDirectoryRadio.getSelection()) {
                // updateItemsList(directoryPathField.getText().trim(), true);
                // } else {
                // updateItemsList(archivePathField.getText().trim(), true);
                // }
            }
        });
        setButtonLayoutData(refresh);
    }

    private void createErrorsListArea(Composite workArea) {
        Composite composite = new Composite(workArea, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.makeColumnsEqualWidth = false;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
        gridData.heightHint = 100;
        composite.setLayoutData(gridData);

        Label title = new Label(composite, SWT.NONE);
        title.setText(Messages.getString("ImportItemsWizardPage_messagesText")); //$NON-NLS-1$
        title.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        errorsListViewer = new TableViewer(composite, SWT.BORDER);
        errorsListViewer.getControl().setLayoutData(gridData);

        errorsListViewer.setContentProvider(new ArrayContentProvider());
        errorsListViewer.setLabelProvider(new LabelProvider());
        errorsListViewer.setSorter(new ViewerSorter());

        errorsListViewer.setInput(errors);
    }

    /**
     * DOC ggu Comment method "createAdditionArea".
     * 
     * @param workArea
     */
    private void createAdditionArea(Composite workArea) {
        // see feature 3949
        overwriteButton = new Button(workArea, SWT.CHECK);
        overwriteButton.setText(Messages.getString("ImportItemsWizardPage_overwriteItemsText")); //$NON-NLS-1$
        overwriteButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // overwrite = overwriteButton.getSelection();
                // if (StringUtils.isNotEmpty(directoryPathField.getText()) ||
                // StringUtils.isNotEmpty(archivePathField.getText())) {
                // populateItems();
                // }
            }

        });
    }

    private boolean isEnableForExchange() {
        return PluginChecker.isExchangeSystemLoaded() && !TalendPropertiesUtil.isHideExchange();
    }

    /**
     * From directory
     * 
     */
    private void handleDirectoryRadioSelected() {
        boolean selection = this.itemFromDirectoryRadio.getSelection();
        updateSelectionFields(selection);
        if (selection) {
            this.directoryPathField.setFocus();
            updateItemsList(this.directoryPathField.getText().trim(), false);
        }
    }

    /**
     * From directory
     * 
     */
    private void handleBrowseDirectoryButtonPressed() {
        DirectoryDialog dialog = new DirectoryDialog(this.getShell());
        dialog.setMessage(Messages.getString("ImportItemsWizardPage_selectDirectoryDialogMessage")); //$NON-NLS-1$

        String dirPath = this.directoryPathField.getText().trim();
        if (dirPath.length() == 0) {
            dirPath = previouslyBrowsedDirectory;
        }

        if (dirPath.length() == 0) {
            dialog.setFilterPath(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
        } else {
            File path = new File(dirPath);
            if (path.exists()) {
                dialog.setFilterPath(new Path(dirPath).toOSString());
            }
        }

        String selectedDirectory = dialog.open();
        if (selectedDirectory != null) {
            this.directoryPathField.setText(selectedDirectory);
            previouslyBrowsedDirectory = selectedDirectory;
            updateItemsList(selectedDirectory, false);
        }

    }

    /**
     * From archive
     * 
     */
    private void handleArchiveRadioSelected() {
        boolean selection = this.itemFromArchiveRadio.getSelection();
        updateSelectionFields(!selection);
        if (selection) {
            this.archivePathField.setFocus();
            updateItemsList(this.archivePathField.getText().trim(), false);
        }
    }

    public void updateItemsList(final String path, boolean isneedUpdate) {
        // TODO
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    @Override
    public boolean isPageComplete() {
        // if (selectedItems.isEmpty() || getErrorMessage() != null) {
        // return false;
        // }
        return super.isPageComplete();
    }

}
