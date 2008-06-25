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
package org.talend.librariesmanager.model.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.talend.commons.utils.io.FilesUtils;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class SpecificFilesUtils {

    public static void copySpecificSubFolder(File source, File target, final FileFilter sourceFolderFilter,
            final FileFilter sourceFileFilter, String searchFolderName, IProgressMonitor... monitorWrap) throws IOException {

        IProgressMonitor monitor = null;
        if (monitorWrap != null && monitorWrap.length == 1) {
            monitor = monitorWrap[0];
        }

        FileFilter folderFilter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }

        };

        for (File current : source.listFiles(folderFilter)) {
            if (monitor != null && monitor.isCanceled()) {
                throw new OperationCanceledException("Operation is canceled during copying folders or files");
            }
            if (current.getName().equals(searchFolderName)) {
                FilesUtils.copyFolder(current, target, false, sourceFolderFilter, sourceFileFilter, true, monitor);
            } else {
                copySpecificSubFolder(current, target, sourceFolderFilter, sourceFileFilter, searchFolderName, monitor);
            }
        }

    }
}
