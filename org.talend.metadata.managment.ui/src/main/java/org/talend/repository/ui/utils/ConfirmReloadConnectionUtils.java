// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.utils;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.talend.repository.ui.dialog.ConfirmReloadConnectionDialog;
import org.talend.utils.sugars.ReturnCode;

/**
 * Utilities class for ConfirmReloadConnection.
 */
public class ConfirmReloadConnectionUtils {

    public static final String RELOAD_FLAG_TRUE = "RELOAD";

    public static final String RELOAD_FLAG_FALSE = "UNRELOAD";

    public static boolean reload(String reloadFlag) {
        return RELOAD_FLAG_TRUE.equals(reloadFlag);
    }

    public static ReturnCode openConfirmReloadConnectionDialog(Shell shell) {
        ReturnCode result = new ReturnCode(false);
        ConfirmReloadConnectionDialog dialog = new ConfirmReloadConnectionDialog(shell);
        if (dialog.open() == Window.OK) {
            result.setOk(true);
            result.setMessage(dialog.getReloadFlag());
        }
        return result;
    }
}
