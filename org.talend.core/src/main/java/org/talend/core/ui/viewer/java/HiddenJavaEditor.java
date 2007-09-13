// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.ui.viewer.java;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.talend.core.CorePlugin;
import org.talend.core.language.LanguageManager;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class HiddenJavaEditor extends CompilationUnitEditor {

    private IEditorInput input;

    private IDocument document;

    public HiddenJavaEditor(IDocument document) {
        super();
        this.document = document;
    }

    public IEditorSite getEditorSite() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorSite();
    }

    public IWorkbenchPartSite getSite() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite();
        // return super.getSite();
    }

    String oldCode;

    public IEditorInput getEditorInput() {
        String newCode = document.get();
        if (!newCode.equals(oldCode)) {
            IPath path = new Path("src/internal/" + TalendJavaSourceViewer.VIEWER_CLASS_NAME + ".java");
            IFile file = null;
            try {

                file = CorePlugin.getDefault().getRunProcessService().getProject(LanguageManager.getCurrentLanguage()).getFile(
                        path);
                InputStream codeStream = new ByteArrayInputStream(newCode.getBytes());
                if (!file.exists()) {
                    file.create(codeStream, true, null);
                } else {
                    file.setContents(codeStream, true, false, null);
                }
                input = new FileEditorInput(file);
            } catch (CoreException e) {
                e.printStackTrace();
            }
            oldCode = newCode;
        }
        return input;
    }
}
