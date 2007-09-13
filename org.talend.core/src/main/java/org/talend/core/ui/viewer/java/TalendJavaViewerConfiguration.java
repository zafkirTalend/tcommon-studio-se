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

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.text.ContentAssistPreference;
import org.eclipse.jdt.internal.ui.text.java.ContentAssistProcessor;
import org.eclipse.jdt.internal.ui.text.javadoc.JavadocCompletionProcessor;
import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendJavaViewerConfiguration extends JavaSourceViewerConfiguration {

    public TalendJavaViewerConfiguration(IColorManager colorManager, IPreferenceStore preferenceStore) {
        super(colorManager, preferenceStore, null, IJavaPartitions.JAVA_PARTITIONING);
    }

    HiddenJavaEditor editor;

    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        if (editor == null) {
            editor = new HiddenJavaEditor(sourceViewer.getDocument());
        }
        ContentAssistant assistant = new ContentAssistant();
        assistant.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

        assistant.setRestoreCompletionProposalSize(getSettings("completion_proposal_size")); //$NON-NLS-1$

        IContentAssistProcessor javaProcessor = new TalendJavaCompletionProcessor(editor, assistant,
                IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(javaProcessor, IDocument.DEFAULT_CONTENT_TYPE);

        ContentAssistProcessor singleLineProcessor = new TalendJavaCompletionProcessor(editor, assistant,
                IJavaPartitions.JAVA_SINGLE_LINE_COMMENT);
        assistant.setContentAssistProcessor(singleLineProcessor, IJavaPartitions.JAVA_SINGLE_LINE_COMMENT);

        ContentAssistProcessor stringProcessor = new TalendJavaCompletionProcessor(editor, assistant, IJavaPartitions.JAVA_STRING);
        assistant.setContentAssistProcessor(stringProcessor, IJavaPartitions.JAVA_STRING);

        ContentAssistProcessor multiLineProcessor = new TalendJavaCompletionProcessor(editor, assistant,
                IJavaPartitions.JAVA_MULTI_LINE_COMMENT);
        assistant.setContentAssistProcessor(multiLineProcessor, IJavaPartitions.JAVA_MULTI_LINE_COMMENT);

        ContentAssistProcessor javadocProcessor = new JavadocCompletionProcessor(editor, assistant);
        assistant.setContentAssistProcessor(javadocProcessor, IJavaPartitions.JAVA_DOC);

        ContentAssistPreference.configure(assistant, fPreferenceStore);

        assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
        assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));

        return assistant;

    }

    /**
     * yzhang Comment method "getSettings".
     * 
     * @param sectionName
     * @return
     */
    private IDialogSettings getSettings(String sectionName) {
        IDialogSettings settings = JavaPlugin.getDefault().getDialogSettings().getSection(sectionName);
        if (settings == null)
            settings = JavaPlugin.getDefault().getDialogSettings().addNewSection(sectionName);

        return settings;
    }
}
