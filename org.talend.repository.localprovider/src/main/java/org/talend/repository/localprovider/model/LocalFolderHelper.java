// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
