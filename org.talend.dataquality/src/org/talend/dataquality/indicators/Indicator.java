/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;

import org.eclipse.emf.ecore.EObject;
import org.talend.dataquality.domain.LiteralValue;

import orgomg.cwmx.analysis.informationreporting.ReportField;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.Indicator#getValue <em>Value</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.Indicator#getIndicatorType <em>Indicator Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator()
 * @model
 * @generated
 */
public interface Indicator extends EObject {
    /**
     * Returns the value of the '<em><b>Indicator Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Indicator Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Indicator Type</em>' reference.
     * @see #setIndicatorType(IndicatorType)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator_IndicatorType()
     * @model
     * @generated
     */
    IndicatorType getIndicatorType();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.Indicator#getIndicatorType <em>Indicator Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Indicator Type</em>' reference.
     * @see #getIndicatorType()
     * @generated
     */
    void setIndicatorType(IndicatorType value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.talend.dataquality.domain.LiteralValue#getIndicator <em>Indicator</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * value of the indicator
     * <!-- end-model-doc -->
     * @return the value of the '<em>Value</em>' reference.
     * @see #setValue(LiteralValue)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicator_Value()
     * @see org.talend.dataquality.domain.LiteralValue#getIndicator
     * @model opposite="indicator"
     * @generated
     */
    LiteralValue getValue();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.Indicator#getValue <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' reference.
     * @see #getValue()
     * @generated
     */
    void setValue(LiteralValue value);

} // Indicator
