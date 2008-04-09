/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>HEADER Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getSIGNATURE <em>SIGNATURE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getFORMAT <em>FORMAT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getAUTHOR <em>AUTHOR</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getCOMPATIBILITY <em>COMPATIBILITY</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isDATAAUTOPROPAGATE <em>DATAAUTOPROPAGATE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getEXTENSION <em>EXTENSION</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isHASCONDITIONALOUTPUTS <em>HASCONDITIONALOUTPUTS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isHASHCOMPONENT <em>HASHCOMPONENT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isISMULTIPLYINGOUTPUTS <em>ISMULTIPLYINGOUTPUTS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isISSUBTREEWITHLOOP <em>ISSUBTREEWITHLOOP</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getPLATEFORM <em>PLATEFORM</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getRELEASEDATE <em>RELEASEDATE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isSCHEMAAUTOPROPAGATE <em>SCHEMAAUTOPROPAGATE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getSERIAL <em>SERIAL</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isSTARTABLE <em>STARTABLE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getSTATUS <em>STATUS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isTSTATCATCHERSTATS <em>TSTATCATCHERSTATS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getVERSION <em>VERSION</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isVISIBLE <em>VISIBLE</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType()
 * @model extendedMetaData="name='HEADER_._type' kind='elementOnly'"
 * @generated
 */
public interface HEADERType extends EObject {
    /**
     * Returns the value of the '<em><b>SIGNATURE</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>SIGNATURE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * 							Not used for the moment
     * 						
     * <!-- end-model-doc -->
     * @return the value of the '<em>SIGNATURE</em>' attribute.
     * @see #setSIGNATURE(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_SIGNATURE()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='SIGNATURE' namespace='##targetNamespace'"
     * @generated
     */
    String getSIGNATURE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getSIGNATURE <em>SIGNATURE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>SIGNATURE</em>' attribute.
     * @see #getSIGNATURE()
     * @generated
     */
    void setSIGNATURE(String value);

    /**
     * Returns the value of the '<em><b>FORMAT</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>FORMAT</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>FORMAT</em>' containment reference.
     * @see #setFORMAT(FORMATType)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_FORMAT()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='FORMAT' namespace='##targetNamespace'"
     * @generated
     */
    FORMATType getFORMAT();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getFORMAT <em>FORMAT</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>FORMAT</em>' containment reference.
     * @see #getFORMAT()
     * @generated
     */
    void setFORMAT(FORMATType value);

    /**
     * Returns the value of the '<em><b>AUTHOR</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>AUTHOR</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>AUTHOR</em>' attribute.
     * @see #setAUTHOR(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_AUTHOR()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='AUTHOR' namespace='##targetNamespace'"
     * @generated
     */
    String getAUTHOR();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getAUTHOR <em>AUTHOR</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>AUTHOR</em>' attribute.
     * @see #getAUTHOR()
     * @generated
     */
    void setAUTHOR(String value);

    /**
     * Returns the value of the '<em><b>COMPATIBILITY</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>COMPATIBILITY</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>COMPATIBILITY</em>' attribute.
     * @see #setCOMPATIBILITY(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_COMPATIBILITY()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='COMPATIBILITY' namespace='##targetNamespace'"
     * @generated
     */
    String getCOMPATIBILITY();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getCOMPATIBILITY <em>COMPATIBILITY</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>COMPATIBILITY</em>' attribute.
     * @see #getCOMPATIBILITY()
     * @generated
     */
    void setCOMPATIBILITY(String value);

