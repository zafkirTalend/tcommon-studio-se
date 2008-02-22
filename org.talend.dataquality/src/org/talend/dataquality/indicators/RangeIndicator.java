/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Range Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.RangeIndicator#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.RangeIndicator#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.RangeIndicator#getRange <em>Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getRangeIndicator()
 * @model
 * @generated
 */
public interface RangeIndicator extends CompositeIndicator {
    /**
     * Returns the value of the '<em><b>Lower Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lower Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lower Value</em>' reference.
     * @see #setLowerValue(Indicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getRangeIndicator_LowerValue()
     * @model
     * @generated
     */
    Indicator getLowerValue();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.RangeIndicator#getLowerValue <em>Lower Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lower Value</em>' reference.
     * @see #getLowerValue()
     * @generated
     */
    void setLowerValue(Indicator value);

    /**
     * Returns the value of the '<em><b>Upper Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Upper Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Upper Value</em>' reference.
     * @see #setUpperValue(Indicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getRangeIndicator_UpperValue()
     * @model
     * @generated
     */
    Indicator getUpperValue();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.RangeIndicator#getUpperValue <em>Upper Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Upper Value</em>' reference.
     * @see #getUpperValue()
     * @generated
     */
    void setUpperValue(Indicator value);

    /**
     * Returns the value of the '<em><b>Range</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Range</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Range</em>' reference.
     * @see #setRange(Indicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getRangeIndicator_Range()
     * @model
     * @generated
     */
    Indicator getRange();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.RangeIndicator#getRange <em>Range</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Range</em>' reference.
     * @see #getRange()
     * @generated
     */
    void setRange(Indicator value);

} // RangeIndicator
