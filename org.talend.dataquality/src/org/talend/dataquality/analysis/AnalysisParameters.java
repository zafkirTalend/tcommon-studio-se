/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.analysis;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.talend.dataquality.domain.Domain;
import orgomg.cwmx.analysis.informationreporting.ReportGroup;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameters</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.analysis.AnalysisParameters#getDataFilter <em>Data Filter</em>}</li>
 *   <li>{@link org.talend.dataquality.analysis.AnalysisParameters#getIndicatorValidationDomains <em>Indicator Validation Domains</em>}</li>
 *   <li>{@link org.talend.dataquality.analysis.AnalysisParameters#getDataValidationDomains <em>Data Validation Domains</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.analysis.AnalysisPackage#getAnalysisParameters()
 * @model
 * @generated
 */
public interface AnalysisParameters extends ReportGroup {
    /**
     * Returns the value of the '<em><b>Data Filter</b></em>' reference list.
     * The list contents are of type {@link org.talend.dataquality.domain.Domain}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Data filtered from the analysis. Data outside this domain are not taken into account for the analysis.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Data Filter</em>' reference list.
     * @see org.talend.dataquality.analysis.AnalysisPackage#getAnalysisParameters_DataFilter()
     * @model
     * @generated
     */
    EList<Domain> getDataFilter();

    /**
     * Returns the value of the '<em><b>Indicator Validation Domains</b></em>' reference list.
     * The list contents are of type {@link org.talend.dataquality.domain.Domain}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Thresholds (or patterns) on indicators. Indicator that do not respect these thresholds are used to compute a quality indicator.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Indicator Validation Domains</em>' reference list.
     * @see org.talend.dataquality.analysis.AnalysisPackage#getAnalysisParameters_IndicatorValidationDomains()
     * @model
     * @generated
     */
    EList<Domain> getIndicatorValidationDomains();

    /**
     * Returns the value of the '<em><b>Data Validation Domains</b></em>' reference list.
     * The list contents are of type {@link org.talend.dataquality.domain.Domain}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The domain that defines the valid data. It can be patterns or thresholds, or more complex validation function. 
     * This is not a filter. Data that do not respect these domain are counted in the analysis but are used to compute a quality indicator.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Data Validation Domains</em>' reference list.
     * @see org.talend.dataquality.analysis.AnalysisPackage#getAnalysisParameters_DataValidationDomains()
     * @model
     * @generated
     */
    EList<Domain> getDataValidationDomains();

} // AnalysisParameters
