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
package org.talend.repository.viewer.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.navigator.CommonNavigator;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.RepositoryManager;
import org.talend.repository.model.ProjectRepositoryNode;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class RepositoryRefreshActionProvider extends AbstractRepositoryActionProvider {

    public RepositoryRefreshActionProvider() {
        super();
    }

    @Override
    protected void fillToolBar(IToolBarManager toolBarManager) {
        super.fillToolBar(toolBarManager);

        final RepoRefreshAction refreshAction = new RepoRefreshAction();
        toolBarManager.add(refreshAction);

    }

    /**
     * 
     * DOC ggu RepoRefreshAction class global comment. Detailled comment
     */
    class RepoRefreshAction extends Action {

        public RepoRefreshAction() {
            super();

            this.setText("Refresh");
            this.setToolTipText(this.getText()); // use same
            this.setImageDescriptor(ImageProvider.getImageDesc(EImage.REFRESH_ICON));
            this.setActionDefinitionId("refresh"); //$NON-NLS-1$
        }

        public void run() {
            // FIXME maybe later,will remove this flag
            ProjectRepositoryNode.refProjectBool = true;

            final CommonNavigator commonNavigator = RepositoryRefreshActionProvider.this.getCommonNavigator();
            if (commonNavigator instanceof IRepositoryView) {
                ((IRepositoryView) commonNavigator).refreshView();
            } else {
                commonNavigator.getCommonViewer().refresh();
            }
            ProjectRepositoryNode.refProjectBool = false;
            // qli modified to fix the bug 6659.
            RepositoryManager.syncRoutineAndJoblet(ERepositoryObjectType.ROUTINES);
            RepositoryManager.syncRoutineAndJoblet(ERepositoryObjectType.JOBLET);
            RepositoryManager.syncUserComponents();

        }

    }
}
