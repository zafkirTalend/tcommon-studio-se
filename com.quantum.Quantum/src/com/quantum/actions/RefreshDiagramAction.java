package com.quantum.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;

import com.quantum.editors.graphical.EntityRelationEditor;
import com.quantum.editors.graphical.model.EntityRelationDiagram;

public class RefreshDiagramAction extends Action {
    IEditorPart editor;

    public RefreshDiagramAction(IEditorPart editor)
    {
        super("Refresh diagram", Action.AS_PUSH_BUTTON);
        this.editor = editor;
    }
    
    public void run()
    {
        if (editor instanceof EntityRelationEditor)
        {
            EntityRelationEditor schemaEditor = (EntityRelationEditor) editor;
            EntityRelationDiagram diagram = schemaEditor.getModel();
            diagram.refresh(schemaEditor); // this is where the hack starts to get Undo/Redo functionality
        }
    }

    public void setActiveEditor(IEditorPart editor)
    {
        this.editor = editor;
    }
}
