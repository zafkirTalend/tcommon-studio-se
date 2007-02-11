package com.quantum.sql.grammar.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import com.quantum.ImageStore;
import com.quantum.QuantumPlugin;

public class ColumnReferencedByTable implements IMarkerResolutionGenerator2 {

	private IDocument document;
	
	public ColumnReferencedByTable() {
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
		List ars = new ArrayList();
		try
		{
			int k = 0;
			while(true)
			{
				String label = (String)marker.getAttribute("QuickFix" + k);
				if(label == null)break;
				String replacement = (String)marker.getAttribute("Replace" + k);
				ars.add(new ColumnReferencedByTableQuickFix(document, label, replacement));
				k++;
			}
		}catch(CoreException e){
		}
		return (IMarkerResolution[])ars.toArray(new ColumnReferencedByTableQuickFix[ars.size()]);
	}
		
	private class ColumnReferencedByTableQuickFix extends QuantumQuickFix {

        String replacement = null;
		
        ColumnReferencedByTableQuickFix(IDocument document, String label, String replacement)
		{
            super(document, label);
			this.replacement = replacement;
		}
		
		public void run(IMarker marker)
		{
			int start=0;
			int end=0;
			try {
				start = ((Integer)marker.getAttribute(IMarker.CHAR_START)).intValue();
				end = ((Integer)marker.getAttribute(IMarker.CHAR_END)).intValue();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			
			try {
				getDocument().replace(start, end - start, replacement);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
            // do the bookkeeping
            super.setCharStart(start);
            super.setNumberOfCharsAdded(replacement.length() - end + start);
            super.run(marker);
		}

        public Image getImage() {
            // This should work in the next release...
            return ImageStore.getImage(ImageStore.TABLE);
       }
	}

    public boolean hasResolutions(IMarker marker) {
        return true;
    }
}
