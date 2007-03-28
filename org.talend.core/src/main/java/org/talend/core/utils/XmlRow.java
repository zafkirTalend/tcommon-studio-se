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
package org.talend.core.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * DOC chuger  class global comment. Detailled comment
 * <br/>
 *
 * $Id$
 *
 */
public class XmlRow {
    
    private List<XmlField> fields;

    /**
     * Constructs a new XmlRow.
     */
    public XmlRow() {
        super();
        fields = new ArrayList<XmlField>();
    }

    
    /**
     * Getter for fields.
     * @return the fields
     */
    public List<XmlField> getFields() {
        return this.fields;
    }
    
    /**
     * Adds a fields at the end of the row.
     * @param field Field to add.
     */
    public void add(XmlField field) {
        fields.add(field);
    }

}
