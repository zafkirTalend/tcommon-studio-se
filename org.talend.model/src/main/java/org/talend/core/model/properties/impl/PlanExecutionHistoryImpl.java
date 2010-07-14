/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.core.model.properties.PlanExecutionHistory;
import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plan Execution History</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getParentTaskExecId <em>Parent Task Exec Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getBasicStatus <em>Basic Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getOriginalLabel <em>Original Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getCurrentLabel <em>Current Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getEndDate <em>End Date</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getIdQuartzJob <em>Id Quartz Job</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.PlanExecutionHistoryImpl#getErrorStackTrace <em>Error Stack Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlanExecutionHistoryImpl extends EObjectImpl implements PlanExecutionHistory {
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
     * The default value of the '{@link #getParentTaskExecId() <em>Parent Task Exec Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParentTaskExecId()
     * @generated
     * @ordered
     */
    protected static final int PARENT_TASK_EXEC_ID_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getParentTaskExecId() <em>Parent Task Exec Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParentTaskExecId()
     * @generated
     * @ordered
     */
    protected int parentTaskExecId = PARENT_TASK_EXEC_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getBasicStatus() <em>Basic Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBasicStatus()
     * @generated
     * @ordered
     */
    protected static final String BASIC_STATUS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBasicStatus() <em>Basic Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBasicStatus()
     * @generated
     * @ordered
     */
    protected String basicStatus = BASIC_STATUS_EDEFAULT;

    /**
     * The default value of the '{@link #getOriginalLabel() <em>Original Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOriginalLabel()
     * @generated
     * @ordered
     */
    protected static final String ORIGINAL_LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOriginalLabel() <em>Original Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOriginalLabel()
     * @generated
     * @ordered
     */
    protected String originalLabel = ORIGINAL_LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getCurrentLabel() <em>Current Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCurrentLabel()
     * @generated
     * @ordered
     */
    protected static final String CURRENT_LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCurrentLabel() <em>Current Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCurrentLabel()
     * @generated
     * @ordered
     */
    protected String currentLabel = CURRENT_LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartDate()
     * @generated
     * @ordered
     */
    protected static final Date START_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartDate()
     * @generated
     * @ordered
     */
    protected Date startDate = START_DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getEndDate() <em>End Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndDate()
     * @generated
     * @ordered
     */
    protected static final Date END_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEndDate() <em>End Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndDate()
     * @generated
     * @ordered
     */
    protected Date endDate = END_DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getParameters() <em>Parameters</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected static final String PARAMETERS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected String parameters = PARAMETERS_EDEFAULT;

    /**
     * The default value of the '{@link #getIdQuartzJob() <em>Id Quartz Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdQuartzJob()
     * @generated
     * @ordered
     */
    protected static final int ID_QUARTZ_JOB_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getIdQuartzJob() <em>Id Quartz Job</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdQuartzJob()
     * @generated
     * @ordered
     */
    protected int idQuartzJob = ID_QUARTZ_JOB_EDEFAULT;

    /**
     * The default value of the '{@link #getErrorStackTrace() <em>Error Stack Trace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getErrorStackTrace()
     * @generated
     * @ordered
     */
    protected static final String ERROR_STACK_TRACE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getErrorStackTrace() <em>Error Stack Trace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getErrorStackTrace()
     * @generated
     * @ordered
     */
    protected String errorStackTrace = ERROR_STACK_TRACE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PlanExecutionHistoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.PLAN_EXECUTION_HISTORY;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getParentTaskExecId() {
        return parentTaskExecId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParentTaskExecId(int newParentTaskExecId) {
        int oldParentTaskExecId = parentTaskExecId;
        parentTaskExecId = newParentTaskExecId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__PARENT_TASK_EXEC_ID, oldParentTaskExecId, parentTaskExecId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBasicStatus() {
        return basicStatus;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBasicStatus(String newBasicStatus) {
        String oldBasicStatus = basicStatus;
        basicStatus = newBasicStatus;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__BASIC_STATUS, oldBasicStatus, basicStatus));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOriginalLabel() {
        return originalLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOriginalLabel(String newOriginalLabel) {
        String oldOriginalLabel = originalLabel;
        originalLabel = newOriginalLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__ORIGINAL_LABEL, oldOriginalLabel, originalLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCurrentLabel() {
        return currentLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCurrentLabel(String newCurrentLabel) {
        String oldCurrentLabel = currentLabel;
        currentLabel = newCurrentLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__CURRENT_LABEL, oldCurrentLabel, currentLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStartDate(Date newStartDate) {
        Date oldStartDate = startDate;
        startDate = newStartDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__START_DATE, oldStartDate, startDate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndDate(Date newEndDate) {
        Date oldEndDate = endDate;
        endDate = newEndDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__END_DATE, oldEndDate, endDate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParameters(String newParameters) {
        String oldParameters = parameters;
        parameters = newParameters;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__PARAMETERS, oldParameters, parameters));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getIdQuartzJob() {
        return idQuartzJob;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIdQuartzJob(int newIdQuartzJob) {
        int oldIdQuartzJob = idQuartzJob;
        idQuartzJob = newIdQuartzJob;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__ID_QUARTZ_JOB, oldIdQuartzJob, idQuartzJob));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getErrorStackTrace() {
        return errorStackTrace;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setErrorStackTrace(String newErrorStackTrace) {
        String oldErrorStackTrace = errorStackTrace;
        errorStackTrace = newErrorStackTrace;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PLAN_EXECUTION_HISTORY__ERROR_STACK_TRACE, oldErrorStackTrace, errorStackTrace));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ID:
                return new Integer(getId());
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__PARENT_TASK_EXEC_ID:
                return new Integer(getParentTaskExecId());
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__BASIC_STATUS:
                return getBasicStatus();
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ORIGINAL_LABEL:
                return getOriginalLabel();
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__CURRENT_LABEL:
                return getCurrentLabel();
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__START_DATE:
                return getStartDate();
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__END_DATE:
                return getEndDate();
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__PARAMETERS:
                return getParameters();
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ID_QUARTZ_JOB:
                return new Integer(getIdQuartzJob());
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ERROR_STACK_TRACE:
                return getErrorStackTrace();
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
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ID:
                setId(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__PARENT_TASK_EXEC_ID:
                setParentTaskExecId(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__BASIC_STATUS:
                setBasicStatus((String)newValue);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ORIGINAL_LABEL:
                setOriginalLabel((String)newValue);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__CURRENT_LABEL:
                setCurrentLabel((String)newValue);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__START_DATE:
                setStartDate((Date)newValue);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__END_DATE:
                setEndDate((Date)newValue);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__PARAMETERS:
                setParameters((String)newValue);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ID_QUARTZ_JOB:
                setIdQuartzJob(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ERROR_STACK_TRACE:
                setErrorStackTrace((String)newValue);
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
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ID:
                setId(ID_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__PARENT_TASK_EXEC_ID:
                setParentTaskExecId(PARENT_TASK_EXEC_ID_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__BASIC_STATUS:
                setBasicStatus(BASIC_STATUS_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ORIGINAL_LABEL:
                setOriginalLabel(ORIGINAL_LABEL_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__CURRENT_LABEL:
                setCurrentLabel(CURRENT_LABEL_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__START_DATE:
                setStartDate(START_DATE_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__END_DATE:
                setEndDate(END_DATE_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__PARAMETERS:
                setParameters(PARAMETERS_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ID_QUARTZ_JOB:
                setIdQuartzJob(ID_QUARTZ_JOB_EDEFAULT);
                return;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ERROR_STACK_TRACE:
                setErrorStackTrace(ERROR_STACK_TRACE_EDEFAULT);
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
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ID:
                return id != ID_EDEFAULT;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__PARENT_TASK_EXEC_ID:
                return parentTaskExecId != PARENT_TASK_EXEC_ID_EDEFAULT;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__BASIC_STATUS:
                return BASIC_STATUS_EDEFAULT == null ? basicStatus != null : !BASIC_STATUS_EDEFAULT.equals(basicStatus);
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ORIGINAL_LABEL:
                return ORIGINAL_LABEL_EDEFAULT == null ? originalLabel != null : !ORIGINAL_LABEL_EDEFAULT.equals(originalLabel);
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__CURRENT_LABEL:
                return CURRENT_LABEL_EDEFAULT == null ? currentLabel != null : !CURRENT_LABEL_EDEFAULT.equals(currentLabel);
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__START_DATE:
                return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__END_DATE:
                return END_DATE_EDEFAULT == null ? endDate != null : !END_DATE_EDEFAULT.equals(endDate);
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__PARAMETERS:
                return PARAMETERS_EDEFAULT == null ? parameters != null : !PARAMETERS_EDEFAULT.equals(parameters);
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ID_QUARTZ_JOB:
                return idQuartzJob != ID_QUARTZ_JOB_EDEFAULT;
            case PropertiesPackage.PLAN_EXECUTION_HISTORY__ERROR_STACK_TRACE:
                return ERROR_STACK_TRACE_EDEFAULT == null ? errorStackTrace != null : !ERROR_STACK_TRACE_EDEFAULT.equals(errorStackTrace);
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
        result.append(", parentTaskExecId: ");
        result.append(parentTaskExecId);
        result.append(", basicStatus: ");
        result.append(basicStatus);
        result.append(", originalLabel: ");
        result.append(originalLabel);
        result.append(", currentLabel: ");
        result.append(currentLabel);
        result.append(", startDate: ");
        result.append(startDate);
        result.append(", endDate: ");
        result.append(endDate);
        result.append(", parameters: ");
        result.append(parameters);
        result.append(", idQuartzJob: ");
        result.append(idQuartzJob);
        result.append(", errorStackTrace: ");
        result.append(errorStackTrace);
        result.append(')');
        return result.toString();
    }

} //PlanExecutionHistoryImpl
