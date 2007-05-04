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
package org.talend.commons.utils.workbench.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.talend.commons.exception.IllegalPluginConfigurationException;

/**
 * 
 * DOC amaumont ExtensionImplementationProvider class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40Z nrousseau $
 * 
 * @param <T>
 */
public class ClassExtensionImplementationProvider<T> extends ExtensionImplementationProvider<T> {

    /**
     * DOC amaumont DefaultExtensionImplementationProvider constructor comment.
     * @param plugInId 
     */
    public ClassExtensionImplementationProvider(IExtensionPointLimiter extensionPointLimiter, String plugInId) {
        super(extensionPointLimiter, plugInId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider#createImplementation(org.eclipse.core.runtime.IExtension,
     * org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter)
     */
    @Override
    protected T createImplementation(IExtension extension, IExtensionPointLimiter extensionPointLimiter,
            IConfigurationElement configurationElement) {
        try {
            return (T) configurationElement.createExecutableExtension("class");
        } catch (CoreException e) {
            throw new IllegalPluginConfigurationException(e);
        }
    }

}
