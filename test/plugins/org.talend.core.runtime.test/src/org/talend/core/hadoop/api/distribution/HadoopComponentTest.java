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

import static org.junit.Assert.*;

import org.junit.Test;
import org.talend.core.hadoop.api.DistributionFactory;
import org.talend.core.hadoop.api.EHadoopVersion;
import org.talend.core.hadoop.api.components.HBaseComponent;
import org.talend.core.hadoop.api.components.HCatalogComponent;
import org.talend.core.hadoop.api.components.HDFSComponent;
import org.talend.core.hadoop.api.components.HadoopComponent;
import org.talend.core.hadoop.api.components.HiveComponent;
import org.talend.core.hadoop.api.components.ImpalaComponent;
import org.talend.core.hadoop.api.components.MRComponent;
import org.talend.core.hadoop.api.components.PigComponent;
import org.talend.core.hadoop.api.components.SparkBatchComponent;
import org.talend.core.hadoop.api.components.SparkStreamingComponent;
import org.talend.core.hadoop.api.components.SqoopComponent;
import org.talend.core.hadoop.version.EHadoopDistributions;

public class HadoopComponentTest {

    private static final String EMPTY = ""; //$NON-NLS-1$

    private static final String CLOUDERA = "CLOUDERA"; //$NON-NLS-1$

    private static final String HORTONWORKS = "HORTONWORKS"; //$NON-NLS-1$

    private static final String MAPR = "MAPR"; //$NON-NLS-1$

    private static final String AMAZON_EMR = "AMAZON_EMR"; //$NON-NLS-1$

    private static final String PIVOTAL_HD = "PIVOTAL_HD"; //$NON-NLS-1$

    private static final String APACHE = "APACHE"; //$NON-NLS-1$

    private static final String HDINSIGHT = "MICROSOFT_HD_INSIGHT"; //$NON-NLS-1$

    private static final String CUSTOM = "CUSTOM"; //$NON-NLS-1$

    private static final String CDH400MR1 = "Cloudera_CDH4"; //$NON-NLS-1$

    private static final String CDH400MR2 = "Cloudera_CDH4_YARN"; //$NON-NLS-1$

    private static final String CDH500 = "Cloudera_CDH5"; //$NON-NLS-1$

    private static final String CDH510MR1 = "Cloudera_CDH5_1_MR1"; //$NON-NLS-1$

    private static final String CDH510MR2 = "Cloudera_CDH5_1"; //$NON-NLS-1$

    private static final String CDH540 = "Cloudera_CDH5_4"; //$NON-NLS-1$

    private static final String HDP120 = "HDP_1_2"; //$NON-NLS-1$

    private static final String HDP130 = "HDP_1_3"; //$NON-NLS-1$

    private static final String HDP200 = "HDP_2_0"; //$NON-NLS-1$

    private static final String HDP210 = "HDP_2_1"; //$NON-NLS-1$

    private static final String HDP220 = "HDP_2_2"; //$NON-NLS-1$

    private static final String HDP230 = "HDP_2_3"; //$NON-NLS-1$

    private static final String APACHE100 = "APACHE_1_0_0"; //$NON-NLS-1$

    private static final String MAPR200 = "MAPR2"; //$NON-NLS-1$

    private static final String MAPR212 = "MAPR212"; //$NON-NLS-1$

    private static final String MAPR213 = "MAPR213"; //$NON-NLS-1$

    private static final String MAPR301 = "MAPR301"; //$NON-NLS-1$

    private static final String MAPR310 = "MAPR310"; //$NON-NLS-1$

    private static final String MAPR401 = "MAPR401"; //$NON-NLS-1$

    private static final String MAPR410 = "MAPR410"; //$NON-NLS-1$

    private static final String EMRAPACHE103 = "APACHE_1_0_3_EMR"; //$NON-NLS-1$

    private static final String EMRAPACHE240 = "APACHE_2_4_0_EMR"; //$NON-NLS-1$

    private static final String EMRAPACHE240_HIVE0131 = "APACHE_2_4_0_EMR_0_13_1"; //$NON-NLS-1$

    private static final String EMR400 = "EMR_4_0_0"; //$NON-NLS-1$

    private static final String PIV101 = "PIVOTAL_HD_1_0_1"; //$NON-NLS-1$

    private static final String PIV200 = "PIVOTAL_HD_2_0"; //$NON-NLS-1$

    private static final String HDINSIGHT310 = "MICROSOFT_HD_INSIGHT_3_1"; //$NON-NLS-1$

    private static final String HDINSIGHT320 = "MICROSOFT_HD_INSIGHT_3_2"; //$NON-NLS-1$

    private final static String DEFAULT_YARN_APPLICATION_CLASSPATH = "$HADOOP_CONF_DIR,$HADOOP_COMMON_HOME/*,$HADOOP_COMMON_HOME/lib/*,$HADOOP_HDFS_HOME/*,$HADOOP_HDFS_HOME/lib/*,$HADOOP_MAPRED_HOME/*,$HADOOP_MAPRED_HOME/lib/*,$YARN_HOME/*,$YARN_HOME/lib/*,$HADOOP_YARN_HOME/*,$HADOOP_YARN_HOME/lib/*,$HADOOP_COMMON_HOME/share/hadoop/common/*,$HADOOP_COMMON_HOME/share/hadoop/common/lib/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/lib/*,$HADOOP_YARN_HOME/share/hadoop/yarn/*,$HADOOP_YARN_HOME/share/hadoop/yarn/lib/*"; //$NON-NLS-1$

