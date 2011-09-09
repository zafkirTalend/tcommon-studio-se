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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.talend.commons.emf.EmfHelper;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.language.ECodeLanguage;
import org.talend.librariesmanager.emf.librariesindex.LibrariesIndex;
import org.talend.librariesmanager.emf.librariesindex.LibrariesindexFactory;
import org.talend.librariesmanager.emf.librariesindex.LibrariesindexPackage;
import org.talend.librariesmanager.emf.librariesindex.util.LibrariesindexResourceFactoryImpl;
import org.talend.librariesmanager.prefs.PreferencesUtilities;

public class LibrariesIndexManager {

    private LibrariesIndex index;

    private static LibrariesIndexManager manager;

    private static final String LIBRARIES_INDEX = "index.xml";

    private boolean loaded = false;

    private LibrariesIndexManager() {
        index = LibrariesindexFactory.eINSTANCE.createLibrariesIndex();
    }

    public static LibrariesIndexManager getInstance() {
        if (manager == null) {
            manager = new LibrariesIndexManager();
        }
        return manager;
    }

    public void loadResource() {
        if (!loaded) {
            String installLocation = PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
            try {
                Resource resource = createLibrariesIndexResource(installLocation);
                Map optionMap = new HashMap();
                optionMap.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
                optionMap.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
                optionMap.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
                optionMap.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());
                optionMap.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
                resource.load(optionMap);
                index = (LibrariesIndex) EcoreUtil.getObjectByType(resource.getContents(),
                        LibrariesindexPackage.eINSTANCE.getLibrariesIndex());
            } catch (IOException e) {
                ExceptionHandler.process(e);
            }
            loaded = true;
        }
    }

    public void saveResource() {
        PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
        String installLocation = PreferencesUtilities.getLibrariesPath(ECodeLanguage.JAVA);
        try {
            Resource resource = createLibrariesIndexResource(installLocation);
            resource.getContents().add(index);
            EmfHelper.saveResource(index.eResource());
        } catch (PersistenceException e1) {
            ExceptionHandler.process(e1);
        }
    }

    private Resource createLibrariesIndexResource(String installLocation) {
        URI uri = URI.createFileURI(installLocation).appendSegment(LIBRARIES_INDEX);
        LibrariesindexResourceFactoryImpl indexFact = new LibrariesindexResourceFactoryImpl();
        return indexFact.createResource(uri);
    }

    public LibrariesIndex getIndex() {
        return index;
    }

    public void setIndex(LibrariesIndex index) {
        this.index = index;
    }
}
