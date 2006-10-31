/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import java.util.HashMap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.talend.core.model.metadata.builder.connection.AbstractMetadataObject;
import org.talend.core.model.metadata.builder.connection.CSVFileConnection;
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
import org.talend.core.model.metadata.builder.connection.Metadata;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataSchema;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.PositionalFileConnection;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.metadata.builder.connection.RegexpFileConnection;
import org.talend.core.model.metadata.builder.connection.RowSeparator;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class ConnectionPackageImpl extends EPackageImpl implements ConnectionPackage 
{

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
    private EClass csvFileConnectionEClass = null;

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
    private EClass metadataSchemaEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass schemaTargetEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass queriesConnectionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass queryEClass = null;

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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMetadataColumn_OriginalField() {
        return (EAttribute)metadataColumnEClass.getEStructuralFeatures().get(8);
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDatabaseConnection_Queries() {
        return (EReference)databaseConnectionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getCSVFileConnection() {
        return csvFileConnectionEClass;
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
    public EAttribute getXmlFileConnection_IsGuess() {
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
    public EClass getMetadataSchema() {
        return metadataSchemaEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getMetadataSchema_SchemaTargets() {
        return (EReference)metadataSchemaEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getMetadataSchema_Connection() {
        return (EReference)metadataSchemaEClass.getEStructuralFeatures().get(1);
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
    public EAttribute getSchemaTarget_XPathQuery() {
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
    public EAttribute getSchemaTarget_IsBoucle() {
        return (EAttribute)schemaTargetEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSchemaTarget_LimitBoucle() {
        return (EAttribute)schemaTargetEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getSchemaTarget_Schema() {
        return (EReference)schemaTargetEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getQueriesConnection() {
        return queriesConnectionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueriesConnection_Connection() {
        return (EReference)queriesConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueriesConnection_Query() {
        return (EReference)queriesConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getQuery() {
        return queryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQuery_Id() {
        return (EAttribute)queryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQuery_Label() {
        return (EAttribute)queryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQuery_Comment() {
        return (EAttribute)queryEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQuery_Value() {
        return (EAttribute)queryEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQuery_Queries() {
        return (EReference)queryEClass.getEStructuralFeatures().get(4);
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

        abstractMetadataObjectEClass = createEClass(ABSTRACT_METADATA_OBJECT);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__PROPERTIES);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__ID);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__COMMENT);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__LABEL);
        createEAttribute(abstractMetadataObjectEClass, ABSTRACT_METADATA_OBJECT__READ_ONLY);

        metadataTableEClass = createEClass(METADATA_TABLE);
        createEAttribute(metadataTableEClass, METADATA_TABLE__SOURCE_NAME);
        createEReference(metadataTableEClass, METADATA_TABLE__COLUMNS);
        createEReference(metadataTableEClass, METADATA_TABLE__CONNECTION);

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
        createEReference(databaseConnectionEClass, DATABASE_CONNECTION__QUERIES);

        csvFileConnectionEClass = createEClass(CSV_FILE_CONNECTION);

        regexpFileConnectionEClass = createEClass(REGEXP_FILE_CONNECTION);
        createEAttribute(regexpFileConnectionEClass, REGEXP_FILE_CONNECTION__FIELD_SEPARATOR_TYPE);

        xmlFileConnectionEClass = createEClass(XML_FILE_CONNECTION);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__XSD_FILE_PATH);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__XML_FILE_PATH);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__IS_GUESS);
        createEAttribute(xmlFileConnectionEClass, XML_FILE_CONNECTION__MASK_XPATTERN);
        createEReference(xmlFileConnectionEClass, XML_FILE_CONNECTION__SCHEMA);

        metadataSchemaEClass = createEClass(METADATA_SCHEMA);
        createEReference(metadataSchemaEClass, METADATA_SCHEMA__SCHEMA_TARGETS);
        createEReference(metadataSchemaEClass, METADATA_SCHEMA__CONNECTION);

        schemaTargetEClass = createEClass(SCHEMA_TARGET);
        createEAttribute(schemaTargetEClass, SCHEMA_TARGET__XPATH_QUERY);
        createEAttribute(schemaTargetEClass, SCHEMA_TARGET__TAG_NAME);
        createEAttribute(schemaTargetEClass, SCHEMA_TARGET__IS_BOUCLE);
        createEAttribute(schemaTargetEClass, SCHEMA_TARGET__LIMIT_BOUCLE);
        createEReference(schemaTargetEClass, SCHEMA_TARGET__SCHEMA);

        queriesConnectionEClass = createEClass(QUERIES_CONNECTION);
        createEReference(queriesConnectionEClass, QUERIES_CONNECTION__CONNECTION);
        createEReference(queriesConnectionEClass, QUERIES_CONNECTION__QUERY);

        queryEClass = createEClass(QUERY);
        createEAttribute(queryEClass, QUERY__ID);
        createEAttribute(queryEClass, QUERY__LABEL);
        createEAttribute(queryEClass, QUERY__COMMENT);
        createEAttribute(queryEClass, QUERY__VALUE);
        createEReference(queryEClass, QUERY__QUERIES);

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
        csvFileConnectionEClass.getESuperTypes().add(this.getDelimitedFileConnection());
        regexpFileConnectionEClass.getESuperTypes().add(this.getFileConnection());
        xmlFileConnectionEClass.getESuperTypes().add(this.getConnection());

        // Initialize classes and features; add operations and parameters
        initEClass(metadataEClass, Metadata.class, "Metadata", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMetadata_Connections(), this.getConnection(), null, "connections", null, 0, -1, Metadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConnection_Version(), ecorePackage.getEString(), "version", null, 0, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConnection_Tables(), this.getMetadataTable(), this.getMetadataTable_Connection(), "tables", null, 0, -1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metadataColumnEClass, MetadataColumn.class, "MetadataColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMetadataColumn_SourceType(), ecorePackage.getEString(), "sourceType", null, 1, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_DefaultValue(), ecorePackage.getEString(), "defaultValue", "", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_TalendType(), ecorePackage.getEString(), "talendType", null, 1, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Key(), ecorePackage.getEBoolean(), "key", "false", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Nullable(), ecorePackage.getEBoolean(), "nullable", "true", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Length(), ecorePackage.getEInt(), "length", "-1", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_Precision(), ecorePackage.getEInt(), "precision", "-1", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMetadataColumn_Table(), this.getMetadataTable(), this.getMetadataTable_Columns(), "table", null, 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadataColumn_OriginalField(), ecorePackage.getEString(), "originalField", "", 0, 1, MetadataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractMetadataObjectEClass, AbstractMetadataObject.class, "AbstractMetadataObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractMetadataObject_Properties(), this.getMap(), "properties", "", 1, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_Id(), ecorePackage.getEString(), "id", null, 1, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_Comment(), ecorePackage.getEString(), "comment", "", 0, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_Label(), ecorePackage.getEString(), "label", null, 1, 1, AbstractMetadataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMetadataObject_ReadOnly(), ecorePackage.getEBoolean(), "readOnly", "false", 0, 1, AbstractMetadataObject.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metadataTableEClass, MetadataTable.class, "MetadataTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMetadataTable_SourceName(), ecorePackage.getEString(), "sourceName", null, 0, 1, MetadataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMetadataTable_Columns(), this.getMetadataColumn(), this.getMetadataColumn_Table(), "columns", null, 0, -1, MetadataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMetadataTable_Connection(), this.getConnection(), this.getConnection_Tables(), "connection", null, 0, 1, MetadataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
        initEAttribute(getFileConnection_EscapeChar(), ecorePackage.getEString(), "EscapeChar", null, 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFileConnection_TextEnclosure(), ecorePackage.getEString(), "TextEnclosure", null, 1, 1, FileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(delimitedFileConnectionEClass, DelimitedFileConnection.class, "DelimitedFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDelimitedFileConnection_FieldSeparatorType(), this.getFieldSeparator(), "FieldSeparatorType", null, 1, 1, DelimitedFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
        initEReference(getDatabaseConnection_Queries(), this.getQueriesConnection(), this.getQueriesConnection_Connection(), "queries", null, 0, -1, DatabaseConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(csvFileConnectionEClass, CSVFileConnection.class, "CSVFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(regexpFileConnectionEClass, RegexpFileConnection.class, "RegexpFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRegexpFileConnection_FieldSeparatorType(), this.getFieldSeparator(), "FieldSeparatorType", null, 1, 1, RegexpFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(xmlFileConnectionEClass, XmlFileConnection.class, "XmlFileConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getXmlFileConnection_XsdFilePath(), ecorePackage.getEString(), "XsdFilePath", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXmlFileConnection_XmlFilePath(), ecorePackage.getEString(), "XmlFilePath", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXmlFileConnection_IsGuess(), ecorePackage.getEBoolean(), "IsGuess", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getXmlFileConnection_MaskXPattern(), ecorePackage.getEString(), "MaskXPattern", null, 0, 1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getXmlFileConnection_Schema(), this.getMetadataSchema(), this.getMetadataSchema_Connection(), "schema", null, 0, -1, XmlFileConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metadataSchemaEClass, MetadataSchema.class, "MetadataSchema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMetadataSchema_SchemaTargets(), this.getSchemaTarget(), this.getSchemaTarget_Schema(), "schemaTargets", null, 0, -1, MetadataSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMetadataSchema_Connection(), this.getXmlFileConnection(), this.getXmlFileConnection_Schema(), "connection", null, 0, 1, MetadataSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(schemaTargetEClass, SchemaTarget.class, "SchemaTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSchemaTarget_XPathQuery(), ecorePackage.getEString(), "XPathQuery", null, 0, 1, SchemaTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSchemaTarget_TagName(), ecorePackage.getEString(), "TagName", null, 0, 1, SchemaTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSchemaTarget_IsBoucle(), ecorePackage.getEBoolean(), "IsBoucle", null, 0, 1, SchemaTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSchemaTarget_LimitBoucle(), ecorePackage.getEInt(), "LimitBoucle", null, 0, 1, SchemaTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSchemaTarget_Schema(), this.getMetadataSchema(), this.getMetadataSchema_SchemaTargets(), "schema", null, 0, 1, SchemaTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queriesConnectionEClass, QueriesConnection.class, "QueriesConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueriesConnection_Connection(), this.getDatabaseConnection(), this.getDatabaseConnection_Queries(), "connection", null, 0, 1, QueriesConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueriesConnection_Query(), this.getQuery(), this.getQuery_Queries(), "query", null, 0, -1, QueriesConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQuery_Id(), ecorePackage.getEString(), "id", null, 1, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getQuery_Label(), ecorePackage.getEString(), "label", null, 1, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getQuery_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getQuery_Value(), ecorePackage.getEString(), "value", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuery_Queries(), this.getQueriesConnection(), this.getQueriesConnection_Query(), "queries", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
