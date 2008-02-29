/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.talend.dataquality.indicators.IndicatorsPackage
 * @generated
 */
public class IndicatorsSwitch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static IndicatorsPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IndicatorsSwitch() {
        if (modelPackage == null) {
            modelPackage = IndicatorsPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case IndicatorsPackage.INDICATOR: {
                Indicator indicator = (Indicator)theEObject;
                T result = caseIndicator(indicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.ROW_COUNT_INDICATOR: {
                RowCountIndicator rowCountIndicator = (RowCountIndicator)theEObject;
                T result = caseRowCountIndicator(rowCountIndicator);
                if (result == null) result = caseIndicator(rowCountIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.MEAN_INDICATOR: {
                MeanIndicator meanIndicator = (MeanIndicator)theEObject;
                T result = caseMeanIndicator(meanIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.SUM_INDICATOR: {
                SumIndicator sumIndicator = (SumIndicator)theEObject;
                T result = caseSumIndicator(sumIndicator);
                if (result == null) result = caseIndicator(sumIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.COMPOSITE_INDICATOR: {
                CompositeIndicator compositeIndicator = (CompositeIndicator)theEObject;
                T result = caseCompositeIndicator(compositeIndicator);
                if (result == null) result = caseIndicator(compositeIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.RANGE_INDICATOR: {
                RangeIndicator rangeIndicator = (RangeIndicator)theEObject;
                T result = caseRangeIndicator(rangeIndicator);
                if (result == null) result = caseCompositeIndicator(rangeIndicator);
                if (result == null) result = caseIndicator(rangeIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.BOX_INDICATOR: {
                BoxIndicator boxIndicator = (BoxIndicator)theEObject;
                T result = caseBoxIndicator(boxIndicator);
                if (result == null) result = caseCompositeIndicator(boxIndicator);
                if (result == null) result = caseIndicator(boxIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.INDICATOR_TYPE: {
                IndicatorType indicatorType = (IndicatorType)theEObject;
                T result = caseIndicatorType(indicatorType);
                if (result == null) result = caseClassifier(indicatorType);
                if (result == null) result = caseNamespace(indicatorType);
                if (result == null) result = caseModelElement(indicatorType);
                if (result == null) result = caseElement(indicatorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.INTEGER_SUM_INDICATOR: {
                IntegerSumIndicator integerSumIndicator = (IntegerSumIndicator)theEObject;
                T result = caseIntegerSumIndicator(integerSumIndicator);
                if (result == null) result = caseSumIndicator(integerSumIndicator);
                if (result == null) result = caseIndicator(integerSumIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.DOUBLE_SUM_INDICATOR: {
                DoubleSumIndicator doubleSumIndicator = (DoubleSumIndicator)theEObject;
                T result = caseDoubleSumIndicator(doubleSumIndicator);
                if (result == null) result = caseSumIndicator(doubleSumIndicator);
                if (result == null) result = caseIndicator(doubleSumIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.BIG_DECIMAL_INDICATOR: {
                BigDecimalIndicator bigDecimalIndicator = (BigDecimalIndicator)theEObject;
                T result = caseBigDecimalIndicator(bigDecimalIndicator);
                if (result == null) result = caseSumIndicator(bigDecimalIndicator);
                if (result == null) result = caseIndicator(bigDecimalIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.FREQUENCY_INDICATOR: {
                FrequencyIndicator frequencyIndicator = (FrequencyIndicator)theEObject;
                T result = caseFrequencyIndicator(frequencyIndicator);
                if (result == null) result = caseIndicator(frequencyIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.INTEGER_MEAN_INDICATOR: {
                IntegerMeanIndicator integerMeanIndicator = (IntegerMeanIndicator)theEObject;
                T result = caseIntegerMeanIndicator(integerMeanIndicator);
                if (result == null) result = caseIntegerSumIndicator(integerMeanIndicator);
                if (result == null) result = caseMeanIndicator(integerMeanIndicator);
                if (result == null) result = caseSumIndicator(integerMeanIndicator);
                if (result == null) result = caseIndicator(integerMeanIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.DOUBLE_MEAN_INDICATOR: {
                DoubleMeanIndicator doubleMeanIndicator = (DoubleMeanIndicator)theEObject;
                T result = caseDoubleMeanIndicator(doubleMeanIndicator);
                if (result == null) result = caseDoubleSumIndicator(doubleMeanIndicator);
                if (result == null) result = caseMeanIndicator(doubleMeanIndicator);
                if (result == null) result = caseSumIndicator(doubleMeanIndicator);
                if (result == null) result = caseIndicator(doubleMeanIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.BIG_DECIMAL_MEAN_INDICATOR: {
                BigDecimalMeanIndicator bigDecimalMeanIndicator = (BigDecimalMeanIndicator)theEObject;
                T result = caseBigDecimalMeanIndicator(bigDecimalMeanIndicator);
                if (result == null) result = caseBigDecimalIndicator(bigDecimalMeanIndicator);
                if (result == null) result = caseMeanIndicator(bigDecimalMeanIndicator);
                if (result == null) result = caseSumIndicator(bigDecimalMeanIndicator);
                if (result == null) result = caseIndicator(bigDecimalMeanIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.BLANK_COUNT_INDICATOR: {
                BlankCountIndicator blankCountIndicator = (BlankCountIndicator)theEObject;
                T result = caseBlankCountIndicator(blankCountIndicator);
                if (result == null) result = caseIndicator(blankCountIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.INDICATOR_PARAMETERS: {
                IndicatorParameters indicatorParameters = (IndicatorParameters)theEObject;
                T result = caseIndicatorParameters(indicatorParameters);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.MEDIAN_INDICATOR: {
                MedianIndicator medianIndicator = (MedianIndicator)theEObject;
                T result = caseMedianIndicator(medianIndicator);
                if (result == null) result = caseIndicator(medianIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIndicator(Indicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Row Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Row Count Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRowCountIndicator(RowCountIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Mean Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mean Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMeanIndicator(MeanIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sum Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sum Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSumIndicator(SumIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Composite Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Composite Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCompositeIndicator(CompositeIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Range Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Range Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRangeIndicator(RangeIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Box Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Box Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBoxIndicator(BoxIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Indicator Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Indicator Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIndicatorType(IndicatorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Integer Sum Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Integer Sum Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntegerSumIndicator(IntegerSumIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Double Sum Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Double Sum Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoubleSumIndicator(DoubleSumIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Big Decimal Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Big Decimal Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBigDecimalIndicator(BigDecimalIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Frequency Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Frequency Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFrequencyIndicator(FrequencyIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Integer Mean Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Integer Mean Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntegerMeanIndicator(IntegerMeanIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Double Mean Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Double Mean Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoubleMeanIndicator(DoubleMeanIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Big Decimal Mean Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Big Decimal Mean Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBigDecimalMeanIndicator(BigDecimalMeanIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Blank Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Blank Count Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBlankCountIndicator(BlankCountIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Indicator Parameters</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Indicator Parameters</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIndicatorParameters(IndicatorParameters object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Median Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Median Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMedianIndicator(MedianIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElement(Element object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelElement(ModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamespace(Namespace object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseClassifier(Classifier object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} //IndicatorsSwitch
