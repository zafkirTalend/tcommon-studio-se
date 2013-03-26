// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.metadata.managment.hive;

import org.talend.core.classloader.ClassLoaderFactory;
import org.talend.core.database.conn.ConnParameterKeys;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.metadata.managment.connection.manager.DatabaseConnConstants;

/**
 * <pre>
 * This factory is used to get the class loaders for hive by the different index.
 * </pre>
 * 
 * <pre>
 * <li>For hive server one, the index format should be like this: <B>HIVE:HORTONWORKS:HDP_1_0:EMBEDDED</B>. 
 * <li>For hive server two, the index format should be like this: <B>HIVE2:HORTONWORKS:HDP_1_0:EMBEDDED</B>.
 * </pre>
 * 
 * Created by Marvin Wang on Mar 12, 2013.
 */
public class HiveClassLoaderFactory {

    private static HiveClassLoaderFactory instance = null;

    private HiveClassLoaderFactory() {
    }

    /**
     * Gets an instance of <code>HiveClassLoaderFactory</code>, which is synchronized. Added by Marvin Wang on Mar 12,
     * 2013.
     * 
     * @return
     */
    public static synchronized HiveClassLoaderFactory getInstance() {
        if (instance == null) {
            instance = new HiveClassLoaderFactory();
        }
        return instance;
    }

    /**
     * Gets an instance of <code>ClassLoader</code> by the given argument, it invokes {
     * {@link #getClassLoader(String, String, String)}. The parameters required by {
     * {@link #getClassLoader(String, String, String)} are fetched from {@link IMetadataConnection}. Added by Marvin
     * Wang on Mar 13, 2013.
     * 
     * @param metadataConn
     * @return
     */
    public ClassLoader getClassLoader(IMetadataConnection metadataConn) {
        ClassLoader classloader = null;
        String url = metadataConn.getUrl();
        // url = "jdbc:hive2://";
        if (url != null) {
            // For hive embedded model, all parameters below should not be null, if null, then need to check.
            String distro = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_DISTRIBUTION);
            String distroVersion = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_VERSION);
            String hiveModel = (String) metadataConn.getParameter(ConnParameterKeys.CONN_PARA_KEY_HIVE_MODE);
            if (url.startsWith(DatabaseConnConstants.HIVE_2_URL_FORMAT)) {
                classloader = getHive2ClassLoader(distro, distroVersion, hiveModel);
            } else if (url.startsWith(DatabaseConnConstants.HIVE_1_URL_FORMAT)) {
                classloader = getHive1ClassLoader(distro, distroVersion, hiveModel);
            } else {
                // do nothing
            }
        }
        return classloader;
    }

    /**
     * Gets the class loader for different version by the given parameters for hive1 server. Added by Marvin Wang on Mar
     * 12, 2013.
     * 
     * @param distribution
     * @param distroVersion
     * @param model
     * @return
     */
    protected ClassLoader getHive1ClassLoader(String distribution, String distroVersion, String model) {
        String index = "HIVE" + ":" + distribution + ":" + distroVersion + ":" + model; //$NON-NLS-1$  //$NON-NLS-2$  //$NON-NLS-3$ //$NON-NLS-4$ 
        ClassLoader loader = ClassLoaderFactory.getClassLoader(index);
        // If there is no class loader gotten by index, then use the current classloader.
        if (loader == null) {
            loader = HiveClassLoaderFactory.class.getClassLoader();
        }
        return loader;
    }

    /**
     * Gets the class loader for hive2 server. Added by Marvin Wang on Mar 25, 2013.
     * 
     * @param distribution
     * @param distroVersion
     * @param model
     * @return
     */
    protected ClassLoader getHive2ClassLoader(String distribution, String distroVersion, String model) {
        String index = "HIVE2" + ":" + distribution + ":" + distroVersion + ":" + model; //$NON-NLS-1$  //$NON-NLS-2$  //$NON-NLS-3$ //$NON-NLS-4$ 
        ClassLoader loader = ClassLoaderFactory.getClassLoader(index);
        // If there is no class loader gotten by index, then use the current classloader.
        if (loader == null) {
            loader = HiveClassLoaderFactory.class.getClassLoader();
        }
        return loader;
    }
}
