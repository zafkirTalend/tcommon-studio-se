// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.repository.localprovider.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.User;
import org.talend.core.model.properties.helper.ByteArrayResource;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.localprovider.model.ResourceFilenameHelper.FileName;

/**
 * DOC mhelleboid class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class XmiResourceManager {

    public static final String PROPERTIES_EXTENSION = PropertiesPackage.eNAME;

    public static final String ITEM_EXTENSION = "item";

    private static final String PROJECT_DESCRIPTION_FILE = "talendProject";

    // PTODO MHE should use a custom ResourceFactory
    // PTODO MHE test duplicate resourcesUri in resourceSet !
    private ResourceSet resourceSet = new ResourceSetImpl();

    public Project loadProject(IProject project) throws PersistenceException {
        URI uri = getProjectResourceUri(project);
        Resource resource = resourceSet.getResource(uri, true);
        Project emfProject = (Project) EcoreUtil
                .getObjectByType(resource.getContents(), PropertiesPackage.eINSTANCE.getProject());
        return emfProject;
    }

    public Property loadProperty(IResource iResource) {
        Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(iResource.getFullPath().toString()), true);
        Property property = (Property) EcoreUtil.getObjectByType(resource.getContents(), PropertiesPackage.eINSTANCE
                .getProperty());
        return property;
    }

    private IPath getFolderPath(IProject project, ERepositoryObjectType repositoryObjectType, IPath relativePath)
            throws PersistenceException {
        IFolder folder = project.getFolder(ERepositoryObjectType.getFolderName(repositoryObjectType)).getFolder(relativePath);
        return folder.getFullPath();
    }

    public Resource createProjectResource(IProject project) {
        URI uri = getProjectResourceUri(project);
        return resourceSet.createResource(uri);
    }

    private URI getProjectResourceUri(IProject project) {
        URI uri = URI.createPlatformResourceURI(project.getFullPath().append(PROJECT_DESCRIPTION_FILE).toString());
        return uri;
    }

    public Resource createPropertyResource(Resource itemResource) {
        URI propertyResourceURI = getPropertyResourceURI(itemResource.getURI());
        return resourceSet.createResource(propertyResourceURI);
    }

    public Resource createItemResource(IProject project, Item item, IPath path, ERepositoryObjectType repositoryObjectType,
            boolean byteArrayResource) throws PersistenceException {
        URI itemResourceURI = getItemResourceURI(project, repositoryObjectType, path, item);

        Resource itemResource = createItemResource(byteArrayResource, itemResourceURI);

        return itemResource;
    }

    private Resource createItemResource(boolean byteArrayResource, URI itemResourceURI) {
        Resource itemResource;
        if (byteArrayResource) {
            itemResource = new ByteArrayResource(itemResourceURI);
            resourceSet.getResources().add(itemResource);
        } else {
            itemResource = resourceSet.createResource(itemResourceURI);
        }
        return itemResource;
    }

    public void deleteResource(Resource resource) throws PersistenceException {
        ResourceUtils.deleteFile(URIHelper.getFile(resource.getURI()));
        resourceSet.getResources().remove(resource);
    }

    public Resource getItemResource(Item item) {
        URI itemResourceURI = getItemResourceURI(item.eResource().getURI());
        Resource itemResource = resourceSet.getResource(itemResourceURI, true);

        // PTODO MHE usefull ?
        if (itemResource == null) {
            itemResource = new ByteArrayResource(itemResourceURI);
            resourceSet.getResources().add(itemResource);
        }

        return itemResource;
    }

    public List<Resource> getAffectedResources(Property property) {
        EcoreUtil.resolveAll(property.getItem());

        List<Resource> resources = new ArrayList<Resource>();

        Resource propertyResource = property.eResource();
        URI itemResourceURI = getItemResourceURI(propertyResource.getURI());
        Resource itemResource = resourceSet.getResource(itemResourceURI, true);
        resources.add(itemResource);
        resources.add(propertyResource);

        return resources;
    }

    public void moveResource(Resource resource, IPath path) throws PersistenceException {
        ResourceUtils.moveResource(URIHelper.getFile(resource.getURI()), path);
        resource.setURI(URIHelper.convert(path));
    }

    public void saveResource(Resource resource) throws PersistenceException {
        try {
            resource.save(null);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    private URI getItemResourceURI(URI propertyResourceURI) {
        return propertyResourceURI.trimFileExtension().appendFileExtension(ITEM_EXTENSION);
    }

    private URI getPropertyResourceURI(URI itemResourceURI) {
        return itemResourceURI.trimFileExtension().appendFileExtension(PROPERTIES_EXTENSION);
    }

    private URI getItemResourceURI(IProject project, ERepositoryObjectType repositoryObjectType, IPath path, Item item)
            throws PersistenceException {
        IPath folderPath = getFolderPath(project, repositoryObjectType, path);
        FileName fileName = ResourceFilenameHelper.create(item.getProperty());
        IPath resourcePath = ResourceFilenameHelper.getExpectedFilePath(fileName, folderPath, ITEM_EXTENSION);

        return URI.createPlatformResourceURI(resourcePath.toOSString());
    }

    public static boolean isPropertyFile(IFile file) {
        return PROPERTIES_EXTENSION.equals(file.getFileExtension());
    }

    public void propagateFileName(Property lastVersionProperty, Property resourceProperty) throws PersistenceException {
        List<Resource> affectedResources = getAffectedResources(resourceProperty);
        List<Resource> resourcesToSave = new ArrayList<Resource>();

        Property previousVersionProperty = null;

        for (Resource resource : affectedResources) {
            ResourceFilenameHelper.FileName fileName = ResourceFilenameHelper.create(resource, resourceProperty,
                    lastVersionProperty);

            if (ResourceFilenameHelper.mustChangeVersion(fileName)) {
                IPath path = URIHelper.convert(resource.getURI());
                IPath bakPath = path.addFileExtension("bak");

                // Create copy
                copyResource(resource, bakPath);
                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(bakPath);

                // move actual to new version
                moveResource(resource, ResourceFilenameHelper.getExpectedFilePath(fileName, false));
                resourcesToSave.add(resource);

                // restore copy as previous version
                ResourceUtils.moveResource(file, path);
                file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

                if (isPropertyFile(file)) {
                    previousVersionProperty = loadProperty(file);
                }
            } else if (ResourceFilenameHelper.mustChangeLabel(fileName)) {
                resourceProperty.setLabel(lastVersionProperty.getLabel());
                moveResource(resource, ResourceFilenameHelper.getExpectedFilePath(fileName, false));
                resourcesToSave.add(resource);
            }
        }

        if (previousVersionProperty != null) {
            List<Resource> previousVersionResources = getAffectedResources(previousVersionProperty);
            for (Resource resource : previousVersionResources) {
                FileName fileName = ResourceFilenameHelper.create(resource, previousVersionProperty, lastVersionProperty);

                if (ResourceFilenameHelper.mustChangeLabel(fileName)) {
                    IPath expectedFilePath = ResourceFilenameHelper.getExpectedFilePath(fileName, true);
                    previousVersionProperty.setLabel(lastVersionProperty.getLabel());
                    moveResource(resource, expectedFilePath);
                }
                resourcesToSave.add(resource);
            }
        }

        for (Resource resource : resourcesToSave) {
            saveResource(resource);
        }
    }

    private void copyResource(Resource resource, IPath path) throws PersistenceException {
        IFile file = URIHelper.getFile(resource.getURI());
        try {
            file.copy(path, true, null);
        } catch (CoreException e) {
            throw new PersistenceException(e);
        }
    }

    //we need to affect an id to each user, because users objects are cross referenced across differents xmi resource
    //also used to affect an id to users previously created without an id
    public boolean handleUserId(Project emfProject, User user) throws PersistenceException {
        int defaultId = 1;
        if (user.getId() <= defaultId) {
            Collection users = EcoreUtil.getObjectsByType(emfProject.eResource().getContents(), PropertiesPackage.eINSTANCE.getUser());
            
            for (Iterator iter = users.iterator(); iter.hasNext();) {
                User emfUser = (User) iter.next();
                defaultId = Math.max(defaultId, emfUser.getId());
            }
            
            user.setId(defaultId + 1);
            saveResource(emfProject.eResource());
            
            return true;
        }
        
        return false;
    }
}
