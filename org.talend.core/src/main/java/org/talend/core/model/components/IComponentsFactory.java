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
package org.talend.core.model.components;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Defines methods to use components. Implementation from extension point is given by ComponentsFactoryProvider<br/>
 * 
 * $Id$
 * 
 */
public interface IComponentsFactory {

    String COMPONENTS_LOCATION = "org.talend.designer.components.localprovider"; //$NON-NLS-1$

    String COMPONENTS_INNER_FOLDER = "components"; //$NON-NLS-1$

    String COMPONENTS_USER_INNER_FOLDER = "user"; //$NON-NLS-1$

    public void init();

    public int size();

    public List<IComponent> getComponents();

    public IComponent get(String name);

    public URL getComponentPath() throws IOException;
}
