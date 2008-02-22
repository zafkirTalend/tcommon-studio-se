/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.RangeIndicator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Range Indicator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl#getRange <em>Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RangeIndicatorImpl extends CompositeIndicatorImpl implements RangeIndicator {
    /**
     * The cached value of the '{@link #getLowerValue() <em>Lower Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLowerValue()
     * @generated
     * @ordered
     */
    protected Indicator lowerValue;

    /**
     * The cached value of the '{@link #getUpperValue() <em>Upper Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpperValue()
     * @generated
     * @ordered
     */
    protected Indicator upperValue;

    /**
     * The cached value of the '{@link #getRange() <em>Range</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRange()
     * @generated
     * @ordered
     */
    protected Indicator range;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RangeIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.RANGE_INDICATOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getLowerValue() {
        if (lowerValue != null && lowerValue.eIsProxy()) {
            InternalEObject oldLowerValue = (InternalEObject)lowerValue;
            lowerValue = (Indicator)eResolveProxy(oldLowerValue);
            if (lowerValue != oldLowerValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE, oldLowerValue, lowerValue));
            }
        }
        return lowerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetLowerValue() {
        return lowerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLowerValue(Indicator newLowerValue) {
        Indicator oldLowerValue = lowerValue;
        lowerValue = newLowerValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE, oldLowerValue, lowerValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getUpperValue() {
        if (upperValue != null && upperValue.eIsProxy()) {
            InternalEObject oldUpperValue = (InternalEObject)upperValue;
            upperValue = (Indicator)eResolveProxy(oldUpperValue);
            if (upperValue != oldUpperValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE, oldUpperValue, upperValue));
            }
        }
        return upperValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetUpperValue() {
        return upperValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpperValue(Indicator newUpperValue) {
        Indicator oldUpperValue = upperValue;
        upperValue = newUpperValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE, oldUpperValue, upperValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getRange() {
        if (range != null && range.eIsProxy()) {
            InternalEObject oldRange = (InternalEObject)range;
            range = (Indicator)eResolveProxy(oldRange);
            if (range != oldRange) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.RANGE_INDICATOR__RANGE, oldRange, range));
            }
        }
        return range;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetRange() {
        return range;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRange(Indicator newRange) {
        Indicator oldRange = range;
        range = newRange;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.RANGE_INDICATOR__RANGE, oldRange, range));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE:
                if (resolve) return getLowerValue();
                return basicGetLowerValue();
            case IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE:
                if (resolve) return getUpperValue();
                return basicGetUpperValue();
            case IndicatorsPackage.RANGE_INDICATOR__RANGE:
                if (resolve) return getRange();
                return basicGetRange();
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
            case IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE:
                setLowerValue((Indicator)newValue);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE:
                setUpperValue((Indicator)newValue);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__RANGE:
                setRange((Indicator)newValue);
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
            case IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE:
                setLowerValue((Indicator)null);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE:
                setUpperValue((Indicator)null);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__RANGE:
                setRange((Indicator)null);
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
            case IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE:
                return lowerValue != null;
            case IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE:
                return upperValue != null;
            case IndicatorsPackage.RANGE_INDICATOR__RANGE:
                return range != null;
        }
        return super.eIsSet(featureID);
    }

} //RangeIndicatorImpl
