// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.librariesmanager.model.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.talend.commons.utils.io.FilesUtils;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class SpecificFilesUtils {

    public static void copySpecificSubFolder(File source, File target, final FileFilter sourceFolderFilter,
            final FileFilter sourceFileFilter, String searchFolderName) throws IOException {

        FileFilter folderFilter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }

        };

        for (File current : source.listFiles(folderFilter)) {
            if (current.getName().equals(searchFolderName)) {
                FilesUtils.copyFolder(current, target, false, sourceFolderFilter, sourceFileFilter, true);
            }else{
                copySpecificSubFolder(current, target, sourceFolderFilter, sourceFileFilter, searchFolderName);
            }
        }

    }
}
