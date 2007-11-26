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
package org.talend.repository.localprovider.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.workbench.resources.ResourceUtils;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.helper.ByteArrayResource;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.constants.FileConstants;
import org.talend.repository.local.ResourceFilenameHelper;
import org.talend.repository.local.ResourceFilenameHelper.FileName;
import org.talend.repository.model.URIHelper;

/**
 * DOC mhelleboid class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class XmiResourceManager {

    private static final String OLD_PROJECT_FILENAME = "talendProject"; //$NON-NLS-1$

    // PTODO mhelleboid should use a custom ResourceFactory
    // PTODO mhelleboid test duplicate resourcesUri in resourceSet !
    private static ResourceSet resourceSet = new ResourceSetImpl();

    private boolean useOldProjectFile;

    public XmiResourceManager() {
        setUseOldProjectFile(false);
    }

    public Project loadProject(IProject project) throws PersistenceException {
        URI uri = getProjectResourceUri(project);

        Resource resource = resourceSet.getResource(uri, true);
        Project emfProject = (Project) EcoreUtil
                .getObjectByType(resource.getContents(), PropertiesPackage.eINSTANCE.getProject());
        return emfProject;
    }

    public boolean hasTalendProjectFile(IProject project) {
        URI uri = getProjectResourceUri(project);
        IPath path = URIHelper.convert(uri);
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
        return file.exists();
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
        URI uri = URI.createPlatformResourceURI(project.getFullPath().append(getProjectFilename()).toString());
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
        Resource itemResource = resourceSet.getResource(itemResourceURI, false);

        if (itemResource == null) {
            if (item instanceof FileItem) {
                itemResource = new ByteArrayResource(itemResourceURI);
                resourceSet.getResources().add(itemResource);
            }
            itemResource = resourceSet.getResource(itemResourceURI, true);
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
            HashMap options = new HashMap(2);
            options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
            options.put(XMLResource.OPTION_XML_VERSION, "1.1"); //$NON-NLS-1$
            resource.save(options);
            // resource.save(null);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    private URI getItemResourceURI(URI propertyResourceURI) {
        return propertyResourceURI.trimFileExtension().appendFileExtension(FileConstants.ITEM_EXTENSION);
    }

    private URI getPropertyResourceURI(URI itemResourceURI) {
        return itemResourceURI.trimFileExtension().appendFileExtension(FileConstants.PROPERTIES_EXTENSION);
    }

    private URI getItemResourceURI(IProject project, ERepositoryObjectType repositoryObjectType, IPath path, Item item)
            throws PersistenceException {
        IPath folderPath = getFolderPath(project, repositoryObjectType, path);
        FileName fileName = ResourceFilenameHelper.create(item.getProperty());
        IPath resourcePath = ResourceFilenameHelper.getExpectedFilePath(fileName, folderPath, FileConstants.ITEM_EXTENSION);

        return URI.createPlatformResourceURI(resourcePath.toOSString());
    }

    public boolean isPropertyFile(IFile file) {
        return FileConstants.PROPERTIES_EXTENSION.equals(file.getFileExtension());
    }

    public boolean isPropertyFile(File file) {
        return file.getAbsolutePath().endsWith(FileConstants.PROPERTIES_EXTENSION);
    }

    public boolean isPropertyFile(String filename) {
        return filename.endsWith(FileConstants.PROPERTIES_EXTENSION);
    }

    public boolean isProjectFile(IFile file) {
        return getProjectFilename().equals(file.getName());
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
                IPath bakPath = path.addFileExtension("bak"); //$NON-NLS-1$

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

    private String getProjectFilename() {
        if (useOldProjectFile) {
            return OLD_PROJECT_FILENAME;
        } else {
            return FileConstants.LOCAL_PROJECT_FILENAME;
        }
    }

    public void setUseOldProjectFile(boolean useOldProjectFile) {
        this.useOldProjectFile = useOldProjectFile;
    }

    public void unloadResources() {
        List<Resource> resources = new ArrayList<Resource>(resourceSet.getResources());
        for (Resource resource : resources) {
            resource.unload();
            resourceSet.getResources().remove(resource);
        }
    }
}
