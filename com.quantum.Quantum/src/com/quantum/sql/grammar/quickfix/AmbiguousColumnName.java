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

public class AmbiguousColumnName implements IMarkerResolutionGenerator2 {

	private IDocument document;
	
	public AmbiguousColumnName() {
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
				String label = (String)marker.getAttribute("QuickFix " + k);
				if(label == null)break;
				String prepend = (String)marker.getAttribute("Insert " + k);
				ars.add(new AmbiguousQuickFix(document, label, prepend));
				k++;
			}
		}catch(CoreException e){
		}
		return (IMarkerResolution[])ars.toArray(new AmbiguousQuickFix[ars.size()]);
	}
	
	private class AmbiguousQuickFix extends QuantumQuickFix
	{
		String prepend;

        AmbiguousQuickFix(IDocument document, String label, String prepend)
		{
            super(document, label);
			this.prepend = prepend;
		}
		
		public void run(IMarker marker)
		{
			int start=0;
			try {
				start = ((Integer)marker.getAttribute(IMarker.CHAR_START)).intValue();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			
			try {
				document.replace(start, 0, prepend + ".");
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
            // do the bookkeeping
            super.setCharStart(start);
            super.setNumberOfCharsAdded(prepend.length() + 1);
            super.run(marker);
		}

        public Image getImage() {
            return ImageStore.getImage(ImageStore.TABLE);
        }
	}

    public boolean hasResolutions(IMarker marker) {
        return true;
    }
}
