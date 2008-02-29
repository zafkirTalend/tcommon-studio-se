/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.talend.dataquality.indicators.FrequencyIndicator;
import org.talend.dataquality.indicators.IndicatorsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Frequency Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl#getUniqueValues <em>Unique Values</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl#getDistinctValueCount <em>Distinct Value Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl#getUniqueValueCount <em>Unique Value Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl#getDupplicateValueCount <em>Dupplicate Value Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.FrequencyIndicatorImpl#getValueToFreq <em>Value To Freq</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FrequencyIndicatorImpl extends IndicatorImpl implements FrequencyIndicator {

    /**
     * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getMode()
     * @generated
     * @ordered
     */
    protected static final Object MODE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getMode()
     * @generated
     * @ordered
     */
    protected Object mode = MODE_EDEFAULT;

    /**
     * The cached value of the '{@link #getUniqueValues() <em>Unique Values</em>}' attribute list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getUniqueValues()
     * @generated
     * @ordered
     */
    protected EList<Object> uniqueValues;

    /**
     * The default value of the '{@link #getDistinctValueCount() <em>Distinct Value Count</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDistinctValueCount()
     * @generated
     * @ordered
     */
    protected static final int DISTINCT_VALUE_COUNT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getDistinctValueCount() <em>Distinct Value Count</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDistinctValueCount()
     * @generated
     * @ordered
     */
    protected int distinctValueCount = DISTINCT_VALUE_COUNT_EDEFAULT;

    /**
     * The default value of the '{@link #getUniqueValueCount() <em>Unique Value Count</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getUniqueValueCount()
     * @generated
     * @ordered
     */
    protected static final int UNIQUE_VALUE_COUNT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getUniqueValueCount() <em>Unique Value Count</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getUniqueValueCount()
     * @generated
     * @ordered
     */
    protected int uniqueValueCount = UNIQUE_VALUE_COUNT_EDEFAULT;

    /**
     * The default value of the '{@link #getDupplicateValueCount() <em>Dupplicate Value Count</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDupplicateValueCount()
     * @generated
     * @ordered
     */
    protected static final int DUPPLICATE_VALUE_COUNT_EDEFAULT = 0;

    /**
     * The default value of the '{@link #getValueToFreq() <em>Value To Freq</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueToFreq()
     * @generated
     * @ordered
     */
    protected static final HashMap<Object, Long> VALUE_TO_FREQ_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValueToFreq() <em>Value To Freq</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueToFreq()
     * @generated
     * @ordered
     */
    protected HashMap<Object, Long> valueToFreq = VALUE_TO_FREQ_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FrequencyIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.FREQUENCY_INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Object getMode() {
        return mode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setMode(Object newMode) {
        Object oldMode = mode;
        mode = newMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.FREQUENCY_INDICATOR__MODE, oldMode, mode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Object> getUniqueValues() {
        if (uniqueValues == null) {
            uniqueValues = new EDataTypeUniqueEList<Object>(Object.class, this, IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUES);
        }
        return uniqueValues;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public long getCount(Object dataValue) {
        Long freq = this.valueToFreq.get(dataValue);
        return (freq == null) ? 0L : freq;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public double getFrequency(Object dataValue) {
        if (this.count == 0L) {
            return 0.0d;
        }
        return getCount(dataValue) / this.count;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public Set<Object> getDistinctValues() {
        if (!distinctComputed) {
            distinctValues = computeDistinctValues();
        }
        return distinctValues;
    }

    /**
     * ADDED Method "computeDistinctValues".
     * 
     * @return the distinct values
     */
    private Set<Object> computeDistinctValues() {
        Set<Object> keySet = this.valueToFreq.keySet();
        this.setDistinctValueCount(keySet.size());
        distinctComputed = true;
        return keySet;
    }

    private Set<Object> distinctValues = null;

    /**
     * @generated
     */
    protected int getDistinctValueCountGen() {
        return distinctValueCount;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public int getDistinctValueCount() {
        if (!distinctComputed) {
            computeDistinctValues();
        }
        return getDistinctValueCountGen();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDistinctValueCount(int newDistinctValueCount) {
        int oldDistinctValueCount = distinctValueCount;
        distinctValueCount = newDistinctValueCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.FREQUENCY_INDICATOR__DISTINCT_VALUE_COUNT, oldDistinctValueCount, distinctValueCount));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getUniqueValueCount() {
        return uniqueValueCount;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setUniqueValueCount(int newUniqueValueCount) {
        int oldUniqueValueCount = uniqueValueCount;
        uniqueValueCount = newUniqueValueCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUE_COUNT, oldUniqueValueCount, uniqueValueCount));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getDupplicateValueCount() {
        // TODO: implement this method to return the 'Dupplicate Value Count' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public HashMap<Object, Long> getValueToFreqGen() {
        return valueToFreq;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.FrequencyIndicator#getValueToFreq() @generated NOT
     */
    public HashMap<Object, Long> getValueToFreq() {
        if (valueToFreq == VALUE_TO_FREQ_EDEFAULT) {
            valueToFreq = new HashMap<Object, Long>();
        }
        return getValueToFreqGen();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setValueToFreq(HashMap<Object, Long> newValueToFreq) {
        HashMap<Object, Long> oldValueToFreq = valueToFreq;
        valueToFreq = newValueToFreq;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.FREQUENCY_INDICATOR__VALUE_TO_FREQ, oldValueToFreq, valueToFreq));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IndicatorsPackage.FREQUENCY_INDICATOR__MODE:
                return getMode();
            case IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUES:
                return getUniqueValues();
            case IndicatorsPackage.FREQUENCY_INDICATOR__DISTINCT_VALUE_COUNT:
                return new Integer(getDistinctValueCount());
            case IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUE_COUNT:
                return new Integer(getUniqueValueCount());
            case IndicatorsPackage.FREQUENCY_INDICATOR__DUPPLICATE_VALUE_COUNT:
                return new Integer(getDupplicateValueCount());
            case IndicatorsPackage.FREQUENCY_INDICATOR__VALUE_TO_FREQ:
                return getValueToFreq();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case IndicatorsPackage.FREQUENCY_INDICATOR__MODE:
                setMode(newValue);
                return;
            case IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUES:
                getUniqueValues().clear();
                getUniqueValues().addAll((Collection<? extends Object>)newValue);
                return;
            case IndicatorsPackage.FREQUENCY_INDICATOR__DISTINCT_VALUE_COUNT:
                setDistinctValueCount(((Integer)newValue).intValue());
                return;
            case IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUE_COUNT:
                setUniqueValueCount(((Integer)newValue).intValue());
                return;
            case IndicatorsPackage.FREQUENCY_INDICATOR__VALUE_TO_FREQ:
                setValueToFreq((HashMap<Object, Long>)newValue);
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
            case IndicatorsPackage.FREQUENCY_INDICATOR__MODE:
                setMode(MODE_EDEFAULT);
                return;
            case IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUES:
                getUniqueValues().clear();
                return;
            case IndicatorsPackage.FREQUENCY_INDICATOR__DISTINCT_VALUE_COUNT:
                setDistinctValueCount(DISTINCT_VALUE_COUNT_EDEFAULT);
                return;
            case IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUE_COUNT:
                setUniqueValueCount(UNIQUE_VALUE_COUNT_EDEFAULT);
                return;
            case IndicatorsPackage.FREQUENCY_INDICATOR__VALUE_TO_FREQ:
                setValueToFreq(VALUE_TO_FREQ_EDEFAULT);
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
            case IndicatorsPackage.FREQUENCY_INDICATOR__MODE:
                return MODE_EDEFAULT == null ? mode != null : !MODE_EDEFAULT.equals(mode);
            case IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUES:
                return uniqueValues != null && !uniqueValues.isEmpty();
            case IndicatorsPackage.FREQUENCY_INDICATOR__DISTINCT_VALUE_COUNT:
                return distinctValueCount != DISTINCT_VALUE_COUNT_EDEFAULT;
            case IndicatorsPackage.FREQUENCY_INDICATOR__UNIQUE_VALUE_COUNT:
                return uniqueValueCount != UNIQUE_VALUE_COUNT_EDEFAULT;
            case IndicatorsPackage.FREQUENCY_INDICATOR__DUPPLICATE_VALUE_COUNT:
                return getDupplicateValueCount() != DUPPLICATE_VALUE_COUNT_EDEFAULT;
            case IndicatorsPackage.FREQUENCY_INDICATOR__VALUE_TO_FREQ:
                return VALUE_TO_FREQ_EDEFAULT == null ? valueToFreq != null : !VALUE_TO_FREQ_EDEFAULT.equals(valueToFreq);
        }
        return super.eIsSet(featureID);
    }

    /**
     * true is distinct value count is computed
     */
    private boolean distinctComputed = false;

    @Override
    public boolean handle(Object data) {
        super.handle(data);
        Long freq = getValueToFreq().get(data);
        if (freq == null) { // new data
            freq = 0L;
            this.getUniqueValues().add(data);
            this.uniqueValueCount++;
        } else { // data not new
            this.getUniqueValues().remove(data);
            if (freq.compareTo(1L) == 0) { // decrement when data is seen twice
                this.uniqueValueCount--;
            }
        }
        freq++;
        // TODO scorreia compute distinct values ?
        valueToFreq.put(data, freq);
        return freq > 0;

    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        Set<Object> keySet = this.valueToFreq.keySet();
        for (Object object : keySet) {
            buf.append(object);
            buf.append(": ");
            buf.append(this.valueToFreq.get(object));
            buf.append("\n");
        }
        // TODO scorreia could compute average frequency (sum values/# keys)
        return buf.toString();
    }

} // FrequencyIndicatorImpl
