// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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

import org.talend.commons.utils.workbench.extensions.ExtensionPointImpl;
import org.talend.commons.utils.workbench.extensions.ISimpleExtensionPoint;
import org.talend.core.i18n.Messages;

/**
 * Provides extension points for this plug-in.<br/>
 * 
 * $Id: ExtensionPointFactory.java 911 2006-12-08 06:50:50 +0000 (星期五, 08 十二月 2006) bqian $
 * 
 */
public class ExtensionPointFactory {

    public static final ISimpleExtensionPoint COMPONENTS_PROVIDER = new ExtensionPointImpl(
            "org.talend.core.components_provider", "ComponentsFactory", 1, 1); //$NON-NLS-1$ //$NON-NLS-2$

    public static final ISimpleExtensionPoint EXTERNAL_COMPONENT = new ExtensionPointImpl(
            "org.talend.core.external_component", "ExternalComponent", 1, 1); //$NON-NLS-1$ //$NON-NLS-2$

    public static final ISimpleExtensionPoint ACTIONS_GROUPS = new ExtensionPointImpl("org.talend.core.actions", "Group", //$NON-NLS-1$ //$NON-NLS-2$
            -1, -1);
}
