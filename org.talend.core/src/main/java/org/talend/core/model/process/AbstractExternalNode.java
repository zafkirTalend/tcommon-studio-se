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

import org.talend.core.i18n.Messages;
import org.talend.core.model.components.IODataComponent;
import org.talend.core.model.components.IODataComponentContainer;
import org.talend.core.model.metadata.ColumnNameChanged;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractExternalNode extends AbstractNode implements IExternalNode {

    private IODataComponentContainer ioDataContainer;

    public List<Problem> getProblems() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IExternalNode#setIODataComponents(org.talend.core.model.components.IODataComponentContainer)
     */
    public void setIODataComponents(IODataComponentContainer ioDatacontainer) {
        this.ioDataContainer = ioDatacontainer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.IExternalNode#getIODataComponents()
     */
    public IODataComponentContainer getIODataComponents() {
        return this.ioDataContainer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.INode#metataChanged(org.talend.core.model.components.IODataComponent)
     */
    public void metadataInputChanged(IODataComponent dataComponent, String connectionToApply) {
        for (ColumnNameChanged col : dataComponent.getColumnNameChanged()) {
//            System.out.println("    -> " + col + " " + connectionToApply); //$NON-NLS-1$ //$NON-NLS-2$
            this.renameMetadataColumnName(connectionToApply, col.getOldName(), col.getNewName());
        }
    }

    public void metadataOutputChanged(IODataComponent dataComponent, String connectionToApply) {
        for (ColumnNameChanged col : dataComponent.getColumnNameChanged()) {
//            System.out.println("    -> " + col + " " + connectionToApply); //$NON-NLS-1$ //$NON-NLS-2$
            this.renameMetadataColumnName(connectionToApply, col.getOldName(), col.getNewName());
        }
    }

    protected abstract void renameMetadataColumnName(String conectionName, String oldColumnName, String newColumnName);
}
