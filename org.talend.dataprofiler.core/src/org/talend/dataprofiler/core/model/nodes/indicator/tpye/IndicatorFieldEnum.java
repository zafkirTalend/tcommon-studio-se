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

/**
 * @author rli
 * 
 */
public enum IndicatorFieldEnum {

    BlankValueField("blank values", IndicatorEnum.BlankCountIndicatorEnum),

    RowCountField("row count", IndicatorEnum.FrequencyIndicatorEnum),
    DistinctValuesField("distinct values", IndicatorEnum.FrequencyIndicatorEnum),
    UniqueField("unique", IndicatorEnum.FrequencyIndicatorEnum),
    RepeatField("repeat", IndicatorEnum.FrequencyIndicatorEnum),
    NullValueField("null value", IndicatorEnum.IndicatorEnumType),
    ModeField("mode", IndicatorEnum.FrequencyIndicatorEnum),

    FrequenceTableField("frequence table", IndicatorEnum.MedianIndicatorEnum),
    MedianField("median", IndicatorEnum.MedianIndicatorEnum),

    MinimumValueField("minimum value", IndicatorEnum.BoxIndicatorEnum),
    MaximumValueField("maximum value", IndicatorEnum.BoxIndicatorEnum),

    RangeField("range", IndicatorEnum.RangeIndicatorEnum),
    LowQuartileField("LowQuartile", IndicatorEnum.RangeIndicatorEnum),
    UpperQuartileField("UpperQuartile", IndicatorEnum.RangeIndicatorEnum),
    IQRField("IQR", IndicatorEnum.BoxIndicatorEnum),

    MeanField("mean", IndicatorEnum.DoubleMeanIndicatorEnum);

    private final String label;

    private final IndicatorEnum indicatorEnum;

    IndicatorFieldEnum(String label, IndicatorEnum indicatorEnum) {
        this.label = label;
        this.indicatorEnum = indicatorEnum;

    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the indicatorEnum
     */
    public IndicatorEnum getIndicatorEnum() {
        return indicatorEnum;
    }

}
