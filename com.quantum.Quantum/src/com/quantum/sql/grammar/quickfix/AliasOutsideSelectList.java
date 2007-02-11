package com.quantum.sql.grammar.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import com.quantum.QuantumPlugin;

public class AliasOutsideSelectList implements IMarkerResolutionGenerator2 {

	private IDocument document;

	public AliasOutsideSelectList() {
		super();
		IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart part = page.getActiveEditor();
		if(part instanceof AbstractTextEditor)
		{
			ITextEditor editor = (ITextEditor) part;
			IDocumentProvider dp = editor.getDocumentProvider();
			document = dp.getDocument(editor.getEditorInput());
		}
	}

	public IMarkerResolution[] getResolutions(final IMarker marker) {
        IMarkerResolution resolution = null;
        try {
            resolution = new AliasOutsideSelectListQuickFix(document, (String) marker.getAttribute("QuickFix"));
        } catch (CoreException e) {
        }
        return new IMarkerResolution[] {resolution};
	}

    public boolean hasResolutions(IMarker marker) {
        return true;
    }
    
    private class AliasOutsideSelectListQuickFix extends QuantumQuickFix{
        
        AliasOutsideSelectListQuickFix(IDocument document, String label){
            super(document, label);
        }
        
        public void run(IMarker marker)
        {
            int start=0;
            int end=0;
            String replacement="";
            try {
                start = ((Integer)marker.getAttribute(IMarker.CHAR_START)).intValue();
                end = ((Integer)marker.getAttribute(IMarker.CHAR_END)).intValue();
                replacement = marker.getAttribute("Insert", "Insert attribute not defined");
            } catch (CoreException e) {
                e.printStackTrace();
            }
            
            try {
                document.replace(start, end - start, replacement);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            // do the bookkeeping
            super.setCharStart(start);
            super.setNumberOfCharsAdded(replacement.length() - end + start);
            super.run(marker);
        }
    };

}
