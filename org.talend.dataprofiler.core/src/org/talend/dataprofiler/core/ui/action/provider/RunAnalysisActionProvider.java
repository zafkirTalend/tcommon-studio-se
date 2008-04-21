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
package org.talend.dataprofiler.core.ui.action.provider;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.talend.commons.emf.EMFUtil;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.helper.AnaResourceFileHelper;
import org.talend.dataprofiler.core.ui.progress.ProgressUI;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dataquality.helpers.AnalysisHelper;
import org.talend.dq.analysis.AnalysisExecutor;
import org.talend.dq.analysis.ColumnAnalysisExecutor;
import org.talend.dq.analysis.ConnectionAnalysisExecutor;
import org.talend.utils.sugars.ReturnCode;

/**
 * DOC rli class global comment. Detailled comment
 */
public class RunAnalysisActionProvider extends CommonActionProvider {

    private static Logger log = Logger.getLogger(RunAnalysisActionProvider.class);

    private IAction runAnalysisAction;

    private IFile currentSelection;

    public RunAnalysisActionProvider() {
    }

    public void init(ICommonActionExtensionSite anExtensionSite) {

        if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            runAnalysisAction = new RunAnalysisAction();
        }
    }

    /**
     * Adds a submenu to the given menu with the name "New Component".
     */
    public void fillContextMenu(IMenuManager menu) {
        Object obj = ((TreeSelection) this.getContext().getSelection()).getFirstElement();
        if (obj instanceof IFile) {
            currentSelection = (IFile) obj;
        }
        menu.add(runAnalysisAction);
    }

    /**
     * @author rli
     * 
     */
    private class RunAnalysisAction extends Action {

        public RunAnalysisAction() {
            super("Run");
            setImageDescriptor(ImageLib.getImageDescriptor(ImageLib.REFRESH_IMAGE));
        }

        /*
         * (non-Javadoc) Method declared on IAction.
         */
        public void run() {
            Analysis analysis = null;
            if (currentSelection.getName().endsWith(PluginConstant.ANA_SUFFIX)) {
                analysis = AnaResourceFileHelper.getInstance().getAnalysis(currentSelection);
            }
            AnalysisType analysisType = AnalysisHelper.getAnalysisType(analysis);
            AnalysisExecutor exec = null;
            switch (analysisType) {
            case COLUMN:
                exec = new ColumnAnalysisExecutor();
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
                        Resource resource = finalAnalysis.eResource();
                        if (resource != null) {
                            EMFUtil.saveResource(resource);
                        }
                    }
                    // if(executed.isOk()){
                    // throw new InvocationTargetException(executed.);
                    // }
                }
            };
            try {
                ProgressUI.popProgressDialog(op, shell);
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}
