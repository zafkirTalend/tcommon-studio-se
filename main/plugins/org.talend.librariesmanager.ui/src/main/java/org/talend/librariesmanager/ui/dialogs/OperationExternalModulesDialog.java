// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.widgets.Shell;
import org.talend.librariesmanager.utils.RemoteModulesHelper;

/**
 * created by WCHEN on 2012-9-20 Detailled comment
 * 
 */
public class OperationExternalModulesDialog extends ComponentExternalModulesDialog {

    private String[] neededJars;

    /**
     * DOC WCHEN OperationExternalModulesDialog constructor comment.
     * 
     * @param shell
     * @param modules
     * @param text
     * @param title
     */
    public OperationExternalModulesDialog(Shell shell, String[] neededJars, String text, String title) {
        super(shell, text, title);
        this.neededJars = neededJars;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.librariesmanager.ui.dialogs.ComponentExternalModulesDialog#getModulesToInstall()
     */
    @Override
    public void openDialog() {
        inputList.clear();
        if (!jarsInstalledSuccuss.isEmpty()) {
            Set<String> updated = new HashSet<String>();
            int n = 0;
            if (neededJars != null) {
                for (String jarName : neededJars) {
                    boolean found = false;
                    for (int i = 0; i < jarsInstalledSuccuss.size() && n != jarsInstalledSuccuss.size(); i++) {
                        if (jarName.endsWith(jarsInstalledSuccuss.get(i))) {
                            found = true;
                            n++;
                        }
                    }
                    if (!found) {
                        updated.add(jarName);
                    }
                }
            }
            RemoteModulesHelper.getInstance()
                    .getNotInstalledModules(updated.toArray(new String[updated.size()]), inputList, this);
        } else {
            RemoteModulesHelper.getInstance().getNotInstalledModules(neededJars, inputList, this);
        }
    }

}
