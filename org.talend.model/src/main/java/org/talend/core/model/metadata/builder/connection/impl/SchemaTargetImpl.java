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
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.MetadataSchema;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schema Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl#getXPathQuery <em>XPath Query</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl#isIsBoucle <em>Is Boucle</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl#getLimitBoucle <em>Limit Boucle</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.SchemaTargetImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SchemaTargetImpl extends EObjectImpl implements SchemaTarget 
{
    /**
     * The default value of the '{@link #getXPathQuery() <em>XPath Query</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXPathQuery()
     * @generated
     * @ordered
     */
    protected static final String XPATH_QUERY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getXPathQuery() <em>XPath Query</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXPathQuery()
     * @generated
     * @ordered
     */
    protected String xPathQuery = XPATH_QUERY_EDEFAULT;

    /**
     * The default value of the '{@link #getTagName() <em>Tag Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTagName()
     * @generated
     * @ordered
     */
    protected static final String TAG_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTagName() <em>Tag Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTagName()
     * @generated
     * @ordered
     */
    protected String tagName = TAG_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isIsBoucle() <em>Is Boucle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsBoucle()
     * @generated
     * @ordered
     */
    protected static final boolean IS_BOUCLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsBoucle() <em>Is Boucle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsBoucle()
     * @generated
     * @ordered
     */
    protected boolean isBoucle = IS_BOUCLE_EDEFAULT;

    /**
     * The default value of the '{@link #getLimitBoucle() <em>Limit Boucle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLimitBoucle()
     * @generated
     * @ordered
     */
    protected static final int LIMIT_BOUCLE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getLimitBoucle() <em>Limit Boucle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLimitBoucle()
     * @generated
     * @ordered
     */
    protected int limitBoucle = LIMIT_BOUCLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SchemaTargetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.SCHEMA_TARGET;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getXPathQuery() {
        return xPathQuery;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXPathQuery(String newXPathQuery) {
        String oldXPathQuery = xPathQuery;
        xPathQuery = newXPathQuery;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SCHEMA_TARGET__XPATH_QUERY, oldXPathQuery, xPathQuery));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTagName(String newTagName) {
        String oldTagName = tagName;
        tagName = newTagName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SCHEMA_TARGET__TAG_NAME, oldTagName, tagName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsBoucle() {
        return isBoucle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsBoucle(boolean newIsBoucle) {
        boolean oldIsBoucle = isBoucle;
        isBoucle = newIsBoucle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SCHEMA_TARGET__IS_BOUCLE, oldIsBoucle, isBoucle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getLimitBoucle() {
        return limitBoucle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLimitBoucle(int newLimitBoucle) {
        int oldLimitBoucle = limitBoucle;
        limitBoucle = newLimitBoucle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SCHEMA_TARGET__LIMIT_BOUCLE, oldLimitBoucle, limitBoucle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MetadataSchema getSchema() {
        if (eContainerFeatureID != ConnectionPackage.SCHEMA_TARGET__SCHEMA) return null;
        return (MetadataSchema)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSchema(MetadataSchema newSchema, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSchema, ConnectionPackage.SCHEMA_TARGET__SCHEMA, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSchema(MetadataSchema newSchema) {
        if (newSchema != eInternalContainer() || (eContainerFeatureID != ConnectionPackage.SCHEMA_TARGET__SCHEMA && newSchema != null)) {
            if (EcoreUtil.isAncestor(this, newSchema))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSchema != null)
                msgs = ((InternalEObject)newSchema).eInverseAdd(this, ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS, MetadataSchema.class, msgs);
            msgs = basicSetSchema(newSchema, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.SCHEMA_TARGET__SCHEMA, newSchema, newSchema));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.SCHEMA_TARGET__SCHEMA:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSchema((MetadataSchema)otherEnd, msgs);
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
            case ConnectionPackage.SCHEMA_TARGET__SCHEMA:
                return basicSetSchema(null, msgs);
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
            case ConnectionPackage.SCHEMA_TARGET__SCHEMA:
                return eInternalContainer().eInverseRemove(this, ConnectionPackage.METADATA_SCHEMA__SCHEMA_TARGETS, MetadataSchema.class, msgs);
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
            case ConnectionPackage.SCHEMA_TARGET__XPATH_QUERY:
                return getXPathQuery();
            case ConnectionPackage.SCHEMA_TARGET__TAG_NAME:
                return getTagName();
            case ConnectionPackage.SCHEMA_TARGET__IS_BOUCLE:
                return isIsBoucle() ? Boolean.TRUE : Boolean.FALSE;
            case ConnectionPackage.SCHEMA_TARGET__LIMIT_BOUCLE:
                return new Integer(getLimitBoucle());
            case ConnectionPackage.SCHEMA_TARGET__SCHEMA:
                return getSchema();
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
            case ConnectionPackage.SCHEMA_TARGET__XPATH_QUERY:
                setXPathQuery((String)newValue);
                return;
            case ConnectionPackage.SCHEMA_TARGET__TAG_NAME:
                setTagName((String)newValue);
                return;
            case ConnectionPackage.SCHEMA_TARGET__IS_BOUCLE:
                setIsBoucle(((Boolean)newValue).booleanValue());
                return;
            case ConnectionPackage.SCHEMA_TARGET__LIMIT_BOUCLE:
                setLimitBoucle(((Integer)newValue).intValue());
                return;
            case ConnectionPackage.SCHEMA_TARGET__SCHEMA:
                setSchema((MetadataSchema)newValue);
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
            case ConnectionPackage.SCHEMA_TARGET__XPATH_QUERY:
                setXPathQuery(XPATH_QUERY_EDEFAULT);
                return;
            case ConnectionPackage.SCHEMA_TARGET__TAG_NAME:
                setTagName(TAG_NAME_EDEFAULT);
                return;
            case ConnectionPackage.SCHEMA_TARGET__IS_BOUCLE:
                setIsBoucle(IS_BOUCLE_EDEFAULT);
                return;
            case ConnectionPackage.SCHEMA_TARGET__LIMIT_BOUCLE:
                setLimitBoucle(LIMIT_BOUCLE_EDEFAULT);
                return;
            case ConnectionPackage.SCHEMA_TARGET__SCHEMA:
                setSchema((MetadataSchema)null);
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
            case ConnectionPackage.SCHEMA_TARGET__XPATH_QUERY:
                return XPATH_QUERY_EDEFAULT == null ? xPathQuery != null : !XPATH_QUERY_EDEFAULT.equals(xPathQuery);
            case ConnectionPackage.SCHEMA_TARGET__TAG_NAME:
                return TAG_NAME_EDEFAULT == null ? tagName != null : !TAG_NAME_EDEFAULT.equals(tagName);
            case ConnectionPackage.SCHEMA_TARGET__IS_BOUCLE:
                return isBoucle != IS_BOUCLE_EDEFAULT;
            case ConnectionPackage.SCHEMA_TARGET__LIMIT_BOUCLE:
                return limitBoucle != LIMIT_BOUCLE_EDEFAULT;
            case ConnectionPackage.SCHEMA_TARGET__SCHEMA:
                return getSchema() != null;
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
        result.append(" (XPathQuery: ");
        result.append(xPathQuery);
        result.append(", TagName: ");
        result.append(tagName);
        result.append(", IsBoucle: ");
        result.append(isBoucle);
        result.append(", LimitBoucle: ");
        result.append(limitBoucle);
        result.append(')');
        return result.toString();
    }

} //SchemaTargetImpl