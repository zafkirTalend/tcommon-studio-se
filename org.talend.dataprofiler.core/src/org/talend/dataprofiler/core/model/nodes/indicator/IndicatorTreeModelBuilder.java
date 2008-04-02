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
import org.talend.dataprofiler.core.model.nodes.indicator.impl.IndicatorNode;
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
                IndicatorEnum.RangeIndicatorEnum, IndicatorEnum.FrequencyIndicatorEnum };
        createChildren(advanceCategoryNode, advanceIndicatorEnums);

        return new IndicatorCategoryNode[] { simpleCategoryNode, advanceCategoryNode };
    }

    private static void createChildren(IndicatorCategoryNode parent, IndicatorEnum[] fieldEnums) {
        IndicatorNode[] leafNodes = new IndicatorNode[fieldEnums.length];
        for (int i = 0; i < fieldEnums.length; i++) {
            leafNodes[i] = createLeafNode(fieldEnums[i]);
            leafNodes[i].setParent(parent);
        }
        parent.setChildren(leafNodes);
    }

    private static IndicatorNode createLeafNode(IndicatorEnum indicatorEnum) {
        IndicatorNode node = new IndicatorNode();
        node.setIndicatorEnum(indicatorEnum);
        if (indicatorEnum.hasChildren()) {
            IndicatorNode[] indicatorNodes = new IndicatorNode[indicatorEnum.getChildren().length];
            for (int i = 0; i < indicatorEnum.getChildren().length; i++) {
                indicatorNodes[i] = createLeafNode(indicatorEnum.getChildren()[i]);
            }
            node.setChildren(indicatorNodes);
        }
        return node;
    }

}
