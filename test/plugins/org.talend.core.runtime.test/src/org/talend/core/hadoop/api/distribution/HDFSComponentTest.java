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

import org.junit.Before;
import org.junit.Test;
import org.talend.core.hadoop.api.DistributionFactory;
import org.talend.core.hadoop.api.EHadoopVersion;
import org.talend.core.hadoop.api.components.HDFSComponent;

public class HDFSComponentTest {

    private static final String CLOUDERA = "CLOUDERA"; //$NON-NLS-1$

    private static final String HORTONWORKS = "HORTONWORKS"; //$NON-NLS-1$

    private static final String MAPR = "MAPR"; //$NON-NLS-1$

    private static final String AMAZON_EMR = "AMAZON_EMR"; //$NON-NLS-1$

    private static final String PIVOTAL_HD = "PIVOTAL_HD"; //$NON-NLS-1$

    private static final String APACHE = "APACHE"; //$NON-NLS-1$

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

    HDFSComponent cdh400mr1, cdh400mr2, cdh500, cdh510mr1, cdh510mr2, cdh540, hdp120, hdp130, hdp200, hdp210, hdp220, apache100,
            mapr200, mapr212, mapr213, mapr301, mapr310, mapr401, mapr410, emr103, emr240, emr400, piv101, piv200, custom;

    @Before
    public void setUp() throws Exception {
        cdh400mr1 = (HDFSComponent) DistributionFactory.buildDistribution(CLOUDERA, CDH400MR1);
        cdh400mr2 = (HDFSComponent) DistributionFactory.buildDistribution(CLOUDERA, CDH400MR2);
        cdh500 = (HDFSComponent) DistributionFactory.buildDistribution(CLOUDERA, CDH500);
        cdh510mr1 = (HDFSComponent) DistributionFactory.buildDistribution(CLOUDERA, CDH510MR1);
        cdh510mr2 = (HDFSComponent) DistributionFactory.buildDistribution(CLOUDERA, CDH510MR2);
        cdh540 = (HDFSComponent) DistributionFactory.buildDistribution(CLOUDERA, CDH540);
        hdp120 = (HDFSComponent) DistributionFactory.buildDistribution(HORTONWORKS, HDP120);
        hdp130 = (HDFSComponent) DistributionFactory.buildDistribution(HORTONWORKS, HDP130);
        hdp200 = (HDFSComponent) DistributionFactory.buildDistribution(HORTONWORKS, HDP200);
        hdp210 = (HDFSComponent) DistributionFactory.buildDistribution(HORTONWORKS, HDP210);
        hdp220 = (HDFSComponent) DistributionFactory.buildDistribution(HORTONWORKS, HDP220);
        apache100 = (HDFSComponent) DistributionFactory.buildDistribution(APACHE, APACHE100);
        mapr200 = (HDFSComponent) DistributionFactory.buildDistribution(MAPR, MAPR200);
        mapr212 = (HDFSComponent) DistributionFactory.buildDistribution(MAPR, MAPR212);
        mapr213 = (HDFSComponent) DistributionFactory.buildDistribution(MAPR, MAPR213);
        mapr301 = (HDFSComponent) DistributionFactory.buildDistribution(MAPR, MAPR301);
        mapr310 = (HDFSComponent) DistributionFactory.buildDistribution(MAPR, MAPR310);
        mapr401 = (HDFSComponent) DistributionFactory.buildDistribution(MAPR, MAPR401);
        mapr410 = (HDFSComponent) DistributionFactory.buildDistribution(MAPR, MAPR410);
        emr103 = (HDFSComponent) DistributionFactory.buildDistribution(AMAZON_EMR, EMRAPACHE103);
        emr240 = (HDFSComponent) DistributionFactory.buildDistribution(AMAZON_EMR, EMRAPACHE240);
        emr400 = (HDFSComponent) DistributionFactory.buildDistribution(AMAZON_EMR, EMR400);
        piv101 = (HDFSComponent) DistributionFactory.buildDistribution(PIVOTAL_HD, PIV101);
        piv200 = (HDFSComponent) DistributionFactory.buildDistribution(PIVOTAL_HD, PIV200);
        custom = (HDFSComponent) DistributionFactory.buildDistribution(CUSTOM, PIV200);
    }

    @Test
    public void testHadoopVersion() {
        assertEquals(EHadoopVersion.HADOOP_1, cdh400mr1.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, cdh400mr2.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, cdh500.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, cdh510mr1.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, cdh510mr2.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, cdh540.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, hdp120.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, hdp130.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, hdp200.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, hdp210.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, hdp220.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, apache100.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, mapr200.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, mapr212.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, mapr213.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, mapr301.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, mapr310.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, mapr401.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, mapr410.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_1, emr103.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, emr240.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, emr400.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, piv101.getHadoopVersion());
        assertEquals(EHadoopVersion.HADOOP_2, piv200.getHadoopVersion());
        assertNull(custom.getHadoopVersion());
    }

    @Test
    public void testSupportKerberos() {
        assertTrue(cdh400mr1.doSupportKerberos());
        assertTrue(cdh400mr2.doSupportKerberos());
        assertTrue(cdh500.doSupportKerberos());
        assertTrue(cdh510mr1.doSupportKerberos());
        assertTrue(cdh510mr2.doSupportKerberos());
        assertTrue(cdh540.doSupportKerberos());
        assertTrue(hdp120.doSupportKerberos());
        assertTrue(hdp130.doSupportKerberos());
        assertTrue(hdp200.doSupportKerberos());
        assertTrue(hdp210.doSupportKerberos());
        assertTrue(hdp220.doSupportKerberos());
        assertTrue(apache100.doSupportKerberos());
        assertFalse(mapr200.doSupportKerberos());
        assertFalse(mapr212.doSupportKerberos());
        assertFalse(mapr213.doSupportKerberos());
        assertFalse(mapr301.doSupportKerberos());
        assertFalse(mapr310.doSupportKerberos());
        assertFalse(mapr401.doSupportKerberos());
        assertFalse(mapr410.doSupportKerberos());
        assertTrue(emr103.doSupportKerberos());
        assertFalse(emr240.doSupportKerberos());
        assertFalse(emr400.doSupportKerberos());
        assertFalse(piv101.doSupportKerberos());
        assertTrue(piv200.doSupportKerberos());
        assertFalse(custom.doSupportKerberos());
    }

