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

import org.talend.designer.core.model.utils.emf.component.ComponentPackage;
import org.talend.designer.core.model.utils.emf.component.SQLPATTERNSType;
import org.talend.designer.core.model.utils.emf.component.SQLPATTERNType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQLPATTERNS Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.SQLPATTERNSTypeImpl#getSQLPATTERN <em>SQLPATTERN</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.SQLPATTERNSTypeImpl#getDB <em>DB</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SQLPATTERNSTypeImpl extends EObjectImpl implements SQLPATTERNSType {
    /**
     * The cached value of the '{@link #getSQLPATTERN() <em>SQLPATTERN</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSQLPATTERN()
     * @generated
     * @ordered
     */
    protected EList sQLPATTERN;

    /**
     * The default value of the '{@link #getDB() <em>DB</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDB()
     * @generated
     * @ordered
     */
    protected static final String DB_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDB() <em>DB</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDB()
     * @generated
     * @ordered
     */
    protected String dB = DB_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SQLPATTERNSTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.SQLPATTERNS_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSQLPATTERN() {
        if (sQLPATTERN == null) {
            sQLPATTERN = new EObjectContainmentEList(SQLPATTERNType.class, this, ComponentPackage.SQLPATTERNS_TYPE__SQLPATTERN);
        }
        return sQLPATTERN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDB() {
        return dB;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDB(String newDB) {
        String oldDB = dB;
        dB = newDB;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.SQLPATTERNS_TYPE__DB, oldDB, dB));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentPackage.SQLPATTERNS_TYPE__SQLPATTERN:
                return ((InternalEList)getSQLPATTERN()).basicRemove(otherEnd, msgs);
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
            case ComponentPackage.SQLPATTERNS_TYPE__SQLPATTERN:
                return getSQLPATTERN();
            case ComponentPackage.SQLPATTERNS_TYPE__DB:
                return getDB();
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
            case ComponentPackage.SQLPATTERNS_TYPE__SQLPATTERN:
                getSQLPATTERN().clear();
                getSQLPATTERN().addAll((Collection)newValue);
                return;
            case ComponentPackage.SQLPATTERNS_TYPE__DB:
                setDB((String)newValue);
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
            case ComponentPackage.SQLPATTERNS_TYPE__SQLPATTERN:
                getSQLPATTERN().clear();
                return;
            case ComponentPackage.SQLPATTERNS_TYPE__DB:
                setDB(DB_EDEFAULT);
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
            case ComponentPackage.SQLPATTERNS_TYPE__SQLPATTERN:
                return sQLPATTERN != null && !sQLPATTERN.isEmpty();
            case ComponentPackage.SQLPATTERNS_TYPE__DB:
                return DB_EDEFAULT == null ? dB != null : !DB_EDEFAULT.equals(dB);
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
        result.append(" (dB: ");
        result.append(dB);
        result.append(')');
        return result.toString();
    }

} //SQLPATTERNSTypeImpl
