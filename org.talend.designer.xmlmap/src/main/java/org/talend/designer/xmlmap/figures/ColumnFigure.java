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

import org.eclipse.draw2d.Figure;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputTreeNode;

/**
 * wchen class global comment. Detailled comment
 */
public abstract class ColumnFigure extends Figure {

    protected OutputTreeNode treeNode;

    public ColumnFigure(OutputTreeNode treeNode) {
        this.treeNode = treeNode;
        org.eclipse.draw2d.FlowLayout myGenLayoutManager = new org.eclipse.draw2d.FlowLayout();
        myGenLayoutManager.setStretchMinorAxis(false);
        myGenLayoutManager.setMinorAlignment(org.eclipse.draw2d.FlowLayout.ALIGN_CENTER);
        myGenLayoutManager.setMajorAlignment(org.eclipse.draw2d.FlowLayout.ALIGN_LEFTTOP);
        myGenLayoutManager.setMajorSpacing(5);
        myGenLayoutManager.setMinorSpacing(5);
        myGenLayoutManager.setHorizontal(true);
        createContent();
    }

    protected abstract void createContent();
}
