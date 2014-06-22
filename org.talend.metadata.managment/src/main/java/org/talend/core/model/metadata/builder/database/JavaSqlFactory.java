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
package org.talend.core.model.metadata.builder.database;

import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.talend.core.database.conn.DatabaseConnStrUtil;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.MDMConnection;
import org.talend.core.model.metadata.builder.database.dburl.SupportDBUrlStore;
import org.talend.core.model.metadata.builder.database.dburl.SupportDBUrlType;
import org.talend.core.model.metadata.builder.util.MetadataConnectionUtils;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.cwm.helper.ConnectionHelper;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.repository.model.IRepositoryService;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sugars.ReturnCode;
import org.talend.utils.sugars.TypedReturnCode;

/**
 * @author scorreia
 * 
 * This utility class provides methods that convert CWM object into java.sql object. It is a kind of inverse of the
 * DatabaseContentRetriever class.
 */
public final class JavaSqlFactory {

    public static final String DEFAULT_USERNAME = "root";

    public static final String DEFAULT_PASSWORD = "";

    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger(JavaSqlFactory.class);

    private JavaSqlFactory() {
    }

    /**
     * Method "createConnection" returns the connection with {@link ReturnCode#getObject()} if {@link ReturnCode#isOk()}
     * is true. This is the behaviour when everything goes ok.
     * <p>
     * When something goes wrong, {@link ReturnCode#isOk()} is false and {@link ReturnCode#getMessage()} gives the error
     * message.
     * <p>
     * The created connection must be closed by the caller. (use {@link ConnectionUtils#closeConnection(Connection)})
     * 
     * @param providerConnection the provider connection
     * @return a ReturnCode (never null)
     * @deprecated
     */
    public static TypedReturnCode<java.sql.Connection> createConnection(DatabaseConnection providerConnection) {
        TypedReturnCode<java.sql.Connection> rc = new TypedReturnCode<java.sql.Connection>(false);
        String url = providerConnection.getURL();
        if (url == null) {
            rc.setMessage(Messages.getString("JavaSqlFactory.DatabaseConnectionNull")); //$NON-NLS-1$
            rc.setOk(false);
        }
        String driverClassName = providerConnection.getDriverClass();
        Properties props = new Properties();
        props.put(TaggedValueHelper.USER, providerConnection.getUsername());
        props.put(TaggedValueHelper.PASSWORD, providerConnection.getPassword());
        String pass = props.getProperty(TaggedValueHelper.PASSWORD);
        if (pass != null) {
            String clearTextPassword = providerConnection.getPassword();
            if (clearTextPassword == null) {
                rc.setMessage(Messages.getString("JavaSqlFactory.UnableDecryptPassword")); //$NON-NLS-1$
                rc.setOk(false);
            } else {
                props.setProperty(TaggedValueHelper.PASSWORD, clearTextPassword);
            }
        }
        try {
            java.sql.Connection connection = ConnectionUtils.createConnection(url, driverClassName, props);
            rc.setObject(connection);
            rc.setOk(true);
        } catch (SQLException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (InstantiationException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (IllegalAccessException e) {
            rc.setReturnCode(e.getMessage(), false);
        } catch (ClassNotFoundException e) {
            rc.setReturnCode(e.getMessage(), false);
        }
        return rc;
    }

    /**
     * Method "createConnection" returns the connection with {@link ReturnCode#getObject()} if {@link ReturnCode#isOk()}
     * is true. This is the behaviour when everything goes ok.
     * <p>
     * When something goes wrong, {@link ReturnCode#isOk()} is false and {@link ReturnCode#getMessage()} gives the error
     * message.
     * <p>
     * The created connection must be closed by the caller. (use {@link ConnectionUtils#closeConnection(Connection)})
     * 
     * @param connection the connection (DatabaseConnection MDMConnection or others)
     * @return a ReturnCode (never null)
     */
    public static TypedReturnCode<java.sql.Connection> createConnection(Connection connection) {
        TypedReturnCode<java.sql.Connection> rc = new TypedReturnCode<java.sql.Connection>(false);
        String url = getURL(connection);
        if (url == null) {
            rc.setMessage(Messages.getString("JavaSqlFactory.DatabaseConnectionNull")); //$NON-NLS-1$
            rc.setOk(false);
            return rc; // MOD scorreia 2010-10-20 bug 16403 avoid NPE while importing items
        }

        String driverClassName = getDriverClass(connection);
        // MOD scorreia 2010-10-20 bug 16403 avoid NPE while importing items
        if (StringUtils.isEmpty(driverClassName)) {
            rc.setMessage("No classname given to find the driver");
            rc.setOk(false);
            return rc;
        }
        Properties props = new Properties();
        // MOD xqliu 2010-08-06 bug 14593
        props.put(TaggedValueHelper.USER, getUsernameDefault(connection));
        props.put(TaggedValueHelper.PASSWORD, getPasswordDefault(connection));
        // ~ 14593
        java.sql.Connection sqlConnection = null;
        try {
            sqlConnection = ConnectionUtils.createConnection(url, driverClassName, props);
        } catch (Throwable e) {
            rc.setReturnCode(e.getMessage(), false);
        }

        if (sqlConnection == null && connection instanceof DatabaseConnection) {
            sqlConnection = MetadataConnectionUtils.checkConnection((DatabaseConnection) connection).getObject();
        }
        rc.setObject(sqlConnection);
        rc.setOk(sqlConnection != null);

        return rc;
    }

    /**
     * DOC xqliu Comment method "getDriverClass".
     * 
     * @param conn
     * @return driver class name of the connection or null
     */
    public static String getDriverClass(Connection conn) {
        DatabaseConnection dbConn = SwitchHelpers.DATABASECONNECTION_SWITCH.doSwitch(conn);
        if (dbConn != null) {
            String driverClassName = dbConn.getDriverClass();
            // SG : issue http://talendforge.org/bugs/view.php?id=16199
            if (driverClassName == null) {// no drive is specified so let us try
                                          // to guess it
                SupportDBUrlType dbType = SupportDBUrlStore.getInstance().findDBTypeByName(dbConn.getDatabaseType());
                if (dbType != null) {
                    driverClassName = dbType.getDbDriver();
                }// else we keep the drive class to null, we do not know how to
                 // guess it anymore.
            } // else we are ok
            return driverClassName;
        }
        MDMConnection mdmConn = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(conn);
        if (mdmConn != null) {
            return "";
        }
        DelimitedFileConnection dfConn = SwitchHelpers.DELIMITEDFILECONNECTION_SWITCH.doSwitch(conn);
        if (dfConn != null) {
            return "";
        }
        return null;
    }

    /**
     * DOC xqliu Comment method "getUsernameDefault".
     * 
     * @param conn
     * @return username of the connection or default username
     */
    public static String getUsernameDefault(Connection conn) {
        return getUsername(conn, DEFAULT_USERNAME);
    }

    /**
     * DOC xqliu Comment method "getPasswordDefault".
     * 
     * @param conn
     * @return password of the connection or default password
     */
    public static String getPasswordDefault(Connection conn) {
        return getPassword(conn, DEFAULT_PASSWORD);
    }

    /**
     * DOC xqliu Comment method "getUsername".
     * 
     * @param conn
     * @param defaultUsername
     * @return username of the connection or given username
     */
    public static String getUsername(Connection conn, String defaultUsername) {
        String result = getUsername(conn);
        result = (result == null || "".equals(result.trim())) ? defaultUsername : result;
        return result;
    }

    /**
     * DOC xqliu Comment method "getPassword".
     * 
     * @param conn
     * @param defaultPassword
     * @return password of the connection or given password
     */
    public static String getPassword(Connection conn, String defaultPassword) {
        String result = getPassword(conn);
        result = result == null ? defaultPassword : result;
        return result;
    }

    /**
     * DOC xqliu Comment method "setURL".
     * 
     * @param conn
     * @param url
     */
    public static void setURL(Connection conn, String url) {
        DatabaseConnection dbConn = SwitchHelpers.DATABASECONNECTION_SWITCH.doSwitch(conn);
        if (dbConn != null) {
            dbConn.setURL(url);
        }
        MDMConnection mdmConn = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(conn);
        if (mdmConn != null) {
            mdmConn.setPathname(url);
        }
        // MOD qiongli 2011-1-9 feature 16796
        DelimitedFileConnection dfConnection = SwitchHelpers.DELIMITEDFILECONNECTION_SWITCH.doSwitch(conn);
        if (dfConnection != null) {
            dfConnection.setFilePath(url);
        }
    }

    /**
     * DOC xqliu Comment method "getUsername".
     * 
     * @param conn
     * @return username of the connection or null
     */
    public static String getUsername(Connection conn) {
        DatabaseConnection dbConn = SwitchHelpers.DATABASECONNECTION_SWITCH.doSwitch(conn);
        String dbUserName = null;
        if (dbConn != null) {
            dbUserName = dbConn.getUsername();
            if (conn.isContextMode()) {
                IRepositoryService repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
                if (repositoryService != null) {
                    // get the original value and select the defalut context
                    String contextName = conn.getContextName();
                    DatabaseConnection origValueConn = repositoryService.cloneOriginalValueConnection(dbConn,
                            contextName == null ? true : false, contextName);
                    dbUserName = origValueConn.getUsername();
                }
            }
            return dbUserName;
        }
        MDMConnection mdmConn = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(conn);
        if (mdmConn != null) {
            return mdmConn.getUsername();
        }
        return null;
    }

    /**
     * DOC xqliu Comment method "getPassword".
     * 
     * @param conn
     * @return password of the connection or null
     */
    public static String getPassword(Connection conn) {
        DatabaseConnection dbConn = SwitchHelpers.DATABASECONNECTION_SWITCH.doSwitch(conn);
        String psw = null;
        if (dbConn != null) {
            psw = dbConn.getPassword();
            if (conn.isContextMode()) {
                IRepositoryService repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
                if (repositoryService != null) {
                    // get the original value and select the defalut context
                    String contextName = conn.getContextName();
                    DatabaseConnection origValueConn = repositoryService.cloneOriginalValueConnection(dbConn,
                            contextName == null ? true : false, contextName);
                    psw = origValueConn.getPassword();
                }

            }
            return psw;
        }
        MDMConnection mdmConn = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(conn);
        if (mdmConn != null) {
            return ConnectionHelper.getPassword(mdmConn);
        }
        return null;
    }

    /**
     * DOC xqliu Comment method "setUsername".
     * 
     * @param conn
     * @param username
     */
    public static void setUsername(Connection conn, String username) {
        DatabaseConnection dbConn = SwitchHelpers.DATABASECONNECTION_SWITCH.doSwitch(conn);
        if (dbConn != null) {
            dbConn.setUsername(username);
        }
        MDMConnection mdmConn = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(conn);
        if (mdmConn != null) {
            mdmConn.setUsername(username);
        }
    }

    /**
     * DOC xqliu Comment method "setPassword".
     * 
     * @param conn
     * @param password
     */
    public static void setPassword(Connection conn, String password) {
        DatabaseConnection dbConn = SwitchHelpers.DATABASECONNECTION_SWITCH.doSwitch(conn);
        if (dbConn != null) {
            dbConn.setPassword(ConnectionHelper.getEncryptPassword(password));
        }
        MDMConnection mdmConn = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(conn);
        if (mdmConn != null) {
            mdmConn.setPassword(ConnectionHelper.getEncryptPassword(password));
        }
    }

    /**
     * DOC xqliu Comment method "getURL".
     * 
     * @param conn
     * @return url string of the connection or null
     */
    public static String getURL(Connection conn) {
        DatabaseConnection dbConn = SwitchHelpers.DATABASECONNECTION_SWITCH.doSwitch(conn);
        String url = null;
        if (dbConn != null) {
            url = dbConn.getURL();
            if (conn.isContextMode()) {
                IRepositoryService repositoryService = CoreRuntimePlugin.getInstance().getRepositoryService();
                if (repositoryService != null) {
                    // get the original value and select the defalut context
                    String contextName = conn.getContextName();
                    DatabaseConnection origValueConn = repositoryService.cloneOriginalValueConnection(dbConn,
                            contextName == null ? true : false, contextName);
                    url = DatabaseConnStrUtil.getURLString(origValueConn);
                    // url = origValueConn.getURL();
                }
            }
            return url;
        }
        MDMConnection mdmConn = SwitchHelpers.MDMCONNECTION_SWITCH.doSwitch(conn);
        if (mdmConn != null) {
            return mdmConn.getPathname();
        }
        // MOD qiongli 2011-1-11 feature 16796.
        DelimitedFileConnection dfConnection = SwitchHelpers.DELIMITEDFILECONNECTION_SWITCH.doSwitch(conn);
        if (dfConnection != null) {
            return dfConnection.getFilePath();
        }
        return null;
    }
}
