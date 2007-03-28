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
package org.talend.repository;

import java.util.EventObject;

/**
 * An element changed event describes a change to the structure or contents of a tree of repository. The changes to the
 * elements are described by the associated delta object carried by this event.
 * 
 * $Id: RepositoryChangedEvent.java 2007-1-4下午03:36:34 bqian $
 * 
 */

public class RepositoryChangedEvent extends EventObject {

    private static final long serialVersionUID = -8947240431612844420L; // backward compatible

    /*
     * Event type indicating the nature of this event. It can be a combination either: - POST_CHANGE - PRE_AUTO_BUILD -
     * POST_RECONCILE
     */
    private int type;

    /**
     * Creates an new element changed event (based on a <code>IJavaElementDelta</code>).
     * 
     * @param delta the Java element delta.
     * @param type the type of delta (ADDED, REMOVED, CHANGED) this event contains
     */
    public RepositoryChangedEvent(IRepositoryElementDelta delta, int type) {
        super(delta);
        this.type = type;
    }

    /**
     * Creates an new element changed event (based on a <code>IJavaElementDelta</code>).
     * 
     * @param delta the Java element delta.
     */
    public RepositoryChangedEvent(IRepositoryElementDelta delta) {
        super(delta);
    }

    /**
     * Returns the delta describing the change.
     * 
     * @return the delta describing the change
     */
    public IRepositoryElementDelta getDelta() {
        return (IRepositoryElementDelta) this.source;
    }

    /**
     * Returns the type of event being reported.
     * 
     * @return one of the event type constants
     * @see #POST_CHANGE
     * @see #PRE_AUTO_BUILD
     * @see #POST_RECONCILE
     * @since 2.0
     */
    public int getType() {
        return this.type;
    }
}