    /**
     * Returns the value of the '<em><b>DATAAUTOPROPAGATE</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>DATAAUTOPROPAGATE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>DATAAUTOPROPAGATE</em>' attribute.
     * @see #isSetDATAAUTOPROPAGATE()
     * @see #unsetDATAAUTOPROPAGATE()
     * @see #setDATAAUTOPROPAGATE(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_DATAAUTOPROPAGATE()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='DATA_AUTO_PROPAGATE' namespace='##targetNamespace'"
     * @generated
     */
    boolean isDATAAUTOPROPAGATE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isDATAAUTOPROPAGATE <em>DATAAUTOPROPAGATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>DATAAUTOPROPAGATE</em>' attribute.
     * @see #isSetDATAAUTOPROPAGATE()
     * @see #unsetDATAAUTOPROPAGATE()
     * @see #isDATAAUTOPROPAGATE()
     * @generated
     */
    void setDATAAUTOPROPAGATE(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isDATAAUTOPROPAGATE <em>DATAAUTOPROPAGATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDATAAUTOPROPAGATE()
     * @see #isDATAAUTOPROPAGATE()
     * @see #setDATAAUTOPROPAGATE(boolean)
     * @generated
     */
    void unsetDATAAUTOPROPAGATE();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isDATAAUTOPROPAGATE <em>DATAAUTOPROPAGATE</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>DATAAUTOPROPAGATE</em>' attribute is set.
     * @see #unsetDATAAUTOPROPAGATE()
     * @see #isDATAAUTOPROPAGATE()
     * @see #setDATAAUTOPROPAGATE(boolean)
     * @generated
     */
    boolean isSetDATAAUTOPROPAGATE();

    /**
     * Returns the value of the '<em><b>EXTENSION</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>EXTENSION</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>EXTENSION</em>' attribute.
     * @see #setEXTENSION(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_EXTENSION()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='EXTENSION' namespace='##targetNamespace'"
     * @generated
     */
    String getEXTENSION();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getEXTENSION <em>EXTENSION</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>EXTENSION</em>' attribute.
     * @see #getEXTENSION()
     * @generated
     */
    void setEXTENSION(String value);

    /**
     * Returns the value of the '<em><b>HASCONDITIONALOUTPUTS</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>HASCONDITIONALOUTPUTS</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>HASCONDITIONALOUTPUTS</em>' attribute.
     * @see #isSetHASCONDITIONALOUTPUTS()
     * @see #unsetHASCONDITIONALOUTPUTS()
     * @see #setHASCONDITIONALOUTPUTS(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_HASCONDITIONALOUTPUTS()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='HAS_CONDITIONAL_OUTPUTS' namespace='##targetNamespace'"
     * @generated
     */
    boolean isHASCONDITIONALOUTPUTS();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isHASCONDITIONALOUTPUTS <em>HASCONDITIONALOUTPUTS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>HASCONDITIONALOUTPUTS</em>' attribute.
     * @see #isSetHASCONDITIONALOUTPUTS()
     * @see #unsetHASCONDITIONALOUTPUTS()
     * @see #isHASCONDITIONALOUTPUTS()
     * @generated
     */
    void setHASCONDITIONALOUTPUTS(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isHASCONDITIONALOUTPUTS <em>HASCONDITIONALOUTPUTS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetHASCONDITIONALOUTPUTS()
     * @see #isHASCONDITIONALOUTPUTS()
     * @see #setHASCONDITIONALOUTPUTS(boolean)
     * @generated
     */
    void unsetHASCONDITIONALOUTPUTS();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isHASCONDITIONALOUTPUTS <em>HASCONDITIONALOUTPUTS</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>HASCONDITIONALOUTPUTS</em>' attribute is set.
     * @see #unsetHASCONDITIONALOUTPUTS()
     * @see #isHASCONDITIONALOUTPUTS()
     * @see #setHASCONDITIONALOUTPUTS(boolean)
     * @generated
     */
    boolean isSetHASCONDITIONALOUTPUTS();

    /**
     * Returns the value of the '<em><b>HASHCOMPONENT</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>HASHCOMPONENT</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>HASHCOMPONENT</em>' attribute.
     * @see #isSetHASHCOMPONENT()
     * @see #unsetHASHCOMPONENT()
     * @see #setHASHCOMPONENT(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_HASHCOMPONENT()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='HASH_COMPONENT' namespace='##targetNamespace'"
     * @generated
     */
    boolean isHASHCOMPONENT();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isHASHCOMPONENT <em>HASHCOMPONENT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>HASHCOMPONENT</em>' attribute.
     * @see #isSetHASHCOMPONENT()
     * @see #unsetHASHCOMPONENT()
     * @see #isHASHCOMPONENT()
     * @generated
     */
    void setHASHCOMPONENT(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isHASHCOMPONENT <em>HASHCOMPONENT</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetHASHCOMPONENT()
     * @see #isHASHCOMPONENT()
     * @see #setHASHCOMPONENT(boolean)
     * @generated
     */
    void unsetHASHCOMPONENT();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isHASHCOMPONENT <em>HASHCOMPONENT</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>HASHCOMPONENT</em>' attribute is set.
     * @see #unsetHASHCOMPONENT()
     * @see #isHASHCOMPONENT()
     * @see #setHASHCOMPONENT(boolean)
     * @generated
     */
    boolean isSetHASHCOMPONENT();

    /**
     * Returns the value of the '<em><b>ISMULTIPLYINGOUTPUTS</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ISMULTIPLYINGOUTPUTS</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>ISMULTIPLYINGOUTPUTS</em>' attribute.
     * @see #isSetISMULTIPLYINGOUTPUTS()
     * @see #unsetISMULTIPLYINGOUTPUTS()
     * @see #setISMULTIPLYINGOUTPUTS(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_ISMULTIPLYINGOUTPUTS()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='IS_MULTIPLYING_OUTPUTS' namespace='##targetNamespace'"
     * @generated
     */
    boolean isISMULTIPLYINGOUTPUTS();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isISMULTIPLYINGOUTPUTS <em>ISMULTIPLYINGOUTPUTS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>ISMULTIPLYINGOUTPUTS</em>' attribute.
     * @see #isSetISMULTIPLYINGOUTPUTS()
     * @see #unsetISMULTIPLYINGOUTPUTS()
     * @see #isISMULTIPLYINGOUTPUTS()
     * @generated
     */
    void setISMULTIPLYINGOUTPUTS(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isISMULTIPLYINGOUTPUTS <em>ISMULTIPLYINGOUTPUTS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetISMULTIPLYINGOUTPUTS()
     * @see #isISMULTIPLYINGOUTPUTS()
     * @see #setISMULTIPLYINGOUTPUTS(boolean)
     * @generated
     */
    void unsetISMULTIPLYINGOUTPUTS();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isISMULTIPLYINGOUTPUTS <em>ISMULTIPLYINGOUTPUTS</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>ISMULTIPLYINGOUTPUTS</em>' attribute is set.
     * @see #unsetISMULTIPLYINGOUTPUTS()
     * @see #isISMULTIPLYINGOUTPUTS()
     * @see #setISMULTIPLYINGOUTPUTS(boolean)
     * @generated
     */
    boolean isSetISMULTIPLYINGOUTPUTS();

    /**
     * Returns the value of the '<em><b>ISSUBTREEWITHLOOP</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ISSUBTREEWITHLOOP</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>ISSUBTREEWITHLOOP</em>' attribute.
     * @see #isSetISSUBTREEWITHLOOP()
     * @see #unsetISSUBTREEWITHLOOP()
     * @see #setISSUBTREEWITHLOOP(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_ISSUBTREEWITHLOOP()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='IS_SUBTREE_WITH_LOOP' namespace='##targetNamespace'"
     * @generated
     */
    boolean isISSUBTREEWITHLOOP();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isISSUBTREEWITHLOOP <em>ISSUBTREEWITHLOOP</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>ISSUBTREEWITHLOOP</em>' attribute.
     * @see #isSetISSUBTREEWITHLOOP()
     * @see #unsetISSUBTREEWITHLOOP()
     * @see #isISSUBTREEWITHLOOP()
     * @generated
     */
    void setISSUBTREEWITHLOOP(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isISSUBTREEWITHLOOP <em>ISSUBTREEWITHLOOP</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetISSUBTREEWITHLOOP()
     * @see #isISSUBTREEWITHLOOP()
     * @see #setISSUBTREEWITHLOOP(boolean)
     * @generated
     */
    void unsetISSUBTREEWITHLOOP();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isISSUBTREEWITHLOOP <em>ISSUBTREEWITHLOOP</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>ISSUBTREEWITHLOOP</em>' attribute is set.
     * @see #unsetISSUBTREEWITHLOOP()
     * @see #isISSUBTREEWITHLOOP()
     * @see #setISSUBTREEWITHLOOP(boolean)
     * @generated
     */
    boolean isSetISSUBTREEWITHLOOP();

    /**
     * Returns the value of the '<em><b>PLATEFORM</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>PLATEFORM</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>PLATEFORM</em>' attribute.
     * @see #setPLATEFORM(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_PLATEFORM()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='PLATEFORM' namespace='##targetNamespace'"
     * @generated
     */
    String getPLATEFORM();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getPLATEFORM <em>PLATEFORM</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>PLATEFORM</em>' attribute.
     * @see #getPLATEFORM()
     * @generated
     */
    void setPLATEFORM(String value);

    /**
     * Returns the value of the '<em><b>RELEASEDATE</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>RELEASEDATE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>RELEASEDATE</em>' attribute.
     * @see #setRELEASEDATE(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_RELEASEDATE()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='RELEASE_DATE' namespace='##targetNamespace'"
     * @generated
     */
    String getRELEASEDATE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getRELEASEDATE <em>RELEASEDATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>RELEASEDATE</em>' attribute.
     * @see #getRELEASEDATE()
     * @generated
     */
    void setRELEASEDATE(String value);

    /**
     * Returns the value of the '<em><b>SCHEMAAUTOPROPAGATE</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>SCHEMAAUTOPROPAGATE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>SCHEMAAUTOPROPAGATE</em>' attribute.
     * @see #isSetSCHEMAAUTOPROPAGATE()
     * @see #unsetSCHEMAAUTOPROPAGATE()
     * @see #setSCHEMAAUTOPROPAGATE(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_SCHEMAAUTOPROPAGATE()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='SCHEMA_AUTO_PROPAGATE' namespace='##targetNamespace'"
     * @generated
     */
    boolean isSCHEMAAUTOPROPAGATE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isSCHEMAAUTOPROPAGATE <em>SCHEMAAUTOPROPAGATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>SCHEMAAUTOPROPAGATE</em>' attribute.
     * @see #isSetSCHEMAAUTOPROPAGATE()
     * @see #unsetSCHEMAAUTOPROPAGATE()
     * @see #isSCHEMAAUTOPROPAGATE()
     * @generated
     */
    void setSCHEMAAUTOPROPAGATE(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isSCHEMAAUTOPROPAGATE <em>SCHEMAAUTOPROPAGATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetSCHEMAAUTOPROPAGATE()
     * @see #isSCHEMAAUTOPROPAGATE()
     * @see #setSCHEMAAUTOPROPAGATE(boolean)
     * @generated
     */
    void unsetSCHEMAAUTOPROPAGATE();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isSCHEMAAUTOPROPAGATE <em>SCHEMAAUTOPROPAGATE</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>SCHEMAAUTOPROPAGATE</em>' attribute is set.
     * @see #unsetSCHEMAAUTOPROPAGATE()
     * @see #isSCHEMAAUTOPROPAGATE()
     * @see #setSCHEMAAUTOPROPAGATE(boolean)
     * @generated
     */
    boolean isSetSCHEMAAUTOPROPAGATE();

    /**
     * Returns the value of the '<em><b>SERIAL</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>SERIAL</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>SERIAL</em>' attribute.
     * @see #setSERIAL(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_SERIAL()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='SERIAL' namespace='##targetNamespace'"
     * @generated
     */
    String getSERIAL();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getSERIAL <em>SERIAL</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>SERIAL</em>' attribute.
     * @see #getSERIAL()
     * @generated
     */
    void setSERIAL(String value);

    /**
     * Returns the value of the '<em><b>STARTABLE</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>STARTABLE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>STARTABLE</em>' attribute.
     * @see #isSetSTARTABLE()
     * @see #unsetSTARTABLE()
     * @see #setSTARTABLE(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_STARTABLE()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
     *        extendedMetaData="kind='attribute' name='STARTABLE' namespace='##targetNamespace'"
     * @generated
     */
    boolean isSTARTABLE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isSTARTABLE <em>STARTABLE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>STARTABLE</em>' attribute.
     * @see #isSetSTARTABLE()
     * @see #unsetSTARTABLE()
     * @see #isSTARTABLE()
     * @generated
     */
    void setSTARTABLE(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isSTARTABLE <em>STARTABLE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetSTARTABLE()
     * @see #isSTARTABLE()
     * @see #setSTARTABLE(boolean)
     * @generated
     */
    void unsetSTARTABLE();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isSTARTABLE <em>STARTABLE</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>STARTABLE</em>' attribute is set.
     * @see #unsetSTARTABLE()
     * @see #isSTARTABLE()
     * @see #setSTARTABLE(boolean)
     * @generated
     */
    boolean isSetSTARTABLE();

    /**
     * Returns the value of the '<em><b>STATUS</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>STATUS</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>STATUS</em>' attribute.
     * @see #setSTATUS(String)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_STATUS()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='STATUS' namespace='##targetNamespace'"
     * @generated
     */
    String getSTATUS();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getSTATUS <em>STATUS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>STATUS</em>' attribute.
     * @see #getSTATUS()
     * @generated
     */
    void setSTATUS(String value);

    /**
     * Returns the value of the '<em><b>TSTATCATCHERSTATS</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>TSTATCATCHERSTATS</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>TSTATCATCHERSTATS</em>' attribute.
     * @see #isSetTSTATCATCHERSTATS()
     * @see #unsetTSTATCATCHERSTATS()
     * @see #setTSTATCATCHERSTATS(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_TSTATCATCHERSTATS()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='TSTATCATCHER_STATS' namespace='##targetNamespace'"
     * @generated
     */
    boolean isTSTATCATCHERSTATS();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isTSTATCATCHERSTATS <em>TSTATCATCHERSTATS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>TSTATCATCHERSTATS</em>' attribute.
     * @see #isSetTSTATCATCHERSTATS()
     * @see #unsetTSTATCATCHERSTATS()
     * @see #isTSTATCATCHERSTATS()
     * @generated
     */
    void setTSTATCATCHERSTATS(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isTSTATCATCHERSTATS <em>TSTATCATCHERSTATS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTSTATCATCHERSTATS()
     * @see #isTSTATCATCHERSTATS()
     * @see #setTSTATCATCHERSTATS(boolean)
     * @generated
     */
    void unsetTSTATCATCHERSTATS();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isTSTATCATCHERSTATS <em>TSTATCATCHERSTATS</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>TSTATCATCHERSTATS</em>' attribute is set.
     * @see #unsetTSTATCATCHERSTATS()
     * @see #isTSTATCATCHERSTATS()
     * @see #setTSTATCATCHERSTATS(boolean)
     * @generated
     */
    boolean isSetTSTATCATCHERSTATS();

    /**
     * Returns the value of the '<em><b>VERSION</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>VERSION</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>VERSION</em>' attribute.
     * @see #setVERSION(BigDecimal)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_VERSION()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
     *        extendedMetaData="kind='attribute' name='VERSION' namespace='##targetNamespace'"
     * @generated
     */
    BigDecimal getVERSION();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#getVERSION <em>VERSION</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>VERSION</em>' attribute.
     * @see #getVERSION()
     * @generated
     */
    void setVERSION(BigDecimal value);

    /**
     * Returns the value of the '<em><b>VISIBLE</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>VISIBLE</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>VISIBLE</em>' attribute.
     * @see #isSetVISIBLE()
     * @see #unsetVISIBLE()
     * @see #setVISIBLE(boolean)
     * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage#getHEADERType_VISIBLE()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='VISIBLE' namespace='##targetNamespace'"
     * @generated
     */
    boolean isVISIBLE();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isVISIBLE <em>VISIBLE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>VISIBLE</em>' attribute.
     * @see #isSetVISIBLE()
     * @see #unsetVISIBLE()
     * @see #isVISIBLE()
     * @generated
     */
    void setVISIBLE(boolean value);

    /**
     * Unsets the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isVISIBLE <em>VISIBLE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetVISIBLE()
     * @see #isVISIBLE()
     * @see #setVISIBLE(boolean)
     * @generated
     */
    void unsetVISIBLE();

    /**
     * Returns whether the value of the '{@link org.talend.designer.core.model.utils.emf.component.HEADERType#isVISIBLE <em>VISIBLE</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>VISIBLE</em>' attribute is set.
     * @see #unsetVISIBLE()
     * @see #isVISIBLE()
     * @see #setVISIBLE(boolean)
     * @generated
     */
    boolean isSetVISIBLE();

} // HEADERType