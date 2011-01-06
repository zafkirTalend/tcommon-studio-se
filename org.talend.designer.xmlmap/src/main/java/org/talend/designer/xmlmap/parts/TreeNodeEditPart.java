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

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.swt.SWT;
import org.talend.designer.xmlmap.figures.XmlTreeBranch;
import org.talend.designer.xmlmap.figures.TreeBranchFigure;
import org.talend.designer.xmlmap.figures.TreeNodeFigure;
import org.talend.designer.xmlmap.figures.layout.EqualWidthLayout;
import org.talend.designer.xmlmap.model.emf.xmlmap.NodeType;
import org.talend.designer.xmlmap.model.emf.xmlmap.TreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.XmlmapPackage;
import org.talend.designer.xmlmap.policy.CustomComponentEditPolicy;
import org.talend.designer.xmlmap.policy.CustomGraphicalNodeEditPolicy;
import org.talend.designer.xmlmap.policy.DragAndDropEditPolicy;
import org.talend.designer.xmlmap.policy.TreeExpandSupportEditPolicy;
import org.talend.designer.xmlmap.util.XmlMapUtil;

/**
 * wchen class global comment. Detailled comment
 */
public class TreeNodeEditPart extends BaseEditPart implements NodeEditPart {

    // used for anchor
    protected IFigure figureForAnchor;

    // for expand and collapse
    protected IFigure rootAnchor;

    protected XmlTreeBranch treeBranchFigure;

    @Override
    protected IFigure createFigure() {
        TreeNode model = (TreeNode) getModel();
        IFigure figure = null;
        // nodes in xml tree
        if (XmlMapUtil.getXPathLength(model.getXpath()) > 2) {
            String status = model.isLoop() ? String.valueOf(model.isLoop()) : "";
            figure = new XmlTreeBranch(new TreeBranchFigure(model), XmlTreeBranch.STYLE_ROW_HANGING);
            // figure = new TreeBranch(new TreeBranchFigure(getTreeBranchName(model), "true"),
            // TreeBranch.STYLE_HANGING);
            figureForAnchor = ((XmlTreeBranch) figure).getElement();
            treeBranchFigure = (XmlTreeBranch) figure;

            // for test
            // if (model.getChildren().size() == 1) {
            // figure.setOpaque(true);
            // figure.setBackgroundColor(ColorConstants.green);
            // }

        }
        // normal column and tree root
        else {
            figure = new TreeNodeFigure(model);
            figure.setLayoutManager(new EqualWidthLayout());
            figureForAnchor = figure;
        }
        return figure;
    }

