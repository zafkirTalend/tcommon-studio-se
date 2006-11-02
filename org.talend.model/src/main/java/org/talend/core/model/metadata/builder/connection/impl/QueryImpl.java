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
import org.talend.core.model.metadata.builder.connection.QueriesConnection;
import org.talend.core.model.metadata.builder.connection.Query;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.QueryImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.QueryImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.QueryImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.QueryImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.QueryImpl#getQueries <em>Queries</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryImpl extends EObjectImpl implements Query {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComment()
     * @generated
     * @ordered
     */
    protected static final String COMMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComment()
     * @generated
     * @ordered
     */
    protected String comment = COMMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected String value = VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.QUERY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.QUERY__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.QUERY__LABEL, oldLabel, label));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getComment() {
        return comment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComment(String newComment) {
        String oldComment = comment;
        comment = newComment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.QUERY__COMMENT, oldComment, comment));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue(String newValue) {
        String oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.QUERY__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueriesConnection getQueries() {
        if (eContainerFeatureID != ConnectionPackage.QUERY__QUERIES) return null;
        return (QueriesConnection)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQueries(QueriesConnection newQueries, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQueries, ConnectionPackage.QUERY__QUERIES, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueries(QueriesConnection newQueries) {
        if (newQueries != eInternalContainer() || (eContainerFeatureID != ConnectionPackage.QUERY__QUERIES && newQueries != null)) {
            if (EcoreUtil.isAncestor(this, newQueries))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQueries != null)
                msgs = ((InternalEObject)newQueries).eInverseAdd(this, ConnectionPackage.QUERIES_CONNECTION__QUERY, QueriesConnection.class, msgs);
            msgs = basicSetQueries(newQueries, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.QUERY__QUERIES, newQueries, newQueries));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.QUERY__QUERIES:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
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
            case ConnectionPackage.QUERY__QUERIES:
                return basicSetQueries(null, msgs);
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
            case ConnectionPackage.QUERY__QUERIES:
                return eInternalContainer().eInverseRemove(this, ConnectionPackage.QUERIES_CONNECTION__QUERY, QueriesConnection.class, msgs);
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
            case ConnectionPackage.QUERY__ID:
                return getId();
            case ConnectionPackage.QUERY__LABEL:
                return getLabel();
            case ConnectionPackage.QUERY__COMMENT:
                return getComment();
            case ConnectionPackage.QUERY__VALUE:
                return getValue();
            case ConnectionPackage.QUERY__QUERIES:
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
            case ConnectionPackage.QUERY__ID:
                setId((String)newValue);
                return;
            case ConnectionPackage.QUERY__LABEL:
                setLabel((String)newValue);
                return;
            case ConnectionPackage.QUERY__COMMENT:
                setComment((String)newValue);
                return;
            case ConnectionPackage.QUERY__VALUE:
                setValue((String)newValue);
                return;
            case ConnectionPackage.QUERY__QUERIES:
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
            case ConnectionPackage.QUERY__ID:
                setId(ID_EDEFAULT);
                return;
            case ConnectionPackage.QUERY__LABEL:
                setLabel(LABEL_EDEFAULT);
                return;
            case ConnectionPackage.QUERY__COMMENT:
                setComment(COMMENT_EDEFAULT);
                return;
            case ConnectionPackage.QUERY__VALUE:
                setValue(VALUE_EDEFAULT);
                return;
            case ConnectionPackage.QUERY__QUERIES:
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
            case ConnectionPackage.QUERY__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case ConnectionPackage.QUERY__LABEL:
                return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
            case ConnectionPackage.QUERY__COMMENT:
                return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
            case ConnectionPackage.QUERY__VALUE:
                return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
            case ConnectionPackage.QUERY__QUERIES:
                return getQueries() != null;
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
        result.append(" (id: ");
        result.append(id);
        result.append(", label: ");
        result.append(label);
        result.append(", comment: ");
        result.append(comment);
        result.append(", value: ");
        result.append(value);
        result.append(')');
        return result.toString();
    }

} //QueryImpl