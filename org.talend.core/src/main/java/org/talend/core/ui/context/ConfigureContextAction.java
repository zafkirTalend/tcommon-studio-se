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
package org.talend.core.ui.context;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.core.model.process.IContext;
import org.talend.core.ui.images.ECoreImage;
import org.talend.core.model.process.IContext;
import org.talend.core.ui.images.ECoreImage;

/**
 * An action that can config the contexts. <br/>
 * 
 */
public class ConfigureContextAction extends Action {

    private Shell shell;

    private IContextModelManager manager;

    @SuppressWarnings("restriction")
    public ConfigureContextAction(IContextModelManager modelManager, Shell shell) {
        super("Configure Contexts...");
        this.manager = modelManager;
        this.shell = shell;
        this.setImageDescriptor(ImageProvider.getImageDesc(ECoreImage.CONTEXT_ICON));
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        ContextSetConfigurationDialog dialog = new ContextSetConfigurationDialog(shell, manager.getContextManager());
        if (dialog.open() == IDialogConstants.OK_ID) {
            List<IContext> result = dialog.getResultContexts();
            manager.getContextManager().setListContext(result);
            Command command = new Command() {

                public void execute() {
                    manager.refresh();
                }
            };

            if (manager.getCommandStack() == null) {
                command.execute();
            } else {
                manager.getCommandStack().execute(command);
            }
        }
    }
}
