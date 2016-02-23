// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.runtime.project;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.PasswordHelper;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.User;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ProjectHelper {

    public static Project createProject(String projectName, String projectDescription) {
        Project newProject = new Project();
        newProject.setLabel(projectName.trim());// fwang fixed bug TDI-13127 Thurs,12 Jan 2012
        newProject.setTechnicalLabel(Project.createTechnicalName(newProject.getLabel()));
        newProject.setLanguage(ECodeLanguage.JAVA); // always Java
        newProject.setDescription(projectDescription);

        return newProject;
    }

    public static User createUser(String projectAuthor, String projectAuthorPass, String projectAuthorFirstname,
            String projectAuthorLastname, boolean encrypt) {
        User newUser = PropertiesFactory.eINSTANCE.createUser();
        newUser.setLogin(projectAuthor);
        newUser.setFirstName(projectAuthorFirstname);
        newUser.setLastName(projectAuthorLastname);
        if (projectAuthorPass != null && !"".equals(projectAuthorPass)) { //$NON-NLS-1$
            if (encrypt) {
                try {
                    newUser.setPassword(PasswordHelper.encryptPasswd(projectAuthorPass));
                } catch (NoSuchAlgorithmException e) {
                    ExceptionHandler.process(e);
                }
            } else {
                try {
                    newUser.setPassword(projectAuthorPass.getBytes("UTF8")); //$NON-NLS-1$
                } catch (UnsupportedEncodingException e) {
                    ExceptionHandler.process(e);
                }
            }
        }

        return newUser;
    }
}
