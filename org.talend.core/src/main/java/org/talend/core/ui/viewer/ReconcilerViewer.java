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

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.epic.core.model.SourceFile;
import org.epic.perleditor.editors.OccurrencesUpdater;
import org.talend.commons.utils.threading.ExecutionLimiter;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public abstract class ReconcilerViewer extends ProjectionViewer {

    protected IFile file = null;

    private SourceFile source;

    private SourceViewerDecorationSupport fSourceViewerDecorationSupport;

    protected IOverviewRuler fOverviewRuler;

    private MarkerAnnotationPreferences fAnnotationPreferences;

    protected IAnnotationAccess annotationAccess;

    protected ISharedTextColors sharedColors;

    private OccurrencesUpdater occ;

    protected boolean checkCode;

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

        IDocumentPartitioner partitioner = getDocumentPartitioner();

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

        if (checkCode) {
            addCodeChecker(document);
        }

        addMenu();
    }

    private void addCodeChecker(IDocument document) {
        final ExecutionLimiter reconcileLimiter = new ExecutionLimiter(20, false) {

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
     * DOC nrousseau Comment method "getDocumentPartitioner".
     * 
     * @return
     */
    protected abstract IDocumentPartitioner getDocumentPartitioner();

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

    public abstract void reconcile();

    protected abstract void setContents(IDocument document);

    protected abstract void initializeModel();

    protected void installOccurrencesUpdater() {
        occ = new OccurrencesUpdater();
        occ.install(this);
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

}
