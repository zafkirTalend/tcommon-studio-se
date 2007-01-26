/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see org.talend.core.model.metadata.builder.connection.ConnectionFactory
 * @model kind="package"
 * @generated
 */
public interface ConnectionPackage extends EPackage {

    /**
     * The package name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "connection";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.talend.org";

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "TalendMetadata";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    ConnectionPackage eINSTANCE = org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl.init();

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.AbstractMetadataObjectImpl <em>Abstract Metadata Object</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.AbstractMetadataObjectImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getAbstractMetadataObject()
     * @generated
     */
    int ABSTRACT_METADATA_OBJECT = 3;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_METADATA_OBJECT__PROPERTIES = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_METADATA_OBJECT__ID = 1;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_METADATA_OBJECT__COMMENT = 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_METADATA_OBJECT__LABEL = 3;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_METADATA_OBJECT__READ_ONLY = 4;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_METADATA_OBJECT__SYNCHRONISED = 5;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_METADATA_OBJECT__DIVERGENCY = 6;

    /**
     * The number of structural features of the '<em>Abstract Metadata Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_METADATA_OBJECT_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.MetadataImpl <em>Metadata</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.MetadataImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getMetadata()
     * @generated
     */
    int METADATA = 0;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA__PROPERTIES = ABSTRACT_METADATA_OBJECT__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA__ID = ABSTRACT_METADATA_OBJECT__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA__COMMENT = ABSTRACT_METADATA_OBJECT__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA__LABEL = ABSTRACT_METADATA_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA__READ_ONLY = ABSTRACT_METADATA_OBJECT__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA__SYNCHRONISED = ABSTRACT_METADATA_OBJECT__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA__DIVERGENCY = ABSTRACT_METADATA_OBJECT__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Connections</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA__CONNECTIONS = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Metadata</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int METADATA_FEATURE_COUNT = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.ConnectionImpl <em>Connection</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getConnection()
     * @generated
     */
    int CONNECTION = 1;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__PROPERTIES = ABSTRACT_METADATA_OBJECT__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__ID = ABSTRACT_METADATA_OBJECT__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__COMMENT = ABSTRACT_METADATA_OBJECT__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__LABEL = ABSTRACT_METADATA_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__READ_ONLY = ABSTRACT_METADATA_OBJECT__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__SYNCHRONISED = ABSTRACT_METADATA_OBJECT__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__DIVERGENCY = ABSTRACT_METADATA_OBJECT__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__VERSION = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION__TABLES = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Connection</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTION_FEATURE_COUNT = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.MetadataTableImpl <em>Metadata Table</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.MetadataTableImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getMetadataTable()
     * @generated
     */
    int METADATA_TABLE = 4;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl <em>Metadata Column</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getMetadataColumn()
     * @generated
     */
    int METADATA_COLUMN = 2;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__PROPERTIES = ABSTRACT_METADATA_OBJECT__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__ID = ABSTRACT_METADATA_OBJECT__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__COMMENT = ABSTRACT_METADATA_OBJECT__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__LABEL = ABSTRACT_METADATA_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__READ_ONLY = ABSTRACT_METADATA_OBJECT__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__SYNCHRONISED = ABSTRACT_METADATA_OBJECT__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__DIVERGENCY = ABSTRACT_METADATA_OBJECT__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Source Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__SOURCE_TYPE = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Default Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__DEFAULT_VALUE = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Talend Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__TALEND_TYPE = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__KEY = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Nullable</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__NULLABLE = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Length</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__LENGTH = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Precision</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__PRECISION = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Table</b></em>' container reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN__TABLE = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Original Field</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int METADATA_COLUMN__ORIGINAL_FIELD = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Metadata Column</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_COLUMN_FEATURE_COUNT = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__PROPERTIES = ABSTRACT_METADATA_OBJECT__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__ID = ABSTRACT_METADATA_OBJECT__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__COMMENT = ABSTRACT_METADATA_OBJECT__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__LABEL = ABSTRACT_METADATA_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__READ_ONLY = ABSTRACT_METADATA_OBJECT__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__SYNCHRONISED = ABSTRACT_METADATA_OBJECT__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__DIVERGENCY = ABSTRACT_METADATA_OBJECT__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Source Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int METADATA_TABLE__SOURCE_NAME = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Columns</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__COLUMNS = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Connection</b></em>' container reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__CONNECTION = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Table Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE__TABLE_TYPE = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Metadata Table</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int METADATA_TABLE_FEATURE_COUNT = ABSTRACT_METADATA_OBJECT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.FileConnectionImpl <em>File Connection</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.FileConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getFileConnection()
     * @generated
     */
    int FILE_CONNECTION = 5;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__ID = CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__COMMENT = CONNECTION__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__LABEL = CONNECTION__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__READ_ONLY = CONNECTION__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__SYNCHRONISED = CONNECTION__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__DIVERGENCY = CONNECTION__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__VERSION = CONNECTION__VERSION;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__TABLES = CONNECTION__TABLES;

    /**
     * The feature id for the '<em><b>Server</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__SERVER = CONNECTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>File Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__FILE_PATH = CONNECTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Format</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__FORMAT = CONNECTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Encoding</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__ENCODING = CONNECTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Field Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__FIELD_SEPARATOR_VALUE = CONNECTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Row Separator Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__ROW_SEPARATOR_TYPE = CONNECTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Row Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__ROW_SEPARATOR_VALUE = CONNECTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Text Identifier</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__TEXT_IDENTIFIER = CONNECTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Use Header</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__USE_HEADER = CONNECTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Header Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__HEADER_VALUE = CONNECTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Use Footer</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__USE_FOOTER = CONNECTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Footer Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__FOOTER_VALUE = CONNECTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Use Limit</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__USE_LIMIT = CONNECTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Limit Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__LIMIT_VALUE = CONNECTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>First Line Caption</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__FIRST_LINE_CAPTION = CONNECTION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Remove Empty Row</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__REMOVE_EMPTY_ROW = CONNECTION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Escape Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__ESCAPE_TYPE = CONNECTION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>Escape Char</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__ESCAPE_CHAR = CONNECTION_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>Text Enclosure</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int FILE_CONNECTION__TEXT_ENCLOSURE = CONNECTION_FEATURE_COUNT + 18;

    /**
     * The number of structural features of the '<em>File Connection</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FILE_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 19;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.DelimitedFileConnectionImpl <em>Delimited File Connection</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.DelimitedFileConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getDelimitedFileConnection()
     * @generated
     */
    int DELIMITED_FILE_CONNECTION = 6;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__PROPERTIES = FILE_CONNECTION__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__ID = FILE_CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__COMMENT = FILE_CONNECTION__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__LABEL = FILE_CONNECTION__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__READ_ONLY = FILE_CONNECTION__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__SYNCHRONISED = FILE_CONNECTION__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__DIVERGENCY = FILE_CONNECTION__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__VERSION = FILE_CONNECTION__VERSION;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__TABLES = FILE_CONNECTION__TABLES;

