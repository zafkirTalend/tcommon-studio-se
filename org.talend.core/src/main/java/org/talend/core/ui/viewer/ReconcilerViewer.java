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
package org.talend.core.ui.viewer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.internal.ui.text.java.hover.SourceViewerInformationControl;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.epic.core.model.SourceFile;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.threading.ExecutionLimiter;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
/**
 * DOC nrousseau class global comment. Detailled comment
 */
public abstract class ReconcilerViewer extends ProjectionViewer {

    protected IFile file = null;

    private SourceFile source;

    private SourceViewerDecorationSupport fSourceViewerDecorationSupport;

    protected IOverviewRuler fOverviewRuler;

    private MarkerAnnotationPreferences fAnnotationPreferences;

    protected IAnnotationAccess annotationAccess;

    protected ISharedTextColors sharedColors;

    protected boolean checkCode;

    // used to calculate the full document displayed (in the visible part, no matter if there is annotations or not)
    private IRegion viewerStartRegion, viewerEndRegion;

    /**
     * Preference key for highlighting current line.
     */
    private static final String CURRENT_LINE = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_CURRENT_LINE;

    /**
     * Preference key for highlight color of current line.
     */
    private static final String CURRENT_LINE_COLOR = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_CURRENT_LINE_COLOR;

    /**
     * Preference key for showing print margin ruler.
     */
    private static final String PRINT_MARGIN = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_PRINT_MARGIN;

    /**
     * Preference key for print margin ruler color.
     */
    private static final String PRINT_MARGIN_COLOR = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_PRINT_MARGIN_COLOR;

    /**
     * Preference key for print margin ruler column.
     */
    private static final String PRINT_MARGIN_COLUMN = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_PRINT_MARGIN_COLUMN;

