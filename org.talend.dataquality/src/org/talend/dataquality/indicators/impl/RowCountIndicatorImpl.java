/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.ecore.EClass;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.RowCountIndicator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Row Count Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class RowCountIndicatorImpl extends IndicatorImpl implements RowCountIndicator {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected RowCountIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.ROW_COUNT_INDICATOR;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.impl.IndicatorImpl#handle(java.lang.Object)
     * 
     * @generated NOT
     */
    @Override
    public boolean handle(Object value) {
        return super.handle(value);
    }

    @Override
    public String toString() {
        return "Count = " + count;
    }

} // RowCountIndicatorImpl
