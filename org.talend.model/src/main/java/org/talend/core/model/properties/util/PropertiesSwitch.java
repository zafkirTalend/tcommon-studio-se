/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.ByteArray;
import org.talend.core.model.properties.CSVFileConnectionItem;
import org.talend.core.model.properties.Component;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.DatabaseConnectionItem;
import org.talend.core.model.properties.DelimitedFileConnectionItem;
import org.talend.core.model.properties.DocumentationItem;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ItemState;
import org.talend.core.model.properties.NotationHolder;
import org.talend.core.model.properties.PositionalFileConnectionItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RegExFileConnectionItem;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.properties.UserRole;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see org.talend.core.model.properties.PropertiesPackage
 * @generated
 */
public class PropertiesSwitch {

    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static PropertiesPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PropertiesSwitch() {
        if (modelPackage == null) {
            modelPackage = PropertiesPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public Object doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch((EClass) eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case PropertiesPackage.PROJECT: {
            Project project = (Project) theEObject;
            Object result = caseProject(project);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.STATUS: {
            Status status = (Status) theEObject;
            Object result = caseStatus(status);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.ITEM_STATE: {
            ItemState itemState = (ItemState) theEObject;
            Object result = caseItemState(itemState);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.PROPERTY: {
            Property property = (Property) theEObject;
            Object result = caseProperty(property);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.ITEM: {
            Item item = (Item) theEObject;
            Object result = caseItem(item);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.BUSINESS_PROCESS_ITEM: {
            BusinessProcessItem businessProcessItem = (BusinessProcessItem) theEObject;
            Object result = caseBusinessProcessItem(businessProcessItem);
            if (result == null)
                result = caseItem(businessProcessItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.FILE_ITEM: {
            FileItem fileItem = (FileItem) theEObject;
            Object result = caseFileItem(fileItem);
            if (result == null)
                result = caseItem(fileItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.BYTE_ARRAY: {
            ByteArray byteArray = (ByteArray) theEObject;
            Object result = caseByteArray(byteArray);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.DOCUMENTATION_ITEM: {
            DocumentationItem documentationItem = (DocumentationItem) theEObject;
            Object result = caseDocumentationItem(documentationItem);
            if (result == null)
                result = caseFileItem(documentationItem);
            if (result == null)
                result = caseItem(documentationItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.ROUTINE_ITEM: {
            RoutineItem routineItem = (RoutineItem) theEObject;
            Object result = caseRoutineItem(routineItem);
            if (result == null)
                result = caseFileItem(routineItem);
            if (result == null)
                result = caseItem(routineItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.CONNECTION_ITEM: {
            ConnectionItem connectionItem = (ConnectionItem) theEObject;
            Object result = caseConnectionItem(connectionItem);
            if (result == null)
                result = caseItem(connectionItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.DELIMITED_FILE_CONNECTION_ITEM: {
            DelimitedFileConnectionItem delimitedFileConnectionItem = (DelimitedFileConnectionItem) theEObject;
            Object result = caseDelimitedFileConnectionItem(delimitedFileConnectionItem);
            if (result == null)
                result = caseConnectionItem(delimitedFileConnectionItem);
            if (result == null)
                result = caseItem(delimitedFileConnectionItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.POSITIONAL_FILE_CONNECTION_ITEM: {
            PositionalFileConnectionItem positionalFileConnectionItem = (PositionalFileConnectionItem) theEObject;
            Object result = casePositionalFileConnectionItem(positionalFileConnectionItem);
            if (result == null)
                result = caseConnectionItem(positionalFileConnectionItem);
            if (result == null)
                result = caseItem(positionalFileConnectionItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.REG_EX_FILE_CONNECTION_ITEM: {
            RegExFileConnectionItem regExFileConnectionItem = (RegExFileConnectionItem) theEObject;
            Object result = caseRegExFileConnectionItem(regExFileConnectionItem);
            if (result == null)
                result = caseConnectionItem(regExFileConnectionItem);
            if (result == null)
                result = caseItem(regExFileConnectionItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.CSV_FILE_CONNECTION_ITEM: {
            CSVFileConnectionItem csvFileConnectionItem = (CSVFileConnectionItem) theEObject;
            Object result = caseCSVFileConnectionItem(csvFileConnectionItem);
            if (result == null)
                result = caseDelimitedFileConnectionItem(csvFileConnectionItem);
            if (result == null)
                result = caseConnectionItem(csvFileConnectionItem);
            if (result == null)
                result = caseItem(csvFileConnectionItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.DATABASE_CONNECTION_ITEM: {
            DatabaseConnectionItem databaseConnectionItem = (DatabaseConnectionItem) theEObject;
            Object result = caseDatabaseConnectionItem(databaseConnectionItem);
            if (result == null)
                result = caseConnectionItem(databaseConnectionItem);
            if (result == null)
                result = caseItem(databaseConnectionItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.PROCESS_ITEM: {
            ProcessItem processItem = (ProcessItem) theEObject;
            Object result = caseProcessItem(processItem);
            if (result == null)
                result = caseItem(processItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.USER_ROLE: {
            UserRole userRole = (UserRole) theEObject;
            Object result = caseUserRole(userRole);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.USER: {
            User user = (User) theEObject;
            Object result = caseUser(user);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.FOLDER_ITEM: {
            FolderItem folderItem = (FolderItem) theEObject;
            Object result = caseFolderItem(folderItem);
            if (result == null)
                result = caseItem(folderItem);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.COMPONENT: {
            Component component = (Component) theEObject;
            Object result = caseComponent(component);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case PropertiesPackage.NOTATION_HOLDER: {
            NotationHolder notationHolder = (NotationHolder) theEObject;
            Object result = caseNotationHolder(notationHolder);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Status</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Status</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseStatus(Status object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Project</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Project</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProject(Project object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Property</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Property</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProperty(Property object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Item</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseItem(Item object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Business Process Item</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Business Process Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseBusinessProcessItem(BusinessProcessItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Item State</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Item State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseItemState(ItemState object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>File Item</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>File Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFileItem(FileItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Documentation Item</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Documentation Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDocumentationItem(DocumentationItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Routine Item</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Routine Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseRoutineItem(RoutineItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Byte Array</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Byte Array</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseByteArray(ByteArray object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Connection Item</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseConnectionItem(ConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Delimited File Connection Item</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Delimited File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDelimitedFileConnectionItem(DelimitedFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Positional File Connection Item</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Positional File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePositionalFileConnectionItem(PositionalFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Reg Ex File Connection Item</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Reg Ex File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseRegExFileConnectionItem(RegExFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>CSV File Connection Item</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>CSV File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCSVFileConnectionItem(CSVFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Database Connection Item</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Database Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDatabaseConnectionItem(DatabaseConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Process Item</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Process Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProcessItem(ProcessItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>User</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>User</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUser(User object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Folder Item</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Folder Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFolderItem(FolderItem object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Component</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseComponent(Component object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Notation Holder</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Notation Holder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseNotationHolder(NotationHolder object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>User Role</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>User Role</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUserRole(UserRole object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch, but this is the last
     * case anyway. <!-- end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public Object defaultCase(EObject object) {
        return null;
    }

} // PropertiesSwitch
