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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.maven.DesignerMavenPlugin;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.maven.project.CreateMaven;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 */
public class CreateTemplateMavenPom extends CreateMaven {

    protected static final MavenModelManager MODEL_MANAGER = MavenPlugin.getMavenModelManager();

    private final String templatePomFile;

    private final IFolder baseFolder;

    public CreateTemplateMavenPom(IFolder baseFolder, String templatePomFile) {
        super();
        this.baseFolder = baseFolder;
        this.templatePomFile = templatePomFile;
    }

    @Override
    protected Model getModel() {
        Model model = null;

        URL routinesTemplateUrl = DesignerMavenPlugin.getPlugin().getContext().getBundle()
                .getEntry(MavenTemplateConstants.TEMPLATE_PATH + '/' + templatePomFile);
        if (routinesTemplateUrl != null) {
            try {
                InputStream inputStream = routinesTemplateUrl.openStream();
                if (inputStream != null) {
                    model = MODEL_MANAGER.readMavenModel(inputStream);

                }
            } catch (IOException e) {
                ExceptionHandler.process(e);
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }

        // retrieve template failure. try default one.
        if (model == null) {
            setGroupId(TalendMavenContants.DEFAULT_GROUP_ID);
            setArtifactId(TalendMavenContants.DEFAULT_ROUTINES_ARTIFACT_ID);
            setVersion(TalendMavenContants.DEFAULT_VERSION);
            // create default model
            model = super.getModel();
        }
        return model;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.maven.project.CreateMaven#create(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void create(IProgressMonitor monitor) throws Exception {
        if (baseFolder == null) {
            return;
        }
        IFile routinesPomFile = baseFolder.getFile(IMavenConstants.POM_FILE_NAME);
        if (routinesPomFile.exists()) {
            throw new IOException("The pom file for routine have been existed. it's " + routinesPomFile); //$NON-NLS-1$
        }

        Model model = getModel();
        MODEL_MANAGER.createMavenModel(routinesPomFile, model);

    }

}
