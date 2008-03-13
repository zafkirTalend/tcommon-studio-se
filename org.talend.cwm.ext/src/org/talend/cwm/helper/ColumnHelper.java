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
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.talend.cwm.relational.RelationalFactory;
import org.talend.cwm.relational.TdColumn;
import orgomg.cwm.resource.relational.Column;

/**
 * @author scorreia
 * 
 * Utility class for handling (Td)Columns.
 */
public class ColumnHelper {

    private ColumnHelper() {
    }

    /**
     * Method "createColumn" creates a column with the given name.
     * 
     * @param name the name of the column
     * @return the created column.
     */
    public static Column createColumn(String name) {
        Column column = orgomg.cwm.resource.relational.RelationalFactory.eINSTANCE.createColumn();
        column.setName(name);

        return column;
    }

    /**
     * Method "createColumn" creates a column with the given name.
     * 
     * @param name the name of the column
     * @return the created column.
     */
    public static TdColumn createTdColumn(String name) {
        TdColumn column = RelationalFactory.eINSTANCE.createTdColumn();
        column.setName(name);
        return column;
    }

    /**
     * Method "getColumns" returns the columns that are in the list.
     * 
     * @param elements a list with various elements
     * @return the list of TdColumn contained in the given list
     */
    public static List<TdColumn> getColumns(EList<? extends EObject> elements) {
        List<TdColumn> columns = new ArrayList<TdColumn>();
        for (EObject elt : elements) {
            if (elt != null) {
                TdColumn col = SwitchHelpers.COLUMN_SWITCH.doSwitch(elt);
                if (col != null) {
                    columns.add(col);
                }
            }
        }
        return columns;
    }

}
