// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.connection.hive;

/**
 * This class provides the info for Hive. There are 3 levels as follows:
 * 
 * <pre>
 * <li> 0 -- stands for "Distribution"
 * <li> 1 -- stands for "Hive version"
 * <li> 2 -- stands for "Hive mode"
 * </pre>
 * 
 * The <code>key</code> of this enum is matched to the config file of component.
 * 
 * @author Marvin Wang
 * @version 1.0 jdk1.6
 * @date Aug 9, 2012
 */
public enum HiveConnVersionInfo {

    HORTONWORKS(0, "HORTONWORKS", "HortonWorks", null),

    APACHE(0, "APACHE", "Apache", null),

    CLOUDERA(0, "CLOUDERA", "Cloudera", null),

    HDP_1_0(1, "HDP_1_0", "HDP 1.0", HiveConnVersionInfo.HORTONWORKS),

    APACHE_0_20_203(1, "APACHE_0_20_203", "Apache 0.20.203", HiveConnVersionInfo.APACHE),

    APACHE_1_0_0(1, "APACHE_1_0_0", "Apache 1.0.0", HiveConnVersionInfo.APACHE),

    Cloudera_CDH4(1, "Cloudera_CDH4", "Cloudera CDH4", HiveConnVersionInfo.CLOUDERA),

    MODE_EMBEDDED(
                  2,
                  "EMBEDDED",
                  "Embedded",
                  HiveConnVersionInfo.HDP_1_0,
                  HiveConnVersionInfo.APACHE_1_0_0,
                  HiveConnVersionInfo.Cloudera_CDH4),

    MODE_STANDALONE(
                    2,
                    "STANDALONE",
                    "Standalone",
                    HiveConnVersionInfo.APACHE_0_20_203,
                    HiveConnVersionInfo.APACHE_1_0_0,
                    HiveConnVersionInfo.Cloudera_CDH4);

    private int level;

    private String key, displayName;

    private HiveConnVersionInfo[] follows;

    private HiveConnVersionInfo(int level, String key, String displayName, HiveConnVersionInfo... follows) {
        this.level = level;
        this.key = key;
        this.displayName = displayName;
        this.follows = follows;
    }

    public int getLevel() {
        return this.level;
    }

    public String getKey() {
        return this.key;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public HiveConnVersionInfo[] getFollows() {
        return this.follows;
    }

}
