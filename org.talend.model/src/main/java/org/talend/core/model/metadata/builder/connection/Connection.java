/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.Connection#getVersion <em>Version</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.Connection#getTables <em>Tables</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.Connection#getQueries <em>Queries</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection extends AbstractMetadataObject {
    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getConnection_Version()
     * @model
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.Connection#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Tables</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.metadata.builder.connection.MetadataTable}.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tables</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tables</em>' containment reference list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getConnection_Tables()
     * @see org.talend.core.model.metadata.builder.connection.MetadataTable#getConnection
     * @model type="org.talend.core.model.metadata.builder.connection.MetadataTable" opposite="connection" containment="true"
     * @generated
     */
    EList getTables();

    /**
     * Returns the value of the '<em><b>Queries</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.QueriesConnection#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Queries</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Queries</em>' containment reference.
     * @see #setQueries(QueriesConnection)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getConnection_Queries()
     * @see org.talend.core.model.metadata.builder.connection.QueriesConnection#getConnection
     * @model opposite="connection" containment="true"
     * @generated
     */
    QueriesConnection getQueries();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.Connection#getQueries <em>Queries</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Queries</em>' containment reference.
     * @see #getQueries()
     * @generated
     */
    void setQueries(QueriesConnection value);

} // Connection