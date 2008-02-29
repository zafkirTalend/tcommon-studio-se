/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Double Sum Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.DoubleSumIndicator#getSum <em>Sum</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getDoubleSumIndicator()
 * @model
 * @generated
 */
public interface DoubleSumIndicator extends SumIndicator {
    /**
     * Returns the value of the '<em><b>Sum</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sum</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sum</em>' attribute.
     * @see #setSum(double)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getDoubleSumIndicator_Sum()
     * @model
     * @generated
     */
    double getSum();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.DoubleSumIndicator#getSum <em>Sum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sum</em>' attribute.
     * @see #getSum()
     * @generated
     */
    void setSum(double value);

} // DoubleSumIndicator
