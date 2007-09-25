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
 * A representation of the model object '<em><b>Execution Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getLabel <em>Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getExecutionServer <em>Execution Server</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getProject <em>Project</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getProcessItem <em>Process Item</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getContext <em>Context</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getJobVersion <em>Job Version</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#isActive <em>Active</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getIdQuartzJob <em>Id Quartz Job</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getLastScriptGenerationDate <em>Last Script Generation Date</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getIdRemoteJob <em>Id Remote Job</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getIdRemoteJobExecution <em>Id Remote Job Execution</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getChecksumArchive <em>Checksum Archive</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getJobScriptArchive <em>Job Script Archive</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getStatus <em>Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#isProcessingState <em>Processing State</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTask#getErrorStatus <em>Error Status</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask()
 * @model
 * @generated
 */
public interface ExecutionTask extends EObject {
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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_Id()
     * @model id="true" required="true"
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(int value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Execution Server</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Execution Server</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Execution Server</em>' reference.
     * @see #setExecutionServer(ExecutionServer)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_ExecutionServer()
     * @model
     * @generated
     */
    ExecutionServer getExecutionServer();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getExecutionServer <em>Execution Server</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Execution Server</em>' reference.
     * @see #getExecutionServer()
     * @generated
     */
    void setExecutionServer(ExecutionServer value);

    /**
     * Returns the value of the '<em><b>Project</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Project</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Project</em>' reference.
     * @see #setProject(Project)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_Project()
     * @model
     * @generated
     */
    Project getProject();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getProject <em>Project</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Project</em>' reference.
     * @see #getProject()
     * @generated
     */
    void setProject(Project value);

    /**
     * Returns the value of the '<em><b>Process Item</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Process Item</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Process Item</em>' reference.
     * @see #setProcessItem(ProcessItem)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_ProcessItem()
     * @model
     * @generated
     */
    ProcessItem getProcessItem();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getProcessItem <em>Process Item</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Process Item</em>' reference.
     * @see #getProcessItem()
     * @generated
     */
    void setProcessItem(ProcessItem value);

    /**
     * Returns the value of the '<em><b>Context</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Context</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Context</em>' attribute.
     * @see #setContext(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_Context()
     * @model
     * @generated
     */
    String getContext();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getContext <em>Context</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Context</em>' attribute.
     * @see #getContext()
     * @generated
     */
    void setContext(String value);

    /**
     * Returns the value of the '<em><b>Job Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Job Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Job Version</em>' attribute.
     * @see #setJobVersion(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_JobVersion()
     * @model
     * @generated
     */
    String getJobVersion();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getJobVersion <em>Job Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Job Version</em>' attribute.
     * @see #getJobVersion()
     * @generated
     */
    void setJobVersion(String value);

    /**
     * Returns the value of the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Active</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Active</em>' attribute.
     * @see #setActive(boolean)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_Active()
     * @model
     * @generated
     */
    boolean isActive();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#isActive <em>Active</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Active</em>' attribute.
     * @see #isActive()
     * @generated
     */
    void setActive(boolean value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_IdQuartzJob()
     * @model
     * @generated
     */
    int getIdQuartzJob();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getIdQuartzJob <em>Id Quartz Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Quartz Job</em>' attribute.
     * @see #getIdQuartzJob()
     * @generated
     */
    void setIdQuartzJob(int value);

    /**
     * Returns the value of the '<em><b>Last Script Generation Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Last Script Generation Date</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Last Script Generation Date</em>' attribute.
     * @see #setLastScriptGenerationDate(Date)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_LastScriptGenerationDate()
     * @model
     * @generated
     */
    Date getLastScriptGenerationDate();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getLastScriptGenerationDate <em>Last Script Generation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Last Script Generation Date</em>' attribute.
     * @see #getLastScriptGenerationDate()
     * @generated
     */
    void setLastScriptGenerationDate(Date value);

    /**
     * Returns the value of the '<em><b>Id Remote Job</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id Remote Job</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id Remote Job</em>' attribute.
     * @see #setIdRemoteJob(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_IdRemoteJob()
     * @model
     * @generated
     */
    String getIdRemoteJob();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getIdRemoteJob <em>Id Remote Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Remote Job</em>' attribute.
     * @see #getIdRemoteJob()
     * @generated
     */
    void setIdRemoteJob(String value);

    /**
     * Returns the value of the '<em><b>Id Remote Job Execution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id Remote Job Execution</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id Remote Job Execution</em>' attribute.
     * @see #setIdRemoteJobExecution(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_IdRemoteJobExecution()
     * @model
     * @generated
     */
    String getIdRemoteJobExecution();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getIdRemoteJobExecution <em>Id Remote Job Execution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Remote Job Execution</em>' attribute.
     * @see #getIdRemoteJobExecution()
     * @generated
     */
    void setIdRemoteJobExecution(String value);

    /**
     * Returns the value of the '<em><b>Checksum Archive</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Checksum Archive</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Checksum Archive</em>' attribute.
     * @see #setChecksumArchive(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_ChecksumArchive()
     * @model
     * @generated
     */
    String getChecksumArchive();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getChecksumArchive <em>Checksum Archive</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Checksum Archive</em>' attribute.
     * @see #getChecksumArchive()
     * @generated
     */
    void setChecksumArchive(String value);

    /**
     * Returns the value of the '<em><b>Job Script Archive</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Job Script Archive</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Job Script Archive</em>' attribute.
     * @see #setJobScriptArchive(byte[])
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_JobScriptArchive()
     * @model
     * @generated
     */
    byte[] getJobScriptArchive();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getJobScriptArchive <em>Job Script Archive</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Job Script Archive</em>' attribute.
     * @see #getJobScriptArchive()
     * @generated
     */
    void setJobScriptArchive(byte[] value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_Status()
     * @model
     * @generated
     */
    String getStatus();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getStatus <em>Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Status</em>' attribute.
     * @see #getStatus()
     * @generated
     */
    void setStatus(String value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_ProcessingState()
     * @model
     * @generated
     */
    boolean isProcessingState();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#isProcessingState <em>Processing State</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Processing State</em>' attribute.
     * @see #isProcessingState()
     * @generated
     */
    void setProcessingState(boolean value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_ErrorStatus()
     * @model
     * @generated
     */
    String getErrorStatus();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getErrorStatus <em>Error Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Error Status</em>' attribute.
     * @see #getErrorStatus()
     * @generated
     */
    void setErrorStatus(String value);

} // ExecutionTask
