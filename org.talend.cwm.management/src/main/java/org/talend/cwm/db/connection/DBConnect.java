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
package org.talend.cwm.db.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.talend.commons.emf.EMFUtil;
import org.talend.cwm.builders.CatalogBuilder;
import org.talend.cwm.builders.SoftwareSystemBuilder;
import org.talend.cwm.relational.TdCatalog;
import org.talend.cwm.relational.TdSchema;
import org.talend.cwm.softwaredeployment.TdSoftwareSystem;
import orgomg.cwm.foundation.typemapping.TypeSystem;

/**
 * @author scorreia
 * 
 * This class builds CWM classes from a database connection.
 */
public class DBConnect {

    private static final Class<DBConnect> THAT = DBConnect.class;

    private static Logger log = Logger.getLogger(THAT);

    private final EMFUtil emfUtil = new EMFUtil();

    private final String databaseUrl;

    private final Properties connectionProperties;

    private final String driverClass;

    private SoftwareSystemBuilder softwareSystemBuilder;

    private CatalogBuilder catalogBuilder;

    private Connection connection;

    // TODO scorreia errorMessage;
    /**
     * DBConnect constructor.
     * 
     * @param dbUrl the database URL (must not be null)
     * @param driverClassName the driver class name (must not be null)
     * @param props properties passed to the connection
     */
    public DBConnect(final String dbUrl, final String driverClassName, final Properties props) {
        assert dbUrl != null && driverClassName != null;

        this.databaseUrl = dbUrl;
        this.driverClass = driverClassName;
        this.connectionProperties = props;
    }

    /**
     * Method "getCatalogs". Method this.{@link #retrieveDatabaseStructure()} should be called before getting the
     * catalogs.
     * 
     * @return the catalogs built from the connection, or an empty list.
     */
    public Collection<TdCatalog> getCatalogs() {
        if (catalogBuilder == null) {
            return Collections.emptyList();
        }
        return catalogBuilder.getCatalogs();
    }

    /**
     * Method "getSchemata". Method this.{@link #retrieveDatabaseStructure()} should be called before getting the
     * schematas.
     * 
     * @return the schemata built from the connection, or an empty list.
     */
    public Collection<TdSchema> getSchemata() {
        if (catalogBuilder == null) {
            return Collections.emptyList();
        }
        return catalogBuilder.getSchemata();
    }

    /**
     * Method "getSoftwareSystem". Method this.{@link #retrieveDeployedSystemInformations()} should be called before
     * getting the software system.
     * 
     * @return the Software system or null.
     */
    public TdSoftwareSystem getSoftwareSystem() {
        if (softwareSystemBuilder == null) {
            return null;
        }
        return softwareSystemBuilder.getSoftwareSystem();
    }

    /**
     * Method "getTypeSystem". Method this.{@link #retrieveDeployedSystemInformations()} should be called before
     * getting the type system.
     * 
     * @return the TypeSystem that defines the datatypes supported by the software system.
     */
    public TypeSystem getTypeSystem() {
        if (softwareSystemBuilder == null) {
            return null;
        }
        return softwareSystemBuilder.getTypeSystem();
    }

    /**
     * Method "saveInFiles". Calling this method without having called this.{@link #storeInResourceSet(EObject, String)}
     * will do nothing.
     * 
     * @return true if CWM objects have been saved in file.
     */
    public boolean saveInFiles() {
        return emfUtil.save();
    }

    /**
     * Method "storeInResourceSet".
     * 
     * @param eObject the object to be saved
     * @param filename the file name to which the object must be saved
     * @return true (as per the general contract of the Collection.add method).
     */
    public boolean storeInResourceSet(EObject eObject, String filename) {
        return emfUtil.addPoolToResourceSet(new File(filename).toURI().toString(), eObject);
    }

    /**
     * Method "closeConnection".
     * 
     * @param connection the connection to close
     * @return true if connection has been closed.
     */
    public boolean closeConnection() {
        if (connection == null) {
            return true;
        }
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            log.error("Problem when closing connection", e);
            return false;
        }
    }

    /**
     * Method "connect". Opens a connection by using the DriverManager class. It is of the responsability of the caller
     * to close it correctly. When calling this method, you must call individual retrieveXXX() methods in order to get
     * the desired informations of the database.
     * 
     * @param dbUrl the url
     * @param driverClassName
     * @param props
     * @return false if problem. true if the connection is opened.
     * @throws SQLException
     */
    public boolean connect() throws SQLException {
        // --- check preconditions
        boolean ok = StringUtils.isNotBlank(databaseUrl) && StringUtils.isNotBlank(driverClass);
        // --- connect
        ok = ok && connectLow(this.databaseUrl, this.driverClass, this.connectionProperties);
        return ok;
    }

    /**
     * Method "connectAndRetrieveInformations". Opens a connection by using the DriverManager class. It is of the
     * responsability of the caller to close it correctly. This method retrieve all available informations of the
     * connected database.
     * 
     * @param dbUrl the url
     * @param driverClassName
     * @param props
     * @return false if problem. true if the connection is opened.
     */
    public boolean connectAndRetrieveInformations() {
        boolean ok = true;
        try {
            ok = connect();
            // --- initialize fields
            ok = ok && retrieveDeployedSystemInformations();
            ok = ok && retrieveDatabaseStructure();
        } catch (SQLException e) {
            log.error("Problem during connection to " + databaseUrl + " with " + driverClass, e);
            ok = false;
        }
        return ok;
    }

    @Override
    public String toString() {
        return "Connection " + databaseUrl;
    }

    /**
     * Method "retrieveDatabaseStructure".
     * 
     * @return ok if no problem
     * @throws SQLException
     */
    public boolean retrieveDatabaseStructure() throws SQLException {
        boolean ok = checkConnection("Cannot retrieve database structure. ");
        if (ok) {
            catalogBuilder = new CatalogBuilder(connection);
        }
        return ok;
    }

    /**
     * Method "checkConnection" checks that the connection is not null and not closed.
     * 
     * @param message the error message to log in case of problem.
     * @return true if ok
     * @throws SQLException
     */
    private boolean checkConnection(String message) throws SQLException {
        boolean ok = true;
        if (connection == null) {
            log.error(message + " Cause: connection is null." + this);
            ok = false;
        } else if (connection.isClosed()) {
            log.error(message + " Cause: connection is closed." + this);
            ok = false;
        }
        return ok;
    }

    /**
     * Method "retrieveDeployedSystemInformations". This method fills in the Software deployment information like the
     * SoftwareSystem.
     * 
     * @return true if ok
     * @throws SQLException
     */
    public boolean retrieveDeployedSystemInformations() throws SQLException {
        boolean ok = checkConnection("Cannot retrieve deployed system informations");
        if (ok) {
            softwareSystemBuilder = new SoftwareSystemBuilder(connection);
        }
        return ok;
    }

    /**
     * Method "connect". Opens a connection by using the DriverManager class. It is of the responsability of the caller
     * to close it correctly.
     * 
     * @param dbUrl the url
     * @param driverClassName
     * @param props
     * @return true if the connection is opened.
     * @throws SQLException
     */
    private boolean connectLow(String dbUrl, String driverClassName, Properties props) throws SQLException {
        boolean ok = true;
        try {
            Driver driver = (Driver) Class.forName(driverClassName).newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(dbUrl, props);
        } catch (InstantiationException e) {
            log.error(e);
            ok = false;
        } catch (IllegalAccessException e) {
            log.error(e);
            ok = false;
        } catch (ClassNotFoundException e) {
            log.error(e);
            ok = false;
        }
        return ok;
    }
}
