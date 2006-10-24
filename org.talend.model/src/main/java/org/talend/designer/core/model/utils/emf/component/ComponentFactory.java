/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage
 * @generated
 */
public interface ComponentFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ComponentFactory eINSTANCE = org.talend.designer.core.model.utils.emf.component.impl.ComponentFactoryImpl.init();

    /**
     * Returns a new object of class '<em>CODEGENERATION Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CODEGENERATION Type</em>'.
     * @generated
     */
    CODEGENERATIONType createCODEGENERATIONType();

    /**
     * Returns a new object of class '<em>COMPONENT Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>COMPONENT Type</em>'.
     * @generated
     */
    COMPONENTType createCOMPONENTType();

    /**
     * Returns a new object of class '<em>CONNECTORS Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CONNECTORS Type</em>'.
     * @generated
     */
    CONNECTORSType createCONNECTORSType();

    /**
     * Returns a new object of class '<em>CONNECTOR Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CONNECTOR Type</em>'.
     * @generated
     */
    CONNECTORType createCONNECTORType();

    /**
     * Returns a new object of class '<em>DEFAULT Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>DEFAULT Type</em>'.
     * @generated
     */
    DEFAULTType createDEFAULTType();

    /**
     * Returns a new object of class '<em>DOCUMENTATION Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>DOCUMENTATION Type</em>'.
     * @generated
     */
    DOCUMENTATIONType createDOCUMENTATIONType();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>HEADER Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>HEADER Type</em>'.
     * @generated
     */
    HEADERType createHEADERType();

    /**
     * Returns a new object of class '<em>IMPORTS Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>IMPORTS Type</em>'.
     * @generated
     */
    IMPORTSType createIMPORTSType();

    /**
     * Returns a new object of class '<em>IMPORT Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>IMPORT Type</em>'.
     * @generated
     */
    IMPORTType createIMPORTType();

    /**
     * Returns a new object of class '<em>ITEMS Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>ITEMS Type</em>'.
     * @generated
     */
    ITEMSType createITEMSType();

    /**
     * Returns a new object of class '<em>ITEM Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>ITEM Type</em>'.
     * @generated
     */
    ITEMType createITEMType();

    /**
     * Returns a new object of class '<em>PARAMETERS Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>PARAMETERS Type</em>'.
     * @generated
     */
    PARAMETERSType createPARAMETERSType();

    /**
     * Returns a new object of class '<em>PARAMETER Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>PARAMETER Type</em>'.
     * @generated
     */
    PARAMETERType createPARAMETERType();

    /**
     * Returns a new object of class '<em>RETURNS Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>RETURNS Type</em>'.
     * @generated
     */
    RETURNSType createRETURNSType();

    /**
     * Returns a new object of class '<em>RETURN Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>RETURN Type</em>'.
     * @generated
     */
    RETURNType createRETURNType();

    /**
     * Returns a new object of class '<em>TEMPLATEPARAM Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>TEMPLATEPARAM Type</em>'.
     * @generated
     */
    TEMPLATEPARAMType createTEMPLATEPARAMType();

    /**
     * Returns a new object of class '<em>TEMPLATES Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>TEMPLATES Type</em>'.
     * @generated
     */
    TEMPLATESType createTEMPLATESType();

    /**
     * Returns a new object of class '<em>TEMPLATE Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>TEMPLATE Type</em>'.
     * @generated
     */
    TEMPLATEType createTEMPLATEType();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ComponentPackage getComponentPackage();

} //ComponentFactory
