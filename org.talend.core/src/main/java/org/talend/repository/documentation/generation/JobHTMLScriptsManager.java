// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.documentation.generation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.talend.core.model.genhtml.HTMLDocUtils;
import org.talend.core.model.genhtml.IHTMLDocConstants;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.documentation.ExportFileResource;

/**
 * This class has utility methods for generating job information as HTML file.
 * 
 * $Id: JobHTMLScriptsManager.java 2007-3-8,下午03:15:50 ftang $
 * 
 */
public class JobHTMLScriptsManager implements IDocumentationManager {

    private IDocumentationGenerator docGenerator = null;

    private boolean needGenerate = true;

    /**
     * JobHTMLScriptsManager constructor comment.
     * 
     * @param docGenerator
     * @param isNeedGenerate
     */
    public JobHTMLScriptsManager(IDocumentationGenerator docGenerator, boolean isNeedGenerate) {
        this.docGenerator = docGenerator;
        this.needGenerate = isNeedGenerate;
    }

    /**
     * Gets the set of <code>ExportFileResource</code>
     * 
     * @param process
     * @return
     */
    public List<ExportFileResource> getExportResources(ExportFileResource[] process) {
        for (int i = 0; i < process.length; i++) {
            docGenerator.generateHTMLFile(process[i]);// added path
        }
        return Arrays.asList(process);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.documentation.generation.IDocumentationManager#getExportResources(org.talend.repository.documentation.ExportFileResource[],
     * java.lang.String, java.lang.String[])
     */
    public List<ExportFileResource> getExportResources(ExportFileResource[] process, String targetPath, String... jobVersion)
            throws Exception {

        if (this.needGenerate) {
            for (int i = 0; i < process.length; i++) {
                docGenerator.generateDocumentation(process[i], targetPath, jobVersion);
            }
        } else {
            for (int i = 0; i < process.length; i++) {
                collectGeneratedDocumentation(process[i], targetPath, jobVersion);
            }
        }

        return Arrays.asList(process);

    }

    /**
     * ftang Comment method "collectionGeneratedDocumentation".
     * 
     * @param exportFileResource
     * @param jobVersion
     * @param targetPath
     * @throws MalformedURLException
     */
    private void collectGeneratedDocumentation(ExportFileResource exportFileResource, String targetPath, String[] jobVersion)
            throws MalformedURLException {

        String jobName = exportFileResource.getItem().getProperty().getLabel();

        String jobPath = exportFileResource.getItem().getProperty().getItem().getState().getPath();

        // Used for generating/updating all jobs' documentaiton only.
        if (targetPath.endsWith(ERepositoryObjectType.JOBS.toString().toLowerCase())) {
            targetPath = targetPath + IPath.SEPARATOR + jobPath + IPath.SEPARATOR + jobName;
        }
        String version = "";

        // Checks if the job's version is specified, see it on "Export documentation" Dialog:
        if (jobVersion != null && jobVersion.length == 1) {
            version = jobVersion[0];
        } else {
            version = exportFileResource.getItem().getProperty().getVersion();
        }
        targetPath = targetPath + "_" + version;

        // Store xml and html files:
        List<URL> resultFiles = new ArrayList<URL>(5);

        // Store all pictures' path:
        List<URL> picList = new ArrayList<URL>(5);

        File file = new File(targetPath);

        if (file.exists()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (!(file2.exists())) {
                    continue;
                }

                // i.e. xml file and html file:
                if (file2.isFile()) {

                    resultFiles.add(file2.toURL());

                } else if (file2.isDirectory() && file2.getName().equals(IHTMLDocConstants.PIC_FOLDER_NAME)) {
                    for (File tempFile : file2.listFiles()) {
                        if (tempFile.exists() && tempFile.isFile()) {
                            // Copy all picture folders:
                            picList.add(tempFile.toURL());
                        }
                    }

                }
            }

            exportFileResource.addResources(resultFiles);

            exportFileResource.addResources(IHTMLDocConstants.PIC_FOLDER_NAME, picList);
        }

    }

    /**
     * Deletes the temporary files.
     */
    public void deleteTempFiles() {
        String tmpFold = HTMLDocUtils.getTmpFolder();
        File dir = new File(tmpFold);
        if (dir.exists()) {
            deleteDirectory(dir);
        }
    }

    /**
     * This method is used for deleting a directory.
     * 
     * @param dir
     */
    private void deleteDirectory(File dir) {
        File[] entries = dir.listFiles();
        int sz = entries.length;
        for (int i = 0; i < sz; i++) {
            if (entries[i].isDirectory()) {
                deleteDirectory(entries[i]);
            } else {
                // System.out.println("" + entries[i].delete() + " *** " + entries[i]);
                entries[i].delete();
            }
        }
        dir.delete();
    }
}
