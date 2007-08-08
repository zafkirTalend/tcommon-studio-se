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
package org.talend.core.model.metadata;

import java.util.List;

import org.talend.core.model.process.Element;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public class QueryUtil {

    public static String generateNewQuery(Element node, IMetadataTable repositoryMetadata, String dbType, String schema,
            String realTableName) {
        String tableName = MetadataTool.getTableName(node, repositoryMetadata, schema, dbType, realTableName);

        List<IMetadataColumn> metaDataColumnList = repositoryMetadata.getListColumns();
        int index = metaDataColumnList.size();
        if (index == 0) {
            return "";
        }

        StringBuffer query = new StringBuffer();
        String enter = "\n";
        String space = " ";
        query.append("SELECT").append(space);

        String tableNameForColumnSuffix = TalendTextUtils.addQuotesWithSpaceField(tableName, dbType) + ".";

        for (int i = 0; i < metaDataColumnList.size(); i++) {
            IMetadataColumn metaDataColumn = metaDataColumnList.get(i);
            String columnName = TalendTextUtils.addQuotesWithSpaceField(getColumnName(metaDataColumn.getOriginalDbColumnName(),
                    dbType), dbType);
            if (i != index - 1) {
                query.append(tableNameForColumnSuffix).append(columnName).append(",").append(space);
            } else {
                query.append(tableNameForColumnSuffix).append(columnName).append(space);
            }
        }
        query.append(enter).append("FROM").append(space).append(TalendTextUtils.addQuotesWithSpaceField(tableName, dbType));

        return query.toString();
    }

    private static String getColumnName(String name, String dbType) {
        String nameAfterChanged;
        if (name.startsWith("\"") && name.endsWith("\"")) {
            nameAfterChanged = name;
        } else {
            if (!dbType.equalsIgnoreCase("PostgreSQL")) {
                nameAfterChanged = name;
            } else {
                nameAfterChanged = "\"" + name + "\"";
            }
        }
        return nameAfterChanged;
    }

}
