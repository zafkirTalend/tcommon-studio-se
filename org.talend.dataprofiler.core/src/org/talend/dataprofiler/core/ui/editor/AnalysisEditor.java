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

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.talend.dataprofiler.core.exception.ExceptionHandler;

/**
 * @author rli
 * 
 */
public class AnalysisEditor extends FormEditor {

    
    private IFormPage masterPage;

    private boolean isDirty;

    /**
     * 
     */
    public AnalysisEditor() {
    }

    protected void addPages() {
        try {
            masterPage = new MasterDetailsPage(this, "MasterPage", "analysis detail");
            addPage(masterPage);
        } catch (PartInitException e) {
            ExceptionHandler.process(e, Level.ERROR);
        }
    }

    public void doSave(IProgressMonitor monitor) {
        IEditorInput input = getEditorInput();
        if (input instanceof AnalysisEditorInuput) {
            AnalysisEditorInuput sqlInput = (AnalysisEditorInuput) input;
        }
    }
  
    public void doSaveAs() {
        doSave(null);
    }

    public boolean isSaveAsAllowed() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
     */
    @Override
    protected void setInput(IEditorInput input) {
        super.setInput(input);

        // Handle our own form of input
        if (input instanceof AnalysisEditor) {
            AnalysisEditorInuput analysisInput = (AnalysisEditorInuput) input;
            if (input != null) {
                isDirty = true;
                // isUntitled = true;
            }
        }

        setPartName(input.getName());
    }

}
