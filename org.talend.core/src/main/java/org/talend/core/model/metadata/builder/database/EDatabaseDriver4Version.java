// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * DOC YeXiaowei class global comment. Detailled comment <br/>
 * 
 * @deprecated have modified and moved to EDatabaseVersion4Drivers
 */
public enum EDatabaseDriver4Version {
    // Oracle
    ORACLE_11("Oracle", "Oracle_11", "ojdbc5-11g.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ORACLE_10("Oracle", "Oracle_10", "ojdbc14-10g.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ORACLE_9("Oracle", "Oracle_9", "ojdbc14-9i.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ORACLE_8("Oracle", "Oracle_8", "ojdbc12-8i.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    // Vertica
    VERTICA_4("Vertica", "VERTICA_4", "vertica_4.0_jdk_5.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    VERTICA_3("Vertica", "VERTICA_3", "vertica_3.0_jdk_5.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    VERTICA("Vertica", "VERTICA_3.5", "vertica_3.5_jdk_5.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    // AS400
    V5R4_V6R1("AS400", "V5R4 to V6R1", "jt400_V5R3.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    V5R2_V5R4("AS400", "V5R2 to V5R4", "jt400_V5R2.jar"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    INTERBASE("Interbase", null, "interclient.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    SQLITE("SQLite", null, "sqlitejdbc-v056.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    FIREBIRD("FireBird", null, "jaybird-full-2.1.1.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    TERADATA("Teradata", null, "terajdbc4.jar;tdgssconfig.jar;tdgssjava.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    JAVADB_DERBYCLIENT("JavaDB DerbyClient", null, "derbyclient.jar"), //$NON-NLS-1$ //$NON-NLS-2$
    NETEZZA("Netezza", null, "nzjdbc.jar"); //$NON-NLS-1$ //$NON-NLS-2$

    private String dbVersionName;

    private String drivers;

    private String dbType;

    public String getDbType() {
        return this.dbType;
    }

    public String getDbVersionName() {
        return this.dbVersionName;
    }

    public String getDriverName() {
        if (this.getDbVersionName() != null && this.getDbVersionName().equals(ORACLE_11.dbVersionName)) {
            if (System.getProperty("java.version").startsWith("1.6")) { //$NON-NLS-1$ //$NON-NLS-2$
                return "ojdbc6-11g.jar"; //$NON-NLS-1$
            }
        }
        return this.drivers;
    }

    EDatabaseDriver4Version(String dbType, String versionName, String drivers) {
        this.dbVersionName = versionName;
        this.drivers = drivers;
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

    public static String getDbVersionName(final String dbType, final String driver) {
        if (driver != null) {
            for (EDatabaseDriver4Version driverVersion : values()) {
                if (driver.equals(driverVersion.getDriverName()) && driverVersion.getDbType().equals(dbType)) {
                    return driverVersion.getDbVersionName();
                }
            }
        }
        for (EDatabaseDriver4Version driverVersion : values()) {
            if (driverVersion.getDbType().equals(dbType)) {
                return driverVersion.getDbVersionName();
            }
        }
        return null;
    }

    public static List<String> getDrivers(final String dbType, final String versionName) {
        return getDrivers(getDriver(dbType, versionName));
    }

    private static List<String> getDrivers(String driverNames) {
        if (driverNames != null) {
            String[] s = driverNames.split(";"); //$NON-NLS-1$
            if (s != null) {
                return Arrays.asList(s);
            }
        }
        return Collections.emptyList();
    }
}
