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

import org.talend.dataquality.indicators.IndicatorsPackage;

/**
 * @author rli
 * 
 */
public enum IndicatorEnum {
    RowCountIndicatorEnum(IndicatorsPackage.ROW_COUNT_INDICATOR, "row count"),
    NullCountIndicatorEnum(IndicatorsPackage.NULL_COUNT_INDICATOR, "null count"),
    DistinctCountIndicatorEnum(IndicatorsPackage.DISTINCT_COUNT_INDICATOR, "distinct count"),
    UniqueIndicatorEnum(IndicatorsPackage.UNIQUE_COUNT_INDICATOR, "unique count"),
    DuplicateCountIndicatorEnum(IndicatorsPackage.DUPLICATE_COUNT_INDICATOR, "duplicate count"),
    BlankCountIndicatorEnum(IndicatorsPackage.BLANK_COUNT_INDICATOR, "blank count"),

    ModeIndicatorEnum(IndicatorsPackage.MODE_INDICATOR, "mode"),
    MeanIndicatorEnum(IndicatorsPackage.INTEGER_MEAN_INDICATOR, "mean"),
    MedianIndicatorEnum(IndicatorsPackage.MEDIAN_INDICATOR, "median"),
    IQRIndicatorEnum(IndicatorsPackage.IQR_INDICATOR, "IQR"),
    RangeIndicatorEnum(IndicatorsPackage.RANGE_INDICATOR, "range"),
    MinValueIndicatorEnum(IndicatorsPackage.MIN_VALUE_INDICATOR, "min"),
    MaxValueIndicatorEnum(IndicatorsPackage.MAX_VALUE_INDICATOR, "max"),
    FrequencyIndicatorEnum(IndicatorsPackage.FREQUENCY_INDICATOR, "frequency table");

    private int indicatorType;

    private String label;

    IndicatorEnum(int indicatorType, String label) {
        this.indicatorType = indicatorType;
        this.label = label;
    }

    /**
     * @return the indicator
     */
    public int getIndicatorType() {
        return indicatorType;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

}
