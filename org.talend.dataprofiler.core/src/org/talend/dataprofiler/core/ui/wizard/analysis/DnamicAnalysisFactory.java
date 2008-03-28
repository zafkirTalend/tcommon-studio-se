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
package org.talend.dataprofiler.core.ui.wizard.analysis;

import org.eclipse.jface.wizard.WizardPage;
import org.talend.cwm.management.connection.ConnectionParameters;
import org.talend.dataprofiler.core.ui.wizard.analysis.connection.ConnAnalysisPageStep0;


/**
 * @author zqin
 *
 */
public class DnamicAnalysisFactory {

    /**
     * 
     */
    public DnamicAnalysisFactory() {
        // TODO Auto-generated constructor stub
    }
    
    public static WizardPage[] getConnectionPages(ConnectionParameters parameters) {
        WizardPage[] connnectionPages = new WizardPage[1];
        connnectionPages[0] = new ConnAnalysisPageStep0(parameters);
        
        return connnectionPages;
    }

}
