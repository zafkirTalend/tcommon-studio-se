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
package org.talend.core.model.metadata.designerproperties;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.database.conn.template.EDatabaseConnTemplate;
import org.talend.core.database.conn.version.EDatabaseVersion4Drivers;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.EMetadataEncoding;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.EbcdicConnection;
import org.talend.core.model.metadata.builder.connection.FileConnection;
import org.talend.core.model.metadata.builder.connection.FileExcelConnection;
import org.talend.core.model.metadata.builder.connection.LDAPSchemaConnection;
import org.talend.core.model.metadata.builder.connection.LdifFileConnection;
import org.talend.core.model.metadata.builder.connection.PositionalFileConnection;
import org.talend.core.model.metadata.builder.connection.RegexpFileConnection;
import org.talend.core.model.metadata.builder.connection.SAPConnection;
import org.talend.core.model.metadata.builder.connection.SAPFunctionParameterColumn;
import org.talend.core.model.metadata.builder.connection.SAPFunctionParameterTable;
import org.talend.core.model.metadata.builder.connection.SAPFunctionUnit;
import org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.metadata.builder.connection.WSDLSchemaConnection;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;
import org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor;
import org.talend.core.model.metadata.designerproperties.PropertyConstants.CDCTypeMode;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class RepositoryToComponentProperty {

    public static Object getValue(Connection connection, String value) {
        if (connection instanceof FileConnection) {
            return getFileValue((FileConnection) connection, value);
        }
        if (connection instanceof XmlFileConnection) {
            return getXmlFileValue((XmlFileConnection) connection, value);
        }
        if (connection instanceof DatabaseConnection) {
            return getDatabaseValue((DatabaseConnection) connection, value);
        }
        if (connection instanceof LDAPSchemaConnection) {
            return getLDAPValue((LDAPSchemaConnection) connection, value);
        }
        if (connection instanceof WSDLSchemaConnection) {
            return getWSDLValue((WSDLSchemaConnection) connection, value);
        }
        if (connection instanceof LdifFileConnection) {
            return getLdifFileValue((LdifFileConnection) connection, value);
        }
        if (connection instanceof FileExcelConnection) {
            return getExcelFileValue((FileExcelConnection) connection, value);
        }

        if (connection instanceof SAPConnection) {
            return getSAPValue((SAPConnection) connection, value);
        }

        if (connection instanceof SalesforceSchemaConnection) {
            return getSalesforceSchemaValue((SalesforceSchemaConnection) connection, value);
        }

        return null;
    }

    /**
     * 
     * DOC YeXiaowei Comment method "getSAPInputAndOutputValue".
     * 
     * @param conn
     * @param value2
     * @param functionName
     * @param isInput
     * @return
     */
    public static void getSAPInputAndOutputValue(SAPConnection conn, List<Map<String, Object>> value2, String functionName,
            boolean isInput) {
        if (conn == null) {
            return;
        }
        SAPFunctionUnit unit = null;
        for (int i = 0; i < conn.getFuntions().size(); i++) {
            unit = (SAPFunctionUnit) conn.getFuntions().get(i);
            if (unit.getName().equals(functionName)) {
                break;
            }
        }
        if (unit == null) {
            return;
        }

        SAPFunctionParameterTable table = isInput ? unit.getInputParameterTable() : unit.getOutputParameterTable();
        if (table == null || table.getColumns() == null || table.getColumns().isEmpty()) {
            return;
        }
        value2.clear();// Make sure for this
        for (int i = 0; i < table.getColumns().size(); i++) {
            SAPFunctionParameterColumn column = (SAPFunctionParameterColumn) table.getColumns().get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            if (isInput) {
                String talendType = getTalendTypeFromJCOType(column.getDataType());
                if (talendType.contains("String")) { //$NON-NLS-1$
                    map.put("SAP_PARAMETER_VALUE", TalendTextUtils.addQuotes(column.getValue())); //$NON-NLS-1$
                } else {
                    map.put("SAP_PARAMETER_VALUE", column.getValue()); //$NON-NLS-1$
                }

            }
            map.put("SAP_PARAMETER_TYPE", column.getParameterType().replace('.', '_')); //$NON-NLS-1$
            map.put("SAP_TABLE_NAME", TalendTextUtils.addQuotes(column.getStructureOrTableName())); //$NON-NLS-1$
            map.put("SAP_PARAMETER_NAME", TalendTextUtils.addQuotes(column.getName())); //$NON-NLS-1$
            value2.add(map);
        }
    }

    /**
     * 
     * DOC xye Comment method "getSAPValuesForFunction".
     * 
     * @param conn
     * @param functionName
     * @param paramterName
     * @return
     */
    public static String getSAPValuesForFunction(SAPConnection conn, String functionName, String paramterName) {
        SAPFunctionUnit unit = null;
        if (conn == null) {
            return null;
        }
        for (int i = 0; i < conn.getFuntions().size(); i++) {
            unit = (SAPFunctionUnit) conn.getFuntions().get(i);
            if (unit.getName().equals(functionName)) {
                break;
            }
        }
        if (unit == null) {
            return null;
        }
        if (paramterName.equals("SAP_ITERATE_OUT_TYPE")) { //$NON-NLS-1$
            if (unit.getOutputType() != null) {
                return unit.getOutputType().replace(".", "_"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        } else if (paramterName.equals("SAP_ITERATE_OUT_TABLENAME")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(unit.getOutputTableName());
        }
        return null;
    }

    /**
     * 
     * DOC YeXiaowei Comment method "getTalendTypeFromJCOType".
     * 
     * @param jcoType
     * @return
     */
    private static String getTalendTypeFromJCOType(final String jcoType) {
        if (jcoType == null) {
            return ""; //$NON-NLS-1$
        }
        return MetadataTalendType.getMappingTypeRetriever("sap_id").getAdvicedDbToTalendTypes(jcoType).get(0).getTalendType(); //$NON-NLS-1$
    }

    /**
     * 
     * DOC YeXiaowei Comment method "getSAPValue".
     * 
     * @param connection
     * @param value
     * @return
     */
    public static Object getSAPValue(SAPConnection connection, String value) {

        if ("CLIENT".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getClient())) {
                return connection.getClient();
            } else {
                return TalendTextUtils.addQuotes(connection.getClient());
            }
        } else if ("USERID".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getUsername())) {
                return connection.getUsername();
            } else {
                return TalendTextUtils.addQuotes(connection.getUsername());
            }
        } else if ("PASSWORD".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getPassword())) {
                return connection.getPassword();
            } else {
                return TalendTextUtils.addQuotes(connection.getPassword());
            }
        } else if ("LANGUAGE".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getLanguage())) {
                return connection.getLanguage();
            } else {
                return TalendTextUtils.addQuotes(connection.getLanguage());
            }
        } else if ("HOSTNAME".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getHost())) {
                return connection.getHost();
            } else {
                return TalendTextUtils.addQuotes(connection.getHost());
            }
        } else if ("SYSTEMNUMBER".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getSystemNumber())) {
                return connection.getSystemNumber();
            } else {
                return TalendTextUtils.addQuotes(connection.getSystemNumber());
            }
        }

        return null;
    }

    /**
     * DOC YeXiaowei Comment method "getSalesforceSchemaValue".
     * 
     * @param connection
     * @param value
     * @return
     */
    private static Object getSalesforceSchemaValue(SalesforceSchemaConnection connection, String value) {
        if ("ENDPOINT".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getWebServiceUrl())) {
                return connection.getWebServiceUrl();
            } else {
                return TalendTextUtils.addQuotes(connection.getWebServiceUrl());
            }
        } else if ("USER_NAME".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getUserName())) {
                return connection.getUserName();
            } else {
                return TalendTextUtils.addQuotes(connection.getUserName());
            }
        } else if ("PASSWORD".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getPassword())) {
                return connection.getPassword();
            } else {
                return TalendTextUtils.addQuotes(connection.getPassword());
            }
        } else if ("CUSTOM_MODULE_NAME".equals(value)) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getModuleName());
        } else if ("MODULENAME".equals(value)) { //$NON-NLS-1$
            if (connection.isUseCustomModuleName()) {
                return "CustomModule"; //$NON-NLS-1$
            } else {
                return connection.getModuleName();
            }
        } else if ("QUERY_CONDITION".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getQueryCondition())) {
                return connection.getQueryCondition();
            } else {
                return TalendTextUtils.addQuotes(connection.getQueryCondition());
            }
            // add for feature 7507
        } else if ("BATCH_SIZE".equals(value)) { //$NON-NLS-1$
            return connection.getBatchSize();
        } else if ("UES_PROXY".equals(value)) { //$NON-NLS-1$
            return connection.isUseProxy();
        } else if ("PROXY_HOST".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getProxyHost())) {
                return connection.getProxyHost();
            } else {
                return TalendTextUtils.addQuotes(connection.getProxyHost());
            }
        } else if ("PROXY_PORT".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getProxyPort())) {
                return connection.getProxyPort();
            } else {
                return TalendTextUtils.addQuotes(connection.getProxyPort());
            }

        } else if ("PROXY_USERNAME".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getProxyUsername())) {
                return connection.getProxyUsername();
            } else {
                return TalendTextUtils.addQuotes(connection.getProxyUsername());
            }

        } else if ("PROXY_PASSWORD".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getProxyPassword())) {
                return connection.getProxyPassword();
            } else {
                return TalendTextUtils.addQuotes(connection.getProxyPassword());
            }
        }
        return null;
    }

    /**
     * DOC qzhang Comment method "getWSDLValue".
     * 
     * @param connection
     * @param value
     * @return
     */
    private static Object getWSDLValue(WSDLSchemaConnection connection, String value) {
        if ("ENDPOINT".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getWSDL())) {
                return connection.getWSDL();
            } else {
                return TalendTextUtils.addQuotes(connection.getWSDL());
            }
        } else if ("NEED_AUTH".equals(value)) { //$NON-NLS-1$
            return new Boolean(connection.isNeedAuth());
        } else if ("AUTH_USERNAME".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getUserName())) {
                return connection.getUserName();
            } else {
                return TalendTextUtils.addQuotes(connection.getUserName());
            }
        } else if ("AUTH_PASSWORD".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getPassword())) {
                return connection.getPassword();
            } else {
                return TalendTextUtils.addQuotes(connection.getPassword());
            }
        } else if ("UES_PROXY".equals(value)) { //$NON-NLS-1$
            return new Boolean(connection.isUseProxy());
        } else if ("PROXY_HOST".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getProxyHost())) {
                return connection.getProxyHost();
            } else {
                return TalendTextUtils.addQuotes(connection.getProxyHost());
            }
        } else if ("PROXY_PORT".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getProxyPort())) {
                return connection.getProxyPort();
            } else {
                return TalendTextUtils.addQuotes(connection.getProxyPort());
            }
        } else if ("PROXY_USERNAME".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getProxyUser())) {
                return connection.getProxyUser();
            } else {
                return TalendTextUtils.addQuotes(connection.getProxyUser());
            }
        } else if ("PROXY_PASSWORD".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getProxyPassword())) {
                return connection.getProxyPassword();
            } else {
                return TalendTextUtils.addQuotes(connection.getProxyPassword());
            }
        } else if ("METHOD".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getMethodName())) {
                return connection.getMethodName();
            } else {
                return TalendTextUtils.addQuotes(connection.getMethodName());
            }
        } else if ("WSDLURL".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getEndpointURI())) {
                return connection.getEndpointURI();
            } else {
                return TalendTextUtils.addQuotes(connection.getEndpointURI());
            }
        } else if (value.equals("ENCODING")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getEncoding())) {
                return connection.getEncoding();
            } else {
                if (connection.getEncoding() == null) {
                    // get the default encoding
                    return TalendTextUtils.addQuotes(EMetadataEncoding.getMetadataEncoding("").getName()); //$NON-NLS-1$
                } else {
                    return TalendTextUtils.addQuotes(connection.getEncoding());
                }
            }
        }
        // else if ("PARAMS".equals(value)) {
        // return connection.getParameters();
        // }
        return null;
    }

    private static String getStandardDbTypeFromConnection(String dbType) {

        // if (dbType.equals(EDatabaseTypeName.GODBC.getDisplayName())) {
        // return ODBC; MSODBC
        // }
        // if (dbType.equals(EDatabaseTypeName.MSODBC.getDisplayName())) {
        // return ODBC; MSODBC
        // }
        // if (dbType.equals(EDatabaseTypeName.MSSQL.getDisplayName())) {
        // return SQL_SERVER; MSSQL
        // }

        // if (dbType.equals(EDatabaseTypeName.INTERBASE.getDisplayName())) {
        // return INTERBASE; Interbase
        // }

        return EDatabaseTypeName.getTypeFromDbType(dbType).getProduct();

    }

    /**
     * DOC nrousseau Comment method "getDatabaseValue".
     * 
     * @param connection
     * @param value
     * @return
     */
    private static Object getDatabaseValue(DatabaseConnection connection, String value) {

        if (value.equals("TYPE")) { //$NON-NLS-1$
            String typeByProduct = getStandardDbTypeFromConnection(connection.getDatabaseType());
            // See bug 4565
            if (connection.getDatabaseType().equals(EDatabaseTypeName.ORACLEFORSID.getDisplayName())) {
                // see StatsAndLogConstants
                // This connection is Oracle_SID
                return "ORACLE_SID"; //$NON-NLS-1$
            } else if (connection.getDatabaseType().equals(EDatabaseTypeName.ORACLESN.getDisplayName())) {
                // This connection is Oracle_service_name
                return "ORACLE_SERVICE_NAME"; //$NON-NLS-1$
            } else {
                return typeByProduct;
            }
        }

        if (value.equals("SERVER_NAME")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getServerName())) {
                return connection.getServerName();
            } else {
                return TalendTextUtils.addQuotes(connection.getServerName());
            }
        }
        if (value.equals("PORT")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getPort())) {
                return connection.getPort();
            } else {
                return TalendTextUtils.addQuotes(connection.getPort());
            }
        }
        if (value.equals("FRAMEWORK_TYPE")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getDatabaseType())) {
                if (connection.getDatabaseType().equals("JavaDB Embeded")) { //$NON-NLS-1$
                    return "EMBEDED"; //$NON-NLS-1$
                }
                if (connection.getDatabaseType().equals("JavaDB JCCJDBC")) { //$NON-NLS-1$
                    return "JCCJDBC"; //$NON-NLS-1$
                }
                if (connection.getDatabaseType().equals("JavaDB DerbyClient")) { //$NON-NLS-1$
                    return "DERBYCLIENT"; //$NON-NLS-1$
                }
            } else {
                if (connection.getDatabaseType().equals("JavaDB Embeded")) { //$NON-NLS-1$
                    return "EMBEDED"; //$NON-NLS-1$
                }
                if (connection.getDatabaseType().equals("JavaDB JCCJDBC")) { //$NON-NLS-1$
                    return "JCCJDBC"; //$NON-NLS-1$
                }
                if (connection.getDatabaseType().equals("JavaDB DerbyClient")) { //$NON-NLS-1$
                    return "DERBYCLIENT"; //$NON-NLS-1$
                }
            }
        }
        if (value.equals("SID")) { //$NON-NLS-1$
            if (("").equals(connection.getSID()) || connection.getSID() == null) { //$NON-NLS-1$
                if (isConetxtMode(connection, connection.getDatasourceName())) {
                    return connection.getDatasourceName();
                } else {
                    return TalendTextUtils.addQuotes(connection.getDatasourceName());
                }
            } else {
                if (isConetxtMode(connection, connection.getSID())) {
                    return connection.getSID();
                } else {
                    return TalendTextUtils.addQuotes(connection.getSID());
                }
            }
        }
        if (value.equals("DATASOURCE")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getDatasourceName())) {
                return connection.getDatasourceName();
            } else {
                return TalendTextUtils.addQuotes(connection.getDatasourceName());
            }
        }
        if (value.equals("USERNAME")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getUsername())) {
                return connection.getUsername();
            } else {
                return TalendTextUtils.addQuotes(connection.getUsername());
            }
        }
        if (value.equals("PASSWORD")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getPassword())) {
                return connection.getPassword();
            } else {
                return TalendTextUtils.addQuotes(connection.getPassword());
            }
        }
        if (value.equals("NULL_CHAR")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getNullChar())) {
                return connection.getNullChar();
            } else {
                return TalendTextUtils.addQuotes(connection.getNullChar());
            }
        }
        if (value.equals("SCHEMA")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getSchema())) {
                return connection.getSchema();
            } else {
                return TalendTextUtils.addQuotes(connection.getSchema());
            }
        }
        if (value.equals("FILE")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getFileFieldName())) {
                return connection.getFileFieldName();
            } else {
                return TalendTextUtils.addQuotes(connection.getFileFieldName());
            }
        }
        if (value.equals("PROPERTIES_STRING")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getAdditionalParams())) {
                return connection.getAdditionalParams();
            } else {
                return TalendTextUtils.addQuotes(connection.getAdditionalParams());
            }
        }

        if (value.equals("DB_VERSION")) { //$NON-NLS-1$
            String dbVersionString = connection.getDbVersionString();
            if (EDatabaseConnTemplate.ACCESS.getDBDisplayName().equals(connection.getDatabaseType())) {
                // see bug 7262
                return dbVersionString;
            } else {
                String driverValue = EDatabaseVersion4Drivers.getDriversStr(connection.getDatabaseType(), dbVersionString);
                if (isConetxtMode(connection, dbVersionString)) {
                    return dbVersionString;
                } else {
                    return driverValue;
                }
            }
        }

        // add new class name property
        if (value.equals("DRIVER_CLASS")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getDriverClass())) {
                return connection.getDriverClass();
            } else {
                return TalendTextUtils.addQuotes(connection.getDriverClass());
            }
        }

        if (value.equals("URL")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getURL())) {
                return connection.getURL();
            } else {
                return TalendTextUtils.addQuotes(connection.getURL());
            }
        }

        if (value.equals("DRIVER_JAR")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getDriverJarPath())) {
                return connection.getDriverJarPath();
            } else {
                String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
                String pathSeparator = System.getProperty("path.separator"); //$NON-NLS-1$
                String defaultPath = userDir + pathSeparator + "lib" + pathSeparator + "java"; //$NON-NLS-1$ //$NON-NLS-2$
                String jarPath = connection.getDriverJarPath();

                if (jarPath == null) {
                    return null;
                }

                try {
                    String fileName = new File(jarPath).getName();

                    if (!jarPath.equals(defaultPath + pathSeparator + fileName)) {
                        // deploy this library
                        try {
                            CorePlugin.getDefault().getLibrariesService().deployLibrary(
                                    Path.fromOSString(jarPath).toFile().toURL());
                        } catch (IOException e) {
                            ExceptionHandler.process(e);
                            return null;
                        }
                    }
                    CorePlugin.getDefault().getLibrariesService().resetModulesNeeded();
                    return TalendTextUtils.addQuotes(new File(jarPath).getName());
                } catch (Exception e) {
                    return null;
                }
            }

        }
        if (value.equals("CDC_TYPE_MODE")) { //$NON-NLS-1$
            return new Boolean(CDCTypeMode.LOG_MODE.getName().equals(connection.getCdcTypeMode()));
        }
        return null;
    }

    private static boolean isConetxtMode(Connection connection, String value) {
        if (connection == null || value == null) {
            return false;
        }
        if (connection.isContextMode() && ContextParameterUtils.isContainContextParam(value)) {
            return true;
        }
        return false;
    }

    /**
     * DOC nrousseau Comment method "getFileValue".
     * 
     * @param connection
     * @param value
     * @return
     */
    private static Object getFileValue(FileConnection connection, String value) {
        if (value.equals("FILE_PATH")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getFilePath())) {
                return connection.getFilePath();
            } else {
                if (connection.getFilePath() != null) {
                    Path p = new Path(connection.getFilePath());
                    return TalendTextUtils.addQuotes(p.toPortableString());
                } else {
                    return ""; //$NON-NLS-1$
                }
            }
        }
        if (value.equals("ROW_SEPARATOR")) { //$NON-NLS-1$
            return connection.getRowSeparatorValue();
        }
        if (value.equals("FIELD_SEPARATOR")) { //$NON-NLS-1$
            return connection.getFieldSeparatorValue();
        }
        if (value.equals("HEADER")) { //$NON-NLS-1$
            if (connection.isUseHeader()) {
                return connection.getHeaderValue();
            } else {
                return "0"; //$NON-NLS-1$
            }
        }
        if (value.equals("FOOTER")) { //$NON-NLS-1$
            if (connection.isUseFooter()) {
                return connection.getFooterValue();
            } else {
                return "0"; //$NON-NLS-1$
            }
        }
        if (value.equals("LIMIT")) { //$NON-NLS-1$
            if (connection.isUseLimit()) {
                return connection.getLimitValue();
            } else {
                return ""; //$NON-NLS-1$
            }
        }
        if (value.equals("ENCODING")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getEncoding())) {
                return connection.getEncoding();
            } else {
                if (connection.getEncoding() == null) {
                    // get the default encoding
                    return TalendTextUtils.addQuotes(EMetadataEncoding.getMetadataEncoding("").getName()); //$NON-NLS-1$
                } else {
                    return TalendTextUtils.addQuotes(connection.getEncoding());
                }
            }
        }
        if (value.equals("REMOVE_EMPTY_ROW")) { //$NON-NLS-1$
            return new Boolean(connection.isRemoveEmptyRow());
        }
        if (value.equals("CSV_OPTION")) { //$NON-NLS-1$
            return new Boolean(connection.isCsvOption());
        }
        if (connection instanceof DelimitedFileConnection) {
            return getDelimitedFileValue((DelimitedFileConnection) connection, value);
        }
        if (connection instanceof PositionalFileConnection) {
            return getPositionalFileValue((PositionalFileConnection) connection, value);
        }
        if (connection instanceof RegexpFileConnection) {
            return getRegexpFileValue((RegexpFileConnection) connection, value);
        }
        if (connection instanceof LdifFileConnection) {
            return getLdifFileValue((LdifFileConnection) connection, value);
        }
        if (connection instanceof FileExcelConnection) {
            return getExcelFileValue((FileExcelConnection) connection, value);
        }
        if (connection instanceof EbcdicConnection) {
            return getEBCDICFieldValue((EbcdicConnection) connection, value);
        }
        return null;
    }

    /**
     * 
     * ggu Comment method "getEBCDICFieldValue".
     * 
     */
    private static Object getEBCDICFieldValue(EbcdicConnection connection, String value) {
        if ("XC2J_FILE".equals(value)) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getFilePath())) {
                return connection.getMidFile();
            } else {
                Path p = new Path(connection.getMidFile());
                return TalendTextUtils.addQuotes(p.toPortableString());
            }
        }
        if ("DATA_FILE".equals(value)) {
            if (isConetxtMode(connection, connection.getDataFile())) {
                return connection.getDataFile();
            } else {
                Path p = new Path(connection.getDataFile());
                return TalendTextUtils.addQuotes(p.toPortableString());
            }
        }
        return null;
    }

    /**
     * DOC yexiaowei Comment method "getExcelFileValue".
     * 
     * @param connection
     * @param value
     * @return
     */
    private static Object getExcelFileValue(FileExcelConnection connection, String value) {
        if (value.equals("FILE_PATH")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getFilePath())) {
                return connection.getFilePath();
            } else {
                Path p = new Path(connection.getFilePath());
                return TalendTextUtils.addQuotes(p.toPortableString());
            }
        }
        if (value.equals("SHEET_NAME")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getSheetName());
        }

        if (value.equals("SELECT_ALL_SHEETS")) { //$NON-NLS-1$
            return connection.isSelectAllSheets();
        }

        if (value.equals("FIRST_COLUMN")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getFirstColumn())) {
                return connection.getFirstColumn();
            } else {
                if (isPerlProject()) {
                    return TalendTextUtils.addQuotes(connection.getFirstColumn());
                } else {
                    return connection.getFirstColumn();
                }
            }
        }
        if (value.equals("LAST_COLUMN")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getLastColumn())) {
                return connection.getLastColumn();
            } else {
                if (isPerlProject()) {
                    if (connection.getLastColumn() != null && !connection.getLastColumn().equals("")) { //$NON-NLS-1$
                        return TalendTextUtils.addQuotes(connection.getLastColumn());
                    }
                } else {
                    return connection.getLastColumn();
                }
            }

        }
        if (value.equals("ADVANCED_SEPARATOR")) { //$NON-NLS-1$
            return connection.isAdvancedSpearator();
        }
        if (value.equals("THOUSANDS_SEPARATOR")) { //$NON-NLS-1$
            return connection.getThousandSeparator();
        }
        if (value.equals("DECIMAL_SEPARATOR")) { //$NON-NLS-1$
            return connection.getDecimalSeparator();
        }

        if (value.equals("SHEET_LIST")) { //$NON-NLS-1$
            return getExcelSheetTableValue(connection);
        }

        return null;
    }

    private static boolean isPerlProject() {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        return (codeLanguage == ECodeLanguage.PERL);
    }

    /**
     * DOC YeXiaowei Comment method "getExcelSheetTableValue".
     * 
     * @param connection
     */
    private static List<Map<String, Object>> getExcelSheetTableValue(FileExcelConnection connection) {
        ArrayList<String> list = connection.getSheetList();
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (String s : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("SHEETNAME", TalendTextUtils.addQuotes(s)); //$NON-NLS-1$
            maps.add(map);
        }
        return maps;

    }

    /**
     * DOC nrousseau Comment method "getPositionalFileValue".
     * 
     * @param connection
     * @param value
     * @return
     */
    private static Object getPositionalFileValue(PositionalFileConnection connection, String value) {
        if (value.equals("PATTERN")) { //$NON-NLS-1$
            return connection.getFieldSeparatorValue();
        }
        return null;
    }

    private static Object getDelimitedFileValue(DelimitedFileConnection connection, String value) {
        if (value.equals("ESCAPE_CHAR")) { //$NON-NLS-1$
            return connection.getEscapeChar();
        }
        if (value.equals("TEXT_ENCLOSURE")) { //$NON-NLS-1$
            return connection.getTextEnclosure();
        }

        if (value.equals("SPLITRECORD")) { //$NON-NLS-1$           
            return connection.isSplitRecord();
        }
        return null;
    }

    private static Object getRegexpFileValue(RegexpFileConnection connection, String value) {
        if (value.equals("ESCAPE_CHAR")) { //$NON-NLS-1$
            return connection.getEscapeChar();
        }
        if (value.equals("TEXT_ENCLOSURE")) { //$NON-NLS-1$
            return connection.getTextEnclosure();
        }
        if (value.equals("REGEXP")) { //$NON-NLS-1$
            return connection.getFieldSeparatorValue();
        }
        return null;
    }

    // added by nma to deal with .xsd file
    public static Object getXmlAndXSDFileValue(XmlFileConnection connection, String value) {
        EList list = connection.getSchema();
        XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) list.get(0);
        if (value.equals("FILE_PATH")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getXmlFilePath())) {
                return connection.getXmlFilePath();
            } else {
                Path p = new Path(connection.getXmlFilePath());
                if ((p.toPortableString()).endsWith("xsd")) {
                    OpenXSDFileDialog openXSDFileDialog = new OpenXSDFileDialog(PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow().getShell());
                    openXSDFileDialog.setTitle("Select a XML File to Validate");
                    openXSDFileDialog.setPath(p);
                    int dialogValue = openXSDFileDialog.open();
                    if (dialogValue == 0)
                        return TalendTextUtils.addQuotes(openXSDFileDialog.portableValue);
                }
                return TalendTextUtils.addQuotes(p.toPortableString());
            }
        }
        if (value.equals("LIMIT")) { //$NON-NLS-1$
            if ((xmlDesc == null) || (xmlDesc.getLimitBoucle() == null)) {
                return ""; //$NON-NLS-1$
            } else {
                return xmlDesc.getLimitBoucle().toString();
            }
        }
        if (value.equals("XPATH_QUERY")) { //$NON-NLS-1$
            if (xmlDesc == null) {
                return ""; //$NON-NLS-1$
            } else {
                if (isConetxtMode(connection, xmlDesc.getAbsoluteXPathQuery())) {
                    return xmlDesc.getAbsoluteXPathQuery();
                } else {
                    return TalendTextUtils.addQuotes(xmlDesc.getAbsoluteXPathQuery());
                }
            }
        }
        if (value.equals("ENCODING")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getEncoding())) {
                return connection.getEncoding();
            } else {
                if (connection.getEncoding() == null) {
                    // get the default encoding
                    return TalendTextUtils.addQuotes(EMetadataEncoding.getMetadataEncoding("").getName()); //$NON-NLS-1$
                } else {
                    return TalendTextUtils.addQuotes(connection.getEncoding());
                }
            }
        }
        return null;
    }

    private static Object getXmlFileValue(XmlFileConnection connection, String value) {
        EList list = connection.getSchema();
        XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) list.get(0);
        if (value.equals("FILE_PATH")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getXmlFilePath())) {
                return connection.getXmlFilePath();
            } else {
                Path p = new Path(connection.getXmlFilePath());
                return TalendTextUtils.addQuotes(p.toPortableString());
            }
        }
        if (value.equals("LIMIT")) { //$NON-NLS-1$
            if ((xmlDesc == null) || (xmlDesc.getLimitBoucle() == null)) {
                return ""; //$NON-NLS-1$
            } else {
                return xmlDesc.getLimitBoucle().toString();
            }
        }
        if (value.equals("XPATH_QUERY")) { //$NON-NLS-1$
            if (xmlDesc == null) {
                return ""; //$NON-NLS-1$
            } else {
                if (isConetxtMode(connection, xmlDesc.getAbsoluteXPathQuery())) {
                    return xmlDesc.getAbsoluteXPathQuery();
                } else {
                    return TalendTextUtils.addQuotes(xmlDesc.getAbsoluteXPathQuery());
                }
            }
        }
        if (value.equals("ENCODING")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getEncoding())) {
                return connection.getEncoding();
            } else {
                if (connection.getEncoding() == null) {
                    // get the default encoding
                    return TalendTextUtils.addQuotes(EMetadataEncoding.getMetadataEncoding("").getName()); //$NON-NLS-1$
                } else {
                    return TalendTextUtils.addQuotes(connection.getEncoding());
                }
            }
        }
        return null;
    }

    /**
     * qiang.zhang Comment method "getTableXMLMappingValue".
     * 
     * @param connection
     * @param tableInfo
     * @param metaTable
     */
    public static void getTableXMLMappingValue(Connection connection, List<Map<String, Object>> tableInfo,
            IMetadataTable metaTable) {
        if (connection instanceof XmlFileConnection) {
            XmlFileConnection xmlConnection = (XmlFileConnection) connection;
            EList objectList = xmlConnection.getSchema();
            XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) objectList.get(0);
            List<SchemaTarget> schemaTargets = xmlDesc.getSchemaTargets();
            tableInfo.clear();
            List<IMetadataColumn> listColumns = metaTable.getListColumns();
            for (IMetadataColumn metadataColumn : listColumns) {
                for (SchemaTarget schema : schemaTargets) {
                    if (metadataColumn.getLabel().equals(schema.getTagName())) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("SCHEMA_COLUMN", schema.getTagName()); //$NON-NLS-1$
                        map.put("QUERY", TalendTextUtils.addQuotes(schema.getRelativeXPathQuery())); //$NON-NLS-1$
                        tableInfo.add(map);
                    }
                }
            }
        }
    }

    public static void getTableXmlFileValue(Connection connection, String value, IElementParameter param,
            List<Map<String, Object>> tableInfo, IMetadataTable metaTable) {
        if (connection instanceof XmlFileConnection) {
            XmlFileConnection xmlConnection = (XmlFileConnection) connection;
            EList objectList = xmlConnection.getSchema();
            XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) objectList.get(0);
            if (value.equals("XML_MAPPING")) { //$NON-NLS-1$
                if (xmlDesc == null) {
                    return;
                } else {
                    String[] list = param.getListRepositoryItems();

                    int column = 0;
                    boolean found = false;
                    for (int k = 0; (k < list.length) && (!found); k++) {
                        if (list[k].equals("XML_QUERY")) { //$NON-NLS-1$
                            column = k;
                            found = true;
                        }
                    }
                    EList schemaList = xmlDesc.getSchemaTargets();
                    String[] names = param.getListItemsDisplayCodeName();
                    for (int k = 0; k < schemaList.size(); k++) {
                        if (tableInfo.size() > k) {
                            Map<String, Object> line = tableInfo.get(k);
                            if (metaTable != null) {
                                if (metaTable.getListColumns().size() > k) {
                                    SchemaTarget schemaTarget = (SchemaTarget) schemaList.get(k);
                                    String strValue = TalendTextUtils.addQuotes(schemaTarget.getRelativeXPathQuery());
                                    line.put(names[column], strValue);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static Object getLdifFileValue(LdifFileConnection connection, String value) {
        if (value.equals("FILE_PATH")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getFilePath())) {
                return connection.getFilePath();
            } else {
                Path p = new Path(connection.getFilePath());
                return TalendTextUtils.addQuotes(p.toPortableString());
            }
        }
        return null;
    }

    /**
     * Gets repository value for LDAP schema.
     * 
     * @param connection
     * @param value
     * @return
     */
    private static Object getLDAPValue(LDAPSchemaConnection connection, String value) {

        if (value.equals("HOST")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getHost())) {
                return connection.getHost();
            } else {
                return TalendTextUtils.addQuotes(connection.getHost());
            }
        }

        if (value.equals("PORT")) { //$NON-NLS-1$
            return connection.getPort();
        }

        if (value.equals("BASEDN")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getSelectedDN())) {
                return connection.getSelectedDN();
            } else {
                return TalendTextUtils.addQuotes(connection.getSelectedDN());
            }
        }
        String protocol = connection.getProtocol();// Simple or Anonymous
        if (value.equals("PROTOCOL")) { //$NON-NLS-1$
            String encryptionMethodName = connection.getEncryptionMethodName();
            if (encryptionMethodName.equals("LDAPS(SSL)")) { //$NON-NLS-1$
                return "LDAPS"; //$NON-NLS-1$
            }
        }

        boolean useAuthen = connection.isUseAuthen();
        if (value.equals("AUTHENTIFICATION")) { //$NON-NLS-1$
            return new Boolean(useAuthen);
        }

        if (useAuthen && value.equals("USER")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getBindPrincipal())) {
                return connection.getBindPrincipal();
            } else {
                return TalendTextUtils.addQuotes(connection.getBindPrincipal());
            }
        }
        if (useAuthen && value.equals("PASSWD")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getBindPassword())) {
                return connection.getBindPassword();
            } else {
                return TalendTextUtils.addQuotes(connection.getBindPassword());
            }
        }
        if (value.equals("FILTER")) { //$NON-NLS-1$
            if (isConetxtMode(connection, connection.getFilter())) {
                return connection.getFilter();
            } else {
                return TalendTextUtils.addQuotes(connection.getFilter());
            }
        }

        if (value.equals("MULTI_VALUE_SEPARATOR")) { //$NON-NLS-1$
            String separator = connection.getSeparator();
            return separator == null ? TalendTextUtils.addQuotes(",") : TalendTextUtils.addQuotes(separator); //$NON-NLS-1$
        }

        if (value.equals("COLUMN_COUNT_LIMIT")) { //$NON-NLS-1$
            return connection.getCountLimit();
        }

        if (value.equals("TIME_OUT_LIMIT")) { //$NON-NLS-1$
            return connection.getTimeOutLimit();
        }

        if (value.equals("ALIASES")) { //$NON-NLS-1$
            return connection.getAliases();
        }

        if (value.equals("REFERRALS")) { //$NON-NLS-1$
            return connection.getReferrals();
        }
        return null;
    }

    /**
     * DOC qiang.zhang Comment method "getXMLMappingValue".
     * 
     * @param repositoryConnection
     * @param metadataTable
     * @return
     */
    public static List<Map<String, Object>> getXMLMappingValue(Connection connection, IMetadataTable metadataTable) {
        if (connection instanceof XmlFileConnection) {
            XmlFileConnection xmlConnection = (XmlFileConnection) connection;
            EList objectList = xmlConnection.getSchema();
            XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) objectList.get(0);
            if (metadataTable != null) {
                if (xmlDesc != null) {
                    List<SchemaTarget> schemaTargets = xmlDesc.getSchemaTargets();
                    List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
                    for (IMetadataColumn col : metadataTable.getListColumns()) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("QUERY", null); //$NON-NLS-1$
                        for (int i = 0; i < schemaTargets.size(); i++) {
                            SchemaTarget sch = schemaTargets.get(i);
                            if (col.getLabel().equals(sch.getTagName())) {
                                // map.put("SCHEMA_COLUMN", sch.getTagName());
                                map.put("QUERY", TalendTextUtils.addQuotes(sch.getRelativeXPathQuery())); //$NON-NLS-1$
                            }
                        }
                        maps.add(map);
                    }
                    return maps;
                }
            }
        }
        return null;
    }
}
