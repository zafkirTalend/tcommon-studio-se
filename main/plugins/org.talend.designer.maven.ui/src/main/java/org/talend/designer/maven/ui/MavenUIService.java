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

import java.io.InputStream;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceNode;
import org.talend.core.runtime.services.IMavenUIService;
import org.talend.designer.maven.template.AbstractMavenTemplateManager;
import org.talend.designer.maven.template.MavenTemplateManager;
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
    public String getProjectSettingValue(String key) {
        Map<String, AbstractMavenTemplateManager> templateManagerMap = MavenTemplateManager.getTemplateManagerMap();

        for (String bundleName : templateManagerMap.keySet()) {
            AbstractMavenTemplateManager templateManager = templateManagerMap.get(bundleName);
            try {
                InputStream steam = templateManager.readProjectSettingStream(key);
                if (steam != null) {
                    String content = templateManager.getContentFromInputStream(steam);
                    if (content != null) {
                        return content;
                    }
                }
            } catch (Exception e) {
                // try to find another one
            }
        }
        return null;
    }

    @Override
    public InputStream getProjectSettingStream(String key) {
        Map<String, AbstractMavenTemplateManager> templateManagerMap = MavenTemplateManager.getTemplateManagerMap();

        for (String bundleName : templateManagerMap.keySet()) {
            AbstractMavenTemplateManager templateManager = templateManagerMap.get(bundleName);
            try {
                InputStream steam = templateManager.readProjectSettingStream(key);
                if (steam != null) {
                    return steam;
                }
            } catch (Exception e) {
                // try to find another one
            }
        }
        return null;
    }
}
