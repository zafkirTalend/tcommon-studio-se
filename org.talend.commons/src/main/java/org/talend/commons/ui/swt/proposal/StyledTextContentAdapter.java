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
package org.talend.commons.ui.swt.proposal;

import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class StyledTextContentAdapter implements IControlContentAdapter {

    public String getControlContents(Control control) {
        return ((StyledText) control).getText();
    }

    public void setControlContents(Control control, String text, int cursorPosition) {
        ((StyledText) control).setText(text);
        ((StyledText) control).setSelection(cursorPosition, cursorPosition);
    }

    public void insertControlContents(Control control, String text, int cursorPosition) {
        Point selection = ((StyledText) control).getSelection();
        StyledText styledText = (StyledText) control;
        if (selection.x != selection.y) {
            styledText.replaceTextRange(selection.x, Math.abs(selection.y - selection.x), text);
        } else {
            styledText.insert(text);
        }
        int offsetCursor = selection.x + text.length();
        int textLength = styledText.getText().length();
        if (offsetCursor <= textLength) {
            styledText.setSelection(offsetCursor, offsetCursor);
        }
    }

    public int getCursorPosition(Control control) {
        return ((StyledText) control).getCaretOffset();
    }

    public Rectangle getInsertionBounds(Control control) {
        StyledText text = (StyledText) control;
        Point selection = text.getSelection();
        Point pointLocation = text.getLocationAtOffset(selection.x);
        Rectangle insertionBounds = new Rectangle(pointLocation.x, pointLocation.y, 1, text.getLineHeight() + 2);
        return insertionBounds;
    }

    public void setCursorPosition(Control control, int position) {
//        ((StyledText) control).setSelection(new Point(position, position));
    }
}
