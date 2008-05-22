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
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getCompositeIndicator()
 * @model
 * @generated
 */
public interface CompositeIndicator extends Indicator {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    EList<Indicator> getChildIndicators();

} // CompositeIndicator
