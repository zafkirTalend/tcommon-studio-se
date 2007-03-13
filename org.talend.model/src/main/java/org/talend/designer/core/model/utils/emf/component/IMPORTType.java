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
 * A representation of the model object '<em><b>IMPORT Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#getENTRYPOINT <em>ENTRYPOINT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#getMESSAGE <em>MESSAGE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#getMODULE <em>MODULE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#getNAME <em>NAME</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#isREQUIRED <em>REQUIRED</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getIMPORTType()
 * @model extendedMetaData="name='IMPORT_._type' kind='empty'"
 * @generated
 */
public interface IMPORTType extends EObject {
    /**
     * Returns the value of the '<em><b>ENTRYPOINT</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ENTRYPOINT</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>ENTRYPOINT</em>' attribute.
     * @see #setENTRYPOINT(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getIMPORTType_ENTRYPOINT()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='ENTRYPOINT' namespace='##targetNamespace'"
     * @generated
     */
    String getENTRYPOINT();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#getENTRYPOINT <em>ENTRYPOINT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>ENTRYPOINT</em>' attribute.
     * @see #getENTRYPOINT()
     * @generated
     */
    void setENTRYPOINT(String value);

    /**
     * Returns the value of the '<em><b>MESSAGE</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>MESSAGE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>MESSAGE</em>' attribute.
     * @see #setMESSAGE(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getIMPORTType_MESSAGE()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='MESSAGE' namespace='##targetNamespace'"
     * @generated
     */
    String getMESSAGE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#getMESSAGE <em>MESSAGE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>MESSAGE</em>' attribute.
     * @see #getMESSAGE()
     * @generated
     */
    void setMESSAGE(String value);

    /**
     * Returns the value of the '<em><b>MODULE</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>MODULE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>MODULE</em>' attribute.
     * @see #setMODULE(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getIMPORTType_MODULE()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='MODULE' namespace='##targetNamespace'"
     * @generated
     */
    String getMODULE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#getMODULE <em>MODULE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>MODULE</em>' attribute.
     * @see #getMODULE()
     * @generated
     */
    void setMODULE(String value);

    /**
     * Returns the value of the '<em><b>NAME</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>NAME</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>NAME</em>' attribute.
     * @see #setNAME(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getIMPORTType_NAME()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='NAME' namespace='##targetNamespace'"
     * @generated
     */
    String getNAME();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#getNAME <em>NAME</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>NAME</em>' attribute.
     * @see #getNAME()
     * @generated
     */
    void setNAME(String value);

    /**
     * Returns the value of the '<em><b>REQUIRED</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>REQUIRED</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>REQUIRED</em>' attribute.
     * @see #isSetREQUIRED()
     * @see #unsetREQUIRED()
     * @see #setREQUIRED(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getIMPORTType_REQUIRED()
     * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='REQUIRED' namespace='##targetNamespace'"
     * @generated
     */
    boolean isREQUIRED();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#isREQUIRED <em>REQUIRED</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>REQUIRED</em>' attribute.
     * @see #isSetREQUIRED()
     * @see #unsetREQUIRED()
     * @see #isREQUIRED()
     * @generated
     */
    void setREQUIRED(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#isREQUIRED <em>REQUIRED</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetREQUIRED()
     * @see #isREQUIRED()
     * @see #setREQUIRED(boolean)
     * @generated
     */
    void unsetREQUIRED();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.IMPORTType#isREQUIRED <em>REQUIRED</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>REQUIRED</em>' attribute is set.
     * @see #unsetREQUIRED()
     * @see #isREQUIRED()
     * @see #setREQUIRED(boolean)
     * @generated
     */
    boolean isSetREQUIRED();

} // IMPORTType