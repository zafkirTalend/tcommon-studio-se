/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.designer.core.model.utils.emf.component.CODEGENERATIONType;
import org.talend.designer.core.model.utils.emf.component.COMPONENTType;
import org.talend.designer.core.model.utils.emf.component.CONNECTORSType;
import org.talend.designer.core.model.utils.emf.component.ComponentPackage;
import org.talend.designer.core.model.utils.emf.component.DOCUMENTATIONType;
import org.talend.designer.core.model.utils.emf.component.HEADERType;
import org.talend.designer.core.model.utils.emf.component.PARAMETERSType;
import org.talend.designer.core.model.utils.emf.component.RETURNSType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>COMPONENT Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.COMPONENTTypeImpl#getHEADER <em>HEADER</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.COMPONENTTypeImpl#getDOCUMENTATION <em>DOCUMENTATION</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.COMPONENTTypeImpl#getCONNECTORS <em>CONNECTORS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.COMPONENTTypeImpl#getPARAMETERS <em>PARAMETERS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.COMPONENTTypeImpl#getCODEGENERATION <em>CODEGENERATION</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.COMPONENTTypeImpl#getRETURNS <em>RETURNS</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class COMPONENTTypeImpl extends EObjectImpl implements COMPONENTType {
    /**
     * The cached value of the '{@link #getHEADER() <em>HEADER</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHEADER()
     * @generated
     * @ordered
     */
    protected HEADERType hEADER = null;

    /**
     * The cached value of the '{@link #getDOCUMENTATION() <em>DOCUMENTATION</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDOCUMENTATION()
     * @generated
     * @ordered
     */
    protected DOCUMENTATIONType dOCUMENTATION = null;

    /**
     * The cached value of the '{@link #getCONNECTORS() <em>CONNECTORS</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCONNECTORS()
     * @generated
     * @ordered
     */
    protected CONNECTORSType cONNECTORS = null;

    /**
     * The cached value of the '{@link #getPARAMETERS() <em>PARAMETERS</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPARAMETERS()
     * @generated
     * @ordered
     */
    protected PARAMETERSType pARAMETERS = null;

    /**
     * The cached value of the '{@link #getCODEGENERATION() <em>CODEGENERATION</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCODEGENERATION()
     * @generated
     * @ordered
     */
    protected CODEGENERATIONType cODEGENERATION = null;

    /**
     * The cached value of the '{@link #getRETURNS() <em>RETURNS</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRETURNS()
     * @generated
     * @ordered
     */
    protected RETURNSType rETURNS = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COMPONENTTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.COMPONENT_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HEADERType getHEADER() {
        return hEADER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetHEADER(HEADERType newHEADER, NotificationChain msgs) {
        HEADERType oldHEADER = hEADER;
        hEADER = newHEADER;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__HEADER, oldHEADER, newHEADER);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHEADER(HEADERType newHEADER) {
        if (newHEADER != hEADER) {
            NotificationChain msgs = null;
            if (hEADER != null)
                msgs = ((InternalEObject)hEADER).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__HEADER, null, msgs);
            if (newHEADER != null)
                msgs = ((InternalEObject)newHEADER).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__HEADER, null, msgs);
            msgs = basicSetHEADER(newHEADER, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__HEADER, newHEADER, newHEADER));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DOCUMENTATIONType getDOCUMENTATION() {
        return dOCUMENTATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDOCUMENTATION(DOCUMENTATIONType newDOCUMENTATION, NotificationChain msgs) {
        DOCUMENTATIONType oldDOCUMENTATION = dOCUMENTATION;
        dOCUMENTATION = newDOCUMENTATION;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__DOCUMENTATION, oldDOCUMENTATION, newDOCUMENTATION);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDOCUMENTATION(DOCUMENTATIONType newDOCUMENTATION) {
        if (newDOCUMENTATION != dOCUMENTATION) {
            NotificationChain msgs = null;
            if (dOCUMENTATION != null)
                msgs = ((InternalEObject)dOCUMENTATION).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__DOCUMENTATION, null, msgs);
            if (newDOCUMENTATION != null)
                msgs = ((InternalEObject)newDOCUMENTATION).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__DOCUMENTATION, null, msgs);
            msgs = basicSetDOCUMENTATION(newDOCUMENTATION, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__DOCUMENTATION, newDOCUMENTATION, newDOCUMENTATION));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CONNECTORSType getCONNECTORS() {
        return cONNECTORS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCONNECTORS(CONNECTORSType newCONNECTORS, NotificationChain msgs) {
        CONNECTORSType oldCONNECTORS = cONNECTORS;
        cONNECTORS = newCONNECTORS;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__CONNECTORS, oldCONNECTORS, newCONNECTORS);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCONNECTORS(CONNECTORSType newCONNECTORS) {
        if (newCONNECTORS != cONNECTORS) {
            NotificationChain msgs = null;
            if (cONNECTORS != null)
                msgs = ((InternalEObject)cONNECTORS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__CONNECTORS, null, msgs);
            if (newCONNECTORS != null)
                msgs = ((InternalEObject)newCONNECTORS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__CONNECTORS, null, msgs);
            msgs = basicSetCONNECTORS(newCONNECTORS, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__CONNECTORS, newCONNECTORS, newCONNECTORS));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PARAMETERSType getPARAMETERS() {
        return pARAMETERS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPARAMETERS(PARAMETERSType newPARAMETERS, NotificationChain msgs) {
        PARAMETERSType oldPARAMETERS = pARAMETERS;
        pARAMETERS = newPARAMETERS;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__PARAMETERS, oldPARAMETERS, newPARAMETERS);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPARAMETERS(PARAMETERSType newPARAMETERS) {
        if (newPARAMETERS != pARAMETERS) {
            NotificationChain msgs = null;
            if (pARAMETERS != null)
                msgs = ((InternalEObject)pARAMETERS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__PARAMETERS, null, msgs);
            if (newPARAMETERS != null)
                msgs = ((InternalEObject)newPARAMETERS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__PARAMETERS, null, msgs);
            msgs = basicSetPARAMETERS(newPARAMETERS, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__PARAMETERS, newPARAMETERS, newPARAMETERS));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CODEGENERATIONType getCODEGENERATION() {
        return cODEGENERATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCODEGENERATION(CODEGENERATIONType newCODEGENERATION, NotificationChain msgs) {
        CODEGENERATIONType oldCODEGENERATION = cODEGENERATION;
        cODEGENERATION = newCODEGENERATION;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__CODEGENERATION, oldCODEGENERATION, newCODEGENERATION);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCODEGENERATION(CODEGENERATIONType newCODEGENERATION) {
        if (newCODEGENERATION != cODEGENERATION) {
            NotificationChain msgs = null;
            if (cODEGENERATION != null)
                msgs = ((InternalEObject)cODEGENERATION).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__CODEGENERATION, null, msgs);
            if (newCODEGENERATION != null)
                msgs = ((InternalEObject)newCODEGENERATION).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__CODEGENERATION, null, msgs);
            msgs = basicSetCODEGENERATION(newCODEGENERATION, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__CODEGENERATION, newCODEGENERATION, newCODEGENERATION));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RETURNSType getRETURNS() {
        return rETURNS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRETURNS(RETURNSType newRETURNS, NotificationChain msgs) {
        RETURNSType oldRETURNS = rETURNS;
        rETURNS = newRETURNS;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__RETURNS, oldRETURNS, newRETURNS);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRETURNS(RETURNSType newRETURNS) {
        if (newRETURNS != rETURNS) {
            NotificationChain msgs = null;
            if (rETURNS != null)
                msgs = ((InternalEObject)rETURNS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__RETURNS, null, msgs);
            if (newRETURNS != null)
                msgs = ((InternalEObject)newRETURNS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.COMPONENT_TYPE__RETURNS, null, msgs);
            msgs = basicSetRETURNS(newRETURNS, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_TYPE__RETURNS, newRETURNS, newRETURNS));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentPackage.COMPONENT_TYPE__HEADER:
                return basicSetHEADER(null, msgs);
            case ComponentPackage.COMPONENT_TYPE__DOCUMENTATION:
                return basicSetDOCUMENTATION(null, msgs);
            case ComponentPackage.COMPONENT_TYPE__CONNECTORS:
                return basicSetCONNECTORS(null, msgs);
            case ComponentPackage.COMPONENT_TYPE__PARAMETERS:
                return basicSetPARAMETERS(null, msgs);
            case ComponentPackage.COMPONENT_TYPE__CODEGENERATION:
                return basicSetCODEGENERATION(null, msgs);
            case ComponentPackage.COMPONENT_TYPE__RETURNS:
                return basicSetRETURNS(null, msgs);
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
            case ComponentPackage.COMPONENT_TYPE__HEADER:
                return getHEADER();
            case ComponentPackage.COMPONENT_TYPE__DOCUMENTATION:
                return getDOCUMENTATION();
            case ComponentPackage.COMPONENT_TYPE__CONNECTORS:
                return getCONNECTORS();
            case ComponentPackage.COMPONENT_TYPE__PARAMETERS:
                return getPARAMETERS();
            case ComponentPackage.COMPONENT_TYPE__CODEGENERATION:
                return getCODEGENERATION();
            case ComponentPackage.COMPONENT_TYPE__RETURNS:
                return getRETURNS();
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
            case ComponentPackage.COMPONENT_TYPE__HEADER:
                setHEADER((HEADERType)newValue);
                return;
            case ComponentPackage.COMPONENT_TYPE__DOCUMENTATION:
                setDOCUMENTATION((DOCUMENTATIONType)newValue);
                return;
            case ComponentPackage.COMPONENT_TYPE__CONNECTORS:
                setCONNECTORS((CONNECTORSType)newValue);
                return;
            case ComponentPackage.COMPONENT_TYPE__PARAMETERS:
                setPARAMETERS((PARAMETERSType)newValue);
                return;
            case ComponentPackage.COMPONENT_TYPE__CODEGENERATION:
                setCODEGENERATION((CODEGENERATIONType)newValue);
                return;
            case ComponentPackage.COMPONENT_TYPE__RETURNS:
                setRETURNS((RETURNSType)newValue);
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
            case ComponentPackage.COMPONENT_TYPE__HEADER:
                setHEADER((HEADERType)null);
                return;
            case ComponentPackage.COMPONENT_TYPE__DOCUMENTATION:
                setDOCUMENTATION((DOCUMENTATIONType)null);
                return;
            case ComponentPackage.COMPONENT_TYPE__CONNECTORS:
                setCONNECTORS((CONNECTORSType)null);
                return;
            case ComponentPackage.COMPONENT_TYPE__PARAMETERS:
                setPARAMETERS((PARAMETERSType)null);
                return;
            case ComponentPackage.COMPONENT_TYPE__CODEGENERATION:
                setCODEGENERATION((CODEGENERATIONType)null);
                return;
            case ComponentPackage.COMPONENT_TYPE__RETURNS:
                setRETURNS((RETURNSType)null);
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
            case ComponentPackage.COMPONENT_TYPE__HEADER:
                return hEADER != null;
            case ComponentPackage.COMPONENT_TYPE__DOCUMENTATION:
                return dOCUMENTATION != null;
            case ComponentPackage.COMPONENT_TYPE__CONNECTORS:
                return cONNECTORS != null;
            case ComponentPackage.COMPONENT_TYPE__PARAMETERS:
                return pARAMETERS != null;
            case ComponentPackage.COMPONENT_TYPE__CODEGENERATION:
                return cODEGENERATION != null;
            case ComponentPackage.COMPONENT_TYPE__RETURNS:
                return rETURNS != null;
        }
        return super.eIsSet(featureID);
    }

} //COMPONENTTypeImpl