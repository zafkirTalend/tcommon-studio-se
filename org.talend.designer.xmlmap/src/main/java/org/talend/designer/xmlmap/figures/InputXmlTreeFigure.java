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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.talend.designer.xmlmap.figures.layout.EqualWidthLayout;
import org.talend.designer.xmlmap.model.emf.xmlmap.InputXmlTree;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputXmlTree;

/**
 * wchen class global comment. Detailled comment
 */
public class InputXmlTreeFigure extends GenericFigure {

    protected Figure columnContainer;

    protected Object xmlTree;

    protected InputXmlTree inputXmlTree;

    protected OutputXmlTree outputXmlTree;

    protected Figure tableColumnstitle;

    public InputXmlTreeFigure(Object xmlTree) {
        this.xmlTree = xmlTree;
        if (xmlTree instanceof InputXmlTree) {
            this.inputXmlTree = (InputXmlTree) xmlTree;
        }
        if (xmlTree instanceof OutputXmlTree) {
            this.outputXmlTree = (OutputXmlTree) xmlTree;
        }
        createContents();
    }

    protected String getModelName() {
        return inputXmlTree.getName();
    }

    protected void createContents() {
        setLayoutManager(new ToolbarLayout());
        this.setBorder(new LineBorder());
        Label tableName = new Label();
        tableName.setBorder(new LineBorder(ColorConstants.black));
        tableName.setText(getModelName());
        Font erFont = new Font(null, "Arial", 9, SWT.BOLD); //$NON-NLS-1$
        tableName.setFont(erFont);
        tableName.setOpaque(true);
        tableName.setBackgroundColor(ColorConstants.yellow);
        this.add(tableName);

        tableColumnstitle = new ColumnTitleFigure();

        Font cFont = new Font(null, "Arial", 10, SWT.BOLD);
        if (outputXmlTree != null && inputXmlTree == null) {
            ((ColumnTitleFigure) tableColumnstitle).getColumn1().setText("Expression");
            Label columnLabel = new Label();
            columnLabel.setText("Column");
            columnLabel.setBorder(new LineBorder());
            columnLabel.setFont(cFont);
            tableColumnstitle.add(columnLabel);

        }
        // Label NodeStatus = new Label();
        // NodeStatus.setText("Node status");
        // NodeStatus.setBorder(new LineBorder());
        // NodeStatus.setFont(cFont);
        // tableColumnstitle.add(NodeStatus);

        this.add(tableColumnstitle);
        // ScrollPane scrollPane = new ScrollPane();
        columnContainer = new Figure();
        ToolbarLayout layout = new ToolbarLayout();
        layout.setStretchMinorAxis(true);
        layout.setVertical(true);
        columnContainer.setLayoutManager(layout);
        columnContainer.setOpaque(true);
        columnContainer.setBackgroundColor(ColorConstants.white);
        // scrollPane.getViewport().setContents(columnContainer);
        // this.add(scrollPane);
        this.add(columnContainer);
    }

    public IFigure getColumnContainer() {
        return this.columnContainer;
    }

    class ColumnTitleFigure extends Figure {

        private Label column1;

        public ColumnTitleFigure() {
            column1 = new Label();
            column1.setText("Column");
            column1.setBorder(new LineBorder());
            Font cFont = new Font(null, "Arial", 10, SWT.BOLD);
            column1.setFont(cFont);
            this.add(column1);
            this.setLayoutManager(new EqualWidthLayout());
        }

        public Label getColumn1() {
            return this.column1;
        }

    }
}