    public ReconcilerViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles, IAnnotationAccess annotationAccess, ISharedTextColors sharedColors,
            boolean checkCode, IDocument document) {
        super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);
        fOverviewRuler = overviewRuler;
        this.annotationAccess = annotationAccess;
        this.sharedColors = sharedColors;
        this.checkCode = checkCode;

        initializeViewer(document);
    }

    protected void initializeViewer(IDocument document) {
        fAnnotationPreferences = EditorsPlugin.getDefault().getMarkerAnnotationPreferences();

        setDocument(document);

        installViewerConfiguration();
        setEditable(true);

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
        addDocumentListener(document);

        addMenu();
    }

    private void addDocumentListener(final IDocument document) {
        final ExecutionLimiter documentReconcilerLimiter = new ExecutionLimiter(50, true) {

            @Override
            protected void execute(boolean isFinalExecution) {
                if (isFinalExecution) {
                    if (getControl() != null && !getControl().isDisposed()) {
                        getControl().getDisplay().asyncExec(new Runnable() {

                            public void run() {
                                updateContents();
                                if (document.get().length() != 0) {
                                    calculatePositions();
                                }
                            }
                        });
                    }
                }
            }
        };

        document.addDocumentListener(new IDocumentListener() {

            public void documentAboutToBeChanged(DocumentEvent event) {
                // nothing
            }

            public void documentChanged(DocumentEvent event) {
                documentReconcilerLimiter.resetTimer();
                documentReconcilerLimiter.startIfExecutable(true);
            }
        });
    }

    protected Map<ProjectionAnnotation, Position> oldAnnotations = new HashMap<ProjectionAnnotation, Position>();

    protected void calculatePositions() {
        if (hasSnippetsModifications()) {
            final Map<ProjectionAnnotation, Position> annotations = getAllSnippetsAnnotations();

            Display.getDefault().asyncExec(new Runnable() {

                public void run() {
                    if (!annotations.isEmpty() && getProjectionAnnotationModel() == null) {
                        enableProjection();
                    }
                    if (getProjectionAnnotationModel() != null) {
                        Annotation[] oldAnno = oldAnnotations.keySet().toArray(new Annotation[0]);
                        getProjectionAnnotationModel().modifyAnnotations(oldAnno, annotations, null);
                        oldAnnotations.clear();
                        oldAnnotations.putAll(annotations);
                        if (annotations.isEmpty()) {
                            disableProjection();
                        }
                    }
                }

            });
        }
    }

    /**
     * Check if there is any new snippet or if some has been deleted.
     * 
     * @return
     */
    private boolean hasSnippetsModifications() {
        IDocument document = getDocument();
        if (document == null) {
            return false;
        }
        int curNbAnnotations = oldAnnotations.size();
        int actualNbAnnotations = 0;
        int curOffset = 0;
        FindReplaceDocumentAdapter frda = new FindReplaceDocumentAdapter(document);
        try {
            IRegion startRegion = frda.find(curOffset, "SNIPPET_START", true, false, false, false);
            while (startRegion != null && startRegion.getOffset() >= curOffset) {
                int startLine = document.getLineOfOffset(startRegion.getOffset());
                int startOffset = document.getLineOffset(startLine);
                curOffset = startOffset + document.getLineLength(startLine);
                IRegion endRegion = frda.find(startRegion.getOffset(), "SNIPPET_END", true, false, false, false);
                if (endRegion != null) {
                    actualNbAnnotations++;
                    int endLine = document.getLineOfOffset(endRegion.getOffset());
                    int endOffset = document.getLineOffset(endLine);
                    endOffset += document.getLineLength(endLine);
                    curOffset = endOffset;
                    boolean contains = false;
                    String text = document.get(startOffset, endOffset - startOffset);
                    for (ProjectionAnnotation annotation : oldAnnotations.keySet()) {
                        Position pos = oldAnnotations.get(annotation);
                        if (annotation.getText().equals(text) && (startOffset == pos.getOffset())) {
                            contains = true;
                        }
                    }
                    if (!contains) {
                        return true;
                    }
                }
                if (curOffset < document.getLength()) {
                    startRegion = frda.find(curOffset, "SNIPPET_START", true, false, false, false);
                }
            }

        } catch (BadLocationException e) {
            ExceptionHandler.process(e);
        }
        if (curNbAnnotations != actualNbAnnotations) {
            return true;
        }
        return false;
    }

    private Map<ProjectionAnnotation, Position> getAllSnippetsAnnotations() {
        Map<ProjectionAnnotation, Position> annotations = new HashMap<ProjectionAnnotation, Position>();
        IDocument document = getDocument();
        int curOffset = 0;
        FindReplaceDocumentAdapter frda = new FindReplaceDocumentAdapter(document);
        try {
            IRegion startRegion = frda.find(curOffset, "SNIPPET_START", true, false, false, false);
            while (startRegion != null && startRegion.getOffset() >= curOffset) {
                int startLine = document.getLineOfOffset(startRegion.getOffset());
                int startOffset = document.getLineOffset(startLine);
                curOffset = startOffset + document.getLineLength(startLine);
                IRegion endRegion = frda.find(startRegion.getOffset(), "SNIPPET_END", true, false, false, false);
                if (endRegion != null) {
                    int endLine = document.getLineOfOffset(endRegion.getOffset());
                    int endOffset = document.getLineOffset(endLine);
                    endOffset += document.getLineLength(endLine);
                    curOffset = endOffset;
                    String text = document.get(startOffset, endOffset - startOffset);
                    ProjectionAnnotation annotation = new ProjectionAnnotation(true);
                    annotation.setText(text);
                    annotation.setRangeIndication(true);
                    annotations.put(annotation, new Position(startOffset, endOffset - startOffset));
                }
                if (curOffset < document.getLength()) {
                    startRegion = frda.find(curOffset, "SNIPPET_START", true, false, false, false);
                }
            }

        } catch (BadLocationException e) {
            ExceptionHandler.process(e);
        }
        return annotations;
    }

    /**
     * DOC nrousseau Comment method "addMenu".
     */
    private void addMenu() {
        ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
        Image image = sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY).createImage();
        Menu popupMenu = new Menu(this.getTextWidget());
        MenuItem copyItem = new MenuItem(popupMenu, SWT.PUSH);
        copyItem.setText("Copy");
        copyItem.setImage(image);
        copyItem.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                getTextWidget().copy();
            }
        });

        image = sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE).createImage();
        MenuItem pasteItem = new MenuItem(popupMenu, SWT.PUSH);
        pasteItem.setText("Paste");
        pasteItem.setData(this);
        pasteItem.setImage(image);
        pasteItem.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                getTextWidget().paste();
            }
        });

        MenuItem selectAllItem = new MenuItem(popupMenu, SWT.PUSH);
        selectAllItem.setText("Select All");
        selectAllItem.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                getTextWidget().selectAll();
            }
        });
        getTextWidget().setMenu(popupMenu);

        Listener selectAllListener = new Listener() {

            public void handleEvent(Event event) {
                if (event.character == '\u0001') { // CTRL + A
                    getTextWidget().selectAll();
                }
            }
        };

        getTextWidget().addListener(SWT.KeyDown, selectAllListener);

    }

    /**
     * Example: configure(new TalendPerlSourceViewerConfiguration(PerlEditorPlugin.getDefault().getPreferenceStore(),
     * this));.
     */
    protected abstract void installViewerConfiguration();

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

    public abstract void updateContents();

    protected void initializeModel() {
        ProjectionSupport projectionSupport = new ProjectionSupport(this, annotationAccess, sharedColors);
        projectionSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.error"); //$NON-NLS-1$
        projectionSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.warning"); //$NON-NLS-1$
        projectionSupport.setHoverControlCreator(new IInformationControlCreator() {

            public IInformationControl createInformationControl(Shell shell) {
                return new SourceViewerInformationControl(shell, SWT.TOOL | SWT.NO_TRIM | SWT.LEFT_TO_RIGHT, SWT.NONE, EditorsUI
                        .getTooltipAffordanceString());
            }
        });
        projectionSupport.setInformationPresenterControlCreator(new IInformationControlCreator() {

            public IInformationControl createInformationControl(Shell shell) {
                int shellStyle = SWT.RESIZE | SWT.TOOL | SWT.LEFT_TO_RIGHT;
                int style = SWT.V_SCROLL | SWT.H_SCROLL;
                return new SourceViewerInformationControl(shell, shellStyle, style);
            }
        });
        projectionSupport.install();
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

        case '.':
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

    /**
     * Adds support for dropping items into this viewer via a user drag-and-drop operation.
     * 
     * @param operations a bitwise OR of the supported drag and drop operation types ( <code>DROP_COPY</code>,<code>DROP_LINK</code>,
     * and <code>DROP_MOVE</code>)
     * @param transferTypes the transfer types that are supported by the drop operation
     * @param listener the callback that will be invoked after the drag and drop operation finishes
     * @see org.eclipse.swt.dnd.DND
     */
    public void addDropSupport(int operations, Transfer[] transferTypes, final DropTargetListener listener) {
        Control control = getTextWidget();
        DropTarget dropTarget = new DropTarget(control, operations);
        dropTarget.setTransfer(transferTypes);
        dropTarget.addDropListener(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.source.projection.ProjectionViewer#handleDispose()
     */
    @Override
    protected void handleDispose() {
        if (file != null && file.exists()) {
            try {
                file.delete(false, new NullProgressMonitor());
            } catch (CoreException e1) {
                // do nothing as the delete is not important.
            }
        }
        super.handleDispose();
    }

    public IRegion getViewerRegion() {
        if (viewerStartRegion == null) {
            return new Region(0, getDocument().getLength());
        }
        return new Region(viewerStartRegion.getLength(), getDocument().getLength() - viewerStartRegion.getLength()
                - viewerEndRegion.getLength());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.source.projection.ProjectionViewer#setVisibleRegion(int, int)
     */
    @Override
    public void setVisibleRegion(int start, int length) {
        viewerStartRegion = new Region(0, start);
        if (getDocument().getLength() > start) {
            viewerEndRegion = new Region(start + 1, getDocument().getLength() - start);
        } else {
            viewerEndRegion = new Region(start, 0);
        }
        super.setVisibleRegion(start, length);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.TextViewer#createTextWidget(org.eclipse.swt.widgets.Composite, int)
     */
    @Override
    protected StyledText createTextWidget(Composite parent, int styles) {
        return new ReconcilerStyledText(parent, styles, this);
    }

    /**
     * DOC nrousseau ReconcilerViewer class global comment. Detailled comment
     */
    private class ReconcilerStyledText extends StyledText {

        ReconcilerViewer viewer;

        /**
         * DOC nrousseau ReconcilerStyledText constructor comment.
         * 
         * @param parent
         * @param style
         */
        public ReconcilerStyledText(Composite parent, int style, ReconcilerViewer viewer) {
            super(parent, style);
            this.viewer = viewer;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.swt.custom.StyledText#getText()
         */
        @Override
        public String getText() {
            IRegion region = viewer.getViewerRegion();
            try {
                return viewer.getDocument().get(region.getOffset(), region.getLength());
            } catch (BadLocationException e) {
                return super.getText();
            }
        }
    }
}
