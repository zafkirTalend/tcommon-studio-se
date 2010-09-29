// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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

import org.talend.core.model.metadata.builder.database.EDatabaseSchemaOrCatalogMapping;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public enum EDatabaseTypeName {
    MYSQL(
          "MySQL", "MySQL", new Boolean(false), "MYSQL", "MYSQL", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    PSQL(
         "PostgreSQL", "PostgreSQL", new Boolean(true), "POSTGRESQL", "POSTGRE", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    PLUSPSQL(
             "PostgresPlus", "PostgresPlus", new Boolean(true), "POSTGRESPLUS", "POSTGREPLUS", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    ORACLEFORSID(
                 "ORACLE_SID", "Oracle with SID", new Boolean(true), "ORACLE", "DBORACLE", EDatabaseSchemaOrCatalogMapping.None, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    ORACLESN(
             "ORACLE_SERVICE_NAME", "Oracle with service name", new Boolean(true), "ORACLE", "DBORACLE", EDatabaseSchemaOrCatalogMapping.None, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    ORACLE_OCI(
               "ORACLE_OCI", "Oracle OCI", new Boolean(true), "ORACLE", "DBORACLE", EDatabaseSchemaOrCatalogMapping.None, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    GODBC(
          "Generic ODBC", "Generic ODBC", new Boolean(false), "ODBC", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    MSODBC(
           "Microsoft SQL (Odbc driver)", "Microsoft SQL Server (Odbc driver)", new Boolean(false), "ODBC", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    IBMDB2(
           "IBM DB2", "IBM DB2", new Boolean(true), "IBM_DB2", "DB2", EDatabaseSchemaOrCatalogMapping.None, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    IBMDB2ZOS(
              "IBM DB2 ZOS", "IBM DB2 ZOS", new Boolean(true), "IBM_DB2", "DB2", EDatabaseSchemaOrCatalogMapping.None, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    SYBASEASE(
              "SybaseASE", "Sybase (ASE and IQ)", new Boolean(true), "SYBASE", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    // this Sybase IQ not used.
    SYBASEIQ(
             "Sybase IQ", "Sybase IQ", new Boolean(true), "SYBASE", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    MSSQL(
          "MSSQL", "Microsoft SQL Server", new Boolean(true), "SQL_SERVER", "MSSQL", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    // this don't use in Branch 2.0
    HSQLDB(
           "HSQLDB", "HSQLDB", new Boolean(false), "HSQLDB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    HSQLDB_SERVER(
                  "HSQLDB Server", "HSQLDB Server", new Boolean(false), "HSQLDB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    HSQLDB_WEBSERVER(
                     "HSQLDB WebServer", "HSQLDB WebServer", new Boolean(false), "HSQLDB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    HSQLDB_IN_PROGRESS(
                       "HSQLDB In-Process", "HSQLDB In-Process", new Boolean(false), "HSQLDB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    JAVADB(
           "JavaDB", "JavaDB", new Boolean(false), "JAVADB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    INGRES(
           "Ingres", "Ingres", new Boolean(false), "INGRES", EDatabaseSchemaOrCatalogMapping.None, EDatabaseSchemaOrCatalogMapping.Schema), // "INGRES"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    INTERBASE(
              "Interbase", "Interbase", new Boolean(false), "INTERBASE", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), // "INTERBASE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    SQLITE(
           "SQLite", "SQLite", new Boolean(false), "SQLITE", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), // "SQLITE"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    FIREBIRD(
             "FireBird", "FireBird", new Boolean(false), "FIREBIRD", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), // "FIREBIRD"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    INFORMIX(
             "Informix", "Informix", new Boolean(true), "INFORMIX", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), // "INFORMIX"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    VECTORWISE(
               "VectorWise", "VectorWise", new Boolean(false), "VECTORWISE", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$  //$NON-NLS-3$

    ACCESS(
           "Access", "Access", new Boolean(false), "ACCESS", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), // "ACCESS"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    TERADATA(
             "Teradata", "Teradata", new Boolean(true), "TERADATA", EDatabaseSchemaOrCatalogMapping.None, EDatabaseSchemaOrCatalogMapping.Schema), // "TERADATA"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    AS400(
          "AS400", "AS400", new Boolean(false), "AS400", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.Login), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    JAVADB_EMBEDED(
                   "JavaDB Embeded", "JavaDB Embeded", new Boolean(false), "JAVADB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    JAVADB_JCCJDBC(
                   "JavaDB JCCJDBC", "JavaDB JCCJDBC", new Boolean(false), "JAVADB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    JAVADB_DERBYCLIENT(
                       "JavaDB DerbyClient", "JavaDB DerbyClient", new Boolean(false), "JAVADB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    VERTICA(
            "Vertica", "Vertica", new Boolean(true), "VERTICA", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    MAXDB(
          "MAXDB", "MaxDB", new Boolean(false), "MAXDB", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    GREENPLUM(
              "Greenplum", "Greenplum", new Boolean(true), "GREENPLUM", "GREENPLUM", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    PARACCEL(
             "ParAccel", "ParAccel", new Boolean(true), "PARACCEL", "PARACCEL", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.Schema), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    NETEZZA(
            "Netezza", "Netezza", new Boolean(false), "NETEZZA", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    SAS("SAS", "SAS", new Boolean(false), "SAS", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    // General JDBC not support schema defalut
    GENERAL_JDBC(
                 "General JDBC", "General JDBC", new Boolean(false), "JDBC", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    EXASOL(
           "Exasol", "Exasol", new Boolean(true), "Exasol", EDatabaseSchemaOrCatalogMapping.Sid, EDatabaseSchemaOrCatalogMapping.None);//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    // displayName is used in Java code.
    private String displayName;

    private Boolean isNeedSchema;

    // dbType is used in compnonent XML file.
    private String dbType;

    // product used for the mappings.
    private String product;

    // needs a mapping for bug 0004305
    private String xmlType;

    private EDatabaseSchemaOrCatalogMapping catalogMappingField;

    private EDatabaseSchemaOrCatalogMapping schemaMappingField;

    public EDatabaseSchemaOrCatalogMapping getCatalogMappingField() {
        return this.catalogMappingField;
    }

    public EDatabaseSchemaOrCatalogMapping getSchemaMappingField() {
        return this.schemaMappingField;
    }

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

    EDatabaseTypeName(String dbType, String displayName, Boolean isNeedSchema, String product,
            EDatabaseSchemaOrCatalogMapping catalogMappingField, EDatabaseSchemaOrCatalogMapping schemaMappingField) {
        this.displayName = displayName;
        this.isNeedSchema = isNeedSchema;
        this.dbType = dbType;
        this.product = product;
        this.xmlType = product;
        this.catalogMappingField = catalogMappingField;
        this.schemaMappingField = schemaMappingField;
    }

    EDatabaseTypeName(String dbType, String displayName, Boolean isNeedSchema, String product, String xmlType,
            EDatabaseSchemaOrCatalogMapping catalogMappingField, EDatabaseSchemaOrCatalogMapping schemaMappingField) {
        this.displayName = displayName;
        this.isNeedSchema = isNeedSchema;
        this.dbType = dbType;
        this.product = product;
        this.xmlType = xmlType;
        this.catalogMappingField = catalogMappingField;
        this.schemaMappingField = schemaMappingField;
    }

    public static EDatabaseTypeName getTypeFromDbType(String dbType) {
        if (dbType == null) {
            return getTypeFromDisplayName(dbType);
        }
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getXmlName().toUpperCase().equals(dbType.toUpperCase())) {
                return typename;
            }
            if (typename.getProduct().toUpperCase().equals(dbType.toUpperCase())) {
                return typename;
            }
        }
        return getTypeFromDisplayName(dbType);
    }

    public static EDatabaseTypeName getTypeFromDisplayName(String displayName) {
        if (displayName == null) {
            return MYSQL;
        }
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getDisplayName().toLowerCase().equals(displayName.toLowerCase())) {
                return typename;
            }
        }
        return MYSQL;
    }

    /**
     * DOC zli Comment method "getTypeFromProductName".
     * 
     * @param productName
     * @return
     */
    public static EDatabaseTypeName getTypeFromProductName(String productName) {
        if (productName == null) {
            return MYSQL;
        }
        for (EDatabaseTypeName typename : EDatabaseTypeName.values()) {
            if (typename.getProduct().equals(productName)) {
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
