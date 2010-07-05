/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;


import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Xml File Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getXsdFilePath <em>Xsd File Path</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getXmlFilePath <em>Xml File Path</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#isGuess <em>Guess</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getMaskXPattern <em>Mask XPattern</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getGroup <em>Group</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getRoot <em>Root</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getLoop <em>Loop</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#isInputModel <em>Input Model</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getOutputFilePath <em>Output File Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection()
 * @model
 * @generated
 */
public interface XmlFileConnection extends Connection {
    /**
     * Returns the value of the '<em><b>Xsd File Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xsd File Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xsd File Path</em>' attribute.
     * @see #setXsdFilePath(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_XsdFilePath()
     * @model
     * @generated
     */
    String getXsdFilePath();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getXsdFilePath <em>Xsd File Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xsd File Path</em>' attribute.
     * @see #getXsdFilePath()
     * @generated
     */
    void setXsdFilePath(String value);

    /**
     * Returns the value of the '<em><b>Xml File Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xml File Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xml File Path</em>' attribute.
     * @see #setXmlFilePath(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_XmlFilePath()
     * @model
     * @generated
     */
    String getXmlFilePath();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getXmlFilePath <em>Xml File Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xml File Path</em>' attribute.
     * @see #getXmlFilePath()
     * @generated
     */
    void setXmlFilePath(String value);

    /**
     * Returns the value of the '<em><b>Guess</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Guess</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Guess</em>' attribute.
     * @see #setGuess(boolean)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_Guess()
     * @model
     * @generated
     */
    boolean isGuess();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#isGuess <em>Guess</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Guess</em>' attribute.
     * @see #isGuess()
     * @generated
     */
    void setGuess(boolean value);

    /**
     * Returns the value of the '<em><b>Mask XPattern</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mask XPattern</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mask XPattern</em>' attribute.
     * @see #setMaskXPattern(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_MaskXPattern()
     * @model
     * @generated
     */
    String getMaskXPattern();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getMaskXPattern <em>Mask XPattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mask XPattern</em>' attribute.
     * @see #getMaskXPattern()
     * @generated
     */
    void setMaskXPattern(String value);

    /**
     * Returns the value of the '<em><b>Schema</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor}.
     * It is bidirectional and its opposite is '{@link org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Schema</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Schema</em>' containment reference list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_Schema()
     * @see org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor#getConnection
     * @model type="org.talend.core.model.metadata.builder.connection.XmlXPathLoopDescriptor" opposite="connection" containment="true"
     * @generated
     */
    EList getSchema();

    /**
     * Returns the value of the '<em><b>Encoding</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Encoding</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Encoding</em>' attribute.
     * @see #setEncoding(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_Encoding()
     * @model
     * @generated
     */
    String getEncoding();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getEncoding <em>Encoding</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Encoding</em>' attribute.
     * @see #getEncoding()
     * @generated
     */
    void setEncoding(String value);

    /**
     * Returns the value of the '<em><b>Group</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.metadata.builder.connection.XMLFileNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Group</em>' containment reference list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_Group()
     * @model type="org.talend.core.model.metadata.builder.connection.XMLFileNode" containment="true"
     * @generated
     */
    EList getGroup();

    /**
     * Returns the value of the '<em><b>Root</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.metadata.builder.connection.XMLFileNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root</em>' containment reference list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_Root()
     * @model type="org.talend.core.model.metadata.builder.connection.XMLFileNode" containment="true" ordered="false"
     * @generated
     */
    EList getRoot();

    /**
     * Returns the value of the '<em><b>Loop</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.core.model.metadata.builder.connection.XMLFileNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Loop</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loop</em>' containment reference list.
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_Loop()
     * @model type="org.talend.core.model.metadata.builder.connection.XMLFileNode" containment="true"
     * @generated
     */
    EList getLoop();

    /**
     * Returns the value of the '<em><b>Input Model</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Model</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Model</em>' attribute.
     * @see #setInputModel(boolean)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_InputModel()
     * @model
     * @generated
     */
    boolean isInputModel();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#isInputModel <em>Input Model</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Model</em>' attribute.
     * @see #isInputModel()
     * @generated
     */
    void setInputModel(boolean value);

    /**
     * Returns the value of the '<em><b>Output File Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output File Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output File Path</em>' attribute.
     * @see #setOutputFilePath(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getXmlFileConnection_OutputFilePath()
     * @model
     * @generated
     */
    String getOutputFilePath();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.XmlFileConnection#getOutputFilePath <em>Output File Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output File Path</em>' attribute.
     * @see #getOutputFilePath()
     * @generated
     */
    void setOutputFilePath(String value);

} // XmlFileConnection