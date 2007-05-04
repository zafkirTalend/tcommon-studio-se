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
package org.talend.core.model.metadata;


/**
 * 
 * DOC smallet IODataComponentContainer class global comment. Detailled comment <br/>
 * 
 * $Id: IODataComponent.java 961 2006-12-12 04:42:46 +0000 (mar., 12 déc. 2006) nrousseau $
 * 
 */
public class ColumnNameChanged {

    private String oldName;

    private String newName;

    /**
     * DOC smallet ColumnNameChanged constructor comment.
     * 
     * @param oldName
     * @param newName
     */
    public ColumnNameChanged(String oldName, String newName) {
        super();
        this.oldName = oldName;
        this.newName = newName;
    }

    /**
     * Getter for newName.
     * 
     * @return the newName
     */
    public String getNewName() {
        return this.newName;
    }

    /**
     * Getter for oldName.
     * 
     * @return the oldName
     */
    public String getOldName() {
        return this.oldName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Column changed : " + oldName + "->" + newName;  //$NON-NLS-1$//$NON-NLS-2$
    }

}
