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

import org.eclipse.gef.commands.CommandStack;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.IProcess;

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 */
public interface IContextModelManager {

    public IContextManager getContextManager();

    public IProcess getProcess();

    /**
     * refresh the contextComposite.
     */
    public void refresh();

    public CommandStack getCommandStack();

    public void onContextChangeDefault(IContextManager contextManager, IContext newDefault);

    public void onContextRenameParameter(IContextManager contextManager, String oldName, String newName);

    public void onContextModify(IContextManager contextManager, IContextParameter parameter);

    public void onContextAddParameter(IContextManager contextManager, IContextParameter parameter);

    public void onContextRemoveParameter(IContextManager contextManager, String paramName);

    public boolean isReadOnly();
}
