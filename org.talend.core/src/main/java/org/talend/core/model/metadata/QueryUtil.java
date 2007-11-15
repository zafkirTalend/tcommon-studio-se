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
package org.talend.core.model.metadata;

import java.util.List;

import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public class QueryUtil {

    public static final String DEFAULT_TABLE_NAME = "_MyTable_";

    public static String generateNewQuery(Element node, IMetadataTable repositoryMetadata, String dbType,
            String schema, String realTableName) {
        String tableName = getTableName(node, repositoryMetadata, schema, dbType, realTableName);
        return generateNewQuery(repositoryMetadata, dbType, tableName);
    }

    public static String generateNewQuery(IMetadataTable repositoryMetadata, String dbType, String tableName) {
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
            String columnName = TalendTextUtils.addQuotesWithSpaceField(metaDataColumn.getOriginalDbColumnName(),
                    dbType);
            if (i != index - 1) {
                query.append(tableNameForColumnSuffix).append(columnName).append(",").append(space);
            } else {
                query.append(tableNameForColumnSuffix).append(columnName).append(space);
            }
        }
        query.append(enter).append("FROM").append(space).append(
                TalendTextUtils.addQuotesWithSpaceField(tableName, dbType));

        return query.toString();
    }

    public static String getTableName(Element node, IMetadataTable repositoryMetadata, String schema, String dbType,
            String realTableName) {
        String currentTableName = null;
        boolean flag = false;
        String dbTableName = getDbTableName(node);
        if (dbTableName != null) {
            switch (LanguageManager.getCurrentLanguage()) {
            case JAVA:
                if (dbTableName.contains(TalendTextUtils.QUOTATION_MARK)) {
                    if (dbTableName.startsWith(TalendTextUtils.QUOTATION_MARK)
                            && dbTableName.endsWith(TalendTextUtils.QUOTATION_MARK) && dbTableName.length() > 2) {
                        currentTableName = dbTableName.substring(1, dbTableName.length() - 1);
                        flag = true;
                    } else {
                        currentTableName = null;
                    }
                } else {
                    currentTableName = dbTableName;
                }
                break;
            default:
                if (dbTableName.contains(TalendTextUtils.SINGLE_QUOTE)) {
                    if (dbTableName.startsWith(TalendTextUtils.SINGLE_QUOTE)
                            && dbTableName.endsWith(TalendTextUtils.SINGLE_QUOTE) && dbTableName.length() > 2) {
                        currentTableName = dbTableName.substring(1, dbTableName.length() - 1);
                        flag = true;
                    } else {
                        currentTableName = null;
                    }
                } else {
                    currentTableName = dbTableName;
                }
            }
        }
        if (!flag) {
            currentTableName = realTableName;
        }
        if (currentTableName == null) {
            currentTableName = DEFAULT_TABLE_NAME;
        }
        if (schema != null && schema.length() > 0) {
            currentTableName = getSchemaName(schema, dbType, currentTableName);
        }
        return currentTableName;
    }

    private static String getSchemaName(String schema, String dbType, String currentTableName) {
        if (EDatabaseTypeName.getTypeFromDbType(dbType).isNeedSchema()) {
            currentTableName = TalendTextUtils.addQuotesWithSpaceField(schema, dbType) + "."
                    + TalendTextUtils.addQuotesWithSpaceField(currentTableName, dbType);
        }
        return currentTableName;
    }

    private static String getDbTableName(Element node) {
        IElementParameter param = node.getElementParameterFromField(EParameterFieldType.DBTABLE);
        if (param != null && param.isShow(node.getElementParameters())) {
            return (String) param.getValue();
        }
        return null;
    }
}
