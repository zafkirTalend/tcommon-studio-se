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
package org.talend.core.hadoop.api;

import org.talend.core.hadoop.api.components.HadoopComponent;
import org.talend.core.hadoop.api.distribution.Apache100Distribution;
import org.talend.core.hadoop.api.distribution.CustomDistribution;
import org.talend.core.hadoop.api.distribution.HDInsight31Distribution;
import org.talend.core.hadoop.api.distribution.HDInsight32Distribution;
import org.talend.core.hadoop.api.distribution.cloudera.CDH4MR1Distribution;
import org.talend.core.hadoop.api.distribution.cloudera.CDH4MR2Distribution;
import org.talend.core.hadoop.api.distribution.cloudera.CDH500MR2Distribution;
import org.talend.core.hadoop.api.distribution.cloudera.CDH510MR1Distribution;
import org.talend.core.hadoop.api.distribution.cloudera.CDH510MR2Distribution;
import org.talend.core.hadoop.api.distribution.cloudera.CDH540Distribution;
import org.talend.core.hadoop.api.distribution.emr.EMR400Distribution;
import org.talend.core.hadoop.api.distribution.emr.EMRApache103Distribution;
import org.talend.core.hadoop.api.distribution.emr.EMRApache240Distribution;
import org.talend.core.hadoop.api.distribution.emr.EMRApache240_Hive_0_13_1_Distribution;
import org.talend.core.hadoop.api.distribution.hdp.HDP120Distribution;
import org.talend.core.hadoop.api.distribution.hdp.HDP130Distribution;
import org.talend.core.hadoop.api.distribution.hdp.HDP200Distribution;
import org.talend.core.hadoop.api.distribution.hdp.HDP210Distribution;
import org.talend.core.hadoop.api.distribution.hdp.HDP220Distribution;
import org.talend.core.hadoop.api.distribution.mapr.MapR200Distribution;
import org.talend.core.hadoop.api.distribution.mapr.MapR212Distribution;
import org.talend.core.hadoop.api.distribution.mapr.MapR213Distribution;
import org.talend.core.hadoop.api.distribution.mapr.MapR301Distribution;
import org.talend.core.hadoop.api.distribution.mapr.MapR310Distribution;
import org.talend.core.hadoop.api.distribution.mapr.MapR401Distribution;
import org.talend.core.hadoop.api.distribution.mapr.MapR410Distribution;
import org.talend.core.hadoop.api.distribution.pivotal.Pivotal101Distribution;
import org.talend.core.hadoop.api.distribution.pivotal.Pivotal200Distribution;
import org.talend.core.hadoop.version.EHadoopDistributions;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;

/**
 * This is a factory class that provides a way to create a @link{HadoopComponent} using the underlying GUI classes
 *
 * @link{EHadoopVersion4Drivers and @link{EHadoopDistributions}
 *
 */
public class DistributionFactory {

    private final static String EMR240_HIVE_0_13_1 = "APACHE_2_4_0_EMR_0_13_1"; //$NON-NLS-1$

    private final static String MICROSOFT_HD_INSIGHT_3_2 = "MICROSOFT_HD_INSIGHT_3_2"; //$NON-NLS-1$

    /**
     *
     * Builds a @link{HadoopComponent} distribution.
     *
     * @param pDistribution The name of the distribution
     * @param pVersion The name of the version
     * @return an implementation of @link{HadoopComponent}.
     * @throws Exception
     */
    public static HadoopComponent buildDistribution(String pDistribution, String pVersion) throws Exception {

        if (EHadoopDistributions.getDistributionByName(pDistribution, false) == EHadoopDistributions.CUSTOM) {
            return new CustomDistribution(EHadoopVersion4Drivers.CUSTOM);
        }

        EHadoopVersion4Drivers distribution = EHadoopVersion4Drivers.indexOfByVersion(pVersion);
        if (distribution != null) {
            if (distribution == EHadoopVersion4Drivers.CLOUDERA_CDH4) {
                return new CDH4MR1Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.CLOUDERA_CDH4_YARN) {
                return new CDH4MR2Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.CLOUDERA_CDH5) {
                return new CDH500MR2Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.CLOUDERA_CDH5_1_MR1) {
                return new CDH510MR1Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.CLOUDERA_CDH5_1) {
                return new CDH510MR2Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.CLOUDERA_CDH5_4) {
                return new CDH540Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.HDP_1_2) {
                return new HDP120Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.HDP_1_3) {
                return new HDP130Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.HDP_2_0) {
                return new HDP200Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.HDP_2_1) {
                return new HDP210Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.HDP_2_2) {
                return new HDP220Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.MAPR2) {
                return new MapR200Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.MAPR212) {
                return new MapR212Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.MAPR213) {
                return new MapR213Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.MAPR301) {
                return new MapR301Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.MAPR310) {
                return new MapR310Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.MAPR401) {
                return new MapR401Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.MAPR410) {
                return new MapR410Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.APACHE_1_0_3_EMR) {
                return new EMRApache103Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.APACHE_2_4_0_EMR) {
                return new EMRApache240Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.APACHE_1_0_0) {
                return new Apache100Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.PIVOTAL_HD_1_0_1) {
                return new Pivotal101Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.PIVOTAL_HD_2_0) {
                return new Pivotal200Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.MICROSOFT_HD_INSIGHT_3_1) {
                return new HDInsight31Distribution(distribution);
            }
            if (distribution == EHadoopVersion4Drivers.EMR_4_0_0) {
                return new EMR400Distribution(distribution);
            }
        } else {
            // The distribution can be null in case of the GUI has not implemented the distribution in
            // EHadoopVersion4Drivers. In this case, we create this temporary code waiting for them to implement it.

            if (MICROSOFT_HD_INSIGHT_3_2.equals(pVersion)) {
                return new HDInsight32Distribution();
            }

            if (EMR240_HIVE_0_13_1.equals(pVersion)) {
                return new EMRApache240_Hive_0_13_1_Distribution();
            }
        }
        throw new Exception("The distribution " + pDistribution + " with the version " + pVersion + " doesn't exist."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * Execute a methode for a given distribution and version. This function must return a booelan
     *
     * @param methodName the name of the method
     * @param distribution the name of the distribution
     * @param version the name of the version
     * 
     * @return
     * @throws Exception
     */
    public static boolean executeBooleanMethod(String methodName, String distribution, String version) throws Exception {
        HadoopComponent distrib = DistributionFactory.buildDistribution(distribution, version);
        java.lang.reflect.Method m = distrib.getClass().getMethod(methodName, new Class<?>[0]);
        return (Boolean) m.invoke(distrib, new Object[0]);
    }

}
