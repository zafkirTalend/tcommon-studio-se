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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.pref.PreferenceConstant;

import orgomg.cwm.foundation.businessinformation.Description;

/**
 * @author rli
 * 
 */
public class EMFObjFilter extends ViewerFilter {

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
        if ((!CorePlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstant.REPORTINGENABLE))
                && (element instanceof IFolder)) {
            IPath projectRelativePath = ((IFolder) element).getFullPath();
            if (projectRelativePath.toString().equals(PluginConstant.REPORT_FOLDER_PATH)) {
                return false;
            }
        }
        return true;
    }
}
