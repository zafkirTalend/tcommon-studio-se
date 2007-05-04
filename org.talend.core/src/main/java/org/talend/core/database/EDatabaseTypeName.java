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
package org.talend.core.database;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public enum EDatabaseTypeName {
    MYSQL("MySQL", "MySQL", new Boolean(false), "mysql_id"),
    PSQL("PostgreSQL", "PostgreSQL", new Boolean(true), "postgres_id"),
    ORACLEFORSID("Oracle", "Oracle with SID", new Boolean(true), "oracle_id"),
    ORACLESN("Oracle with service name", "Oracle with service name", new Boolean(true), "oracle_id"),
    GODBC("Generic ODBC", "Generic ODBC", new Boolean(false), "MSODBC"),
    MSODBC("Microsoft SQL (Odbc driver)", "Microsoft SQL Server (Odbc driver)", new Boolean(false), "MSODBC"),
    IBMDB2("IBM DB2", "IBM DB2", new Boolean(false), "mysql_id"),
    SYBASEASE("SybaseASE", "Sybase ASE", new Boolean(false), "mysql_id"),
    // this Sybase IQ not used.
    SYBASEIQ("Sybase IQ", "Sybase IQ", new Boolean(false), "mysql_id"),
    MSSQL("MSSQL", "Microsoft SQL Server", new Boolean(false), "MSODBC"),
    // this don't use in Branch 2.0
    INGRES("Ingres", "Ingres", new Boolean(false), "mysql_id");

    //displayName is used in Java code.
    private String displayName;

    private Boolean isNeedSchema;

    //dbType is used in compnonent XML file.
    private String dbType;

    //use TalendType mapping id.
    private String dbmsId;

    public String getDisplayName() {
        return this.displayName;
    }

    public Boolean getIsNeedSchema() {
        return this.isNeedSchema;
    }

    public String getXmlName() {
        return this.dbType;
    }

    public String getDbmsId() {
        return this.dbmsId;
    }
    
    EDatabaseTypeName(String dbType, String displayName, Boolean isNeedSchema, String dbmsid) {
        this.displayName = displayName;
        this.isNeedSchema = isNeedSchema;
        this.dbType = dbType;
        this.dbmsId = dbmsid;
    }

    public static String getDisplayNameFromDbType(String dbType) {
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getXmlName().equals(dbType)) {
                return typename.getDisplayName();
            }
        }
        return dbType;
    }

    public static Boolean isNeedSchemaFromDbType(String dbType) {
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getDisplayName().equals(dbType)) {
                return typename.getIsNeedSchema();
            }
        }
        return new Boolean(false);
    }

    public static Boolean isNeedSchema(String dbType) {
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getXmlName().equals(dbType)) {
                return typename.getIsNeedSchema();
            }
        }
        return new Boolean(false);
    }

    public static String getDBMSMappingId(String dbms) {
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getDisplayName().equals(dbms)) {
                return typename.getDbmsId();
            }
        }
        return MYSQL.dbmsId;
    }

}
