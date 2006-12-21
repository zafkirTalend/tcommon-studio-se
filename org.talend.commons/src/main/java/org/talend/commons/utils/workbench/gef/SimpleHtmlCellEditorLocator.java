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
package org.talend.commons.utils.workbench.gef;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

/**
 * Create a text cell editor to edit a text in GEF. <br/>
 * 
 * $Id$
 * 
 */
public final class SimpleHtmlCellEditorLocator implements CellEditorLocator {

    /**
     * 
     */
    private static final int A_4 = 4;

    private SimpleHtmlFigure label;

    public SimpleHtmlCellEditorLocator(SimpleHtmlFigure label) {
        setLabel(label);
    }

    public void relocate(CellEditor celleditor) {
        Text text = (Text) celleditor.getControl();
        Point sel = text.getSelection();
        Point pref = text.computeSize(-1, -1);
        Rectangle rect = label.getBounds().getCopy();
        label.translateToAbsolute(rect);
        text.setBounds(rect.x - A_4, rect.y - 1, pref.x + 1, pref.y + 1);
        text.setSelection(0);
        text.setSelection(sel);
    }

    protected SimpleHtmlFigure getLabel() {
        return label;
    }

    protected void setLabel(SimpleHtmlFigure label) {
        this.label = label;
    }
}
