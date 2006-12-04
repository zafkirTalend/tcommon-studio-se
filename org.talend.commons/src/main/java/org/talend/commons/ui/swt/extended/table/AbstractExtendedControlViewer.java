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
package org.talend.commons.ui.swt.extended.table;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.widgets.Composite;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractExtendedControlViewer {

    private AbstractExtendedControlModel extendedControlModel;

    private Composite parentComposite;

    private CommandStack commandStack;

    /**
     * DOC amaumont AbstractExtendedControlViewer constructor comment.
     */
    public AbstractExtendedControlViewer(Composite parentComposite) {
        super();
        this.parentComposite = parentComposite;
    }

    /**
     * DOC amaumont AbstractExtendedControlViewer constructor comment.
     * 
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
     * 
     * @param command
     */
    public void executeCommand(Command command) {
        if (this.commandStack != null) {
            this.commandStack.execute(command);
        } else {
            command.execute();
        }

    }

    /**
     * Getter for extendedControl.
     * 
     * @return the extendedControl
     */
    public AbstractExtendedControlModel getExtendedControlModel() {
        return this.extendedControlModel;
    }

    /**
     * Sets the extendedControl.
     * 
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
     * 
     * @return the parentComposite
     */
    public Composite getParentComposite() {
        return this.parentComposite;
    }

    
    /**
     * Getter for commandStackAdapter.
     * @return the commandStackAdapter
     */
    public CommandStack getCommandStack() {
        return this.commandStack;
    }

    
    /**
     * Sets the commandStackAdapter.
     * @param commandStack the commandStackAdapter to set
     */
    public void setCommandStack(CommandStack commandStack) {
        this.commandStack = commandStack;
    }

    
    
}
