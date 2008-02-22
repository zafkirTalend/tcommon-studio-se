/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.domain;

import org.eclipse.emf.ecore.EObject;

import org.talend.dataquality.expressions.BooleanExpressionNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Range Restriction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.domain.RangeRestriction#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.RangeRestriction#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.RangeRestriction#getExpressions <em>Expressions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.domain.DomainPackage#getRangeRestriction()
 * @model
 * @generated
 */
public interface RangeRestriction extends EObject {
    /**
     * Returns the value of the '<em><b>Lower Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lower Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lower Value</em>' reference.
     * @see #setLowerValue(LiteralValue)
     * @see org.talend.dataquality.domain.DomainPackage#getRangeRestriction_LowerValue()
     * @model
     * @generated
     */
    LiteralValue getLowerValue();

    /**
     * Sets the value of the '{@link org.talend.dataquality.domain.RangeRestriction#getLowerValue <em>Lower Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lower Value</em>' reference.
     * @see #getLowerValue()
     * @generated
     */
    void setLowerValue(LiteralValue value);

    /**
     * Returns the value of the '<em><b>Upper Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Upper Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Upper Value</em>' reference.
     * @see #setUpperValue(LiteralValue)
     * @see org.talend.dataquality.domain.DomainPackage#getRangeRestriction_UpperValue()
     * @model
     * @generated
     */
    LiteralValue getUpperValue();

    /**
     * Sets the value of the '{@link org.talend.dataquality.domain.RangeRestriction#getUpperValue <em>Upper Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Upper Value</em>' reference.
     * @see #getUpperValue()
     * @generated
     */
    void setUpperValue(LiteralValue value);

    /**
     * Returns the value of the '<em><b>Expressions</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expressions</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expressions</em>' reference.
     * @see #setExpressions(BooleanExpressionNode)
     * @see org.talend.dataquality.domain.DomainPackage#getRangeRestriction_Expressions()
     * @model
     * @generated
     */
    BooleanExpressionNode getExpressions();

    /**
     * Sets the value of the '{@link org.talend.dataquality.domain.RangeRestriction#getExpressions <em>Expressions</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expressions</em>' reference.
     * @see #getExpressions()
     * @generated
     */
    void setExpressions(BooleanExpressionNode value);

} // RangeRestriction
