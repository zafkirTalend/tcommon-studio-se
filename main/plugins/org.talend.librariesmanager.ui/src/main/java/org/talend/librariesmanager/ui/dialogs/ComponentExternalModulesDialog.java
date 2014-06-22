// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.librariesmanager.ui.LibManagerUiPlugin;
import org.talend.librariesmanager.ui.i18n.Messages;
import org.talend.librariesmanager.utils.RemoteModulesHelper;

/**
 * created by Administrator on 2012-9-18 Detailled comment
 * 
 */
public class ComponentExternalModulesDialog extends ExternalModulesInstallDialog {

    private List<ModuleNeeded> modules;

    private Button doNotShowBtn;

    /**
     * DOC Administrator ComponentExternalModulesDialog constructor comment.
     * 
     * @param shell
     * @param modulesToInstall
     */
    public ComponentExternalModulesDialog(Shell shell, List<ModuleNeeded> modules, String text, String title) {
        super(shell, text, title);
        this.modules = modules;
    }

    public ComponentExternalModulesDialog(Shell shell, String text, String title) {
        super(shell, text, title);
    }

    @Override
    protected void createFooter(Composite parent) {
        doNotShowBtn = new Button(parent, SWT.CHECK);
        doNotShowBtn.setText(Messages.getString("ComponentExternalModulesDialog.doNotShow"));
        doNotShowBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                LibManagerUiPlugin.getDefault().getPreferenceStore().setValue(DO_NOT_SHOW_EXTERNALMODULESINSTALLDIALOG, true);
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.librariesmanager.ui.dialogs.ExternalModulesInstallDialog#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(text);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.librariesmanager.ui.dialogs.ExternalModulesInstallDialog#getModulesToInstall()
     */
    @Override
    public void openDialog() {
        List<ModuleNeeded> updatedModules = new ArrayList<ModuleNeeded>();
        // get module from provider incase it is rested
        List<ModuleNeeded> modulesNeeded = ModulesNeededProvider.getModulesNeeded();
        if (modules != null) {
            for (ModuleNeeded module : modules) {
                for (ModuleNeeded fromProvider : modulesNeeded) {
                    if (fromProvider.getModuleName().equals(module.getModuleName())
                            && fromProvider.getContext().equals(module.getContext())
                            && ELibraryInstallStatus.NOT_INSTALLED == fromProvider.getStatus()) {
                        updatedModules.add(fromProvider);
                        break;
                    }
                }
            }
        }
        inputList.clear();
        RemoteModulesHelper.getInstance().getNotInstalledModules(updatedModules, inputList, this);
    }

    @Override
    public void listModulesDone() {

        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (inputList.size() > 0) {
                    open();
                }
            }
        });

    }

}
