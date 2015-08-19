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
package org.talend.core.hadoop.api.components;

import org.talend.core.hadoop.api.EHadoopVersion;
import org.talend.core.hadoop.version.EHadoopDistributions;

/**
 * Base interface that exposes generic methods for all Hadoop components (eg. HDFS, M/R, Pig).
 *
 */
public interface HadoopComponent {

    /**
     * @return the @link{EHadoopVersion4Drivers} display name of the distribution.
     */
    public String getName();

    /**
     * @return the @link{EHadoopDistributions} of the distribution.
     */
    public EHadoopDistributions getDistribution();

    /**
     * A distribution can be using Hadoop 1 or Hadoop 2. This method returns the used Hadoop version.
     * 
     * @return the @link{EHadoopVersion} of the distribution.
     */
    public EHadoopVersion getHadoopVersion();

    /**
     * @return true if the distribution uses @link{EHadoopVersion} HADOOP_2.
     */
    public boolean isHadoop2();

    /**
     * @return true if the distribution uses @link{EHadoopVersion} HADOOP_1.
     */
    public boolean isHadoop1();

    /**
     * @return a boolean that indicates if the distribution supports Kerberos.
     */
    public boolean doSupportKerberos();

    /**
     * @return a boolean that indicates if the distribution supports the USE_DATANODE_HOSTNAME property.
     */
    public boolean doSupportUseDatanodeHostname();

    /**
     * @return a boolean that indicates if the distribution supports the Group information to build the
     * UserGroupInformation object.
     */
    public boolean doSupportGroup();
}
