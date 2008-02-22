/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.talend.dataquality.indicators.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IndicatorsFactoryImpl extends EFactoryImpl implements IndicatorsFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static IndicatorsFactory init() {
        try {
            IndicatorsFactory theIndicatorsFactory = (IndicatorsFactory)EPackage.Registry.INSTANCE.getEFactory("http://.dataquality.indicators"); 
            if (theIndicatorsFactory != null) {
                return theIndicatorsFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new IndicatorsFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IndicatorsFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case IndicatorsPackage.INDICATOR: return createIndicator();
            case IndicatorsPackage.ROW_COUNT_INDICATOR: return createRowCountIndicator();
            case IndicatorsPackage.MEAN_INDICATOR: return createMeanIndicator();
            case IndicatorsPackage.SUM_INDICATOR: return createSumIndicator();
            case IndicatorsPackage.COMPOSITE_INDICATOR: return createCompositeIndicator();
            case IndicatorsPackage.RANGE_INDICATOR: return createRangeIndicator();
            case IndicatorsPackage.BOX_INDICATOR: return createBoxIndicator();
            case IndicatorsPackage.INDICATOR_TYPE: return createIndicatorType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Indicator createIndicator() {
        IndicatorImpl indicator = new IndicatorImpl();
        return indicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RowCountIndicator createRowCountIndicator() {
        RowCountIndicatorImpl rowCountIndicator = new RowCountIndicatorImpl();
        return rowCountIndicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MeanIndicator createMeanIndicator() {
        MeanIndicatorImpl meanIndicator = new MeanIndicatorImpl();
        return meanIndicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SumIndicator createSumIndicator() {
        SumIndicatorImpl sumIndicator = new SumIndicatorImpl();
        return sumIndicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CompositeIndicator createCompositeIndicator() {
        CompositeIndicatorImpl compositeIndicator = new CompositeIndicatorImpl();
        return compositeIndicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RangeIndicator createRangeIndicator() {
        RangeIndicatorImpl rangeIndicator = new RangeIndicatorImpl();
        return rangeIndicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoxIndicator createBoxIndicator() {
        BoxIndicatorImpl boxIndicator = new BoxIndicatorImpl();
        return boxIndicator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IndicatorType createIndicatorType() {
        IndicatorTypeImpl indicatorType = new IndicatorTypeImpl();
        return indicatorType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IndicatorsPackage getIndicatorsPackage() {
        return (IndicatorsPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static IndicatorsPackage getPackage() {
        return IndicatorsPackage.eINSTANCE;
    }

} //IndicatorsFactoryImpl
