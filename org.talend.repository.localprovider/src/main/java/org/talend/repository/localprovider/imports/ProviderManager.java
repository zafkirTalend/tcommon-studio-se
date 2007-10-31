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
package org.talend.repository.localprovider.imports;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;

/**
 */
public class ProviderManager extends ResourcesManager {

    private IImportStructureProvider provider;

    public ProviderManager(Object provider) {
        this.provider = (IImportStructureProvider) provider;
    }

    public InputStream getStream(IPath path) {
        return provider.getContents(path2Object.get(path));
    }

    public boolean collectPath2Object(Object root) {
        return doCollectItemFiles(root, 0);
    }

    private boolean doCollectItemFiles(Object entry, int level) {
        List children = provider.getChildren(entry);
        if (children == null) {
            children = new ArrayList(1);
        }
        Iterator childrenEnum = children.iterator();
        while (childrenEnum.hasNext()) {
            Object child = childrenEnum.next();
            if (provider.isFolder(child)) {
                doCollectItemFiles(child, level + 1);
            } else {
                add(provider.getFullPath(child), child);
            }
        }
        return true;
    }
}
