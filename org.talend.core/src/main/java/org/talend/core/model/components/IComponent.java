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
package org.talend.core.model.components;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INodeConnector;
import org.talend.core.model.process.INodeReturn;
import org.talend.core.model.temp.ECodeLanguage;

/**
 * Interface that describes the functions that a must implements a component manager. <br/>
 * 
 * $Id$
 */
public interface IComponent {

    String PROP_NAME = "NAME";

    String PROP_LONG_NAME = "LONG_NAME";

    String PROP_FAMILY = "FAMILY";

    String PROP_MENU = "MENU";

    String PROP_LINK = "LINK";

    String PROP_HELP = "HELP";

    public String getName();

    public String getLongName();

    public String getFamily();

    public ImageDescriptor getIcon32();

    public ImageDescriptor getIcon24();

    public ImageDescriptor getIcon16();

    public List<? extends IElementParameter> createElementParameters(IElement element);

    public List<? extends INodeReturn> createReturns();

    public List<? extends INodeConnector> createConnectors();

    public Boolean isMultipleMethods(ECodeLanguage language);

    public String getPluginFullName();

    public boolean isSchemaAutoPropagated();

    public boolean isDataAutoPropagated();

    public boolean isCheckColumns();

    public String getVersion();

    public IMultipleComponentManager getMultipleComponentManager();

    public boolean isLoaded();
}
