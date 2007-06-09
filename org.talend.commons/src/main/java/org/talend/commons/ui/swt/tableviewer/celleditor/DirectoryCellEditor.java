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

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;


/**
 * DOC qiang.zhang  class global comment. Detailled comment
 * <br/>
 *
 */
public class DirectoryCellEditor extends DialogCellEditor {

    /**
     * DOC qiang.zhang DirectoryCellEditor constructor comment.
     * @param parent
     */
    public DirectoryCellEditor(Composite parent) {
        super(parent);
    }

    /**
     * DOC qiang.zhang DirectoryCellEditor constructor comment.
     * @param parent
     * @param style
     */
    public DirectoryCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        DirectoryDialog dialog = new DirectoryDialog(cellEditorWindow.getShell());
        String path = dialog.open();
        return path;
    }

}
