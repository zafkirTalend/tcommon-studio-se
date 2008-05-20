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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataquality.indicators.AverageLengthIndicator;
import org.talend.dataquality.indicators.BlankCountIndicator;
import org.talend.dataquality.indicators.DistinctCountIndicator;
import org.talend.dataquality.indicators.DuplicateCountIndicator;
import org.talend.dataquality.indicators.FrequencyIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.LowerQuartileIndicator;
import org.talend.dataquality.indicators.MaxLengthIndicator;
import org.talend.dataquality.indicators.MaxValueIndicator;
import org.talend.dataquality.indicators.MeanIndicator;
import org.talend.dataquality.indicators.MedianIndicator;
import org.talend.dataquality.indicators.MinLengthIndicator;
import org.talend.dataquality.indicators.MinValueIndicator;
import org.talend.dataquality.indicators.NullCountIndicator;
import org.talend.dataquality.indicators.RowCountIndicator;
import org.talend.dataquality.indicators.UniqueCountIndicator;
import org.talend.dataquality.indicators.UpperQuartileIndicator;


/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public class IndicatorCommonUtil {

    public IndicatorCommonUtil() {
        
    }
    
    public static Object getIndicatorValue(IndicatorEnum type, Indicator indicator) {
        switch (type) {
        case RowCountIndicatorEnum:
            return ((RowCountIndicator) indicator).getCount();

        case NullCountIndicatorEnum:
            return ((NullCountIndicator) indicator).getNullCount();

        case DistinctCountIndicatorEnum:
            return ((DistinctCountIndicator) indicator).getDistinctValueCount();

        case UniqueIndicatorEnum:
            return ((UniqueCountIndicator) indicator).getUniqueValueCount();

        case DuplicateCountIndicatorEnum:
            return ((DuplicateCountIndicator) indicator).getDuplicateValueCount();

        case BlankCountIndicatorEnum:
            return ((BlankCountIndicator) indicator).getBlankCount();

        case MinLengthIndicatorEnum:
            return ((MinLengthIndicator) indicator).getLength();

        case MaxLengthIndicatorEnum:
            return ((MaxLengthIndicator) indicator).getLength();

        case AverageLengthIndicatorEnum:
            return ((AverageLengthIndicator) indicator).getSumLength();

        case FrequencyIndicatorEnum:
            FrequencyIndicator frequency = (FrequencyIndicator) indicator;
            Set<Object> valueSet = frequency.getDistinctValues();
            Map<Object, Double> returnMap = new HashMap<Object, Double>();
            for (Object o : valueSet) {

                returnMap.put(o, frequency.getFrequency(o));
            }
            
            return returnMap;
            
        case MeanIndicatorEnum:
            return ((MeanIndicator) indicator).getMean();
            
        case MedianIndicatorEnum:
            return ((MedianIndicator) indicator).getMedian();
            
        case MinValueIndicatorEnum:
            return ((MinValueIndicator) indicator).getValue();
            
        case MaxValueIndicatorEnum:
            return ((MaxValueIndicator) indicator).getValue();
            
        case LowerQuartileIndicatorEnum:
            return ((LowerQuartileIndicator) indicator).getValue();
            
        case UpperQuartileIndicatorEnum:
            return ((UpperQuartileIndicator) indicator).getValue();
            
        default:
            
            return null;
        }
    }
    
}
