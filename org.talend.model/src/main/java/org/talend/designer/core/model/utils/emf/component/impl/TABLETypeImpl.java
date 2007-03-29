/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.talend.designer.core.model.utils.emf.component.COLUMNType;
import org.talend.designer.core.model.utils.emf.component.ComponentPackage;
import org.talend.designer.core.model.utils.emf.component.TABLEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TABLE Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.TABLETypeImpl#getCOLUMN <em>COLUMN</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.TABLETypeImpl#isREADONLY <em>READONLY</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TABLETypeImpl extends EObjectImpl implements TABLEType {
    /**
     * The cached value of the '{@link #getCOLUMN() <em>COLUMN</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCOLUMN()
     * @generated
     * @ordered
     */
    protected EList cOLUMN = null;

    /**
     * The default value of the '{@link #isREADONLY() <em>READONLY</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isREADONLY()
     * @generated
     * @ordered
     */
    protected static final boolean READONLY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isREADONLY() <em>READONLY</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isREADONLY()
     * @generated
     * @ordered
     */
    protected boolean rEADONLY = READONLY_EDEFAULT;

    /**
     * This is true if the READONLY attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean rEADONLYESet = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TABLETypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.TABLE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getCOLUMN() {
        if (cOLUMN == null) {
            cOLUMN = new EObjectContainmentEList(COLUMNType.class, this, ComponentPackage.TABLE_TYPE__COLUMN);
        }
        return cOLUMN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isREADONLY() {
        return rEADONLY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setREADONLY(boolean newREADONLY) {
        boolean oldREADONLY = rEADONLY;
        rEADONLY = newREADONLY;
        boolean oldREADONLYESet = rEADONLYESet;
        rEADONLYESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.TABLE_TYPE__READONLY, oldREADONLY, rEADONLY, !oldREADONLYESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetREADONLY() {
        boolean oldREADONLY = rEADONLY;
        boolean oldREADONLYESet = rEADONLYESet;
        rEADONLY = READONLY_EDEFAULT;
        rEADONLYESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.TABLE_TYPE__READONLY, oldREADONLY, READONLY_EDEFAULT, oldREADONLYESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetREADONLY() {
        return rEADONLYESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentPackage.TABLE_TYPE__COLUMN:
                return ((InternalEList)getCOLUMN()).basicRemove(otherEnd, msgs);
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
            case ComponentPackage.TABLE_TYPE__COLUMN:
                return getCOLUMN();
            case ComponentPackage.TABLE_TYPE__READONLY:
                return isREADONLY() ? Boolean.TRUE : Boolean.FALSE;
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
            case ComponentPackage.TABLE_TYPE__COLUMN:
                getCOLUMN().clear();
                getCOLUMN().addAll((Collection)newValue);
                return;
            case ComponentPackage.TABLE_TYPE__READONLY:
                setREADONLY(((Boolean)newValue).booleanValue());
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
            case ComponentPackage.TABLE_TYPE__COLUMN:
                getCOLUMN().clear();
                return;
            case ComponentPackage.TABLE_TYPE__READONLY:
                unsetREADONLY();
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
            case ComponentPackage.TABLE_TYPE__COLUMN:
                return cOLUMN != null && !cOLUMN.isEmpty();
            case ComponentPackage.TABLE_TYPE__READONLY:
                return isSetREADONLY();
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
        result.append(" (rEADONLY: ");
        if (rEADONLYESet) result.append(rEADONLY); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //TABLETypeImpl