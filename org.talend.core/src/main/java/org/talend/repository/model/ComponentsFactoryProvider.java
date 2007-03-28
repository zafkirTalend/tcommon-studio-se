// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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

    public static IComponentsFactory getInstance() {
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
