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
package org.talend.dataprofiler.core.model.nodes.indicator;

import org.talend.dataprofiler.core.model.nodes.indicator.impl.IndicatorCategoryNode;
import org.talend.dataprofiler.core.model.nodes.indicator.impl.IndicatorLeafNode;
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;

/**
 * This class for the indicator tree building.
 * 
 */
public class IndicatorTreeModelBuilder {

    public static IIndicatorNode[] buildIndicatorCategory() {
        // build Basic Statistic categoryNode
        IndicatorCategoryNode simpleCategoryNode = new IndicatorCategoryNode();
        simpleCategoryNode.setLabel("Simple Statistics");
        IndicatorEnum[] simpleIndicatorEnums = new IndicatorEnum[] { IndicatorEnum.RowCountIndicatorEnum,
                IndicatorEnum.NullCountIndicatorEnum, IndicatorEnum.DistinctCountIndicatorEnum,
                IndicatorEnum.UniqueIndicatorEnum, IndicatorEnum.DuplicateCountIndicatorEnum,
                IndicatorEnum.BlankCountIndicatorEnum };
        createChildren(simpleCategoryNode, simpleIndicatorEnums);

        // build Nominal Statistic categoryNode
        IndicatorCategoryNode advanceCategoryNode = new IndicatorCategoryNode();
        advanceCategoryNode.setLabel("Advanced statistics");
        IndicatorEnum[] advanceIndicatorEnums = new IndicatorEnum[] { IndicatorEnum.ModeIndicatorEnum,
                IndicatorEnum.MeanIndicatorEnum, IndicatorEnum.MedianIndicatorEnum, IndicatorEnum.IQRIndicatorEnum,
                IndicatorEnum.RangeIndicatorEnum, IndicatorEnum.MinValueIndicatorEnum, IndicatorEnum.MaxValueIndicatorEnum };
        createChildren(advanceCategoryNode, advanceIndicatorEnums);

        // // build Numerical Statistic categoryNode
        // IndicatorCategoryNode numericalCategoryNode = new IndicatorCategoryNode();
        // numericalCategoryNode.setLabel("Numerical Statistic");
        // IndicatorEnum[] numericalFieldEnums = new IndicatorEnum[] { IndicatorEnum.MeanField,
        // IndicatorEnum.MedianField,
        // IndicatorEnum.MinimumValueField, IndicatorEnum.MaximumValueField, IndicatorEnum.RangeField,
        // IndicatorEnum.LowQuartileField, IndicatorEnum.UpperQuartileField, IndicatorEnum.IQRField,
        // IndicatorEnum.ModeField };
        // createChildren(numericalCategoryNode, new IndicatorCategoryNode[] { basicCategoryNode },
        // numericalFieldEnums);
        //
        // // build Date Statistic categoryNode
        // IndicatorCategoryNode dateCategoryNode = new IndicatorCategoryNode();
        // dateCategoryNode.setLabel("Date Statistic");
        // IndicatorEnum[] dateFieldEnums = new IndicatorEnum[] { IndicatorEnum.ModeField,
        // IndicatorEnum.FrequenceTableField,
        // IndicatorEnum.MeanField, IndicatorEnum.MinimumValueField, IndicatorEnum.MaximumValueField,
        // IndicatorEnum.MedianField, IndicatorEnum.LowQuartileField, IndicatorEnum.UpperQuartileField,
        // IndicatorEnum.IQRField, IndicatorEnum.RangeField };
        // createChildren(dateCategoryNode, new IndicatorCategoryNode[] { basicCategoryNode }, dateFieldEnums);

        return new IndicatorCategoryNode[] { simpleCategoryNode, advanceCategoryNode };
    }

//    private static void createChildren(IndicatorCategoryNode parent, IndicatorCategoryNode[] nodes, IndicatorEnum[] fieldEnums) {
//        createChildren(parent, fieldEnums);
//
//        for (IndicatorCategoryNode node : nodes) {
//            parent.addChildren(node);
//            node.setParent(parent);
//        }
//
//    }

    private static void createChildren(IndicatorCategoryNode parent, IndicatorEnum[] fieldEnums) {
        IndicatorLeafNode[] leafNodes = new IndicatorLeafNode[fieldEnums.length];
        for (int i = 0; i < fieldEnums.length; i++) {
            leafNodes[i] = createLeafNode(fieldEnums[i]);
            leafNodes[i].setParent(parent);
        }
        parent.setChildren(leafNodes);
    }

    private static IndicatorLeafNode createLeafNode(IndicatorEnum indicatorEnum) {
        IndicatorLeafNode node = new IndicatorLeafNode();
        node.setIndicatorEnum(indicatorEnum);
        return node;
    }

}