    /**
     * The feature id for the '<em><b>Server</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__SERVER = FILE_CONNECTION__SERVER;

    /**
     * The feature id for the '<em><b>File Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__FILE_PATH = FILE_CONNECTION__FILE_PATH;

    /**
     * The feature id for the '<em><b>Format</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__FORMAT = FILE_CONNECTION__FORMAT;

    /**
     * The feature id for the '<em><b>Encoding</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__ENCODING = FILE_CONNECTION__ENCODING;

    /**
     * The feature id for the '<em><b>Field Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__FIELD_SEPARATOR_VALUE = FILE_CONNECTION__FIELD_SEPARATOR_VALUE;

    /**
     * The feature id for the '<em><b>Row Separator Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__ROW_SEPARATOR_TYPE = FILE_CONNECTION__ROW_SEPARATOR_TYPE;

    /**
     * The feature id for the '<em><b>Row Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__ROW_SEPARATOR_VALUE = FILE_CONNECTION__ROW_SEPARATOR_VALUE;

    /**
     * The feature id for the '<em><b>Text Identifier</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__TEXT_IDENTIFIER = FILE_CONNECTION__TEXT_IDENTIFIER;

    /**
     * The feature id for the '<em><b>Use Header</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__USE_HEADER = FILE_CONNECTION__USE_HEADER;

    /**
     * The feature id for the '<em><b>Header Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__HEADER_VALUE = FILE_CONNECTION__HEADER_VALUE;

    /**
     * The feature id for the '<em><b>Use Footer</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__USE_FOOTER = FILE_CONNECTION__USE_FOOTER;

    /**
     * The feature id for the '<em><b>Footer Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__FOOTER_VALUE = FILE_CONNECTION__FOOTER_VALUE;

    /**
     * The feature id for the '<em><b>Use Limit</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__USE_LIMIT = FILE_CONNECTION__USE_LIMIT;

    /**
     * The feature id for the '<em><b>Limit Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__LIMIT_VALUE = FILE_CONNECTION__LIMIT_VALUE;

    /**
     * The feature id for the '<em><b>First Line Caption</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__FIRST_LINE_CAPTION = FILE_CONNECTION__FIRST_LINE_CAPTION;

    /**
     * The feature id for the '<em><b>Remove Empty Row</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__REMOVE_EMPTY_ROW = FILE_CONNECTION__REMOVE_EMPTY_ROW;

    /**
     * The feature id for the '<em><b>Escape Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__ESCAPE_TYPE = FILE_CONNECTION__ESCAPE_TYPE;

    /**
     * The feature id for the '<em><b>Escape Char</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__ESCAPE_CHAR = FILE_CONNECTION__ESCAPE_CHAR;

    /**
     * The feature id for the '<em><b>Text Enclosure</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__TEXT_ENCLOSURE = FILE_CONNECTION__TEXT_ENCLOSURE;

    /**
     * The feature id for the '<em><b>Field Separator Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION__FIELD_SEPARATOR_TYPE = FILE_CONNECTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Delimited File Connection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DELIMITED_FILE_CONNECTION_FEATURE_COUNT = FILE_CONNECTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.PositionalFileConnectionImpl <em>Positional File Connection</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.PositionalFileConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getPositionalFileConnection()
     * @generated
     */
    int POSITIONAL_FILE_CONNECTION = 7;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__PROPERTIES = FILE_CONNECTION__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__ID = FILE_CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__COMMENT = FILE_CONNECTION__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__LABEL = FILE_CONNECTION__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__READ_ONLY = FILE_CONNECTION__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__SYNCHRONISED = FILE_CONNECTION__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__DIVERGENCY = FILE_CONNECTION__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__VERSION = FILE_CONNECTION__VERSION;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__TABLES = FILE_CONNECTION__TABLES;

    /**
     * The feature id for the '<em><b>Server</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__SERVER = FILE_CONNECTION__SERVER;

    /**
     * The feature id for the '<em><b>File Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__FILE_PATH = FILE_CONNECTION__FILE_PATH;

    /**
     * The feature id for the '<em><b>Format</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__FORMAT = FILE_CONNECTION__FORMAT;

    /**
     * The feature id for the '<em><b>Encoding</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__ENCODING = FILE_CONNECTION__ENCODING;

    /**
     * The feature id for the '<em><b>Field Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__FIELD_SEPARATOR_VALUE = FILE_CONNECTION__FIELD_SEPARATOR_VALUE;

    /**
     * The feature id for the '<em><b>Row Separator Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__ROW_SEPARATOR_TYPE = FILE_CONNECTION__ROW_SEPARATOR_TYPE;

    /**
     * The feature id for the '<em><b>Row Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__ROW_SEPARATOR_VALUE = FILE_CONNECTION__ROW_SEPARATOR_VALUE;

    /**
     * The feature id for the '<em><b>Text Identifier</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__TEXT_IDENTIFIER = FILE_CONNECTION__TEXT_IDENTIFIER;

    /**
     * The feature id for the '<em><b>Use Header</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__USE_HEADER = FILE_CONNECTION__USE_HEADER;

    /**
     * The feature id for the '<em><b>Header Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__HEADER_VALUE = FILE_CONNECTION__HEADER_VALUE;

    /**
     * The feature id for the '<em><b>Use Footer</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__USE_FOOTER = FILE_CONNECTION__USE_FOOTER;

    /**
     * The feature id for the '<em><b>Footer Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__FOOTER_VALUE = FILE_CONNECTION__FOOTER_VALUE;

    /**
     * The feature id for the '<em><b>Use Limit</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__USE_LIMIT = FILE_CONNECTION__USE_LIMIT;

    /**
     * The feature id for the '<em><b>Limit Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__LIMIT_VALUE = FILE_CONNECTION__LIMIT_VALUE;

    /**
     * The feature id for the '<em><b>First Line Caption</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__FIRST_LINE_CAPTION = FILE_CONNECTION__FIRST_LINE_CAPTION;

    /**
     * The feature id for the '<em><b>Remove Empty Row</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__REMOVE_EMPTY_ROW = FILE_CONNECTION__REMOVE_EMPTY_ROW;

    /**
     * The feature id for the '<em><b>Escape Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__ESCAPE_TYPE = FILE_CONNECTION__ESCAPE_TYPE;

    /**
     * The feature id for the '<em><b>Escape Char</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__ESCAPE_CHAR = FILE_CONNECTION__ESCAPE_CHAR;

    /**
     * The feature id for the '<em><b>Text Enclosure</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION__TEXT_ENCLOSURE = FILE_CONNECTION__TEXT_ENCLOSURE;

    /**
     * The number of structural features of the '<em>Positional File Connection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POSITIONAL_FILE_CONNECTION_FEATURE_COUNT = FILE_CONNECTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl <em>Database Connection</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getDatabaseConnection()
     * @generated
     */
    int DATABASE_CONNECTION = 8;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__ID = CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__COMMENT = CONNECTION__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__LABEL = CONNECTION__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__READ_ONLY = CONNECTION__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__SYNCHRONISED = CONNECTION__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__DIVERGENCY = CONNECTION__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__VERSION = CONNECTION__VERSION;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__TABLES = CONNECTION__TABLES;

    /**
     * The feature id for the '<em><b>Database Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__DATABASE_TYPE = CONNECTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Driver Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__DRIVER_CLASS = CONNECTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>URL</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__URL = CONNECTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Port</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__PORT = CONNECTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Username</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__USERNAME = CONNECTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Password</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__PASSWORD = CONNECTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Server Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__SERVER_NAME = CONNECTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Datasource Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__DATASOURCE_NAME = CONNECTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>File Field Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__FILE_FIELD_NAME = CONNECTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Schema</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__SCHEMA = CONNECTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>SID</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__SID = CONNECTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Sql Synthax</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__SQL_SYNTHAX = CONNECTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>String Quote</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__STRING_QUOTE = CONNECTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Null Char</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__NULL_CHAR = CONNECTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Queries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION__QUERIES = CONNECTION_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '<em>Database Connection</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int DATABASE_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.CSVFileConnectionImpl <em>CSV File Connection</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.CSVFileConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getCSVFileConnection()
     * @generated
     */
    int CSV_FILE_CONNECTION = 9;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__PROPERTIES = DELIMITED_FILE_CONNECTION__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__ID = DELIMITED_FILE_CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__COMMENT = DELIMITED_FILE_CONNECTION__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__LABEL = DELIMITED_FILE_CONNECTION__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__READ_ONLY = DELIMITED_FILE_CONNECTION__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__SYNCHRONISED = DELIMITED_FILE_CONNECTION__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__DIVERGENCY = DELIMITED_FILE_CONNECTION__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__VERSION = DELIMITED_FILE_CONNECTION__VERSION;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__TABLES = DELIMITED_FILE_CONNECTION__TABLES;

