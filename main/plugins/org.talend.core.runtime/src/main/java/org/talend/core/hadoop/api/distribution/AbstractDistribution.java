// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.hadoop.api.distribution;

import org.talend.core.hadoop.api.EHadoopVersion;
import org.talend.core.hadoop.version.EHadoopDistributions;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;

/**
 * Base class that describes a Distribution.
 *
 */
public abstract class AbstractDistribution {

    protected EHadoopVersion4Drivers version = null;

    public String getName() {
        if (this.version != null) {
            return this.version.getVersionDisplay();
        } else {
            throw new UnsupportedOperationException("The version should not be null."); //$NON-NLS-1$
        }
    }

    public EHadoopVersion getHadoopVersion() {
        if (this.version != null) {
            return this.version.isSupportYARN() ? EHadoopVersion.HADOOP_2 : EHadoopVersion.HADOOP_1;
        } else {
            throw new UnsupportedOperationException("The version should not be null."); //$NON-NLS-1$
        }
    }

    public boolean isHadoop1() {
        return getHadoopVersion() == EHadoopVersion.HADOOP_1;
    }

    public boolean isHadoop2() {
        return getHadoopVersion() == EHadoopVersion.HADOOP_2;
    }

    public boolean doSupportKerberos() {
        if (this.version != null) {
            return this.version.isSupportSecurity();
        } else {
            throw new UnsupportedOperationException("The version should not be null."); //$NON-NLS-1$
        }
    }

    public EHadoopDistributions getDistribution() {
        if (this.version != null) {
            return this.version.getDistribution();
        } else {
            throw new UnsupportedOperationException("The version should not be null."); //$NON-NLS-1$
        }
    }

    public boolean doSupportGroup() {
        return false;
    }

    public boolean isExecutedThroughWebHCat() {
        return false;
    }

    public String getYarnApplicationClasspath() {
        // Not used in Hadoop 1
        return ""; //$NON-NLS-1$
    }

    public boolean doSupportHive1Standalone() {
        return true;
    }
}
