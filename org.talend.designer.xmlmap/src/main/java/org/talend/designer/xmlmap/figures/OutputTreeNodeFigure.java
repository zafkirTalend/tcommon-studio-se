// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.xmlmap.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.talend.designer.xmlmap.figures.layout.EqualHeightLayout;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputTreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputXmlTree;
import org.talend.designer.xmlmap.util.XmlMapUtil;

/**
 * wchen class global comment. Detailled comment
 */
public class OutputTreeNodeFigure extends Figure {

    private XmlTreeBranch treeBranch;

    private OutputTreeNode treeNode;

    private Figure outputTreeNodeExpressionFigure;

    private Label columnExpressionFigure;

    public OutputTreeNodeFigure(OutputTreeNode treeNode) {
        this.treeNode = treeNode;
        createContent();
    }

    private void createContent() {
        ToolbarLayout layout = new ToolbarLayout();
        layout.setVertical(false);
        // normal column
        if (!XmlMapUtil.DOCUMENT.equals(treeNode.getType())) {
            setLayoutManager(layout);
            columnExpressionFigure = new Label();
            columnExpressionFigure.setText(treeNode.getExpression());
            columnExpressionFigure.setBorder(new LineBorder());

            Label valueLabel = new Label();
            valueLabel.setText(treeNode.getName());
            valueLabel.setBorder(new LineBorder());

            this.add(columnExpressionFigure);
            this.add(valueLabel);
        }
        // xml root
        else if (XmlMapUtil.DOCUMENT.equals(treeNode.getType()) && treeNode.eContainer() instanceof OutputXmlTree) {
            setLayoutManager(layout);
            /* column1,expression */
            outputTreeNodeExpressionFigure = new Label();
            outputTreeNodeExpressionFigure.setLayoutManager(new EqualHeightLayout());
            this.add(outputTreeNodeExpressionFigure);
            Label label = new Label();
            // label.setText(((OutputTreeNode) childPart.getModel()).getExpression());
            label.setBorder(new LineBorder());
            outputTreeNodeExpressionFigure.add(label);
            outputTreeNodeExpressionFigure.setBackgroundColor(ColorConstants.red);

            /* column2, column */
            String status = treeNode.isLoop() ? String.valueOf(treeNode.isLoop()) : "";
            treeBranch = new XmlTreeRoot(new TreeBranchFigure(treeNode), XmlTreeBranch.STYLE_ROW_HANGING);
            treeBranch.setBorder(new LineBorder());

            // outputTreeNodeNameFigure.generateSplitFigures();
            // createTreeNode(root, treeNode.getChildren());
            this.add(treeBranch);
        }
        /* column3,node status */
        // Label nodeStatus = new Label();
        // // nodeStatus.setText("element loop");
        // nodeStatus.setBorder(new LineBorder());
        // this.add(nodeStatus);

    }

    public IFigure getContentPane() {
        if (!XmlMapUtil.DOCUMENT.equals(treeNode.getType())) {
            return this;
        } else {
            return treeBranch.getContentsPane();
        }
    }

    public Figure getOutputTreeNodeExpressionFigure() {
        return this.outputTreeNodeExpressionFigure;
    }

    public Label getColumnExpressionFigure() {
        return columnExpressionFigure;
    }

    public XmlTreeBranch getTreeBranch() {
        return treeBranch;
    }

}
