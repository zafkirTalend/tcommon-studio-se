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

import java.util.ArrayList;
import java.util.List;

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

        // build Text statistics categoryNode
        IndicatorCategoryNode textCategoryNode = new IndicatorCategoryNode();
        textCategoryNode.setLabel("Text statistics");
        IndicatorEnum[] textIndicatorEnums = new IndicatorEnum[] { IndicatorEnum.MinLengthIndicatorEnum,
                IndicatorEnum.MaxLengthIndicatorEnum, IndicatorEnum.AverageLengthIndicatorEnum };
        createChildren(textCategoryNode, textIndicatorEnums);

        // build Summary Statistic categoryNode
        IndicatorCategoryNode summaryCategoryNode = new IndicatorCategoryNode();
        summaryCategoryNode.setLabel("Summary Statistics");
        IndicatorEnum[] summaryIndicatorEnums = new IndicatorEnum[] { IndicatorEnum.MeanIndicatorEnum,
                IndicatorEnum.MedianIndicatorEnum, IndicatorEnum.IQRIndicatorEnum, IndicatorEnum.RangeIndicatorEnum };
        createChildren(summaryCategoryNode, summaryIndicatorEnums);

        // build Nominal Statistic categoryNode
        IndicatorCategoryNode advanceCategoryNode = new IndicatorCategoryNode();
        advanceCategoryNode.setLabel("Advanced statistics");
        IndicatorEnum[] advanceIndicatorEnums = new IndicatorEnum[] { IndicatorEnum.ModeIndicatorEnum,
                IndicatorEnum.FrequencyIndicatorEnum };
        createChildren(advanceCategoryNode, advanceIndicatorEnums, new IndicatorCategoryNode[] { summaryCategoryNode });

        return new IndicatorCategoryNode[] { simpleCategoryNode, textCategoryNode, advanceCategoryNode };
    }

    private static void createChildren(IndicatorCategoryNode parent, IndicatorEnum[] fieldEnums) {
        IndicatorNode[] leafNodes = new IndicatorNode[fieldEnums.length];
        for (int i = 0; i < fieldEnums.length; i++) {
            leafNodes[i] = createLeafNode(fieldEnums[i]);
            leafNodes[i].setParent(parent);
        }
        parent.setChildren(leafNodes);
    }

    private static void createChildren(IndicatorCategoryNode parent, IndicatorEnum[] fieldEnums,
            IndicatorCategoryNode[] categoryNode) {
        List<IIndicatorNode> leafNodeList = new ArrayList<IIndicatorNode>();
        for (int i = 0; i < fieldEnums.length; i++) {
            IndicatorNode leafNode = createLeafNode(fieldEnums[i]);
            leafNodeList.add(leafNode);
            leafNode.setParent(parent);
        }
        for (int i = 0; i < categoryNode.length; i++) {
            leafNodeList.add(categoryNode[i]);
        }
        parent.setChildren(leafNodeList.toArray(new IIndicatorNode[leafNodeList.size()]));
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
