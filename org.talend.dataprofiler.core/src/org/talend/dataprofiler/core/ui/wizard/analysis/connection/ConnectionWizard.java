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

import org.apache.log4j.Level;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.RefreshAction;
import org.talend.cwm.constants.DevelopmentStatus;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.softwaredeployment.TdDataProvider;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.exception.DataprofilerCoreException;
import org.talend.dataprofiler.core.exception.ExceptionHandler;
import org.talend.dataprofiler.core.ui.editor.AnalysisEditor;
import org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizardPage;
import org.talend.dataprofiler.core.ui.wizard.analysis.MetadataWizardPage;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dq.analysis.AnalysisBuilder;
import org.talend.dq.analysis.AnalysisWriter;
import org.talend.dq.analysis.parameters.ConnectionAnalysisParameter;
import org.talend.dq.analysis.parameters.IAnalysisParameterConstant;
import org.talend.utils.sugars.ReturnCode;


/**
 * @author zqin
 *
 */
public class ConnectionWizard extends Wizard {
    
    private MetadataWizardPage metadataPage;
    
    private ConnAnalysisPageStep0 page0;
    
    private ConnAnalysisPageStep1 page1;
    /**
     * 
     */
    public ConnectionWizard() {
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        // TODO Auto-generated method stub
        ConnectionAnalysisParameter parameters = (ConnectionAnalysisParameter) AbstractAnalysisWizardPage.getConnectionParams();
        
        if (parameters != null) {
            try {
                String analysisName = parameters.getAnalysisMetadate().get(IAnalysisParameterConstant.ANALYSIS_NAME);
                String analysisStatue = parameters.getAnalysisMetadate().get(IAnalysisParameterConstant.ANALYSIS_STATUS);
                
                AnalysisBuilder analysisBuilder = new AnalysisBuilder();
                boolean analysisInitialized = analysisBuilder.initializeAnalysis(parameters.getAnalysisTypeName(), 
                        AnalysisType.get(parameters.getAnalysisTypeName()));
                if (analysisInitialized) {
                    TdDataProvider dataManager = parameters.getTdDataProvider();
                    analysisBuilder.setAnalysisConnection(dataManager);
                    
//                    DqRepositoryViewService.saveDomain(null, parameters.getFolderProvider());
                    TaggedValueHelper.setDevStatus(dataManager, DevelopmentStatus.getByName(analysisStatue));
                    Analysis analysis = analysisBuilder.getAnalysis();
//                    IAnalysisExecutor exec = new ColumnAnalysisExecutor();
//                    ReturnCode executed = exec.execute(analysis);
//                    save data provider
//                    DqRepositoryViewService.saveDataProviderAndStructure(dataManager, parameters.getFolderProvider());
//                    save analysis
                    AnalysisWriter writer = new AnalysisWriter();    
                    File file = new File(parameters.getFolderProvider().getFolder() + File.separator + analysisName + ".ana");
                    
                    ReturnCode saved = writer.save(analysis, file);
                    if (saved.isOk()) {
                        RefreshAction refreshAction = new RefreshAction(this.getShell());
                        refreshAction.run();
                        
                        //open the editor
                        IEditorPart openEditor = null;
                        try {
                            openEditor = CorePlugin.getDefault().openEditor(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                            ExceptionHandler.process(e, Level.ERROR);
                        }
                        if (openEditor != null) {
                            ((AnalysisEditor) openEditor).setDirty(true);
                        }
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    /* (non-Javadoc)
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

    /* (non-Javadoc)
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
}
