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
package org.talend.librariesmanager.model.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.components.IComponentsService;
import org.talend.librariesmanager.Activator;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class JavaLibrariesService extends AbstractLibrariesService {

    @Override
    public String getLibrariesPath() {
        return Platform.getInstallLocation().getURL().getFile() + "/lib/java";
    }

    @Override
    public URL getRoutineTemplate() {
        return Activator.BUNDLE.getEntry("resources/java/routines/Template.java");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSystemRoutines()
     */
    public List<URL> getSystemRoutines() {
        List<URL> toReturn = new ArrayList<URL>();

        Enumeration entryPaths = Activator.BUNDLE.getEntryPaths("resources/java/routines/system/");
        for (Enumeration enumer = entryPaths; enumer.hasMoreElements();) {
            String routine = (String) enumer.nextElement();
            if (routine.endsWith(".java")) {
                URL url = Activator.BUNDLE.getEntry(routine);
                toReturn.add(url);
            }
        }
        return toReturn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getTalendRoutines()
     */
    public URL getTalendRoutinesFolder() throws IOException {
        URL url = Activator.BUNDLE.getEntry("resources/java/talend"); //$NON-NLS-1$
        return FileLocator.resolve(url);
    }

    public void checkLibraries() {
        // TODO MHI/SML implements to check if java libs are installed
    }

    public void syncLibraries() {
        File target = new File(getLibrariesPath());
        try {
            // 1. libs livrées dans le plugin
            File source = new File(FileLocator.resolve(Activator.BUNDLE.getEntry("resources/java/lib/")).getFile());

            FilesUtils.copyFolder(source, target, false, FilesUtils.getExcludeSVNFilesFilter(), null, true);

            // 2. libs livrées dans les composants
            IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                    IComponentsService.class);
            File source2 = new File(service.getComponentsFactory().getComponentPath().getFile());
            FilesUtils.copyFolder(source2, target, false, FilesUtils.getExcludeSVNFilesFilter(), FilesUtils
                    .getAcceptJARFilesFilter(), false);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }

    }

}
