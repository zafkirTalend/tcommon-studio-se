/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.MaxValueIndicator;
import org.talend.dataquality.indicators.MinValueIndicator;
import org.talend.dataquality.indicators.RangeIndicator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Range Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl#getDatatype <em>Datatype</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.RangeIndicatorImpl#getRange <em>Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RangeIndicatorImpl extends CompositeIndicatorImpl implements RangeIndicator {

    /**
     * The cached value of the '{@link #getLowerValue() <em>Lower Value</em>}' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLowerValue()
     * @generated
     * @ordered
     */
    protected MinValueIndicator lowerValue;

    /**
     * The cached value of the '{@link #getUpperValue() <em>Upper Value</em>}' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getUpperValue()
     * @generated
     * @ordered
     */
    protected MaxValueIndicator upperValue;

    /**
     * The default value of the '{@link #getDatatype() <em>Datatype</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getDatatype()
     * @generated
     * @ordered
     */
    protected static final int DATATYPE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getDatatype() <em>Datatype</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getDatatype()
     * @generated
     * @ordered
     */
    protected int datatype = DATATYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getRange() <em>Range</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRange()
     * @generated
     * @ordered
     */
    protected static final String RANGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRange() <em>Range</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRange()
     * @generated
     * @ordered
     */
    protected String range = RANGE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected RangeIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.RANGE_INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public MinValueIndicator getLowerValue() {
        if (lowerValue != null && lowerValue.eIsProxy()) {
            InternalEObject oldLowerValue = (InternalEObject)lowerValue;
            lowerValue = (MinValueIndicator)eResolveProxy(oldLowerValue);
            if (lowerValue != oldLowerValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE, oldLowerValue, lowerValue));
            }
        }
        return lowerValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public MinValueIndicator basicGetLowerValue() {
        return lowerValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setLowerValue(MinValueIndicator newLowerValue) {
        MinValueIndicator oldLowerValue = lowerValue;
        lowerValue = newLowerValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE, oldLowerValue, lowerValue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public MaxValueIndicator getUpperValue() {
        if (upperValue != null && upperValue.eIsProxy()) {
            InternalEObject oldUpperValue = (InternalEObject)upperValue;
            upperValue = (MaxValueIndicator)eResolveProxy(oldUpperValue);
            if (upperValue != oldUpperValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE, oldUpperValue, upperValue));
            }
        }
        return upperValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public MaxValueIndicator basicGetUpperValue() {
        return upperValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setUpperValue(MaxValueIndicator newUpperValue) {
        MaxValueIndicator oldUpperValue = upperValue;
        upperValue = newUpperValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE, oldUpperValue, upperValue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getDatatype() {
        return datatype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDatatype(int newDatatype) {
        int oldDatatype = datatype;
        datatype = newDatatype;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.RANGE_INDICATOR__DATATYPE, oldDatatype, datatype));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getRange() {
        return range;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setRange(String newRange) {
        String oldRange = range;
        range = newRange;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.RANGE_INDICATOR__RANGE, oldRange, range));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
            case IndicatorsPackage.RANGE_INDICATOR__DATATYPE:
                return new Integer(getDatatype());
            case IndicatorsPackage.RANGE_INDICATOR__RANGE:
                return getRange();
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
            case IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE:
                setLowerValue((MinValueIndicator)newValue);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE:
                setUpperValue((MaxValueIndicator)newValue);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__DATATYPE:
                setDatatype(((Integer)newValue).intValue());
                return;
            case IndicatorsPackage.RANGE_INDICATOR__RANGE:
                setRange((String)newValue);
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
            case IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE:
                setLowerValue((MinValueIndicator)null);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE:
                setUpperValue((MaxValueIndicator)null);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__DATATYPE:
                setDatatype(DATATYPE_EDEFAULT);
                return;
            case IndicatorsPackage.RANGE_INDICATOR__RANGE:
                setRange(RANGE_EDEFAULT);
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
            case IndicatorsPackage.RANGE_INDICATOR__LOWER_VALUE:
                return lowerValue != null;
            case IndicatorsPackage.RANGE_INDICATOR__UPPER_VALUE:
                return upperValue != null;
            case IndicatorsPackage.RANGE_INDICATOR__DATATYPE:
                return datatype != DATATYPE_EDEFAULT;
            case IndicatorsPackage.RANGE_INDICATOR__RANGE:
                return RANGE_EDEFAULT == null ? range != null : !RANGE_EDEFAULT.equals(range);
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
        result.append(" (datatype: ");
        result.append(datatype);
        result.append(", range: ");
        result.append(range);
        result.append(')');
        return result.toString();
    }

} // RangeIndicatorImpl
