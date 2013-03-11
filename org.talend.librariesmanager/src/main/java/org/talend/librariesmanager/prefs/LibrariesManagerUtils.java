// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.librariesmanager.prefs;

import org.eclipse.core.runtime.Platform;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ILibraryManagerUIService;
import org.talend.core.language.ECodeLanguage;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 */
public class LibrariesManagerUtils {

    public static final String BUNDLE_DI = "org.talend.librariesmanager";

    public static String getLibrariesPath(ECodeLanguage language) {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibraryManagerUIService.class)) {
            ILibraryManagerUIService libUiService = (ILibraryManagerUIService) GlobalServiceRegister.getDefault().getService(
                    ILibraryManagerUIService.class);
            return libUiService.getLibrariesPath(language);

        }
        return Platform.getInstallLocation().getURL().getFile() + "lib/java";
    }

}
