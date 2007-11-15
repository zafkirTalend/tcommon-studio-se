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
package org.talend.core.model.repository;

import java.util.ArrayList;
import java.util.List;

import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.Property;

/**
 * Represents a folder as a repository object.<br/>
 * 
 * A folder has (in this version) no version, no author.
 * 
 * $Id$
 * 
 */
public class Folder extends RepositoryObject implements IRepositoryObject {

    private ERepositoryObjectType type;

    public Folder(String id, String label) {
        super(id, label);
    }

    public Folder(Property property, ERepositoryObjectType type) {
        super(property);
        this.type = type;
    }

    /**
     * Returns the type.
     * 
     * @return ERepositoryObjectType.FOLDER
     */
    public ERepositoryObjectType getType() {
        return ERepositoryObjectType.FOLDER;
    }

    public ERepositoryObjectType getContentType() {
        return this.type;
    }

    @Override
    public List<IRepositoryObject> getChildren() {
        List<IRepositoryObject> toReturn = new ArrayList<IRepositoryObject>();
        FolderItem folderItem = (FolderItem) getProperty().getItem();

        for (Object current : folderItem.getChildren()) {
            IRepositoryObject currentChild = new Folder(((FolderItem) current).getProperty(), getContentType());
            toReturn.add(currentChild);
        }

        return toReturn;
    }

}
