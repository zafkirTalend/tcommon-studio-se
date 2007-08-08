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
package org.talend.core.ui.metadata.dialog;

import java.util.ArrayList;
import java.util.List;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public class DbTableSelectorObject {

    private ObjectType type;
    /**
     * qzhang DbTableSelectorObject class global comment. Detailled comment.
     * <br/>
     *
     */
    public enum ObjectType {
        TABLE,
        COLUMN,
        DB;
        
    }

    private String label;

    private List<DbTableSelectorObject> children;
    
    private DbTableSelectorObject parent;
    /**
     * qzhang DbTableSelectorDialog.TreeObject constructor comment.
     */
    public DbTableSelectorObject() {

    }

    public List<DbTableSelectorObject> getChildren() {
        return this.children;
    }

    public void setChildren(List<DbTableSelectorObject> children) {
        this.children = children;
    }

    public String getLabel() {
        return this.label;
    }

    public void addChildren(DbTableSelectorObject object) {
        if (children == null) {
            children = new ArrayList<DbTableSelectorObject>();
        }
        object.setParent(this);
        children.add(object);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    
    public ObjectType getType() {
        return this.type;
    }

    
    public void setType(ObjectType type) {
        this.type = type;
    }

    
    public DbTableSelectorObject getParent() {
        return this.parent;
    }

    
    public void setParent(DbTableSelectorObject parent) {
        this.parent = parent;
    }
}
