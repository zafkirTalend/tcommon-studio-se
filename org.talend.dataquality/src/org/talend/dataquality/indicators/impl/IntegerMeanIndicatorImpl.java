/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.ecore.EClass;

import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.IntegerMeanIndicator;
import org.talend.dataquality.indicators.MeanIndicator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Integer Mean Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IntegerMeanIndicatorImpl extends IntegerSumIndicatorImpl implements IntegerMeanIndicator {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected IntegerMeanIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.INTEGER_MEAN_INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public double getMean() {
        long c = getCount();
        return (c == 0) ? 0 : ((double) super.getSum()) / super.getCount();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getMeanWithNulls(double valueForNull) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl#handle(java.lang.Object)
     */
    @Override
    public boolean handle(Object data) {
        boolean handled = super.handle(data);
        return handled;
    }

    @Override
    public String toString() {
        return "Mean = " + getMean();
    }

} // IntegerMeanIndicatorImpl
