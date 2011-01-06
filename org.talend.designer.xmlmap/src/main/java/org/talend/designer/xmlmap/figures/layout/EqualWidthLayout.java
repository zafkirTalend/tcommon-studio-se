// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.xmlmap.figures.layout;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class EqualWidthLayout extends ToolbarLayout {

    @Override
    public void layout(IFigure parent) {
        List children = parent.getChildren();
        int numChildren = children.size();
        Rectangle clientArea = transposer.t(parent.getClientArea());
        int clientx = clientArea.x;
        int clienty = clientArea.y;
        int clientWidth = clientArea.width;
        for (int i = 0; i < numChildren; i++) {
            IFigure child = (IFigure) children.get(i);
            Rectangle newBounds = new Rectangle(clientx, clienty, -1, clientArea.height);
            int devideWidth = (clientWidth - (numChildren - 1) * spacing) / numChildren;
            newBounds.width = devideWidth;
            child.setBounds(transposer.t(newBounds));
            clientx += newBounds.width + spacing;
        }
    }
}
