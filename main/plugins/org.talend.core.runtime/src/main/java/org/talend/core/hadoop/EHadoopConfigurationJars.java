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
package org.talend.core.hadoop;

/**
 * created by ycbai on 2014-5-28 Detailled comment
 * 
 */
public enum EHadoopConfigurationJars {

    HDFS(
         new String[] { "hadoop-conf.jar" }, new String[] { "hadoop-conf-kerberos.jar", "jetty-util-6.1.26.jar", "jersey-core-1.8.jar", "commons-io-2.4.jar" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

    HCATALOG(new String[] { "hadoop-conf.jar" }, new String[] { "hadoop-conf-kerberos.jar" }), //$NON-NLS-1$ //$NON-NLS-2$

    HIVE(new String[] { "hadoop-conf.jar" }, new String[] { "hadoop-conf-kerberos.jar" }), //$NON-NLS-1$ //$NON-NLS-2$

    ;

    private String[] disableSecurityJars;

    private String[] enableSecurityJars;

    EHadoopConfigurationJars(String[] disableSecurityJars, String[] enableSecurityJars) {
        this.disableSecurityJars = disableSecurityJars;
        this.enableSecurityJars = enableSecurityJars;
    }

    public String getName() {
        return name();
    }

    public String[] getDisableSecurityJars() {
        return this.disableSecurityJars;
    }

    public String[] getEnableSecurityJars() {
        return this.enableSecurityJars;
    }

}
