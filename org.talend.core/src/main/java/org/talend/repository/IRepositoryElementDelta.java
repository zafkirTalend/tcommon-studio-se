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
 * A repository element delta describes changes in repository element between two discrete points in time. Given a
 * delta, clients can access the element that has changed, and any children that have changed.
 * 
 * $Id: IRepositoryElementDelta.java 2007-1-4下午03:40:26 bqian $
 * 
 */
public interface IRepositoryElementDelta {

    public IRepositoryObject getRepositoryObject();

}
