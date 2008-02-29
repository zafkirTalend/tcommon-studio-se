/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Frequency Indicator</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Stores Frequencies for each value.
 * TODO store them in EMF (need to be modeled). 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.FrequencyIndicator#getMode <em>Mode</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.FrequencyIndicator#getUniqueValues <em>Unique Values</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.FrequencyIndicator#getDistinctValueCount <em>Distinct Value Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.FrequencyIndicator#getUniqueValueCount <em>Unique Value Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.FrequencyIndicator#getDupplicateValueCount <em>Dupplicate Value Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.FrequencyIndicator#getValueToFreq <em>Value To Freq</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getFrequencyIndicator()
 * @model
 * @generated
 */
public interface FrequencyIndicator extends Indicator {

    /**
     * Returns the value of the '<em><b>Mode</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * most frequent value
     * <!-- end-model-doc -->
     * @return the value of the '<em>Mode</em>' attribute.
     * @see #setMode(Object)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getFrequencyIndicator_Mode()
     * @model
     * @generated
     */
    Object getMode();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.FrequencyIndicator#getMode <em>Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mode</em>' attribute.
     * @see #getMode()
     * @generated
     */
    void setMode(Object value);

    /**
     * Returns the value of the '<em><b>Unique Values</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Object}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unique Values</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Unique Values</em>' attribute list.
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getFrequencyIndicator_UniqueValues()
     * @model
     * @generated
     */
    EList<Object> getUniqueValues();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * returns the number of apparition of the given data.
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    long getCount(Object dataValue);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * returns the frequency (between 0 and 1)
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    double getFrequency(Object dataValue);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" dataType="org.talend.dataquality.indicators.JavaSet"
     * @generated
     */
    Set<Object> getDistinctValues();

    /**
     * Returns the value of the '<em><b>Distinct Value Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Distinct Value Count</em>' attribute.
     * @see #setDistinctValueCount(int)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getFrequencyIndicator_DistinctValueCount()
     * @model
     * @generated
     */
    int getDistinctValueCount();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.FrequencyIndicator#getDistinctValueCount <em>Distinct Value Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Distinct Value Count</em>' attribute.
     * @see #getDistinctValueCount()
     * @generated
     */
    void setDistinctValueCount(int value);

    /**
     * Returns the value of the '<em><b>Unique Value Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Unique Value Count</em>' attribute.
     * @see #setUniqueValueCount(int)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getFrequencyIndicator_UniqueValueCount()
     * @model
     * @generated
     */
    int getUniqueValueCount();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.FrequencyIndicator#getUniqueValueCount <em>Unique Value Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unique Value Count</em>' attribute.
     * @see #getUniqueValueCount()
     * @generated
     */
    void setUniqueValueCount(int value);

    /**
     * Returns the value of the '<em><b>Dupplicate Value Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dupplicate Value Count</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dupplicate Value Count</em>' attribute.
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getFrequencyIndicator_DupplicateValueCount()
     * @model transient="true" changeable="false" volatile="true"
     * @generated
     */
    int getDupplicateValueCount();

    /**
     * Returns the value of the '<em><b>Value To Freq</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value To Freq</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value To Freq</em>' attribute.
     * @see #setValueToFreq(HashMap)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getFrequencyIndicator_ValueToFreq()
     * @model dataType="org.talend.dataquality.indicators.JavaHashMap"
     * @generated
     */
    HashMap<Object, Long> getValueToFreq();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.FrequencyIndicator#getValueToFreq <em>Value To Freq</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value To Freq</em>' attribute.
     * @see #getValueToFreq()
     * @generated
     */
    void setValueToFreq(HashMap<Object, Long> value);

} // FrequencyIndicator
