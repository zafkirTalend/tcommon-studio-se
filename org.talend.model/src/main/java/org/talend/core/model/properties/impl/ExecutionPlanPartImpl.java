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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.talend.core.model.properties.ExecutionPlan;
import org.talend.core.model.properties.ExecutionPlanPart;
import org.talend.core.model.properties.ExecutionPlanPartCmdPrm;
import org.talend.core.model.properties.ExecutionPlanPartJobPrm;
import org.talend.core.model.properties.ExecutionTask;
import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Plan Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanPartImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanPartImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanPartImpl#getExecutionPlan <em>Execution Plan</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanPartImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanPartImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanPartImpl#getJvmPrms <em>Jvm Prms</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanPartImpl#getContextPrms <em>Context Prms</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionPlanPartImpl extends EObjectImpl implements ExecutionPlanPart {
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
     * The cached value of the '{@link #getTask() <em>Task</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTask()
     * @generated
     * @ordered
     */
    protected ExecutionTask task;

    /**
     * The cached value of the '{@link #getExecutionPlan() <em>Execution Plan</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExecutionPlan()
     * @generated
     * @ordered
     */
    protected ExecutionPlan executionPlan;

    /**
     * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParent()
     * @generated
     * @ordered
     */
    protected ExecutionPlanPart parent;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getJvmPrms() <em>Jvm Prms</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJvmPrms()
     * @generated
     * @ordered
     */
    protected EList jvmPrms;

    /**
     * The cached value of the '{@link #getContextPrms() <em>Context Prms</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContextPrms()
     * @generated
     * @ordered
     */
    protected EList contextPrms;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExecutionPlanPartImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.EXECUTION_PLAN_PART;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_PART__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionTask getTask() {
        if (task != null && task.eIsProxy()) {
            InternalEObject oldTask = (InternalEObject)task;
            task = (ExecutionTask)eResolveProxy(oldTask);
            if (task != oldTask) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.EXECUTION_PLAN_PART__TASK, oldTask, task));
            }
        }
        return task;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionTask basicGetTask() {
        return task;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTask(ExecutionTask newTask) {
        ExecutionTask oldTask = task;
        task = newTask;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_PART__TASK, oldTask, task));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionPlan getExecutionPlan() {
        if (executionPlan != null && executionPlan.eIsProxy()) {
            InternalEObject oldExecutionPlan = (InternalEObject)executionPlan;
            executionPlan = (ExecutionPlan)eResolveProxy(oldExecutionPlan);
            if (executionPlan != oldExecutionPlan) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.EXECUTION_PLAN_PART__EXECUTION_PLAN, oldExecutionPlan, executionPlan));
            }
        }
        return executionPlan;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionPlan basicGetExecutionPlan() {
        return executionPlan;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExecutionPlan(ExecutionPlan newExecutionPlan) {
        ExecutionPlan oldExecutionPlan = executionPlan;
        executionPlan = newExecutionPlan;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_PART__EXECUTION_PLAN, oldExecutionPlan, executionPlan));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionPlanPart getParent() {
        if (parent != null && parent.eIsProxy()) {
            InternalEObject oldParent = (InternalEObject)parent;
            parent = (ExecutionPlanPart)eResolveProxy(oldParent);
            if (parent != oldParent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.EXECUTION_PLAN_PART__PARENT, oldParent, parent));
            }
        }
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionPlanPart basicGetParent() {
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(ExecutionPlanPart newParent) {
        ExecutionPlanPart oldParent = parent;
        parent = newParent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_PART__PARENT, oldParent, parent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_PART__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getJvmPrms() {
        if (jvmPrms == null) {
            jvmPrms = new EObjectContainmentEList(ExecutionPlanPartCmdPrm.class, this, PropertiesPackage.EXECUTION_PLAN_PART__JVM_PRMS);
        }
        return jvmPrms;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getContextPrms() {
        if (contextPrms == null) {
            contextPrms = new EObjectContainmentEList(ExecutionPlanPartJobPrm.class, this, PropertiesPackage.EXECUTION_PLAN_PART__CONTEXT_PRMS);
        }
        return contextPrms;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_PLAN_PART__JVM_PRMS:
                return ((InternalEList)getJvmPrms()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.EXECUTION_PLAN_PART__CONTEXT_PRMS:
                return ((InternalEList)getContextPrms()).basicRemove(otherEnd, msgs);
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
            case PropertiesPackage.EXECUTION_PLAN_PART__ID:
                return new Integer(getId());
            case PropertiesPackage.EXECUTION_PLAN_PART__TASK:
                if (resolve) return getTask();
                return basicGetTask();
            case PropertiesPackage.EXECUTION_PLAN_PART__EXECUTION_PLAN:
                if (resolve) return getExecutionPlan();
                return basicGetExecutionPlan();
            case PropertiesPackage.EXECUTION_PLAN_PART__PARENT:
                if (resolve) return getParent();
                return basicGetParent();
            case PropertiesPackage.EXECUTION_PLAN_PART__TYPE:
                return getType();
            case PropertiesPackage.EXECUTION_PLAN_PART__JVM_PRMS:
                return getJvmPrms();
            case PropertiesPackage.EXECUTION_PLAN_PART__CONTEXT_PRMS:
                return getContextPrms();
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
            case PropertiesPackage.EXECUTION_PLAN_PART__ID:
                setId(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__TASK:
                setTask((ExecutionTask)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__EXECUTION_PLAN:
                setExecutionPlan((ExecutionPlan)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__PARENT:
                setParent((ExecutionPlanPart)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__TYPE:
                setType((String)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__JVM_PRMS:
                getJvmPrms().clear();
                getJvmPrms().addAll((Collection)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__CONTEXT_PRMS:
                getContextPrms().clear();
                getContextPrms().addAll((Collection)newValue);
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
            case PropertiesPackage.EXECUTION_PLAN_PART__ID:
                setId(ID_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__TASK:
                setTask((ExecutionTask)null);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__EXECUTION_PLAN:
                setExecutionPlan((ExecutionPlan)null);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__PARENT:
                setParent((ExecutionPlanPart)null);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__JVM_PRMS:
                getJvmPrms().clear();
                return;
            case PropertiesPackage.EXECUTION_PLAN_PART__CONTEXT_PRMS:
                getContextPrms().clear();
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
            case PropertiesPackage.EXECUTION_PLAN_PART__ID:
                return id != ID_EDEFAULT;
            case PropertiesPackage.EXECUTION_PLAN_PART__TASK:
                return task != null;
            case PropertiesPackage.EXECUTION_PLAN_PART__EXECUTION_PLAN:
                return executionPlan != null;
            case PropertiesPackage.EXECUTION_PLAN_PART__PARENT:
                return parent != null;
            case PropertiesPackage.EXECUTION_PLAN_PART__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
            case PropertiesPackage.EXECUTION_PLAN_PART__JVM_PRMS:
                return jvmPrms != null && !jvmPrms.isEmpty();
            case PropertiesPackage.EXECUTION_PLAN_PART__CONTEXT_PRMS:
                return contextPrms != null && !contextPrms.isEmpty();
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
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //ExecutionPlanPartImpl
