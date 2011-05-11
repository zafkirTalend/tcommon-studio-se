// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.wizards.metadata.connection.files.xml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.general.Project;
import org.talend.repository.ProjectManager;

/**
 * DOC hwang class global comment. Detailled comment <br/>
 * 
 */
public class CopyDeleteFileUtil {

    public static List<String> fileList = new ArrayList<String>();

    public String copyToTemp(String oldFile) {
        Project project = ProjectManager.getInstance().getCurrentProject();
        IProject fsProject = null;
        try {
            fsProject = org.talend.core.repository.model.ResourceModelUtils.getProject(project);
        } catch (PersistenceException e2) {
            ExceptionHandler.process(e2);
        }
        if (fsProject == null) {
            return oldFile;
        }
        String temPath = fsProject.getLocationURI().getPath() + File.separator + "temp" + File.separator;

        File oFile = new File(oldFile);
        String oldPath = oFile.getParent();
        copyFolder(oldPath, temPath);
        String newPath = temPath + oFile.getName();
        File newFile = new File(newPath);
        return newFile.getAbsolutePath();
    }

    public void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs();
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                File nFile = new File(newPath + File.separator + (temp.getName()).toString());
                fileList.add(temp.getName());
                if (temp.isFile() && !nFile.exists()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + File.separator + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(oldPath + File.separator + file[i], newPath + File.separator + file[i]);
                }
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }

    }

    public void deleteToTemp(String oldFile) {
        Project project = ProjectManager.getInstance().getCurrentProject();
        IProject fsProject = null;
        try {
            fsProject = org.talend.core.repository.model.ResourceModelUtils.getProject(project);
        } catch (PersistenceException e2) {
            ExceptionHandler.process(e2);
        }
        if (fsProject == null) {
            return;
        }
        String temPath = fsProject.getLocationURI().getPath() + File.separator + "temp" + File.separator;

        File oFile = new File(oldFile);
        String oldPath = oFile.getParent();
        deleteFolder(oldPath, temPath);
    }

    public void deleteFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs();
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (newPath.endsWith(File.separator)) {
                    temp = new File(newPath + file[i]);
                } else {
                    temp = new File(newPath + File.separator + file[i]);
                }

                if (!temp.exists()) {
                    return;
                }

                if (temp.isFile()) {
                    temp.delete();
                }
                if (temp.isDirectory()) {
                    delFolder(newPath + File.separator + file[i]);
                }
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }

    }

    public void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete();

        } catch (Exception e) {
            ExceptionHandler.process(e);

        }

    }

    public void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + File.separator + tempList[i]);
                delFolder(path + File.separator + tempList[i]);
            }
        }
    }

    public void delFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        }
        if (file.isDirectory()) {
            delAllFile(path);
            delFolder(path);
        }
    }
}
