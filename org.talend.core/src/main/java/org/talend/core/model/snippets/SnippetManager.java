// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
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
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.StringUtils;
import org.talend.core.CorePlugin;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.ui.snippet.VariableItemHelper;

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 */
public class SnippetManager {

    // public static final String SNIPPET_PREFFIX = "<SNIPPET>";
    //
    // public static final String SNIPPET_SUFFIX = "</SNIPPET>";

    private static SnippetManager instance = null;

    public synchronized static SnippetManager getInstance() {
        if (instance == null) {
            instance = new SnippetManager();
        }
        return instance;
    }

    List<SnippetItem> snippets = new ArrayList<SnippetItem>();

    public SnippetManager() {
    }

    public List<SnippetItem> getListSnippet() {
        try {
            List<IRepositoryViewObject> snippets = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory()
                    .getMetadata(ERepositoryObjectType.SNIPPETS).getMembers();

            List<SnippetItem> list = new ArrayList<SnippetItem>(snippets.size());
            for (IRepositoryViewObject repositoryObject : snippets) {
                list.add((SnippetItem) repositoryObject.getProperty().getItem());
            }
            return list;
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
            return Collections.EMPTY_LIST;
        }
    }

    public String applySnippet(SnippetItem snippetItem, Shell shell) {
        if (shell == null) {
            shell = Display.getCurrent().getActiveShell();
        }
        String content = VariableItemHelper.getInsertString(shell, snippetItem);
        // Update EOLs (bug 80231)
        String systemEOL = System.getProperty("line.separator"); //$NON-NLS-1$
        content = StringUtils.replace(content, "\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        content = StringUtils.replace(content, "\r", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        if (!"\n".equals(systemEOL) && systemEOL != null) { //$NON-NLS-1$
            content = StringUtils.replace(content, "\n", systemEOL); //$NON-NLS-1$
        }

        return content;
    }

}
