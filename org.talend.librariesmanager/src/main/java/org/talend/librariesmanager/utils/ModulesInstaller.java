// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.librariesmanager.Activator;
import org.talend.librariesmanager.ui.dialogs.ComponentExternalModulesDialog;
import org.talend.librariesmanager.ui.dialogs.ExternalModulesInstallDialog;
import org.talend.librariesmanager.ui.dialogs.OperationExternalModulesDialog;

/**
 * created by Administrator on 2012-9-17 Detailled comment
 * 
 */
public class ModulesInstaller {

    public static void installModules(Shell shell, IComponent component) {
        if (!Activator.getDefault().getPreferenceStore()
                .getBoolean(ExternalModulesInstallDialog.DO_NOT_SHOW_EXTERNALMODULESINSTALLDIALOG)) {
            String text = "Component " + component.getName() + " requires the following third party modules";
            String title = "List of modules not installed for component " + component.getName();
            if (!component.getModulesNeeded().isEmpty()) {
                ComponentExternalModulesDialog dialog = new ComponentExternalModulesDialog(shell, component.getModulesNeeded(),
                        text, title);
                if (dialog.needOpen()) {
                    dialog.open();
                }
            }
        }
    }

    public static void installModules(Shell shell, List<IComponent> components) {
        if (!Activator.getDefault().getPreferenceStore()
                .getBoolean(ExternalModulesInstallDialog.DO_NOT_SHOW_EXTERNALMODULESINSTALLDIALOG)) {
            String text = "Selected components require the following third party modules";
            String title = "List of modules not installed for the selected components";
            List<ModuleNeeded> needed = new ArrayList<ModuleNeeded>();
            for (IComponent component : components) {
                needed.addAll(component.getModulesNeeded());
            }
            if (!needed.isEmpty()) {
                ComponentExternalModulesDialog dialog = new ComponentExternalModulesDialog(shell, needed, text, title);
                if (dialog.needOpen()) {
                    dialog.open();
                }
            }
        }
    }

    public static Collection<String> installModules(Shell shell, String[] jarNames) {
        if (!Activator.getDefault().getPreferenceStore()
                .getBoolean(ExternalModulesInstallDialog.DO_NOT_SHOW_EXTERNALMODULESINSTALLDIALOG)) {
            String text = "Current operation  requires the following third party modules";
            String title = "List of modules not installed for component current operation";
            OperationExternalModulesDialog dialog = new OperationExternalModulesDialog(shell, jarNames, text, title);
            if (dialog.needOpen()) {
                dialog.open();
            }
            return dialog.getInstalledJars();
        }
        return null;
    }

}
