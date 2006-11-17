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

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Composite;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class AbstractExtendedControlViewer {

    protected AbstractExtendedControlModel extendedControl;

    protected Composite parentComposite;


    /**
     * DOC amaumont AbstractExtendedControlViewer constructor comment.
     * @param extendedControl
     * @param parentComposite
     */
    public AbstractExtendedControlViewer(AbstractExtendedControlModel extendedControl, Composite parentComposite) {
        super();
        this.extendedControl = extendedControl;
        this.parentComposite = parentComposite;
    }


    /**
     * DOC amaumont Comment method "executeCommand".
     * @param command
     */
    public void executeCommand(Command command) {
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
        return this.extendedControl;
    }

    
    
}
