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
package org.talend.repository.model;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.IllegalPluginConfigurationException;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.components.IComponentFileNaming;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.components.IComponentsService;
import org.talend.core.model.properties.ComponentSetting;
import org.talend.core.model.properties.PropertiesFactory;

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
        IComponentsFactory componentsFactory = ComponentsFactoryProvider.getInstance();
        List<IComponent> components = componentsFactory.getComponents();

        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                Context.REPOSITORY_CONTEXT_KEY);
        EList list = repositoryContext.getProject().getEmfProject().getComponentsSettings();
        if (!list.isEmpty()) {
            if (reset) {
                // if need to reset, clear the list
                list.clear();
            } else {
                // no need to reset, so no need to computate again.
                return;
            }
        }

        for (IComponent component : components) {
            String[] families = component.getFamily().split(FAMILY_SEPARATOR_REGEX);
            for (String family : families) {
                ComponentSetting setting = PropertiesFactory.eINSTANCE.createComponentSetting();
                setting.setFamily(family);
                setting.setName(component.getName());
                setting.setHidden(!component.isVisibleInComponentDefinition());
                list.add(setting);
            }
        }

        if (persist) {
            IProxyRepositoryFactory prf = CorePlugin.getDefault().getProxyRepositoryFactory();

            try {
                prf.saveProject(repositoryContext.getProject());
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
            family = entry.getLabel();
            family = getPaletteEntryFamily(entry.getParent()) + "/" + family; //$NON-NLS-1$
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
