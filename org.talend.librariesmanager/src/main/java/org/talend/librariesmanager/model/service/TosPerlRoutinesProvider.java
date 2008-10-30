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
package org.talend.librariesmanager.model.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.routines.IRoutinesProvider;
import org.talend.librariesmanager.Activator;

/**
 * class global comment. Detailled comment
 */
public class TosPerlRoutinesProvider implements IRoutinesProvider {

    /**
     * TosJavaRoutinesProvider constructor comment.
     */
    public TosPerlRoutinesProvider() {
    }

    public List<URL> getSystemRoutines() {
        List<URL> toReturn = FilesUtils.getFilesFromFolder(Activator.BUNDLE, "resources/perl/"
                + ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + "/system/", ".pm");

        return toReturn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.routines.IRoutinesProvider#getTalendRoutinesFolder()
     */
    public URL getTalendRoutinesFolder() throws IOException {
        URL url = Activator.BUNDLE.getEntry("resources/perl/talend");
        return FileLocator.toFileURL(url);
    }

    public List<URL> getTalendRoutines() {
        List<URL> toReturn = FilesUtils.getFilesFromFolder(Activator.BUNDLE, "resources/perl/talend", "");
        return toReturn;
    }
}
