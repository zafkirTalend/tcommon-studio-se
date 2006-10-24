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
import org.talend.designer.core.model.utils.emf.component.TEMPLATEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TEMPLATE Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.TEMPLATETypeImpl#getCTYPE <em>CTYPE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.TEMPLATETypeImpl#getLINKTO <em>LINKTO</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.TEMPLATETypeImpl#isMULTIPLEMETHODS <em>MULTIPLEMETHODS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.TEMPLATETypeImpl#getNAME <em>NAME</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TEMPLATETypeImpl extends EObjectImpl implements TEMPLATEType {
    /**
     * The default value of the '{@link #getCTYPE() <em>CTYPE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCTYPE()
     * @generated
     * @ordered
     */
    protected static final String CTYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCTYPE() <em>CTYPE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCTYPE()
     * @generated
     * @ordered
     */
    protected String cTYPE = CTYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getLINKTO() <em>LINKTO</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLINKTO()
     * @generated
     * @ordered
     */
    protected static final String LINKTO_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLINKTO() <em>LINKTO</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLINKTO()
     * @generated
     * @ordered
     */
    protected String lINKTO = LINKTO_EDEFAULT;

    /**
     * The default value of the '{@link #isMULTIPLEMETHODS() <em>MULTIPLEMETHODS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMULTIPLEMETHODS()
     * @generated
     * @ordered
     */
    protected static final boolean MULTIPLEMETHODS_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMULTIPLEMETHODS() <em>MULTIPLEMETHODS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMULTIPLEMETHODS()
     * @generated
     * @ordered
     */
    protected boolean mULTIPLEMETHODS = MULTIPLEMETHODS_EDEFAULT;

    /**
     * This is true if the MULTIPLEMETHODS attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean mULTIPLEMETHODSESet = false;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TEMPLATETypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.TEMPLATE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCTYPE() {
        return cTYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCTYPE(String newCTYPE) {
        String oldCTYPE = cTYPE;
        cTYPE = newCTYPE;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.TEMPLATE_TYPE__CTYPE, oldCTYPE, cTYPE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLINKTO() {
        return lINKTO;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLINKTO(String newLINKTO) {
        String oldLINKTO = lINKTO;
        lINKTO = newLINKTO;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.TEMPLATE_TYPE__LINKTO, oldLINKTO, lINKTO));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isMULTIPLEMETHODS() {
        return mULTIPLEMETHODS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMULTIPLEMETHODS(boolean newMULTIPLEMETHODS) {
        boolean oldMULTIPLEMETHODS = mULTIPLEMETHODS;
        mULTIPLEMETHODS = newMULTIPLEMETHODS;
        boolean oldMULTIPLEMETHODSESet = mULTIPLEMETHODSESet;
        mULTIPLEMETHODSESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.TEMPLATE_TYPE__MULTIPLEMETHODS, oldMULTIPLEMETHODS, mULTIPLEMETHODS, !oldMULTIPLEMETHODSESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMULTIPLEMETHODS() {
        boolean oldMULTIPLEMETHODS = mULTIPLEMETHODS;
        boolean oldMULTIPLEMETHODSESet = mULTIPLEMETHODSESet;
        mULTIPLEMETHODS = MULTIPLEMETHODS_EDEFAULT;
        mULTIPLEMETHODSESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.TEMPLATE_TYPE__MULTIPLEMETHODS, oldMULTIPLEMETHODS, MULTIPLEMETHODS_EDEFAULT, oldMULTIPLEMETHODSESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMULTIPLEMETHODS() {
        return mULTIPLEMETHODSESet;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.TEMPLATE_TYPE__NAME, oldNAME, nAME));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentPackage.TEMPLATE_TYPE__CTYPE:
                return getCTYPE();
            case ComponentPackage.TEMPLATE_TYPE__LINKTO:
                return getLINKTO();
            case ComponentPackage.TEMPLATE_TYPE__MULTIPLEMETHODS:
                return isMULTIPLEMETHODS() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.TEMPLATE_TYPE__NAME:
                return getNAME();
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
            case ComponentPackage.TEMPLATE_TYPE__CTYPE:
                setCTYPE((String)newValue);
                return;
            case ComponentPackage.TEMPLATE_TYPE__LINKTO:
                setLINKTO((String)newValue);
                return;
            case ComponentPackage.TEMPLATE_TYPE__MULTIPLEMETHODS:
                setMULTIPLEMETHODS(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.TEMPLATE_TYPE__NAME:
                setNAME((String)newValue);
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
            case ComponentPackage.TEMPLATE_TYPE__CTYPE:
                setCTYPE(CTYPE_EDEFAULT);
                return;
            case ComponentPackage.TEMPLATE_TYPE__LINKTO:
                setLINKTO(LINKTO_EDEFAULT);
                return;
            case ComponentPackage.TEMPLATE_TYPE__MULTIPLEMETHODS:
                unsetMULTIPLEMETHODS();
                return;
            case ComponentPackage.TEMPLATE_TYPE__NAME:
                setNAME(NAME_EDEFAULT);
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
            case ComponentPackage.TEMPLATE_TYPE__CTYPE:
                return CTYPE_EDEFAULT == null ? cTYPE != null : !CTYPE_EDEFAULT.equals(cTYPE);
            case ComponentPackage.TEMPLATE_TYPE__LINKTO:
                return LINKTO_EDEFAULT == null ? lINKTO != null : !LINKTO_EDEFAULT.equals(lINKTO);
            case ComponentPackage.TEMPLATE_TYPE__MULTIPLEMETHODS:
                return isSetMULTIPLEMETHODS();
            case ComponentPackage.TEMPLATE_TYPE__NAME:
                return NAME_EDEFAULT == null ? nAME != null : !NAME_EDEFAULT.equals(nAME);
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
        result.append(" (cTYPE: ");
        result.append(cTYPE);
        result.append(", lINKTO: ");
        result.append(lINKTO);
        result.append(", mULTIPLEMETHODS: ");
        if (mULTIPLEMETHODSESet) result.append(mULTIPLEMETHODS); else result.append("<unset>");
        result.append(", nAME: ");
        result.append(nAME);
        result.append(')');
        return result.toString();
    }

} //TEMPLATETypeImpl