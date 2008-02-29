/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.IntegerSumIndicator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Integer Sum Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl#getSum <em>Sum</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntegerSumIndicatorImpl extends SumIndicatorImpl implements IntegerSumIndicator {

    /**
     * The default value of the '{@link #getSum() <em>Sum</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getSum()
     * @generated
     * @ordered
     */
    protected static final long SUM_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getSum() <em>Sum</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getSum()
     * @generated
     * @ordered
     */
    protected long sum = SUM_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected IntegerSumIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.INTEGER_SUM_INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public long getSum() {
        return sum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSum(long newSum) {
        long oldSum = sum;
        sum = newSum;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INTEGER_SUM_INDICATOR__SUM, oldSum, sum));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IndicatorsPackage.INTEGER_SUM_INDICATOR__SUM:
                return new Long(getSum());
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case IndicatorsPackage.INTEGER_SUM_INDICATOR__SUM:
                setSum(((Long)newValue).longValue());
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case IndicatorsPackage.INTEGER_SUM_INDICATOR__SUM:
                setSum(SUM_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case IndicatorsPackage.INTEGER_SUM_INDICATOR__SUM:
                return sum != SUM_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (sum: ");
        result.append(sum);
        result.append(')');
        return result.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.impl.IndicatorImpl#handle(java.lang.Object)
     */
    @Override
    public boolean handle(Object data) {
        boolean handled = super.handle(data);
        if (data == null) {
            // TODO scorreia handle null values !!!
            return false;
        }
        assert data instanceof Integer : "Sum indicator wants integer data, got: " + data;
        Integer intValue = (Integer) data;
        sum += intValue;
        return handled;
    }

} // IntegerSumIndicatorImpl
