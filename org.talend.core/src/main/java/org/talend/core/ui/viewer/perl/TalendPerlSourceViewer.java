// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.core.ui.viewer.perl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITextOperationTarget;
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
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.epic.core.model.SourceFile;
import org.epic.perleditor.PerlEditorPlugin;
import org.epic.perleditor.editors.OccurrencesUpdater;
import org.epic.perleditor.editors.PerlPartitioner;
import org.epic.perleditor.editors.PerlSourceAnnotationModel;
import org.epic.perleditor.editors.util.PerlValidator;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.utils.threading.ExecutionLimiter;
import org.talend.core.CorePlugin;
import org.talend.core.language.LanguageManager;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendPerlSourceViewer extends ProjectionViewer {

    private IFile file = null;

    private SourceFile source;

    private SourceViewerDecorationSupport fSourceViewerDecorationSupport;

    private IOverviewRuler fOverviewRuler;

    private MarkerAnnotationPreferences fAnnotationPreferences;

    private IAnnotationAccess annotationAccess;

    private ISharedTextColors sharedColors;

    private OccurrencesUpdater occ;

    private boolean checkCode;

    private static int currentId = 0;

    /**
     * Preference key for highlighting current line.
     */
    private final static String CURRENT_LINE = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_CURRENT_LINE;

    /**
     * Preference key for highlight color of current line.
     */
    private final static String CURRENT_LINE_COLOR = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_CURRENT_LINE_COLOR;

    /**
     * Preference key for showing print margin ruler.
     */
    private final static String PRINT_MARGIN = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_PRINT_MARGIN;

    /**
     * Preference key for print margin ruler color.
     */
    private final static String PRINT_MARGIN_COLOR = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_PRINT_MARGIN_COLOR;

    /**
     * Preference key for print margin ruler column.
     */
    private final static String PRINT_MARGIN_COLUMN = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_PRINT_MARGIN_COLUMN;

    public static ISourceViewer createViewer(Composite composite, int styles, boolean checkCode) {
        // / see JavaEditor.createSourceViewer
        IAnnotationAccess annotationAccess = new DefaultMarkerAnnotationAccess();
        ISharedTextColors sharedColors = EditorsPlugin.getDefault().getSharedTextColors();
        IOverviewRuler overviewRuler = null;
        IVerticalRuler verticalRuler = null;
        if (checkCode) {
            overviewRuler = new OverviewRuler(annotationAccess, 12, sharedColors);
            Iterator e = EditorsPlugin.getDefault().getMarkerAnnotationPreferences().getAnnotationPreferences().iterator();
            while (e.hasNext()) {
                AnnotationPreference preference = (AnnotationPreference) e.next();
                if (preference.contributesToHeader())
                    overviewRuler.addHeaderAnnotationType(preference.getAnnotationType());
            }
            verticalRuler = new VerticalRuler(12);
        }

        return new TalendPerlSourceViewer(composite, verticalRuler, overviewRuler, checkCode, styles, annotationAccess,
                sharedColors, checkCode);
    }

    public TalendPerlSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles, IAnnotationAccess annotationAccess, ISharedTextColors sharedColors,
            boolean checkCode) {
        super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);
        fOverviewRuler = overviewRuler;
        this.annotationAccess = annotationAccess;
        this.sharedColors = sharedColors;
        this.checkCode = checkCode;
        initializeViewer();
    }

    // private IContentAssistProcessor fProcessor;

    // private TemplateTranslator fTranslator = new TemplateTranslator();

    private void initializeViewer() {
        fAnnotationPreferences = EditorsPlugin.getDefault().getMarkerAnnotationPreferences();
        IDocument document = new Document();

        // fProcessor = new TalendPerlCompletionProcessor();

        setDocument(document);
        configure(new TalendPerlSourceViewerConfiguration(PerlEditorPlugin.getDefault().getPreferenceStore(), this));
        setEditable(true);

        IDocumentPartitioner partitioner = new PerlPartitioner(PerlEditorPlugin.getDefault().getLog());

        document.setDocumentPartitioner(partitioner);
        partitioner.connect(document);

        Font font = JFaceResources.getFontRegistry().get(JFaceResources.TEXT_FONT);
        getTextWidget().setFont(font);

        Control control = getControl();
        GridData data = new GridData(GridData.FILL_BOTH);
        control.setLayoutData(data);

        prependVerifyKeyListener(new VerifyKeyListener() {

            public void verifyKey(VerifyEvent event) {
                handleVerifyKeyPressed(event);
            }
        });

        final ExecutionLimiter reconcileLimiter = new ExecutionLimiter(10, false) {

            @Override
            public void execute(final boolean isFinalExecution) {
                if (!getControl().isDisposed()) {
                    getControl().getDisplay().asyncExec(new Runnable() {

                        public void run() {
                            reconcile();
                        }
                    });
                }
            }
        };

        if (checkCode) {
            document.addDocumentListener(new IDocumentListener() {

                public void documentAboutToBeChanged(DocumentEvent event) {
                    if (occ != null) {
                        occ.selectionChanged(new SelectionChangedEvent(getSelectionProvider(), getSelection()));
                    }
                }

                public void documentChanged(DocumentEvent event) {
                    setContents(event.getDocument());
                    reconcileLimiter.resetTimer();
                    reconcileLimiter.startIfExecutable(true);
                }
            });

            this.getTextWidget().addDisposeListener(new DisposeListener() {

                public void widgetDisposed(DisposeEvent e) {
                    if (file != null && file.exists()) {
                        try {
                            file.delete(false, new NullProgressMonitor());
                        } catch (CoreException e1) {
                            // do nothing as the delete is not important.
                        }
                    }
                }
            });
        }
    }

    protected SourceViewerDecorationSupport getSourceViewerDecorationSupport() {
        if (fSourceViewerDecorationSupport == null) {
            fSourceViewerDecorationSupport = new SourceViewerDecorationSupport(this, fOverviewRuler, annotationAccess,
                    sharedColors);
            configureSourceViewerDecorationSupport(fSourceViewerDecorationSupport);
        }
        return fSourceViewerDecorationSupport;
    }

    protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {

        Iterator e = fAnnotationPreferences.getAnnotationPreferences().iterator();
        while (e.hasNext()) {
            support.setAnnotationPreference((AnnotationPreference) e.next());
        }

        support.setCursorLinePainterPreferenceKeys(CURRENT_LINE, CURRENT_LINE_COLOR);
        support.setMarginPainterPreferenceKeys(PRINT_MARGIN, PRINT_MARGIN_COLOR, PRINT_MARGIN_COLUMN);
        support.setSymbolicFontName(JFaceResources.TEXT_FONT);
    }

    public void reconcile() {
        if (file != null) {
            try {
                PerlValidator.instance().validate(file, getDocument().get());
            } catch (CoreException e) {
                MessageBoxExceptionHandler.process(e);
            }
        }
    }

    private void setContents(IDocument document) {
        InputStream codeStream = new ByteArrayInputStream(document.get().getBytes());
        try {
            if (file == null) {
                IProject perlProject = CorePlugin.getDefault().getRunProcessService().getProject(
                        LanguageManager.getCurrentLanguage());
                if (!perlProject.getFolder("internal").exists()) {
                    perlProject.getFolder("internal").create(true, false, new NullProgressMonitor());
                }

                IPath path = new Path("internal/codeChecker" + currentId++ + ".pl");
                file = perlProject.getFile(path);
                if (!file.exists()) {
                    file.create(codeStream, true, null);
                } else {
                    file.setContents(codeStream, true, false, null);
                }
                initializeModel();
                occ = new OccurrencesUpdater();
                occ.install(this);
            } else {
                file.setContents(codeStream, true, false, null);
            }
        } catch (CoreException e) {
            MessageBoxExceptionHandler.process(e);
        }

    }

    private void initializeModel() {
        IAnnotationModel model = new PerlSourceAnnotationModel(file);
        IDocument document = this.getDocument();
        model.connect(document);

        if (document != null) {
            setDocument(document, model);
            showAnnotations(model != null);
        }

        ProjectionSupport projectionSupport = new ProjectionSupport(this, annotationAccess, sharedColors);
        projectionSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.error");
        projectionSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.warning");
        projectionSupport.install();

        getSourceViewerDecorationSupport().install(PerlEditorPlugin.getDefault().getPreferenceStore());

        doOperation(ProjectionViewer.TOGGLE);
    }

    private void handleVerifyKeyPressed(VerifyEvent event) {
        if (!event.doit) {
            return;
        }

        if (event.stateMask != SWT.CTRL) {
            return;
        }

        switch (event.character) {
        case ' ':
            doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
            event.doit = false;
            break;

        // CTRL-Z
        case (int) 'z' - (int) 'a' + 1:
            doOperation(ITextOperationTarget.UNDO);
            event.doit = false;
            break;
        default:
        }
    }

    /**
     * Getter for source.
     * 
     * @return the source
     */
    public SourceFile getSource() {
        return this.source;
    }

    /**
     * Getter for file.
     * 
     * @return the file
     */
    public IFile getFile() {
        return this.file;
    }
}
