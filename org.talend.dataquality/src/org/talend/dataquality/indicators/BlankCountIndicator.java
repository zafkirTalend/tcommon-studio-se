/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Blank Count Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.BlankCountIndicator#getBlankCount <em>Blank Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getBlankCountIndicator()
 * @model
 * @generated
 */
public interface BlankCountIndicator extends TextIndicator {
    /**
     * Returns the value of the '<em><b>Blank Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Blank Count</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Blank Count</em>' attribute.
     * @see #setBlankCount(int)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getBlankCountIndicator_BlankCount()
     * @model
     * @generated
     */
    int getBlankCount();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.BlankCountIndicator#getBlankCount <em>Blank Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Blank Count</em>' attribute.
     * @see #getBlankCount()
     * @generated
     */
    void setBlankCount(int value);

} // BlankCountIndicator
