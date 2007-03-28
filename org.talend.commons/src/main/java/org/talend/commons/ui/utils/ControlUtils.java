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
package org.talend.commons.ui.utils;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.i18n.internal.Messages;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ControlUtils {

    /**
     * 
     * DOC amaumont Comment method "getText".
     * 
     * @param control
     * @return
     */
    public static String getText(Control control) {
        String value = null;
        if (control instanceof Text) {
            value = ((Text) control).getText();
        } else if (control instanceof StyledText) {
            value = ((StyledText) control).getText();
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
        return value;

    }

    /**
     * 
     * DOC amaumont Comment method "getText".
     * 
     * @param control
     * @return
     */
    public static void setText(Control control, String text) {
        if (control instanceof Text) {
            ((Text) control).setText(text);
        } else if (control instanceof StyledText) {
            ((StyledText) control).setText(text);
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }

    }

    /**
     * 
     * DOC amaumont Comment method "getText".
     * 
     * @param control
     * @return
     */
    public static void addModifyListener(Control control, ModifyListener modifyListener) {
        if (control instanceof Text) {
            ((Text) control).addModifyListener(modifyListener);
        } else if (control instanceof StyledText) {
            ((StyledText) control).addModifyListener(modifyListener);
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
    }

    /**
     * DOC amaumont Comment method "removeModifyListener".
     * 
     * @param control
     * @param modifyListener
     */
    public static void removeModifyListener(Control control, ModifyListener modifyListener) {
        if (control instanceof Text) {
            ((Text) control).removeModifyListener(modifyListener);
        } else if (control instanceof StyledText) {
            ((StyledText) control).removeModifyListener(modifyListener);
        } else {
            throw new UnsupportedOperationException(Messages.getString("ControlUtils.Unsupported1", control.getClass())); //$NON-NLS-1$
        }
    }

}
