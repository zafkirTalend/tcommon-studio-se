/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Metadata Object</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getComment <em>Comment</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getLabel <em>Label</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#isReadOnly <em>Read Only</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getAbstractMetadataObject()
 * @model abstract="true"
 * @generated
 */
public interface AbstractMetadataObject extends EObject
{

  /**
   * Returns the value of the '<em><b>Properties</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Properties</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Properties</em>' attribute.
   * @see #setProperties(HashMap)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getAbstractMetadataObject_Properties()
   * @model default="" dataType="org.talend.core.model.metadata.builder.connection.Map" required="true"
   * @generated
   */
    HashMap getProperties();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getProperties <em>Properties</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Properties</em>' attribute.
   * @see #getProperties()
   * @generated
   */
    void setProperties(HashMap value);

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getAbstractMetadataObject_Id()
   * @model id="true" required="true"
   * @generated
   */
    String getId();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Comment</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comment</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Comment</em>' attribute.
     * @see #setComment(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getAbstractMetadataObject_Comment()
     * @model default=""
     * @generated
     */
    String getComment();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
    void setComment(String value);

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getAbstractMetadataObject_Label()
   * @model required="true"
   * @generated
   */
    String getLabel();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
    void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Read Only</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Read Only</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Read Only</em>' attribute.
   * @see #setReadOnly(boolean)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getAbstractMetadataObject_ReadOnly()
   * @model default="false" volatile="true"
   * @generated
   */
    boolean isReadOnly();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#isReadOnly <em>Read Only</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Read Only</em>' attribute.
   * @see #isReadOnly()
   * @generated
   */
    void setReadOnly(boolean value);

} // AbstractMetadataObject
