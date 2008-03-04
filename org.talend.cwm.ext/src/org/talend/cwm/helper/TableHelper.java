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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.talend.cwm.relational.TdColumn;
import org.talend.cwm.relational.TdTable;
import orgomg.cwm.objectmodel.core.Feature;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * @author scorreia
 * 
 * Helper for Table class.
 */
public final class TableHelper {

    private TableHelper() {
    }

    /**
     * Method "getTables" extracts the tables from the list.
     * 
     * @param elements any elements that could contain TdTables
     * @return the list of TdTables found in the given list (never null, but can be empty).
     */
    public static List<TdTable> getTables(EList<ModelElement> elements) {
        List<TdTable> tables = new ArrayList<TdTable>();
        for (ModelElement modelElement : elements) {
            TdTable table = SwitchHelpers.TABLE_SWITCH.doSwitch(modelElement);
            if (table == null) {
                continue;
            }
            // else
            tables.add(table);
        }
        return tables;
    }

    /**
     * Method "getColumns" returns the columns of a table.
     * 
     * @param table a table
     * @return the list of TdColumn contained in the table
     */
    public static List<TdColumn> getColumns(TdTable table) {
        List<TdColumn> columns = new ArrayList<TdColumn>();
        EList<Feature> features = table.getFeature();
        for (Feature feature : features) {
            if (feature == null) {
                continue;
            }
            // else
            columns.add(SwitchHelpers.COLUMN_SWITCH.doSwitch(feature));
        }
        return columns;
    }

    /**
     * Method "addColumns".
     * 
     * @param table the table in which to add the columns (must not be null)
     * @param columns the columns to add (must not be null)
     * @return true if the content of the table changed as a result of the call.
     */
    public static boolean addColumns(TdTable table, Collection<TdColumn> columns) {
        assert table != null;
        assert columns != null;
        return table.getFeature().addAll(columns);
    }

    /**
     * Method "addColumn" adds a column to the given table
     * 
     * @param table the table in which the column is added (must not be null)
     * @param column the column to add (must not be null)
     * @return true if the content of the table changed as a result of the call.
     */
    public static boolean addColumn(TdTable table, TdColumn column) {
        assert table != null;
        assert column != null;
        return table.getFeature().add(column);
    }
}
