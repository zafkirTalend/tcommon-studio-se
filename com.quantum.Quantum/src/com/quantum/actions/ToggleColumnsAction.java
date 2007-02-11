/*
 * Created on Jul 23, 2004
 */
package com.quantum.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;

import com.quantum.editors.graphical.EntityRelationEditor;
import com.quantum.editors.graphical.model.EntityRelationDiagram;


/**
 * Action to toggle the showing of columns
 * 
 * @author jhvdv
 */
public class ToggleColumnsAction extends Action
{

	IEditorPart editor;
	boolean checked;

	public ToggleColumnsAction(IEditorPart editor)
	{
		super("Toggle columns", Action.AS_CHECK_BOX);
		this.editor = editor;
	}

	public void run()
	{
		if (editor instanceof EntityRelationEditor)
		{
            EntityRelationEditor schemaEditor = (EntityRelationEditor) editor;
            EntityRelationDiagram diagram = schemaEditor.getModel();
			boolean isManual = diagram.areColumnsShown();
			diagram.setShowColumns(!isManual);
			checked = !isManual;
			setChecked(checked);
		}
	}

	public boolean isChecked()
	{
		if (editor != null)
			return isChecked(editor);
		else
			return super.isChecked();
	}

	/**
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked(IEditorPart editor)
	{

		if (editor instanceof EntityRelationEditor)
		{
            EntityRelationEditor schemaEditor = (EntityRelationEditor) editor;
            EntityRelationDiagram diagram = schemaEditor.getModel();
			boolean checkTrue = diagram.areColumnsShown();
			return (!checkTrue);
		}
		else
		{
			return false;
		}

	}

	public void setActiveEditor(IEditorPart editor)
	{
		this.editor = editor;
		boolean localChecked = isChecked(editor);
		
//		//there appears to be a bug in the framework which necessitates this
		if (localChecked)
			firePropertyChange(CHECKED, Boolean.FALSE, Boolean.TRUE);
		else
			firePropertyChange(CHECKED, Boolean.TRUE, Boolean.FALSE);
	}

}