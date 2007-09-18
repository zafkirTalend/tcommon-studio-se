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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.SubActionBars;
import org.eclipse.ui.internal.EditorAreaHelper;
import org.eclipse.ui.internal.EditorManager;
import org.eclipse.ui.internal.EditorReference;
import org.eclipse.ui.internal.EditorSite;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.registry.EditorDescriptor;
import org.eclipse.ui.part.FileEditorInput;
import org.talend.core.CorePlugin;
import org.talend.core.language.LanguageManager;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class HiddenJavaEditor extends CompilationUnitEditor {

    private String filename;

    private IEditorInput input;

    private ISourceViewer viewer;

    private IDocument document;

    private EditorSite site;

    public HiddenJavaEditor(ISourceViewer viewer) {
        this();
        this.viewer = viewer;
    }

    /**
     * DOC nrousseau HiddenJavaEditor constructor comment.
     * 
     * @param composite
     */
    public HiddenJavaEditor() {
        super();
        filename = "src/internal/" + TalendJavaSourceViewer.VIEWER_CLASS_NAME + ".java";
        if (viewer != null) {
            WorkbenchWindow ww = (WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            EditorManager manager = new EditorManager(ww, (WorkbenchPage) ww.getActivePage(), new EditorAreaHelper(
                    (WorkbenchPage) ww.getActivePage()));
            EditorDescriptor desc = EditorDescriptor.createForProgram(filename);
            EditorReference ref = new EditorReference(manager, getEditorInput(), desc);
            site = new EditorSite(ref, this, (WorkbenchPage) ww.getActivePage(), desc);
            IEditorSite baseSite = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()
                    .getEditorSite();
            site.setActionBars((SubActionBars) baseSite.getActionBars());
        }
    }

    public IEditorSite getEditorSite() {
        if (site != null) {
            return site;
        }
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorSite();
    }

    public IWorkbenchPartSite getSite() {
        // return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite();
        return getEditorSite();
    }

    String oldCode;

    public IEditorInput getEditorInput() {
        if (viewer == null) {
            return super.getEditorInput();
        }
        if (viewer.getDocument() == null) {
            return input;
        }
        String newCode = viewer.getDocument().get() + "\n}\n}";
        if (!newCode.equals(oldCode)) {
            IPath path = new Path(filename);
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor#reconciled(org.eclipse.jdt.core.dom.CompilationUnit,
     * boolean, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void reconciled(CompilationUnit ast, boolean forced, IProgressMonitor progressMonitor) {
        super.reconciled(ast, true, progressMonitor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor#createJavaSourceViewer(org.eclipse.swt.widgets.Composite,
     * org.eclipse.jface.text.source.IVerticalRuler, org.eclipse.jface.text.source.IOverviewRuler, boolean, int,
     * org.eclipse.jface.preference.IPreferenceStore)
     */
    @Override
    protected ISourceViewer createJavaSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean isOverviewRulerVisible, int styles, IPreferenceStore store) {
        if (viewer != null) {
            return viewer;
        } else {
            return super.createJavaSourceViewer(parent, verticalRuler, overviewRuler, isOverviewRulerVisible, styles, store);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.internal.ui.javaeditor.JavaEditor#createJavaSourceViewerConfiguration()
     */
    @Override
    protected JavaSourceViewerConfiguration createJavaSourceViewerConfiguration() {
        JavaTextTools tools = JavaPlugin.getDefault().getJavaTextTools();
        TalendJavaViewerConfiguration config = new TalendJavaViewerConfiguration(tools.getColorManager(), getPreferenceStore(),
                this, IJavaPartitions.JAVA_PARTITIONING);
        return config;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.internal.ui.javaeditor.JavaEditor#setPreferenceStore(org.eclipse.jface.preference.IPreferenceStore)
     */
    @Override
    protected void setPreferenceStore(IPreferenceStore store) {
        super.setPreferenceStore(store);
        if (getSourceViewerConfiguration() instanceof JavaSourceViewerConfiguration) {
            JavaTextTools textTools = JavaPlugin.getDefault().getJavaTextTools();
            setSourceViewerConfiguration(new TalendJavaViewerConfiguration(textTools.getColorManager(), store, this,
                    IJavaPartitions.JAVA_PARTITIONING));
        }
    }
}
