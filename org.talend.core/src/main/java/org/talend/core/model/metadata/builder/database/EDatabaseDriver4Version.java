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
package org.talend.core.model.metadata.builder.database;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 */
public enum EDatabaseDriver4Version {
    // see feature 4720
    // just now , for oracle

    // qli comment
    // see feature 6046,and now,for Oracle and AS400(version).
    ORACLE_11("Oracle", "Oracle_11", "ojdbc5-11g.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ORACLE_10("Oracle", "Oracle_10", "ojdbc14-10g.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ORACLE_9("Oracle", "Oracle_9", "ojdbc14-9i.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ORACLE_8("Oracle", "Oracle_8", "ojdbc12-8i.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    V5R3_V6R1("AS400", "V5R3 to V6R1", "jt400_V5R3.jar"), // AS400 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    V5R2_V5R4("AS400", "V5R2 to V5R4", "jt400_V5R2.jar"), // AS400 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    INTERBASE("Interbase", null, "interclient.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    SQLITE("SQLite", null, "sqlitejdbc_v037_nested.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    FIREBIRD("FireBird", null, "jaybird-full-2.1.1.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    TERADATA("Teradata", null, "terajdbc4.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    JAVADB_DERBYCLIENT("JavaDB DerbyClient", null, "derbyclient.jar"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * Getter for dbType.
     * 
     * @return the dbType
     */
    public String getDbType() {
        return this.dbType;
    }

    private String dbVersionName;

    /**
     * Getter for versionName.
     * 
     * @return the versionName
     */
    public String getDbVersionName() {
        return this.dbVersionName;
    }

    /**
     * Getter for driverName.
     * 
     * @return the driverName
     */
    public String getDriverName() {
        if (this.getDbVersionName() != null && this.getDbVersionName().equals(ORACLE_11.dbVersionName)) {
            if (System.getProperty("java.version").startsWith("1.6")) { //$NON-NLS-1$ //$NON-NLS-2$
                return "ojdbc6-11g.jar"; //$NON-NLS-1$
            }
        }
        return this.driverName;
    }

    private String driverName;

    private String dbType;

    // qli comment the method 'EDatabaseDriver4Version'.
    // just add a parameter 'dbType'.
    EDatabaseDriver4Version(String dbType, String versionName, String driverName) {
        this.dbVersionName = versionName;
        this.driverName = driverName;
        this.dbType = dbType;
    }

    public static String getDriver(final String dbType, final String versionName) {
        if (versionName != null) {
            for (EDatabaseDriver4Version driverVersion : values()) {
                if (versionName.equals(driverVersion.getDbVersionName())) {
                    return driverVersion.getDriverName();
                }
            }
        }
        for (EDatabaseDriver4Version driverVersion : values()) {
            if (driverVersion.getDbType().equals(dbType)) {
                return driverVersion.getDriverName();
            }
        }
        return null;
    }

}
