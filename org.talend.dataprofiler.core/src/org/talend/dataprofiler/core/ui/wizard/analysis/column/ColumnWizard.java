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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.core.manager.DQStructureManager;
import org.talend.dataprofiler.core.ui.wizard.analysis.AbstractAnalysisWizard;
import org.talend.dataprofiler.core.ui.wizard.analysis.connection.ConnAnalysisPageStep1;
import org.talend.dataquality.analysis.AnalysisType;

/**
 * @author zqin
 * 
 */
public class ColumnWizard extends AbstractAnalysisWizard {

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

    @Override
    protected void fillAnalysisEditorParam() {
        analysisName = "columnAnalysis";
        analysisType = AnalysisType.COLUMN;

        // TODO Need ZhongYong fill the parameters.
        // FolderProvider folderProvider = parameters.getFolderProvider();
        IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getProject(PluginConstant.DATA_PROFILING_PROJECTNAME)
                .getFolder(DQStructureManager.ANALYSIS);
        IFile samplefile = folder.getFile("sample.ana");
        this.pathName = samplefile.getLocationURI().getPath();
    }

}
