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
 * A representation of the model object '<em><b>ITEMS Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMSType#getITEM <em>ITEM</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMSType#getDEFAULT <em>DEFAULT</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMSType()
 * @model extendedMetaData="name='ITEMS_._type' kind='elementOnly'"
 * @generated
 */
public interface ITEMSType extends EObject {
    /**
     * Returns the value of the '<em><b>ITEM</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.designer.core.model.utils.emf.component.ITEMType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ITEM</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>ITEM</em>' containment reference list.
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMSType_ITEM()
     * @model type="org.talend.designer.core.model.utils.emf.component.ITEMType" containment="true" required="true"
     *        extendedMetaData="kind='element' name='ITEM' namespace='##targetNamespace'"
     * @generated
     */
    EList getITEM();

    /**
     * Returns the value of the '<em><b>DEFAULT</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>DEFAULT</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>DEFAULT</em>' attribute.
     * @see #setDEFAULT(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMSType_DEFAULT()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='DEFAULT' namespace='##targetNamespace'"
     * @generated
     */
    String getDEFAULT();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMSType#getDEFAULT <em>DEFAULT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>DEFAULT</em>' attribute.
     * @see #getDEFAULT()
     * @generated
     */
    void setDEFAULT(String value);

} // ITEMSType