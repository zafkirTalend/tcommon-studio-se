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

import org.talend.core.CorePlugin;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.prefs.ITalendCorePrefConstants;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public class QueryUtil {

    public static final String DEFAULT_TABLE_NAME = "_MyTable_";

    // public static final String CONTEXT = "context";

    private static final String ENTER = "\n";

    private static final String SPACE = " ";

    private static final String CON = TalendTextUtils.getStringConnect();

    public static boolean isContextQuery = false;

    public static String generateNewQuery(Element node, IMetadataTable repositoryMetadata, String dbType, String schema,
            String realTableName) {
        String tableName = getTableName(node, repositoryMetadata, schema, dbType, realTableName);
        return generateNewQuery(repositoryMetadata, dbType, tableName);
    }

    public static String generateNewQuery(Element node, IMetadataTable repositoryMetadata, String dbType, String schema,
            String realTableName, boolean standardSyntax) {

        String tableName = getTableName(node, repositoryMetadata, schema, dbType, realTableName);
        return generateNewQuery(repositoryMetadata, dbType, tableName, standardSyntax);
    }

    /**
     * TODO need to check logic
     * <p>
     * DOC YeXiaowei Comment method "generateNewQuery".
     * 
     * @param repositoryMetadata
     * @param dbType
     * @param tableName
     * @param standardSyntax
     * @return
     */
    public static String generateNewQuery(final IMetadataTable repositoryMetadata, final String dbType, final String tableName,
            final boolean standardSyntax) {

        List<IMetadataColumn> metaDataColumnList = repositoryMetadata.getListColumns();
        int index = metaDataColumnList.size();
        if (index == 0) {
            return "";
        }

        isContextQuery = false;
        if (isContext(tableName)) {
            isContextQuery = true;
        }

        String columnsQuery = "";
        for (int i = 0; i < metaDataColumnList.size(); i++) {
            IMetadataColumn metaDataColumn = metaDataColumnList.get(i);
            String columnName = TalendTextUtils.addQuotesWithSpaceFieldForSQLString(metaDataColumn.getOriginalDbColumnName(),
                    dbType, true);
            if (!isContainDeclareString(columnName, TalendTextUtils.getStringDeclare())) {
                columnName = TalendTextUtils.declareString(columnName);
            }
            String columnStr = columnName;

            if (i != index - 1) {
                columnStr = checkAndConcatString(columnStr, TalendTextUtils.declareString("," + SPACE));
            }
            String declareString = TalendTextUtils.declareString("/");
            if (standardSyntax) {
                declareString = TalendTextUtils.declareString(".");
            }
            columnStr = checkAndConcatString(declareString, columnStr);
            columnStr = checkAndConcatString(tableName, columnStr);

            if ("".equals(columnsQuery.trim())) {
                columnsQuery = columnStr;
            } else {
                columnsQuery = checkAndConcatString(columnsQuery, columnStr);
            }

        }

        String query = TalendTextUtils.declareString("SELECT ");
        if (isContextQuery) { // new line
            String end = ENTER
                    + CON
                    + checkAndConcatString(TalendTextUtils.declareString(" FROM "), TalendTextUtils
                            .addQuotesWithSpaceFieldForSQLString(tableName, dbType, !isContextQuery));

            end = replaceTheSchemaString(end);

            query = checkAndConcatString(query, columnsQuery) + end;
        } else {
            String end = checkAndConcatString(TalendTextUtils.declareString(" FROM "), TalendTextUtils
                    .addQuotesWithSpaceFieldForSQLString(tableName, dbType, !isContextQuery));
            end = replaceTheSchemaString(end);

            query = checkAndConcatString(checkAndConcatString(query, columnsQuery), end);
        }

        return query;
    }

    public static String generateNewQuery(final IMetadataTable repositoryMetadata, final String dbType,
            final String tableNameWithQuoteIfNeed) {
        if (repositoryMetadata == null) {
            return "";
        }

        List<IMetadataColumn> metaDataColumnList = repositoryMetadata.getListColumns();
        int index = metaDataColumnList.size();
        if (index == 0) {
            return "";
        }

        isContextQuery = false;
        if (isContext(tableNameWithQuoteIfNeed)) {
            isContextQuery = true;
        }

        String columnsQuery = "";

        for (int i = 0; i < metaDataColumnList.size(); i++) {
            IMetadataColumn metaDataColumn = metaDataColumnList.get(i);
            String columnName = quoteStringValue(metaDataColumn.getOriginalDbColumnName(), dbType);

            String columnStr = columnName;

            if (i != index - 1) {
                columnStr = checkAndConcatString(columnStr, TalendTextUtils.declareString("," + SPACE));
            }

            columnStr = checkAndConcatString(TalendTextUtils.declareString("."), columnStr);
            columnStr = checkAndConcatString(tableNameWithQuoteIfNeed, columnStr);

            if ("".equals(columnsQuery.trim())) {
                columnsQuery = columnStr;
            } else {
                columnsQuery = checkAndConcatString(columnsQuery, columnStr);
            }

        }

        String query = TalendTextUtils.declareString("SELECT ");
        if (isContextQuery) { // new line
            String end = ENTER + CON + checkAndConcatString(TalendTextUtils.declareString(" FROM "), tableNameWithQuoteIfNeed);

            end = replaceTheSchemaString(end);

            query = checkAndConcatString(query, columnsQuery) + end;
        } else {
            String end = checkAndConcatString(TalendTextUtils.declareString(" FROM "), tableNameWithQuoteIfNeed);

            end = replaceTheSchemaString(end);

            query = checkAndConcatString(checkAndConcatString(query, columnsQuery), end);
        }
        return query;
    }

    /*
     * if there is no schema in table string and not add quote, It's no effect.
     */
    private static String replaceTheSchemaString(String schema) {
        if (schema == null) {
            return null;
        }
        if (isContextQuery) {
            boolean isCheck = CorePlugin.getDefault().getPreferenceStore().getBoolean(ITalendCorePrefConstants.SQL_ADD_QUOTE);
            if (!isCheck) {
                String addStr = TalendTextUtils.getStringDeclare();
                return schema.replaceFirst(addStr + "\\" + CON + addStr, "");
            }
        }
        return schema;
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
                    if (null != currentTableName && !("".equals(currentTableName))) {
                        flag = true;
                    }
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
                    if (null != currentTableName && !("".equals(currentTableName))) {
                        flag = true;
                    }
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
            // Quote is added to schema and table when call getSchemaName() method
            currentTableName = getSchemaName(schema, dbType, currentTableName);
        } else {
            // If no schema, you also need to add quote to table name
            if (isContext(currentTableName)) {
                currentTableName = quoteVariableRefrence(currentTableName, dbType);
            } else {
                currentTableName = quoteStringValue(currentTableName, dbType);
            }
        }

        return currentTableName;
    }

    private static String getSchemaName(String schema, String dbType, String currentTableName) {
        String prefix;
        String suffix;
        if (EDatabaseTypeName.getTypeFromDbType(dbType).isNeedSchema()) {
            if (isContext(schema)) {
                if (isContext(currentTableName)) {
                    prefix = checkAndConcatString(quoteVariableRefrence(schema, dbType), TalendTextUtils.declareString("."));
                    suffix = quoteVariableRefrence(currentTableName, dbType);
                } else {
                    prefix = quoteVariableRefrence(schema, dbType);
                    suffix = checkAndConcatString(TalendTextUtils.declareString("."), quoteStringValue(currentTableName, dbType));
                }
            } else {
                prefix = checkAndConcatString(quoteStringValue(schema, dbType), TalendTextUtils.declareString("."));
                if (isContext(currentTableName)) {
                    suffix = quoteVariableRefrence(currentTableName, dbType);
                } else {
                    suffix = quoteStringValue(currentTableName, dbType);
                }
            }
            currentTableName = checkAndConcatString(prefix, suffix);
        }
        return currentTableName;
    }

    private static String quoteVariableRefrence(String ref, String dbType) {
        return TalendTextUtils.addQuotesWithSpaceFieldForSQLStringForce(ref, dbType, false);
    }

    private static String quoteStringValue(String value, String dbType) {
        return TalendTextUtils.addQuotesWithSpaceFieldForSQLStringForce(TalendTextUtils.declareString(value), dbType, true);
    }

    private static String getDbTableName(Element node) {
        if (node != null) { // for job settings extra.(feature 2710)
            IElementParameter param = node.getElementParameterFromField(EParameterFieldType.DBTABLE);
            if (param != null && param.isShow(node.getElementParameters())) {
                return (String) param.getValue();
            }
        }
        return null;
    }

    private static String checkAndConcatString(String str1, String str2) {
        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }

        String declareString = TalendTextUtils.getStringDeclare();

        if (isContainDeclareString(str1, declareString) && isContainDeclareString(str2, declareString)) {
            str1 = TalendTextUtils.removeQuotes(str1, declareString);
            str2 = TalendTextUtils.removeQuotes(str2, declareString);

            return declareString + str1 + str2 + declareString;
        }
        return str1 + CON + str2;

    }

    private static boolean isContainDeclareString(String str, String declareString) {
        if (str == null || declareString == null) {
            return false;
        }
        return str.startsWith(declareString) && str.endsWith(declareString);
    }

    private static boolean isContext(String str) {
        if (str == null) {
            return false;
        }
        // return str.indexOf(QueryUtil.CONTEXT) != -1; // maybe, it's not exact
        return ContextParameterUtils.containContextVariables(str);
    }

}
