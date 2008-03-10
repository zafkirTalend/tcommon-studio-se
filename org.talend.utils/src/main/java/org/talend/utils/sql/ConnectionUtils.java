// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.talend.utils.sugars.ReturnCode;

/**
 * Utility class for database connection handling.
 */
public final class ConnectionUtils {

    /**
     * The query to execute in order to verify the connection.
     */
    // private static final String PING_SELECT = "SELECT 1";
    /**
     * private constructor.
     */
    private ConnectionUtils() {
    }

    /**
     * Method "createConnection".
     * 
     * @param url the database url
     * @param driverClassName the Driver classname
     * @param props properties passed to the driver manager for getting the connection (normally at least a "user" and
     * "password" property should be included)
     * @return the connection
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static Connection createConnection(String url, String driverClassName, Properties props)
            throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Driver driver = (Driver) Class.forName(driverClassName).newInstance();
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, props);
        return connection;
    }

    /**
     * Method "isValid".
     * 
     * @param connection the connection to test
     * @return a return code with the appropriate message (never null)
     */
    public static ReturnCode isValid(Connection connection) {
        ReturnCode retCode = new ReturnCode();
        if (connection == null) {
            retCode.setReturnCode("Connection is null!", false);
            return retCode;
        }

        ResultSet ping = null;
        try {
            if (connection.isClosed()) {
                retCode.setReturnCode("Connection is closed", false);
                return retCode;
            }

            // do something so that exception is thrown is database connection failed
            connection.getAutoCommit();

            // select 1 is not understood by Oracle => do not use it
            // ping = connection.createStatement().executeQuery(PING_SELECT);
            // boolean next = ping.next();
            // if (!next) {
            // retCode.setReturnCode("Problem executing query " + PING_SELECT, next);
            // return retCode;
            // }
            // if we are here, everything is ok
            return retCode;
        } catch (SQLException sqle) {
            retCode.setReturnCode("SQLException caught:" + sqle.getMessage() + " SQL error code: "
                    + sqle.getErrorCode(), false);
            return retCode;
        } finally {
            if (ping != null) {
                try {
                    ping.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }

    }

    /**
     * Method "closeConnection".
     * 
     * @param connection the connection to close.
     * @return a ReturnCode with true if ok, false if problem. {@link ReturnCode#getMessage()} gives the error message
     * when there is a problem.
     */
    public static ReturnCode closeConnection(Connection connection) {
        assert connection != null;
        ReturnCode rc = new ReturnCode(true);
        try {
            connection.close();
        } catch (SQLException e) {
            rc.setReturnCode("Failed to close connection. Reason: " + e.getMessage(), false);
        }
        return rc;
    }
}
