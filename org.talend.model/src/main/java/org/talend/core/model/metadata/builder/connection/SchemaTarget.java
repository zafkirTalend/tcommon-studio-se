/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schema Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getXPathQuery <em>XPath Query</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#isIsBoucle <em>Is Boucle</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getLimitBoucle <em>Limit Boucle</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSchemaTarget()
 * @model
 * @generated
 */
public interface SchemaTarget extends AbstractMetadataObject
{
  /**
   * Returns the value of the '<em><b>XPath Query</b></em>' attribute.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XPath Query</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>XPath Query</em>' attribute.
   * @see #setXPathQuery(String)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSchemaTarget_XPathQuery()
   * @model
   * @generated
   */
    String getXPathQuery();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getXPathQuery <em>XPath Query</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @param value the new value of the '<em>XPath Query</em>' attribute.
   * @see #getXPathQuery()
   * @generated
   */
    void setXPathQuery(String value);

  /**
   * Returns the value of the '<em><b>Tag Name</b></em>' attribute.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tag Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Tag Name</em>' attribute.
   * @see #setTagName(String)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSchemaTarget_TagName()
   * @model
   * @generated
   */
    String getTagName();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getTagName <em>Tag Name</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tag Name</em>' attribute.
   * @see #getTagName()
   * @generated
   */
    void setTagName(String value);

  /**
   * Returns the value of the '<em><b>Is Boucle</b></em>' attribute.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Boucle</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Is Boucle</em>' attribute.
   * @see #setIsBoucle(boolean)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSchemaTarget_IsBoucle()
   * @model
   * @generated
   */
    boolean isIsBoucle();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#isIsBoucle <em>Is Boucle</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Boucle</em>' attribute.
   * @see #isIsBoucle()
   * @generated
   */
    void setIsBoucle(boolean value);

  /**
   * Returns the value of the '<em><b>Limit Boucle</b></em>' attribute.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Limit Boucle</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Limit Boucle</em>' attribute.
   * @see #setLimitBoucle(int)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSchemaTarget_LimitBoucle()
   * @model
   * @generated
   */
    int getLimitBoucle();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getLimitBoucle <em>Limit Boucle</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @param value the new value of the '<em>Limit Boucle</em>' attribute.
   * @see #getLimitBoucle()
   * @generated
   */
    void setLimitBoucle(int value);

  /**
   * Returns the value of the '<em><b>Schema</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.MetadataSchema#getSchemaTargets <em>Schema Targets</em>}'.
   * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Schema</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
   * @return the value of the '<em>Schema</em>' container reference.
   * @see #setSchema(MetadataSchema)
   * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSchemaTarget_Schema()
   * @see org.talend.core.model.metadata.builder.connection.MetadataSchema#getSchemaTargets
   * @model opposite="schemaTargets"
   * @generated
   */
    MetadataSchema getSchema();

  /**
   * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SchemaTarget#getSchema <em>Schema</em>}' container reference.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @param value the new value of the '<em>Schema</em>' container reference.
   * @see #getSchema()
   * @generated
   */
    void setSchema(MetadataSchema value);

} // SchemaTarget