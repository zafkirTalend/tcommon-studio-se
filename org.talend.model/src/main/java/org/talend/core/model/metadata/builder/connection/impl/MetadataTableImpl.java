/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metadata Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataTableImpl#getSourceName <em>Source Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataTableImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataTableImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataTableImpl#getTableType <em>Table Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetadataTableImpl extends AbstractMetadataObjectImpl implements MetadataTable 
{
    /**
     * The default value of the '{@link #getSourceName() <em>Source Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceName()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceName() <em>Source Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceName()
     * @generated
     * @ordered
     */
    protected String sourceName = SOURCE_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getColumns() <em>Columns</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumns()
     * @generated
     * @ordered
     */
    protected EList columns = null;

    /**
     * The default value of the '{@link #getTableType() <em>Table Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableType()
     * @generated
     * @ordered
     */
    protected static final String TABLE_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTableType() <em>Table Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableType()
     * @generated
     * @ordered
     */
    protected String tableType = TABLE_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MetadataTableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.METADATA_TABLE;
    }

    public boolean isReadOnly() {
        Connection connection = getConnection();
        return connection == null ? false: connection.isReadOnly();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceName(String newSourceName) {
        String oldSourceName = sourceName;
        sourceName = newSourceName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_TABLE__SOURCE_NAME, oldSourceName, sourceName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getColumns() {
        if (columns == null) {
            columns = new EObjectContainmentWithInverseEList(MetadataColumn.class, this, ConnectionPackage.METADATA_TABLE__COLUMNS, ConnectionPackage.METADATA_COLUMN__TABLE);
        }
        return columns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Connection getConnection() {
        if (eContainerFeatureID != ConnectionPackage.METADATA_TABLE__CONNECTION) return null;
        return (Connection)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConnection(Connection newConnection, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newConnection, ConnectionPackage.METADATA_TABLE__CONNECTION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConnection(Connection newConnection) {
        if (newConnection != eInternalContainer() || (eContainerFeatureID != ConnectionPackage.METADATA_TABLE__CONNECTION && newConnection != null)) {
            if (EcoreUtil.isAncestor(this, newConnection))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newConnection != null)
                msgs = ((InternalEObject)newConnection).eInverseAdd(this, ConnectionPackage.CONNECTION__TABLES, Connection.class, msgs);
            msgs = basicSetConnection(newConnection, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_TABLE__CONNECTION, newConnection, newConnection));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTableType() {
        return tableType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableType(String newTableType) {
        String oldTableType = tableType;
        tableType = newTableType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_TABLE__TABLE_TYPE, oldTableType, tableType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.METADATA_TABLE__COLUMNS:
                return ((InternalEList)getColumns()).basicAdd(otherEnd, msgs);
            case ConnectionPackage.METADATA_TABLE__CONNECTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetConnection((Connection)otherEnd, msgs);
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
            case ConnectionPackage.METADATA_TABLE__COLUMNS:
                return ((InternalEList)getColumns()).basicRemove(otherEnd, msgs);
            case ConnectionPackage.METADATA_TABLE__CONNECTION:
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
            case ConnectionPackage.METADATA_TABLE__CONNECTION:
                return eInternalContainer().eInverseRemove(this, ConnectionPackage.CONNECTION__TABLES, Connection.class, msgs);
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
            case ConnectionPackage.METADATA_TABLE__SOURCE_NAME:
                return getSourceName();
            case ConnectionPackage.METADATA_TABLE__COLUMNS:
                return getColumns();
            case ConnectionPackage.METADATA_TABLE__CONNECTION:
                return getConnection();
            case ConnectionPackage.METADATA_TABLE__TABLE_TYPE:
                return getTableType();
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
            case ConnectionPackage.METADATA_TABLE__SOURCE_NAME:
                setSourceName((String)newValue);
                return;
            case ConnectionPackage.METADATA_TABLE__COLUMNS:
                getColumns().clear();
                getColumns().addAll((Collection)newValue);
                return;
            case ConnectionPackage.METADATA_TABLE__CONNECTION:
                setConnection((Connection)newValue);
                return;
            case ConnectionPackage.METADATA_TABLE__TABLE_TYPE:
                setTableType((String)newValue);
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
            case ConnectionPackage.METADATA_TABLE__SOURCE_NAME:
                setSourceName(SOURCE_NAME_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_TABLE__COLUMNS:
                getColumns().clear();
                return;
            case ConnectionPackage.METADATA_TABLE__CONNECTION:
                setConnection((Connection)null);
                return;
            case ConnectionPackage.METADATA_TABLE__TABLE_TYPE:
                setTableType(TABLE_TYPE_EDEFAULT);
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
            case ConnectionPackage.METADATA_TABLE__SOURCE_NAME:
                return SOURCE_NAME_EDEFAULT == null ? sourceName != null : !SOURCE_NAME_EDEFAULT.equals(sourceName);
            case ConnectionPackage.METADATA_TABLE__COLUMNS:
                return columns != null && !columns.isEmpty();
            case ConnectionPackage.METADATA_TABLE__CONNECTION:
                return getConnection() != null;
            case ConnectionPackage.METADATA_TABLE__TABLE_TYPE:
                return TABLE_TYPE_EDEFAULT == null ? tableType != null : !TABLE_TYPE_EDEFAULT.equals(tableType);
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
        result.append(" (sourceName: ");
        result.append(sourceName);
        result.append(", tableType: ");
        result.append(tableType);
        result.append(')');
        return result.toString();
    }

} //MetadataTableImpl