/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlan#getLabel <em>Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlan#getExecPlanParts <em>Exec Plan Parts</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlan#getExecPlanPrms <em>Exec Plan Prms</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlan#getIdQuartzJob <em>Id Quartz Job</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlan#getStatus <em>Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlan#getErrorStatus <em>Error Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlan#isConcurrentExecution <em>Concurrent Execution</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlan#isProcessingState <em>Processing State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan()
 * @model
 * @generated
 */
public interface ExecutionPlan extends ITriggerable {
    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlan#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Exec Plan Parts</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.properties.ExecutionPlanPart}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exec Plan Parts</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exec Plan Parts</em>' containment reference list.
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan_ExecPlanParts()
     * @model type="org.talend.core.model.properties.ExecutionPlanPart" containment="true" ordered="false"
     * @generated
     */
    EList getExecPlanParts();

    /**
     * Returns the value of the '<em><b>Exec Plan Prms</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.properties.ExecutionPlanPrm}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exec Plan Prms</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exec Plan Prms</em>' containment reference list.
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan_ExecPlanPrms()
     * @model type="org.talend.core.model.properties.ExecutionPlanPrm" containment="true" ordered="false"
     * @generated
     */
    EList getExecPlanPrms();

    /**
     * Returns the value of the '<em><b>Id Quartz Job</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id Quartz Job</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id Quartz Job</em>' attribute.
     * @see #setIdQuartzJob(int)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan_IdQuartzJob()
     * @model
     * @generated
     */
    int getIdQuartzJob();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlan#getIdQuartzJob <em>Id Quartz Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Quartz Job</em>' attribute.
     * @see #getIdQuartzJob()
     * @generated
     */
    void setIdQuartzJob(int value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan_Status()
     * @model
     * @generated
     */
    String getStatus();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlan#getStatus <em>Status</em>}' attribute.
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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan_ErrorStatus()
     * @model
     * @generated
     */
    String getErrorStatus();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlan#getErrorStatus <em>Error Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Error Status</em>' attribute.
     * @see #getErrorStatus()
     * @generated
     */
    void setErrorStatus(String value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan_ConcurrentExecution()
     * @model
     * @generated
     */
    boolean isConcurrentExecution();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlan#isConcurrentExecution <em>Concurrent Execution</em>}' attribute.
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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlan_ProcessingState()
     * @model
     * @generated
     */
    boolean isProcessingState();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlan#isProcessingState <em>Processing State</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Processing State</em>' attribute.
     * @see #isProcessingState()
     * @generated
     */
    void setProcessingState(boolean value);

} // ExecutionPlan
