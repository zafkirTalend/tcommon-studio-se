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
package org.talend.repository;

import org.talend.core.model.repository.IRepositoryObject;

/**
 * DOC bqian concrete class of interface IRepositoryElementDelta <br/>
 * 
 * $Id: RepositoryElementDelta.java 2007-1-4下午04:12:08 bqian $
 * 
 */
public class RepositoryElementDelta implements IRepositoryElementDelta {

    private IRepositoryObject repositoryObject;

    /**
     * DOC bqian RepositoryElementDelta constructor comment.
     */
    public RepositoryElementDelta(IRepositoryObject repositoryObject) {
        this.repositoryObject = repositoryObject;
    }

    public IRepositoryObject getRepositoryObject() {
        return repositoryObject;
    }
}
