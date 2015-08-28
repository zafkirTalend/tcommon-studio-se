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
package org.talend.designer.maven.tools.creator;

import java.util.Collections;
import java.util.Set;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.routines.IRoutinesService;
import org.talend.core.runtime.projectsetting.IProjectSettingTemplateConstants;
import org.talend.designer.maven.template.MavenTemplateManager;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class CreateMavenPigUDFPom extends AbstractMavenCodesTemplatePom {

    public CreateMavenPigUDFPom(IFile pomFile) {
        super(pomFile, IProjectSettingTemplateConstants.POM_PIGUDFS_TEMPLATE_FILE_NAME);
    }

    @Override
    protected Model getTemplateModel() {
        return MavenTemplateManager.getPigUDFsTempalteModel();
    }

    @Override
    protected Set<ModuleNeeded> getDependenciesModules() {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IRoutinesService.class)) {
            IRoutinesService routiensService = (IRoutinesService) GlobalServiceRegister.getDefault().getService(
                    IRoutinesService.class);
            // TODO, maybe should only add the routines, not for pigudfs and beans.
            Set<ModuleNeeded> runningModules = routiensService.getRunningModules();
            return runningModules;
        }
        return Collections.emptySet();
    }
}
