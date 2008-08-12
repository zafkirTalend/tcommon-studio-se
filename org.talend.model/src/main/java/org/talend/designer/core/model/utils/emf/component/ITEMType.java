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
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getCONTEXT <em>CONTEXT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getFIELD <em>FIELD</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getFILTER <em>FILTER</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getNAME <em>NAME</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getNOTSHOWIF <em>NOTSHOWIF</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.ITEMType#isREADONLY <em>READONLY</em>}</li>
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
	 * <!-- begin-model-doc -->
	 * Used for combo box in TABLE field
	 * <!-- end-model-doc -->
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
	 * Returns the value of the '<em><b>CONTEXT</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>CONTEXT</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>CONTEXT</em>' attribute.
	 * @see #setCONTEXT(String)
	 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_CONTEXT()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='CONTEXT' namespace='##targetNamespace'"
	 * @generated
	 */
    String getCONTEXT();

    /**
	 * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getCONTEXT <em>CONTEXT</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CONTEXT</em>' attribute.
	 * @see #getCONTEXT()
	 * @generated
	 */
    void setCONTEXT(String value);

    /**
	 * Returns the value of the '<em><b>FIELD</b></em>' attribute.
	 * The default value is <code>"TEXT"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>FIELD</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>FIELD</em>' attribute.
	 * @see #isSetFIELD()
	 * @see #unsetFIELD()
	 * @see #setFIELD(String)
	 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_FIELD()
	 * @model default="TEXT" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='FIELD' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFIELD();

    /**
	 * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getFIELD <em>FIELD</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>FIELD</em>' attribute.
	 * @see #isSetFIELD()
	 * @see #unsetFIELD()
	 * @see #getFIELD()
	 * @generated
	 */
	void setFIELD(String value);

    /**
	 * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getFIELD <em>FIELD</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSetFIELD()
	 * @see #getFIELD()
	 * @see #setFIELD(String)
	 * @generated
	 */
    void unsetFIELD();

    /**
	 * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getFIELD <em>FIELD</em>}' attribute is set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return whether the value of the '<em>FIELD</em>' attribute is set.
	 * @see #unsetFIELD()
	 * @see #getFIELD()
	 * @see #setFIELD(String)
	 * @generated
	 */
    boolean isSetFIELD();

    /**
	 * Returns the value of the '<em><b>FILTER</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>FILTER</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>FILTER</em>' attribute.
	 * @see #setFILTER(String)
	 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_FILTER()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='FILTER' namespace='##targetNamespace'"
	 * @generated
	 */
    String getFILTER();

    /**
	 * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#getFILTER <em>FILTER</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>FILTER</em>' attribute.
	 * @see #getFILTER()
	 * @generated
	 */
    void setFILTER(String value);

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
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
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
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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
	 * Returns the value of the '<em><b>READONLY</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>READONLY</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>READONLY</em>' attribute.
	 * @see #isSetREADONLY()
	 * @see #unsetREADONLY()
	 * @see #setREADONLY(boolean)
	 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getITEMType_READONLY()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='READONLY' namespace='##targetNamespace'"
	 * @generated
	 */
    boolean isREADONLY();

    /**
	 * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#isREADONLY <em>READONLY</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>READONLY</em>' attribute.
	 * @see #isSetREADONLY()
	 * @see #unsetREADONLY()
	 * @see #isREADONLY()
	 * @generated
	 */
    void setREADONLY(boolean value);

    /**
	 * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#isREADONLY <em>READONLY</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSetREADONLY()
	 * @see #isREADONLY()
	 * @see #setREADONLY(boolean)
	 * @generated
	 */
    void unsetREADONLY();

    /**
	 * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.ITEMType#isREADONLY <em>READONLY</em>}' attribute is set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return whether the value of the '<em>READONLY</em>' attribute is set.
	 * @see #unsetREADONLY()
	 * @see #isREADONLY()
	 * @see #setREADONLY(boolean)
	 * @generated
	 */
    boolean isSetREADONLY();

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
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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