// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import junit.framework.TestCase;

import org.talend.commons.exception.ExceptionHandler;

/**
 * test case for db2 on zos.<br>
 * see feature 0005827
 */
public class DB2OnZosTestCase extends TestCase {

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSystemColumns() throws Exception {
        String url = "jdbc:db2://talend-dbms:50000/Talend"; //$NON-NLS-1$
        Properties prop = new Properties();
        prop.setProperty("user", "root"); //$NON-NLS-1$ //$NON-NLS-2$
        prop.setProperty("password", "toor"); //$NON-NLS-1$ //$NON-NLS-2$
        String schema = "ROOT"; //$NON-NLS-1$
        String sql = "SELECT * FROM SYSIBM.SYSCOLUMNS WHERE NAME='TEXT' "; //$NON-NLS-1$
        ResultSet rs = null;
        Connection connection = null;
        Statement stmt = null;
        try {

            Class.forName("com.ibm.db2.jcc.DB2Driver"); //$NON-NLS-1$
            connection = DriverManager.getConnection(url, prop);
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("NAME"); //$NON-NLS-1$

                System.out.print(name);
                System.out.println();
            }

        } catch (SQLException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
        } finally {
            try {
                rs.close();
                stmt.close();
                connection.close();
            } catch (Exception e2) {
            }
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:db2://talend-dbms:50000/Talend"; //$NON-NLS-1$
        Properties prop = new Properties();
        prop.setProperty("user", "root"); //$NON-NLS-1$ //$NON-NLS-2$
        prop.setProperty("password", "toor"); //$NON-NLS-1$ //$NON-NLS-2$
        String schema = "ROOT"; //$NON-NLS-1$
        String sql = "SELECT * FROM SYSIBM.SYSTABLES where CREATOR = '" + schema + "'"; //$NON-NLS-1$ //$NON-NLS-2$
        ResultSet rs = null;
        Connection connection = null;
        Statement stmt = null;
        try {

            Class.forName("com.ibm.db2.jcc.DB2Driver"); //$NON-NLS-1$
            connection = DriverManager.getConnection(url, prop);
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("2ss"); //$NON-NLS-1$
                String creator = rs.getString(2);
                String type = rs.getString(3);
                String dbname = rs.getString(4);

                System.out.println(name + "|" + creator + "|" + type + "|" + dbname); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                System.out.println();
            }

        } catch (SQLException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
        } finally {
            try {
                rs.close();
                stmt.close();
                connection.close();
            } catch (Exception e2) {
            }
        }
    }
}
