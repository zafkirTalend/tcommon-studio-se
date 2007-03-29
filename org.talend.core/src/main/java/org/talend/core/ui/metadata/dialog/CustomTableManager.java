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
package org.talend.core.ui.metadata.dialog;

import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultCellModifier;
import org.talend.commons.ui.swt.tableviewer.behavior.DefaultTableLabelProvider;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.ui.metadata.editor.AbstractMetadataTableEditorView;
import org.talend.core.ui.metadata.editor.MetadataTableEditorView;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class CustomTableManager {

    public static final Color CUSTOM_CELL_BG_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);

    public static final Color READONLY_CUSTOM_CELL_BG_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);

    public static final Color STANDARD_CELL_BG_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);

    public static void addCustomManagementToTable(TableViewerCreator tableViewerCreator, boolean readOnly) {
        CustomTableLabelProvider tableProvider = new CustomTableLabelProvider(tableViewerCreator, readOnly);
        tableViewerCreator.setLabelProvider(tableProvider);
        tableViewerCreator.init(tableViewerCreator.getInputList());
        tableViewerCreator.setCellModifier(new CustomTableCellModifier(tableViewerCreator));
    }

    public static void addCustomManagementToToolBar(final MetadataTableEditorView tableEditorView,
            final IMetadataTable table, final boolean readOnly) {
        tableEditorView.getTableViewerCreator().getTableViewer().addPostSelectionChangedListener(
                new ISelectionChangedListener() {

                    public void selectionChanged(SelectionChangedEvent event) {
                        updateToolBarButtonsOnSelection(event.getSelection(), tableEditorView, table, readOnly);
                    }

                });
        boolean isThereCustom = false;
        for (IMetadataColumn column : table.getListColumns()) {
            if (column.isCustom()) {
                isThereCustom = true;
            }
        }
        if (isThereCustom) {
            tableEditorView.getToolBar().getImportButton().getButton().setEnabled(false);
        }
        if (table.isReadOnly()) {
            tableEditorView.getToolBar().getAddButton().getButton().setEnabled(false);
            tableEditorView.getToolBar().getMoveDownButton().getButton().setEnabled(false);
            tableEditorView.getToolBar().getMoveUpButton().getButton().setEnabled(false);
            tableEditorView.getToolBar().getRemoveButton().getButton().setEnabled(false);
            tableEditorView.getToolBar().getPasteButton().getButton().setEnabled(false);
        } else {
            tableEditorView.getToolBar().getAddButton().getButton().addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {
                }

                public void widgetSelected(SelectionEvent e) {
                    table.sortCustomColumns();
                    tableEditorView.getTableViewerCreator().getTableViewer().refresh();
                }

            });
        }
        SelectionListener customListener = new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                updateToolBarButtonsOnSelection(
                        tableEditorView.getTableViewerCreator().getTableViewer().getSelection(), tableEditorView,
                        table, readOnly);
            }

        };
        tableEditorView.getToolBar().getRemoveButton().getButton().addSelectionListener(customListener);
        tableEditorView.getToolBar().getCopyButton().getButton().addSelectionListener(customListener);
    }

    private static void updateToolBarButtonsOnSelection(ISelection currentSelection,
            MetadataTableEditorView tableEditorView, IMetadataTable table, boolean readOnly) {
        IStructuredSelection selection = (IStructuredSelection) currentSelection;
        boolean isThereCustom = false;
        for (Iterator iter = selection.iterator(); iter.hasNext();) {
            IMetadataColumn column = (IMetadataColumn) iter.next();
            if (column.isCustom()) {
                isThereCustom = true;
            }
        }
        if (isThereCustom || table.isReadOnly()) {
            tableEditorView.getToolBar().getMoveDownButton().getButton().setEnabled(false);
            tableEditorView.getToolBar().getMoveUpButton().getButton().setEnabled(false);
            tableEditorView.getToolBar().getRemoveButton().getButton().setEnabled(false);
            tableEditorView.getToolBar().getPasteButton().getButton().setEnabled(false);
        } else if (!readOnly) {
            tableEditorView.getToolBar().getMoveDownButton().getButton().setEnabled(true);
            tableEditorView.getToolBar().getMoveUpButton().getButton().setEnabled(true);
            tableEditorView.getToolBar().getRemoveButton().getButton().setEnabled(true);
            tableEditorView.getToolBar().getPasteButton().getButton().setEnabled(false);
        }

    }

    /**
     * DOC nrousseau CustomTableManager class global comment. Detailled comment <br/>
     * 
     * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
     * 
     */
    private static class CustomTableLabelProvider extends DefaultTableLabelProvider {

        private boolean readOnly = false;

        public CustomTableLabelProvider(TableViewerCreator tableViewerCreator, boolean readOnly) {
            super(tableViewerCreator);
            this.readOnly = readOnly;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.tableviewer.behavior.DefaultTableLabelProvider#getBackground(java.lang.Object,
         * int)
         */
        @Override
        public Color getBackground(Object element, int columnIndex) {
            if (!(element instanceof IMetadataColumn)) {
                return null;
            }
            IMetadataColumn column = (IMetadataColumn) element;
            if (column.isCustom()) {
                if (column.isReadOnly() || readOnly) {
                    return READONLY_CUSTOM_CELL_BG_COLOR;
                } else {
                    return CUSTOM_CELL_BG_COLOR;
                }
            }
            if (column.isReadOnly()) {
                return STANDARD_CELL_BG_COLOR;
            }
            return super.getBackground(element, columnIndex);
        }
    }

    /**
     * DOC nrousseau class global comment. Detailled comment <br/>
     * 
     * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
     * 
     */
    private static class CustomTableCellModifier extends DefaultCellModifier {

        public CustomTableCellModifier(TableViewerCreator tableViewerCreator) {
            super(tableViewerCreator);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.commons.ui.swt.tableviewer.behavior.DefaultCellModifier#canModify(java.lang.Object,
         * java.lang.String)
         */
        @Override
        public boolean canModify(Object element, String property) {
            if (element instanceof IMetadataColumn) {
                IMetadataColumn column = (IMetadataColumn) element;
                if (column.isReadOnly()
                        || (column.isCustom() && property.equals(AbstractMetadataTableEditorView.ID_COLUMN_NAME))) {
                    return false;
                }
            }
            return super.canModify(element, property);
        }
    }
}
