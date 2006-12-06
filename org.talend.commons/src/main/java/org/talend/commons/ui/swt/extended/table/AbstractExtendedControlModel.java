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
package org.talend.commons.ui.swt.extended.table;

import org.eclipse.core.runtime.ListenerList;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractExtendedControlModel {

    
    /**
     * 
     * Event type.
     * <br/>
     *
     * $Id$
     *
     */
    public enum EVENT_TYPE implements IExtendedControlEventType {
        NAME_CHANGED,
    };
    
    public static final String NAME_CHANGED = "NAME_CHANGED";

    /*
     * The list of listeners who wish to be notified when something significant happens.
     */
    private ListenerList listeners = new ListenerList();

    private String name;

    /**
     * DOC amaumont AbstractExtendedControlModel constructor comment.
     */
    public AbstractExtendedControlModel(String name) {
        super();
        this.name = name;
    }

    /**
     * DOC amaumont AbstractExtendedControlModel constructor comment.
     */
    public AbstractExtendedControlModel() {
    }

    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
        fireEvent(new ExtendedModelEvent(EVENT_TYPE.NAME_CHANGED));
    }

    /**
     * DOC amaumont Comment method "fireEvent".
     * 
     * @param event
     */
    protected void fireEvent(ExtendedModelEvent event) {
        final Object[] listenerArray = listeners.getListeners();
        for (int i = 0; i < listenerArray.length; i++) {
            ((IExtendedModelListener) listenerArray[i]).handleEvent(event);
        }

    }

    public abstract boolean isDataRegistered();

    public abstract void release();

    public void addListener(IExtendedModelListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(IExtendedModelListener listener) {
        this.listeners.remove(listener);
    }

}
