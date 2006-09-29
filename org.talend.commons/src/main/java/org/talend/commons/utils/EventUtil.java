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
package org.talend.commons.utils;

import org.eclipse.swt.SWT;

/**
 * Utility class for event handling.
 */
public final class EventUtil {
    /**
     * Default Constructor.
     * Must not be used.
     */
    private EventUtil() {
    }

    public static String getEventName(int eventType) {

        switch (eventType) {
        case SWT.None:
            return "null";
        case SWT.Activate:
            return "activate";
        case SWT.Arm:
            return "arm";
        case SWT.Close:
            return "close";
        case SWT.Collapse:
            return "collapse";
        case SWT.Deactivate:
            return "deactivate";
        case SWT.Deiconify:
            return "deiconify";
        case SWT.DefaultSelection:
            return "default selection";
        case SWT.Dispose:
            return "dispose";
        case SWT.DragDetect:
            return "drag detect";
        case SWT.Expand:
            return "expand";
        case SWT.FocusIn:
            return "focus in";
        case SWT.FocusOut:
            return "focus out";
        case SWT.HardKeyDown:
            return "hard key down";
        case SWT.HardKeyUp:
            return "hard key up";
        case SWT.Help:
            return "help";
        case SWT.Hide:
            return "hide";
        case SWT.Iconify:
            return "iconify";
        case SWT.KeyDown:
            return "key down";
        case SWT.KeyUp:
            return "key up";
        case SWT.Modify:
            return "modify";
        case SWT.MenuDetect:
            return "menu detect";
        case SWT.MouseDoubleClick:
            return "mouse double click";
        case SWT.MouseDown:
            return "mouse down";
        case SWT.MouseEnter:
            return "mouse enter";
        case SWT.MouseExit:
            return "mouse exit";
        case SWT.MouseHover:
            return "mouse hover";
        case SWT.MouseMove:
            return "mouse move";
        case SWT.MouseUp:
            return "mouse up";
        case SWT.Move:
            return "move";
        case SWT.Paint:
            return "paint";
        case SWT.Resize:
            return "resize";
        case SWT.Selection:
            return "selection";
        case SWT.Show:
            return "show";
        case SWT.Traverse:
            return "traverse";
        case SWT.Verify:
            return "verify";
        default:
            return "unkown ??? value of event : " + eventType;
        }
    }

    public static void main(String[] args) {
    }
}
