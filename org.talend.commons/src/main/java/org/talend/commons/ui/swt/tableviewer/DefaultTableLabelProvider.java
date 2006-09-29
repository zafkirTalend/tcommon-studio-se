// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.commons.ui.swt.tableviewer;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class DefaultTableLabelProvider implements ITableLabelProvider {

   protected TableViewerCreator tableViewerCreator;

    public DefaultTableLabelProvider(TableViewerCreator tableViewerCreator) {
        super();
        this.tableViewerCreator = tableViewerCreator;
    }

    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        String returnValue = null;
        TableViewerCreatorColumn tableEditorColumn = (TableViewerCreatorColumn) this.tableViewerCreator.getColumns()
                .get(columnIndex);
        if (tableEditorColumn.getDisplayedValue() != null || tableEditorColumn.getDefaultDisplayedValue() != null
                || tableEditorColumn.getBeanPropertyName() == null
                && tableEditorColumn.getBeanPropertyAccessors() == null) {
            String defaultValue = tableEditorColumn.getDefaultDisplayedValue();
            String imposedDisplayedValue = tableEditorColumn.getDisplayedValue();
            if (imposedDisplayedValue != null) {
                returnValue = imposedDisplayedValue;
            } else if (defaultValue == null) {
                returnValue = "";
            } else {
                returnValue = defaultValue;
            }
        } else {
            Object value = AccessorUtils.get(element, tableEditorColumn);
            CellEditor cellEditor = tableEditorColumn.getCellEditor();
            CellEditorValueAdapter retrieverValue = tableEditorColumn.getRetrieverValue();
            if (cellEditor != null && retrieverValue != null && value != null) {
                returnValue = retrieverValue.getColumnText(cellEditor, value);
            } else if (value != null) {
                returnValue = String.valueOf(value);
            } else {
                returnValue = "";
            }
        }
        return returnValue;
    }

    public void addListener(ILabelProviderListener listener) {
    }

    public void dispose() {
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener lpl) {
    }
}
