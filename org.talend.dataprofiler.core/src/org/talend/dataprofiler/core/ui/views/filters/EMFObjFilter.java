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
package org.talend.dataprofiler.core.ui.views.filters;

import org.eclipse.jface.viewers.Viewer;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import orgomg.cwm.foundation.businessinformation.Description;

/**
 * @author rli
 * 
 */
public class EMFObjFilter extends AbstractViewerFilter {    

   public static final int FILTER_ID = 1;
    
    public int getId() {
        return FILTER_ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if ((element instanceof Description) || (element instanceof TdDataProvider)) {
            return false;
        }
        return true;
    }
}
