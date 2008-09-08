/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.InputSAPFunctionParameterTable;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.metadata.builder.connection.OutputSAPFunctionParameterTable;
import org.talend.core.model.metadata.builder.connection.SAPConnection;
import org.talend.core.model.metadata.builder.connection.SAPFunctionUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SAP Function Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SAPFunctionUnitImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SAPFunctionUnitImpl#getDocument <em>Document</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SAPFunctionUnitImpl#getInputParameterTable <em>Input Parameter Table</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SAPFunctionUnitImpl#getOutputParameterTable <em>Output Parameter Table</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SAPFunctionUnitImpl#getMetadataTable <em>Metadata Table</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SAPFunctionUnitImpl#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SAPFunctionUnitImpl extends AbstractMetadataObjectImpl implements SAPFunctionUnit {
	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getDocument() <em>Document</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocument()
     * @generated
     * @ordered
     */
	protected static final String DOCUMENT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDocument() <em>Document</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocument()
     * @generated
     * @ordered
     */
	protected String document = DOCUMENT_EDEFAULT;

	/**
     * The cached value of the '{@link #getInputParameterTable() <em>Input Parameter Table</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInputParameterTable()
     * @generated
     * @ordered
     */
	protected InputSAPFunctionParameterTable inputParameterTable;

	/**
     * The cached value of the '{@link #getOutputParameterTable() <em>Output Parameter Table</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOutputParameterTable()
     * @generated
     * @ordered
     */
	protected OutputSAPFunctionParameterTable outputParameterTable;

	/**
     * The cached value of the '{@link #getMetadataTable() <em>Metadata Table</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMetadataTable()
     * @generated
     * @ordered
     */
	protected MetadataTable metadataTable;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SAPFunctionUnitImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return ConnectionPackage.Literals.SAP_FUNCTION_UNIT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getDocument() {
        return document;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDocument(String newDocument) {
        String oldDocument = document;
        document = newDocument;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__DOCUMENT, oldDocument, document));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public InputSAPFunctionParameterTable getInputParameterTable() {
        return inputParameterTable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInputParameterTable(InputSAPFunctionParameterTable newInputParameterTable, NotificationChain msgs) {
        InputSAPFunctionParameterTable oldInputParameterTable = inputParameterTable;
        inputParameterTable = newInputParameterTable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE, oldInputParameterTable, newInputParameterTable);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setInputParameterTable(InputSAPFunctionParameterTable newInputParameterTable) {
        if (newInputParameterTable != inputParameterTable) {
            NotificationChain msgs = null;
            if (inputParameterTable != null)
                msgs = ((InternalEObject)inputParameterTable).eInverseRemove(this, ConnectionPackage.INPUT_SAP_FUNCTION_PARAMETER_TABLE__FUNCTION_UNIT, InputSAPFunctionParameterTable.class, msgs);
            if (newInputParameterTable != null)
                msgs = ((InternalEObject)newInputParameterTable).eInverseAdd(this, ConnectionPackage.INPUT_SAP_FUNCTION_PARAMETER_TABLE__FUNCTION_UNIT, InputSAPFunctionParameterTable.class, msgs);
            msgs = basicSetInputParameterTable(newInputParameterTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE, newInputParameterTable, newInputParameterTable));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OutputSAPFunctionParameterTable getOutputParameterTable() {
        return outputParameterTable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetOutputParameterTable(OutputSAPFunctionParameterTable newOutputParameterTable, NotificationChain msgs) {
        OutputSAPFunctionParameterTable oldOutputParameterTable = outputParameterTable;
        outputParameterTable = newOutputParameterTable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE, oldOutputParameterTable, newOutputParameterTable);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOutputParameterTable(OutputSAPFunctionParameterTable newOutputParameterTable) {
        if (newOutputParameterTable != outputParameterTable) {
            NotificationChain msgs = null;
            if (outputParameterTable != null)
                msgs = ((InternalEObject)outputParameterTable).eInverseRemove(this, ConnectionPackage.OUTPUT_SAP_FUNCTION_PARAMETER_TABLE__FUNCTION_UNIT, OutputSAPFunctionParameterTable.class, msgs);
            if (newOutputParameterTable != null)
                msgs = ((InternalEObject)newOutputParameterTable).eInverseAdd(this, ConnectionPackage.OUTPUT_SAP_FUNCTION_PARAMETER_TABLE__FUNCTION_UNIT, OutputSAPFunctionParameterTable.class, msgs);
            msgs = basicSetOutputParameterTable(newOutputParameterTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE, newOutputParameterTable, newOutputParameterTable));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MetadataTable getMetadataTable() {
        return metadataTable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMetadataTable(MetadataTable newMetadataTable, NotificationChain msgs) {
        MetadataTable oldMetadataTable = metadataTable;
        metadataTable = newMetadataTable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE, oldMetadataTable, newMetadataTable);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMetadataTable(MetadataTable newMetadataTable) {
        if (newMetadataTable != metadataTable) {
            NotificationChain msgs = null;
            if (metadataTable != null)
                msgs = ((InternalEObject)metadataTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE, null, msgs);
            if (newMetadataTable != null)
                msgs = ((InternalEObject)newMetadataTable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE, null, msgs);
            msgs = basicSetMetadataTable(newMetadataTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE, newMetadataTable, newMetadataTable));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SAPConnection getConnection() {
        if (eContainerFeatureID != ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION) return null;
        return (SAPConnection)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetConnection(SAPConnection newConnection, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newConnection, ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setConnection(SAPConnection newConnection) {
        if (newConnection != eInternalContainer() || (eContainerFeatureID != ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION && newConnection != null)) {
            if (EcoreUtil.isAncestor(this, newConnection))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newConnection != null)
                msgs = ((InternalEObject)newConnection).eInverseAdd(this, ConnectionPackage.SAP_CONNECTION__FUNTIONS, SAPConnection.class, msgs);
            msgs = basicSetConnection(newConnection, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION, newConnection, newConnection));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE:
                if (inputParameterTable != null)
                    msgs = ((InternalEObject)inputParameterTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE, null, msgs);
                return basicSetInputParameterTable((InputSAPFunctionParameterTable)otherEnd, msgs);
            case ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE:
                if (outputParameterTable != null)
                    msgs = ((InternalEObject)outputParameterTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE, null, msgs);
                return basicSetOutputParameterTable((OutputSAPFunctionParameterTable)otherEnd, msgs);
            case ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetConnection((SAPConnection)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE:
                return basicSetInputParameterTable(null, msgs);
            case ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE:
                return basicSetOutputParameterTable(null, msgs);
            case ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE:
                return basicSetMetadataTable(null, msgs);
            case ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION:
                return basicSetConnection(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION:
                return eInternalContainer().eInverseRemove(this, ConnectionPackage.SAP_CONNECTION__FUNTIONS, SAPConnection.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectionPackage.SAP_FUNCTION_UNIT__NAME:
                return getName();
            case ConnectionPackage.SAP_FUNCTION_UNIT__DOCUMENT:
                return getDocument();
            case ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE:
                return getInputParameterTable();
            case ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE:
                return getOutputParameterTable();
            case ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE:
                return getMetadataTable();
            case ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION:
                return getConnection();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ConnectionPackage.SAP_FUNCTION_UNIT__NAME:
                setName((String)newValue);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__DOCUMENT:
                setDocument((String)newValue);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE:
                setInputParameterTable((InputSAPFunctionParameterTable)newValue);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE:
                setOutputParameterTable((OutputSAPFunctionParameterTable)newValue);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE:
                setMetadataTable((MetadataTable)newValue);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION:
                setConnection((SAPConnection)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID) {
            case ConnectionPackage.SAP_FUNCTION_UNIT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__DOCUMENT:
                setDocument(DOCUMENT_EDEFAULT);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE:
                setInputParameterTable((InputSAPFunctionParameterTable)null);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE:
                setOutputParameterTable((OutputSAPFunctionParameterTable)null);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE:
                setMetadataTable((MetadataTable)null);
                return;
            case ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION:
                setConnection((SAPConnection)null);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ConnectionPackage.SAP_FUNCTION_UNIT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ConnectionPackage.SAP_FUNCTION_UNIT__DOCUMENT:
                return DOCUMENT_EDEFAULT == null ? document != null : !DOCUMENT_EDEFAULT.equals(document);
            case ConnectionPackage.SAP_FUNCTION_UNIT__INPUT_PARAMETER_TABLE:
                return inputParameterTable != null;
            case ConnectionPackage.SAP_FUNCTION_UNIT__OUTPUT_PARAMETER_TABLE:
                return outputParameterTable != null;
            case ConnectionPackage.SAP_FUNCTION_UNIT__METADATA_TABLE:
                return metadataTable != null;
            case ConnectionPackage.SAP_FUNCTION_UNIT__CONNECTION:
                return getConnection() != null;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (Name: ");
        result.append(name);
        result.append(", Document: ");
        result.append(document);
        result.append(')');
        return result.toString();
    }
	
    public boolean isReadOnly() {
        Connection connection = getConnection();
        return connection == null ? false: connection.isReadOnly();
    }

} //SAPFunctionUnitImpl
