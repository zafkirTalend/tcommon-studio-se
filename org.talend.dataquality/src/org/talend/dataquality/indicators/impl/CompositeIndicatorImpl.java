/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.talend.dataquality.indicators.CompositeIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Composite Indicator</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class CompositeIndicatorImpl extends IndicatorImpl implements CompositeIndicator {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CompositeIndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.COMPOSITE_INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Indicator> getChildIndicators() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * Method "addChildToList".
     * 
     * @param child a child (can be null) to add to the given list
     * @param children the list in which to insert non null children
     */
    protected void addChildToList(final Indicator child, final EList<Indicator> children) {
        if (child != null) {
            children.add(child);
        }
    }
} // CompositeIndicatorImpl
