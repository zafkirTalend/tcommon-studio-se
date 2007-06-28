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
package org.talend.designer.core;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ILabelProvider;
import org.talend.core.IService;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ProcessItem;

/**
 * Provides Designer core services for other components <br/>.
 * 
 * $Id: IDesignerCore.java 1 2006-12-19 上午10:16:43 bqian
 * 
 */
public interface IDesignerCoreService extends IService {

    // ¨Process will be loaded from XML File with this method, so it can be slow
    // This won't load the graphics of the job, only the datas
    public IProcess getProcessFromProcessItem(ProcessItem processItem);

    // Used for generating HTML only
    public List<Map> getMaps();

    // Ends
    public String getRepositoryAliasName(ConnectionItem connectionItem);

    public ILabelProvider getGEFEditorNodeLabelProvider();

    public void switchToCurContextsView();
    //add for feature 840
    public void saveJobBeforeRun(IProcess activeProcess);
}
