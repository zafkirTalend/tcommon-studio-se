/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Plan Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanItem#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanItem#getExecutionTask <em>Execution Task</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanItem#getExecutionPlan <em>Execution Plan</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanItem#getParent <em>Parent</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionPlanItem#getActionType <em>Action Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanItem()
 * @model
 * @generated
 */
public interface ExecutionPlanItem extends EObject {
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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanItem_Id()
     * @model
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanItem#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(int value);

    /**
     * Returns the value of the '<em><b>Execution Task</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Execution Task</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Execution Task</em>' reference.
     * @see #setExecutionTask(ExecutionTask)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanItem_ExecutionTask()
     * @model
     * @generated
     */
    ExecutionTask getExecutionTask();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanItem#getExecutionTask <em>Execution Task</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Execution Task</em>' reference.
     * @see #getExecutionTask()
     * @generated
     */
    void setExecutionTask(ExecutionTask value);

    /**
     * Returns the value of the '<em><b>Execution Plan</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Execution Plan</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Execution Plan</em>' reference.
     * @see #setExecutionPlan(ExecutionPlan)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanItem_ExecutionPlan()
     * @model
     * @generated
     */
    ExecutionPlan getExecutionPlan();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanItem#getExecutionPlan <em>Execution Plan</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Execution Plan</em>' reference.
     * @see #getExecutionPlan()
     * @generated
     */
    void setExecutionPlan(ExecutionPlan value);

    /**
     * Returns the value of the '<em><b>Parent</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' reference.
     * @see #setParent(ExecutionPlanItem)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanItem_Parent()
     * @model
     * @generated
     */
    ExecutionPlanItem getParent();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanItem#getParent <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' reference.
     * @see #getParent()
     * @generated
     */
    void setParent(ExecutionPlanItem value);

    /**
     * Returns the value of the '<em><b>Action Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Action Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Action Type</em>' attribute.
     * @see #setActionType(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionPlanItem_ActionType()
     * @model
     * @generated
     */
    String getActionType();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionPlanItem#getActionType <em>Action Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Action Type</em>' attribute.
     * @see #getActionType()
     * @generated
     */
    void setActionType(String value);

} // ExecutionPlanItem