    /**
     * The feature id for the '<em><b>Server</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__SERVER = DELIMITED_FILE_CONNECTION__SERVER;

    /**
     * The feature id for the '<em><b>File Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__FILE_PATH = DELIMITED_FILE_CONNECTION__FILE_PATH;

    /**
     * The feature id for the '<em><b>Format</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__FORMAT = DELIMITED_FILE_CONNECTION__FORMAT;

    /**
     * The feature id for the '<em><b>Encoding</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__ENCODING = DELIMITED_FILE_CONNECTION__ENCODING;

    /**
     * The feature id for the '<em><b>Field Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__FIELD_SEPARATOR_VALUE = DELIMITED_FILE_CONNECTION__FIELD_SEPARATOR_VALUE;

    /**
     * The feature id for the '<em><b>Row Separator Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__ROW_SEPARATOR_TYPE = DELIMITED_FILE_CONNECTION__ROW_SEPARATOR_TYPE;

    /**
     * The feature id for the '<em><b>Row Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__ROW_SEPARATOR_VALUE = DELIMITED_FILE_CONNECTION__ROW_SEPARATOR_VALUE;

    /**
     * The feature id for the '<em><b>Text Identifier</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__TEXT_IDENTIFIER = DELIMITED_FILE_CONNECTION__TEXT_IDENTIFIER;

    /**
     * The feature id for the '<em><b>Use Header</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__USE_HEADER = DELIMITED_FILE_CONNECTION__USE_HEADER;

    /**
     * The feature id for the '<em><b>Header Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__HEADER_VALUE = DELIMITED_FILE_CONNECTION__HEADER_VALUE;

    /**
     * The feature id for the '<em><b>Use Footer</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__USE_FOOTER = DELIMITED_FILE_CONNECTION__USE_FOOTER;

    /**
     * The feature id for the '<em><b>Footer Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__FOOTER_VALUE = DELIMITED_FILE_CONNECTION__FOOTER_VALUE;

    /**
     * The feature id for the '<em><b>Use Limit</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__USE_LIMIT = DELIMITED_FILE_CONNECTION__USE_LIMIT;

    /**
     * The feature id for the '<em><b>Limit Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__LIMIT_VALUE = DELIMITED_FILE_CONNECTION__LIMIT_VALUE;

    /**
     * The feature id for the '<em><b>First Line Caption</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__FIRST_LINE_CAPTION = DELIMITED_FILE_CONNECTION__FIRST_LINE_CAPTION;

    /**
     * The feature id for the '<em><b>Remove Empty Row</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__REMOVE_EMPTY_ROW = DELIMITED_FILE_CONNECTION__REMOVE_EMPTY_ROW;

    /**
     * The feature id for the '<em><b>Escape Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__ESCAPE_TYPE = DELIMITED_FILE_CONNECTION__ESCAPE_TYPE;

    /**
     * The feature id for the '<em><b>Escape Char</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__ESCAPE_CHAR = DELIMITED_FILE_CONNECTION__ESCAPE_CHAR;

    /**
     * The feature id for the '<em><b>Text Enclosure</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__TEXT_ENCLOSURE = DELIMITED_FILE_CONNECTION__TEXT_ENCLOSURE;

    /**
     * The feature id for the '<em><b>Field Separator Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION__FIELD_SEPARATOR_TYPE = DELIMITED_FILE_CONNECTION__FIELD_SEPARATOR_TYPE;

    /**
     * The number of structural features of the '<em>CSV File Connection</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int CSV_FILE_CONNECTION_FEATURE_COUNT = DELIMITED_FILE_CONNECTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.RegexpFileConnectionImpl <em>Regexp File Connection</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.RegexpFileConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getRegexpFileConnection()
     * @generated
     */
    int REGEXP_FILE_CONNECTION = 10;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__PROPERTIES = FILE_CONNECTION__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__ID = FILE_CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__COMMENT = FILE_CONNECTION__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__LABEL = FILE_CONNECTION__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__READ_ONLY = FILE_CONNECTION__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__SYNCHRONISED = FILE_CONNECTION__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__DIVERGENCY = FILE_CONNECTION__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__VERSION = FILE_CONNECTION__VERSION;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__TABLES = FILE_CONNECTION__TABLES;

    /**
     * The feature id for the '<em><b>Server</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__SERVER = FILE_CONNECTION__SERVER;

    /**
     * The feature id for the '<em><b>File Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__FILE_PATH = FILE_CONNECTION__FILE_PATH;

    /**
     * The feature id for the '<em><b>Format</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__FORMAT = FILE_CONNECTION__FORMAT;

    /**
     * The feature id for the '<em><b>Encoding</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__ENCODING = FILE_CONNECTION__ENCODING;

    /**
     * The feature id for the '<em><b>Field Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__FIELD_SEPARATOR_VALUE = FILE_CONNECTION__FIELD_SEPARATOR_VALUE;

    /**
     * The feature id for the '<em><b>Row Separator Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__ROW_SEPARATOR_TYPE = FILE_CONNECTION__ROW_SEPARATOR_TYPE;

    /**
     * The feature id for the '<em><b>Row Separator Value</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__ROW_SEPARATOR_VALUE = FILE_CONNECTION__ROW_SEPARATOR_VALUE;

    /**
     * The feature id for the '<em><b>Text Identifier</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__TEXT_IDENTIFIER = FILE_CONNECTION__TEXT_IDENTIFIER;

    /**
     * The feature id for the '<em><b>Use Header</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__USE_HEADER = FILE_CONNECTION__USE_HEADER;

    /**
     * The feature id for the '<em><b>Header Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__HEADER_VALUE = FILE_CONNECTION__HEADER_VALUE;

    /**
     * The feature id for the '<em><b>Use Footer</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__USE_FOOTER = FILE_CONNECTION__USE_FOOTER;

    /**
     * The feature id for the '<em><b>Footer Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__FOOTER_VALUE = FILE_CONNECTION__FOOTER_VALUE;

    /**
     * The feature id for the '<em><b>Use Limit</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__USE_LIMIT = FILE_CONNECTION__USE_LIMIT;

    /**
     * The feature id for the '<em><b>Limit Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__LIMIT_VALUE = FILE_CONNECTION__LIMIT_VALUE;

    /**
     * The feature id for the '<em><b>First Line Caption</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__FIRST_LINE_CAPTION = FILE_CONNECTION__FIRST_LINE_CAPTION;

    /**
     * The feature id for the '<em><b>Remove Empty Row</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__REMOVE_EMPTY_ROW = FILE_CONNECTION__REMOVE_EMPTY_ROW;

    /**
     * The feature id for the '<em><b>Escape Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__ESCAPE_TYPE = FILE_CONNECTION__ESCAPE_TYPE;

    /**
     * The feature id for the '<em><b>Escape Char</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__ESCAPE_CHAR = FILE_CONNECTION__ESCAPE_CHAR;

    /**
     * The feature id for the '<em><b>Text Enclosure</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__TEXT_ENCLOSURE = FILE_CONNECTION__TEXT_ENCLOSURE;

    /**
     * The feature id for the '<em><b>Field Separator Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION__FIELD_SEPARATOR_TYPE = FILE_CONNECTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Regexp File Connection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REGEXP_FILE_CONNECTION_FEATURE_COUNT = FILE_CONNECTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.XmlFileConnectionImpl <em>Xml File Connection</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.XmlFileConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getXmlFileConnection()
     * @generated
     */
    int XML_FILE_CONNECTION = 11;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__ID = CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__COMMENT = CONNECTION__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__LABEL = CONNECTION__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__READ_ONLY = CONNECTION__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__SYNCHRONISED = CONNECTION__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__DIVERGENCY = CONNECTION__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__VERSION = CONNECTION__VERSION;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__TABLES = CONNECTION__TABLES;

    /**
     * The feature id for the '<em><b>Xsd File Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__XSD_FILE_PATH = CONNECTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Xml File Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__XML_FILE_PATH = CONNECTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Guess</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__GUESS = CONNECTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Mask XPattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__MASK_XPATTERN = CONNECTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Schema</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__SCHEMA = CONNECTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Encoding</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION__ENCODING = CONNECTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Xml File Connection</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_FILE_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl <em>Schema Target</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getSchemaTarget()
     * @generated
     */
    int SCHEMA_TARGET = 12;

