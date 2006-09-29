/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage
 * @generated
 */
public interface ConnectionFactory extends EFactory {

    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    ConnectionFactory eINSTANCE = org.talend.core.model.metadata.builder.connection.impl.ConnectionFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Metadata</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Metadata</em>'.
     * @generated
     */
    Metadata createMetadata();

    /**
     * Returns a new object of class '<em>Connection</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Connection</em>'.
     * @generated
     */
    Connection createConnection();

    /**
     * Returns a new object of class '<em>Metadata Table</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Metadata Table</em>'.
     * @generated
     */
    MetadataTable createMetadataTable();

    /**
     * Returns a new object of class '<em>Metadata Column</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Metadata Column</em>'.
     * @generated
     */
    MetadataColumn createMetadataColumn();

    /**
     * Returns a new object of class '<em>Delimited File Connection</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Delimited File Connection</em>'.
     * @generated
     */
    DelimitedFileConnection createDelimitedFileConnection();

    /**
     * Returns a new object of class '<em>Positional File Connection</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return a new object of class '<em>Positional File Connection</em>'.
     * @generated
     */
    PositionalFileConnection createPositionalFileConnection();

    /**
     * Returns a new object of class '<em>Database Connection</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Database Connection</em>'.
     * @generated
     */
    DatabaseConnection createDatabaseConnection();

    /**
     * Returns a new object of class '<em>CSV File Connection</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>CSV File Connection</em>'.
     * @generated
     */
    CSVFileConnection createCSVFileConnection();

    /**
     * Returns a new object of class '<em>Regexp File Connection</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Regexp File Connection</em>'.
     * @generated
     */
    RegexpFileConnection createRegexpFileConnection();

    /**
     * Returns a new object of class '<em>Xml File Connection</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Xml File Connection</em>'.
     * @generated
     */
    XmlFileConnection createXmlFileConnection();

    /**
     * Returns a new object of class '<em>Metadata Schema</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Metadata Schema</em>'.
     * @generated
     */
    MetadataSchema createMetadataSchema();

    /**
     * Returns a new object of class '<em>Schema Target</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Schema Target</em>'.
     * @generated
     */
    SchemaTarget createSchemaTarget();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    ConnectionPackage getConnectionPackage();

    /**
     * copy this MetadataColumn.
     * 
     * @return
     */
    MetadataColumn copy(MetadataColumn column, String newId);

} // ConnectionFactory
