// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.builder.connection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

/**
 * DOC tguiu class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class TableHelper extends SubItemHelper {

    public static String[] getTableNames(Connection connection) {
        List<String> result = doGetTableNames(connection);
        return result.toArray(new String[result.size()]);
    }

    public static String[] getTableNames(SAPFunctionUnit functionUnit) {
        List<String> result = doGetTableNames(functionUnit);
        return result.toArray(new String[result.size()]);
    }

    public static String[] getTableNames(Connection connection, String discardedValued) {
        List<String> result = doGetTableNames(connection);
        result.remove(discardedValued);
        return result.toArray(new String[result.size()]);
    }

    public static String[] getTableNames(SAPFunctionUnit functionUnit, String discardedValued) {
        List<String> result = doGetTableNames(functionUnit);
        result.remove(discardedValued);
        return result.toArray(new String[result.size()]);
    }

    /**
     * DOC tguiu Comment method "doGetTableNames".
     * 
     * @param connection
     * @return
     */
    private static List<String> doGetTableNames(Connection connection) {
        List<String> result = new ArrayList<String>(15);
        for (Object table : connection.getTables()) {
            if (table == null) {
                continue;
            }
            result.add(((MetadataTable) table).getLabel());
        }
        return result;
    }

    private static List<String> doGetTableNames(SAPFunctionUnit functionUnit) {
        List<String> result = new ArrayList<String>(15);
        for (Object table : functionUnit.getTables()) {
            result.add(((MetadataTable) table).getLabel());
        }
        return result;
    }

    /**
     * 
     * DOC tguiu Comment method "findByLabel".
     * 
     * @deprecated il vaudrait mieux utiliser find avec un identifiant
     * @param connection
     * @param label
     * @return
     */
    public static MetadataTable findByLabel(Connection connection, String label) {
        if (connection == null)
            throw new IllegalArgumentException("null connection"); //$NON-NLS-1$
        if (label == null || "".equals(label)) //$NON-NLS-1$
            throw new IllegalArgumentException("null/empty label"); //$NON-NLS-1$
        EList tables = connection.getTables();
        for (int i = 0; i < tables.size(); i++) {
            MetadataTable table = (MetadataTable) tables.get(i);
            if (label.equals(table.getLabel()))
                return table;
        }
        return null;
    }

    public static MetadataTable findByLabel(SAPFunctionUnit functionUnit, String label) {
        if (functionUnit == null)
            throw new IllegalArgumentException("null connection"); //$NON-NLS-1$
        if (label == null || "".equals(label)) //$NON-NLS-1$
            throw new IllegalArgumentException("null/empty label"); //$NON-NLS-1$
        EList tables = functionUnit.getTables();
        for (int i = 0; i < tables.size(); i++) {
            MetadataTable table = (MetadataTable) tables.get(i);
            if (label.equals(table.getLabel()))
                return table;
        }
        return null;
    }

    private TableHelper() {
    }

}
