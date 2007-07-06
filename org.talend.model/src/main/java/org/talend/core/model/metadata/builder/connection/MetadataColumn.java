/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metadata Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getSourceType <em>Source Type</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getTalendType <em>Talend Type</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#isKey <em>Key</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getLength <em>Length</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getPrecision <em>Precision</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getTable <em>Table</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getOriginalField <em>Original Field</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getDisplayField <em>Display Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn()
 * @model
 * @generated
 */
public interface MetadataColumn extends AbstractMetadataObject {
    
    /**
     * Returns the value of the '<em><b>Source Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Type</em>' attribute.
     * @see #setSourceType(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_SourceType()
     * @model
     * @generated
     */
    String getSourceType();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getSourceType <em>Source Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Type</em>' attribute.
     * @see #getSourceType()
     * @generated
     */
    void setSourceType(String value);

    /**
     * Returns the value of the '<em><b>Default Value</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Value</em>' attribute.
     * @see #setDefaultValue(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_DefaultValue()
     * @model default=""
     * @generated
     */
    String getDefaultValue();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getDefaultValue <em>Default Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Value</em>' attribute.
     * @see #getDefaultValue()
     * @generated
     */
    void setDefaultValue(String value);

    /**
     * Returns the value of the '<em><b>Talend Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Talend Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Talend Type</em>' attribute.
     * @see #setTalendType(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_TalendType()
     * @model
     * @generated
     */
    String getTalendType();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getTalendType <em>Talend Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Talend Type</em>' attribute.
     * @see #getTalendType()
     * @generated
     */
    void setTalendType(String value);

    /**
     * Returns the value of the '<em><b>Key</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Key</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(boolean)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_Key()
     * @model default="false"
     * @generated
     */
    boolean isKey();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#isKey <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Key</em>' attribute.
     * @see #isKey()
     * @generated
     */
    void setKey(boolean value);

    /**
     * Returns the value of the '<em><b>Nullable</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nullable</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Nullable</em>' attribute.
     * @see #setNullable(boolean)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_Nullable()
     * @model default="true"
     * @generated
     */
    boolean isNullable();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#isNullable <em>Nullable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nullable</em>' attribute.
     * @see #isNullable()
     * @generated
     */
    void setNullable(boolean value);

    /**
     * Returns the value of the '<em><b>Length</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Length</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Length</em>' attribute.
     * @see #setLength(int)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_Length()
     * @model default="-1"
     * @generated
     */
    int getLength();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getLength <em>Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Length</em>' attribute.
     * @see #getLength()
     * @generated
     */
    void setLength(int value);

    /**
     * Returns the value of the '<em><b>Precision</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Precision</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Precision</em>' attribute.
     * @see #setPrecision(int)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_Precision()
     * @model default="-1"
     * @generated
     */
    int getPrecision();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getPrecision <em>Precision</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Precision</em>' attribute.
     * @see #getPrecision()
     * @generated
     */
    void setPrecision(int value);

    /**
     * Returns the value of the '<em><b>Table</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.MetadataTable#getColumns <em>Columns</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table</em>' container reference.
     * @see #setTable(MetadataTable)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_Table()
     * @see org.talend.core.model.metadata.builder.connection.MetadataTable#getColumns
     * @model opposite="columns"
     * @generated
     */
    MetadataTable getTable();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getTable <em>Table</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table</em>' container reference.
     * @see #getTable()
     * @generated
     */
    void setTable(MetadataTable value);

    /**
     * Returns the value of the '<em><b>Original Field</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Original Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Original Field</em>' attribute.
     * @see #setOriginalField(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_OriginalField()
     * @model default=""
     * @generated
     */
	String getOriginalField();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getOriginalField <em>Original Field</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Original Field</em>' attribute.
     * @see #getOriginalField()
     * @generated
     */
	void setOriginalField(String value);

    /**
     * Returns the value of the '<em><b>Pattern</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern</em>' attribute.
     * @see #setPattern(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_Pattern()
     * @model default=""
     * @generated
     */
    String getPattern();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getPattern <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern</em>' attribute.
     * @see #getPattern()
     * @generated
     */
    void setPattern(String value);

    /**
     * Returns the value of the '<em><b>Display Field</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Display Field</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Display Field</em>' attribute.
     * @see #setDisplayField(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getMetadataColumn_DisplayField()
     * @model
     * @generated
     */
    String getDisplayField();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.MetadataColumn#getDisplayField <em>Display Field</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Field</em>' attribute.
     * @see #getDisplayField()
     * @generated
     */
    void setDisplayField(String value);

} // MetadataColumn