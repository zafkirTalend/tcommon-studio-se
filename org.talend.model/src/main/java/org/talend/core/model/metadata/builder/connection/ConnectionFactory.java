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
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage
 * @generated
 */
public interface ConnectionFactory extends EFactory {

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    ConnectionFactory eINSTANCE = org.talend.core.model.metadata.builder.connection.impl.ConnectionFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Metadata</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Metadata</em>'.
     * @generated
     */
    Metadata createMetadata();

    /**
     * Returns a new object of class '<em>Connection</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Connection</em>'.
     * @generated
     */
    Connection createConnection();

    /**
     * Returns a new object of class '<em>Metadata Table</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Metadata Table</em>'.
     * @generated
     */
    MetadataTable createMetadataTable();

    /**
     * Returns a new object of class '<em>Metadata Column</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * Returns a new object of class '<em>Positional File Connection</em>'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return a new object of class '<em>Positional File Connection</em>'.
     * @generated
     */
    PositionalFileConnection createPositionalFileConnection();

    /**
     * Returns a new object of class '<em>Database Connection</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Database Connection</em>'.
     * @generated
     */
    DatabaseConnection createDatabaseConnection();

    /**
     * Returns a new object of class '<em>Regexp File Connection</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return a new object of class '<em>Regexp File Connection</em>'.
     * @generated
     */
    RegexpFileConnection createRegexpFileConnection();

    /**
     * Returns a new object of class '<em>Xml File Connection</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Xml File Connection</em>'.
     * @generated
     */
    XmlFileConnection createXmlFileConnection();

    /**
     * Returns a new object of class '<em>Schema Target</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Schema Target</em>'.
     * @generated
     */
    SchemaTarget createSchemaTarget();

    /**
     * Returns a new object of class '<em>Queries Connection</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Queries Connection</em>'.
     * @generated
     */
    QueriesConnection createQueriesConnection();

    /**
     * Returns a new object of class '<em>Query</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Query</em>'.
     * @generated
     */
    Query createQuery();

    /**
     * Returns a new object of class '<em>Ldif File Connection</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Ldif File Connection</em>'.
     * @generated
     */
    LdifFileConnection createLdifFileConnection();

    /**
     * Returns a new object of class '<em>Xml XPath Loop Descriptor</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Xml XPath Loop Descriptor</em>'.
     * @generated
     */
    XmlXPathLoopDescriptor createXmlXPathLoopDescriptor();

    /**
     * Returns a new object of class '<em>Generic Schema Connection</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Generic Schema Connection</em>'.
     * @generated
     */
    GenericSchemaConnection createGenericSchemaConnection();

    /**
     * Returns a new object of class '<em>LDAP Schema Connection</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>LDAP Schema Connection</em>'.
     * @generated
     */
    LDAPSchemaConnection createLDAPSchemaConnection();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
