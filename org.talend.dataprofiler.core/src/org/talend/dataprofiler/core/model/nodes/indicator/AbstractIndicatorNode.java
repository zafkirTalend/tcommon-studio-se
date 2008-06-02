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
public abstract class AbstractIndicatorNode implements IIndicatorNode {

    private IIndicatorNode parent;

    protected IIndicatorNode[] children;

    protected IndicatorEnum indicatorEnum;

    protected String label;

    public AbstractIndicatorNode(IndicatorEnum indicatorEnum) {
        this.indicatorEnum = indicatorEnum;
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
    


    public String getLabel() {
        return this.indicatorEnum.getLabel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.dataprofiler.core.model.nodes.indicator.IIndicatorNode#getIndicatorEnum()
     */
    public IndicatorEnum getIndicatorEnum() {
        return indicatorEnum;
    }

}
