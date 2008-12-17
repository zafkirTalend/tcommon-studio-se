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
package org.talend.core.ui.metadata.celleditor;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.MetadataTool;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.EbcdicConnectionItem;

/**
 * nrousseau class global comment. Detailled comment
 */
public class SchemaOperationChoiceDialog extends SelectionDialog {

    /**
     * 
     * nrousseau ESelectionCategory.
     */
    public enum ESelectionCategory {
        SHOW_SCHEMA,
        BUILDIN,
        REPOSITORY;
    }

    /**
     * 
     * nrousseau EProcessType.
     */
    public enum EProcessType {
        CREATE,
        BUILTIN,
        REPOSITORY;

    }

    private static final String TITLE = Messages.getString("SchemaOperationChoiceDialog.Title"); //$NON-NLS-1$

    private static final String MESSAGE = Messages.getString("SchemaOperationChoiceDialog.Message"); //$NON-NLS-1$

    private ESelectionCategory selection;

    private Button viewSchemaBtn, builtinBtn, repositoryBtn;

    private Combo schemaCombo;

    private Label status;

    private final INode node;

    private final EbcdicConnectionItem item;

    private MetadataTable selectedTable;

    private final EProcessType processType;

    private final String schemaName;

    private final boolean readOnlyJob;

    public SchemaOperationChoiceDialog(Shell parentShell, INode node, EbcdicConnectionItem item, EProcessType pType,
            String schemaName, boolean readOnlyJob) {
        super(parentShell);
        this.node = node;
        this.item = item;
        this.processType = pType;
        this.schemaName = schemaName;
        this.readOnlyJob = readOnlyJob;
        this.setTitle(TITLE);
        this.setMessage(MESSAGE);
        this.setHelpAvailable(false);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        createMessageArea(composite);

        Label titleBarSeparator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
        titleBarSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        createOptionArea(composite);

        status = new Label(composite, SWT.NONE);
        status.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        status.setText(Messages.getString("SchemaOperationChoiceDialog.StatusMessage")); //$NON-NLS-1$
        status.setForeground(new Color(null, 255, 0, 0));
        status.setVisible(false);
        return composite;
    }

