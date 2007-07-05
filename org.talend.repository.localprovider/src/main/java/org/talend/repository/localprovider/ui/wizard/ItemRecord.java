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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.helper.ByteArrayResource;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 */
public class ItemRecord {

    File itemSystemFile;

    File itemSystemFile2;

    Object itemArchiveFile;

    Object itemArchiveFile2;

    String itemName;

    Object parent;

    int level;

    IImportStructureProvider provider;

    private boolean valid = false;

    private Property property;

    private int mode;
    private static final int ZIP_MODE = 0;
    private static final int FILE_MODE = 1;
    
    public boolean isValid() {
        return valid;
    }

    ItemRecord(ImportItemWizardPage page, File file, File file2) {
        mode = FILE_MODE;
        itemSystemFile = file;
        itemSystemFile2 = file2;
        computeProperty();
    }

    ItemRecord(Object file, Object file2, Object parent, int level, IImportStructureProvider entryProvider) {
        mode = ZIP_MODE;
        this.itemArchiveFile = file;
        this.itemArchiveFile2 = file2;
        this.parent = parent;
        this.level = level;
        this.provider = entryProvider;
        computeProperty();
    }

    private void computeProperty() {
        InputStream stream = null;
        try {
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource;
            if (mode == ZIP_MODE) {
                stream = provider.getContents(itemArchiveFile);
                resource = resourceSet.createResource(getURI(itemArchiveFile.toString()));
            } else {
                stream = new BufferedInputStream(new FileInputStream(itemSystemFile));
                resource = resourceSet.createResource(getURI(itemSystemFile.toString()));
            }
            resource.load(stream, null);
            Property property = (Property) EcoreUtil.getObjectByType(resource.getContents(),
                    PropertiesPackage.eINSTANCE.getProperty());

            itemName = ERepositoryObjectType.getItemType(property.getItem()).toString() + " " + property.getLabel() //$NON-NLS-1$
                    + " " + property.getVersion(); //$NON-NLS-1$

            property.setAuthor(null);
            this.property = property;

            valid = true;
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

    private URI getURI(String path) {
        String separator;
        if (mode == ZIP_MODE) {
            separator = "/";
        } else {
            separator = File.separator;
        }
        String filename =             path.substring(path.lastIndexOf(separator) + 1); //$NON-NLS-1$
        return URI.createURI(filename);
    }

    public String getItemName() {
        return itemName;
    }

    public Item getItem() {
        return property.getItem();
    }

    public void resolveItem() {
        InputStream stream2 = null;
        Resource resource2;
        ResourceSet resourceSet = property.eResource().getResourceSet();

        try {
            if (property.getItem() instanceof FileItem) {
                if (mode == ZIP_MODE) {
                    stream2 = provider.getContents(itemArchiveFile2);
                    resource2 = new ByteArrayResource(getURI(itemArchiveFile2.toString()));
                } else {
                    stream2 = new BufferedInputStream(new FileInputStream(itemSystemFile2));
                    resource2 = new ByteArrayResource(getURI(itemSystemFile2.toString()));
                }
                resourceSet.getResources().add(resource2);
            } else {
                if (mode == ZIP_MODE) {
                    stream2 = provider.getContents(itemArchiveFile2);
                    resource2 = resourceSet.createResource(getURI(itemArchiveFile2.toString()));
                } else {
                    stream2 = new BufferedInputStream(new FileInputStream(itemSystemFile2));
                    resource2 = resourceSet.createResource(getURI(itemSystemFile2.toString()));
                }
            }
            resource2.load(stream2, null);
            EcoreUtil.resolveAll(resourceSet);
        } catch (IOException e) {
            // ignore
        } finally {
            if (stream2 != null) {
                try {
                    stream2.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}