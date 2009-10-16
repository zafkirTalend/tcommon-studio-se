/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SAP Function Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getOutputType <em>Output Type</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getOutputTableName <em>Output Table Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getDocument <em>Document</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getInputParameterTable <em>Input Parameter Table</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getOutputParameterTable <em>Output Parameter Table</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getMetadataTable <em>Metadata Table</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getTables <em>Tables</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getTestInputParameterTable <em>Test Input Parameter Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit()
 * @model
 * @generated
 */
public interface SAPFunctionUnit extends AbstractMetadataObject {
	/**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Output Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Type</em>' attribute.
     * @see #setOutputType(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_OutputType()
     * @model
     * @generated
     */
    String getOutputType();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getOutputType <em>Output Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Type</em>' attribute.
     * @see #getOutputType()
     * @generated
     */
    void setOutputType(String value);

    /**
     * Returns the value of the '<em><b>Output Table Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Table Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Table Name</em>' attribute.
     * @see #setOutputTableName(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_OutputTableName()
     * @model
     * @generated
     */
    String getOutputTableName();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getOutputTableName <em>Output Table Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Table Name</em>' attribute.
     * @see #getOutputTableName()
     * @generated
     */
    void setOutputTableName(String value);

    /**
     * Returns the value of the '<em><b>Document</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Document</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Document</em>' attribute.
     * @see #setDocument(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_Document()
     * @model
     * @generated
     */
	String getDocument();

	/**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getDocument <em>Document</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Document</em>' attribute.
     * @see #getDocument()
     * @generated
     */
	void setDocument(String value);

	/**
     * Returns the value of the '<em><b>Input Parameter Table</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.InputSAPFunctionParameterTable#getFunctionUnit <em>Function Unit</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Parameter Table</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Input Parameter Table</em>' containment reference.
     * @see #setInputParameterTable(InputSAPFunctionParameterTable)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_InputParameterTable()
     * @see org.talend.core.model.metadata.builder.connection.InputSAPFunctionParameterTable#getFunctionUnit
     * @model opposite="functionUnit" containment="true"
     * @generated
     */
	InputSAPFunctionParameterTable getInputParameterTable();

	/**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getInputParameterTable <em>Input Parameter Table</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Parameter Table</em>' containment reference.
     * @see #getInputParameterTable()
     * @generated
     */
	void setInputParameterTable(InputSAPFunctionParameterTable value);

	/**
     * Returns the value of the '<em><b>Output Parameter Table</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.OutputSAPFunctionParameterTable#getFunctionUnit <em>Function Unit</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Parameter Table</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Output Parameter Table</em>' containment reference.
     * @see #setOutputParameterTable(OutputSAPFunctionParameterTable)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_OutputParameterTable()
     * @see org.talend.core.model.metadata.builder.connection.OutputSAPFunctionParameterTable#getFunctionUnit
     * @model opposite="functionUnit" containment="true"
     * @generated
     */
	OutputSAPFunctionParameterTable getOutputParameterTable();

	/**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getOutputParameterTable <em>Output Parameter Table</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Parameter Table</em>' containment reference.
     * @see #getOutputParameterTable()
     * @generated
     */
	void setOutputParameterTable(OutputSAPFunctionParameterTable value);

	/**
     * Returns the value of the '<em><b>Metadata Table</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata Table</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Metadata Table</em>' containment reference.
     * @see #setMetadataTable(MetadataTable)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_MetadataTable()
     * @model containment="true"
     * @generated
     */
	MetadataTable getMetadataTable();

	/**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getMetadataTable <em>Metadata Table</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Metadata Table</em>' containment reference.
     * @see #getMetadataTable()
     * @generated
     */
	void setMetadataTable(MetadataTable value);

	/**
     * Returns the value of the '<em><b>Connection</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.SAPConnection#getFuntions <em>Funtions</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Connection</em>' container reference.
     * @see #setConnection(SAPConnection)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_Connection()
     * @see org.talend.core.model.metadata.builder.connection.SAPConnection#getFuntions
     * @model opposite="Funtions" transient="false"
     * @generated
     */
	SAPConnection getConnection();

	/**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getConnection <em>Connection</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Connection</em>' container reference.
     * @see #getConnection()
     * @generated
     */
	void setConnection(SAPConnection value);

    /**
     * Returns the value of the '<em><b>Tables</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.metadata.builder.connection.MetadataTable}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tables</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tables</em>' containment reference list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_Tables()
     * @model type="org.talend.core.model.metadata.builder.connection.MetadataTable" containment="true"
     * @generated
     */
    EList getTables();

    /**
     * Returns the value of the '<em><b>Test Input Parameter Table</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.SAPTestInputParameterTable#getFunctionUnit <em>Function Unit</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Test Input Parameter Table</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Test Input Parameter Table</em>' containment reference.
     * @see #setTestInputParameterTable(SAPTestInputParameterTable)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getSAPFunctionUnit_TestInputParameterTable()
     * @see org.talend.core.model.metadata.builder.connection.SAPTestInputParameterTable#getFunctionUnit
     * @model opposite="functionUnit" containment="true"
     * @generated
     */
    SAPTestInputParameterTable getTestInputParameterTable();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.SAPFunctionUnit#getTestInputParameterTable <em>Test Input Parameter Table</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Test Input Parameter Table</em>' containment reference.
     * @see #getTestInputParameterTable()
     * @generated
     */
    void setTestInputParameterTable(SAPTestInputParameterTable value);

} // SAPFunctionUnit
