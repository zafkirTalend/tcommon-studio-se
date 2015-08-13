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
import org.talend.core.hadoop.api.components.HDFSComponent;
import org.talend.core.hadoop.api.components.HadoopComponent;
import org.talend.core.hadoop.api.components.MRComponent;
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

    private static final String EMR400 = "EMR_4_0_0"; //$NON-NLS-1$

    private static final String PIV101 = "PIVOTAL_HD_1_0_1"; //$NON-NLS-1$

    private static final String PIV200 = "PIVOTAL_HD_2_0"; //$NON-NLS-1$

    private static final String HDINSIGHT310 = "MICROSOFT_HD_INSIGHT_3_1"; //$NON-NLS-1$

    private final static String DEFAULT_YARN_APPLICATION_CLASSPATH = "$HADOOP_CONF_DIR,$HADOOP_COMMON_HOME/*,$HADOOP_COMMON_HOME/lib/*,$HADOOP_HDFS_HOME/*,$HADOOP_HDFS_HOME/lib/*,$HADOOP_MAPRED_HOME/*,$HADOOP_MAPRED_HOME/lib/*,$YARN_HOME/*,$YARN_HOME/lib/*,$HADOOP_YARN_HOME/*,$HADOOP_YARN_HOME/lib/*,$HADOOP_COMMON_HOME/share/hadoop/common/*,$HADOOP_COMMON_HOME/share/hadoop/common/lib/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/lib/*,$HADOOP_YARN_HOME/share/hadoop/yarn/*,$HADOOP_YARN_HOME/share/hadoop/yarn/lib/*"; //$NON-NLS-1$

    @Test
    public void testCDH400MR1() throws Exception {
        HadoopComponent cdh400mr1 = DistributionFactory.buildDistribution(CLOUDERA, CDH400MR1);
        assertEquals(EHadoopDistributions.CLOUDERA, cdh400mr1.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, cdh400mr1.getHadoopVersion());
        assertTrue(cdh400mr1.doSupportKerberos());
        assertFalse(cdh400mr1.doSupportUseDatanodeHostname());
        assertFalse(cdh400mr1.doSupportGroup());
        assertTrue(((HDFSComponent) cdh400mr1).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh400mr1).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) cdh400mr1).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) cdh400mr1).getYarnApplicationClasspath());
    }

    @Test
    public void testCDH400MR2() throws Exception {
        HadoopComponent cdh400mr2 = DistributionFactory.buildDistribution(CLOUDERA, CDH400MR2);
        assertEquals(EHadoopDistributions.CLOUDERA, cdh400mr2.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, cdh400mr2.getHadoopVersion());
        assertTrue(cdh400mr2.doSupportKerberos());
        assertTrue(cdh400mr2.doSupportUseDatanodeHostname());
        assertFalse(cdh400mr2.doSupportGroup());
        assertTrue(((HDFSComponent) cdh400mr2).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh400mr2).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) cdh400mr2).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) cdh400mr2).getYarnApplicationClasspath());
    }

    @Test
    public void testCDH500() throws Exception {
        HadoopComponent cdh500 = DistributionFactory.buildDistribution(CLOUDERA, CDH500);
        assertEquals(EHadoopDistributions.CLOUDERA, cdh500.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, cdh500.getHadoopVersion());
        assertTrue(cdh500.doSupportKerberos());
        assertTrue(cdh500.doSupportUseDatanodeHostname());
        assertFalse(cdh500.doSupportGroup());
        assertTrue(((HDFSComponent) cdh500).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh500).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) cdh500).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) cdh500).getYarnApplicationClasspath());
    }

    @Test
    public void testCDH510MR1() throws Exception {
        HadoopComponent cdh510mr1 = DistributionFactory.buildDistribution(CLOUDERA, CDH510MR1);
        assertEquals(EHadoopDistributions.CLOUDERA, cdh510mr1.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, cdh510mr1.getHadoopVersion());
        assertTrue(cdh510mr1.doSupportKerberos());
        assertFalse(cdh510mr1.doSupportUseDatanodeHostname());
        assertFalse(cdh510mr1.doSupportGroup());
        assertTrue(((HDFSComponent) cdh510mr1).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh510mr1).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) cdh510mr1).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) cdh510mr1).getYarnApplicationClasspath());
    }

    @Test
    public void testCDH510MR2() throws Exception {
        HadoopComponent cdh510mr2 = DistributionFactory.buildDistribution(CLOUDERA, CDH510MR2);
        assertEquals(EHadoopDistributions.CLOUDERA, cdh510mr2.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, cdh510mr2.getHadoopVersion());
        assertTrue(cdh510mr2.doSupportKerberos());
        assertTrue(cdh510mr2.doSupportUseDatanodeHostname());
        assertFalse(cdh510mr2.doSupportGroup());
        assertTrue(((HDFSComponent) cdh510mr2).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh510mr2).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) cdh510mr2).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) cdh510mr2).getYarnApplicationClasspath());
    }

    @Test
    public void testCDH540() throws Exception {
        HadoopComponent cdh540 = DistributionFactory.buildDistribution(CLOUDERA, CDH540);
        assertEquals(EHadoopDistributions.CLOUDERA, cdh540.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, cdh540.getHadoopVersion());
        assertTrue(cdh540.doSupportKerberos());
        assertTrue(cdh540.doSupportUseDatanodeHostname());
        assertFalse(cdh540.doSupportGroup());
        assertTrue(((HDFSComponent) cdh540).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) cdh540).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) cdh540).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) cdh540).getYarnApplicationClasspath());
    }

    @Test
    public void testHDP120() throws Exception {
        HadoopComponent hdp120 = DistributionFactory.buildDistribution(HORTONWORKS, HDP120);
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp120.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, hdp120.getHadoopVersion());
        assertTrue(hdp120.doSupportKerberos());
        assertTrue(hdp120.doSupportUseDatanodeHostname());
        assertFalse(hdp120.doSupportGroup());
        assertFalse(((HDFSComponent) hdp120).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp120).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdp120).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) hdp120).getYarnApplicationClasspath());
    }

    @Test
    public void testHDP130() throws Exception {
        HadoopComponent hdp130 = DistributionFactory.buildDistribution(HORTONWORKS, HDP130);
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp130.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, hdp130.getHadoopVersion());
        assertTrue(hdp130.doSupportKerberos());
        assertTrue(hdp130.doSupportUseDatanodeHostname());
        assertFalse(hdp130.doSupportGroup());
        assertFalse(((HDFSComponent) hdp130).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp130).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdp130).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) hdp130).getYarnApplicationClasspath());
    }

    @Test
    public void testHDP200() throws Exception {
        HadoopComponent hdp200 = DistributionFactory.buildDistribution(HORTONWORKS, HDP200);
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp200.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdp200.getHadoopVersion());
        assertTrue(hdp200.doSupportKerberos());
        assertTrue(hdp200.doSupportUseDatanodeHostname());
        assertFalse(hdp200.doSupportGroup());
        assertTrue(((HDFSComponent) hdp200).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp200).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdp200).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) hdp200).getYarnApplicationClasspath());
    }

    @Test
    public void testHDP210() throws Exception {
        HadoopComponent hdp210 = DistributionFactory.buildDistribution(HORTONWORKS, HDP210);
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp210.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdp210.getHadoopVersion());
        assertTrue(hdp210.doSupportKerberos());
        assertTrue(hdp210.doSupportUseDatanodeHostname());
        assertFalse(hdp210.doSupportGroup());
        assertTrue(((HDFSComponent) hdp210).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp210).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) hdp210).doSupportCrossPlatformSubmission());
    }

    @Test
    public void testHDP220() throws Exception {
        HadoopComponent hdp220 = DistributionFactory.buildDistribution(HORTONWORKS, HDP220);
        assertEquals(EHadoopDistributions.HORTONWORKS, hdp220.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdp220.getHadoopVersion());
        assertTrue(hdp220.doSupportKerberos());
        assertTrue(hdp220.doSupportUseDatanodeHostname());
        assertFalse(hdp220.doSupportGroup());
        assertTrue(((HDFSComponent) hdp220).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) hdp220).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) hdp220).doSupportCrossPlatformSubmission());
    }

    @Test
    public void testAPACHE100() throws Exception {
        HadoopComponent apache100 = DistributionFactory.buildDistribution(APACHE, APACHE100);
        assertEquals(EHadoopDistributions.APACHE, apache100.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, apache100.getHadoopVersion());
        assertTrue(apache100.doSupportKerberos());
        assertFalse(apache100.doSupportUseDatanodeHostname());
        assertFalse(apache100.doSupportGroup());
        assertFalse(((HDFSComponent) apache100).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) apache100).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) apache100).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) apache100).getYarnApplicationClasspath());
    }

    @Test
    public void testMAPR200() throws Exception {
        HadoopComponent mapr200 = DistributionFactory.buildDistribution(MAPR, MAPR200);
        assertEquals(EHadoopDistributions.MAPR, mapr200.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr200.getHadoopVersion());
        assertFalse(mapr200.doSupportKerberos());
        assertFalse(mapr200.doSupportUseDatanodeHostname());
        assertFalse(((HDFSComponent) mapr200).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr200).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr200).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) mapr200).getYarnApplicationClasspath());
    }

    @Test
    public void testMAPR212() throws Exception {
        HadoopComponent mapr212 = DistributionFactory.buildDistribution(MAPR, MAPR212);
        assertEquals(EHadoopDistributions.MAPR, mapr212.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr212.getHadoopVersion());
        assertFalse(mapr212.doSupportKerberos());
        assertFalse(mapr212.doSupportUseDatanodeHostname());
        assertTrue(mapr212.doSupportGroup());
        assertFalse(((HDFSComponent) mapr212).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr212).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr212).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) mapr212).getYarnApplicationClasspath());
    }

    @Test
    public void testMAPR213() throws Exception {
        HadoopComponent mapr213 = DistributionFactory.buildDistribution(MAPR, MAPR213);
        assertEquals(EHadoopDistributions.MAPR, mapr213.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr213.getHadoopVersion());
        assertFalse(mapr213.doSupportKerberos());
        assertFalse(mapr213.doSupportUseDatanodeHostname());
        assertTrue(mapr213.doSupportGroup());
        assertFalse(((HDFSComponent) mapr213).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr213).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr213).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) mapr213).getYarnApplicationClasspath());
    }

    @Test
    public void testMAPR301() throws Exception {
        HadoopComponent mapr301 = DistributionFactory.buildDistribution(MAPR, MAPR301);
        assertEquals(EHadoopDistributions.MAPR, mapr301.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr301.getHadoopVersion());
        assertFalse(mapr301.doSupportKerberos());
        assertFalse(mapr301.doSupportUseDatanodeHostname());
        assertTrue(mapr301.doSupportGroup());
        assertFalse(((HDFSComponent) mapr301).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr301).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr301).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) mapr301).getYarnApplicationClasspath());
    }

    @Test
    public void testMAPR310() throws Exception {
        HadoopComponent mapr310 = DistributionFactory.buildDistribution(MAPR, MAPR310);
        assertEquals(EHadoopDistributions.MAPR, mapr310.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, mapr310.getHadoopVersion());
        assertFalse(mapr310.doSupportKerberos());
        assertFalse(mapr310.doSupportUseDatanodeHostname());
        assertTrue(mapr310.doSupportGroup());
        assertFalse(((HDFSComponent) mapr310).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr310).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) mapr310).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) mapr310).getYarnApplicationClasspath());
    }

    @Test
    public void testMAPR401() throws Exception {
        HadoopComponent mapr401 = DistributionFactory.buildDistribution(MAPR, MAPR401);
        assertEquals(EHadoopDistributions.MAPR, mapr401.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, mapr401.getHadoopVersion());
        assertFalse(mapr401.doSupportKerberos());
        assertFalse(mapr401.doSupportUseDatanodeHostname());
        assertTrue(mapr401.doSupportGroup());
        assertTrue(((HDFSComponent) mapr401).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr401).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) mapr401).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) mapr401).getYarnApplicationClasspath());
    }

    @Test
    public void testMAPR410() throws Exception {
        HadoopComponent mapr410 = DistributionFactory.buildDistribution(MAPR, MAPR410);
        assertEquals(EHadoopDistributions.MAPR, mapr410.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, mapr410.getHadoopVersion());
        assertFalse(mapr410.doSupportKerberos());
        assertFalse(mapr410.doSupportUseDatanodeHostname());
        assertTrue(mapr410.doSupportGroup());
        assertTrue(((HDFSComponent) mapr410).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) mapr410).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) mapr410).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) mapr410).getYarnApplicationClasspath());
    }

    @Test
    public void testEMR103() throws Exception {
        HadoopComponent emr103 = DistributionFactory.buildDistribution(AMAZON_EMR, EMRAPACHE103);
        assertEquals(EHadoopDistributions.AMAZON_EMR, emr103.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_1, emr103.getHadoopVersion());
        assertTrue(emr103.doSupportKerberos());
        assertFalse(emr103.doSupportUseDatanodeHostname());
        assertFalse(emr103.doSupportGroup());
        assertFalse(((HDFSComponent) emr103).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) emr103).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) emr103).doSupportCrossPlatformSubmission());
        assertEquals(EMPTY, ((MRComponent) emr103).getYarnApplicationClasspath());
    }

    @Test
    public void testEMR240() throws Exception {
        HadoopComponent emr240 = DistributionFactory.buildDistribution(AMAZON_EMR, EMRAPACHE240);
        assertEquals(EHadoopDistributions.AMAZON_EMR, emr240.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, emr240.getHadoopVersion());
        assertFalse(emr240.doSupportKerberos());
        assertTrue(emr240.doSupportUseDatanodeHostname());
        assertFalse(emr240.doSupportGroup());
        assertTrue(((HDFSComponent) emr240).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) emr240).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) emr240).doSupportCrossPlatformSubmission());
    }

    @Test
    public void testEMR400() throws Exception {
        HadoopComponent emr400 = DistributionFactory.buildDistribution(AMAZON_EMR, EMR400);
        assertEquals(EHadoopDistributions.AMAZON_EMR, emr400.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, emr400.getHadoopVersion());
        assertFalse(emr400.doSupportKerberos());
        assertTrue(emr400.doSupportUseDatanodeHostname());
        assertFalse(emr400.doSupportGroup());
        assertTrue(((HDFSComponent) emr400).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) emr400).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) emr400).doSupportCrossPlatformSubmission());
    }

    @Test
    public void testPIV101() throws Exception {
        HadoopComponent piv101 = DistributionFactory.buildDistribution(PIVOTAL_HD, PIV101);
        assertEquals(EHadoopDistributions.PIVOTAL_HD, piv101.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, piv101.getHadoopVersion());
        assertFalse(piv101.doSupportKerberos());
        assertTrue(piv101.doSupportUseDatanodeHostname());
        assertFalse(piv101.doSupportGroup());
        assertTrue(((HDFSComponent) piv101).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) piv101).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) piv101).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) piv101).getYarnApplicationClasspath());
    }

    @Test
    public void testPIV200() throws Exception {
        HadoopComponent piv200 = DistributionFactory.buildDistribution(PIVOTAL_HD, PIV200);
        assertEquals(EHadoopDistributions.PIVOTAL_HD, piv200.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, piv200.getHadoopVersion());
        assertTrue(piv200.doSupportKerberos());
        assertTrue(piv200.doSupportUseDatanodeHostname());
        assertFalse(piv200.doSupportGroup());
        assertTrue(((HDFSComponent) piv200).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) piv200).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) piv200).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) piv200).getYarnApplicationClasspath());
    }

    @Test
    public void testHDINSIGHT310() throws Exception {
        HadoopComponent hdinsight310 = DistributionFactory.buildDistribution(HDINSIGHT, HDINSIGHT310);
        assertEquals(EHadoopDistributions.MICROSOFT_HD_INSIGHT, hdinsight310.getDistribution());
        assertEquals(EHadoopVersion.HADOOP_2, hdinsight310.getHadoopVersion());
        assertFalse(hdinsight310.doSupportKerberos());
        assertTrue(hdinsight310.doSupportUseDatanodeHostname());
        assertFalse(hdinsight310.doSupportGroup());
        assertFalse(hdinsight310 instanceof HDFSComponent);
        assertTrue(((MRComponent) hdinsight310).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) hdinsight310).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) hdinsight310).getYarnApplicationClasspath());
    }

    @Test
    public void testCUSTOM() throws Exception {
        HadoopComponent custom = DistributionFactory.buildDistribution(CUSTOM, PIV200);
        assertEquals(EHadoopDistributions.CUSTOM, custom.getDistribution());
        assertNull(custom.getHadoopVersion());
        assertFalse(custom.doSupportKerberos());
        assertTrue(custom.doSupportUseDatanodeHostname());
        assertFalse(custom.doSupportGroup());
        assertTrue(((HDFSComponent) custom).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) custom).isExecutedThroughWebHCat());
        assertFalse(((MRComponent) custom).doSupportCrossPlatformSubmission());
        assertEquals(DEFAULT_YARN_APPLICATION_CLASSPATH, ((MRComponent) custom).getYarnApplicationClasspath());
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
