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
package org.talend.dataprofiler.core.ui.action.provider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.talend.dataprofiler.core.manager.DQStructureManager;
import org.talend.dataprofiler.core.sql.AddSqlFileAction;
import org.talend.dataprofiler.core.sql.OpenSqlFileAction;

/**
 * DOC qzhang class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
public class NewSourceFileActionProvider extends CommonActionProvider {

    private String selectedFolderName;

    public NewSourceFileActionProvider() {
    }

    public void fillContextMenu(IMenuManager menu) {
        Object obj = ((TreeSelection) this.getContext().getSelection()).getFirstElement();
        if (obj instanceof IFolder) {
            selectedFolderName = ((IFolder) obj).getName();
            if (selectedFolderName.equals(DQStructureManager.SOURCE_FILES)) {
                menu.add(new AddSqlFileAction((IFolder) obj));
            }
        } else if (obj instanceof IFile) {
            IFile file = (IFile) obj;
            selectedFolderName = file.getName();
            if (file.getFileExtension().equalsIgnoreCase("sql")) {
                menu.add(new OpenSqlFileAction((IFile) obj));
            }

        }
    }
}
