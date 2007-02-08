/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.RoutineItem;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Routine Item</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class RoutineItemImpl extends FileItemImpl implements RoutineItem {

    /**
     * The default value of the '{@link #isBuiltIn() <em>Built In</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBuiltIn()
     * @generated
     * @ordered
     */
    protected static final boolean BUILT_IN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBuiltIn() <em>Built In</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBuiltIn()
     * @generated
     * @ordered
     */
    protected boolean builtIn = BUILT_IN_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected RoutineItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ROUTINE_ITEM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isBuiltIn() {
        return builtIn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBuiltIn(boolean newBuiltIn) {
        boolean oldBuiltIn = builtIn;
        builtIn = newBuiltIn;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ROUTINE_ITEM__BUILT_IN, oldBuiltIn, builtIn));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.ROUTINE_ITEM__BUILT_IN:
                return isBuiltIn() ? Boolean.TRUE : Boolean.FALSE;
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
            case PropertiesPackage.ROUTINE_ITEM__BUILT_IN:
                setBuiltIn(((Boolean)newValue).booleanValue());
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
            case PropertiesPackage.ROUTINE_ITEM__BUILT_IN:
                setBuiltIn(BUILT_IN_EDEFAULT);
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
            case PropertiesPackage.ROUTINE_ITEM__BUILT_IN:
                return builtIn != BUILT_IN_EDEFAULT;
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
        result.append(" (builtIn: ");
        result.append(builtIn);
        result.append(')');
        return result.toString();
    }

} // RoutineItemImpl
