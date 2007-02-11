package com.quantum.view.bookmark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.quantum.Messages;
import com.quantum.actions.BaseExecuteAction;
import com.quantum.model.Bookmark;
import com.quantum.model.Entity;
import com.quantum.model.EntityHolder;
import com.quantum.sql.parser.DropEntityStatement;
import com.quantum.wizards.sql.DropEntityWizardPage;
import com.quantum.wizards.sql.SQLStatementWizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;


/**
 * Drop a table.
 * 
 * @author BC Holmes
 */
public class DropEntityAction extends BaseExecuteAction {

	private final IViewPart view;
	private DropEntityStatement statement = new DropEntityStatement();

	public DropEntityAction(IViewPart view) {
		this.view = view;
		setText(Messages.getString(getClass(), "text"));
	}
	
	public void run() {
		Entity entity = getEntity();
		if (entity != null) {
			this.statement.setTableName(entity.getQuotedTableName());
			this.statement.setType(entity.getType());
		}
		
		DropEntityWizardPage page = new DropEntityWizardPage("page1", this.statement);
		SQLStatementWizard wizard = new SQLStatementWizard(new WizardPage[] { page }, this.statement);
		
		WizardDialog dialog = new WizardDialog(this.view.getViewSite().getShell(), wizard);
		if (WizardDialog.OK == dialog.open()) {
			super.run();
		}
	}
	protected Shell getShell() {
		return this.view.getViewSite().getShell();
	}

	protected Bookmark getBookmark() {
		Entity entity = getEntity();
		return entity != null ? entity.getBookmark() : super.getBookmark();
	}
	protected List getQueries(boolean sendQueryAsIs) throws IOException, CoreException {
		List list = new ArrayList();
		//sendQueryAsIs is ignored because in this case, no parsing occurs
		list.add(this.statement.toString());
		return list;
	}
	
	protected Entity getEntity() {
		List list = getSelectedNonResources();
		return list == null || list.isEmpty() ? null : ((EntityHolder) list.get(0)).getEntity();
	}

	protected IStatusLineManager getStatusLineManager() {
		return this.view.getViewSite().getActionBars().getStatusLineManager();
	}

	protected boolean updateSelection(IStructuredSelection selection) {
		return !selection.isEmpty();
	}
}
