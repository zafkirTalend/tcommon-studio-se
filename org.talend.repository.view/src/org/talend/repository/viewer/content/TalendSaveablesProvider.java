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
package org.talend.repository.viewer.content;

import org.eclipse.ui.Saveable;
import org.eclipse.ui.navigator.SaveablesProvider;

/**
 * Created by Marvin Wang on Apr. 20, 2012.
 */
public class TalendSaveablesProvider extends SaveablesProvider {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveables()
     */
    @Override
    public Saveable[] getSaveables() {
        return new Saveable[0];
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.SaveablesProvider#getElements(org.eclipse.ui.Saveable)
     */
    @Override
    public Object[] getElements(Saveable saveable) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveable(java.lang.Object)
     */
    @Override
    public Saveable getSaveable(Object element) {
        // TODO Auto-generated method stub
        return null;
    }

}
