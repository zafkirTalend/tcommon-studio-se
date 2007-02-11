package com.quantum.view.driver;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.SelectionListenerAction;


/**
 * @author BC Holmes
 */
class JDBCDriverViewActionGroup extends ActionGroup  {
	
	private SelectionListenerAction deleteAction; 

	public JDBCDriverViewActionGroup(JDBCDriverView view, ISelectionProvider selectionProvider) {
		this.deleteAction = new DeleteDriverAction(view, selectionProvider);
	}
	
    public void fillActionBars(IActionBars actionBars) {
        actionBars.setGlobalActionHandler(
        		ActionFactory.DELETE.getId(), this.deleteAction);
    }
}
