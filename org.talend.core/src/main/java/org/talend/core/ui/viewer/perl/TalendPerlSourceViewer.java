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
package org.talend.core.ui.viewer.perl;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.epic.perleditor.PerlEditorPlugin;
import org.epic.perleditor.editors.PerlPartitioner;
import org.epic.perleditor.editors.PerlSourceViewerConfiguration;
import org.epic.perleditor.preferences.PreferenceConstants;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendPerlSourceViewer extends SourceViewer {

    public static ISourceViewer createViewer(Composite composite, String text, int styles) {
        return new TalendPerlSourceViewer(composite, null, styles, new Document(text));
    }

    public TalendPerlSourceViewer(Composite parent, IVerticalRuler ruler, int styles, IDocument document) {
        super(parent, ruler, styles);
        initializeViewer(document);
    }

    private IContentAssistProcessor fProcessor;

    // private TemplateTranslator fTranslator = new TemplateTranslator();

    private void initializeViewer(IDocument document) {
        fProcessor = new TalendPerlCompletionProcessor();
        IDocumentPartitioner partitioner = new PerlPartitioner(PerlEditorPlugin.getDefault().getLog());

        document.setDocumentPartitioner(partitioner);
        partitioner.connect(document);
        configure(new SimpleJavaSourceViewerConfiguration(fProcessor));
        setEditable(true);
        setDocument(document);

        Font font = JFaceResources.getFontRegistry().get(JFaceResources.TEXT_FONT);
        getTextWidget().setFont(font);

        Control control = getControl();
        GridData data = new GridData(GridData.FILL_BOTH);
        control.setLayoutData(data);

        addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                // updateSelectionDependentActions();
            }
        });

        prependVerifyKeyListener(new VerifyKeyListener() {

            public void verifyKey(VerifyEvent event) {
                handleVerifyKeyPressed(event);
            }
        });
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
     * DOC nrousseau TalendPerlSourceViewer class global comment. Detailled comment <br/>
     * 
     */
    private static class SimpleJavaSourceViewerConfiguration extends PerlSourceViewerConfiguration {

        private final IContentAssistProcessor fProcessor;

        SimpleJavaSourceViewerConfiguration(IContentAssistProcessor processor) {
            super(PerlEditorPlugin.getDefault().getPreferenceStore(), null);
            fProcessor = processor;
        }

        /*
         * @see SourceViewerConfiguration#getContentAssistant(ISourceViewer)
         */
        public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {

            IPreferenceStore store = PerlEditorPlugin.getDefault().getPreferenceStore();

            ContentAssistant assistant = new ContentAssistant();
            assistant.setContentAssistProcessor(fProcessor, IDocument.DEFAULT_CONTENT_TYPE);
            assistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
            assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
            // assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));

            Display display = sourceViewer.getTextWidget().getDisplay();
            Color background = PerlEditorPlugin.getDefault().getColor(new RGB(254, 241, 233));
            assistant.setContextInformationPopupBackground(background);
            assistant.setContextSelectorBackground(background);
            assistant.setProposalSelectorBackground(background);

            Color foreground = createColor(store, PreferenceConstants.EDITOR_FOREGROUND_COLOR, display);
            assistant.setContextInformationPopupForeground(foreground);
            assistant.setContextSelectorForeground(foreground);
            assistant.setProposalSelectorForeground(foreground);

            return assistant;
        }

        /**
         * Creates a color from the information stored in the given preference store. Returns <code>null</code> if
         * there is no such information available.
         */
        private Color createColor(IPreferenceStore store, String key, Display display) {

            RGB rgb = null;

            if (store.contains(key)) {
                if (store.isDefault(key)) {
                    rgb = PreferenceConverter.getDefaultColor(store, key);
                } else {
                    rgb = PreferenceConverter.getColor(store, key);
                }

                if (rgb != null) {
                    return new Color(display, rgb);
                }
            }

            return null;
        }
    }
}
