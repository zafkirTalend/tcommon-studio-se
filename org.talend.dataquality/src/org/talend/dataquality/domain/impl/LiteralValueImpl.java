/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.domain.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.dataquality.domain.DomainPackage;
import org.talend.dataquality.domain.LiteralValue;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Literal Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.domain.impl.LiteralValueImpl#getEncodeValueMeaning <em>Encode Value Meaning</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.impl.LiteralValueImpl#getIndicator <em>Indicator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LiteralValueImpl extends EObjectImpl implements LiteralValue {
    /**
     * The default value of the '{@link #getEncodeValueMeaning() <em>Encode Value Meaning</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEncodeValueMeaning()
     * @generated
     * @ordered
     */
    protected static final String ENCODE_VALUE_MEANING_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEncodeValueMeaning() <em>Encode Value Meaning</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEncodeValueMeaning()
     * @generated
     * @ordered
     */
    protected String encodeValueMeaning = ENCODE_VALUE_MEANING_EDEFAULT;

    /**
     * The cached value of the '{@link #getIndicator() <em>Indicator</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIndicator()
     * @generated
     * @ordered
     */
    protected Indicator indicator;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LiteralValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DomainPackage.Literals.LITERAL_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEncodeValueMeaning() {
        return encodeValueMeaning;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEncodeValueMeaning(String newEncodeValueMeaning) {
        String oldEncodeValueMeaning = encodeValueMeaning;
        encodeValueMeaning = newEncodeValueMeaning;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.LITERAL_VALUE__ENCODE_VALUE_MEANING, oldEncodeValueMeaning, encodeValueMeaning));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getIndicator() {
        if (indicator != null && indicator.eIsProxy()) {
            InternalEObject oldIndicator = (InternalEObject)indicator;
            indicator = (Indicator)eResolveProxy(oldIndicator);
            if (indicator != oldIndicator) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.LITERAL_VALUE__INDICATOR, oldIndicator, indicator));
            }
        }
        return indicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetIndicator() {
        return indicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIndicator(Indicator newIndicator, NotificationChain msgs) {
        Indicator oldIndicator = indicator;
        indicator = newIndicator;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DomainPackage.LITERAL_VALUE__INDICATOR, oldIndicator, newIndicator);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIndicator(Indicator newIndicator) {
        if (newIndicator != indicator) {
            NotificationChain msgs = null;
            if (indicator != null)
                msgs = ((InternalEObject)indicator).eInverseRemove(this, IndicatorsPackage.INDICATOR__VALUE, Indicator.class, msgs);
            if (newIndicator != null)
                msgs = ((InternalEObject)newIndicator).eInverseAdd(this, IndicatorsPackage.INDICATOR__VALUE, Indicator.class, msgs);
            msgs = basicSetIndicator(newIndicator, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.LITERAL_VALUE__INDICATOR, newIndicator, newIndicator));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DomainPackage.LITERAL_VALUE__INDICATOR:
                if (indicator != null)
                    msgs = ((InternalEObject)indicator).eInverseRemove(this, IndicatorsPackage.INDICATOR__VALUE, Indicator.class, msgs);
                return basicSetIndicator((Indicator)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DomainPackage.LITERAL_VALUE__INDICATOR:
                return basicSetIndicator(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DomainPackage.LITERAL_VALUE__ENCODE_VALUE_MEANING:
                return getEncodeValueMeaning();
            case DomainPackage.LITERAL_VALUE__INDICATOR:
                if (resolve) return getIndicator();
                return basicGetIndicator();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case DomainPackage.LITERAL_VALUE__ENCODE_VALUE_MEANING:
                setEncodeValueMeaning((String)newValue);
                return;
            case DomainPackage.LITERAL_VALUE__INDICATOR:
                setIndicator((Indicator)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case DomainPackage.LITERAL_VALUE__ENCODE_VALUE_MEANING:
                setEncodeValueMeaning(ENCODE_VALUE_MEANING_EDEFAULT);
                return;
            case DomainPackage.LITERAL_VALUE__INDICATOR:
                setIndicator((Indicator)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case DomainPackage.LITERAL_VALUE__ENCODE_VALUE_MEANING:
                return ENCODE_VALUE_MEANING_EDEFAULT == null ? encodeValueMeaning != null : !ENCODE_VALUE_MEANING_EDEFAULT.equals(encodeValueMeaning);
            case DomainPackage.LITERAL_VALUE__INDICATOR:
                return indicator != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (encodeValueMeaning: ");
        result.append(encodeValueMeaning);
        result.append(')');
        return result.toString();
    }

} //LiteralValueImpl
