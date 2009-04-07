/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * @see org.talend.core.model.properties.PropertiesPackage
 * @generated
 */
public interface PropertiesFactory extends EFactory {

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    PropertiesFactory eINSTANCE = org.talend.core.model.properties.impl.PropertiesFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Status</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Status</em>'.
     * @generated
     */
    Status createStatus();

    /**
     * Returns a new object of class '<em>Project</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Project</em>'.
     * @generated
     */
    Project createProject();

    /**
     * Returns a new object of class '<em>Property</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Property</em>'.
     * @generated
     */
    Property createProperty();

    /**
     * Returns a new object of class '<em>Link Documentation Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Link Documentation Item</em>'.
     * @generated
     */
    LinkDocumentationItem createLinkDocumentationItem();

    /**
     * Returns a new object of class '<em>Link Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Link Type</em>'.
     * @generated
     */
    LinkType createLinkType();

    /**
     * Returns a new object of class '<em>Business Process Item</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Business Process Item</em>'.
     * @generated
     */
    BusinessProcessItem createBusinessProcessItem();

    /**
     * Returns a new object of class '<em>Item State</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Item State</em>'.
     * @generated
     */
    ItemState createItemState();

    /**
     * Returns a new object of class '<em>Documentation Item</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Documentation Item</em>'.
     * @generated
     */
    DocumentationItem createDocumentationItem();

    /**
     * Returns a new object of class '<em>Routine Item</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Routine Item</em>'.
     * @generated
     */
    RoutineItem createRoutineItem();

    /**
     * Returns a new object of class '<em>Byte Array</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Byte Array</em>'.
     * @generated
     */
    ByteArray createByteArray();

    /**
     * Returns a new object of class '<em>Connection Item</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Connection Item</em>'.
     * @generated
     */
    ConnectionItem createConnectionItem();

    /**
     * Returns a new object of class '<em>Snippet Variable</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Snippet Variable</em>'.
     * @generated
     */
    SnippetVariable createSnippetVariable();

    /**
     * Returns a new object of class '<em>Snippet Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Snippet Item</em>'.
     * @generated
     */
    SnippetItem createSnippetItem();

    /**
     * Returns a new object of class '<em>Delimited File Connection Item</em>'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return a new object of class '<em>Delimited File Connection Item</em>'.
     * @generated
     */
    DelimitedFileConnectionItem createDelimitedFileConnectionItem();

    /**
     * Returns a new object of class '<em>Positional File Connection Item</em>'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return a new object of class '<em>Positional File Connection Item</em>'.
     * @generated
     */
    PositionalFileConnectionItem createPositionalFileConnectionItem();

    /**
     * Returns a new object of class '<em>Reg Ex File Connection Item</em>'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return a new object of class '<em>Reg Ex File Connection Item</em>'.
     * @generated
     */
    RegExFileConnectionItem createRegExFileConnectionItem();

    /**
     * Returns a new object of class '<em>CSV File Connection Item</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>CSV File Connection Item</em>'.
     * @generated
     */
    CSVFileConnectionItem createCSVFileConnectionItem();

    /**
     * Returns a new object of class '<em>Database Connection Item</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Database Connection Item</em>'.
     * @generated
     */
    DatabaseConnectionItem createDatabaseConnectionItem();

    /**
     * Returns a new object of class '<em>SAP Connection Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>SAP Connection Item</em>'.
     * @generated
     */
    SAPConnectionItem createSAPConnectionItem();

    /**
     * Returns a new object of class '<em>Process Item</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Process Item</em>'.
     * @generated
     */
    ProcessItem createProcessItem();

    /**
     * Returns a new object of class '<em>User</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>User</em>'.
     * @generated
     */
    User createUser();

    /**
     * Returns a new object of class '<em>Folder Item</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Folder Item</em>'.
     * @generated
     */
    FolderItem createFolderItem();

    /**
     * Returns a new object of class '<em>Component</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Component</em>'.
     * @generated
     */
    Component createComponent();

