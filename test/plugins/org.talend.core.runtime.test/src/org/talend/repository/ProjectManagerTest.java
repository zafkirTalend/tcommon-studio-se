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
package org.talend.repository;

import org.junit.Assert;
import org.junit.Test;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.properties.impl.PropertiesFactoryImpl;

/**
 * 
 * @author Kevin Wang
 *
 */
public class ProjectManagerTest {

    @Test
    public void testSetAndGetLocalRefBranch() {
        Project project = ProjectManager.getInstance().getCurrentProject();
        ProjectManager.getInstance().setLocalRefBranch(project.getEmfProject(), "sourceBranch", "targetBranch");
        Assert.assertEquals("sourceBranch",
                ProjectManager.getInstance().getLocalRefBranch(project.getEmfProject(), "targetBranch"));
        ProjectManager.getInstance().deleteLocalRefBranchSetting(project.getEmfProject(), "targetBranch");
    }

    @Test
    public void testDeleteLocalRefBranchSetting() {
        Project project = ProjectManager.getInstance().getCurrentProject();
        ProjectManager.getInstance().setLocalRefBranch(project.getEmfProject(), "sourceBranch", "targetBranch");
        Assert.assertNotNull(ProjectManager.getInstance().getLocalRefBranch(project.getEmfProject(), "targetBranch"));

        ProjectManager.getInstance().deleteLocalRefBranchSetting(project.getEmfProject(), "targetBranch");
        Assert.assertNull(ProjectManager.getInstance().getLocalRefBranch(project.getEmfProject(), "targetBranch"));
    }

    @Test
    public void testValidReferenceProject() {
        PropertiesFactoryImpl factory = new PropertiesFactoryImpl();
        ProjectReference projectReference = factory.createProjectReference();
        Project project = ProjectManager.getInstance().getCurrentProject();
        ProjectManager.getInstance().setMainProjectBranch(project, "targetBranch");
        ProjectManager.getInstance().deleteLocalRefBranchSetting(project.getEmfProject(), "targetBranch");

        projectReference.setProject(project.getEmfProject());
        Assert.assertTrue(ProjectManager.validReferenceProject(project.getEmfProject(), projectReference));

        projectReference.setBranch("sourceBranch");
        Assert.assertFalse(ProjectManager.validReferenceProject(project.getEmfProject(), projectReference));

        ProjectManager.getInstance().setLocalRefBranch(project.getEmfProject(), "sourceBranch", "targetBranch");
        Assert.assertTrue(ProjectManager.validReferenceProject(project.getEmfProject(), projectReference));
        
        ProjectManager.getInstance().setLocalRefBranch(project.getEmfProject(), "origin/sourceBranch", "targetBranch");
        Assert.assertTrue(ProjectManager.validReferenceProject(project.getEmfProject(), projectReference));

        ProjectManager.getInstance().deleteLocalRefBranchSetting(project.getEmfProject(), "targetBranch");

    }
}
