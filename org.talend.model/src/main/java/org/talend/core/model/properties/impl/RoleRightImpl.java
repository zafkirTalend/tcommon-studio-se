/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Right;
import org.talend.core.model.properties.RoleRight;
import org.talend.core.model.properties.UserRole;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Right</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.RoleRightImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.RoleRightImpl#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleRightImpl extends EObjectImpl implements RoleRight {
    /**
     * The cached value of the '{@link #getRole() <em>Role</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRole()
     * @generated
     * @ordered
     */
    protected UserRole role;

    /**
     * The cached value of the '{@link #getRight() <em>Right</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRight()
     * @generated
     * @ordered
     */
    protected Right right;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RoleRightImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ROLE_RIGHT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UserRole getRole() {
        if (role != null && role.eIsProxy()) {
            InternalEObject oldRole = (InternalEObject)role;
            role = (UserRole)eResolveProxy(oldRole);
            if (role != oldRole) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ROLE_RIGHT__ROLE, oldRole, role));
            }
        }
        return role;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UserRole basicGetRole() {
        return role;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRole(UserRole newRole, NotificationChain msgs) {
        UserRole oldRole = role;
        role = newRole;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ROLE_RIGHT__ROLE, oldRole, newRole);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRole(UserRole newRole) {
        if (newRole != role) {
            NotificationChain msgs = null;
            if (role != null)
                msgs = ((InternalEObject)role).eInverseRemove(this, PropertiesPackage.USER_ROLE__ROLES_RIGHTS, UserRole.class, msgs);
            if (newRole != null)
                msgs = ((InternalEObject)newRole).eInverseAdd(this, PropertiesPackage.USER_ROLE__ROLES_RIGHTS, UserRole.class, msgs);
            msgs = basicSetRole(newRole, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ROLE_RIGHT__ROLE, newRole, newRole));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Right getRight() {
        if (right != null && right.eIsProxy()) {
            InternalEObject oldRight = (InternalEObject)right;
            right = (Right)eResolveProxy(oldRight);
            if (right != oldRight) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ROLE_RIGHT__RIGHT, oldRight, right));
            }
        }
        return right;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Right basicGetRight() {
        return right;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRight(Right newRight, NotificationChain msgs) {
        Right oldRight = right;
        right = newRight;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ROLE_RIGHT__RIGHT, oldRight, newRight);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRight(Right newRight) {
        if (newRight != right) {
            NotificationChain msgs = null;
            if (right != null)
                msgs = ((InternalEObject)right).eInverseRemove(this, PropertiesPackage.RIGHT__ROLES_RIGHTS, Right.class, msgs);
            if (newRight != null)
                msgs = ((InternalEObject)newRight).eInverseAdd(this, PropertiesPackage.RIGHT__ROLES_RIGHTS, Right.class, msgs);
            msgs = basicSetRight(newRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ROLE_RIGHT__RIGHT, newRight, newRight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.ROLE_RIGHT__ROLE:
                if (role != null)
                    msgs = ((InternalEObject)role).eInverseRemove(this, PropertiesPackage.USER_ROLE__ROLES_RIGHTS, UserRole.class, msgs);
                return basicSetRole((UserRole)otherEnd, msgs);
            case PropertiesPackage.ROLE_RIGHT__RIGHT:
                if (right != null)
                    msgs = ((InternalEObject)right).eInverseRemove(this, PropertiesPackage.RIGHT__ROLES_RIGHTS, Right.class, msgs);
                return basicSetRight((Right)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.ROLE_RIGHT__ROLE:
                return basicSetRole(null, msgs);
            case PropertiesPackage.ROLE_RIGHT__RIGHT:
                return basicSetRight(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.ROLE_RIGHT__ROLE:
                if (resolve) return getRole();
                return basicGetRole();
            case PropertiesPackage.ROLE_RIGHT__RIGHT:
                if (resolve) return getRight();
                return basicGetRight();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiesPackage.ROLE_RIGHT__ROLE:
                setRole((UserRole)newValue);
                return;
            case PropertiesPackage.ROLE_RIGHT__RIGHT:
                setRight((Right)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case PropertiesPackage.ROLE_RIGHT__ROLE:
                setRole((UserRole)null);
                return;
            case PropertiesPackage.ROLE_RIGHT__RIGHT:
                setRight((Right)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PropertiesPackage.ROLE_RIGHT__ROLE:
                return role != null;
            case PropertiesPackage.ROLE_RIGHT__RIGHT:
                return right != null;
        }
        return super.eIsSet(featureID);
    }

} //RoleRightImpl
