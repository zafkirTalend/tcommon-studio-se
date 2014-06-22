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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.internal.navigator.NavigatorActivationService;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.actions.MoveObjectAction;
import org.talend.repository.ui.views.RepositoryDropAdapter;
import org.talend.repository.viewer.filter.RepositoryNodeFilterHelper;

/**
 * DOC sgandon class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class RepoViewCommonViewer extends CommonViewer {

    private Map<String, Boolean> expanded = new HashMap<String, Boolean>();

    private final RepoViewCommonNavigator repViewCommonNavigator;

    private Listener dragDetectListener;

    /**
     * Getter for repViewCommonNavigator.
     * 
     * @return the repViewCommonNavigator
     */
    public RepoViewCommonNavigator getRepViewCommonNavigator() {
        return this.repViewCommonNavigator;
    }

    public RepoViewCommonViewer(RepoViewCommonNavigator repViewCommonNavigator, String aViewerId, Composite parent, int style) {
        super(aViewerId, parent, style);
        this.repViewCommonNavigator = repViewCommonNavigator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.CommonViewer#init()
     */
    @Override
    protected void init() {
        super.init();
        // updateNavigatorContentState();
    }

    @SuppressWarnings("restriction")
    private void updateNavigatorContentState() {
        INavigatorContentService contentService = getNavigatorContentService();
        String[] visibleExtensionIds = contentService.getVisibleExtensionIds();
        List<String> needRemovedExtensionIds = RepositoryNodeFilterHelper.getExtensionIdsNeedRemove(visibleExtensionIds);
        if (contentService.getActivationService() instanceof NavigatorActivationService) {
            NavigatorActivationService activationService = (NavigatorActivationService) contentService.getActivationService();
            activationService.setActive(needRemovedExtensionIds.toArray(new String[needRemovedExtensionIds.size()]), false);
            activationService.persistExtensionActivations();
        }
    }

    private RepositoryNode getRepositoryNode(Item node) {
        Object data = node.getData();
        RepositoryNode repositoryNode = null;
        if (data instanceof RepositoryNode) {
            repositoryNode = (RepositoryNode) data;
        }
        return repositoryNode;
    }

    private boolean idIsValid(IRepositoryNode repositoryNode) {
        String id = repositoryNode.getId();
        return id != null && !RepositoryNode.NO_ID.equals(id);
    }

    @Override
    protected void initDragAndDrop() {
        int ops = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
        Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };

        this.addDragSupport(ops, transfers, new DragSourceAdapter() {

            private static final long FFFFFFFFL = 0xFFFFFFFFL;

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.dnd.DragSourceAdapter#dragSetData(org.eclipse.swt.dnd.DragSourceEvent)
             */
            @Override
            public void dragSetData(DragSourceEvent event) {
                repViewCommonNavigator.setNoNeedUpdate(true);
                event.data = LocalSelectionTransfer.getTransfer().getSelection();
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.dnd.DragSourceAdapter#dragStart(org.eclipse.swt.dnd.DragSourceEvent)
             */
            @Override
            public void dragStart(DragSourceEvent event) {
                ISelection selection = RepoViewCommonViewer.this.getSelection();

                for (Object obj : ((StructuredSelection) selection).toArray()) {
                    RepositoryNode sourceNode = (RepositoryNode) obj;

                    // As i don't know how to get event operation i test on MoveOperation
                    event.doit = MoveObjectAction.getInstance().validateAction(sourceNode, null, true);
                }

                LocalSelectionTransfer.getTransfer().setSelection(selection);
                LocalSelectionTransfer.getTransfer().setSelectionSetTime(event.time & FFFFFFFFL);
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.dnd.DragSourceAdapter#dragFinished(org.eclipse.swt.dnd.DragSourceEvent)
             */
            @Override
            public void dragFinished(DragSourceEvent event) {
                repViewCommonNavigator.dragFinished();
            }
        });
        RepositoryDropAdapter adapter = new RepositoryDropAdapter(this);
        adapter.setFeedbackEnabled(false);
        this.addDropSupport(ops | DND.DROP_DEFAULT, transfers, adapter);
        dragDetectListener = new Listener() {

            @Override
            public void handleEvent(Event event) {
                // dragDetected = true;
            }
        };
        this.getControl().addListener(SWT.DragDetect, dragDetectListener);
    }

    @Override
    public void refresh(Object element) {
        if (repViewCommonNavigator != null && repViewCommonNavigator.isNoNeedUpdate()) {
            return;
        } else {
            super.refresh(element);
        }

    }

}
