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
 * A representation of the model object '<em><b>Ldif File Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.LdifFileConnection#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getLdifFileConnection()
 * @model
 * @generated
 */
public interface LdifFileConnection extends Connection {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getLdifFileConnection_Value()
     * @model type="java.lang.String"
     * @generated
     */
    EList getValue();

} // LdifFileConnection