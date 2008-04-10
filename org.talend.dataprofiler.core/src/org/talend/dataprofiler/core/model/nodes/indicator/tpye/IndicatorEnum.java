// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.model.nodes.indicator.tpye;

import org.eclipse.emf.ecore.EClass;
import org.talend.dataquality.indicators.IndicatorsPackage;

/**
 * @author rli
 * 
 */
public enum IndicatorEnum {
    RowCountIndicatorEnum(IndicatorsPackage.Literals.ROW_COUNT_INDICATOR, "row count", null),
    NullCountIndicatorEnum(IndicatorsPackage.Literals.NULL_COUNT_INDICATOR, "null count", null),
    DistinctCountIndicatorEnum(IndicatorsPackage.Literals.DISTINCT_COUNT_INDICATOR, "distinct count", null),
    UniqueIndicatorEnum(IndicatorsPackage.Literals.UNIQUE_COUNT_INDICATOR, "unique count", null),
    DuplicateCountIndicatorEnum(IndicatorsPackage.Literals.DUPLICATE_COUNT_INDICATOR, "duplicate count", null),
    BlankCountIndicatorEnum(IndicatorsPackage.Literals.BLANK_COUNT_INDICATOR, "blank count", null),

    MinLengthIndicatorEnum(IndicatorsPackage.Literals.MIN_LENGTH_INDICATOR, "min length", null),
    MaxLengthIndicatorEnum(IndicatorsPackage.Literals.MAX_LENGTH_INDICATOR, "max length", null),
    AverageLengthIndicatorEnum(IndicatorsPackage.Literals.AVERAGE_LENGTH_INDICATOR, "average length", null),

    ModeIndicatorEnum(IndicatorsPackage.Literals.MODE_INDICATOR, "mode", null),
    MeanIndicatorEnum(IndicatorsPackage.Literals.MEAN_INDICATOR, "mean", null),
    MedianIndicatorEnum(IndicatorsPackage.Literals.MEDIAN_INDICATOR, "median", null),
    MinValueIndicatorEnum(IndicatorsPackage.Literals.MIN_VALUE_INDICATOR, "min.value", null),
    MaxValueIndicatorEnum(IndicatorsPackage.Literals.MAX_VALUE_INDICATOR, "max.value", null),
    LowerQuartileIndicatorEnum(IndicatorsPackage.Literals.MIN_VALUE_INDICATOR, "lower quartile", null),
    UpperQuartileIndicatorEnum(IndicatorsPackage.Literals.MAX_VALUE_INDICATOR, "upper quartile", null),
    IQRIndicatorEnum(IndicatorsPackage.Literals.IQR_INDICATOR, "inter quartile range", new IndicatorEnum[] {
            LowerQuartileIndicatorEnum, UpperQuartileIndicatorEnum }),
    RangeIndicatorEnum(IndicatorsPackage.Literals.RANGE_INDICATOR, "range", new IndicatorEnum[] { MinValueIndicatorEnum,
            MaxValueIndicatorEnum }),
    FrequencyIndicatorEnum(IndicatorsPackage.Literals.FREQUENCY_INDICATOR, "frequency table", null);

    private EClass indicatorType;

    private String label;

    private IndicatorEnum[] children;

    IndicatorEnum(EClass indicatorType, String label, IndicatorEnum[] children) {
        this.indicatorType = indicatorType;
        this.label = label;
        this.children = children;
    }

    /**
     * @return the indicator
     */
    public EClass getIndicatorType() {
        return indicatorType;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the children
     */
    public IndicatorEnum[] getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return children != null;
    }

    public static IndicatorEnum findIndicatorEnum(EClass indicatorType) {
        IndicatorEnum returnEnum = null;
        if (indicatorType == RowCountIndicatorEnum.getIndicatorType()) {
            returnEnum = RowCountIndicatorEnum;
        } else if (indicatorType == NullCountIndicatorEnum.getIndicatorType()) {
            returnEnum = NullCountIndicatorEnum;
        } else if (indicatorType == DistinctCountIndicatorEnum.getIndicatorType()) {
            returnEnum = DistinctCountIndicatorEnum;
        } else if (indicatorType == UniqueIndicatorEnum.getIndicatorType()) {
            returnEnum = UniqueIndicatorEnum;
        } else if (indicatorType == DuplicateCountIndicatorEnum.getIndicatorType()) {
            returnEnum = DuplicateCountIndicatorEnum;
        } else if (indicatorType == BlankCountIndicatorEnum.getIndicatorType()) {
            returnEnum = BlankCountIndicatorEnum;
        } else if (indicatorType == MinLengthIndicatorEnum.getIndicatorType()) {
            returnEnum = MinLengthIndicatorEnum;
        } else if (indicatorType == MaxLengthIndicatorEnum.getIndicatorType()) {
            returnEnum = MaxLengthIndicatorEnum;
        } else if (indicatorType == AverageLengthIndicatorEnum.getIndicatorType()) {
            returnEnum = AverageLengthIndicatorEnum;
        } else if (indicatorType == ModeIndicatorEnum.getIndicatorType()) {
            returnEnum = ModeIndicatorEnum;
        } else if (indicatorType == MeanIndicatorEnum.getIndicatorType()) {
            returnEnum = MeanIndicatorEnum;
        } else if (indicatorType == MedianIndicatorEnum.getIndicatorType()) {
            returnEnum = MedianIndicatorEnum;
        } else if (indicatorType == MinValueIndicatorEnum.getIndicatorType()) {
            returnEnum = MinValueIndicatorEnum;
        } else if (indicatorType == MaxValueIndicatorEnum.getIndicatorType()) {
            returnEnum = MaxValueIndicatorEnum;
        } else if (indicatorType == LowerQuartileIndicatorEnum.getIndicatorType()) {
            returnEnum = LowerQuartileIndicatorEnum;
        } else if (indicatorType == UpperQuartileIndicatorEnum.getIndicatorType()) {
            returnEnum = UpperQuartileIndicatorEnum;
        } else if (indicatorType == IQRIndicatorEnum.getIndicatorType()) {
            returnEnum = IQRIndicatorEnum;
        } else if (indicatorType == RangeIndicatorEnum.getIndicatorType()) {
            returnEnum = RangeIndicatorEnum;
        } else if (indicatorType == FrequencyIndicatorEnum.getIndicatorType()) {
            returnEnum = FrequencyIndicatorEnum;
        }
        return returnEnum;

    }

}
