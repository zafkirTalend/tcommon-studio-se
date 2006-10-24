/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.talend.designer.core.model.utils.emf.component.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.talend.designer.core.model.utils.emf.component.ComponentPackage
 * @generated
 */
public class ComponentSwitch {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ComponentPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentSwitch() {
        if (modelPackage == null) {
            modelPackage = ComponentPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public Object doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch((EClass)eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case ComponentPackage.CODEGENERATION_TYPE: {
                CODEGENERATIONType codegenerationType = (CODEGENERATIONType)theEObject;
                Object result = caseCODEGENERATIONType(codegenerationType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.COMPONENT_TYPE: {
                COMPONENTType componentType = (COMPONENTType)theEObject;
                Object result = caseCOMPONENTType(componentType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.CONNECTORS_TYPE: {
                CONNECTORSType connectorsType = (CONNECTORSType)theEObject;
                Object result = caseCONNECTORSType(connectorsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.CONNECTOR_TYPE: {
                CONNECTORType connectorType = (CONNECTORType)theEObject;
                Object result = caseCONNECTORType(connectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.DEFAULT_TYPE: {
                DEFAULTType defaultType = (DEFAULTType)theEObject;
                Object result = caseDEFAULTType(defaultType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.DOCUMENTATION_TYPE: {
                DOCUMENTATIONType documentationType = (DOCUMENTATIONType)theEObject;
                Object result = caseDOCUMENTATIONType(documentationType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                Object result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.HEADER_TYPE: {
                HEADERType headerType = (HEADERType)theEObject;
                Object result = caseHEADERType(headerType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.IMPORTS_TYPE: {
                IMPORTSType importsType = (IMPORTSType)theEObject;
                Object result = caseIMPORTSType(importsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.IMPORT_TYPE: {
                IMPORTType importType = (IMPORTType)theEObject;
                Object result = caseIMPORTType(importType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.ITEMS_TYPE: {
                ITEMSType itemsType = (ITEMSType)theEObject;
                Object result = caseITEMSType(itemsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.ITEM_TYPE: {
                ITEMType itemType = (ITEMType)theEObject;
                Object result = caseITEMType(itemType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.PARAMETERS_TYPE: {
                PARAMETERSType parametersType = (PARAMETERSType)theEObject;
                Object result = casePARAMETERSType(parametersType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.PARAMETER_TYPE: {
                PARAMETERType parameterType = (PARAMETERType)theEObject;
                Object result = casePARAMETERType(parameterType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.RETURNS_TYPE: {
                RETURNSType returnsType = (RETURNSType)theEObject;
                Object result = caseRETURNSType(returnsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.RETURN_TYPE: {
                RETURNType returnType = (RETURNType)theEObject;
                Object result = caseRETURNType(returnType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.TEMPLATEPARAM_TYPE: {
                TEMPLATEPARAMType templateparamType = (TEMPLATEPARAMType)theEObject;
                Object result = caseTEMPLATEPARAMType(templateparamType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.TEMPLATES_TYPE: {
                TEMPLATESType templatesType = (TEMPLATESType)theEObject;
                Object result = caseTEMPLATESType(templatesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentPackage.TEMPLATE_TYPE: {
                TEMPLATEType templateType = (TEMPLATEType)theEObject;
                Object result = caseTEMPLATEType(templateType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>CODEGENERATION Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>CODEGENERATION Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCODEGENERATIONType(CODEGENERATIONType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>COMPONENT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>COMPONENT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCOMPONENTType(COMPONENTType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>CONNECTORS Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>CONNECTORS Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCONNECTORSType(CONNECTORSType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>CONNECTOR Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>CONNECTOR Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCONNECTORType(CONNECTORType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>DEFAULT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>DEFAULT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDEFAULTType(DEFAULTType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>DOCUMENTATION Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>DOCUMENTATION Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDOCUMENTATIONType(DOCUMENTATIONType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDocumentRoot(DocumentRoot object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>HEADER Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>HEADER Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseHEADERType(HEADERType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IMPORTS Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IMPORTS Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIMPORTSType(IMPORTSType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IMPORT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IMPORT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIMPORTType(IMPORTType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>ITEMS Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>ITEMS Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseITEMSType(ITEMSType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>ITEM Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>ITEM Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseITEMType(ITEMType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>PARAMETERS Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>PARAMETERS Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePARAMETERSType(PARAMETERSType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>PARAMETER Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>PARAMETER Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePARAMETERType(PARAMETERType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>RETURNS Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>RETURNS Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseRETURNSType(RETURNSType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>RETURN Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>RETURN Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseRETURNType(RETURNType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>TEMPLATEPARAM Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>TEMPLATEPARAM Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTEMPLATEPARAMType(TEMPLATEPARAMType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>TEMPLATES Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>TEMPLATES Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTEMPLATESType(TEMPLATESType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>TEMPLATE Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>TEMPLATE Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTEMPLATEType(TEMPLATEType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public Object defaultCase(EObject object) {
        return null;
    }

} //ComponentSwitch
