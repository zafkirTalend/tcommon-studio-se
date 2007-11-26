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

import org.eclipse.emf.common.util.EList;
import org.talend.core.model.properties.SnippetItem;

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 */
public class SnippetManager {

    public static final String SNIPPET_PREFFIX = "<SNIPPET>";

    public static final String SNIPPET_SUFFIX = "</SNIPPET>";

    private static SnippetManager instance = null;

    public synchronized static SnippetManager getInstance() {
        if (instance == null) {
            instance = new SnippetManager();
        }
        return instance;
    }

    List<SnippetItem> snippets = new ArrayList<SnippetItem>();

    private SnippetManager() {
    }

    public List<SnippetItem> getListSnippet() {
        return snippets;
    }

    public void setListSnippet(List<SnippetItem> listSnippet) {
    }

    public SnippetItem getSnippet(String name) {
        return null;
    }

    // public void addSnippetListener(ISnippetListener listener) {
    // }
    //
    // public void removeSnippetListener(ISnippetListener listener) {
    // }
    //
    // public void fireSnippetsChangedEvent() {
    // }

    public boolean checkValidParameterName(String parameterName) {
        return false;
    }

    public void saveToEmf(SnippetItem item) {

    }

    public void loadFromEmf(EList contextTypeList) {
    }

    /**
     * DOC bqian Comment method "addSnippet".
     * 
     * @param snippetItem
     */
    public void addSnippet(SnippetItem snippetItem) {
        snippets.add(snippetItem);

    }

}
