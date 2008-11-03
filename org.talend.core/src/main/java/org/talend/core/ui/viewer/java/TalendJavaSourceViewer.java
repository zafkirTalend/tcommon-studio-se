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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.SourceField;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProcessor;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposal;
import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.OverviewRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.DefaultRangeIndicator;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.ui.viewer.ReconcilerViewer;
import org.talend.expressionbuilder.IExpressionDataBean;
import org.talend.expressionbuilder.test.shadow.Variable;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class TalendJavaSourceViewer extends ReconcilerViewer {

    private String filename;

    public static final String VIEWER_CLASS_NAME = "TalendJavaSourceViewer";

    private static int currentId = 0;

    private ICompilationUnit compilationUnit;

    private static final String NL = "\n";

    private static final String TEXT_1 = NL + "    // create and load default properties" + NL
            + "\tprivate static java.util.Properties defaultProps = new java.util.Properties();" + NL
            + "\t// create application properties with default" + NL
            + "\tprivate static class ContextProperties extends java.util.Properties {" + NL + "\t    " + NL
            + "\t    public ContextProperties(java.util.Properties properties){" + NL + "\t        super(properties);" + NL
            + "\t    }" + NL + "\t    public ContextProperties(){" + NL + "\t        super();" + NL + "\t    }" + NL + "\t    "
            + NL + "\t\tpublic void synchronizeContext(){" + NL + "\t\t\t";

    private static final String TEXT_2 = NL + "\t\t\tthis.put(\"";

    private static final String TEXT_3 = "\", ";

    private static final String TEXT_4 = ");" + NL + "\t\t\t";

    private static final String TEXT_5 = NL + "\t\t}" + NL + "\t\t\t    ";

    private static final String TEXT_6 = NL + "     public  static String ";

    private static final String TEXT_7 = ";";

    private static final String TEXT_8 = "      " + NL + " public  static ";

    private static final String TEXT_9 = "  ";

    private static final String TEXT_10 = ";";

    private static final String TEXT_11 = NL + "\t}";

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
                document, null);

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
        updateContents();
    }

    public static ISourceViewer createViewer(Composite composite, int styles, boolean checkCode) {
        StringBuffer buff = new StringBuffer();
        buff.append("package internal;\n\n");
        buff.append(getImports());
        buff.append("public class " + VIEWER_CLASS_NAME + currentId + " {\n");
        buff.append("\tprivate static java.util.Properties context = new java.util.Properties();\n");
        buff
                .append("\tprivate static final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();\n");

        buff.append("\tpublic void myFunction(){\n");
        buff.append("\t  if( \n");

        int documentOffset = buff.toString().length();
        buff.append("){\n\t}");
        buff.append("\n\t\n}\n}");

        IDocument document = new Document();
        document.set(buff.toString());
        return initializeViewer(composite, styles, checkCode, document, documentOffset);
    }

    public static ISourceViewer createViewerForIfConnection(Composite composite) {
        StringBuffer buff = new StringBuffer();
        buff.append("package internal;\n\n");
        buff.append(getImports());
        buff.append("public class " + VIEWER_CLASS_NAME + currentId + " {\n");
        buff.append("\tprivate static java.util.Properties context = new java.util.Properties();\n");
        buff
                .append("\tprivate static final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();\n");

        buff.append("\tpublic void myFunction(){\n");
        buff.append("\t  if( \n");

        int documentOffset = buff.toString().length();
        buff.append("){\n\t}");
        buff.append("\n\t\n}\n}");

        IDocument document = new Document();
        document.set(buff.toString());
        boolean checkCode = true;
        int styles = SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP;
        return initializeViewer(composite, styles, checkCode, document, documentOffset);
    }

    public static ReconcilerViewer createViewerWithVariables(Composite composite, int styles, IExpressionDataBean dataBean) {
        IDocument document = new Document();
        StringBuffer buff = new StringBuffer();
        buff.append("\npackage internal;\n\n");
        buff.append(getImports());
        buff.append("public class " + VIEWER_CLASS_NAME + currentId + " {\n");

        List<IContextParameter> params = CorePlugin.getDefault().getRunProcessService().getSelectedContext()
                .getContextParameterList();
        buff.append(TEXT_1);
        for (IContextParameter ctxParam : params) {
            buff.append(TEXT_2);
            buff.append(ctxParam.getName());
            buff.append(TEXT_3);
            buff.append(ctxParam.getName());
            buff.append(TEXT_4);
        }
        buff.append(TEXT_5);
        for (IContextParameter ctxParam : params) {

            if (ctxParam.getType().equals("id_List Of Value") || ctxParam.getType().equals("id_File")
                    || ctxParam.getType().equals("id_Directory") || ctxParam.getType().equals("id_Character")) {

                buff.append(TEXT_6);
                buff.append(ctxParam.getName());
                buff.append(TEXT_7);

            } else {

                buff.append(TEXT_8);
                buff.append(JavaTypesManager.getTypeToGenerate(ctxParam.getType(), true));
                buff.append(TEXT_9);
                buff.append(ctxParam.getName());
                buff.append(TEXT_10);
            }
        }
        buff.append(TEXT_11);

        buff.append("\tprivate static ContextProperties context = new ContextProperties();\n");
        buff
                .append("\tprivate static final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();\n");

        buff.append(parseVariables(dataBean.getVariables()));
        buff.append("\tpublic " + dataBean.getExpressionType() + " myFunction(){\n");
        buff.append("\t\treturn \n");

        int length = buff.toString().length();
        String defaultValue = "";
        buff.append(defaultValue + "\n;\t\n}\n}");

        document.set(buff.toString());

        return initializeViewer(composite, styles, true, document, length);
    }

    public static ISourceViewer createViewerForComponent(Composite composite, int styles, CompilationUnitEditor editor,
            List<Variable> variableList, String uniqueName, String codePart) {

        String selectedPart = "[" + uniqueName + " " + codePart + " ] start";

        IDocument originalDocument = editor.getDocumentProvider().getDocument(editor.getEditorInput());
        int documentOffset = -1;
        String globalFields = "";
        String localFields = "";
        globalFields = "\tprivate static java.util.Properties context = new java.util.Properties();\n";
        globalFields += "\tprivate static final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();\n";

        IDocument newDoc = new Document();
        boolean checkCode = false;
        FindReplaceDocumentAdapter frda = new FindReplaceDocumentAdapter(originalDocument);
        try {
            Region region = (Region) frda.find(0, selectedPart, true, false, false, false);
            if (region != null) {
                checkCode = true;
                documentOffset = region.getOffset();
                documentOffset = originalDocument.getLineOffset(originalDocument.getLineOfOffset(documentOffset) + 2);
                JavaCompletionProcessor processor = new JavaCompletionProcessor(editor, new ContentAssistant(),
                        IDocument.DEFAULT_CONTENT_TYPE);

                boolean globalFieldsDone = false;
                globalFields = "";
                String className = editor.getPartName().substring(0, editor.getPartName().indexOf('.') + 1);

                ICompletionProposal[] proposals = processor.computeCompletionProposals(editor.getViewer(), documentOffset);
                for (ICompletionProposal curProposal : proposals) {
                    if (curProposal instanceof JavaCompletionProposal) {
                        JavaCompletionProposal javaProposal = ((JavaCompletionProposal) curProposal);

                        if (javaProposal.getJavaElement() instanceof SourceField) {
                            globalFieldsDone = true;
                            SourceField sourceField = (SourceField) javaProposal.getJavaElement();
                            globalFields += "\t" + sourceField.getSource() + "\n";
                            // System.out.println();
                        }
                        if (javaProposal.getJavaElement() == null && !globalFieldsDone) {
                            String[] str = javaProposal.getSortString().split(" ");
                            String variable = "";
                            for (int i = str.length - 1; i >= 0; i--) {
                                variable += str[i].length() == 0 ? " " : str[i];
                            }
                            if (variable.contains(className)) {
                                continue;
                            }
                            variable += ";";
                            localFields += "\t\t" + variable + "\n";
                            // System.out.println(variable);
                        }
                    }
                }
            }
        } catch (BadLocationException e) {
            ExceptionHandler.process(e);
        } catch (JavaModelException e) {
            e.printStackTrace();
        }
        StringBuffer buff = new StringBuffer();
        buff.append("package internal;\n\n");
        buff.append(getImports());
        buff.append("public class " + VIEWER_CLASS_NAME + currentId + " {\n");
        buff.append(globalFields);
        buff.append("\tpublic void myFunction(){\n");
        buff.append(localFields);

        documentOffset = buff.toString().length();
        buff.append("\n\t\n}\n}");

        newDoc.set(buff.toString());

        return initializeViewer(composite, styles, checkCode, newDoc, documentOffset);
    }

    private static ReconcilerViewer initializeViewer(Composite composite, int styles, boolean checkCode, IDocument document,
            int visibleOffset) {
        IAnnotationAccess annotationAccess = new DefaultMarkerAnnotationAccess();
        ISharedTextColors sharedColors = JavaPlugin.getDefault().getJavaTextTools().getColorManager();
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
        }
        verticalRuler = new CompositeRuler(12);
        ReconcilerViewer viewer = new TalendJavaSourceViewer(composite, verticalRuler, overviewRuler, checkCode, styles,
                annotationAccess, sharedColors, checkCode, document);

        if (visibleOffset != -1) {
            viewer.setVisibleRegion(visibleOffset, 0);
        }

        return viewer;
    }

    private static String getImports() {
        String imports = "";
        IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        Project project = ProjectManager.getInstance().getProject(null);
        try {
            List<IRepositoryObject> routines = repositoryFactory.getAll(ERepositoryObjectType.ROUTINES);
            for (IRepositoryObject routine : routines) {
                if (routine.getProperty().getItem() instanceof RoutineItem
                        && ((RoutineItem) routine.getProperty().getItem()).isBuiltIn()) {

                    imports += "import routines." + routine.getLabel() + ";\n";
                } else {
                    imports += "import routines." + project.getTechnicalLabel().toLowerCase() + "." + routine.getLabel() + ";\n";
                }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
            return "";
        }

        imports += "import routines.system.*;\n";
        imports += "import java.text.ParseException;\n";
        imports += "import java.text.SimpleDateFormat;\n";
        imports += "import java.util.Date;\n";
        imports += "import java.util.List;\n";
        imports += "import java.math.BigDecimal;\n";
        return imports;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#initializeModel(IDocument document)
     */
    @Override
    protected void initializeModel() {
        getSourceViewerDecorationSupport().install(JavaPlugin.getDefault().getCombinedPreferenceStore());
        this.setRangeIndicator(new DefaultRangeIndicator());

        IAnnotationModel model;
        IDocument document;
        if (checkCode) {
            IDocumentProvider provider = JavaPlugin.getDefault().getCompilationUnitDocumentProvider();
            IEditorInput ei = new FileEditorInput(file);
            try {
                provider.connect(ei);
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
            document = provider.getDocument(ei);
            model = provider.getAnnotationModel(ei);
        } else {
            model = new AnnotationModel();
            document = getDocument();
            model.connect(document);
        }

        if (document != null) {
            setDocument(document, model);
            showAnnotations(model != null && checkCode);
        }
        super.initializeModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#installViewerConfiguration()
     */
    @Override
    protected void installViewerConfiguration() {
        JavaTextTools tools = JavaPlugin.getDefault().getJavaTextTools();
        tools.setupJavaDocumentPartitioner(getDocument(), IJavaPartitions.JAVA_PARTITIONING);
        IPreferenceStore store = JavaPlugin.getDefault().getCombinedPreferenceStore();
        configure(new TalendJavaViewerConfiguration((IColorManager) sharedColors, store, this));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#setContents(org.eclipse.jface.text.IDocument)
     */
    @Override
    public void updateContents() {
        IPath filePath = new Path("src/internal/" + filename);
        if (getDocument() == null) {
            return;
        }
        InputStream codeStream = new ByteArrayInputStream(getDocument().get().getBytes());
        try {
            if (file == null) {
                file = CorePlugin.getDefault().getRunProcessService().getProject(LanguageManager.getCurrentLanguage()).getFile(
                        filePath);
                file.setContents(codeStream, true, false, null);
                initializeModel();
            } else {
                // only happen sometimes with a "fast click" (file don't exist yet).
                file.setContents(codeStream, true, false, null);
            }
        } catch (CoreException e) {
            // don't display any error.
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
        buff.append("\t" + VIEWER_CLASS_NAME + currentId + "() {\n");
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
