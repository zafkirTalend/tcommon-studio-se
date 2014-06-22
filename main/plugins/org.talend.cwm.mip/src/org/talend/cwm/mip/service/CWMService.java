// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.cwm.mip.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import orgomg.cwm.foundation.softwaredeployment.DataManager;

/**
 * created by talend on Sep 29, 2012 <BR>
 * This service extension point is for the purpose of hanlding CWM model related operations.
 * 
 */
public class CWMService {

    private static Logger log = Logger.getLogger("org.talend.cwm.mip.service.CWMService");

    private Map<Class, CWMService> services = new HashMap<Class, CWMService>();

    private static IConfigurationElement[] configurationElements = null;

    // The shared instance
    private static CWMService instance = new CWMService();

    public static CWMService getDefault() {
        return instance;
    }

    private IConfigurationElement[] getConfigurationElements() {
        if (configurationElements == null) {
            IExtensionRegistry registry = Platform.getExtensionRegistry();
            configurationElements = (registry == null ? null : registry
                    .getConfigurationElementsFor("org.talend.cwm.mip.cwmService")); //$NON-NLS-1$
        }
        return configurationElements;
    }

    /**
     * DOC qian Comment method "getService".Gets the specific CWMService.
     * 
     * @param klass the Service type you want to get
     * @return CWMService CWMService
     */
    public CWMService getService(Class klass) {
        CWMService service = services.get(klass);
        if (service == null && getConfigurationElements() != null) {
            service = findService(klass);
            if (service == null) {

                throw new RuntimeException("The service " + klass.getName() + " has not been registered."); //$NON-NLS-1$ 
            }
            services.put(klass, service);
        }
        return service;
    }

    public boolean isServiceRegistered(Class klass) {
        CWMService service = services.get(klass);
        if (service == null) {
            service = findService(klass);
            if (service == null) {
                return false;
            }
            services.put(klass, service);
        }
        return true;
    }

    /**
     * DOC qian Comment method "findService".Finds the specific service from the list.
     * 
     * @param klass the interface type want to find.
     * @return CWMService
     */
    private CWMService findService(Class klass) {
        String key = klass.getName();
        IConfigurationElement[] configElements = getConfigurationElements();
        if (configElements != null) {
            for (IConfigurationElement element : configElements) {
                if (element.isValid()) {
                    try {
                        Object service = element.createExecutableExtension("class"); //$NON-NLS-1$
                        if (klass.isInstance(service)) {
                            return (CWMService) service;
                        }
                    } catch (CoreException e) {
                        log.warning(e.getMessage());
                    }
                }// else element is not valid because the bundle may have been stoped or uninstalled and the extension
                 // point
                 // registry is still holding values
                 // has mentionned in the class TODO, this class should be removed and OSGI dynamic services used.
            }
        }
        return null;
    }

    /**
     * 
     * Get the readable name (actual item display name) via context style name when the item is on context mode.<BR>
     * Note that in order to make it easier for caller to get a name, this contextualName may be passed as an actual
     * display name, in this case, this name will be simply returned.
     * 
     * @param dataManager
     * @param contextualName eigher the contextual name or the actual display name.
     * @return the readable actual item display name.
     */
    public String getReadableName(DataManager dataManager, String contextualName) {
        return null;
    }
}
