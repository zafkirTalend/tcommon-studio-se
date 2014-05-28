// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.kerberos;

/**
 * created by ycbai on 2014-5-27 Detailled comment
 * 
 */
public enum EKerberosDependentJars {

    HDFS(new String[] { "hadoop-conf-kerberos.jar", "jetty-util-6.1.26.jar", "jersey-core-1.8.jar", "commons-io-2.4.jar" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    HCATALOG(new String[] { "hadoop-conf-kerberos.jar" }), //$NON-NLS-1$

    HIVE(new String[] { "hadoop-conf-kerberos.jar" }), //$NON-NLS-1$

    ;

    private String[] krbJars;

    EKerberosDependentJars(String[] krbJars) {
        this.krbJars = krbJars;
    }

    public String getName() {
        return name();
    }

    public String[] getKrbJars() {
        return this.krbJars;
    }

}
