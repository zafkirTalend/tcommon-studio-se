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

import java.util.Date;

import org.talend.core.model.general.User;
import org.talend.core.model.properties.Property;

/**
 * Defines a repository object, an object that can be stored in the repository. <br/>
 * 
 * $Id$
 * 
 */
public interface IRepositoryObject {

    /**
     * Returns the process id. Returns -1 if process id is not set or cannot be retrieve.
     * 
     * @return the process id
     */
    public String getId();

    public void setId(String id);

    /**
     * Returns the process label. Returns null if process label is not set or cannot be retrieve.
     * 
     * @return the process id
     */
    public String getLabel();

    public void setLabel(String label);

    /**
     * Returns the process version. Returns null if process id is not set or cannot be retrieve.
     * 
     * @return the process version
     */
    public String getVersion();

    public void setVersion(String version);

    public User getAuthor();

    public void setAuthor(User author);

    public String getStatusCode();

    public void setStatusCode(String statusCode);

    public Date getCreationDate();

    public void setCreationDate(Date value);

    public String getDescription();

    public void setDescription(String value);

    public Date getModificationDate();

    public void setModificationDate(Date value);

    public String getPurpose();

    public void setPurpose(String value);

    public ERepositoryObjectType getType();

    public Property getProperty();

}
