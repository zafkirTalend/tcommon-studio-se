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

import org.talend.dataquality.indicators.BoxIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Box Indicator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.BoxIndicatorImpl#getMin <em>Min</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.BoxIndicatorImpl#getMax <em>Max</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.BoxIndicatorImpl#getFirstQuartile <em>First Quartile</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.BoxIndicatorImpl#getThirdQuartile <em>Third Quartile</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.BoxIndicatorImpl#getIQR <em>IQR</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoxIndicatorImpl extends CompositeIndicatorImpl implements BoxIndicator {
    /**
     * The cached value of the '{@link #getMin() <em>Min</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
    protected Indicator min;

    /**
     * The cached value of the '{@link #getMax() <em>Max</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected Indicator max;

    /**
     * The cached value of the '{@link #getFirstQuartile() <em>First Quartile</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFirstQuartile()
     * @generated
     * @ordered
     */
    protected Indicator firstQuartile;

    /**
     * The cached value of the '{@link #getThirdQuartile() <em>Third Quartile</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getThirdQuartile()
     * @generated
     * @ordered
     */
    protected Indicator thirdQuartile;

    /**
     * The cached value of the '{@link #getIQR() <em>IQR</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIQR()
     * @generated
     * @ordered
     */
    protected Indicator iqr;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BoxIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.BOX_INDICATOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getMin() {
        if (min != null && min.eIsProxy()) {
            InternalEObject oldMin = (InternalEObject)min;
            min = (Indicator)eResolveProxy(oldMin);
            if (min != oldMin) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.BOX_INDICATOR__MIN, oldMin, min));
            }
        }
        return min;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetMin() {
        return min;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMin(Indicator newMin) {
        Indicator oldMin = min;
        min = newMin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.BOX_INDICATOR__MIN, oldMin, min));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getMax() {
        if (max != null && max.eIsProxy()) {
            InternalEObject oldMax = (InternalEObject)max;
            max = (Indicator)eResolveProxy(oldMax);
            if (max != oldMax) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.BOX_INDICATOR__MAX, oldMax, max));
            }
        }
        return max;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetMax() {
        return max;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMax(Indicator newMax) {
        Indicator oldMax = max;
        max = newMax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.BOX_INDICATOR__MAX, oldMax, max));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getFirstQuartile() {
        if (firstQuartile != null && firstQuartile.eIsProxy()) {
            InternalEObject oldFirstQuartile = (InternalEObject)firstQuartile;
            firstQuartile = (Indicator)eResolveProxy(oldFirstQuartile);
            if (firstQuartile != oldFirstQuartile) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.BOX_INDICATOR__FIRST_QUARTILE, oldFirstQuartile, firstQuartile));
            }
        }
        return firstQuartile;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetFirstQuartile() {
        return firstQuartile;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFirstQuartile(Indicator newFirstQuartile) {
        Indicator oldFirstQuartile = firstQuartile;
        firstQuartile = newFirstQuartile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.BOX_INDICATOR__FIRST_QUARTILE, oldFirstQuartile, firstQuartile));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getThirdQuartile() {
        if (thirdQuartile != null && thirdQuartile.eIsProxy()) {
            InternalEObject oldThirdQuartile = (InternalEObject)thirdQuartile;
            thirdQuartile = (Indicator)eResolveProxy(oldThirdQuartile);
            if (thirdQuartile != oldThirdQuartile) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.BOX_INDICATOR__THIRD_QUARTILE, oldThirdQuartile, thirdQuartile));
            }
        }
        return thirdQuartile;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetThirdQuartile() {
        return thirdQuartile;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setThirdQuartile(Indicator newThirdQuartile) {
        Indicator oldThirdQuartile = thirdQuartile;
        thirdQuartile = newThirdQuartile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.BOX_INDICATOR__THIRD_QUARTILE, oldThirdQuartile, thirdQuartile));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator getIQR() {
        if (iqr != null && iqr.eIsProxy()) {
            InternalEObject oldIQR = (InternalEObject)iqr;
            iqr = (Indicator)eResolveProxy(oldIQR);
            if (iqr != oldIQR) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.BOX_INDICATOR__IQR, oldIQR, iqr));
            }
        }
        return iqr;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator basicGetIQR() {
        return iqr;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIQR(Indicator newIQR) {
        Indicator oldIQR = iqr;
        iqr = newIQR;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.BOX_INDICATOR__IQR, oldIQR, iqr));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IndicatorsPackage.BOX_INDICATOR__MIN:
                if (resolve) return getMin();
                return basicGetMin();
            case IndicatorsPackage.BOX_INDICATOR__MAX:
                if (resolve) return getMax();
                return basicGetMax();
            case IndicatorsPackage.BOX_INDICATOR__FIRST_QUARTILE:
                if (resolve) return getFirstQuartile();
                return basicGetFirstQuartile();
            case IndicatorsPackage.BOX_INDICATOR__THIRD_QUARTILE:
                if (resolve) return getThirdQuartile();
                return basicGetThirdQuartile();
            case IndicatorsPackage.BOX_INDICATOR__IQR:
                if (resolve) return getIQR();
                return basicGetIQR();
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
            case IndicatorsPackage.BOX_INDICATOR__MIN:
                setMin((Indicator)newValue);
                return;
            case IndicatorsPackage.BOX_INDICATOR__MAX:
                setMax((Indicator)newValue);
                return;
            case IndicatorsPackage.BOX_INDICATOR__FIRST_QUARTILE:
                setFirstQuartile((Indicator)newValue);
                return;
            case IndicatorsPackage.BOX_INDICATOR__THIRD_QUARTILE:
                setThirdQuartile((Indicator)newValue);
                return;
            case IndicatorsPackage.BOX_INDICATOR__IQR:
                setIQR((Indicator)newValue);
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
            case IndicatorsPackage.BOX_INDICATOR__MIN:
                setMin((Indicator)null);
                return;
            case IndicatorsPackage.BOX_INDICATOR__MAX:
                setMax((Indicator)null);
                return;
            case IndicatorsPackage.BOX_INDICATOR__FIRST_QUARTILE:
                setFirstQuartile((Indicator)null);
                return;
            case IndicatorsPackage.BOX_INDICATOR__THIRD_QUARTILE:
                setThirdQuartile((Indicator)null);
                return;
            case IndicatorsPackage.BOX_INDICATOR__IQR:
                setIQR((Indicator)null);
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
            case IndicatorsPackage.BOX_INDICATOR__MIN:
                return min != null;
            case IndicatorsPackage.BOX_INDICATOR__MAX:
                return max != null;
            case IndicatorsPackage.BOX_INDICATOR__FIRST_QUARTILE:
                return firstQuartile != null;
            case IndicatorsPackage.BOX_INDICATOR__THIRD_QUARTILE:
                return thirdQuartile != null;
            case IndicatorsPackage.BOX_INDICATOR__IQR:
                return iqr != null;
        }
        return super.eIsSet(featureID);
    }

} //BoxIndicatorImpl
