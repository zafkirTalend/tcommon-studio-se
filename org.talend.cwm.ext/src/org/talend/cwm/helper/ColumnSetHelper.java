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

import java.util.Collection;
import java.util.List;

import org.talend.cwm.relational.TdColumn;
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

    public static List<TdColumn> getColumns(ColumnSet columnSet) {
        return ColumnHelper.getColumns(columnSet.getFeature());
    }

    /**
     * Method "addColumns".
     * 
     * @param columnSet the column set in which to add the columns (must not be null)
     * @param columns the columns to add (must not be null)
     * @return true if the content of the table changed as a result of the call.
     */
    public static boolean addColumns(ColumnSet columnSet, Collection<? extends Column> columns) {
        assert columnSet != null;
        assert columns != null;
        return columnSet.getFeature().addAll(columns);
    }

    /**
     * Method "createQueryColumnSet".
     * 
     * @return a Query column set
     */
    public static QueryColumnSet createQueryColumnSet() {
        return orgomg.cwm.resource.relational.RelationalFactory.eINSTANCE.createQueryColumnSet();
    }

    /**
     * DMethod "createQueryColumnSet" creates a column set from given columns.
     * 
     * @param columns the columns
     * @return the column set
     */
    public static QueryColumnSet createQueryColumnSet(Column... columns) {
        QueryColumnSet columnSet = orgomg.cwm.resource.relational.RelationalFactory.eINSTANCE.createQueryColumnSet();
        for (Column column : columns) {
            addColumn(column, columnSet);
        }
        return columnSet;
    }
}
