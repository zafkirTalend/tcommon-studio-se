/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.indicators;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.talend.dataquality.indicators.IndicatorsPackage
 * @generated
 */
public interface IndicatorsFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    IndicatorsFactory eINSTANCE = org.talend.dataquality.indicators.impl.IndicatorsFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Indicator</em>'.
     * @generated
     */
    Indicator createIndicator();

    /**
     * Returns a new object of class '<em>Row Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Row Count Indicator</em>'.
     * @generated
     */
    RowCountIndicator createRowCountIndicator();

    /**
     * Returns a new object of class '<em>Composite Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Composite Indicator</em>'.
     * @generated
     */
    CompositeIndicator createCompositeIndicator();

    /**
     * Returns a new object of class '<em>Range Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Range Indicator</em>'.
     * @generated
     */
    RangeIndicator createRangeIndicator();

    /**
     * Returns a new object of class '<em>Box Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Box Indicator</em>'.
     * @generated
     */
    BoxIndicator createBoxIndicator();

    /**
     * Returns a new object of class '<em>Indicator Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Indicator Type</em>'.
     * @generated
     */
    IndicatorType createIndicatorType();

    /**
     * Returns a new object of class '<em>Integer Sum Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Integer Sum Indicator</em>'.
     * @generated
     */
    IntegerSumIndicator createIntegerSumIndicator();

    /**
     * Returns a new object of class '<em>Double Sum Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Double Sum Indicator</em>'.
     * @generated
     */
    DoubleSumIndicator createDoubleSumIndicator();

    /**
     * Returns a new object of class '<em>Big Decimal Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Big Decimal Indicator</em>'.
     * @generated
     */
    BigDecimalIndicator createBigDecimalIndicator();

    /**
     * Returns a new object of class '<em>Frequency Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Frequency Indicator</em>'.
     * @generated
     */
    FrequencyIndicator createFrequencyIndicator();

    /**
     * Returns a new object of class '<em>Integer Mean Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Integer Mean Indicator</em>'.
     * @generated
     */
    IntegerMeanIndicator createIntegerMeanIndicator();

    /**
     * Returns a new object of class '<em>Double Mean Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Double Mean Indicator</em>'.
     * @generated
     */
    DoubleMeanIndicator createDoubleMeanIndicator();

    /**
     * Returns a new object of class '<em>Big Decimal Mean Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Big Decimal Mean Indicator</em>'.
     * @generated
     */
    BigDecimalMeanIndicator createBigDecimalMeanIndicator();

    /**
     * Returns a new object of class '<em>Blank Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Blank Count Indicator</em>'.
     * @generated
     */
    BlankCountIndicator createBlankCountIndicator();

    /**
     * Returns a new object of class '<em>Indicator Parameters</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Indicator Parameters</em>'.
     * @generated
     */
    IndicatorParameters createIndicatorParameters();

    /**
     * Returns a new object of class '<em>Median Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Median Indicator</em>'.
     * @generated
     */
    MedianIndicator createMedianIndicator();

    /**
     * Returns a new object of class '<em>Value Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Indicator</em>'.
     * @generated
     */
    ValueIndicator createValueIndicator();

    /**
     * Returns a new object of class '<em>Min Value Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Min Value Indicator</em>'.
     * @generated
     */
    MinValueIndicator createMinValueIndicator();

    /**
     * Returns a new object of class '<em>Max Value Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Max Value Indicator</em>'.
     * @generated
     */
    MaxValueIndicator createMaxValueIndicator();

    /**
     * Returns a new object of class '<em>Mode Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Mode Indicator</em>'.
     * @generated
     */
    ModeIndicator createModeIndicator();

    /**
     * Returns a new object of class '<em>Null Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Null Count Indicator</em>'.
     * @generated
     */
    NullCountIndicator createNullCountIndicator();

    /**
     * Returns a new object of class '<em>Distinct Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Distinct Count Indicator</em>'.
     * @generated
     */
    DistinctCountIndicator createDistinctCountIndicator();

    /**
     * Returns a new object of class '<em>Unique Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Unique Count Indicator</em>'.
     * @generated
     */
    UniqueCountIndicator createUniqueCountIndicator();

    /**
     * Returns a new object of class '<em>Duplicate Count Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Duplicate Count Indicator</em>'.
     * @generated
     */
    DuplicateCountIndicator createDuplicateCountIndicator();

    /**
     * Returns a new object of class '<em>IQR Indicator</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>IQR Indicator</em>'.
     * @generated
     */
    IQRIndicator createIQRIndicator();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    IndicatorsPackage getIndicatorsPackage();

} //IndicatorsFactory
