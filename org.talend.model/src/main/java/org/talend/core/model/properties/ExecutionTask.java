/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties;

import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Execution Task</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getId <em>Id</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getLabel <em>Label</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getDescription <em>Description</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getExecutionServer <em>Execution Server</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getProject <em>Project</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getContext <em>Context</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getJobVersion <em>Job Version</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#isActive <em>Active</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getIdQuartzJob <em>Id Quartz Job</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getLastScriptGenerationDate <em>Last Script Generation Date</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getIdRemoteJob <em>Id Remote Job</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getIdRemoteJobExecution <em>Id Remote Job Execution</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getChecksumArchive <em>Checksum Archive</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getJobScriptArchiveFilename <em>Job Script Archive Filename</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getStatus <em>Status</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#isProcessingState <em>Processing State</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getErrorStatus <em>Error Status</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getLastRunDate <em>Last Run Date</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getLastDeploymentDate <em>Last Deployment Date</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getLastEndedRunDate <em>Last Ended Run Date</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getTriggers <em>Triggers</em>}</li>
 * <li>{@link org.talend.core.model.properties.ExecutionTask#getJobName <em>Job Name</em>}</li>
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
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description
     * here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
    void setId(int value);

    /**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear, there really should be more of a description
     * here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
    void setLabel(String value);

    /**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there really should be more of a
     * description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
    void setDescription(String value);

    /**
	 * Returns the value of the '<em><b>Execution Server</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Execution Server</em>' reference isn't clear, there really should be more of a
     * description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Server</em>' reference.
	 * @see #getExecutionServer()
	 * @generated
	 */
    void setExecutionServer(ExecutionServer value);

    /**
	 * Returns the value of the '<em><b>Project</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Project</em>' reference isn't clear, there really should be more of a description
     * here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project</em>' reference.
	 * @see #getProject()
	 * @generated
	 */
    void setProject(Project value);

    /**
	 * Returns the value of the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Context</em>' attribute isn't clear, there really should be more of a description
     * here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' attribute.
	 * @see #getContext()
	 * @generated
	 */
    void setContext(String value);

    /**
	 * Returns the value of the '<em><b>Job Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Job Version</em>' attribute isn't clear, there really should be more of a
     * description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Job Version</em>' attribute.
	 * @see #getJobVersion()
	 * @generated
	 */
    void setJobVersion(String value);

    /**
	 * Returns the value of the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Active</em>' attribute isn't clear, there really should be more of a description
     * here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active</em>' attribute.
	 * @see #isActive()
	 * @generated
	 */
    void setActive(boolean value);

    /**
	 * Returns the value of the '<em><b>Id Quartz Job</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id Quartz Job</em>' attribute isn't clear, there really should be more of a
     * description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Quartz Job</em>' attribute.
	 * @see #getIdQuartzJob()
	 * @generated
	 */
    void setIdQuartzJob(int value);

    /**
	 * Returns the value of the '<em><b>Last Script Generation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Last Script Generation Date</em>' attribute isn't clear, there really should be
     * more of a description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Script Generation Date</em>' attribute.
	 * @see #getLastScriptGenerationDate()
	 * @generated
	 */
    void setLastScriptGenerationDate(Date value);

    /**
	 * Returns the value of the '<em><b>Id Remote Job</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id Remote Job</em>' attribute isn't clear, there really should be more of a
     * description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Remote Job</em>' attribute.
	 * @see #getIdRemoteJob()
	 * @generated
	 */
    void setIdRemoteJob(String value);

    /**
	 * Returns the value of the '<em><b>Id Remote Job Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id Remote Job Execution</em>' attribute isn't clear, there really should be more
     * of a description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Remote Job Execution</em>' attribute.
	 * @see #getIdRemoteJobExecution()
	 * @generated
	 */
    void setIdRemoteJobExecution(String value);

    /**
	 * Returns the value of the '<em><b>Checksum Archive</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Checksum Archive</em>' attribute isn't clear, there really should be more of a
     * description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Checksum Archive</em>' attribute.
	 * @see #getChecksumArchive()
	 * @generated
	 */
    void setChecksumArchive(String value);

    /**
	 * Returns the value of the '<em><b>Job Script Archive Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Job Script Archive Filename</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Job Script Archive Filename</em>' attribute.
	 * @see #setJobScriptArchiveFilename(String)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_JobScriptArchiveFilename()
	 * @model
	 * @generated
	 */
    String getJobScriptArchiveFilename();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getJobScriptArchiveFilename <em>Job Script Archive Filename</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Job Script Archive Filename</em>' attribute.
	 * @see #getJobScriptArchiveFilename()
	 * @generated
	 */
    void setJobScriptArchiveFilename(String value);

    /**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Status</em>' attribute isn't clear, there really should be more of a description
     * here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
    void setStatus(String value);

    /**
	 * Returns the value of the '<em><b>Processing State</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Processing State</em>' attribute isn't clear, there really should be more of a
     * description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Processing State</em>' attribute.
	 * @see #isProcessingState()
	 * @generated
	 */
    void setProcessingState(boolean value);

    /**
	 * Returns the value of the '<em><b>Error Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Error Status</em>' attribute isn't clear, there really should be more of a
     * description here...
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Status</em>' attribute.
	 * @see #getErrorStatus()
	 * @generated
	 */
    void setErrorStatus(String value);

    /**
	 * Returns the value of the '<em><b>Last Run Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Last Run Date</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Run Date</em>' attribute.
	 * @see #setLastRunDate(Date)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_LastRunDate()
	 * @model
	 * @generated
	 */
    Date getLastRunDate();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getLastRunDate <em>Last Run Date</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Run Date</em>' attribute.
	 * @see #getLastRunDate()
	 * @generated
	 */
    void setLastRunDate(Date value);

    /**
	 * Returns the value of the '<em><b>Last Deployment Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Last Deployment Date</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Deployment Date</em>' attribute.
	 * @see #setLastDeploymentDate(Date)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_LastDeploymentDate()
	 * @model
	 * @generated
	 */
    Date getLastDeploymentDate();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getLastDeploymentDate <em>Last Deployment Date</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Deployment Date</em>' attribute.
	 * @see #getLastDeploymentDate()
	 * @generated
	 */
    void setLastDeploymentDate(Date value);

    /**
	 * Returns the value of the '<em><b>Last Ended Run Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Last Ended Run Date</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Ended Run Date</em>' attribute.
	 * @see #setLastEndedRunDate(Date)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_LastEndedRunDate()
	 * @model
	 * @generated
	 */
    Date getLastEndedRunDate();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getLastEndedRunDate <em>Last Ended Run Date</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Ended Run Date</em>' attribute.
	 * @see #getLastEndedRunDate()
	 * @generated
	 */
    void setLastEndedRunDate(Date value);

    /**
	 * Returns the value of the '<em><b>Triggers</b></em>' containment reference list.
	 * The list contents are of type {@link org.talend.core.model.properties.TalendTrigger}.
	 * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.TalendTrigger#getExecutionTask <em>Execution Task</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Triggers</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' containment reference list.
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_Triggers()
	 * @see org.talend.core.model.properties.TalendTrigger#getExecutionTask
	 * @model type="org.talend.core.model.properties.TalendTrigger" opposite="executionTask" containment="true" ordered="false"
	 * @generated
	 */
    EList getTriggers();

    /**
	 * Returns the value of the '<em><b>Job Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Job Name</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Job Id</em>' attribute.
	 * @see #setJobId(String)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_JobId()
	 * @model
	 * @generated
	 */
    String getJobId();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getJobId <em>Job Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Job Id</em>' attribute.
	 * @see #getJobId()
	 * @generated
	 */
    void setJobId(String value);

    /**
	 * Returns the value of the '<em><b>Virtual Server</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Virtual Server</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Virtual Server</em>' reference.
	 * @see #setVirtualServer(ExecutionVirtualServer)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_VirtualServer()
	 * @model
	 * @generated
	 */
    ExecutionVirtualServer getVirtualServer();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getVirtualServer <em>Virtual Server</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Virtual Server</em>' reference.
	 * @see #getVirtualServer()
	 * @generated
	 */
    void setVirtualServer(ExecutionVirtualServer value);

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
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_ConcurrentExecution()
	 * @model
	 * @generated
	 */
    boolean isConcurrentExecution();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#isConcurrentExecution <em>Concurrent Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concurrent Execution</em>' attribute.
	 * @see #isConcurrentExecution()
	 * @generated
	 */
    void setConcurrentExecution(boolean value);

    /**
	 * Returns the value of the '<em><b>Max Concurrent Executions</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Max Concurrent Executions</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Concurrent Executions</em>' attribute.
	 * @see #setMaxConcurrentExecutions(int)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_MaxConcurrentExecutions()
	 * @model
	 * @generated
	 */
    int getMaxConcurrentExecutions();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getMaxConcurrentExecutions <em>Max Concurrent Executions</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Concurrent Executions</em>' attribute.
	 * @see #getMaxConcurrentExecutions()
	 * @generated
	 */
    void setMaxConcurrentExecutions(int value);

    /**
	 * Returns the value of the '<em><b>Generated Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Generated Project Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Generated Project Name</em>' attribute.
	 * @see #setGeneratedProjectName(String)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_GeneratedProjectName()
	 * @model
	 * @generated
	 */
    String getGeneratedProjectName();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getGeneratedProjectName <em>Generated Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated Project Name</em>' attribute.
	 * @see #getGeneratedProjectName()
	 * @generated
	 */
    void setGeneratedProjectName(String value);

    /**
	 * Returns the value of the '<em><b>Generated Job Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Generated Job Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Generated Job Name</em>' attribute.
	 * @see #setGeneratedJobName(String)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_GeneratedJobName()
	 * @model
	 * @generated
	 */
    String getGeneratedJobName();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#getGeneratedJobName <em>Generated Job Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated Job Name</em>' attribute.
	 * @see #getGeneratedJobName()
	 * @generated
	 */
    void setGeneratedJobName(String value);

    /**
	 * Returns the value of the '<em><b>Apply Context To Children</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Apply Context To Children</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Apply Context To Children</em>' attribute.
	 * @see #setApplyContextToChildren(boolean)
	 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTask_ApplyContextToChildren()
	 * @model
	 * @generated
	 */
    boolean isApplyContextToChildren();

    /**
	 * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTask#isApplyContextToChildren <em>Apply Context To Children</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Apply Context To Children</em>' attribute.
	 * @see #isApplyContextToChildren()
	 * @generated
	 */
    void setApplyContextToChildren(boolean value);

} // ExecutionTask
