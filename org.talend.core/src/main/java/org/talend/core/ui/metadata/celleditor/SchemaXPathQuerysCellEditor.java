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
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess2;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class SchemaXPathQuerysCellEditor extends DialogCellEditor {

    private INode node;

    private AbstractDataTableEditorView tableEditorView;

    private Map mapping;

    public SchemaXPathQuerysCellEditor(Composite parent, INode node) {
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
        // itemDisplayCodename
        Object firstElement = ((StructuredSelection) this.getTableViewer().getSelection()).getFirstElement();
        mapping = ((Map) firstElement);

        String[] listItemsDisplayCodeName = node.getElementParameter("SCHEMAS").getListItemsDisplayCodeName(); //$NON-NLS-1$
        Object object = ((Map) firstElement).get(listItemsDisplayCodeName[0]);
        List<IMetadataTable> metadataList = node.getMetadataList();
        List<IMetadataColumn> listColumns = null;
        for (IMetadataTable metadataTable : metadataList) {
            if (metadataTable.getTableName().equals(object.toString())) {
                listColumns = metadataTable.getListColumns();
            }
        }
        if (object != null && !"".equals(object.toString())) { //$NON-NLS-1$
            Shell activeShell = Display.getCurrent().getActiveShell();
            SchemaXPathQuerysDialog schemaXPathQuerysDialog = new SchemaXPathQuerysDialog(activeShell, listColumns, mapping);

            if (schemaXPathQuerysDialog.open() == Window.OK) {
                executeCommand(new XPathQueryChangeCommand(mapping, schemaXPathQuerysDialog.getValues()));
                getTableViewer().refresh();
                return schemaXPathQuerysDialog.getValues();
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

    /**
     * 
     * zli XPathQueryChangeCommand class global comment. Detailled comment
     */
    class XPathQueryChangeCommand extends Command {

        private String oldValue, newValue;

        private Map<String, Object> line;

        public XPathQueryChangeCommand(Map<String, Object> line, String newValue) {
            super();
            this.line = line;
            this.newValue = newValue;
            this.oldValue = (String) line.get("MAPPING"); //$NON-NLS-1$
        }

        @Override
        public void execute() {
            line.put("MAPPING", newValue); //$NON-NLS-1$
        }

        @Override
        public void undo() {
            line.put("MAPPING", oldValue); //$NON-NLS-1$
        }

    }
}
