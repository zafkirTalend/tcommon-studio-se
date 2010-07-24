/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>WSDL Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.WSDLParameter#getElement <em>Element</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.WSDLParameter#getSource <em>Source</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.WSDLParameter#getColumn <em>Column</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.WSDLParameter#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getWSDLParameter()
 * @model
 * @generated
 */
public interface WSDLParameter extends EObject {

    /**
     * Returns the value of the '<em><b>Element</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Element</em>' attribute.
     * @see #setElement(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getWSDLParameter_Element()
     * @model
     * @generated
     */
    String getElement();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.WSDLParameter#getElement <em>Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Element</em>' attribute.
     * @see #getElement()
     * @generated
     */
    void setElement(String value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' attribute.
     * @see #setSource(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getWSDLParameter_Source()
     * @model
     * @generated
     */
    String getSource();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.WSDLParameter#getSource <em>Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' attribute.
     * @see #getSource()
     * @generated
     */
    void setSource(String value);

    /**
     * Returns the value of the '<em><b>Column</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Column</em>' attribute.
     * @see #setColumn(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getWSDLParameter_Column()
     * @model ordered="false"
     * @generated
     */
    String getColumn();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.WSDLParameter#getColumn <em>Column</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column</em>' attribute.
     * @see #getColumn()
     * @generated
     */
    void setColumn(String value);

    /**
     * Returns the value of the '<em><b>Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' attribute.
     * @see #setExpression(String)
     * @see org.talend.core.model.metadata.builder.connection.ConnectionPackage#getWSDLParameter_Expression()
     * @model
     * @generated
     */
    String getExpression();

    /**
     * Sets the value of the '{@link org.talend.core.model.metadata.builder.connection.WSDLParameter#getExpression <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' attribute.
     * @see #getExpression()
     * @generated
     */
    void setExpression(String value);

} // WSDLParameter
