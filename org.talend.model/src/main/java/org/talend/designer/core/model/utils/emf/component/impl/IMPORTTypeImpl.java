/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.talend.designer.core.model.utils.emf.component.ComponentPackage;
import org.talend.designer.core.model.utils.emf.component.IMPORTType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IMPORT Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.IMPORTTypeImpl#getMODULE <em>MODULE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.IMPORTTypeImpl#getNAME <em>NAME</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.IMPORTTypeImpl#isREQUIRED <em>REQUIRED</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IMPORTTypeImpl extends EObjectImpl implements IMPORTType {
    /**
     * The default value of the '{@link #getMODULE() <em>MODULE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMODULE()
     * @generated
     * @ordered
     */
    protected static final String MODULE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMODULE() <em>MODULE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMODULE()
     * @generated
     * @ordered
     */
    protected String mODULE = MODULE_EDEFAULT;

    /**
     * The default value of the '{@link #getNAME() <em>NAME</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNAME()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNAME() <em>NAME</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNAME()
     * @generated
     * @ordered
     */
    protected String nAME = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isREQUIRED() <em>REQUIRED</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isREQUIRED()
     * @generated
     * @ordered
     */
    protected static final boolean REQUIRED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isREQUIRED() <em>REQUIRED</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isREQUIRED()
     * @generated
     * @ordered
     */
    protected boolean rEQUIRED = REQUIRED_EDEFAULT;

    /**
     * This is true if the REQUIRED attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean rEQUIREDESet = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IMPORTTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.IMPORT_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getMODULE() {
        return mODULE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMODULE(String newMODULE) {
        String oldMODULE = mODULE;
        mODULE = newMODULE;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.IMPORT_TYPE__MODULE, oldMODULE, mODULE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNAME() {
        return nAME;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNAME(String newNAME) {
        String oldNAME = nAME;
        nAME = newNAME;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.IMPORT_TYPE__NAME, oldNAME, nAME));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isREQUIRED() {
        return rEQUIRED;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setREQUIRED(boolean newREQUIRED) {
        boolean oldREQUIRED = rEQUIRED;
        rEQUIRED = newREQUIRED;
        boolean oldREQUIREDESet = rEQUIREDESet;
        rEQUIREDESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.IMPORT_TYPE__REQUIRED, oldREQUIRED, rEQUIRED, !oldREQUIREDESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetREQUIRED() {
        boolean oldREQUIRED = rEQUIRED;
        boolean oldREQUIREDESet = rEQUIREDESet;
        rEQUIRED = REQUIRED_EDEFAULT;
        rEQUIREDESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.IMPORT_TYPE__REQUIRED, oldREQUIRED, REQUIRED_EDEFAULT, oldREQUIREDESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetREQUIRED() {
        return rEQUIREDESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentPackage.IMPORT_TYPE__MODULE:
                return getMODULE();
            case ComponentPackage.IMPORT_TYPE__NAME:
                return getNAME();
            case ComponentPackage.IMPORT_TYPE__REQUIRED:
                return isREQUIRED() ? Boolean.TRUE : Boolean.FALSE;
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
            case ComponentPackage.IMPORT_TYPE__MODULE:
                setMODULE((String)newValue);
                return;
            case ComponentPackage.IMPORT_TYPE__NAME:
                setNAME((String)newValue);
                return;
            case ComponentPackage.IMPORT_TYPE__REQUIRED:
                setREQUIRED(((Boolean)newValue).booleanValue());
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
            case ComponentPackage.IMPORT_TYPE__MODULE:
                setMODULE(MODULE_EDEFAULT);
                return;
            case ComponentPackage.IMPORT_TYPE__NAME:
                setNAME(NAME_EDEFAULT);
                return;
            case ComponentPackage.IMPORT_TYPE__REQUIRED:
                unsetREQUIRED();
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
            case ComponentPackage.IMPORT_TYPE__MODULE:
                return MODULE_EDEFAULT == null ? mODULE != null : !MODULE_EDEFAULT.equals(mODULE);
            case ComponentPackage.IMPORT_TYPE__NAME:
                return NAME_EDEFAULT == null ? nAME != null : !NAME_EDEFAULT.equals(nAME);
            case ComponentPackage.IMPORT_TYPE__REQUIRED:
                return isSetREQUIRED();
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
        result.append(" (mODULE: ");
        result.append(mODULE);
        result.append(", nAME: ");
        result.append(nAME);
        result.append(", rEQUIRED: ");
        if (rEQUIREDESet) result.append(rEQUIRED); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //IMPORTTypeImpl