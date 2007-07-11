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
package org.talend.repository.localprovider.ui.wizard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.helper.ByteArrayResource;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.constants.FileConstants;
import org.talend.repository.localprovider.RepositoryLocalProviderPlugin;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.localprovider.model.XmiResourceManager;
import org.talend.repository.localprovider.ui.wizard.ResourcesManagerFactory.ResourcesManager;
import org.talend.repository.model.ProxyRepositoryFactory;

/**
 */
class RepositoryUtil {

    private static final String SEGMENT_PARENT = ".."; //$NON-NLS-1$

    private XmiResourceManager xmiResourceManager = new XmiResourceManager();

    private boolean hasErrors = false;

    public void setErrors(boolean errors) {
        this.hasErrors = errors;
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    public void populateValidItems(List validItems, ItemRecord itemRecord, List<String> errors) {
        String error = null;

        try {
            boolean nameAvailable = ProxyRepositoryFactory.getInstance().isNameAvailable(itemRecord.getItem(),
                    itemRecord.getItem().getProperty().getLabel());
            boolean idAvailable = ProxyRepositoryFactory.getInstance().getLastVersion(
                    itemRecord.getItem().getProperty().getId()) == null;

            boolean isSystemRoutine = false;
            // we do not import built in routines
            if (itemRecord.getItem().eClass().equals(PropertiesPackage.eINSTANCE.getRoutineItem())) {
                RoutineItem routineItem = (RoutineItem) itemRecord.getItem();
                if (routineItem.isBuiltIn()) {
                    isSystemRoutine = true;
                }
            }

            if (nameAvailable) {
                if (idAvailable) {
                    if (!isSystemRoutine) {
                        validItems.add(itemRecord);
                    } else {
                        error = getQuotedItemName(itemRecord) + Messages.getString("RepositoryUtil.isSystemRoutine"); //$NON-NLS-1$ 
                    }
                } else {
                    error = getQuotedItemName(itemRecord) + Messages.getString("RepositoryUtil.idUsed"); //$NON-NLS-1$
                }
            } else {
                error = getQuotedItemName(itemRecord) + Messages.getString("RepositoryUtil.nameUsed"); //$NON-NLS-1$
            }
        } catch (PersistenceException e) {
            // ignore
        }

        if (error != null && !errors.contains(error)) {
            errors.add(error);
        }
    }

    private String getQuotedItemName(ItemRecord itemRecord) {
        return "\"" + itemRecord.getItemName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
    }

    public void importItemRecord(ResourcesManager manager, ItemRecord itemRecord) {
        resolveItem(manager, itemRecord);
        if (itemRecord.getItem() != null) {
            ERepositoryObjectType itemType = ERepositoryObjectType.getItemType(itemRecord.getItem());
            IPath path = new Path(itemRecord.getItem().getState().getPath());

            List<String> folders = null;
            try {
                folders = ProxyRepositoryFactory.getInstance().getFolders(itemType);
            } catch (Exception e) {
                logError(e);
            }

            boolean foldersCreated = true;

            try {
                for (int i = 0; i < path.segmentCount(); i++) {
                    IPath parentPath = path.removeLastSegments(path.segmentCount() - i);
                    String folderLabel = path.segment(i);

                    String folderName = parentPath.append(folderLabel).toString();
                    if (!folders.contains(folderName)) {
                        ProxyRepositoryFactory.getInstance().createFolder(itemType, parentPath, folderLabel);
                    }
                }
            } catch (Exception e) {
                foldersCreated = false;
                logError(e);
            }

            if (!foldersCreated) {
                path = new Path(""); //$NON-NLS-1$
            }

            try {
                ProxyRepositoryFactory.getInstance().create(itemRecord.getItem(), path);
            } catch (Exception e) {
                logError(e);
            }

        }
    }

    private void logError(Exception e) {
        hasErrors = true;
        IStatus status;
        String messageStatus = e.getMessage() != null ? e.getMessage() : ""; //$NON-NLS-1$
        status = new Status(IStatus.ERROR, RepositoryLocalProviderPlugin.PLUGIN_ID, IStatus.OK, messageStatus, e);
        RepositoryLocalProviderPlugin.getDefault().getLog().log(status);
    }

    public Collection<ItemRecord> populateItems(ResourcesManager collector, List<String> errors) {
        List<ItemRecord> items = new ArrayList<ItemRecord>();

        for (IPath path : collector.getPaths()) {
            if (isPropertyPath(path)) {
                IPath itemPath = getItemPath(path);
                if (collector.getPaths().contains(itemPath)) {
                    Property property = computeProperty(collector, path);
                    if (property != null) {
                        ItemRecord itemRecord = new ItemRecord(path, property);

                        InternalEObject author = (InternalEObject) property.getAuthor();
                        property.setAuthor(null); // the author will be the one who import items

                        IPath projectFilePath = getValidProjectFilePath(collector, path, author);
                        if (projectFilePath != null) {
                            Project project = computeProject(collector, itemRecord, projectFilePath);
                            boolean checkProject = checkProject(errors, project, itemRecord);
                            if (checkProject) {
                                items.add(itemRecord);
                            }
                        } else {
                            errors.add(getQuotedItemName(itemRecord)
                                    + Messages.getString("RepositoryUtil.ProjectNotFound")); //$NON-NLS-1$
                        }
                    }
                }
            }
        }

        return items;
    }

    private boolean checkProject(List<String> errors, Project project, ItemRecord itemRecord) {
        boolean checkProject = false;

        Context ctx = CorePlugin.getContext();
        RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        Project currentProject = repositoryContext.getProject().getEmfProject();

        if (project != null) {
            if (project.getLanguage().equals(currentProject.getLanguage())) {
                if (checkMigrationTasks(errors, project, itemRecord, currentProject)) {
                    checkProject = true;
                }
            } else {
                errors.add(getQuotedItemName(itemRecord)
                        + Messages.getString("RepositoryUtil.DifferentLanguage", project.getLanguage(), currentProject //$NON-NLS-1$
                                .getLanguage()));
            }
        } else {
            errors.add(getQuotedItemName(itemRecord) + Messages.getString("RepositoryUtil.ProjectNotFound")); //$NON-NLS-1$
        }

        return checkProject;
    }

    private boolean checkMigrationTasks(List<String> errors, Project project, ItemRecord itemRecord,
            Project currentProject) {
        boolean result = true;

        if (project.getMigrationTasks().size() == currentProject.getMigrationTasks().size()) {
            for (Iterator iter = project.getMigrationTasks().iterator(); iter.hasNext();) {
                String element = (String) iter.next();
                boolean found = false;
                for (Iterator iterator = currentProject.getMigrationTasks().iterator(); iterator.hasNext();) {
                    String element2 = (String) iterator.next();
                    if (element.equals(element2)) {
                        found = true;
                    }
                }
                if (!found) {
                    result = false;
                }
            }
        } else {
            result = false;
        }

        if (!result) {
            errors.add(getQuotedItemName(itemRecord) + " " //$NON-NLS-1$
                    + Messages.getString("RepositoryUtil.DifferentVersion")); //$NON-NLS-1$
        }

        return result;
    }

    private IPath getValidProjectFilePath(ResourcesManager collector, IPath path, InternalEObject author) {
        IPath projectFilePath = getSiblingProjectFilePath(path);
        if (!collector.getPaths().contains(projectFilePath)) {
            projectFilePath = getAuthorProjectFilePath(author, path);
            if (!collector.getPaths().contains(projectFilePath)) {
                return null;
            }
        }

        return projectFilePath;
    }

    private IPath getAuthorProjectFilePath(InternalEObject author, IPath path) {
        IPath projectFilePath = path.removeLastSegments(1);

        if (author != null) {
            URI uri = author.eProxyURI();
            for (int i = 0; i < uri.segments().length; i++) {
                String segment = uri.segments()[i];
                if (segment.equals(SEGMENT_PARENT)) {
                    projectFilePath = projectFilePath.removeLastSegments(1);
                } else {
                    projectFilePath = projectFilePath.append(FileConstants.LOCAL_PROJECT_FILENAME);
                }
            }
        }

        return projectFilePath;
    }

	//usefull when you export a job with source in an archive
    private IPath getSiblingProjectFilePath(IPath path) {
        IPath projectFilePath = path.removeLastSegments(1);
        projectFilePath = projectFilePath.append(FileConstants.LOCAL_PROJECT_FILENAME);

        return projectFilePath;
    }

    private Property computeProperty(ResourcesManager manager, IPath path) {
        InputStream stream = null;
        try {
            ResourceSet resourceSet = new ResourceSetImpl();
            stream = manager.getStream(path);
            Resource resource = createResource(resourceSet, path, false);
            resource.load(stream, null);
            return (Property) EcoreUtil.getObjectByType(resource.getContents(), PropertiesPackage.eINSTANCE
                    .getProperty());
        } catch (IOException e) {
            // ignore
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        return null;
    }

    public void resolveItem(ResourcesManager manager, ItemRecord itemRecord) {
        InputStream stream = null;
        ResourceSet resourceSet = itemRecord.getProperty().eResource().getResourceSet();

        try {
            boolean byteArray = (itemRecord.getItem() instanceof FileItem);
            IPath itemPath = getItemPath(itemRecord.getPath());
            stream = manager.getStream(itemPath);
            Resource resource = createResource(resourceSet, itemPath, byteArray);
            resource.load(stream, null);
            EcoreUtil.resolveAll(resourceSet);
        } catch (IOException e) {
            // ignore
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    public Project computeProject(ResourcesManager manager, ItemRecord itemRecord, IPath path) {
        InputStream stream = null;
        ResourceSet resourceSet = itemRecord.getProperty().eResource().getResourceSet();

        try {
            stream = manager.getStream(path);
            Resource resource = createResource(resourceSet, path, false);
            resource.load(stream, null);
            return (Project) EcoreUtil
                    .getObjectByType(resource.getContents(), PropertiesPackage.eINSTANCE.getProject());
        } catch (IOException e) {
            // ignore
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return null;
    }

    public Resource createResource(ResourceSet resourceSet, IPath path, boolean byteArrayResource)
            throws FileNotFoundException {
        Resource resource;
        if (byteArrayResource) {
            resource = new ByteArrayResource(getURI(path));
            resourceSet.getResources().add(resource);
        } else {
            resource = resourceSet.createResource(getURI(path));
        }
        return resource;
    }

    private URI getURI(IPath path) {
        return URI.createURI(path.lastSegment());
    }

    private boolean isPropertyPath(IPath path) {
        return xmiResourceManager.isPropertyFile(path.lastSegment());
    }

    private IPath getItemPath(IPath path) {
        return path.removeFileExtension().addFileExtension(XmiResourceManager.ITEM_EXTENSION);
    }

}
