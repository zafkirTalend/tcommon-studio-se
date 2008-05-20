// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.perspective;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.sqlexplorer.EDriverName;
import net.sourceforge.sqlexplorer.ExplorerException;
import net.sourceforge.sqlexplorer.dbproduct.Alias;
import net.sourceforge.sqlexplorer.dbproduct.AliasManager;
import net.sourceforge.sqlexplorer.dbproduct.ManagedDriver;
import net.sourceforge.sqlexplorer.dbproduct.User;
import net.sourceforge.sqlexplorer.plugin.SQLExplorerPlugin;
import net.sourceforge.sqlexplorer.plugin.actions.OpenPasswordConnectDialogAction;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.talend.cwm.helper.DataProviderHelper;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.management.api.DqRepositoryViewService;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.cwm.softwaredeployment.TdProviderConnection;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.manager.DQStructureManager;
import org.talend.utils.sugars.TypedReturnCode;

/**
 * Changes the active perspective. <br/>
 * 
 * $Id: ChangePerspectiveAction.java 1774 2007-02-03 02:05:47 +0000 (Sat, 03 Feb 2007) bqian $
 * 
 */
public class ChangePerspectiveAction extends Action {

    public static final String SE_ID = "net.sourceforge.sqlexplorer.plugin.perspectives.SQLExplorerPluginPerspective";

    /** Id of the perspective to move to front. */
    private String perspectiveId;

    /**
     * Constructs a new ChangePerspectiveAction.
     */
    public ChangePerspectiveAction(String perspectiveId) {
        super(perspectiveId, AS_CHECK_BOX);

        this.perspectiveId = perspectiveId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
        if (!perspectiveId.equals(page.getPerspective().getId())) {
            try {
                workbench.showPerspective(perspectiveId, workbench.getActiveWorkbenchWindow());
            } catch (WorkbenchException e) {
                IStatus status = new Status(IStatus.ERROR, CorePlugin.PLUGIN_ID, IStatus.OK, "Show perspective failed.", e); //$NON-NLS-1$
                CorePlugin.getDefault().getLog().log(status);
            }
        }
        // PTODO qzhang switch to DB Discovery
        if (SE_ID.equals(perspectiveId)) {
            IPath location = ResourcesPlugin.getWorkspace().getRoot().getLocation();
            String portableString = location.append(DQStructureManager.METADATA).append(DQStructureManager.DB_CONNECTIONS)
                    .toPortableString();
            List<TdDataProvider> listTdDataProviders = DqRepositoryViewService.listTdDataProviders(new File(portableString));
            SQLExplorerPlugin default1 = SQLExplorerPlugin.getDefault();
            AliasManager aliasManager = default1.getAliasManager();
            aliasManager.getAliases().clear();
            Set<User> users = new HashSet<User>();
            try {
                aliasManager.closeAllConnections();
            } catch (ExplorerException e1) {
                e1.printStackTrace();
            }
            for (TdDataProvider tdDataProvider : listTdDataProviders) {
                Alias alias = new Alias(tdDataProvider.getName());
                String user = TaggedValueHelper.getValue("user", tdDataProvider);
                String password = TaggedValueHelper.getValue("password", tdDataProvider);
                User previousUser = new User(user, password);
                alias.setDefaultUser(previousUser);
                alias.setAutoLogon(true);
                alias.setConnectAtStartup(true);
                TypedReturnCode<TdProviderConnection> tdPc = DataProviderHelper.getTdProviderConnection(tdDataProvider);
                TdProviderConnection providerConnection = tdPc.getObject();
                String url = providerConnection.getConnectionString();
                alias.setUrl(url);
                ManagedDriver manDr = default1.getDriverModel().getDriver(
                        EDriverName.getId(providerConnection.getDriverClassName()));
                alias.setDriver(manDr);
                try {
                    aliasManager.addAlias(alias);
                } catch (ExplorerException e) {
                    e.printStackTrace();
                }
                users.add(previousUser);
            }
            aliasManager.modelChanged();
            for (User user : users) {
                OpenPasswordConnectDialogAction openDlgAction = new OpenPasswordConnectDialogAction(user.getAlias(), user, false);
                openDlgAction.run();
            }
        }
    }
}
