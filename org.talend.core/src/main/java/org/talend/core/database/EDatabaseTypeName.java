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
    MYSQL("MySQL", "MySQL", new Boolean(false), "MYSQL"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    PSQL("PostgreSQL", "PostgreSQL", new Boolean(true), "POSTGRESQL", "POSTGRE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    PLUSPSQL("PostgresPlus", "PostgresPlus", new Boolean(true), "POSTGRESPLUS", "POSTGREPLUS"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    ORACLEFORSID("Oracle", "Oracle with SID", new Boolean(true), "ORACLE", "DBORACLE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    ORACLESN("Oracle with service name", "Oracle with service name", new Boolean(true), "ORACLE", "DBORACLE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    GODBC("Generic ODBC", "Generic ODBC", new Boolean(false), "ODBC"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    MSODBC("Microsoft SQL (Odbc driver)", "Microsoft SQL Server (Odbc driver)", new Boolean(false), "ODBC"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    IBMDB2("IBM DB2", "IBM DB2", new Boolean(true), "IBM_DB2", "DB2"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    SYBASEASE("SybaseASE", "Sybase ASE", new Boolean(false), "SYBASE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    // this Sybase IQ not used.
    SYBASEIQ("Sybase IQ", "Sybase IQ", new Boolean(false), "SYBASE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    MSSQL("MSSQL", "Microsoft SQL Server", new Boolean(true), "SQL_SERVER", "MSSQL"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    // this don't use in Branch 2.0
    HSQLDB("HSQLDB", "HSQLDB", new Boolean(false), "HSQLDB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    HSQLDB_SERVER("HSQLDB Server", "HSQLDB Server", new Boolean(false), "HSQLDB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    HSQLDB_WEBSERVER("HSQLDB WebServer", "HSQLDB WebServer", new Boolean(false), "HSQLDB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    HSQLDB_IN_PROGRESS("HSQLDB In-Process", "HSQLDB In-Process", new Boolean(false), "HSQLDB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    JAVADB("JavaDB", "JavaDB", new Boolean(false), "JAVADB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    INGRES("Ingres", "Ingres", new Boolean(false), "INGRES"), // "INGRES"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    INTERBASE("Interbase", "Interbase", new Boolean(false), "INTERBASE"), // "INTERBASE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    SQLITE("SQLite", "SQLite", new Boolean(false), "SQLITE"), // "SQLITE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    FIREBIRD("FireBird", "FireBird", new Boolean(false), "FIREBIRD"), // "FIREBIRD"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    INFORMIX("Informix", "Informix", new Boolean(false), "INFORMIX"), // "INFORMIX"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    ACCESS("Access", "Access", new Boolean(false), "ACCESS"), // "ACCESS"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    TERADATA("Teradata", "Teradata", new Boolean(true), "TERADATA"), // "TERADATA"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    AS400("AS400", "AS400", new Boolean(false), "AS400"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    JAVADB_EMBEDED("JavaDB Embeded", "JavaDB Embeded", new Boolean(false), "JAVADB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    JAVADB_JCCJDBC("JavaDB JCCJDBC", "JavaDB JCCJDBC", new Boolean(false), "JAVADB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    JAVADB_DERBYCLIENT("JavaDB DerbyClient", "JavaDB DerbyClient", new Boolean(false), "JAVADB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    VERTICA("Vertica", "Vertica", new Boolean(false), "VERTICA"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    MAXDB("MAXDB", "MaxDB", new Boolean(false), "MAXDB"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    GREENPLUM("Greenplum", "Greenplum", new Boolean(true), "GREENPLUM", "GREENPLUM"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    PARACCEL("ParAccel", "ParAccel", new Boolean(true), "PARACCEL", "PARACCEL"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    NETEZZA("Netezza", "Netezza", new Boolean(false), "NETEZZA"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    // General JDBC not support schema defalut
    GENERAL_JDBC("General JDBC", "General JDBC", new Boolean(false), "JDBC"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    // displayName is used in Java code.
    private String displayName;

    private Boolean isNeedSchema;

    // dbType is used in compnonent XML file.
    private String dbType;

    // product used for the mappings.
    private String product;

    // needs a mapping for bug 0004305
    private String xmlType;

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

    public String getXMLType() {
        return this.xmlType;
    }

    EDatabaseTypeName(String dbType, String displayName, Boolean isNeedSchema, String product) {
        this.displayName = displayName;
        this.isNeedSchema = isNeedSchema;
        this.dbType = dbType;
        this.product = product;
        this.xmlType = product;
    }

    EDatabaseTypeName(String dbType, String displayName, Boolean isNeedSchema, String product, String xmlType) {
        this.displayName = displayName;
        this.isNeedSchema = isNeedSchema;
        this.dbType = dbType;
        this.product = product;
        this.xmlType = xmlType;
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
