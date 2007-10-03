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
package org.talend.core.model.process;

import java.util.List;
import java.util.Set;

import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.runprocess.IProcessor;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IProcess extends IRepositoryObject, IElement {

    public static final String SCREEN_OFFSET_X = "SCREEN_OFFSET_X";

    public static final String SCREEN_OFFSET_Y = "SCREEN_OFFSET_Y";

    public String getName();

    // list of nodes that are in the designer
    public List<? extends INode> getGraphicalNodes();

    // list of nodes that will be used to generated the code
    // this list is slightly different from the designer nodes
    public List<? extends INode> getGeneratingNodes();

    public String generateUniqueConnectionName(String baseName);

    public void addUniqueConnectionName(String uniqueConnectionName);

    public void removeUniqueConnectionName(String uniqueConnectionName);

    public boolean checkValidConnectionName(String connectionName);

    public boolean checkValidConnectionName(String connectionName, boolean checkExists);

    public IContextManager getContextManager();

    public List<? extends INode> getNodesOfType(String componentName);

    public void setProcessor(IProcessor processor);

    public IProcessor getProcessor();

    /**
     * Comment method "getAllConnections".
     * 
     * @param filter only return the filter matched connections
     * @return
     */
    public IConnection[] getAllConnections(String filter);

    public Set<String> getNeededLibraries(boolean withChildrens);

    public int getMergelinkOrder(final INode node);

    public boolean isThereLinkWithHash(final INode node);
}
