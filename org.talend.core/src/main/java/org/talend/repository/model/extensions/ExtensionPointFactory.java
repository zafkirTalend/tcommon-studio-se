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
package org.talend.repository.model.extensions;

import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;

/**
 * Provides extension points for this plug-in.<br/>
 * 
 * $Id: ExtensionPointFactory.java 911 2006-12-08 06:50:50 +0000 (星期五, 08 十二月 2006) bqian $
 * 
 */
public class ExtensionPointFactory {

    public static final IExtensionPointLimiter COMPONENTS_PROVIDER = new ExtensionPointLimiterImpl(
            "org.talend.core.components_provider", "ComponentsFactory", 1, 1); //$NON-NLS-1$ //$NON-NLS-2$

    public static final IExtensionPointLimiter EXTERNAL_COMPONENT = new ExtensionPointLimiterImpl(
            "org.talend.core.external_component", "ExternalComponent", 1, 1); //$NON-NLS-1$ //$NON-NLS-2$

    public static final IExtensionPointLimiter ACTIONS_GROUPS = new ExtensionPointLimiterImpl(
            "org.talend.core.actions", "Group"); //$NON-NLS-1$ //$NON-NLS-2$

}
