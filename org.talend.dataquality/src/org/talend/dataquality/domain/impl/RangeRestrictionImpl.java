/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.domain.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.dataquality.domain.DomainPackage;
import org.talend.dataquality.domain.LiteralValue;
import org.talend.dataquality.domain.RangeRestriction;

import org.talend.dataquality.expressions.BooleanExpressionNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Range Restriction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.domain.impl.RangeRestrictionImpl#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.impl.RangeRestrictionImpl#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.impl.RangeRestrictionImpl#getExpressions <em>Expressions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RangeRestrictionImpl extends EObjectImpl implements RangeRestriction {
    /**
     * The cached value of the '{@link #getLowerValue() <em>Lower Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLowerValue()
     * @generated
     * @ordered
     */
    protected LiteralValue lowerValue;

    /**
     * The cached value of the '{@link #getUpperValue() <em>Upper Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpperValue()
     * @generated
     * @ordered
     */
    protected LiteralValue upperValue;

    /**
     * The cached value of the '{@link #getExpressions() <em>Expressions</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpressions()
     * @generated
     * @ordered
     */
    protected BooleanExpressionNode expressions;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RangeRestrictionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DomainPackage.Literals.RANGE_RESTRICTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LiteralValue getLowerValue() {
        if (lowerValue != null && lowerValue.eIsProxy()) {
            InternalEObject oldLowerValue = (InternalEObject)lowerValue;
            lowerValue = (LiteralValue)eResolveProxy(oldLowerValue);
            if (lowerValue != oldLowerValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.RANGE_RESTRICTION__LOWER_VALUE, oldLowerValue, lowerValue));
            }
        }
        return lowerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LiteralValue basicGetLowerValue() {
        return lowerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLowerValue(LiteralValue newLowerValue) {
        LiteralValue oldLowerValue = lowerValue;
        lowerValue = newLowerValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.RANGE_RESTRICTION__LOWER_VALUE, oldLowerValue, lowerValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LiteralValue getUpperValue() {
        if (upperValue != null && upperValue.eIsProxy()) {
            InternalEObject oldUpperValue = (InternalEObject)upperValue;
            upperValue = (LiteralValue)eResolveProxy(oldUpperValue);
            if (upperValue != oldUpperValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.RANGE_RESTRICTION__UPPER_VALUE, oldUpperValue, upperValue));
            }
        }
        return upperValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LiteralValue basicGetUpperValue() {
        return upperValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpperValue(LiteralValue newUpperValue) {
        LiteralValue oldUpperValue = upperValue;
        upperValue = newUpperValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.RANGE_RESTRICTION__UPPER_VALUE, oldUpperValue, upperValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BooleanExpressionNode getExpressions() {
        if (expressions != null && expressions.eIsProxy()) {
            InternalEObject oldExpressions = (InternalEObject)expressions;
            expressions = (BooleanExpressionNode)eResolveProxy(oldExpressions);
            if (expressions != oldExpressions) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.RANGE_RESTRICTION__EXPRESSIONS, oldExpressions, expressions));
            }
        }
        return expressions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BooleanExpressionNode basicGetExpressions() {
        return expressions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExpressions(BooleanExpressionNode newExpressions) {
        BooleanExpressionNode oldExpressions = expressions;
        expressions = newExpressions;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.RANGE_RESTRICTION__EXPRESSIONS, oldExpressions, expressions));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DomainPackage.RANGE_RESTRICTION__LOWER_VALUE:
                if (resolve) return getLowerValue();
                return basicGetLowerValue();
            case DomainPackage.RANGE_RESTRICTION__UPPER_VALUE:
                if (resolve) return getUpperValue();
                return basicGetUpperValue();
            case DomainPackage.RANGE_RESTRICTION__EXPRESSIONS:
                if (resolve) return getExpressions();
                return basicGetExpressions();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case DomainPackage.RANGE_RESTRICTION__LOWER_VALUE:
                setLowerValue((LiteralValue)newValue);
                return;
            case DomainPackage.RANGE_RESTRICTION__UPPER_VALUE:
                setUpperValue((LiteralValue)newValue);
                return;
            case DomainPackage.RANGE_RESTRICTION__EXPRESSIONS:
                setExpressions((BooleanExpressionNode)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case DomainPackage.RANGE_RESTRICTION__LOWER_VALUE:
                setLowerValue((LiteralValue)null);
                return;
            case DomainPackage.RANGE_RESTRICTION__UPPER_VALUE:
                setUpperValue((LiteralValue)null);
                return;
            case DomainPackage.RANGE_RESTRICTION__EXPRESSIONS:
                setExpressions((BooleanExpressionNode)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case DomainPackage.RANGE_RESTRICTION__LOWER_VALUE:
                return lowerValue != null;
            case DomainPackage.RANGE_RESTRICTION__UPPER_VALUE:
                return upperValue != null;
            case DomainPackage.RANGE_RESTRICTION__EXPRESSIONS:
                return expressions != null;
        }
        return super.eIsSet(featureID);
    }

} //RangeRestrictionImpl