    /**
     * Returns a new object of class '<em>Xml File Connection Item</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Xml File Connection Item</em>'.
     * @generated
     */
    XmlFileConnectionItem createXmlFileConnectionItem();

    /**
     * Returns a new object of class '<em>Notation Holder</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Notation Holder</em>'.
     * @generated
     */
    NotationHolder createNotationHolder();

    /**
     * Returns a new object of class '<em>Project Component Authorisation</em>'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return a new object of class '<em>Project Component Authorisation</em>'.
     * @generated
     */
    ProjectComponentAuthorisation createProjectComponentAuthorisation();

    /**
     * Returns a new object of class '<em>Project Reference</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Project Reference</em>'.
     * @generated
     */
    ProjectReference createProjectReference();

    /**
     * Returns a new object of class '<em>Ldif File Connection Item</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Ldif File Connection Item</em>'.
     * @generated
     */
    LdifFileConnectionItem createLdifFileConnectionItem();

    /**
     * Returns a new object of class '<em>Excel File Connection Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Excel File Connection Item</em>'.
     * @generated
     */
    ExcelFileConnectionItem createExcelFileConnectionItem();

    /**
     * Returns a new object of class '<em>Ebcdic Connection Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Ebcdic Connection Item</em>'.
     * @generated
     */
    EbcdicConnectionItem createEbcdicConnectionItem();

    /**
     * Returns a new object of class '<em>User Project Authorization</em>'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return a new object of class '<em>User Project Authorization</em>'.
     * @generated
     */
    UserProjectAuthorization createUserProjectAuthorization();

    /**
     * Returns a new object of class '<em>Context Item</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Context Item</em>'.
     * @generated
     */
    ContextItem createContextItem();

    /**
     * Returns a new object of class '<em>Spago Bi Server</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Spago Bi Server</em>'.
     * @generated
     */
    SpagoBiServer createSpagoBiServer();

    /**
     * Returns a new object of class '<em>License</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>License</em>'.
     * @generated
     */
    License createLicense();

    /**
     * Returns a new object of class '<em>Generic Schema Connection Item</em>'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return a new object of class '<em>Generic Schema Connection Item</em>'.
     * @generated
     */
    GenericSchemaConnectionItem createGenericSchemaConnectionItem();

    /**
     * Returns a new object of class '<em>User Module Authorization</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>User Module Authorization</em>'.
     * @generated
     */
    UserModuleAuthorization createUserModuleAuthorization();

    /**
     * Returns a new object of class '<em>LDAP Schema Connection Item</em>'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return a new object of class '<em>LDAP Schema Connection Item</em>'.
     * @generated
     */
    LDAPSchemaConnectionItem createLDAPSchemaConnectionItem();

    /**
     * Returns a new object of class '<em>Salesforce Schema Connection Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Salesforce Schema Connection Item</em>'.
     * @generated
     */
    SalesforceSchemaConnectionItem createSalesforceSchemaConnectionItem();

    /**
     * Returns a new object of class '<em>Dashboard Connection</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Dashboard Connection</em>'.
     * @generated
     */
    DashboardConnection createDashboardConnection();

    /**
     * Returns a new object of class '<em>Execution Server</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Execution Server</em>'.
     * @generated
     */
    ExecutionServer createExecutionServer();

    /**
     * Returns a new object of class '<em>Execution Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Execution Task</em>'.
     * @generated
     */
    ExecutionTask createExecutionTask();

    /**
     * Returns a new object of class '<em>Execution Task Cmd Prm</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Execution Task Cmd Prm</em>'.
     * @generated
     */
    ExecutionTaskCmdPrm createExecutionTaskCmdPrm();

    /**
     * Returns a new object of class '<em>Execution Task Job Prm</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Execution Task Job Prm</em>'.
     * @generated
     */
    ExecutionTaskJobPrm createExecutionTaskJobPrm();

    /**
     * Returns a new object of class '<em>Talend Trigger</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Talend Trigger</em>'.
     * @generated
     */
    TalendTrigger createTalendTrigger();

