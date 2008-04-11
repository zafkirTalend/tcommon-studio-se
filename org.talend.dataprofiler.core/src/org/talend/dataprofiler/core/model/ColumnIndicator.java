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
package org.talend.dataprofiler.core.model;

import java.util.ArrayList;
import java.util.List;

import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataquality.indicators.DataminingType;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorsFactory;

/**
 * This class can store the all the Indicators of one TdColumn, and provide the method to access all indicator.
 * 
 */
public class ColumnIndicator {

    private TdColumn tdColumn;

    private List<IndicatorEnum> indicatorEnums;

    private Indicator[] indicators;

    private DataminingType dataminingType = DataminingType.NOMINAL;

    public ColumnIndicator(TdColumn tdColumn) {
        this.tdColumn = tdColumn;
    }

    /**
     * @return the indicatorEnums
     */
    public void addIndicatorEnum(IndicatorEnum indicatorEnum) {
        if (indicatorEnum == null) {
            return;
        }
        if (indicatorEnums == null) {
            indicatorEnums = new ArrayList<IndicatorEnum>();
        }
        if (!this.indicatorEnums.contains(indicatorEnum)) {
            indicatorEnums.add(indicatorEnum);
        }
    }

    public boolean contains(IndicatorEnum indicatorEnum) {
        return indicatorEnums == null ? false : this.indicatorEnums.contains(indicatorEnum);
    }

    /**
     * @return the indicatorEnums
     */
    public void removeIndicatorEnum(IndicatorEnum indicatorEnum) {
        this.indicatorEnums.remove(indicatorEnum);
    }

    /**
     * @param indicatorEnums the indicatorEnums to set
     */
    public IndicatorEnum[] getIndicatorEnums() {
        if (indicatorEnums == null) {
            return null;
        }
        return this.indicatorEnums.toArray(new IndicatorEnum[indicatorEnums.size()]);
    }

    public boolean hasIndicators() {
        return !(this.indicatorEnums == null || this.indicatorEnums.size() == 0);
    }

    /**
     * @return the tdColumn
     */
    public TdColumn getTdColumn() {
        return tdColumn;
    }

    public Indicator[] getIndicators() {
        if (indicatorEnums == null) {
            return new Indicator[0];
        }
        IndicatorsFactory factory = IndicatorsFactory.eINSTANCE;
        List<Indicator> indicatorList = new ArrayList<Indicator>();
        Indicator indicator = null;
        for (IndicatorEnum indicatorEnum : indicatorEnums) {
            indicator = (Indicator) factory.create(indicatorEnum.getIndicatorType());
            // switch (indicatorEnum) {
            // case RowCountIndicatorEnum:
            // indicator = factory.createRowCountIndicator();
            // break;
            // case NullCountIndicatorEnum:
            // indicator = factory.createNullCountIndicator();
            // break;
            // case DistinctCountIndicatorEnum:
            // indicator = factory.createDistinctCountIndicator();
            // break;
            // case UniqueIndicatorEnum:
            // indicator = factory.createUniqueCountIndicator();
            // break;
            // case DuplicateCountIndicatorEnum:
            // indicator = factory.createUniqueCountIndicator();
            // break;
            // case BlankCountIndicatorEnum:
            // indicator = factory.createBlankCountIndicator();
            // break;
            //
            // case ModeIndicatorEnum:
            // indicator = factory.createModeIndicator();
            // break;
            //
            // // TODO MeanIndicator only corresponding DoubleMeanIndicator?
            // case MeanIndicatorEnum:
            // indicator = factory.createDoubleMeanIndicator();
            // break;
            // case MedianIndicatorEnum:
            // indicator = factory.createMedianIndicator();
            // break;
            // case IQRIndicatorEnum:
            // indicator = factory.createIQRIndicator();
            // break;
            // case RangeIndicatorEnum:
            // indicator = factory.createRangeIndicator();
            // break;
            // case MinValueIndicatorEnum:
            // indicator = factory.createMinValueIndicator();
            // break;
            // case MaxValueIndicatorEnum:
            // indicator = factory.createMaxValueIndicator();
            // break;
            // case FrequencyIndicatorEnum:
            // indicator = factory.createFrequencyIndicator();
            // break;
            // default:
            // }
            indicatorList.add(indicator);
        }
        this.indicators = indicatorList.toArray(new Indicator[indicatorList.size()]);
        return this.indicators;
    }

    public void setIndicators(Indicator[] indicators) {
        this.indicators = indicators;
        for (int i = 0; i < indicators.length; i++) {
            addIndicatorEnum(IndicatorEnum.findIndicatorEnum(indicators[i].eClass()));
        }
    }

    /**
     * Getter for detaminingType.
     * 
     * @return the detaminingType
     */
    public DataminingType getDataminingType() {
        return dataminingType;
    }

    /**
     * Sets the detaminingType.
     * 
     * @param detaminingType the detaminingType to set
     */
    public void setDataminingType(DataminingType dataminingType) {
        this.dataminingType = dataminingType;
    }

}
