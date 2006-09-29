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

import org.eclipse.emf.ecore.EObject;
import org.talend.core.model.general.User;
import org.talend.core.model.general.Version;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.CSVFileConnectionItem;
import org.talend.core.model.properties.DatabaseConnectionItem;
import org.talend.core.model.properties.DelimitedFileConnectionItem;
import org.talend.core.model.properties.DocumentationItem;
import org.talend.core.model.properties.PositionalFileConnectionItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RegExFileConnectionItem;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.util.PropertiesSwitch;

public class RepositoryObject implements IRepositoryObject {

    protected Property property = PropertiesFactory.eINSTANCE.createProperty();

    public RepositoryObject() {
    }

    public RepositoryObject(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return this.property;
    }

    public RepositoryObject(String id, String label) {
        this.setId(id);
        this.setLabel(label);
    }

    public User getAuthor() {
        return new User(this.property.getAuthor());
    }

    public Date getCreationDate() {
        return this.property.getCreationDate();
    }

    public String getDescription() {
        return this.property.getDescription();
    }

    public String getId() {
        return this.property.getId();
    }

    public Date getModificationDate() {
        return this.property.getModificationDate();
    }

    public String getLabel() {
        return this.property.getLabel();
    }

    public String getPurpose() {
        return this.property.getPurpose();
    }

    public String getStatusCode() {
        return this.property.getStatusCode();
    }

    public Version getVersion() {
        try {
            return new Version(this.property.getVersion());
        } catch (RuntimeException e) {
            return new Version();
        }
    }

    public void setAuthor(User value) {
        this.property.setAuthor(value.getEmfUser());
    }

    public void setCreationDate(Date value) {
        this.property.setCreationDate(value);
    }

    public void setDescription(String value) {
        this.property.setDescription(value);
    }

    public void setId(String value) {
        this.property.setId(value);
    }

    public void setModificationDate(Date value) {
        this.property.setModificationDate(value);
    }

    public void setLabel(String value) {
        this.property.setLabel(value);
    }

    public void setPurpose(String value) {
        this.property.setPurpose(value);
    }

    public void setStatusCode(String value) {
        this.property.setStatusCode(value);
    }

    public void setVersion(Version value) {
        this.property.setVersion(value.toString());
    }

    public ERepositoryObjectType getType() {
        return (ERepositoryObjectType) new PropertiesSwitch() {

            public Object caseDocumentationItem(DocumentationItem object) {
                return ERepositoryObjectType.DOCUMENTATION;
            }

            public Object caseRoutineItem(RoutineItem object) {
                return ERepositoryObjectType.ROUTINES;
            }

            public Object caseProcessItem(ProcessItem object) {
                return ERepositoryObjectType.PROCESS;
            }

            public Object caseBusinessProcessItem(BusinessProcessItem object) {
                return ERepositoryObjectType.BUSINESS_PROCESS;
            }

            public Object caseCSVFileConnectionItem(CSVFileConnectionItem object) {
                throw new IllegalStateException("not implemented");
            }

            public Object caseDatabaseConnectionItem(DatabaseConnectionItem object) {
                return ERepositoryObjectType.METADATA_CONNECTIONS;
            }

            public Object caseDelimitedFileConnectionItem(DelimitedFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_DELIMITED;
            }

            public Object casePositionalFileConnectionItem(PositionalFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_POSITIONAL;
            }

            public Object caseRegExFileConnectionItem(RegExFileConnectionItem object) {
                return ERepositoryObjectType.METADATA_FILE_REGEXP;
            }

            public Object defaultCase(EObject object) {
                throw new IllegalStateException();
            }
        }.doSwitch(property.getItem());
    }
}
