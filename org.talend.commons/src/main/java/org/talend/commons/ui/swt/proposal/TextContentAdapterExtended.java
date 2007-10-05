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
package org.talend.commons.ui.swt.proposal;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class TextContentAdapterExtended extends TextContentAdapter implements IControlContentAdapterExtended {

    private String filterValue;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.mapper.ui.proposal.expression.IControlContentAdapterExtended#getFilterValue()
     */
    public String getFilterValue(Control control) {
        String controlContents = getControlContents(control);
        int cursorPosition = getCursorPosition(control);
        String text = controlContents.substring(0, cursorPosition);
        int lastCRIndex = text.lastIndexOf("\n");
        int lastSpaceIndex = text.lastIndexOf(" ");
        if (lastSpaceIndex != -1 && (lastCRIndex != -1 && lastSpaceIndex > lastCRIndex || lastCRIndex == -1)) {
            return text.substring(lastSpaceIndex + 1, text.length());
        }
        if (lastCRIndex != -1) {
            return text.substring(lastCRIndex + 1, text.length());
        }
        return text;
    }

    public void insertControlContents(Control control, String text, int cursorPosition) {
        int filterValueLength = filterValue.length();
        String controlContents = getControlContents(control);
        Point selection = ((Text) control).getSelection();
        Text controlText = (Text) control;
        if (selection.x != selection.y) {
            super.insertControlContents(control, text, cursorPosition);
            return;
        } else {
            int remaingCharsOffset = 0;
            int nextSpaceIndex = -1;
            char[] separators = new char[] { ' ', '.', '\'', '"', '(', ')', '+' };
            for (int i = selection.x; i < controlContents.length(); i++) {
                if (ArrayUtils.contains(separators, controlContents.charAt(i))) {
                    nextSpaceIndex = i;
                    break;
                }
            }
            int nextCrIndex = controlContents.indexOf('\n', selection.x);
            if (filterValueLength == 0) {
                remaingCharsOffset = 0;
            } else if (nextSpaceIndex != -1 && (nextCrIndex != -1 && nextSpaceIndex < nextCrIndex || nextCrIndex == -1)) {
                remaingCharsOffset = nextSpaceIndex - selection.x;
            } else if (nextCrIndex != -1) {
                remaingCharsOffset = nextCrIndex - 1 - selection.x;
            } else {
                remaingCharsOffset = controlContents.length() - selection.x;
            }
            int end1 = selection.x - filterValueLength - 1;
            String beforeSelection = controlText.getText(0, end1);
            int start2 = selection.x + remaingCharsOffset;
            String afterSelection = controlText.getText(start2, controlText.getCharCount());
            String newText = beforeSelection + text + afterSelection;
            controlText.setText(newText);
        }
        int offsetCursor = selection.x - filterValueLength + text.length();
        int textLength = controlText.getText().length();
        if (offsetCursor <= textLength) {
            controlText.setSelection(offsetCursor, offsetCursor);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.proposal.IControlContentAdapterExtended#setUsedFilterValue(java.lang.String)
     */
    public void setUsedFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

}
