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
 *   <li>{@link org.talend.dataquality.indicators.RangeIndicator#getDatatype <em>Datatype</em>}</li>
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
     * @see #setLowerValue(MinValueIndicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getRangeIndicator_LowerValue()
     * @model
     * @generated
     */
    MinValueIndicator getLowerValue();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.RangeIndicator#getLowerValue <em>Lower Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lower Value</em>' reference.
     * @see #getLowerValue()
     * @generated
     */
    void setLowerValue(MinValueIndicator value);

    /**
     * Returns the value of the '<em><b>Upper Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Upper Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Upper Value</em>' reference.
     * @see #setUpperValue(MaxValueIndicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getRangeIndicator_UpperValue()
     * @model
     * @generated
     */
    MaxValueIndicator getUpperValue();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.RangeIndicator#getUpperValue <em>Upper Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Upper Value</em>' reference.
     * @see #getUpperValue()
     * @generated
     */
    void setUpperValue(MaxValueIndicator value);

    /**
     * Returns the value of the '<em><b>Datatype</b></em>' attribute.
     * The literals are from the enumeration {@link org.talend.dataquality.indicators.Datatype}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Datatype</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Datatype</em>' attribute.
     * @see org.talend.dataquality.indicators.Datatype
     * @see #setDatatype(Datatype)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getRangeIndicator_Datatype()
     * @model
     * @generated
     */
    Datatype getDatatype();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.RangeIndicator#getDatatype <em>Datatype</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Datatype</em>' attribute.
     * @see org.talend.dataquality.indicators.Datatype
     * @see #getDatatype()
     * @generated
     */
    void setDatatype(Datatype value);

    /**
     * Returns the value of the '<em><b>Range</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Range</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Range</em>' attribute.
     * @see #setRange(String)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getRangeIndicator_Range()
     * @model
     * @generated
     */
    String getRange();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.RangeIndicator#getRange <em>Range</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Range</em>' attribute.
     * @see #getRange()
     * @generated
     */
    void setRange(String value);

} // RangeIndicator
