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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.internal.wizards.datatransfer.WizardProjectsImportPage;

/**
 */
public class FilesManager extends ResourcesManager {

    public InputStream getStream(IPath path) throws IOException {
        return new BufferedInputStream(new FileInputStream((File) path2Object.get(path)));
    }

    public boolean collectPath2Object(Object root) {
        return doCollectItemFiles((File) root);
    }

    private boolean doCollectItemFiles(File directory) {
        File[] contents = directory.listFiles();

        if (contents != null) {
            for (int i = 0; i < contents.length; i++) {
                File file = contents[i];

                if (file.isFile()) {
                    add(file.getAbsolutePath(), file);
                }
                if (file.isDirectory()) {
                    if (!contents[i].getName().equals(WizardProjectsImportPage.METADATA_FOLDER)) {
                        collectPath2Object(contents[i]);
                    }
                }
            }
        }
        return true;
    }
}
