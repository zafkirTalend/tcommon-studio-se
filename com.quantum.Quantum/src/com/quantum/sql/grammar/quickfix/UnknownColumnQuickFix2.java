package com.quantum.sql.grammar.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class UnknownColumnQuickFix2 extends QuantumQuickFix {
	String replace;
	
	UnknownColumnQuickFix2(IDocument document, String label, String replace)
	{
        super(document, label);
		this.replace = replace;
	}
	
	public void run(IMarker marker)
	{
		int start=0;
		int end = 0;
		try {
			start = ((Integer)marker.getAttribute(IMarker.CHAR_START)).intValue();
			end = ((Integer)marker.getAttribute(IMarker.CHAR_END)).intValue();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		try {
			getDocument().replace(start, end - start, replace);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        // do the bookkeeping
        super.setCharStart(start);
        super.setNumberOfCharsAdded(replace.length() - end + start);
        super.run(marker);
	}
}
