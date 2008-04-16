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
                if (result == null) result = caseModelElement(indicator);
                if (result == null) result = caseElement(indicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.ROW_COUNT_INDICATOR: {
                RowCountIndicator rowCountIndicator = (RowCountIndicator)theEObject;
                T result = caseRowCountIndicator(rowCountIndicator);
                if (result == null) result = caseIndicator(rowCountIndicator);
                if (result == null) result = caseModelElement(rowCountIndicator);
                if (result == null) result = caseElement(rowCountIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.MEAN_INDICATOR: {
                MeanIndicator meanIndicator = (MeanIndicator)theEObject;
                T result = caseMeanIndicator(meanIndicator);
                if (result == null) result = caseIndicator(meanIndicator);
                if (result == null) result = caseModelElement(meanIndicator);
                if (result == null) result = caseElement(meanIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.SUM_INDICATOR: {
                SumIndicator sumIndicator = (SumIndicator)theEObject;
                T result = caseSumIndicator(sumIndicator);
                if (result == null) result = caseIndicator(sumIndicator);
                if (result == null) result = caseModelElement(sumIndicator);
                if (result == null) result = caseElement(sumIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.COMPOSITE_INDICATOR: {
                CompositeIndicator compositeIndicator = (CompositeIndicator)theEObject;
                T result = caseCompositeIndicator(compositeIndicator);
                if (result == null) result = caseIndicator(compositeIndicator);
                if (result == null) result = caseModelElement(compositeIndicator);
                if (result == null) result = caseElement(compositeIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.RANGE_INDICATOR: {
                RangeIndicator rangeIndicator = (RangeIndicator)theEObject;
                T result = caseRangeIndicator(rangeIndicator);
                if (result == null) result = caseCompositeIndicator(rangeIndicator);
                if (result == null) result = caseIndicator(rangeIndicator);
                if (result == null) result = caseModelElement(rangeIndicator);
                if (result == null) result = caseElement(rangeIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.BOX_INDICATOR: {
                BoxIndicator boxIndicator = (BoxIndicator)theEObject;
                T result = caseBoxIndicator(boxIndicator);
                if (result == null) result = caseCompositeIndicator(boxIndicator);
                if (result == null) result = caseIndicator(boxIndicator);
                if (result == null) result = caseModelElement(boxIndicator);
                if (result == null) result = caseElement(boxIndicator);
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
                if (result == null) result = caseModelElement(integerSumIndicator);
                if (result == null) result = caseElement(integerSumIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.DOUBLE_SUM_INDICATOR: {
                DoubleSumIndicator doubleSumIndicator = (DoubleSumIndicator)theEObject;
                T result = caseDoubleSumIndicator(doubleSumIndicator);
                if (result == null) result = caseSumIndicator(doubleSumIndicator);
                if (result == null) result = caseIndicator(doubleSumIndicator);
                if (result == null) result = caseModelElement(doubleSumIndicator);
                if (result == null) result = caseElement(doubleSumIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.BIG_DECIMAL_INDICATOR: {
                BigDecimalIndicator bigDecimalIndicator = (BigDecimalIndicator)theEObject;
                T result = caseBigDecimalIndicator(bigDecimalIndicator);
                if (result == null) result = caseSumIndicator(bigDecimalIndicator);
                if (result == null) result = caseIndicator(bigDecimalIndicator);
                if (result == null) result = caseModelElement(bigDecimalIndicator);
                if (result == null) result = caseElement(bigDecimalIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.FREQUENCY_INDICATOR: {
                FrequencyIndicator frequencyIndicator = (FrequencyIndicator)theEObject;
                T result = caseFrequencyIndicator(frequencyIndicator);
                if (result == null) result = caseCompositeIndicator(frequencyIndicator);
                if (result == null) result = caseIndicator(frequencyIndicator);
                if (result == null) result = caseModelElement(frequencyIndicator);
                if (result == null) result = caseElement(frequencyIndicator);
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
                if (result == null) result = caseModelElement(integerMeanIndicator);
                if (result == null) result = caseElement(integerMeanIndicator);
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
                if (result == null) result = caseModelElement(doubleMeanIndicator);
                if (result == null) result = caseElement(doubleMeanIndicator);
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
                if (result == null) result = caseModelElement(bigDecimalMeanIndicator);
                if (result == null) result = caseElement(bigDecimalMeanIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.BLANK_COUNT_INDICATOR: {
                BlankCountIndicator blankCountIndicator = (BlankCountIndicator)theEObject;
                T result = caseBlankCountIndicator(blankCountIndicator);
                if (result == null) result = caseTextIndicator(blankCountIndicator);
                if (result == null) result = caseIndicator(blankCountIndicator);
                if (result == null) result = caseModelElement(blankCountIndicator);
                if (result == null) result = caseElement(blankCountIndicator);
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
                if (result == null) result = caseModelElement(medianIndicator);
                if (result == null) result = caseElement(medianIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.VALUE_INDICATOR: {
                ValueIndicator valueIndicator = (ValueIndicator)theEObject;
                T result = caseValueIndicator(valueIndicator);
                if (result == null) result = caseIndicator(valueIndicator);
                if (result == null) result = caseModelElement(valueIndicator);
                if (result == null) result = caseElement(valueIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.MIN_VALUE_INDICATOR: {
                MinValueIndicator minValueIndicator = (MinValueIndicator)theEObject;
                T result = caseMinValueIndicator(minValueIndicator);
                if (result == null) result = caseValueIndicator(minValueIndicator);
                if (result == null) result = caseIndicator(minValueIndicator);
                if (result == null) result = caseModelElement(minValueIndicator);
                if (result == null) result = caseElement(minValueIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.MAX_VALUE_INDICATOR: {
                MaxValueIndicator maxValueIndicator = (MaxValueIndicator)theEObject;
                T result = caseMaxValueIndicator(maxValueIndicator);
                if (result == null) result = caseValueIndicator(maxValueIndicator);
                if (result == null) result = caseIndicator(maxValueIndicator);
                if (result == null) result = caseModelElement(maxValueIndicator);
                if (result == null) result = caseElement(maxValueIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.MODE_INDICATOR: {
                ModeIndicator modeIndicator = (ModeIndicator)theEObject;
                T result = caseModeIndicator(modeIndicator);
                if (result == null) result = caseIndicator(modeIndicator);
                if (result == null) result = caseModelElement(modeIndicator);
                if (result == null) result = caseElement(modeIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.NULL_COUNT_INDICATOR: {
                NullCountIndicator nullCountIndicator = (NullCountIndicator)theEObject;
                T result = caseNullCountIndicator(nullCountIndicator);
                if (result == null) result = caseIndicator(nullCountIndicator);
                if (result == null) result = caseModelElement(nullCountIndicator);
                if (result == null) result = caseElement(nullCountIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.DISTINCT_COUNT_INDICATOR: {
                DistinctCountIndicator distinctCountIndicator = (DistinctCountIndicator)theEObject;
                T result = caseDistinctCountIndicator(distinctCountIndicator);
                if (result == null) result = caseIndicator(distinctCountIndicator);
                if (result == null) result = caseModelElement(distinctCountIndicator);
                if (result == null) result = caseElement(distinctCountIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.UNIQUE_COUNT_INDICATOR: {
                UniqueCountIndicator uniqueCountIndicator = (UniqueCountIndicator)theEObject;
                T result = caseUniqueCountIndicator(uniqueCountIndicator);
                if (result == null) result = caseIndicator(uniqueCountIndicator);
                if (result == null) result = caseModelElement(uniqueCountIndicator);
                if (result == null) result = caseElement(uniqueCountIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.DUPLICATE_COUNT_INDICATOR: {
                DuplicateCountIndicator duplicateCountIndicator = (DuplicateCountIndicator)theEObject;
                T result = caseDuplicateCountIndicator(duplicateCountIndicator);
                if (result == null) result = caseIndicator(duplicateCountIndicator);
                if (result == null) result = caseModelElement(duplicateCountIndicator);
                if (result == null) result = caseElement(duplicateCountIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.IQR_INDICATOR: {
                IQRIndicator iqrIndicator = (IQRIndicator)theEObject;
                T result = caseIQRIndicator(iqrIndicator);
                if (result == null) result = caseRangeIndicator(iqrIndicator);
                if (result == null) result = caseCompositeIndicator(iqrIndicator);
                if (result == null) result = caseIndicator(iqrIndicator);
                if (result == null) result = caseModelElement(iqrIndicator);
                if (result == null) result = caseElement(iqrIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.TEXT_INDICATOR: {
                TextIndicator textIndicator = (TextIndicator)theEObject;
                T result = caseTextIndicator(textIndicator);
                if (result == null) result = caseIndicator(textIndicator);
                if (result == null) result = caseModelElement(textIndicator);
                if (result == null) result = caseElement(textIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.MIN_LENGTH_INDICATOR: {
                MinLengthIndicator minLengthIndicator = (MinLengthIndicator)theEObject;
                T result = caseMinLengthIndicator(minLengthIndicator);
                if (result == null) result = caseLengthIndicator(minLengthIndicator);
                if (result == null) result = caseTextIndicator(minLengthIndicator);
                if (result == null) result = caseIndicator(minLengthIndicator);
                if (result == null) result = caseModelElement(minLengthIndicator);
                if (result == null) result = caseElement(minLengthIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.MAX_LENGTH_INDICATOR: {
                MaxLengthIndicator maxLengthIndicator = (MaxLengthIndicator)theEObject;
                T result = caseMaxLengthIndicator(maxLengthIndicator);
                if (result == null) result = caseLengthIndicator(maxLengthIndicator);
                if (result == null) result = caseTextIndicator(maxLengthIndicator);
                if (result == null) result = caseIndicator(maxLengthIndicator);
                if (result == null) result = caseModelElement(maxLengthIndicator);
                if (result == null) result = caseElement(maxLengthIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.AVERAGE_LENGTH_INDICATOR: {
                AverageLengthIndicator averageLengthIndicator = (AverageLengthIndicator)theEObject;
                T result = caseAverageLengthIndicator(averageLengthIndicator);
                if (result == null) result = caseLengthIndicator(averageLengthIndicator);
                if (result == null) result = caseTextIndicator(averageLengthIndicator);
                if (result == null) result = caseIndicator(averageLengthIndicator);
                if (result == null) result = caseModelElement(averageLengthIndicator);
                if (result == null) result = caseElement(averageLengthIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.LENGTH_INDICATOR: {
                LengthIndicator lengthIndicator = (LengthIndicator)theEObject;
                T result = caseLengthIndicator(lengthIndicator);
                if (result == null) result = caseTextIndicator(lengthIndicator);
                if (result == null) result = caseIndicator(lengthIndicator);
                if (result == null) result = caseModelElement(lengthIndicator);
                if (result == null) result = caseElement(lengthIndicator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case IndicatorsPackage.TEXT_PARAMETERS: {
                TextParameters textParameters = (TextParameters)theEObject;
                T result = caseTextParameters(textParameters);
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
     * Returns the result of interpreting the object as an instance of '<em>Value Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseValueIndicator(ValueIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Min Value Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Min Value Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMinValueIndicator(MinValueIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Max Value Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Max Value Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMaxValueIndicator(MaxValueIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Mode Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mode Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModeIndicator(ModeIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Null Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Null Count Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNullCountIndicator(NullCountIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Distinct Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Distinct Count Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDistinctCountIndicator(DistinctCountIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Unique Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Unique Count Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUniqueCountIndicator(UniqueCountIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Duplicate Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Duplicate Count Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDuplicateCountIndicator(DuplicateCountIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IQR Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IQR Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIQRIndicator(IQRIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextIndicator(TextIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Min Length Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Min Length Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMinLengthIndicator(MinLengthIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Max Length Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Max Length Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMaxLengthIndicator(MaxLengthIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Average Length Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Average Length Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAverageLengthIndicator(AverageLengthIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Length Indicator</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Length Indicator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLengthIndicator(LengthIndicator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Parameters</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Parameters</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextParameters(TextParameters object) {
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
