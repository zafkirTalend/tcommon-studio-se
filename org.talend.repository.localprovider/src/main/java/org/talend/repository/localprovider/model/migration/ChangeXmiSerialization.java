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
package org.talend.repository.localprovider.model.migration;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.general.Project;
import org.talend.core.model.migration.AbstractMigrationTask;
import org.talend.core.model.migration.IProjectMigrationTask;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.repository.localprovider.model.XmiResourceManager;
import org.talend.repository.model.ResourceModelUtils;

/**
 *
 */
public class ChangeXmiSerialization extends AbstractMigrationTask implements IProjectMigrationTask {

    private XmiResourceManager xmiResourceManager;
    
    private Collection<Resource> modifiedResources = new ArrayList<Resource>();
    
    public ExecutionResult execute(Project project) {
        try {
            if (!project.isLocal()) {
                return ExecutionResult.NOTHING_TO_DO;
            }

            IProject iProject = ResourceModelUtils.getProject(project);
            xmiResourceManager = new XmiResourceManager();
            xmiResourceManager.setUseOldProjectFile(true);
            if (!xmiResourceManager.hasTalendProjectFile(iProject)) {
                return ExecutionResult.NOTHING_TO_DO;
            }
            
            PropertiesResourcesCollector propertiesResourcesCollector = new PropertiesResourcesCollector();
            iProject.accept(propertiesResourcesCollector);
            Collection<IFile> propertiesResourcesFiles = propertiesResourcesCollector.getPropertiesResourcesFiles();
            
            for (IFile file : propertiesResourcesFiles) {
                Property property = xmiResourceManager.loadProperty(file);
              
                // we access the author (resolve proxy)
                boolean correctAuthor = false;
                try {
                    property.getAuthor();
                } catch (Exception e) {
                    // author is null : fix old bug
                    correctAuthor = true;
                }
                if (correctAuthor || property.getAuthor().getLogin() == null) {
                    property.setAuthor(project.getAuthor());
                }

                EcoreUtil.resolveAll(property.eResource());
                
                // if .properties is saved then migrated .item must also be saved
                if (property.getItem() instanceof BusinessProcessItem) {
                    BusinessProcessItem businessProcessItem = (BusinessProcessItem) property.getItem();
                    businessProcessItem.getNotation();
                }
                
                modifiedResources.add(property.eResource());
            }

            Resource projectResource = xmiResourceManager.loadProject(iProject).eResource();
            xmiResourceManager.setUseOldProjectFile(false);
            Resource newProjectResource = xmiResourceManager.createProjectResource(iProject);
            EObject[] objects = (EObject[]) projectResource.getContents().toArray();
            for (int i = 0; i < objects.length; i++) {
                newProjectResource.getContents().add(objects[i]);
            }
            
            modifiedResources.add(newProjectResource);

            for (Resource resource : modifiedResources) {
                try {
                    resource.setModified(true);
                    xmiResourceManager.saveResource(resource);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }
            }
            
            xmiResourceManager.deleteResource(projectResource);
            
            return ExecutionResult.SUCCESS_NO_ALERT;
        } catch (Exception e) {
            ExceptionHandler.process(e);
            return ExecutionResult.FAILURE;
        }
    }

    /**
     *
     */
    private final class PropertiesResourcesCollector implements IResourceVisitor {
    
        private Collection<IFile> propertiesResourcesFiles = new ArrayList<IFile>();
    
        public boolean visit(IResource resource) {
            if (resource.getType() == IResource.FILE) {
                IFile file = (IFile) resource;
                if (xmiResourceManager.isPropertyFile(file)) {
                    propertiesResourcesFiles.add(file);
                }
            }
            return true;
        }
        
        public Collection<IFile> getPropertiesResourcesFiles() {
            return propertiesResourcesFiles;
        }
    }
}
