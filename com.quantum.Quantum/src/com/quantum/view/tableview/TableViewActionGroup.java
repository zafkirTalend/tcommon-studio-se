package com.quantum.view.tableview;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Vector;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.extensions.ExtensionAction;
import com.quantum.extensions.ProcessServiceMembers;
import com.quantum.model.Bookmark;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.TableRow;
import com.quantum.util.StringMatrix;
import com.quantum.util.versioning.VersioningHelper;
import com.quantum.wizards.DeleteRowPage;
import com.quantum.wizards.InsertRowPage;
import com.quantum.wizards.SQLPage;
import com.quantum.wizards.SQLRowWizard;
import com.quantum.wizards.SortFilterPage;
import com.quantum.wizards.UpdateRowPage;
import com.quantum.wizards.ViewRowPage;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.SelectionListenerAction;


/**
 * @author Julen Parra
 * @author BC Holmes
 */
public class TableViewActionGroup extends ActionGroup {

	/*
	 * Don't change this to OtherEncodingsAction.class.getName()!!!
	 * We want to avoid loading the class until we're sure we're 
	 * under JDK 1.4 or better.
	 */ 
	private static final String OTHER_ENCODINGS_ACTION_CLASS_NAME = 
		"com.quantum.view.tableview.OtherEncodingsAction";
	
	abstract class SQLWizardAction extends SelectionListenerAction {
		
		/**
		 * @param text
		 */
		protected SQLWizardAction(String text, ISelectionProvider selectionProvider) {
			super(text);
			selectionProvider.addSelectionChangedListener(this);
			setEnabled(!selectionProvider.getSelection().isEmpty());
		}
		
		protected abstract SQLPage createSQLPage();
		
		protected abstract String getTitle();
		
		public void run() {
			SQLPage page = createSQLPage();
			SQLRowWizard wizard = new SQLRowWizard();
			wizard.init(getTitle(), page, getSelectedSQLResults(), getSelectedRow());
			WizardDialog dialog = new WizardDialog(
					tableView.getSite().getShell(), wizard);
			dialog.open();
		}
		
		protected boolean updateSelection(IStructuredSelection selection) {
			return selection != null && !selection.isEmpty();
		}
	};
	
	class SortFilterAction extends SQLWizardAction {

