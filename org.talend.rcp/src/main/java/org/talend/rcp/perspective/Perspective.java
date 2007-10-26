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
package org.talend.rcp.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * DOC ccarbone class global comment. Detailed comment <br/>
 * 
 * $Id$
 * 
 */
public class Perspective implements IPerspectiveFactory {

    /* (non-Javadoc)
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
    public void createInitialLayout(IPageLayout layout) {
        String propertyId = "org.eclipse.ui.views.PropertySheet"; //$NON-NLS-1$
        String navigatorId = "org.eclipse.ui.views.ResourceNavigator"; //$NON-NLS-1$
        String outlineId = "org.eclipse.ui.views.ContentOutline"; //$NON-NLS-1$
        String codeId = "org.talend.designer.core.codeView"; //$NON-NLS-1$
        String repositoryId = "org.talend.repository.views.repository"; //$NON-NLS-1$
        String epicPerlDocId = "org.epic.perleditor.views.PerlDocView"; //$NON-NLS-1$
        // String epicExplainErrorsViewId = "org.epic.perleditor.views.ExplainErrorsView";
        // String epicBrowserViewId = "org.epic.core.views.browser.BrowserView";
        String epicRegExpViewId = "org.epic.regexp.views.RegExpView"; //$NON-NLS-1$
        String pdeErrorViewId = "org.eclipse.pde.runtime.LogView"; //$NON-NLS-1$
        String pdeTaskViewId = "org.eclipse.ui.views.TaskList"; //$NON-NLS-1$
        String runProcessViewId = "org.talend.designer.runprocess.ui.views.processview"; //$NON-NLS-1$
        String problemsViewId = "org.talend.designer.core.ui.views.ProblemsView"; //$NON-NLS-1$
        String modulesViewId = "org.talend.designer.codegen.perlmodule.ModulesView"; //$NON-NLS-1$
        String schedulerViewId = "org.talend.scheduler.views.Scheduler"; //$NON-NLS-1$
        String contextsViewId = "org.talend.designer.core.ui.views.ContextsView"; //$NON-NLS-1$
        String gefPaletteViewId = "org.eclipse.gef.ui.palette_view";
        String statsAndLogsViewId = "org.talend.designer.core.ui.views.statsandlogs.statsAndLogsView";

        // leftTopLayout
        IFolderLayout leftTopLayout = layout.createFolder("navigatorLayout", IPageLayout.LEFT, new Float(0.3), //$NON-NLS-1$
                IPageLayout.ID_EDITOR_AREA);
        leftTopLayout.addView(repositoryId);
        leftTopLayout.addView(navigatorId);

        // leftBottomLayout
        IFolderLayout leftBottomLayout = layout.createFolder("outlineCodeLayout", IPageLayout.BOTTOM, new Float(0.6), //$NON-NLS-1$
                repositoryId);
        leftBottomLayout.addView(outlineId);
        leftBottomLayout.addView(codeId);
        
        IFolderLayout rightTopLayout = layout.createFolder("paletteLayout", IPageLayout.RIGHT, new Float(0.8), //$NON-NLS-1$
                IPageLayout.ID_EDITOR_AREA);
        rightTopLayout.addView(gefPaletteViewId);
        
        // bottomLayout
        IFolderLayout bottomLayout = layout.createFolder("bottomLayout", IPageLayout.BOTTOM, new Float(0.6), //$NON-NLS-1$
                IPageLayout.ID_EDITOR_AREA);
        bottomLayout.addView(propertyId);
        bottomLayout.addView(epicPerlDocId);
        bottomLayout.addView(epicRegExpViewId);
        // bottomLayout.addView(epicExplainErrorsViewId);
        // bottomLayout.addView(epicBrowserViewId);
        // bottomLayout.addView(pdeErrorViewId);
        bottomLayout.addView(contextsViewId);
        bottomLayout.addView(pdeTaskViewId);
        bottomLayout.addView(runProcessViewId);
        bottomLayout.addView(problemsViewId);
        bottomLayout.addView(modulesViewId);
        bottomLayout.addView(schedulerViewId);
        bottomLayout.addView(statsAndLogsViewId);
        
        
        
        // rightTopLayout
        // IFolderLayout rightTopLayout = layout.createFolder("rightLayout", IPageLayout.RIGHT, new Float(0.7),
        // IPageLayout.ID_EDITOR_AREA);
        // rightTopLayout.addView(epicPerlDocId);
        // rightTopLayout.addView(epicRegExpViewId);
        // rightTopLayout.addView(epicExplainErrorsViewId);
        // rightLayout.addView(epicBrowserViewId);

    }
}
