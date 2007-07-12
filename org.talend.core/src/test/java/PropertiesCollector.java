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

import java.io.File;
import java.io.FileFilter;

import org.talend.commons.utils.io.FilesUtils;

/**
 * bqian class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public class PropertiesCollector {

    public static void main(String[] args) throws Exception {
        File file = new File("");
        File desFile = new File("c:/talend_properites");
        desFile.mkdir();
        File workspace = new File(file.getAbsolutePath() + "/../");
        FileFilter folderFilter = new FileFilter() {

            public boolean accept(File pathname) {
                String fileName = pathname.getName();
                if (fileName.equals("bin") || fileName.equals("class") || fileName.equals("classes")) {
                    return false;
                }

                return true;
            }
        };

        FileFilter fileFilter = new FileFilter() {

            public boolean accept(File pathname) {
                String fileName = pathname.getName();
                if (fileName.equals("build.properties")) {
                    return false;
                }
                return pathname.getName().endsWith(".properties");
            }
        };

        FilesUtils.copyFolder(workspace, desFile, true, folderFilter, fileFilter, true);
        FilesUtils.removeEmptyFolder(desFile);
    }
}
