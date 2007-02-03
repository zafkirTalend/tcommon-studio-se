// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend – www.talend.com
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
package org.talend.core.model.metadata.builder.connection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;


/**
 * DOC tguiu  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class TableHelper {
    public static final String DELETED = "deleted"; //$NON-NLS-1$
    
    public static boolean isDeleted(MetadataTable table)
    {
        return table.getProperties().containsKey(DELETED);
    }
    
    public static void setDeleted(MetadataTable table, boolean deleted)
    {
        if (deleted)
            table.getProperties().put(DELETED, Boolean.TRUE.toString());
        else
            table.getProperties().remove(DELETED);
    }
    
    public static String[] getTableNames(Connection connection)
    {
        List<String> result = doGetTableNames(connection);
        return result.toArray(new String[result.size()]);
    }
    
    public static String[] getTableNames(Connection connection, String discardedValued)
    {
        List<String> result = doGetTableNames(connection);
        result.remove(discardedValued);
        return result.toArray(new String[result.size()]);
    }

    /**
     * DOC tguiu Comment method "doGetTableNames".
     * @param connection
     * @return
     */
    private static List<String> doGetTableNames(Connection connection) {
        List<String> result = new ArrayList<String>(15);
        for (Object table:connection.getTables())
        {
            result.add(((MetadataTable)table).getLabel());
        }
        return result;
    }
    
    /**
     * 
     * DOC tguiu Comment method "findByLabel".
     * @deprecated il vaudrait mieux utiliser find avec un identifiant
     * @param connection
     * @param label
     * @return
     */
    public static MetadataTable findByLabel (Connection connection, String label)
    {
        if (connection == null)
            throw new IllegalArgumentException("null connection"); //$NON-NLS-1$
        if (label == null || "".equals(label)) //$NON-NLS-1$
            throw new IllegalArgumentException("null/empty label"); //$NON-NLS-1$
        EList tables = connection.getTables();
        for (int i = 0; i<tables.size(); i++)
        {
            MetadataTable table = (MetadataTable)tables.get(i);
            if (label.equals(table.getLabel()))
                return table;
        }
        return null;
    }
    
    private TableHelper() {}

}
