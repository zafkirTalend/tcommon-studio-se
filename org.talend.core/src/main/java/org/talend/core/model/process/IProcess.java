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
package org.talend.core.model.process;

import java.util.List;

import org.talend.core.model.repository.IRepositoryObject;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IProcess extends IRepositoryObject, IElement {

    // list of nodes that are in the designer
    public List<? extends INode> getGraphicalNodes();

    // list of nodes that will be used to generated the code
    // this list is slightly different from the designer nodes
    public List<? extends INode> getGeneratingNodes();

    public String generateUniqueConnectionName(String baseName);

    public void addUniqueConnectionName(String uniqueConnectionName);

    public void removeUniqueConnectionName(String uniqueConnectionName);

    public boolean checkValidConnectionName(String connectionName);

    public IContextManager getContextManager();
}
