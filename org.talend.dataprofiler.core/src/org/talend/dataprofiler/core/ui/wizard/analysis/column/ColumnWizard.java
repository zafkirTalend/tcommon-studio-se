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

import java.io.File;

import org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizard;
import org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizardPage;
import org.talend.dataprofiler.core.ui.wizard.analysis.MetadataWizardPage;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dq.analysis.parameters.ConnectionAnalysisParameter;
import org.talend.dq.analysis.parameters.IAnalysisParameterConstant;

/**
 * @author zqin
 * 
 */
public class ColumnWizard extends AbstractAnalysisWizard {

    private MetadataWizardPage metadataPage;

    /**
     * 
     */
    public ColumnWizard() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {

        metadataPage = new MetadataWizardPage();

        addPage(metadataPage);
    }

    @Override
    protected void fillAnalysisEditorParam() {
        ConnectionAnalysisParameter parameters = (ConnectionAnalysisParameter) AbstractAnalysisWizardPage.getConnectionParams();
        this.analysisName = parameters.getAnalysisMetadate().get(IAnalysisParameterConstant.ANALYSIS_NAME);
        this.analysisType = AnalysisType.get(parameters.getAnalysisTypeName());
        this.pathName = parameters.getFolderProvider().getFolder() + File.separator + analysisName + ".ana";
    }

}
