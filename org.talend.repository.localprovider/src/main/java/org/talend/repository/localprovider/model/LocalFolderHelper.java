// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.localprovider.model;

import org.eclipse.emf.ecore.resource.Resource;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.User;
import org.talend.repository.model.FolderHelper;

/**
 * DOC matthieu class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LocalFolderHelper extends FolderHelper {

    protected LocalFolderHelper(Project project, User connectedUser) {
        super(project, connectedUser);
    }

    public static FolderHelper createInstance(Project project, User connectedUser) {
        return new LocalFolderHelper(project, connectedUser);
    }

    protected void removeFromResource(FolderItem folder) {
        project.eResource().setModified(true);
        cleanResource(project.eResource(), folder);
    }

    private void cleanResource(Resource resource, FolderItem folder) {
        resource.getContents().remove(folder.getProperty());
        if (folder.getState() != null) {
            resource.getContents().remove(folder.getState());
        }
        for (Object o : folder.getChildren()) {
            cleanResource(resource, (FolderItem) o);
        }
    }

    protected void doCreateFolder(FolderItem folderItem) {
        project.eResource().getContents().add(folderItem.getProperty());
        project.eResource().setModified(true);
    }

    public void doCreateItemState(FolderItem folder) {
        project.eResource().getContents().add(folder.getState());
    }
}
