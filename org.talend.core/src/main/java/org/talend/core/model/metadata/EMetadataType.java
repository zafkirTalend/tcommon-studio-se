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
package org.talend.core.model.metadata;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public enum EMetadataType {
    INTEGER("Integer", ""),
    STRING("String", "");
    //BOOLEAN("Boolean", ""),
    //DATE("Date", "");

    private String displayName;

    private String comment;

    /**
     * DOC nrousseau EMetaDataType constructor comment.
     * 
     * @param name
     * @param comment
     */
    EMetadataType(String displayName, String comment) {
        this.displayName = displayName;
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public String getName() {
        return toString();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static EMetadataType getTypeByName(String name) {
        for (int i = 0; i < EMetadataType.values().length; i++) {
            if (EMetadataType.values()[i].getName().equals(name)) {
                return EMetadataType.values()[i];
            }
        }
        return STRING; // Default Value
    }
}
