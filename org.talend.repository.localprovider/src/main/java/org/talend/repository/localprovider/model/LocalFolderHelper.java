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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.FolderType;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.repository.model.FolderHelper;

/**
 * DOC matthieu class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class LocalFolderHelper extends FolderHelper {

    protected LocalFolderHelper(Project project) {
        super(project);
    }

    public static FolderHelper createInstance(Project project) {
        return new LocalFolderHelper(project);
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

    protected FolderItem doCreateFolder(String name, FolderType type) {
        FolderItem folder;
        folder = PropertiesFactory.eINSTANCE.createFolderItem();
        folder.setType(type);

        Property property = PropertiesFactory.eINSTANCE.createProperty();
        project.eResource().getContents().add(property);
        property.setId(EcoreUtil.generateUUID());
        property.setLabel(name);
        folder.setProperty(property);

        createItemState(folder);

        project.eResource().setModified(true);
        return folder;
    }

    public void doCreateItemState(FolderItem folder) {
        project.eResource().getContents().add(folder.getState());
    }
}
