/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plan Execution History</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getParentTaskExecId <em>Parent Task Exec Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getBasicStatus <em>Basic Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getOriginalLabel <em>Original Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getCurrentLabel <em>Current Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getEndDate <em>End Date</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getIdQuartzJob <em>Id Quartz Job</em>}</li>
 *   <li>{@link org.talend.core.model.properties.PlanExecutionHistory#getErrorStackTrace <em>Error Stack Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory()
 * @model
 * @generated
 */
public interface PlanExecutionHistory extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(int)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_Id()
     * @model id="true" required="true"
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(int value);

    /**
     * Returns the value of the '<em><b>Parent Task Exec Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Task Exec Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Task Exec Id</em>' attribute.
     * @see #setParentTaskExecId(int)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_ParentTaskExecId()
     * @model
     * @generated
     */
    int getParentTaskExecId();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getParentTaskExecId <em>Parent Task Exec Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent Task Exec Id</em>' attribute.
     * @see #getParentTaskExecId()
     * @generated
     */
    void setParentTaskExecId(int value);

    /**
     * Returns the value of the '<em><b>Basic Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Basic Status</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Basic Status</em>' attribute.
     * @see #setBasicStatus(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_BasicStatus()
     * @model
     * @generated
     */
    String getBasicStatus();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getBasicStatus <em>Basic Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Basic Status</em>' attribute.
     * @see #getBasicStatus()
     * @generated
     */
    void setBasicStatus(String value);

    /**
     * Returns the value of the '<em><b>Original Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Original Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Original Label</em>' attribute.
     * @see #setOriginalLabel(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_OriginalLabel()
     * @model
     * @generated
     */
    String getOriginalLabel();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getOriginalLabel <em>Original Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Original Label</em>' attribute.
     * @see #getOriginalLabel()
     * @generated
     */
    void setOriginalLabel(String value);

    /**
     * Returns the value of the '<em><b>Current Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Current Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Current Label</em>' attribute.
     * @see #setCurrentLabel(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_CurrentLabel()
     * @model
     * @generated
     */
    String getCurrentLabel();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getCurrentLabel <em>Current Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Current Label</em>' attribute.
     * @see #getCurrentLabel()
     * @generated
     */
    void setCurrentLabel(String value);

    /**
     * Returns the value of the '<em><b>Start Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Date</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Start Date</em>' attribute.
     * @see #setStartDate(Date)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_StartDate()
     * @model
     * @generated
     */
    Date getStartDate();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getStartDate <em>Start Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Date</em>' attribute.
     * @see #getStartDate()
     * @generated
     */
    void setStartDate(Date value);

    /**
     * Returns the value of the '<em><b>End Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Date</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>End Date</em>' attribute.
     * @see #setEndDate(Date)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_EndDate()
     * @model
     * @generated
     */
    Date getEndDate();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getEndDate <em>End Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Date</em>' attribute.
     * @see #getEndDate()
     * @generated
     */
    void setEndDate(Date value);

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameters</em>' attribute.
     * @see #setParameters(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_Parameters()
     * @model
     * @generated
     */
    String getParameters();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getParameters <em>Parameters</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameters</em>' attribute.
     * @see #getParameters()
     * @generated
     */
    void setParameters(String value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_IdQuartzJob()
     * @model
     * @generated
     */
    int getIdQuartzJob();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getIdQuartzJob <em>Id Quartz Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Quartz Job</em>' attribute.
     * @see #getIdQuartzJob()
     * @generated
     */
    void setIdQuartzJob(int value);

    /**
     * Returns the value of the '<em><b>Error Stack Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Error Stack Trace</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Error Stack Trace</em>' attribute.
     * @see #setErrorStackTrace(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getPlanExecutionHistory_ErrorStackTrace()
     * @model
     * @generated
     */
    String getErrorStackTrace();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.PlanExecutionHistory#getErrorStackTrace <em>Error Stack Trace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Error Stack Trace</em>' attribute.
     * @see #getErrorStackTrace()
     * @generated
     */
    void setErrorStackTrace(String value);

} // PlanExecutionHistory
