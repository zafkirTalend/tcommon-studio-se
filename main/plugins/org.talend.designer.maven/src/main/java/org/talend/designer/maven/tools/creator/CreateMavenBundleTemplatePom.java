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

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.PluginChecker;
import org.talend.core.runtime.projectsetting.IProjectSettingTemplateConstants;
import org.talend.designer.maven.template.MavenTemplateManager;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 */
public class CreateMavenBundleTemplatePom extends CreateMaven {

    /**
     * FIXME, the templates should be moved to maven.job plugin. now use proxy for MavenJobMavenTemplateManager.
     */
    protected static final String JOB_TEMPLATE_BUNDLE = PluginChecker.MAVEN_JOB_PLUGIN_ID;

    protected static final MavenModelManager MODEL_MANAGER = MavenPlugin.getMavenModelManager();

    private final String bundleTemplateName;

    private final IFile pomFile;

    private boolean overwrite = true;

    /*
     * specially for win os, with file name for different case. make sure all platform are same, can set true.
     */
    private boolean ignoreFileNameCase = false;

    /*
     * if true, will load from tempalte. else, can create one pom with attributes settings.
     */
    private boolean baseOnTemplateOnly = false;

    public CreateMavenBundleTemplatePom(IFile pomFile, String bundleTemplateName) {
        super();
        this.pomFile = pomFile;
        this.bundleTemplateName = bundleTemplateName;
    }

    protected IFile getPomFile() {
        return this.pomFile;
    }

    protected String getBundleTemplateName() {
        return bundleTemplateName;
    }

    public boolean isOverwrite() {
        return this.overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public boolean isIgnoreFileNameCase() {
        return ignoreFileNameCase;
    }

    public void setIgnoreFileNameCase(boolean ignoreFileNameCase) {
        this.ignoreFileNameCase = ignoreFileNameCase;
    }

    public boolean isBaseOnTemplateOnly() {
        return baseOnTemplateOnly;
    }

    public void setBaseOnTemplateOnly(boolean baseOnTemplateOnly) {
        this.baseOnTemplateOnly = baseOnTemplateOnly;
    }

    @Override
    public String toString() {
        return pomFile.toString();
    }

    @Override
    protected Model createModel() {
        InputStream inputStream = null;
        try {
            Model model = null;
            inputStream = getTemplateStream();
            if (inputStream != null) {
                model = MODEL_MANAGER.readMavenModel(inputStream);
            }
            // load failure. try default one.
            if (model == null) {
                if (!isBaseOnTemplateOnly()) {
                    // create default model
                    model = super.createModel();
                }
            } else { // if load from template, try to set the attributes again.
                setAttributes(model);
                addProperties(model);
            }
            return model;
        } catch (IOException e) {
            ExceptionHandler.process(e);
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //
                }
            }
        }
        return null;
    }

    protected InputStream getTemplateStream() throws IOException {
        try {
            return MavenTemplateManager.getBundleTemplateStream(JOB_TEMPLATE_BUNDLE,
                    IProjectSettingTemplateConstants.PATH_STANDALONE + '/' + bundleTemplateName);
        } catch (Exception e) {
            throw new IOException(e);
        }
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

        // curPomFile.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);

        try {
            checkCreatingFile(monitor, curPomFile);
        } catch (Exception e) {
            ExceptionHandler.process(e);
            return;
        }

        Model model = createModel();
        if (model == null) {
            throw new Exception("Can't create the maven pom in file:" + curPomFile);
        }
        MODEL_MANAGER.createMavenModel(curPomFile, model);

        afterCreate(monitor);

        curPomFile.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);

    }

    protected void checkCreatingFile(IProgressMonitor monitor, IFile currentFile) throws Exception {
        currentFile.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);

        List<IFile> existedSameFiles = getExistedFiles(currentFile);

        // existed current one and not overwrite.
        if (existedSameFiles.contains(currentFile) && !isOverwrite()) {
            throw new IOException("Can't overwrite the file: " + currentFile);
        }
        // delete all
        for (IFile file : existedSameFiles) {
            File f = file.getLocation().toFile();
            if (f.exists()) {
                f.delete();
            }
        }

        currentFile.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);
    }

    protected List<IFile> getExistedFiles(IFile file) {
        List<IFile> existedFiles = new ArrayList<IFile>();
        if (isIgnoreFileNameCase()) {
            final String currentFileName = file.getName();
            File parentFile = file.getLocation().toFile().getParentFile();
            if (parentFile.exists()) {
                File[] listFiles = parentFile.listFiles(new FilenameFilter() {

                    @Override
                    public boolean accept(File dir, String name) {
                        return name.equalsIgnoreCase(currentFileName);
                    }
                });
                if (listFiles != null) {
                    for (File f : listFiles) {
                        IFile sameFile = file.getParent().getFile(new Path(f.getName()));
                        existedFiles.add(sameFile);
                    }
                }
            }
        } else if (file.exists()) { // only add current file
            existedFiles.add(file);
        }
        return existedFiles;
    }

    protected void afterCreate(IProgressMonitor monitor) throws Exception {
        // nothing to do
    }

}
