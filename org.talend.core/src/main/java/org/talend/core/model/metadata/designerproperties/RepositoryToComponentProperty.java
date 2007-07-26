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
package org.talend.core.model.metadata.designerproperties;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.EMetadataEncoding;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.FileConnection;
import org.talend.core.model.metadata.builder.connection.LdifFileConnection;
import org.talend.core.model.metadata.builder.connection.PositionalFileConnection;
import org.talend.core.model.metadata.builder.connection.RegexpFileConnection;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
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
    
//    public static final String JAVADB = "JAVADB"; //$NON-NLS-1$

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
//        if (dbType.equals(EDatabaseTypeName.JAVADB_EMBEDED.getDisplayName())) {
//            return JAVADB;
//        }
//        if (dbType.equals(EDatabaseTypeName.JAVADB_JCCJDBC.getDisplayName())) {
//            return JAVADB;
//        }
//        if (dbType.equals(EDatabaseTypeName.JAVADB_DERBYCLIENT.getDisplayName())) {
//            return JAVADB;
//        }
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

    public static void getTableXmlFileValue(Connection connection, String value, IElementParameter param,
            List<Map<String, Object>> tableInfo, IMetadataTable metaTable) {
        if (connection instanceof XmlFileConnection) {
            XmlFileConnection xmlConnection = (XmlFileConnection) connection;
            EObjectContainmentWithInverseEList objectList = (EObjectContainmentWithInverseEList) xmlConnection
                    .getSchema();
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
                    EList schemaList = (EList) xmlDesc.getSchemaTargets();
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
}
