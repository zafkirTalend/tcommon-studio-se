// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.ui.provider;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewerSorter;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.talend.repository.ui.views.IRepositoryView;
import org.talend.repository.viewer.filter.PerspectiveFilterActionProvider;
import org.talend.repository.viewer.filter.PerspectiveFilterHelper;
import org.talend.repository.viewer.ui.viewer.CheckboxRepoCommonViewer;
import org.talend.repository.viewer.ui.viewer.RepoCommonViewer;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class RepoCommonViewerProvider extends AbstractViewerProvider {

    public static final RepoCommonViewerProvider NORMAL = new RepoCommonViewerProvider();

    public static final RepoCommonViewerProvider CHECKBOX = new RepoCommonViewerProvider() {

        @Override
        protected TreeViewer createTreeViewer(Composite parent, int style) {
            return new CheckboxRepoCommonViewer(getViewId(), parent, style);
        }

    };

    @Override
    public TreeViewer createViewer(Composite parent) {
        TreeViewer treeViewer = super.createViewer(parent);
        doFilterForCommonViewer(treeViewer);

        // Maybe, no need filter in dialog, because it's not for view
        // if (treeViewer instanceof IRepoNavigatorContentService) {
        //
        // INavigatorContentService navigatorContentService = ((IRepoNavigatorContentService) treeViewer)
        // .getNavigatorContentService();
        // INavigatorFilterService filterService = navigatorContentService.getFilterService();
        // ViewerFilter[] visibleFilters = filterService.getVisibleFilters(true);
        // for (int i = 0; i < visibleFilters.length; i++) {
        // treeViewer.addFilter(visibleFilters[i]);
        // }
        //
        // }

        return treeViewer;
    }

    protected TreeViewer createTreeViewer(final Composite parent, final int style) {
        return new RepoCommonViewer(getViewId(), parent, style);
    }

    protected void checkSorter(TreeViewer treeViewer) {
        treeViewer.setSorter(new CommonViewerSorter());
    }

    protected void doFilterForCommonViewer(TreeViewer treeViewer) {
        if (treeViewer instanceof IRepoNavigatorContentService) {
            INavigatorContentService navigatorContentService = ((IRepoNavigatorContentService) treeViewer)
                    .getNavigatorContentService();

            IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (activeWorkbenchWindow != null) {
                IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                if (activePage != null) {
                    PerspectiveFilterHelper helper = new PerspectiveFilterHelper();
                    helper.setTreeViewer(treeViewer);
                    helper.setNavigatorContentService(navigatorContentService);
                    helper.setActionProviderId(PerspectiveFilterActionProvider.ID);

                    String perspectiveId = activePage.getPerspective().getId();
                    helper.doFiltering(perspectiveId);

                }
            }
        }
    }

    protected String getViewId() {
        return IRepositoryView.VIEW_ID;
    }

}
