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
package org.talend.commons.ui.swt.tableviewer.behavior;

/**
 * 
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 */
public interface IColumnCellModifier {

    /**
     * 
     * Can modify the value at current column in the given bean.
     * @param bean
     * @return true if can be modified
     */
    public boolean canModify(Object bean);

    /**
     * 
     * Get the value at current column from the given bean.
     * @param bean
     * @param value
     * @return value at current column in the given bean, if null the DefaultCellModifier will try to get the value. 
     */
    public Object getValue(Object bean);

    /**
     * 
     * DOC amaumont Comment method "modify".
     * @param bean
     * @param value
     * @return true if modify has modified bean, else false so DefaultCellModifier will process the modification. 
     */
    public boolean modify(Object bean, Object value);
    
}
