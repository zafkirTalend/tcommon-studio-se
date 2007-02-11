package com.quantum.view.driver;

import java.util.List;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.model.JDBCDriver;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.SelectionListenerAction;

/**
 * This action deletes JDBC Drivers from the JDBC Driver collection.
 * 
 * @author BC Holmes
 */
public class DeleteDriverAction extends SelectionListenerAction {

	private final IViewPart viewPart;

	/**
	 * @param text
	 */
	protected DeleteDriverAction(IViewPart viewPart, ISelectionProvider selectionProvider) {
		super(Messages.getString(DeleteDriverAction.class, "text"));
		this.viewPart = viewPart;
		selectionProvider.addSelectionChangedListener(this);
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.DELETE));
	}
	
	public void run() {
		JDBCDriver driver = getJDBCDriver();

		if (driver != null) {
			
			Bookmark[] bookmarks = BookmarkCollection.getInstance().getBookmarksUsingDriver(driver);
			if (bookmarks.length > 0) {
				MessageDialog.openWarning(getShell(), 
						Messages.getString(DeleteDriverAction.class, "failed"),
						Messages.getString(DeleteDriverAction.class, "inUse"));
			} else {
				if (MessageDialog.openConfirm(getShell(), 
					Messages.getString(DeleteDriverAction.class, "confirm"),
					Messages.getString(DeleteDriverAction.class, "confirmText", 
							new Object[] { driver.getName() }))) {
		
					if (!BookmarkCollection.getInstance().removeDriver(driver)) {
						MessageDialog.openWarning(getShell(), 
								Messages.getString(DeleteDriverAction.class, "failed"),
								Messages.getString(DeleteDriverAction.class, "cant"));
					} 
				}
			}
		}
	}
	
	/**
	 * @return
	 */
	private Shell getShell() {
		return this.viewPart.getViewSite().getShell();
	}

	/**
	 * @return
	 */
	private JDBCDriver getJDBCDriver() {
		List list = getSelectedNonResources();
		return list == null || list.size() == 0 ? null : (JDBCDriver) list.get(0);
	}

	protected boolean updateSelection(IStructuredSelection selection) {
		return selection != null && !selection.isEmpty();
	}
}
