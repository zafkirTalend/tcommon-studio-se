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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.talend.dataprofiler.core.model.ColumnIndicator;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;
import org.talend.dataprofiler.core.ui.utils.ChartUtils;


/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public class IndicatorChartFactory {
    
    private static final  int CHART_WIDTH = 420;
    
    private static final  int CHART_HEIGHT = 230;
    

    public static ImageDescriptor createSimpleChart(String titile, CategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createBarChart3D(
                null,
                titile, // 目录轴的显示标签
                "Value", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                false,   // 是否显示图例(对于简单的柱状图必须是false)
                false,  // 是否生成工具
                false   // 是否生成URL链接
                );
        
        try {
            
            return ChartUtils.convertToImage(chart, CHART_WIDTH, CHART_HEIGHT);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void createTextChart() {
        
    }
    
    public static ImageDescriptor createFrequencyChart(String titile, CategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createBarChart3D(
                null,
                titile, 
                "Value", 
                dataset, 
                PlotOrientation.HORIZONTAL, // 图表方向：水平、垂直
                false,   
                false,  
                false   
                );
        
        try {
            
            return ChartUtils.convertToImage(chart, CHART_WIDTH, CHART_HEIGHT);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static ImageDescriptor createSummaryChart(String title, BoxAndWhiskerCategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
                null, 
                title, 
                "value label", 
                dataset, 
                false
                );
        
        try {
            
            return ChartUtils.convertToImage(chart, CHART_WIDTH, CHART_HEIGHT);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private static CategoryDataset createSimpleDataset(List<IndicatorTypeMapping> indicatorTypeMapping) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (IndicatorTypeMapping indicator : indicatorTypeMapping) {
            
            Object object = null;
            try {
                object = IndicatorCommonUtil.getIndicatorValue(indicator.getType(), indicator.getIndicator());
            } catch (UnsupportedOperationException ue) {
                object = null;
            }
            
            
            if (object == null) {
                dataset.addValue(0, "", indicator.getType().getLabel());
            } else {
                try {
                    String valueStr = String.valueOf(object);
                    int value = Integer.parseInt(valueStr);
                    dataset.addValue(50, "", indicator.getType().getLabel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }

        return dataset;
        
    }
    
    private static BoxAndWhiskerCategoryDataset createSummaryDataset(List<IndicatorTypeMapping> indicatorTypeMapping) {

        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        
        Map<IndicatorEnum, Double> map = new HashMap<IndicatorEnum, Double>();
        
        for (IndicatorTypeMapping indicator : indicatorTypeMapping) {
            
            Object object = null;
            try {
                object = IndicatorCommonUtil.getIndicatorValue(indicator.getType(), indicator.getIndicator());
            } catch (Exception ue) {
                object = null;
            }
            
            if (object != null) {
                String strValue = String.valueOf(object);
                double doubleValue = Double.valueOf(strValue);
                map.put(indicator.getType(), doubleValue);
            } else {
                map.put(indicator.getType(), 0.0);
            }
        }
        
//        BoxAndWhiskerItem item = new BoxAndWhiskerItem(
//                map.get(IndicatorEnum.MeanIndicatorEnum),
//                map.get(IndicatorEnum.MedianIndicatorEnum),
//                null,
//                null,
//                map.get(IndicatorEnum.MinValueIndicatorEnum),
//                map.get(IndicatorEnum.MaxValueIndicatorEnum),
//                null,
//                null,
//                null
//                );
        BoxAndWhiskerItem item = new BoxAndWhiskerItem(
                50,
                55,
                20,
                40,
                5,
                80,
                1,
                100,
                null
                );
        dataset.add(item, "", "");

        return dataset;
        
    }
    
    private static CategoryDataset createFrequenceDataset(List<IndicatorTypeMapping> indicatorTypeMapping) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (IndicatorTypeMapping indicator : indicatorTypeMapping) {
            Object object = null;
            try {
                object = IndicatorCommonUtil.getIndicatorValue(indicator.getType(), indicator.getIndicator());
            } catch (UnsupportedOperationException ue) {
                object = null;
            }
            
            if (object != null) {
                Map<Object, Double> map = (Map<Object, Double>) object;
                
                if (map.size() == 0) {
                    map.put("season1", 123.0);
                    map.put("season2", 341.12);
                    map.put("season3", 300.58);
                    map.put("season4", 450.62);
                }
                for (Object o : map.keySet()) {
                    dataset.addValue(map.get(o), "", String.valueOf(o));
                }
            }
        }
        
        return dataset;
    }
    
    public static List<ImageDescriptor> createChart(ColumnIndicator column) {
        
        CompositeIndicator compositeIndicator = new CompositeIndicator(column);
        Map<String, List<IndicatorTypeMapping>> separatedMap = compositeIndicator.getIndicatorComposite();
        List<ImageDescriptor> returnFiles = new ArrayList<ImageDescriptor>();
        
        if (separatedMap.get(CompositeIndicator.SIMPLE_STATISTICS).size() != 0) {

            CategoryDataset dataset = createSimpleDataset(separatedMap.get(CompositeIndicator.SIMPLE_STATISTICS));
            returnFiles.add(createSimpleChart(CompositeIndicator.SIMPLE_STATISTICS, dataset));

        }
        
        if (separatedMap.get(CompositeIndicator.TEXT_STATISTICS).size() != 0) {
                
            CategoryDataset dataset = createSimpleDataset(separatedMap.get(CompositeIndicator.TEXT_STATISTICS));
            returnFiles.add(createSimpleChart(CompositeIndicator.TEXT_STATISTICS, dataset));
        }
        
        if (separatedMap.get(CompositeIndicator.FREQUENCE_STATISTICS).size() != 0) {
            
            CategoryDataset dataset = createFrequenceDataset(separatedMap.get(CompositeIndicator.FREQUENCE_STATISTICS));
            returnFiles.add(createFrequencyChart(CompositeIndicator.FREQUENCE_STATISTICS, dataset));
        }
        
        if (separatedMap.get(CompositeIndicator.SUMMARY_STATISTICS).size() != 0) {
   
            BoxAndWhiskerCategoryDataset dataset = createSummaryDataset(separatedMap.get(CompositeIndicator.SUMMARY_STATISTICS));
            returnFiles.add(createSummaryChart(CompositeIndicator.SUMMARY_STATISTICS, dataset));
        }

        return returnFiles;
    }
}
