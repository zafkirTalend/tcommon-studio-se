// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceNode;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.runtime.services.IMavenUIService;
import org.talend.designer.maven.ui.setting.preference.M2eUserSettingForTalendLoginTask;
import org.talend.designer.maven.ui.setting.repository.RepositoryMavenSettingManager;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class MavenUIService implements IMavenUIService {

    @Override
    public void addCustomMavenSettingChildren(IPreferenceNode parentNode) {
        if (parentNode == null) {
            return;
        }
        RepositoryMavenSettingManager manager = new RepositoryMavenSettingManager();
        manager.init(IRepositoryView.VIEW_ID);
        IPreferenceNode[] rootSubNodes = manager.getRootSubNodes();
        for (IPreferenceNode node : rootSubNodes) {
            parentNode.add(node);
        }
    }

    @Override
    public void checkUserSettings(IProgressMonitor monitor) {
        try {
            M2eUserSettingForTalendLoginTask loginTask = new M2eUserSettingForTalendLoginTask();
            loginTask.run(monitor);
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }
}
