/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.talend.dataquality.indicators.*;

import orgomg.cwm.analysis.informationvisualization.RenderedObject;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.Element;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;

import orgomg.cwmx.analysis.informationreporting.ReportField;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.talend.dataquality.indicators.IndicatorsPackage
 * @generated
 */
public class IndicatorsAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static IndicatorsPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IndicatorsAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = IndicatorsPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch the delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IndicatorsSwitch<Adapter> modelSwitch =
        new IndicatorsSwitch<Adapter>() {
            @Override
            public Adapter caseIndicator(Indicator object) {
                return createIndicatorAdapter();
            }
            @Override
            public Adapter caseRowCountIndicator(RowCountIndicator object) {
                return createRowCountIndicatorAdapter();
            }
            @Override
            public Adapter caseMeanIndicator(MeanIndicator object) {
                return createMeanIndicatorAdapter();
            }
            @Override
            public Adapter caseSumIndicator(SumIndicator object) {
                return createSumIndicatorAdapter();
            }
            @Override
            public Adapter caseCompositeIndicator(CompositeIndicator object) {
                return createCompositeIndicatorAdapter();
            }
            @Override
            public Adapter caseRangeIndicator(RangeIndicator object) {
                return createRangeIndicatorAdapter();
            }
            @Override
            public Adapter caseBoxIndicator(BoxIndicator object) {
                return createBoxIndicatorAdapter();
            }
            @Override
            public Adapter caseIndicatorType(IndicatorType object) {
                return createIndicatorTypeAdapter();
            }
            @Override
            public Adapter caseIntegerSumIndicator(IntegerSumIndicator object) {
                return createIntegerSumIndicatorAdapter();
            }
            @Override
            public Adapter caseDoubleSumIndicator(DoubleSumIndicator object) {
                return createDoubleSumIndicatorAdapter();
            }
            @Override
            public Adapter caseBigDecimalIndicator(BigDecimalIndicator object) {
                return createBigDecimalIndicatorAdapter();
            }
            @Override
            public Adapter caseFrequencyIndicator(FrequencyIndicator object) {
                return createFrequencyIndicatorAdapter();
            }
            @Override
            public Adapter caseIntegerMeanIndicator(IntegerMeanIndicator object) {
                return createIntegerMeanIndicatorAdapter();
            }
            @Override
            public Adapter caseDoubleMeanIndicator(DoubleMeanIndicator object) {
                return createDoubleMeanIndicatorAdapter();
            }
            @Override
            public Adapter caseBigDecimalMeanIndicator(BigDecimalMeanIndicator object) {
                return createBigDecimalMeanIndicatorAdapter();
            }
            @Override
            public Adapter caseBlankCountIndicator(BlankCountIndicator object) {
                return createBlankCountIndicatorAdapter();
            }
            @Override
            public Adapter caseIndicatorParameters(IndicatorParameters object) {
                return createIndicatorParametersAdapter();
            }
            @Override
            public Adapter caseMedianIndicator(MedianIndicator object) {
                return createMedianIndicatorAdapter();
            }
            @Override
            public Adapter caseValueIndicator(ValueIndicator object) {
                return createValueIndicatorAdapter();
            }
            @Override
            public Adapter caseMinValueIndicator(MinValueIndicator object) {
                return createMinValueIndicatorAdapter();
            }
            @Override
            public Adapter caseMaxValueIndicator(MaxValueIndicator object) {
                return createMaxValueIndicatorAdapter();
            }
            @Override
            public Adapter caseModeIndicator(ModeIndicator object) {
                return createModeIndicatorAdapter();
            }
            @Override
            public Adapter caseNullCountIndicator(NullCountIndicator object) {
                return createNullCountIndicatorAdapter();
            }
            @Override
            public Adapter caseDistinctCountIndicator(DistinctCountIndicator object) {
                return createDistinctCountIndicatorAdapter();
            }
            @Override
            public Adapter caseUniqueCountIndicator(UniqueCountIndicator object) {
                return createUniqueCountIndicatorAdapter();
            }
            @Override
            public Adapter caseDuplicateCountIndicator(DuplicateCountIndicator object) {
                return createDuplicateCountIndicatorAdapter();
            }
            @Override
            public Adapter caseIQRIndicator(IQRIndicator object) {
                return createIQRIndicatorAdapter();
            }
            @Override
            public Adapter caseElement(Element object) {
                return createElementAdapter();
            }
            @Override
            public Adapter caseModelElement(ModelElement object) {
                return createModelElementAdapter();
            }
            @Override
            public Adapter caseNamespace(Namespace object) {
                return createNamespaceAdapter();
            }
            @Override
            public Adapter caseClassifier(Classifier object) {
                return createClassifierAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.Indicator <em>Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.Indicator
     * @generated
     */
    public Adapter createIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.RowCountIndicator <em>Row Count Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.RowCountIndicator
     * @generated
     */
    public Adapter createRowCountIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.MeanIndicator <em>Mean Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.MeanIndicator
     * @generated
     */
    public Adapter createMeanIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.SumIndicator <em>Sum Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.SumIndicator
     * @generated
     */
    public Adapter createSumIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.CompositeIndicator <em>Composite Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.CompositeIndicator
     * @generated
     */
    public Adapter createCompositeIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.RangeIndicator <em>Range Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.RangeIndicator
     * @generated
     */
    public Adapter createRangeIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.BoxIndicator <em>Box Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.BoxIndicator
     * @generated
     */
    public Adapter createBoxIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.IndicatorType <em>Indicator Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.IndicatorType
     * @generated
     */
    public Adapter createIndicatorTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.IntegerSumIndicator <em>Integer Sum Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.IntegerSumIndicator
     * @generated
     */
    public Adapter createIntegerSumIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.DoubleSumIndicator <em>Double Sum Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.DoubleSumIndicator
     * @generated
     */
    public Adapter createDoubleSumIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.BigDecimalIndicator <em>Big Decimal Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.BigDecimalIndicator
     * @generated
     */
    public Adapter createBigDecimalIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.FrequencyIndicator <em>Frequency Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.FrequencyIndicator
     * @generated
     */
    public Adapter createFrequencyIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.IntegerMeanIndicator <em>Integer Mean Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.IntegerMeanIndicator
     * @generated
     */
    public Adapter createIntegerMeanIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.DoubleMeanIndicator <em>Double Mean Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.DoubleMeanIndicator
     * @generated
     */
    public Adapter createDoubleMeanIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.BigDecimalMeanIndicator <em>Big Decimal Mean Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.BigDecimalMeanIndicator
     * @generated
     */
    public Adapter createBigDecimalMeanIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.BlankCountIndicator <em>Blank Count Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.BlankCountIndicator
     * @generated
     */
    public Adapter createBlankCountIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.IndicatorParameters <em>Indicator Parameters</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.IndicatorParameters
     * @generated
     */
    public Adapter createIndicatorParametersAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.MedianIndicator <em>Median Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.MedianIndicator
     * @generated
     */
    public Adapter createMedianIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.ValueIndicator <em>Value Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.ValueIndicator
     * @generated
     */
    public Adapter createValueIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.MinValueIndicator <em>Min Value Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.MinValueIndicator
     * @generated
     */
    public Adapter createMinValueIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.MaxValueIndicator <em>Max Value Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.MaxValueIndicator
     * @generated
     */
    public Adapter createMaxValueIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.ModeIndicator <em>Mode Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.ModeIndicator
     * @generated
     */
    public Adapter createModeIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.NullCountIndicator <em>Null Count Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.NullCountIndicator
     * @generated
     */
    public Adapter createNullCountIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.DistinctCountIndicator <em>Distinct Count Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.DistinctCountIndicator
     * @generated
     */
    public Adapter createDistinctCountIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.UniqueCountIndicator <em>Unique Count Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.UniqueCountIndicator
     * @generated
     */
    public Adapter createUniqueCountIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.DuplicateCountIndicator <em>Duplicate Count Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.DuplicateCountIndicator
     * @generated
     */
    public Adapter createDuplicateCountIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.dataquality.indicators.IQRIndicator <em>IQR Indicator</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.dataquality.indicators.IQRIndicator
     * @generated
     */
    public Adapter createIQRIndicatorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.core.Element <em>Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see orgomg.cwm.objectmodel.core.Element
     * @generated
     */
    public Adapter createElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.core.ModelElement <em>Model Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see orgomg.cwm.objectmodel.core.ModelElement
     * @generated
     */
    public Adapter createModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.core.Namespace <em>Namespace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see orgomg.cwm.objectmodel.core.Namespace
     * @generated
     */
    public Adapter createNamespaceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link orgomg.cwm.objectmodel.core.Classifier <em>Classifier</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see orgomg.cwm.objectmodel.core.Classifier
     * @generated
     */
    public Adapter createClassifierAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //IndicatorsAdapterFactory
