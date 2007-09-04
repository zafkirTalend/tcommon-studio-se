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
package org.talend.core.model.properties;

import java.util.Date;


/**
 * DOC amaumont  class global comment. Detailled comment
 * <br/>
 *
 */
public class SimpleTalendTrigger extends TalendTrigger {

    private Integer repeatCount;
    private int repeatInterval;
    
    /**
     * Getter for repeatCount.
     * @return the repeatCount
     */
    public Integer getRepeatCount() {
        return this.repeatCount;
    }
    
    /**
     * Sets the repeatCount.
     * @param repeatCount the repeatCount to set
     */
    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }
    
    /**
     * Getter for repeatInterval.
     * @return the repeatInterval
     */
    public int getRepeatInterval() {
        return this.repeatInterval;
    }
    
    /**
     * Sets the repeatInterval.
     * @param repeatInterval the repeatInterval to set
     */
    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }
       
    
}
