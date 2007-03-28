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
package org.talend.commons.ui.swt.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.i18n.internal.Messages;

/**
 * Open an Error dialog with a details area (title = "Error Message"). This dialog box show a Details button that shows
 * or hides an extra area with more information. This functionality is provided by the JFace ErrorDialog. The naming of
 * this class is a bit unfortunate as it doesn�t fully express all the things it can be used for. A better name might
 * have been StatusDialog as it is used to display any IStatus object, which can represent information, warnings, or
 * errors. The dialog looks at the severity of the supplied status and uses an appropriate icon: an exclamation mark for
 * errors, a yield sign for warnings, and an i character for information.
 * 
 * If you want to provide more information in the details area, you need to supply a MultiStatus object. The dialog will
 * obtain the message string from the MultiStatus parent, and one line in the details area will be for the message from
 * each child status.
 * 
 * $Id$
 * 
 */
public class ErrorDialogWidthDetailArea {

    /**
     * Open an Error dialog with a details area (title = "Error Message").
     * 
     * @param shell
     * @param String (Product ID -> Activator.PLUGIN_ID)
     * @param mainMessage
     * @param detailMessage (\n an \t are interpreted ; \r are deleted)
     */
    public ErrorDialogWidthDetailArea(Shell shell, String pid, String mainMessage, String detailMessage) {
        MultiStatus info = new MultiStatus(pid, 1, mainMessage, null);
        if (detailMessage != null) {
            String[] lines = detailMessage.split("\n");
            for (int i = 0; i < lines.length; i++) {
                info.add(new Status(IStatus.INFO, pid, 1, lines[i].replaceAll("\t", "    ").replaceAll("\r", ""), null));
            }
        }
        ErrorDialog.openError(shell, Messages.getString("ErrorDialogWidthDetailArea.ErrorMessage.Text"), null, info); //$NON-NLS-1$
    }
}
