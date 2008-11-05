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
 * A representation of the model object '<em><b>Execution Server</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.ExecutionServer#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionServer#getLabel <em>Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionServer#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionServer#getHost <em>Host</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionServer#getPort <em>Port</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionServer#getFileTransfertPort <em>File Transfert Port</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionServer#isActive <em>Active</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionServer#getMonitoringPort <em>Monitoring Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer()
 * @model
 * @generated
 */
public interface ExecutionServer extends EObject {
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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer_Id()
     * @model id="true" required="true"
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServer#getId <em>Id</em>}' attribute.
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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServer#getLabel <em>Label</em>}' attribute.
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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServer#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Host</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Host</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Host</em>' attribute.
     * @see #setHost(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer_Host()
     * @model
     * @generated
     */
    String getHost();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServer#getHost <em>Host</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Host</em>' attribute.
     * @see #getHost()
     * @generated
     */
    void setHost(String value);

    /**
     * Returns the value of the '<em><b>Port</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Port</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Port</em>' attribute.
     * @see #setPort(int)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer_Port()
     * @model default="-1"
     * @generated
     */
    int getPort();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServer#getPort <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Port</em>' attribute.
     * @see #getPort()
     * @generated
     */
    void setPort(int value);

    /**
     * Returns the value of the '<em><b>File Transfert Port</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>File Transfert Port</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>File Transfert Port</em>' attribute.
     * @see #setFileTransfertPort(int)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer_FileTransfertPort()
     * @model default="-1"
     * @generated
     */
    int getFileTransfertPort();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServer#getFileTransfertPort <em>File Transfert Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>File Transfert Port</em>' attribute.
     * @see #getFileTransfertPort()
     * @generated
     */
    void setFileTransfertPort(int value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer_Active()
     * @model
     * @generated
     */
    boolean isActive();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServer#isActive <em>Active</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Active</em>' attribute.
     * @see #isActive()
     * @generated
     */
    void setActive(boolean value);

    /**
     * Returns the value of the '<em><b>Monitoring Port</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Monitoring Port</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Monitoring Port</em>' attribute.
     * @see #setMonitoringPort(int)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServer_MonitoringPort()
     * @model default="-1"
     * @generated
     */
    int getMonitoringPort();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServer#getMonitoringPort <em>Monitoring Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Monitoring Port</em>' attribute.
     * @see #getMonitoringPort()
     * @generated
     */
    void setMonitoringPort(int value);

} // ExecutionServer
