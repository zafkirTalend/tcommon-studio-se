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

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorPart;
import org.talend.dataprofiler.core.CorePlugin;
import org.talend.dataprofiler.core.exception.DataprofilerCoreException;
import org.talend.dataprofiler.core.exception.ExceptionHandler;
import org.talend.dataprofiler.core.ui.editor.AnalysisEditor;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dq.analysis.AnalysisBuilder;
import org.talend.dq.analysis.AnalysisWriter;
import org.talend.utils.sugars.ReturnCode;

/**
 * AbstractAnalysisWizard can creat a empty analysis file.
 */
public abstract class AbstractAnalysisWizard extends Wizard {

    static Logger log = Logger.getLogger(AbstractAnalysisWizard.class);

    protected String analysisName;

    protected AnalysisType analysisType;

    protected String pathName;

    public AbstractAnalysisWizard() {
        super();
    }

    @Override
    public boolean performFinish() {
        // CorePlugin.getDefault().openEditor(folderProvider.getFolder());
        IEditorPart openEditor = null;
        
        try {
            this.fillAnalysisEditorParam();
            if (!checkAnalysisEditorParam()) {
                return false;
            }

            openEditor = CorePlugin.getDefault().openEditor(createEmptyAnalysisFile());
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandler.process(e, Level.ERROR);
        }
        if (openEditor != null) {
            ((AnalysisEditor) openEditor).setDirty(true);
        }
        return true;
    }

    protected abstract void fillAnalysisEditorParam();

    private boolean checkAnalysisEditorParam() {
        if (analysisType == null) {
            log.error("The field 'analysisType' haven't assigned a value.");
            return false;
        } else if (analysisName == null) {
            log.error("The field 'analysisName' haven't assigned a value.");
            return false;
        } else if (pathName == null) {
            log.error("The field 'fileURI' haven't assigned a value.");
            return false;
        }
        return true;
    }

    private File createEmptyAnalysisFile() throws DataprofilerCoreException {
        AnalysisBuilder analysisBuilder = new AnalysisBuilder();
        boolean analysisInitialized = analysisBuilder.initializeAnalysis(analysisName, analysisType);
        if (!analysisInitialized) {
            throw new DataprofilerCoreException(analysisName + " failed to initialize!");
        }
        Analysis analysis = analysisBuilder.getAnalysis();
        fillAnalysisBuilder(analysisBuilder);
        AnalysisWriter writer = new AnalysisWriter();
        File file = new File(this.pathName);
        ReturnCode saved = writer.save(analysis, file);
        if (saved.isOk()) {
            log.info("Saved in  " + file.getAbsolutePath());
        } else {
            throw new DataprofilerCoreException("Problem saving file: " + file.getAbsolutePath() + ": " + saved.getMessage());
        }
        CorePlugin.getDefault().refreshWorkSpace();
        return file;

    }

    protected void fillAnalysisBuilder(AnalysisBuilder analysisBuilder) {
    }

}
