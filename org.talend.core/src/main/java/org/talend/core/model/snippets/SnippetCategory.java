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
package org.talend.core.model.snippets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.common.snippets.core.ISnippetCategory;
import org.eclipse.wst.common.snippets.core.ISnippetItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.model.repository.RepositoryObject;

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 */
public class SnippetCategory extends RepositoryObject {

    ISnippetCategory category;

    public SnippetCategory(ISnippetCategory category) {
        this.category = category;
    }

    /**
     * Returns the type.
     * 
     * @return ERepositoryObjectType.FOLDER
     */
    public ERepositoryObjectType getType() {
        return ERepositoryObjectType.SNIPPETS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.RepositoryObject#getLabel()
     */
    @Override
    public String getLabel() {
        return category.getLabel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.RepositoryObject#getId()
     */
    @Override
    public String getId() {
        return category.getId();
    }

    @Override
    public List<IRepositoryObject> getChildren() {
        List<IRepositoryObject> toReturn = new ArrayList<IRepositoryObject>();
        ISnippetItem[] items = category.getItems();
        for (ISnippetItem snippetItem : items) {
            SnippetItem itemObject = new SnippetItem(snippetItem);
            toReturn.add(itemObject);
        }
        return toReturn;
    }
}
