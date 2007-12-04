/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import static org.talend.core.model.metadata.builder.connection.ConnectionPackage.ESCAPE;

import java.util.HashMap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.talend.core.model.metadata.builder.connection.AbstractMetadataObject;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DatabaseProperties;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.Escape;
import org.talend.core.model.metadata.builder.connection.FieldSeparator;
import org.talend.core.model.metadata.builder.connection.FileConnection;
import org.talend.core.model.metadata.builder.connection.FileFormat;
import org.talend.core.model.metadata.builder.connection.GenericSchemaConnection;
import org.talend.core.model.metadata.builder.connection.LDAPSchemaConnection;
import org.talend.core.model.metadata.builder.connection.LdifFileConnection;
import org.talend.core.model.metadata.builder.connection.Metadata;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.PositionalFileConnection;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.metadata.builder.connection.RegexpFileConnection;
import org.talend.core.model.metadata.builder.connection.RowSeparator;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;
import org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class ConnectionPackageImpl extends EPackageImpl implements ConnectionPackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass metadataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass connectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass metadataTableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass metadataColumnEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractMetadataObjectEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fileConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass delimitedFileConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass positionalFileConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass databaseConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass regexpFileConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlFileConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass schemaTargetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass queriesConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass queryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass ldifFileConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass xmlXPathLoopDescriptorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass genericSchemaConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass ldapSchemaConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum databasePropertiesEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum fileFormatEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum fieldSeparatorEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum escapeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum rowSeparatorEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EDataType mapEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ConnectionPackageImpl() {
        super(eNS_URI, ConnectionFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ConnectionPackage init() {
        if (isInited) return (ConnectionPackage)EPackage.Registry.INSTANCE.getEPackage(ConnectionPackage.eNS_URI);

        // Obtain or create and register package
        ConnectionPackageImpl theConnectionPackage = (ConnectionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ConnectionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ConnectionPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theConnectionPackage.createPackageContents();

        // Initialize created meta-data
        theConnectionPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theConnectionPackage.freeze();

        return theConnectionPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getMetadata() {
        return metadataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getMetadata_Connections() {
        return (EReference)metadataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getConnection() {
        return connectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnection_Version() {
        return (EAttribute)connectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnection_Tables() {
        return (EReference)connectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnection_Queries() {
        return (EReference)connectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getMetadataTable() {
        return metadataTableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataTable_SourceName() {
        return (EAttribute)metadataTableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getMetadataTable_Columns() {
        return (EReference)metadataTableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getMetadataTable_Connection() {
        return (EReference)metadataTableEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataTable_TableType() {
        return (EAttribute)metadataTableEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getMetadataColumn() {
        return metadataColumnEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_SourceType() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_DefaultValue() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_TalendType() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_Key() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_Nullable() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_Length() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_Precision() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getMetadataColumn_Table() {
        return (EReference)metadataColumnEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_OriginalField() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_Pattern() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMetadataColumn_DisplayField() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractMetadataObject() {
        return abstractMetadataObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractMetadataObject_Properties() {
        return (EAttribute)abstractMetadataObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractMetadataObject_Id() {
        return (EAttribute)abstractMetadataObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractMetadataObject_Comment() {
        return (EAttribute)abstractMetadataObjectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractMetadataObject_Label() {
        return (EAttribute)abstractMetadataObjectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractMetadataObject_ReadOnly() {
        return (EAttribute)abstractMetadataObjectEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractMetadataObject_Synchronised() {
        return (EAttribute)abstractMetadataObjectEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractMetadataObject_Divergency() {
        return (EAttribute)abstractMetadataObjectEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getFileConnection() {
        return fileConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_Server() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_FilePath() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_Format() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_Encoding() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_FieldSeparatorValue() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_RowSeparatorType() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_RowSeparatorValue() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_TextIdentifier() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_UseHeader() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_HeaderValue() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_UseFooter() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_FooterValue() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_UseLimit() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_LimitValue() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_FirstLineCaption() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_RemoveEmptyRow() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_EscapeType() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_EscapeChar() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileConnection_TextEnclosure() {
        return (EAttribute)fileConnectionEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDelimitedFileConnection() {
        return delimitedFileConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDelimitedFileConnection_FieldSeparatorType() {
        return (EAttribute)delimitedFileConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getPositionalFileConnection() {
        return positionalFileConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getDatabaseConnection() {
        return databaseConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_DatabaseType() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_DriverClass() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_URL() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_Port() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_Username() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_Password() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_ServerName() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_DatasourceName() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_FileFieldName() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_Schema() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_SID() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_SqlSynthax() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_StringQuote() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_NullChar() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_DbmsId() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_ProductId() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_DBRootPath() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDatabaseConnection_AdditionalParams() {
        return (EAttribute)databaseConnectionEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRegexpFileConnection() {
        return regexpFileConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRegexpFileConnection_FieldSeparatorType() {
        return (EAttribute)regexpFileConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getXmlFileConnection() {
        return xmlFileConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXmlFileConnection_XsdFilePath() {
        return (EAttribute)xmlFileConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXmlFileConnection_XmlFilePath() {
        return (EAttribute)xmlFileConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXmlFileConnection_Guess() {
        return (EAttribute)xmlFileConnectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXmlFileConnection_MaskXPattern() {
        return (EAttribute)xmlFileConnectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getXmlFileConnection_Schema() {
        return (EReference)xmlFileConnectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXmlFileConnection_Encoding() {
        return (EAttribute)xmlFileConnectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getSchemaTarget() {
        return schemaTargetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSchemaTarget_RelativeXPathQuery() {
        return (EAttribute)schemaTargetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSchemaTarget_TagName() {
        return (EAttribute)schemaTargetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getSchemaTarget_Schema() {
        return (EReference)schemaTargetEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getQueriesConnection() {
        return queriesConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueriesConnection_Connection() {
        return (EReference)queriesConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueriesConnection_Query() {
        return (EReference)queriesConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getQuery() {
        return queryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQuery_Value() {
        return (EAttribute)queryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getQuery_Queries() {
        return (EReference)queryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getLdifFileConnection() {
        return ldifFileConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLdifFileConnection_Value() {
        return (EAttribute)ldifFileConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLdifFileConnection_FilePath() {
        return (EAttribute)ldifFileConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLdifFileConnection_LimitEntry() {
        return (EAttribute)ldifFileConnectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLdifFileConnection_UseLimit() {
        return (EAttribute)ldifFileConnectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLdifFileConnection_Server() {
        return (EAttribute)ldifFileConnectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getXmlXPathLoopDescriptor() {
        return xmlXPathLoopDescriptorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXmlXPathLoopDescriptor_LimitBoucle() {
        return (EAttribute)xmlXPathLoopDescriptorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getXmlXPathLoopDescriptor_AbsoluteXPathQuery() {
        return (EAttribute)xmlXPathLoopDescriptorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getXmlXPathLoopDescriptor_Connection() {
        return (EReference)xmlXPathLoopDescriptorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getXmlXPathLoopDescriptor_SchemaTargets() {
        return (EReference)xmlXPathLoopDescriptorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getGenericSchemaConnection() {
        return genericSchemaConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenericSchemaConnection_MappingTypeUsed() {
        return (EAttribute)genericSchemaConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenericSchemaConnection_MappingTypeId() {
        return (EAttribute)genericSchemaConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getLDAPSchemaConnection() {
        return ldapSchemaConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_Host() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_Port() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_Protocol() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_Filter() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_Separator() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_UseAdvanced() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_StorePath() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_UseLimit() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_UseAuthen() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_BindPrincipal() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_BindPassword() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_LimitValue() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_EncryptionMethodName() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_Value() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_SavePassword() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_Aliases() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_Referrals() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_CountLimit() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_TimeOutLimit() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_BaseDNs() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_GetBaseDNsFromRoot() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_ReturnAttributes() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLDAPSchemaConnection_SelectedDN() {
        return (EAttribute)ldapSchemaConnectionEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getDatabaseProperties() {
        return databasePropertiesEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFileFormat() {
        return fileFormatEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFieldSeparator() {
        return fieldSeparatorEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getEscape() {
        return escapeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getRowSeparator() {
        return rowSeparatorEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EDataType getMap() {
        return mapEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ConnectionFactory getConnectionFactory() {
        return (ConnectionFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        metadataEClass = createEClass(METADATA);
        createEReference(metadataEClass, METADATA__CONNECTIONS);

        connectionEClass = createEClass(CONNECTION);
        createEAttribute(connectionEClass, CONNECTION__VERSION);
        createEReference(connectionEClass, CONNECTION__TABLES);
        createEReference(connectionEClass, CONNECTION__QUERIES);

        metadataColumnEClass = createEClass(METADATA_COLUMN);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__SOURCE_TYPE);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__DEFAULT_VALUE);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__TALEND_TYPE);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__KEY);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__NULLABLE);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__LENGTH);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__PRECISION);
        createEReference(metadataColumnEClass, METADATA_COLUMN__TABLE);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__ORIGINAL_FIELD);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__PATTERN);
        createEAttribute(metadataColumnEClass, METADATA_COLUMN__DISPLAY_FIELD);

        abstractMetadataObjectEClass = createEClass(ABSTRACT_METADATA_OBJECT);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__PROPERTIES);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__ID);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__COMMENT);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__LABEL);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__READ_ONLY);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__SYNCHRONISED);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__DIVERGENCY);

        metadataTableEClass = createEClass(METADATA_TABLE);
        createEAttribute(metadataTableEClass, METADATA_TABLE__SOURCE_NAME);
        createEReference(metadataTableEClass, METADATA_TABLE__COLUMNS);
        createEReference(metadataTableEClass, METADATA_TABLE__CONNECTION);
        createEAttribute(metadataTableEClass, METADATA_TABLE__TABLE_TYPE);

        fileConnectionEClass = createEClass(FILE_CONNECTION);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__SERVER);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__FILE_PATH);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__FORMAT);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__ENCODING);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__FIELD_SEPARATOR_VALUE);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__ROW_SEPARATOR_TYPE);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__ROW_SEPARATOR_VALUE);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__TEXT_IDENTIFIER);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__USE_HEADER);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__HEADER_VALUE);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__USE_FOOTER);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__FOOTER_VALUE);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__USE_LIMIT);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__LIMIT_VALUE);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__FIRST_LINE_CAPTION);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__REMOVE_EMPTY_ROW);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__ESCAPE_TYPE);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__ESCAPE_CHAR);
        createEAttribute(fileConnectionEClass, FILE_CONNECTION__TEXT_ENCLOSURE);

        delimitedFileConnectionEClass = createEClass(DELIMITED_FILE_CONNECTION);
        createEAttribute(delimitedFileConnectionEClass, DELIMITED_FILE_CONNECTION__FIELD_SEPARATOR_TYPE);

        positionalFileConnectionEClass = createEClass(POSITIONAL_FILE_CONNECTION);

        databaseConnectionEClass = createEClass(DATABASE_CONNECTION);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__DATABASE_TYPE);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__DRIVER_CLASS);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__URL);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__PORT);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__USERNAME);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__PASSWORD);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__SERVER_NAME);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__DATASOURCE_NAME);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__FILE_FIELD_NAME);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__SCHEMA);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__SID);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__SQL_SYNTHAX);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__STRING_QUOTE);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__NULL_CHAR);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__DBMS_ID);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__PRODUCT_ID);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__DB_ROOT_PATH);
        createEAttribute(databaseConnectionEClass, DATABASE_CONNECTION__ADDITIONAL_PARAMS);

        regexpFileConnectionEClass = createEClass(REGEXP_FILE_CONNECTION);
        createEAttribute(regexpFileConnectionEClass, REGEXP_FILE_CONNECTION__FIELD_SEPARATOR_TYPE);

        xmlFileConnectionEClass = createEClass(XML_FILE_CONNECTION);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__XSD_FILE_PATH);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__XML_FILE_PATH);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__GUESS);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__MASK_XPATTERN);
        createEReference(xmlFileConnectionEClass, XML_FILE_CONNECTION__SCHEMA);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__ENCODING);

        schemaTargetEClass = createEClass(SCHEMA_TARGET);
        createEAttribute(schemaTargetEClass, SCHEMA_TARGET__RELATIVE_XPATH_QUERY);
        createEAttribute(schemaTargetEClass, SCHEMA_TARGET__TAG_NAME);
        createEReference(schemaTargetEClass, SCHEMA_TARGET__SCHEMA);

        queriesConnectionEClass = createEClass(QUERIES_CONNECTION);
        createEReference(queriesConnectionEClass, QUERIES_CONNECTION__CONNECTION);
        createEReference(queriesConnectionEClass, QUERIES_CONNECTION__QUERY);

        queryEClass = createEClass(QUERY);
        createEAttribute(queryEClass, QUERY__VALUE);
        createEReference(queryEClass, QUERY__QUERIES);

        ldifFileConnectionEClass = createEClass(LDIF_FILE_CONNECTION);
        createEAttribute(ldifFileConnectionEClass, LDIF_FILE_CONNECTION__VALUE);
        createEAttribute(ldifFileConnectionEClass, LDIF_FILE_CONNECTION__FILE_PATH);
        createEAttribute(ldifFileConnectionEClass, LDIF_FILE_CONNECTION__LIMIT_ENTRY);
        createEAttribute(ldifFileConnectionEClass, LDIF_FILE_CONNECTION__USE_LIMIT);
        createEAttribute(ldifFileConnectionEClass, LDIF_FILE_CONNECTION__SERVER);

        xmlXPathLoopDescriptorEClass = createEClass(XML_XPATH_LOOP_DESCRIPTOR);
        createEAttribute(xmlXPathLoopDescriptorEClass, XML_XPATH_LOOP_DESCRIPTOR__LIMIT_BOUCLE);
        createEAttribute(xmlXPathLoopDescriptorEClass, XML_XPATH_LOOP_DESCRIPTOR__ABSOLUTE_XPATH_QUERY);
        createEReference(xmlXPathLoopDescriptorEClass, XML_XPATH_LOOP_DESCRIPTOR__CONNECTION);
        createEReference(xmlXPathLoopDescriptorEClass, XML_XPATH_LOOP_DESCRIPTOR__SCHEMA_TARGETS);

        genericSchemaConnectionEClass = createEClass(GENERIC_SCHEMA_CONNECTION);
        createEAttribute(genericSchemaConnectionEClass, GENERIC_SCHEMA_CONNECTION__MAPPING_TYPE_USED);
        createEAttribute(genericSchemaConnectionEClass, GENERIC_SCHEMA_CONNECTION__MAPPING_TYPE_ID);

        ldapSchemaConnectionEClass = createEClass(LDAP_SCHEMA_CONNECTION);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__HOST);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__PORT);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__PROTOCOL);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__FILTER);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__SEPARATOR);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__USE_ADVANCED);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__STORE_PATH);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__USE_LIMIT);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__USE_AUTHEN);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__BIND_PRINCIPAL);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__BIND_PASSWORD);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__LIMIT_VALUE);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__ENCRYPTION_METHOD_NAME);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__VALUE);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__SAVE_PASSWORD);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__ALIASES);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__REFERRALS);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__COUNT_LIMIT);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__TIME_OUT_LIMIT);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__BASE_DNS);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__GET_BASE_DNS_FROM_ROOT);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__RETURN_ATTRIBUTES);
        createEAttribute(ldapSchemaConnectionEClass, LDAP_SCHEMA_CONNECTION__SELECTED_DN);

        // Create enums
        databasePropertiesEEnum = createEEnum(DATABASE_PROPERTIES);
        fileFormatEEnum = createEEnum(FILE_FORMAT);
        fieldSeparatorEEnum = createEEnum(FIELD_SEPARATOR);
        escapeEEnum = createEEnum(ESCAPE);
        rowSeparatorEEnum = createEEnum(ROW_SEPARATOR);

        // Create data types
        mapEDataType = createEDataType(MAP);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Add supertypes to classes
        metadataEClass.getESuperTypes().add(this.getAbstractMetadataObject());
        connectionEClass.getESuperTypes().add(this.getAbstractMetadataObject());
        metadataColumnEClass.getESuperTypes().add(this.getAbstractMetadataObject());
        metadataTableEClass.getESuperTypes().add(this.getAbstractMetadataObject());
        fileConnectionEClass.getESuperTypes().add(this.getConnection());
        delimitedFileConnectionEClass.getESuperTypes().add(this.getFileConnection());
        positionalFileConnectionEClass.getESuperTypes().add(this.getFileConnection());
        databaseConnectionEClass.getESuperTypes().add(this.getConnection());
        regexpFileConnectionEClass.getESuperTypes().add(this.getFileConnection());
        xmlFileConnectionEClass.getESuperTypes().add(this.getConnection());
        queryEClass.getESuperTypes().add(this.getAbstractMetadataObject());
        ldifFileConnectionEClass.getESuperTypes().add(this.getConnection());
        genericSchemaConnectionEClass.getESuperTypes().add(this.getConnection());
        ldapSchemaConnectionEClass.getESuperTypes().add(this.getConnection());

        // Initialize classes and features; add operations and parameters
        initEClass(metadataEClass, Metadata.class, "Metadata", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMetadata_Connections(), this.getConnection(), null, "connections", null, 0, -1, Metadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConnection_Version(), ecorePackage.getEString(), "version", null, 0, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConnection_Tables(), this.getMetadataTable(), this.getMetadataTable_Connection(), "tables", null, 0, -1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConnection_Queries(), this.getQueriesConnection(), this.getQueriesConnection_Connection(), "queries", null, 0, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metadataColumnEClass, MetadataColumn.class, "MetadataColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMetadataColumn_SourceType(), ecorePackage.getEString(), "sourceType", null, 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_DefaultValue(), ecorePackage.getEString(), "defaultValue", "", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_TalendType(), ecorePackage.getEString(), "talendType", null, 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Key(), ecorePackage.getEBoolean(), "key", "false", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Nullable(), ecorePackage.getEBoolean(), "nullable", "true", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Length(), ecorePackage.getEInt(), "length", "-1", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Precision(), ecorePackage.getEInt(), "precision", "-1", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMetadataColumn_Table(), this.getMetadataTable(), this.getMetadataTable_Columns(), "table", null, 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_OriginalField(), ecorePackage.getEString(), "originalField", "", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Pattern(), ecorePackage.getEString(), "pattern", "", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_DisplayField(), ecorePackage.getEString(), "displayField", null, 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractMetadataObjectEClass, AbstractMetadataObject.class, "AbstractMetadataObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractMetadataObject_Properties(), this.getMap(), "properties", "", 1, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_Id(), ecorePackage.getEString(), "id", null, 0, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_Comment(), ecorePackage.getEString(), "comment", "", 0, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_Label(), ecorePackage.getEString(), "label", null, 0, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_ReadOnly(), ecorePackage.getEBoolean(), "readOnly", "false", 0, 1, AbstractMetadataObject.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_Synchronised(), ecorePackage.getEBoolean(), "synchronised", null, 0, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_Divergency(), ecorePackage.getEBoolean(), "divergency", null, 0, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metadataTableEClass, MetadataTable.class, "MetadataTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMetadataTable_SourceName(), ecorePackage.getEString(), "sourceName", null, 0, 1, MetadataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMetadataTable_Columns(), this.getMetadataColumn(), this.getMetadataColumn_Table(), "columns", null, 0, -1, MetadataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMetadataTable_Connection(), this.getConnection(), this.getConnection_Tables(), "connection", null, 0, 1, MetadataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataTable_TableType(), ecorePackage.getEString(), "tableType", null, 0, 1, MetadataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fileConnectionEClass, FileConnection.class, "FileConnection", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFileConnection_Server(), ecorePackage.getEString(), "Server", null, 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_FilePath(), ecorePackage.getEString(), "FilePath", null, 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_Format(), this.getFileFormat(), "Format", null, 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_Encoding(), ecorePackage.getEString(), "Encoding", null, 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_FieldSeparatorValue(), ecorePackage.getEString(), "FieldSeparatorValue", null, 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_RowSeparatorType(), this.getRowSeparator(), "RowSeparatorType", "Standart_EOL = 1", 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_RowSeparatorValue(), ecorePackage.getEString(), "RowSeparatorValue", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_TextIdentifier(), ecorePackage.getEString(), "TextIdentifier", "", 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_UseHeader(), ecorePackage.getEBoolean(), "UseHeader", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_HeaderValue(), ecorePackage.getEInt(), "HeaderValue", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_UseFooter(), ecorePackage.getEBoolean(), "UseFooter", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_FooterValue(), ecorePackage.getEInt(), "FooterValue", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_UseLimit(), ecorePackage.getEBoolean(), "UseLimit", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_LimitValue(), ecorePackage.getEInt(), "LimitValue", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_FirstLineCaption(), ecorePackage.getEBoolean(), "FirstLineCaption", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_RemoveEmptyRow(), ecorePackage.getEBoolean(), "RemoveEmptyRow", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_EscapeType(), this.getEscape(), "EscapeType", null, 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_EscapeChar(), ecorePackage.getEString(), "EscapeChar", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_TextEnclosure(), ecorePackage.getEString(), "TextEnclosure", null, 0, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(delimitedFileConnectionEClass, DelimitedFileConnection.class, "DelimitedFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDelimitedFileConnection_FieldSeparatorType(), this.getFieldSeparator(), "FieldSeparatorType", "Semicolon", 1, 1, DelimitedFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(positionalFileConnectionEClass, PositionalFileConnection.class, "PositionalFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(databaseConnectionEClass, DatabaseConnection.class, "DatabaseConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDatabaseConnection_DatabaseType(), ecorePackage.getEString(), "DatabaseType", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_DriverClass(), ecorePackage.getEString(), "DriverClass", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_URL(), ecorePackage.getEString(), "URL", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_Port(), ecorePackage.getEString(), "Port", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_Username(), ecorePackage.getEString(), "Username", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_Password(), ecorePackage.getEString(), "Password", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_ServerName(), ecorePackage.getEString(), "ServerName", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_DatasourceName(), ecorePackage.getEString(), "DatasourceName", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_FileFieldName(), ecorePackage.getEString(), "FileFieldName", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_Schema(), ecorePackage.getEString(), "Schema", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_SID(), ecorePackage.getEString(), "SID", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_SqlSynthax(), ecorePackage.getEString(), "SqlSynthax", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_StringQuote(), ecorePackage.getEString(), "StringQuote", "\"", 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_NullChar(), ecorePackage.getEString(), "NullChar", "000", 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_DbmsId(), ecorePackage.getEString(), "DbmsId", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_ProductId(), ecorePackage.getEString(), "ProductId", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_DBRootPath(), ecorePackage.getEString(), "DBRootPath", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDatabaseConnection_AdditionalParams(), ecorePackage.getEString(), "AdditionalParams", null, 0, 1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(regexpFileConnectionEClass, RegexpFileConnection.class, "RegexpFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRegexpFileConnection_FieldSeparatorType(), this.getFieldSeparator(), "FieldSeparatorType", null, 1, 1, RegexpFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlFileConnectionEClass, XmlFileConnection.class, "XmlFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXmlFileConnection_XsdFilePath(), ecorePackage.getEString(), "XsdFilePath", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXmlFileConnection_XmlFilePath(), ecorePackage.getEString(), "XmlFilePath", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXmlFileConnection_Guess(), ecorePackage.getEBoolean(), "Guess", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXmlFileConnection_MaskXPattern(), ecorePackage.getEString(), "MaskXPattern", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXmlFileConnection_Schema(), this.getXmlXPathLoopDescriptor(), this.getXmlXPathLoopDescriptor_Connection(), "schema", null, 0, -1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXmlFileConnection_Encoding(), ecorePackage.getEString(), "Encoding", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(schemaTargetEClass, SchemaTarget.class, "SchemaTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSchemaTarget_RelativeXPathQuery(), ecorePackage.getEString(), "RelativeXPathQuery", null, 0, 1, SchemaTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSchemaTarget_TagName(), ecorePackage.getEString(), "TagName", null, 0, 1, SchemaTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSchemaTarget_Schema(), this.getXmlXPathLoopDescriptor(), this.getXmlXPathLoopDescriptor_SchemaTargets(), "schema", null, 0, 1, SchemaTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queriesConnectionEClass, QueriesConnection.class, "QueriesConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueriesConnection_Connection(), this.getConnection(), this.getConnection_Queries(), "connection", null, 0, 1, QueriesConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueriesConnection_Query(), this.getQuery(), this.getQuery_Queries(), "query", null, 0, -1, QueriesConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQuery_Value(), ecorePackage.getEString(), "value", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuery_Queries(), this.getQueriesConnection(), this.getQueriesConnection_Query(), "queries", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ldifFileConnectionEClass, LdifFileConnection.class, "LdifFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLdifFileConnection_Value(), ecorePackage.getEString(), "value", null, 0, -1, LdifFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLdifFileConnection_FilePath(), ecorePackage.getEString(), "FilePath", null, 1, 1, LdifFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLdifFileConnection_LimitEntry(), ecorePackage.getEInt(), "LimitEntry", null, 0, 1, LdifFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLdifFileConnection_UseLimit(), ecorePackage.getEBoolean(), "UseLimit", null, 0, 1, LdifFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLdifFileConnection_Server(), ecorePackage.getEString(), "Server", null, 1, 1, LdifFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlXPathLoopDescriptorEClass, XmlXPathLoopDescriptor.class, "XmlXPathLoopDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXmlXPathLoopDescriptor_LimitBoucle(), ecorePackage.getEIntegerObject(), "LimitBoucle", null, 0, 1, XmlXPathLoopDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXmlXPathLoopDescriptor_AbsoluteXPathQuery(), ecorePackage.getEString(), "AbsoluteXPathQuery", null, 0, 1, XmlXPathLoopDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXmlXPathLoopDescriptor_Connection(), this.getXmlFileConnection(), this.getXmlFileConnection_Schema(), "connection", null, 0, 1, XmlXPathLoopDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXmlXPathLoopDescriptor_SchemaTargets(), this.getSchemaTarget(), this.getSchemaTarget_Schema(), "schemaTargets", null, 0, -1, XmlXPathLoopDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(genericSchemaConnectionEClass, GenericSchemaConnection.class, "GenericSchemaConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGenericSchemaConnection_MappingTypeUsed(), ecorePackage.getEBoolean(), "mappingTypeUsed", null, 0, 1, GenericSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenericSchemaConnection_MappingTypeId(), ecorePackage.getEString(), "mappingTypeId", null, 0, 1, GenericSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ldapSchemaConnectionEClass, LDAPSchemaConnection.class, "LDAPSchemaConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLDAPSchemaConnection_Host(), ecorePackage.getEString(), "Host", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_Port(), ecorePackage.getEString(), "Port", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_Protocol(), ecorePackage.getEString(), "Protocol", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_Filter(), ecorePackage.getEString(), "Filter", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_Separator(), ecorePackage.getEString(), "Separator", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_UseAdvanced(), ecorePackage.getEBoolean(), "UseAdvanced", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_StorePath(), ecorePackage.getEString(), "StorePath", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_UseLimit(), ecorePackage.getEBoolean(), "UseLimit", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_UseAuthen(), ecorePackage.getEBoolean(), "UseAuthen", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_BindPrincipal(), ecorePackage.getEString(), "BindPrincipal", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_BindPassword(), ecorePackage.getEString(), "BindPassword", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_LimitValue(), ecorePackage.getEInt(), "LimitValue", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_EncryptionMethodName(), ecorePackage.getEString(), "EncryptionMethodName", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_Value(), ecorePackage.getEString(), "Value", null, 0, -1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_SavePassword(), ecorePackage.getEBoolean(), "SavePassword", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_Aliases(), ecorePackage.getEString(), "Aliases", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_Referrals(), ecorePackage.getEString(), "Referrals", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_CountLimit(), ecorePackage.getEString(), "CountLimit", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_TimeOutLimit(), ecorePackage.getEString(), "TimeOutLimit", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_BaseDNs(), ecorePackage.getEString(), "BaseDNs", null, 0, -1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_GetBaseDNsFromRoot(), ecorePackage.getEBoolean(), "GetBaseDNsFromRoot", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_ReturnAttributes(), ecorePackage.getEString(), "ReturnAttributes", null, 0, -1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLDAPSchemaConnection_SelectedDN(), ecorePackage.getEString(), "SelectedDN", null, 0, 1, LDAPSchemaConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(databasePropertiesEEnum, DatabaseProperties.class, "DatabaseProperties");
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.DATABASE_TYPE_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.DRIVER_CLASS_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.URL_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.PORT_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.USERNAME_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.PASSWORD_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.SERVER_NAME_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.DATASOURCE_NAME_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.FILE_FIELD_NAME_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.SCHEMA_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.SID_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.SQL_SYNTHAX_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.STRING_QUOTE_LITERAL);
        addEEnumLiteral(databasePropertiesEEnum, DatabaseProperties.NULL_CHAR_LITERAL);

        initEEnum(fileFormatEEnum, FileFormat.class, "FileFormat");
        addEEnumLiteral(fileFormatEEnum, FileFormat.UNIX_LITERAL);
        addEEnumLiteral(fileFormatEEnum, FileFormat.MAC_LITERAL);
        addEEnumLiteral(fileFormatEEnum, FileFormat.WINDOWS_LITERAL);

        initEEnum(fieldSeparatorEEnum, FieldSeparator.class, "FieldSeparator");
        addEEnumLiteral(fieldSeparatorEEnum, FieldSeparator.TABULATION_LITERAL);
        addEEnumLiteral(fieldSeparatorEEnum, FieldSeparator.SEMICOLON_LITERAL);
        addEEnumLiteral(fieldSeparatorEEnum, FieldSeparator.COMMA_LITERAL);
        addEEnumLiteral(fieldSeparatorEEnum, FieldSeparator.SPACE_LITERAL);
        addEEnumLiteral(fieldSeparatorEEnum, FieldSeparator.ALT_65_LITERAL);
        addEEnumLiteral(fieldSeparatorEEnum, FieldSeparator.CUSTOM_ANSI_LITERAL);
        addEEnumLiteral(fieldSeparatorEEnum, FieldSeparator.CUSTOM_UTF8_LITERAL);
        addEEnumLiteral(fieldSeparatorEEnum, FieldSeparator.CUSTOM_REG_EXP_LITERAL);

        initEEnum(escapeEEnum, Escape.class, "Escape");
        addEEnumLiteral(escapeEEnum, Escape.DELIMITED_LITERAL);
        addEEnumLiteral(escapeEEnum, Escape.CSV_LITERAL);

        initEEnum(rowSeparatorEEnum, RowSeparator.class, "RowSeparator");
        addEEnumLiteral(rowSeparatorEEnum, RowSeparator.CUSTOM_STRING_LITERAL);
        addEEnumLiteral(rowSeparatorEEnum, RowSeparator.STANDART_EOL_LITERAL);

        // Initialize data types
        initEDataType(mapEDataType, HashMap.class, "Map", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} // ConnectionPackageImpl
