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
import org.talend.dataquality.indicators.BoxIndicator;
import org.talend.dataquality.indicators.DistinctCountIndicator;
import org.talend.dataquality.indicators.DuplicateCountIndicator;
import org.talend.dataquality.indicators.FrequencyIndicator;
import org.talend.dataquality.indicators.IQRIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.MaxLengthIndicator;
import org.talend.dataquality.indicators.MaxValueIndicator;
import org.talend.dataquality.indicators.MeanIndicator;
import org.talend.dataquality.indicators.MedianIndicator;
import org.talend.dataquality.indicators.MinLengthIndicator;
import org.talend.dataquality.indicators.MinValueIndicator;
import org.talend.dataquality.indicators.NullCountIndicator;
import org.talend.dataquality.indicators.RangeIndicator;
import org.talend.dataquality.indicators.RowCountIndicator;
import org.talend.dataquality.indicators.UniqueCountIndicator;


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
//                Long value = frequency.getCount(o);
                returnMap.put(o, frequency.getFrequency(o));
            }
            
            return returnMap;
            
        case MeanIndicatorEnum:
            return ((MeanIndicator) indicator).getMean();
            
        case IQRIndicatorEnum:
            return ((IQRIndicator) indicator).getRange();
            
        case MinValueIndicatorEnum:
            return ((MinValueIndicator) indicator).getValue();
            
        case MaxValueIndicatorEnum:
            return ((MaxValueIndicator) indicator).getValue();
            
        case MedianIndicatorEnum:
            return ((MedianIndicator) indicator).getMedian();
            
        default:
            
            return null;
        }
    }
    

//    class FrequencyComposite {
//        
//        private long count;
//        
//        private Set distinctValues;
//        
//        private double frequency;
//        
//        public FrequencyComposite(long count,Set distinctValues, double frequency){
//            
//            this.count = count;
//            this.distinctValues = distinctValues;
//            this.frequency = frequency;
//        }
//
//        
//        /**
//         * Getter for count.
//         * @return the count
//         */
//        public long getCount() {
//            return this.count;
//        }
//
//        
//        /**
//         * Getter for distinctValues.
//         * @return the distinctValues
//         */
//        public Set getDistinctValues() {
//            return this.distinctValues;
//        }
//
//        
//        /**
//         * Getter for frequency.
//         * @return the frequency
//         */
//        public double getFrequency() {
//            return this.frequency;
//        }
//    }
}
