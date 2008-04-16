/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.analysis;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Informations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.analysis.ExecutionInformations#getExecutionDate <em>Execution Date</em>}</li>
 *   <li>{@link org.talend.dataquality.analysis.ExecutionInformations#getExecutionDuration <em>Execution Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.analysis.AnalysisPackage#getExecutionInformations()
 * @model
 * @generated
 */
public interface ExecutionInformations extends EObject {
    /**
     * Returns the value of the '<em><b>Execution Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Execution Date</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Execution Date</em>' attribute.
     * @see #setExecutionDate(Date)
     * @see org.talend.dataquality.analysis.AnalysisPackage#getExecutionInformations_ExecutionDate()
     * @model
     * @generated
     */
    Date getExecutionDate();

    /**
     * Sets the value of the '{@link org.talend.dataquality.analysis.ExecutionInformations#getExecutionDate <em>Execution Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Execution Date</em>' attribute.
     * @see #getExecutionDate()
     * @generated
     */
    void setExecutionDate(Date value);

    /**
     * Returns the value of the '<em><b>Execution Duration</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Execution Duration</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Execution Duration</em>' attribute.
     * @see #setExecutionDuration(int)
     * @see org.talend.dataquality.analysis.AnalysisPackage#getExecutionInformations_ExecutionDuration()
     * @model
     * @generated
     */
    int getExecutionDuration();

    /**
     * Sets the value of the '{@link org.talend.dataquality.analysis.ExecutionInformations#getExecutionDuration <em>Execution Duration</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Execution Duration</em>' attribute.
     * @see #getExecutionDuration()
     * @generated
     */
    void setExecutionDuration(int value);

} // ExecutionInformations
