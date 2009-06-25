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
package org.talend.core.database;

import java.util.ArrayList;
import java.util.List;

/**
 * cli class global comment. Detailled comment
 */
public enum EDatabase4DriverClassName {
    ACCESS(EDatabaseTypeName.ACCESS, "sun.jdbc.odbc.JdbcOdbcDriver"), //$NON-NLS-1$
    AS400(EDatabaseTypeName.AS400, "com.ibm.as400.access.AS400JDBCDriver"), //$NON-NLS-1$
    FIREBIRD(EDatabaseTypeName.FIREBIRD, "org.firebirdsql.jdbc.FBDriver"), //$NON-NLS-1$
    GODBC(EDatabaseTypeName.GODBC, "sun.jdbc.odbc.JdbcOdbcDriver"), //$NON-NLS-1$
    GREENPLUM(EDatabaseTypeName.GREENPLUM, "org.postgresql.Driver"), //$NON-NLS-1$

    HSQLDB(EDatabaseTypeName.HSQLDB, "org.hsqldb.jdbcDriver"), //$NON-NLS-1$
    HSQLDB_IN_PROGRESS(EDatabaseTypeName.HSQLDB_IN_PROGRESS, "org.hsqldb.jdbcDriver"), //$NON-NLS-1$
    HSQLDB_SERVER(EDatabaseTypeName.HSQLDB_SERVER, "org.hsqldb.jdbcDriver"), //$NON-NLS-1$
    HSQLDB_WEBSERVER(EDatabaseTypeName.HSQLDB_WEBSERVER, "org.hsqldb.jdbcDriver"), //$NON-NLS-1$

    IBMDB2(EDatabaseTypeName.IBMDB2, "com.ibm.db2.jcc.DB2Driver"), //$NON-NLS-1$
    IBMDB2ZOS(EDatabaseTypeName.IBMDB2ZOS, "COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver"), //$NON-NLS-1$

    INFORMIX(EDatabaseTypeName.INFORMIX, "com.informix.jdbc.IfxDriver"), //$NON-NLS-1$
    INGRES(EDatabaseTypeName.INGRES, "com.ingres.jdbc.IngresDriver"), //$NON-NLS-1$
    INTERBASE(EDatabaseTypeName.INTERBASE, "interbase.interclient.Driver"), //$NON-NLS-1$

    JAVADB_DERBYCLIENT(EDatabaseTypeName.JAVADB_DERBYCLIENT, "org.apache.derby.jdbc.ClientDriver"), //$NON-NLS-1$
    JAVADB_EMBEDED(EDatabaseTypeName.JAVADB_EMBEDED, "org.apache.derby.jdbc.EmbeddedDriver"), //$NON-NLS-1$
    JAVADB_JCCJDBC(EDatabaseTypeName.JAVADB_JCCJDBC, "com.ibm.db2.jcc.DB2Driver"), //$NON-NLS-1$

    MAXDB(EDatabaseTypeName.MAXDB, "com.sap.dbtech.jdbc.DriverSapDB"), //$NON-NLS-1$

    MSODBC(EDatabaseTypeName.MSODBC, "sun.jdbc.odbc.JdbcOdbcDriver"), //$NON-NLS-1$
    MSSQL(EDatabaseTypeName.MSSQL, "net.sourceforge.jtds.jdbc.Driver"), //$NON-NLS-1$

    MYSQL(EDatabaseTypeName.MYSQL, "org.gjt.mm.mysql.Driver"), //$NON-NLS-1$
    NETEZZA(EDatabaseTypeName.NETEZZA, "org.netezza.Driver"), //$NON-NLS-1$

    ORACLEFORSID(EDatabaseTypeName.ORACLEFORSID, "oracle.jdbc.driver.OracleDriver"), //$NON-NLS-1$
    ORACLESN(EDatabaseTypeName.ORACLESN, "oracle.jdbc.driver.OracleDriver"), //$NON-NLS-1$

    PARACCEL(EDatabaseTypeName.PARACCEL, "com.paraccel.Driver"), //$NON-NLS-1$

    PLUSPSQL(EDatabaseTypeName.PLUSPSQL, "org.postgresql.Driver"), //$NON-NLS-1$
    PSQL(EDatabaseTypeName.PSQL, "org.postgresql.Driver"), //$NON-NLS-1$

    SAS(EDatabaseTypeName.SAS, "com.sas.rio.MVADriver"), //$NON-NLS-1$
    SQLITE(EDatabaseTypeName.SQLITE, "org.sqlite.JDBC"), //$NON-NLS-1$

    SYBASEASE(EDatabaseTypeName.SYBASEASE, "com.sybase.jdbc3.jdbc.SybDriver"), //$NON-NLS-1$
    SYBASEIQ(EDatabaseTypeName.SYBASEIQ, "com.sybase.jdbc3.jdbc.SybDriver"), //$NON-NLS-1$

    TERADATA(EDatabaseTypeName.TERADATA, "com.ncr.teradata.TeraDriver"), //$NON-NLS-1$

    VERTICA(EDatabaseTypeName.VERTICA, "com.vertica.Driver"), //$NON-NLS-1$

    //
    ;

    private EDatabaseTypeName dbType;

    private String driverClass;

    EDatabase4DriverClassName(EDatabaseTypeName dbType, String driverClass) {
        this.dbType = dbType;
        this.driverClass = driverClass;
    }

    public EDatabaseTypeName getDbType() {
        return this.dbType;
    }

    public String getDbTypeName() {
        return this.dbType.getXmlName();
    }

    public String getDriverClass() {
        return this.driverClass;
    }

    public static EDatabase4DriverClassName indexOfByDbType(String dbType) {
        if (dbType != null) {
            EDatabaseTypeName db = EDatabaseTypeName.getTypeFromDbType(dbType);
            if (db != null) {
                for (EDatabase4DriverClassName t4d : EDatabase4DriverClassName.values()) {
                    if (t4d.getDbType() == db) {
                        return t4d;
                    }
                }
            }
        }
        return null;
    }

    public static List<EDatabase4DriverClassName> indexOfByDriverClass(String driverClass) {
        List<EDatabase4DriverClassName> dbType4Drivers = new ArrayList<EDatabase4DriverClassName>();
        for (EDatabase4DriverClassName t4d : EDatabase4DriverClassName.values()) {
            if (t4d.getDriverClass().equals(driverClass)) {
                dbType4Drivers.add(t4d);
            }
        }
        return dbType4Drivers;
    }

    public static String getDriverClassByDbType(String dbType) {
        EDatabase4DriverClassName t4d = indexOfByDbType(dbType);
        if (t4d != null) {
            return t4d.getDriverClass();
        }
        return null;
    }

}
