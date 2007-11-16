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
