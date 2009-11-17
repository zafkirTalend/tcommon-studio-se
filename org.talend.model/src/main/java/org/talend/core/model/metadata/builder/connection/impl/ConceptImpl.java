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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.core.model.metadata.builder.connection.Concept;
import org.talend.core.model.metadata.builder.connection.ConceptTarget;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.MDMConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.ConceptImpl#getLoopExpression <em>Loop Expression</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.ConceptImpl#getLoopLimit <em>Loop Limit</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.ConceptImpl#getConceptTargets <em>Concept Targets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConceptImpl extends MetadataTableImpl implements Concept {
    /**
     * The default value of the '{@link #getLoopExpression() <em>Loop Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopExpression()
     * @generated
     * @ordered
     */
    protected static final String LOOP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLoopExpression() <em>Loop Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopExpression()
     * @generated
     * @ordered
     */
    protected String loopExpression = LOOP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getLoopLimit() <em>Loop Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopLimit()
     * @generated
     * @ordered
     */
    protected static final Integer LOOP_LIMIT_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getLoopLimit() <em>Loop Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopLimit()
     * @generated
     * @ordered
     */
    protected Integer loopLimit = LOOP_LIMIT_EDEFAULT;

    /**
     * The cached value of the '{@link #getConceptTargets() <em>Concept Targets</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConceptTargets()
     * @generated
     * @ordered
     */
    protected EList conceptTargets;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConceptImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.CONCEPT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLoopExpression() {
        return loopExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLoopExpression(String newLoopExpression) {
        String oldLoopExpression = loopExpression;
        loopExpression = newLoopExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.CONCEPT__LOOP_EXPRESSION, oldLoopExpression, loopExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Integer getLoopLimit() {
        return loopLimit;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLoopLimit(Integer newLoopLimit) {
        Integer oldLoopLimit = loopLimit;
        loopLimit = newLoopLimit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.CONCEPT__LOOP_LIMIT, oldLoopLimit, loopLimit));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getConceptTargets() {
        if (conceptTargets == null) {
            conceptTargets = new EObjectContainmentWithInverseEList(ConceptTarget.class, this, ConnectionPackage.CONCEPT__CONCEPT_TARGETS, ConnectionPackage.CONCEPT_TARGET__SCHEMA);
        }
        return conceptTargets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.CONCEPT__CONCEPT_TARGETS:
                return ((InternalEList)getConceptTargets()).basicAdd(otherEnd, msgs);
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
            case ConnectionPackage.CONCEPT__CONCEPT_TARGETS:
                return ((InternalEList)getConceptTargets()).basicRemove(otherEnd, msgs);
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
            case ConnectionPackage.CONCEPT__LOOP_EXPRESSION:
                return getLoopExpression();
            case ConnectionPackage.CONCEPT__LOOP_LIMIT:
                return getLoopLimit();
            case ConnectionPackage.CONCEPT__CONCEPT_TARGETS:
                return getConceptTargets();
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
            case ConnectionPackage.CONCEPT__LOOP_EXPRESSION:
                setLoopExpression((String)newValue);
                return;
            case ConnectionPackage.CONCEPT__LOOP_LIMIT:
                setLoopLimit((Integer)newValue);
                return;
            case ConnectionPackage.CONCEPT__CONCEPT_TARGETS:
                getConceptTargets().clear();
                getConceptTargets().addAll((Collection)newValue);
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
            case ConnectionPackage.CONCEPT__LOOP_EXPRESSION:
                setLoopExpression(LOOP_EXPRESSION_EDEFAULT);
                return;
            case ConnectionPackage.CONCEPT__LOOP_LIMIT:
                setLoopLimit(LOOP_LIMIT_EDEFAULT);
                return;
            case ConnectionPackage.CONCEPT__CONCEPT_TARGETS:
                getConceptTargets().clear();
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
            case ConnectionPackage.CONCEPT__LOOP_EXPRESSION:
                return LOOP_EXPRESSION_EDEFAULT == null ? loopExpression != null : !LOOP_EXPRESSION_EDEFAULT.equals(loopExpression);
            case ConnectionPackage.CONCEPT__LOOP_LIMIT:
                return LOOP_LIMIT_EDEFAULT == null ? loopLimit != null : !LOOP_LIMIT_EDEFAULT.equals(loopLimit);
            case ConnectionPackage.CONCEPT__CONCEPT_TARGETS:
                return conceptTargets != null && !conceptTargets.isEmpty();
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
        result.append(" (LoopExpression: ");
        result.append(loopExpression);
        result.append(", LoopLimit: ");
        result.append(loopLimit);
        result.append(')');
        return result.toString();
    }

} //ConceptImpl