    /**
     * The feature id for the '<em><b>Relative XPath Query</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_TARGET__RELATIVE_XPATH_QUERY = 0;

    /**
     * The feature id for the '<em><b>Tag Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_TARGET__TAG_NAME = 1;

    /**
     * The feature id for the '<em><b>Schema</b></em>' container reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_TARGET__SCHEMA = 2;

    /**
     * The number of structural features of the '<em>Schema Target</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int SCHEMA_TARGET_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.QueriesConnectionImpl <em>Queries Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.QueriesConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getQueriesConnection()
     * @generated
     */
    int QUERIES_CONNECTION = 13;

    /**
     * The feature id for the '<em><b>Connection</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERIES_CONNECTION__CONNECTION = 0;

    /**
     * The feature id for the '<em><b>Query</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERIES_CONNECTION__QUERY = 1;

    /**
     * The number of structural features of the '<em>Queries Connection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERIES_CONNECTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.QueryImpl <em>Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.QueryImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getQuery()
     * @generated
     */
    int QUERY = 14;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__LABEL = 1;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__COMMENT = 2;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__VALUE = 3;

    /**
     * The feature id for the '<em><b>Queries</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__QUERIES = 4;

    /**
     * The number of structural features of the '<em>Query</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.LdifFileConnectionImpl <em>Ldif File Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.LdifFileConnectionImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getLdifFileConnection()
     * @generated
     */
    int LDIF_FILE_CONNECTION = 15;

    /**
     * The feature id for the '<em><b>Properties</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__ID = CONNECTION__ID;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__COMMENT = CONNECTION__COMMENT;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__LABEL = CONNECTION__LABEL;

    /**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__READ_ONLY = CONNECTION__READ_ONLY;

    /**
     * The feature id for the '<em><b>Synchronised</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__SYNCHRONISED = CONNECTION__SYNCHRONISED;

    /**
     * The feature id for the '<em><b>Divergency</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__DIVERGENCY = CONNECTION__DIVERGENCY;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__VERSION = CONNECTION__VERSION;

    /**
     * The feature id for the '<em><b>Tables</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__TABLES = CONNECTION__TABLES;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__VALUE = CONNECTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>File Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__FILE_PATH = CONNECTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Limit Entry</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__LIMIT_ENTRY = CONNECTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Use Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__USE_LIMIT = CONNECTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Server</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION__SERVER = CONNECTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Ldif File Connection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LDIF_FILE_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.impl.XmlXPathLoopDescriptorImpl <em>Xml XPath Loop Descriptor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.impl.XmlXPathLoopDescriptorImpl
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getXmlXPathLoopDescriptor()
     * @generated
     */
    int XML_XPATH_LOOP_DESCRIPTOR = 16;

    /**
     * The feature id for the '<em><b>Limit Boucle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_XPATH_LOOP_DESCRIPTOR__LIMIT_BOUCLE = 0;

    /**
     * The feature id for the '<em><b>Absolute XPath Query</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_XPATH_LOOP_DESCRIPTOR__ABSOLUTE_XPATH_QUERY = 1;

    /**
     * The feature id for the '<em><b>Connection</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_XPATH_LOOP_DESCRIPTOR__CONNECTION = 2;

    /**
     * The feature id for the '<em><b>Schema Targets</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_XPATH_LOOP_DESCRIPTOR__SCHEMA_TARGETS = 3;

    /**
     * The number of structural features of the '<em>Xml XPath Loop Descriptor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_XPATH_LOOP_DESCRIPTOR_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.DatabaseProperties <em>Database Properties</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.DatabaseProperties
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getDatabaseProperties()
     * @generated
     */
    int DATABASE_PROPERTIES = 17;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.FileFormat <em>File Format</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.FileFormat
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getFileFormat()
     * @generated
     */
    int FILE_FORMAT = 18;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.FieldSeparator <em>Field Separator</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.FieldSeparator
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getFieldSeparator()
     * @generated
     */
    int FIELD_SEPARATOR = 19;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.Escape <em>Escape</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.Escape
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getEscape()
     * @generated
     */
    int ESCAPE = 20;

    /**
     * The meta object id for the '{@link org.talend.core.model.metadata.builder.connection.RowSeparator <em>Row Separator</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.talend.core.model.metadata.builder.connection.RowSeparator
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getRowSeparator()
     * @generated
     */
    int ROW_SEPARATOR = 21;

    /**
     * The meta object id for the '<em>Map</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see java.util.HashMap
     * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getMap()
     * @generated
     */
    int MAP = 22;

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.Metadata <em>Metadata</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Metadata</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Metadata
     * @generated
     */
    EClass getMetadata();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.metadata.builder.connection.Metadata#getConnections <em>Connections</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Connections</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Metadata#getConnections()
     * @see #getMetadata()
     * @generated
     */
    EReference getMetadata_Connections();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.Connection <em>Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Connection
     * @generated
     */
    EClass getConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.Connection#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Connection#getVersion()
     * @see #getConnection()
     * @generated
     */
    EAttribute getConnection_Version();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.metadata.builder.connection.Connection#getTables <em>Tables</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Tables</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Connection#getTables()
     * @see #getConnection()
     * @generated
     */
    EReference getConnection_Tables();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.MetadataTable <em>Metadata Table</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Metadata Table</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataTable
     * @generated
     */
    EClass getMetadataTable();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getSourceName <em>Source Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source Name</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataTable#getSourceName()
     * @see #getMetadataTable()
     * @generated
     */
    EAttribute getMetadataTable_SourceName();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getColumns <em>Columns</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Columns</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataTable#getColumns()
     * @see #getMetadataTable()
     * @generated
     */
    EReference getMetadataTable_Columns();

    /**
     * Returns the meta object for the container reference '{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataTable#getConnection()
     * @see #getMetadataTable()
     * @generated
     */
    EReference getMetadataTable_Connection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getTableType <em>Table Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Table Type</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataTable#getTableType()
     * @see #getMetadataTable()
     * @generated
     */
    EAttribute getMetadataTable_TableType();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn <em>Metadata Column</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Metadata Column</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn
     * @generated
     */
    EClass getMetadataColumn();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getSourceType <em>Source Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source Type</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#getSourceType()
     * @see #getMetadataColumn()
     * @generated
     */
    EAttribute getMetadataColumn_SourceType();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getDefaultValue <em>Default Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Value</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#getDefaultValue()
     * @see #getMetadataColumn()
     * @generated
     */
    EAttribute getMetadataColumn_DefaultValue();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getTalendType <em>Talend Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Talend Type</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#getTalendType()
     * @see #getMetadataColumn()
     * @generated
     */
    EAttribute getMetadataColumn_TalendType();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#isKey <em>Key</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#isKey()
     * @see #getMetadataColumn()
     * @generated
     */
    EAttribute getMetadataColumn_Key();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#isNullable <em>Nullable</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Nullable</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#isNullable()
     * @see #getMetadataColumn()
     * @generated
     */
    EAttribute getMetadataColumn_Nullable();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getLength <em>Length</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Length</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#getLength()
     * @see #getMetadataColumn()
     * @generated
     */
    EAttribute getMetadataColumn_Length();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getPrecision <em>Precision</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Precision</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#getPrecision()
     * @see #getMetadataColumn()
     * @generated
     */
    EAttribute getMetadataColumn_Precision();

