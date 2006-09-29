/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TEMPLATES Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.TEMPLATESType#getTEMPLATE <em>TEMPLATE</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTEMPLATESType()
 * @model extendedMetaData="name='TEMPLATES_._type' kind='elementOnly'"
 * @generated
 */
public interface TEMPLATESType extends EObject {
    /**
     * Returns the value of the '<em><b>TEMPLATE</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>TEMPLATE</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>TEMPLATE</em>' containment reference list.
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTEMPLATESType_TEMPLATE()
     * @model type="org.talend.designer.core.model.utils.emf.component.TEMPLATEType" containment="true" required="true"
     *        extendedMetaData="kind='element' name='TEMPLATE' namespace='##targetNamespace'"
     * @generated
     */
    EList getTEMPLATE();

} // TEMPLATESType