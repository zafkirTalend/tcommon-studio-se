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
import java.net.URI;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.part.FileEditorInput;
import org.talend.dataprofiler.core.exception.ExceptionHandler;

/**
 * @author rli
 * 
 */
public class AnalysisEditor extends FormEditor {

    private IFormPage masterPage;

    private boolean isDirty = false;

    /**
     * 
     */
    public AnalysisEditor() {
    }

    protected void addPages() {
        try {
            masterPage = new ColumnMasterDetailsPage(this, "MasterPage", "analysis settings");
            addPage(masterPage);
        } catch (PartInitException e) {
            ExceptionHandler.process(e, Level.ERROR);
        }
    }

    public void doSave(IProgressMonitor monitor) {
        if (masterPage.isDirty()) {
            masterPage.doSave(monitor);
        }
        this.isDirty = false;
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
     * @see org.eclipse.ui.forms.editor.FormEditor#isDirty()
     */
    @Override
    public boolean isDirty() {
        return isDirty || super.isDirty();
    }
    
    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
     */
    @Override
    protected void setInput(IEditorInput input) {
        if (input == null) {
            return;
        }

        AnalysisEditorInuput analysisInput = null;
        if (input instanceof FileEditorInput) {
            URI uri = ((FileEditorInput) input).getFile().getLocationURI();
            analysisInput = new AnalysisEditorInuput(new File(uri));
            super.setInput(analysisInput);
        } else if (input instanceof AnalysisEditorInuput) {
            super.setInput(input);
        }

        setPartName(input.getName());
    }

}
