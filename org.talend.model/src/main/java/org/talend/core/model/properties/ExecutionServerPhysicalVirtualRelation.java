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
 * A representation of the model object '<em><b>Execution Server Physical Virtual Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.ExecutionServerPhysicalVirtualRelation#getPhysical <em>Physical</em>}</li>
 *   <li>{@link org.talend.core.model.properties.ExecutionServerPhysicalVirtualRelation#getVirtual <em>Virtual</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServerPhysicalVirtualRelation()
 * @model
 * @generated
 */
public interface ExecutionServerPhysicalVirtualRelation extends EObject {
    /**
     * Returns the value of the '<em><b>Physical</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.ExecutionServer#getVirtualServersRelations <em>Virtual Servers Relations</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Physical</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Physical</em>' reference.
     * @see #setPhysical(ExecutionServer)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServerPhysicalVirtualRelation_Physical()
     * @see org.talend.core.model.properties.ExecutionServer#getVirtualServersRelations
     * @model opposite="virtualServersRelations"
     * @generated
     */
    ExecutionServer getPhysical();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServerPhysicalVirtualRelation#getPhysical <em>Physical</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Physical</em>' reference.
     * @see #getPhysical()
     * @generated
     */
    void setPhysical(ExecutionServer value);

    /**
     * Returns the value of the '<em><b>Virtual</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.properties.ExecutionVirtualServer#getPhysicalServersRelations <em>Physical Servers Relations</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Virtual</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Virtual</em>' reference.
     * @see #setVirtual(ExecutionVirtualServer)
     * @see org.talend.core.model.properties.PropertiesPackage#getExecutionServerPhysicalVirtualRelation_Virtual()
     * @see org.talend.core.model.properties.ExecutionVirtualServer#getPhysicalServersRelations
     * @model opposite="physicalServersRelations"
     * @generated
     */
    ExecutionVirtualServer getVirtual();

    /**
     * Sets the value of the '{@link org.talend.core.model.properties.ExecutionServerPhysicalVirtualRelation#getVirtual <em>Virtual</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Virtual</em>' reference.
     * @see #getVirtual()
     * @generated
     */
    void setVirtual(ExecutionVirtualServer value);

} // ExecutionServerPhysicalVirtualRelation
