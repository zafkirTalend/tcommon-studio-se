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
package org.talend.commons.ui.swt.proposal;

import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.widgets.Control;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public interface IControlContentAdapterExtended extends IControlContentAdapter {

    /**
     * 
     * Return the last word before the cursor.
     * If control contains "test filter value" and cursor is at offset 8,
     * The filter value is "fil".
     * 
     * @param control
     * @return return the last word before the cursor.
     */
    public String getFilterValue(Control control);
    
    /**
     * Proposal set the used filter. 
     * 
     * @param filterValue
     */
    public void setUsedFilterValue(String filterValue);
    
}
