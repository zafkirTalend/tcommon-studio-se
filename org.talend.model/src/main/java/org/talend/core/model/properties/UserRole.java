/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.UserRole#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.UserRole#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.core.model.properties.UserRole#getLocalizedLabel <em>Localized Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getUserRole()
 * @model
 * @generated
 */
public interface UserRole extends EObject {
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
     * @see org.talend.core.model.properties.PropertiesPackage#getUserRole_Id()
     * @model id="true" required="true"
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.UserRole#getId <em>Id</em>}' attribute.
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
     * @see org.talend.core.model.properties.PropertiesPackage#getUserRole_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.UserRole#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Localized Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Localized Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Localized Label</em>' attribute.
     * @see #setLocalizedLabel(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getUserRole_LocalizedLabel()
     * @model required="true"
     * @generated
     */
    String getLocalizedLabel();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.UserRole#getLocalizedLabel <em>Localized Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Localized Label</em>' attribute.
     * @see #getLocalizedLabel()
     * @generated
     */
    void setLocalizedLabel(String value);

} // UserRole