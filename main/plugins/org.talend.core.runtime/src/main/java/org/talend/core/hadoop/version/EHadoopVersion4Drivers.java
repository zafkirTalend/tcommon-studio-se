// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.hadoop.version;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * DOC ycbai class global comment. Detailled comment
 */
public enum EHadoopVersion4Drivers {

    HDP_1_0(new HadoopVersion4Drivers(EHadoopDistributions.HORTONWORKS, "Hortonworks Data Platform V1.0.0", "HDP_1_0")),

    HDP_1_2(new HadoopVersion4Drivers(EHadoopDistributions.HORTONWORKS, "Hortonworks Data Platform V1.2.0(Bimota)", "HDP_1_2")),

    APACHE_1_0_0(new HadoopVersion4Drivers(EHadoopDistributions.APACHE, "Apache 1.0.0", "APACHE_1_0_0")),

    APACHE_0_20_204(new HadoopVersion4Drivers(EHadoopDistributions.APACHE, "Apache 0.20.204", "APACHE_0_20_204")),

    APACHE_0_20_203(new HadoopVersion4Drivers(EHadoopDistributions.APACHE, "Apache 0.20.203", "APACHE_0_20_203")),

    APACHE_0_20_2(new HadoopVersion4Drivers(EHadoopDistributions.APACHE, "Apache 0.20.2", "APACHE_0_20_2")),

    CLOUDERA_CDH3(new HadoopVersion4Drivers(EHadoopDistributions.CLOUDERA, "Cloudera CDH3", "Cloudera_CDH3")),

    CLOUDERA_CDH4(new HadoopVersion4Drivers(EHadoopDistributions.CLOUDERA, "Cloudera CDH4", "Cloudera_CDH4")),

    MAPR1(new HadoopVersion4Drivers(EHadoopDistributions.MAPR, "MapR 1.2.0", "MAPR1")),

    MAPR2(new HadoopVersion4Drivers(EHadoopDistributions.MAPR, "MapR 2.0.0", "MAPR2")),

    MAPR212(new HadoopVersion4Drivers(EHadoopDistributions.MAPR, "MapR 2.1.2", "MAPR212")),

    MAPR_EMR(new HadoopVersion4Drivers(EHadoopDistributions.AMAZON_EMR, "MapR 1.2.8", "MapR_EMR")),

    APACHE_1_0_3_EMR(new HadoopVersion4Drivers(EHadoopDistributions.AMAZON_EMR, "Apache 1.0.3", "APACHE_1_0_3_EMR")),

    CUSTOM(new HadoopVersion4Drivers(EHadoopDistributions.CUSTOM));

    private HadoopVersion4Drivers version4Drivers;

    EHadoopVersion4Drivers(HadoopVersion4Drivers version4Drivers) {
        this.version4Drivers = version4Drivers;
    }

    public String getVersionDisplay() {
        return this.version4Drivers.getVersionDisplayName();
    }

    public String getVersionValue() {
        return this.version4Drivers.getVersionValue();
    }

    public List<String> getDrivers() {
        return this.version4Drivers.getDrivers();
    }

    public String getDriverStrings() {
        StringBuffer buffer = new StringBuffer();
        List<String> drivers = getDrivers();
        for (String driver : drivers) {
            buffer.append(driver).append(";"); //$NON-NLS-1$
        }
        if (buffer.length() > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer.toString();
    }

    public List<EHadoopDistributions> getSupportDistributions() {
        return this.version4Drivers.getDistributions();
    }

    public boolean supportDistribution(EHadoopDistributions distribution) {
        if (distribution != null) {
            return getSupportDistributions().contains(distribution);
        }
        return false;
    }

    public boolean supportDistribution(String distribution) {
        if (distribution != null) {
            for (EHadoopDistributions type : getSupportDistributions()) {
                if (type.getName().equalsIgnoreCase(distribution) || type.getDisplayName().equalsIgnoreCase(distribution)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean support(String distribution, String version) {
        if (distribution == null || version == null) {
            return false;
        }
        for (EHadoopVersion4Drivers v4d : EHadoopVersion4Drivers.values()) {
            if (v4d.supportDistribution(distribution)) {
                if (version.equalsIgnoreCase(v4d.getVersionValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static EHadoopVersion4Drivers indexOfByVersionDisplay(String displayName) {
        return indexOf(displayName, true);
    }

    public static EHadoopVersion4Drivers indexOfByVersion(String value) {
        return indexOf(value, false);
    }

    private static EHadoopVersion4Drivers indexOf(String name, boolean display) {
        if (name != null) {
            for (EHadoopVersion4Drivers version : EHadoopVersion4Drivers.values()) {
                if (display) {
                    if (name.equalsIgnoreCase(version.getVersionDisplay())) {
                        return version;
                    }
                } else {
                    if (name.equalsIgnoreCase(version.getVersionValue())) {
                        return version;
                    }
                }
            }
        }
        return EHadoopVersion4Drivers.CUSTOM;
    }

    public static List<EHadoopVersion4Drivers> indexOfByDistribution(String distribution) {
        List<EHadoopVersion4Drivers> v4dList = new ArrayList<EHadoopVersion4Drivers>();
        if (distribution != null) {
            for (EHadoopVersion4Drivers v4d : EHadoopVersion4Drivers.values()) {
                if (v4d.supportDistribution(distribution)) {
                    v4dList.add(v4d);
                }
            }
        }
        return v4dList;
    }

    public static List<EHadoopVersion4Drivers> indexOfByDistribution(EHadoopDistributions distribution) {
        List<EHadoopVersion4Drivers> v4dList = new ArrayList<EHadoopVersion4Drivers>();
        if (distribution != null) {
            for (EHadoopVersion4Drivers v4d : EHadoopVersion4Drivers.values()) {
                if (v4d.supportDistribution(distribution)) {
                    v4dList.add(v4d);
                }
            }
        }
        return v4dList;
    }

    public static EHadoopVersion4Drivers getVersion4DriversByDriverStrs(String driverStrs) {
        if (StringUtils.isEmpty(driverStrs)) {
            return null;
        }
        for (EHadoopVersion4Drivers version4Drivers : EHadoopVersion4Drivers.values()) {
            if (driverStrs.equalsIgnoreCase(version4Drivers.getDriverStrings())) {
                return version4Drivers;
            }
        }

        return null;
    }

    public static String getVersionByDriverStrs(String driverStrs) {
        return getVersionByDriverStrs(driverStrs, false);
    }

    public static String getVersionDisplayByDriverStrs(String driverStrs) {
        return getVersionByDriverStrs(driverStrs, true);
    }

    private static String getVersionByDriverStrs(String driverStrs, boolean display) {
        EHadoopVersion4Drivers version4Drivers = getVersion4DriversByDriverStrs(driverStrs);
        if (version4Drivers == null) {
            return null;
        }
        if (display) {
            return version4Drivers.getVersionDisplay();
        }
        return version4Drivers.getVersionValue();
    }
}
