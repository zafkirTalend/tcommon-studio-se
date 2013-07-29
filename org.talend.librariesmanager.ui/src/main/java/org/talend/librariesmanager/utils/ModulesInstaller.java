// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.utils;

import org.eclipse.swt.widgets.Shell;
import org.talend.librariesmanager.ui.LibManagerUiPlugin;
import org.talend.librariesmanager.ui.dialogs.ExternalModulesInstallDialog;
import org.talend.librariesmanager.ui.dialogs.OperationExternalModulesDialog;
import org.talend.librariesmanager.ui.i18n.Messages;

/**
 * created by Administrator on 2012-9-17 Detailled comment
 * 
 */
public class ModulesInstaller {

    public static void installModules(Shell shell, String[] jarNames) {
        if (!LibManagerUiPlugin.getDefault().getPreferenceStore()
                .getBoolean(ExternalModulesInstallDialog.DO_NOT_SHOW_EXTERNALMODULESINSTALLDIALOG)) {
            String text = Messages.getString("ModulesInstaller_text"); //$NON-NLS-1$
            String title = Messages.getString("ModulesInstaller_title"); //$NON-NLS-1$
            OperationExternalModulesDialog dialog = new OperationExternalModulesDialog(shell, jarNames, text, title);
            dialog.openDialog();
        }
    }

}
