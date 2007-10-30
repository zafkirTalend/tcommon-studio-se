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
import org.eclipse.jface.text.DefaultAutoIndentStrategy;
import org.eclipse.jface.text.IAutoIndentStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.RGB;
import org.epic.perleditor.PerlEditorPlugin;
import org.epic.perleditor.editors.PartitionTypes;
import org.epic.perleditor.editors.PerlPresentationReconciler;
import org.epic.perleditor.editors.perl.PerlAutoIndentStrategy;
import org.epic.perleditor.editors.perl.PerlDoubleClickSelector;
import org.epic.perleditor.editors.util.PreferenceUtil;
import org.epic.perleditor.preferences.PreferenceConstants;

/**
 * @author luelljoc
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code Generation>Code and
 * Comments
 */
public class TalendPerlSourceViewerConfiguration extends SourceViewerConfiguration {

    private final IPreferenceStore prefs;

    private final TalendPerlSourceViewer viewer;

    public TalendPerlSourceViewerConfiguration(IPreferenceStore store, TalendPerlSourceViewer viewer) {
        assert store != null;

        prefs = store;
        this.viewer = viewer;
    }

    public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
        return new TalendPerlAnnotationHover(viewer);
    }

    public IAutoIndentStrategy getAutoIndentStrategy(ISourceViewer sourceViewer, String contentType) {
        return (IDocument.DEFAULT_CONTENT_TYPE.equals(contentType) ? new PerlAutoIndentStrategy()
                : new DefaultAutoIndentStrategy());
    }

    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return PartitionTypes.getTypes();
    }

    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant assistant = new ContentAssistant();

        // Enable content assist for all content types
        String[] contentTypes = this.getConfiguredContentTypes(sourceViewer);
        for (int i = 0; i < contentTypes.length; i++) {
            assistant.setContentAssistProcessor(new TalendPerlCompletionProcessor(), contentTypes[i]);
        }

        assistant.enableAutoActivation(true);
        assistant.enableAutoInsert(true);
        assistant.setAutoActivationDelay(500);
        assistant.setProposalPopupOrientation(ContentAssistant.PROPOSAL_OVERLAY);
        assistant.setContextInformationPopupOrientation(ContentAssistant.CONTEXT_INFO_ABOVE);
        assistant.setContextInformationPopupBackground(PerlEditorPlugin.getDefault().getColor(new RGB(0, 0, 0)));
        assistant.setProposalSelectorBackground(PerlEditorPlugin.getDefault().getColor(new RGB(255, 255, 255)));
        assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));

        return assistant;
    }

    public String[] getDefaultPrefixes(ISourceViewer sourceViewer, String contentType) {
        return new String[] { "#", "" }; //$NON-NLS-1$

    }

    public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
        return new PerlDoubleClickSelector();
    }

    public String[] getIndentPrefixes(ISourceViewer sourceViewer, String contentType) {
        return new String[] { PreferenceUtil.getTab(0), "\t" };
    }

    public int getTabWidth(ISourceViewer sourceViewer) {
        return prefs.getInt(PreferenceConstants.EDITOR_TAB_WIDTH);
    }

    public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
        return new TalendPerlTextHover(viewer);
    }

    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        return new PerlPresentationReconciler(prefs);
    }

    public IReconciler getReconciler(ISourceViewer sourceViewer) {
        return null;
    }
}