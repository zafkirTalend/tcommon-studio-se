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
import org.talend.designer.core.model.utils.emf.component.ITEMSType;
import org.talend.designer.core.model.utils.emf.component.ITEMType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ITEMS Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.ITEMSTypeImpl#getITEM <em>ITEM</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.ITEMSTypeImpl#isBASEDONSCHEMA <em>BASEDONSCHEMA</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.ITEMSTypeImpl#getDEFAULT <em>DEFAULT</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ITEMSTypeImpl extends EObjectImpl implements ITEMSType {
    /**
     * The cached value of the '{@link #getITEM() <em>ITEM</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getITEM()
     * @generated
     * @ordered
     */
    protected EList iTEM= null;

    /**
     * The default value of the '{@link #isBASEDONSCHEMA() <em>BASEDONSCHEMA</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBASEDONSCHEMA()
     * @generated
     * @ordered
     */
    protected static final boolean BASEDONSCHEMA_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBASEDONSCHEMA() <em>BASEDONSCHEMA</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBASEDONSCHEMA()
     * @generated
     * @ordered
     */
    protected boolean bASEDONSCHEMA = BASEDONSCHEMA_EDEFAULT;

    /**
     * This is true if the BASEDONSCHEMA attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean bASEDONSCHEMAESet= false;

    /**
     * The default value of the '{@link #getDEFAULT() <em>DEFAULT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDEFAULT()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDEFAULT() <em>DEFAULT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDEFAULT()
     * @generated
     * @ordered
     */
    protected String dEFAULT = DEFAULT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ITEMSTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.ITEMS_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getITEM() {
        if (iTEM == null) {
            iTEM = new EObjectContainmentEList(ITEMType.class, this, ComponentPackage.ITEMS_TYPE__ITEM);
        }
        return iTEM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isBASEDONSCHEMA() {
        return bASEDONSCHEMA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBASEDONSCHEMA(boolean newBASEDONSCHEMA) {
        boolean oldBASEDONSCHEMA = bASEDONSCHEMA;
        bASEDONSCHEMA = newBASEDONSCHEMA;
        boolean oldBASEDONSCHEMAESet = bASEDONSCHEMAESet;
        bASEDONSCHEMAESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.ITEMS_TYPE__BASEDONSCHEMA, oldBASEDONSCHEMA, bASEDONSCHEMA, !oldBASEDONSCHEMAESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetBASEDONSCHEMA() {
        boolean oldBASEDONSCHEMA = bASEDONSCHEMA;
        boolean oldBASEDONSCHEMAESet = bASEDONSCHEMAESet;
        bASEDONSCHEMA = BASEDONSCHEMA_EDEFAULT;
        bASEDONSCHEMAESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.ITEMS_TYPE__BASEDONSCHEMA, oldBASEDONSCHEMA, BASEDONSCHEMA_EDEFAULT, oldBASEDONSCHEMAESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetBASEDONSCHEMA() {
        return bASEDONSCHEMAESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDEFAULT() {
        return dEFAULT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDEFAULT(String newDEFAULT) {
        String oldDEFAULT = dEFAULT;
        dEFAULT = newDEFAULT;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.ITEMS_TYPE__DEFAULT, oldDEFAULT, dEFAULT));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentPackage.ITEMS_TYPE__ITEM:
                return ((InternalEList)getITEM()).basicRemove(otherEnd, msgs);
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
            case ComponentPackage.ITEMS_TYPE__ITEM:
                return getITEM();
            case ComponentPackage.ITEMS_TYPE__BASEDONSCHEMA:
                return isBASEDONSCHEMA() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.ITEMS_TYPE__DEFAULT:
                return getDEFAULT();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked") //$NON-NLS-1$
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ComponentPackage.ITEMS_TYPE__ITEM:
                getITEM().clear();
                getITEM().addAll((Collection)newValue);
                return;
            case ComponentPackage.ITEMS_TYPE__BASEDONSCHEMA:
                setBASEDONSCHEMA(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.ITEMS_TYPE__DEFAULT:
                setDEFAULT((String)newValue);
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
            case ComponentPackage.ITEMS_TYPE__ITEM:
                getITEM().clear();
                return;
            case ComponentPackage.ITEMS_TYPE__BASEDONSCHEMA:
                unsetBASEDONSCHEMA();
                return;
            case ComponentPackage.ITEMS_TYPE__DEFAULT:
                setDEFAULT(DEFAULT_EDEFAULT);
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
            case ComponentPackage.ITEMS_TYPE__ITEM:
                return iTEM != null && !iTEM.isEmpty();
            case ComponentPackage.ITEMS_TYPE__BASEDONSCHEMA:
                return isSetBASEDONSCHEMA();
            case ComponentPackage.ITEMS_TYPE__DEFAULT:
                return DEFAULT_EDEFAULT == null ? dEFAULT != null : !DEFAULT_EDEFAULT.equals(dEFAULT);
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
        result.append(" (bASEDONSCHEMA: ");
        if (bASEDONSCHEMAESet) result.append(bASEDONSCHEMA); else result.append("<unset>");
        result.append(", dEFAULT: ");
        result.append(dEFAULT);
        result.append(')');
        return result.toString();
    }

} //ITEMSTypeImpl