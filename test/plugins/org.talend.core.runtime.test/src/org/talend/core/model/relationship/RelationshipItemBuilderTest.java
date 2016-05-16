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
package org.talend.core.model.relationship;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.talend.core.model.general.Project;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.repository.ProjectManager;

/**
 * created by cmeng on May 10, 2016
 * Detailled comment
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ProjectManager.class, CoreRuntimePlugin.class })
public class RelationshipItemBuilderTest {

    /**
     * For TDI-36019
     */
    @Test
    public void testGetInstance() {
        ProjectManager projectManager = PowerMockito.mock(ProjectManager.class);
        PowerMockito.mockStatic(ProjectManager.class);
        when(ProjectManager.getInstance()).thenReturn(projectManager);

        CoreRuntimePlugin coreRuntimePlugin = PowerMockito.mock(CoreRuntimePlugin.class);
        PowerMockito.mockStatic(CoreRuntimePlugin.class);
        when(CoreRuntimePlugin.getInstance()).thenReturn(coreRuntimePlugin);

        String projectName = "MAIN"; //$NON-NLS-1$
        Project project = new Project(projectName);
        project.setTechnicalLabel(projectName);
        RelationshipItemBuilder relationshipItemBuilder = RelationshipItemBuilder.getInstance(project, true);

        assert (project == relationshipItemBuilder.getAimProject());

        when(projectManager.getCurrentProject()).thenReturn(project);
        assert (project == RelationshipItemBuilder.getInstance().getAimProject());

        Project project2 = new Project(projectName);
        project2.setTechnicalLabel(projectName);
        when(projectManager.getCurrentProject()).thenReturn(project2);
        assert (project2 == RelationshipItemBuilder.getInstance().getAimProject());
    }
}
