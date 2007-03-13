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
 * A representation of the model object '<em><b>TABLE Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.TABLEType#getCOLUMN <em>COLUMN</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTABLEType()
 * @model extendedMetaData="name='TABLE_._type' kind='elementOnly'"
 * @generated
 */
public interface TABLEType extends EObject {
    /**
     * Returns the value of the '<em><b>COLUMN</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.designer.core.model.utils.emf.component.COLUMNType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>COLUMN</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>COLUMN</em>' containment reference list.
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTABLEType_COLUMN()
     * @model type="org.talend.designer.core.model.utils.emf.component.COLUMNType" containment="true"
     *        extendedMetaData="kind='element' name='COLUMN' namespace='##targetNamespace'"
     * @generated
     */
    EList getCOLUMN();

} // TABLEType