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
package org.talend.repository.navigator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.ui.internal.navigator.NavigatorPlugin;
import org.eclipse.ui.internal.navigator.NavigatorSafeRunnable;
import org.eclipse.ui.internal.navigator.extensions.INavigatorContentExtPtConstants;
import org.eclipse.ui.internal.navigator.extensions.NavigatorContentDescriptor;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.IExtensionActivationListener;
import org.eclipse.ui.navigator.INavigatorContentDescriptor;
import org.eclipse.ui.navigator.INavigatorContentExtension;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.talend.repository.viewer.content.EmptyTopNodeContentProvider;
import org.talend.repository.viewer.content.FolderListenerSingleTopContentProvider;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class RepoViewExtensionActivationListener implements IExtensionActivationListener {

    private final CommonNavigator navigator;

    public RepoViewExtensionActivationListener(CommonNavigator navigator) {
        super();
        this.navigator = navigator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.IExtensionActivationListener#onExtensionActivation(java.lang.String,
     * java.lang.String[], boolean)
     */
    @SuppressWarnings("restriction")
    @Override
    public void onExtensionActivation(final String viewerId, final String[] theNavigatorExtensionIds, final boolean isActive) {
        synchronized (this) {
            SafeRunner.run(new NavigatorSafeRunnable() {

                public void run() throws Exception {
                    if (viewerId == null || theNavigatorExtensionIds == null || theNavigatorExtensionIds.length <= 0) {
                        return;
                    }
                    if (navigator == null || navigator.getCommonViewer().getTree().isDisposed()) {
                        return;
                    }
                    if (isActive && viewerId.equals(navigator.getSite().getId())) {

                        Map<String, Boolean> oldActivationExtensions = getOldActivationExtensions();

                        // find out the new activations
                        List<String> newActivationIds = new ArrayList<String>();

                        for (String id : theNavigatorExtensionIds) {
                            if (oldActivationExtensions.containsKey(id)) {
                                Boolean b = oldActivationExtensions.get(id);
                                if (b != null && b.booleanValue()) {
                                    continue;
                                }
                            }
                            newActivationIds.add(id);
                        }

                        INavigatorContentService navigatorContentService = navigator.getNavigatorContentService();

                        for (String id : newActivationIds) {
                            INavigatorContentExtension contentExtension = navigatorContentService.getContentExtensionById(id);
                            if (contentExtension != null) {
                                try {
                                    boolean hasContentProvider = false;
                                    INavigatorContentDescriptor descriptor = contentExtension.getDescriptor();
                                    if (descriptor != null && descriptor instanceof NavigatorContentDescriptor) {
                                        IConfigurationElement configElement = ((NavigatorContentDescriptor) descriptor)
                                                .getConfigElement();
                                        if (configElement != null) {
                                            String contentProviderAttr = configElement
                                                    .getAttribute(INavigatorContentExtPtConstants.ATT_CONTENT_PROVIDER);
                                            if (contentProviderAttr != null) {
                                                hasContentProvider = true;
                                            }
                                        }
                                    }
                                    if (hasContentProvider) {
                                        ITreeContentProvider contentProvider = contentExtension.getContentProvider();
                                        if (contentProvider instanceof FolderListenerSingleTopContentProvider) {
                                            if (contentProvider instanceof EmptyTopNodeContentProvider) {
                                                continue; // not for empty top node. no need update
                                            }

                                            FolderListenerSingleTopContentProvider topProvider = (FolderListenerSingleTopContentProvider) contentProvider;
                                            if (topProvider != null) {
                                                topProvider.initRepositoryNode();
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    // ignore
                                }
                            }
                        }

                    }
                }
            });
        }

    }

    private static final String DELIM = ";"; //$NON-NLS-1$

    private static final char EQUALS = '=';

    private static final String ACTIVATED_EXTENSIONS = ".activatedExtensions"; //$NON-NLS-1$

    /**
     * @see NavigatorContentService.getPreferencesRoot()
     */
    static IEclipsePreferences getPreferencesRoot() {
        IEclipsePreferences root = (IEclipsePreferences) Platform.getPreferencesService().getRootNode().node(InstanceScope.SCOPE);
        return (IEclipsePreferences) root.node(NavigatorPlugin.PLUGIN_ID);
    }

    private Map<String, Boolean> getOldActivationExtensions() {
        Map<String, Boolean> oldActivatedExtensionsMap = new HashMap<String, Boolean>();

        IEclipsePreferences prefs = getPreferencesRoot();
        INavigatorContentService navigatorContentService = navigator.getNavigatorContentService();
        String activatedExtensionsString = prefs.get(navigatorContentService.getViewerId() + ACTIVATED_EXTENSIONS, null);

        if (activatedExtensionsString != null && activatedExtensionsString.length() > 0) {
            String[] contentExtensionIds = activatedExtensionsString.split(DELIM);

            String id = null;
            String booleanString = null;
            int indx = 0;
            for (int i = 0; i < contentExtensionIds.length; i++) {
                if ((indx = contentExtensionIds[i].indexOf(EQUALS)) > -1) {
                    // up to but not including the equals
                    id = contentExtensionIds[i].substring(0, indx);
                    booleanString = contentExtensionIds[i].substring(indx + 1, contentExtensionIds[i].length());
                    oldActivatedExtensionsMap.put(id, Boolean.valueOf(booleanString));
                }
            }
        }
        return oldActivatedExtensionsMap;
    }
}
