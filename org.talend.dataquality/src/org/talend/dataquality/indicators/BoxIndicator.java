/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Box Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.BoxIndicator#getMin <em>Min</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.BoxIndicator#getMax <em>Max</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.BoxIndicator#getFirstQuartile <em>First Quartile</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.BoxIndicator#getThirdQuartile <em>Third Quartile</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.BoxIndicator#getIQR <em>IQR</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getBoxIndicator()
 * @model
 * @generated
 */
public interface BoxIndicator extends CompositeIndicator {
    /**
     * Returns the value of the '<em><b>Min</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Min</em>' reference.
     * @see #setMin(Indicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getBoxIndicator_Min()
     * @model
     * @generated
     */
    Indicator getMin();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.BoxIndicator#getMin <em>Min</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min</em>' reference.
     * @see #getMin()
     * @generated
     */
    void setMin(Indicator value);

    /**
     * Returns the value of the '<em><b>Max</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Max</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Max</em>' reference.
     * @see #setMax(Indicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getBoxIndicator_Max()
     * @model
     * @generated
     */
    Indicator getMax();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.BoxIndicator#getMax <em>Max</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max</em>' reference.
     * @see #getMax()
     * @generated
     */
    void setMax(Indicator value);

    /**
     * Returns the value of the '<em><b>First Quartile</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>First Quartile</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>First Quartile</em>' reference.
     * @see #setFirstQuartile(Indicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getBoxIndicator_FirstQuartile()
     * @model
     * @generated
     */
    Indicator getFirstQuartile();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.BoxIndicator#getFirstQuartile <em>First Quartile</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>First Quartile</em>' reference.
     * @see #getFirstQuartile()
     * @generated
     */
    void setFirstQuartile(Indicator value);

    /**
     * Returns the value of the '<em><b>Third Quartile</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Third Quartile</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Third Quartile</em>' reference.
     * @see #setThirdQuartile(Indicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getBoxIndicator_ThirdQuartile()
     * @model
     * @generated
     */
    Indicator getThirdQuartile();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.BoxIndicator#getThirdQuartile <em>Third Quartile</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Third Quartile</em>' reference.
     * @see #getThirdQuartile()
     * @generated
     */
    void setThirdQuartile(Indicator value);

    /**
     * Returns the value of the '<em><b>IQR</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>IQR</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>IQR</em>' reference.
     * @see #setIQR(Indicator)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getBoxIndicator_IQR()
     * @model
     * @generated
     */
    Indicator getIQR();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.BoxIndicator#getIQR <em>IQR</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>IQR</em>' reference.
     * @see #getIQR()
     * @generated
     */
    void setIQR(Indicator value);

} // BoxIndicator
