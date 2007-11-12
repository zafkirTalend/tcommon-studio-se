// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.repository.model;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.IllegalPluginConfigurationException;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.components.IComponentFileNaming;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.components.IComponentsService;

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
}
