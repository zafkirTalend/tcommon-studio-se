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
import org.talend.dataprofiler.core.model.nodes.indicator.tpye.IndicatorEnum;


/**
 * @author rli
 * 
 */
public class AbstractIndicatorNode implements IIndicatorNode {

    private IIndicatorNode parent;

    private IIndicatorNode[] children;

    private IndicatorEnum indicatorEnum;

    private String label;

    /**
     * @param children the children to set
     */
    public void setChildren(IIndicatorNode[] children) {
        this.children = children;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(IIndicatorNode parent) {
        this.parent = parent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataprofiler.core.model.nodes.indicator.IIndicatorNode#getChildren()
     */
    public IIndicatorNode[] getChildren() {
        return children;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataprofiler.core.model.nodes.indicator.IIndicatorNode#getParent()
     */
    public IIndicatorNode getParent() {
        return parent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataprofiler.core.model.nodes.indicator.IIndicatorNode#hasChildren()
     */
    public boolean hasChildren() {
        return children != null;
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.model.nodes.indicator.IIndicatorNode#getIndicatorEnum()
     */
    public IndicatorEnum getIndicatorEnum() {
        return indicatorEnum;
    }

    /**
     * @param indicatorEnum
     */
    public void setIndicatorEnum(IndicatorEnum indicatorEnum) {
        this.indicatorEnum = indicatorEnum;
    }

    public String getLabel() {
        if (this.label == null && indicatorEnum != null) {
            return this.indicatorEnum.getLabel();
        }
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    public void addChildren(IIndicatorNode node) {
        if (this.children != null) {
            IIndicatorNode[] nodes = new IIndicatorNode[this.children.length + 1];
            System.arraycopy(children, 0, nodes, 0, this.children.length);
            nodes[nodes.length - 1] = node;
            this.children = nodes;
        } else {
            this.children = new IIndicatorNode[] { node };
        }
    }

}
