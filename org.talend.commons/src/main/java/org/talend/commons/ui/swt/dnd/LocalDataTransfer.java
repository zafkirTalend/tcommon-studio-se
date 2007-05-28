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
package org.talend.commons.ui.swt.dnd;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id: TableEntriesTransfer.java 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class LocalDataTransfer extends ByteArrayTransfer {

    private LocalDraggedData draggedData;

    private static final String TYPE_NAME = "local-data-transfer-format" + (new Long(System.currentTimeMillis())).toString(); //$NON-NLS-1$;

    private static final int TYPE_ID = registerType(TYPE_NAME);

    private static final LocalDataTransfer INSTANCE = new LocalDataTransfer();

    public static LocalDataTransfer getInstance() {
        return INSTANCE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
     */
    @Override
    protected int[] getTypeIds() {
        return new int[] { TYPE_ID };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
     */
    @Override
    protected String[] getTypeNames() {
        return new String[] { TYPE_NAME };
    }

    @Override
    protected void javaToNative(Object object, TransferData transferData) {
    }

    @Override
    protected Object nativeToJava(TransferData transferData) {
        return new byte[0];
    }

    public LocalDraggedData getDraggedData() {
        return draggedData;
    }

    public void setLocalDraggedData(LocalDraggedData draggedData) {
        this.draggedData = draggedData;
    }
}