    protected String getTreeBranchName(TreeNode model) {
        String name = "";
        if (NodeType.ATTRIBUT.equals(model.getNodeType()) || NodeType.NAME_SPACE.equals(model.getNodeType())) {
            name = model.getXpath().substring(model.getXpath().lastIndexOf(XmlMapUtil.XPATH_SEPARATOR) + 1,
                    model.getXpath().length());
        } else {
            name = model.getName();
        }
        return name;
    }

    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new CustomGraphicalNodeEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new NonResizableEditPolicy());
        installEditPolicy("Drag and Drop", new DragAndDropEditPolicy());
        installEditPolicy(TreeExpandSupportEditPolicy.EXPAND_SUPPORT, new TreeExpandSupportEditPolicy());
        // to deleteNode
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new CustomComponentEditPolicy());
    }

    @Override
    public List getModelChildren() {
        TreeNode model = (TreeNode) getModel();
        return model.getChildren();
    }

    @Override
    protected List getModelSourceConnections() {
        TreeNode model = (TreeNode) getModel();
        return model.getOutgoingConnections();
    }

    public void expand(boolean expand) {
        if (getFigure() instanceof XmlTreeBranch) {
            ((XmlTreeBranch) this.getFigure()).doExpandCollapse((XmlTreeBranch) (getFigure()));
        }
    }

    // @Override
    // public List getSourceConnections() {
    // TreeNode model = (TreeNode) getModel();
    // return model.getOutgoingConnections();
    // }

    @Override
    public IFigure getContentPane() {
        if (getFigure() instanceof TreeNodeFigure) {
            return ((TreeNodeFigure) getFigure()).getContentPane();
        } else if (getFigure() instanceof XmlTreeBranch) {
            return ((XmlTreeBranch) getFigure()).getContentsPane();
        }
        return super.getContentPane();
    }

    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        if (getRootAnchor() != null) {
            return new ChopboxAnchor(getRootAnchor());
        }
        return new ChopboxAnchor(getFigureForAnchor());
    }

    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        if (getRootAnchor() != null) {
            return new ChopboxAnchor(getRootAnchor());
        }
        return new ChopboxAnchor(getFigureForAnchor());
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        if (getRootAnchor() != null) {
            return new ChopboxAnchor(getRootAnchor());
        }
        return new ChopboxAnchor(getFigureForAnchor());
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        if (getRootAnchor() != null) {
            return new ChopboxAnchor(getRootAnchor());
        }
        return new ChopboxAnchor(getFigureForAnchor());
    }

    public void notifyChanged(Notification notification) {
        int type = notification.getEventType();
        int featureId = notification.getFeatureID(XmlmapPackage.class);
        switch (type) {
        case Notification.SET:
            switch (featureId) {
            case XmlmapPackage.TREE_NODE__LOOP:
            case XmlmapPackage.OUTPUT_TREE_NODE__GROUP:
                if (treeBranchFigure != null) {
                    if (treeBranchFigure.getElement() instanceof TreeBranchFigure) {
                        TreeBranchFigure branchFigure = (TreeBranchFigure) treeBranchFigure.getElement();
                        branchFigure.updateStatus();
                    }
                }

                break;
            }
        case Notification.ADD:
            switch (featureId) {
            case XmlmapPackage.TREE_NODE__CHILDREN:
                refreshChildren();
                break;
            case XmlmapPackage.TREE_NODE__OUTGOING_CONNECTIONS:
                refreshSourceConnections();
                break;
            case XmlmapPackage.OUTPUT_TREE_NODE__INCOMING_CONNECTIONS:
                refreshTargetConnections();
                break;
            }
        case Notification.REMOVE:
            switch (featureId) {
            case XmlmapPackage.TREE_NODE__CHILDREN:
                refreshChildren();
            case XmlmapPackage.TREE_NODE__OUTGOING_CONNECTIONS:
                refreshSourceConnections();
                break;

            }

        }

    }

    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        super.addChildVisual(childEditPart, index);
    }

    public void refreshChildrenSourceConnections(TreeNodeEditPart rootPart, boolean expanded) {
        for (Object obj : getChildren()) {
            if (obj instanceof TreeNodeEditPart) {
                TreeNodeEditPart part = (TreeNodeEditPart) obj;
                if (expanded) {
                    // do collapse
                    part.setRootAnchor(rootPart.getFigureForAnchor());
                } else {
                    part.setRootAnchor(null);
                }
                if (part.getSourceConnections() != null) {
                    for (Object conn : part.getSourceConnections()) {
                        if (conn instanceof AbstractConnectionEditPart) {
                            AbstractConnectionEditPart connectionEditPart = (AbstractConnectionEditPart) conn;
                            if (connectionEditPart.getFigure() instanceof PolylineConnection) {
                                PolylineConnection connFigure = (PolylineConnection) connectionEditPart.getFigure();
                                if (expanded) {
                                    connFigure.setLineStyle(SWT.LINE_DASHDOTDOT);
                                } else {
                                    connFigure.setLineStyle(SWT.LINE_SOLID);
                                }
                            }
                            connectionEditPart.refresh();
                        }
                    }
                }
                part.refreshChildrenSourceConnections(rootPart, expanded);
            }
        }

    }

    public void refreshChildrenTargetConnections(TreeNodeEditPart rootPart, boolean expanded) {
        for (Object obj : getChildren()) {
            if (obj instanceof TreeNodeEditPart) {
                TreeNodeEditPart part = (TreeNodeEditPart) obj;
                if (expanded) {
                    // do collapse
                    part.setRootAnchor(rootPart.getFigureForAnchor());
                } else {
                    part.setRootAnchor(null);
                }
                if (part.getTargetConnections() != null) {
                    for (Object conn : part.getTargetConnections()) {
                        if (conn instanceof AbstractConnectionEditPart) {
                            AbstractConnectionEditPart connectionEditPart = (AbstractConnectionEditPart) conn;
                            if (connectionEditPart.getFigure() instanceof PolylineConnection) {
                                PolylineConnection connFigure = (PolylineConnection) connectionEditPart.getFigure();
                                if (part.getTreeBranchFigure() != null) {
                                    if (expanded) {
                                        connFigure.setLineStyle(SWT.LINE_DASHDOTDOT);
                                    } else {
                                        connFigure.setLineStyle(SWT.LINE_SOLID);
                                    }
                                }
                            }
                            connectionEditPart.refresh();
                        }
                    }
                }
                part.refreshChildrenTargetConnections(rootPart, expanded);
            }
        }
    }

    protected IFigure getFigureForAnchor() {

        return figureForAnchor;
    }

    protected void setFigureForAnchor(IFigure figureForAnchor) {
        this.figureForAnchor = figureForAnchor;
    }

    protected XmlTreeBranch getTreeBranchFigure() {
        return this.treeBranchFigure;

    }

    protected IFigure getRootAnchor() {
        return rootAnchor;
    }

    protected void setRootAnchor(IFigure rootAnchor) {
        this.rootAnchor = rootAnchor;
    }

}
