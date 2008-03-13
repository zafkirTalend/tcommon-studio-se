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
package org.talend.dataprofiler.core.ui.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonNavigator;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.ui.views.filters.EMFObjFilter;

/**
 * @author rli
 * 
 */
public class DQRespositoryView extends CommonNavigator {

    public DQRespositoryView() {
        super();
        CorePlugin.getDefault().checkDQStructure();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.navigator.CommonNavigator#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        this.getCommonViewer().addFilter(new EMFObjFilter());
    }
    
  
}
