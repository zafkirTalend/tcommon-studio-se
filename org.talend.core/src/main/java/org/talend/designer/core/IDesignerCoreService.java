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
package org.talend.designer.core;

import org.talend.core.IService;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.properties.ProcessItem;

/**
 * Provides Designer core services for other components <br/>
 * 
 * $Id: IDesignerCore.java 1 2006-12-19 上午10:16:43 bqian
 * 
 */
public interface IDesignerCoreService extends IService {

    /**
     * Load a temporary process from a xml file to have an access to the context manager.
     * 
     * @param ProcessItem
     * @return IContextManager
     */
    public IContextManager getContextManagerFromXmlProcess(ProcessItem processItem);

}
