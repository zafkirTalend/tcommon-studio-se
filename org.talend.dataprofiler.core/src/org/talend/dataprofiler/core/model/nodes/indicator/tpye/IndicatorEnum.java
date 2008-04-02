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
    RowCountIndicatorEnum(IndicatorsPackage.ROW_COUNT_INDICATOR, "row count", null),
    NullCountIndicatorEnum(IndicatorsPackage.NULL_COUNT_INDICATOR, "null count", null),
    DistinctCountIndicatorEnum(IndicatorsPackage.DISTINCT_COUNT_INDICATOR, "distinct count", null),
    UniqueIndicatorEnum(IndicatorsPackage.UNIQUE_COUNT_INDICATOR, "unique count", null),
    DuplicateCountIndicatorEnum(IndicatorsPackage.DUPLICATE_COUNT_INDICATOR, "duplicate count", null),
    BlankCountIndicatorEnum(IndicatorsPackage.BLANK_COUNT_INDICATOR, "blank count", null),

    ModeIndicatorEnum(IndicatorsPackage.MODE_INDICATOR, "mode", null),
    MeanIndicatorEnum(IndicatorsPackage.INTEGER_MEAN_INDICATOR, "mean", null),
    MedianIndicatorEnum(IndicatorsPackage.MEDIAN_INDICATOR, "median", null),
    MinValueIndicatorEnum(IndicatorsPackage.MIN_VALUE_INDICATOR, "min", null),
    MaxValueIndicatorEnum(IndicatorsPackage.MAX_VALUE_INDICATOR, "max", null),
    IQRIndicatorEnum(IndicatorsPackage.IQR_INDICATOR, "IQR", new IndicatorEnum[] { MinValueIndicatorEnum, MaxValueIndicatorEnum }),
    RangeIndicatorEnum(IndicatorsPackage.RANGE_INDICATOR, "range", new IndicatorEnum[] { MinValueIndicatorEnum,
            MaxValueIndicatorEnum }),
    FrequencyIndicatorEnum(IndicatorsPackage.FREQUENCY_INDICATOR, "frequency table", null);

    private int indicatorType;

    private String label;

    private IndicatorEnum[] children;

    IndicatorEnum(int indicatorType, String label, IndicatorEnum[] children) {
        this.indicatorType = indicatorType;
        this.label = label;
        this.children = children;
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

    /**
     * @return the children
     */
    public IndicatorEnum[] getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return children != null;
    }

}
