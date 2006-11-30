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


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class CommonCommand implements ICommonCommand {

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.ICommonCommand#canExecute()
     */
    public boolean canExecute() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.ICommonCommand#canUndo()
     */
    public boolean canUndo() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.ICommonCommand#dispose()
     */
    public void dispose() {
        
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.ICommonCommand#execute()
     */
    public void execute() {
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.ICommonCommand#getLabel()
     */
    public String getLabel() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.ICommonCommand#redo()
     */
    public void redo() {
        
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.ICommonCommand#setLabel(java.lang.String)
     */
    public void setLabel(String label) {
        
    }

    /* (non-Javadoc)
     * @see org.talend.commons.ui.command.ICommonCommand#undo()
     */
    public void undo() {
        
    }

}
