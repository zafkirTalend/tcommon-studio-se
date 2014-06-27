/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SAP Table Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPTableField#getRefTable <em>Ref Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPTableField()
 * @model
 * @generated
 */
public interface SAPTableField extends MetadataColumn {

    /**
     * Returns the value of the '<em><b>Ref Table</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ref Table</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ref Table</em>' attribute list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPTableField_RefTable()
     * @model
     * @generated
     */
    EList<String> getRefTable();

} // SAPTableField
