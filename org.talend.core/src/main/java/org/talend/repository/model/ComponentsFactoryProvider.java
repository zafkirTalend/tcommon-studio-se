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
package org.talend.repository.model;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.talend.commons.exception.IllegalPluginConfigurationException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.components.IComponentFileNaming;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.components.IComponentsService;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.ComponentSetting;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.designer.core.IPaletteFilter;

/**
 * Provides, using extension points, implementation of many factories.
 * 
 * <ul>
 * <li>IProcessFactory</li>
 * </ul>
 * 
 * $Id: ComponentsFactoryProvider.java 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 */
public class ComponentsFactoryProvider {

    public static final String FAMILY_SEPARATOR_REGEX = "\\|"; //$NON-NLS-1$

    private static IComponentsFactory factorySingleton = null;

    private static IComponentFileNaming componentFileNaming = null;

    public static synchronized IComponentsFactory getInstance() {
        if (factorySingleton == null) {
            try {
                // processSingleton =
                // ExtensionImplementationProviders.getSingleInstance(ExtensionPointFactory.COMPONENTS_PROVIDER);
                IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                        IComponentsService.class);
                factorySingleton = service.getComponentsFactory();
            } catch (IllegalPluginConfigurationException e) {
                ExceptionHandler.process(e);
            }
        }
        return factorySingleton;
    }

    public static IComponentFileNaming getFileNamingInstance() {
        if (componentFileNaming == null) {
            try {
                IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(
                        IComponentsService.class);
                componentFileNaming = service.getComponentFileNaming();
            } catch (IllegalPluginConfigurationException e) {
                ExceptionHandler.process(e);
            }
        }
        return componentFileNaming;
    }

    /**
     * save the Component Visibility Status for pallete settings.
     */
    public static void saveComponentVisibilityStatus(boolean reset, boolean persist) {
        boolean modified = false;
        IComponentsFactory componentsFactory = ComponentsFactoryProvider.getInstance();
        Set<IComponent> components = componentsFactory.getComponents();

        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                Context.REPOSITORY_CONTEXT_KEY);
        if (repositoryContext == null) {
            return;
        }
        Project project = repositoryContext.getProject();
        if (project == null) {
            return;
        }
        org.talend.core.model.properties.Project emfProject = project.getEmfProject();
        if (emfProject == null) {
            return;
        }
        EList list = emfProject.getComponentsSettings();
        if (!list.isEmpty()) {
            if (reset) {
                // if need to reset, clear the list
                list.clear();
                modified = true;
            }
        }

        for (IComponent component : new ArrayList<IComponent>(components)) {
            String[] families = component.getOriginalFamilyName().split(FAMILY_SEPARATOR_REGEX);
            String componentName = component.getName();
            for (String family : families) {
                boolean existingFamily = false;
                boolean existSettings = false;
                for (Object curSetting : list) {
                    if (curSetting instanceof ComponentSetting) {
                        String curFamily = ((ComponentSetting) curSetting).getFamily();
                        if (curFamily != null && curFamily.equals(family)) {
                            existingFamily = true;
                            if (componentName != null && componentName.equals(((ComponentSetting) curSetting).getName())) {
                                existSettings = true;
                                break;
                            }
                        }
                    }
                }
                boolean addTechnical = "Technical".equals(family) && !component.isTechnical() && !existSettings;
                if (addTechnical || !existingFamily) {
                    ComponentSetting setting = PropertiesFactory.eINSTANCE.createComponentSetting();
                    setting.setFamily(family);
                    setting.setName(component.getName());
                    setting.setHidden(!component.isVisibleInComponentDefinition());
                    list.add(setting);
                    modified = true;
                }
            }
        }

        if (persist && modified) {
            IProxyRepositoryFactory prf = CorePlugin.getDefault().getProxyRepositoryFactory();

            try {
                prf.saveProject(project);
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }
    }

    /**
     * Get the family string of specific palette entry, need to remove first '/'.
     * 
     * yzhang Comment method "getPaletteEntryFamily".
     * 
     * @param entry
     * @return
     */
    public static String getPaletteEntryFamily(PaletteEntry entry) {
        String family = ""; //$NON-NLS-1$
        if (!(entry instanceof PaletteRoot)) {
            if (entry instanceof IPaletteFilter) {
                family = ((IPaletteFilter) entry).getOriginalName();
            }
            // else {
            // family = entry.getLabel();
            //                family = getPaletteEntryFamily(entry.getParent()) + "/" + family; //$NON-NLS-1$
            // }
        }
        return family;
    }

    public static void saveComponentVisibilityStatus() {
        saveComponentVisibilityStatus(false, true);
    }

    public static void restoreComponentVisibilityStatus() {
        saveComponentVisibilityStatus(true, false);
    }

}
