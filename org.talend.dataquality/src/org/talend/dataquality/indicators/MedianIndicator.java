/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Median Indicator</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * TODO model the frequency table
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.MedianIndicator#getMedian <em>Median</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.MedianIndicator#getFrequenceTable <em>Frequence Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getMedianIndicator()
 * @model
 * @generated
 */
public interface MedianIndicator extends Indicator {

    /**
     * Returns the value of the '<em><b>Median</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The median value
     * <!-- end-model-doc -->
     * @return the value of the '<em>Median</em>' attribute.
     * @see #isSetMedian()
     * @see #unsetMedian()
     * @see #setMedian(BigDecimal)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getMedianIndicator_Median()
     * @model unsettable="true"
     * @generated
     */
    BigDecimal getMedian();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.MedianIndicator#getMedian <em>Median</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Median</em>' attribute.
     * @see #isSetMedian()
     * @see #unsetMedian()
     * @see #getMedian()
     * @generated
     */
    void setMedian(BigDecimal value);

    /**
     * Unsets the value of the '{@link org.talend.dataquality.indicators.MedianIndicator#getMedian <em>Median</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMedian()
     * @see #getMedian()
     * @see #setMedian(BigDecimal)
     * @generated
     */
    void unsetMedian();

    /**
     * Returns whether the value of the '{@link org.talend.dataquality.indicators.MedianIndicator#getMedian <em>Median</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Median</em>' attribute is set.
     * @see #unsetMedian()
     * @see #getMedian()
     * @see #setMedian(BigDecimal)
     * @generated
     */
    boolean isSetMedian();

    /**
     * Returns the value of the '<em><b>Frequence Table</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Frequence Table</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Frequence Table</em>' attribute.
     * @see #setFrequenceTable(TreeMap)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getMedianIndicator_FrequenceTable()
     * @model dataType="org.talend.dataquality.indicators.JavaTreeMap"
     * @generated
     */
    TreeMap<Object, Long> getFrequenceTable();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.MedianIndicator#getFrequenceTable <em>Frequence Table</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Frequence Table</em>' attribute.
     * @see #getFrequenceTable()
     * @generated
     */
    void setFrequenceTable(TreeMap<Object, Long> value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * computes the median and update attribute "median".
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean computeMedian();

} // MedianIndicator
