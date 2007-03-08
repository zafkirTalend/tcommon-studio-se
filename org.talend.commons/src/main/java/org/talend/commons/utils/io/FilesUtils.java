// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.ExceptionHandler;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class FilesUtils {

    public static void copyFolder(File source, File target, boolean emptyTargetBeforeCopy, final FileFilter sourceFolderFilter,
            final FileFilter sourceFileFilter, boolean copyFolder) throws IOException {
        if (!target.exists()) {
            target.mkdirs();
        }

        if (emptyTargetBeforeCopy) {
            emptyFolder(target);
        }

        FileFilter folderFilter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.isDirectory() && (sourceFolderFilter == null || sourceFolderFilter.accept(pathname));
            }

        };
        FileFilter fileFilter = new FileFilter() {

            public boolean accept(File pathname) {
                return !pathname.isDirectory() && (sourceFileFilter == null || sourceFileFilter.accept(pathname));
            }

        };

        for (File current : source.listFiles(folderFilter)) {
            if (copyFolder) {
                File newFolder = new File(target, current.getName());
                newFolder.mkdir();
                copyFolder(current, newFolder, emptyTargetBeforeCopy, sourceFolderFilter, sourceFileFilter, copyFolder);
            } else {
                copyFolder(current, target, emptyTargetBeforeCopy, sourceFolderFilter, sourceFileFilter, copyFolder);
            }
        }

        for (File current : source.listFiles(fileFilter)) {
            File out = new File(target, current.getName());
            copyFile(current, out);
        }
    }

    private static void emptyFolder(File toEmpty) {
        for (File current : toEmpty.listFiles()) {
            if (current.isDirectory()) {
                emptyFolder(current);
            }
            current.delete();
        }
    }

    public static void copyFile(File source, File target) throws IOException {
        // Need to recopy the file in one of these cases:
        // 1. target doesn't exists (never copied)
        // 2. target exists but source has been modified recently
        // 3. target exists but source and target have different sizes

        if (!target.exists() || source.lastModified() > target.lastModified() || source.length() != target.length()) {
            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(target);
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
            fis.close();
            fos.close();
        }
    }

    public static void replaceInFile(String regex, String fileName, String replacement) throws IOException {
        InputStream in = new FileInputStream(fileName);
        StringBuffer buffer = new StringBuffer();
        try {
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inR);
            String line;
            while ((line = buf.readLine()) != null) {
                buffer.append(line.replaceAll(regex, replacement)).append("\n");
            }
        } finally {
            in.close();
        }

        OutputStream os = new FileOutputStream(fileName);
        os.write(buffer.toString().getBytes());
        os.close();
    }

    public static List<URL> getFilesFromFolder(Bundle bundle, String path, String extension) {
        List<URL> toReturn = new ArrayList<URL>();

        Enumeration entryPaths = bundle.getEntryPaths(path);
        for (Enumeration enumer = entryPaths; enumer.hasMoreElements();) {
            String fileName = (String) enumer.nextElement();
            if (fileName.endsWith(extension)) {
                URL url = bundle.getEntry(fileName);
                try {
                    toReturn.add(FileLocator.toFileURL(url));
                } catch (IOException e) {
                    ExceptionHandler.process(e);
                }
            }
        }
        return toReturn;
    }

    public static FileFilter getExcludeSVNFilesFilter() {
        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                return !pathname.toString().endsWith(".svn");
            }

        };
        return filter;
    }

    public static FileFilter getAcceptJARFilesFilter() {
        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.toString().endsWith(".jar");
            }

        };
        return filter;
    }

    public static FileFilter getAcceptPMFilesFilter() {
        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.toString().endsWith(".pm");
            }

        };
        return filter;
    }
}
