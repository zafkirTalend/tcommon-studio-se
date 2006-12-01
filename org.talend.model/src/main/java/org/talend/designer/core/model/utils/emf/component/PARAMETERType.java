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
 * A representation of the model object '<em><b>PARAMETER Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getDEFAULT <em>DEFAULT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getITEMS <em>ITEMS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getTABLE <em>TABLE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getFIELD <em>FIELD</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNAME <em>NAME</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNBLINES <em>NBLINES</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNOTSHOWIF <em>NOTSHOWIF</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNUMROW <em>NUMROW</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isREADONLY <em>READONLY</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getREPOSITORYVALUE <em>REPOSITORYVALUE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isREQUIRED <em>REQUIRED</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isSHOW <em>SHOW</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getSHOWIF <em>SHOWIF</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType()
 * @model extendedMetaData="name='PARAMETER_._type' kind='elementOnly'"
 * @generated
 */
public interface PARAMETERType extends EObject {
    /**
     * Returns the value of the '<em><b>DEFAULT</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.designer.core.model.utils.emf.component.DEFAULTType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>DEFAULT</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Used for TEXT or CHECK field
     * <!-- end-model-doc -->
     * @return the value of the '<em>DEFAULT</em>' containment reference list.
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_DEFAULT()
     * @model type="org.talend.designer.core.model.utils.emf.component.DEFAULTType" containment="true"
     *        extendedMetaData="kind='element' name='DEFAULT' namespace='##targetNamespace'"
     * @generated
     */
    EList getDEFAULT();

    /**
     * Returns the value of the '<em><b>ITEMS</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ITEMS</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Used for CLOSED_LIST or TABLE field
     * <!-- end-model-doc -->
     * @return the value of the '<em>ITEMS</em>' containment reference.
     * @see #setITEMS(ITEMSType)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_ITEMS()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='ITEMS' namespace='##targetNamespace'"
     * @generated
     */
    ITEMSType getITEMS();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getITEMS <em>ITEMS</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>ITEMS</em>' containment reference.
     * @see #getITEMS()
     * @generated
     */
    void setITEMS(ITEMSType value);

