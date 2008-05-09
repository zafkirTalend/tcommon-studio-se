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
import java.util.Collections;
import java.util.List;

import org.talend.cwm.relational.TdColumn;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.editor.preview.IndicatorTypeMapping;
import org.talend.dataquality.helpers.MetadataHelper;
import org.talend.dataquality.indicators.DataminingType;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorsFactory;

/**
 * This class can store the all the Indicators of one TdColumn, and provide the method to access all indicator.
 * 
 */
public class ColumnIndicator {

    private TdColumn tdColumn;

    private List<IndicatorEnum> indicatorEnums = new ArrayList<IndicatorEnum>();

    private List<Indicator> indicatorList = new ArrayList<Indicator>(0);

    private List<IndicatorTypeMapping> indicatorMappingTypeList = new ArrayList<IndicatorTypeMapping>();

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
        if (!this.indicatorEnums.contains(indicatorEnum)) {
            indicatorEnums.add(indicatorEnum);
            IndicatorTypeMapping one = new IndicatorTypeMapping(indicatorEnum, createIndicator(indicatorEnum), this);
            this.indicatorMappingTypeList.add(one);
        }
    }

    private Indicator createIndicator(IndicatorEnum indicatorEnum) {
        IndicatorsFactory factory = IndicatorsFactory.eINSTANCE;
        Indicator indicator = (Indicator) factory.create(indicatorEnum.getIndicatorType());
        indicatorList.add(indicator);
        return indicator;
    }

    public boolean contains(IndicatorEnum indicatorEnum) {
        return indicatorEnums == null ? false : this.indicatorEnums.contains(indicatorEnum);
    }

    /**
     * @return the indicatorEnums
     */
    public void removeIndicatorEnum(IndicatorEnum indicatorEnum) {
        this.indicatorEnums.remove(indicatorEnum);
        for (IndicatorTypeMapping indicatorMapping : this.indicatorMappingTypeList) {
            if (indicatorMapping.getType() == indicatorEnum) {
                this.indicatorList.remove(indicatorMapping.getIndicator());
                indicatorMappingTypeList.remove(indicatorMapping);
                break;
            }
        }
    }

    /**
     * @param indicatorEnums the indicatorEnums to set
     */
    public IndicatorEnum[] getIndicatorEnums() {
        return this.indicatorEnums.toArray(new IndicatorEnum[indicatorEnums.size()]);
    }

    public boolean hasIndicators() {
        return !(this.indicatorEnums.size() == 0 || this.indicatorEnums.size() == 0);
    }

    /**
     * @return the tdColumn
     */
    public TdColumn getTdColumn() {
        return tdColumn;
    }

    public Indicator[] getIndicators() {
        return this.indicatorList.toArray(new Indicator[indicatorList.size()]);
    }

    public List<IndicatorTypeMapping> getIndicatorForMap() {
        if (indicatorEnums.size() == 0) {
            return Collections.emptyList();
        }
        return indicatorMappingTypeList;
    }

    public void setIndicators(Indicator[] indicators) {
        indicatorEnums.clear();
        indicatorList.clear();
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
        DataminingType type = MetadataHelper.getDataminingType(tdColumn);
        
        if (type == null) {
            return MetadataHelper.getDefaultDataminingType(tdColumn.getJavaType());
        }
        
        return type;
    }

    /**
     * Sets the detaminingType.
     * 
     * @param detaminingType the detaminingType to set
     */
    public void setDataminingType(DataminingType dataminingType) {
        MetadataHelper.setDataminingType(dataminingType, tdColumn);
    }

}
