package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.ecore.EClass;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.MeanIndicator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Mean Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class MeanIndicatorImpl extends SumIndicatorImpl implements MeanIndicator {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected MeanIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.MEAN_INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public double getMean() {
        long c = getCount();
        if (super.genericSum == null) {// TODO scorreia check that this work
            if (c == 0) {
                throw new RuntimeException("Invalid mean!!");
            }
            Double sum = Double.valueOf(getSumStr());
            if (sum == null) {
                throw new RuntimeException("Invalid sum in mean computation!!");
            }
            return sum / c;
            // throw new RuntimeException("Problem when computing mean!!");
        }

        Double mean = super.genericSum.getMean(c);
        if (mean == null) {
            throw new RuntimeException("Invalid mean!!");
        }
        return mean;
    }

    /*
     * ADDED
     * 
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl#handle(java.lang.Object)
     */
    @Override
    public boolean handle(Object data) {
        boolean handled = super.handle(data);
        return handled;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * ADDED
     * 
     * @see org.talend.dataquality.indicators.impl.SumIndicatorImpl#toString()
     */
    @Override
    public String toString() {
        return "Mean = " + getMean();
    }
} // MeanIndicatorImpl
