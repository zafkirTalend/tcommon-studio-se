/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Salesforce Schema Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getWebServiceUrl <em>Web Service Url</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getUserName <em>User Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getPassword <em>Password</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getQueryCondition <em>Query Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSalesforceSchemaConnection()
 * @model
 * @generated
 */
public interface SalesforceSchemaConnection extends Connection {
    /**
     * Returns the value of the '<em><b>Web Service Url</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Web Service Url</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Web Service Url</em>' attribute.
     * @see #setWebServiceUrl(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSalesforceSchemaConnection_WebServiceUrl()
     * @model
     * @generated
     */
    String getWebServiceUrl();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getWebServiceUrl <em>Web Service Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Web Service Url</em>' attribute.
     * @see #getWebServiceUrl()
     * @generated
     */
    void setWebServiceUrl(String value);

    /**
     * Returns the value of the '<em><b>User Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>User Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>User Name</em>' attribute.
     * @see #setUserName(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSalesforceSchemaConnection_UserName()
     * @model
     * @generated
     */
    String getUserName();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getUserName <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>User Name</em>' attribute.
     * @see #getUserName()
     * @generated
     */
    void setUserName(String value);

    /**
     * Returns the value of the '<em><b>Password</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Password</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Password</em>' attribute.
     * @see #setPassword(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSalesforceSchemaConnection_Password()
     * @model
     * @generated
     */
    String getPassword();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getPassword <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Password</em>' attribute.
     * @see #getPassword()
     * @generated
     */
    void setPassword(String value);

    /**
     * Returns the value of the '<em><b>Module Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Module Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Module Name</em>' attribute.
     * @see #setModuleName(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSalesforceSchemaConnection_ModuleName()
     * @model
     * @generated
     */
    String getModuleName();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getModuleName <em>Module Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Module Name</em>' attribute.
     * @see #getModuleName()
     * @generated
     */
    void setModuleName(String value);

    /**
     * Returns the value of the '<em><b>Query Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Condition</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Condition</em>' attribute.
     * @see #setQueryCondition(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSalesforceSchemaConnection_QueryCondition()
     * @model
     * @generated
     */
    String getQueryCondition();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SalesforceSchemaConnection#getQueryCondition <em>Query Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Condition</em>' attribute.
     * @see #getQueryCondition()
     * @generated
     */
    void setQueryCondition(String value);

} // SalesforceSchemaConnection
