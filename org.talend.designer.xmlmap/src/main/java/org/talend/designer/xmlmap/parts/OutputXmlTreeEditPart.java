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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.designer.xmlmap.figures.OutputXmlTreeFigure;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputXmlTree;

/**
 * wchen class global comment. Detailled comment
 */
public class OutputXmlTreeEditPart extends BaseEditPart {

    OutputXmlTreeFigure figure;

    @Override
    protected IFigure createFigure() {
        figure = new OutputXmlTreeFigure((OutputXmlTree) getModel());
        return figure;
    }

    @Override
    public IFigure getContentPane() {
        return ((OutputXmlTreeFigure) getFigure()).getColumnContainer();
    }

    @Override
    protected List getModelChildren() {
        return ((OutputXmlTree) getModel()).getNodes();
    }

    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        try {
            super.addChildVisual(childEditPart, index);
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            int childHeight = child.getBounds().height;
            IFigure parent = ((GraphicalEditPart) childEditPart.getParent()).getFigure();
            int parentHeight = parent.getBounds().height;
            int parentWidth = parent.getBounds().width;
            int parentX = parent.getBounds().x;
            int parentY = parent.getBounds().y;
            parentHeight += childHeight;
            Rectangle newBounds = new Rectangle(parentX, parentY, parentWidth, parentHeight);
            parent.setBounds(newBounds);
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }
}
