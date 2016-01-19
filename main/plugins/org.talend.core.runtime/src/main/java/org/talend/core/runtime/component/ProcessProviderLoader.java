// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.runtime.component;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.runtime.process.IProcessProvider;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ProcessProviderLoader {

    public static final String EXTENSION_ID = "org.talend.designer.core.process_provider"; //$NON-NLS-1$

    public static final String ATTR_CLASS = "class"; //$NON-NLS-1$

    public static final String ATTR_PID = "pluginId"; //$NON-NLS-1$

    public static Map<String, IProcessProvider> findProcessProviders() {
        Map<String, IProcessProvider> providers = new HashMap<String, IProcessProvider>();

        IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);
        for (IConfigurationElement elem : elems) {
            String pid = elem.getAttribute(ATTR_PID);
            try {
                IProcessProvider provider = (IProcessProvider) elem.createExecutableExtension(ATTR_CLASS);
                providers.put(pid, provider);
            } catch (CoreException ex) {
                ExceptionHandler.process(ex);
            }
        }
        return providers;
    }

    public static void loadComponentsFromProviders() {
        for (IProcessProvider processProvider : findProcessProviders().values()) {
            processProvider.loadComponentsFromExtensionPoint();
        }
    }
}
