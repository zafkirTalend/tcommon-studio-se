/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.CompositeIndicator#getIndicators <em>Indicators</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getCompositeIndicator()
 * @model
 * @generated
 */
public interface CompositeIndicator extends Indicator {
    /**
     * Returns the value of the '<em><b>Indicators</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.dataquality.indicators.Indicator}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Indicators</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Indicators</em>' containment reference list.
     * @see org.talend.dataquality.indicators.IndicatorsPackage#getCompositeIndicator_Indicators()
     * @model containment="true"
     * @generated
     */
    EList<Indicator> getIndicators();

} // CompositeIndicator