    @Test
    public void testSupportDatanodeHostname() {
        assertFalse(cdh400mr1.doSupportUseDatanodeHostname());
        assertTrue(cdh400mr2.doSupportUseDatanodeHostname());
        assertTrue(cdh500.doSupportUseDatanodeHostname());
        assertFalse(cdh510mr1.doSupportUseDatanodeHostname());
        assertTrue(cdh510mr2.doSupportUseDatanodeHostname());
        assertTrue(cdh540.doSupportUseDatanodeHostname());
        assertTrue(hdp120.doSupportUseDatanodeHostname());
        assertTrue(hdp130.doSupportUseDatanodeHostname());
        assertTrue(hdp200.doSupportUseDatanodeHostname());
        assertTrue(hdp210.doSupportUseDatanodeHostname());
        assertTrue(hdp220.doSupportUseDatanodeHostname());
        assertFalse(apache100.doSupportUseDatanodeHostname());
        assertFalse(mapr200.doSupportUseDatanodeHostname());
        assertFalse(mapr212.doSupportUseDatanodeHostname());
        assertFalse(mapr213.doSupportUseDatanodeHostname());
        assertFalse(mapr301.doSupportUseDatanodeHostname());
        assertFalse(mapr310.doSupportUseDatanodeHostname());
        assertFalse(mapr401.doSupportUseDatanodeHostname());
        assertFalse(mapr410.doSupportUseDatanodeHostname());
        assertFalse(emr103.doSupportUseDatanodeHostname());
        assertTrue(emr240.doSupportUseDatanodeHostname());
        assertTrue(emr400.doSupportUseDatanodeHostname());
        assertTrue(piv101.doSupportUseDatanodeHostname());
        assertTrue(piv200.doSupportUseDatanodeHostname());
        assertTrue(custom.doSupportUseDatanodeHostname());
    }

    @Test
    public void testSupportGroup() {
        assertFalse(cdh400mr1.doSupportGroup());
        assertFalse(cdh400mr2.doSupportGroup());
        assertFalse(cdh500.doSupportGroup());
        assertFalse(cdh510mr1.doSupportGroup());
        assertFalse(cdh510mr2.doSupportGroup());
        assertFalse(cdh540.doSupportGroup());
        assertFalse(hdp120.doSupportGroup());
        assertFalse(hdp130.doSupportGroup());
        assertFalse(hdp200.doSupportGroup());
        assertFalse(hdp210.doSupportGroup());
        assertFalse(hdp220.doSupportGroup());
        assertFalse(apache100.doSupportGroup());
        assertTrue(mapr200.doSupportGroup());
        assertTrue(mapr212.doSupportGroup());
        assertTrue(mapr213.doSupportGroup());
        assertTrue(mapr301.doSupportGroup());
        assertTrue(mapr310.doSupportGroup());
        assertTrue(mapr401.doSupportGroup());
        assertTrue(mapr410.doSupportGroup());
        assertFalse(emr103.doSupportGroup());
        assertFalse(emr240.doSupportGroup());
        assertFalse(emr400.doSupportGroup());
        assertFalse(piv101.doSupportGroup());
        assertFalse(piv200.doSupportGroup());
        assertFalse(custom.doSupportGroup());
    }

    @Test
    public void testSupportSequenceFileShortType() {
        assertTrue(cdh400mr1.doSupportSequenceFileShortType());
        assertTrue(cdh400mr2.doSupportSequenceFileShortType());
        assertTrue(cdh500.doSupportSequenceFileShortType());
        assertTrue(cdh510mr1.doSupportSequenceFileShortType());
        assertTrue(cdh510mr2.doSupportSequenceFileShortType());
        assertTrue(cdh540.doSupportSequenceFileShortType());
        assertFalse(hdp120.doSupportSequenceFileShortType());
        assertFalse(hdp130.doSupportSequenceFileShortType());
        assertTrue(hdp200.doSupportSequenceFileShortType());
        assertTrue(hdp210.doSupportSequenceFileShortType());
        assertTrue(hdp220.doSupportSequenceFileShortType());
        assertFalse(apache100.doSupportSequenceFileShortType());
        assertFalse(mapr200.doSupportSequenceFileShortType());
        assertFalse(mapr212.doSupportSequenceFileShortType());
        assertFalse(mapr213.doSupportSequenceFileShortType());
        assertFalse(mapr301.doSupportSequenceFileShortType());
        assertFalse(mapr310.doSupportSequenceFileShortType());
        assertTrue(mapr401.doSupportSequenceFileShortType());
        assertTrue(mapr410.doSupportSequenceFileShortType());
        assertFalse(emr103.doSupportSequenceFileShortType());
        assertTrue(emr240.doSupportSequenceFileShortType());
        assertTrue(emr400.doSupportSequenceFileShortType());
        assertTrue(piv101.doSupportSequenceFileShortType());
        assertTrue(piv200.doSupportSequenceFileShortType());
        assertTrue(custom.doSupportSequenceFileShortType());
    }
}
