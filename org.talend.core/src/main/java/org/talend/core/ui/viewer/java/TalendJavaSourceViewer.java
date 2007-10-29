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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.internal.filebuffers.SynchronizableDocument;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jdt.internal.ui.javaeditor.JavaSourceViewer;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.internal.texteditor.LineNumberColumn;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.rulers.IColumnSupport;
import org.eclipse.ui.texteditor.rulers.RulerColumnDescriptor;
import org.eclipse.ui.texteditor.rulers.RulerColumnRegistry;
import org.talend.commons.utils.threading.ExecutionLimiter;
import org.talend.core.CorePlugin;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.expressionbuilder.IExpressionDataBean;
import org.talend.expressionbuilder.test.shadow.Variable;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendJavaSourceViewer extends JavaSourceViewer {

    public static final String VIEWER_CLASS_NAME = "TalendJavaSourceViewer";

    private TalendJavaSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles, IPreferenceStore store) {
        super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles, store);
    }

    /**
     * This viewer is for test only.
     * 
     * @param composite
     * @param text
     * @param styles
     * @return
     */
    public static ISourceViewer createViewer2(Composite composite, int styles, IExpressionDataBean dataBean) {
        final CompilationUnitEditor editor = new HiddenJavaEditor();
        StringBuffer buff = new StringBuffer();
        buff.append("package internal;\n\n");
        buff.append("public class " + VIEWER_CLASS_NAME + " {\n");
        buff
                .append("\tprivate static final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();\n");

        buff.append(parseVariables(dataBean.getVariables()));
        buff.append("\tpublic " + dataBean.getExpressionType() + " myFunction(){\n");
        buff.append("\t\treturn \n");

        int length = buff.toString().length();
        String defaultValue = JavaTypesManager.getDefaultValueFromJavaType(dataBean.getExpressionType());
        buff.append(defaultValue + ";\t\n}\n}");
        editor.setInput(createFileEditorInput(buff.toString()));
        composite.setLayout(new FillLayout());
        editor.createPartControl(composite);
        IColumnSupport columnSupport = (IColumnSupport) editor.getAdapter(IColumnSupport.class);
        RulerColumnDescriptor lineNumberColumnDescriptor = RulerColumnRegistry.getDefault().getColumnDescriptor(
                LineNumberColumn.ID);
        if (lineNumberColumnDescriptor != null)
            columnSupport.setColumnVisible(lineNumberColumnDescriptor, false);

        final JavaSourceViewer viewer = (JavaSourceViewer) editor.getViewer();
        SynchronizableDocument doc = (SynchronizableDocument) viewer.getDocument();

        // doc.ignorePostNotificationReplaces();

        viewer.setVisibleRegion(length, defaultValue.length());
        viewer.getControl().addDisposeListener(new DisposeListener() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
             */
            public void widgetDisposed(DisposeEvent e) {
                editor.dispose();
            }

        });

        final ExecutionLimiter saveLimiter = new ExecutionLimiter(10, false) {

            @Override
            public void execute(final boolean isFinalExecution) {
                if (!viewer.getControl().isDisposed()) {
                    viewer.getControl().getDisplay().asyncExec(new Runnable() {

                        public void run() {
                            editor.doSave(new NullProgressMonitor());
                        }
                    });
                }
            }
        };
        viewer.getDocument().addDocumentListener(new IDocumentListener() {

            public void documentAboutToBeChanged(DocumentEvent event) {
            }

            public void documentChanged(DocumentEvent event) {
                saveLimiter.resetTimer();
                saveLimiter.startIfExecutable(true);
            }

        });
        Point sel = viewer.getSelectedRange();
        try {
            viewer.getDocument().replace(sel.x, defaultValue.length(), "");
        } catch (BadLocationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        viewer.enableOperation(ISourceViewer.CONTENTASSIST_PROPOSALS, true);
        viewer.enableOperation(ISourceViewer.QUICK_ASSIST, false);
        viewer.getTextWidget().addKeyListener(new KeyAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.stateMask == SWT.CTRL && e.keyCode == 32) {
                    viewer.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
                } else if (e.stateMask == SWT.CTRL && e.stateMask == SWT.SHIFT && e.keyCode == 'F') {
                    viewer.doOperation(ISourceViewer.FORMAT);
                }
            }
        });

        return viewer;
    }

    /**
     * DOC nrousseau Comment method "parseVariables".
     * 
     * @param variables
     * @return
     */
    private static String parseVariables(List<Variable> variables) {
        StringBuffer buff = new StringBuffer();
        Map<String, List<Variable>> classesToGenerate = new HashMap<String, List<Variable>>();

        if (variables != null) {
            for (Variable var : variables) {
                if (var.getName() != null && var.getTalendType() != null) {
                    if (var.getName().contains(".")) {
                        // class
                        StringTokenizer token = new StringTokenizer(var.getName(), ".");
                        String className = token.nextToken();
                        String newVarName = token.nextToken();
                        Variable newVar = new Variable(newVarName, var.getValue(), var.getTalendType(), var.isNullable());
                        if (!classesToGenerate.containsKey(className)) {
                            classesToGenerate.put(className, new ArrayList<Variable>());
                        }
                        classesToGenerate.get(className).add(newVar);
                    } else {
                        String typeToGenerate = JavaTypesManager.getTypeToGenerate(var.getTalendType(), var.isNullable());
                        buff.append("\tprivate " + typeToGenerate + " " + var.getName() + ";\\n");
                    }
                }
            }
            for (String className : classesToGenerate.keySet()) {
                buff.append("\tprivate class " + className + "Struct {\n");
                for (Variable var : classesToGenerate.get(className)) {
                    String typeToGenerate = JavaTypesManager.getTypeToGenerate(var.getTalendType(), var.isNullable());
                    buff.append("\t\tprivate " + typeToGenerate + " " + var.getName() + ";\n");
                }
                buff.append("\t};\n");
                buff.append("\tprivate " + className + "Struct " + className + "= new " + className + "Struct();\n");
            }
        }
        buff.append("\t" + VIEWER_CLASS_NAME + "() {\n");
        buff.append("\t\tglobalMap.put(\"NULL\", null);\n");
        if (variables != null) {
            for (String className : classesToGenerate.keySet()) {
                for (Variable var : classesToGenerate.get(className)) {
                    buff.append("\t\t" + className + "." + var.getName() + " =  " + className + "." + var.getName() + ";\n");
                }
            }
        }
        buff.append("\t}\n");

        return buff.toString();
    }

    public static ISourceViewer createViewer(Composite composite, String text, int styles) {
        IPreferenceStore store = JavaPlugin.getDefault().getCombinedPreferenceStore();
        final TalendJavaSourceViewer viewer = new TalendJavaSourceViewer(composite, null, null, false, styles, store);
        StringBuffer buff = new StringBuffer();
        buff.append("package internal;\n\n");
        buff.append("public class " + VIEWER_CLASS_NAME + " {\n");
        buff.append("public void myFunction(){\n");
        buff.append(text);
        Document doc = new Document(buff.toString());

        viewer.setDocument(doc, buff.toString().length() - text.length(), text.length());

        JavaTextTools tools = JavaPlugin.getDefault().getJavaTextTools();
        tools.setupJavaDocumentPartitioner(viewer.getDocument(), IJavaPartitions.JAVA_PARTITIONING);
        CompilationUnitEditor editor = new HiddenJavaEditor(viewer);
        TalendJavaViewerConfiguration config = new TalendJavaViewerConfiguration(tools.getColorManager(), store, editor);
        viewer.configure(config);

        viewer.getTextWidget().setFont(JFaceResources.getFont(PreferenceConstants.EDITOR_TEXT_FONT));

        viewer.enableOperation(ISourceViewer.CONTENTASSIST_PROPOSALS, true);
        viewer.setEditable(true);
        viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));

        viewer.getTextWidget().addKeyListener(new KeyAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.stateMask == SWT.CTRL && e.keyCode == 32) {
                    viewer.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
                } else if (e.stateMask == SWT.CTRL && e.stateMask == SWT.SHIFT && e.keyCode == 'F') {
                    viewer.doOperation(ISourceViewer.FORMAT);
                }
            }
        });

        return viewer;
    }

    private static FileEditorInput createFileEditorInput(String content) {
        FileEditorInput input = null;
        IPath path = new Path("src/internal/" + TalendJavaSourceViewer.VIEWER_CLASS_NAME + ".java");

        IFile file = null;
        try {

            file = CorePlugin.getDefault().getRunProcessService().getProject(LanguageManager.getCurrentLanguage()).getFile(path);
            InputStream codeStream = new ByteArrayInputStream(content.getBytes());
            if (!file.exists()) {
                file.create(codeStream, true, null);
            } else {
                file.setContents(codeStream, true, false, null);
            }
            input = new FileEditorInput(file);
        } catch (CoreException e) {
            e.printStackTrace();
        }
        return input;
    }
}
