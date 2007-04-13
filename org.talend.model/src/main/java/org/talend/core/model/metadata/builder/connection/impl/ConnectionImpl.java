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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.MetadataTable;

import org.talend.core.model.metadata.builder.connection.QueriesConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.ConnectionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.ConnectionImpl#getTables <em>Tables</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.ConnectionImpl#getQueries <em>Queries</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionImpl extends AbstractMetadataObjectImpl implements Connection 
{
    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String version = VERSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getTables() <em>Tables</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTables()
     * @generated
     * @ordered
     */
    protected EList tables = null;

    /**
     * The cached value of the '{@link #getQueries() <em>Queries</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueries()
     * @generated
     * @ordered
     */
    protected QueriesConnection queries = null;

    protected boolean readOnly = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.CONNECTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVersion() {
        return version;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean newReadOnly) {
        readOnly = newReadOnly;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.CONNECTION__VERSION, oldVersion, version));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTables() {
        if (tables == null) {
            tables = new EObjectContainmentWithInverseEList(MetadataTable.class, this, ConnectionPackage.CONNECTION__TABLES, ConnectionPackage.METADATA_TABLE__CONNECTION);
        }
        return tables;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueriesConnection getQueries() {
        return queries;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQueries(QueriesConnection newQueries, NotificationChain msgs) {
        QueriesConnection oldQueries = queries;
        queries = newQueries;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectionPackage.CONNECTION__QUERIES, oldQueries, newQueries);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueries(QueriesConnection newQueries) {
        if (newQueries != queries) {
            NotificationChain msgs = null;
            if (queries != null)
                msgs = ((InternalEObject)queries).eInverseRemove(this, ConnectionPackage.QUERIES_CONNECTION__CONNECTION, QueriesConnection.class, msgs);
            if (newQueries != null)
                msgs = ((InternalEObject)newQueries).eInverseAdd(this, ConnectionPackage.QUERIES_CONNECTION__CONNECTION, QueriesConnection.class, msgs);
            msgs = basicSetQueries(newQueries, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.CONNECTION__QUERIES, newQueries, newQueries));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.CONNECTION__TABLES:
                return ((InternalEList)getTables()).basicAdd(otherEnd, msgs);
            case ConnectionPackage.CONNECTION__QUERIES:
                if (queries != null)
                    msgs = ((InternalEObject)queries).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectionPackage.CONNECTION__QUERIES, null, msgs);
                return basicSetQueries((QueriesConnection)otherEnd, msgs);
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
            case ConnectionPackage.CONNECTION__TABLES:
                return ((InternalEList)getTables()).basicRemove(otherEnd, msgs);
            case ConnectionPackage.CONNECTION__QUERIES:
                return basicSetQueries(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectionPackage.CONNECTION__VERSION:
                return getVersion();
            case ConnectionPackage.CONNECTION__TABLES:
                return getTables();
            case ConnectionPackage.CONNECTION__QUERIES:
                return getQueries();
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
            case ConnectionPackage.CONNECTION__VERSION:
                setVersion((String)newValue);
                return;
            case ConnectionPackage.CONNECTION__TABLES:
                getTables().clear();
                getTables().addAll((Collection)newValue);
                return;
            case ConnectionPackage.CONNECTION__QUERIES:
                setQueries((QueriesConnection)newValue);
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
            case ConnectionPackage.CONNECTION__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case ConnectionPackage.CONNECTION__TABLES:
                getTables().clear();
                return;
            case ConnectionPackage.CONNECTION__QUERIES:
                setQueries((QueriesConnection)null);
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
            case ConnectionPackage.CONNECTION__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case ConnectionPackage.CONNECTION__TABLES:
                return tables != null && !tables.isEmpty();
            case ConnectionPackage.CONNECTION__QUERIES:
                return queries != null;
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
        result.append(" (version: ");
        result.append(version);
        result.append(')');
        return result.toString();
    }

} //ConnectionImpl