/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Right</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.Right#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.Right#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.core.model.properties.Right#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.core.model.properties.Right#getRolesRights <em>Roles Rights</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getRight()
 * @model
 * @generated
 */
public interface Right extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(int)
     * @see org.talend.core.model.properties.PropertiesPackage#getRight_Id()
     * @model id="true" required="true"
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.Right#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(int value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getRight_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.Right#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getRight_Description()
     * @model required="true"
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.Right#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Roles Rights</b></em>' reference list.
     * The list contents are of type {@link org.talend.core.model.properties.RoleRight}.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.RoleRight#getRight <em>Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Roles Rights</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Roles Rights</em>' reference list.
     * @see org.talend.core.model.properties.PropertiesPackage#getRight_RolesRights()
     * @see org.talend.core.model.properties.RoleRight#getRight
     * @model type="org.talend.core.model.properties.RoleRight" opposite="right" ordered="false"
     * @generated
     */
    EList getRolesRights();

} // Right
