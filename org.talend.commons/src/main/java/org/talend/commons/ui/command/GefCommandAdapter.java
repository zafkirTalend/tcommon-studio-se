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
package org.talend.commons.ui.command;

import org.eclipse.gef.commands.Command;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class GefCommandAdapter extends Command {

    private ICommonCommand command;

    /**
     * DOC amaumont GefCommandAdapter constructor comment.
     */
    public GefCommandAdapter(ICommonCommand command) {
        super();
        this.command = command;
    }

    /**
     * DOC amaumont GefCommandAdapter constructor comment.
     * @param arg0
     */
    public GefCommandAdapter(String arg0) {
        super(arg0);
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        return command.canExecute();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canUndo()
     */
    @Override
    public boolean canUndo() {
        return command.canUndo();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#chain(org.eclipse.gef.commands.Command)
     */
    @Override
    public Command chain(Command arg0) {
        return super.chain(arg0);
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#dispose()
     */
    @Override
    public void dispose() {
        command.dispose();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        command.execute();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#getDebugLabel()
     */
    @Override
    public String getDebugLabel() {
        return super.getDebugLabel();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#getLabel()
     */
    @Override
    public String getLabel() {
        return command.getLabel();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        command.redo();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#setDebugLabel(java.lang.String)
     */
    @Override
    public void setDebugLabel(String arg0) {
        super.setDebugLabel(arg0);
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#setLabel(java.lang.String)
     */
    @Override
    public void setLabel(String label) {
        command.setLabel(label);
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        command.undo();
    }

    
    
}