    /**
     * Returns the value of the '<em><b>TABLE</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Used only for SCHEMA_TYPE field 
     * <!-- end-model-doc -->
     * @return the value of the '<em>TABLE</em>' containment reference.
     * @see #setTABLE(TABLEType)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_TABLE()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='TABLE' namespace='##targetNamespace'"
     * @generated
     */
    TABLEType getTABLE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getTABLE <em>TABLE</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>TABLE</em>' containment reference.
     * @see #getTABLE()
     * @generated
     */
    void setTABLE(TABLEType value);

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
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_FIELD()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='FIELD' namespace='##targetNamespace'"
     * @generated
     */
    String getFIELD();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getFIELD <em>FIELD</em>}' attribute.
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
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_NAME()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='NAME' namespace='##targetNamespace'"
     * @generated
     */
    String getNAME();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNAME <em>NAME</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>NAME</em>' attribute.
     * @see #getNAME()
     * @generated
     */
    void setNAME(String value);

    /**
     * Returns the value of the '<em><b>NBLINES</b></em>' attribute.
     * The default value is <code>"3"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>NBLINES</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>NBLINES</em>' attribute.
     * @see #isSetNBLINES()
     * @see #unsetNBLINES()
     * @see #setNBLINES(int)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_NBLINES()
     * @model default="3" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='attribute' name='NB_LINES' namespace='##targetNamespace'"
     * @generated
     */
    int getNBLINES();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNBLINES <em>NBLINES</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>NBLINES</em>' attribute.
     * @see #isSetNBLINES()
     * @see #unsetNBLINES()
     * @see #getNBLINES()
     * @generated
     */
    void setNBLINES(int value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNBLINES <em>NBLINES</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetNBLINES()
     * @see #getNBLINES()
     * @see #setNBLINES(int)
     * @generated
     */
    void unsetNBLINES();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNBLINES <em>NBLINES</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>NBLINES</em>' attribute is set.
     * @see #unsetNBLINES()
     * @see #getNBLINES()
     * @see #setNBLINES(int)
     * @generated
     */
    boolean isSetNBLINES();

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
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_NOTSHOWIF()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='NOT_SHOW_IF' namespace='##targetNamespace'"
     * @generated
     */
    String getNOTSHOWIF();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNOTSHOWIF <em>NOTSHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>NOTSHOWIF</em>' attribute.
     * @see #getNOTSHOWIF()
     * @generated
     */
    void setNOTSHOWIF(String value);

    /**
     * Returns the value of the '<em><b>NUMROW</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>NUMROW</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>NUMROW</em>' attribute.
     * @see #isSetNUMROW()
     * @see #unsetNUMROW()
     * @see #setNUMROW(int)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_NUMROW()
     * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
     *        extendedMetaData="kind='attribute' name='NUM_ROW' namespace='##targetNamespace'"
     * @generated
     */
    int getNUMROW();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNUMROW <em>NUMROW</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>NUMROW</em>' attribute.
     * @see #isSetNUMROW()
     * @see #unsetNUMROW()
     * @see #getNUMROW()
     * @generated
     */
    void setNUMROW(int value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNUMROW <em>NUMROW</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetNUMROW()
     * @see #getNUMROW()
     * @see #setNUMROW(int)
     * @generated
     */
    void unsetNUMROW();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getNUMROW <em>NUMROW</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>NUMROW</em>' attribute is set.
     * @see #unsetNUMROW()
     * @see #getNUMROW()
     * @see #setNUMROW(int)
     * @generated
     */
    boolean isSetNUMROW();

    /**
     * Returns the value of the '<em><b>READONLY</b></em>' attribute.
     * The default value is <code>"false"</code>.
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
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_READONLY()
     * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='READONLY' namespace='##targetNamespace'"
     * @generated
     */
    boolean isREADONLY();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isREADONLY <em>READONLY</em>}' attribute.
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
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isREADONLY <em>READONLY</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetREADONLY()
     * @see #isREADONLY()
     * @see #setREADONLY(boolean)
     * @generated
     */
    void unsetREADONLY();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isREADONLY <em>READONLY</em>}' attribute is set.
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
     * Returns the value of the '<em><b>REPOSITORYVALUE</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>REPOSITORYVALUE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>REPOSITORYVALUE</em>' attribute.
     * @see #setREPOSITORYVALUE(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_REPOSITORYVALUE()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='REPOSITORY_VALUE' namespace='##targetNamespace'"
     * @generated
     */
    String getREPOSITORYVALUE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getREPOSITORYVALUE <em>REPOSITORYVALUE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>REPOSITORYVALUE</em>' attribute.
     * @see #getREPOSITORYVALUE()
     * @generated
     */
    void setREPOSITORYVALUE(String value);

    /**
     * Returns the value of the '<em><b>REQUIRED</b></em>' attribute.
     * The default value is <code>"false"</code>.
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
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_REQUIRED()
     * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='REQUIRED' namespace='##targetNamespace'"
     * @generated
     */
    boolean isREQUIRED();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isREQUIRED <em>REQUIRED</em>}' attribute.
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
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isREQUIRED <em>REQUIRED</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetREQUIRED()
     * @see #isREQUIRED()
     * @see #setREQUIRED(boolean)
     * @generated
     */
    void unsetREQUIRED();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isREQUIRED <em>REQUIRED</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>REQUIRED</em>' attribute is set.
     * @see #unsetREQUIRED()
     * @see #isREQUIRED()
     * @see #setREQUIRED(boolean)
     * @generated
     */
    boolean isSetREQUIRED();

    /**
     * Returns the value of the '<em><b>SHOW</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>SHOW</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>SHOW</em>' attribute.
     * @see #isSetSHOW()
     * @see #unsetSHOW()
     * @see #setSHOW(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_SHOW()
     * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='SHOW' namespace='##targetNamespace'"
     * @generated
     */
    boolean isSHOW();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isSHOW <em>SHOW</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>SHOW</em>' attribute.
     * @see #isSetSHOW()
     * @see #unsetSHOW()
     * @see #isSHOW()
     * @generated
     */
    void setSHOW(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isSHOW <em>SHOW</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetSHOW()
     * @see #isSHOW()
     * @see #setSHOW(boolean)
     * @generated
     */
    void unsetSHOW();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#isSHOW <em>SHOW</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>SHOW</em>' attribute is set.
     * @see #unsetSHOW()
     * @see #isSHOW()
     * @see #setSHOW(boolean)
     * @generated
     */
    boolean isSetSHOW();

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
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getPARAMETERType_SHOWIF()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='SHOW_IF' namespace='##targetNamespace'"
     * @generated
     */
    String getSHOWIF();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.PARAMETERType#getSHOWIF <em>SHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>SHOWIF</em>' attribute.
     * @see #getSHOWIF()
     * @generated
     */
    void setSHOWIF(String value);

} // PARAMETERType