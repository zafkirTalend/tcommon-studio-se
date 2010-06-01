/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Plan Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanTrigger#getIdQuartzJob <em>Id Quartz Job</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanTrigger#getStatus <em>Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanTrigger#getErrorStatus <em>Error Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanTrigger#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanTrigger#isConcurrentExecution <em>Concurrent Execution</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanTrigger#isProcessingState <em>Processing State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanTrigger()
 * @model
 * @generated
 */
public interface ExecutionPlanTrigger extends TalendTrigger {
    /**
     * Returns the value of the '<em><b>Id Quartz Job</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id Quartz Job</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id Quartz Job</em>' attribute.
     * @see #setIdQuartzJob(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanTrigger_IdQuartzJob()
     * @model
     * @generated
     */
    String getIdQuartzJob();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanTrigger#getIdQuartzJob <em>Id Quartz Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Quartz Job</em>' attribute.
     * @see #getIdQuartzJob()
     * @generated
     */
    void setIdQuartzJob(String value);

    /**
     * Returns the value of the '<em><b>Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Status</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Status</em>' attribute.
     * @see #setStatus(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanTrigger_Status()
     * @model
     * @generated
     */
    String getStatus();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanTrigger#getStatus <em>Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Status</em>' attribute.
     * @see #getStatus()
     * @generated
     */
    void setStatus(String value);

    /**
     * Returns the value of the '<em><b>Error Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Error Status</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Error Status</em>' attribute.
     * @see #setErrorStatus(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanTrigger_ErrorStatus()
     * @model
     * @generated
     */
    String getErrorStatus();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanTrigger#getErrorStatus <em>Error Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Error Status</em>' attribute.
     * @see #getErrorStatus()
     * @generated
     */
    void setErrorStatus(String value);

    /**
     * Returns the value of the '<em><b>Triggers</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.properties.TalendTrigger}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Triggers</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Triggers</em>' containment reference list.
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanTrigger_Triggers()
     * @model type="org.talend.core.model.properties.TalendTrigger" containment="true" ordered="false"
     * @generated
     */
    EList getTriggers();

    /**
     * Returns the value of the '<em><b>Concurrent Execution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Concurrent Execution</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Concurrent Execution</em>' attribute.
     * @see #setConcurrentExecution(boolean)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanTrigger_ConcurrentExecution()
     * @model
     * @generated
     */
    boolean isConcurrentExecution();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanTrigger#isConcurrentExecution <em>Concurrent Execution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Concurrent Execution</em>' attribute.
     * @see #isConcurrentExecution()
     * @generated
     */
    void setConcurrentExecution(boolean value);

    /**
     * Returns the value of the '<em><b>Processing State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Processing State</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Processing State</em>' attribute.
     * @see #setProcessingState(boolean)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanTrigger_ProcessingState()
     * @model
     * @generated
     */
    boolean isProcessingState();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanTrigger#isProcessingState <em>Processing State</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Processing State</em>' attribute.
     * @see #isProcessingState()
     * @generated
     */
    void setProcessingState(boolean value);

} // ExecutionPlanTrigger