    /**
     * Returns the meta object for the container reference '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getTable <em>Table</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#getTable()
     * @see #getMetadataColumn()
     * @generated
     */
    EReference getMetadataColumn_Table();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getOriginalField <em>Original Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Original Field</em>'.
     * @see org.talend.core.model.metadata.builder.connection.MetadataColumn#getOriginalField()
     * @see #getMetadataColumn()
     * @generated
     */
	EAttribute getMetadataColumn_OriginalField();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject <em>Abstract Metadata Object</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Metadata Object</em>'.
     * @see org.talend.core.model.metadata.builder.connection.AbstractMetadataObject
     * @generated
     */
    EClass getAbstractMetadataObject();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getProperties <em>Properties</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Properties</em>'.
     * @see org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getProperties()
     * @see #getAbstractMetadataObject()
     * @generated
     */
    EAttribute getAbstractMetadataObject_Properties();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getId()
     * @see #getAbstractMetadataObject()
     * @generated
     */
    EAttribute getAbstractMetadataObject_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getComment <em>Comment</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Comment</em>'.
     * @see org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getComment()
     * @see #getAbstractMetadataObject()
     * @generated
     */
    EAttribute getAbstractMetadataObject_Comment();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#getLabel()
     * @see #getAbstractMetadataObject()
     * @generated
     */
    EAttribute getAbstractMetadataObject_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#isReadOnly <em>Read Only</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Read Only</em>'.
     * @see org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#isReadOnly()
     * @see #getAbstractMetadataObject()
     * @generated
     */
    EAttribute getAbstractMetadataObject_ReadOnly();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#isSynchronised <em>Synchronised</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Synchronised</em>'.
     * @see org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#isSynchronised()
     * @see #getAbstractMetadataObject()
     * @generated
     */
    EAttribute getAbstractMetadataObject_Synchronised();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#isDivergency <em>Divergency</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Divergency</em>'.
     * @see org.talend.core.model.metadata.builder.connection.AbstractMetadataObject#isDivergency()
     * @see #getAbstractMetadataObject()
     * @generated
     */
    EAttribute getAbstractMetadataObject_Divergency();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.FileConnection <em>File Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>File Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection
     * @generated
     */
    EClass getFileConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getServer <em>Server</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Server</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getServer()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_Server();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getFilePath <em>File Path</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Path</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getFilePath()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_FilePath();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getFormat <em>Format</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Format</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getFormat()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_Format();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getEncoding <em>Encoding</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Encoding</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getEncoding()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_Encoding();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getFieldSeparatorValue <em>Field Separator Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Field Separator Value</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getFieldSeparatorValue()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_FieldSeparatorValue();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getRowSeparatorType <em>Row Separator Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Row Separator Type</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getRowSeparatorType()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_RowSeparatorType();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getRowSeparatorValue <em>Row Separator Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Row Separator Value</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getRowSeparatorValue()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_RowSeparatorValue();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getTextIdentifier <em>Text Identifier</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text Identifier</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getTextIdentifier()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_TextIdentifier();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#isUseHeader <em>Use Header</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Header</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#isUseHeader()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_UseHeader();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getHeaderValue <em>Header Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Header Value</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getHeaderValue()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_HeaderValue();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#isUseFooter <em>Use Footer</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Footer</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#isUseFooter()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_UseFooter();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getFooterValue <em>Footer Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Footer Value</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getFooterValue()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_FooterValue();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#isUseLimit <em>Use Limit</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Limit</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#isUseLimit()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_UseLimit();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getLimitValue <em>Limit Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Value</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getLimitValue()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_LimitValue();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#isFirstLineCaption <em>First Line Caption</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>First Line Caption</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#isFirstLineCaption()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_FirstLineCaption();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#isRemoveEmptyRow <em>Remove Empty Row</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Remove Empty Row</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#isRemoveEmptyRow()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_RemoveEmptyRow();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getEscapeType <em>Escape Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Escape Type</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getEscapeType()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_EscapeType();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getEscapeChar <em>Escape Char</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Escape Char</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getEscapeChar()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_EscapeChar();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.FileConnection#getTextEnclosure <em>Text Enclosure</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text Enclosure</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileConnection#getTextEnclosure()
     * @see #getFileConnection()
     * @generated
     */
    EAttribute getFileConnection_TextEnclosure();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.DelimitedFileConnection <em>Delimited File Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Delimited File Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DelimitedFileConnection
     * @generated
     */
    EClass getDelimitedFileConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DelimitedFileConnection#getFieldSeparatorType <em>Field Separator Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Field Separator Type</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DelimitedFileConnection#getFieldSeparatorType()
     * @see #getDelimitedFileConnection()
     * @generated
     */
    EAttribute getDelimitedFileConnection_FieldSeparatorType();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.PositionalFileConnection <em>Positional File Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Positional File Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.PositionalFileConnection
     * @generated
     */
    EClass getPositionalFileConnection();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection <em>Database Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Database Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection
     * @generated
     */
    EClass getDatabaseConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getDatabaseType <em>Database Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Database Type</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getDatabaseType()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_DatabaseType();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getDriverClass <em>Driver Class</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Driver Class</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getDriverClass()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_DriverClass();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getURL <em>URL</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>URL</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getURL()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_URL();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getPort <em>Port</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Port</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getPort()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_Port();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getUsername <em>Username</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Username</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getUsername()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_Username();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getPassword <em>Password</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Password</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getPassword()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_Password();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getServerName <em>Server Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Server Name</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getServerName()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_ServerName();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getDatasourceName <em>Datasource Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Datasource Name</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getDatasourceName()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_DatasourceName();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getFileFieldName <em>File Field Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Field Name</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getFileFieldName()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_FileFieldName();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getSchema <em>Schema</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Schema</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getSchema()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_Schema();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getSID <em>SID</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>SID</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getSID()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_SID();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getSqlSynthax <em>Sql Synthax</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Sql Synthax</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getSqlSynthax()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_SqlSynthax();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getStringQuote <em>String Quote</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>String Quote</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getStringQuote()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_StringQuote();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getNullChar <em>Null Char</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Null Char</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getNullChar()
     * @see #getDatabaseConnection()
     * @generated
     */
    EAttribute getDatabaseConnection_NullChar();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getQueries <em>Queries</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Queries</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseConnection#getQueries()
     * @see #getDatabaseConnection()
     * @generated
     */
    EReference getDatabaseConnection_Queries();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.CSVFileConnection <em>CSV File Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>CSV File Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.CSVFileConnection
     * @generated
     */
    EClass getCSVFileConnection();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.RegexpFileConnection <em>Regexp File Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Regexp File Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.RegexpFileConnection
     * @generated
     */
    EClass getRegexpFileConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.RegexpFileConnection#getFieldSeparatorType <em>Field Separator Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Field Separator Type</em>'.
     * @see org.talend.core.model.metadata.builder.connection.RegexpFileConnection#getFieldSeparatorType()
     * @see #getRegexpFileConnection()
     * @generated
     */
    EAttribute getRegexpFileConnection_FieldSeparatorType();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection <em>Xml File Connection</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Xml File Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlFileConnection
     * @generated
     */
    EClass getXmlFileConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getXsdFilePath <em>Xsd File Path</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Xsd File Path</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlFileConnection#getXsdFilePath()
     * @see #getXmlFileConnection()
     * @generated
     */
    EAttribute getXmlFileConnection_XsdFilePath();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getXmlFilePath <em>Xml File Path</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Xml File Path</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlFileConnection#getXmlFilePath()
     * @see #getXmlFileConnection()
     * @generated
     */
    EAttribute getXmlFileConnection_XmlFilePath();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#isGuess <em>Guess</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Guess</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlFileConnection#isGuess()
     * @see #getXmlFileConnection()
     * @generated
     */
    EAttribute getXmlFileConnection_Guess();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getMaskXPattern <em>Mask XPattern</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Mask XPattern</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlFileConnection#getMaskXPattern()
     * @see #getXmlFileConnection()
     * @generated
     */
    EAttribute getXmlFileConnection_MaskXPattern();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getSchema <em>Schema</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Schema</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlFileConnection#getSchema()
     * @see #getXmlFileConnection()
     * @generated
     */
    EReference getXmlFileConnection_Schema();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getEncoding <em>Encoding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Encoding</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlFileConnection#getEncoding()
     * @see #getXmlFileConnection()
     * @generated
     */
    EAttribute getXmlFileConnection_Encoding();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget <em>Schema Target</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Schema Target</em>'.
     * @see org.talend.core.model.metadata.builder.connection.SchemaTarget
     * @generated
     */
    EClass getSchemaTarget();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getRelativeXPathQuery <em>Relative XPath Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative XPath Query</em>'.
     * @see org.talend.core.model.metadata.builder.connection.SchemaTarget#getRelativeXPathQuery()
     * @see #getSchemaTarget()
     * @generated
     */
    EAttribute getSchemaTarget_RelativeXPathQuery();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getTagName <em>Tag Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tag Name</em>'.
     * @see org.talend.core.model.metadata.builder.connection.SchemaTarget#getTagName()
     * @see #getSchemaTarget()
     * @generated
     */
    EAttribute getSchemaTarget_TagName();

