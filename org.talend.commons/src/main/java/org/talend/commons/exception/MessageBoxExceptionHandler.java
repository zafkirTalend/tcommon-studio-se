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
package org.talend.commons.exception;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.i18n.internal.Messages;

/**
 * Exception handling via message box.<br/>
 * 
 * $Id$
 * 
 */
public final class MessageBoxExceptionHandler {

    private static Throwable lastShowedAction;

    /**
     * Empty constructor.
     */
    private MessageBoxExceptionHandler() {
    }

    /**
     * Log the exeption then open a message box showing a generic message and exception message.
     * 
     * @param ex - exception to log
     */
    public static void process(Throwable ex) {
        process(ex, null);
    }

    public static void process(Throwable ex, Shell shell) {
        ExceptionHandler.process(ex);

        if (shell == null) {
            try {
                shell = new Shell();
            } catch (Exception e) {
                //ignore me
            }
        }
        
        if (shell != null) {
            showMessage(ex, shell);
        }
    }

    /**
     * Open a message box showing a generic message and exception message.
     * 
     * @param ex - exception to show
     */
    protected static void showMessage(Throwable ex, Shell shell) {
        if (ex.equals(lastShowedAction)) {
            return;
        }
        lastShowedAction = ex;
        
        // TODO smallet use ErrorDialogWidthDetailArea ?
        String title = Messages.getString("commons.error"); //$NON-NLS-1$
        String msg = Messages.getString("exception.errorOccured", ex.getMessage()); //$NON-NLS-1$
        Priority priority = ExceptionHandler.getPriority(ex);

        if (priority == Level.FATAL || priority == Level.ERROR) {
            MessageDialog.openError(shell, title, msg);
        } else if (priority == Level.WARN) {
            MessageDialog.openWarning(shell, title, msg);
        } else if (priority == Level.INFO) {
            MessageDialog.openInformation(shell, title, msg);
        } else {
            MessageDialog.openInformation(shell, title, msg);
        }
    }
}
