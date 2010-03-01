// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;

/**
 * wchen class global comment. Detailled comment
 */
public class RoutineLibraryMananger {

    private Map<String, List<String>> routineAndJars = null;

    private static RoutineLibraryMananger manager = new RoutineLibraryMananger();

    public static RoutineLibraryMananger getInstance() {
        return manager;
    }

    public Map<String, List<String>> getRoutineAndJars() {
        if (routineAndJars == null) {
            routineAndJars = new HashMap<String, List<String>>();
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
                    List<String> list = routineAndJars.get(routineName);
                    if (list == null) {
                        list = new ArrayList<String>();
                        list.add(moduleName);
                        routineAndJars.put(routineName, list);
                    } else {
                        list.add(moduleName);
                    }
                }
            }
        }

        return routineAndJars;
    }
}
