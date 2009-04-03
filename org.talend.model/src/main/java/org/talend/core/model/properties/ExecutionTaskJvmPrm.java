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
 * A representation of the model object '<em><b>Execution Task Jvm Prm</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#isActive <em>Active</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#getExecutionTask <em>Execution Task</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTaskJvmPrm()
 * @model
 * @generated
 */
public interface ExecutionTaskJvmPrm extends EObject {
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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTaskJvmPrm_Id()
     * @model id="true" required="true"
     * @generated
     */
    int getId();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(int value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTaskJvmPrm_Active()
     * @model
     * @generated
     */
    boolean isActive();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#isActive <em>Active</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Active</em>' attribute.
     * @see #isActive()
     * @generated
     */
    void setActive(boolean value);

    /**
     * Returns the value of the '<em><b>Parameter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter</em>' attribute.
     * @see #setParameter(String)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTaskJvmPrm_Parameter()
     * @model
     * @generated
     */
    String getParameter();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#getParameter <em>Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameter</em>' attribute.
     * @see #getParameter()
     * @generated
     */
    void setParameter(String value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTaskJvmPrm_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

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
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionTaskJvmPrm_ExecutionTask()
     * @model
     * @generated
     */
    ExecutionTask getExecutionTask();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionTaskJvmPrm#getExecutionTask <em>Execution Task</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Execution Task</em>' reference.
     * @see #getExecutionTask()
     * @generated
     */
    void setExecutionTask(ExecutionTask value);

} // ExecutionTaskJvmPrm
