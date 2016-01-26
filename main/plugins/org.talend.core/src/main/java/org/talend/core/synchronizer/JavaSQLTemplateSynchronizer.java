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
package org.talend.core.synchronizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.talend.commons.exception.SystemException;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.properties.SQLPatternItem;
import org.talend.core.runtime.process.ITalendProcessJavaProject;
import org.talend.designer.codegen.ISQLTemplateSynchronizer;
import org.talend.designer.runprocess.IRunProcessService;

/**
 * SQLPattern synchronizer of java project.
 * 
 * bqian class global comment. Detailled comment <br/>
 * 
 * 
 */
public class JavaSQLTemplateSynchronizer implements ISQLTemplateSynchronizer {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.codegen.ISQLPatternSynchronizer#getSQLPattern(org.talend.core.model.properties.SQLPatternItem
     * )
     */
    @Override
    public IFile getSQLTemplateFile(SQLPatternItem item) throws SystemException {
        if (!GlobalServiceRegister.getDefault().isServiceRegistered(IRunProcessService.class)) {
            return null;
        }
        IRunProcessService service = (IRunProcessService) GlobalServiceRegister.getDefault().getService(IRunProcessService.class);
        ITalendProcessJavaProject talendProcessJavaProject = service.getTalendProcessJavaProject();
        if (talendProcessJavaProject == null) {
            return null;
        }
        String basePath = JavaUtils.JAVA_SQLPATTERNS_DIRECTORY + '/' + item.getEltName();
        // init user defined folder
        talendProcessJavaProject.getResourceSubFolder(null, basePath + '/' + JavaUtils.JAVA_USER_DEFINED);

        IFolder systemFolder = talendProcessJavaProject.getResourceSubFolder(null, basePath + '/'
                + JavaUtils.JAVA_SYSTEM_DIRECTORY);

        IFile file = systemFolder.getFile(item.getProperty().getLabel() + JavaUtils.JAVA_SQLPATTERN_EXTENSION);
        return file;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.codegen.ISQLPatternSynchronizer#syncSQLPattern(org.talend.core.model.properties.SQLPatternItem
     * , boolean)
     */
    @Override
    public void syncSQLTemplate(SQLPatternItem routineItem, boolean copyToTemp) throws SystemException {
        FileOutputStream fos = null;
        try {
            IFile file = getSQLTemplateFile(routineItem);
            if (file == null) {
                return;
            }
            if (copyToTemp) {
                String routineContent = new String(routineItem.getContent().getInnerContent());
                File f = file.getLocation().toFile();
                fos = new FileOutputStream(f);
                fos.write(routineContent.getBytes());
                fos.close();
            }
            file.refreshLocal(1, null);
        } catch (CoreException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                // ignore me even if i'm null
            }
        }

    }

}
