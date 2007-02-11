/*
 * Created on Jul 14, 2004
 */
package com.quantum.editors.graphical.dnd;

import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import com.quantum.editors.graphical.EntityRelationEditor;

/**
 * Provides a listener for dropping templates onto the editor drawing
 */
public class DataEditDropTargetListener extends TemplateTransferDropTargetListener
{
    private EntityRelationEditor editor;
//    private ColumnFactory factory = new ColumnFactory();
    
	public DataEditDropTargetListener(EntityRelationEditor editor)
	{
        super(editor.getGraphicalViewer());
        this.editor = editor;
	}

//    protected Request createTargetRequest() {
//        CreateRequest request = new CreateRequest();
//        request.setFactory(factory);
//        return request;
//    }
//    
//    protected void updateTargetRequest() {
//        ((CreateRequest)getTargetRequest()).setLocation(getDropLocation());
//     }
//
//    protected void handleDrop() {
//        Object o = getCurrentEvent();
//        String s = "jopie";
//        factory.setType(s);
//        super.handleDrop();
//     }

    protected CreationFactory getFactory(Object template) {
        return new DataElementFactory(editor, template);
    }


}
