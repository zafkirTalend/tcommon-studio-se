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
package org.talend.core.hadoop.api.distribution.hdp;

import org.talend.core.hadoop.api.components.HBaseComponent;
import org.talend.core.hadoop.api.components.HDFSComponent;
import org.talend.core.hadoop.api.components.MRComponent;
import org.talend.core.hadoop.api.components.SqoopComponent;
import org.talend.core.hadoop.api.distribution.AbstractDistribution;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;

public class HDP220Distribution extends AbstractDistribution implements HDFSComponent, MRComponent, HBaseComponent,
        SqoopComponent {

    private final static String YARN_APPLICATION_CLASSPATH = "$HADOOP_CONF_DIR,/usr/hdp/current/hadoop-client/*,/usr/hdp/current/hadoop-client/lib/*,/usr/hdp/current/hadoop-hdfs-client/*,/usr/hdp/current/hadoop-hdfs-client/lib/*,/usr/hdp/current/hadoop-mapreduce-client/*,/usr/hdp/current/hadoop-mapreduce-client/lib/*,/usr/hdp/current/hadoop-yarn-client/*,/usr/hdp/current/hadoop-yarn-client/lib/*"; //$NON-NLS-1$

    public HDP220Distribution(EHadoopVersion4Drivers version) {
        this.version = version;
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
    public boolean doSupportSequenceFileShortType() {
        return true;
    }

    @Override
    public boolean doSupportUseDatanodeHostname() {
        return true;
    }

    @Override
    public boolean doSupportNewHBaseAPI() {
        return true;
    }

    @Override
    public boolean doJavaAPISupportStorePasswordInFile() {
        return true;
    }

    @Override
    public boolean doJavaAPISqoopImportSupportDeleteTargetDir() {
        return true;
    }

    @Override
    public boolean doJavaAPISqoopImportAllTablesSupportExcludeTable() {
        return true;
    }
}
