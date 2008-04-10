/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;

import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Distinct Count Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.talend.dataquality.indicators.IndicatorsPackage#getDistinctCountIndicator()
 * @model
 * @generated
 */
public interface DistinctCountIndicator extends Indicator {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the set of distinct values. (This set is larger than the Unique values set).
     * <!-- end-model-doc -->
     * @model kind="operation" dataType="org.talend.dataquality.indicators.JavaSet"
     * @generated
     */
    Set<Object> getDistinctValues();
} // DistinctCountIndicator
