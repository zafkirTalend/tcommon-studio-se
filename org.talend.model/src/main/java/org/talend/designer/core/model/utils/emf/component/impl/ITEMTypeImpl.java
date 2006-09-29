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
import org.talend.designer.core.model.utils.emf.component.ITEMType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ITEM Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.ITEMTypeImpl#getNAME <em>NAME</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.ITEMTypeImpl#getNOTSHOWIF <em>NOTSHOWIF</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.ITEMTypeImpl#getREPOSITORYITEM <em>REPOSITORYITEM</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.ITEMTypeImpl#getSHOWIF <em>SHOWIF</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.ITEMTypeImpl#getVALUE <em>VALUE</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ITEMTypeImpl extends EObjectImpl implements ITEMType {
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
     * The default value of the '{@link #getNOTSHOWIF() <em>NOTSHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNOTSHOWIF()
     * @generated
     * @ordered
     */
    protected static final String NOTSHOWIF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNOTSHOWIF() <em>NOTSHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNOTSHOWIF()
     * @generated
     * @ordered
     */
    protected String nOTSHOWIF = NOTSHOWIF_EDEFAULT;

    /**
     * The default value of the '{@link #getREPOSITORYITEM() <em>REPOSITORYITEM</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getREPOSITORYITEM()
     * @generated
     * @ordered
     */
    protected static final String REPOSITORYITEM_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getREPOSITORYITEM() <em>REPOSITORYITEM</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getREPOSITORYITEM()
     * @generated
     * @ordered
     */
    protected String rEPOSITORYITEM = REPOSITORYITEM_EDEFAULT;

    /**
     * The default value of the '{@link #getSHOWIF() <em>SHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSHOWIF()
     * @generated
     * @ordered
     */
    protected static final String SHOWIF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSHOWIF() <em>SHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSHOWIF()
     * @generated
     * @ordered
     */
    protected String sHOWIF = SHOWIF_EDEFAULT;

    /**
     * The default value of the '{@link #getVALUE() <em>VALUE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVALUE()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVALUE() <em>VALUE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVALUE()
     * @generated
     * @ordered
     */
    protected String vALUE = VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ITEMTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.ITEM_TYPE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.ITEM_TYPE__NAME, oldNAME, nAME));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNOTSHOWIF() {
        return nOTSHOWIF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNOTSHOWIF(String newNOTSHOWIF) {
        String oldNOTSHOWIF = nOTSHOWIF;
        nOTSHOWIF = newNOTSHOWIF;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.ITEM_TYPE__NOTSHOWIF, oldNOTSHOWIF, nOTSHOWIF));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getREPOSITORYITEM() {
        return rEPOSITORYITEM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setREPOSITORYITEM(String newREPOSITORYITEM) {
        String oldREPOSITORYITEM = rEPOSITORYITEM;
        rEPOSITORYITEM = newREPOSITORYITEM;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.ITEM_TYPE__REPOSITORYITEM, oldREPOSITORYITEM, rEPOSITORYITEM));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSHOWIF() {
        return sHOWIF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSHOWIF(String newSHOWIF) {
        String oldSHOWIF = sHOWIF;
        sHOWIF = newSHOWIF;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.ITEM_TYPE__SHOWIF, oldSHOWIF, sHOWIF));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVALUE() {
        return vALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVALUE(String newVALUE) {
        String oldVALUE = vALUE;
        vALUE = newVALUE;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.ITEM_TYPE__VALUE, oldVALUE, vALUE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentPackage.ITEM_TYPE__NAME:
                return getNAME();
            case ComponentPackage.ITEM_TYPE__NOTSHOWIF:
                return getNOTSHOWIF();
            case ComponentPackage.ITEM_TYPE__REPOSITORYITEM:
                return getREPOSITORYITEM();
            case ComponentPackage.ITEM_TYPE__SHOWIF:
                return getSHOWIF();
            case ComponentPackage.ITEM_TYPE__VALUE:
                return getVALUE();
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
            case ComponentPackage.ITEM_TYPE__NAME:
                setNAME((String)newValue);
                return;
            case ComponentPackage.ITEM_TYPE__NOTSHOWIF:
                setNOTSHOWIF((String)newValue);
                return;
            case ComponentPackage.ITEM_TYPE__REPOSITORYITEM:
                setREPOSITORYITEM((String)newValue);
                return;
            case ComponentPackage.ITEM_TYPE__SHOWIF:
                setSHOWIF((String)newValue);
                return;
            case ComponentPackage.ITEM_TYPE__VALUE:
                setVALUE((String)newValue);
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
            case ComponentPackage.ITEM_TYPE__NAME:
                setNAME(NAME_EDEFAULT);
                return;
            case ComponentPackage.ITEM_TYPE__NOTSHOWIF:
                setNOTSHOWIF(NOTSHOWIF_EDEFAULT);
                return;
            case ComponentPackage.ITEM_TYPE__REPOSITORYITEM:
                setREPOSITORYITEM(REPOSITORYITEM_EDEFAULT);
                return;
            case ComponentPackage.ITEM_TYPE__SHOWIF:
                setSHOWIF(SHOWIF_EDEFAULT);
                return;
            case ComponentPackage.ITEM_TYPE__VALUE:
                setVALUE(VALUE_EDEFAULT);
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
            case ComponentPackage.ITEM_TYPE__NAME:
                return NAME_EDEFAULT == null ? nAME != null : !NAME_EDEFAULT.equals(nAME);
            case ComponentPackage.ITEM_TYPE__NOTSHOWIF:
                return NOTSHOWIF_EDEFAULT == null ? nOTSHOWIF != null : !NOTSHOWIF_EDEFAULT.equals(nOTSHOWIF);
            case ComponentPackage.ITEM_TYPE__REPOSITORYITEM:
                return REPOSITORYITEM_EDEFAULT == null ? rEPOSITORYITEM != null : !REPOSITORYITEM_EDEFAULT.equals(rEPOSITORYITEM);
            case ComponentPackage.ITEM_TYPE__SHOWIF:
                return SHOWIF_EDEFAULT == null ? sHOWIF != null : !SHOWIF_EDEFAULT.equals(sHOWIF);
            case ComponentPackage.ITEM_TYPE__VALUE:
                return VALUE_EDEFAULT == null ? vALUE != null : !VALUE_EDEFAULT.equals(vALUE);
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
        result.append(" (nAME: ");
        result.append(nAME);
        result.append(", nOTSHOWIF: ");
        result.append(nOTSHOWIF);
        result.append(", rEPOSITORYITEM: ");
        result.append(rEPOSITORYITEM);
        result.append(", sHOWIF: ");
        result.append(sHOWIF);
        result.append(", vALUE: ");
        result.append(vALUE);
        result.append(')');
        return result.toString();
    }

} //ITEMTypeImpl