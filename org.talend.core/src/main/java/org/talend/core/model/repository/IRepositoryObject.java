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

import java.util.Date;
import java.util.List;

import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.User;

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

    public void setProperty(Property property);

    public List<IRepositoryObject> getChildren();
}
