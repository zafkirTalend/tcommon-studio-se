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

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.process.INode;
import org.talend.core.ui.metadata.dialog.MetadataDialog;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class SchemaCellEditor extends DialogCellEditor {

    private INode node;

    public SchemaCellEditor(Composite parent, INode node) {
        super(parent, SWT.NONE);
        this.node = node;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        String schemaToEdit = (String) this.getValue();

        final List<IMetadataTable> metadatas = node.getMetadataList();
        IMetadataTable tableToEdit = null;
        if (schemaToEdit == null || "".equals(schemaToEdit)) {
            InputDialog dialogInput = new InputDialog(cellEditorWindow.getShell(), "Give the name for the schema", "Schema Name",
                    "", new IInputValidator() {

                        public String isValid(String newText) {
                            if (!node.getProcess().checkValidConnectionName(newText)) {
                                return "This name already exists";
                            }
                            return null;
                        }

                    });

            if (dialogInput.open() == InputDialog.OK) {
                node.getProcess().addUniqueConnectionName(dialogInput.getValue());
                MetadataTable table = new MetadataTable();
                table.setTableName(dialogInput.getValue());
                tableToEdit = table;
                metadatas.add(table);
            }
        } else {
            for (IMetadataTable metadata : metadatas) {
                if (metadata.getTableName().equals(schemaToEdit)) {
                    tableToEdit = metadata;
                    break;
                }
            }
        }

        if (tableToEdit != null) {
            MetadataDialog dialog = new MetadataDialog(cellEditorWindow.getShell(), tableToEdit, node, null);
            if (dialog.open() == MetadataDialog.OK) {
                tableToEdit.setListColumns(dialog.getOutputMetaData().getListColumns());
            }

            return tableToEdit.getTableName();
        } else {
            return "";
        }
    }

}
