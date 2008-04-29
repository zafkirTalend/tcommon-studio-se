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
package org.talend.dataprofiler.core.ui.editor.preview;

import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataquality.indicators.Indicator;


/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public class IndicatorTypeMapping {

    private IndicatorEnum type;
    
    private Indicator indicator;
    
    public IndicatorTypeMapping(IndicatorEnum type, Indicator indicator) {
        this.type = type;
        this.indicator = indicator;
    }

    
    /**
     * Getter for type.
     * @return the type
     */
    public IndicatorEnum getType() {
        return this.type;
    }

    
    /**
     * Getter for indicator.
     * @return the indicator
     */
    public Indicator getIndicator() {
        return this.indicator;
    }
    
    
}
