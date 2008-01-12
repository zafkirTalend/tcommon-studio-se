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
package org.talend.core.model.metadata.designerproperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.EMetadataEncoding;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.FileConnection;
import org.talend.core.model.metadata.builder.connection.LDAPSchemaConnection;
import org.talend.core.model.metadata.builder.connection.LdifFileConnection;
import org.talend.core.model.metadata.builder.connection.PositionalFileConnection;
import org.talend.core.model.metadata.builder.connection.RegexpFileConnection;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.metadata.builder.connection.WSDLSchemaConnection;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;
import org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class RepositoryToComponentProperty {

    public static final String MYSQL = "MYSQL"; //$NON-NLS-1$

    public static final String POSTGRESQL = "POSTGRESQL"; //$NON-NLS-1$

    public static final String ODBC = "ODBC"; //$NON-NLS-1$

    public static final String ORACLE = "ORACLE"; //$NON-NLS-1$

    public static final String IBM_DB2 = "IBM_DB2"; //$NON-NLS-1$

    public static final String SYBASE = "SYBASE"; //$NON-NLS-1$

    public static final String SQL_SERVER = "SQL_SERVER"; //$NON-NLS-1$

    public static final String MS_ACCESS = "MS_ACCESS"; //$NON-NLS-1$

    public static final String INGRES = "INGRES"; //$NON-NLS-1$

    public static final String INTERBASE = "INTERBASE"; //$NON-NLS-1$

    public static final String SQLITE = "SQLITE"; //$NON-NLS-1$

    public static final String FIREBIRD = "FIREBIRD"; //$NON-NLS-1$

    public static final String INFORMIX = "INFORMIX"; //$NON-NLS-1$

    public static final String ACCESS = "ACCESS"; //$NON-NLS-1$

    public static final String TERADATA = "TERADATA"; //$NON-NLS-1$

    public static final String AS400 = "AS400"; //$NON-NLS-1$

    public static final String HSQLDB = "HSQLDB"; //$NON-NLS-1$

    public static final String JAVADB = "JAVADB"; //$NON-NLS-1$

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
        if ("ENDPOINT".equals(value)) {

            return TalendTextUtils.addQuotes(connection.getWSDL());
        } else if ("NEED_AUTH".equals(value)) {
            return new Boolean(connection.isNeedAuth());
        } else if ("AUTH_USERNAME".equals(value)) {
            return TalendTextUtils.addQuotes(connection.getUserName());
        } else if ("AUTH_PASSWORD".equals(value)) {
            return TalendTextUtils.addQuotes(connection.getPassword());
        } else if ("UES_PROXY".equals(value)) {
            return new Boolean(connection.isUseProxy());
        } else if ("PROXY_HOST".equals(value)) {
            return TalendTextUtils.addQuotes(connection.getProxyHost());
        } else if ("PROXY_PORT".equals(value)) {
            return TalendTextUtils.addQuotes(connection.getProxyPort());
        } else if ("PROXY_USERNAME".equals(value)) {
            return TalendTextUtils.addQuotes(connection.getProxyUser());
        } else if ("PROXY_PASSWORD".equals(value)) {
            return TalendTextUtils.addQuotes(connection.getProxyPassword());
        } else if ("METHOD".equals(value)) {
            return TalendTextUtils.addQuotes(connection.getMethodName());
        }
        // else if ("PARAMS".equals(value)) {
        // return connection.getParameters();
        // }
        return null;
    }

    private static String getStandardDbTypeFromConnection(String dbType) {
        if (dbType.equals(EDatabaseTypeName.MYSQL.getDisplayName())) {
            return MYSQL;
        }
        if (dbType.equals(EDatabaseTypeName.PSQL.getDisplayName())) {
            return POSTGRESQL;
        }
        if (dbType.equals(EDatabaseTypeName.ORACLEFORSID.getDisplayName())) {
            return ORACLE;
        }
        if (dbType.equals(EDatabaseTypeName.ORACLESN.getDisplayName())) {
            return ORACLE;
        }
        if (dbType.equals(EDatabaseTypeName.GODBC.getDisplayName())) {
            return ODBC;
        }
        if (dbType.equals(EDatabaseTypeName.MSODBC.getDisplayName())) {
            return ODBC;
        }
        if (dbType.equals(EDatabaseTypeName.MSSQL.getDisplayName())) {
            return SQL_SERVER;
        }
        if (dbType.equals(EDatabaseTypeName.IBMDB2.getDisplayName())) {
            return IBM_DB2;
        }
        if (dbType.equals(EDatabaseTypeName.SYBASEASE.getDisplayName())) {
            return SYBASE;
        }
        if (dbType.equals(EDatabaseTypeName.SYBASEIQ.getDisplayName())) {
            return SYBASE;
        }
        if (dbType.equals(EDatabaseTypeName.INGRES.getDisplayName())) {
            return INGRES;
        }
        if (dbType.equals(EDatabaseTypeName.INTERBASE.getDisplayName())) {
            return INTERBASE;
        }
        if (dbType.equals(EDatabaseTypeName.SQLITE.getDisplayName())) {
            return SQLITE;
        }
        if (dbType.equals(EDatabaseTypeName.FIREBIRD.getDisplayName())) {
            return FIREBIRD;
        }
        if (dbType.equals(EDatabaseTypeName.INFORMIX.getDisplayName())) {
            return INFORMIX;
        }
        if (dbType.equals(EDatabaseTypeName.ACCESS.getDisplayName())) {
            return ACCESS;
        }
        if (dbType.equals(EDatabaseTypeName.TERADATA.getDisplayName())) {
            return TERADATA;
        }
        if (dbType.equals(EDatabaseTypeName.AS400.getDisplayName())) {
            return AS400;
        }
        if (dbType.equals(EDatabaseTypeName.HSQLDB.getDisplayName())) {
            return HSQLDB;
        }

        if (dbType.equals(EDatabaseTypeName.HSQLDB_SERVER.getDisplayName())) {
            return HSQLDB;
        }
        if (dbType.equals(EDatabaseTypeName.HSQLDB_IN_PROGRESS.getDisplayName())) {
            return HSQLDB;
        }
        if (dbType.equals(EDatabaseTypeName.HSQLDB_WEBSERVER.getDisplayName())) {
            return HSQLDB;
        }
        if (dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())) {
            return JAVADB;
        }
        if (dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName())) {
            return JAVADB;
        }
        if (dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())) {
            return JAVADB;
        }
        return ""; //$NON-NLS-1$
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
            return getStandardDbTypeFromConnection(connection.getDatabaseType());
        }
        if (value.equals("SERVER_NAME")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getServerName());
        }
        if (value.equals("PORT")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getPort());
        }
        if (value.equals("SID")) { //$NON-NLS-1$
            if (("").equals(connection.getSID())) { //$NON-NLS-1$
                return TalendTextUtils.addQuotes(connection.getDatasourceName());
            } else {
                return TalendTextUtils.addQuotes(connection.getSID());
            }
        }
        if (value.equals("DATASOURCE")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getDatasourceName());
        }
        if (value.equals("USERNAME")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getUsername());
        }
        if (value.equals("PASSWORD")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getPassword());
        }
        if (value.equals("NULL_CHAR")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getNullChar());
        }
        if (value.equals("SCHEMA")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getSchema());
        }
        if (value.equals("FILE")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getFileFieldName());
        }
        if (value.equals("PROPERTIES_STRING")) { //$NON-NLS-1$
            return TalendTextUtils.addQuotes(connection.getAdditionalParams());
        }
        return null;
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
            Path p = new Path(connection.getFilePath());
            return TalendTextUtils.addQuotes(p.toPortableString());
        }
        if (value.equals("ROW_SEPARATOR")) { //$NON-NLS-1$
            return connection.getRowSeparatorValue();
        }
        if (value.equals("FIELD_SEPARATOR")) { //$NON-NLS-1$
            return connection.getFieldSeparatorValue();
        }
        if (value.equals("HEADER")) { //$NON-NLS-1$
            if (connection.isUseHeader()) {
                return String.valueOf(connection.getHeaderValue());
            } else {
                return "0"; //$NON-NLS-1$
            }
        }
        if (value.equals("FOOTER")) { //$NON-NLS-1$
            if (connection.isUseFooter()) {
                return String.valueOf(connection.getFooterValue());
            } else {
                return "0"; //$NON-NLS-1$
            }
        }
        if (value.equals("LIMIT")) { //$NON-NLS-1$
            if (connection.isUseLimit()) {
                return String.valueOf(connection.getLimitValue());
            } else {
                return ""; //$NON-NLS-1$
            }
        }
        if (value.equals("ENCODING")) { //$NON-NLS-1$
            if (connection.getEncoding() == null) {
                // get the default encoding
                return TalendTextUtils.addQuotes(EMetadataEncoding.getMetadataEncoding("").getName()); //$NON-NLS-1$
            } else {
                return TalendTextUtils.addQuotes(connection.getEncoding());
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
        return null;
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

    private static Object getXmlFileValue(XmlFileConnection connection, String value) {
        EObjectContainmentWithInverseEList list = (EObjectContainmentWithInverseEList) connection.getSchema();
        XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) list.get(0);
        if (value.equals("FILE_PATH")) { //$NON-NLS-1$
            Path p = new Path(connection.getXmlFilePath());
            return TalendTextUtils.addQuotes(p.toPortableString());
        }
        if (value.equals("LIMIT")) { //$NON-NLS-1$
            if ((xmlDesc == null) || (xmlDesc.getLimitBoucle() == null)) {
                return ""; //$NON-NLS-1$
            } else {
                return TalendTextUtils.addQuotes(xmlDesc.getLimitBoucle().toString());
            }
        }
        if (value.equals("XPATH_QUERY")) { //$NON-NLS-1$
            if (xmlDesc == null) {
                return ""; //$NON-NLS-1$
            } else {
                return TalendTextUtils.addQuotes(xmlDesc.getAbsoluteXPathQuery());
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
            EObjectContainmentWithInverseEList objectList = (EObjectContainmentWithInverseEList) xmlConnection.getSchema();
            XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) objectList.get(0);
            List<SchemaTarget> schemaTargets = xmlDesc.getSchemaTargets();
            tableInfo.clear();
            List<IMetadataColumn> listColumns = metaTable.getListColumns();
            for (IMetadataColumn metadataColumn : listColumns) {
                for (SchemaTarget schema : schemaTargets) {
                    if (metadataColumn.getLabel().equals(schema.getTagName())) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("SCHEMA_COLUMN", schema.getTagName());
                        map.put("QUERY", TalendTextUtils.addQuotes(schema.getRelativeXPathQuery()));
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
            EObjectContainmentWithInverseEList objectList = (EObjectContainmentWithInverseEList) xmlConnection.getSchema();
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
            return TalendTextUtils.addQuotes(connection.getHost());
        }

        if (value.equals("PORT")) { //$NON-NLS-1$
            return connection.getPort();
        }

        if (value.equals("BASEDN")) {
            return TalendTextUtils.addQuotes(connection.getSelectedDN());
        }
        String protocol = connection.getProtocol();// Simple or Anonymous
        if (value.equals("PROTOCOL")) {
            String encryptionMethodName = connection.getEncryptionMethodName();
            if (encryptionMethodName.equals("LDAPS(SSL)")) {
                return "LDAPS";
            }
        }

        boolean useAuthen = connection.isUseAuthen();
        if (value.equals("AUTHENTIFICATION")) {
            return new Boolean(useAuthen);
        }

        if (useAuthen && value.equals("USER")) {
            return TalendTextUtils.addQuotes(connection.getBindPrincipal());
        }
        if (useAuthen && value.equals("PASSWD")) {
            return TalendTextUtils.addQuotes(connection.getBindPassword());
        }
        if (value.equals("FILTER")) {
            return TalendTextUtils.addQuotes(connection.getFilter());
        }

        if (value.equals("MULTI_VALUE_SEPARATOR")) {
            String separator = connection.getSeparator();
            return separator == null ? TalendTextUtils.addQuotes(",") : TalendTextUtils.addQuotes(separator);
        }

        if (value.equals("COLUMN_COUNT_LIMIT")) {
            return connection.getCountLimit();
        }

        if (value.equals("TIME_OUT_LIMIT")) {
            return connection.getTimeOutLimit();
        }

        if (value.equals("ALIASES")) {
            return connection.getAliases();
        }

        if (value.equals("REFERRALS")) {
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
            EObjectContainmentWithInverseEList objectList = (EObjectContainmentWithInverseEList) xmlConnection.getSchema();
            XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) objectList.get(0);
            if (metadataTable != null) {
                if (xmlDesc != null) {
                    List<SchemaTarget> schemaTargets = xmlDesc.getSchemaTargets();
                    List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
                    for (IMetadataColumn col : metadataTable.getListColumns()) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("QUERY", null);
                        for (int i = 0; i < schemaTargets.size(); i++) {
                            SchemaTarget sch = schemaTargets.get(i);
                            if (col.getLabel().equals(sch.getTagName())) {
                                // map.put("SCHEMA_COLUMN", sch.getTagName());
                                map.put("QUERY", TalendTextUtils.addQuotes(sch.getRelativeXPathQuery()));
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
