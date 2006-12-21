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
package org.talend.commons.ui.swt.advanced.dataeditor.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.talend.commons.ui.utils.SimpleClipboard;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ExtendedTableCopyCommand extends Command implements IExtendedTableCommand {

    private List beansToCopy;

    public static final String LABEL = "Copy one or more table entries in the internal clipboard";

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    @SuppressWarnings("unchecked")
    public ExtendedTableCopyCommand(List beansToCopy) {
        super(LABEL);
        this.beansToCopy = new ArrayList(beansToCopy);
    }

    /**
     * DOC amaumont ExtendedTableAddCommand constructor comment.
     */
    @SuppressWarnings("unchecked")
    public ExtendedTableCopyCommand(Object beanToCopy) {
        super(LABEL);
        beansToCopy = new ArrayList(1);
        beansToCopy.add(beanToCopy);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        SimpleClipboard.getInstance().setData(beansToCopy);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.command.CommonCommand#canUndo()
     */
    @Override
    public boolean canUndo() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.command.CommonCommand#redo()
     */
    @Override
    public void redo() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.command.CommonCommand#undo()
     */
    @Override
    public void undo() {
    }

}
