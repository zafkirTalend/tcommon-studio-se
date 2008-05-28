/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.talend.core.model.properties.PropertiesFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiesPackage extends EPackage {

    /**
     * The package name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "properties"; //$NON-NLS-1$

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.talend.org/properties"; //$NON-NLS-1$

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "TalendProperties"; //$NON-NLS-1$

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    PropertiesPackage eINSTANCE = org.talend.core.model.properties.impl.PropertiesPackageImpl.init();

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.StatusImpl <em>Status</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.StatusImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getStatus()
     * @generated
     */
    int STATUS = 3;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ProjectImpl <em>Project</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ProjectImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProject()
     * @generated
     */
    int PROJECT = 0;

    /**
     * The feature id for the '<em><b>Technical Status</b></em>' containment reference list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__TECHNICAL_STATUS = 0;

    /**
     * The feature id for the '<em><b>Documentation Status</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT__DOCUMENTATION_STATUS = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__ID = 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__LABEL = 3;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT__DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Language</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__LANGUAGE = 5;

    /**
     * The feature id for the '<em><b>Technical Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__TECHNICAL_LABEL = 6;

    /**
     * The feature id for the '<em><b>Local</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__LOCAL = 7;

    /**
     * The feature id for the '<em><b>Folders</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__FOLDERS = 8;

    /**
     * The feature id for the '<em><b>Deleted</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__DELETED = 9;

    /**
     * The feature id for the '<em><b>Delete Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT__DELETE_DATE = 10;

    /**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT__CREATION_DATE = 11;

    /**
     * The feature id for the '<em><b>Author</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__AUTHOR = 12;

    /**
     * The feature id for the '<em><b>User Authorization</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__USER_AUTHORIZATION = 13;

    /**
     * The feature id for the '<em><b>Allowed Components</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__ALLOWED_COMPONENTS = 14;

    /**
     * The feature id for the '<em><b>Referenced Projects</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__REFERENCED_PROJECTS = 15;

    /**
     * The feature id for the '<em><b>Available Ref Project</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__AVAILABLE_REF_PROJECT = 16;

    /**
     * The feature id for the '<em><b>Migration Tasks</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__MIGRATION_TASKS = 17;

    /**
     * The feature id for the '<em><b>Master Job Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__MASTER_JOB_ID = 18;

    /**
     * The feature id for the '<em><b>Spago Bi Server</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__SPAGO_BI_SERVER = 19;

    /**
     * The feature id for the '<em><b>Product Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__PRODUCT_VERSION = 20;

    /**
     * The feature id for the '<em><b>Components Settings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__COMPONENTS_SETTINGS = 21;

    /**
     * The number of structural features of the '<em>Project</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_FEATURE_COUNT = 22;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.PropertyImpl <em>Property</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.PropertyImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProperty()
     * @generated
     */
    int PROPERTY = 5;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ItemImpl <em>Item</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.talend.core.model.properties.impl.ItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getItem()
     * @generated
     */
    int ITEM = 6;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.BusinessProcessItemImpl <em>Business Process Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.BusinessProcessItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getBusinessProcessItem()
     * @generated
     */
    int BUSINESS_PROCESS_ITEM = 9;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ItemStateImpl <em>Item State</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ItemStateImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getItemState()
     * @generated
     */
    int ITEM_STATE = 4;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.FileItemImpl <em>File Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.FileItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFileItem()
     * @generated
     */
    int FILE_ITEM = 10;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.DocumentationItemImpl <em>Documentation Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.DocumentationItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDocumentationItem()
     * @generated
     */
    int DOCUMENTATION_ITEM = 12;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.RoutineItemImpl <em>Routine Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.RoutineItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getRoutineItem()
     * @generated
     */
    int ROUTINE_ITEM = 13;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ByteArrayImpl <em>Byte Array</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ByteArrayImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getByteArray()
     * @generated
     */
    int BYTE_ARRAY = 11;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ConnectionItemImpl <em>Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getConnectionItem()
     * @generated
     */
    int CONNECTION_ITEM = 14;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.DelimitedFileConnectionItemImpl <em>Delimited File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.DelimitedFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDelimitedFileConnectionItem()
     * @generated
     */
    int DELIMITED_FILE_CONNECTION_ITEM = 17;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.PositionalFileConnectionItemImpl <em>Positional File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.PositionalFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getPositionalFileConnectionItem()
     * @generated
     */
    int POSITIONAL_FILE_CONNECTION_ITEM = 18;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.RegExFileConnectionItemImpl <em>Reg Ex File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.RegExFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getRegExFileConnectionItem()
     * @generated
     */
    int REG_EX_FILE_CONNECTION_ITEM = 19;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.CSVFileConnectionItemImpl <em>CSV File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.CSVFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getCSVFileConnectionItem()
     * @generated
     */
    int CSV_FILE_CONNECTION_ITEM = 20;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.DatabaseConnectionItemImpl <em>Database Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.DatabaseConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDatabaseConnectionItem()
     * @generated
     */
    int DATABASE_CONNECTION_ITEM = 21;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ProcessItemImpl <em>Process Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ProcessItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProcessItem()
     * @generated
     */
    int PROCESS_ITEM = 25;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.UserImpl <em>User</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.talend.core.model.properties.impl.UserImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUser()
     * @generated
     */
    int USER = 27;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.FolderItemImpl <em>Folder Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.FolderItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFolderItem()
     * @generated
     */
    int FOLDER_ITEM = 28;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.UserRoleImpl <em>User Role</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.UserRoleImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserRole()
     * @generated
     */
    int USER_ROLE = 26;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ComponentImpl <em>Component</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ComponentImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getComponent()
     * @generated
     */
    int COMPONENT = 29;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.XmlFileConnectionItemImpl <em>Xml File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.XmlFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getXmlFileConnectionItem()
     * @generated
     */
    int XML_FILE_CONNECTION_ITEM = 22;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.NotationHolderImpl <em>Notation Holder</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.NotationHolderImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getNotationHolder()
     * @generated
     */
    int NOTATION_HOLDER = 30;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ProjectComponentAuthorisationImpl <em>Project Component Authorisation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ProjectComponentAuthorisationImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProjectComponentAuthorisation()
     * @generated
     */
    int PROJECT_COMPONENT_AUTHORISATION = 1;

    /**
     * The feature id for the '<em><b>Project</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT_COMPONENT_AUTHORISATION__PROJECT = 0;

    /**
     * The feature id for the '<em><b>Component</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT_COMPONENT_AUTHORISATION__COMPONENT = 1;

    /**
     * The number of structural features of the '<em>Project Component Authorisation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT_COMPONENT_AUTHORISATION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ProjectReferenceImpl <em>Project Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ProjectReferenceImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProjectReference()
     * @generated
     */
    int PROJECT_REFERENCE = 2;

    /**
     * The feature id for the '<em><b>Project</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT_REFERENCE__PROJECT = 0;

    /**
     * The feature id for the '<em><b>Referenced Project</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT_REFERENCE__REFERENCED_PROJECT = 1;

    /**
     * The number of structural features of the '<em>Project Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT_REFERENCE_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATUS__LABEL = 0;

    /**
     * The feature id for the '<em><b>Code</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATUS__CODE = 1;

    /**
     * The number of structural features of the '<em>Status</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int STATUS_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM_STATE__PATH = 0;

    /**
     * The feature id for the '<em><b>Deleted</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM_STATE__DELETED = 1;

    /**
     * The feature id for the '<em><b>Locked</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM_STATE__LOCKED = 2;

    /**
     * The feature id for the '<em><b>Locker</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM_STATE__LOCKER = 3;

    /**
     * The feature id for the '<em><b>Lock Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM_STATE__LOCK_DATE = 4;

    /**
     * The feature id for the '<em><b>Commit Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM_STATE__COMMIT_DATE = 5;

    /**
     * The number of structural features of the '<em>Item State</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM_STATE_FEATURE_COUNT = 6;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__LABEL = 1;

    /**
     * The feature id for the '<em><b>Purpose</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__PURPOSE = 2;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROPERTY__DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROPERTY__CREATION_DATE = 4;

    /**
     * The feature id for the '<em><b>Modification Date</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__MODIFICATION_DATE = 5;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__VERSION = 6;

    /**
     * The feature id for the '<em><b>Status Code</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROPERTY__STATUS_CODE = 7;

    /**
     * The feature id for the '<em><b>Item</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__ITEM = 8;

    /**
     * The feature id for the '<em><b>Author</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__AUTHOR = 9;

    /**
     * The feature id for the '<em><b>Informations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__INFORMATIONS = 10;

    /**
     * The feature id for the '<em><b>Max Information Level</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__MAX_INFORMATION_LEVEL = 11;

    /**
     * The number of structural features of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROPERTY_FEATURE_COUNT = 12;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM__PROPERTY = 0;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM__STATE = 1;

    /**
     * The number of structural features of the '<em>Item</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ITEM_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.LinkDocumentationItemImpl <em>Link Documentation Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.LinkDocumentationItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLinkDocumentationItem()
     * @generated
     */
    int LINK_DOCUMENTATION_ITEM = 7;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_DOCUMENTATION_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_DOCUMENTATION_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_DOCUMENTATION_ITEM__NAME = ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Extension</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_DOCUMENTATION_ITEM__EXTENSION = ITEM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Link</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_DOCUMENTATION_ITEM__LINK = ITEM_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Link Documentation Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_DOCUMENTATION_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.LinkTypeImpl <em>Link Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.LinkTypeImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLinkType()
     * @generated
     */
    int LINK_TYPE = 8;

    /**
     * The feature id for the '<em><b>URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_TYPE__URI = 0;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_TYPE__STATE = 1;

    /**
     * The number of structural features of the '<em>Link Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_TYPE_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_PROCESS_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_PROCESS_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Notation</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_PROCESS_ITEM__NOTATION = ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_PROCESS_ITEM__SEMANTIC = ITEM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Notation Holder</b></em>' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_PROCESS_ITEM__NOTATION_HOLDER = ITEM_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Business Process Item</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_PROCESS_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_ITEM__NAME = ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Extension</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_ITEM__EXTENSION = ITEM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Content</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_ITEM__CONTENT = ITEM_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>File Item</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Inner Content</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int BYTE_ARRAY__INNER_CONTENT = 0;

    /**
     * The number of structural features of the '<em>Byte Array</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BYTE_ARRAY_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION_ITEM__PROPERTY = FILE_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION_ITEM__STATE = FILE_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION_ITEM__NAME = FILE_ITEM__NAME;

    /**
     * The feature id for the '<em><b>Extension</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION_ITEM__EXTENSION = FILE_ITEM__EXTENSION;

    /**
     * The feature id for the '<em><b>Content</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION_ITEM__CONTENT = FILE_ITEM__CONTENT;

    /**
     * The number of structural features of the '<em>Documentation Item</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION_ITEM_FEATURE_COUNT = FILE_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM__PROPERTY = FILE_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM__STATE = FILE_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM__NAME = FILE_ITEM__NAME;

    /**
     * The feature id for the '<em><b>Extension</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM__EXTENSION = FILE_ITEM__EXTENSION;

    /**
     * The feature id for the '<em><b>Content</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM__CONTENT = FILE_ITEM__CONTENT;

    /**
     * The feature id for the '<em><b>Built In</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM__BUILT_IN = FILE_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Imports</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM__IMPORTS = FILE_ITEM_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Routine Item</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM_FEATURE_COUNT = FILE_ITEM_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION_ITEM__CONNECTION = ITEM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Connection Item</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.SnippetVariableImpl <em>Snippet Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.SnippetVariableImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSnippetVariable()
     * @generated
     */
    int SNIPPET_VARIABLE = 15;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_VARIABLE__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_VARIABLE__VALUE = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_VARIABLE__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_VARIABLE__ID = 3;

    /**
     * The number of structural features of the '<em>Snippet Variable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_VARIABLE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.SnippetItemImpl <em>Snippet Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.SnippetItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSnippetItem()
     * @generated
     */
    int SNIPPET_ITEM = 16;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_ITEM__NAME = ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_ITEM__CONTENT = ITEM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Variables</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_ITEM__VARIABLES = ITEM_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Snippet Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SNIPPET_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Delimited File Connection Item</em>' class.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Positional File Connection Item</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REG_EX_FILE_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REG_EX_FILE_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REG_EX_FILE_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Reg Ex File Connection Item</em>' class.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REG_EX_FILE_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION_ITEM__PROPERTY = DELIMITED_FILE_CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION_ITEM__STATE = DELIMITED_FILE_CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION_ITEM__CONNECTION = DELIMITED_FILE_CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>CSV File Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION_ITEM_FEATURE_COUNT = DELIMITED_FILE_CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Database Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Xml File Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.LdifFileConnectionItemImpl <em>Ldif File Connection Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.LdifFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLdifFileConnectionItem()
     * @generated
     */
    int LDIF_FILE_CONNECTION_ITEM = 23;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Ldif File Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ExcelFileConnectionItemImpl <em>Excel File Connection Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ExcelFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getExcelFileConnectionItem()
     * @generated
     */
    int EXCEL_FILE_CONNECTION_ITEM = 24;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCEL_FILE_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCEL_FILE_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCEL_FILE_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Excel File Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCEL_FILE_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Process</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS_ITEM__PROCESS = ITEM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Process Item</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_ROLE__ID = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_ROLE__NAME = 1;

    /**
     * The feature id for the '<em><b>Localized Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_ROLE__LOCALIZED_LABEL = 2;

    /**
     * The number of structural features of the '<em>User Role</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_ROLE_FEATURE_COUNT = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__ID = 0;

    /**
     * The feature id for the '<em><b>Login</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__LOGIN = 1;

    /**
     * The feature id for the '<em><b>Password</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__PASSWORD = 2;

    /**
     * The feature id for the '<em><b>First Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__FIRST_NAME = 3;

    /**
     * The feature id for the '<em><b>Last Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__LAST_NAME = 4;

    /**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int USER__CREATION_DATE = 5;

    /**
     * The feature id for the '<em><b>Delete Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int USER__DELETE_DATE = 6;

    /**
     * The feature id for the '<em><b>Deleted</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__DELETED = 7;

    /**
     * The feature id for the '<em><b>Allowed To Modify Components</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__ALLOWED_TO_MODIFY_COMPONENTS = 8;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__COMMENT = 9;

    /**
     * The feature id for the '<em><b>Role</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__ROLE = 10;

    /**
     * The feature id for the '<em><b>Project Authorization</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__PROJECT_AUTHORIZATION = 11;

    /**
     * The feature id for the '<em><b>Module Authorization</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__MODULE_AUTHORIZATION = 12;

    /**
     * The feature id for the '<em><b>Preferred Dashboard Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__PREFERRED_DASHBOARD_CONNECTION = 13;

    /**
     * The feature id for the '<em><b>Last Admin Connection Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__LAST_ADMIN_CONNECTION_DATE = 14;

    /**
     * The feature id for the '<em><b>Last Studio Connection Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__LAST_STUDIO_CONNECTION_DATE = 15;

    /**
     * The feature id for the '<em><b>First Admin Connection Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__FIRST_ADMIN_CONNECTION_DATE = 16;

    /**
     * The feature id for the '<em><b>First Studio Connection Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__FIRST_STUDIO_CONNECTION_DATE = 17;

    /**
     * The feature id for the '<em><b>Admin Connexion Number</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__ADMIN_CONNEXION_NUMBER = 18;

    /**
     * The feature id for the '<em><b>Studio Connexion Number</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER__STUDIO_CONNEXION_NUMBER = 19;

    /**
     * The number of structural features of the '<em>User</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_FEATURE_COUNT = 20;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER_ITEM__CHILDREN = ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER_ITEM__TYPE = ITEM_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Folder Item</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FOLDER_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__LABEL = 1;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__VERSION = 2;

    /**
     * The feature id for the '<em><b>Last Update Date</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__LAST_UPDATE_DATE = 3;

    /**
     * The feature id for the '<em><b>Author</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__AUTHOR = 4;

    /**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__CREATION_DATE = 5;

    /**
     * The feature id for the '<em><b>Delete Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__DELETE_DATE = 6;

    /**
     * The feature id for the '<em><b>Deleted</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__DELETED = 7;

    /**
     * The feature id for the '<em><b>File Descriptor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__FILE_DESCRIPTOR = 8;

    /**
     * The feature id for the '<em><b>Projects</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__PROJECTS = 9;

    /**
     * The number of structural features of the '<em>Component</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_FEATURE_COUNT = 10;

    /**
     * The feature id for the '<em><b>Notation String</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_HOLDER__NOTATION_STRING = 0;

    /**
     * The number of structural features of the '<em>Notation Holder</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTATION_HOLDER_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.UserProjectAuthorizationImpl <em>User Project Authorization</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.UserProjectAuthorizationImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserProjectAuthorization()
     * @generated
     */
    int USER_PROJECT_AUTHORIZATION = 31;

    /**
     * The feature id for the '<em><b>User</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_PROJECT_AUTHORIZATION__USER = 0;

    /**
     * The feature id for the '<em><b>Project</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_PROJECT_AUTHORIZATION__PROJECT = 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_PROJECT_AUTHORIZATION__TYPE = 2;

    /**
     * The number of structural features of the '<em>User Project Authorization</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_PROJECT_AUTHORIZATION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ContextItemImpl <em>Context Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ContextItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getContextItem()
     * @generated
     */
    int CONTEXT_ITEM = 32;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTEXT_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTEXT_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTEXT_ITEM__CONTEXT = ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Default Context</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTEXT_ITEM__DEFAULT_CONTEXT = ITEM_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Context Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTEXT_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.SpagoBiServerImpl <em>Spago Bi Server</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.SpagoBiServerImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSpagoBiServer()
     * @generated
     */
    int SPAGO_BI_SERVER = 33;

    /**
     * The feature id for the '<em><b>Engine Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPAGO_BI_SERVER__ENGINE_NAME = 0;

    /**
     * The feature id for the '<em><b>Short Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPAGO_BI_SERVER__SHORT_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Host</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPAGO_BI_SERVER__HOST = 2;

    /**
     * The feature id for the '<em><b>Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPAGO_BI_SERVER__PORT = 3;

    /**
     * The feature id for the '<em><b>Login</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPAGO_BI_SERVER__LOGIN = 4;

    /**
     * The feature id for the '<em><b>Password</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPAGO_BI_SERVER__PASSWORD = 5;

    /**
     * The feature id for the '<em><b>Application Context</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPAGO_BI_SERVER__APPLICATION_CONTEXT = 6;

    /**
     * The number of structural features of the '<em>Spago Bi Server</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPAGO_BI_SERVER_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.LicenseImpl <em>License</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.LicenseImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLicense()
     * @generated
     */
    int LICENSE = 34;

    /**
     * The feature id for the '<em><b>License</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LICENSE__LICENSE = 0;

    /**
     * The number of structural features of the '<em>License</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LICENSE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.GenericSchemaConnectionItemImpl <em>Generic Schema Connection Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.GenericSchemaConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getGenericSchemaConnectionItem()
     * @generated
     */
    int GENERIC_SCHEMA_CONNECTION_ITEM = 35;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GENERIC_SCHEMA_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GENERIC_SCHEMA_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GENERIC_SCHEMA_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Generic Schema Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GENERIC_SCHEMA_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.UserModuleAuthorizationImpl <em>User Module Authorization</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.UserModuleAuthorizationImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserModuleAuthorization()
     * @generated
     */
    int USER_MODULE_AUTHORIZATION = 36;

    /**
     * The feature id for the '<em><b>User</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_MODULE_AUTHORIZATION__USER = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_MODULE_AUTHORIZATION__TYPE = 1;

    /**
     * The number of structural features of the '<em>User Module Authorization</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_MODULE_AUTHORIZATION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.LDAPSchemaConnectionItemImpl <em>LDAP Schema Connection Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.LDAPSchemaConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLDAPSchemaConnectionItem()
     * @generated
     */
    int LDAP_SCHEMA_CONNECTION_ITEM = 37;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDAP_SCHEMA_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDAP_SCHEMA_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDAP_SCHEMA_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>LDAP Schema Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDAP_SCHEMA_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.SalesforceSchemaConnectionItemImpl <em>Salesforce Schema Connection Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.SalesforceSchemaConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSalesforceSchemaConnectionItem()
     * @generated
     */
    int SALESFORCE_SCHEMA_CONNECTION_ITEM = 38;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SALESFORCE_SCHEMA_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SALESFORCE_SCHEMA_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SALESFORCE_SCHEMA_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>Salesforce Schema Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SALESFORCE_SCHEMA_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.DashboardConnectionImpl <em>Dashboard Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.DashboardConnectionImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDashboardConnection()
     * @generated
     */
    int DASHBOARD_CONNECTION = 39;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__LABEL = 1;

    /**
     * The feature id for the '<em><b>Dialect</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__DIALECT = 2;

    /**
     * The feature id for the '<em><b>Host</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__HOST = 3;

    /**
     * The feature id for the '<em><b>Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__PORT = 4;

    /**
     * The feature id for the '<em><b>Database</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__DATABASE = 5;

    /**
     * The feature id for the '<em><b>Username</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__USERNAME = 6;

    /**
     * The feature id for the '<em><b>Password</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__PASSWORD = 7;

    /**
     * The feature id for the '<em><b>Log Table</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__LOG_TABLE = 8;

    /**
     * The feature id for the '<em><b>Stat Table</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__STAT_TABLE = 9;

    /**
     * The feature id for the '<em><b>Flow Meter Table</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION__FLOW_METER_TABLE = 10;

    /**
     * The number of structural features of the '<em>Dashboard Connection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DASHBOARD_CONNECTION_FEATURE_COUNT = 11;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ExecutionServerImpl <em>Execution Server</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ExecutionServerImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getExecutionServer()
     * @generated
     */
    int EXECUTION_SERVER = 40;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER__LABEL = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Host</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER__HOST = 3;

    /**
     * The feature id for the '<em><b>Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER__PORT = 4;

    /**
     * The feature id for the '<em><b>File Transfert Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER__FILE_TRANSFERT_PORT = 5;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER__ACTIVE = 6;

    /**
     * The feature id for the '<em><b>Monitoring Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER__MONITORING_PORT = 7;

    /**
     * The number of structural features of the '<em>Execution Server</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_SERVER_FEATURE_COUNT = 8;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ExecutionTaskImpl <em>Execution Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ExecutionTaskImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getExecutionTask()
     * @generated
     */
    int EXECUTION_TASK = 41;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__LABEL = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Execution Server</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__EXECUTION_SERVER = 3;

    /**
     * The feature id for the '<em><b>Project</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__PROJECT = 4;

    /**
     * The feature id for the '<em><b>Context</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__CONTEXT = 5;

    /**
     * The feature id for the '<em><b>Job Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__JOB_VERSION = 6;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__ACTIVE = 7;

    /**
     * The feature id for the '<em><b>Id Quartz Job</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__ID_QUARTZ_JOB = 8;

    /**
     * The feature id for the '<em><b>Last Script Generation Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__LAST_SCRIPT_GENERATION_DATE = 9;

    /**
     * The feature id for the '<em><b>Id Remote Job</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__ID_REMOTE_JOB = 10;

    /**
     * The feature id for the '<em><b>Id Remote Job Execution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__ID_REMOTE_JOB_EXECUTION = 11;

    /**
     * The feature id for the '<em><b>Checksum Archive</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__CHECKSUM_ARCHIVE = 12;

    /**
     * The feature id for the '<em><b>Job Script Archive Filename</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__JOB_SCRIPT_ARCHIVE_FILENAME = 13;

    /**
     * The feature id for the '<em><b>Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__STATUS = 14;

    /**
     * The feature id for the '<em><b>Processing State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__PROCESSING_STATE = 15;

    /**
     * The feature id for the '<em><b>Error Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__ERROR_STATUS = 16;

    /**
     * The feature id for the '<em><b>Last Run Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__LAST_RUN_DATE = 17;

    /**
     * The feature id for the '<em><b>Last Deployment Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__LAST_DEPLOYMENT_DATE = 18;

    /**
     * The feature id for the '<em><b>Last Ended Run Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__LAST_ENDED_RUN_DATE = 19;

    /**
     * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__TRIGGERS = 20;

    /**
     * The feature id for the '<em><b>Job Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__JOB_ID = 21;

    /**
     * The feature id for the '<em><b>Virtual Server</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__VIRTUAL_SERVER = 22;

    /**
     * The feature id for the '<em><b>Concurrent Execution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__CONCURRENT_EXECUTION = 23;

    /**
     * The feature id for the '<em><b>Max Concurrent Executions</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__MAX_CONCURRENT_EXECUTIONS = 24;

    /**
     * The feature id for the '<em><b>Generated Project Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__GENERATED_PROJECT_NAME = 25;

    /**
     * The feature id for the '<em><b>Generated Job Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__GENERATED_JOB_NAME = 26;

    /**
     * The feature id for the '<em><b>Apply Context To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK__APPLY_CONTEXT_TO_CHILDREN = 27;

    /**
     * The number of structural features of the '<em>Execution Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_TASK_FEATURE_COUNT = 28;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.TalendTriggerImpl <em>Talend Trigger</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.TalendTriggerImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getTalendTrigger()
     * @generated
     */
    int TALEND_TRIGGER = 42;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__ID = 0;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__ACTIVE = 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__LABEL = 2;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Trigger Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__TRIGGER_TYPE = 4;

    /**
     * The feature id for the '<em><b>Execution Task</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__EXECUTION_TASK = 5;

    /**
     * The feature id for the '<em><b>Start Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__START_TIME = 6;

    /**
     * The feature id for the '<em><b>End Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__END_TIME = 7;

    /**
     * The feature id for the '<em><b>Previous Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__PREVIOUS_FIRE_TIME = 8;

    /**
     * The feature id for the '<em><b>Final Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER__FINAL_FIRE_TIME = 9;

    /**
     * The number of structural features of the '<em>Talend Trigger</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TALEND_TRIGGER_FEATURE_COUNT = 10;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl <em>Cron Talend Trigger</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.CronTalendTriggerImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getCronTalendTrigger()
     * @generated
     */
    int CRON_TALEND_TRIGGER = 43;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__ID = TALEND_TRIGGER__ID;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__ACTIVE = TALEND_TRIGGER__ACTIVE;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__LABEL = TALEND_TRIGGER__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__DESCRIPTION = TALEND_TRIGGER__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Trigger Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__TRIGGER_TYPE = TALEND_TRIGGER__TRIGGER_TYPE;

    /**
     * The feature id for the '<em><b>Execution Task</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__EXECUTION_TASK = TALEND_TRIGGER__EXECUTION_TASK;

    /**
     * The feature id for the '<em><b>Start Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__START_TIME = TALEND_TRIGGER__START_TIME;

    /**
     * The feature id for the '<em><b>End Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__END_TIME = TALEND_TRIGGER__END_TIME;

    /**
     * The feature id for the '<em><b>Previous Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__PREVIOUS_FIRE_TIME = TALEND_TRIGGER__PREVIOUS_FIRE_TIME;

    /**
     * The feature id for the '<em><b>Final Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__FINAL_FIRE_TIME = TALEND_TRIGGER__FINAL_FIRE_TIME;

    /**
     * The feature id for the '<em><b>Cron Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER__CRON_EXPRESSION = TALEND_TRIGGER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Cron Talend Trigger</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_TALEND_TRIGGER_FEATURE_COUNT = TALEND_TRIGGER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.CronUITalendTriggerImpl <em>Cron UI Talend Trigger</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.CronUITalendTriggerImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getCronUITalendTrigger()
     * @generated
     */
    int CRON_UI_TALEND_TRIGGER = 44;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__ID = TALEND_TRIGGER__ID;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__ACTIVE = TALEND_TRIGGER__ACTIVE;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__LABEL = TALEND_TRIGGER__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__DESCRIPTION = TALEND_TRIGGER__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Trigger Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__TRIGGER_TYPE = TALEND_TRIGGER__TRIGGER_TYPE;

    /**
     * The feature id for the '<em><b>Execution Task</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__EXECUTION_TASK = TALEND_TRIGGER__EXECUTION_TASK;

    /**
     * The feature id for the '<em><b>Start Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__START_TIME = TALEND_TRIGGER__START_TIME;

    /**
     * The feature id for the '<em><b>End Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__END_TIME = TALEND_TRIGGER__END_TIME;

    /**
     * The feature id for the '<em><b>Previous Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__PREVIOUS_FIRE_TIME = TALEND_TRIGGER__PREVIOUS_FIRE_TIME;

    /**
     * The feature id for the '<em><b>Final Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__FINAL_FIRE_TIME = TALEND_TRIGGER__FINAL_FIRE_TIME;

    /**
     * The feature id for the '<em><b>List Days Of Week</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__LIST_DAYS_OF_WEEK = TALEND_TRIGGER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>List Days Of Month</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__LIST_DAYS_OF_MONTH = TALEND_TRIGGER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>List Months</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__LIST_MONTHS = TALEND_TRIGGER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>List Years</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__LIST_YEARS = TALEND_TRIGGER_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>List Hours</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__LIST_HOURS = TALEND_TRIGGER_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>List Minutes</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER__LIST_MINUTES = TALEND_TRIGGER_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Cron UI Talend Trigger</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CRON_UI_TALEND_TRIGGER_FEATURE_COUNT = TALEND_TRIGGER_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.SimpleTalendTriggerImpl <em>Simple Talend Trigger</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.SimpleTalendTriggerImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSimpleTalendTrigger()
     * @generated
     */
    int SIMPLE_TALEND_TRIGGER = 45;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__ID = TALEND_TRIGGER__ID;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__ACTIVE = TALEND_TRIGGER__ACTIVE;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__LABEL = TALEND_TRIGGER__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__DESCRIPTION = TALEND_TRIGGER__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Trigger Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__TRIGGER_TYPE = TALEND_TRIGGER__TRIGGER_TYPE;

    /**
     * The feature id for the '<em><b>Execution Task</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__EXECUTION_TASK = TALEND_TRIGGER__EXECUTION_TASK;

    /**
     * The feature id for the '<em><b>Start Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__START_TIME = TALEND_TRIGGER__START_TIME;

    /**
     * The feature id for the '<em><b>End Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__END_TIME = TALEND_TRIGGER__END_TIME;

    /**
     * The feature id for the '<em><b>Previous Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__PREVIOUS_FIRE_TIME = TALEND_TRIGGER__PREVIOUS_FIRE_TIME;

    /**
     * The feature id for the '<em><b>Final Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__FINAL_FIRE_TIME = TALEND_TRIGGER__FINAL_FIRE_TIME;

    /**
     * The feature id for the '<em><b>Repeat Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__REPEAT_COUNT = TALEND_TRIGGER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Repeat Interval</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__REPEAT_INTERVAL = TALEND_TRIGGER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Times Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER__TIMES_TRIGGERED = TALEND_TRIGGER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Simple Talend Trigger</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_TALEND_TRIGGER_FEATURE_COUNT = TALEND_TRIGGER_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ExecutionVirtualServerImpl <em>Execution Virtual Server</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ExecutionVirtualServerImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getExecutionVirtualServer()
     * @generated
     */
    int EXECUTION_VIRTUAL_SERVER = 46;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_VIRTUAL_SERVER__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_VIRTUAL_SERVER__LABEL = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_VIRTUAL_SERVER__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_VIRTUAL_SERVER__ACTIVE = 3;

    /**
     * The feature id for the '<em><b>Execution Servers</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_VIRTUAL_SERVER__EXECUTION_SERVERS = 4;

    /**
     * The number of structural features of the '<em>Execution Virtual Server</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_VIRTUAL_SERVER_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.FileTriggerImpl <em>File Trigger</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.FileTriggerImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFileTrigger()
     * @generated
     */
    int FILE_TRIGGER = 47;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__ID = SIMPLE_TALEND_TRIGGER__ID;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__ACTIVE = SIMPLE_TALEND_TRIGGER__ACTIVE;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__LABEL = SIMPLE_TALEND_TRIGGER__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__DESCRIPTION = SIMPLE_TALEND_TRIGGER__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Trigger Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__TRIGGER_TYPE = SIMPLE_TALEND_TRIGGER__TRIGGER_TYPE;

    /**
     * The feature id for the '<em><b>Execution Task</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__EXECUTION_TASK = SIMPLE_TALEND_TRIGGER__EXECUTION_TASK;

    /**
     * The feature id for the '<em><b>Start Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__START_TIME = SIMPLE_TALEND_TRIGGER__START_TIME;

    /**
     * The feature id for the '<em><b>End Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__END_TIME = SIMPLE_TALEND_TRIGGER__END_TIME;

    /**
     * The feature id for the '<em><b>Previous Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__PREVIOUS_FIRE_TIME = SIMPLE_TALEND_TRIGGER__PREVIOUS_FIRE_TIME;

    /**
     * The feature id for the '<em><b>Final Fire Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__FINAL_FIRE_TIME = SIMPLE_TALEND_TRIGGER__FINAL_FIRE_TIME;

    /**
     * The feature id for the '<em><b>Repeat Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__REPEAT_COUNT = SIMPLE_TALEND_TRIGGER__REPEAT_COUNT;

    /**
     * The feature id for the '<em><b>Repeat Interval</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__REPEAT_INTERVAL = SIMPLE_TALEND_TRIGGER__REPEAT_INTERVAL;

    /**
     * The feature id for the '<em><b>Times Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__TIMES_TRIGGERED = SIMPLE_TALEND_TRIGGER__TIMES_TRIGGERED;

    /**
     * The feature id for the '<em><b>File Trigger Masks</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER__FILE_TRIGGER_MASKS = SIMPLE_TALEND_TRIGGER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>File Trigger</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_FEATURE_COUNT = SIMPLE_TALEND_TRIGGER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.FileTriggerMaskImpl <em>File Trigger Mask</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.FileTriggerMaskImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFileTriggerMask()
     * @generated
     */
    int FILE_TRIGGER_MASK = 48;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__ID = 0;

    /**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__ACTIVE = 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__LABEL = 2;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>File Trigger</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__FILE_TRIGGER = 4;

    /**
     * The feature id for the '<em><b>Folder Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__FOLDER_PATH = 5;

    /**
     * The feature id for the '<em><b>File Mask</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__FILE_MASK = 6;

    /**
     * The feature id for the '<em><b>Context Parameter Base Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__CONTEXT_PARAMETER_BASE_NAME = 7;

    /**
     * The feature id for the '<em><b>Check File Server</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK__CHECK_FILE_SERVER = 8;

    /**
     * The number of structural features of the '<em>File Trigger Mask</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_TRIGGER_MASK_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.JobletProcessItemImpl <em>Joblet Process Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.JobletProcessItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getJobletProcessItem()
     * @generated
     */
    int JOBLET_PROCESS_ITEM = 49;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS_ITEM__PROPERTY = ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS_ITEM__STATE = ITEM__STATE;

    /**
     * The feature id for the '<em><b>Joblet Process</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS_ITEM__JOBLET_PROCESS = ITEM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Joblet Process Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.JobDocumentationItemImpl <em>Job Documentation Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.JobDocumentationItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getJobDocumentationItem()
     * @generated
     */
    int JOB_DOCUMENTATION_ITEM = 50;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOB_DOCUMENTATION_ITEM__PROPERTY = FILE_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOB_DOCUMENTATION_ITEM__STATE = FILE_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOB_DOCUMENTATION_ITEM__NAME = FILE_ITEM__NAME;

    /**
     * The feature id for the '<em><b>Extension</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOB_DOCUMENTATION_ITEM__EXTENSION = FILE_ITEM__EXTENSION;

    /**
     * The feature id for the '<em><b>Content</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOB_DOCUMENTATION_ITEM__CONTENT = FILE_ITEM__CONTENT;

    /**
     * The number of structural features of the '<em>Job Documentation Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOB_DOCUMENTATION_ITEM_FEATURE_COUNT = FILE_ITEM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.JobletDocumentationItemImpl <em>Joblet Documentation Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.JobletDocumentationItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getJobletDocumentationItem()
     * @generated
     */
    int JOBLET_DOCUMENTATION_ITEM = 51;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_DOCUMENTATION_ITEM__PROPERTY = FILE_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_DOCUMENTATION_ITEM__STATE = FILE_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_DOCUMENTATION_ITEM__NAME = FILE_ITEM__NAME;

    /**
     * The feature id for the '<em><b>Extension</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_DOCUMENTATION_ITEM__EXTENSION = FILE_ITEM__EXTENSION;

    /**
     * The feature id for the '<em><b>Content</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_DOCUMENTATION_ITEM__CONTENT = FILE_ITEM__CONTENT;

    /**
     * The number of structural features of the '<em>Joblet Documentation Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_DOCUMENTATION_ITEM_FEATURE_COUNT = FILE_ITEM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.WSDLSchemaConnectionItemImpl <em>WSDL Schema Connection Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.WSDLSchemaConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getWSDLSchemaConnectionItem()
     * @generated
     */
    int WSDL_SCHEMA_CONNECTION_ITEM = 52;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WSDL_SCHEMA_CONNECTION_ITEM__PROPERTY = CONNECTION_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WSDL_SCHEMA_CONNECTION_ITEM__STATE = CONNECTION_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WSDL_SCHEMA_CONNECTION_ITEM__CONNECTION = CONNECTION_ITEM__CONNECTION;

    /**
     * The number of structural features of the '<em>WSDL Schema Connection Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WSDL_SCHEMA_CONNECTION_ITEM_FEATURE_COUNT = CONNECTION_ITEM_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.SchemaInformationImpl <em>Schema Information</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.SchemaInformationImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSchemaInformation()
     * @generated
     */
    int SCHEMA_INFORMATION = 53;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INFORMATION__VERSION = 0;

    /**
     * The number of structural features of the '<em>Schema Information</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_INFORMATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.InformationImpl <em>Information</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.InformationImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getInformation()
     * @generated
     */
    int INFORMATION = 54;

    /**
     * The feature id for the '<em><b>Level</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INFORMATION__LEVEL = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INFORMATION__TYPE = 1;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INFORMATION__TEXT = 2;

    /**
     * The number of structural features of the '<em>Information</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INFORMATION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.SQLPatternItemImpl <em>SQL Pattern Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.SQLPatternItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSQLPatternItem()
     * @generated
     */
    int SQL_PATTERN_ITEM = 55;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_PATTERN_ITEM__PROPERTY = FILE_ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_PATTERN_ITEM__STATE = FILE_ITEM__STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_PATTERN_ITEM__NAME = FILE_ITEM__NAME;

    /**
     * The feature id for the '<em><b>Extension</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_PATTERN_ITEM__EXTENSION = FILE_ITEM__EXTENSION;

    /**
     * The feature id for the '<em><b>Content</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_PATTERN_ITEM__CONTENT = FILE_ITEM__CONTENT;

    /**
     * The feature id for the '<em><b>System</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_PATTERN_ITEM__SYSTEM = FILE_ITEM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Elt Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_PATTERN_ITEM__ELT_NAME = FILE_ITEM_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>SQL Pattern Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_PATTERN_ITEM_FEATURE_COUNT = FILE_ITEM_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ComponentSettingImpl <em>Component Setting</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ComponentSettingImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getComponentSetting()
     * @generated
     */
    int COMPONENT_SETTING = 56;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_SETTING__NAME = 0;

    /**
     * The feature id for the '<em><b>Hidden</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_SETTING__HIDDEN = 1;

    /**
     * The number of structural features of the '<em>Component Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_SETTING_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.FolderType <em>Folder Type</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.FolderType
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFolderType()
     * @generated
     */
    int FOLDER_TYPE = 57;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.UserProjectAuthorizationType <em>User Project Authorization Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.UserProjectAuthorizationType
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserProjectAuthorizationType()
     * @generated
     */
    int USER_PROJECT_AUTHORIZATION_TYPE = 58;


    /**
     * The meta object id for the '{@link org.talend.core.model.properties.UserModuleAuthorizationType <em>User Module Authorization Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.UserModuleAuthorizationType
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserModuleAuthorizationType()
     * @generated
     */
    int USER_MODULE_AUTHORIZATION_TYPE = 59;


    /**
     * The meta object id for the '{@link org.talend.core.model.properties.InformationLevel <em>Information Level</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.InformationLevel
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getInformationLevel()
     * @generated
     */
    int INFORMATION_LEVEL = 60;


    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.Status <em>Status</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Status</em>'.
     * @see org.talend.core.model.properties.Status
     * @generated
     */
    EClass getStatus();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Status#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.Status#getLabel()
     * @see #getStatus()
     * @generated
     */
    EAttribute getStatus_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Status#getCode <em>Code</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Code</em>'.
     * @see org.talend.core.model.properties.Status#getCode()
     * @see #getStatus()
     * @generated
     */
    EAttribute getStatus_Code();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.Project <em>Project</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Project</em>'.
     * @see org.talend.core.model.properties.Project
     * @generated
     */
    EClass getProject();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.Project#getTechnicalStatus <em>Technical Status</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Technical Status</em>'.
     * @see org.talend.core.model.properties.Project#getTechnicalStatus()
     * @see #getProject()
     * @generated
     */
    EReference getProject_TechnicalStatus();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.Project#getDocumentationStatus <em>Documentation Status</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Documentation Status</em>'.
     * @see org.talend.core.model.properties.Project#getDocumentationStatus()
     * @see #getProject()
     * @generated
     */
    EReference getProject_DocumentationStatus();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.Project#getId()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.Project#getLabel()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.core.model.properties.Project#getDescription()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_Description();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getLanguage <em>Language</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Language</em>'.
     * @see org.talend.core.model.properties.Project#getLanguage()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_Language();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.Project#getAuthor <em>Author</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Author</em>'.
     * @see org.talend.core.model.properties.Project#getAuthor()
     * @see #getProject()
     * @generated
     */
    EReference getProject_Author();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.Project#getUserAuthorization <em>User Authorization</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>User Authorization</em>'.
     * @see org.talend.core.model.properties.Project#getUserAuthorization()
     * @see #getProject()
     * @generated
     */
    EReference getProject_UserAuthorization();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.Project#getAllowedComponents <em>Allowed Components</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Allowed Components</em>'.
     * @see org.talend.core.model.properties.Project#getAllowedComponents()
     * @see #getProject()
     * @generated
     */
    EReference getProject_AllowedComponents();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.Project#getReferencedProjects <em>Referenced Projects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Referenced Projects</em>'.
     * @see org.talend.core.model.properties.Project#getReferencedProjects()
     * @see #getProject()
     * @generated
     */
    EReference getProject_ReferencedProjects();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.Project#getAvailableRefProject <em>Available Ref Project</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Available Ref Project</em>'.
     * @see org.talend.core.model.properties.Project#getAvailableRefProject()
     * @see #getProject()
     * @generated
     */
    EReference getProject_AvailableRefProject();

    /**
     * Returns the meta object for the attribute list '{@link org.talend.core.model.properties.Project#getMigrationTasks <em>Migration Tasks</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Migration Tasks</em>'.
     * @see org.talend.core.model.properties.Project#getMigrationTasks()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_MigrationTasks();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getMasterJobId <em>Master Job Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Master Job Id</em>'.
     * @see org.talend.core.model.properties.Project#getMasterJobId()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_MasterJobId();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.Project#getSpagoBiServer <em>Spago Bi Server</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Spago Bi Server</em>'.
     * @see org.talend.core.model.properties.Project#getSpagoBiServer()
     * @see #getProject()
     * @generated
     */
    EReference getProject_SpagoBiServer();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getProductVersion <em>Product Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Product Version</em>'.
     * @see org.talend.core.model.properties.Project#getProductVersion()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_ProductVersion();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.Project#getComponentsSettings <em>Components Settings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Components Settings</em>'.
     * @see org.talend.core.model.properties.Project#getComponentsSettings()
     * @see #getProject()
     * @generated
     */
    EReference getProject_ComponentsSettings();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getTechnicalLabel <em>Technical Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Technical Label</em>'.
     * @see org.talend.core.model.properties.Project#getTechnicalLabel()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_TechnicalLabel();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#isLocal <em>Local</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Local</em>'.
     * @see org.talend.core.model.properties.Project#isLocal()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_Local();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.Project#getFolders <em>Folders</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Folders</em>'.
     * @see org.talend.core.model.properties.Project#getFolders()
     * @see #getProject()
     * @generated
     */
    EReference getProject_Folders();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#isDeleted <em>Deleted</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Deleted</em>'.
     * @see org.talend.core.model.properties.Project#isDeleted()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_Deleted();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getDeleteDate <em>Delete Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Delete Date</em>'.
     * @see org.talend.core.model.properties.Project#getDeleteDate()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_DeleteDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Project#getCreationDate <em>Creation Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Creation Date</em>'.
     * @see org.talend.core.model.properties.Project#getCreationDate()
     * @see #getProject()
     * @generated
     */
    EAttribute getProject_CreationDate();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.Property <em>Property</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Property</em>'.
     * @see org.talend.core.model.properties.Property
     * @generated
     */
    EClass getProperty();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.Property#getId()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.Property#getLabel()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getPurpose <em>Purpose</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Purpose</em>'.
     * @see org.talend.core.model.properties.Property#getPurpose()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_Purpose();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.core.model.properties.Property#getDescription()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_Description();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getCreationDate <em>Creation Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Creation Date</em>'.
     * @see org.talend.core.model.properties.Property#getCreationDate()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_CreationDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getModificationDate <em>Modification Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Modification Date</em>'.
     * @see org.talend.core.model.properties.Property#getModificationDate()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_ModificationDate();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.Property#getAuthor <em>Author</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Author</em>'.
     * @see org.talend.core.model.properties.Property#getAuthor()
     * @see #getProperty()
     * @generated
     */
    EReference getProperty_Author();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.Property#getInformations <em>Informations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Informations</em>'.
     * @see org.talend.core.model.properties.Property#getInformations()
     * @see #getProperty()
     * @generated
     */
    EReference getProperty_Informations();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getMaxInformationLevel <em>Max Information Level</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Information Level</em>'.
     * @see org.talend.core.model.properties.Property#getMaxInformationLevel()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_MaxInformationLevel();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.talend.core.model.properties.Property#getVersion()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_Version();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Property#getStatusCode <em>Status Code</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Status Code</em>'.
     * @see org.talend.core.model.properties.Property#getStatusCode()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_StatusCode();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.Property#getItem <em>Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Item</em>'.
     * @see org.talend.core.model.properties.Property#getItem()
     * @see #getProperty()
     * @generated
     */
    EReference getProperty_Item();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.Item <em>Item</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Item</em>'.
     * @see org.talend.core.model.properties.Item
     * @generated
     */
    EClass getItem();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.Item#getProperty <em>Property</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Property</em>'.
     * @see org.talend.core.model.properties.Item#getProperty()
     * @see #getItem()
     * @generated
     */
    EReference getItem_Property();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.Item#getState <em>State</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>State</em>'.
     * @see org.talend.core.model.properties.Item#getState()
     * @see #getItem()
     * @generated
     */
    EReference getItem_State();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.LinkDocumentationItem <em>Link Documentation Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Link Documentation Item</em>'.
     * @see org.talend.core.model.properties.LinkDocumentationItem
     * @generated
     */
    EClass getLinkDocumentationItem();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.LinkDocumentationItem#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.talend.core.model.properties.LinkDocumentationItem#getName()
     * @see #getLinkDocumentationItem()
     * @generated
     */
    EAttribute getLinkDocumentationItem_Name();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.LinkDocumentationItem#getExtension <em>Extension</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Extension</em>'.
     * @see org.talend.core.model.properties.LinkDocumentationItem#getExtension()
     * @see #getLinkDocumentationItem()
     * @generated
     */
    EAttribute getLinkDocumentationItem_Extension();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.LinkDocumentationItem#getLink <em>Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Link</em>'.
     * @see org.talend.core.model.properties.LinkDocumentationItem#getLink()
     * @see #getLinkDocumentationItem()
     * @generated
     */
    EReference getLinkDocumentationItem_Link();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.LinkType <em>Link Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Link Type</em>'.
     * @see org.talend.core.model.properties.LinkType
     * @generated
     */
    EClass getLinkType();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.LinkType#getURI <em>URI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>URI</em>'.
     * @see org.talend.core.model.properties.LinkType#getURI()
     * @see #getLinkType()
     * @generated
     */
    EAttribute getLinkType_URI();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.LinkType#isState <em>State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>State</em>'.
     * @see org.talend.core.model.properties.LinkType#isState()
     * @see #getLinkType()
     * @generated
     */
    EAttribute getLinkType_State();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.BusinessProcessItem <em>Business Process Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Business Process Item</em>'.
     * @see org.talend.core.model.properties.BusinessProcessItem
     * @generated
     */
    EClass getBusinessProcessItem();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.BusinessProcessItem#getNotation <em>Notation</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Notation</em>'.
     * @see org.talend.core.model.properties.BusinessProcessItem#getNotation()
     * @see #getBusinessProcessItem()
     * @generated
     */
    EReference getBusinessProcessItem_Notation();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.BusinessProcessItem#getSemantic <em>Semantic</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Semantic</em>'.
     * @see org.talend.core.model.properties.BusinessProcessItem#getSemantic()
     * @see #getBusinessProcessItem()
     * @generated
     */
    EReference getBusinessProcessItem_Semantic();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.BusinessProcessItem#getNotationHolder <em>Notation Holder</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Notation Holder</em>'.
     * @see org.talend.core.model.properties.BusinessProcessItem#getNotationHolder()
     * @see #getBusinessProcessItem()
     * @generated
     */
    EReference getBusinessProcessItem_NotationHolder();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ItemState <em>Item State</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Item State</em>'.
     * @see org.talend.core.model.properties.ItemState
     * @generated
     */
    EClass getItemState();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ItemState#getPath <em>Path</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Path</em>'.
     * @see org.talend.core.model.properties.ItemState#getPath()
     * @see #getItemState()
     * @generated
     */
    EAttribute getItemState_Path();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ItemState#isDeleted <em>Deleted</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Deleted</em>'.
     * @see org.talend.core.model.properties.ItemState#isDeleted()
     * @see #getItemState()
     * @generated
     */
    EAttribute getItemState_Deleted();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ItemState#isLocked <em>Locked</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Locked</em>'.
     * @see org.talend.core.model.properties.ItemState#isLocked()
     * @see #getItemState()
     * @generated
     */
    EAttribute getItemState_Locked();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ItemState#getLocker <em>Locker</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Locker</em>'.
     * @see org.talend.core.model.properties.ItemState#getLocker()
     * @see #getItemState()
     * @generated
     */
    EReference getItemState_Locker();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ItemState#getLockDate <em>Lock Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lock Date</em>'.
     * @see org.talend.core.model.properties.ItemState#getLockDate()
     * @see #getItemState()
     * @generated
     */
    EAttribute getItemState_LockDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ItemState#getCommitDate <em>Commit Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Commit Date</em>'.
     * @see org.talend.core.model.properties.ItemState#getCommitDate()
     * @see #getItemState()
     * @generated
     */
    EAttribute getItemState_CommitDate();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.FileItem <em>File Item</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>File Item</em>'.
     * @see org.talend.core.model.properties.FileItem
     * @generated
     */
    EClass getFileItem();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileItem#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.talend.core.model.properties.FileItem#getName()
     * @see #getFileItem()
     * @generated
     */
    EAttribute getFileItem_Name();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileItem#getExtension <em>Extension</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Extension</em>'.
     * @see org.talend.core.model.properties.FileItem#getExtension()
     * @see #getFileItem()
     * @generated
     */
    EAttribute getFileItem_Extension();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.FileItem#getContent <em>Content</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Content</em>'.
     * @see org.talend.core.model.properties.FileItem#getContent()
     * @see #getFileItem()
     * @generated
     */
    EReference getFileItem_Content();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.DocumentationItem <em>Documentation Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Documentation Item</em>'.
     * @see org.talend.core.model.properties.DocumentationItem
     * @generated
     */
    EClass getDocumentationItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.RoutineItem <em>Routine Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Routine Item</em>'.
     * @see org.talend.core.model.properties.RoutineItem
     * @generated
     */
    EClass getRoutineItem();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.RoutineItem#isBuiltIn <em>Built In</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Built In</em>'.
     * @see org.talend.core.model.properties.RoutineItem#isBuiltIn()
     * @see #getRoutineItem()
     * @generated
     */
    EAttribute getRoutineItem_BuiltIn();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.RoutineItem#getImports <em>Imports</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Imports</em>'.
     * @see org.talend.core.model.properties.RoutineItem#getImports()
     * @see #getRoutineItem()
     * @generated
     */
    EReference getRoutineItem_Imports();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ByteArray <em>Byte Array</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Byte Array</em>'.
     * @see org.talend.core.model.properties.ByteArray
     * @generated
     */
    EClass getByteArray();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ByteArray#getInnerContent <em>Inner Content</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Inner Content</em>'.
     * @see org.talend.core.model.properties.ByteArray#getInnerContent()
     * @see #getByteArray()
     * @generated
     */
    EAttribute getByteArray_InnerContent();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ConnectionItem <em>Connection Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Connection Item</em>'.
     * @see org.talend.core.model.properties.ConnectionItem
     * @generated
     */
    EClass getConnectionItem();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ConnectionItem#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Connection</em>'.
     * @see org.talend.core.model.properties.ConnectionItem#getConnection()
     * @see #getConnectionItem()
     * @generated
     */
    EReference getConnectionItem_Connection();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.SnippetVariable <em>Snippet Variable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Snippet Variable</em>'.
     * @see org.talend.core.model.properties.SnippetVariable
     * @generated
     */
    EClass getSnippetVariable();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SnippetVariable#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.talend.core.model.properties.SnippetVariable#getName()
     * @see #getSnippetVariable()
     * @generated
     */
    EAttribute getSnippetVariable_Name();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SnippetVariable#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.talend.core.model.properties.SnippetVariable#getValue()
     * @see #getSnippetVariable()
     * @generated
     */
    EAttribute getSnippetVariable_Value();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SnippetVariable#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.core.model.properties.SnippetVariable#getDescription()
     * @see #getSnippetVariable()
     * @generated
     */
    EAttribute getSnippetVariable_Description();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SnippetVariable#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.SnippetVariable#getId()
     * @see #getSnippetVariable()
     * @generated
     */
    EAttribute getSnippetVariable_Id();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.SnippetItem <em>Snippet Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Snippet Item</em>'.
     * @see org.talend.core.model.properties.SnippetItem
     * @generated
     */
    EClass getSnippetItem();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SnippetItem#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.talend.core.model.properties.SnippetItem#getName()
     * @see #getSnippetItem()
     * @generated
     */
    EAttribute getSnippetItem_Name();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SnippetItem#getContent <em>Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content</em>'.
     * @see org.talend.core.model.properties.SnippetItem#getContent()
     * @see #getSnippetItem()
     * @generated
     */
    EAttribute getSnippetItem_Content();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.SnippetItem#getVariables <em>Variables</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Variables</em>'.
     * @see org.talend.core.model.properties.SnippetItem#getVariables()
     * @see #getSnippetItem()
     * @generated
     */
    EReference getSnippetItem_Variables();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.DelimitedFileConnectionItem <em>Delimited File Connection Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Delimited File Connection Item</em>'.
     * @see org.talend.core.model.properties.DelimitedFileConnectionItem
     * @generated
     */
    EClass getDelimitedFileConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.PositionalFileConnectionItem <em>Positional File Connection Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Positional File Connection Item</em>'.
     * @see org.talend.core.model.properties.PositionalFileConnectionItem
     * @generated
     */
    EClass getPositionalFileConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.RegExFileConnectionItem <em>Reg Ex File Connection Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Reg Ex File Connection Item</em>'.
     * @see org.talend.core.model.properties.RegExFileConnectionItem
     * @generated
     */
    EClass getRegExFileConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.CSVFileConnectionItem <em>CSV File Connection Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>CSV File Connection Item</em>'.
     * @see org.talend.core.model.properties.CSVFileConnectionItem
     * @generated
     */
    EClass getCSVFileConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.DatabaseConnectionItem <em>Database Connection Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Database Connection Item</em>'.
     * @see org.talend.core.model.properties.DatabaseConnectionItem
     * @generated
     */
    EClass getDatabaseConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ProcessItem <em>Process Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Process Item</em>'.
     * @see org.talend.core.model.properties.ProcessItem
     * @generated
     */
    EClass getProcessItem();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ProcessItem#getProcess <em>Process</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Process</em>'.
     * @see org.talend.core.model.properties.ProcessItem#getProcess()
     * @see #getProcessItem()
     * @generated
     */
    EReference getProcessItem_Process();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.User <em>User</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>User</em>'.
     * @see org.talend.core.model.properties.User
     * @generated
     */
    EClass getUser();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.User#getId()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getLogin <em>Login</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Login</em>'.
     * @see org.talend.core.model.properties.User#getLogin()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_Login();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getPassword <em>Password</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Password</em>'.
     * @see org.talend.core.model.properties.User#getPassword()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_Password();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getFirstName <em>First Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>First Name</em>'.
     * @see org.talend.core.model.properties.User#getFirstName()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_FirstName();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getLastName <em>Last Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Name</em>'.
     * @see org.talend.core.model.properties.User#getLastName()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_LastName();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getCreationDate <em>Creation Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Creation Date</em>'.
     * @see org.talend.core.model.properties.User#getCreationDate()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_CreationDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getDeleteDate <em>Delete Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Delete Date</em>'.
     * @see org.talend.core.model.properties.User#getDeleteDate()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_DeleteDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#isDeleted <em>Deleted</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Deleted</em>'.
     * @see org.talend.core.model.properties.User#isDeleted()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_Deleted();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#isAllowedToModifyComponents <em>Allowed To Modify Components</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Allowed To Modify Components</em>'.
     * @see org.talend.core.model.properties.User#isAllowedToModifyComponents()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_AllowedToModifyComponents();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getComment <em>Comment</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Comment</em>'.
     * @see org.talend.core.model.properties.User#getComment()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_Comment();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.User#getRole <em>Role</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Role</em>'.
     * @see org.talend.core.model.properties.User#getRole()
     * @see #getUser()
     * @generated
     */
    EReference getUser_Role();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.User#getProjectAuthorization <em>Project Authorization</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Project Authorization</em>'.
     * @see org.talend.core.model.properties.User#getProjectAuthorization()
     * @see #getUser()
     * @generated
     */
    EReference getUser_ProjectAuthorization();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.User#getModuleAuthorization <em>Module Authorization</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Module Authorization</em>'.
     * @see org.talend.core.model.properties.User#getModuleAuthorization()
     * @see #getUser()
     * @generated
     */
    EReference getUser_ModuleAuthorization();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.User#getPreferredDashboardConnection <em>Preferred Dashboard Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Preferred Dashboard Connection</em>'.
     * @see org.talend.core.model.properties.User#getPreferredDashboardConnection()
     * @see #getUser()
     * @generated
     */
    EReference getUser_PreferredDashboardConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getLastAdminConnectionDate <em>Last Admin Connection Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Admin Connection Date</em>'.
     * @see org.talend.core.model.properties.User#getLastAdminConnectionDate()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_LastAdminConnectionDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getLastStudioConnectionDate <em>Last Studio Connection Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Studio Connection Date</em>'.
     * @see org.talend.core.model.properties.User#getLastStudioConnectionDate()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_LastStudioConnectionDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getFirstAdminConnectionDate <em>First Admin Connection Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>First Admin Connection Date</em>'.
     * @see org.talend.core.model.properties.User#getFirstAdminConnectionDate()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_FirstAdminConnectionDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getFirstStudioConnectionDate <em>First Studio Connection Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>First Studio Connection Date</em>'.
     * @see org.talend.core.model.properties.User#getFirstStudioConnectionDate()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_FirstStudioConnectionDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getAdminConnexionNumber <em>Admin Connexion Number</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Admin Connexion Number</em>'.
     * @see org.talend.core.model.properties.User#getAdminConnexionNumber()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_AdminConnexionNumber();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.User#getStudioConnexionNumber <em>Studio Connexion Number</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Studio Connexion Number</em>'.
     * @see org.talend.core.model.properties.User#getStudioConnexionNumber()
     * @see #getUser()
     * @generated
     */
    EAttribute getUser_StudioConnexionNumber();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.FolderItem <em>Folder Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Folder Item</em>'.
     * @see org.talend.core.model.properties.FolderItem
     * @generated
     */
    EClass getFolderItem();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.FolderItem#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see org.talend.core.model.properties.FolderItem#getChildren()
     * @see #getFolderItem()
     * @generated
     */
    EReference getFolderItem_Children();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FolderItem#getType <em>Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.talend.core.model.properties.FolderItem#getType()
     * @see #getFolderItem()
     * @generated
     */
    EAttribute getFolderItem_Type();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.Component <em>Component</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Component</em>'.
     * @see org.talend.core.model.properties.Component
     * @generated
     */
    EClass getComponent();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Component#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.Component#getId()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Component#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.Component#getLabel()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Component#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.talend.core.model.properties.Component#getVersion()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Version();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Component#getLastUpdateDate <em>Last Update Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Update Date</em>'.
     * @see org.talend.core.model.properties.Component#getLastUpdateDate()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_LastUpdateDate();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.Component#getProjects <em>Projects</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Projects</em>'.
     * @see org.talend.core.model.properties.Component#getProjects()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_Projects();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.Component#getAuthor <em>Author</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Author</em>'.
     * @see org.talend.core.model.properties.Component#getAuthor()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_Author();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Component#getCreationDate <em>Creation Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Creation Date</em>'.
     * @see org.talend.core.model.properties.Component#getCreationDate()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_CreationDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Component#getDeleteDate <em>Delete Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Delete Date</em>'.
     * @see org.talend.core.model.properties.Component#getDeleteDate()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_DeleteDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Component#isDeleted <em>Deleted</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Deleted</em>'.
     * @see org.talend.core.model.properties.Component#isDeleted()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Deleted();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Component#getFileDescriptor <em>File Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Descriptor</em>'.
     * @see org.talend.core.model.properties.Component#getFileDescriptor()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_FileDescriptor();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.XmlFileConnectionItem <em>Xml File Connection Item</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Xml File Connection Item</em>'.
     * @see org.talend.core.model.properties.XmlFileConnectionItem
     * @generated
     */
    EClass getXmlFileConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.NotationHolder <em>Notation Holder</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Notation Holder</em>'.
     * @see org.talend.core.model.properties.NotationHolder
     * @generated
     */
    EClass getNotationHolder();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.NotationHolder#getNotationString <em>Notation String</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Notation String</em>'.
     * @see org.talend.core.model.properties.NotationHolder#getNotationString()
     * @see #getNotationHolder()
     * @generated
     */
    EAttribute getNotationHolder_NotationString();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ProjectComponentAuthorisation <em>Project Component Authorisation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Project Component Authorisation</em>'.
     * @see org.talend.core.model.properties.ProjectComponentAuthorisation
     * @generated
     */
    EClass getProjectComponentAuthorisation();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ProjectComponentAuthorisation#getProject <em>Project</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Project</em>'.
     * @see org.talend.core.model.properties.ProjectComponentAuthorisation#getProject()
     * @see #getProjectComponentAuthorisation()
     * @generated
     */
    EReference getProjectComponentAuthorisation_Project();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ProjectComponentAuthorisation#getComponent <em>Component</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Component</em>'.
     * @see org.talend.core.model.properties.ProjectComponentAuthorisation#getComponent()
     * @see #getProjectComponentAuthorisation()
     * @generated
     */
    EReference getProjectComponentAuthorisation_Component();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ProjectReference <em>Project Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Project Reference</em>'.
     * @see org.talend.core.model.properties.ProjectReference
     * @generated
     */
    EClass getProjectReference();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ProjectReference#getProject <em>Project</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Project</em>'.
     * @see org.talend.core.model.properties.ProjectReference#getProject()
     * @see #getProjectReference()
     * @generated
     */
    EReference getProjectReference_Project();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ProjectReference#getReferencedProject <em>Referenced Project</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Referenced Project</em>'.
     * @see org.talend.core.model.properties.ProjectReference#getReferencedProject()
     * @see #getProjectReference()
     * @generated
     */
    EReference getProjectReference_ReferencedProject();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.LdifFileConnectionItem <em>Ldif File Connection Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Ldif File Connection Item</em>'.
     * @see org.talend.core.model.properties.LdifFileConnectionItem
     * @generated
     */
    EClass getLdifFileConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ExcelFileConnectionItem <em>Excel File Connection Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Excel File Connection Item</em>'.
     * @see org.talend.core.model.properties.ExcelFileConnectionItem
     * @generated
     */
    EClass getExcelFileConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.UserProjectAuthorization <em>User Project Authorization</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>User Project Authorization</em>'.
     * @see org.talend.core.model.properties.UserProjectAuthorization
     * @generated
     */
    EClass getUserProjectAuthorization();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.UserProjectAuthorization#getUser <em>User</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>User</em>'.
     * @see org.talend.core.model.properties.UserProjectAuthorization#getUser()
     * @see #getUserProjectAuthorization()
     * @generated
     */
    EReference getUserProjectAuthorization_User();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.UserProjectAuthorization#getProject <em>Project</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Project</em>'.
     * @see org.talend.core.model.properties.UserProjectAuthorization#getProject()
     * @see #getUserProjectAuthorization()
     * @generated
     */
    EReference getUserProjectAuthorization_Project();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.UserProjectAuthorization#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.talend.core.model.properties.UserProjectAuthorization#getType()
     * @see #getUserProjectAuthorization()
     * @generated
     */
    EAttribute getUserProjectAuthorization_Type();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ContextItem <em>Context Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Context Item</em>'.
     * @see org.talend.core.model.properties.ContextItem
     * @generated
     */
    EClass getContextItem();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.ContextItem#getContext <em>Context</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Context</em>'.
     * @see org.talend.core.model.properties.ContextItem#getContext()
     * @see #getContextItem()
     * @generated
     */
    EReference getContextItem_Context();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ContextItem#getDefaultContext <em>Default Context</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Context</em>'.
     * @see org.talend.core.model.properties.ContextItem#getDefaultContext()
     * @see #getContextItem()
     * @generated
     */
    EAttribute getContextItem_DefaultContext();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.SpagoBiServer <em>Spago Bi Server</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Spago Bi Server</em>'.
     * @see org.talend.core.model.properties.SpagoBiServer
     * @generated
     */
    EClass getSpagoBiServer();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SpagoBiServer#getEngineName <em>Engine Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Engine Name</em>'.
     * @see org.talend.core.model.properties.SpagoBiServer#getEngineName()
     * @see #getSpagoBiServer()
     * @generated
     */
    EAttribute getSpagoBiServer_EngineName();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SpagoBiServer#getShortDescription <em>Short Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Short Description</em>'.
     * @see org.talend.core.model.properties.SpagoBiServer#getShortDescription()
     * @see #getSpagoBiServer()
     * @generated
     */
    EAttribute getSpagoBiServer_ShortDescription();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SpagoBiServer#getHost <em>Host</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Host</em>'.
     * @see org.talend.core.model.properties.SpagoBiServer#getHost()
     * @see #getSpagoBiServer()
     * @generated
     */
    EAttribute getSpagoBiServer_Host();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SpagoBiServer#getPort <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Port</em>'.
     * @see org.talend.core.model.properties.SpagoBiServer#getPort()
     * @see #getSpagoBiServer()
     * @generated
     */
    EAttribute getSpagoBiServer_Port();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SpagoBiServer#getLogin <em>Login</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Login</em>'.
     * @see org.talend.core.model.properties.SpagoBiServer#getLogin()
     * @see #getSpagoBiServer()
     * @generated
     */
    EAttribute getSpagoBiServer_Login();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SpagoBiServer#getPassword <em>Password</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Password</em>'.
     * @see org.talend.core.model.properties.SpagoBiServer#getPassword()
     * @see #getSpagoBiServer()
     * @generated
     */
    EAttribute getSpagoBiServer_Password();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SpagoBiServer#getApplicationContext <em>Application Context</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Application Context</em>'.
     * @see org.talend.core.model.properties.SpagoBiServer#getApplicationContext()
     * @see #getSpagoBiServer()
     * @generated
     */
    EAttribute getSpagoBiServer_ApplicationContext();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.License <em>License</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>License</em>'.
     * @see org.talend.core.model.properties.License
     * @generated
     */
    EClass getLicense();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.License#getLicense <em>License</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>License</em>'.
     * @see org.talend.core.model.properties.License#getLicense()
     * @see #getLicense()
     * @generated
     */
    EAttribute getLicense_License();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.GenericSchemaConnectionItem <em>Generic Schema Connection Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Generic Schema Connection Item</em>'.
     * @see org.talend.core.model.properties.GenericSchemaConnectionItem
     * @generated
     */
    EClass getGenericSchemaConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.UserModuleAuthorization <em>User Module Authorization</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>User Module Authorization</em>'.
     * @see org.talend.core.model.properties.UserModuleAuthorization
     * @generated
     */
    EClass getUserModuleAuthorization();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.UserModuleAuthorization#getUser <em>User</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>User</em>'.
     * @see org.talend.core.model.properties.UserModuleAuthorization#getUser()
     * @see #getUserModuleAuthorization()
     * @generated
     */
    EReference getUserModuleAuthorization_User();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.UserModuleAuthorization#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.talend.core.model.properties.UserModuleAuthorization#getType()
     * @see #getUserModuleAuthorization()
     * @generated
     */
    EAttribute getUserModuleAuthorization_Type();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.LDAPSchemaConnectionItem <em>LDAP Schema Connection Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>LDAP Schema Connection Item</em>'.
     * @see org.talend.core.model.properties.LDAPSchemaConnectionItem
     * @generated
     */
    EClass getLDAPSchemaConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.SalesforceSchemaConnectionItem <em>Salesforce Schema Connection Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Salesforce Schema Connection Item</em>'.
     * @see org.talend.core.model.properties.SalesforceSchemaConnectionItem
     * @generated
     */
    EClass getSalesforceSchemaConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.DashboardConnection <em>Dashboard Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Dashboard Connection</em>'.
     * @see org.talend.core.model.properties.DashboardConnection
     * @generated
     */
    EClass getDashboardConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getId()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getLabel()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getDialect <em>Dialect</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dialect</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getDialect()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_Dialect();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getHost <em>Host</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Host</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getHost()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_Host();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getPort <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Port</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getPort()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_Port();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getDatabase <em>Database</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Database</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getDatabase()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_Database();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getUsername <em>Username</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Username</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getUsername()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_Username();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getPassword <em>Password</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Password</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getPassword()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_Password();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getLogTable <em>Log Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Log Table</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getLogTable()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_LogTable();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getStatTable <em>Stat Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Stat Table</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getStatTable()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_StatTable();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.DashboardConnection#getFlowMeterTable <em>Flow Meter Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Flow Meter Table</em>'.
     * @see org.talend.core.model.properties.DashboardConnection#getFlowMeterTable()
     * @see #getDashboardConnection()
     * @generated
     */
    EAttribute getDashboardConnection_FlowMeterTable();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ExecutionServer <em>Execution Server</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Execution Server</em>'.
     * @see org.talend.core.model.properties.ExecutionServer
     * @generated
     */
    EClass getExecutionServer();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionServer#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.ExecutionServer#getId()
     * @see #getExecutionServer()
     * @generated
     */
    EAttribute getExecutionServer_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionServer#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.ExecutionServer#getLabel()
     * @see #getExecutionServer()
     * @generated
     */
    EAttribute getExecutionServer_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionServer#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.core.model.properties.ExecutionServer#getDescription()
     * @see #getExecutionServer()
     * @generated
     */
    EAttribute getExecutionServer_Description();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionServer#getHost <em>Host</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Host</em>'.
     * @see org.talend.core.model.properties.ExecutionServer#getHost()
     * @see #getExecutionServer()
     * @generated
     */
    EAttribute getExecutionServer_Host();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionServer#getPort <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Port</em>'.
     * @see org.talend.core.model.properties.ExecutionServer#getPort()
     * @see #getExecutionServer()
     * @generated
     */
    EAttribute getExecutionServer_Port();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionServer#getFileTransfertPort <em>File Transfert Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Transfert Port</em>'.
     * @see org.talend.core.model.properties.ExecutionServer#getFileTransfertPort()
     * @see #getExecutionServer()
     * @generated
     */
    EAttribute getExecutionServer_FileTransfertPort();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionServer#isActive <em>Active</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Active</em>'.
     * @see org.talend.core.model.properties.ExecutionServer#isActive()
     * @see #getExecutionServer()
     * @generated
     */
    EAttribute getExecutionServer_Active();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionServer#getMonitoringPort <em>Monitoring Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Monitoring Port</em>'.
     * @see org.talend.core.model.properties.ExecutionServer#getMonitoringPort()
     * @see #getExecutionServer()
     * @generated
     */
    EAttribute getExecutionServer_MonitoringPort();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ExecutionTask <em>Execution Task</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Execution Task</em>'.
     * @see org.talend.core.model.properties.ExecutionTask
     * @generated
     */
    EClass getExecutionTask();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getId()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getLabel()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getDescription()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_Description();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ExecutionTask#getExecutionServer <em>Execution Server</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Execution Server</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getExecutionServer()
     * @see #getExecutionTask()
     * @generated
     */
    EReference getExecutionTask_ExecutionServer();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ExecutionTask#getProject <em>Project</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Project</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getProject()
     * @see #getExecutionTask()
     * @generated
     */
    EReference getExecutionTask_Project();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getContext <em>Context</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Context</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getContext()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_Context();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getJobVersion <em>Job Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Job Version</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getJobVersion()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_JobVersion();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#isActive <em>Active</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Active</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#isActive()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_Active();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getIdQuartzJob <em>Id Quartz Job</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Quartz Job</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getIdQuartzJob()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_IdQuartzJob();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getLastScriptGenerationDate <em>Last Script Generation Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Script Generation Date</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getLastScriptGenerationDate()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_LastScriptGenerationDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getIdRemoteJob <em>Id Remote Job</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Remote Job</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getIdRemoteJob()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_IdRemoteJob();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getIdRemoteJobExecution <em>Id Remote Job Execution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Remote Job Execution</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getIdRemoteJobExecution()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_IdRemoteJobExecution();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getChecksumArchive <em>Checksum Archive</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Checksum Archive</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getChecksumArchive()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_ChecksumArchive();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getJobScriptArchiveFilename <em>Job Script Archive Filename</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Job Script Archive Filename</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getJobScriptArchiveFilename()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_JobScriptArchiveFilename();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getStatus <em>Status</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Status</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getStatus()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_Status();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#isProcessingState <em>Processing State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Processing State</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#isProcessingState()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_ProcessingState();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getErrorStatus <em>Error Status</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Error Status</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getErrorStatus()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_ErrorStatus();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getLastRunDate <em>Last Run Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Run Date</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getLastRunDate()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_LastRunDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getLastDeploymentDate <em>Last Deployment Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Deployment Date</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getLastDeploymentDate()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_LastDeploymentDate();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getLastEndedRunDate <em>Last Ended Run Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Ended Run Date</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getLastEndedRunDate()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_LastEndedRunDate();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.ExecutionTask#getTriggers <em>Triggers</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Triggers</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getTriggers()
     * @see #getExecutionTask()
     * @generated
     */
    EReference getExecutionTask_Triggers();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getJobId <em>Job Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Job Id</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getJobId()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_JobId();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.ExecutionTask#getVirtualServer <em>Virtual Server</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Virtual Server</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getVirtualServer()
     * @see #getExecutionTask()
     * @generated
     */
    EReference getExecutionTask_VirtualServer();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#isConcurrentExecution <em>Concurrent Execution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Concurrent Execution</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#isConcurrentExecution()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_ConcurrentExecution();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getMaxConcurrentExecutions <em>Max Concurrent Executions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Concurrent Executions</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getMaxConcurrentExecutions()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_MaxConcurrentExecutions();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getGeneratedProjectName <em>Generated Project Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Generated Project Name</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getGeneratedProjectName()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_GeneratedProjectName();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#getGeneratedJobName <em>Generated Job Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Generated Job Name</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#getGeneratedJobName()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_GeneratedJobName();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionTask#isApplyContextToChildren <em>Apply Context To Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Apply Context To Children</em>'.
     * @see org.talend.core.model.properties.ExecutionTask#isApplyContextToChildren()
     * @see #getExecutionTask()
     * @generated
     */
    EAttribute getExecutionTask_ApplyContextToChildren();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.TalendTrigger <em>Talend Trigger</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Talend Trigger</em>'.
     * @see org.talend.core.model.properties.TalendTrigger
     * @generated
     */
    EClass getTalendTrigger();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getId()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#isActive <em>Active</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Active</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#isActive()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_Active();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getLabel()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getDescription()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_Description();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#getTriggerType <em>Trigger Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Trigger Type</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getTriggerType()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_TriggerType();

    /**
     * Returns the meta object for the container reference '{@link org.talend.core.model.properties.TalendTrigger#getExecutionTask <em>Execution Task</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Execution Task</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getExecutionTask()
     * @see #getTalendTrigger()
     * @generated
     */
    EReference getTalendTrigger_ExecutionTask();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#getStartTime <em>Start Time</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Start Time</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getStartTime()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_StartTime();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#getEndTime <em>End Time</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End Time</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getEndTime()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_EndTime();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#getPreviousFireTime <em>Previous Fire Time</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Previous Fire Time</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getPreviousFireTime()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_PreviousFireTime();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.TalendTrigger#getFinalFireTime <em>Final Fire Time</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Final Fire Time</em>'.
     * @see org.talend.core.model.properties.TalendTrigger#getFinalFireTime()
     * @see #getTalendTrigger()
     * @generated
     */
    EAttribute getTalendTrigger_FinalFireTime();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.CronTalendTrigger <em>Cron Talend Trigger</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Cron Talend Trigger</em>'.
     * @see org.talend.core.model.properties.CronTalendTrigger
     * @generated
     */
    EClass getCronTalendTrigger();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.CronTalendTrigger#getCronExpression <em>Cron Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Cron Expression</em>'.
     * @see org.talend.core.model.properties.CronTalendTrigger#getCronExpression()
     * @see #getCronTalendTrigger()
     * @generated
     */
    EAttribute getCronTalendTrigger_CronExpression();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.CronUITalendTrigger <em>Cron UI Talend Trigger</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Cron UI Talend Trigger</em>'.
     * @see org.talend.core.model.properties.CronUITalendTrigger
     * @generated
     */
    EClass getCronUITalendTrigger();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.CronUITalendTrigger#getListDaysOfWeek <em>List Days Of Week</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>List Days Of Week</em>'.
     * @see org.talend.core.model.properties.CronUITalendTrigger#getListDaysOfWeek()
     * @see #getCronUITalendTrigger()
     * @generated
     */
    EAttribute getCronUITalendTrigger_ListDaysOfWeek();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.CronUITalendTrigger#getListDaysOfMonth <em>List Days Of Month</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>List Days Of Month</em>'.
     * @see org.talend.core.model.properties.CronUITalendTrigger#getListDaysOfMonth()
     * @see #getCronUITalendTrigger()
     * @generated
     */
    EAttribute getCronUITalendTrigger_ListDaysOfMonth();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.CronUITalendTrigger#getListMonths <em>List Months</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>List Months</em>'.
     * @see org.talend.core.model.properties.CronUITalendTrigger#getListMonths()
     * @see #getCronUITalendTrigger()
     * @generated
     */
    EAttribute getCronUITalendTrigger_ListMonths();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.CronUITalendTrigger#getListYears <em>List Years</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>List Years</em>'.
     * @see org.talend.core.model.properties.CronUITalendTrigger#getListYears()
     * @see #getCronUITalendTrigger()
     * @generated
     */
    EAttribute getCronUITalendTrigger_ListYears();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.CronUITalendTrigger#getListHours <em>List Hours</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>List Hours</em>'.
     * @see org.talend.core.model.properties.CronUITalendTrigger#getListHours()
     * @see #getCronUITalendTrigger()
     * @generated
     */
    EAttribute getCronUITalendTrigger_ListHours();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.CronUITalendTrigger#getListMinutes <em>List Minutes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>List Minutes</em>'.
     * @see org.talend.core.model.properties.CronUITalendTrigger#getListMinutes()
     * @see #getCronUITalendTrigger()
     * @generated
     */
    EAttribute getCronUITalendTrigger_ListMinutes();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.SimpleTalendTrigger <em>Simple Talend Trigger</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simple Talend Trigger</em>'.
     * @see org.talend.core.model.properties.SimpleTalendTrigger
     * @generated
     */
    EClass getSimpleTalendTrigger();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SimpleTalendTrigger#getRepeatCount <em>Repeat Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Repeat Count</em>'.
     * @see org.talend.core.model.properties.SimpleTalendTrigger#getRepeatCount()
     * @see #getSimpleTalendTrigger()
     * @generated
     */
    EAttribute getSimpleTalendTrigger_RepeatCount();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SimpleTalendTrigger#getRepeatInterval <em>Repeat Interval</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Repeat Interval</em>'.
     * @see org.talend.core.model.properties.SimpleTalendTrigger#getRepeatInterval()
     * @see #getSimpleTalendTrigger()
     * @generated
     */
    EAttribute getSimpleTalendTrigger_RepeatInterval();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SimpleTalendTrigger#getTimesTriggered <em>Times Triggered</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Times Triggered</em>'.
     * @see org.talend.core.model.properties.SimpleTalendTrigger#getTimesTriggered()
     * @see #getSimpleTalendTrigger()
     * @generated
     */
    EAttribute getSimpleTalendTrigger_TimesTriggered();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ExecutionVirtualServer <em>Execution Virtual Server</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Execution Virtual Server</em>'.
     * @see org.talend.core.model.properties.ExecutionVirtualServer
     * @generated
     */
    EClass getExecutionVirtualServer();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionVirtualServer#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.ExecutionVirtualServer#getId()
     * @see #getExecutionVirtualServer()
     * @generated
     */
    EAttribute getExecutionVirtualServer_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionVirtualServer#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.ExecutionVirtualServer#getLabel()
     * @see #getExecutionVirtualServer()
     * @generated
     */
    EAttribute getExecutionVirtualServer_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionVirtualServer#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.core.model.properties.ExecutionVirtualServer#getDescription()
     * @see #getExecutionVirtualServer()
     * @generated
     */
    EAttribute getExecutionVirtualServer_Description();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ExecutionVirtualServer#isActive <em>Active</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Active</em>'.
     * @see org.talend.core.model.properties.ExecutionVirtualServer#isActive()
     * @see #getExecutionVirtualServer()
     * @generated
     */
    EAttribute getExecutionVirtualServer_Active();

    /**
     * Returns the meta object for the reference list '{@link org.talend.core.model.properties.ExecutionVirtualServer#getExecutionServers <em>Execution Servers</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Execution Servers</em>'.
     * @see org.talend.core.model.properties.ExecutionVirtualServer#getExecutionServers()
     * @see #getExecutionVirtualServer()
     * @generated
     */
    EReference getExecutionVirtualServer_ExecutionServers();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.FileTrigger <em>File Trigger</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>File Trigger</em>'.
     * @see org.talend.core.model.properties.FileTrigger
     * @generated
     */
    EClass getFileTrigger();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.properties.FileTrigger#getFileTriggerMasks <em>File Trigger Masks</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>File Trigger Masks</em>'.
     * @see org.talend.core.model.properties.FileTrigger#getFileTriggerMasks()
     * @see #getFileTrigger()
     * @generated
     */
    EReference getFileTrigger_FileTriggerMasks();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.FileTriggerMask <em>File Trigger Mask</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>File Trigger Mask</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask
     * @generated
     */
    EClass getFileTriggerMask();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileTriggerMask#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#getId()
     * @see #getFileTriggerMask()
     * @generated
     */
    EAttribute getFileTriggerMask_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileTriggerMask#isActive <em>Active</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Active</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#isActive()
     * @see #getFileTriggerMask()
     * @generated
     */
    EAttribute getFileTriggerMask_Active();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileTriggerMask#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#getLabel()
     * @see #getFileTriggerMask()
     * @generated
     */
    EAttribute getFileTriggerMask_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileTriggerMask#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#getDescription()
     * @see #getFileTriggerMask()
     * @generated
     */
    EAttribute getFileTriggerMask_Description();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.FileTriggerMask#getFileTrigger <em>File Trigger</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>File Trigger</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#getFileTrigger()
     * @see #getFileTriggerMask()
     * @generated
     */
    EReference getFileTriggerMask_FileTrigger();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileTriggerMask#getFolderPath <em>Folder Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Folder Path</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#getFolderPath()
     * @see #getFileTriggerMask()
     * @generated
     */
    EAttribute getFileTriggerMask_FolderPath();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileTriggerMask#getFileMask <em>File Mask</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Mask</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#getFileMask()
     * @see #getFileTriggerMask()
     * @generated
     */
    EAttribute getFileTriggerMask_FileMask();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.FileTriggerMask#getContextParameterBaseName <em>Context Parameter Base Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Context Parameter Base Name</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#getContextParameterBaseName()
     * @see #getFileTriggerMask()
     * @generated
     */
    EAttribute getFileTriggerMask_ContextParameterBaseName();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.FileTriggerMask#getCheckFileServer <em>Check File Server</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Check File Server</em>'.
     * @see org.talend.core.model.properties.FileTriggerMask#getCheckFileServer()
     * @see #getFileTriggerMask()
     * @generated
     */
    EReference getFileTriggerMask_CheckFileServer();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.JobletProcessItem <em>Joblet Process Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Joblet Process Item</em>'.
     * @see org.talend.core.model.properties.JobletProcessItem
     * @generated
     */
    EClass getJobletProcessItem();

    /**
     * Returns the meta object for the reference '{@link org.talend.core.model.properties.JobletProcessItem#getJobletProcess <em>Joblet Process</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Joblet Process</em>'.
     * @see org.talend.core.model.properties.JobletProcessItem#getJobletProcess()
     * @see #getJobletProcessItem()
     * @generated
     */
    EReference getJobletProcessItem_JobletProcess();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.JobDocumentationItem <em>Job Documentation Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Job Documentation Item</em>'.
     * @see org.talend.core.model.properties.JobDocumentationItem
     * @generated
     */
    EClass getJobDocumentationItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.JobletDocumentationItem <em>Joblet Documentation Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Joblet Documentation Item</em>'.
     * @see org.talend.core.model.properties.JobletDocumentationItem
     * @generated
     */
    EClass getJobletDocumentationItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.WSDLSchemaConnectionItem <em>WSDL Schema Connection Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>WSDL Schema Connection Item</em>'.
     * @see org.talend.core.model.properties.WSDLSchemaConnectionItem
     * @generated
     */
    EClass getWSDLSchemaConnectionItem();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.SchemaInformation <em>Schema Information</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Schema Information</em>'.
     * @see org.talend.core.model.properties.SchemaInformation
     * @generated
     */
    EClass getSchemaInformation();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SchemaInformation#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.talend.core.model.properties.SchemaInformation#getVersion()
     * @see #getSchemaInformation()
     * @generated
     */
    EAttribute getSchemaInformation_Version();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.Information <em>Information</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Information</em>'.
     * @see org.talend.core.model.properties.Information
     * @generated
     */
    EClass getInformation();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Information#getLevel <em>Level</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Level</em>'.
     * @see org.talend.core.model.properties.Information#getLevel()
     * @see #getInformation()
     * @generated
     */
    EAttribute getInformation_Level();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Information#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.talend.core.model.properties.Information#getType()
     * @see #getInformation()
     * @generated
     */
    EAttribute getInformation_Type();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.Information#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see org.talend.core.model.properties.Information#getText()
     * @see #getInformation()
     * @generated
     */
    EAttribute getInformation_Text();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.SQLPatternItem <em>SQL Pattern Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SQL Pattern Item</em>'.
     * @see org.talend.core.model.properties.SQLPatternItem
     * @generated
     */
    EClass getSQLPatternItem();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SQLPatternItem#isSystem <em>System</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>System</em>'.
     * @see org.talend.core.model.properties.SQLPatternItem#isSystem()
     * @see #getSQLPatternItem()
     * @generated
     */
    EAttribute getSQLPatternItem_System();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.SQLPatternItem#getEltName <em>Elt Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Elt Name</em>'.
     * @see org.talend.core.model.properties.SQLPatternItem#getEltName()
     * @see #getSQLPatternItem()
     * @generated
     */
    EAttribute getSQLPatternItem_EltName();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.ComponentSetting <em>Component Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Component Setting</em>'.
     * @see org.talend.core.model.properties.ComponentSetting
     * @generated
     */
    EClass getComponentSetting();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ComponentSetting#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.talend.core.model.properties.ComponentSetting#getName()
     * @see #getComponentSetting()
     * @generated
     */
    EAttribute getComponentSetting_Name();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.ComponentSetting#isHidden <em>Hidden</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Hidden</em>'.
     * @see org.talend.core.model.properties.ComponentSetting#isHidden()
     * @see #getComponentSetting()
     * @generated
     */
    EAttribute getComponentSetting_Hidden();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.properties.FolderType <em>Folder Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Folder Type</em>'.
     * @see org.talend.core.model.properties.FolderType
     * @generated
     */
    EEnum getFolderType();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.properties.UserProjectAuthorizationType <em>User Project Authorization Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>User Project Authorization Type</em>'.
     * @see org.talend.core.model.properties.UserProjectAuthorizationType
     * @generated
     */
    EEnum getUserProjectAuthorizationType();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.properties.UserModuleAuthorizationType <em>User Module Authorization Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>User Module Authorization Type</em>'.
     * @see org.talend.core.model.properties.UserModuleAuthorizationType
     * @generated
     */
    EEnum getUserModuleAuthorizationType();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.properties.InformationLevel <em>Information Level</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Information Level</em>'.
     * @see org.talend.core.model.properties.InformationLevel
     * @generated
     */
    EEnum getInformationLevel();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.properties.UserRole <em>User Role</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>User Role</em>'.
     * @see org.talend.core.model.properties.UserRole
     * @generated
     */
    EClass getUserRole();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.UserRole#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.properties.UserRole#getId()
     * @see #getUserRole()
     * @generated
     */
    EAttribute getUserRole_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.UserRole#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.talend.core.model.properties.UserRole#getName()
     * @see #getUserRole()
     * @generated
     */
    EAttribute getUserRole_Name();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.properties.UserRole#getLocalizedLabel <em>Localized Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Localized Label</em>'.
     * @see org.talend.core.model.properties.UserRole#getLocalizedLabel()
     * @see #getUserRole()
     * @generated
     */
    EAttribute getUserRole_LocalizedLabel();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiesFactory getPropertiesFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals  {

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.StatusImpl <em>Status</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.StatusImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getStatus()
         * @generated
         */
        EClass STATUS = eINSTANCE.getStatus();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute STATUS__LABEL = eINSTANCE.getStatus_Label();

        /**
         * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute STATUS__CODE = eINSTANCE.getStatus_Code();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ProjectImpl <em>Project</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ProjectImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProject()
         * @generated
         */
        EClass PROJECT = eINSTANCE.getProject();

        /**
         * The meta object literal for the '<em><b>Technical Status</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT__TECHNICAL_STATUS = eINSTANCE.getProject_TechnicalStatus();

        /**
         * The meta object literal for the '<em><b>Documentation Status</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT__DOCUMENTATION_STATUS = eINSTANCE.getProject_DocumentationStatus();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__ID = eINSTANCE.getProject_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__LABEL = eINSTANCE.getProject_Label();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__DESCRIPTION = eINSTANCE.getProject_Description();

        /**
         * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__LANGUAGE = eINSTANCE.getProject_Language();

        /**
         * The meta object literal for the '<em><b>Author</b></em>' reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference PROJECT__AUTHOR = eINSTANCE.getProject_Author();

        /**
         * The meta object literal for the '<em><b>User Authorization</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT__USER_AUTHORIZATION = eINSTANCE.getProject_UserAuthorization();

        /**
         * The meta object literal for the '<em><b>Allowed Components</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT__ALLOWED_COMPONENTS = eINSTANCE.getProject_AllowedComponents();

        /**
         * The meta object literal for the '<em><b>Referenced Projects</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT__REFERENCED_PROJECTS = eINSTANCE.getProject_ReferencedProjects();

        /**
         * The meta object literal for the '<em><b>Available Ref Project</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT__AVAILABLE_REF_PROJECT = eINSTANCE.getProject_AvailableRefProject();

        /**
         * The meta object literal for the '<em><b>Migration Tasks</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__MIGRATION_TASKS = eINSTANCE.getProject_MigrationTasks();

        /**
         * The meta object literal for the '<em><b>Master Job Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__MASTER_JOB_ID = eINSTANCE.getProject_MasterJobId();

        /**
         * The meta object literal for the '<em><b>Spago Bi Server</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT__SPAGO_BI_SERVER = eINSTANCE.getProject_SpagoBiServer();

        /**
         * The meta object literal for the '<em><b>Product Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__PRODUCT_VERSION = eINSTANCE.getProject_ProductVersion();

        /**
         * The meta object literal for the '<em><b>Components Settings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT__COMPONENTS_SETTINGS = eINSTANCE.getProject_ComponentsSettings();

        /**
         * The meta object literal for the '<em><b>Technical Label</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__TECHNICAL_LABEL = eINSTANCE.getProject_TechnicalLabel();

        /**
         * The meta object literal for the '<em><b>Local</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__LOCAL = eINSTANCE.getProject_Local();

        /**
         * The meta object literal for the '<em><b>Folders</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PROJECT__FOLDERS = eINSTANCE.getProject_Folders();

        /**
         * The meta object literal for the '<em><b>Deleted</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__DELETED = eINSTANCE.getProject_Deleted();

        /**
         * The meta object literal for the '<em><b>Delete Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__DELETE_DATE = eINSTANCE.getProject_DeleteDate();

        /**
         * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROJECT__CREATION_DATE = eINSTANCE.getProject_CreationDate();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.PropertyImpl <em>Property</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.PropertyImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProperty()
         * @generated
         */
        EClass PROPERTY = eINSTANCE.getProperty();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__ID = eINSTANCE.getProperty_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__LABEL = eINSTANCE.getProperty_Label();

        /**
         * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__PURPOSE = eINSTANCE.getProperty_Purpose();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__DESCRIPTION = eINSTANCE.getProperty_Description();

        /**
         * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__CREATION_DATE = eINSTANCE.getProperty_CreationDate();

        /**
         * The meta object literal for the '<em><b>Modification Date</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PROPERTY__MODIFICATION_DATE = eINSTANCE.getProperty_ModificationDate();

        /**
         * The meta object literal for the '<em><b>Author</b></em>' reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference PROPERTY__AUTHOR = eINSTANCE.getProperty_Author();

        /**
         * The meta object literal for the '<em><b>Informations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROPERTY__INFORMATIONS = eINSTANCE.getProperty_Informations();

        /**
         * The meta object literal for the '<em><b>Max Information Level</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__MAX_INFORMATION_LEVEL = eINSTANCE.getProperty_MaxInformationLevel();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__VERSION = eINSTANCE.getProperty_Version();

        /**
         * The meta object literal for the '<em><b>Status Code</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__STATUS_CODE = eINSTANCE.getProperty_StatusCode();

        /**
         * The meta object literal for the '<em><b>Item</b></em>' reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference PROPERTY__ITEM = eINSTANCE.getProperty_Item();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ItemImpl <em>Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getItem()
         * @generated
         */
        EClass ITEM = eINSTANCE.getItem();

        /**
         * The meta object literal for the '<em><b>Property</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ITEM__PROPERTY = eINSTANCE.getItem_Property();

        /**
         * The meta object literal for the '<em><b>State</b></em>' reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference ITEM__STATE = eINSTANCE.getItem_State();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.LinkDocumentationItemImpl <em>Link Documentation Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.LinkDocumentationItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLinkDocumentationItem()
         * @generated
         */
        EClass LINK_DOCUMENTATION_ITEM = eINSTANCE.getLinkDocumentationItem();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINK_DOCUMENTATION_ITEM__NAME = eINSTANCE.getLinkDocumentationItem_Name();

        /**
         * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINK_DOCUMENTATION_ITEM__EXTENSION = eINSTANCE.getLinkDocumentationItem_Extension();

        /**
         * The meta object literal for the '<em><b>Link</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LINK_DOCUMENTATION_ITEM__LINK = eINSTANCE.getLinkDocumentationItem_Link();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.LinkTypeImpl <em>Link Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.LinkTypeImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLinkType()
         * @generated
         */
        EClass LINK_TYPE = eINSTANCE.getLinkType();

        /**
         * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINK_TYPE__URI = eINSTANCE.getLinkType_URI();

        /**
         * The meta object literal for the '<em><b>State</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINK_TYPE__STATE = eINSTANCE.getLinkType_State();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.BusinessProcessItemImpl <em>Business Process Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.BusinessProcessItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getBusinessProcessItem()
         * @generated
         */
        EClass BUSINESS_PROCESS_ITEM = eINSTANCE.getBusinessProcessItem();

        /**
         * The meta object literal for the '<em><b>Notation</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_PROCESS_ITEM__NOTATION = eINSTANCE.getBusinessProcessItem_Notation();

        /**
         * The meta object literal for the '<em><b>Semantic</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_PROCESS_ITEM__SEMANTIC = eINSTANCE.getBusinessProcessItem_Semantic();

        /**
         * The meta object literal for the '<em><b>Notation Holder</b></em>' reference feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_PROCESS_ITEM__NOTATION_HOLDER = eINSTANCE.getBusinessProcessItem_NotationHolder();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ItemStateImpl <em>Item State</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ItemStateImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getItemState()
         * @generated
         */
        EClass ITEM_STATE = eINSTANCE.getItemState();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ITEM_STATE__PATH = eINSTANCE.getItemState_Path();

        /**
         * The meta object literal for the '<em><b>Deleted</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ITEM_STATE__DELETED = eINSTANCE.getItemState_Deleted();

        /**
         * The meta object literal for the '<em><b>Locked</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ITEM_STATE__LOCKED = eINSTANCE.getItemState_Locked();

        /**
         * The meta object literal for the '<em><b>Locker</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ITEM_STATE__LOCKER = eINSTANCE.getItemState_Locker();

        /**
         * The meta object literal for the '<em><b>Lock Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ITEM_STATE__LOCK_DATE = eINSTANCE.getItemState_LockDate();

        /**
         * The meta object literal for the '<em><b>Commit Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ITEM_STATE__COMMIT_DATE = eINSTANCE.getItemState_CommitDate();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.FileItemImpl <em>File Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.FileItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFileItem()
         * @generated
         */
        EClass FILE_ITEM = eINSTANCE.getFileItem();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FILE_ITEM__NAME = eINSTANCE.getFileItem_Name();

        /**
         * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_ITEM__EXTENSION = eINSTANCE.getFileItem_Extension();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FILE_ITEM__CONTENT = eINSTANCE.getFileItem_Content();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.DocumentationItemImpl <em>Documentation Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.DocumentationItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDocumentationItem()
         * @generated
         */
        EClass DOCUMENTATION_ITEM = eINSTANCE.getDocumentationItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.RoutineItemImpl <em>Routine Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.RoutineItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getRoutineItem()
         * @generated
         */
        EClass ROUTINE_ITEM = eINSTANCE.getRoutineItem();

        /**
         * The meta object literal for the '<em><b>Built In</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ROUTINE_ITEM__BUILT_IN = eINSTANCE.getRoutineItem_BuiltIn();

        /**
         * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ROUTINE_ITEM__IMPORTS = eINSTANCE.getRoutineItem_Imports();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ByteArrayImpl <em>Byte Array</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ByteArrayImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getByteArray()
         * @generated
         */
        EClass BYTE_ARRAY = eINSTANCE.getByteArray();

        /**
         * The meta object literal for the '<em><b>Inner Content</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute BYTE_ARRAY__INNER_CONTENT = eINSTANCE.getByteArray_InnerContent();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ConnectionItemImpl <em>Connection Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getConnectionItem()
         * @generated
         */
        EClass CONNECTION_ITEM = eINSTANCE.getConnectionItem();

        /**
         * The meta object literal for the '<em><b>Connection</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONNECTION_ITEM__CONNECTION = eINSTANCE.getConnectionItem_Connection();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.SnippetVariableImpl <em>Snippet Variable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.SnippetVariableImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSnippetVariable()
         * @generated
         */
        EClass SNIPPET_VARIABLE = eINSTANCE.getSnippetVariable();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SNIPPET_VARIABLE__NAME = eINSTANCE.getSnippetVariable_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SNIPPET_VARIABLE__VALUE = eINSTANCE.getSnippetVariable_Value();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SNIPPET_VARIABLE__DESCRIPTION = eINSTANCE.getSnippetVariable_Description();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SNIPPET_VARIABLE__ID = eINSTANCE.getSnippetVariable_Id();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.SnippetItemImpl <em>Snippet Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.SnippetItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSnippetItem()
         * @generated
         */
        EClass SNIPPET_ITEM = eINSTANCE.getSnippetItem();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SNIPPET_ITEM__NAME = eINSTANCE.getSnippetItem_Name();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SNIPPET_ITEM__CONTENT = eINSTANCE.getSnippetItem_Content();

        /**
         * The meta object literal for the '<em><b>Variables</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SNIPPET_ITEM__VARIABLES = eINSTANCE.getSnippetItem_Variables();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.DelimitedFileConnectionItemImpl <em>Delimited File Connection Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.DelimitedFileConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDelimitedFileConnectionItem()
         * @generated
         */
        EClass DELIMITED_FILE_CONNECTION_ITEM = eINSTANCE.getDelimitedFileConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.PositionalFileConnectionItemImpl <em>Positional File Connection Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.PositionalFileConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getPositionalFileConnectionItem()
         * @generated
         */
        EClass POSITIONAL_FILE_CONNECTION_ITEM = eINSTANCE.getPositionalFileConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.RegExFileConnectionItemImpl <em>Reg Ex File Connection Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.RegExFileConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getRegExFileConnectionItem()
         * @generated
         */
        EClass REG_EX_FILE_CONNECTION_ITEM = eINSTANCE.getRegExFileConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.CSVFileConnectionItemImpl <em>CSV File Connection Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.CSVFileConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getCSVFileConnectionItem()
         * @generated
         */
        EClass CSV_FILE_CONNECTION_ITEM = eINSTANCE.getCSVFileConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.DatabaseConnectionItemImpl <em>Database Connection Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.DatabaseConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDatabaseConnectionItem()
         * @generated
         */
        EClass DATABASE_CONNECTION_ITEM = eINSTANCE.getDatabaseConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ProcessItemImpl <em>Process Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ProcessItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProcessItem()
         * @generated
         */
        EClass PROCESS_ITEM = eINSTANCE.getProcessItem();

        /**
         * The meta object literal for the '<em><b>Process</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROCESS_ITEM__PROCESS = eINSTANCE.getProcessItem_Process();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.UserImpl <em>User</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.UserImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUser()
         * @generated
         */
        EClass USER = eINSTANCE.getUser();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute USER__ID = eINSTANCE.getUser_Id();

        /**
         * The meta object literal for the '<em><b>Login</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute USER__LOGIN = eINSTANCE.getUser_Login();

        /**
         * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__PASSWORD = eINSTANCE.getUser_Password();

        /**
         * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__FIRST_NAME = eINSTANCE.getUser_FirstName();

        /**
         * The meta object literal for the '<em><b>Last Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__LAST_NAME = eINSTANCE.getUser_LastName();

        /**
         * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__CREATION_DATE = eINSTANCE.getUser_CreationDate();

        /**
         * The meta object literal for the '<em><b>Delete Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__DELETE_DATE = eINSTANCE.getUser_DeleteDate();

        /**
         * The meta object literal for the '<em><b>Deleted</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__DELETED = eINSTANCE.getUser_Deleted();

        /**
         * The meta object literal for the '<em><b>Allowed To Modify Components</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER__ALLOWED_TO_MODIFY_COMPONENTS = eINSTANCE.getUser_AllowedToModifyComponents();

        /**
         * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__COMMENT = eINSTANCE.getUser_Comment();

        /**
         * The meta object literal for the '<em><b>Role</b></em>' reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference USER__ROLE = eINSTANCE.getUser_Role();

        /**
         * The meta object literal for the '<em><b>Project Authorization</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference USER__PROJECT_AUTHORIZATION = eINSTANCE.getUser_ProjectAuthorization();

        /**
         * The meta object literal for the '<em><b>Module Authorization</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference USER__MODULE_AUTHORIZATION = eINSTANCE.getUser_ModuleAuthorization();

        /**
         * The meta object literal for the '<em><b>Preferred Dashboard Connection</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference USER__PREFERRED_DASHBOARD_CONNECTION = eINSTANCE.getUser_PreferredDashboardConnection();

        /**
         * The meta object literal for the '<em><b>Last Admin Connection Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__LAST_ADMIN_CONNECTION_DATE = eINSTANCE.getUser_LastAdminConnectionDate();

        /**
         * The meta object literal for the '<em><b>Last Studio Connection Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__LAST_STUDIO_CONNECTION_DATE = eINSTANCE.getUser_LastStudioConnectionDate();

        /**
         * The meta object literal for the '<em><b>First Admin Connection Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__FIRST_ADMIN_CONNECTION_DATE = eINSTANCE.getUser_FirstAdminConnectionDate();

        /**
         * The meta object literal for the '<em><b>First Studio Connection Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__FIRST_STUDIO_CONNECTION_DATE = eINSTANCE.getUser_FirstStudioConnectionDate();

        /**
         * The meta object literal for the '<em><b>Admin Connexion Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__ADMIN_CONNEXION_NUMBER = eINSTANCE.getUser_AdminConnexionNumber();

        /**
         * The meta object literal for the '<em><b>Studio Connexion Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER__STUDIO_CONNEXION_NUMBER = eINSTANCE.getUser_StudioConnexionNumber();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.FolderItemImpl <em>Folder Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.FolderItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFolderItem()
         * @generated
         */
        EClass FOLDER_ITEM = eINSTANCE.getFolderItem();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FOLDER_ITEM__CHILDREN = eINSTANCE.getFolderItem_Children();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FOLDER_ITEM__TYPE = eINSTANCE.getFolderItem_Type();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ComponentImpl <em>Component</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ComponentImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getComponent()
         * @generated
         */
        EClass COMPONENT = eINSTANCE.getComponent();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT__ID = eINSTANCE.getComponent_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT__LABEL = eINSTANCE.getComponent_Label();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT__VERSION = eINSTANCE.getComponent_Version();

        /**
         * The meta object literal for the '<em><b>Last Update Date</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPONENT__LAST_UPDATE_DATE = eINSTANCE.getComponent_LastUpdateDate();

        /**
         * The meta object literal for the '<em><b>Projects</b></em>' reference list feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference COMPONENT__PROJECTS = eINSTANCE.getComponent_Projects();

        /**
         * The meta object literal for the '<em><b>Author</b></em>' reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference COMPONENT__AUTHOR = eINSTANCE.getComponent_Author();

        /**
         * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT__CREATION_DATE = eINSTANCE.getComponent_CreationDate();

        /**
         * The meta object literal for the '<em><b>Delete Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT__DELETE_DATE = eINSTANCE.getComponent_DeleteDate();

        /**
         * The meta object literal for the '<em><b>Deleted</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT__DELETED = eINSTANCE.getComponent_Deleted();

        /**
         * The meta object literal for the '<em><b>File Descriptor</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT__FILE_DESCRIPTOR = eINSTANCE.getComponent_FileDescriptor();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.XmlFileConnectionItemImpl <em>Xml File Connection Item</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.XmlFileConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getXmlFileConnectionItem()
         * @generated
         */
        EClass XML_FILE_CONNECTION_ITEM = eINSTANCE.getXmlFileConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.NotationHolderImpl <em>Notation Holder</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.NotationHolderImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getNotationHolder()
         * @generated
         */
        EClass NOTATION_HOLDER = eINSTANCE.getNotationHolder();

        /**
         * The meta object literal for the '<em><b>Notation String</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute NOTATION_HOLDER__NOTATION_STRING = eINSTANCE.getNotationHolder_NotationString();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ProjectComponentAuthorisationImpl <em>Project Component Authorisation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ProjectComponentAuthorisationImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProjectComponentAuthorisation()
         * @generated
         */
        EClass PROJECT_COMPONENT_AUTHORISATION = eINSTANCE.getProjectComponentAuthorisation();

        /**
         * The meta object literal for the '<em><b>Project</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT_COMPONENT_AUTHORISATION__PROJECT = eINSTANCE.getProjectComponentAuthorisation_Project();

        /**
         * The meta object literal for the '<em><b>Component</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT_COMPONENT_AUTHORISATION__COMPONENT = eINSTANCE.getProjectComponentAuthorisation_Component();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ProjectReferenceImpl <em>Project Reference</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ProjectReferenceImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProjectReference()
         * @generated
         */
        EClass PROJECT_REFERENCE = eINSTANCE.getProjectReference();

        /**
         * The meta object literal for the '<em><b>Project</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT_REFERENCE__PROJECT = eINSTANCE.getProjectReference_Project();

        /**
         * The meta object literal for the '<em><b>Referenced Project</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROJECT_REFERENCE__REFERENCED_PROJECT = eINSTANCE.getProjectReference_ReferencedProject();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.LdifFileConnectionItemImpl <em>Ldif File Connection Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.LdifFileConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLdifFileConnectionItem()
         * @generated
         */
        EClass LDIF_FILE_CONNECTION_ITEM = eINSTANCE.getLdifFileConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ExcelFileConnectionItemImpl <em>Excel File Connection Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ExcelFileConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getExcelFileConnectionItem()
         * @generated
         */
        EClass EXCEL_FILE_CONNECTION_ITEM = eINSTANCE.getExcelFileConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.UserProjectAuthorizationImpl <em>User Project Authorization</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.UserProjectAuthorizationImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserProjectAuthorization()
         * @generated
         */
        EClass USER_PROJECT_AUTHORIZATION = eINSTANCE.getUserProjectAuthorization();

        /**
         * The meta object literal for the '<em><b>User</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference USER_PROJECT_AUTHORIZATION__USER = eINSTANCE.getUserProjectAuthorization_User();

        /**
         * The meta object literal for the '<em><b>Project</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference USER_PROJECT_AUTHORIZATION__PROJECT = eINSTANCE.getUserProjectAuthorization_Project();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER_PROJECT_AUTHORIZATION__TYPE = eINSTANCE.getUserProjectAuthorization_Type();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ContextItemImpl <em>Context Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ContextItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getContextItem()
         * @generated
         */
        EClass CONTEXT_ITEM = eINSTANCE.getContextItem();

        /**
         * The meta object literal for the '<em><b>Context</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONTEXT_ITEM__CONTEXT = eINSTANCE.getContextItem_Context();

        /**
         * The meta object literal for the '<em><b>Default Context</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONTEXT_ITEM__DEFAULT_CONTEXT = eINSTANCE.getContextItem_DefaultContext();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.SpagoBiServerImpl <em>Spago Bi Server</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.SpagoBiServerImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSpagoBiServer()
         * @generated
         */
        EClass SPAGO_BI_SERVER = eINSTANCE.getSpagoBiServer();

        /**
         * The meta object literal for the '<em><b>Engine Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPAGO_BI_SERVER__ENGINE_NAME = eINSTANCE.getSpagoBiServer_EngineName();

        /**
         * The meta object literal for the '<em><b>Short Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPAGO_BI_SERVER__SHORT_DESCRIPTION = eINSTANCE.getSpagoBiServer_ShortDescription();

        /**
         * The meta object literal for the '<em><b>Host</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPAGO_BI_SERVER__HOST = eINSTANCE.getSpagoBiServer_Host();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPAGO_BI_SERVER__PORT = eINSTANCE.getSpagoBiServer_Port();

        /**
         * The meta object literal for the '<em><b>Login</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPAGO_BI_SERVER__LOGIN = eINSTANCE.getSpagoBiServer_Login();

        /**
         * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPAGO_BI_SERVER__PASSWORD = eINSTANCE.getSpagoBiServer_Password();

        /**
         * The meta object literal for the '<em><b>Application Context</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPAGO_BI_SERVER__APPLICATION_CONTEXT = eINSTANCE.getSpagoBiServer_ApplicationContext();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.LicenseImpl <em>License</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.LicenseImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLicense()
         * @generated
         */
        EClass LICENSE = eINSTANCE.getLicense();

        /**
         * The meta object literal for the '<em><b>License</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LICENSE__LICENSE = eINSTANCE.getLicense_License();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.GenericSchemaConnectionItemImpl <em>Generic Schema Connection Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.GenericSchemaConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getGenericSchemaConnectionItem()
         * @generated
         */
        EClass GENERIC_SCHEMA_CONNECTION_ITEM = eINSTANCE.getGenericSchemaConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.UserModuleAuthorizationImpl <em>User Module Authorization</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.UserModuleAuthorizationImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserModuleAuthorization()
         * @generated
         */
        EClass USER_MODULE_AUTHORIZATION = eINSTANCE.getUserModuleAuthorization();

        /**
         * The meta object literal for the '<em><b>User</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference USER_MODULE_AUTHORIZATION__USER = eINSTANCE.getUserModuleAuthorization_User();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER_MODULE_AUTHORIZATION__TYPE = eINSTANCE.getUserModuleAuthorization_Type();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.LDAPSchemaConnectionItemImpl <em>LDAP Schema Connection Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.LDAPSchemaConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLDAPSchemaConnectionItem()
         * @generated
         */
        EClass LDAP_SCHEMA_CONNECTION_ITEM = eINSTANCE.getLDAPSchemaConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.SalesforceSchemaConnectionItemImpl <em>Salesforce Schema Connection Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.SalesforceSchemaConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSalesforceSchemaConnectionItem()
         * @generated
         */
        EClass SALESFORCE_SCHEMA_CONNECTION_ITEM = eINSTANCE.getSalesforceSchemaConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.DashboardConnectionImpl <em>Dashboard Connection</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.DashboardConnectionImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDashboardConnection()
         * @generated
         */
        EClass DASHBOARD_CONNECTION = eINSTANCE.getDashboardConnection();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__ID = eINSTANCE.getDashboardConnection_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__LABEL = eINSTANCE.getDashboardConnection_Label();

        /**
         * The meta object literal for the '<em><b>Dialect</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__DIALECT = eINSTANCE.getDashboardConnection_Dialect();

        /**
         * The meta object literal for the '<em><b>Host</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__HOST = eINSTANCE.getDashboardConnection_Host();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__PORT = eINSTANCE.getDashboardConnection_Port();

        /**
         * The meta object literal for the '<em><b>Database</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__DATABASE = eINSTANCE.getDashboardConnection_Database();

        /**
         * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__USERNAME = eINSTANCE.getDashboardConnection_Username();

        /**
         * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__PASSWORD = eINSTANCE.getDashboardConnection_Password();

        /**
         * The meta object literal for the '<em><b>Log Table</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__LOG_TABLE = eINSTANCE.getDashboardConnection_LogTable();

        /**
         * The meta object literal for the '<em><b>Stat Table</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__STAT_TABLE = eINSTANCE.getDashboardConnection_StatTable();

        /**
         * The meta object literal for the '<em><b>Flow Meter Table</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DASHBOARD_CONNECTION__FLOW_METER_TABLE = eINSTANCE.getDashboardConnection_FlowMeterTable();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ExecutionServerImpl <em>Execution Server</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ExecutionServerImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getExecutionServer()
         * @generated
         */
        EClass EXECUTION_SERVER = eINSTANCE.getExecutionServer();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_SERVER__ID = eINSTANCE.getExecutionServer_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_SERVER__LABEL = eINSTANCE.getExecutionServer_Label();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_SERVER__DESCRIPTION = eINSTANCE.getExecutionServer_Description();

        /**
         * The meta object literal for the '<em><b>Host</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_SERVER__HOST = eINSTANCE.getExecutionServer_Host();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_SERVER__PORT = eINSTANCE.getExecutionServer_Port();

        /**
         * The meta object literal for the '<em><b>File Transfert Port</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_SERVER__FILE_TRANSFERT_PORT = eINSTANCE.getExecutionServer_FileTransfertPort();

        /**
         * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_SERVER__ACTIVE = eINSTANCE.getExecutionServer_Active();

        /**
         * The meta object literal for the '<em><b>Monitoring Port</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_SERVER__MONITORING_PORT = eINSTANCE.getExecutionServer_MonitoringPort();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ExecutionTaskImpl <em>Execution Task</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ExecutionTaskImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getExecutionTask()
         * @generated
         */
        EClass EXECUTION_TASK = eINSTANCE.getExecutionTask();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__ID = eINSTANCE.getExecutionTask_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__LABEL = eINSTANCE.getExecutionTask_Label();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__DESCRIPTION = eINSTANCE.getExecutionTask_Description();

        /**
         * The meta object literal for the '<em><b>Execution Server</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXECUTION_TASK__EXECUTION_SERVER = eINSTANCE.getExecutionTask_ExecutionServer();

        /**
         * The meta object literal for the '<em><b>Project</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXECUTION_TASK__PROJECT = eINSTANCE.getExecutionTask_Project();

        /**
         * The meta object literal for the '<em><b>Context</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__CONTEXT = eINSTANCE.getExecutionTask_Context();

        /**
         * The meta object literal for the '<em><b>Job Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__JOB_VERSION = eINSTANCE.getExecutionTask_JobVersion();

        /**
         * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__ACTIVE = eINSTANCE.getExecutionTask_Active();

        /**
         * The meta object literal for the '<em><b>Id Quartz Job</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__ID_QUARTZ_JOB = eINSTANCE.getExecutionTask_IdQuartzJob();

        /**
         * The meta object literal for the '<em><b>Last Script Generation Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__LAST_SCRIPT_GENERATION_DATE = eINSTANCE.getExecutionTask_LastScriptGenerationDate();

        /**
         * The meta object literal for the '<em><b>Id Remote Job</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__ID_REMOTE_JOB = eINSTANCE.getExecutionTask_IdRemoteJob();

        /**
         * The meta object literal for the '<em><b>Id Remote Job Execution</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__ID_REMOTE_JOB_EXECUTION = eINSTANCE.getExecutionTask_IdRemoteJobExecution();

        /**
         * The meta object literal for the '<em><b>Checksum Archive</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__CHECKSUM_ARCHIVE = eINSTANCE.getExecutionTask_ChecksumArchive();

        /**
         * The meta object literal for the '<em><b>Job Script Archive Filename</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__JOB_SCRIPT_ARCHIVE_FILENAME = eINSTANCE.getExecutionTask_JobScriptArchiveFilename();

        /**
         * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__STATUS = eINSTANCE.getExecutionTask_Status();

        /**
         * The meta object literal for the '<em><b>Processing State</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__PROCESSING_STATE = eINSTANCE.getExecutionTask_ProcessingState();

        /**
         * The meta object literal for the '<em><b>Error Status</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__ERROR_STATUS = eINSTANCE.getExecutionTask_ErrorStatus();

        /**
         * The meta object literal for the '<em><b>Last Run Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__LAST_RUN_DATE = eINSTANCE.getExecutionTask_LastRunDate();

        /**
         * The meta object literal for the '<em><b>Last Deployment Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__LAST_DEPLOYMENT_DATE = eINSTANCE.getExecutionTask_LastDeploymentDate();

        /**
         * The meta object literal for the '<em><b>Last Ended Run Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__LAST_ENDED_RUN_DATE = eINSTANCE.getExecutionTask_LastEndedRunDate();

        /**
         * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXECUTION_TASK__TRIGGERS = eINSTANCE.getExecutionTask_Triggers();

        /**
         * The meta object literal for the '<em><b>Job Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__JOB_ID = eINSTANCE.getExecutionTask_JobId();

        /**
         * The meta object literal for the '<em><b>Virtual Server</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXECUTION_TASK__VIRTUAL_SERVER = eINSTANCE.getExecutionTask_VirtualServer();

        /**
         * The meta object literal for the '<em><b>Concurrent Execution</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__CONCURRENT_EXECUTION = eINSTANCE.getExecutionTask_ConcurrentExecution();

        /**
         * The meta object literal for the '<em><b>Max Concurrent Executions</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__MAX_CONCURRENT_EXECUTIONS = eINSTANCE.getExecutionTask_MaxConcurrentExecutions();

        /**
         * The meta object literal for the '<em><b>Generated Project Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__GENERATED_PROJECT_NAME = eINSTANCE.getExecutionTask_GeneratedProjectName();

        /**
         * The meta object literal for the '<em><b>Generated Job Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__GENERATED_JOB_NAME = eINSTANCE.getExecutionTask_GeneratedJobName();

        /**
         * The meta object literal for the '<em><b>Apply Context To Children</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_TASK__APPLY_CONTEXT_TO_CHILDREN = eINSTANCE.getExecutionTask_ApplyContextToChildren();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.TalendTriggerImpl <em>Talend Trigger</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.TalendTriggerImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getTalendTrigger()
         * @generated
         */
        EClass TALEND_TRIGGER = eINSTANCE.getTalendTrigger();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__ID = eINSTANCE.getTalendTrigger_Id();

        /**
         * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__ACTIVE = eINSTANCE.getTalendTrigger_Active();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__LABEL = eINSTANCE.getTalendTrigger_Label();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__DESCRIPTION = eINSTANCE.getTalendTrigger_Description();

        /**
         * The meta object literal for the '<em><b>Trigger Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__TRIGGER_TYPE = eINSTANCE.getTalendTrigger_TriggerType();

        /**
         * The meta object literal for the '<em><b>Execution Task</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TALEND_TRIGGER__EXECUTION_TASK = eINSTANCE.getTalendTrigger_ExecutionTask();

        /**
         * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__START_TIME = eINSTANCE.getTalendTrigger_StartTime();

        /**
         * The meta object literal for the '<em><b>End Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__END_TIME = eINSTANCE.getTalendTrigger_EndTime();

        /**
         * The meta object literal for the '<em><b>Previous Fire Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__PREVIOUS_FIRE_TIME = eINSTANCE.getTalendTrigger_PreviousFireTime();

        /**
         * The meta object literal for the '<em><b>Final Fire Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TALEND_TRIGGER__FINAL_FIRE_TIME = eINSTANCE.getTalendTrigger_FinalFireTime();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl <em>Cron Talend Trigger</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.CronTalendTriggerImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getCronTalendTrigger()
         * @generated
         */
        EClass CRON_TALEND_TRIGGER = eINSTANCE.getCronTalendTrigger();

        /**
         * The meta object literal for the '<em><b>Cron Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CRON_TALEND_TRIGGER__CRON_EXPRESSION = eINSTANCE.getCronTalendTrigger_CronExpression();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.CronUITalendTriggerImpl <em>Cron UI Talend Trigger</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.CronUITalendTriggerImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getCronUITalendTrigger()
         * @generated
         */
        EClass CRON_UI_TALEND_TRIGGER = eINSTANCE.getCronUITalendTrigger();

        /**
         * The meta object literal for the '<em><b>List Days Of Week</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CRON_UI_TALEND_TRIGGER__LIST_DAYS_OF_WEEK = eINSTANCE.getCronUITalendTrigger_ListDaysOfWeek();

        /**
         * The meta object literal for the '<em><b>List Days Of Month</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CRON_UI_TALEND_TRIGGER__LIST_DAYS_OF_MONTH = eINSTANCE.getCronUITalendTrigger_ListDaysOfMonth();

        /**
         * The meta object literal for the '<em><b>List Months</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CRON_UI_TALEND_TRIGGER__LIST_MONTHS = eINSTANCE.getCronUITalendTrigger_ListMonths();

        /**
         * The meta object literal for the '<em><b>List Years</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CRON_UI_TALEND_TRIGGER__LIST_YEARS = eINSTANCE.getCronUITalendTrigger_ListYears();

        /**
         * The meta object literal for the '<em><b>List Hours</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CRON_UI_TALEND_TRIGGER__LIST_HOURS = eINSTANCE.getCronUITalendTrigger_ListHours();

        /**
         * The meta object literal for the '<em><b>List Minutes</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CRON_UI_TALEND_TRIGGER__LIST_MINUTES = eINSTANCE.getCronUITalendTrigger_ListMinutes();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.SimpleTalendTriggerImpl <em>Simple Talend Trigger</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.SimpleTalendTriggerImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSimpleTalendTrigger()
         * @generated
         */
        EClass SIMPLE_TALEND_TRIGGER = eINSTANCE.getSimpleTalendTrigger();

        /**
         * The meta object literal for the '<em><b>Repeat Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMPLE_TALEND_TRIGGER__REPEAT_COUNT = eINSTANCE.getSimpleTalendTrigger_RepeatCount();

        /**
         * The meta object literal for the '<em><b>Repeat Interval</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMPLE_TALEND_TRIGGER__REPEAT_INTERVAL = eINSTANCE.getSimpleTalendTrigger_RepeatInterval();

        /**
         * The meta object literal for the '<em><b>Times Triggered</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMPLE_TALEND_TRIGGER__TIMES_TRIGGERED = eINSTANCE.getSimpleTalendTrigger_TimesTriggered();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ExecutionVirtualServerImpl <em>Execution Virtual Server</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ExecutionVirtualServerImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getExecutionVirtualServer()
         * @generated
         */
        EClass EXECUTION_VIRTUAL_SERVER = eINSTANCE.getExecutionVirtualServer();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_VIRTUAL_SERVER__ID = eINSTANCE.getExecutionVirtualServer_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_VIRTUAL_SERVER__LABEL = eINSTANCE.getExecutionVirtualServer_Label();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_VIRTUAL_SERVER__DESCRIPTION = eINSTANCE.getExecutionVirtualServer_Description();

        /**
         * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXECUTION_VIRTUAL_SERVER__ACTIVE = eINSTANCE.getExecutionVirtualServer_Active();

        /**
         * The meta object literal for the '<em><b>Execution Servers</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXECUTION_VIRTUAL_SERVER__EXECUTION_SERVERS = eINSTANCE.getExecutionVirtualServer_ExecutionServers();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.FileTriggerImpl <em>File Trigger</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.FileTriggerImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFileTrigger()
         * @generated
         */
        EClass FILE_TRIGGER = eINSTANCE.getFileTrigger();

        /**
         * The meta object literal for the '<em><b>File Trigger Masks</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FILE_TRIGGER__FILE_TRIGGER_MASKS = eINSTANCE.getFileTrigger_FileTriggerMasks();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.FileTriggerMaskImpl <em>File Trigger Mask</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.FileTriggerMaskImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFileTriggerMask()
         * @generated
         */
        EClass FILE_TRIGGER_MASK = eINSTANCE.getFileTriggerMask();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_TRIGGER_MASK__ID = eINSTANCE.getFileTriggerMask_Id();

        /**
         * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_TRIGGER_MASK__ACTIVE = eINSTANCE.getFileTriggerMask_Active();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_TRIGGER_MASK__LABEL = eINSTANCE.getFileTriggerMask_Label();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_TRIGGER_MASK__DESCRIPTION = eINSTANCE.getFileTriggerMask_Description();

        /**
         * The meta object literal for the '<em><b>File Trigger</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FILE_TRIGGER_MASK__FILE_TRIGGER = eINSTANCE.getFileTriggerMask_FileTrigger();

        /**
         * The meta object literal for the '<em><b>Folder Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_TRIGGER_MASK__FOLDER_PATH = eINSTANCE.getFileTriggerMask_FolderPath();

        /**
         * The meta object literal for the '<em><b>File Mask</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_TRIGGER_MASK__FILE_MASK = eINSTANCE.getFileTriggerMask_FileMask();

        /**
         * The meta object literal for the '<em><b>Context Parameter Base Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_TRIGGER_MASK__CONTEXT_PARAMETER_BASE_NAME = eINSTANCE.getFileTriggerMask_ContextParameterBaseName();

        /**
         * The meta object literal for the '<em><b>Check File Server</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FILE_TRIGGER_MASK__CHECK_FILE_SERVER = eINSTANCE.getFileTriggerMask_CheckFileServer();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.JobletProcessItemImpl <em>Joblet Process Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.JobletProcessItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getJobletProcessItem()
         * @generated
         */
        EClass JOBLET_PROCESS_ITEM = eINSTANCE.getJobletProcessItem();

        /**
         * The meta object literal for the '<em><b>Joblet Process</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference JOBLET_PROCESS_ITEM__JOBLET_PROCESS = eINSTANCE.getJobletProcessItem_JobletProcess();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.JobDocumentationItemImpl <em>Job Documentation Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.JobDocumentationItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getJobDocumentationItem()
         * @generated
         */
        EClass JOB_DOCUMENTATION_ITEM = eINSTANCE.getJobDocumentationItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.JobletDocumentationItemImpl <em>Joblet Documentation Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.JobletDocumentationItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getJobletDocumentationItem()
         * @generated
         */
        EClass JOBLET_DOCUMENTATION_ITEM = eINSTANCE.getJobletDocumentationItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.WSDLSchemaConnectionItemImpl <em>WSDL Schema Connection Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.WSDLSchemaConnectionItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getWSDLSchemaConnectionItem()
         * @generated
         */
        EClass WSDL_SCHEMA_CONNECTION_ITEM = eINSTANCE.getWSDLSchemaConnectionItem();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.SchemaInformationImpl <em>Schema Information</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.SchemaInformationImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSchemaInformation()
         * @generated
         */
        EClass SCHEMA_INFORMATION = eINSTANCE.getSchemaInformation();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_INFORMATION__VERSION = eINSTANCE.getSchemaInformation_Version();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.InformationImpl <em>Information</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.InformationImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getInformation()
         * @generated
         */
        EClass INFORMATION = eINSTANCE.getInformation();

        /**
         * The meta object literal for the '<em><b>Level</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INFORMATION__LEVEL = eINSTANCE.getInformation_Level();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INFORMATION__TYPE = eINSTANCE.getInformation_Type();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INFORMATION__TEXT = eINSTANCE.getInformation_Text();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.SQLPatternItemImpl <em>SQL Pattern Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.SQLPatternItemImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getSQLPatternItem()
         * @generated
         */
        EClass SQL_PATTERN_ITEM = eINSTANCE.getSQLPatternItem();

        /**
         * The meta object literal for the '<em><b>System</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SQL_PATTERN_ITEM__SYSTEM = eINSTANCE.getSQLPatternItem_System();

        /**
         * The meta object literal for the '<em><b>Elt Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SQL_PATTERN_ITEM__ELT_NAME = eINSTANCE.getSQLPatternItem_EltName();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.ComponentSettingImpl <em>Component Setting</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.ComponentSettingImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getComponentSetting()
         * @generated
         */
        EClass COMPONENT_SETTING = eINSTANCE.getComponentSetting();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT_SETTING__NAME = eINSTANCE.getComponentSetting_Name();

        /**
         * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT_SETTING__HIDDEN = eINSTANCE.getComponentSetting_Hidden();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.FolderType <em>Folder Type</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.FolderType
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFolderType()
         * @generated
         */
        EEnum FOLDER_TYPE = eINSTANCE.getFolderType();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.UserProjectAuthorizationType <em>User Project Authorization Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.UserProjectAuthorizationType
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserProjectAuthorizationType()
         * @generated
         */
        EEnum USER_PROJECT_AUTHORIZATION_TYPE = eINSTANCE.getUserProjectAuthorizationType();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.UserModuleAuthorizationType <em>User Module Authorization Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.UserModuleAuthorizationType
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserModuleAuthorizationType()
         * @generated
         */
        EEnum USER_MODULE_AUTHORIZATION_TYPE = eINSTANCE.getUserModuleAuthorizationType();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.InformationLevel <em>Information Level</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.properties.InformationLevel
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getInformationLevel()
         * @generated
         */
        EEnum INFORMATION_LEVEL = eINSTANCE.getInformationLevel();

        /**
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.UserRoleImpl <em>User Role</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.UserRoleImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserRole()
         * @generated
         */
        EClass USER_ROLE = eINSTANCE.getUserRole();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER_ROLE__ID = eINSTANCE.getUserRole_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute USER_ROLE__NAME = eINSTANCE.getUserRole_Name();

        /**
         * The meta object literal for the '<em><b>Localized Label</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER_ROLE__LOCALIZED_LABEL = eINSTANCE.getUserRole_LocalizedLabel();

    }

} // PropertiesPackage
