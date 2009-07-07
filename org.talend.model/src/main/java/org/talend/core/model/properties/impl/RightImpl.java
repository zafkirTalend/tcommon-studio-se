/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Right;
import org.talend.core.model.properties.RoleRight;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Right</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.RightImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.RightImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.RightImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.RightImpl#getRolesRights <em>Roles Rights</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RightImpl extends EObjectImpl implements Right {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final int ID_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected int id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getRolesRights() <em>Roles Rights</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRolesRights()
     * @generated
     * @ordered
     */
    protected EList rolesRights;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RightImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.RIGHT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(int newId) {
        int oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.RIGHT__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.RIGHT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.RIGHT__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getRolesRights() {
        if (rolesRights == null) {
            rolesRights = new EObjectWithInverseResolvingEList(RoleRight.class, this, PropertiesPackage.RIGHT__ROLES_RIGHTS, PropertiesPackage.ROLE_RIGHT__RIGHT);
        }
        return rolesRights;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.RIGHT__ROLES_RIGHTS:
                return ((InternalEList)getRolesRights()).basicAdd(otherEnd, msgs);
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
            case PropertiesPackage.RIGHT__ROLES_RIGHTS:
                return ((InternalEList)getRolesRights()).basicRemove(otherEnd, msgs);
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
            case PropertiesPackage.RIGHT__ID:
                return new Integer(getId());
            case PropertiesPackage.RIGHT__NAME:
                return getName();
            case PropertiesPackage.RIGHT__DESCRIPTION:
                return getDescription();
            case PropertiesPackage.RIGHT__ROLES_RIGHTS:
                return getRolesRights();
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
            case PropertiesPackage.RIGHT__ID:
                setId(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.RIGHT__NAME:
                setName((String)newValue);
                return;
            case PropertiesPackage.RIGHT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case PropertiesPackage.RIGHT__ROLES_RIGHTS:
                getRolesRights().clear();
                getRolesRights().addAll((Collection)newValue);
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
            case PropertiesPackage.RIGHT__ID:
                setId(ID_EDEFAULT);
                return;
            case PropertiesPackage.RIGHT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case PropertiesPackage.RIGHT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case PropertiesPackage.RIGHT__ROLES_RIGHTS:
                getRolesRights().clear();
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
            case PropertiesPackage.RIGHT__ID:
                return id != ID_EDEFAULT;
            case PropertiesPackage.RIGHT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case PropertiesPackage.RIGHT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case PropertiesPackage.RIGHT__ROLES_RIGHTS:
                return rolesRights != null && !rolesRights.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (id: ");
        result.append(id);
        result.append(", name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(')');
        return result.toString();
    }

} //RightImpl
