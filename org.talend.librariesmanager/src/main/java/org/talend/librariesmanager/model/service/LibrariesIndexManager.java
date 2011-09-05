// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.commons.emf.EmfHelper;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.language.ECodeLanguage;
import org.talend.librariesmanager.emf.librariesindex.LibrariesIndex;
import org.talend.librariesmanager.emf.librariesindex.LibrariesindexFactory;
import org.talend.librariesmanager.emf.librariesindex.util.LibrariesindexResourceFactoryImpl;
import org.talend.librariesmanager.prefs.PreferencesUtilities;

public class LibrariesIndexManager {

    private static LibrariesIndex index;

    private static final String LIBRARIES_INDEX = "index.xml";

    public static LibrariesIndex getInstance() {
        if (index == null) {
            index = LibrariesindexFactory.eINSTANCE.createLibrariesIndex();
        }
        return index;
    }

    public static void saveResource() {
        PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
        String installLocation = PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
        try {
            Resource resource = createLibrariesIndexResource(installLocation);
            resource.getContents().add(index);
            EmfHelper.saveResource(index.eResource());
        } catch (PersistenceException e1) {
            // TODO Auto-generated catch block
            ExceptionHandler.process(e1);
        }
    }

    public static Resource createLibrariesIndexResource(String installLocation) {
        URI uri = URI.createFileURI(installLocation).appendSegment(LIBRARIES_INDEX);
        LibrariesindexResourceFactoryImpl indexFact = new LibrariesindexResourceFactoryImpl();
        return indexFact.createResource(uri);
    }
}
