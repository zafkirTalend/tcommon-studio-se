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
package org.talend.repository.viewer.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.filters.UpdateActiveExtensionsOperation;
import org.eclipse.ui.internal.navigator.filters.UpdateActiveFiltersOperation;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonFilterDescriptor;
import org.eclipse.ui.navigator.INavigatorContentDescriptor;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.talend.core.model.repository.IRepositoryPrefConstants;
import org.talend.repository.RepositoryViewPlugin;

/**
 * DOC ggu class global comment. Detailled comment
 */
@SuppressWarnings("restriction")
public class RepositoryNodeFilterHelper {

    public static void filter(final ICommonActionExtensionSite commonActionSite, boolean activedFilter,
            boolean isPerspectiveFilter, boolean restoring) {

        processContentExtensions(commonActionSite, activedFilter, isPerspectiveFilter);
        processCommonFilters(commonActionSite, activedFilter);

    }

    private static void processContentExtensions(final ICommonActionExtensionSite commonActionSite, boolean activeFilter,
            boolean activedPerspectiveFilter) {
        final CommonViewer commonViewer = (CommonViewer) commonActionSite.getStructuredViewer();
        final INavigatorContentService contentService = commonActionSite.getContentService();
        final INavigatorContentDescriptor[] visibleExtensions = contentService.getVisibleExtensions();
        List<String> visibleIDsForPecpective = new ArrayList<String>();
        List<String> visibleIdsForActiveFilter = new ArrayList<String>();
        for (INavigatorContentDescriptor nd : visibleExtensions) {
            visibleIdsForActiveFilter.add(nd.getId());
        }

        if (activedPerspectiveFilter) {
            String perspectiveId = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId();
            PerspectiveFilterActionProvider pvFilter = new PerspectiveFilterActionProvider();
            pvFilter.getHelper().setTreeViewer(commonViewer);
            pvFilter.getHelper().setNavigatorContentService(contentService);
            pvFilter.getHelper().setActionProviderId(pvFilter.ID);
            String[] pvExtensions = pvFilter.getHelper().getExtensionIdsToActivate(perspectiveId);

            if (pvExtensions != null && pvExtensions.length > 0) {
                visibleIDsForPecpective = Arrays.asList(pvExtensions);
            }
            visibleIdsForActiveFilter.retainAll(visibleIDsForPecpective);
        }

        String[] filteredContents = RepositoryViewPlugin.getDefault()
                .getPreferenceValues(IRepositoryPrefConstants.FILTER_BY_NODE);
        List<String> filteredContentsList = new ArrayList<String>();
        if (filteredContents != null) {
            filteredContentsList = Arrays.asList(filteredContents);
        }

        List<String> checkedExtensions = new ArrayList<String>();
        for (String id : visibleIdsForActiveFilter) {
            if (!activedPerspectiveFilter || (activedPerspectiveFilter && !filteredContentsList.contains(id))) {
                checkedExtensions.add(id);
            }
        }

        String[] contentExtensionIdsToActivate = (String[]) checkedExtensions.toArray(new String[checkedExtensions.size()]);
        UpdateActiveExtensionsOperation updateExtensions = new UpdateActiveExtensionsOperation(commonViewer,
                contentExtensionIdsToActivate);
        updateExtensions.execute(null, null);
    }

    private static void processCommonFilters(final ICommonActionExtensionSite commonActionSite, boolean activedFilter) {
        final CommonViewer commonViewer = (CommonViewer) commonActionSite.getStructuredViewer();
        final ICommonFilterDescriptor[] visibleFilterDescriptors = commonActionSite.getContentService().getFilterService()
                .getVisibleFilterDescriptors();

        String[] filters = new String[visibleFilterDescriptors.length];

        for (int i = 0; i < visibleFilterDescriptors.length; i++) {
            final String id = visibleFilterDescriptors[i].getId();
            filters[i] = id;
        }
        UpdateActiveFiltersOperation updateFilters = new UpdateActiveFiltersOperation(commonViewer, filters);
        updateFilters.execute(null, null);
    }
}
