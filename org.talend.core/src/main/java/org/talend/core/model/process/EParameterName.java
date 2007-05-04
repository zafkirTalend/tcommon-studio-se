// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.core.model.process;

/**
 * Enumeration that describes all the standard name used in the properties.
 * 
 * $Id: EParameterName.java 2738 2007-03-28 13:12:27 +0000 (星期三, 28 三月 2007) plegall $
 * 
 */
public enum EParameterName {

    REPOSITORY_SCHEMA_TYPE("Repository"), //$NON-NLS-1$  Hidden parameter so no translation needed
    REPOSITORY_QUERYSTORE_TYPE("Repository"), //$NON-NLS-1$  Hidden parameter so no translation needed
    REPOSITORY_ENCODING_TYPE("Repository"), //$NON-NLS-1$  Hidden parameter so no translation needed
    REPOSITORY_PROPERTY_TYPE("Repository");  //$NON-NLS-1$  Hidden parameter so no translation needed

    private String displayName;

    EParameterName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.toString();
    }

    private String getDisplayName() {
        return this.displayName;
    }
}
