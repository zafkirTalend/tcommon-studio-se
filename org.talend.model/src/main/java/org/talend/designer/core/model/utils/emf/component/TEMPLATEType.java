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
 * A representation of the model object '<em><b>TEMPLATE Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#getCTYPE <em>CTYPE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#getLINKTO <em>LINKTO</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#isMULTIPLEMETHODS <em>MULTIPLEMETHODS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#getNAME <em>NAME</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTEMPLATEType()
 * @model extendedMetaData="name='TEMPLATE_._type' kind='empty'"
 * @generated
 */
public interface TEMPLATEType extends EObject {
    /**
     * Returns the value of the '<em><b>CTYPE</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>CTYPE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>CTYPE</em>' attribute.
     * @see #setCTYPE(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTEMPLATEType_CTYPE()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='CTYPE' namespace='##targetNamespace'"
     * @generated
     */
    String getCTYPE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#getCTYPE <em>CTYPE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>CTYPE</em>' attribute.
     * @see #getCTYPE()
     * @generated
     */
    void setCTYPE(String value);

    /**
     * Returns the value of the '<em><b>LINKTO</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>LINKTO</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>LINKTO</em>' attribute.
     * @see #setLINKTO(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTEMPLATEType_LINKTO()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='LINK_TO' namespace='##targetNamespace'"
     * @generated
     */
    String getLINKTO();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#getLINKTO <em>LINKTO</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>LINKTO</em>' attribute.
     * @see #getLINKTO()
     * @generated
     */
    void setLINKTO(String value);

    /**
     * Returns the value of the '<em><b>MULTIPLEMETHODS</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>MULTIPLEMETHODS</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>MULTIPLEMETHODS</em>' attribute.
     * @see #isSetMULTIPLEMETHODS()
     * @see #unsetMULTIPLEMETHODS()
     * @see #setMULTIPLEMETHODS(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTEMPLATEType_MULTIPLEMETHODS()
     * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='MULTIPLE_METHODS' namespace='##targetNamespace'"
     * @generated
     */
    boolean isMULTIPLEMETHODS();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#isMULTIPLEMETHODS <em>MULTIPLEMETHODS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>MULTIPLEMETHODS</em>' attribute.
     * @see #isSetMULTIPLEMETHODS()
     * @see #unsetMULTIPLEMETHODS()
     * @see #isMULTIPLEMETHODS()
     * @generated
     */
    void setMULTIPLEMETHODS(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#isMULTIPLEMETHODS <em>MULTIPLEMETHODS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMULTIPLEMETHODS()
     * @see #isMULTIPLEMETHODS()
     * @see #setMULTIPLEMETHODS(boolean)
     * @generated
     */
    void unsetMULTIPLEMETHODS();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#isMULTIPLEMETHODS <em>MULTIPLEMETHODS</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>MULTIPLEMETHODS</em>' attribute is set.
     * @see #unsetMULTIPLEMETHODS()
     * @see #isMULTIPLEMETHODS()
     * @see #setMULTIPLEMETHODS(boolean)
     * @generated
     */
    boolean isSetMULTIPLEMETHODS();

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
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getTEMPLATEType_NAME()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='NAME' namespace='##targetNamespace'"
     * @generated
     */
    String getNAME();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.TEMPLATEType#getNAME <em>NAME</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>NAME</em>' attribute.
     * @see #getNAME()
     * @generated
     */
    void setNAME(String value);

} // TEMPLATEType