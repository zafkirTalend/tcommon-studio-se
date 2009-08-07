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
package org.talend.core.ui.metadata.celleditor.xpathquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.command.CommandStackForComposite;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess2;
import org.talend.core.ui.metadata.celleditor.SchemaXPathQuerysCellEditor;

/**
 * cli class global comment. Detailled comment
 */
public class SchemaXPathQuerysDialog extends Dialog {

    private static final String COMMA = ","; //$NON-NLS-1$

    private static final String MAPPING = "MAPPING"; //$NON-NLS-1$

    private INode node;

    private IMetadataTable metadataTable;

    private Map<String, Object> mapping;

    private String values;

    /*
     * 
     */
    private XPathQueryMetadataTableEditorExt metadataTableEditor;

    private XPathQueryMetadataTableEditorViewExt metadataTableEditorView;

    public SchemaXPathQuerysDialog(Shell parentShell) {
        super(parentShell);
    }

    public SchemaXPathQuerysDialog(Shell parentShell, INode node, IMetadataTable metadataTable, Map mapping) {
        super(parentShell);
        this.node = node;
        this.metadataTable = metadataTable.clone(true);
        this.mapping = mapping;
        // this.setShellStyle(this.getShellStyle() | SWT.RESIZE);

        convertMetadataTable();
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.getString("SchemaXPathQuerysDialog.Mappings")); //$NON-NLS-1$

    }

    private void convertMetadataTable() {
        List<XPathQueryMetadataColumnExt> newColumns = new ArrayList<XPathQueryMetadataColumnExt>();
        List<IMetadataColumn> listColumns = metadataTable.getListColumns();

        String[] mappingValues = getMappingValues();

        for (int i = 0; i < listColumns.size(); i++) {
            IMetadataColumn col = listColumns.get(i);
            XPathQueryMetadataColumnExt colExt = new XPathQueryMetadataColumnExt(col);
            if (i < mappingValues.length) {
                colExt.setXpathQuery(mappingValues[i]);
            }
            newColumns.add(colExt);
        }
        //
        listColumns.clear();
        listColumns.addAll(newColumns);
    }

    private String[] getMappingValues() {
        String mappingValues = (String) mapping.get(MAPPING);
        if (mappingValues == null) {
            String[] mapValue = { "" };
            return mapValue;
        }
        return mappingValues.split(COMMA);
    }

    public Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        //
        final boolean readonly = node.getProcess().isReadOnly();
        metadataTableEditor = new XPathQueryMetadataTableEditorExt(metadataTable, ""); //$NON-NLS-1$
        metadataTableEditorView = new XPathQueryMetadataTableEditorViewExt(composite, SWT.BORDER, metadataTableEditor, readonly,
                !readonly);
        metadataTableEditorView.setShowDbTypeColumn(true, true, false);
        metadataTableEditorView.setShowDbColumnName(false, false);
        metadataTableEditorView.getExtendedTableViewer().setCommandStack(new CommandStackForComposite(getShell()));
        //
        GridData layoutData = new GridData(GridData.FILL_BOTH);
        layoutData.widthHint = 700;
        layoutData.heightHint = 200;
        Table table = metadataTableEditor.getTableViewer().getTable();
        table.setLayoutData(layoutData);

        return composite;
    }

    protected void okPressed() {
        values = ""; //$NON-NLS-1$
        List<IMetadataColumn> listColumns = metadataTable.getListColumns();
        final int size = listColumns.size();

        for (int i = 0; i < size; i++) {
            XPathQueryMetadataColumnExt col = (XPathQueryMetadataColumnExt) listColumns.get(i);
            String xpathQuery = col.getXpathQuery();
            if (xpathQuery == null) {
                xpathQuery = ""; //$NON-NLS-1$
            }
            xpathQuery = xpathQuery.trim();
            if (i < size - 1) {
                values += xpathQuery + COMMA; //$NON-NLS-1$
            } else {
                values += xpathQuery;
            }
        }
        values = removeEndComma(values);
        //
        executeCommand(new XPathQueryChangeCommand(node, getNewMetadataTable(), mapping, getValues()));
        super.okPressed();

    }

    private String removeEndComma(String value) {
        if (value.endsWith(COMMA)) {
            value = value.substring(0, value.length() - 1);
            value = removeEndComma(value);
        }
        return value;
    }

    public String getValues() {
        return this.values;
    }

    private IMetadataTable getNewMetadataTable() {
        return this.metadataTable;
    }

    private void executeCommand(Command cmd) {
        boolean exe = false;
        if (node.getProcess() instanceof IProcess2) {
            IProcess2 process = (IProcess2) node.getProcess();
            CommandStack commandStack = process.getCommandStack();
            if (commandStack != null) {
                commandStack.execute(cmd);
                exe = true;
            }
        }
        if (!exe) {
            cmd.execute();
        }
    }

    /**
     * 
     * cli XPathQueryChangeCommand class global comment. Detailled comment
     */
    class XPathQueryChangeCommand extends Command {

        private INode node;

        private IMetadataTable newMetadataTable, oldMetadataTable;

        private Map<String, Object> line;

        private String oldValue, newValue;

        public XPathQueryChangeCommand(INode node, IMetadataTable newMetadataTable, Map<String, Object> line, String newValue) {
            super();
            this.node = node;
            this.newMetadataTable = newMetadataTable;
            this.line = line;
            this.newValue = newValue;
            initOldValues();
        }

        private void initOldValues() {
            this.oldValue = (String) line.get(MAPPING);
            this.oldMetadataTable = SchemaXPathQuerysCellEditor.findMetadataTable(node, newMetadataTable.getTableName());
        }

        @Override
        public void execute() {
            line.put(MAPPING, newValue);
            //
            IMetadataTable table = SchemaXPathQuerysCellEditor.findMetadataTable(node, newMetadataTable.getTableName());
            table.getListColumns().clear();
            table.getListColumns().addAll(newMetadataTable.getListColumns());
        }

        @Override
        public void undo() {
            line.put(MAPPING, oldValue);
            //
            if (oldMetadataTable != null) {
                IMetadataTable table = SchemaXPathQuerysCellEditor.findMetadataTable(node, oldMetadataTable.getTableName());
                table.getListColumns().clear();
                table.getListColumns().addAll(oldMetadataTable.getListColumns());
            }
        }

    }
}
