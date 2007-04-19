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
 * Different types of connections in Talend. <br/>
 * 
 * $Id$
 * 
 */
public enum EConnectionType implements IConnectionCategory {
    FLOW_MAIN(0, "FLOW", EConnectionCategory.MAIN, MAIN | DATA | FLOW | CUSTOM_NAME | UNIQUE_NAME), //$NON-NLS-1$
    RUN_BEFORE(1, "BEFORE", EConnectionCategory.OTHER, EXECUTION_ORDER | DEPENDENCY), //$NON-NLS-1$
    RUN_AFTER(2, "AFTER", EConnectionCategory.OTHER, EXECUTION_ORDER | DEPENDENCY), //$NON-NLS-1$
    LOOKUP(3, "LOOKUP", EConnectionCategory.OTHER, USE_HASH | CUSTOM_NAME | UNIQUE_NAME), //$NON-NLS-1$
    RUN_IF_OK(4, "RUN_OK", EConnectionCategory.OTHER, CONDITION | DEPENDENCY), //$NON-NLS-1$
    RUN_IF_ERROR(5, "RUN_ERROR", EConnectionCategory.OTHER, CONDITION | DEPENDENCY), //$NON-NLS-1$
    RUN_IF(6, "RUN_IF", EConnectionCategory.OTHER, CONDITION | DEPENDENCY), //$NON-NLS-1$
    ITERATE(7, "ITERATE", EConnectionCategory.MAIN, MAIN), //$NON-NLS-1$
    FLOW_REF(8, "FLOW", EConnectionCategory.OTHER, DATA | FLOW | CUSTOM_NAME | USE_HASH | UNIQUE_NAME), //$NON-NLS-1$
    TABLE(9, "TABLE", EConnectionCategory.MAIN, MAIN | DATA | CUSTOM_NAME), //$NON-NLS-1$
    FLOW_MERGE(10, "FLOW", EConnectionCategory.MAIN, DATA | FLOW | CUSTOM_NAME | UNIQUE_NAME); //$NON-NLS-1$

    private String name;

    private int id;

    private EConnectionCategory category;

    private int connectionCategory;

    EConnectionType(int id, String name, EConnectionCategory category, int connectionCategory) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.connectionCategory = connectionCategory;
    }

    public static EConnectionType getTypeFromId(int id) {
        EConnectionType[] listConnectionType = EConnectionType.values();
        for (int i = 0; i < listConnectionType.length; i++) {
            if ((listConnectionType[i].getId()) == id) {
                return listConnectionType[i];
            }
        }
        // Default Value
        return EConnectionType.FLOW_MAIN;
    }

    public static EConnectionType getTypeFromName(String name) {
        EConnectionType[] listConnectionType = EConnectionType.values();
        for (int i = 0; i < listConnectionType.length; i++) {
            if (listConnectionType[i].getName().equals(name)) {
                return listConnectionType[i];
            }
        }
        // Default Value
        return EConnectionType.FLOW_MAIN;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Getter for category.
     * 
     * @return the category
     * @deprecated
     */
    public EConnectionCategory getCategory() {
        return this.category;
    }

    public boolean hasConnectionCategory(int category) {
        return (connectionCategory & category) != 0;
    }
}
