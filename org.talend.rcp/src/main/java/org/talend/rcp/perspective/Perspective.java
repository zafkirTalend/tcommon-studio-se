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
package org.talend.rcp.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * DOC ccarbone class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class Perspective implements IPerspectiveFactory {

    public void createInitialLayout(IPageLayout layout) {
        String propertyId = "org.eclipse.ui.views.PropertySheet";
        String navigatorId = "org.eclipse.ui.views.ResourceNavigator";
        String outlineId = "org.eclipse.ui.views.ContentOutline";
        String codeId = "org.talend.designer.core.codeView";
        String repositoryId = "org.talend.repository.views.repository";
        String epicPerlDocId = "org.epic.perleditor.views.PerlDocView";
        // String epicExplainErrorsViewId = "org.epic.perleditor.views.ExplainErrorsView";
        // String epicBrowserViewId = "org.epic.core.views.browser.BrowserView";
        String epicRegExpViewId = "org.epic.regexp.views.RegExpView";
        String pdeErrorViewId = "org.eclipse.pde.runtime.LogView";
        String pdeTaskViewId = "org.eclipse.ui.views.TaskList";
        String runProcessViewId = "org.talend.designer.runprocess.ui.views.processview";
        String problemsViewId = "org.talend.designer.core.ui.views.ProblemsView";
        String modulesViewId = "org.talend.designer.codegen.perlmodule.ModulesView";
        String schedulerViewId = "org.talend.scheduler.views.Scheduler";

        // leftTopLayout
        IFolderLayout leftTopLayout = layout.createFolder("navigatorLayout", IPageLayout.LEFT, new Float(0.3),
                IPageLayout.ID_EDITOR_AREA);
        leftTopLayout.addView(repositoryId);
        leftTopLayout.addView(navigatorId);

        // leftBottomLayout
        IFolderLayout leftBottomLayout = layout.createFolder("outlineCodeLayout", IPageLayout.BOTTOM, new Float(0.6),
                repositoryId);
        leftBottomLayout.addView(outlineId);
        leftBottomLayout.addView(codeId);

        // bottomLayout
        IFolderLayout bottomLayout = layout.createFolder("bottomLayout", IPageLayout.BOTTOM, new Float(0.6),
                IPageLayout.ID_EDITOR_AREA);
        bottomLayout.addView(propertyId);
        bottomLayout.addView(epicPerlDocId);
        bottomLayout.addView(epicRegExpViewId);
        // bottomLayout.addView(epicExplainErrorsViewId);
        // bottomLayout.addView(epicBrowserViewId);
        // bottomLayout.addView(pdeErrorViewId);
        bottomLayout.addView(pdeTaskViewId);
        bottomLayout.addView(runProcessViewId);
        bottomLayout.addView(problemsViewId);
        bottomLayout.addView(modulesViewId);
        bottomLayout.addView(schedulerViewId);
        // rightTopLayout
        // IFolderLayout rightTopLayout = layout.createFolder("rightLayout", IPageLayout.RIGHT, new Float(0.7),
        // IPageLayout.ID_EDITOR_AREA);
        // rightTopLayout.addView(epicPerlDocId);
        // rightTopLayout.addView(epicRegExpViewId);
        // rightTopLayout.addView(epicExplainErrorsViewId);
        // rightLayout.addView(epicBrowserViewId);

    }
}
