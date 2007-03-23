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
package org.talend.librariesmanager.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.EList;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider;
import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.model.utils.emf.component.IMPORTType;
import org.talend.repository.model.ComponentsFactoryProvider;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: ModulesNeededProvider.java 1893 2007-02-07 11:33:35Z mhirt $
 * 
 */
public class ModulesNeededProvider {

    private static List<ModuleNeeded> componentImportNeedsList = new ArrayList<ModuleNeeded>();;

    public static List<ModuleNeeded> getModulesNeeded() {
        if (componentImportNeedsList.isEmpty()) {
            componentImportNeedsList.addAll(getModulesNeededForRoutines());
            componentImportNeedsList.addAll(getModulesNeededForComponents());
            componentImportNeedsList.addAll(getModulesNeededForApplication());
        }

        return componentImportNeedsList;
    }

    public static void reset() {
        componentImportNeedsList.clear();
    }

    private static List<ModuleNeeded> getModulesNeededForComponents() {
        List<ModuleNeeded> importNeedsList = new ArrayList<ModuleNeeded>();
        IComponentsFactory compFac = ComponentsFactoryProvider.getInstance();
        List<IComponent> componentList = compFac.getComponents();
        for (IComponent component : componentList) {
            importNeedsList.addAll(component.getModulesNeeded());
        }
        return importNeedsList;
    }

    private static List<ModuleNeeded> getModulesNeededForRoutines() {
        List<ModuleNeeded> importNeedsList = new ArrayList<ModuleNeeded>();
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        try {
            List<IRepositoryObject> routines = repositoryFactory.getAll(ERepositoryObjectType.ROUTINES, true);
            for (IRepositoryObject current : routines) {
                Item item = current.getProperty().getItem();
                RoutineItem routine = (RoutineItem) item;
                EList imports = routine.getImports();
                for (Object o : imports) {
                    IMPORTType currentImport = (IMPORTType) o;
                    // FIXME SML i18n
                    ModuleNeeded toAdd = new ModuleNeeded("Routine " + currentImport.getNAME(), currentImport.getMODULE(),
                            currentImport.getMESSAGE(), currentImport.isREQUIRED());
                    importNeedsList.add(toAdd);
                }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return importNeedsList;
    }

    private static List<ModuleNeeded> getModulesNeededForApplication() {
        List<ModuleNeeded> importNeedsList = new ArrayList<ModuleNeeded>();

        IExtensionPointLimiter actionExtensionPoint = new ExtensionPointLimiterImpl(
                "org.talend.core.librariesNeeded", "libraryNeeded"); //$NON-NLS-1$ //$NON-NLS-2$
        List<IConfigurationElement> extension = ExtensionImplementationProvider.getInstanceV2(actionExtensionPoint);

        ECodeLanguage projectLanguage = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getProject().getLanguage();
        for (IConfigurationElement current : extension) {
            ECodeLanguage lang = ECodeLanguage.getCodeLanguage(current.getAttribute("language")); //$NON-NLS-1$
            if (lang == projectLanguage) {
                String context = current.getAttribute("context"); //$NON-NLS-1$
                String name = current.getAttribute("name"); //$NON-NLS-1$
                String message = current.getAttribute("message"); //$NON-NLS-1$
                boolean required = new Boolean(current.getAttribute("required")); //$NON-NLS-1$
                importNeedsList.add(new ModuleNeeded(context, name, message, required));
            }
        }

        return importNeedsList;
    }

    public static List<ModuleNeeded> getModulesNeeded(String componentName) {
        List<ModuleNeeded> toReturn = new ArrayList<ModuleNeeded>();
        for (ModuleNeeded current : getModulesNeeded()) {
            if (current.getContext().equals(componentName)) {
                toReturn.add(current);
            }
        }

        return toReturn;
    }

}
