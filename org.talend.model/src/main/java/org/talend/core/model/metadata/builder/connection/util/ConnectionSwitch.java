/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.talend.core.model.metadata.builder.connection.*;

import org.talend.core.model.metadata.builder.connection.AbstractMetadataObject;
import org.talend.core.model.metadata.builder.connection.CSVFileConnection;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.core.model.metadata.builder.connection.FileConnection;
import org.talend.core.model.metadata.builder.connection.Metadata;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataSchema;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.PositionalFileConnection;
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.metadata.builder.connection.RegexpFileConnection;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;

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
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage
 * @generated
 */
public class ConnectionSwitch {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ConnectionPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConnectionSwitch() {
        if (modelPackage == null) {
            modelPackage = ConnectionPackage.eINSTANCE;
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
            case ConnectionPackage.METADATA: {
                Metadata metadata = (Metadata)theEObject;
                Object result = caseMetadata(metadata);
                if (result == null) result = caseAbstractMetadataObject(metadata);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.CONNECTION: {
                Connection connection = (Connection)theEObject;
                Object result = caseConnection(connection);
                if (result == null) result = caseAbstractMetadataObject(connection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.METADATA_COLUMN: {
                MetadataColumn metadataColumn = (MetadataColumn)theEObject;
                Object result = caseMetadataColumn(metadataColumn);
                if (result == null) result = caseAbstractMetadataObject(metadataColumn);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.ABSTRACT_METADATA_OBJECT: {
                AbstractMetadataObject abstractMetadataObject = (AbstractMetadataObject)theEObject;
                Object result = caseAbstractMetadataObject(abstractMetadataObject);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.METADATA_TABLE: {
                MetadataTable metadataTable = (MetadataTable)theEObject;
                Object result = caseMetadataTable(metadataTable);
                if (result == null) result = caseAbstractMetadataObject(metadataTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.FILE_CONNECTION: {
                FileConnection fileConnection = (FileConnection)theEObject;
                Object result = caseFileConnection(fileConnection);
                if (result == null) result = caseConnection(fileConnection);
                if (result == null) result = caseAbstractMetadataObject(fileConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.DELIMITED_FILE_CONNECTION: {
                DelimitedFileConnection delimitedFileConnection = (DelimitedFileConnection)theEObject;
                Object result = caseDelimitedFileConnection(delimitedFileConnection);
                if (result == null) result = caseFileConnection(delimitedFileConnection);
                if (result == null) result = caseConnection(delimitedFileConnection);
                if (result == null) result = caseAbstractMetadataObject(delimitedFileConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.POSITIONAL_FILE_CONNECTION: {
                PositionalFileConnection positionalFileConnection = (PositionalFileConnection)theEObject;
                Object result = casePositionalFileConnection(positionalFileConnection);
                if (result == null) result = caseFileConnection(positionalFileConnection);
                if (result == null) result = caseConnection(positionalFileConnection);
                if (result == null) result = caseAbstractMetadataObject(positionalFileConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.DATABASE_CONNECTION: {
                DatabaseConnection databaseConnection = (DatabaseConnection)theEObject;
                Object result = caseDatabaseConnection(databaseConnection);
                if (result == null) result = caseConnection(databaseConnection);
                if (result == null) result = caseAbstractMetadataObject(databaseConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.CSV_FILE_CONNECTION: {
                CSVFileConnection csvFileConnection = (CSVFileConnection)theEObject;
                Object result = caseCSVFileConnection(csvFileConnection);
                if (result == null) result = caseDelimitedFileConnection(csvFileConnection);
                if (result == null) result = caseFileConnection(csvFileConnection);
                if (result == null) result = caseConnection(csvFileConnection);
                if (result == null) result = caseAbstractMetadataObject(csvFileConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.REGEXP_FILE_CONNECTION: {
                RegexpFileConnection regexpFileConnection = (RegexpFileConnection)theEObject;
                Object result = caseRegexpFileConnection(regexpFileConnection);
                if (result == null) result = caseFileConnection(regexpFileConnection);
                if (result == null) result = caseConnection(regexpFileConnection);
                if (result == null) result = caseAbstractMetadataObject(regexpFileConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.XML_FILE_CONNECTION: {
                XmlFileConnection xmlFileConnection = (XmlFileConnection)theEObject;
                Object result = caseXmlFileConnection(xmlFileConnection);
                if (result == null) result = caseConnection(xmlFileConnection);
                if (result == null) result = caseAbstractMetadataObject(xmlFileConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.SCHEMA_TARGET: {
                SchemaTarget schemaTarget = (SchemaTarget)theEObject;
                Object result = caseSchemaTarget(schemaTarget);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.QUERIES_CONNECTION: {
                QueriesConnection queriesConnection = (QueriesConnection)theEObject;
                Object result = caseQueriesConnection(queriesConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.QUERY: {
                Query query = (Query)theEObject;
                Object result = caseQuery(query);
                if (result == null) result = caseAbstractMetadataObject(query);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.LDIF_FILE_CONNECTION: {
                LdifFileConnection ldifFileConnection = (LdifFileConnection)theEObject;
                Object result = caseLdifFileConnection(ldifFileConnection);
                if (result == null) result = caseConnection(ldifFileConnection);
                if (result == null) result = caseAbstractMetadataObject(ldifFileConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.XML_XPATH_LOOP_DESCRIPTOR: {
                XmlXPathLoopDescriptor xmlXPathLoopDescriptor = (XmlXPathLoopDescriptor)theEObject;
                Object result = caseXmlXPathLoopDescriptor(xmlXPathLoopDescriptor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConnectionPackage.GENERIC_SCHEMA_CONNECTION: {
                GenericSchemaConnection genericSchemaConnection = (GenericSchemaConnection)theEObject;
                Object result = caseGenericSchemaConnection(genericSchemaConnection);
                if (result == null) result = caseConnection(genericSchemaConnection);
                if (result == null) result = caseAbstractMetadataObject(genericSchemaConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Metadata</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Metadata</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMetadata(Metadata object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseConnection(Connection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Metadata Table</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Metadata Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMetadataTable(MetadataTable object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Metadata Column</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Metadata Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMetadataColumn(MetadataColumn object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Abstract Metadata Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Abstract Metadata Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseAbstractMetadataObject(AbstractMetadataObject object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>File Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>File Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseFileConnection(FileConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Delimited File Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Delimited File Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDelimitedFileConnection(DelimitedFileConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Positional File Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Positional File Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePositionalFileConnection(PositionalFileConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Database Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Database Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDatabaseConnection(DatabaseConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>CSV File Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>CSV File Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCSVFileConnection(CSVFileConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Regexp File Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Regexp File Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseRegexpFileConnection(RegexpFileConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Xml File Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Xml File Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXmlFileConnection(XmlFileConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Schema Target</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Schema Target</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSchemaTarget(SchemaTarget object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Queries Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Queries Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueriesConnection(QueriesConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQuery(Query object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Ldif File Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Ldif File Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLdifFileConnection(LdifFileConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Xml XPath Loop Descriptor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Xml XPath Loop Descriptor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseXmlXPathLoopDescriptor(XmlXPathLoopDescriptor object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Generic Schema Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Generic Schema Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGenericSchemaConnection(GenericSchemaConnection object) {
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

} //ConnectionSwitch
