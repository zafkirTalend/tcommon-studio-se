/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.joblet.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Joblet Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.joblet.model.AbstractJobletObject#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.designer.joblet.model.AbstractJobletObject#getPosX <em>Pos X</em>}</li>
 *   <li>{@link org.talend.designer.joblet.model.AbstractJobletObject#getPosY <em>Pos Y</em>}</li>
 *   <li>{@link org.talend.designer.joblet.model.AbstractJobletObject#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.designer.joblet.model.AbstractJobletObject#isInput <em>Input</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.joblet.model.JobletPackage#getAbstractJobletObject()
 * @model abstract="true"
 * @generated
 */
public interface AbstractJobletObject extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.talend.designer.joblet.model.JobletPackage#getAbstractJobletObject_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.talend.designer.joblet.model.AbstractJobletObject#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Pos X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pos X</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pos X</em>' attribute.
     * @see #setPosX(int)
     * @see org.talend.designer.joblet.model.JobletPackage#getAbstractJobletObject_PosX()
     * @model
     * @generated
     */
    int getPosX();

    /**
     * Sets the value of the '{@link org.talend.designer.joblet.model.AbstractJobletObject#getPosX <em>Pos X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pos X</em>' attribute.
     * @see #getPosX()
     * @generated
     */
    void setPosX(int value);

    /**
     * Returns the value of the '<em><b>Pos Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pos Y</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pos Y</em>' attribute.
     * @see #setPosY(int)
     * @see org.talend.designer.joblet.model.JobletPackage#getAbstractJobletObject_PosY()
     * @model
     * @generated
     */
    int getPosY();

    /**
     * Sets the value of the '{@link org.talend.designer.joblet.model.AbstractJobletObject#getPosY <em>Pos Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pos Y</em>' attribute.
     * @see #getPosY()
     * @generated
     */
    void setPosY(int value);

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
     * @see org.talend.designer.joblet.model.JobletPackage#getAbstractJobletObject_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.talend.designer.joblet.model.AbstractJobletObject#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Input</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input</em>' attribute.
     * @see #setInput(boolean)
     * @see org.talend.designer.joblet.model.JobletPackage#getAbstractJobletObject_Input()
     * @model
     * @generated
     */
    boolean isInput();

    /**
     * Sets the value of the '{@link org.talend.designer.joblet.model.AbstractJobletObject#isInput <em>Input</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input</em>' attribute.
     * @see #isInput()
     * @generated
     */
    void setInput(boolean value);

} // AbstractJobletObject
