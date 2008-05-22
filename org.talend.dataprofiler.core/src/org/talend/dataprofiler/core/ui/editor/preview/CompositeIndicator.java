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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.dataprofiler.core.model.ColumnIndicator;


/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public class CompositeIndicator {
    
    public static final String SIMPLE_STATISTICS = "Simple Statistics";
    
    public static final String TEXT_STATISTICS = "Text Statistics";
    
    public static final String FREQUENCE_STATISTICS = "Frequency Statistics";
    
    public static final String SUMMARY_STATISTICS = "Summary Statistics";
    
    List<IndicatorTypeMapping> indicatorTypeMapping;
    
    Map<String, List<IndicatorTypeMapping>> separatedMap; 
    
    public CompositeIndicator(ColumnIndicator columnIndicator) {
        
        this.separatedMap = new HashMap<String, List<IndicatorTypeMapping>>();
        this.indicatorTypeMapping = columnIndicator.getIndicatorForMap();
    }
    
    public Map<String, List<IndicatorTypeMapping>> getIndicatorComposite() {
        
        List<IndicatorTypeMapping> simpleList = new ArrayList<IndicatorTypeMapping>();
        List<IndicatorTypeMapping> textList = new ArrayList<IndicatorTypeMapping>();
        List<IndicatorTypeMapping> frequencyList = new ArrayList<IndicatorTypeMapping>();
        List<IndicatorTypeMapping> summaryList = new ArrayList<IndicatorTypeMapping>();
        
        for (IndicatorTypeMapping one : indicatorTypeMapping) {
            
            switch (one.getType()) {
            case RowCountIndicatorEnum:
            case NullCountIndicatorEnum:
            case DistinctCountIndicatorEnum:
            case UniqueIndicatorEnum:
            case DuplicateCountIndicatorEnum:
            case BlankCountIndicatorEnum:
                simpleList.add(one);
                break;
            case MinLengthIndicatorEnum:
            case MaxLengthIndicatorEnum:
            case AverageLengthIndicatorEnum:
                textList.add(one);
                break;
            case FrequencyIndicatorEnum:
                frequencyList.add(one);
                break;
            case MeanIndicatorEnum:
            case MinValueIndicatorEnum:
            case MaxValueIndicatorEnum:
            case MedianIndicatorEnum:
            case LowerQuartileIndicatorEnum:
            case UpperQuartileIndicatorEnum:
                summaryList.add(one);
                break;
                
            default:
            }
        }
        
        separatedMap.put(SIMPLE_STATISTICS, simpleList);
        separatedMap.put(TEXT_STATISTICS, textList);
        separatedMap.put(FREQUENCE_STATISTICS, frequencyList);
        separatedMap.put(SUMMARY_STATISTICS, summaryList);
        
        return separatedMap;
    }
    
 }
