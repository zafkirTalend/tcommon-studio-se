package com.quantum.sql.grammar.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;

public class QuantumQuickFix implements IMarkerResolution2 {

    private String label;
    private IDocument document;
    int numberOfCharsAdded;
    int charStart;
    
    public QuantumQuickFix()
    {
        super();
    }
    
    public QuantumQuickFix(IDocument document, String label) {
        super();
        this.label = label;
        this.document = document;
    }

    public String getDescription() {
        return null;
    }

    public Image getImage() {
        return null;
    }

    public String getLabel() {
        return label;
    }

    public void setNumberOfCharsAdded(int n)
    {
        numberOfCharsAdded = n;
    }
    
    public void setCharStart(int s)
    {
        charStart = s;
    }
    
    public void run(IMarker marker) {
        IResource res = marker.getResource();
        try {
            marker.delete();
            // here we need to update the start and end of any marker
            // after the one we have just deleted.
            IMarker[] markers = res.findMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE);
            for(int i=0; i<markers.length; i++){
                int oldStart = ((Integer) markers[i].getAttribute(IMarker.CHAR_START)).intValue(); 
                if(oldStart>charStart)
                {
                    int oldEnd = ((Integer) markers[i].getAttribute(IMarker.CHAR_END)).intValue();
                    markers[i].setAttribute(IMarker.CHAR_START, oldStart + numberOfCharsAdded );
                    markers[i].setAttribute(IMarker.CHAR_END, oldEnd + numberOfCharsAdded);
                }
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }
    
    public IDocument getDocument(){
        return document;
    }
}
