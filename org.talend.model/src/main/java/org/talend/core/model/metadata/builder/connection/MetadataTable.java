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
 * A representation of the model object '<em><b>Metadata Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getSourceName <em>Source Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataTable()
 * @model
 * @generated
 */
public interface MetadataTable extends AbstractMetadataObject
{
  /**
   * Returns the value of the '<em><b>Source Name</b></em>' attribute.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Source Name</em>' attribute.
   * @see #setSourceName(String)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataTable_SourceName()
   * @model
   * @generated
   */
    String getSourceName();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getSourceName <em>Source Name</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Name</em>' attribute.
   * @see #getSourceName()
   * @generated
   */
    void setSourceName(String value);

  /**
   * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
   * The list contents are of type {@link org.talend.core.model.metadata.builder.connection.MetadataColumn}.
   * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getTable <em>Table</em>}'.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Columns</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Columns</em>' containment reference list.
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataTable_Columns()
   * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#getTable
   * @model type="org.talend.core.model.metadata.builder.connection.MetadataColumn" opposite="table" containment="true"
   * @generated
   */
    EList getColumns();

  /**
   * Returns the value of the '<em><b>Connection</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.Connection#getTables <em>Tables</em>}'.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connection</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Connection</em>' container reference.
   * @see #setConnection(Connection)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataTable_Connection()
   * @see org.talend.core.model.metadata.builder.connection.Connection#getTables
   * @model opposite="tables"
   * @generated
   */
    Connection getConnection();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getConnection <em>Connection</em>}' container reference.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @param value the new value of the '<em>Connection</em>' container reference.
   * @see #getConnection()
   * @generated
   */
    void setConnection(Connection value);

} // MetadataTable