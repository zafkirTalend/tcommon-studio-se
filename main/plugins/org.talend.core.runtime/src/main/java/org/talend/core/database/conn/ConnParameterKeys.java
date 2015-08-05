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
package org.talend.core.database.conn;

/**
 * These constants are the keys of map
 * {@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getParameters <em>Parameters</em>} of
 * database connection. The key of a parameter you want to add to database connection later is put here to manager
 * easily.
 * 
 * @author Marvin Wang
 * @version 1.0 jdk1.6
 * @date Aug 8, 2012
 */
public class ConnParameterKeys {

    /**
     * The key is used for Hive Mode to store its value.
     */
    public static final String CONN_PARA_KEY_HIVE_MODE = "CONN_PARA_KEY_HIVE_MODE"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HIVE_DISTRIBUTION = "CONN_PARA_KEY_HIVE_DISTRIBUTION"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HIVE_VERSION = "CONN_PARA_KEY_HIVE_VERSION"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_CHARACTER_ENCODING = "CONN_PARA_KEY_CHARACTER_ENCODING"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_NAME_NODE_URL = "CONN_PARA_KEY_NAME_NODE_URL"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_JOB_TRACKER_URL = "CONN_PARA_KEY_JOB_TRACKER_URL"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_URL = "CONN_PARA_KEY_METASTORE_CONN_URL"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_USERNAME = "CONN_PARA_KEY_METASTORE_CONN_USERNAME"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_PASSWORD = "CONN_PARA_KEY_METASTORE_CONN_PASSWORD"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_DRIVER_JAR = "CONN_PARA_KEY_METASTORE_CONN_DRIVER_JAR"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_DRIVER_NAME = "CONN_PARA_KEY_METASTORE_CONN_DRIVER_NAME"; //$NON-NLS-1$

    /**
     * The key is for metastore server.
     */
    public static final String CONN_PARA_KEY_METASTORE_SERVERNAME = "CONN_PARA_KEY_METASTORE_SERVERNAME"; //$NON-NLS-1$

    /**
     * The key is for metastore server port.
     */
    public static final String CONN_PARA_KEY_METASTORE_PROT = "CONN_PARA_KEY_METASTORE_PROT"; //$NON-NLS-1$

    /**
     * keys used for Hadoop settings.
     */
    public static final String CONN_PARA_KEY_HBASE_DISTRIBUTION = "CONN_PARA_KEY_HBASE_DISTRIBUTION"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HBASE_VERSION = "CONN_PARA_KEY_HBASE_VERSION"; //$NON-NLS-1$

}
