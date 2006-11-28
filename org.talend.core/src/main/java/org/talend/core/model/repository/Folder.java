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
package org.talend.core.model.repository;

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

    /**
     * Default constructor.
     * 
     * @param id - the id
     * @param label - the label
     */
    public Folder(String id, String label) {
        super(id, label);
    }

    public Folder(Property property) {
        super(property);
    }

    /**
     * Returns the type.
     * 
     * @return ERepositoryObjectType.FOLDER
     */
    public ERepositoryObjectType getType() {
        return ERepositoryObjectType.FOLDER;
    }
}
