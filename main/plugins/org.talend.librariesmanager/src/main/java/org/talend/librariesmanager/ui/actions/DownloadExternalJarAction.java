// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.librariesmanager.ui.dialogs.ExternalModulesInstallDialog;

/**
 * created by Administrator on 2012-9-20 Detailled comment
 * 
 */
public class DownloadExternalJarAction extends Action {

    public DownloadExternalJarAction() {
        super();
        this.setText("Download external modules");
        this.setDescription("Download external modules");
        this.setImageDescriptor(ImageProvider.getImageDesc(EImage.DOWNLOAD_MODULE));
    }

    @Override
    public void run() {
        String title = "The following modules are not yet installed. Please download and install all required modules.";
        String text = "List of modules not installed in the product";
        ExternalModulesInstallDialog dialog = new ExternalModulesInstallDialog(PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getShell(), text, title);
        dialog.openDialog();
    }

}
