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

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
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
    
    private static final  int CHART_WIDTH = 400;
    
    private static final  int CHART_HEIGHT = 230;
    
    private static final  int CHART_WIDTH2 = 100;
    
    private static final  int CHART_HEIGHT2 = 380;
    
    private static BarRenderer3D renderer = new BarRenderer3D();
    
    //start to create kinds of chart
    public static ImageDescriptor createSimpleChart(String titile, CategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createBarChart3D(
                null,
                titile, 
                "Value", 
                dataset, 
                PlotOrientation.VERTICAL,
                false,  
                false,  
                false   
                );
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinesVisible(true);

        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelFont(new Font("Arial", Font.PLAIN, 14));
        renderer.setMaximumBarWidth(0.15);
        
        plot.setRenderer(renderer);
        plot.setForegroundAlpha(0.6f);
        
        try {
            
            return ChartUtils.convertToImage(chart, CHART_WIDTH, CHART_HEIGHT);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static ImageDescriptor createTextChart(String titile, CategoryDataset dataset) {
        
        return createSimpleChart(titile, dataset);
    }
    
    public static ImageDescriptor createFrequencyChart(String titile, CategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createBarChart(
                null,
                titile, 
                "Value", 
                dataset, 
                PlotOrientation.HORIZONTAL, 
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
                null, 
                "value", 
                dataset, 
                false
                );
        
        try {
            
            return ChartUtils.convertToImage(chart, CHART_WIDTH2, CHART_HEIGHT2);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //end creating kinds of chart
    
    //start to create kinds of dataset with real value
    
    private static CategoryDataset createSimpleDataset(List<IndicatorTypeMapping> indicatorTypeMapping, boolean isCreate) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (isCreate) {
            for (int index = 0; index<indicatorTypeMapping.size(); index++) {
                IndicatorTypeMapping indicatorMap = indicatorTypeMapping.get(index);
                IndicatorCommonUtil.compositeIndicatorMap(indicatorMap);
                Object object = indicatorMap.getValue();
                String label = indicatorMap.getIndicatorName();
                
                if (object == null) {
                    dataset.addValue(0, "", indicatorMap.getType().getLabel());
                } else {
                    String valueStr = String.valueOf(object);
                    double value = Double.parseDouble(valueStr);
                    
                    renderer.setSeriesPaint(index, indicatorMap.getColor());
                    dataset.addValue(value, label, label);
                }
            }
        } else {
            for (IndicatorTypeMapping indicator : indicatorTypeMapping) {
                
                dataset.addValue(150, "", indicator.getIndicator().getIndicatorDefinition().getLabel());
            }
        }


        return dataset;
        
    }
    
    private static CategoryDataset createTextedDataset(List<IndicatorTypeMapping> indicatorTypeMapping, boolean isCreate) {
        
        return createSimpleDataset(indicatorTypeMapping, isCreate);
    }
    
    private static BoxAndWhiskerCategoryDataset createSummaryDataset(List<IndicatorTypeMapping> indicatorTypeMapping, boolean isCreate) {

        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        
        if (isCreate) {
            Map<IndicatorEnum, Double> map = new HashMap<IndicatorEnum, Double>();
            
            for (IndicatorTypeMapping indicatorMap : indicatorTypeMapping) {
                
                Object object = null;
                try {
                    IndicatorCommonUtil.compositeIndicatorMap(indicatorMap);
                    object = indicatorMap.getValue();
                } catch (Exception ue) {
                    ue.printStackTrace();
                    object = null;
                }
                
                if (object != null) {
                    String strValue = String.valueOf(object);
                    double doubleValue = Double.valueOf(strValue);
                    map.put(indicatorMap.getType(), doubleValue);
                } else {
                    map.put(indicatorMap.getType(), 0.0);
                }
            }
            
            BoxAndWhiskerItem item = new BoxAndWhiskerItem(
                    map.get(IndicatorEnum.MeanIndicatorEnum),
                    map.get(IndicatorEnum.MedianIndicatorEnum),
                    map.get(IndicatorEnum.LowerQuartileIndicatorEnum),
                    map.get(IndicatorEnum.UpperQuartileIndicatorEnum),
                    map.get(IndicatorEnum.MinValueIndicatorEnum),
                    map.get(IndicatorEnum.MaxValueIndicatorEnum),
                    map.get(IndicatorEnum.LowerQuartileIndicatorEnum),
                    map.get(IndicatorEnum.UpperQuartileIndicatorEnum),
                    null
                    );

            dataset.add(item, "", "");
        } else {
            BoxAndWhiskerItem item = new BoxAndWhiskerItem(40, 45, 30, 60, 15 , 75, 1, 100, null);
            dataset.add(item, "", "");
        }

        return dataset;
        
    }
    
    private static CategoryDataset createFrequenceDataset(List<IndicatorTypeMapping> indicatorTypeMapping, boolean isCreate) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (IndicatorTypeMapping indicatorMap : indicatorTypeMapping) {
            Object object = null;
            try {
                IndicatorCommonUtil.compositeIndicatorMap(indicatorMap);
                object = indicatorMap.getValue();
                
            } catch (UnsupportedOperationException ue) {
                object = null;
            }
            
            if (object != null) {
                Map<Object, Long> map = (Map<Object, Long>) object;
                
                if (isCreate) {
                    for (Object obj : map.keySet()) {
                        dataset.addValue(map.get(obj), "", String.valueOf(obj));
                    }
                } else {
                    
                    dataset.addValue(70, "", "one");
                    dataset.addValue(150, "", "two");
                    dataset.addValue(40, "", "three");
                    dataset.addValue(125, "", "four");
                    dataset.addValue(174, "", "five");
                }
            }
        }
        
        return dataset;
    }
    
    //end create dataset with real value
    
    public static List<ImageDescriptor> createChart(ColumnIndicator column, boolean isCreate) {
        
        CompositeIndicator compositeIndicator = new CompositeIndicator(column);
        Map<String, List<IndicatorTypeMapping>> separatedMap = compositeIndicator.getIndicatorComposite();
        List<ImageDescriptor> returnFiles = new ArrayList<ImageDescriptor>();
        
        if (separatedMap.get(CompositeIndicator.SIMPLE_STATISTICS).size() != 0) {

            CategoryDataset dataset = createSimpleDataset(separatedMap.get(CompositeIndicator.SIMPLE_STATISTICS), isCreate);
            
            returnFiles.add(createSimpleChart(CompositeIndicator.SIMPLE_STATISTICS, dataset));

        }
        
        if (separatedMap.get(CompositeIndicator.TEXT_STATISTICS).size() != 0) {
                
            CategoryDataset dataset = createTextedDataset(separatedMap.get(CompositeIndicator.TEXT_STATISTICS), isCreate);
             
            returnFiles.add(createTextChart(CompositeIndicator.TEXT_STATISTICS, dataset));
        }
        
        if (separatedMap.get(CompositeIndicator.FREQUENCE_STATISTICS).size() != 0) {
            
            CategoryDataset dataset = createFrequenceDataset(separatedMap.get(CompositeIndicator.FREQUENCE_STATISTICS), isCreate);
             
            returnFiles.add(createFrequencyChart(CompositeIndicator.FREQUENCE_STATISTICS, dataset));
        }
        
        if (separatedMap.get(CompositeIndicator.SUMMARY_STATISTICS).size() != 0) {

            BoxAndWhiskerCategoryDataset dataset = createSummaryDataset(separatedMap.get(CompositeIndicator.SUMMARY_STATISTICS), isCreate);
             
            returnFiles.add(createSummaryChart(CompositeIndicator.SUMMARY_STATISTICS, dataset));
        }

        return returnFiles;
    }
    
}


