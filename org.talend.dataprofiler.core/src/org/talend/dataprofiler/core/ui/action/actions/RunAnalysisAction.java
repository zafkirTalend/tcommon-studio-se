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
package org.talend.dataprofiler.core.ui.action.actions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.helper.AnaResourceFileHelper;
import org.talend.dataprofiler.core.ui.editor.AnalysisEditor;
import org.talend.dataprofiler.core.ui.editor.AnalysisEditorInuput;
import org.talend.dataprofiler.core.ui.editor.ColumnMasterDetailsPage;
import org.talend.dataprofiler.core.ui.progress.ProgressUI;
import org.talend.dataprofiler.core.ui.views.DQRespositoryView;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dataquality.helpers.AnalysisHelper;
import org.talend.dq.analysis.AnalysisExecutor;
import org.talend.dq.analysis.ColumnAnalysisExecutor;
import org.talend.dq.analysis.ColumnAnalysisSqlExecutor;
import org.talend.dq.analysis.ConnectionAnalysisExecutor;
import org.talend.utils.sugars.ReturnCode;


/**
 * DOC zqin  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend.epf 1 2006-09-29 17:06:40Z zqin $
 *
 */
public class RunAnalysisAction extends Action implements ICheatSheetAction {

    private static Logger log = Logger.getLogger(RunAnalysisAction.class);
    
    private TreeViewer treeViewer;
    
    private IFile currentSelection;
    
    private Analysis analysis = null;
    
    public RunAnalysisAction() {
        
        super("Run");
        setImageDescriptor(ImageLib.getImageDescriptor(ImageLib.REFRESH_IMAGE));
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {

        if (currentSelection == null) {
            AnalysisEditor editor = (AnalysisEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if (editor != null) {
                ColumnMasterDetailsPage page = (ColumnMasterDetailsPage) editor.getMasterPage();
                AnalysisEditorInuput input = (AnalysisEditorInuput) page.getEditorInput();
                
                File file = input.getFile();
                
                if (file.getName().endsWith(PluginConstant.ANA_SUFFIX)) {
                    analysis = AnaResourceFileHelper.getInstance().findAnalysis(file);
                }
            }
            
        } else {
            
            if (currentSelection.getName().endsWith(PluginConstant.ANA_SUFFIX)) {
                analysis = AnaResourceFileHelper.getInstance().findAnalysis(currentSelection);
            }
        }
        
        AnalysisType analysisType = AnalysisHelper.getAnalysisType(analysis);
        AnalysisExecutor exec = null;
        switch (analysisType) {
        case MULTIPLE_COLUMN:
            exec = new ColumnAnalysisSqlExecutor();
            break;
        case CONNECTION:
            exec = new ConnectionAnalysisExecutor();
            break;
        default:
            exec = new ColumnAnalysisExecutor();
        }

        final Analysis finalAnalysis = analysis;
        final AnalysisExecutor finalExec = exec;
        final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        IRunnableWithProgress op = new IRunnableWithProgress() {

            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                ReturnCode executed = finalExec.execute(finalAnalysis);
                if (log.isInfoEnabled()) {
                    log.info("Analysis " + finalAnalysis.getName() + "execution code: " + executed);
                }
                if (executed.isOk()) {
                    AnaResourceFileHelper.getInstance().save(finalAnalysis);
                }
            }
        };
        try {
            ProgressUI.popProgressDialog(op, shell);
            if (treeViewer == null) {
                DQRespositoryView view = (DQRespositoryView) CorePlugin.getDefault().findView(PluginConstant.DQ_VIEW_ID);
                view.refresh();
            } else {
                treeViewer.refresh();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.cheatsheets.ICheatSheetAction#run(java.lang.String[], org.eclipse.ui.cheatsheets.ICheatSheetManager)
     */
    public void run(String[] params, ICheatSheetManager manager) {

        run();
    }
    
    /**
     * Sets the currentSelection.
     * @param currentSelection the currentSelection to set
     */
    public void setCurrentSelection(IFile currentSelection) {
        this.currentSelection = currentSelection;
    }
    
    /**
     * Sets the treeViewer.
     * @param treeViewer the treeViewer to set
     */
    public void setTreeViewer(TreeViewer treeViewer) {
        this.treeViewer = treeViewer;
    }

}
