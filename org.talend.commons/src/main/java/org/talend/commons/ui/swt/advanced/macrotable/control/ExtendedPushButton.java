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
package org.talend.commons.ui.swt.advanced.macrotable.control;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.talend.commons.ui.swt.extended.macrotable.AbstractExtendedControlViewer;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public abstract class ExtendedPushButton {

    private Button button;
    protected AbstractExtendedControlViewer extendedControlViewer;

    /**
     * DOC amaumont ExtendedTableButton constructor comment.
     * @param extendedViewer 
     */
    public ExtendedPushButton(Composite parent, AbstractExtendedControlViewer extendedViewer, String tooltip, Image image) {
        super();
        this.extendedControlViewer = extendedViewer;
        init(parent, tooltip, image);
    }

    /**
     * DOC amaumont Comment method "init".
     * @param image 
     * @param tooltip 
     * @param parent2 
     */
    private void init(Composite parent, String tooltip, Image image) {
        button = new Button(parent, SWT.PUSH);
        button.setToolTipText(tooltip);
        button.setImage(image);

        button.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                handleSelectionEvent(event);
            }

        });
                
        
    }

    
    /**
     * Getter for button.
     * @return the button
     */
    public Button getButton() {
        return this.button;
    }

    
    /**
     * Getter for extendedTableViewer.
     * @return the extendedTableViewer
     */
    public AbstractExtendedControlViewer getExtendedControlViewer() {
        return this.extendedControlViewer;
    }
    
    /**
     * DOC amaumont Comment method "executeCommand".
     * @param command
     */
    public void executeCommand(Command command) {
        extendedControlViewer.executeCommand(command);
    }

    protected void handleSelectionEvent(Event event) {
        beforeCommandExecution();
        executeCommand(getCommandToExecute());
        afterCommandExecution();
    }

    /**
     * DOC amaumont Comment method "getCommandToExecute".
     * @return
     */
    protected abstract Command getCommandToExecute();

    /**
     * DOC amaumont Comment method "afterCommandExecution".
     */
    protected void afterCommandExecution() {
        // override it if needed
    }

    /**
     * DOC amaumont Comment method "beforeCommandExecution".
     */
    protected void beforeCommandExecution() {
        // override it if needed
    }


    
}
