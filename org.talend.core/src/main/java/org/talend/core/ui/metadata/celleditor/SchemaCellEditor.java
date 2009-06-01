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
package org.talend.core.ui.metadata.celleditor;

import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.IEbcdicConstant;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.metadata.MetadataTool;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.EbcdicConnectionItem;
import org.talend.core.ui.IEBCDICProviderService;
import org.talend.core.ui.metadata.celleditor.SchemaOperationChoiceDialog.EProcessType;
import org.talend.core.ui.metadata.celleditor.SchemaOperationChoiceDialog.ESelectionCategory;
import org.talend.core.ui.metadata.command.RepositoryChangeMetadataForEBCDICCommand;
import org.talend.core.ui.metadata.dialog.MetadataDialog;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class SchemaCellEditor extends DialogCellEditor {

    private INode node;

    private AbstractDataTableEditorView tableEditorView;

    public SchemaCellEditor(Composite parent, INode node) {
        super(parent, SWT.NONE);
        this.node = node;
    }

    public AbstractDataTableEditorView getTableEditorView() {
        return this.tableEditorView;
    }

    private TableViewer getTableViewer() {
        if (this.tableEditorView != null) {
            AbstractExtendedTableViewer extendedTableViewer = this.tableEditorView.getExtendedTableViewer();
            if (extendedTableViewer != null) {
                TableViewerCreator tableViewerCreator = extendedTableViewer.getTableViewerCreator();
                if (tableViewerCreator != null) {
                    return tableViewerCreator.getTableViewer();
                }
            }
        }
        return null;
    }

    public void setTableEditorView(AbstractDataTableEditorView tableEditorView) {
        this.tableEditorView = tableEditorView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {

        String schemaToEdit = (String) this.getValue();

        boolean metaReadonly = false;

        final List<IMetadataTable> metadatas = node.getMetadataList();
        IMetadataTable tableToEdit = null;
        if (schemaToEdit == null || "".equals(schemaToEdit)) { //$NON-NLS-1$

            // for EBCDIC (bug 5860)
            EbcdicConnectionItem repositoryItem = getRepositoryItem();
            if (repositoryItem != null) {
                SchemaOperationChoiceDialog choiceDialog = new SchemaOperationChoiceDialog(cellEditorWindow.getShell(), node,
                        repositoryItem, EProcessType.CREATE, schemaToEdit, node.getProcess().isReadOnly());
                if (choiceDialog.open() == Window.OK) {
                    if (choiceDialog.getSelctionType() == ESelectionCategory.REPOSITORY) {
                        org.talend.core.model.metadata.builder.connection.MetadataTable selectedMetadataTable = choiceDialog
                                .getSelectedMetadataTable();
                        if (selectedMetadataTable != null) {
                            final IMetadataTable metaTable = ConvertionHelper.convert(selectedMetadataTable);
                            int index = 0;
                            if (getTableViewer() != null) {
                                index = getTableViewer().getTable().getSelectionIndex();
                            }
                            //
                            executeCommand(new RepositoryChangeMetadataForEBCDICCommand(node, IEbcdicConstant.TABLE_SCHEMAS,
                                    metaTable.getLabel(), metaTable, index));
                            //
                            if (getTableViewer() != null) {
                                getTableViewer().refresh(true);
                            }
                            return selectedMetadataTable.getLabel();
                        }
                        return null;
                    } else {
                        // built-in
                    }
                } else {
                    return null;
                }
            }
            // built-in
            InputDialog dialogInput = new InputDialog(cellEditorWindow.getShell(), Messages.getString("SchemaCellEditor.giveSchemaName"), Messages.getString("SchemaCellEditor.schemaName"), //$NON-NLS-1$ //$NON-NLS-2$
                    "", new IInputValidator() { //$NON-NLS-1$

                        public String isValid(String newText) {
                            if ("".equals(newText.trim())) { //$NON-NLS-1$
                                return Messages.getString("SchemaCellEditor.inputName"); //$NON-NLS-1$
                            }
                            if (MetadataTool.getMetadataTableFromNodeTableName(node, newText) != null
                                    || !node.getProcess().checkValidConnectionName(newText)) {
                                return Messages.getString("SchemaCellEditor.nameExistOrInvalid"); //$NON-NLS-1$
                            }
                            return null;
                        }

                    });

            if (dialogInput.open() == InputDialog.OK) {
                node.getProcess().addUniqueConnectionName(dialogInput.getValue());
                final MetadataTable table = new MetadataTable();
                table.setLabel(dialogInput.getValue());
                table.setTableName(dialogInput.getValue());
                tableToEdit = table;
                metadatas.add(table);
                executeCommand(new Command() {

                    @Override
                    public void execute() {
                        if (getTableViewer() != null) {
                            Table tTable = getTableViewer().getTable();
                            Object data = tTable.getItem(tTable.getSelectionIndex()).getData();
                            if (data instanceof Map) {
                                final Map<String, Object> valueMap = (Map<String, Object>) data;
                                Object code = valueMap.get(IEbcdicConstant.FIELD_CODE);
                                if (code == null || "".equals(code)) { //$NON-NLS-1$
                                    valueMap.put(IEbcdicConstant.FIELD_CODE, table.getTableName());
                                    valueMap.put(IEbcdicConstant.FIELD_SCHEMA, table.getTableName());
                                }
                            }
                            getTableViewer().refresh();
                        }
                    }
                });
            }
        } else {
            tableToEdit = MetadataTool.getMetadataTableFromNodeTableName(node, schemaToEdit);

            if (getTableViewer() != null && tableToEdit != null && isEBCDICNode(node)) {
                Table tTable = getTableViewer().getTable();
                Object data = tTable.getItem(tTable.getSelectionIndex()).getData();
                if (data instanceof Map) {
                    final Map<String, Object> valueMap = (Map<String, Object>) data;
                    final String key = IEbcdicConstant.FIELD_SCHEMA + IEbcdicConstant.REF_TYPE;
                    Object repositoyType = valueMap.get(key);
                    EbcdicConnectionItem repositoryItem = getRepositoryItem();

                    EProcessType processType = EProcessType.BUILTIN;
                    if (repositoryItem != null) {
                        if (repositoyType != null && IEbcdicConstant.REF_ATTR_REPOSITORY.equals(repositoyType)) {
                            processType = EProcessType.REPOSITORY;
                        }
                    }
                    SchemaOperationChoiceDialog choiceDialog = new SchemaOperationChoiceDialog(cellEditorWindow.getShell(), node,
                            repositoryItem, processType, schemaToEdit, node.getProcess().isReadOnly());
                    if (choiceDialog.open() == Window.OK) {
                        ESelectionCategory selctionType = choiceDialog.getSelctionType();
                        switch (selctionType) {
                        case SHOW_SCHEMA:
                            if (processType == EProcessType.REPOSITORY) {
                                metaReadonly = true; // readonly
                            }
                            break;
                        case REPOSITORY:
                            final org.talend.core.model.metadata.builder.connection.MetadataTable selectedMetadataTable = choiceDialog
                                    .getSelectedMetadataTable();
                            if (selectedMetadataTable != null) {
                                String newSchema = selectedMetadataTable.getLabel();
                                if (!schemaToEdit.equals(newSchema) || processType == EProcessType.BUILTIN) {
                                    // changed
                                    RepositoryChangeMetadataForEBCDICCommand cmd = new RepositoryChangeMetadataForEBCDICCommand(
                                            node, IEbcdicConstant.TABLE_SCHEMAS, newSchema, schemaToEdit, ConvertionHelper //$NON-NLS-1$
                                                    .convert(selectedMetadataTable), tableToEdit, tTable.getSelectionIndex());
                                    executeCommand(cmd);
                                    if (getTableViewer() != null) {
                                        getTableViewer().refresh();
                                    }
                                }
                                return newSchema;
                            } else {
                                // built-in
                            }
                        case BUILDIN:
                            executeCommand(new Command() {

                                @Override
                                public void execute() {
                                    valueMap.remove(key);
                                    if (getTableViewer() != null) {
                                        getTableViewer().refresh();
                                    }
                                }

                            });
                            return schemaToEdit; // keep
                        default:

                        }
                    } else {
                        return null;
                    }
                }
            }
        }

        if (tableToEdit != null) {
            MetadataDialog dialog = new MetadataDialog(cellEditorWindow.getShell(), tableToEdit.clone(), node, null);
            dialog.setInputReadOnly(metaReadonly);
            dialog.setOutputReadOnly(metaReadonly);
            if (dialog.open() == MetadataDialog.OK && !metaReadonly) {
                final IMetadataTable oldTable = tableToEdit;
                final IMetadataTable newTable = dialog.getOutputMetaData();
                if (!oldTable.sameMetadataAs(newTable, IMetadataColumn.OPTIONS_NONE)) {
                    executeCommand(new Command() {

                        @Override
                        public void execute() {
                            oldTable.setListColumns(newTable.getListColumns());
                        }

                    });
                }

            }

            return tableToEdit.getTableName();
        } else {
            return ""; //$NON-NLS-1$
        }
    }

    private boolean isEBCDICNode(INode node) {
        if (PluginChecker.isEBCDICPluginLoaded()) {
            IEBCDICProviderService service = (IEBCDICProviderService) GlobalServiceRegister.getDefault().getService(
                    IEBCDICProviderService.class);
            if (service != null) {
                return service.isEbcdicNode(node);
            }
        }
        return false;
    }

    private EbcdicConnectionItem getRepositoryItem() {
        if (PluginChecker.isEBCDICPluginLoaded()) {
            IEBCDICProviderService service = (IEBCDICProviderService) GlobalServiceRegister.getDefault().getService(
                    IEBCDICProviderService.class);
            if (service != null) {
                return service.getRepositoryItem(node);
            }

        }
        return null;
    }

    private void executeCommand(Command cmd) {
        if (node.getProcess() instanceof IProcess2) {
            IProcess2 process = (IProcess2) node.getProcess();
            CommandStack commandStack = process.getCommandStack();
            if (commandStack != null) {
                commandStack.execute(cmd);
            } else {
                cmd.execute();
            }
            return;
        }
        cmd.execute();
    }

}
