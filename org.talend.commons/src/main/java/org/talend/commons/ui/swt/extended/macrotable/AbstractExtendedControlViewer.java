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
package org.talend.commons.ui.swt.extended.macrotable;

import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.command.ICommonCommand;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public abstract class AbstractExtendedControlViewer {

    private AbstractExtendedControlModel extendedControlModel;

    private Composite parentComposite;
    
    /**
     * DOC amaumont AbstractExtendedControlViewer constructor comment.
     */
    public AbstractExtendedControlViewer(Composite parentComposite) {
        super();
        this.parentComposite = parentComposite;
    }


    /**
     * DOC amaumont AbstractExtendedControlViewer constructor comment.
     * @param extendedControl
     * @param parentComposite
     */
    public AbstractExtendedControlViewer(AbstractExtendedControlModel extendedControl, Composite parentComposite) {
        super();
        this.extendedControlModel = extendedControl;
        this.parentComposite = parentComposite;
    }


    /**
     * DOC amaumont Comment method "executeCommand".
     * @param command
     */
    public void executeCommand(ICommonCommand command) {
//        IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//        Object adapter = activeEditor.getAdapter(CommandStack.class);
//        CommandStack commandStack = (CommandStack) adapter;
//        commandStack.execute(command);
        
        command.execute();
    }


    
    
    /**
     * Getter for extendedControl.
     * @return the extendedControl
     */
    public AbstractExtendedControlModel getExtendedControlModel() {
        return this.extendedControlModel;
    }


    
    /**
     * Sets the extendedControl.
     * @param extendedControl the extendedControl to set
     */
    public void setExtendedControlModel(AbstractExtendedControlModel extendedControl) {
        AbstractExtendedControlModel previousModel = this.extendedControlModel;
        this.extendedControlModel = extendedControl;
        modelChanged(previousModel, extendedControl);
    }


    
    /**
     * DOC amaumont Comment method "modelChanged".
     */
    protected abstract void modelChanged(AbstractExtendedControlModel previousModel, AbstractExtendedControlModel newModel);


    /**
     * Getter for parentComposite.
     * @return the parentComposite
     */
    public Composite getParentComposite() {
        return this.parentComposite;
    }
    
    
    

}
