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
package org.talend.commons.ui.swt.tableviewer.celleditor;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * DOC qiang.zhang class global comment. Detailled comment <br/>
 * 
 */
public class DateCellEditor extends DialogCellEditor {

    /**
     *  qiang.zhang DateCellEditor constructor comment.
     * 
     * @param parent
     */
    public DateCellEditor(Composite parent) {
        super(parent);
    }

    /**
     *  qiang.zhang DateCellEditor constructor comment.
     * 
     * @param parent
     * @param style
     */
    public DateCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /*
     * (non-Java)
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        DateDialog d = new DateDialog(cellEditorWindow.getShell());
        int res = d.open();
        if (res == Dialog.OK) {
            return d.getTalendDateString();
        } else {
            return getValue();
        }
    }
}
