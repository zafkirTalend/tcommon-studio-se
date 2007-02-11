package com.quantum.actions;

import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.ISQLSyntaxModel;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPart;

import com.quantum.editors.SQLContentOutlinePage;
import com.quantum.editors.SQLEditor;
import org.eclipse.core.resources.IFile;
/**
 * @author JHvdV
 */
public class CheckSyntaxAction extends Action{
    
	// the document
	IFile f = null;
	
	private final IWorkbenchPart workbenchPart;
	
	public CheckSyntaxAction(IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;
		setText(Messages.getString(CheckSyntaxAction.class, "text"));
	}

	public void dispose() {
	}

	public void run(){
		SQLEditor editor = (SQLEditor)workbenchPart;
		ISQLSyntaxModel model = editor.getConfig().getModel();
		model.checkSyntax();
		editor.getMeTheSourceViewer().invalidateTextPresentation();
		// update the outline page...
		SQLContentOutlinePage page = editor.getOutlinePage();
		if(page != null)page.refresh();
	}

	public boolean isEnabled(Bookmark bookmark) {
		return true;
	}

}
