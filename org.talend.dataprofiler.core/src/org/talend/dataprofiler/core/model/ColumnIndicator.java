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

/**
 * @author rli
 * 
 */
public class ColumnIndicator {

    private TdColumn tdColumn;

    private List<IndicatorEnum> indicatorEnums;

    public ColumnIndicator(TdColumn tdColumn) {
        this.tdColumn = tdColumn;
    }

    /**
     * @return the indicatorEnums
     */
    public void addIndicatorEnum(IndicatorEnum indicatorEnum) {
        if (indicatorEnums == null) {
            indicatorEnums = new ArrayList<IndicatorEnum>();
        }
        if (!this.indicatorEnums.contains(indicatorEnum)) {
            indicatorEnums.add(indicatorEnum);
        }
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

}