    /**
     * Returns the meta object for the container reference '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getSchema <em>Schema</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Schema</em>'.
     * @see org.talend.core.model.metadata.builder.connection.SchemaTarget#getSchema()
     * @see #getSchemaTarget()
     * @generated
     */
    EReference getSchemaTarget_Schema();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.QueriesConnection <em>Queries Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Queries Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.QueriesConnection
     * @generated
     */
    EClass getQueriesConnection();

    /**
     * Returns the meta object for the container reference '{@link org.talend.core.model.metadata.builder.connection.QueriesConnection#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.QueriesConnection#getConnection()
     * @see #getQueriesConnection()
     * @generated
     */
    EReference getQueriesConnection_Connection();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.metadata.builder.connection.QueriesConnection#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Query</em>'.
     * @see org.talend.core.model.metadata.builder.connection.QueriesConnection#getQuery()
     * @see #getQueriesConnection()
     * @generated
     */
    EReference getQueriesConnection_Query();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Query
     * @generated
     */
    EClass getQuery();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.Query#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Query#getId()
     * @see #getQuery()
     * @generated
     */
    EAttribute getQuery_Id();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.Query#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Query#getLabel()
     * @see #getQuery()
     * @generated
     */
    EAttribute getQuery_Label();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.Query#getComment <em>Comment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Comment</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Query#getComment()
     * @see #getQuery()
     * @generated
     */
    EAttribute getQuery_Comment();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.Query#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Query#getValue()
     * @see #getQuery()
     * @generated
     */
    EAttribute getQuery_Value();

    /**
     * Returns the meta object for the container reference '{@link org.talend.core.model.metadata.builder.connection.Query#getQueries <em>Queries</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Queries</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Query#getQueries()
     * @see #getQuery()
     * @generated
     */
    EReference getQuery_Queries();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.LdifFileConnection <em>Ldif File Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Ldif File Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.LdifFileConnection
     * @generated
     */
    EClass getLdifFileConnection();

    /**
     * Returns the meta object for the attribute list '{@link org.talend.core.model.metadata.builder.connection.LdifFileConnection#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.talend.core.model.metadata.builder.connection.LdifFileConnection#getValue()
     * @see #getLdifFileConnection()
     * @generated
     */
    EAttribute getLdifFileConnection_Value();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.LdifFileConnection#getFilePath <em>File Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Path</em>'.
     * @see org.talend.core.model.metadata.builder.connection.LdifFileConnection#getFilePath()
     * @see #getLdifFileConnection()
     * @generated
     */
    EAttribute getLdifFileConnection_FilePath();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.LdifFileConnection#getLimitEntry <em>Limit Entry</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Entry</em>'.
     * @see org.talend.core.model.metadata.builder.connection.LdifFileConnection#getLimitEntry()
     * @see #getLdifFileConnection()
     * @generated
     */
    EAttribute getLdifFileConnection_LimitEntry();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.LdifFileConnection#isUseLimit <em>Use Limit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Limit</em>'.
     * @see org.talend.core.model.metadata.builder.connection.LdifFileConnection#isUseLimit()
     * @see #getLdifFileConnection()
     * @generated
     */
    EAttribute getLdifFileConnection_UseLimit();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.LdifFileConnection#getServer <em>Server</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Server</em>'.
     * @see org.talend.core.model.metadata.builder.connection.LdifFileConnection#getServer()
     * @see #getLdifFileConnection()
     * @generated
     */
    EAttribute getLdifFileConnection_Server();

    /**
     * Returns the meta object for class '{@link org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor <em>Xml XPath Loop Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Xml XPath Loop Descriptor</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor
     * @generated
     */
    EClass getXmlXPathLoopDescriptor();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getLimitBoucle <em>Limit Boucle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Boucle</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getLimitBoucle()
     * @see #getXmlXPathLoopDescriptor()
     * @generated
     */
    EAttribute getXmlXPathLoopDescriptor_LimitBoucle();

    /**
     * Returns the meta object for the attribute '{@link org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getAbsoluteXPathQuery <em>Absolute XPath Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Absolute XPath Query</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getAbsoluteXPathQuery()
     * @see #getXmlXPathLoopDescriptor()
     * @generated
     */
    EAttribute getXmlXPathLoopDescriptor_AbsoluteXPathQuery();

    /**
     * Returns the meta object for the container reference '{@link org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Connection</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getConnection()
     * @see #getXmlXPathLoopDescriptor()
     * @generated
     */
    EReference getXmlXPathLoopDescriptor_Connection();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getSchemaTargets <em>Schema Targets</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Schema Targets</em>'.
     * @see org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getSchemaTargets()
     * @see #getXmlXPathLoopDescriptor()
     * @generated
     */
    EReference getXmlXPathLoopDescriptor_SchemaTargets();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.metadata.builder.connection.DatabaseProperties <em>Database Properties</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Database Properties</em>'.
     * @see org.talend.core.model.metadata.builder.connection.DatabaseProperties
     * @generated
     */
    EEnum getDatabaseProperties();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.metadata.builder.connection.FileFormat <em>File Format</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>File Format</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FileFormat
     * @generated
     */
    EEnum getFileFormat();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.metadata.builder.connection.FieldSeparator <em>Field Separator</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Field Separator</em>'.
     * @see org.talend.core.model.metadata.builder.connection.FieldSeparator
     * @generated
     */
    EEnum getFieldSeparator();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.metadata.builder.connection.Escape <em>Escape</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Escape</em>'.
     * @see org.talend.core.model.metadata.builder.connection.Escape
     * @generated
     */
    EEnum getEscape();

    /**
     * Returns the meta object for enum '{@link org.talend.core.model.metadata.builder.connection.RowSeparator <em>Row Separator</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Row Separator</em>'.
     * @see org.talend.core.model.metadata.builder.connection.RowSeparator
     * @generated
     */
    EEnum getRowSeparator();

    /**
     * Returns the meta object for data type '{@link java.util.HashMap <em>Map</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for data type '<em>Map</em>'.
     * @see java.util.HashMap
     * @model instanceClass="java.util.HashMap"
     * @generated
     */
    EDataType getMap();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ConnectionFactory getConnectionFactory();

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
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.MetadataImpl <em>Metadata</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.MetadataImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getMetadata()
         * @generated
         */
        EClass METADATA = eINSTANCE.getMetadata();

        /**
         * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference METADATA__CONNECTIONS = eINSTANCE.getMetadata_Connections();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.ConnectionImpl <em>Connection</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getConnection()
         * @generated
         */
        EClass CONNECTION = eINSTANCE.getConnection();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONNECTION__VERSION = eINSTANCE.getConnection_Version();

        /**
         * The meta object literal for the '<em><b>Tables</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONNECTION__TABLES = eINSTANCE.getConnection_Tables();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.MetadataTableImpl <em>Metadata Table</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.MetadataTableImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getMetadataTable()
         * @generated
         */
        EClass METADATA_TABLE = eINSTANCE.getMetadataTable();

        /**
         * The meta object literal for the '<em><b>Source Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute METADATA_TABLE__SOURCE_NAME = eINSTANCE.getMetadataTable_SourceName();

        /**
         * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference METADATA_TABLE__COLUMNS = eINSTANCE.getMetadataTable_Columns();

        /**
         * The meta object literal for the '<em><b>Connection</b></em>' container reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference METADATA_TABLE__CONNECTION = eINSTANCE.getMetadataTable_Connection();

        /**
         * The meta object literal for the '<em><b>Table Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute METADATA_TABLE__TABLE_TYPE = eINSTANCE.getMetadataTable_TableType();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl <em>Metadata Column</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getMetadataColumn()
         * @generated
         */
        EClass METADATA_COLUMN = eINSTANCE.getMetadataColumn();

