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
package org.talend.core.hadoop.api.distribution.pivotal;

import org.talend.core.hadoop.api.components.HBaseComponent;
import org.talend.core.hadoop.api.components.HDFSComponent;
import org.talend.core.hadoop.api.components.HiveComponent;
import org.talend.core.hadoop.api.components.MRComponent;
import org.talend.core.hadoop.api.components.PigComponent;
import org.talend.core.hadoop.api.components.SqoopComponent;
import org.talend.core.hadoop.api.distribution.AbstractDistribution;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;

@Deprecated
public class Pivotal101Distribution extends AbstractDistribution implements HDFSComponent, MRComponent, HBaseComponent,
        SqoopComponent, PigComponent, HiveComponent {

    private final static String YARN_APPLICATION_CLASSPATH = "$HADOOP_CONF_DIR,$HADOOP_COMMON_HOME/*,$HADOOP_COMMON_HOME/lib/*,$HADOOP_HDFS_HOME/*,$HADOOP_HDFS_HOME/lib/*,$HADOOP_MAPRED_HOME/*,$HADOOP_MAPRED_HOME/lib/*,$YARN_HOME/*,$YARN_HOME/lib/*,$HADOOP_YARN_HOME/*,$HADOOP_YARN_HOME/lib/*,$HADOOP_COMMON_HOME/share/hadoop/common/*,$HADOOP_COMMON_HOME/share/hadoop/common/lib/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/lib/*,$HADOOP_YARN_HOME/share/hadoop/yarn/*,$HADOOP_YARN_HOME/share/hadoop/yarn/lib/*"; //$NON-NLS-1$

    public Pivotal101Distribution(EHadoopVersion4Drivers version) {
        this.version = version;
    }

    @Override
    public String getYarnApplicationClasspath() {
        return YARN_APPLICATION_CLASSPATH;
    }

    @Override
    public boolean doSupportUseDatanodeHostname() {
        return true;
    }

    @Override
    public boolean doSupportCrossPlatformSubmission() {
        return false;
    }

    @Override
    public boolean doSupportSequenceFileShortType() {
        return true;
    }

    @Override
    public boolean doSupportNewHBaseAPI() {
        return false;
    }

    @Override
    public boolean doJavaAPISupportStorePasswordInFile() {
        return false;
    }

    @Override
    public boolean doJavaAPISqoopImportSupportDeleteTargetDir() {
        return false;
    }

    @Override
    public boolean doJavaAPISqoopImportAllTablesSupportExcludeTable() {
        return false;
    }

    @Override
    public boolean doSupportHCatalog() {
        return false;
    }

    @Override
    public boolean pigVersionPriorTo_0_12() {
        // return false because this distribution doesn't support HCatalog.
        return false;
    }

    @Override
    public boolean doSupportHBase() {
        return true;
    }

    @Override
    public boolean doSupportImpersonation() {
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
        return false;
    }

    @Override
    public boolean doSupportTezForHive() {
        return false;
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
        return false;
    }

    @Override
    public boolean doSupportAvroFormat() {
        return false;
    }

    @Override
    public boolean doSupportParquetFormat() {
        return false;
    }

    @Override
    public boolean doSupportStoreAsParquet() {
        return false;
    }

}