		public SortFilterAction(ISelectionProvider selectionProvider) {
			super(Messages.getString(TableViewActionGroup.class, "filterSort"), 
					selectionProvider);
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.FILTER));
			setToolTipText(Messages.getString(TableViewActionGroup.class, "filterSort"));
			setEnabled(sortFilterApplies());
		}
		
		private boolean sortFilterApplies() {
			SQLResultSetResults results = getSelectedSQLResults();
			return results != null && !results.isMetaData() && results.getEntity() != null;
		}
		
		protected SQLPage createSQLPage() {
			return new SortFilterPage("page1");
		}

		protected String getTitle() {
			return Messages.getString(TableViewActionGroup.class, "filterSort");
		}
		
		protected boolean updateSelection(IStructuredSelection selection) {
			return sortFilterApplies();
		}
	}
	
	class InsertAction extends Action {
		public InsertAction() {
			setText(Messages.getString("tableview.insert"));
		}
		
		public void run() {
			SQLResultSetResults resultSet = getSelectedSQLResults();
			if (resultSet == null || resultSet.isMetaData() || resultSet.getEntity() == null) {
				MessageDialog.openInformation(
						tableView.getSite().getShell(),"Operation not allowed","You cannot insert rows in this recordset");
				return;
			}
			InsertRowPage page = new InsertRowPage(""); //$NON-NLS-1$
			SQLRowWizard wizard = new SQLRowWizard();
			wizard.init(Messages.getString("TableView.InsertRow"), 
					page, getSelectedSQLResults(), null); //$NON-NLS-1$
			WizardDialog dialog =
				new WizardDialog(
					tableView.getSite().getShell(),
					wizard);
			dialog.open();	
			
			// trigger refresh action if user had pressed finish button 
			if (dialog.getReturnCode() == 0) {
				refreshAction.run();
			}
		}
	};
	
	class DeleteAction extends Action {
		public DeleteAction() {
			setText(Messages.getString("tableview.delete"));
		}
		
		public void run() {
			SQLResultSetResults resultSet = getSelectedSQLResults();
			if (resultSet == null || resultSet.isMetaData() || resultSet.getEntity() == null) {
				MessageDialog.openInformation(
						tableView.getSite().getShell(),"Operation not allowed","You cannot delete rows in this recordset");
				return;
			}
			DeleteRowPage page = new DeleteRowPage(""); //$NON-NLS-1$
			SQLRowWizard wizard = new SQLRowWizard();
			wizard.init(Messages.getString("TableView.DeleteRow"), 
					page, getSelectedSQLResults(), getSelectedRow()); //$NON-NLS-1$
			WizardDialog dialog =
				new WizardDialog(
					tableView.getSite().getShell(),
					wizard);
			dialog.open();
			
			// trigger refresh action if user had pressed finish button 
			if (dialog.getReturnCode() == 0) {
				refreshAction.run();
			}
		}
	};
	
	class UpdateAction extends Action {
		public UpdateAction() {
			setText(Messages.getString("tableview.update"));
		}
		
		public void run() {
			SQLResultSetResults resultSet = getSelectedSQLResults();
			if (resultSet == null || resultSet.isMetaData() || resultSet.getEntity() == null) {
				MessageDialog.openInformation(
						tableView.getSite().getShell(),"Operation not allowed","You cannot update rows in this recordset");
				return;
			}
			UpdateRowPage page = new UpdateRowPage(""); //$NON-NLS-1$
			SQLRowWizard wizard = new SQLRowWizard();
			wizard.init(Messages.getString("TableView.UpdateRow"), 
					page, getSelectedSQLResults(), getSelectedRow()); //$NON-NLS-1$
			WizardDialog dialog =
				new WizardDialog(
					tableView.getSite().getShell(),
					wizard);
			dialog.open();
			
			// trigger refresh action if user had pressed finish button 
			if (dialog.getReturnCode() == 0) {
				refreshAction.run();
			}
		}
	};
	
	
	class ViewAction extends Action {
		public ViewAction() {
			setText(Messages.getString("tableview.view"));
		}
		
		public void run() {
			ViewRowPage page = new ViewRowPage(""); //$NON-NLS-1$
			SQLRowWizard wizard = new SQLRowWizard();
			wizard.init(Messages.getString("TableView.ViewRow"), 
					page, getSelectedSQLResults(), getSelectedRow()); //$NON-NLS-1$
			WizardDialog dialog =
				new WizardDialog(
					tableView.getSite().getShell(),
					wizard);
			dialog.open();
		}
	};

	class MouseListenerImpl implements MouseListener {		
		public void mouseDown(MouseEvent event) {}
		public void mouseUp(MouseEvent event) {}
		public void mouseDoubleClick(MouseEvent event) {
			updateRowAction.run();
		}
	}
	
	private final TableView tableView;
	
    private Action otherEncodingsAction;
    
    private SelectionListenerAction closeAction;
    private SelectionListenerAction closeAllAction;
    private SelectionListenerAction nextAction;
    private SelectionListenerAction previousAction;
    private SelectionListenerAction refreshAction;
    private SelectionListenerAction fullModeAction;
    private SelectionListenerAction defaultEncodingAction;
    private SelectionListenerAction utf8EncodingAction;
    private SelectionListenerAction utf16EncodingAction;
    
    private CopyAction copyAction;
    private SelectAllAction selectAllAction;
    private InsertAction insertRowAction;
    private DeleteAction deleteRowAction;
    private UpdateAction updateRowAction;
    private SortFilterAction sortFilterAction;
    private ViewAction viewAction;
    protected MouseListenerImpl mouseDblClk;
    
    private Vector extensionActions = new Vector();

	public TableViewActionGroup(TableView tableView) {
		this.tableView = tableView;
		
		this.closeAction = new CloseResultSetAction(this.tableView, this.tableView);
		this.closeAllAction = new CloseAllResultSetsAction(this.tableView, this.tableView);
		this.defaultEncodingAction = new ChangeEncodingAction(this.tableView, this.tableView, "", "default");
		this.utf8EncodingAction = new ChangeEncodingAction(this.tableView, this.tableView, "UTF-8", "utf8");
		this.utf16EncodingAction = new ChangeEncodingAction(this.tableView, this.tableView, "UTF-16", "utf16");
		
		if (VersioningHelper.getJDKVersion() >= VersioningHelper.JDK_1_4) {
			createOtherEncodingsAction();
		}
		this.nextAction = new NextPageAction(this.tableView, this.tableView);
		this.previousAction = new PreviousPageAction(this.tableView, this.tableView);
		this.refreshAction = new RefreshTableAction(this.tableView, this.tableView);
		this.fullModeAction = new FullModeAction(this.tableView, this.tableView);
		
		this.copyAction = new CopyAction(this.tableView);
		this.selectAllAction = new SelectAllAction(this.tableView);
		
		this.insertRowAction = new InsertAction();
		this.deleteRowAction = new DeleteAction();
		this.updateRowAction = new UpdateAction();
		this.sortFilterAction = new SortFilterAction(this.tableView);
		this.viewAction = new ViewAction();
		
		this.mouseDblClk = new MouseListenerImpl();
		
		try {
			ProcessServiceMembers.process(tableView, this.extensionActions);
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
    }
	
    /**
	 * @return
	 */
	private void createOtherEncodingsAction() {
		try {
			Class action = Class.forName(OTHER_ENCODINGS_ACTION_CLASS_NAME);
			Constructor constructor = action.getConstructor(new Class[] { TableView.class });
			this.otherEncodingsAction = (Action) constructor.newInstance(new Object[] { this.tableView } );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// can't get it for some reason
		} catch (NoClassDefFoundError e) {
			e.printStackTrace();
			// can't get it for some reason
		} catch (SecurityException e) {
			e.printStackTrace();
			// can't get it for some reason
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			// can't get it for some reason
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			// can't get it for some reason
		} catch (InstantiationException e) {
			e.printStackTrace();
			// can't get it for some reason
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			// can't get it for some reason
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			// can't get it for some reason
		}
	}

	public void fillActionBars(IActionBars actionBars) {
        IToolBarManager toolBar = actionBars.getToolBarManager();
        toolBar.add(this.previousAction);
        toolBar.add(this.nextAction);
        toolBar.add(this.fullModeAction);
        toolBar.add(this.closeAction);
        toolBar.add(this.closeAllAction);
        toolBar.add(this.refreshAction);
        toolBar.add(this.sortFilterAction);
        
        actionBars.setGlobalActionHandler(
        		ActionFactory.COPY.getId(), this.copyAction);
		actionBars.setGlobalActionHandler(
				ActionFactory.SELECT_ALL.getId(), this.selectAllAction);
    }
	
    
	public void fillContextMenu(IMenuManager menuManager) {
        menuManager.add(this.defaultEncodingAction);
        menuManager.add(this.utf8EncodingAction);
        menuManager.add(this.utf16EncodingAction);
        if (this.otherEncodingsAction != null) {
        	menuManager.add(this.otherEncodingsAction);
        }
        menuManager.add(new Separator());
        menuManager.add(this.copyAction);
        menuManager.add(this.selectAllAction);
        menuManager.add(new Separator());

        SQLResultSetResults resultSet = getSelectedSQLResults();
        
        menuManager.add(this.insertRowAction);
        menuManager.add(this.updateRowAction);
        menuManager.add(this.deleteRowAction);
        menuManager.add(this.viewAction);
        menuManager.add(new Separator());
        
		createExtensionMenu(menuManager);
        
        createMarkerForActionsProvidedByOtherPlugins(menuManager);
	}

    /**
	 * @return
	 */
	private SQLResultSetResults getSelectedSQLResults() {
		return this.tableView.getSelectedResultSet();
	}

	protected SQLResultSetResults.Row getSelectedRow() {
		IStructuredSelection selection = getTableRowSelection();
		
		return selection == null || selection.isEmpty() 
				? null 
				: (SQLResultSetResults.Row) selection.getFirstElement();
	}
	/**
	 * @return
	 */
	private IStructuredSelection getTableRowSelection() {
		ResultSetViewer viewer = this.tableView.getSelectedResultSetViewer();
		IStructuredSelection selection = viewer == null ? null : (IStructuredSelection) viewer.getSelection();
		return selection;
	}

	/**
	 * @param menuManager
	 */
	private void createExtensionMenu(IMenuManager menuManager) {
		MenuManager subMenuExtension = new MenuManager("Extensions"); 
		for (int i = 0; i < this.extensionActions.size(); i++) {
			ExtensionAction extensionAction = (ExtensionAction) this.extensionActions.get(i);
			extensionAction.addRowData(createTableRow());
			subMenuExtension.add(extensionAction);
		}
		if (this.extensionActions.size() > 0) {
			menuManager.add(subMenuExtension);
		}
	}
	
	/**
	 * This method supports an earlier API for other plug-ins to add functionality to
	 * QuantumDB.
	 * 
	 * @return
	 */
	private TableRow createTableRow() {
		
		SQLResultSetResults results = this.tableView.getSelectedResultSet();
		IStructuredSelection selection = getTableRowSelection();
		
		if (results != null) {
			StringMatrix data = new StringMatrix();
			data.addMatrixHeader(results.getColumnNames());
			if (selection != null && !selection.isEmpty()) {
				int rowNumber = 0;
				for (Iterator i = selection.iterator(); i.hasNext(); ) {
					SQLResultSetResults.Row row = (SQLResultSetResults.Row) i.next();
					for (int j = 1, length = results.getColumnCount(); j <= length; j++) {
						Object object = row.get(j);
						data.addAt(results.getColumnName(j), 
								object == null ? null : object.toString(), 
										rowNumber);
					}
					rowNumber++;
				}
			} else {
				// Create dummy values in case nothing selected
				for (int i = 1, length = results.getColumnCount(); i <= length; i++) {
					data.addAt(results.getColumnName(i+1), "", 0); //$NON-NLS-1$
				}
			}
			
			return new TableRow(results.getEntity(), (Bookmark) results.getConnectable(), 
					results.getEntity() == null 
							? null 
							: results.getEntity().getQualifiedName(), 
					data);
		} else {
			return null;
		}
	}

	private void createMarkerForActionsProvidedByOtherPlugins(IMenuManager menuManager) {
        menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end")); //$NON-NLS-1$
        menuManager.add(new Separator());
    }
}
