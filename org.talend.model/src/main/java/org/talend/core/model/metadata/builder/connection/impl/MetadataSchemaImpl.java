/**
 * <copyright>
 * </copyright>
 *
 * $Id: MetadataSchemaImpl.java 295 2006-11-02 08:28:03 +0000 (jeu., 02 nov. 2006) smallet $
 */
package org.talend.core.model.metadata.builder.connection.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.MetadataSchema;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;
import org.talend.core.model.metadata.builder.connection.XmlFileConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metadata Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataSchemaImpl#getSchemaTargets <em>Schema Targets</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataSchemaImpl#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetadataSchemaImpl extends EObjectImpl implements MetadataSchema 
{
    /**
     * The cached value of the '{@link #getSchemaTargets() <em>Schema Targets</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSchemaTargets()
     * @generated
     * @ordered
     */
    protected EList schemaTargets = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MetadataSchemaImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.METADATA_SCHEMA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSchemaTargets() {
        if (schemaTargets == null) {
            schemaTargets = new EObjectContainmentWithInverseEList(SchemaTarget.class, this, ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS, ConnectionPackage.SCHEMA_TARGET__SCHEMA);
        }
        return schemaTargets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XmlFileConnection getConnection() {
        if (eContainerFeatureID != ConnectionPackage.METADATA_SCHEMA__CONNECTION) return null;
        return (XmlFileConnection)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConnection(XmlFileConnection newConnection, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newConnection, ConnectionPackage.METADATA_SCHEMA__CONNECTION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConnection(XmlFileConnection newConnection) {
        if (newConnection != eInternalContainer() || (eContainerFeatureID != ConnectionPackage.METADATA_SCHEMA__CONNECTION && newConnection != null)) {
            if (EcoreUtil.isAncestor(this, newConnection))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newConnection != null)
                msgs = ((InternalEObject)newConnection).eInverseAdd(this, ConnectionPackage.XML_FILE_CONNECTION__SCHEMA, XmlFileConnection.class, msgs);
            msgs = basicSetConnection(newConnection, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_SCHEMA__CONNECTION, newConnection, newConnection));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS:
                return ((InternalEList)getSchemaTargets()).basicAdd(otherEnd, msgs);
            case ConnectionPackage.METADATA_SCHEMA__CONNECTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetConnection((XmlFileConnection)otherEnd, msgs);
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
            case ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS:
                return ((InternalEList)getSchemaTargets()).basicRemove(otherEnd, msgs);
            case ConnectionPackage.METADATA_SCHEMA__CONNECTION:
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
            case ConnectionPackage.METADATA_SCHEMA__CONNECTION:
                return eInternalContainer().eInverseRemove(this, ConnectionPackage.XML_FILE_CONNECTION__SCHEMA, XmlFileConnection.class, msgs);
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
            case ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS:
                return getSchemaTargets();
            case ConnectionPackage.METADATA_SCHEMA__CONNECTION:
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
            case ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS:
                getSchemaTargets().clear();
                getSchemaTargets().addAll((Collection)newValue);
                return;
            case ConnectionPackage.METADATA_SCHEMA__CONNECTION:
                setConnection((XmlFileConnection)newValue);
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
            case ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS:
                getSchemaTargets().clear();
                return;
            case ConnectionPackage.METADATA_SCHEMA__CONNECTION:
                setConnection((XmlFileConnection)null);
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
            case ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS:
                return schemaTargets != null && !schemaTargets.isEmpty();
            case ConnectionPackage.METADATA_SCHEMA__CONNECTION:
                return getConnection() != null;
        }
        return super.eIsSet(featureID);
    }

} //MetadataSchemaImpl