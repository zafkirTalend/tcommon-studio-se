package org.talend.designer.xmlmap.figures.layout;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.talend.commons.ui.swt.geftree.figure.TreeBranch;
import org.talend.commons.ui.swt.geftree.layout.TreeHangingLayout;
import org.talend.designer.xmlmap.figures.XmlTreeBranch;

public class RowTreeHangingLayout extends TreeHangingLayout {

    public RowTreeHangingLayout(XmlTreeBranch branch) {
        super(branch);
    }

    @Override
    public void paintRows(Graphics g) {
        drawRows(g, getBranch());

    }

    private int drawRows(Graphics g, TreeBranch rootBranch) {
        g.setLineStyle(SWT.LINE_DOT);
        g.setForegroundColor(generateColor());

        IFigure startFig = rootBranch.getStartFigure();
        IFigure contents = rootBranch.getContentsPane();

        // if (getTransposer().isEnabled()) {
        // int x = startFig.getBounds().right();
        // int y = startFig.getBounds().y + gap;
        // List children = contents.getChildren();
        // if (children.size() == 0)
        // return;
        // int right = x;
        // for (int i = 0; i < children.size(); i++) {
        // TreeBranch treeBranch = (TreeBranch) children.get(i);
        // Rectangle childStartBounds = treeBranch.getStartFigure().getBounds();
        //
        // Point pt = childStartBounds.getTop();
        // g.drawLine(pt.x, y, pt.x, pt.y);
        // right = Math.max(right, pt.x);
        // }
        //
        // } else {
        int currentBottom = startFig.getBounds().bottom();
        List children = contents.getChildren();
        if (children.size() == 0)
            return currentBottom;
        for (int i = 0; i < children.size(); i++) {
            XmlTreeBranch child = (XmlTreeBranch) children.get(i);
            Rectangle childBounds = child.getStartFigure().getBounds();
            // Point pt = childStartBounds.getLeft();

            int childStart = childBounds.y;

            int y2 = currentBottom + (childStart - currentBottom) / 2;
            if (rootBranch.getRoot() != null) {
                int x2 = rootBranch.getRoot().getBounds().x;
                g.drawLine(x2, y2, rootBranch.getRoot().getBounds().right(), y2);
            }
            int lastBottom = drawRows(g, child);
            currentBottom = childBounds.bottom();
        }

        return currentBottom;

        // }

    }

}
