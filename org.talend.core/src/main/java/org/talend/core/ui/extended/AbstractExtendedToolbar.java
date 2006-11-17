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
package org.talend.core.ui.extended;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedTableViewer;
import org.talend.core.ui.extended.button.AddPushButton;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public abstract class AbstractExtendedToolbar {
    
    protected Composite toolbar;

    protected AbstractExtendedTableViewer extendedTableViewer;


    /**
     * DOC amaumont MatadataToolbarEditor constructor comment.
     * 
     * @param parent
     * @param style
     * @param metadataEditorView
     */
    public AbstractExtendedToolbar(Composite parent, int style, AbstractExtendedTableViewer extendedTableViewer) {
        super();
        this.extendedTableViewer = extendedTableViewer;
        initMainComponent(parent, style);
        createComponents(toolbar);
    }

    /**
     * DOC amaumont Comment method "initLayout".
     */
    protected void initMainComponent(Composite parent, int style) {
        toolbar = new Composite(parent, style);
        toolbar.setLayout(new RowLayout(SWT.HORIZONTAL));
    }

    /**
     * DOC amaumont Comment method "createComponents".
     */
    protected abstract void createComponents(Composite parent);

    public abstract void setReadOnly(boolean b);
    
    /**
     * DOC amaumont Comment method "getAddPushButton".
     */
    protected abstract AddPushButton createAddPushButton();
    

    
    
}