        /**
         * The meta object literal for the '<em><b>Source Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute METADATA_COLUMN__SOURCE_TYPE = eINSTANCE.getMetadataColumn_SourceType();

        /**
         * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute METADATA_COLUMN__DEFAULT_VALUE = eINSTANCE.getMetadataColumn_DefaultValue();

        /**
         * The meta object literal for the '<em><b>Talend Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute METADATA_COLUMN__TALEND_TYPE = eINSTANCE.getMetadataColumn_TalendType();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute METADATA_COLUMN__KEY = eINSTANCE.getMetadataColumn_Key();

        /**
         * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute METADATA_COLUMN__NULLABLE = eINSTANCE.getMetadataColumn_Nullable();

        /**
         * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute METADATA_COLUMN__LENGTH = eINSTANCE.getMetadataColumn_Length();

        /**
         * The meta object literal for the '<em><b>Precision</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute METADATA_COLUMN__PRECISION = eINSTANCE.getMetadataColumn_Precision();

        /**
         * The meta object literal for the '<em><b>Table</b></em>' container reference feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference METADATA_COLUMN__TABLE = eINSTANCE.getMetadataColumn_Table();

        /**
         * The meta object literal for the '<em><b>Original Field</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute METADATA_COLUMN__ORIGINAL_FIELD = eINSTANCE.getMetadataColumn_OriginalField();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.AbstractMetadataObjectImpl <em>Abstract Metadata Object</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.AbstractMetadataObjectImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getAbstractMetadataObject()
         * @generated
         */
        EClass ABSTRACT_METADATA_OBJECT = eINSTANCE.getAbstractMetadataObject();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_METADATA_OBJECT__PROPERTIES = eINSTANCE.getAbstractMetadataObject_Properties();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_METADATA_OBJECT__ID = eINSTANCE.getAbstractMetadataObject_Id();

        /**
         * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_METADATA_OBJECT__COMMENT = eINSTANCE.getAbstractMetadataObject_Comment();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_METADATA_OBJECT__LABEL = eINSTANCE.getAbstractMetadataObject_Label();

        /**
         * The meta object literal for the '<em><b>Read Only</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_METADATA_OBJECT__READ_ONLY = eINSTANCE.getAbstractMetadataObject_ReadOnly();

        /**
         * The meta object literal for the '<em><b>Synchronised</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_METADATA_OBJECT__SYNCHRONISED = eINSTANCE.getAbstractMetadataObject_Synchronised();

        /**
         * The meta object literal for the '<em><b>Divergency</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_METADATA_OBJECT__DIVERGENCY = eINSTANCE.getAbstractMetadataObject_Divergency();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.FileConnectionImpl <em>File Connection</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.FileConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getFileConnection()
         * @generated
         */
        EClass FILE_CONNECTION = eINSTANCE.getFileConnection();

        /**
         * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__SERVER = eINSTANCE.getFileConnection_Server();

        /**
         * The meta object literal for the '<em><b>File Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__FILE_PATH = eINSTANCE.getFileConnection_FilePath();

        /**
         * The meta object literal for the '<em><b>Format</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__FORMAT = eINSTANCE.getFileConnection_Format();

        /**
         * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__ENCODING = eINSTANCE.getFileConnection_Encoding();

        /**
         * The meta object literal for the '<em><b>Field Separator Value</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FILE_CONNECTION__FIELD_SEPARATOR_VALUE = eINSTANCE.getFileConnection_FieldSeparatorValue();

        /**
         * The meta object literal for the '<em><b>Row Separator Type</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FILE_CONNECTION__ROW_SEPARATOR_TYPE = eINSTANCE.getFileConnection_RowSeparatorType();

        /**
         * The meta object literal for the '<em><b>Row Separator Value</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FILE_CONNECTION__ROW_SEPARATOR_VALUE = eINSTANCE.getFileConnection_RowSeparatorValue();

        /**
         * The meta object literal for the '<em><b>Text Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__TEXT_IDENTIFIER = eINSTANCE.getFileConnection_TextIdentifier();

        /**
         * The meta object literal for the '<em><b>Use Header</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__USE_HEADER = eINSTANCE.getFileConnection_UseHeader();

        /**
         * The meta object literal for the '<em><b>Header Value</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__HEADER_VALUE = eINSTANCE.getFileConnection_HeaderValue();

        /**
         * The meta object literal for the '<em><b>Use Footer</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__USE_FOOTER = eINSTANCE.getFileConnection_UseFooter();

        /**
         * The meta object literal for the '<em><b>Footer Value</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__FOOTER_VALUE = eINSTANCE.getFileConnection_FooterValue();

        /**
         * The meta object literal for the '<em><b>Use Limit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__USE_LIMIT = eINSTANCE.getFileConnection_UseLimit();

        /**
         * The meta object literal for the '<em><b>Limit Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__LIMIT_VALUE = eINSTANCE.getFileConnection_LimitValue();

        /**
         * The meta object literal for the '<em><b>First Line Caption</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FILE_CONNECTION__FIRST_LINE_CAPTION = eINSTANCE.getFileConnection_FirstLineCaption();

        /**
         * The meta object literal for the '<em><b>Remove Empty Row</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FILE_CONNECTION__REMOVE_EMPTY_ROW = eINSTANCE.getFileConnection_RemoveEmptyRow();

        /**
         * The meta object literal for the '<em><b>Escape Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__ESCAPE_TYPE = eINSTANCE.getFileConnection_EscapeType();

        /**
         * The meta object literal for the '<em><b>Escape Char</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__ESCAPE_CHAR = eINSTANCE.getFileConnection_EscapeChar();

        /**
         * The meta object literal for the '<em><b>Text Enclosure</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILE_CONNECTION__TEXT_ENCLOSURE = eINSTANCE.getFileConnection_TextEnclosure();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.DelimitedFileConnectionImpl <em>Delimited File Connection</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.DelimitedFileConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getDelimitedFileConnection()
         * @generated
         */
        EClass DELIMITED_FILE_CONNECTION = eINSTANCE.getDelimitedFileConnection();

        /**
         * The meta object literal for the '<em><b>Field Separator Type</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DELIMITED_FILE_CONNECTION__FIELD_SEPARATOR_TYPE = eINSTANCE.getDelimitedFileConnection_FieldSeparatorType();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.PositionalFileConnectionImpl <em>Positional File Connection</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.PositionalFileConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getPositionalFileConnection()
         * @generated
         */
        EClass POSITIONAL_FILE_CONNECTION = eINSTANCE.getPositionalFileConnection();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl <em>Database Connection</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.DatabaseConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getDatabaseConnection()
         * @generated
         */
        EClass DATABASE_CONNECTION = eINSTANCE.getDatabaseConnection();

        /**
         * The meta object literal for the '<em><b>Database Type</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__DATABASE_TYPE = eINSTANCE.getDatabaseConnection_DatabaseType();

        /**
         * The meta object literal for the '<em><b>Driver Class</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__DRIVER_CLASS = eINSTANCE.getDatabaseConnection_DriverClass();

        /**
         * The meta object literal for the '<em><b>URL</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__URL = eINSTANCE.getDatabaseConnection_URL();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__PORT = eINSTANCE.getDatabaseConnection_Port();

        /**
         * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__USERNAME = eINSTANCE.getDatabaseConnection_Username();

        /**
         * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__PASSWORD = eINSTANCE.getDatabaseConnection_Password();

        /**
         * The meta object literal for the '<em><b>Server Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__SERVER_NAME = eINSTANCE.getDatabaseConnection_ServerName();

        /**
         * The meta object literal for the '<em><b>Datasource Name</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__DATASOURCE_NAME = eINSTANCE.getDatabaseConnection_DatasourceName();

        /**
         * The meta object literal for the '<em><b>File Field Name</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__FILE_FIELD_NAME = eINSTANCE.getDatabaseConnection_FileFieldName();

        /**
         * The meta object literal for the '<em><b>Schema</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__SCHEMA = eINSTANCE.getDatabaseConnection_Schema();

        /**
         * The meta object literal for the '<em><b>SID</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__SID = eINSTANCE.getDatabaseConnection_SID();

        /**
         * The meta object literal for the '<em><b>Sql Synthax</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__SQL_SYNTHAX = eINSTANCE.getDatabaseConnection_SqlSynthax();

        /**
         * The meta object literal for the '<em><b>String Quote</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__STRING_QUOTE = eINSTANCE.getDatabaseConnection_StringQuote();

        /**
         * The meta object literal for the '<em><b>Null Char</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATABASE_CONNECTION__NULL_CHAR = eINSTANCE.getDatabaseConnection_NullChar();

        /**
         * The meta object literal for the '<em><b>Queries</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DATABASE_CONNECTION__QUERIES = eINSTANCE.getDatabaseConnection_Queries();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.CSVFileConnectionImpl <em>CSV File Connection</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.CSVFileConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getCSVFileConnection()
         * @generated
         */
        EClass CSV_FILE_CONNECTION = eINSTANCE.getCSVFileConnection();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.RegexpFileConnectionImpl <em>Regexp File Connection</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.RegexpFileConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getRegexpFileConnection()
         * @generated
         */
        EClass REGEXP_FILE_CONNECTION = eINSTANCE.getRegexpFileConnection();

