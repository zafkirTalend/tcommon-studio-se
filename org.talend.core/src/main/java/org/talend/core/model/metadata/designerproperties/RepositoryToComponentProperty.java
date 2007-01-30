// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
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
package org.talend.core.model.metadata.designerproperties;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
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

    public static final String MYSQL = "MYSQL";

    public static final String POSTGRESQL = "POSTGRESQL";

    public static final String ODBC_JDBC = "ODBC_JDBC";

    public static final String ODBC = "ODBC";

    public static final String ORACLE = "ORACLE";

    public static final String IBM_DB2 = "IBM_DB2";

    public static final String SYBASE = "SYBASE";

    public static final String SQL_SERVER = "SQL_SERVER";

    public static final String MS_ACCESS = "MS_ACCESS";

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
        if (dbType.equals("MySQL")) {
            return MYSQL;
        }
        if (dbType.equals("PostgreSQL")) {
            return POSTGRESQL;
        }
        if (dbType.equals("Oracle with SID")) {
            return ORACLE;
        }
        if (dbType.equals("Oracle with service name")) {
            return ORACLE;
        }
        if (dbType.equals("Generic ODBC")) {
            return ODBC;
        }
        if (dbType.equals("Microsoft SQL Server (Odbc driver)")) {
            return ODBC_JDBC;
        }
        // if (dbType.equals("Oracle Thin")) {
        // return ORACLE;
        // }
        // if (dbType.equals("Oracle Oci")) {
        // return ORACLE;
        // }
        if (dbType.equals("IBM DB2")) {
            return IBM_DB2;
        }
        if (dbType.equals("Sybase")) {
            return SYBASE;
        }
        if (dbType.equals("Microsoft SQL Server")) {
            return SQL_SERVER;
        }
        if (dbType.equals("Microsoft Access")) {
            return MS_ACCESS;
        }
        return "";
    }

    /**
     * DOC nrousseau Comment method "getDatabaseValue".
     * 
     * @param connection
     * @param value
     * @return
     */
    private static Object getDatabaseValue(DatabaseConnection connection, String value) {
        if (value.equals("TYPE")) {
            return getStandardDbTypeFromConnection(connection.getDatabaseType());
        }
        if (value.equals("SERVER_NAME")) {
            return TalendTextUtils.addQuotes(connection.getServerName());
        }
        if (value.equals("PORT")) {
            return TalendTextUtils.addQuotes(connection.getPort());
        }
        if (value.equals("SID")) {
            if (("").equals(connection.getSID())) {
                return  TalendTextUtils.addQuotes(connection.getDatasourceName());
            } else {
                return  TalendTextUtils.addQuotes(connection.getSID());
            }
        }
        if (value.equals("USERNAME")) {
            return  TalendTextUtils.addQuotes(connection.getUsername());
        }
        if (value.equals("PASSWORD")) {
            return  TalendTextUtils.addQuotes(connection.getPassword());
        }
        if (value.equals("NULL_CHAR")) {
            return  TalendTextUtils.addQuotes(connection.getNullChar());
        }
        if (value.equals("SCHEMA")) {
            return  TalendTextUtils.addQuotes(connection.getSchema());
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
        if (value.equals("FILE_PATH")) {
            Path p = new Path(connection.getFilePath());
            return  TalendTextUtils.addQuotes(p.toOSString());
        }
        if (value.equals("ROW_SEPARATOR")) {
            return  TalendTextUtils.addQuotes(connection.getRowSeparatorValue(), TalendTextUtils.QUOTATION_MARK);
        }
        if (value.equals("FIELD_SEPARATOR")) {
            return TalendTextUtils.addQuotes(connection.getFieldSeparatorValue(), TalendTextUtils.QUOTATION_MARK);
        }
        if (value.equals("HEADER")) {
            if (connection.isUseHeader()) {
                return String.valueOf(connection.getHeaderValue());
            } else {
                return "0";
            }
        }
        if (value.equals("FOOTER")) {
            if (connection.isUseFooter()) {
                return String.valueOf(connection.getFooterValue());
            } else {
                return "0";
            }
        }
        if (value.equals("LIMIT")) {
            if (connection.isUseLimit()) {
                return String.valueOf(connection.getLimitValue());
            } else {
                return "";
            }
        }
        if (value.equals("ENCODING")) {
            if (connection.getEncoding() == null) {
                // get the default encoding
                return TalendTextUtils.addQuotes(EMetadataEncoding.getMetadataEncoding("").getName());
            } else {
                return TalendTextUtils.addQuotes(connection.getEncoding());
            }
        }
        if (value.equals("REMOVE_EMPTY_ROW")) {
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
        if (value.equals("PATTERN")) {
            return TalendTextUtils.addQuotes(connection.getFieldSeparatorValue());
        }
        return null;
    }

    private static Object getDelimitedFileValue(DelimitedFileConnection connection, String value) {
        if (value.equals("ESCAPE_CHAR")) {
            return TalendTextUtils.addQuotes(connection.getEscapeChar());
        }
        if (value.equals("TEXT_ENCLOSURE")) {
            return TalendTextUtils.addQuotes(connection.getTextEnclosure());
        }
        return null;
    }

    private static Object getRegexpFileValue(RegexpFileConnection connection, String value) {
        if (value.equals("ESCAPE_CHAR")) {
            return TalendTextUtils.addQuotes(connection.getEscapeChar());
        }
        if (value.equals("TEXT_ENCLOSURE")) {
            return TalendTextUtils.addQuotes(connection.getTextEnclosure());
        }
        if (value.equals("REGEXP")) {
            return TalendTextUtils.addQuotes(connection.getFieldSeparatorValue());
        }
        return null;
    }

    private static Object getXmlFileValue(XmlFileConnection connection, String value) {
        EObjectContainmentWithInverseEList list = (EObjectContainmentWithInverseEList) connection.getSchema();
        XmlXPathLoopDescriptor xmlDesc = (XmlXPathLoopDescriptor) list.get(0);
        if (value.equals("FILE_PATH")) {
            Path p = new Path(connection.getXmlFilePath());
            return TalendTextUtils.addQuotes(p.toOSString());
        }
        if (value.equals("LIMIT")) {
            if ((xmlDesc == null) || (xmlDesc.getLimitBoucle() == null)) {
                return "";
            } else {
                return TalendTextUtils.addQuotes(xmlDesc.getLimitBoucle().toString());
            }
        }
        if (value.equals("XPATH_QUERY")) {
            if (xmlDesc == null) {
                return "";
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
            if (value.equals("XML_MAPPING")) {
                if (xmlDesc == null) {
                    return;
                } else {
                    String[] list = param.getListRepositoryItems();

                    int column = 0;
                    boolean found = false;
                    for (int k = 0; (k < list.length) && (!found); k++) {
                        if (list[k].equals("XML_QUERY")) {
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
                                    String strValue = TalendTextUtils.addQuotes(schemaTarget
                                                    .getRelativeXPathQuery());
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
