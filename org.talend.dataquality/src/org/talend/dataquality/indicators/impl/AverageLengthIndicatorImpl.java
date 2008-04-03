/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.talend.dataquality.indicators.AverageLengthIndicator;
import org.talend.dataquality.indicators.IndicatorsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Average Length Indicator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.AverageLengthIndicatorImpl#getSumLength <em>Sum Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AverageLengthIndicatorImpl extends LengthIndicatorImpl implements AverageLengthIndicator {
    /**
     * The default value of the '{@link #getSumLength() <em>Sum Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSumLength()
     * @generated
     * @ordered
     */
    protected static final long SUM_LENGTH_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getSumLength() <em>Sum Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSumLength()
     * @generated
     * @ordered
     */
    protected long sumLength = SUM_LENGTH_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AverageLengthIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.AVERAGE_LENGTH_INDICATOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long getSumLength() {
        return sumLength;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSumLength(long newSumLength) {
        long oldSumLength = sumLength;
        sumLength = newSumLength;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.AVERAGE_LENGTH_INDICATOR__SUM_LENGTH, oldSumLength, sumLength));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IndicatorsPackage.AVERAGE_LENGTH_INDICATOR__SUM_LENGTH:
                return new Long(getSumLength());
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
            case IndicatorsPackage.AVERAGE_LENGTH_INDICATOR__SUM_LENGTH:
                setSumLength(((Long)newValue).longValue());
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
            case IndicatorsPackage.AVERAGE_LENGTH_INDICATOR__SUM_LENGTH:
                setSumLength(SUM_LENGTH_EDEFAULT);
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
            case IndicatorsPackage.AVERAGE_LENGTH_INDICATOR__SUM_LENGTH:
                return sumLength != SUM_LENGTH_EDEFAULT;
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
        result.append(" (sumLength: ");
        result.append(sumLength);
        result.append(')');
        return result.toString();
    }

} //AverageLengthIndicatorImpl
