// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.routines;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;

/**
 * wchen class global comment. Detailled comment
 */
public class RoutineLibraryMananger {

    private Map<String, List<URI>> routineAndJars = null;

    private static RoutineLibraryMananger manager = new RoutineLibraryMananger();

    public static RoutineLibraryMananger getInstance() {
        return manager;
    }

    public Map<String, List<URI>> getRoutineAndJars() {
        if (routineAndJars == null) {
            routineAndJars = new HashMap<String, List<URI>>();
            IExtensionRegistry registry = Platform.getExtensionRegistry();
            IConfigurationElement[] configurationElements = registry
                    .getConfigurationElementsFor("org.talend.core.systemRoutineLibrary"); //$NON-NLS-1$

            ECodeLanguage projectLanguage = ((RepositoryContext) CorePlugin.getContext().getProperty(
                    Context.REPOSITORY_CONTEXT_KEY)).getProject().getLanguage();
            for (IConfigurationElement current : configurationElements) {
                ECodeLanguage lang = ECodeLanguage.getCodeLanguage(current.getAttribute("language")); //$NON-NLS-1$
                if (lang == projectLanguage) {
                    String routineName = current.getAttribute("routineName"); //$NON-NLS-1$
                    String moduleName = current.getAttribute("moduleName"); //$NON-NLS-1$
                    List<URI> list = routineAndJars.get(routineName);
                    Bundle bundle = Platform.getBundle(current.getContributor().getName());
                    URL url = bundle.getEntry("/lib/" + moduleName);
                    URL fileUrl;
                    try {
                        fileUrl = FileLocator.toFileURL(url);
                    } catch (IOException e1) {
                        ExceptionHandler.process(e1);
                        continue;
                    }
                    if (url == null) {
                        ExceptionHandler.process(new Exception("jar not found:" + current.getContributor().getName() + "/lib/"
                                + moduleName));
                        continue;
                    }
                    if (list == null) {
                        list = new ArrayList<URI>();
                        try {
                            list.add(fileUrl.toURI());
                        } catch (URISyntaxException e) {
                            ExceptionHandler.process(e);
                            continue;
                        }
                        routineAndJars.put(routineName, list);
                    } else {
                        try {
                            list.add(fileUrl.toURI());
                        } catch (URISyntaxException e) {
                            ExceptionHandler.process(e);
                        }
                    }
                }
            }
        }

        return routineAndJars;
    }
}
