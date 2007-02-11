package com.quantum.view.tableview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.Scrollable;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;


/**
 * @author BC
 */
public class NextPageAction extends ResultSetAction implements PropertyChangeListener {
	
	private SQLResultSetResults resultSet;

	public NextPageAction(IViewPart view, ISelectionProvider selectionProvider) {
		super(view, selectionProvider);
		setText(Messages.getString(getClass(), "text"));
		setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.NEXT));
		setToolTipText(Messages.getString(getClass(), "text"));
		setEnabled(hasNextPage((IStructuredSelection) selectionProvider.getSelection()));
	}

	protected void executeResultSetAction(SQLResultSetResults results) throws SQLException {
		if (results instanceof Scrollable) {
			((Scrollable) results).nextPage(getConnection(results));
		}
	}
	protected boolean updateSelection(IStructuredSelection selection) {
		setResultSet(getResultSet(selection));
		return hasNextPage(selection);
	}

	/**
	 * @param srollable
	 */
	private void setResultSet(SQLResultSetResults results) {
		if (this.resultSet != null) {
			this.resultSet.removePropertyChangeListener(this);
		}
		
		this.resultSet = results;
		if (results != null) {
			this.resultSet.addPropertyChangeListener(this);
		}
	}

	/**
	 * @param selection
	 */
	private SQLResultSetResults getResultSet(IStructuredSelection selection) {
		return (selection != null && !selection.isEmpty() && 
				(selection.getFirstElement() instanceof SQLResultSetResults)) 
				? (SQLResultSetResults) selection.getFirstElement()
				: null;
	}

	/**
	 * @param selection
	 * @return
	 */
	private boolean hasNextPage(IStructuredSelection selection) {
		return !selection.isEmpty() && 
			(selection.getFirstElement() instanceof Scrollable) &&
			((Scrollable) selection.getFirstElement()).hasNextPage();
	}

	public void propertyChange(PropertyChangeEvent event) {
		if ("rows".equals(event.getPropertyName())) {
			setEnabled(hasNextPage(getStructuredSelection()));
		}
	}
}
