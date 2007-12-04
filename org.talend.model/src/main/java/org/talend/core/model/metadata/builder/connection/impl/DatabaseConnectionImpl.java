/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getDatabaseType <em>Database Type</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getDriverClass <em>Driver Class</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getURL <em>URL</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getPort <em>Port</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getUsername <em>Username</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getServerName <em>Server Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getDatasourceName <em>Datasource Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getFileFieldName <em>File Field Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getSID <em>SID</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getSqlSynthax <em>Sql Synthax</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getStringQuote <em>String Quote</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getNullChar <em>Null Char</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getDbmsId <em>Dbms Id</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getProductId <em>Product Id</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getDBRootPath <em>DB Root Path</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl#getAdditionalParams <em>Additional Params</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DatabaseConnectionImpl extends ConnectionImpl implements DatabaseConnection 
{
    /**
     * The default value of the '{@link #getDatabaseType() <em>Database Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDatabaseType()
     * @generated
     * @ordered
     */
    protected static final String DATABASE_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDatabaseType() <em>Database Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDatabaseType()
     * @generated
     * @ordered
     */
    protected String databaseType = DATABASE_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getDriverClass() <em>Driver Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDriverClass()
     * @generated
     * @ordered
     */
    protected static final String DRIVER_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDriverClass() <em>Driver Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDriverClass()
     * @generated
     * @ordered
     */
    protected String driverClass = DRIVER_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getURL() <em>URL</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getURL()
     * @generated
     * @ordered
     */
    protected static final String URL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getURL() <em>URL</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getURL()
     * @generated
     * @ordered
     */
    protected String url = URL_EDEFAULT;

    /**
     * The default value of the '{@link #getPort() <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPort()
     * @generated
     * @ordered
     */
    protected static final String PORT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPort() <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPort()
     * @generated
     * @ordered
     */
    protected String port = PORT_EDEFAULT;

    /**
     * The default value of the '{@link #getUsername() <em>Username</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUsername()
     * @generated
     * @ordered
     */
    protected static final String USERNAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUsername() <em>Username</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUsername()
     * @generated
     * @ordered
     */
    protected String username = USERNAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPassword()
     * @generated
     * @ordered
     */
    protected static final String PASSWORD_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPassword()
     * @generated
     * @ordered
     */
    protected String password = PASSWORD_EDEFAULT;

    /**
     * The default value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getServerName()
     * @generated
     * @ordered
     */
    protected static final String SERVER_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getServerName()
     * @generated
     * @ordered
     */
    protected String serverName = SERVER_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDatasourceName() <em>Datasource Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDatasourceName()
     * @generated
     * @ordered
     */
    protected static final String DATASOURCE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDatasourceName() <em>Datasource Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDatasourceName()
     * @generated
     * @ordered
     */
    protected String datasourceName = DATASOURCE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getFileFieldName() <em>File Field Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFileFieldName()
     * @generated
     * @ordered
     */
    protected static final String FILE_FIELD_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFileFieldName() <em>File Field Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFileFieldName()
     * @generated
     * @ordered
     */
    protected String fileFieldName = FILE_FIELD_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getSchema() <em>Schema</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSchema()
     * @generated
     * @ordered
     */
    protected static final String SCHEMA_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSchema() <em>Schema</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSchema()
     * @generated
     * @ordered
     */
    protected String schema = SCHEMA_EDEFAULT;

    /**
     * The default value of the '{@link #getSID() <em>SID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSID()
     * @generated
     * @ordered
     */
    protected static final String SID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSID() <em>SID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSID()
     * @generated
     * @ordered
     */
    protected String sid = SID_EDEFAULT;

    /**
     * The default value of the '{@link #getSqlSynthax() <em>Sql Synthax</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSqlSynthax()
     * @generated
     * @ordered
     */
    protected static final String SQL_SYNTHAX_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSqlSynthax() <em>Sql Synthax</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSqlSynthax()
     * @generated
     * @ordered
     */
    protected String sqlSynthax = SQL_SYNTHAX_EDEFAULT;

    /**
     * The default value of the '{@link #getStringQuote() <em>String Quote</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringQuote()
     * @generated
     * @ordered
     */
    protected static final String STRING_QUOTE_EDEFAULT = "\""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getStringQuote() <em>String Quote</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringQuote()
     * @generated
     * @ordered
     */
    protected String stringQuote = STRING_QUOTE_EDEFAULT;

    /**
     * The default value of the '{@link #getNullChar() <em>Null Char</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullChar()
     * @generated
     * @ordered
     */
    protected static final String NULL_CHAR_EDEFAULT = "000"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getNullChar() <em>Null Char</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullChar()
     * @generated
     * @ordered
     */
    protected String nullChar = NULL_CHAR_EDEFAULT;

    /**
     * The default value of the '{@link #getDbmsId() <em>Dbms Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDbmsId()
     * @generated
     * @ordered
     */
    protected static final String DBMS_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDbmsId() <em>Dbms Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDbmsId()
     * @generated
     * @ordered
     */
    protected String dbmsId = DBMS_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getProductId() <em>Product Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProductId()
     * @generated
     * @ordered
     */
    protected static final String PRODUCT_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProductId() <em>Product Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProductId()
     * @generated
     * @ordered
     */
    protected String productId = PRODUCT_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getDBRootPath() <em>DB Root Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDBRootPath()
     * @generated
     * @ordered
     */
    protected static final String DB_ROOT_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDBRootPath() <em>DB Root Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDBRootPath()
     * @generated
     * @ordered
     */
    protected String dbRootPath = DB_ROOT_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getAdditionalParams() <em>Additional Params</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAdditionalParams()
     * @generated
     * @ordered
     */
    protected static final String ADDITIONAL_PARAMS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAdditionalParams() <em>Additional Params</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAdditionalParams()
     * @generated
     * @ordered
     */
    protected String additionalParams = ADDITIONAL_PARAMS_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DatabaseConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.DATABASE_CONNECTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDatabaseType() {
        return databaseType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDatabaseType(String newDatabaseType) {
        String oldDatabaseType = databaseType;
        databaseType = newDatabaseType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__DATABASE_TYPE, oldDatabaseType, databaseType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDriverClass() {
        return driverClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDriverClass(String newDriverClass) {
        String oldDriverClass = driverClass;
        driverClass = newDriverClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__DRIVER_CLASS, oldDriverClass, driverClass));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getURL() {
        return url;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setURL(String newURL) {
        String oldURL = url;
        url = newURL;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__URL, oldURL, url));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPort() {
        return port;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPort(String newPort) {
        String oldPort = port;
        port = newPort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__PORT, oldPort, port));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUsername(String newUsername) {
        String oldUsername = username;
        username = newUsername;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__USERNAME, oldUsername, username));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPassword(String newPassword) {
        String oldPassword = password;
        password = newPassword;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__PASSWORD, oldPassword, password));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setServerName(String newServerName) {
        String oldServerName = serverName;
        serverName = newServerName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__SERVER_NAME, oldServerName, serverName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDatasourceName() {
        return datasourceName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDatasourceName(String newDatasourceName) {
        String oldDatasourceName = datasourceName;
        datasourceName = newDatasourceName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__DATASOURCE_NAME, oldDatasourceName, datasourceName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFileFieldName() {
        return fileFieldName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFileFieldName(String newFileFieldName) {
        String oldFileFieldName = fileFieldName;
        fileFieldName = newFileFieldName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__FILE_FIELD_NAME, oldFileFieldName, fileFieldName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSchema() {
        return schema;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSchema(String newSchema) {
        String oldSchema = schema;
        schema = newSchema;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__SCHEMA, oldSchema, schema));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSID() {
        return sid;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSID(String newSID) {
        String oldSID = sid;
        sid = newSID;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__SID, oldSID, sid));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSqlSynthax() {
        return sqlSynthax;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSqlSynthax(String newSqlSynthax) {
        String oldSqlSynthax = sqlSynthax;
        sqlSynthax = newSqlSynthax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__SQL_SYNTHAX, oldSqlSynthax, sqlSynthax));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStringQuote() {
        return stringQuote;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStringQuote(String newStringQuote) {
        String oldStringQuote = stringQuote;
        stringQuote = newStringQuote;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__STRING_QUOTE, oldStringQuote, stringQuote));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNullChar() {
        return nullChar;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNullChar(String newNullChar) {
        String oldNullChar = nullChar;
        nullChar = newNullChar;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__NULL_CHAR, oldNullChar, nullChar));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDbmsId() {
        return dbmsId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDbmsId(String newDbmsId) {
        String oldDbmsId = dbmsId;
        dbmsId = newDbmsId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__DBMS_ID, oldDbmsId, dbmsId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getProductId() {
        return productId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProductId(String newProductId) {
        String oldProductId = productId;
        productId = newProductId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__PRODUCT_ID, oldProductId, productId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDBRootPath() {
        return dbRootPath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDBRootPath(String newDBRootPath) {
        String oldDBRootPath = dbRootPath;
        dbRootPath = newDBRootPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__DB_ROOT_PATH, oldDBRootPath, dbRootPath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAdditionalParams() {
        return additionalParams;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAdditionalParams(String newAdditionalParams) {
        String oldAdditionalParams = additionalParams;
        additionalParams = newAdditionalParams;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.DATABASE_CONNECTION__ADDITIONAL_PARAMS, oldAdditionalParams, additionalParams));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectionPackage.DATABASE_CONNECTION__DATABASE_TYPE:
                return getDatabaseType();
            case ConnectionPackage.DATABASE_CONNECTION__DRIVER_CLASS:
                return getDriverClass();
            case ConnectionPackage.DATABASE_CONNECTION__URL:
                return getURL();
            case ConnectionPackage.DATABASE_CONNECTION__PORT:
                return getPort();
            case ConnectionPackage.DATABASE_CONNECTION__USERNAME:
                return getUsername();
            case ConnectionPackage.DATABASE_CONNECTION__PASSWORD:
                return getPassword();
            case ConnectionPackage.DATABASE_CONNECTION__SERVER_NAME:
                return getServerName();
            case ConnectionPackage.DATABASE_CONNECTION__DATASOURCE_NAME:
                return getDatasourceName();
            case ConnectionPackage.DATABASE_CONNECTION__FILE_FIELD_NAME:
                return getFileFieldName();
            case ConnectionPackage.DATABASE_CONNECTION__SCHEMA:
                return getSchema();
            case ConnectionPackage.DATABASE_CONNECTION__SID:
                return getSID();
            case ConnectionPackage.DATABASE_CONNECTION__SQL_SYNTHAX:
                return getSqlSynthax();
            case ConnectionPackage.DATABASE_CONNECTION__STRING_QUOTE:
                return getStringQuote();
            case ConnectionPackage.DATABASE_CONNECTION__NULL_CHAR:
                return getNullChar();
            case ConnectionPackage.DATABASE_CONNECTION__DBMS_ID:
                return getDbmsId();
            case ConnectionPackage.DATABASE_CONNECTION__PRODUCT_ID:
                return getProductId();
            case ConnectionPackage.DATABASE_CONNECTION__DB_ROOT_PATH:
                return getDBRootPath();
            case ConnectionPackage.DATABASE_CONNECTION__ADDITIONAL_PARAMS:
                return getAdditionalParams();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ConnectionPackage.DATABASE_CONNECTION__DATABASE_TYPE:
                setDatabaseType((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__DRIVER_CLASS:
                setDriverClass((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__URL:
                setURL((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__PORT:
                setPort((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__USERNAME:
                setUsername((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__PASSWORD:
                setPassword((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__SERVER_NAME:
                setServerName((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__DATASOURCE_NAME:
                setDatasourceName((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__FILE_FIELD_NAME:
                setFileFieldName((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__SCHEMA:
                setSchema((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__SID:
                setSID((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__SQL_SYNTHAX:
                setSqlSynthax((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__STRING_QUOTE:
                setStringQuote((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__NULL_CHAR:
                setNullChar((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__DBMS_ID:
                setDbmsId((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__PRODUCT_ID:
                setProductId((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__DB_ROOT_PATH:
                setDBRootPath((String)newValue);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__ADDITIONAL_PARAMS:
                setAdditionalParams((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case ConnectionPackage.DATABASE_CONNECTION__DATABASE_TYPE:
                setDatabaseType(DATABASE_TYPE_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__DRIVER_CLASS:
                setDriverClass(DRIVER_CLASS_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__URL:
                setURL(URL_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__PORT:
                setPort(PORT_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__USERNAME:
                setUsername(USERNAME_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__PASSWORD:
                setPassword(PASSWORD_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__SERVER_NAME:
                setServerName(SERVER_NAME_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__DATASOURCE_NAME:
                setDatasourceName(DATASOURCE_NAME_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__FILE_FIELD_NAME:
                setFileFieldName(FILE_FIELD_NAME_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__SCHEMA:
                setSchema(SCHEMA_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__SID:
                setSID(SID_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__SQL_SYNTHAX:
                setSqlSynthax(SQL_SYNTHAX_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__STRING_QUOTE:
                setStringQuote(STRING_QUOTE_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__NULL_CHAR:
                setNullChar(NULL_CHAR_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__DBMS_ID:
                setDbmsId(DBMS_ID_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__PRODUCT_ID:
                setProductId(PRODUCT_ID_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__DB_ROOT_PATH:
                setDBRootPath(DB_ROOT_PATH_EDEFAULT);
                return;
            case ConnectionPackage.DATABASE_CONNECTION__ADDITIONAL_PARAMS:
                setAdditionalParams(ADDITIONAL_PARAMS_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ConnectionPackage.DATABASE_CONNECTION__DATABASE_TYPE:
                return DATABASE_TYPE_EDEFAULT == null ? databaseType != null : !DATABASE_TYPE_EDEFAULT.equals(databaseType);
            case ConnectionPackage.DATABASE_CONNECTION__DRIVER_CLASS:
                return DRIVER_CLASS_EDEFAULT == null ? driverClass != null : !DRIVER_CLASS_EDEFAULT.equals(driverClass);
            case ConnectionPackage.DATABASE_CONNECTION__URL:
                return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
            case ConnectionPackage.DATABASE_CONNECTION__PORT:
                return PORT_EDEFAULT == null ? port != null : !PORT_EDEFAULT.equals(port);
            case ConnectionPackage.DATABASE_CONNECTION__USERNAME:
                return USERNAME_EDEFAULT == null ? username != null : !USERNAME_EDEFAULT.equals(username);
            case ConnectionPackage.DATABASE_CONNECTION__PASSWORD:
                return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
            case ConnectionPackage.DATABASE_CONNECTION__SERVER_NAME:
                return SERVER_NAME_EDEFAULT == null ? serverName != null : !SERVER_NAME_EDEFAULT.equals(serverName);
            case ConnectionPackage.DATABASE_CONNECTION__DATASOURCE_NAME:
                return DATASOURCE_NAME_EDEFAULT == null ? datasourceName != null : !DATASOURCE_NAME_EDEFAULT.equals(datasourceName);
            case ConnectionPackage.DATABASE_CONNECTION__FILE_FIELD_NAME:
                return FILE_FIELD_NAME_EDEFAULT == null ? fileFieldName != null : !FILE_FIELD_NAME_EDEFAULT.equals(fileFieldName);
            case ConnectionPackage.DATABASE_CONNECTION__SCHEMA:
                return SCHEMA_EDEFAULT == null ? schema != null : !SCHEMA_EDEFAULT.equals(schema);
            case ConnectionPackage.DATABASE_CONNECTION__SID:
                return SID_EDEFAULT == null ? sid != null : !SID_EDEFAULT.equals(sid);
            case ConnectionPackage.DATABASE_CONNECTION__SQL_SYNTHAX:
                return SQL_SYNTHAX_EDEFAULT == null ? sqlSynthax != null : !SQL_SYNTHAX_EDEFAULT.equals(sqlSynthax);
            case ConnectionPackage.DATABASE_CONNECTION__STRING_QUOTE:
                return STRING_QUOTE_EDEFAULT == null ? stringQuote != null : !STRING_QUOTE_EDEFAULT.equals(stringQuote);
            case ConnectionPackage.DATABASE_CONNECTION__NULL_CHAR:
                return NULL_CHAR_EDEFAULT == null ? nullChar != null : !NULL_CHAR_EDEFAULT.equals(nullChar);
            case ConnectionPackage.DATABASE_CONNECTION__DBMS_ID:
                return DBMS_ID_EDEFAULT == null ? dbmsId != null : !DBMS_ID_EDEFAULT.equals(dbmsId);
            case ConnectionPackage.DATABASE_CONNECTION__PRODUCT_ID:
                return PRODUCT_ID_EDEFAULT == null ? productId != null : !PRODUCT_ID_EDEFAULT.equals(productId);
            case ConnectionPackage.DATABASE_CONNECTION__DB_ROOT_PATH:
                return DB_ROOT_PATH_EDEFAULT == null ? dbRootPath != null : !DB_ROOT_PATH_EDEFAULT.equals(dbRootPath);
            case ConnectionPackage.DATABASE_CONNECTION__ADDITIONAL_PARAMS:
                return ADDITIONAL_PARAMS_EDEFAULT == null ? additionalParams != null : !ADDITIONAL_PARAMS_EDEFAULT.equals(additionalParams);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (DatabaseType: ");
        result.append(databaseType);
        result.append(", DriverClass: ");
        result.append(driverClass);
        result.append(", URL: ");
        result.append(url);
        result.append(", Port: ");
        result.append(port);
        result.append(", Username: ");
        result.append(username);
        result.append(", Password: ");
        result.append(password);
        result.append(", ServerName: ");
        result.append(serverName);
        result.append(", DatasourceName: ");
        result.append(datasourceName);
        result.append(", FileFieldName: ");
        result.append(fileFieldName);
        result.append(", Schema: ");
        result.append(schema);
        result.append(", SID: ");
        result.append(sid);
        result.append(", SqlSynthax: ");
        result.append(sqlSynthax);
        result.append(", StringQuote: ");
        result.append(stringQuote);
        result.append(", NullChar: ");
        result.append(nullChar);
        result.append(", DbmsId: ");
        result.append(dbmsId);
        result.append(", ProductId: ");
        result.append(productId);
        result.append(", DBRootPath: ");
        result.append(dbRootPath);
        result.append(", AdditionalParams: ");
        result.append(additionalParams);
        result.append(')');
        return result.toString();
    }

} //DatabaseConnectionImpl