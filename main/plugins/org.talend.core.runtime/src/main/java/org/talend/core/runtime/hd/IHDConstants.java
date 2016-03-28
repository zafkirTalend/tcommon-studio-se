// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.runtime.hd;

/**
 * DOC ggu class global comment. Detailled comment
 */
public interface IHDConstants {

    /**
     * same as ICustomDistribution.DISTRIBUTION_NAME
     */
    static final String DISTRIBUTION_CUSTOM = "CUSTOM";

    /**
     * same as IHortonworksDistribution.DISTRIBUTION_NAME
     */
    static final String DISTRIBUTION_HORTONWORKS = "HORTONWORKS";

    /**
     * same as IAmazonEMRDistribution.DISTRIBUTION_NAME
     */
    static final String DISTRIBUTION_AMAZON_EMR = "AMAZON_EMR";

    /**
     * must be full class name of {@link HadoopComponent}
     */
    static final String SERVICE_HADOOP = "org.talend.hadoop.distribution.component.HadoopComponent";

    /**
     * must be full class name of {@link SparkComponent}
     */
    static final String SERVICE_SPARK = "org.talend.hadoop.distribution.component.SparkComponent";

    /**
     * must be full class name of {@link SparkStreamingComponent}
     */
    static final String SERVICE_SPARK_STREAMING = "org.talend.hadoop.distribution.component.SparkStreamingComponent";
}
