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

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.project.CreateMaven;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 */
public class CreateTemplateMavenPom extends CreateMaven {

    protected static final MavenModelManager MODEL_MANAGER = MavenPlugin.getMavenModelManager();

    private final String templatePomFile;

    private final IFile pomFile;

    private boolean overwrite = true;

    public CreateTemplateMavenPom(IFile pomFile, String templatePomFile) {
        super();
        this.pomFile = pomFile;
        this.templatePomFile = templatePomFile;
    }

    protected IFile getPomFile() {
        return this.pomFile;
    }

    public boolean isOverwrite() {
        return this.overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    @Override
    protected Model createModel() {
        Model model = null;
        try {
            InputStream inputStream = MavenTemplateManager.getTemplate(templatePomFile);
            if (inputStream != null) {
                model = MODEL_MANAGER.readMavenModel(inputStream);

            }
        } catch (IOException e) {
            ExceptionHandler.process(e);
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }

        // retrieve template failure. try default one.
        if (model == null) {
            // create default model
            model = super.createModel();
        } else { // if load from template, try to set the attributes again.
            setAttributes(model);
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
        if (pomFile == null) {
            return;
        }
        if (!pomFile.getName().equals(MavenConstants.POM_FILE_NAME)) {
            throw new IOException("Must be pom.xml, shouldn't be specially like: " + pomFile);
        }
        if (pomFile.exists()) {
            if (isOverwrite()) {
                pomFile.delete(true, monitor);
            } else {
                throw new IOException("The pom file have been existed. it's " + pomFile);
            }
        }

        Model model = createModel();
        MODEL_MANAGER.createMavenModel(pomFile, model);

    }

}
