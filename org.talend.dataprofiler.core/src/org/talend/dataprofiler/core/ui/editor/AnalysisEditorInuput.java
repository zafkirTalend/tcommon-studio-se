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
package org.talend.dataprofiler.core.ui.editor;

import java.io.File;

import org.eclipse.ui.IMemento;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.helper.AnaResourceFileHelper;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dataquality.helpers.AnalysisHelper;

/**
 * @author rli
 * 
 */
public class AnalysisEditorInuput extends AbstractEditorInput {

    private Analysis analysis;

    public AnalysisEditorInuput(String name) {
        super(name);
    }

    public AnalysisEditorInuput(File file) {
        super(file);
    }

    public Analysis getAnalysis() {
        if (analysis != null) {
            return analysis;
        }
        analysis = AnaResourceFileHelper.getInstance().findAnalysis(fFile);
        return analysis;
    }

    public AnalysisType getAnalysisType() {
        return AnalysisHelper.getAnalysisType(getAnalysis());
    }
    
    public void saveState(IMemento memento) {
        super.saveState(memento);
        memento.putString(PluginConstant.FILE_SUFFIX, PluginConstant.ANA_SUFFIX);
    }
}
