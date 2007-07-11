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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;

/**
 */
class ResourcesManagerFactory {

    private static ResourcesManagerFactory instance = new ResourcesManagerFactory();

    public ProviderManager createResourcesManager(IImportStructureProvider provider) {
        return new ProviderManager(provider);
    }

    public FilesManager createResourcesManager() {
        return new FilesManager();
    }

    public static ResourcesManagerFactory getInstance() {
        return instance;
    }

    /**
     */
    public class ProviderManager extends ResourcesManager {

        private IImportStructureProvider provider;

        public ProviderManager(IImportStructureProvider provider) {
            this.provider = provider;
        }

        public InputStream getStream(IPath path) {
            return provider.getContents(path2Object.get(path));
        }
    }

    /**
     */
    public class FilesManager extends ResourcesManager {

        public InputStream getStream(IPath path) throws FileNotFoundException {
            return new BufferedInputStream(new FileInputStream((File) path2Object.get(path)));
        }
    }

    /**
     */
    public abstract class ResourcesManager {

        protected Map<IPath, Object> path2Object = new HashMap<IPath, Object>();

        public void add(String path, Object object) {
            path2Object.put(new Path(path), object);
        }

        public Set<IPath> getPaths() {
            return path2Object.keySet();
        }

        public abstract InputStream getStream(IPath path) throws FileNotFoundException;
    }
}
