// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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
import org.talend.core.updatesite.IPatchBean;

/**
 * wchen class global comment. Detailled comment
 */
public interface ICoreTisService extends IService {

    public void drawWelcomeLogo(String version);

    public boolean isSupportDynamicType(String componentName);

    public boolean validProject(Project project, boolean flag) throws PersistenceException;

    public List<IPatchBean> getPatchesInstalled() throws BackingStoreException;

    public List<IPatchBean> getPatchesToBeInstall(String archivaServicesURL, String... repository) throws BackingStoreException;

    public void addPatchInformation(String key, String value) throws BackingStoreException;

    public void downLoadAndInstallPatches(String archivaServerURL, List<IPatchBean> toBeInstalled, String... repository);

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
