// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils;

import org.eclipse.swt.SWT;
import org.talend.commons.ui.i18n.Messages;

/**
 * Utility class for event handling.
 */
public final class EventUtil {

    /**
     * Default Constructor. Must not be used.
     */
    private EventUtil() {
    }

    public static String getEventNameFromType(int eventType) {

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
            return Messages.getString("EventUtil.UnknowType") + eventType; //$NON-NLS-1$
        }
    }

    public static String getEventNameFromDetail(int eventDetail) {
        switch (eventDetail) {
        case SWT.None:
            return "null";
        case SWT.SELECTED:
            return "SELECTED=" + SWT.SELECTED;
        default:
            return Messages.getString("EventUtil.UnknowDetail") + eventDetail; //$NON-NLS-1$
        }
    }

    public static void main(String[] args) {
    }
}
