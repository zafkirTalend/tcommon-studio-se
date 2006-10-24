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

import org.eclipse.core.runtime.Path;
import org.talend.core.model.metadata.EMetadataEncoding;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.FileConnection;
import org.talend.core.model.metadata.builder.connection.PositionalFileConnection;
import org.talend.core.model.metadata.builder.connection.RegexpFileConnection;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;

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
//        if (dbType.equals("Oracle Thin")) {
//            return ORACLE;
//        }
//        if (dbType.equals("Oracle Oci")) {
//            return ORACLE;
//        }
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

    private static String checkStringQuotes(String str) {
        return str.replace("'", "\\'");
    }
    
    private static String checkStringQuotationMarks(String str) {
        return str.replace("\"", "\\\"");
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
            return "'" + checkStringQuotes(connection.getServerName()) + "'";
        }
        if (value.equals("PORT")) {
            return "'" + checkStringQuotes(connection.getPort()) + "'";
        }
        if (value.equals("SID")) {
            if(("").equals(connection.getSID())) {
                return "'" + checkStringQuotes(connection.getDatasourceName()) + "'";
            }else{
                return "'" + checkStringQuotes(connection.getSID()) + "'";
            }
        }
        if (value.equals("USERNAME")) {
            return "'" + checkStringQuotes(connection.getUsername()) + "'";
        }
        if (value.equals("PASSWORD")) {
            return "'" + checkStringQuotes(connection.getPassword()) + "'";
        }
        if (value.equals("NULL_CHAR")) {
            return "'" + checkStringQuotes(connection.getNullChar()) + "'";
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
            return "'" + checkStringQuotes(p.toOSString()) + "'";
        }
        if (value.equals("ROW_SEPARATOR")) {
            return "\"" + checkStringQuotationMarks(connection.getRowSeparatorValue()) + "\"";
        }
        if (value.equals("FIELD_SEPARATOR")) {
            return "\"" + checkStringQuotationMarks(connection.getFieldSeparatorValue()) + "\"";
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
                return "'" + checkStringQuotes(EMetadataEncoding.getMetadataEncoding("").getName()) + "'";
            } else {
                return "'" + checkStringQuotes(connection.getEncoding()) + "'";
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
        if (connection instanceof XmlFileConnection) {
            return getXmlFileValue((XmlFileConnection) connection, value);
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
            return "'" + checkStringQuotes(connection.getFieldSeparatorValue()) + "'";
        }
        return null;
    }

    private static Object getDelimitedFileValue(DelimitedFileConnection connection, String value) {
        if (value.equals("ESCAPE_CHAR")) {
            if (connection.getEscapeChar() == null) {
                return "''";
            } else {
                return "'" + checkStringQuotes(connection.getEscapeChar()) + "'";
            }
        }
        if (value.equals("TEXT_ENCLOSURE")) {
            if (connection.getTextEnclosure() == null) {
                return "''";
            } else {
                return "'" + checkStringQuotes(connection.getTextEnclosure()) + "'";
            }
        }
        return null;
    }
    
    private static Object getRegexpFileValue(RegexpFileConnection connection, String value) {
        if (value.equals("ESCAPE_CHAR")) {
            if (connection.getEscapeChar() == null) {
                return "''";
            } else {
                return "'" + checkStringQuotes(connection.getEscapeChar()) + "'";
            }
        }
        if (value.equals("TEXT_ENCLOSURE")) {
            if (connection.getTextEnclosure() == null) {
                return "''";
            } else {
                return "'" + checkStringQuotes(connection.getTextEnclosure()) + "'";
            }
        }
        if (value.equals("REGEXP")) {
            if (connection.getFieldSeparatorValue() == null) {
                return "''";
            } else {
                return "'" + checkStringQuotes(connection.getFieldSeparatorValue()) + "'";
            }
        }
        return null;
    }
    // PTODO CAN XmlFile
    private static Object getXmlFileValue(XmlFileConnection connection, String value) {
//        if (value.equals("PATTERN")) {
//            return "'" + checkStringQuotes(connection.getFieldSeparatorValue()) + "'";
//        }
        return null;
    }
}