    /**
     * Returns a new object of class '<em>Cron Talend Trigger</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Cron Talend Trigger</em>'.
     * @generated
     */
    CronTalendTrigger createCronTalendTrigger();

    /**
     * Returns a new object of class '<em>Cron UI Talend Trigger</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Cron UI Talend Trigger</em>'.
     * @generated
     */
    CronUITalendTrigger createCronUITalendTrigger();

    /**
     * Returns a new object of class '<em>Simple Talend Trigger</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Simple Talend Trigger</em>'.
     * @generated
     */
    SimpleTalendTrigger createSimpleTalendTrigger();

    /**
     * Returns a new object of class '<em>Execution Virtual Server</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Execution Virtual Server</em>'.
     * @generated
     */
    ExecutionVirtualServer createExecutionVirtualServer();

    /**
     * Returns a new object of class '<em>File Trigger</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>File Trigger</em>'.
     * @generated
     */
    FileTrigger createFileTrigger();

    /**
     * Returns a new object of class '<em>File Trigger Mask</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>File Trigger Mask</em>'.
     * @generated
     */
    FileTriggerMask createFileTriggerMask();

    /**
     * Returns a new object of class '<em>Joblet Process Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Joblet Process Item</em>'.
     * @generated
     */
    JobletProcessItem createJobletProcessItem();

    /**
     * Returns a new object of class '<em>Job Documentation Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Job Documentation Item</em>'.
     * @generated
     */
    JobDocumentationItem createJobDocumentationItem();

    /**
     * Returns a new object of class '<em>Joblet Documentation Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Joblet Documentation Item</em>'.
     * @generated
     */
    JobletDocumentationItem createJobletDocumentationItem();

    /**
     * Returns a new object of class '<em>WSDL Schema Connection Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>WSDL Schema Connection Item</em>'.
     * @generated
     */
    WSDLSchemaConnectionItem createWSDLSchemaConnectionItem();

    /**
     * Returns a new object of class '<em>Schema Information</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Schema Information</em>'.
     * @generated
     */
    SchemaInformation createSchemaInformation();

    /**
     * Returns a new object of class '<em>Information</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Information</em>'.
     * @generated
     */
    Information createInformation();

    /**
     * Returns a new object of class '<em>SQL Pattern Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>SQL Pattern Item</em>'.
     * @generated
     */
    SQLPatternItem createSQLPatternItem();

    /**
     * Returns a new object of class '<em>Component Setting</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Component Setting</em>'.
     * @generated
     */
    ComponentSetting createComponentSetting();

    /**
     * Returns a new object of class '<em>Stat And Logs Settings</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Stat And Logs Settings</em>'.
     * @generated
     */
    StatAndLogsSettings createStatAndLogsSettings();

    /**
     * Returns a new object of class '<em>Implicit Context Settings</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Implicit Context Settings</em>'.
     * @generated
     */
    ImplicitContextSettings createImplicitContextSettings();

    /**
     * Returns a new object of class '<em>Soa Operation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Soa Operation</em>'.
     * @generated
     */
    SoaOperation createSoaOperation();

    /**
     * Returns a new object of class '<em>Soa Input Parameter</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Soa Input Parameter</em>'.
     * @generated
     */
    SoaInputParameter createSoaInputParameter();

    /**
     * Returns a new object of class '<em>Soa Service</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Soa Service</em>'.
     * @generated
     */
    SoaService createSoaService();

    /**
     * Returns a new object of class '<em>Rules Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Rules Item</em>'.
     * @generated
     */
    RulesItem createRulesItem();

    /**
     * Returns a new object of class '<em>Task Execution History</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Task Execution History</em>'.
     * @generated
     */
    TaskExecutionHistory createTaskExecutionHistory();

    /**
     * Returns a new object of class '<em>User Role</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>User Role</em>'.
     * @generated
     */
    UserRole createUserRole();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    PropertiesPackage getPropertiesPackage();

} // PropertiesFactory
