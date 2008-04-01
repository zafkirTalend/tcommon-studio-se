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
package org.talend.dataprofiler.core.ui.wizard.analysis.column;

import org.eclipse.jface.wizard.Wizard;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.dataprofiler.core.ui.wizard.analysis.connection.ConnAnalysisPageStep1;


/**
 * @author zqin
 *
 */
public class ColumnWizard extends Wizard {

    private ConnectionParameters parameters;
    
    private ConnAnalysisPageStep1 page1;
    /**
     * 
     */
    public ColumnWizard(ConnectionParameters parameters) {
        // TODO Auto-generated constructor stub
        this.parameters = parameters;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        
        page1 = new ConnAnalysisPageStep1(parameters);
        
        addPage(page1);
    }

}
