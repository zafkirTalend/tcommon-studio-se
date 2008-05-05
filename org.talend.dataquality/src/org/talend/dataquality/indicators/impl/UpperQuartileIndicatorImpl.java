/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.UpperQuartileIndicator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Upper Quartile Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class UpperQuartileIndicatorImpl extends MaxValueIndicatorImpl implements UpperQuartileIndicator {

    private static Logger log = Logger.getLogger(UpperQuartileIndicatorImpl.class);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected UpperQuartileIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.UPPER_QUARTILE_INDICATOR;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.impl.IndicatorImpl#storeSqlResults(java.util.List)
     * 
     * ADDED scorreia 2008-05-05 storeSqlResults(List<Object[]> objects)
     */
    @Override
    public boolean storeSqlResults(List<Object[]> objects) {
        if (!checkResults(objects, 1)) {
            return false;
        }

        // TODO scorreia should get the correct type of result from the analyzed element

        if (objects.size() == 1) {
            String med = String.valueOf(objects.get(0)[0]);
            if (med == null) {
                log.error("Upper quartile is null!!");
                return false;
            }
            this.setValue(med);
            // FIXME scorreia (set datatype here ?)
            return true;
        }
        return false;
    }
} // UpperQuartileIndicatorImpl
