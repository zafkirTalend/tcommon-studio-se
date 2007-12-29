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
package org.talend.designer.joblet.ui.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.model.ComponentsFactoryProvider;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public class JobletComponentsUtils {

    // 4.Load Joblet Component:

    public static IJobletComponent createJobletComponent() {
        IConfigurationElement[] configElements = Platform.getExtensionRegistry().getConfigurationElementsFor(
                IComponentsFactory.COMPONENT_DEFINITION);
        for (IConfigurationElement configElement : configElements) {
            try {
                Object execObj = configElement.createExecutableExtension("class");
                if (execObj instanceof IJobletComponent) {
                    return (IJobletComponent) execObj.getClass().newInstance();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static List<IComponent> jobletComponents = null;

    /**
     * DOC qzhang Comment method "loadComponentsFromJoblets".
     */
    public static void loadComponentsFromJoblets() {
        IComponentsFactory components = ComponentsFactoryProvider.getInstance();
        List<IComponent> componentsList = components.getComponents();
        if (jobletComponents == null) {
            jobletComponents = new ArrayList<IComponent>();
        } else {
            componentsList.removeAll(jobletComponents);
            jobletComponents.clear();
        }
        List<String> existCNames = new ArrayList<String>();
        for (IComponent component : componentsList) {
            existCNames.add(component.getName());
        }

        try {
            List<IRepositoryObject> all = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory().getAll(
                    ERepositoryObjectType.JOBLET);
            for (IRepositoryObject repositoryObject : all) {
                String name = repositoryObject.getProperty().getLabel();
                if (!existCNames.contains(name)) {
                    IJobletComponent comp = createJobletComponent();
                    if (comp == null) {
                        return;
                    }
                    comp.setProperty(repositoryObject.getProperty());
                    if (!componentsList.contains(comp)) {
                        componentsList.add(comp);
                        jobletComponents.add(comp);
                    }
                } else {
                    IJobletComponent exJCom = getJobletComponent(componentsList, name);
                    if (exJCom != null) {
                        exJCom.setProperty(repositoryObject.getProperty());
                    }
                }
            }
        } catch (Exception ex) {
            ExceptionHandler.process(ex);
        }
    }

    private static IJobletComponent getJobletComponent(List<IComponent> components, String name) {
        for (IComponent component : components) {
            if (component.getName().equals(name) && component instanceof IJobletComponent) {
                return (IJobletComponent) component;
            }
        }
        return null;
    }

}
