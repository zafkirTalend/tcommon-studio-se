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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * @author rli
 *
 */
public class AnalysisEditor extends FormEditor {

    /**
     * 
     */
    public AnalysisEditor() {
        // TODO Auto-generated constructor stub
    }

    protected void addPages() {
        try {
            addPage(new MasterDetailsPage(this, "MasterPage", "analysis detail"));
        } catch (PartInitException e) {
            //
        }
    }

    public void doSave(IProgressMonitor monitor) {
    }

    public void doSaveAs() {
    }

    public boolean isSaveAsAllowed() {
        return false;
    }
}
