/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CODEGENERATION Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.CODEGENERATIONType#getTEMPLATES <em>TEMPLATES</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.CODEGENERATIONType#getIMPORTS <em>IMPORTS</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getCODEGENERATIONType()
 * @model extendedMetaData="name='CODEGENERATION_._type' kind='elementOnly'"
 * @generated
 */
public interface CODEGENERATIONType extends EObject {
    /**
     * Returns the value of the '<em><b>TEMPLATES</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>TEMPLATES</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>TEMPLATES</em>' containment reference.
     * @see #setTEMPLATES(TEMPLATESType)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getCODEGENERATIONType_TEMPLATES()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='TEMPLATES' namespace='##targetNamespace'"
     * @generated
     */
    TEMPLATESType getTEMPLATES();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.CODEGENERATIONType#getTEMPLATES <em>TEMPLATES</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>TEMPLATES</em>' containment reference.
     * @see #getTEMPLATES()
     * @generated
     */
    void setTEMPLATES(TEMPLATESType value);

    /**
     * Returns the value of the '<em><b>IMPORTS</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>IMPORTS</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>IMPORTS</em>' containment reference.
     * @see #setIMPORTS(IMPORTSType)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getCODEGENERATIONType_IMPORTS()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='IMPORTS' namespace='##targetNamespace'"
     * @generated
     */
    IMPORTSType getIMPORTS();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.CODEGENERATIONType#getIMPORTS <em>IMPORTS</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>IMPORTS</em>' containment reference.
     * @see #getIMPORTS()
     * @generated
     */
    void setIMPORTS(IMPORTSType value);

} // CODEGENERATIONType