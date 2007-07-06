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
package org.talend.repository.model;

import org.eclipse.core.runtime.IPath;
import org.talend.core.IService;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.repository.IRepositoryChangedListener;
import org.talend.repository.RepositoryElementDelta;

/**
 * DOC qian class global comment. Interface for RepositoryService. <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public interface IRepositoryService extends IService {

    public IComponentsFactory getComponentsFactory();

    public IPath getPathFileName(String folderName, String fileName);

    public IProxyRepositoryFactory getProxyRepositoryFactory();

    public IPath getRepositoryPath(RepositoryNode node);

    public void registerRepositoryChangedListener(IRepositoryChangedListener listener);

    public void removeRepositoryChangedListener(IRepositoryChangedListener listener);

    public void repositoryChanged(RepositoryElementDelta event);

    public String validateColumnName(String columnName, int index);
}
