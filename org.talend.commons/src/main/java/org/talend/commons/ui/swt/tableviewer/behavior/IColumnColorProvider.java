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
package org.talend.commons.ui.swt.tableviewer.behavior;

import org.eclipse.swt.graphics.Color;

/**
 * <code>IColumnColorProvider</code> provide a method which return the background color and the foreground color for a
 * given bean of the table (element) and the current column. <br/>
 * 
 * $Id: IColumnImageProvider.java 1155 2006-12-21 09:51:10Z amaumont $
 * 
 */
public interface IColumnColorProvider {

    /**
     * Get the background color.
     * 
     * @param element bean of the <code>TableViewerCreator</code>
     * @return return the background color for the current given bean of the <code>TableViewerCreator</code> and the
     * current column
     */
    public Color getBackgroundColor(Object bean);

    /**
     * Get the foreground color.
     * 
     * @param element bean of the <code>TableViewerCreator</code>
     * @return return the foreground color for the current given bean of the <code>TableViewerCreator</code> and the
     * current column
     */
    public Color getForegroundColor(Object bean);

}
