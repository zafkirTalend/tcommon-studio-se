/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.core.model.properties.ExecutionServer;
import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Server</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerImpl#getHost <em>Host</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerImpl#getPort <em>Port</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerImpl#getFileTransfertPort <em>File Transfert Port</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerImpl#isActive <em>Active</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionServerImpl#getMonitoringPort <em>Monitoring Port</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionServerImpl extends EObjectImpl implements ExecutionServer {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final int ID_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected int id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The default value of the '{@link #getHost() <em>Host</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHost()
     * @generated
     * @ordered
     */
    protected static final String HOST_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHost() <em>Host</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHost()
     * @generated
     * @ordered
     */
    protected String host = HOST_EDEFAULT;

    /**
     * The default value of the '{@link #getPort() <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPort()
     * @generated
     * @ordered
     */
    protected static final int PORT_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getPort() <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPort()
     * @generated
     * @ordered
     */
    protected int port = PORT_EDEFAULT;

    /**
     * The default value of the '{@link #getFileTransfertPort() <em>File Transfert Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFileTransfertPort()
     * @generated
     * @ordered
     */
    protected static final int FILE_TRANSFERT_PORT_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getFileTransfertPort() <em>File Transfert Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFileTransfertPort()
     * @generated
     * @ordered
     */
    protected int fileTransfertPort = FILE_TRANSFERT_PORT_EDEFAULT;

    /**
     * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isActive()
     * @generated
     * @ordered
     */
    protected static final boolean ACTIVE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isActive() <em>Active</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isActive()
     * @generated
     * @ordered
     */
    protected boolean active = ACTIVE_EDEFAULT;

    /**
     * The default value of the '{@link #getMonitoringPort() <em>Monitoring Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMonitoringPort()
     * @generated
     * @ordered
     */
    protected static final int MONITORING_PORT_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getMonitoringPort() <em>Monitoring Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMonitoringPort()
     * @generated
     * @ordered
     */
    protected int monitoringPort = MONITORING_PORT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExecutionServerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.EXECUTION_SERVER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(int newId) {
        int oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER__LABEL, oldLabel, label));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getHost() {
        return host;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHost(String newHost) {
        String oldHost = host;
        host = newHost;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER__HOST, oldHost, host));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPort() {
        return port;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPort(int newPort) {
        int oldPort = port;
        port = newPort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER__PORT, oldPort, port));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getFileTransfertPort() {
        return fileTransfertPort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFileTransfertPort(int newFileTransfertPort) {
        int oldFileTransfertPort = fileTransfertPort;
        fileTransfertPort = newFileTransfertPort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER__FILE_TRANSFERT_PORT, oldFileTransfertPort, fileTransfertPort));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isActive() {
        return active;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setActive(boolean newActive) {
        boolean oldActive = active;
        active = newActive;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER__ACTIVE, oldActive, active));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getMonitoringPort() {
        return monitoringPort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMonitoringPort(int newMonitoringPort) {
        int oldMonitoringPort = monitoringPort;
        monitoringPort = newMonitoringPort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_SERVER__MONITORING_PORT, oldMonitoringPort, monitoringPort));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_SERVER__ID:
                return new Integer(getId());
            case PropertiesPackage.EXECUTION_SERVER__LABEL:
                return getLabel();
            case PropertiesPackage.EXECUTION_SERVER__DESCRIPTION:
                return getDescription();
            case PropertiesPackage.EXECUTION_SERVER__HOST:
                return getHost();
            case PropertiesPackage.EXECUTION_SERVER__PORT:
                return new Integer(getPort());
            case PropertiesPackage.EXECUTION_SERVER__FILE_TRANSFERT_PORT:
                return new Integer(getFileTransfertPort());
            case PropertiesPackage.EXECUTION_SERVER__ACTIVE:
                return isActive() ? Boolean.TRUE : Boolean.FALSE;
            case PropertiesPackage.EXECUTION_SERVER__MONITORING_PORT:
                return new Integer(getMonitoringPort());
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_SERVER__ID:
                setId(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.EXECUTION_SERVER__LABEL:
                setLabel((String)newValue);
                return;
            case PropertiesPackage.EXECUTION_SERVER__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case PropertiesPackage.EXECUTION_SERVER__HOST:
                setHost((String)newValue);
                return;
            case PropertiesPackage.EXECUTION_SERVER__PORT:
                setPort(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.EXECUTION_SERVER__FILE_TRANSFERT_PORT:
                setFileTransfertPort(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.EXECUTION_SERVER__ACTIVE:
                setActive(((Boolean)newValue).booleanValue());
                return;
            case PropertiesPackage.EXECUTION_SERVER__MONITORING_PORT:
                setMonitoringPort(((Integer)newValue).intValue());
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_SERVER__ID:
                setId(ID_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_SERVER__LABEL:
                setLabel(LABEL_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_SERVER__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_SERVER__HOST:
                setHost(HOST_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_SERVER__PORT:
                setPort(PORT_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_SERVER__FILE_TRANSFERT_PORT:
                setFileTransfertPort(FILE_TRANSFERT_PORT_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_SERVER__ACTIVE:
                setActive(ACTIVE_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_SERVER__MONITORING_PORT:
                setMonitoringPort(MONITORING_PORT_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_SERVER__ID:
                return id != ID_EDEFAULT;
            case PropertiesPackage.EXECUTION_SERVER__LABEL:
                return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
            case PropertiesPackage.EXECUTION_SERVER__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case PropertiesPackage.EXECUTION_SERVER__HOST:
                return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
            case PropertiesPackage.EXECUTION_SERVER__PORT:
                return port != PORT_EDEFAULT;
            case PropertiesPackage.EXECUTION_SERVER__FILE_TRANSFERT_PORT:
                return fileTransfertPort != FILE_TRANSFERT_PORT_EDEFAULT;
            case PropertiesPackage.EXECUTION_SERVER__ACTIVE:
                return active != ACTIVE_EDEFAULT;
            case PropertiesPackage.EXECUTION_SERVER__MONITORING_PORT:
                return monitoringPort != MONITORING_PORT_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (id: ");
        result.append(id);
        result.append(", label: ");
        result.append(label);
        result.append(", description: ");
        result.append(description);
        result.append(", host: ");
        result.append(host);
        result.append(", port: ");
        result.append(port);
        result.append(", fileTransfertPort: ");
        result.append(fileTransfertPort);
        result.append(", active: ");
        result.append(active);
        result.append(", monitoringPort: ");
        result.append(monitoringPort);
        result.append(')');
        return result.toString();
    }

} //ExecutionServerImpl
