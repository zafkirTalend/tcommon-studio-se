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
package org.talend.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.i18n.Messages;

/**
 * DOC qian class global comment. A global service register provides the service registration and acquirement. <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public class GlobalServiceRegister {

    // The shared instance
    private static GlobalServiceRegister instance = new GlobalServiceRegister();

    private static IConfigurationElement[] configurationElements;

    public static GlobalServiceRegister getDefault() {
        return instance;
    }

    private Map<Class, IService> services = new HashMap<Class, IService>();

    static {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        configurationElements = registry.getConfigurationElementsFor("org.talend.core.service"); //$NON-NLS-1$
    }

    /**
     * DOC qian Comment method "getService".Gets the specific IService.
     * 
     * @param klass the Service type you want to get
     * @return IService IService
     */
    public IService getService(Class klass) {
        IService service = services.get(klass);
        if (service == null) {
            service = findService(klass);
            if (service == null) {
                throw new RuntimeException(Messages.getString("GlobalServiceRegister.ServiceNotRegistered",klass.getName())); //$NON-NLS-1$ //$NON-NLS-2$
            }
            services.put(klass, service);
        }
        return service;
    }

    /**
     * DOC qian Comment method "findService".Finds the specific service from the list.
     * 
     * @param klass the interface type want to find.
     * @return IService
     */
    private IService findService(Class klass) {
        String key = klass.getName();
        for (int i = 0; i < configurationElements.length; i++) {
            IConfigurationElement element = configurationElements[i];
            String id = element.getAttribute("serviceId"); //$NON-NLS-1$
            
            if (!key.endsWith(id)) {
                continue;
            }
            try {
                Object service = element.createExecutableExtension("class"); //$NON-NLS-1$
                if (klass.isInstance(service)) {
                    return (IService) service;
                }
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }
}
