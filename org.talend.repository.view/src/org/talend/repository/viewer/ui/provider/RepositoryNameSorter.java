// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.ui.provider;

import java.util.Comparator;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * Name sorter for the repository view.<br/>
 * 
 * $Id$
 * 
 */
public class RepositoryNameSorter extends ViewerSorter {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ViewerSorter#getComparator()
     */
    @Override
    protected Comparator<String> getComparator() {

        return new Comparator<String>() {

            /*
             * (non-Javadoc)
             * 
             * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
             */

            public int compare(String o1, String o2) {
                // Replace all "_" to " " due to avoid this situation: job name
                // "a_b_c" before "a_b" in the job list.
                return o1.replaceAll("_", " ").compareToIgnoreCase( //$NON-NLS-1$ //$NON-NLS-2$
                        o2.replaceAll("_", " ")); //$NON-NLS-1$ //$NON-NLS-2$
            }
        };
    }

}
