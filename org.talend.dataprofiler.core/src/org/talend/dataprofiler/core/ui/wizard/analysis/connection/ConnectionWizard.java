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
package org.talend.dataprofiler.core.ui.wizard.analysis.connection;

import java.io.File;

import org.talend.cwm.constants.DevelopmentStatus;
import org.talend.cwm.helper.DescriptionHelper;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizard;
import org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizardPage;
import org.talend.dataprofiler.core.ui.wizard.analysis.MetadataWizardPage;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dq.analysis.AnalysisBuilder;
import org.talend.dq.analysis.parameters.ConnectionAnalysisParameter;
import org.talend.dq.analysis.parameters.IAnalysisParameterConstant;

/**
 * @author zqin
 * 
 */
public class ConnectionWizard extends AbstractAnalysisWizard {

    private MetadataWizardPage metadataPage;

    private ConnAnalysisPageStep0 page0;

    private ConnAnalysisPageStep1 page1;

    /**
     * 
     */
    public ConnectionWizard() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {

        metadataPage = new MetadataWizardPage();
        page0 = new ConnAnalysisPageStep0();
        page1 = new ConnAnalysisPageStep1();

        addPage(metadataPage);
        addPage(page0);
        addPage(page1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        // TODO Auto-generated method stub
        if (this.getContainer().getCurrentPage() != page1) {
            return false;
        }
        return super.canFinish();
    }

    @Override
    protected void fillAnalysisEditorParam() {
        ConnectionAnalysisParameter parameters = (ConnectionAnalysisParameter) getAnalysisParameter();
        this.analysisName = parameters.getAnalysisName();
        this.analysisType = parameters.getAnalysisType();
        this.pathName = parameters.getFolderProvider().getFolder() + File.separator + analysisName + ".ana";
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizard#fillAnalysisBuilder(org.talend.dq.analysis.AnalysisBuilder)
     */
    @Override
    protected void fillAnalysisBuilder(AnalysisBuilder analysisBuilder) {
        ConnectionAnalysisParameter parameters = (ConnectionAnalysisParameter) getAnalysisParameter();
        TdDataProvider tdProvider = parameters.getTdDataProvider();
        analysisBuilder.setAnalysisConnection(tdProvider);
        
        super.fillAnalysisBuilder(analysisBuilder);
    }

}
