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
package org.talend.repository.documentation;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.talend.commons.utils.io.FilesUtils;

/**
 * bqian Exports resources to file system.
 * 
 * @since 3.1
 */
public class FileSystemExporterFullPath implements IFileExporterFullPath {

    File rootFolder = null;

    /**
     * Create an instance of this class.
     * 
     * @param filename java.lang.String
     * @param compress boolean
     * @exception java.io.IOException
     */
    public FileSystemExporterFullPath(String filename) throws IOException {
        rootFolder = new File(filename);
    }

    /**
     * Do all required cleanup now that we're finished with the currently-open .tar.gz
     * 
     * @exception java.io.IOException
     */
    public void finished() throws IOException {
    }

    /**
     * Write the passed resource to the current archive.
     * 
     * @param resource org.eclipse.core.resources.IFile
     * @param destinationPath java.lang.String
     * @exception java.io.IOException
     * @exception org.eclipse.core.runtime.CoreException
     */
    public void write(String resource, String destinationPath) throws IOException, CoreException {
        Path path = new Path(destinationPath);
        if (path.segmentCount() == 1) {
            writeFile(resource, new File(rootFolder, path.segment(0)));
        } else {
            File destination = new File(rootFolder, path.toOSString());
            createFolder(destination.getAbsolutePath());
            writeFile(resource, destination);
        }
    }

    /**
     * DOC bqian Comment method "createFolder".
     * 
     * @param path
     */
    private void createFolder(String path) throws IOException {
        FilesUtils.createFoldersIfNotExists(path, true);
    }

    /**
     * Writes the passed file resource to the specified destination on the local file system
     */
    protected void writeFile(String resource, File destinationPath) throws IOException, CoreException {
        FilesUtils.copyFile(new File(resource), destinationPath);
    }

    public static void main(String[] args) {
        try {
            FileSystemExporterFullPath fs = new FileSystemExporterFullPath("c:/qqq");
            fs.write("C:\\RavBin\\DESKTOP.INI", "aaa/bbb/ccc/DESKTOP.INI");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // this test is ok.
    }

}
