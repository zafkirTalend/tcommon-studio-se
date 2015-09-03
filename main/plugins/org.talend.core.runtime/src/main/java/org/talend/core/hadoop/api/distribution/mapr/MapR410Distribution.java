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
package org.talend.core.hadoop.api.distribution.mapr;

import org.talend.core.hadoop.api.ESparkVersion;
import org.talend.core.hadoop.api.components.HBaseComponent;
import org.talend.core.hadoop.api.components.HCatalogComponent;
import org.talend.core.hadoop.api.components.HDFSComponent;
import org.talend.core.hadoop.api.components.HiveComponent;
import org.talend.core.hadoop.api.components.MRComponent;
import org.talend.core.hadoop.api.components.PigComponent;
import org.talend.core.hadoop.api.components.SparkBatchComponent;
import org.talend.core.hadoop.api.components.SqoopComponent;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;

/**
 * created by rdubois on 11 août 2015 Detailled comment
 *
 */
public class MapR410Distribution extends AbstractMapRDistribution implements HDFSComponent, MRComponent, HBaseComponent,
        SqoopComponent, PigComponent, HiveComponent, HCatalogComponent, SparkBatchComponent {

    private final static String YARN_APPLICATION_CLASSPATH = "$HADOOP_CONF_DIR,$HADOOP_COMMON_HOME/*,$HADOOP_COMMON_HOME/lib/*,$HADOOP_HDFS_HOME/*,$HADOOP_HDFS_HOME/lib/*,$HADOOP_MAPRED_HOME/*,$HADOOP_MAPRED_HOME/lib/*,$YARN_HOME/*,$YARN_HOME/lib/*,$HADOOP_YARN_HOME/*,$HADOOP_YARN_HOME/lib/*,$HADOOP_COMMON_HOME/share/hadoop/common/*,$HADOOP_COMMON_HOME/share/hadoop/common/lib/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/lib/*,$HADOOP_YARN_HOME/share/hadoop/yarn/*,$HADOOP_YARN_HOME/share/hadoop/yarn/lib/*"; //$NON-NLS-1$

    public MapR410Distribution(EHadoopVersion4Drivers version) {
        this.version = version;
    }

    @Override
    public boolean doSupportSequenceFileShortType() {
        return true;
    }

    @Override
    public boolean doSupportCrossPlatformSubmission() {
        return true;
    }

    @Override
    public String getYarnApplicationClasspath() {
        return YARN_APPLICATION_CLASSPATH;
    }

    @Override
    public boolean doSupportUseDatanodeHostname() {
        return false;
    }

    @Override
    public boolean doSupportNewHBaseAPI() {
        return true;
    }

    @Override
    public boolean doJavaAPISupportStorePasswordInFile() {
        return false;
    }

    @Override
    public boolean doSupportHCatalog() {
        return true;
    }

    @Override
    public boolean pigVersionPriorTo_0_12() {
        return false;
    }

    @Override
    public boolean doSupportTez() {
        return false;
    }

    @Override
    public boolean doSupportHBase() {
        return true;
    }

    @Override
    public boolean doSupportEmbeddedMode() {
        return true;
    }

    @Override
    public boolean doSupportStandaloneMode() {
        return true;
    }

    @Override
    public boolean doSupportHive1() {
        return true;
    }

    @Override
    public boolean doSupportHive2() {
        return true;
    }

    @Override
    public boolean doSupportTezForHive() {
        return true;
    }

    @Override
    public boolean doSupportHBaseForHive() {
        return true;
    }

    @Override
    public boolean doSupportSSL() {
        return false;
    }

    @Override
    public boolean doSupportORCFormat() {
        return true;
    }

    @Override
    public boolean doSupportAvroFormat() {
        return true;
    }

    @Override
    public boolean doSupportParquetFormat() {
        return true;
    }

    @Override
    public ESparkVersion getSparkVersion() {
        return ESparkVersion.SPARK_1_3;
    }

    @Override
    public boolean doSupportDynamicMemoryAllocation() {
        return false;
    }

    @Override
    public boolean isExecutedThroughSparkJobServer() {
        return false;
    }
}
