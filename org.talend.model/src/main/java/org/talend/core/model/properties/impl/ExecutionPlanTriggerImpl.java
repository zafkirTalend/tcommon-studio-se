/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.talend.core.model.properties.ExecutionPlanTrigger;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.TalendTrigger;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Plan Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanTriggerImpl#getIdQuartzJob <em>Id Quartz Job</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanTriggerImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanTriggerImpl#getErrorStatus <em>Error Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanTriggerImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanTriggerImpl#isConcurrentExecution <em>Concurrent Execution</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanTriggerImpl#isProcessingState <em>Processing State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionPlanTriggerImpl extends TalendTriggerImpl implements ExecutionPlanTrigger {
    /**
     * The default value of the '{@link #getIdQuartzJob() <em>Id Quartz Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdQuartzJob()
     * @generated
     * @ordered
     */
    protected static final String ID_QUARTZ_JOB_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdQuartzJob() <em>Id Quartz Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdQuartzJob()
     * @generated
     * @ordered
     */
    protected String idQuartzJob = ID_QUARTZ_JOB_EDEFAULT;

    /**
     * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStatus()
     * @generated
     * @ordered
     */
    protected static final String STATUS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStatus()
     * @generated
     * @ordered
     */
    protected String status = STATUS_EDEFAULT;

    /**
     * The default value of the '{@link #getErrorStatus() <em>Error Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getErrorStatus()
     * @generated
     * @ordered
     */
    protected static final String ERROR_STATUS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getErrorStatus() <em>Error Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getErrorStatus()
     * @generated
     * @ordered
     */
    protected String errorStatus = ERROR_STATUS_EDEFAULT;

    /**
     * The cached value of the '{@link #getTriggers() <em>Triggers</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTriggers()
     * @generated
     * @ordered
     */
    protected EList triggers;

    /**
     * The default value of the '{@link #isConcurrentExecution() <em>Concurrent Execution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isConcurrentExecution()
     * @generated
     * @ordered
     */
    protected static final boolean CONCURRENT_EXECUTION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isConcurrentExecution() <em>Concurrent Execution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isConcurrentExecution()
     * @generated
     * @ordered
     */
    protected boolean concurrentExecution = CONCURRENT_EXECUTION_EDEFAULT;

    /**
     * The default value of the '{@link #isProcessingState() <em>Processing State</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isProcessingState()
     * @generated
     * @ordered
     */
    protected static final boolean PROCESSING_STATE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isProcessingState() <em>Processing State</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isProcessingState()
     * @generated
     * @ordered
     */
    protected boolean processingState = PROCESSING_STATE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExecutionPlanTriggerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.EXECUTION_PLAN_TRIGGER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getIdQuartzJob() {
        return idQuartzJob;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIdQuartzJob(String newIdQuartzJob) {
        String oldIdQuartzJob = idQuartzJob;
        idQuartzJob = newIdQuartzJob;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_TRIGGER__ID_QUARTZ_JOB, oldIdQuartzJob, idQuartzJob));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStatus(String newStatus) {
        String oldStatus = status;
        status = newStatus;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_TRIGGER__STATUS, oldStatus, status));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getErrorStatus() {
        return errorStatus;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setErrorStatus(String newErrorStatus) {
        String oldErrorStatus = errorStatus;
        errorStatus = newErrorStatus;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_TRIGGER__ERROR_STATUS, oldErrorStatus, errorStatus));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTriggers() {
        if (triggers == null) {
            triggers = new EObjectContainmentEList(TalendTrigger.class, this, PropertiesPackage.EXECUTION_PLAN_TRIGGER__TRIGGERS);
        }
        return triggers;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isConcurrentExecution() {
        return concurrentExecution;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConcurrentExecution(boolean newConcurrentExecution) {
        boolean oldConcurrentExecution = concurrentExecution;
        concurrentExecution = newConcurrentExecution;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_TRIGGER__CONCURRENT_EXECUTION, oldConcurrentExecution, concurrentExecution));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isProcessingState() {
        return processingState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProcessingState(boolean newProcessingState) {
        boolean oldProcessingState = processingState;
        processingState = newProcessingState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_TRIGGER__PROCESSING_STATE, oldProcessingState, processingState));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__TRIGGERS:
                return ((InternalEList)getTriggers()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__ID_QUARTZ_JOB:
                return getIdQuartzJob();
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__STATUS:
                return getStatus();
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__ERROR_STATUS:
                return getErrorStatus();
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__TRIGGERS:
                return getTriggers();
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__CONCURRENT_EXECUTION:
                return isConcurrentExecution() ? Boolean.TRUE : Boolean.FALSE;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__PROCESSING_STATE:
                return isProcessingState() ? Boolean.TRUE : Boolean.FALSE;
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
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__ID_QUARTZ_JOB:
                setIdQuartzJob((String)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__STATUS:
                setStatus((String)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__ERROR_STATUS:
                setErrorStatus((String)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__TRIGGERS:
                getTriggers().clear();
                getTriggers().addAll((Collection)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__CONCURRENT_EXECUTION:
                setConcurrentExecution(((Boolean)newValue).booleanValue());
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__PROCESSING_STATE:
                setProcessingState(((Boolean)newValue).booleanValue());
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
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__ID_QUARTZ_JOB:
                setIdQuartzJob(ID_QUARTZ_JOB_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__STATUS:
                setStatus(STATUS_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__ERROR_STATUS:
                setErrorStatus(ERROR_STATUS_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__TRIGGERS:
                getTriggers().clear();
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__CONCURRENT_EXECUTION:
                setConcurrentExecution(CONCURRENT_EXECUTION_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__PROCESSING_STATE:
                setProcessingState(PROCESSING_STATE_EDEFAULT);
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
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__ID_QUARTZ_JOB:
                return ID_QUARTZ_JOB_EDEFAULT == null ? idQuartzJob != null : !ID_QUARTZ_JOB_EDEFAULT.equals(idQuartzJob);
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__STATUS:
                return STATUS_EDEFAULT == null ? status != null : !STATUS_EDEFAULT.equals(status);
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__ERROR_STATUS:
                return ERROR_STATUS_EDEFAULT == null ? errorStatus != null : !ERROR_STATUS_EDEFAULT.equals(errorStatus);
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__TRIGGERS:
                return triggers != null && !triggers.isEmpty();
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__CONCURRENT_EXECUTION:
                return concurrentExecution != CONCURRENT_EXECUTION_EDEFAULT;
            case PropertiesPackage.EXECUTION_PLAN_TRIGGER__PROCESSING_STATE:
                return processingState != PROCESSING_STATE_EDEFAULT;
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
        result.append(" (idQuartzJob: ");
        result.append(idQuartzJob);
        result.append(", status: ");
        result.append(status);
        result.append(", errorStatus: ");
        result.append(errorStatus);
        result.append(", concurrentExecution: ");
        result.append(concurrentExecution);
        result.append(", processingState: ");
        result.append(processingState);
        result.append(')');
        return result.toString();
    }

} //ExecutionPlanTriggerImpl
