/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.talendfile;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getContext <em>Context</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getNode <em>Node</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getLogs <em>Logs</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getComment <em>Comment</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getDefaultContext <em>Default Context</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getPurpose <em>Purpose</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getStatus <em>Status</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType()
 * @model extendedMetaData="name='Process_._type' kind='elementOnly'"
 * @generated
 */
public interface ProcessType extends EObject {
    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Description()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Context</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.designer.core.model.utils.emf.talendfile.ContextType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Context</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Context</em>' containment reference list.
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Context()
     * @model type="org.talend.designer.core.model.utils.emf.talendfile.ContextType" containment="true" required="true"
     *        extendedMetaData="kind='element' name='Context' namespace='##targetNamespace'"
     * @generated
     */
    EList getContext();

    /**
     * Returns the value of the '<em><b>Node</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.designer.core.model.utils.emf.talendfile.NodeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node</em>' containment reference list.
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Node()
     * @model type="org.talend.designer.core.model.utils.emf.talendfile.NodeType" containment="true" required="true"
     *        extendedMetaData="kind='element' name='Node' namespace='##targetNamespace'"
     * @generated
     */
    EList getNode();

    /**
     * Returns the value of the '<em><b>Connection</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.designer.core.model.utils.emf.talendfile.ConnectionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connection</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Connection</em>' containment reference list.
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Connection()
     * @model type="org.talend.designer.core.model.utils.emf.talendfile.ConnectionType" containment="true" required="true"
     *        extendedMetaData="kind='element' name='Connection' namespace='##targetNamespace'"
     * @generated
     */
    EList getConnection();

    /**
     * Returns the value of the '<em><b>Logs</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Logs</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Logs</em>' containment reference.
     * @see #setLogs(LogsType)
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Logs()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='Logs' namespace='##targetNamespace'"
     * @generated
     */
    LogsType getLogs();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getLogs <em>Logs</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Logs</em>' containment reference.
     * @see #getLogs()
     * @generated
     */
    void setLogs(LogsType value);

    /**
     * Returns the value of the '<em><b>Author</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Author</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Author</em>' attribute.
     * @see #setAuthor(String)
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Author()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='author' namespace='##targetNamespace'"
     * @generated
     */
    String getAuthor();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getAuthor <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Author</em>' attribute.
     * @see #getAuthor()
     * @generated
     */
    void setAuthor(String value);

    /**
     * Returns the value of the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comment</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comment</em>' attribute.
     * @see #setComment(String)
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Comment()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='comment' namespace='##targetNamespace'"
     * @generated
     */
    String getComment();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getComment <em>Comment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comment</em>' attribute.
     * @see #getComment()
     * @generated
     */
    void setComment(String value);

    /**
     * Returns the value of the '<em><b>Default Context</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Context</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Context</em>' attribute.
     * @see #setDefaultContext(String)
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_DefaultContext()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='defaultContext' namespace='##targetNamespace'"
     * @generated
     */
    String getDefaultContext();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getDefaultContext <em>Default Context</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Context</em>' attribute.
     * @see #getDefaultContext()
     * @generated
     */
    void setDefaultContext(String value);

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
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Name()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Purpose</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Purpose</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Purpose</em>' attribute.
     * @see #setPurpose(String)
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Purpose()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='purpose' namespace='##targetNamespace'"
     * @generated
     */
    String getPurpose();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getPurpose <em>Purpose</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Purpose</em>' attribute.
     * @see #getPurpose()
     * @generated
     */
    void setPurpose(String value);

    /**
     * Returns the value of the '<em><b>Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Status</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Status</em>' attribute.
     * @see #setStatus(String)
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Status()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='status' namespace='##targetNamespace'"
     * @generated
     */
    String getStatus();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getStatus <em>Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Status</em>' attribute.
     * @see #getStatus()
     * @generated
     */
    void setStatus(String value);

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage#getProcessType_Version()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='version' namespace='##targetNamespace'"
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

} // ProcessType