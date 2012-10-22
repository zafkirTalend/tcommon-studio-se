// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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
    public static final String CONN_PARA_KEY_HIVE_MODE = "CONN_PARA_KEY_HIVE_MODE";

    public static final String CONN_PARA_KEY_HIVE_DISTRIBUTION = "CONN_PARA_KEY_HIVE_DISTRIBUTION";

    public static final String CONN_PARA_KEY_HIVE_VERSION = "CONN_PARA_KEY_HIVE_VERSION";

    public static final String CONN_PARA_KEY_CHARACTER_ENCODING = "CONN_PARA_KEY_CHARACTER_ENCODING";
    
    public static final String CONN_PARA_KEY_NAME_NODE_URL = "CONN_PARA_KEY_NAME_NODE_URL";
    
    public static final String CONN_PARA_KEY_JOB_TRACKER_URL = "CONN_PARA_KEY_JOB_TRACKER_URL";
    
    public static final String CONN_PARA_KEY_METASTORE_CONN_URL = "CONN_PARA_KEY_METASTORE_CONN_URL";
    
    public static final String CONN_PARA_KEY_METASTORE_CONN_USERNAME = "CONN_PARA_KEY_METASTORE_CONN_USERNAME";
    
    public static final String CONN_PARA_KEY_METASTORE_CONN_PASSWORD = "CONN_PARA_KEY_METASTORE_CONN_PASSWORD";
    
    public static final String CONN_PARA_KEY_METASTORE_CONN_DRIVER_JAR = "CONN_PARA_KEY_METASTORE_CONN_DRIVER_JAR";
    
    public static final String CONN_PARA_KEY_METASTORE_CONN_DRIVER_NAME = "CONN_PARA_KEY_METASTORE_CONN_DRIVER_NAME";
    
}
