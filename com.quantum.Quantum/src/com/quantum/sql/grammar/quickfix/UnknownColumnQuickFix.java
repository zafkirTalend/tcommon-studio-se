package com.quantum.sql.grammar.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class UnknownColumnQuickFix extends QuantumQuickFix{

	String prepend;
	
	UnknownColumnQuickFix(IDocument document, String label, String prepend)
	{
        super(document, label);
		this.prepend = prepend;
	}
	
	public void run(IMarker marker)
	{
		int start=0;
        int length = 0;
		try {
			start = ((Integer)marker.getAttribute(IMarker.CHAR_START)).intValue();
            String doc = getDocument().get();
            if(doc.charAt(start-1)=='.'){
                // this columnn is preceded by a table
                // we will replace the table
                int idx = start-2;
                while(!(Character.isWhitespace(doc.charAt(idx))) && idx>0){
                    idx--;
                }
                length = (start - idx);
                start = idx;
            }
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		try {
			getDocument().replace(start, length, prepend + ".");
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        // do the bookkeeping
        super.setCharStart(start);
        super.setNumberOfCharsAdded(prepend.length() + 1);
        super.run(marker);
	}
}
