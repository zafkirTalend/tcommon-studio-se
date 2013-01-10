// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.tis;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.osgi.service.prefs.BackingStoreException;
import org.talend.commons.exception.LoginException;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.IService;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.User;
import org.talend.core.updatesite.IUpdateSiteBean;

/**
 * wchen class global comment. Detailled comment
 */
public interface ICoreTisService extends IService {

    public void drawWelcomeLogo(String version);

    public boolean isSupportDynamicType(String componentName);

    public boolean validProject(Project project, boolean flag) throws PersistenceException;

    public List<IUpdateSiteBean> getPatchesInstalled() throws BackingStoreException;

    public List<IUpdateSiteBean> getUpdateSitesToBeInstall(String username, String password, String archivaServicesURL,
            List<String> repositories) throws BackingStoreException;

    public void downLoadAndInstallUpdateSites(String archivaServerURL, String username, String password,
            List<IUpdateSiteBean> toBeInstalled, List<String> repositories) throws Exception;

    public Object getArchivaObject(User user, String password, String url) throws PersistenceException, LoginException;

    public boolean needRestartAfterUpdate();

    public void setNeedResartAfterUpdate(boolean needRestart);

    /**
     * DOC ycbai Comment method "exportAsCWM".
     * 
     * @param itemUri
     * @param destDir
     */
    public void exportAsCWM(URI itemUri, String destDir);

}
