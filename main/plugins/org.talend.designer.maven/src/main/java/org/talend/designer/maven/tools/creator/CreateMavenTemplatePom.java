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

import java.io.IOException;
import java.io.InputStream;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.maven.template.MavenTemplateManager;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 */
public class CreateMavenTemplatePom extends CreateMaven {

    protected static final MavenModelManager MODEL_MANAGER = MavenPlugin.getMavenModelManager();

    private final String templatePomFile;

    private final IFile pomFile;

    private boolean overwrite = true;

    public CreateMavenTemplatePom(IFile pomFile, String templatePomFile) {
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
        Model model = loadModel();

        // load failure. try default one.
        if (model == null) {
            // create default model
            model = super.createModel();
        } else { // if load from template, try to set the attributes again.
            setAttributes(model);
            addProperties(model);
        }
        return model;
    }

    protected Model loadModel() {
        try {
            InputStream inputStream = MavenTemplateManager.getTemplate(templatePomFile);
            if (inputStream != null) {
                return MODEL_MANAGER.readMavenModel(inputStream);

            }
        } catch (IOException e) {
            ExceptionHandler.process(e);
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.maven.project.CreateMaven#create(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void create(IProgressMonitor monitor) throws Exception {
        IFile curPomFile = getPomFile();
        if (curPomFile == null) {
            return;
        }
        // if (!curPomFile.getName().equals(MavenConstants.POM_FILE_NAME)) {
        // throw new IOException("Must be pom.xml, shouldn't be specially like: " + curPomFile);
        // }
        if (curPomFile.exists()) {
            if (isOverwrite()) {
                curPomFile.delete(true, monitor);
            } else {
                // throw new IOException("The pom file have been existed. it's " + curPomFile);
                return;
            }
        }

        Model model = createModel();
        MODEL_MANAGER.createMavenModel(curPomFile, model);

        curPomFile.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);

    }

}
