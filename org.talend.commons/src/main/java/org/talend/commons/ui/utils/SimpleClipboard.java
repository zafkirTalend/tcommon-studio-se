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
package org.talend.commons.ui.utils;

import org.eclipse.core.runtime.ListenerList;
import org.talend.commons.ui.swt.extended.table.ClipboardEvent;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class SimpleClipboard {

    private ListenerList listeners = new ListenerList();

    private static SimpleClipboard instance;

    public static SimpleClipboard getInstance() {
        if (instance == null) {
            instance = new SimpleClipboard();
        }
        return instance;
    }

    private Object object;

    public void setData(Object object) {
        this.object = object;
        fireEvent(new ClipboardEvent());
    }

    public Object getData() {
        return object;
    }

    public void addListener(IClipoardListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(IClipoardListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * DOC amaumont Comment method "fireEvent".
     * 
     * @param event
     */
    protected void fireEvent(ClipboardEvent event) {
        final Object[] listenerArray = listeners.getListeners();
        for (int i = 0; i < listenerArray.length; i++) {
            ((IClipoardListener) listenerArray[i]).handleEvent(event);
        }

    }

}
