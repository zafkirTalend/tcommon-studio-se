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
import org.talend.designer.core.model.utils.emf.component.CONNECTORType;
import org.talend.designer.core.model.utils.emf.component.ComponentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CONNECTOR Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.CONNECTORTypeImpl#isBUILTIN <em>BUILTIN</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.CONNECTORTypeImpl#getCTYPE <em>CTYPE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.CONNECTORTypeImpl#getMAXINPUT <em>MAXINPUT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.CONNECTORTypeImpl#getMAXOUTPUT <em>MAXOUTPUT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.CONNECTORTypeImpl#getMININPUT <em>MININPUT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.CONNECTORTypeImpl#getMINOUTPUT <em>MINOUTPUT</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CONNECTORTypeImpl extends EObjectImpl implements CONNECTORType {
    /**
     * The default value of the '{@link #isBUILTIN() <em>BUILTIN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBUILTIN()
     * @generated
     * @ordered
     */
    protected static final boolean BUILTIN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBUILTIN() <em>BUILTIN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBUILTIN()
     * @generated
     * @ordered
     */
    protected boolean bUILTIN = BUILTIN_EDEFAULT;

    /**
     * This is true if the BUILTIN attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean bUILTINESet = false;

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
     * The default value of the '{@link #getMAXINPUT() <em>MAXINPUT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMAXINPUT()
     * @generated
     * @ordered
     */
    protected static final int MAXINPUT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMAXINPUT() <em>MAXINPUT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMAXINPUT()
     * @generated
     * @ordered
     */
    protected int mAXINPUT = MAXINPUT_EDEFAULT;

    /**
     * This is true if the MAXINPUT attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean mAXINPUTESet = false;

    /**
     * The default value of the '{@link #getMAXOUTPUT() <em>MAXOUTPUT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMAXOUTPUT()
     * @generated
     * @ordered
     */
    protected static final int MAXOUTPUT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMAXOUTPUT() <em>MAXOUTPUT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMAXOUTPUT()
     * @generated
     * @ordered
     */
    protected int mAXOUTPUT = MAXOUTPUT_EDEFAULT;

    /**
     * This is true if the MAXOUTPUT attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean mAXOUTPUTESet = false;

    /**
     * The default value of the '{@link #getMININPUT() <em>MININPUT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMININPUT()
     * @generated
     * @ordered
     */
    protected static final int MININPUT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMININPUT() <em>MININPUT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMININPUT()
     * @generated
     * @ordered
     */
    protected int mININPUT = MININPUT_EDEFAULT;

    /**
     * This is true if the MININPUT attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean mININPUTESet = false;

    /**
     * The default value of the '{@link #getMINOUTPUT() <em>MINOUTPUT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMINOUTPUT()
     * @generated
     * @ordered
     */
    protected static final int MINOUTPUT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMINOUTPUT() <em>MINOUTPUT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMINOUTPUT()
     * @generated
     * @ordered
     */
    protected int mINOUTPUT = MINOUTPUT_EDEFAULT;

    /**
     * This is true if the MINOUTPUT attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean mINOUTPUTESet = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CONNECTORTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.CONNECTOR_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isBUILTIN() {
        return bUILTIN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBUILTIN(boolean newBUILTIN) {
        boolean oldBUILTIN = bUILTIN;
        bUILTIN = newBUILTIN;
        boolean oldBUILTINESet = bUILTINESet;
        bUILTINESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_TYPE__BUILTIN, oldBUILTIN, bUILTIN, !oldBUILTINESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetBUILTIN() {
        boolean oldBUILTIN = bUILTIN;
        boolean oldBUILTINESet = bUILTINESet;
        bUILTIN = BUILTIN_EDEFAULT;
        bUILTINESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.CONNECTOR_TYPE__BUILTIN, oldBUILTIN, BUILTIN_EDEFAULT, oldBUILTINESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetBUILTIN() {
        return bUILTINESet;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_TYPE__CTYPE, oldCTYPE, cTYPE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getMAXINPUT() {
        return mAXINPUT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMAXINPUT(int newMAXINPUT) {
        int oldMAXINPUT = mAXINPUT;
        mAXINPUT = newMAXINPUT;
        boolean oldMAXINPUTESet = mAXINPUTESet;
        mAXINPUTESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_TYPE__MAXINPUT, oldMAXINPUT, mAXINPUT, !oldMAXINPUTESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMAXINPUT() {
        int oldMAXINPUT = mAXINPUT;
        boolean oldMAXINPUTESet = mAXINPUTESet;
        mAXINPUT = MAXINPUT_EDEFAULT;
        mAXINPUTESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.CONNECTOR_TYPE__MAXINPUT, oldMAXINPUT, MAXINPUT_EDEFAULT, oldMAXINPUTESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMAXINPUT() {
        return mAXINPUTESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getMAXOUTPUT() {
        return mAXOUTPUT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMAXOUTPUT(int newMAXOUTPUT) {
        int oldMAXOUTPUT = mAXOUTPUT;
        mAXOUTPUT = newMAXOUTPUT;
        boolean oldMAXOUTPUTESet = mAXOUTPUTESet;
        mAXOUTPUTESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_TYPE__MAXOUTPUT, oldMAXOUTPUT, mAXOUTPUT, !oldMAXOUTPUTESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMAXOUTPUT() {
        int oldMAXOUTPUT = mAXOUTPUT;
        boolean oldMAXOUTPUTESet = mAXOUTPUTESet;
        mAXOUTPUT = MAXOUTPUT_EDEFAULT;
        mAXOUTPUTESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.CONNECTOR_TYPE__MAXOUTPUT, oldMAXOUTPUT, MAXOUTPUT_EDEFAULT, oldMAXOUTPUTESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMAXOUTPUT() {
        return mAXOUTPUTESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getMININPUT() {
        return mININPUT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMININPUT(int newMININPUT) {
        int oldMININPUT = mININPUT;
        mININPUT = newMININPUT;
        boolean oldMININPUTESet = mININPUTESet;
        mININPUTESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_TYPE__MININPUT, oldMININPUT, mININPUT, !oldMININPUTESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMININPUT() {
        int oldMININPUT = mININPUT;
        boolean oldMININPUTESet = mININPUTESet;
        mININPUT = MININPUT_EDEFAULT;
        mININPUTESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.CONNECTOR_TYPE__MININPUT, oldMININPUT, MININPUT_EDEFAULT, oldMININPUTESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMININPUT() {
        return mININPUTESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getMINOUTPUT() {
        return mINOUTPUT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMINOUTPUT(int newMINOUTPUT) {
        int oldMINOUTPUT = mINOUTPUT;
        mINOUTPUT = newMINOUTPUT;
        boolean oldMINOUTPUTESet = mINOUTPUTESet;
        mINOUTPUTESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_TYPE__MINOUTPUT, oldMINOUTPUT, mINOUTPUT, !oldMINOUTPUTESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMINOUTPUT() {
        int oldMINOUTPUT = mINOUTPUT;
        boolean oldMINOUTPUTESet = mINOUTPUTESet;
        mINOUTPUT = MINOUTPUT_EDEFAULT;
        mINOUTPUTESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.CONNECTOR_TYPE__MINOUTPUT, oldMINOUTPUT, MINOUTPUT_EDEFAULT, oldMINOUTPUTESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMINOUTPUT() {
        return mINOUTPUTESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentPackage.CONNECTOR_TYPE__BUILTIN:
                return isBUILTIN() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.CONNECTOR_TYPE__CTYPE:
                return getCTYPE();
            case ComponentPackage.CONNECTOR_TYPE__MAXINPUT:
                return new Integer(getMAXINPUT());
            case ComponentPackage.CONNECTOR_TYPE__MAXOUTPUT:
                return new Integer(getMAXOUTPUT());
            case ComponentPackage.CONNECTOR_TYPE__MININPUT:
                return new Integer(getMININPUT());
            case ComponentPackage.CONNECTOR_TYPE__MINOUTPUT:
                return new Integer(getMINOUTPUT());
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
            case ComponentPackage.CONNECTOR_TYPE__BUILTIN:
                setBUILTIN(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.CONNECTOR_TYPE__CTYPE:
                setCTYPE((String)newValue);
                return;
            case ComponentPackage.CONNECTOR_TYPE__MAXINPUT:
                setMAXINPUT(((Integer)newValue).intValue());
                return;
            case ComponentPackage.CONNECTOR_TYPE__MAXOUTPUT:
                setMAXOUTPUT(((Integer)newValue).intValue());
                return;
            case ComponentPackage.CONNECTOR_TYPE__MININPUT:
                setMININPUT(((Integer)newValue).intValue());
                return;
            case ComponentPackage.CONNECTOR_TYPE__MINOUTPUT:
                setMINOUTPUT(((Integer)newValue).intValue());
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
            case ComponentPackage.CONNECTOR_TYPE__BUILTIN:
                unsetBUILTIN();
                return;
            case ComponentPackage.CONNECTOR_TYPE__CTYPE:
                setCTYPE(CTYPE_EDEFAULT);
                return;
            case ComponentPackage.CONNECTOR_TYPE__MAXINPUT:
                unsetMAXINPUT();
                return;
            case ComponentPackage.CONNECTOR_TYPE__MAXOUTPUT:
                unsetMAXOUTPUT();
                return;
            case ComponentPackage.CONNECTOR_TYPE__MININPUT:
                unsetMININPUT();
                return;
            case ComponentPackage.CONNECTOR_TYPE__MINOUTPUT:
                unsetMINOUTPUT();
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
            case ComponentPackage.CONNECTOR_TYPE__BUILTIN:
                return isSetBUILTIN();
            case ComponentPackage.CONNECTOR_TYPE__CTYPE:
                return CTYPE_EDEFAULT == null ? cTYPE != null : !CTYPE_EDEFAULT.equals(cTYPE);
            case ComponentPackage.CONNECTOR_TYPE__MAXINPUT:
                return isSetMAXINPUT();
            case ComponentPackage.CONNECTOR_TYPE__MAXOUTPUT:
                return isSetMAXOUTPUT();
            case ComponentPackage.CONNECTOR_TYPE__MININPUT:
                return isSetMININPUT();
            case ComponentPackage.CONNECTOR_TYPE__MINOUTPUT:
                return isSetMINOUTPUT();
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
        result.append(" (bUILTIN: ");
        if (bUILTINESet) result.append(bUILTIN); else result.append("<unset>");
        result.append(", cTYPE: ");
        result.append(cTYPE);
        result.append(", mAXINPUT: ");
        if (mAXINPUTESet) result.append(mAXINPUT); else result.append("<unset>");
        result.append(", mAXOUTPUT: ");
        if (mAXOUTPUTESet) result.append(mAXOUTPUT); else result.append("<unset>");
        result.append(", mININPUT: ");
        if (mININPUTESet) result.append(mININPUT); else result.append("<unset>");
        result.append(", mINOUTPUT: ");
        if (mINOUTPUTESet) result.append(mINOUTPUT); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //CONNECTORTypeImpl