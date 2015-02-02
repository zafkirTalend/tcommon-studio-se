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
package org.talend.designer.maven.template;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.runtime.process.ITalendProcessJavaProject;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 */
public class MavenPomSynchronizer {

    private final ITalendProcessJavaProject codeProject;

    public MavenPomSynchronizer(ITalendProcessJavaProject codeProject) {
        super();
        this.codeProject = codeProject;
    }

    public void syncRoutinesPom() {
        IFolder routinesSrcFolder = codeProject.getSrcFolder().getFolder(JavaUtils.JAVA_ROUTINES_DIRECTORY);
        IFile routinesPomFile = routinesSrcFolder.getFile(IMavenConstants.POM_FILE_NAME);
        if (!routinesPomFile.exists()) {// generate new one
            CreateTemplateMavenPom createTemplatePom = new CreateTemplateMavenPom(routinesSrcFolder,
                    MavenTemplateConstants.POM_ROUTINGS_FILE);
            try {
                createTemplatePom.create(null);
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }
    }
}
