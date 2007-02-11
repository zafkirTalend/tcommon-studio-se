package com.quantum.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;

import bsh.EvalError;
import bsh.Interpreter;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.actions.ExecuteAction;
import com.quantum.actions.ExplainAction;
import com.quantum.actions.ExportQueryAction;
import com.quantum.actions.ImportQueryAction;
import com.quantum.log.QuantumLog;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.parser.SQLProvider;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.ui.dialog.SQLExceptionDialog;
import com.quantum.util.QuantumUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.view.tableview.TableView;
/**
 * @author BC Holmes
 */
class SQLQueryActionGroup extends ActionGroup {
	private class ClearAction extends Action {
		
		private final SQLProvider sqlProvider;

		public ClearAction(SQLProvider sqlProvider) {
			this.sqlProvider = sqlProvider;
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLEAR));
			setToolTipText(Messages.getString("sqlqueryview.clear"));
		}
		public void run() {
			this.sqlProvider.clear();
		}
	}

	private class AutoCommitPreferenceAction extends Action {
		
		public AutoCommitPreferenceAction() {
			super(Messages.getString("SQLQueryView.AutoCommit"));
			setToolTipText(Messages.getString("SQLQueryView.AutoCommit"));
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.AUTOCOMMIT));
		}
		
		public void run() {
			setAutoCommitPreference(isChecked());
		}
	}
	
	private class RollbackAction extends Action {
		public RollbackAction() {
			setText(Messages.getString("SQLQueryView.RollBack"));
			setToolTipText(Messages.getString("SQLQueryView.RollBack"));
		}
		
		public void run() {
        	Bookmark[] bookmarks = BookmarkCollection.getInstance().getBookmarks();
        	for (int i = 0, length = bookmarks == null ? 0 : bookmarks.length; i < length; i++) {
        		try {
					if (bookmarks[i].isConnected() && !bookmarks[i].getConnection().getAutoCommit()) {
						MultiSQLServer.getInstance().rollback(bookmarks[i].getConnection());
					}
	            } catch (SQLException e) {
	            	SQLExceptionDialog.openException(getShell(), bookmarks[i], e);
	            } catch (NotConnectedException e) {
	            	ExceptionDisplayDialog.openError(getShell(), null, null, e);
	            }
			}
		}
	}

	private class CutAction extends Action {
		private final StyledText widget;
		public CutAction(StyledText widget) {
			this.widget = widget;
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CUT));
			setText(Messages.getString(SQLQueryActionGroup.class, "cut"));
		}
		public void run() {
			this.widget.cut();
		}
	}
	private class CopyAction extends Action {
		private final StyledText widget;
		public CopyAction(StyledText widget) {
			this.widget = widget;
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.COPY));
			setText(Messages.getString(SQLQueryActionGroup.class, "copy"));
		}
		public void run() {
			this.widget.copy();
		}
	}
	private class CopyAsJavaAction extends Action {
		private final StyledText widget;
		public CopyAsJavaAction(StyledText widget) {
			this.widget = widget;
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.JAVA_CUP));
			setText(Messages.getString(SQLQueryActionGroup.class, "copyAsJava"));
		}
		public void run() {
			if (this.widget.getText().length() > 0) {
			QuantumPlugin.getDefault().getSysClip().setContents(
					new Object[] { QuantumUtil.convertSqlToJava(this.widget.getText())},
					new Transfer[] { TextTransfer.getInstance()});
			}
		}
	}

	private class PasteFromJavaAction extends Action {
		private final StyledText widget;
		public PasteFromJavaAction(StyledText widget) {
			this.widget = widget;
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.EXPORT));
			setText(Messages.getString(SQLQueryActionGroup.class, "pasteFromJava"));
		}
		public void run() {
			/* This is from 15-OCT-2005
			 * The feature works by taking the data from the clipboard and dumping 
			 	it on the query view, probably added to whatever is there. I suppose the 
			 	usual procedure for a user would be to copy the data from some java 
			 	program, and then want to generate a query or something, perhaps adding 
			 	it to some SQL procedure. 
			 */
			String toChange="";
//			try{
				toChange = (String)QuantumPlugin.getDefault().getSysClip().getContents(TextTransfer.getInstance());
//			}catch(CoreException e){
//				QuantumLog.getInstance().error("Error reading clipboard: " + toChange, e);
//				ErrorDialog.openError(getShell(),"Error when reading clipboard. Please check the log",null, e.getStatus());
//				return;
//			}
			if (toChange.length() > 0) {
				while(toChange.endsWith(" ")||toChange.endsWith(";")||toChange.endsWith("\r")||toChange.endsWith("\n")){
					toChange = toChange.substring(0, toChange.length()-1);
				}
				// the line feeds and so on are removed when
				// copying back. But tabs are kept...
				toChange = toChange.replaceAll("\n","\"\\\\n\"+");
				toChange = toChange.replaceAll("\r","\"\\\\r\"+");
					
				toChange = "print (" + toChange + ");";
				// text is correct, now activate the beanshell

				// the problem is that you cannot assign the outcome 
				// of the print statement to a variable.
				ByteArrayOutputStream outStr = new ByteArrayOutputStream();
				PrintStream printOut = new PrintStream(outStr);
				ByteArrayOutputStream errStr = new ByteArrayOutputStream();
				PrintStream printErr = new PrintStream(errStr);
		
				Interpreter i = new Interpreter( null, printOut, printErr, false );

				try{
					i.eval(toChange);
					String textOut = outStr.toString();
					//dumping it on the query view, probably added to whatever is there
					textOut = textOut.replaceAll("\\\\n","\n");//"\\n","\n");
					textOut = textOut.replaceAll("\\\\r","\r");//"\\r","\r");
					this.widget.insert(textOut);
				}catch ( EvalError e ) {
					// The clipboard code wasn't proper java or did not evaluate to a string,
					// we notify the user of the error.
				    QuantumLog.getInstance().error("Problem evaluating statement: " + toChange, e);
					IStatus error = new Status(IStatus.ERROR, QuantumPlugin.getDefault().toString(), 1, e.getMessage(), null);
					ErrorDialog.openError(getShell(), 
							"Paste operation failed", 
							"Probably the clipboard contents are not valid java code. " +
							"See the Quantum log for more info.", error);
				}
			}
		}
	}

	private class PasteAction extends Action {
		private final StyledText widget;
		public PasteAction(StyledText widget) {
			this.widget = widget;
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.PASTE));
			setText(Messages.getString(SQLQueryActionGroup.class, "paste"));
		}
		public void run() {
			this.widget.paste();
		}
	}
	private class SelectAllAction extends Action {
		private final StyledText widget;
		public SelectAllAction(StyledText widget) {
			this.widget = widget;
			setText(Messages.getString(SQLQueryActionGroup.class, "selectAll"));
		}
		public void run() {
			this.widget.selectAll();
		}
	}
	
	private class CommitAction extends Action {
		public CommitAction() {
			setText(Messages.getString("SQLQueryView.Commit"));
			setToolTipText(Messages.getString("SQLQueryView.Commit"));
		}
		
		public void run() {
        	Bookmark[] bookmarks = BookmarkCollection.getInstance().getBookmarks();
        	for (int i = 0, length = bookmarks == null ? 0 : bookmarks.length; i < length; i++) {
        		try {
					if (bookmarks[i].isConnected() && !bookmarks[i].getConnection().getAutoCommit()) {
						MultiSQLServer.getInstance().commit(bookmarks[i].getConnection());
					}
	            } catch (SQLException e) {
	            	SQLExceptionDialog.openException(getShell(), bookmarks[i], e);
	            } catch (NotConnectedException e) {
	            	ExceptionDisplayDialog.openError(getShell(), null, null, e);
	            }
			}
		}
	}
	
	private class PinTabAction extends Action {
		
		public PinTabAction() {
			super(Messages.getString("SQLQueryView.PinTab"));
			setToolTipText(Messages.getString("SQLQueryView.PinTab"));
		}
		
		public void run() {
			setPinTabPreference(isChecked());
		}
	}
	
	/**
	 *
	 * Action that dds into the Query view 
	 * the SQL SELECT command that was used to create the displayed table view
	 */
	private class PasteTableQueryAction extends Action {
		StyledText widget; 
		
		public PasteTableQueryAction(StyledText widget) {			
			super(Messages.getString("SQLQueryView.PasteTableQuery"));
			this.widget = widget;
			setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.PASTE));
			setToolTipText(Messages.getString("SQLQueryView.PasteTableQuery"));			
		}
				
		public void run() {
			SQLResultSetResults displayedResultSet = TableView.getInstance().getSelectedResultSet();
			if (displayedResultSet != null) {
				this.widget.insert(displayedResultSet.getFilteredQuery());
			} else {
				MessageDialog.openInformation(
						getShell(), 
						"Paste operation failed", 
						"Probably there is no active Result Set in the table view.");
			}
		}
	}
	
	private ExecuteAction executeAction;
	private ImportQueryAction importQueryAction;
	private ExportQueryAction exportQueryAction;
	private AutoCommitPreferenceAction autoCommitPreferenceAction;
	private RollbackAction rollbackAction;
	private CommitAction commitAction;
	private PinTabAction pintabAction;
	private PasteTableQueryAction pasteTableQueryAction;

	private Action explainAction;
	private Action clearAction;
	private Action cutAction;
	private Action copyAction;
	private Action copyAsJavaAction;
	private Action pasteFromJavaAction;
	private Action pasteAction;
	private Action selectAllAction;
	private final IViewPart view;
	
	private boolean autoCommitPreference = true;
	private boolean pintabPreference = false;

	public SQLQueryActionGroup(SQLQueryView view) {
		this.view = view;
		this.executeAction = new ExecuteAction(view, view);
		this.explainAction = new ExplainAction(view, view);
		this.importQueryAction = new ImportQueryAction();
		this.importQueryAction.init(this.view);
        this.exportQueryAction = new ExportQueryAction();
        this.exportQueryAction.init(this.view);
        this.autoCommitPreferenceAction = new AutoCommitPreferenceAction();
        this.autoCommitPreferenceAction.setChecked(this.autoCommitPreference);
        this.rollbackAction = new RollbackAction();
        this.commitAction = new CommitAction();
        this.clearAction = new ClearAction(view);
        this.cutAction = new CutAction(view.getControl());
		this.copyAction = new CopyAction(view.getControl());
		this.copyAsJavaAction = new CopyAsJavaAction(view.getControl());
		this.pasteFromJavaAction = new PasteFromJavaAction(view.getControl());
        this.pasteAction = new PasteAction(view.getControl());
        this.selectAllAction = new SelectAllAction(view.getControl());
        this.pintabAction = new PinTabAction();
        this.pintabAction.setChecked(this.pintabPreference);
        this.pasteTableQueryAction = new PasteTableQueryAction(view.getControl());
	}
	
    /**
     * Add all the appropriate actions to the popup menu. This method is 
     * called whenever someone right-clicks on the query view.
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void fillContextMenu(IMenuManager menu) {
        menu.add(this.executeAction);
		menu.add(this.explainAction);
        menu.add(new Separator());
        menu.add(this.cutAction);
        menu.add(this.copyAction);
        menu.add(this.pasteAction);
        menu.add(this.selectAllAction);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
     */
    public void fillActionBars(IActionBars actionBars) {
        IToolBarManager toolBar = actionBars.getToolBarManager();

		toolBar.add(this.executeAction);
		toolBar.add(new Separator());
		toolBar.add(this.clearAction);
		
        actionBars.getMenuManager().add(this.importQueryAction);
        actionBars.getMenuManager().add(this.exportQueryAction);
        
        actionBars.getMenuManager().add(new Separator());        
        actionBars.getMenuManager().add(this.autoCommitPreferenceAction);		
        actionBars.getMenuManager().add(this.rollbackAction);
		actionBars.getMenuManager().add(this.commitAction);
		
		actionBars.getMenuManager().add(new Separator());
		actionBars.getMenuManager().add(this.copyAsJavaAction);
		actionBars.getMenuManager().add(this.pasteFromJavaAction);
		actionBars.getMenuManager().add(this.pasteTableQueryAction);
		
		actionBars.getMenuManager().add(new Separator());
		actionBars.getMenuManager().add(this.pintabAction);
		

		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), this.cutAction);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), this.copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), this.pasteAction);
		actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), this.selectAllAction);
    }

    protected Shell getShell() {
    	return this.view.getSite().getShell();
    }
    
	public ExecuteAction getExecuteAction() {
		return this.executeAction;
	}

	public boolean isAutoCommitPreference() {
		return this.autoCommitPreference;
	}
	
	public void setAutoCommitPreference(boolean autoCommitPreference) {
		this.autoCommitPreference = autoCommitPreference;
	}
	
	public boolean isPinTabPreference() {
		return this.pintabPreference;
	}
	
	public void setPinTabPreference(boolean pintabPreference) {
		this.pintabPreference = pintabPreference;
	}
	
    public void dispose() {
        this.executeAction.dispose();
        
        super.dispose();
    }
}
