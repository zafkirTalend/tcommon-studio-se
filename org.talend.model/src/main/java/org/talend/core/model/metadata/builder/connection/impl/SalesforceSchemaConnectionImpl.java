/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Salesforce Schema Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SalesforceSchemaConnectionImpl#getWebServiceUrl <em>Web Service Url</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SalesforceSchemaConnectionImpl#getUserName <em>User Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SalesforceSchemaConnectionImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SalesforceSchemaConnectionImpl#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SalesforceSchemaConnectionImpl#getQueryCondition <em>Query Condition</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SalesforceSchemaConnectionImpl#isUseCustomModuleName <em>Use Custom Module Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SalesforceSchemaConnectionImpl extends ConnectionImpl implements SalesforceSchemaConnection {
    /**
     * The default value of the '{@link #getWebServiceUrl() <em>Web Service Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWebServiceUrl()
     * @generated
     * @ordered
     */
    protected static final String WEB_SERVICE_URL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWebServiceUrl() <em>Web Service Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWebServiceUrl()
     * @generated
     * @ordered
     */
    protected String webServiceUrl = WEB_SERVICE_URL_EDEFAULT;

    /**
     * The default value of the '{@link #getUserName() <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserName()
     * @generated
     * @ordered
     */
    protected static final String USER_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserName() <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserName()
     * @generated
     * @ordered
     */
    protected String userName = USER_NAME_EDEFAULT;

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
     * The default value of the '{@link #getModuleName() <em>Module Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModuleName()
     * @generated
     * @ordered
     */
    protected static final String MODULE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getModuleName() <em>Module Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModuleName()
     * @generated
     * @ordered
     */
    protected String moduleName = MODULE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getQueryCondition() <em>Query Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueryCondition()
     * @generated
     * @ordered
     */
    protected static final String QUERY_CONDITION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getQueryCondition() <em>Query Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueryCondition()
     * @generated
     * @ordered
     */
    protected String queryCondition = QUERY_CONDITION_EDEFAULT;

    /**
     * The default value of the '{@link #isUseCustomModuleName() <em>Use Custom Module Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isUseCustomModuleName()
     * @generated
     * @ordered
     */
    protected static final boolean USE_CUSTOM_MODULE_NAME_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUseCustomModuleName() <em>Use Custom Module Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isUseCustomModuleName()
     * @generated
     * @ordered
     */
    protected boolean useCustomModuleName = USE_CUSTOM_MODULE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SalesforceSchemaConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.SALESFORCE_SCHEMA_CONNECTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getWebServiceUrl() {
        return webServiceUrl;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWebServiceUrl(String newWebServiceUrl) {
        String oldWebServiceUrl = webServiceUrl;
        webServiceUrl = newWebServiceUrl;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__WEB_SERVICE_URL, oldWebServiceUrl, webServiceUrl));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserName(String newUserName) {
        String oldUserName = userName;
        userName = newUserName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USER_NAME, oldUserName, userName));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__PASSWORD, oldPassword, password));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setModuleName(String newModuleName) {
        String oldModuleName = moduleName;
        moduleName = newModuleName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__MODULE_NAME, oldModuleName, moduleName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getQueryCondition() {
        return queryCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueryCondition(String newQueryCondition) {
        String oldQueryCondition = queryCondition;
        queryCondition = newQueryCondition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__QUERY_CONDITION, oldQueryCondition, queryCondition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isUseCustomModuleName() {
        return useCustomModuleName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUseCustomModuleName(boolean newUseCustomModuleName) {
        boolean oldUseCustomModuleName = useCustomModuleName;
        useCustomModuleName = newUseCustomModuleName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USE_CUSTOM_MODULE_NAME, oldUseCustomModuleName, useCustomModuleName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__WEB_SERVICE_URL:
                return getWebServiceUrl();
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USER_NAME:
                return getUserName();
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__PASSWORD:
                return getPassword();
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__MODULE_NAME:
                return getModuleName();
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__QUERY_CONDITION:
                return getQueryCondition();
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USE_CUSTOM_MODULE_NAME:
                return isUseCustomModuleName() ? Boolean.TRUE : Boolean.FALSE;
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
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__WEB_SERVICE_URL:
                setWebServiceUrl((String)newValue);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USER_NAME:
                setUserName((String)newValue);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__PASSWORD:
                setPassword((String)newValue);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__MODULE_NAME:
                setModuleName((String)newValue);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__QUERY_CONDITION:
                setQueryCondition((String)newValue);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USE_CUSTOM_MODULE_NAME:
                setUseCustomModuleName(((Boolean)newValue).booleanValue());
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
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__WEB_SERVICE_URL:
                setWebServiceUrl(WEB_SERVICE_URL_EDEFAULT);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USER_NAME:
                setUserName(USER_NAME_EDEFAULT);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__PASSWORD:
                setPassword(PASSWORD_EDEFAULT);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__MODULE_NAME:
                setModuleName(MODULE_NAME_EDEFAULT);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__QUERY_CONDITION:
                setQueryCondition(QUERY_CONDITION_EDEFAULT);
                return;
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USE_CUSTOM_MODULE_NAME:
                setUseCustomModuleName(USE_CUSTOM_MODULE_NAME_EDEFAULT);
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
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__WEB_SERVICE_URL:
                return WEB_SERVICE_URL_EDEFAULT == null ? webServiceUrl != null : !WEB_SERVICE_URL_EDEFAULT.equals(webServiceUrl);
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USER_NAME:
                return USER_NAME_EDEFAULT == null ? userName != null : !USER_NAME_EDEFAULT.equals(userName);
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__PASSWORD:
                return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__MODULE_NAME:
                return MODULE_NAME_EDEFAULT == null ? moduleName != null : !MODULE_NAME_EDEFAULT.equals(moduleName);
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__QUERY_CONDITION:
                return QUERY_CONDITION_EDEFAULT == null ? queryCondition != null : !QUERY_CONDITION_EDEFAULT.equals(queryCondition);
            case ConnectionPackage.SALESFORCE_SCHEMA_CONNECTION__USE_CUSTOM_MODULE_NAME:
                return useCustomModuleName != USE_CUSTOM_MODULE_NAME_EDEFAULT;
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
        result.append(" (webServiceUrl: ");
        result.append(webServiceUrl);
        result.append(", userName: ");
        result.append(userName);
        result.append(", password: ");
        result.append(password);
        result.append(", moduleName: ");
        result.append(moduleName);
        result.append(", queryCondition: ");
        result.append(queryCondition);
        result.append(", useCustomModuleName: ");
        result.append(useCustomModuleName);
        result.append(')');
        return result.toString();
    }

} //SalesforceSchemaConnectionImpl
