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
package org.talend.dataprofiler.core.ui.editor.composite;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;

/**
 * @author rli
 * 
 */
public class AnasisColumnTreeViewer {

    private TreeViewer treeView;

    public AnasisColumnTreeViewer(Tree tree) {
        treeView = new TreeViewer(tree);
    }

    
    /**
     * @return the treeView
     */
    public TreeViewer getTreeView() {
        return treeView;
    }

    
    
}
