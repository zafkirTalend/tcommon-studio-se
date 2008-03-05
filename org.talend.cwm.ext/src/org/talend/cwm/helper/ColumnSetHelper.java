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
package org.talend.cwm.helper;

import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.QueryColumnSet;

/**
 * @author scorreia
 * 
 * Utility class for handling ColumnSets.
 */
public class ColumnSetHelper {

    /**
     * Method "addColumn" adds the given column to the given column set.
     * 
     * @param column the column to add
     * @param columnSet the column set
     * @return true if the column set has changed.
     */
    public static boolean addColumn(Column column, ColumnSet columnSet) {
        return columnSet.getFeature().add(column);
    }

    /**
     * Method "createQueryColumnSet".
     * 
     * @return a Query column set
     */
    public static QueryColumnSet createQueryColumnSet() {
        return orgomg.cwm.resource.relational.RelationalFactory.eINSTANCE.createQueryColumnSet();
    }

}
