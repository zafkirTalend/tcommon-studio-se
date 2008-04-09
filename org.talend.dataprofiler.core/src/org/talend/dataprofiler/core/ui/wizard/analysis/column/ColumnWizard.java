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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorPart;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.exception.DataprofilerCoreException;
import org.talend.dataprofiler.core.exception.ExceptionHandler;
import org.talend.dataprofiler.core.manager.DQStructureManager;
import org.talend.dataprofiler.core.ui.editor.AnalysisEditor;
import org.talend.dataprofiler.core.ui.wizard.analysis.connection.ConnAnalysisPageStep1;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dq.analysis.AnalysisBuilder;
import org.talend.dq.analysis.AnalysisWriter;
import org.talend.utils.sugars.ReturnCode;

/**
 * @author zqin
 * 
 */
public class ColumnWizard extends Wizard {

    private static Logger log = Logger.getLogger(ColumnWizard.class);

    private ConnAnalysisPageStep1 page1;

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

        page1 = new ConnAnalysisPageStep1();

        addPage(page1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        // CorePlugin.getDefault().openEditor(folderProvider.getFolder());
        IEditorPart openEditor = null;
        try {
            openEditor = CorePlugin.getDefault().openEditor(createEmptyAnalysisFile());
        } catch (DataprofilerCoreException e) {
            e.printStackTrace();
            ExceptionHandler.process(e, Level.ERROR);
        }
        if (openEditor != null) {
            ((AnalysisEditor) openEditor).setDirty(true);
        }
        return true;
    }

    private File createEmptyAnalysisFile() throws DataprofilerCoreException {
        AnalysisBuilder analysisBuilder = new AnalysisBuilder();
        String analysisName = "columnAnalysis";

        boolean analysisInitialized = analysisBuilder.initializeAnalysis(analysisName, AnalysisType.COLUMN);
        if (!analysisInitialized) {
            throw new DataprofilerCoreException(analysisName + " failed to initialize!");
        }

        Analysis analysis = analysisBuilder.getAnalysis();
        // save analysis
        AnalysisWriter writer = new AnalysisWriter();

        // TODO Need ZhongYong fill the parameters.
        // FolderProvider folderProvider = parameters.getFolderProvider();
        IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getProject(PluginConstant.DATA_PROFILING_PROJECTNAME)
                .getFolder(DQStructureManager.ANALYSIS);
        IFile samplefile = folder.getFile("sample.ana");
        File file = new File(samplefile.getLocationURI());
        ReturnCode saved = writer.save(analysis, file);
        if (saved.isOk()) {
            log.info("Saved in  " + file.getAbsolutePath());
        } else {
            throw new DataprofilerCoreException("Problem saving file: " + file.getAbsolutePath() + ": " + saved.getMessage());
        }
        return file;

    }

}
