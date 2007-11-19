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
package org.talend.core.ui.viewer.java;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.internal.filebuffers.SynchronizableDocument;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.text.FastJavaPartitionScanner;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.OverviewRuler;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.temp.ECodePart;
import org.talend.core.ui.viewer.ReconcilerViewer;
import org.talend.expressionbuilder.IExpressionDataBean;
import org.talend.expressionbuilder.test.shadow.Variable;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class TalendJavaSourceViewer extends ReconcilerViewer {

    private String filename;

    public static final String VIEWER_CLASS_NAME = "TalendJavaSourceViewer";

    private static int currentId = 0;

    private ICompilationUnit compilationUnit;

    /**
     * DOC nrousseau TalendJavaSourceViewer2 constructor comment.
     * 
     * @param parent
     * @param verticalRuler
     * @param overviewRuler
     * @param showAnnotationsOverview
     * @param styles
     * @param annotationAccess
     * @param sharedColors
     * @param checkCode
     */
    public TalendJavaSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles, IAnnotationAccess annotationAccess, ISharedTextColors sharedColors,
            boolean checkCode, IDocument document) {
        super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles, annotationAccess, sharedColors, checkCode,
                document);
        IPath packagePath = new Path("/.Java/src/internal");
        filename = TalendJavaSourceViewer.VIEWER_CLASS_NAME + currentId++ + ".java";
        IPackageFragment packageFragment;
        try {
            packageFragment = CorePlugin.getDefault().getRunProcessService().getJavaProject().findPackageFragment(packagePath);
            compilationUnit = packageFragment.createCompilationUnit(filename, document.get(), false, new NullProgressMonitor());
        } catch (JavaModelException e) {
            ExceptionHandler.process(e);
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }

        setContents(document);
    }

    public static ISourceViewer createViewer(Composite composite, int styles, boolean checkCode) {
        SynchronizableDocument doc = new SynchronizableDocument();
        return initializeViewer(composite, styles, true, new Document(), -1);
    }

    public static ISourceViewer createViewerWithVariables(Composite composite, int styles, IExpressionDataBean dataBean) {
        IDocument document = new Document();
        StringBuffer buff = new StringBuffer();
        buff.append("package internal;\n\n");
        buff.append("public class " + VIEWER_CLASS_NAME + " {\n");
        buff.append("\tprivate static java.util.Properties context = new java.util.Properties();\n");
        buff
                .append("\tprivate static final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();\n");

        buff.append(parseVariables(dataBean.getVariables()));
        buff.append("\tpublic " + dataBean.getExpressionType() + " myFunction(){\n");
        buff.append("\t\treturn \n");

        int length = buff.toString().length();
        String defaultValue = "";
        buff.append(defaultValue + ";\t\n}\n}");

        document.set(buff.toString());

        return initializeViewer(composite, styles, true, document, length);
    }

    // for test only
    public static ISourceViewer createViewerForComponent(Composite composite, int styles, IDocument document,
            List<Variable> variableList, String uniqueName, ECodePart codePart) {

        String mainPart = "[" + uniqueName + " " + codePart.getName() + " ] start";

        int documentOffset = -1;

        IDocument newDoc = new Document();
        newDoc.set(document.get());
        FindReplaceDocumentAdapter frda = new FindReplaceDocumentAdapter(newDoc);
        try {
            Region region = (Region) frda.find(0, mainPart, true, false, false, false);
            if (region != null) {
                documentOffset = region.getOffset();
                documentOffset = newDoc.getLineOffset(newDoc.getLineOfOffset(documentOffset) + 2);
            }
        } catch (BadLocationException e) {
            ExceptionHandler.process(e);
        }

        return initializeViewer(composite, styles, true, newDoc, documentOffset);
    }

    private static ISourceViewer initializeViewer(Composite composite, int styles, boolean checkCode, IDocument document,
            int visibleOffset) {
        IAnnotationAccess annotationAccess = new DefaultMarkerAnnotationAccess();
        ISharedTextColors sharedColors = EditorsPlugin.getDefault().getSharedTextColors();
        IOverviewRuler overviewRuler = null;
        IVerticalRuler verticalRuler = null;
        if (checkCode) {
            overviewRuler = new OverviewRuler(annotationAccess, 12, sharedColors);
            Iterator e = EditorsPlugin.getDefault().getMarkerAnnotationPreferences().getAnnotationPreferences().iterator();
            while (e.hasNext()) {
                AnnotationPreference preference = (AnnotationPreference) e.next();
                if (preference.contributesToHeader()) {
                    overviewRuler.addHeaderAnnotationType(preference.getAnnotationType());
                }
            }
            verticalRuler = new VerticalRuler(12);
        }
        ISourceViewer viewer = new TalendJavaSourceViewer(composite, verticalRuler, overviewRuler, checkCode, styles,
                annotationAccess, sharedColors, checkCode, document);

        if (visibleOffset != -1) {
            viewer.setVisibleRegion(visibleOffset, 0);
        }

        return viewer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#getDocumentPartitioner()
     */
    @Override
    protected IDocumentPartitioner getDocumentPartitioner() {
        return new FastPartitioner(new FastJavaPartitionScanner(), new String[] { IJavaPartitions.JAVA_DOC,
                IJavaPartitions.JAVA_MULTI_LINE_COMMENT, IJavaPartitions.JAVA_SINGLE_LINE_COMMENT, IJavaPartitions.JAVA_STRING,
                IJavaPartitions.JAVA_CHARACTER });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#initializeModel()
     */
    @Override
    protected void initializeModel() {
        IDocumentProvider provider = JavaPlugin.getDefault().getCompilationUnitDocumentProvider();
        IEditorInput ei = new FileEditorInput(file);
        try {
            provider.connect(ei);
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }
        IDocument document = getDocument();
        IAnnotationModel model = provider.getAnnotationModel(ei);
        model.connect(document);

        if (document != null) {
            setDocument(document, model);
            showAnnotations(model != null);
        }

        ProjectionSupport projectionSupport = new ProjectionSupport(this, annotationAccess, sharedColors);
        projectionSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.error");
        projectionSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.warning");
        projectionSupport.install();

        getSourceViewerDecorationSupport().install(JavaPlugin.getDefault().getCombinedPreferenceStore());

        doOperation(ProjectionViewer.TOGGLE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#installViewerConfiguration()
     */
    @Override
    protected void installViewerConfiguration() {
        IPreferenceStore store = JavaPlugin.getDefault().getCombinedPreferenceStore();
        JavaTextTools tools = JavaPlugin.getDefault().getJavaTextTools();
        configure(new TalendJavaViewerConfiguration(tools.getColorManager(), store, this));

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#reconcile()
     */
    @Override
    public void reconcile() {
        if (compilationUnit != null) {
            try {
                compilationUnit.reconcile(ICompilationUnit.NO_AST, false, false, null, new NullProgressMonitor());
            } catch (JavaModelException e) {
                ExceptionHandler.process(e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#setContents(org.eclipse.jface.text.IDocument)
     */
    @Override
    protected void setContents(IDocument document) {
        IPath filePath = new Path("src/internal/" + filename);
        InputStream codeStream = new ByteArrayInputStream(document.get().getBytes());
        try {
            if (file == null) {
                file = CorePlugin.getDefault().getRunProcessService().getProject(LanguageManager.getCurrentLanguage()).getFile(
                        filePath);

                if (!file.exists()) {
                    // file.create(codeStream, true, null);
                    System.out.println("error file should be already created");
                } else {
                    file.setContents(codeStream, true, false, null);
                }
                initializeModel();
                installOccurrencesUpdater();
                reconcile();
            } else {
                file.setContents(codeStream, true, false, null);
            }
        } catch (CoreException e) {
            MessageBoxExceptionHandler.process(e);
        }
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

    /**
     * Getter for compilationUnit.
     * 
     * @return the compilationUnit
     */
    public ICompilationUnit getCompilationUnit() {
        return this.compilationUnit;
    }

    /**
     * Sets the compilationUnit.
     * 
     * @param compilationUnit the compilationUnit to set
     */
    public void setCompilationUnit(ICompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

}
