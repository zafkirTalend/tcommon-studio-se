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
package org.talend.core.database;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public enum EDatabaseTypeName {
    MYSQL("MySQL", "MySQL", new Boolean(false), "MYSQL"),
    PSQL("PostgreSQL", "PostgreSQL", new Boolean(true), "POSTGRESQL"),
    ORACLEFORSID("Oracle", "Oracle with SID", new Boolean(true), "ORACLE"),
    ORACLESN("Oracle with service name", "Oracle with service name", new Boolean(true), "ORACLE"),
    GODBC("Generic ODBC", "Generic ODBC", new Boolean(false), "MSODBC"),
    MSODBC("Microsoft SQL (Odbc driver)", "Microsoft SQL Server (Odbc driver)", new Boolean(false), "MSODBC"),
    IBMDB2("IBM DB2", "IBM DB2", new Boolean(false), "IBMDB2"),
    SYBASEASE("SybaseASE", "Sybase ASE", new Boolean(false), "SYBASE"),

    // this Sybase IQ not used.
    SYBASEIQ("Sybase IQ", "Sybase IQ", new Boolean(false), "SYBASE"),
    MSSQL("MSSQL", "Microsoft SQL Server", new Boolean(false), "MSSQL"),
    // this don't use in Branch 2.0
    HSQLDB("HSQLDB", "HSQLDB", new Boolean(false), "HSQLDB"),
    HSQLDB_SERVER("HSQLDB Server", "HSQLDB Server", new Boolean(false), "HSQLDB"),
    HSQLDB_WEBSERVER("HSQLDB WebServer", "HSQLDB WebServer", new Boolean(false), "HSQLDB"),
    HSQLDB_IN_PROGRESS("HSQLDB In-Process", "HSQLDB In-Process", new Boolean(false), "HSQLDB"),

    JAVADB("JavaDB", "JavaDB", new Boolean(false), "JAVADB"),
    INGRES("Ingres", "Ingres", new Boolean(false), "INGRES"), // "INGRES"),
    INTERBASE("Interbase", "Interbase", new Boolean(false), "Interbase"), // "INTERBASE"),
    SQLITE("SQLite", "SQLite", new Boolean(false), "SQLITE"), // "SQLITE"),
    FIREBIRD("FireBird", "FireBird", new Boolean(false), "FIREBIRD"), // "FIREBIRD"),
    INFORMIX("Informix", "Informix", new Boolean(false), "INFORMIX"), // "INFORMIX");

    ACCESS("Access", "Access", new Boolean(false), "ACCESS"), // "ACCESS");
    TERADATA("Teradata", "Teradata", new Boolean(false), "TERADATA"), // "TERADATA");
    AS400("AS400", "AS400", new Boolean(false), "AS400"),

    JAVADB_EMBEDED("JavaDB Embeded", "JavaDB Embeded", new Boolean(false), "JAVADB"),
    JAVADB_JCCJDBC("JavaDB JCCJDBC", "JavaDB JCCJDBC", new Boolean(false), "JAVADB"),
    JAVADB_DERBYCLIENT("JavaDB DerbyClient", "JavaDB DerbyClient", new Boolean(false), "JAVADB");

    // displayName is used in Java code.
    private String displayName;

    private Boolean isNeedSchema;

    // dbType is used in compnonent XML file.
    private String dbType;

    // product used for the mappings.
    private String product;

    public String getDisplayName() {
        return this.displayName;
    }

    public Boolean isNeedSchema() {
        return this.isNeedSchema;
    }

    public String getXmlName() {
        return this.dbType;
    }

    public String getProduct() {
        return this.product;
    }

    EDatabaseTypeName(String dbType, String displayName, Boolean isNeedSchema, String product) {
        this.displayName = displayName;
        this.isNeedSchema = isNeedSchema;
        this.dbType = dbType;
        this.product = product;
    }

    public static EDatabaseTypeName getTypeFromDbType(String dbType) {
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getXmlName().equals(dbType)) {
                return typename;
            }
        }
        return getTypeFromDisplayName(dbType);
    }

    public static EDatabaseTypeName getTypeFromDisplayName(String displayName) {
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getDisplayName().equals(displayName)) {
                return typename;
            }
        }
        return MYSQL;
    }

    /**
     * This is only for the component type, not for the repository.
     * 
     * @param dbType
     * @return
     */
    public static boolean supportDbType(String dbType) {
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getXmlName().equals(dbType)) {
                return true;
            }
        }
        return false;
    }
}
