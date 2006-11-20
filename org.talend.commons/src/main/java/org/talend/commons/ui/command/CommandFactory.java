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
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CommandFactory {

    /**
     * DOC amaumont CommandFactory constructor comment.
     */
    public CommandFactory() {
        super();
        // TODO Auto-generated constructor stub
    }

    // void init() {
    // // ICommandService commandService = (ICommandService) getSite().getService(
    // // ICommandService.class);
    // IWorkbench workbench = PlatformUI.getWorkbench();
    // ICommandService commandService = (ICommandService)workbench.getAdapter(ICommandService.class);
    // IContextService contextSupport = (IContextService)workbench.getAdapter(IContextService.class);
    // IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getAdapter(IHandlerService.class);
    //    
    // Command showView = commandService.getCommand("org.eclipse.ui.views.showView");
    // IParameter viewIdParm = showView.getParameter("org.eclipse.ui.views.showView.viewId");
    //
    // // the viewId parameter provides a list of valid values ... if you
    // // knew the id of the problem view, you could skip this step.
    // // This method is supposed to be used in places like the keys
    // // preference page, to allow the user to select values
    // IParameterValues parmValues = viewIdParm.getValues();
    // String viewId = null;
    // Iterator i = parmValues.getParameterValues().values().iterator();
    // while (i.hasNext()) {
    // String id = (String) i.next();
    // if (id.indexOf("ProblemView") != -1) {
    // viewId = id;
    // break;
    // }
    // }
    //
    // Parameterization parm = new Parameterization(viewIdParm, viewId);
    // ParameterizedCommand parmCommand = new ParameterizedCommand(
    // showView, new Parameterization[] { parm });
    //
    // handlerService.executeCommand(parmCommand, null);
    //   
    // }
}
