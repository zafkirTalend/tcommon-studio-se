/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.talend.core.model.properties.*;

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
 * @see org.talend.core.model.properties.PropertiesPackage
 * @generated
 */
public class PropertiesSwitch {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static PropertiesPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PropertiesSwitch() {
        if (modelPackage == null) {
            modelPackage = PropertiesPackage.eINSTANCE;
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
            case PropertiesPackage.PROJECT: {
                Project project = (Project)theEObject;
                Object result = caseProject(project);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.PROJECT_COMPONENT_AUTHORISATION: {
                ProjectComponentAuthorisation projectComponentAuthorisation = (ProjectComponentAuthorisation)theEObject;
                Object result = caseProjectComponentAuthorisation(projectComponentAuthorisation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.PROJECT_REFERENCE: {
                ProjectReference projectReference = (ProjectReference)theEObject;
                Object result = caseProjectReference(projectReference);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.STATUS: {
                Status status = (Status)theEObject;
                Object result = caseStatus(status);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.ITEM_STATE: {
                ItemState itemState = (ItemState)theEObject;
                Object result = caseItemState(itemState);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.PROPERTY: {
                Property property = (Property)theEObject;
                Object result = caseProperty(property);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.ITEM: {
                Item item = (Item)theEObject;
                Object result = caseItem(item);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.LINK_DOCUMENTATION_ITEM: {
                LinkDocumentationItem linkDocumentationItem = (LinkDocumentationItem)theEObject;
                Object result = caseLinkDocumentationItem(linkDocumentationItem);
                if (result == null) result = caseItem(linkDocumentationItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.LINK_TYPE: {
                LinkType linkType = (LinkType)theEObject;
                Object result = caseLinkType(linkType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.BUSINESS_PROCESS_ITEM: {
                BusinessProcessItem businessProcessItem = (BusinessProcessItem)theEObject;
                Object result = caseBusinessProcessItem(businessProcessItem);
                if (result == null) result = caseItem(businessProcessItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.FILE_ITEM: {
                FileItem fileItem = (FileItem)theEObject;
                Object result = caseFileItem(fileItem);
                if (result == null) result = caseItem(fileItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.BYTE_ARRAY: {
                ByteArray byteArray = (ByteArray)theEObject;
                Object result = caseByteArray(byteArray);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.DOCUMENTATION_ITEM: {
                DocumentationItem documentationItem = (DocumentationItem)theEObject;
                Object result = caseDocumentationItem(documentationItem);
                if (result == null) result = caseFileItem(documentationItem);
                if (result == null) result = caseItem(documentationItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.ROUTINE_ITEM: {
                RoutineItem routineItem = (RoutineItem)theEObject;
                Object result = caseRoutineItem(routineItem);
                if (result == null) result = caseFileItem(routineItem);
                if (result == null) result = caseItem(routineItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.CONNECTION_ITEM: {
                ConnectionItem connectionItem = (ConnectionItem)theEObject;
                Object result = caseConnectionItem(connectionItem);
                if (result == null) result = caseItem(connectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.SNIPPET_VARIABLE: {
                SnippetVariable snippetVariable = (SnippetVariable)theEObject;
                Object result = caseSnippetVariable(snippetVariable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.SNIPPET_ITEM: {
                SnippetItem snippetItem = (SnippetItem)theEObject;
                Object result = caseSnippetItem(snippetItem);
                if (result == null) result = caseItem(snippetItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.DELIMITED_FILE_CONNECTION_ITEM: {
                DelimitedFileConnectionItem delimitedFileConnectionItem = (DelimitedFileConnectionItem)theEObject;
                Object result = caseDelimitedFileConnectionItem(delimitedFileConnectionItem);
                if (result == null) result = caseConnectionItem(delimitedFileConnectionItem);
                if (result == null) result = caseItem(delimitedFileConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.POSITIONAL_FILE_CONNECTION_ITEM: {
                PositionalFileConnectionItem positionalFileConnectionItem = (PositionalFileConnectionItem)theEObject;
                Object result = casePositionalFileConnectionItem(positionalFileConnectionItem);
                if (result == null) result = caseConnectionItem(positionalFileConnectionItem);
                if (result == null) result = caseItem(positionalFileConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.REG_EX_FILE_CONNECTION_ITEM: {
                RegExFileConnectionItem regExFileConnectionItem = (RegExFileConnectionItem)theEObject;
                Object result = caseRegExFileConnectionItem(regExFileConnectionItem);
                if (result == null) result = caseConnectionItem(regExFileConnectionItem);
                if (result == null) result = caseItem(regExFileConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.CSV_FILE_CONNECTION_ITEM: {
                CSVFileConnectionItem csvFileConnectionItem = (CSVFileConnectionItem)theEObject;
                Object result = caseCSVFileConnectionItem(csvFileConnectionItem);
                if (result == null) result = caseDelimitedFileConnectionItem(csvFileConnectionItem);
                if (result == null) result = caseConnectionItem(csvFileConnectionItem);
                if (result == null) result = caseItem(csvFileConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.DATABASE_CONNECTION_ITEM: {
                DatabaseConnectionItem databaseConnectionItem = (DatabaseConnectionItem)theEObject;
                Object result = caseDatabaseConnectionItem(databaseConnectionItem);
                if (result == null) result = caseConnectionItem(databaseConnectionItem);
                if (result == null) result = caseItem(databaseConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.SAP_CONNECTION_ITEM: {
                SAPConnectionItem sapConnectionItem = (SAPConnectionItem)theEObject;
                Object result = caseSAPConnectionItem(sapConnectionItem);
                if (result == null) result = caseConnectionItem(sapConnectionItem);
                if (result == null) result = caseItem(sapConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.XML_FILE_CONNECTION_ITEM: {
                XmlFileConnectionItem xmlFileConnectionItem = (XmlFileConnectionItem)theEObject;
                Object result = caseXmlFileConnectionItem(xmlFileConnectionItem);
                if (result == null) result = caseConnectionItem(xmlFileConnectionItem);
                if (result == null) result = caseItem(xmlFileConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.LDIF_FILE_CONNECTION_ITEM: {
                LdifFileConnectionItem ldifFileConnectionItem = (LdifFileConnectionItem)theEObject;
                Object result = caseLdifFileConnectionItem(ldifFileConnectionItem);
                if (result == null) result = caseConnectionItem(ldifFileConnectionItem);
                if (result == null) result = caseItem(ldifFileConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.EXCEL_FILE_CONNECTION_ITEM: {
                ExcelFileConnectionItem excelFileConnectionItem = (ExcelFileConnectionItem)theEObject;
                Object result = caseExcelFileConnectionItem(excelFileConnectionItem);
                if (result == null) result = caseConnectionItem(excelFileConnectionItem);
                if (result == null) result = caseItem(excelFileConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.EBCDIC_CONNECTION_ITEM: {
                EbcdicConnectionItem ebcdicConnectionItem = (EbcdicConnectionItem)theEObject;
                Object result = caseEbcdicConnectionItem(ebcdicConnectionItem);
                if (result == null) result = caseConnectionItem(ebcdicConnectionItem);
                if (result == null) result = caseItem(ebcdicConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.PROCESS_ITEM: {
                ProcessItem processItem = (ProcessItem)theEObject;
                Object result = caseProcessItem(processItem);
                if (result == null) result = caseItem(processItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.USER_ROLE: {
                UserRole userRole = (UserRole)theEObject;
                Object result = caseUserRole(userRole);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.USER: {
                User user = (User)theEObject;
                Object result = caseUser(user);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.FOLDER_ITEM: {
                FolderItem folderItem = (FolderItem)theEObject;
                Object result = caseFolderItem(folderItem);
                if (result == null) result = caseItem(folderItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.COMPONENT: {
                Component component = (Component)theEObject;
                Object result = caseComponent(component);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.NOTATION_HOLDER: {
                NotationHolder notationHolder = (NotationHolder)theEObject;
                Object result = caseNotationHolder(notationHolder);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.USER_PROJECT_AUTHORIZATION: {
                UserProjectAuthorization userProjectAuthorization = (UserProjectAuthorization)theEObject;
                Object result = caseUserProjectAuthorization(userProjectAuthorization);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.CONTEXT_ITEM: {
                ContextItem contextItem = (ContextItem)theEObject;
                Object result = caseContextItem(contextItem);
                if (result == null) result = caseItem(contextItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.SPAGO_BI_SERVER: {
                SpagoBiServer spagoBiServer = (SpagoBiServer)theEObject;
                Object result = caseSpagoBiServer(spagoBiServer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.LICENSE: {
                License license = (License)theEObject;
                Object result = caseLicense(license);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.GENERIC_SCHEMA_CONNECTION_ITEM: {
                GenericSchemaConnectionItem genericSchemaConnectionItem = (GenericSchemaConnectionItem)theEObject;
                Object result = caseGenericSchemaConnectionItem(genericSchemaConnectionItem);
                if (result == null) result = caseConnectionItem(genericSchemaConnectionItem);
                if (result == null) result = caseItem(genericSchemaConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.USER_MODULE_AUTHORIZATION: {
                UserModuleAuthorization userModuleAuthorization = (UserModuleAuthorization)theEObject;
                Object result = caseUserModuleAuthorization(userModuleAuthorization);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.LDAP_SCHEMA_CONNECTION_ITEM: {
                LDAPSchemaConnectionItem ldapSchemaConnectionItem = (LDAPSchemaConnectionItem)theEObject;
                Object result = caseLDAPSchemaConnectionItem(ldapSchemaConnectionItem);
                if (result == null) result = caseConnectionItem(ldapSchemaConnectionItem);
                if (result == null) result = caseItem(ldapSchemaConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.SALESFORCE_SCHEMA_CONNECTION_ITEM: {
                SalesforceSchemaConnectionItem salesforceSchemaConnectionItem = (SalesforceSchemaConnectionItem)theEObject;
                Object result = caseSalesforceSchemaConnectionItem(salesforceSchemaConnectionItem);
                if (result == null) result = caseConnectionItem(salesforceSchemaConnectionItem);
                if (result == null) result = caseItem(salesforceSchemaConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.DASHBOARD_CONNECTION: {
                DashboardConnection dashboardConnection = (DashboardConnection)theEObject;
                Object result = caseDashboardConnection(dashboardConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.EXECUTION_SERVER: {
                ExecutionServer executionServer = (ExecutionServer)theEObject;
                Object result = caseExecutionServer(executionServer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.EXECUTION_TASK: {
                ExecutionTask executionTask = (ExecutionTask)theEObject;
                Object result = caseExecutionTask(executionTask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.TASK_EXECUTION_HISTORY: {
                TaskExecutionHistory taskExecutionHistory = (TaskExecutionHistory)theEObject;
                Object result = caseTaskExecutionHistory(taskExecutionHistory);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.TALEND_TRIGGER: {
                TalendTrigger talendTrigger = (TalendTrigger)theEObject;
                Object result = caseTalendTrigger(talendTrigger);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.CRON_TALEND_TRIGGER: {
                CronTalendTrigger cronTalendTrigger = (CronTalendTrigger)theEObject;
                Object result = caseCronTalendTrigger(cronTalendTrigger);
                if (result == null) result = caseTalendTrigger(cronTalendTrigger);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.CRON_UI_TALEND_TRIGGER: {
                CronUITalendTrigger cronUITalendTrigger = (CronUITalendTrigger)theEObject;
                Object result = caseCronUITalendTrigger(cronUITalendTrigger);
                if (result == null) result = caseTalendTrigger(cronUITalendTrigger);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.SIMPLE_TALEND_TRIGGER: {
                SimpleTalendTrigger simpleTalendTrigger = (SimpleTalendTrigger)theEObject;
                Object result = caseSimpleTalendTrigger(simpleTalendTrigger);
                if (result == null) result = caseTalendTrigger(simpleTalendTrigger);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.EXECUTION_VIRTUAL_SERVER: {
                ExecutionVirtualServer executionVirtualServer = (ExecutionVirtualServer)theEObject;
                Object result = caseExecutionVirtualServer(executionVirtualServer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.FILE_TRIGGER: {
                FileTrigger fileTrigger = (FileTrigger)theEObject;
                Object result = caseFileTrigger(fileTrigger);
                if (result == null) result = caseSimpleTalendTrigger(fileTrigger);
                if (result == null) result = caseTalendTrigger(fileTrigger);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.FILE_TRIGGER_MASK: {
                FileTriggerMask fileTriggerMask = (FileTriggerMask)theEObject;
                Object result = caseFileTriggerMask(fileTriggerMask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.JOBLET_PROCESS_ITEM: {
                JobletProcessItem jobletProcessItem = (JobletProcessItem)theEObject;
                Object result = caseJobletProcessItem(jobletProcessItem);
                if (result == null) result = caseItem(jobletProcessItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.JOB_DOCUMENTATION_ITEM: {
                JobDocumentationItem jobDocumentationItem = (JobDocumentationItem)theEObject;
                Object result = caseJobDocumentationItem(jobDocumentationItem);
                if (result == null) result = caseFileItem(jobDocumentationItem);
                if (result == null) result = caseItem(jobDocumentationItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.JOBLET_DOCUMENTATION_ITEM: {
                JobletDocumentationItem jobletDocumentationItem = (JobletDocumentationItem)theEObject;
                Object result = caseJobletDocumentationItem(jobletDocumentationItem);
                if (result == null) result = caseFileItem(jobletDocumentationItem);
                if (result == null) result = caseItem(jobletDocumentationItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.WSDL_SCHEMA_CONNECTION_ITEM: {
                WSDLSchemaConnectionItem wsdlSchemaConnectionItem = (WSDLSchemaConnectionItem)theEObject;
                Object result = caseWSDLSchemaConnectionItem(wsdlSchemaConnectionItem);
                if (result == null) result = caseConnectionItem(wsdlSchemaConnectionItem);
                if (result == null) result = caseItem(wsdlSchemaConnectionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.SCHEMA_INFORMATION: {
                SchemaInformation schemaInformation = (SchemaInformation)theEObject;
                Object result = caseSchemaInformation(schemaInformation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.INFORMATION: {
                Information information = (Information)theEObject;
                Object result = caseInformation(information);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.SQL_PATTERN_ITEM: {
                SQLPatternItem sqlPatternItem = (SQLPatternItem)theEObject;
                Object result = caseSQLPatternItem(sqlPatternItem);
                if (result == null) result = caseFileItem(sqlPatternItem);
                if (result == null) result = caseItem(sqlPatternItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PropertiesPackage.COMPONENT_SETTING: {
                ComponentSetting componentSetting = (ComponentSetting)theEObject;
                Object result = caseComponentSetting(componentSetting);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Project</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Project</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProject(Project object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Project Component Authorisation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Project Component Authorisation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProjectComponentAuthorisation(ProjectComponentAuthorisation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Project Reference</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Project Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProjectReference(ProjectReference object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Status</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Status</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseStatus(Status object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Item State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Item State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseItemState(ItemState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Property</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProperty(Property object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseItem(Item object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Link Documentation Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link Documentation Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLinkDocumentationItem(LinkDocumentationItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Link Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLinkType(LinkType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Business Process Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Business Process Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseBusinessProcessItem(BusinessProcessItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>File Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>File Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFileItem(FileItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Byte Array</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Byte Array</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseByteArray(ByteArray object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Documentation Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Documentation Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDocumentationItem(DocumentationItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Routine Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Routine Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseRoutineItem(RoutineItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseConnectionItem(ConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Snippet Variable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Snippet Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSnippetVariable(SnippetVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Snippet Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Snippet Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSnippetItem(SnippetItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delimited File Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delimited File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDelimitedFileConnectionItem(DelimitedFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Positional File Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Positional File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePositionalFileConnectionItem(PositionalFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Reg Ex File Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reg Ex File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseRegExFileConnectionItem(RegExFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CSV File Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CSV File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCSVFileConnectionItem(CSVFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Database Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Database Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDatabaseConnectionItem(DatabaseConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SAP Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SAP Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSAPConnectionItem(SAPConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Xml File Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Xml File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXmlFileConnectionItem(XmlFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ldif File Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ldif File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLdifFileConnectionItem(LdifFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Excel File Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Excel File Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseExcelFileConnectionItem(ExcelFileConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ebcdic Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ebcdic Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseEbcdicConnectionItem(EbcdicConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Process Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Process Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProcessItem(ProcessItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Role</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Role</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUserRole(UserRole object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUser(User object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Folder Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Folder Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFolderItem(FolderItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseComponent(Component object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Notation Holder</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Notation Holder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseNotationHolder(NotationHolder object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Project Authorization</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Project Authorization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUserProjectAuthorization(UserProjectAuthorization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Context Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Context Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseContextItem(ContextItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Spago Bi Server</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Spago Bi Server</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSpagoBiServer(SpagoBiServer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>License</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>License</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLicense(License object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Generic Schema Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Generic Schema Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGenericSchemaConnectionItem(GenericSchemaConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Module Authorization</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Module Authorization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUserModuleAuthorization(UserModuleAuthorization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>LDAP Schema Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>LDAP Schema Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLDAPSchemaConnectionItem(LDAPSchemaConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Salesforce Schema Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Salesforce Schema Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSalesforceSchemaConnectionItem(SalesforceSchemaConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dashboard Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dashboard Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDashboardConnection(DashboardConnection object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Execution Server</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Execution Server</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseExecutionServer(ExecutionServer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Execution Task</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Execution Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseExecutionTask(ExecutionTask object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Talend Trigger</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Talend Trigger</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTalendTrigger(TalendTrigger object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Cron Talend Trigger</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cron Talend Trigger</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCronTalendTrigger(CronTalendTrigger object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Cron UI Talend Trigger</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cron UI Talend Trigger</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCronUITalendTrigger(CronUITalendTrigger object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Simple Talend Trigger</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Simple Talend Trigger</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSimpleTalendTrigger(SimpleTalendTrigger object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Execution Virtual Server</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Execution Virtual Server</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseExecutionVirtualServer(ExecutionVirtualServer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>File Trigger</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>File Trigger</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFileTrigger(FileTrigger object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>File Trigger Mask</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>File Trigger Mask</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFileTriggerMask(FileTriggerMask object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Joblet Process Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Joblet Process Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseJobletProcessItem(JobletProcessItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Job Documentation Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Job Documentation Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseJobDocumentationItem(JobDocumentationItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Joblet Documentation Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Joblet Documentation Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseJobletDocumentationItem(JobletDocumentationItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>WSDL Schema Connection Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>WSDL Schema Connection Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseWSDLSchemaConnectionItem(WSDLSchemaConnectionItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Schema Information</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Schema Information</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSchemaInformation(SchemaInformation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Information</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Information</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseInformation(Information object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SQL Pattern Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Pattern Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLPatternItem(SQLPatternItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component Setting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseComponentSetting(ComponentSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Task Execution History</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Task Execution History</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTaskExecutionHistory(TaskExecutionHistory object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public Object defaultCase(EObject object) {
        return null;
    }

} //PropertiesSwitch
