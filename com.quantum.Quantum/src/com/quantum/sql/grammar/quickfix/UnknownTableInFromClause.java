package com.quantum.sql.grammar.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import com.quantum.QuantumPlugin;
import com.quantum.editors.SQLEditor;

public class UnknownTableInFromClause implements IMarkerResolutionGenerator2 {

	private IDocument document;
	private ITextEditor editor;
	
	public UnknownTableInFromClause() {
		super();
		IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart part = page.getActiveEditor();
		if(part instanceof AbstractTextEditor)
		{
			editor = (ITextEditor) part;
			IDocumentProvider dp = editor.getDocumentProvider();
			document = dp.getDocument(editor.getEditorInput());
		}
	}

	public IMarkerResolution[] getResolutions(IMarker marker) {
		List ars = new ArrayList();
		try
		{
			int k = 1;
			while(true)
			{
				String label = (String)marker.getAttribute("QuickFix " + k);
				if(label == null)break;
				String insert = (String)marker.getAttribute("InsertBeforeWhere " + k);
				ars.add(new UnknownTableFromClause(document, label, insert));
				k++;
			}
		}catch(CoreException e){
		}
		return (IMarkerResolution[])ars.toArray(new UnknownTableFromClause[ars.size()]);
	}
	
	private class UnknownTableFromClause extends QuantumQuickFix
	{
		String insert;
	
		UnknownTableFromClause(IDocument document, String label, String insert)
		{
            super(document, label);
			this.insert = insert;
		}
		
		public void run(IMarker marker)
		{
			String text = getDocument().get().toLowerCase();
			// the "where" should be surrounded by whitespace... or EOF at the right
            // TODO: we should use a regular expression for this...
			int start = text.indexOf("where"); 
			if(start == -1)
			{
				// no where clause yet
				// move to end of document
				start = text.length();
			}
			try {
				document.replace(start, 0, insert);
				StyledText widget = ((SQLEditor) editor).getMeTheSourceViewer().getTextWidget();
				widget.setCaretOffset(start + insert.length() - 2);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
            // do the bookkeeping
            super.setCharStart(start);
            super.setNumberOfCharsAdded(insert.length());
            super.run(marker);
		}
	}

    public boolean hasResolutions(IMarker marker) {
        return true;
    }

}
