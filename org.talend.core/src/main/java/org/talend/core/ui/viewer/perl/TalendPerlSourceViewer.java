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
package org.talend.core.ui.viewer.perl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.OverviewRuler;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.epic.perleditor.PerlEditorPlugin;
import org.epic.perleditor.editors.PerlPartitioner;
import org.epic.perleditor.editors.PerlSourceAnnotationModel;
import org.epic.perleditor.editors.util.PerlValidator;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.language.LanguageManager;
import org.talend.core.ui.viewer.ReconcilerViewer;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendPerlSourceViewer extends ReconcilerViewer {

    private static int currentId = 0;

    /**
     * DOC nrousseau TalendPerlSourceViewer2 constructor comment.
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
    protected TalendPerlSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles, IAnnotationAccess annotationAccess, ISharedTextColors sharedColors,
            boolean checkCode) {
        super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles, annotationAccess, sharedColors, checkCode,
                new Document());
    }

    public static ReconcilerViewer createViewer(Composite composite, int styles, boolean checkCode) {
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

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#installViewerConfiguration()
     */
    @Override
    protected void installViewerConfiguration() {
        configure(new TalendPerlSourceViewerConfiguration(PerlEditorPlugin.getDefault().getPreferenceStore(), this));
        IDocumentPartitioner partitioner = new PerlPartitioner(PerlEditorPlugin.getDefault().getLog());
        getDocument().setDocumentPartitioner(partitioner);
        partitioner.connect(getDocument());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#setContents(org.eclipse.jface.text.IDocument)
     */
    @Override
    public void updateContents() {
        InputStream codeStream = new ByteArrayInputStream(getDocument().get().getBytes());
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
            } else {
                file.setContents(codeStream, true, false, null);
            }
            try {
                PerlValidator.instance().validate(file, getDocument().get());
            } catch (CoreException e) {
                MessageBoxExceptionHandler.process(e);
            }

        } catch (CoreException e) {
            MessageBoxExceptionHandler.process(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.viewer.ReconcilerViewer#initializeModel()
     */
    @Override
    protected void initializeModel() {
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
}
