/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.apache.log4j.Logger;
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

    private static Logger log = Logger.getLogger(RowCountIndicatorImpl.class);

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

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.impl.IndicatorImpl#storeSqlResults(java.lang.String, java.lang.Object[])
     * @generated NOT
     */
    @Override
    public boolean storeSqlResults(String query, Object[] objects) {
        if (objects == null || objects.length != 1) {
            log.error("unexpected result for query " + query + ": " + objects);
            return false;
        }
        Long c = (Long) objects[0];
        if (c == null) {
            log.error("unexpected result for query " + query + ". Count is null!!");
            return false;
        }
        // TODO store query in instantiated expression
        this.setCount(c);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataquality.indicators.impl.IndicatorImpl#toString()
     * 
     * @generated NOT
     */
    @Override
    public String toString() {
        return "Count = " + count;
    }

} // RowCountIndicatorImpl
