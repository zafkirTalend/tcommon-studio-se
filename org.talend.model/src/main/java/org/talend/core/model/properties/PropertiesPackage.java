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
    String eNAME = "properties";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.talend.org/properties";

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "TalendProperties";

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
    int STATUS = 1;

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
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__LABEL = 2;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT__DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Language</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__LANGUAGE = 4;

    /**
     * The feature id for the '<em><b>Technical Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__TECHNICAL_LABEL = 5;

    /**
     * The feature id for the '<em><b>Local</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__LOCAL = 6;

    /**
     * The feature id for the '<em><b>Folders</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__FOLDERS = 7;

    /**
     * The feature id for the '<em><b>Deleted</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__DELETED = 8;

    /**
     * The feature id for the '<em><b>Delete Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT__DELETE_DATE = 9;

    /**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT__CREATION_DATE = 10;

    /**
     * The feature id for the '<em><b>Author</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__AUTHOR = 11;

    /**
     * The feature id for the '<em><b>User Authorization</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__USER_AUTHORIZATION = 12;

    /**
     * The feature id for the '<em><b>Allowed Components</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__ALLOWED_COMPONENTS = 13;

    /**
     * The feature id for the '<em><b>Referenced Projects</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__REFERENCED_PROJECTS = 14;

    /**
     * The feature id for the '<em><b>Available Ref Project</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROJECT__AVAILABLE_REF_PROJECT = 15;

    /**
     * The number of structural features of the '<em>Project</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_FEATURE_COUNT = 16;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.PropertyImpl <em>Property</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.PropertyImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProperty()
     * @generated
     */
    int PROPERTY = 3;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ItemImpl <em>Item</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.talend.core.model.properties.impl.ItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getItem()
     * @generated
     */
    int ITEM = 4;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.BusinessProcessItemImpl <em>Business Process Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.BusinessProcessItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getBusinessProcessItem()
     * @generated
     */
    int BUSINESS_PROCESS_ITEM = 5;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ItemStateImpl <em>Item State</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ItemStateImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getItemState()
     * @generated
     */
    int ITEM_STATE = 2;

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
     * The number of structural features of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int PROPERTY_FEATURE_COUNT = 10;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.FileItemImpl <em>File Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.FileItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFileItem()
     * @generated
     */
    int FILE_ITEM = 6;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.DocumentationItemImpl <em>Documentation Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.DocumentationItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDocumentationItem()
     * @generated
     */
    int DOCUMENTATION_ITEM = 8;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.RoutineItemImpl <em>Routine Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.RoutineItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getRoutineItem()
     * @generated
     */
    int ROUTINE_ITEM = 9;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ByteArrayImpl <em>Byte Array</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ByteArrayImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getByteArray()
     * @generated
     */
    int BYTE_ARRAY = 7;

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
     * The number of structural features of the '<em>Routine Item</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int ROUTINE_ITEM_FEATURE_COUNT = FILE_ITEM_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ConnectionItemImpl <em>Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getConnectionItem()
     * @generated
     */
    int CONNECTION_ITEM = 10;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.DelimitedFileConnectionItemImpl <em>Delimited File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.DelimitedFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDelimitedFileConnectionItem()
     * @generated
     */
    int DELIMITED_FILE_CONNECTION_ITEM = 11;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.PositionalFileConnectionItemImpl <em>Positional File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.PositionalFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getPositionalFileConnectionItem()
     * @generated
     */
    int POSITIONAL_FILE_CONNECTION_ITEM = 12;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.RegExFileConnectionItemImpl <em>Reg Ex File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.RegExFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getRegExFileConnectionItem()
     * @generated
     */
    int REG_EX_FILE_CONNECTION_ITEM = 13;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.CSVFileConnectionItemImpl <em>CSV File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.CSVFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getCSVFileConnectionItem()
     * @generated
     */
    int CSV_FILE_CONNECTION_ITEM = 14;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.DatabaseConnectionItemImpl <em>Database Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.DatabaseConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getDatabaseConnectionItem()
     * @generated
     */
    int DATABASE_CONNECTION_ITEM = 15;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ProcessItemImpl <em>Process Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ProcessItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProcessItem()
     * @generated
     */
    int PROCESS_ITEM = 17;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.UserImpl <em>User</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.talend.core.model.properties.impl.UserImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUser()
     * @generated
     */
    int USER = 19;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.FolderItemImpl <em>Folder Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.FolderItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFolderItem()
     * @generated
     */
    int FOLDER_ITEM = 20;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.UserRoleImpl <em>User Role</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.UserRoleImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserRole()
     * @generated
     */
    int USER_ROLE = 18;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ComponentImpl <em>Component</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ComponentImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getComponent()
     * @generated
     */
    int COMPONENT = 21;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.XmlFileConnectionItemImpl <em>Xml File Connection Item</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.XmlFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getXmlFileConnectionItem()
     * @generated
     */
    int XML_FILE_CONNECTION_ITEM = 16;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_ROLE__NAME = 0;

    /**
     * The feature id for the '<em><b>Localized Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_ROLE__LOCALIZED_LABEL = 1;

    /**
     * The number of structural features of the '<em>User Role</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_ROLE_FEATURE_COUNT = 2;

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
     * The number of structural features of the '<em>User</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_FEATURE_COUNT = 12;

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
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__LABEL = 0;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__VERSION = 1;

    /**
     * The feature id for the '<em><b>Last Update Date</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__LAST_UPDATE_DATE = 2;

    /**
     * The feature id for the '<em><b>Author</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__AUTHOR = 3;

    /**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__CREATION_DATE = 4;

    /**
     * The feature id for the '<em><b>Delete Date</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__DELETE_DATE = 5;

    /**
     * The feature id for the '<em><b>Deleted</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__DELETED = 6;

    /**
     * The feature id for the '<em><b>File Descriptor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT__FILE_DESCRIPTOR = 7;

    /**
     * The feature id for the '<em><b>Projects</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__PROJECTS = 8;

    /**
     * The number of structural features of the '<em>Component</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.impl.NotationHolderImpl <em>Notation Holder</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.NotationHolderImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getNotationHolder()
     * @generated
     */
    int NOTATION_HOLDER = 22;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.ProjectComponentAuthorisationImpl <em>Project Component Authorisation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.ProjectComponentAuthorisationImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getProjectComponentAuthorisation()
     * @generated
     */
    int PROJECT_COMPONENT_AUTHORISATION = 23;

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
    int PROJECT_REFERENCE = 24;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.LdifFileConnectionItemImpl <em>Ldif File Connection Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.LdifFileConnectionItemImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getLdifFileConnectionItem()
     * @generated
     */
    int LDIF_FILE_CONNECTION_ITEM = 25;

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
     * The meta object id for the '{@link org.talend.core.model.properties.impl.UserProjectAuthorizationImpl <em>User Project Authorization</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.impl.UserProjectAuthorizationImpl
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserProjectAuthorization()
     * @generated
     */
    int USER_PROJECT_AUTHORIZATION = 26;

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
     * The meta object id for the '{@link org.talend.core.model.properties.FolderType <em>Folder Type</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.properties.FolderType
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getFolderType()
     * @generated
     */
    int FOLDER_TYPE = 27;

    /**
     * The meta object id for the '{@link org.talend.core.model.properties.UserProjectAuthorizationType <em>User Project Authorization Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.properties.UserProjectAuthorizationType
     * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserProjectAuthorizationType()
     * @generated
     */
    int USER_PROJECT_AUTHORIZATION_TYPE = 28;


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
     * Returns the meta object for class '{@link org.talend.core.model.properties.UserRole <em>User Role</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>User Role</em>'.
     * @see org.talend.core.model.properties.UserRole
     * @generated
     */
    EClass getUserRole();

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
         * The meta object literal for the '{@link org.talend.core.model.properties.impl.UserRoleImpl <em>User Role</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.properties.impl.UserRoleImpl
         * @see org.talend.core.model.properties.impl.PropertiesPackageImpl#getUserRole()
         * @generated
         */
        EClass USER_ROLE = eINSTANCE.getUserRole();

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
