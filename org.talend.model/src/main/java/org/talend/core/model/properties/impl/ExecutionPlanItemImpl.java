/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.core.model.properties.ExecutionPlan;
import org.talend.core.model.properties.ExecutionPlanItem;
import org.talend.core.model.properties.ExecutionTask;
import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Plan Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanItemImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanItemImpl#getExecutionTask <em>Execution Task</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanItemImpl#getExecutionPlan <em>Execution Plan</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanItemImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ExecutionPlanItemImpl#getActionType <em>Action Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionPlanItemImpl extends EObjectImpl implements ExecutionPlanItem {
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
     * The cached value of the '{@link #getExecutionTask() <em>Execution Task</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExecutionTask()
     * @generated
     * @ordered
     */
    protected ExecutionTask executionTask;

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
    protected ExecutionPlanItem parent;

    /**
     * The default value of the '{@link #getActionType() <em>Action Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActionType()
     * @generated
     * @ordered
     */
    protected static final String ACTION_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getActionType() <em>Action Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActionType()
     * @generated
     * @ordered
     */
    protected String actionType = ACTION_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExecutionPlanItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.EXECUTION_PLAN_ITEM;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_ITEM__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionTask getExecutionTask() {
        if (executionTask != null && executionTask.eIsProxy()) {
            InternalEObject oldExecutionTask = (InternalEObject)executionTask;
            executionTask = (ExecutionTask)eResolveProxy(oldExecutionTask);
            if (executionTask != oldExecutionTask) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_TASK, oldExecutionTask, executionTask));
            }
        }
        return executionTask;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionTask basicGetExecutionTask() {
        return executionTask;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExecutionTask(ExecutionTask newExecutionTask) {
        ExecutionTask oldExecutionTask = executionTask;
        executionTask = newExecutionTask;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_TASK, oldExecutionTask, executionTask));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_PLAN, oldExecutionPlan, executionPlan));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_PLAN, oldExecutionPlan, executionPlan));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionPlanItem getParent() {
        if (parent != null && parent.eIsProxy()) {
            InternalEObject oldParent = (InternalEObject)parent;
            parent = (ExecutionPlanItem)eResolveProxy(oldParent);
            if (parent != oldParent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.EXECUTION_PLAN_ITEM__PARENT, oldParent, parent));
            }
        }
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionPlanItem basicGetParent() {
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(ExecutionPlanItem newParent) {
        ExecutionPlanItem oldParent = parent;
        parent = newParent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_ITEM__PARENT, oldParent, parent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setActionType(String newActionType) {
        String oldActionType = actionType;
        actionType = newActionType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.EXECUTION_PLAN_ITEM__ACTION_TYPE, oldActionType, actionType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.EXECUTION_PLAN_ITEM__ID:
                return new Integer(getId());
            case PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_TASK:
                if (resolve) return getExecutionTask();
                return basicGetExecutionTask();
            case PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_PLAN:
                if (resolve) return getExecutionPlan();
                return basicGetExecutionPlan();
            case PropertiesPackage.EXECUTION_PLAN_ITEM__PARENT:
                if (resolve) return getParent();
                return basicGetParent();
            case PropertiesPackage.EXECUTION_PLAN_ITEM__ACTION_TYPE:
                return getActionType();
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
            case PropertiesPackage.EXECUTION_PLAN_ITEM__ID:
                setId(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_TASK:
                setExecutionTask((ExecutionTask)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_PLAN:
                setExecutionPlan((ExecutionPlan)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__PARENT:
                setParent((ExecutionPlanItem)newValue);
                return;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__ACTION_TYPE:
                setActionType((String)newValue);
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
            case PropertiesPackage.EXECUTION_PLAN_ITEM__ID:
                setId(ID_EDEFAULT);
                return;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_TASK:
                setExecutionTask((ExecutionTask)null);
                return;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_PLAN:
                setExecutionPlan((ExecutionPlan)null);
                return;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__PARENT:
                setParent((ExecutionPlanItem)null);
                return;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__ACTION_TYPE:
                setActionType(ACTION_TYPE_EDEFAULT);
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
            case PropertiesPackage.EXECUTION_PLAN_ITEM__ID:
                return id != ID_EDEFAULT;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_TASK:
                return executionTask != null;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__EXECUTION_PLAN:
                return executionPlan != null;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__PARENT:
                return parent != null;
            case PropertiesPackage.EXECUTION_PLAN_ITEM__ACTION_TYPE:
                return ACTION_TYPE_EDEFAULT == null ? actionType != null : !ACTION_TYPE_EDEFAULT.equals(actionType);
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
        result.append(", actionType: ");
        result.append(actionType);
        result.append(')');
        return result.toString();
    }

} //ExecutionPlanItemImpl
