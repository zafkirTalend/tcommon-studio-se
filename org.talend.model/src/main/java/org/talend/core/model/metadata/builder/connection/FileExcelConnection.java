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
 * A representation of the model object '<em><b>File Excel Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.FileExcelConnection#getSheetName <em>Sheet Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.FileExcelConnection#getSheetColumns <em>Sheet Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getFileExcelConnection()
 * @model
 * @generated
 */
public interface FileExcelConnection extends FileConnection {
    /**
     * Returns the value of the '<em><b>Sheet Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sheet Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sheet Name</em>' attribute.
     * @see #setSheetName(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getFileExcelConnection_SheetName()
     * @model required="true"
     * @generated
     */
    String getSheetName();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.FileExcelConnection#getSheetName <em>Sheet Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sheet Name</em>' attribute.
     * @see #getSheetName()
     * @generated
     */
    void setSheetName(String value);

    /**
     * Returns the value of the '<em><b>Sheet Columns</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sheet Columns</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sheet Columns</em>' attribute list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getFileExcelConnection_SheetColumns()
     * @model
     * @generated
     */
    EList getSheetColumns();

} // FileExcelConnection
