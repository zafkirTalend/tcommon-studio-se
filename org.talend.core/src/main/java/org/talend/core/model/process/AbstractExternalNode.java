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
package org.talend.core.model.process;

import java.util.List;

import org.talend.core.model.components.IODataComponentContainer;

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
    
    /* (non-Javadoc)
     * @see org.talend.core.model.process.IExternalNode#setIODataComponents(org.talend.core.model.components.IODataComponentContainer)
     */
    public void setIODataComponents(IODataComponentContainer ioDatacontainer) {
        this.ioDataContainer = ioDatacontainer;
    }

    /* (non-Javadoc)
     * @see org.talend.core.model.process.IExternalNode#getIODataComponents()
     */
    public IODataComponentContainer getIODataComponents() {
        return this.ioDataContainer;
    }

    
    
    
}