    protected Control createOptionArea(Composite composite) {
        Composite inner = new Composite(composite, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.marginHeight = 0;
        inner.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.minimumWidth = 250;
        inner.setLayoutData(gridData);
        //
        Group group = new Group(inner, SWT.NONE);
        group.setText(Messages.getString("SchemaOperationChoiceDialog.Option")); //$NON-NLS-1$
        group.setLayout(new GridLayout());
        group.setLayoutData(new GridData(GridData.FILL_BOTH));

        // default
        switch (processType) {
        case CREATE:
            setSelection(ESelectionCategory.BUILDIN);
            createCreationComposite(group);
            break;
        case BUILTIN:
            setSelection(ESelectionCategory.SHOW_SCHEMA);
            createBuiltInComposite(group);
            break;
        case REPOSITORY:
            setSelection(ESelectionCategory.SHOW_SCHEMA);
            createRepositoryComposite(group);
            break;
        default:
        }

        configControlStatus();
        return composite;
    }

    private void createCreationComposite(Group group) {
        createBuiltInButton(group, Messages.getString("SchemaOperationChoiceDialog.CreateBuiltInMessage")); //$NON-NLS-1$
        createRepositoryButton(group, Messages.getString("SchemaOperationChoiceDialog.CreateRepositoryMessage")); //$NON-NLS-1$
        createSchemaCombo(group);
        builtinBtn.setSelection(getSelctionType() == ESelectionCategory.BUILDIN);
    }

    private void createBuiltInComposite(Group group) {
        createViewSchemaButton(group, Messages.getString("SchemaOperationChoiceDialog.EditSchemaMessage")); //$NON-NLS-1$
        createRepositoryButton(group, Messages.getString("SchemaOperationChoiceDialog.ChangeRepositoryMessage")); //$NON-NLS-1$
        createSchemaCombo(group);

    }

    private void createRepositoryComposite(Group group) {
        createViewSchemaButton(group, Messages.getString("SchemaOperationChoiceDialog.ViewSchemaMessage")); //$NON-NLS-1$
        createBuiltInButton(group, Messages.getString("SchemaOperationChoiceDialog.ChangeBuiltInMessage")); //$NON-NLS-1$
        createRepositoryButton(group, Messages.getString("SchemaOperationChoiceDialog.ChangeRepositoryMessage")); //$NON-NLS-1$
        createSchemaCombo(group);
    }

    private void createViewSchemaButton(Composite composite, String message) {
        viewSchemaBtn = new Button(composite, SWT.RADIO);
        viewSchemaBtn.setSelection(getSelctionType() == ESelectionCategory.SHOW_SCHEMA);
        viewSchemaBtn.setText(message);
        viewSchemaBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (viewSchemaBtn.getSelection()) {
                    setSelection(ESelectionCategory.SHOW_SCHEMA);
                    if (schemaCombo != null) {
                        schemaCombo.setVisible(false);
                    }
                }
                setButtonAndStatus(true);
            }
        });

    }

    private void createBuiltInButton(Composite composite, String message) {
        builtinBtn = new Button(composite, SWT.RADIO);
        builtinBtn.setText(message);
        builtinBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (builtinBtn.getSelection()) {
                    setSelection(ESelectionCategory.BUILDIN);
                    if (schemaCombo != null) {
                        schemaCombo.setVisible(false);
                    }
                }
                setButtonAndStatus(true);
            }

        });

    }

    private void createRepositoryButton(Composite composite, String message) {
        repositoryBtn = new Button(composite, SWT.RADIO);
        repositoryBtn.setText(message);
        repositoryBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (repositoryBtn.getSelection()) {
                    setSelection(ESelectionCategory.REPOSITORY);

                    setButtonAndStatus(checkSchema());
                    if (schemaCombo != null) {
                        schemaCombo.setVisible(true);
                    }
                }

            }

        });

    }

    private void createSchemaCombo(Composite composite) {
        schemaCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        schemaCombo.setVisible(false);
        GridData layoutData = new GridData();
        layoutData.widthHint = 150;
        schemaCombo.setLayoutData(layoutData);
        if (item != null) {
            EList tables = item.getConnection().getTables();
            for (MetadataTable table : (List<MetadataTable>) tables) {
                schemaCombo.add(table.getLabel());
            }
            if (schemaCombo.getItemCount() > 0) {
                schemaCombo.select(0);
            }
        }
        schemaCombo.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                setButtonAndStatus(checkSchema());
            }

        });

    }

    private void configControlStatus() {
        if (readOnlyJob) {
            if (builtinBtn != null && !builtinBtn.isDisposed()) {
                builtinBtn.setEnabled(false);
            }
            if (repositoryBtn != null && !repositoryBtn.isDisposed()) {
                repositoryBtn.setEnabled(false);
            }
            if (viewSchemaBtn != null && !viewSchemaBtn.isDisposed()) {
                viewSchemaBtn.setEnabled(false);
            }
            if (schemaCombo != null && !schemaCombo.isDisposed()) {
                schemaCombo.setEnabled(false);
            }
        }
    }

    private void setButtonAndStatus(boolean enable) {
        Button okBtn = getButton(IDialogConstants.OK_ID);
        if (okBtn != null) {
            okBtn.setEnabled(enable);
            status.setVisible(!enable);
        }
    }

    private boolean checkSchema() {
        boolean valid = true;
        // valid = node.getProcess().checkValidConnectionName(schemaName);
        if (valid) {
            if (MetadataTool.getMetadataTableFromNode(node, schemaCombo.getText()) != null) {
                valid = false;
            }
        }
        if (!valid && processType == EProcessType.BUILTIN) {
            // only, change to repository
            if (schemaCombo.getText().equals(schemaName)) {
                valid = true;
            }
        }
        return valid;
    }

    @Override
    protected void okPressed() {
        this.selectedTable = null;
        if (getSelctionType() == ESelectionCategory.REPOSITORY) {
            EList tables = item.getConnection().getTables();
            for (MetadataTable table : (List<MetadataTable>) tables) {
                if (table.getLabel().equals(schemaCombo.getText())) {
                    this.selectedTable = table;
                    break;
                }
            }
        }
        super.okPressed();

    }

    private void setSelection(ESelectionCategory selection) {
        this.selection = selection;
    }

    public ESelectionCategory getSelctionType() {

        return this.selection;
    }

    public org.talend.core.model.metadata.builder.connection.MetadataTable getSelectedMetadataTable() {
        return this.selectedTable;
    }
}
