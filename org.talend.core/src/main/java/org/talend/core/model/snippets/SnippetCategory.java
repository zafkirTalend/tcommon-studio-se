// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
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
