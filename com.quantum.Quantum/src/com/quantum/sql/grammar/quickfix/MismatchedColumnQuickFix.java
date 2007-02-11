package com.quantum.sql.grammar.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import com.quantum.ImageStore;

public class MismatchedColumnQuickFix extends QuantumQuickFix {

    String newEntity;
    String oldEntity;
    boolean isTable;
    
    public MismatchedColumnQuickFix(IDocument document, String oldEntity, String newEntity, boolean isTable) {
        super(document, oldEntity + " ---> "+ newEntity);
        this.oldEntity = oldEntity;
        this.newEntity = newEntity;
        this.isTable = isTable;
    }

    public void run(IMarker marker) {
        int start=0;
        try {
            start = ((Integer)marker.getAttribute(IMarker.CHAR_START)).intValue();
        } catch (CoreException e) {
            e.printStackTrace();
        }
        
        try {
            if(isTable){
                // replace the oldTable with the new one.
                // The start will be the length of the old table + .
                start -= (oldEntity.length() + 1);
                getDocument().replace(start, oldEntity.length(), newEntity);
            }else{
                getDocument().replace(start, oldEntity.length(), newEntity);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        // do the bookkeeping
        super.setCharStart(start);
        super.setNumberOfCharsAdded(newEntity.length() - oldEntity.length());
        super.run(marker);

    }

    public Image getImage() {
        if(isTable)
        {
            return(ImageStore.getImage(ImageStore.TABLE));
        }else{
            return(ImageStore.getImage(ImageStore.COLUMN));
        }
    }
}
