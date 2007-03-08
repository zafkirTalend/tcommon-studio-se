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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Level;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.designer.runprocess.IRunProcessService;
import org.talend.designer.runprocess.ProcessorException;
import org.talend.librariesmanager.Activator;
import org.talend.librariesmanager.model.ModulesNeededProvider;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class PerlLibrariesService extends AbstractLibrariesService {

    @Override
    public String getLibrariesPath() {
        return Platform.getInstallLocation().getURL().getFile() + "/libs/perl";
    }

    @Override
    public URL getRoutineTemplate() {
        return Activator.BUNDLE.getEntry("resources/perl/routines/Template.pm");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getSystemRoutines()
     */
    public List<URL> getSystemRoutines() {
        return FilesUtils.getFilesFromFolder(Activator.BUNDLE, "resources/perl/routines/system/", ".pm");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#getTalendRoutines()
     */
    public URL getTalendRoutinesFolder() throws IOException {
        URL url = Activator.BUNDLE.getEntry("resources/perl/talend");
        return FileLocator.toFileURL(url);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.general.ILibrariesService#syncLibraries()
     */
    public void syncLibraries() {
        try {
            File source = new File(FileLocator.resolve(Activator.BUNDLE.getEntry("resources/perl/talend/")).getFile());
            File target = new File(getLibrariesPath());

            FilesUtils.copyFolder(source, target, false, FilesUtils.getExcludeSVNFilesFilter(), null, true);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
    }

    public void checkLibraries() {
        List<ModuleNeeded> toCheck = ModulesNeededProvider.getModulesNeeded();

        if (toCheck.isEmpty()) {
            return;
        }

        // This map contains perl module name as keys and list of object using it as values :
        Map<String, List<ModuleNeeded>> componentsByModules = new HashMap<String, List<ModuleNeeded>>();

        String[] params = new String[] {};
        for (ModuleNeeded current : toCheck) {
            String moduleName = current.getModuleName();
            List<ModuleNeeded> listForThisModule = componentsByModules.get(moduleName);
            if (listForThisModule == null) {
                // We have a new perl module to check :
                listForThisModule = new ArrayList<ModuleNeeded>();
                // Add it in the map :
                componentsByModules.put(moduleName, listForThisModule);
                // And in the params perl command line :
                params = (String[]) ArrayUtils.add(params, MODULE_PARAM_KEY + moduleName);
            }
            // Add this import in the perl module list :
            listForThisModule.add(current);

            // Set the status to unknow as after treatment, modules not in perl response are unknown
            current.setStatus(ELibraryInstallStatus.UNKNOWN);
        }

        try {
            String checkPerlModuleAbsolutePath = FileLocator
                    .toFileURL(Activator.BUNDLE.getEntry(CHECK_PERL_MODULE_RELATIVE_PATH)).getPath();

            StringBuffer out = new StringBuffer();
            StringBuffer err = new StringBuffer();

            IRunProcessService service = (IRunProcessService) GlobalServiceRegister.getDefault().getService(
                    IRunProcessService.class);
            service.perlExec(out, err, new Path(checkPerlModuleAbsolutePath), null, Level.DEBUG, "", "", -1, -1, params);

            analyzeResponse(out, componentsByModules);

            if (err.length() > 0) {
                throw new ProcessorException(err.toString());
            }

        } catch (IOException e) {
            ExceptionHandler.process(e);
        } catch (ProcessorException e) {
            MessageBoxExceptionHandler.process(e);
        }

    }

    private static final String CHECK_PERL_MODULE_RELATIVE_PATH = "resources/perl/check_modules.pl";

    private static final String MODULE_PARAM_KEY = "--module=";

    private static final String RESULT_SEPARATOR = " => ";

    private static final String RESULT_KEY_KO = "KO";

    private static final String RESULT_KEY_OK = "OK";

    private void analyzeResponse(StringBuffer buff, Map<String, List<ModuleNeeded>> componentsByModules) {

        String[] lines = buff.toString().split("\n");
        for (String line : lines) {
            if (line != null && line.length() > 0) {
                // Treat a perl response line :
                String[] elts = line.split(RESULT_SEPARATOR);

                List<ModuleNeeded> componentsToTreat = componentsByModules.get(elts[0]);

                if (componentsToTreat != null) {
                    // Define status regarding the perl response :
                    ELibraryInstallStatus status = ELibraryInstallStatus.UNKNOWN;
                    if (elts[1].startsWith(RESULT_KEY_OK)) {
                        status = ELibraryInstallStatus.INSTALLED;
                    } else if (elts[1].startsWith(RESULT_KEY_KO)) {
                        status = ELibraryInstallStatus.NOT_INSTALLED;
                    }

                    // Step on objects using this module and set their status :
                    for (ModuleNeeded current : componentsToTreat) {
                        current.setStatus(status);
                    }
                }
            }

        }
    }

}
