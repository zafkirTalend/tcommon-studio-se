/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Queries Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.QueriesConnection#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.QueriesConnection#getQuery <em>Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getQueriesConnection()
 * @model
 * @generated
 */
public interface QueriesConnection extends EObject {
    /**
     * Returns the value of the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connection</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Connection</em>' reference.
     * @see #setConnection(DatabaseConnection)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getQueriesConnection_Connection()
     * @model resolveProxies="false"
     * @generated
     */
    DatabaseConnection getConnection();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.QueriesConnection#getConnection <em>Connection</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Connection</em>' reference.
     * @see #getConnection()
     * @generated
     */
    void setConnection(DatabaseConnection value);

    /**
     * Returns the value of the '<em><b>Query</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.metadata.builder.connection.Query}.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.Query#getQueries <em>Queries</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' containment reference list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getQueriesConnection_Query()
     * @see org.talend.core.model.metadata.builder.connection.Query#getQueries
     * @model type="org.talend.core.model.metadata.builder.connection.Query" opposite="queries" containment="true"
     * @generated
     */
    EList getQuery();

} // QueriesConnection