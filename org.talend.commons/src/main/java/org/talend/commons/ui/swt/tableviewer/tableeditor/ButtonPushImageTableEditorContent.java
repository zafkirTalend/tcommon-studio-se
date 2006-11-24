// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.ui.swt.tableviewer.tableeditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class ButtonPushImageTableEditorContent extends TableEditorContent {

    private Image image;

    private Image imageHover;

    /**
     * DOC amaumont ButtonImageTableEditorContent constructor comment.
     */
    public ButtonPushImageTableEditorContent() {
        super();
    }

    public Control initialize(Table table, TableEditor tableEditor, final TableViewerCreatorColumn currentColumn,
            final Object currentRowObject, Object currentCellValue) {

        final Label label = new Label(table, SWT.NONE);
        label.setBackground(label.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        label.setImage(image);
        Point size = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        Rectangle bounds = label.getBounds();
        bounds.y -=5;
        label.setBounds(bounds);
        tableEditor.grabHorizontal = true;
        tableEditor.grabVertical = true;
        tableEditor.minimumHeight = size.y;
        tableEditor.minimumWidth = size.x;
        label.addMouseTrackListener(new MouseTrackListener() {

            public void mouseEnter(MouseEvent e) {

            }

            public void mouseExit(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            public void mouseHover(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        label.addMouseListener(new MouseListener() {

            private boolean hooked;

            public void mouseDoubleClick(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            public void mouseDown(MouseEvent e) {
                hooked = true;
            }

            public void mouseUp(MouseEvent e) {
                Rectangle boundsLabel = label.getBounds();
                Point mousePosition = new Point(e.x, e.y);

                if (hooked && mousePosition.x > 0 && mousePosition.y > 0 && mousePosition.x < boundsLabel.width
                        && mousePosition.y < boundsLabel.height) {
                    hooked = false;
                    selectionEvent(currentColumn, currentRowObject);
                }
            }

        });
        return label;
    }

    /**
     * DOC amaumont Comment method "selectionEvent".
     * 
     * @param bean
     * @param currentRowObject
     */
    protected abstract void selectionEvent(TableViewerCreatorColumn column, Object bean);

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImageHover(Image imageHover) {
        throw new UnsupportedOperationException();
//        this.imageHover = imageHover;
    }

    
    /**
     * Getter for image.
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }

    
    /**
     * Getter for imageHover.
     * @return the imageHover
     */
    public Image getImageHover() {
        return this.imageHover;
    }

    
}