        /**
         * The meta object literal for the '<em><b>Field Separator Type</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REGEXP_FILE_CONNECTION__FIELD_SEPARATOR_TYPE = eINSTANCE.getRegexpFileConnection_FieldSeparatorType();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.XmlFileConnectionImpl <em>Xml File Connection</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.XmlFileConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getXmlFileConnection()
         * @generated
         */
        EClass XML_FILE_CONNECTION = eINSTANCE.getXmlFileConnection();

        /**
         * The meta object literal for the '<em><b>Xsd File Path</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute XML_FILE_CONNECTION__XSD_FILE_PATH = eINSTANCE.getXmlFileConnection_XsdFilePath();

        /**
         * The meta object literal for the '<em><b>Xml File Path</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute XML_FILE_CONNECTION__XML_FILE_PATH = eINSTANCE.getXmlFileConnection_XmlFilePath();

        /**
         * The meta object literal for the '<em><b>Guess</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute XML_FILE_CONNECTION__GUESS = eINSTANCE.getXmlFileConnection_Guess();

        /**
         * The meta object literal for the '<em><b>Mask XPattern</b></em>' attribute feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute XML_FILE_CONNECTION__MASK_XPATTERN = eINSTANCE.getXmlFileConnection_MaskXPattern();

        /**
         * The meta object literal for the '<em><b>Schema</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference XML_FILE_CONNECTION__SCHEMA = eINSTANCE.getXmlFileConnection_Schema();

        /**
         * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute XML_FILE_CONNECTION__ENCODING = eINSTANCE.getXmlFileConnection_Encoding();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl <em>Schema Target</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getSchemaTarget()
         * @generated
         */
        EClass SCHEMA_TARGET = eINSTANCE.getSchemaTarget();

        /**
         * The meta object literal for the '<em><b>Relative XPath Query</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_TARGET__RELATIVE_XPATH_QUERY = eINSTANCE.getSchemaTarget_RelativeXPathQuery();

        /**
         * The meta object literal for the '<em><b>Tag Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCHEMA_TARGET__TAG_NAME = eINSTANCE.getSchemaTarget_TagName();

        /**
         * The meta object literal for the '<em><b>Schema</b></em>' container reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SCHEMA_TARGET__SCHEMA = eINSTANCE.getSchemaTarget_Schema();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.QueriesConnectionImpl <em>Queries Connection</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.QueriesConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getQueriesConnection()
         * @generated
         */
        EClass QUERIES_CONNECTION = eINSTANCE.getQueriesConnection();

        /**
         * The meta object literal for the '<em><b>Connection</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERIES_CONNECTION__CONNECTION = eINSTANCE.getQueriesConnection_Connection();

        /**
         * The meta object literal for the '<em><b>Query</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERIES_CONNECTION__QUERY = eINSTANCE.getQueriesConnection_Query();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.QueryImpl <em>Query</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.QueryImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getQuery()
         * @generated
         */
        EClass QUERY = eINSTANCE.getQuery();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY__ID = eINSTANCE.getQuery_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY__LABEL = eINSTANCE.getQuery_Label();

        /**
         * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY__COMMENT = eINSTANCE.getQuery_Comment();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY__VALUE = eINSTANCE.getQuery_Value();

        /**
         * The meta object literal for the '<em><b>Queries</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY__QUERIES = eINSTANCE.getQuery_Queries();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.LdifFileConnectionImpl <em>Ldif File Connection</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.LdifFileConnectionImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getLdifFileConnection()
         * @generated
         */
        EClass LDIF_FILE_CONNECTION = eINSTANCE.getLdifFileConnection();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LDIF_FILE_CONNECTION__VALUE = eINSTANCE.getLdifFileConnection_Value();

        /**
         * The meta object literal for the '<em><b>File Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LDIF_FILE_CONNECTION__FILE_PATH = eINSTANCE.getLdifFileConnection_FilePath();

        /**
         * The meta object literal for the '<em><b>Limit Entry</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LDIF_FILE_CONNECTION__LIMIT_ENTRY = eINSTANCE.getLdifFileConnection_LimitEntry();

        /**
         * The meta object literal for the '<em><b>Use Limit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LDIF_FILE_CONNECTION__USE_LIMIT = eINSTANCE.getLdifFileConnection_UseLimit();

        /**
         * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LDIF_FILE_CONNECTION__SERVER = eINSTANCE.getLdifFileConnection_Server();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.impl.XmlXPathLoopDescriptorImpl <em>Xml XPath Loop Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.impl.XmlXPathLoopDescriptorImpl
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getXmlXPathLoopDescriptor()
         * @generated
         */
        EClass XML_XPATH_LOOP_DESCRIPTOR = eINSTANCE.getXmlXPathLoopDescriptor();

        /**
         * The meta object literal for the '<em><b>Limit Boucle</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute XML_XPATH_LOOP_DESCRIPTOR__LIMIT_BOUCLE = eINSTANCE.getXmlXPathLoopDescriptor_LimitBoucle();

        /**
         * The meta object literal for the '<em><b>Absolute XPath Query</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute XML_XPATH_LOOP_DESCRIPTOR__ABSOLUTE_XPATH_QUERY = eINSTANCE.getXmlXPathLoopDescriptor_AbsoluteXPathQuery();

        /**
         * The meta object literal for the '<em><b>Connection</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference XML_XPATH_LOOP_DESCRIPTOR__CONNECTION = eINSTANCE.getXmlXPathLoopDescriptor_Connection();

        /**
         * The meta object literal for the '<em><b>Schema Targets</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference XML_XPATH_LOOP_DESCRIPTOR__SCHEMA_TARGETS = eINSTANCE.getXmlXPathLoopDescriptor_SchemaTargets();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.DatabaseProperties <em>Database Properties</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.DatabaseProperties
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getDatabaseProperties()
         * @generated
         */
        EEnum DATABASE_PROPERTIES = eINSTANCE.getDatabaseProperties();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.FileFormat <em>File Format</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.FileFormat
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getFileFormat()
         * @generated
         */
        EEnum FILE_FORMAT = eINSTANCE.getFileFormat();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.FieldSeparator <em>Field Separator</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.FieldSeparator
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getFieldSeparator()
         * @generated
         */
        EEnum FIELD_SEPARATOR = eINSTANCE.getFieldSeparator();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.Escape <em>Escape</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.Escape
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getEscape()
         * @generated
         */
        EEnum ESCAPE = eINSTANCE.getEscape();

        /**
         * The meta object literal for the '{@link org.talend.core.model.metadata.builder.connection.RowSeparator <em>Row Separator</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.talend.core.model.metadata.builder.connection.RowSeparator
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getRowSeparator()
         * @generated
         */
        EEnum ROW_SEPARATOR = eINSTANCE.getRowSeparator();

        /**
         * The meta object literal for the '<em>Map</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see java.util.HashMap
         * @see org.talend.core.model.metadata.builder.connection.impl.ConnectionPackageImpl#getMap()
         * @generated
         */
        EDataType MAP = eINSTANCE.getMap();

    }

} // ConnectionPackage
