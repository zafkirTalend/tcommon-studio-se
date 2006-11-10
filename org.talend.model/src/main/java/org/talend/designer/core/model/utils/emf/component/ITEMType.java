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
 * A representation of the model object '<em><b>ITEM Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getITEMS <em>ITEMS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getFIELD <em>FIELD</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getNAME <em>NAME</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getNOTSHOWIF <em>NOTSHOWIF</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getREPOSITORYITEM <em>REPOSITORYITEM</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getSHOWIF <em>SHOWIF</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getVALUE <em>VALUE</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType()
 * @model extendedMetaData="name='ITEM_._type' kind='elementOnly'"
 * @generated
 */
public interface ITEMType extends EObject {
    /**
     * Returns the value of the '<em><b>ITEMS</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ITEMS</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>ITEMS</em>' containment reference.
     * @see #setITEMS(ITEMSType)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_ITEMS()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='ITEMS' namespace='##targetNamespace'"
     * @generated
     */
	ITEMSType getITEMS();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getITEMS <em>ITEMS</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>ITEMS</em>' containment reference.
     * @see #getITEMS()
     * @generated
     */
	void setITEMS(ITEMSType value);

    /**
     * Returns the value of the '<em><b>FIELD</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>FIELD</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>FIELD</em>' attribute.
     * @see #setFIELD(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_FIELD()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='FIELD' namespace='##targetNamespace'"
     * @generated
     */
	String getFIELD();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getFIELD <em>FIELD</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>FIELD</em>' attribute.
     * @see #getFIELD()
     * @generated
     */
	void setFIELD(String value);

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
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_NAME()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='NAME' namespace='##targetNamespace'"
     * @generated
     */
    String getNAME();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getNAME <em>NAME</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>NAME</em>' attribute.
     * @see #getNAME()
     * @generated
     */
    void setNAME(String value);

    /**
     * Returns the value of the '<em><b>NOTSHOWIF</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>NOTSHOWIF</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>NOTSHOWIF</em>' attribute.
     * @see #setNOTSHOWIF(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_NOTSHOWIF()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='NOT_SHOW_IF' namespace='##targetNamespace'"
     * @generated
     */
    String getNOTSHOWIF();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getNOTSHOWIF <em>NOTSHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>NOTSHOWIF</em>' attribute.
     * @see #getNOTSHOWIF()
     * @generated
     */
    void setNOTSHOWIF(String value);

    /**
     * Returns the value of the '<em><b>REPOSITORYITEM</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>REPOSITORYITEM</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>REPOSITORYITEM</em>' attribute.
     * @see #setREPOSITORYITEM(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_REPOSITORYITEM()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='REPOSITORY_ITEM' namespace='##targetNamespace'"
     * @generated
     */
    String getREPOSITORYITEM();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getREPOSITORYITEM <em>REPOSITORYITEM</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>REPOSITORYITEM</em>' attribute.
     * @see #getREPOSITORYITEM()
     * @generated
     */
    void setREPOSITORYITEM(String value);

    /**
     * Returns the value of the '<em><b>SHOWIF</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>SHOWIF</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>SHOWIF</em>' attribute.
     * @see #setSHOWIF(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_SHOWIF()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='SHOW_IF' namespace='##targetNamespace'"
     * @generated
     */
    String getSHOWIF();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getSHOWIF <em>SHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>SHOWIF</em>' attribute.
     * @see #getSHOWIF()
     * @generated
     */
    void setSHOWIF(String value);

    /**
     * Returns the value of the '<em><b>VALUE</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>VALUE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>VALUE</em>' attribute.
     * @see #setVALUE(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_VALUE()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='VALUE' namespace='##targetNamespace'"
     * @generated
     */
    String getVALUE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getVALUE <em>VALUE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>VALUE</em>' attribute.
     * @see #getVALUE()
     * @generated
     */
    void setVALUE(String value);

} // ITEMType