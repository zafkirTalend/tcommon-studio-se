// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.commons.ui.swt.advanced.dataeditor;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.advanced.dataeditor.control.ExtendedPushButton;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractExtendedToolbar {

    protected Composite toolbar;

    protected AbstractExtendedTableViewer extendedTableViewer;

    private Composite parentComposite;

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
        updateEnabledStateOfButtons();
    }

    /**
     * DOC amaumont Comment method "initLayout".
     */
    protected void initMainComponent(Composite parent, int style) {
        this.parentComposite = parent;
        toolbar = new Composite(parent, style);
        toolbar.setBackground(parent.getBackground());
        toolbar.setLayout(new RowLayout(SWT.HORIZONTAL));
    }

    /**
     * DOC amaumont Comment method "createComponents".
     */
    protected abstract void createComponents(Composite parent);

    /**
     * Getter for parentComposite.
     * 
     * @return the parentComposite
     */
    public Composite getParentComposite() {
        return this.parentComposite;
    }

    /**
     * Getter for extendedTableViewer.
     * 
     * @return the extendedTableViewer
     */
    public AbstractExtendedTableViewer getExtendedTableViewer() {
        return this.extendedTableViewer;
    }

    /**
     * DOC amaumont Comment method "updateEnabledStateOfButtons".
     */
    public abstract void updateEnabledStateOfButtons();

    public abstract List<ExtendedPushButton> getButtons();

}
