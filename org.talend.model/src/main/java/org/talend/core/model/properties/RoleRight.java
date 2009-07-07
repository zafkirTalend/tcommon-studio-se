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
 * A representation of the model object '<em><b>Role Right</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.RoleRight#getRole <em>Role</em>}</li>
 *   <li>{@link org.talend.core.model.properties.RoleRight#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getRoleRight()
 * @model
 * @generated
 */
public interface RoleRight extends EObject {
    /**
     * Returns the value of the '<em><b>Role</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.UserRole#getRolesRights <em>Roles Rights</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Role</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Role</em>' reference.
     * @see #setRole(UserRole)
     * @see org.talend.core.model.properties.PropertiesPackage#getRoleRight_Role()
     * @see org.talend.core.model.properties.UserRole#getRolesRights
     * @model opposite="rolesRights"
     * @generated
     */
    UserRole getRole();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.RoleRight#getRole <em>Role</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Role</em>' reference.
     * @see #getRole()
     * @generated
     */
    void setRole(UserRole value);

    /**
     * Returns the value of the '<em><b>Right</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.Right#getRolesRights <em>Roles Rights</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Right</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Right</em>' reference.
     * @see #setRight(Right)
     * @see org.talend.core.model.properties.PropertiesPackage#getRoleRight_Right()
     * @see org.talend.core.model.properties.Right#getRolesRights
     * @model opposite="rolesRights"
     * @generated
     */
    Right getRight();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.RoleRight#getRight <em>Right</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right</em>' reference.
     * @see #getRight()
     * @generated
     */
    void setRight(Right value);

} // RoleRight
