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
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorFieldEnum;

/**
 * This class for the indicator tree building.
 * 
 */
public class IndicatorTreeModelBuilder {

    public static IIndicatorNode[] buildIndicatorCategory() {
        // build Basic Statistic categoryNode
        IndicatorCategoryNode basicCategoryNode = new IndicatorCategoryNode();
        basicCategoryNode.setLabel("Basic Statistic");
        IndicatorFieldEnum[] basicFieldEnums = new IndicatorFieldEnum[] { IndicatorFieldEnum.RowCountField,
                IndicatorFieldEnum.DistinctValuesField, IndicatorFieldEnum.UniqueField, IndicatorFieldEnum.RepeatField,
                IndicatorFieldEnum.NullValueField };
        createChildren(basicCategoryNode, basicFieldEnums);

        // build Nominal Statistic categoryNode
        IndicatorCategoryNode nominalCategoryNode = new IndicatorCategoryNode();
        nominalCategoryNode.setLabel("Nominal Statistic");
        IndicatorFieldEnum[] nominalFieldEnums = new IndicatorFieldEnum[] { IndicatorFieldEnum.ModeField,
                IndicatorFieldEnum.FrequenceTableField, IndicatorFieldEnum.BlankValueField };
        createChildren(nominalCategoryNode, new IndicatorCategoryNode[] { basicCategoryNode }, nominalFieldEnums);

        // build Numerical Statistic categoryNode
        IndicatorCategoryNode numericalCategoryNode = new IndicatorCategoryNode();
        numericalCategoryNode.setLabel("Numerical Statistic");
        IndicatorFieldEnum[] numericalFieldEnums = new IndicatorFieldEnum[] { IndicatorFieldEnum.MeanField,
                IndicatorFieldEnum.MedianField, IndicatorFieldEnum.MinimumValueField, IndicatorFieldEnum.MaximumValueField,
                IndicatorFieldEnum.RangeField, IndicatorFieldEnum.LowQuartileField, IndicatorFieldEnum.UpperQuartileField,
                IndicatorFieldEnum.IQRField, IndicatorFieldEnum.ModeField };
        createChildren(numericalCategoryNode, new IndicatorCategoryNode[] { basicCategoryNode }, numericalFieldEnums);

        // build Date Statistic categoryNode
        IndicatorCategoryNode dateCategoryNode = new IndicatorCategoryNode();
        dateCategoryNode.setLabel("Date Statistic");
        IndicatorFieldEnum[] dateFieldEnums = new IndicatorFieldEnum[] { IndicatorFieldEnum.ModeField,
                IndicatorFieldEnum.FrequenceTableField, IndicatorFieldEnum.MeanField, IndicatorFieldEnum.MinimumValueField,
                IndicatorFieldEnum.MaximumValueField, IndicatorFieldEnum.MedianField, IndicatorFieldEnum.LowQuartileField,
                IndicatorFieldEnum.UpperQuartileField, IndicatorFieldEnum.IQRField, IndicatorFieldEnum.RangeField };
        createChildren(dateCategoryNode, new IndicatorCategoryNode[] { basicCategoryNode }, dateFieldEnums);

        return new IndicatorCategoryNode[] { nominalCategoryNode, numericalCategoryNode, dateCategoryNode };
    }

    private static  void createChildren(IndicatorCategoryNode parent, IndicatorCategoryNode[] nodes, IndicatorFieldEnum[] fieldEnums) {
        createChildren(parent, fieldEnums);

        for (IndicatorCategoryNode node : nodes) {
            parent.addChildren(node);
            node.setParent(parent);
        }

    }

    private static void createChildren(IndicatorCategoryNode parent, IndicatorFieldEnum[] fieldEnums) {
        IndicatorLeafNode[] leafNodes = new IndicatorLeafNode[fieldEnums.length];
        for (int i = 0; i < fieldEnums.length; i++) {
            leafNodes[i] = createLeafNode(fieldEnums[i]);
            leafNodes[i].setParent(parent);
        }
        parent.setChildren(leafNodes);
    }

    private static IndicatorLeafNode createLeafNode(IndicatorFieldEnum fieldEnum) {
        IndicatorLeafNode node = new IndicatorLeafNode();
        node.setIndicatorFieldEnum(fieldEnum);
        return node;
    }

}
