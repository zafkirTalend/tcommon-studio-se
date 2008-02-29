/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;

import org.eclipse.emf.ecore.EObject;

import org.talend.dataquality.domain.Domain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indicator Parameters</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.IndicatorParameters#getIndicatorValidDomain <em>Indicator Valid Domain</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.IndicatorParameters#getDataValidDomain <em>Data Valid Domain</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicatorParameters()
 * @model
 * @generated
 */
public interface IndicatorParameters extends EObject {
    /**
     * Returns the value of the '<em><b>Indicator Valid Domain</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Thresholds (or patterns) on indicators. Indicator that do not respect these thresholds are used to compute a quality indicator.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Indicator Valid Domain</em>' reference.
     * @see #setIndicatorValidDomain(Domain)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicatorParameters_IndicatorValidDomain()
     * @model
     * @generated
     */
    Domain getIndicatorValidDomain();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.IndicatorParameters#getIndicatorValidDomain <em>Indicator Valid Domain</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Indicator Valid Domain</em>' reference.
     * @see #getIndicatorValidDomain()
     * @generated
     */
    void setIndicatorValidDomain(Domain value);

    /**
     * Returns the value of the '<em><b>Data Valid Domain</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The domain that defines the valid data. It can be patterns or thresholds, or more complex validation function. 
     * This is not a filter. Data that do not respect these domain are counted in the analysis but are used to compute a quality indicator.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Data Valid Domain</em>' reference.
     * @see #setDataValidDomain(Domain)
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getIndicatorParameters_DataValidDomain()
     * @model
     * @generated
     */
    Domain getDataValidDomain();

    /**
     * Sets the value of the '{@link org.talend.dataquality.indicators.IndicatorParameters#getDataValidDomain <em>Data Valid Domain</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Valid Domain</em>' reference.
     * @see #getDataValidDomain()
     * @generated
     */
    void setDataValidDomain(Domain value);

} // IndicatorParameters
