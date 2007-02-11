package com.quantum.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.MarkerUtilities;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.adapters.DatabaseAdapter;
import com.quantum.model.Bookmark;
import com.quantum.model.DatabaseObject;
import com.quantum.model.Entity;
import com.quantum.model.Procedure;
import com.quantum.search.SearchEntry;
import com.quantum.search.SearchResults;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetCollection;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.sql.SQLResults;
import com.quantum.ui.dialog.ConnectionUtil;
import com.quantum.ui.dialog.SQLExceptionDialog;

public class SQLSearchResultsTree extends ViewPart implements PropertyChangeListener {
    
	private Tree widget;
	private TreeItem searchItem = null; // this is our current search definition 
    private ConnectionUtil connectionUtil = new ConnectionUtil();
	public void createPartControl(Composite parent) {
		this.widget =  new Tree(parent, SWT.SINGLE);
        
		IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();
		clearAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.CLEAR));
		clearAction.setToolTipText(Messages.getString("SQLSearchResults.ClearSearch")); //$NON-NLS-1$
		toolBar.add(clearAction);
		
		refresh(SearchResults.getInstance().getEntries());
 		SearchResults.getInstance().addPropertyChangeListener(this);
 		widget.addListener (SWT.MouseDoubleClick, new Listener()
 		{
 			public void handleEvent(Event event) {
                Point point = new Point(event.x, event.y);
                TreeItem item = widget.getItem(point);
                if (item != null) {
                    DatabaseObject assist = null;
                    try {
                        assist = ((SearchEntry) item.getData())
                                .getDatabaseObject();
                    } catch (Exception e) {
                    }
                    if (assist != null) {
                        if (assist instanceof Entity) {
                            Image image = item.getImage();
                            if (image.equals(ImageStore
                                    .getImage(ImageStore.TABLE))) {
                                tableClicked((Entity) assist, event, item);
                            } else if (image.equals(ImageStore
                                    .getImage(ImageStore.COLUMN))) {
                                columnClicked((Entity) assist, event, item);
                            } else if (image.equals(ImageStore
                                    .getImage(ImageStore.KEYCOLUMN))) {
                                keyColumnClicked((Entity) assist, event, item);
                            }
                        } else if (assist instanceof Procedure) {
                            procedureClicked((Procedure)assist, event, item);
                        }
                    }
                }
            }

 		});

	}

    private void keyColumnClicked(Entity entity, Event event, TreeItem item) {
        Bookmark bookmark = entity.getBookmark();
        DatabaseAdapter adapter = bookmark.getAdapter();
        String query = "SELECT * FROM " + entity.getQuotedTableName() + " WHERE " + item.getText();
        showTableView(bookmark, entity, query);
    }
   
    private Shell getShell() {
        return getViewSite().getShell();
    }

    private void procedureClicked(Procedure p, Event event, TreeItem item) {
        // show the procedure body
        Bookmark bookmark = p.getBookmark();
        BookmarkSelectionUtil.getInstance().setLastUsedBookmark(bookmark);
        IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject project = root.getProject(bookmark.getName());
        IFolder folder = project.getFolder("P");
        IFile file = folder.getFile(p.getName() + ".sql");
        try {
            if (!project.exists())project.create(null);
            if (!project.isOpen())project.open(null);
            if (!folder.exists())folder.create(IResource.NONE, false, null);
            if(file.exists())file.delete(true, null);
            if (!file.exists()) {
                String s = p.getBody() + "\r\n";
                FileOutputStream source=null;
                try {
                    source = new FileOutputStream(file.getName());
                } catch (FileNotFoundException e) {
                }
                try {
                    source.write(s.getBytes());
                    source.flush();
                    source.close();
                } catch (IOException e) {
                }
                FileInputStream dest = null;
                try {
                    dest = new FileInputStream(file.getName());
                } catch (FileNotFoundException e) {
                }
                file.create(dest, IResource.NONE, null);
            }
        } catch (CoreException e) {
        }
        try {
            IEditorPart ep = IDE.openEditor(page, file, true);
            ITextEditor editor = (ITextEditor) ep;
            try {
                IDocument doc = editor.getDocumentProvider().getDocument(editor.getEditorInput());
                FindReplaceDocumentAdapter findReplaceDocumentAdapter= new FindReplaceDocumentAdapter(doc);
                // what we are looking for is defined in the first node of this branch
                TreeItem main=item;
                while(true)
                {
                    if(main.getParentItem()!=null){
                        main = main.getParentItem();
                    }else{
                        break;
                    }
                }
                String[] token = (main.getText()).split(";");
                boolean caseSensitive = false;
                boolean regexSearch = false;
                if(token[0].equalsIgnoreCase("text")){
                    caseSensitive = (token[2].equalsIgnoreCase("true"));
                }else if(token[0].equalsIgnoreCase("like")){
                    regexSearch = true;
                }
                int offset = 0;
                try {
                    while (offset<doc.getLength())
                    {
                        IRegion r= findReplaceDocumentAdapter.find(offset, token[1], true, caseSensitive, false, regexSearch);
                        if(r==null)break;
                        offset = r.getOffset() + 1;
                        HashMap attributes= new HashMap(4);
                        attributes.put(IMarker.CHAR_START, new Integer(r.getOffset() ));
                        attributes.put(IMarker.CHAR_END, new Integer(r.getOffset()  + r.getLength()));
                        MarkerUtilities.createMarker(file, attributes, NewSearchUI.SEARCH_MARKER);
                    }
                } catch (BadLocationException e) {
                }
            
            } catch (Exception e) {
            }
        } catch (PartInitException e) {
        }
    }

    private void showTableView(Bookmark bookmark, Entity entity, String query)
    {
        try {
            SQLResults results = MultiSQLServer.getInstance().execute(
                    bookmark, 
                    this.connectionUtil.connect(bookmark, getShell()),
                    entity,
                    query);
            
            if (results != null && results.isResultSet()) {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try
                {
                    page.showView("com.quantum.view.tableview.TableView");
                }catch(PartInitException e){
                }
	        	// This must be done after the showView, else the toolbar buttons won't be
	        	// active for the first opening (except if the view was already shown)
                SQLResultSetCollection.getInstance().addSQLResultSet((SQLResultSetResults) results);
                
            }
        } catch (SQLException e) {
            SQLExceptionDialog.openException(getShell(), bookmark, e);
        }

    }
	
    private void tableClicked(Entity entity, Event event, TreeItem item)
	{
		// Show the table in the TableView
        Bookmark bookmark = entity.getBookmark();
        DatabaseAdapter adapter = bookmark.getAdapter();
        String query = adapter.getTableQuery(entity.getQuotedTableName());
        showTableView(bookmark, entity, query);
	}
	
	private void columnClicked(Entity entity, Event event, TreeItem item)
	{
        Bookmark bookmark = entity.getBookmark();
        DatabaseAdapter adapter = bookmark.getAdapter();
        String query = "SELECT " + bookmark.filterQuoteName(item.getText()) + " FROM " + entity.getQuotedTableName();
        showTableView(bookmark, entity, query);
	}
	
    private void addEntry(SearchEntry entry) {
    	if(entry.isFullyQualified()){
	    	String text = entry.getTable();
            // Possible fix of [ 1494523 ] Search throwing NPE
            // Oracle probably orders the results differently.
            // In the code I assumed that the "Search" entry
            // would be the first. That may not be the case.
            
            // With the new search this might never be called.
            if(searchItem == null)
            {
                // this is the root entry of our current search
                searchItem = new TreeItem(widget, SWT.NONE);
            }
	        TreeItem[] items = searchItem.getItems();
	        TreeItem table;
	        TreeItem column;
	        int index = 0;
	        while (index < items.length && !(items[index]).getText().equals(text))
	          index++;
	        if(index==items.length)
	        {
	        	// this must be a new tablename
	        	table = addTableItem(entry, text);
	        	column = addColumnItem(entry, table);
	        	addValueItem(entry, column);
	        }else{
	        	// add a node underneath this one
	        	table = items[index];
	        	items = table.getItems();
		        index = 0;
		        while (index < items.length && !(items[index]).getText().equals(entry.getColumn()))
		          index++;
		        if(index==items.length)
		        {
		        	// this is a new column
		        	column = addColumnItem(entry, table);
		        	addValueItem(entry, column);
		        }else{
		        	column = items[index];
		        	addValueItem(entry, column);
		        }
	        }
    	}else{
    		String kw = entry.getKeyword();
    		if(kw.equals("Search"))
    		{
                // we create a new root for this search... This was the original plan, but then
                // someone using Oracle said that he had an NPE. Now I am not quite sure what
                // caused the NPE because the original SQLException created one too, because the
                // display/shell was null.
                
                // I now definitely add the Search entry before any one from the database.
                searchItem = new TreeItem(widget, SWT.NONE);
                searchItem.setText(entry.getMessage());
                searchItem.setImage(ImageStore.getImage(entry.getIcon()));
    		}else if (kw.equals("Procedure"))
            {
    		    // message is the procedure, icon is the icon
                TreeItem proc;
                proc = new TreeItem(searchItem, SWT.NONE);
                proc.setText(entry.getTable());
                proc.setImage(ImageStore.getImage(entry.getIcon()));
                proc.setData(entry);
                TreeItem hitCount = new TreeItem(proc, SWT.NONE);
                hitCount.setText("Hits: " + entry.getCount());
            }
    	}
    }

	private void addValueItem(SearchEntry entry, TreeItem column) {
		TreeItem value = new TreeItem(column, SWT.NONE);
		value.setText(entry.getValue());
		value.setImage(ImageStore.getImage(ImageStore.VALUE));
		if(!entry.getPrimaryKey().equals("()"))
		{
			TreeItem key = new TreeItem(value, SWT.NONE);
			key.setText(entry.getPrimaryKey());
			key.setImage(ImageStore.getImage(ImageStore.KEYCOLUMN));
			key.setData(entry);
		}
		value.setData(entry);
	}

	private TreeItem addColumnItem(SearchEntry entry, TreeItem table) {
		TreeItem column;
		column = new TreeItem(table, SWT.NONE);
		column.setText(entry.getColumn());
		if(entry.getPrimaryKey().indexOf(entry.getColumn()) != -1){
			column.setImage(ImageStore.getImage(ImageStore.KEYCOLUMN));
		}else{
			column.setImage(ImageStore.getImage(ImageStore.COLUMN));
		}
		column.setData(entry);
		return column;
	}

	private TreeItem addTableItem(SearchEntry entry, String text) {
		TreeItem table;
		table = new TreeItem(searchItem, SWT.NONE);
		table.setText(text);
		table.setImage(ImageStore.getImage(ImageStore.TABLE));
		table.setData(entry);
		return table;
	}
    
    public void dispose() {
        SearchResults.getInstance().removePropertyChangeListener(this);
        super.dispose();
    }
    
	public void setFocus() {
		widget.setFocus();
	}

	private Action clearAction = new Action() {
		public void run() {
			SearchResults.getInstance().clear();
            searchItem = null;
		}
	};

	/**
	 * Dispatch a change to the SWT Thread and update the search view as soon as possible.
	 */
    public void propertyChange(final PropertyChangeEvent event) {
        if ("entries".equals(event.getPropertyName())) {
        	getViewSite().getShell().getDisplay().asyncExec(new Runnable() {
        		public void run() {
					if (event.getNewValue() == null && event.getOldValue() == null){
						// this probably now means clear all items??
						// but if the view is opened for the first time, we might want to
						// show the latest results in SearchResults...
						refresh(SearchResults.getInstance().getEntries());
     				} else if (event.getOldValue() == null){
    					addEntry((SearchEntry) event.getNewValue());
    				}
        		}
    		});
        }
    }

    private void refresh(SearchEntry[] entries) {
		widget.removeAll();
        //searchItem = null; // this will silently remove the previous search
		for (int i = 0, length = (entries == null ? 0 : entries.length); i < length; i++) {
			addEntry(entries[i]);
        }
    }
}
