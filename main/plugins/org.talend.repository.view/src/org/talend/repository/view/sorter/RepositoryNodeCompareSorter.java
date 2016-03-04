// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.view.sorter;

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class RepositoryNodeCompareSorter implements IRepositoryNodeSorter {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.view.sorter.IRepositoryNodeSorter#sort(org.eclipse.jface.viewers.Viewer,
     * java.lang.Object, java.lang.Object[])
     */
    @Override
    public void sort(Viewer viewer, Object parentPath, Object[] children) {
        if (parentPath instanceof TreePath) {
            Object lastSegm = ((TreePath) parentPath).getLastSegment();
            if (lastSegm instanceof RepositoryNode) {
                sortChildren((RepositoryNode) lastSegm, children);
            }
        } else if (parentPath instanceof RepositoryNode) {
            sortChildren((RepositoryNode) parentPath, children);
        }
    }

    protected void sortChildren(RepositoryNode parent, Object[] children) {
        sortChildren(children);
    }

    protected void sortChildren(Object[] children) {
        if (children == null) {
            return;
        }
        Arrays.sort(children, new Comparator<Object>() {

            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof RepositoryNode && o2 instanceof RepositoryNode) {
                    return compareNode((RepositoryNode) o1, (RepositoryNode) o2);
                }
                return 0; // no sort for others
            }
        });
    }

    protected void swap(Object[] children, int first, int second) {
        if (first != second && first > -1 && second > -1 && first < children.length && second < children.length) {
            Object t = children[first];
            children[first] = children[second];
            children[second] = t;
        }
    }

    protected int compareNode(RepositoryNode n1, RepositoryNode n2) {
        return 0; // keep order
    }
}
