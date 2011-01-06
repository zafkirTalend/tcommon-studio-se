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
package org.talend.designer.xmlmap.parts;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.talend.designer.xmlmap.figures.OutputTreeNodeFigure;
import org.talend.designer.xmlmap.figures.XmlTreeBranch;
import org.talend.designer.xmlmap.figures.TreeBranchFigure;
import org.talend.designer.xmlmap.figures.layout.EqualWidthLayout;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputTreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.TreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.XmlmapPackage;
import org.talend.designer.xmlmap.util.XmlMapUtil;

/**
 * wchen class global comment. Detailled comment
 */
public class OutputTreeNodeEditPart extends TreeNodeEditPart {

    @Override
    protected IFigure createFigure() {
        OutputTreeNode model = (OutputTreeNode) getModel();
        IFigure figure = null;
        // nodes in xml tree
        if (XmlMapUtil.getXPathLength(model.getXpath()) > 2) {
            figure = new XmlTreeBranch(new TreeBranchFigure(model), XmlTreeBranch.STYLE_ROW_HANGING);
            figureForAnchor = ((XmlTreeBranch) figure).getElement();
            treeBranchFigure = (XmlTreeBranch) figure;
        }
        // normal column and tree root
        else {
            figure = new OutputTreeNodeFigure(model);
            figure.setLayoutManager(new EqualWidthLayout());
            figureForAnchor = figure;
        }
        return figure;
    }

    @Override
    public List getModelChildren() {
        TreeNode model = (TreeNode) getModel();
        return model.getChildren();
    }

    @Override
    protected List getModelTargetConnections() {
        OutputTreeNode model = (OutputTreeNode) getModel();
        return model.getIncomingConnections();
    }

    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        OutputTreeNodeEditPart childPart = (OutputTreeNodeEditPart) childEditPart;
        OutputTreeNodeEditPart parentPart = (OutputTreeNodeEditPart) childEditPart.getParent();
        IFigure parentFigure = parentPart.getFigure();
        // when add one child node of a tree,should also add a label for firstColumn
        /* 1.should find the related root outputTreeNodeFigure */
        OutputTreeNodeFigure rootOutputTreeNodeFigure = findRootOutputTreeNodeFigure(parentFigure);
        /* 2.add label to first column of rootOutputTreeNodeFigure */
        if (rootOutputTreeNodeFigure != null) {
            Figure rootOutputTreeNodeNameFigure = rootOutputTreeNodeFigure.getOutputTreeNodeExpressionFigure();
            Label label = new Label();
            label.setText(((OutputTreeNode) childPart.getModel()).getExpression());
            label.setBorder(new LineBorder());
            rootOutputTreeNodeNameFigure.add(label);
        }
        super.addChildVisual(childEditPart, index);
    }

    protected OutputTreeNodeFigure findRootOutputTreeNodeFigure(IFigure parentFigure) {
        OutputTreeNodeFigure rootOutputTreeNodeFigure = null;
        if (parentFigure instanceof OutputTreeNodeFigure) {
            rootOutputTreeNodeFigure = (OutputTreeNodeFigure) parentFigure;
        } else
            rootOutputTreeNodeFigure = findRootOutputTreeNodeFigure(parentFigure.getParent());
        return rootOutputTreeNodeFigure;
    }

    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        // remove expression label when remove a treenode
        OutputTreeNodeEditPart childPart = (OutputTreeNodeEditPart) childEditPart;
        OutputTreeNodeFigure rootOutputTreeNodeFigure = findRootOutputTreeNodeFigure(childPart.getFigure());
        if (rootOutputTreeNodeFigure != null) {
            Figure rootOutputTreeNodeNameFigure = rootOutputTreeNodeFigure.getOutputTreeNodeExpressionFigure();
            if (rootOutputTreeNodeNameFigure.getChildren() != null) {
                Label expressionLabel = null;
                for (Object figure : rootOutputTreeNodeNameFigure.getChildren()) {
                    if (figure instanceof Label) {
                        Label label = (Label) figure;
                        if (label.getText().equals(((TreeNode) childPart.getModel()).getExpression())) {
                            expressionLabel = label;
                            break;
                        }
                    }
                }
                rootOutputTreeNodeNameFigure.getChildren().remove(expressionLabel);
            }
        }

        super.removeChildVisual(childEditPart);
    }

    @Override
    public IFigure getContentPane() {
        if (getFigure() instanceof OutputTreeNodeFigure) {
            return ((OutputTreeNodeFigure) getFigure()).getContentPane();
        } else if (getFigure() instanceof XmlTreeBranch) {
            return ((XmlTreeBranch) getFigure()).getContentsPane();
        }
        return super.getContentPane();
    }

    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        int type = notification.getEventType();
        int featureId = notification.getFeatureID(XmlmapPackage.class);
        switch (type) {
        case Notification.SET:
            switch (featureId) {
            case XmlmapPackage.TREE_NODE__EXPRESSION:
                if (getFigure() instanceof OutputTreeNodeFigure) {
                    OutputTreeNodeFigure outputFigure = (OutputTreeNodeFigure) getFigure();
                    if (outputFigure.getColumnExpressionFigure() != null) {
                        outputFigure.getColumnExpressionFigure().setText(((OutputTreeNode) getModel()).getExpression());
                        // outputFigure.getColumnExpressionFigure().setToolTip(((OutputTreeNode)
                        // getModel()).getExpression());
                    }

                }
                break;
            }
        }
    }

}