    @Test
    public void testCDH400MR1() throws Exception {
        HadoopComponent cdh400mr1 = DistributionFactory.buildDistribution(CLOUDERA, CDH400MR1);
        assertNotNull(cdh400mr1.getName());
        assertEquals(EHadoopDistributions.CLOUDERA, cdh400mr1.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, cdh400mr1.getHadoopVersion());
        assertTrue(cdh400mr1.doSupportKerberos());
        assertFalse(cdh400mr1.doSupportUseDatanodeHostname());
        assertFalse(cdh400mr1.doSupportGroup());
        assertTrue(((HDFSComponent) cdh400mr1).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh400mr1).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) cdh400mr1).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) cdh400mr1).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) cdh400mr1).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) cdh400mr1).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) cdh400mr1).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) cdh400mr1).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) cdh400mr1).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) cdh400mr1).doSupportHCatalog());
        assertFalse(((PigComponent) cdh400mr1).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) cdh400mr1).doSupportTez());
        assertTrue(((PigComponent) cdh400mr1).doSupportHBase());
        assertTrue(((HiveComponent) cdh400mr1).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) cdh400mr1).doSupportStandaloneMode());
        assertTrue(((HiveComponent) cdh400mr1).doSupportHive1());
        assertTrue(((HiveComponent) cdh400mr1).doSupportHive2());
        assertFalse(((HiveComponent) cdh400mr1).doSupportTezForHive());
        assertTrue(((HiveComponent) cdh400mr1).doSupportHBaseForHive());
        assertTrue(((HiveComponent) cdh400mr1).doSupportSSL());
        assertTrue(((HiveComponent) cdh400mr1).doSupportHive1Standalone());
        assertFalse(((HiveComponent) cdh400mr1).doSupportORCFormat());
        assertTrue(((HiveComponent) cdh400mr1).doSupportAvroFormat());
        assertFalse(((HiveComponent) cdh400mr1).doSupportParquetFormat());
        assertFalse(cdh400mr1 instanceof SparkBatchComponent);
        assertFalse(cdh400mr1 instanceof SparkStreamingComponent);
        assertFalse(cdh400mr1 instanceof HCatalogComponent);
        assertFalse(cdh400mr1 instanceof ImpalaComponent);
    }

    @Test
    public void testCDH400MR2() throws Exception {
        HadoopComponent cdh400mr2 = DistributionFactory.buildDistribution(CLOUDERA, CDH400MR2);
        assertNotNull(cdh400mr2.getName());
        assertEquals(EHadoopDistributions.CLOUDERA, cdh400mr2.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, cdh400mr2.getHadoopVersion());
        assertTrue(cdh400mr2.doSupportKerberos());
        assertTrue(cdh400mr2.doSupportUseDatanodeHostname());
        assertFalse(cdh400mr2.doSupportGroup());
        assertTrue(((HDFSComponent) cdh400mr2).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh400mr2).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) cdh400mr2).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) cdh400mr2).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) cdh400mr2).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) cdh400mr2).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) cdh400mr2).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) cdh400mr2).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) cdh400mr2).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) cdh400mr2).doSupportHCatalog());
        assertFalse(((PigComponent) cdh400mr2).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) cdh400mr2).doSupportTez());
        assertTrue(((PigComponent) cdh400mr2).doSupportHBase());
        assertTrue(((HiveComponent) cdh400mr2).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) cdh400mr2).doSupportStandaloneMode());
        assertTrue(((HiveComponent) cdh400mr2).doSupportHive1());
        assertTrue(((HiveComponent) cdh400mr2).doSupportHive2());
        assertFalse(((HiveComponent) cdh400mr2).doSupportTezForHive());
        assertTrue(((HiveComponent) cdh400mr2).doSupportHBaseForHive());
        assertTrue(((HiveComponent) cdh400mr2).doSupportSSL());
        assertFalse(((HiveComponent) cdh400mr2).doSupportORCFormat());
        assertTrue(((HiveComponent) cdh400mr2).doSupportAvroFormat());
        assertTrue(((HiveComponent) cdh400mr2).doSupportParquetFormat());
        assertFalse(cdh400mr2 instanceof SparkBatchComponent);
        assertFalse(cdh400mr2 instanceof SparkStreamingComponent);
        assertFalse(cdh400mr2 instanceof HCatalogComponent);
        assertFalse(cdh400mr2 instanceof ImpalaComponent);
    }

    @Test
    public void testCDH500() throws Exception {
        HadoopComponent cdh500 = DistributionFactory.buildDistribution(CLOUDERA, CDH500);
        assertNotNull(cdh500.getName());
        assertEquals(EHadoopDistributions.CLOUDERA, cdh500.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, cdh500.getHadoopVersion());
        assertTrue(cdh500.doSupportKerberos());
        assertTrue(cdh500.doSupportUseDatanodeHostname());
        assertFalse(cdh500.doSupportGroup());
        assertTrue(((HDFSComponent) cdh500).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh500).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) cdh500).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) cdh500).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) cdh500).getYarnApplicationClasspath());
        assertTrue(((HBaseComponent) cdh500).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) cdh500).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) cdh500).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) cdh500).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) cdh500).doSupportHCatalog());
        assertFalse(((PigComponent) cdh500).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) cdh500).doSupportTez());
        assertTrue(((PigComponent) cdh500).doSupportHBase());
        assertTrue(((HiveComponent) cdh500).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) cdh500).doSupportStandaloneMode());
        assertTrue(((HiveComponent) cdh500).doSupportHive1());
        assertTrue(((HiveComponent) cdh500).doSupportHive2());
        assertFalse(((HiveComponent) cdh500).doSupportTezForHive());
        assertTrue(((HiveComponent) cdh500).doSupportHBaseForHive());
        assertTrue(((HiveComponent) cdh500).doSupportSSL());
        assertTrue(((HiveComponent) cdh500).doSupportORCFormat());
        assertTrue(((HiveComponent) cdh500).doSupportAvroFormat());
        assertTrue(((HiveComponent) cdh500).doSupportParquetFormat());
        assertFalse(cdh500 instanceof SparkBatchComponent);
        assertFalse(cdh500 instanceof SparkStreamingComponent);
        assertTrue(cdh500 instanceof HCatalogComponent);
        assertFalse(cdh500 instanceof ImpalaComponent);
    }

    @Test
    public void testCDH510MR1() throws Exception {
        HadoopComponent cdh510mr1 = DistributionFactory.buildDistribution(CLOUDERA, CDH510MR1);
        assertNotNull(cdh510mr1.getName());
        assertEquals(EHadoopDistributions.CLOUDERA, cdh510mr1.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, cdh510mr1.getHadoopVersion());
        assertTrue(cdh510mr1.doSupportKerberos());
        assertFalse(cdh510mr1.doSupportUseDatanodeHostname());
        assertFalse(cdh510mr1.doSupportGroup());
        assertTrue(((HDFSComponent) cdh510mr1).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh510mr1).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) cdh510mr1).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) cdh510mr1).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) cdh510mr1).getYarnApplicationClasspath());
        assertTrue(((HBaseComponent) cdh510mr1).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) cdh510mr1).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) cdh510mr1).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) cdh510mr1).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) cdh510mr1).doSupportHCatalog());
        assertFalse(((PigComponent) cdh510mr1).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) cdh510mr1).doSupportTez());
        assertTrue(((PigComponent) cdh510mr1).doSupportHBase());
        assertTrue(((HiveComponent) cdh510mr1).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) cdh510mr1).doSupportStandaloneMode());
        assertTrue(((HiveComponent) cdh510mr1).doSupportHive1());
        assertTrue(((HiveComponent) cdh510mr1).doSupportHive2());
        assertFalse(((HiveComponent) cdh510mr1).doSupportTezForHive());
        assertTrue(((HiveComponent) cdh510mr1).doSupportHBaseForHive());
        assertTrue(((HiveComponent) cdh510mr1).doSupportSSL());
        assertTrue(((HiveComponent) cdh510mr1).doSupportORCFormat());
        assertTrue(((HiveComponent) cdh510mr1).doSupportAvroFormat());
        assertTrue(((HiveComponent) cdh510mr1).doSupportParquetFormat());
        assertFalse(cdh510mr1 instanceof SparkBatchComponent);
        assertFalse(cdh510mr1 instanceof SparkStreamingComponent);
        assertTrue(cdh510mr1 instanceof HCatalogComponent);
        assertFalse(cdh510mr1 instanceof ImpalaComponent);
    }

    @Test
    public void testCDH510MR2() throws Exception {
        HadoopComponent cdh510mr2 = DistributionFactory.buildDistribution(CLOUDERA, CDH510MR2);
        assertNotNull(cdh510mr2.getName());
        assertEquals(EHadoopDistributions.CLOUDERA, cdh510mr2.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, cdh510mr2.getHadoopVersion());
        assertTrue(cdh510mr2.doSupportKerberos());
        assertTrue(cdh510mr2.doSupportUseDatanodeHostname());
        assertFalse(cdh510mr2.doSupportGroup());
        assertTrue(((HDFSComponent) cdh510mr2).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh510mr2).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) cdh510mr2).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) cdh510mr2).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) cdh510mr2).getYarnApplicationClasspath());
        assertTrue(((HBaseComponent) cdh510mr2).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) cdh510mr2).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) cdh510mr2).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) cdh510mr2).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) cdh510mr2).doSupportHCatalog());
        assertFalse(((PigComponent) cdh510mr2).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) cdh510mr2).doSupportTez());
        assertTrue(((PigComponent) cdh510mr2).doSupportHBase());
        assertTrue(((HiveComponent) cdh510mr2).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) cdh510mr2).doSupportStandaloneMode());
        assertTrue(((HiveComponent) cdh510mr2).doSupportHive1());
        assertTrue(((HiveComponent) cdh510mr2).doSupportHive2());
        assertFalse(((HiveComponent) cdh510mr2).doSupportTezForHive());
        assertTrue(((HiveComponent) cdh510mr2).doSupportHBaseForHive());
        assertTrue(((HiveComponent) cdh510mr2).doSupportSSL());
        assertTrue(((HiveComponent) cdh510mr2).doSupportORCFormat());
        assertTrue(((HiveComponent) cdh510mr2).doSupportAvroFormat());
        assertTrue(((HiveComponent) cdh510mr2).doSupportParquetFormat());
        assertFalse(((SparkBatchComponent) cdh510mr2).isSpark14());
        assertFalse(((SparkBatchComponent) cdh510mr2).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkBatchComponent) cdh510mr2).isExecutedThroughSparkJobServer());
        assertFalse(((SparkStreamingComponent) cdh510mr2).isSpark14());
        assertFalse(((SparkStreamingComponent) cdh510mr2).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkStreamingComponent) cdh510mr2).isExecutedThroughSparkJobServer());
        assertTrue(cdh510mr2 instanceof HCatalogComponent);
        assertTrue(cdh510mr2 instanceof ImpalaComponent);
    }

    @Test
    public void testCDH540() throws Exception {
        HadoopComponent cdh540 = DistributionFactory.buildDistribution(CLOUDERA, CDH540);
        assertNotNull(cdh540.getName());
        assertEquals(EHadoopDistributions.CLOUDERA, cdh540.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, cdh540.getHadoopVersion());
        assertTrue(cdh540.doSupportKerberos());
        assertTrue(cdh540.doSupportUseDatanodeHostname());
        assertFalse(cdh540.doSupportGroup());
        assertTrue(((HDFSComponent) cdh540).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh540).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) cdh540).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) cdh540).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) cdh540).getYarnApplicationClasspath());
        assertTrue(((HBaseComponent) cdh540).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) cdh540).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) cdh540).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) cdh540).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) cdh540).doSupportHCatalog());
        assertFalse(((PigComponent) cdh540).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) cdh540).doSupportTez());
        assertTrue(((PigComponent) cdh540).doSupportHBase());
        assertTrue(((HiveComponent) cdh540).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) cdh540).doSupportStandaloneMode());
        assertTrue(((HiveComponent) cdh540).doSupportHive1());
        assertTrue(((HiveComponent) cdh540).doSupportHive2());
        assertFalse(((HiveComponent) cdh540).doSupportTezForHive());
        assertTrue(((HiveComponent) cdh540).doSupportHBaseForHive());
        assertTrue(((HiveComponent) cdh540).doSupportSSL());
        assertTrue(((HiveComponent) cdh540).doSupportORCFormat());
        assertTrue(((HiveComponent) cdh540).doSupportAvroFormat());
        assertTrue(((HiveComponent) cdh540).doSupportParquetFormat());
        assertFalse(((SparkBatchComponent) cdh540).isSpark14());
        assertTrue(((SparkBatchComponent) cdh540).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkBatchComponent) cdh540).isExecutedThroughSparkJobServer());
        assertFalse(((SparkStreamingComponent) cdh540).isSpark14());
        assertTrue(((SparkStreamingComponent) cdh540).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkStreamingComponent) cdh540).isExecutedThroughSparkJobServer());
        assertTrue(cdh540 instanceof HCatalogComponent);
        assertTrue(cdh540 instanceof ImpalaComponent);
    }

    @Test
    public void testHDP120() throws Exception {
        HadoopComponent hdp120 = DistributionFactory.buildDistribution(HORTONWORKS, HDP120);
        assertNotNull(hdp120.getName());
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp120.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, hdp120.getHadoopVersion());
        assertTrue(hdp120.doSupportKerberos());
        assertTrue(hdp120.doSupportUseDatanodeHostname());
        assertFalse(hdp120.doSupportGroup());
        assertFalse(((HDFSComponent) hdp120).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp120).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdp120).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) hdp120).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) hdp120).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) hdp120).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) hdp120).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) hdp120).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) hdp120).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) hdp120).doSupportHCatalog());
        assertTrue(((PigComponent) hdp120).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) hdp120).doSupportTez());
        assertTrue(((PigComponent) hdp120).doSupportHBase());
        assertTrue(((HiveComponent) hdp120).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) hdp120).doSupportStandaloneMode());
        assertTrue(((HiveComponent) hdp120).doSupportHive1());
        assertTrue(((HiveComponent) hdp120).doSupportHive2());
        assertFalse(((HiveComponent) hdp120).doSupportTezForHive());
        assertTrue(((HiveComponent) hdp120).doSupportHBaseForHive());
        assertFalse(((HiveComponent) hdp120).doSupportSSL());
        assertFalse(((HiveComponent) hdp120).doSupportORCFormat());
        assertTrue(((HiveComponent) hdp120).doSupportAvroFormat());
        assertTrue(((HiveComponent) hdp120).doSupportParquetFormat());
        assertFalse(hdp120 instanceof SparkBatchComponent);
        assertFalse(hdp120 instanceof SparkStreamingComponent);
        assertTrue(hdp120 instanceof HCatalogComponent);
        assertFalse(hdp120 instanceof ImpalaComponent);
    }

    @Test
    public void testHDP130() throws Exception {
        HadoopComponent hdp130 = DistributionFactory.buildDistribution(HORTONWORKS, HDP130);
        assertNotNull(hdp130.getName());
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp130.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, hdp130.getHadoopVersion());
        assertTrue(hdp130.doSupportKerberos());
        assertTrue(hdp130.doSupportUseDatanodeHostname());
        assertFalse(hdp130.doSupportGroup());
        assertFalse(((HDFSComponent) hdp130).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp130).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdp130).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) hdp130).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) hdp130).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) hdp130).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) hdp130).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) hdp130).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) hdp130).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) hdp130).doSupportHCatalog());
        assertTrue(((PigComponent) hdp130).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) hdp130).doSupportTez());
        assertTrue(((PigComponent) hdp130).doSupportHBase());
        assertTrue(((HiveComponent) hdp130).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) hdp130).doSupportStandaloneMode());
        assertTrue(((HiveComponent) hdp130).doSupportHive1());
        assertTrue(((HiveComponent) hdp130).doSupportHive2());
        assertFalse(((HiveComponent) hdp130).doSupportTezForHive());
        assertTrue(((HiveComponent) hdp130).doSupportHBaseForHive());
        assertFalse(((HiveComponent) hdp130).doSupportSSL());
        assertTrue(((HiveComponent) hdp130).doSupportORCFormat());
        assertTrue(((HiveComponent) hdp130).doSupportAvroFormat());
        assertTrue(((HiveComponent) hdp130).doSupportParquetFormat());
        assertFalse(hdp130 instanceof SparkBatchComponent);
        assertFalse(hdp130 instanceof SparkStreamingComponent);
        assertTrue(hdp130 instanceof HCatalogComponent);
        assertFalse(hdp130 instanceof ImpalaComponent);
    }

    @Test
    public void testHDP200() throws Exception {
        HadoopComponent hdp200 = DistributionFactory.buildDistribution(HORTONWORKS, HDP200);
        assertNotNull(hdp200.getName());
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp200.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdp200.getHadoopVersion());
        assertTrue(hdp200.doSupportKerberos());
        assertTrue(hdp200.doSupportUseDatanodeHostname());
        assertFalse(hdp200.doSupportGroup());
        assertTrue(((HDFSComponent) hdp200).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp200).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdp200).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) hdp200).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) hdp200).getYarnApplicationClasspath());
        assertTrue(((HBaseComponent) hdp200).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) hdp200).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) hdp200).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) hdp200).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) hdp200).doSupportHCatalog());
        assertFalse(((PigComponent) hdp200).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) hdp200).doSupportTez());
        assertFalse(((PigComponent) hdp200).doSupportHBase());
        assertTrue(((HiveComponent) hdp200).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) hdp200).doSupportStandaloneMode());
        assertTrue(((HiveComponent) hdp200).doSupportHive1());
        assertTrue(((HiveComponent) hdp200).doSupportHive2());
        assertFalse(((HiveComponent) hdp200).doSupportTezForHive());
        assertTrue(((HiveComponent) hdp200).doSupportHBaseForHive());
        assertTrue(((HiveComponent) hdp200).doSupportSSL());
        assertTrue(((HiveComponent) hdp200).doSupportORCFormat());
        assertTrue(((HiveComponent) hdp200).doSupportAvroFormat());
        assertTrue(((HiveComponent) hdp200).doSupportParquetFormat());
        assertFalse(hdp200 instanceof SparkBatchComponent);
        assertFalse(hdp200 instanceof SparkStreamingComponent);
        assertTrue(hdp200 instanceof HCatalogComponent);
        assertFalse(hdp200 instanceof ImpalaComponent);
    }

    @Test
    public void testHDP210() throws Exception {
        HadoopComponent hdp210 = DistributionFactory.buildDistribution(HORTONWORKS, HDP210);
        assertNotNull(hdp210.getName());
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp210.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdp210.getHadoopVersion());
        assertTrue(hdp210.doSupportKerberos());
        assertTrue(hdp210.doSupportUseDatanodeHostname());
        assertFalse(hdp210.doSupportGroup());
        assertTrue(((HDFSComponent) hdp210).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp210).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) hdp210).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) hdp210).doSupportImpersonation());
        assertTrue(((HBaseComponent) hdp210).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) hdp210).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) hdp210).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) hdp210).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) hdp210).doSupportHCatalog());
        assertFalse(((PigComponent) hdp210).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) hdp210).doSupportTez());
        assertTrue(((PigComponent) hdp210).doSupportHBase());
        assertTrue(((HiveComponent) hdp210).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) hdp210).doSupportStandaloneMode());
        assertTrue(((HiveComponent) hdp210).doSupportHive1());
        assertTrue(((HiveComponent) hdp210).doSupportHive2());
        assertTrue(((HiveComponent) hdp210).doSupportTezForHive());
        assertTrue(((HiveComponent) hdp210).doSupportHBaseForHive());
        assertTrue(((HiveComponent) hdp210).doSupportSSL());
        assertTrue(((HiveComponent) hdp210).doSupportORCFormat());
        assertTrue(((HiveComponent) hdp210).doSupportAvroFormat());
        assertTrue(((HiveComponent) hdp210).doSupportParquetFormat());
        assertFalse(hdp210 instanceof SparkBatchComponent);
        assertFalse(hdp210 instanceof SparkStreamingComponent);
        assertTrue(hdp210 instanceof HCatalogComponent);
        assertFalse(hdp210 instanceof ImpalaComponent);
    }

    @Test
    public void testHDP220() throws Exception {
        HadoopComponent hdp220 = DistributionFactory.buildDistribution(HORTONWORKS, HDP220);
        assertNotNull(hdp220.getName());
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp220.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdp220.getHadoopVersion());
        assertTrue(hdp220.doSupportKerberos());
        assertTrue(hdp220.doSupportUseDatanodeHostname());
        assertFalse(hdp220.doSupportGroup());
        assertTrue(((HDFSComponent) hdp220).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp220).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) hdp220).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) hdp220).doSupportImpersonation());
        assertTrue(((HBaseComponent) hdp220).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) hdp220).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) hdp220).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) hdp220).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) hdp220).doSupportHCatalog());
        assertFalse(((PigComponent) hdp220).pigVersionPriorTo_0_12());
        assertTrue(((PigComponent) hdp220).doSupportTez());
        assertTrue(((PigComponent) hdp220).doSupportHBase());
        assertTrue(((HiveComponent) hdp220).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) hdp220).doSupportStandaloneMode());
        assertTrue(((HiveComponent) hdp220).doSupportHive1());
        assertTrue(((HiveComponent) hdp220).doSupportHive2());
        assertTrue(((HiveComponent) hdp220).doSupportTezForHive());
        assertTrue(((HiveComponent) hdp220).doSupportHBaseForHive());
        assertTrue(((HiveComponent) hdp220).doSupportSSL());
        assertTrue(((HiveComponent) hdp220).doSupportORCFormat());
        assertTrue(((HiveComponent) hdp220).doSupportAvroFormat());
        assertTrue(((HiveComponent) hdp220).doSupportParquetFormat());
        assertFalse(hdp220 instanceof SparkBatchComponent);
        assertFalse(hdp220 instanceof SparkStreamingComponent);
        assertTrue(hdp220 instanceof HCatalogComponent);
        assertFalse(hdp220 instanceof ImpalaComponent);
    }

    @Test
    public void testHDP230() throws Exception {
        HadoopComponent hdp230 = DistributionFactory.buildDistribution(HORTONWORKS, HDP230);
        assertNotNull(hdp230.getName());
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp230.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdp230.getHadoopVersion());
        assertTrue(hdp230.doSupportKerberos());
        assertTrue(hdp230.doSupportUseDatanodeHostname());
        assertFalse(hdp230.doSupportGroup());
        assertTrue(((HDFSComponent) hdp230).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp230).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) hdp230).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) hdp230).doSupportImpersonation());
        assertTrue(((HBaseComponent) hdp230).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) hdp230).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) hdp230).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) hdp230).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) hdp230).doSupportHCatalog());
        assertFalse(((PigComponent) hdp230).pigVersionPriorTo_0_12());
        assertTrue(((PigComponent) hdp230).doSupportTez());
        assertTrue(((PigComponent) hdp230).doSupportHBase());
        assertTrue(((HiveComponent) hdp230).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) hdp230).doSupportStandaloneMode());
        assertTrue(((HiveComponent) hdp230).doSupportHive1());
        assertTrue(((HiveComponent) hdp230).doSupportHive2());
        assertTrue(((HiveComponent) hdp230).doSupportTezForHive());
        assertTrue(((HiveComponent) hdp230).doSupportHBaseForHive());
        assertTrue(((HiveComponent) hdp230).doSupportSSL());
        assertTrue(((HiveComponent) hdp230).doSupportORCFormat());
        assertTrue(((HiveComponent) hdp230).doSupportAvroFormat());
        assertTrue(((HiveComponent) hdp230).doSupportParquetFormat());
        assertTrue(hdp230 instanceof SparkBatchComponent);
        assertTrue(hdp230 instanceof SparkStreamingComponent);
        assertTrue(hdp230 instanceof HCatalogComponent);
        assertFalse(hdp230 instanceof ImpalaComponent);
    }

    @Test
    public void testAPACHE100() throws Exception {
        HadoopComponent apache100 = DistributionFactory.buildDistribution(APACHE, APACHE100);
        assertNotNull(apache100.getName());
        assertEquals(EHadoopDistributions.APACHE, apache100.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, apache100.getHadoopVersion());
        assertTrue(apache100.doSupportKerberos());
        assertFalse(apache100.doSupportUseDatanodeHostname());
        assertFalse(apache100.doSupportGroup());
        assertFalse(((HDFSComponent) apache100).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) apache100).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) apache100).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) apache100).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) apache100).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) apache100).doSupportNewHBaseAPI());
        assertFalse(apache100 instanceof SqoopComponent);
        assertFalse(((PigComponent) apache100).doSupportHCatalog());
        assertFalse(((PigComponent) apache100).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) apache100).doSupportTez());
        assertTrue(((PigComponent) apache100).doSupportHBase());
        assertTrue(((HiveComponent) apache100).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) apache100).doSupportStandaloneMode());
        assertTrue(((HiveComponent) apache100).doSupportHive1());
        assertFalse(((HiveComponent) apache100).doSupportHive2());
        assertFalse(((HiveComponent) apache100).doSupportTezForHive());
        assertTrue(((HiveComponent) apache100).doSupportHBaseForHive());
        assertFalse(((HiveComponent) apache100).doSupportSSL());
        assertFalse(((HiveComponent) apache100).doSupportORCFormat());
        assertFalse(((HiveComponent) apache100).doSupportAvroFormat());
        assertFalse(((HiveComponent) apache100).doSupportParquetFormat());
        assertFalse(apache100 instanceof SparkBatchComponent);
        assertFalse(apache100 instanceof SparkStreamingComponent);
        assertFalse(apache100 instanceof HCatalogComponent);
        assertFalse(apache100 instanceof ImpalaComponent);
    }

    @Test
    public void testMAPR200() throws Exception {
        HadoopComponent mapr200 = DistributionFactory.buildDistribution(MAPR, MAPR200);
        assertNotNull(mapr200.getName());
        assertEquals(EHadoopDistributions.MAPR, mapr200.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr200.getHadoopVersion());
        assertFalse(mapr200.doSupportKerberos());
        assertFalse(mapr200.doSupportUseDatanodeHostname());
        assertFalse(((HDFSComponent) mapr200).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr200).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr200).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) mapr200).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) mapr200).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) mapr200).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) mapr200).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) mapr200).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) mapr200).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) mapr200).doSupportHCatalog());
        assertFalse(((PigComponent) mapr200).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) mapr200).doSupportTez());
        assertTrue(((PigComponent) mapr200).doSupportHBase());
        assertTrue(((HiveComponent) mapr200).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) mapr200).doSupportStandaloneMode());
        assertTrue(((HiveComponent) mapr200).doSupportHive1());
        assertFalse(((HiveComponent) mapr200).doSupportHive2());
        assertFalse(((HiveComponent) mapr200).doSupportTezForHive());
        assertFalse(((HiveComponent) mapr200).doSupportHBaseForHive());
        assertFalse(((HiveComponent) mapr200).doSupportSSL());
        assertFalse(((HiveComponent) mapr200).doSupportORCFormat());
        assertFalse(((HiveComponent) mapr200).doSupportAvroFormat());
        assertFalse(((HiveComponent) mapr200).doSupportParquetFormat());
        assertFalse(mapr200 instanceof SparkBatchComponent);
        assertFalse(mapr200 instanceof SparkStreamingComponent);
        assertFalse(mapr200 instanceof HCatalogComponent);
        assertFalse(mapr200 instanceof ImpalaComponent);
    }

    @Test
    public void testMAPR212() throws Exception {
        HadoopComponent mapr212 = DistributionFactory.buildDistribution(MAPR, MAPR212);
        assertNotNull(mapr212.getName());
        assertEquals(EHadoopDistributions.MAPR, mapr212.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr212.getHadoopVersion());
        assertFalse(mapr212.doSupportKerberos());
        assertFalse(mapr212.doSupportUseDatanodeHostname());
        assertTrue(mapr212.doSupportGroup());
        assertFalse(((HDFSComponent) mapr212).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr212).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr212).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) mapr212).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) mapr212).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) mapr212).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) mapr212).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) mapr212).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) mapr212).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) mapr212).doSupportHCatalog());
        assertFalse(((PigComponent) mapr212).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) mapr212).doSupportTez());
        assertTrue(((PigComponent) mapr212).doSupportHBase());
        assertTrue(((HiveComponent) mapr212).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) mapr212).doSupportStandaloneMode());
        assertTrue(((HiveComponent) mapr212).doSupportHive1());
        assertFalse(((HiveComponent) mapr212).doSupportHive2());
        assertFalse(((HiveComponent) mapr212).doSupportTezForHive());
        assertTrue(((HiveComponent) mapr212).doSupportHBaseForHive());
        assertFalse(((HiveComponent) mapr212).doSupportSSL());
        assertFalse(((HiveComponent) mapr212).doSupportORCFormat());
        assertTrue(((HiveComponent) mapr212).doSupportAvroFormat());
        assertTrue(((HiveComponent) mapr212).doSupportParquetFormat());
        assertFalse(mapr212 instanceof SparkBatchComponent);
        assertFalse(mapr212 instanceof SparkStreamingComponent);
        assertFalse(mapr212 instanceof HCatalogComponent);
        assertFalse(mapr212 instanceof ImpalaComponent);
    }

    @Test
    public void testMAPR213() throws Exception {
        HadoopComponent mapr213 = DistributionFactory.buildDistribution(MAPR, MAPR213);
        assertNotNull(mapr213.getName());
        assertEquals(EHadoopDistributions.MAPR, mapr213.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr213.getHadoopVersion());
        assertFalse(mapr213.doSupportKerberos());
        assertFalse(mapr213.doSupportUseDatanodeHostname());
        assertTrue(mapr213.doSupportGroup());
        assertFalse(((HDFSComponent) mapr213).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr213).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr213).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) mapr213).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) mapr213).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) mapr213).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) mapr213).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) mapr213).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) mapr213).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) mapr213).doSupportHCatalog());
        assertFalse(((PigComponent) mapr213).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) mapr213).doSupportTez());
        assertTrue(((PigComponent) mapr213).doSupportHBase());
        assertTrue(((HiveComponent) mapr213).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) mapr213).doSupportStandaloneMode());
        assertTrue(((HiveComponent) mapr213).doSupportHive1());
        assertTrue(((HiveComponent) mapr213).doSupportHive2());
        assertFalse(((HiveComponent) mapr213).doSupportTezForHive());
        assertTrue(((HiveComponent) mapr213).doSupportHBaseForHive());
        assertFalse(((HiveComponent) mapr213).doSupportSSL());
        assertTrue(((HiveComponent) mapr213).doSupportORCFormat());
        assertTrue(((HiveComponent) mapr213).doSupportAvroFormat());
        assertTrue(((HiveComponent) mapr213).doSupportParquetFormat());
        assertFalse(mapr213 instanceof SparkBatchComponent);
        assertFalse(mapr213 instanceof SparkStreamingComponent);
        assertFalse(mapr213 instanceof HCatalogComponent);
        assertFalse(mapr213 instanceof ImpalaComponent);
    }

    @Test
    public void testMAPR301() throws Exception {
        HadoopComponent mapr301 = DistributionFactory.buildDistribution(MAPR, MAPR301);
        assertNotNull(mapr301.getName());
        assertEquals(EHadoopDistributions.MAPR, mapr301.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr301.getHadoopVersion());
        assertFalse(mapr301.doSupportKerberos());
        assertFalse(mapr301.doSupportUseDatanodeHostname());
        assertTrue(mapr301.doSupportGroup());
        assertFalse(((HDFSComponent) mapr301).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr301).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr301).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) mapr301).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) mapr301).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) mapr301).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) mapr301).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) mapr301).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) mapr301).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) mapr301).doSupportHCatalog());
        assertFalse(((PigComponent) mapr301).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) mapr301).doSupportTez());
        assertTrue(((PigComponent) mapr301).doSupportHBase());
        assertTrue(((HiveComponent) mapr301).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) mapr301).doSupportStandaloneMode());
        assertTrue(((HiveComponent) mapr301).doSupportHive1());
        assertTrue(((HiveComponent) mapr301).doSupportHive2());
        assertFalse(((HiveComponent) mapr301).doSupportTezForHive());
        assertTrue(((HiveComponent) mapr301).doSupportHBaseForHive());
        assertFalse(((HiveComponent) mapr301).doSupportSSL());
        assertFalse(((HiveComponent) mapr301).doSupportORCFormat());
        assertFalse(((HiveComponent) mapr301).doSupportAvroFormat());
        assertTrue(((HiveComponent) mapr301).doSupportParquetFormat());
        assertFalse(mapr301 instanceof SparkBatchComponent);
        assertFalse(mapr301 instanceof SparkStreamingComponent);
        assertFalse(mapr301 instanceof HCatalogComponent);
        assertFalse(mapr301 instanceof ImpalaComponent);
    }

    @Test
    public void testMAPR310() throws Exception {
        HadoopComponent mapr310 = DistributionFactory.buildDistribution(MAPR, MAPR310);
        assertNotNull(mapr310.getName());
        assertEquals(EHadoopDistributions.MAPR, mapr310.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr310.getHadoopVersion());
        assertFalse(mapr310.doSupportKerberos());
        assertFalse(mapr310.doSupportUseDatanodeHostname());
        assertTrue(mapr310.doSupportGroup());
        assertFalse(((HDFSComponent) mapr310).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr310).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr310).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) mapr310).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) mapr310).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) mapr310).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) mapr310).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) mapr310).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) mapr310).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) mapr310).doSupportHCatalog());
        assertTrue(((PigComponent) mapr310).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) mapr310).doSupportTez());
        assertTrue(((PigComponent) mapr310).doSupportHBase());
        assertTrue(((HiveComponent) mapr310).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) mapr310).doSupportStandaloneMode());
        assertTrue(((HiveComponent) mapr310).doSupportHive1());
        assertTrue(((HiveComponent) mapr310).doSupportHive2());
        assertFalse(((HiveComponent) mapr310).doSupportTezForHive());
        assertTrue(((HiveComponent) mapr310).doSupportHBaseForHive());
        assertFalse(((HiveComponent) mapr310).doSupportSSL());
        assertFalse(((HiveComponent) mapr310).doSupportORCFormat());
        assertFalse(((HiveComponent) mapr310).doSupportAvroFormat());
        assertTrue(((HiveComponent) mapr310).doSupportParquetFormat());
        assertFalse(mapr310 instanceof SparkBatchComponent);
        assertFalse(mapr310 instanceof SparkStreamingComponent);
        assertTrue(mapr310 instanceof HCatalogComponent);
        assertFalse(mapr310 instanceof ImpalaComponent);
    }

    @Test
    public void testMAPR401() throws Exception {
        HadoopComponent mapr401 = DistributionFactory.buildDistribution(MAPR, MAPR401);
        assertNotNull(mapr401.getName());
        assertEquals(EHadoopDistributions.MAPR, mapr401.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, mapr401.getHadoopVersion());
        assertFalse(mapr401.doSupportKerberos());
        assertFalse(mapr401.doSupportUseDatanodeHostname());
        assertTrue(mapr401.doSupportGroup());
        assertTrue(((HDFSComponent) mapr401).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr401).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) mapr401).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) mapr401).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) mapr401).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) mapr401).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) mapr401).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) mapr401).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) mapr401).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) mapr401).doSupportHCatalog());
        assertFalse(((PigComponent) mapr401).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) mapr401).doSupportTez());
        assertTrue(((PigComponent) mapr401).doSupportHBase());
        assertTrue(((HiveComponent) mapr401).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) mapr401).doSupportStandaloneMode());
        assertTrue(((HiveComponent) mapr401).doSupportHive1());
        assertTrue(((HiveComponent) mapr401).doSupportHive2());
        assertTrue(((HiveComponent) mapr401).doSupportTezForHive());
        assertTrue(((HiveComponent) mapr401).doSupportHBaseForHive());
        assertFalse(((HiveComponent) mapr401).doSupportSSL());
        assertTrue(((HiveComponent) mapr401).doSupportORCFormat());
        assertTrue(((HiveComponent) mapr401).doSupportAvroFormat());
        assertTrue(((HiveComponent) mapr401).doSupportParquetFormat());
        assertFalse(mapr401 instanceof SparkBatchComponent);
        assertFalse(mapr401 instanceof SparkStreamingComponent);
        assertTrue(mapr401 instanceof HCatalogComponent);
        assertFalse(mapr401 instanceof ImpalaComponent);
    }

    @Test
    public void testMAPR410() throws Exception {
        HadoopComponent mapr410 = DistributionFactory.buildDistribution(MAPR, MAPR410);
        assertNotNull(mapr410.getName());
        assertEquals(EHadoopDistributions.MAPR, mapr410.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, mapr410.getHadoopVersion());
        assertFalse(mapr410.doSupportKerberos());
        assertFalse(mapr410.doSupportUseDatanodeHostname());
        assertTrue(mapr410.doSupportGroup());
        assertTrue(((HDFSComponent) mapr410).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr410).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) mapr410).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) mapr410).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) mapr410).getYarnApplicationClasspath());
        assertTrue(((HBaseComponent) mapr410).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) mapr410).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) mapr410).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) mapr410).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) mapr410).doSupportHCatalog());
        assertFalse(((PigComponent) mapr410).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) mapr410).doSupportTez());
        assertTrue(((PigComponent) mapr410).doSupportHBase());
        assertTrue(((HiveComponent) mapr410).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) mapr410).doSupportStandaloneMode());
        assertTrue(((HiveComponent) mapr410).doSupportHive1());
        assertTrue(((HiveComponent) mapr410).doSupportHive2());
        assertTrue(((HiveComponent) mapr410).doSupportTezForHive());
        assertTrue(((HiveComponent) mapr410).doSupportHBaseForHive());
        assertFalse(((HiveComponent) mapr410).doSupportSSL());
        assertTrue(((HiveComponent) mapr410).doSupportORCFormat());
        assertTrue(((HiveComponent) mapr410).doSupportAvroFormat());
        assertTrue(((HiveComponent) mapr410).doSupportParquetFormat());
        assertFalse(((SparkBatchComponent) mapr410).isSpark14());
        assertFalse(((SparkBatchComponent) mapr410).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkBatchComponent) mapr410).isExecutedThroughSparkJobServer());
        assertFalse(mapr410 instanceof SparkStreamingComponent);
        assertTrue(mapr410 instanceof HCatalogComponent);
        assertFalse(mapr410 instanceof ImpalaComponent);
    }

    @Test
    public void testEMR103() throws Exception {
        HadoopComponent emr103 = DistributionFactory.buildDistribution(AMAZON_EMR, EMRAPACHE103);
        assertNotNull(emr103.getName());
        assertEquals(EHadoopDistributions.AMAZON_EMR, emr103.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, emr103.getHadoopVersion());
        assertTrue(emr103.doSupportKerberos());
        assertFalse(emr103.doSupportUseDatanodeHostname());
        assertFalse(emr103.doSupportGroup());
        assertFalse(((HDFSComponent) emr103).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) emr103).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) emr103).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) emr103).doSupportImpersonation());
        assertEquals(EMPTY, ((MRComponent) emr103).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) emr103).doSupportNewHBaseAPI());
        assertFalse(emr103 instanceof SqoopComponent);
        assertFalse(((PigComponent) emr103).doSupportHCatalog());
        assertFalse(((PigComponent) emr103).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) emr103).doSupportTez());
        assertTrue(((PigComponent) emr103).doSupportHBase());
        assertTrue(((HiveComponent) emr103).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) emr103).doSupportStandaloneMode());
        assertTrue(((HiveComponent) emr103).doSupportHive1());
        assertFalse(((HiveComponent) emr103).doSupportHive2());
        assertFalse(((HiveComponent) emr103).doSupportTezForHive());
        assertTrue(((HiveComponent) emr103).doSupportHBaseForHive());
        assertFalse(((HiveComponent) emr103).doSupportSSL());
        assertFalse(((HiveComponent) emr103).doSupportORCFormat());
        assertFalse(((HiveComponent) emr103).doSupportAvroFormat());
        assertTrue(((HiveComponent) emr103).doSupportParquetFormat());
        assertFalse(emr103 instanceof SparkBatchComponent);
        assertFalse(emr103 instanceof SparkStreamingComponent);
        assertFalse(emr103 instanceof HCatalogComponent);
        assertFalse(emr103 instanceof ImpalaComponent);
    }

    @Test
    public void testEMR240() throws Exception {
        HadoopComponent emr240 = DistributionFactory.buildDistribution(AMAZON_EMR, EMRAPACHE240);
        assertNotNull(emr240.getName());
        assertEquals(EHadoopDistributions.AMAZON_EMR, emr240.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, emr240.getHadoopVersion());
        assertFalse(emr240.doSupportKerberos());
        assertTrue(emr240.doSupportUseDatanodeHostname());
        assertFalse(emr240.doSupportGroup());
        assertTrue(((HDFSComponent) emr240).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) emr240).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) emr240).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) emr240).doSupportImpersonation());
        assertFalse(((HBaseComponent) emr240).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) emr240).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) emr240).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) emr240).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) emr240).doSupportHCatalog());
        assertFalse(((PigComponent) emr240).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) emr240).doSupportTez());
        assertTrue(((PigComponent) emr240).doSupportHBase());
        assertTrue(((HiveComponent) emr240).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) emr240).doSupportStandaloneMode());
        assertFalse(((HiveComponent) emr240).doSupportHive1());
        assertTrue(((HiveComponent) emr240).doSupportHive2());
        assertFalse(((HiveComponent) emr240).doSupportTezForHive());
        assertTrue(((HiveComponent) emr240).doSupportHBaseForHive());
        assertFalse(((HiveComponent) emr240).doSupportSSL());
        assertFalse(((HiveComponent) emr240).doSupportORCFormat());
        assertFalse(((HiveComponent) emr240).doSupportAvroFormat());
        assertTrue(((HiveComponent) emr240).doSupportParquetFormat());
        assertFalse(emr240 instanceof SparkBatchComponent);
        assertFalse(emr240 instanceof SparkStreamingComponent);
        assertFalse(emr240 instanceof HCatalogComponent);
        assertFalse(emr240 instanceof ImpalaComponent);
    }

    @Test
    public void testEMR240_HIVE0131() throws Exception {
        HadoopComponent emr240hive0131 = DistributionFactory.buildDistribution(AMAZON_EMR, EMRAPACHE240_HIVE0131);
        assertNotNull(emr240hive0131.getName());
        assertEquals(EHadoopDistributions.AMAZON_EMR, emr240hive0131.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, emr240hive0131.getHadoopVersion());
        assertFalse(emr240hive0131.doSupportKerberos());
        assertTrue(emr240hive0131.doSupportUseDatanodeHostname());
        assertFalse(emr240hive0131.doSupportGroup());
        assertFalse(emr240hive0131 instanceof HDFSComponent);
        assertFalse(((MRComponent) emr240hive0131).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) emr240hive0131).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) emr240hive0131).doSupportImpersonation());
        assertFalse(emr240hive0131 instanceof HBaseComponent);
        assertFalse(emr240hive0131 instanceof SqoopComponent);
        assertFalse(emr240hive0131 instanceof PigComponent);
        assertFalse(((HiveComponent) emr240hive0131).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) emr240hive0131).doSupportStandaloneMode());
        assertFalse(((HiveComponent) emr240hive0131).doSupportHive1());
        assertTrue(((HiveComponent) emr240hive0131).doSupportHive2());
        assertFalse(((HiveComponent) emr240hive0131).doSupportTezForHive());
        assertTrue(((HiveComponent) emr240hive0131).doSupportHBaseForHive());
        assertFalse(((HiveComponent) emr240hive0131).doSupportSSL());
        assertFalse(((HiveComponent) emr240hive0131).doSupportORCFormat());
        assertFalse(((HiveComponent) emr240hive0131).doSupportAvroFormat());
        assertTrue(((HiveComponent) emr240hive0131).doSupportParquetFormat());
        assertFalse(emr240hive0131 instanceof SparkBatchComponent);
        assertFalse(emr240hive0131 instanceof SparkStreamingComponent);
        assertFalse(emr240hive0131 instanceof HCatalogComponent);
        assertFalse(emr240hive0131 instanceof ImpalaComponent);
    }

    @Test
    public void testEMR400() throws Exception {
        HadoopComponent emr400 = DistributionFactory.buildDistribution(AMAZON_EMR, EMR400);
        assertNotNull(emr400.getName());
        assertEquals(EHadoopDistributions.AMAZON_EMR, emr400.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, emr400.getHadoopVersion());
        assertFalse(emr400.doSupportKerberos());
        assertTrue(emr400.doSupportUseDatanodeHostname());
        assertFalse(emr400.doSupportGroup());
        assertTrue(((HDFSComponent) emr400).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) emr400).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) emr400).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) emr400).doSupportImpersonation());
        assertFalse(emr400 instanceof HBaseComponent);
        assertFalse(emr400 instanceof SqoopComponent);
        assertFalse(((PigComponent) emr400).doSupportHCatalog());
        assertFalse(((PigComponent) emr400).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) emr400).doSupportTez());
        assertFalse(((PigComponent) emr400).doSupportHBase());
        assertFalse(((HiveComponent) emr400).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) emr400).doSupportStandaloneMode());
        assertFalse(((HiveComponent) emr400).doSupportHive1());
        assertTrue(((HiveComponent) emr400).doSupportHive2());
        assertFalse(((HiveComponent) emr400).doSupportTezForHive());
        assertFalse(((HiveComponent) emr400).doSupportHBaseForHive());
        assertTrue(((HiveComponent) emr400).doSupportSSL());
        assertTrue(((HiveComponent) emr400).doSupportORCFormat());
        assertTrue(((HiveComponent) emr400).doSupportAvroFormat());
        assertTrue(((HiveComponent) emr400).doSupportParquetFormat());
        assertTrue(((SparkBatchComponent) emr400).isSpark14());
        assertTrue(((SparkBatchComponent) emr400).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkBatchComponent) emr400).isExecutedThroughSparkJobServer());
        assertTrue(((SparkStreamingComponent) emr400).isSpark14());
        assertTrue(((SparkStreamingComponent) emr400).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkStreamingComponent) emr400).isExecutedThroughSparkJobServer());
        assertFalse(emr400 instanceof HCatalogComponent);
        assertFalse(emr400 instanceof ImpalaComponent);
    }

    @Test
    public void testPIV101() throws Exception {
        HadoopComponent piv101 = DistributionFactory.buildDistribution(PIVOTAL_HD, PIV101);
        assertNotNull(piv101.getName());
        assertEquals(EHadoopDistributions.PIVOTAL_HD, piv101.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, piv101.getHadoopVersion());
        assertFalse(piv101.doSupportKerberos());
        assertTrue(piv101.doSupportUseDatanodeHostname());
        assertFalse(piv101.doSupportGroup());
        assertTrue(((HDFSComponent) piv101).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) piv101).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) piv101).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) piv101).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) piv101).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) piv101).doSupportNewHBaseAPI());
        assertFalse(((SqoopComponent) piv101).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) piv101).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) piv101).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) piv101).doSupportHCatalog());
        assertFalse(((PigComponent) piv101).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) piv101).doSupportTez());
        assertTrue(((PigComponent) piv101).doSupportHBase());
        assertTrue(((HiveComponent) piv101).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) piv101).doSupportStandaloneMode());
        assertTrue(((HiveComponent) piv101).doSupportHive1());
        assertFalse(((HiveComponent) piv101).doSupportHive2());
        assertFalse(((HiveComponent) piv101).doSupportTezForHive());
        assertTrue(((HiveComponent) piv101).doSupportHBaseForHive());
        assertFalse(((HiveComponent) piv101).doSupportSSL());
        assertFalse(((HiveComponent) piv101).doSupportORCFormat());
        assertFalse(((HiveComponent) piv101).doSupportAvroFormat());
        assertFalse(((HiveComponent) piv101).doSupportParquetFormat());
        assertFalse(piv101 instanceof SparkBatchComponent);
        assertFalse(piv101 instanceof SparkStreamingComponent);
        assertFalse(piv101 instanceof HCatalogComponent);
        assertFalse(piv101 instanceof ImpalaComponent);
    }

    @Test
    public void testPIV200() throws Exception {
        HadoopComponent piv200 = DistributionFactory.buildDistribution(PIVOTAL_HD, PIV200);
        assertNotNull(piv200.getName());
        assertEquals(EHadoopDistributions.PIVOTAL_HD, piv200.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, piv200.getHadoopVersion());
        assertTrue(piv200.doSupportKerberos());
        assertTrue(piv200.doSupportUseDatanodeHostname());
        assertFalse(piv200.doSupportGroup());
        assertTrue(((HDFSComponent) piv200).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) piv200).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) piv200).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) piv200).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) piv200).getYarnApplicationClasspath());
        assertTrue(((HBaseComponent) piv200).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) piv200).doJavaAPISupportStorePasswordInFile());
        assertTrue(((SqoopComponent) piv200).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) piv200).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((PigComponent) piv200).doSupportHCatalog());
        assertFalse(((PigComponent) piv200).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) piv200).doSupportTez());
        assertFalse(((PigComponent) piv200).doSupportHBase());
        assertTrue(((HiveComponent) piv200).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) piv200).doSupportStandaloneMode());
        assertTrue(((HiveComponent) piv200).doSupportHive1());
        assertTrue(((HiveComponent) piv200).doSupportHive2());
        assertFalse(((HiveComponent) piv200).doSupportTezForHive());
        assertTrue(((HiveComponent) piv200).doSupportHBaseForHive());
        assertTrue(((HiveComponent) piv200).doSupportSSL());
        assertTrue(((HiveComponent) piv200).doSupportORCFormat());
        assertTrue(((HiveComponent) piv200).doSupportAvroFormat());
        assertTrue(((HiveComponent) piv200).doSupportParquetFormat());
        assertFalse(piv200 instanceof SparkBatchComponent);
        assertFalse(piv200 instanceof SparkStreamingComponent);
        assertFalse(piv200 instanceof HCatalogComponent);
        assertFalse(piv200 instanceof ImpalaComponent);
    }

    @Test
    public void testHDINSIGHT310() throws Exception {
        HadoopComponent hdinsight310 = DistributionFactory.buildDistribution(HDINSIGHT, HDINSIGHT310);
        assertNotNull(hdinsight310.getName());
        assertEquals(EHadoopDistributions.MICROSOFT_HD_INSIGHT, hdinsight310.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdinsight310.getHadoopVersion());
        assertFalse(hdinsight310.doSupportKerberos());
        assertFalse(hdinsight310.doSupportUseDatanodeHostname());
        assertFalse(hdinsight310.doSupportGroup());
        assertFalse(hdinsight310 instanceof HDFSComponent);
        assertTrue(((MRComponent) hdinsight310).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdinsight310).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) hdinsight310).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) hdinsight310).getYarnApplicationClasspath());
        assertFalse(hdinsight310 instanceof HBaseComponent);
        assertFalse(hdinsight310 instanceof SqoopComponent);
        assertTrue(((PigComponent) hdinsight310).doSupportHCatalog());
        assertFalse(((PigComponent) hdinsight310).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) hdinsight310).doSupportTez());
        assertFalse(((PigComponent) hdinsight310).doSupportHBase());
        assertFalse(((HiveComponent) hdinsight310).doSupportEmbeddedMode());
        assertFalse(((HiveComponent) hdinsight310).doSupportStandaloneMode());
        assertFalse(((HiveComponent) hdinsight310).doSupportHive1());
        assertFalse(((HiveComponent) hdinsight310).doSupportHive2());
        assertFalse(((HiveComponent) hdinsight310).doSupportTezForHive());
        assertFalse(((HiveComponent) hdinsight310).doSupportHBaseForHive());
        assertFalse(((HiveComponent) hdinsight310).doSupportSSL());
        assertTrue(((HiveComponent) hdinsight310).doSupportORCFormat());
        assertTrue(((HiveComponent) hdinsight310).doSupportAvroFormat());
        assertTrue(((HiveComponent) hdinsight310).doSupportParquetFormat());
        assertFalse(hdinsight310 instanceof SparkBatchComponent);
        assertFalse(hdinsight310 instanceof SparkStreamingComponent);
        assertFalse(hdinsight310 instanceof HCatalogComponent);
        assertFalse(hdinsight310 instanceof ImpalaComponent);
    }

    @Test
    public void testHDINSIGHT320() throws Exception {
        HadoopComponent hdinsight320 = DistributionFactory.buildDistribution(HDINSIGHT, HDINSIGHT320);
        assertNotNull(hdinsight320.getName());
        assertEquals(EHadoopDistributions.MICROSOFT_HD_INSIGHT, hdinsight320.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdinsight320.getHadoopVersion());
        assertFalse(hdinsight320.doSupportKerberos());
        assertFalse(hdinsight320.doSupportUseDatanodeHostname());
        assertFalse(hdinsight320.doSupportGroup());
        assertFalse(hdinsight320 instanceof HDFSComponent);
        assertTrue(((MRComponent) hdinsight320).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdinsight320).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) hdinsight320).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) hdinsight320).getYarnApplicationClasspath());
        assertFalse(hdinsight320 instanceof HBaseComponent);
        assertFalse(hdinsight320 instanceof SqoopComponent);
        assertTrue(((PigComponent) hdinsight320).doSupportHCatalog());
        assertFalse(((PigComponent) hdinsight320).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) hdinsight320).doSupportTez());
        assertFalse(((PigComponent) hdinsight320).doSupportHBase());
        assertFalse(((HiveComponent) hdinsight320).doSupportEmbeddedMode());
        assertFalse(((HiveComponent) hdinsight320).doSupportStandaloneMode());
        assertFalse(((HiveComponent) hdinsight320).doSupportHive1());
        assertFalse(((HiveComponent) hdinsight320).doSupportHive2());
        assertFalse(((HiveComponent) hdinsight320).doSupportTezForHive());
        assertFalse(((HiveComponent) hdinsight320).doSupportHBaseForHive());
        assertFalse(((HiveComponent) hdinsight320).doSupportSSL());
        assertTrue(((HiveComponent) hdinsight320).doSupportORCFormat());
        assertTrue(((HiveComponent) hdinsight320).doSupportAvroFormat());
        assertTrue(((HiveComponent) hdinsight320).doSupportParquetFormat());
        assertFalse(((SparkBatchComponent) hdinsight320).isSpark14());
        assertFalse(((SparkBatchComponent) hdinsight320).doSupportDynamicMemoryAllocation());
        assertTrue(((SparkBatchComponent) hdinsight320).isExecutedThroughSparkJobServer());
        assertFalse(hdinsight320 instanceof SparkStreamingComponent);
        assertFalse(hdinsight320 instanceof HCatalogComponent);
        assertFalse(hdinsight320 instanceof ImpalaComponent);
    }

    @Test
    public void testCUSTOM() throws Exception {
        HadoopComponent custom = DistributionFactory.buildDistribution(CUSTOM, PIV200);
        assertNotNull(custom.getName());
        assertEquals(EHadoopDistributions.CUSTOM, custom.getDistribution());
        assertNull(custom.getHadoopVersion());
        assertFalse(custom.doSupportKerberos());
        assertTrue(custom.doSupportUseDatanodeHostname());
        assertFalse(custom.doSupportGroup());
        assertTrue(((HDFSComponent) custom).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) custom).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) custom).doSupportCrossPlatformSubmission());
        assertTrue(((MRComponent) custom).doSupportImpersonation());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) custom).getYarnApplicationClasspath());
        assertFalse(((HBaseComponent) custom).doSupportNewHBaseAPI());
        assertTrue(((SqoopComponent) custom).doJavaAPISupportStorePasswordInFile());
        assertFalse(((SqoopComponent) custom).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertTrue(((SqoopComponent) custom).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertTrue(((PigComponent) custom).doSupportHCatalog());
        assertFalse(((PigComponent) custom).pigVersionPriorTo_0_12());
        assertFalse(((PigComponent) custom).doSupportTez());
        assertTrue(((PigComponent) custom).doSupportHBase());
        assertTrue(((HiveComponent) custom).doSupportEmbeddedMode());
        assertTrue(((HiveComponent) custom).doSupportStandaloneMode());
        assertTrue(((HiveComponent) custom).doSupportHive1());
        assertTrue(((HiveComponent) custom).doSupportHive2());
        assertTrue(((HiveComponent) custom).doSupportTezForHive());
        assertTrue(((HiveComponent) custom).doSupportHBaseForHive());
        assertTrue(((HiveComponent) custom).doSupportSSL());
        assertTrue(((HiveComponent) custom).doSupportORCFormat());
        assertTrue(((HiveComponent) custom).doSupportAvroFormat());
        assertTrue(((HiveComponent) custom).doSupportParquetFormat());
        assertFalse(((SparkBatchComponent) custom).isSpark14());
        assertTrue(((SparkBatchComponent) custom).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkBatchComponent) custom).isExecutedThroughSparkJobServer());
        assertFalse(((SparkStreamingComponent) custom).isSpark14());
        assertTrue(((SparkStreamingComponent) custom).doSupportDynamicMemoryAllocation());
        assertFalse(((SparkStreamingComponent) custom).isExecutedThroughSparkJobServer());
        assertFalse(custom instanceof HCatalogComponent);
        assertFalse(custom instanceof ImpalaComponent);
    }

    @Test
    public void testUnexistingDistribution() {
        try {
            DistributionFactory.buildDistribution(EMPTY, "DOESNOTEXIST"); //$NON-NLS-1$
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
