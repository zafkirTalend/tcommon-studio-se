/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EDIFACT Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#getXmlName <em>Xml Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#getXmlPath <em>Xml Path</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#isInputModel <em>Input Model</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#getXmlNode <em>Xml Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getEDIFACTConnection()
 * @model
 * @generated
 */
public interface EDIFACTConnection extends Connection {

    /**
     * Returns the value of the '<em><b>Xml Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xml Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xml Name</em>' attribute.
     * @see #setXmlName(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getEDIFACTConnection_XmlName()
     * @model
     * @generated
     */
    String getXmlName();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#getXmlName <em>Xml Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xml Name</em>' attribute.
     * @see #getXmlName()
     * @generated
     */
    void setXmlName(String value);

    /**
     * Returns the value of the '<em><b>File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>File Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>File Name</em>' attribute.
     * @see #setFileName(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getEDIFACTConnection_FileName()
     * @model
     * @generated
     */
    String getFileName();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#getFileName <em>File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>File Name</em>' attribute.
     * @see #getFileName()
     * @generated
     */
    void setFileName(String value);

    /**
     * Returns the value of the '<em><b>Xml Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xml Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xml Path</em>' attribute.
     * @see #setXmlPath(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getEDIFACTConnection_XmlPath()
     * @model
     * @generated
     */
    String getXmlPath();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#getXmlPath <em>Xml Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xml Path</em>' attribute.
     * @see #getXmlPath()
     * @generated
     */
    void setXmlPath(String value);

    /**
     * Returns the value of the '<em><b>Input Model</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Model</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Model</em>' attribute.
     * @see #setInputModel(boolean)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getEDIFACTConnection_InputModel()
     * @model default="true"
     * @generated
     */
    boolean isInputModel();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#isInputModel <em>Input Model</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Model</em>' attribute.
     * @see #isInputModel()
     * @generated
     */
    void setInputModel(boolean value);

    /**
     * Returns the value of the '<em><b>Xml Node</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xml Node</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xml Node</em>' attribute.
     * @see #setXmlNode(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getEDIFACTConnection_XmlNode()
     * @model
     * @generated
     */
    String getXmlNode();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.EDIFACTConnection#getXmlNode <em>Xml Node</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xml Node</em>' attribute.
     * @see #getXmlNode()
     * @generated
     */
    void setXmlNode(String value);

} // EDIFACTConnection
