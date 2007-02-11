package com.quantum.view.bookmark;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Vector;

import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.part.ViewPart;

import com.quantum.IQuantumConstants;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.editors.graphical.EntityRelationEditor;
import com.quantum.extensions.ExtensionAction;
import com.quantum.extensions.ProcessServiceMembers;
import com.quantum.util.connection.NotConnectedException;



public class BookmarkView extends ViewPart implements PropertyChangeListener {
	private Vector extensionVector;
    
    private BookmarkViewActionGroup actionGroup;

	private TreeViewer treeViewer;
    private BookmarkLabelProvider labelProvider = new BookmarkLabelProvider();
    
	/**
	 * @return	-
	 * 	The instance of the BookmarkView. There is no guarantee of it being a singleton
	 * 	due to the workspace creating a new one (does the workspace call getInstance() ? ).
	 * 	It seems to work.
	 */
	public synchronized static BookmarkView getInstance() {
		return (BookmarkView) QuantumPlugin.getDefault().getView("com.quantum.view.bookmarkview");
	}
	/**
	 * @return The current selected object in the tree. If it's a multiple selection, return the first.
	 */
	private Object getCurrent() {
		if (treeViewer == null) return null;
		return ((StructuredSelection) treeViewer.getSelection())
					.getFirstElement();
	}
	/**
	 * @return The current selected objects in the tree, in the form of a StructuredSelection.
	 */
	public StructuredSelection getSelection() {
		if (treeViewer == null) return null;
		return ((StructuredSelection) treeViewer.getSelection());
	}
	
	/**
	 * Refreshes the displayed tree with the possible changes to the underlying data
	 */
	public void refresh() {
		treeViewer.refresh();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
		// The main tree of bookmarks
		treeViewer = new TreeViewer(
            parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		treeViewer.setContentProvider(new BookmarkContentProvider(this));
		treeViewer.setLabelProvider(this.labelProvider);
        BookmarkListNode input = BookmarkListNode.getInstance();
		treeViewer.setInput(input);
        initActions();
        
        input.addPropertyChangeListener(this);
        
        initializePopUpMenu();
        fillActionBars();
        
		treeViewer.addOpenListener(new IOpenListener() {
			public void open(OpenEvent event) {
                ActionContext context = new ActionContext(
                    getSelection());
                BookmarkView.this.actionGroup.setContext(context);
                IAction action = actionGroup.getOpenAction();
                if (action != null) {
                    action.run();
                }
			}
		});
	   // Create the drag source on the tree
	   DragSource ds = new DragSource(treeViewer.getTree(), DND.DROP_COPY | DND.DROP_DEFAULT);
	   ds.setTransfer(new Transfer[] {TextTransfer.getInstance(), TemplateTransfer.getInstance()});
	   ds.addDragListener(new DragSourceAdapter() {
	      public void dragStart(DragSourceEvent event) {
	          event.doit = (treeViewer.getTree().getSelection()[0].getText().length() != 0);
	        }

	        public void dragSetData(DragSourceEvent event) {
	        	if(TextTransfer.getInstance().isSupportedType(event.dataType))
	        	{
		        	// which node is selected?
		        	StructuredSelection ss = (StructuredSelection) treeViewer.getSelection();
		        	if(ss.getFirstElement() instanceof EntityNode)
		        	{
		        		// table or view, use text
			        	event.data = treeViewer.getTree().getSelection()[0].getText() +  " " ;
		        	}else if(ss.getFirstElement() instanceof ColumnNode){
		        		// use the column name
		        		ColumnNode c = (ColumnNode) ss.getFirstElement();
		        		event.data = c.getName() + ", ";
		        	}else if(ss.getFirstElement() instanceof ProcedureNode){
		        		ProcedureNode p = (ProcedureNode) ss.getFirstElement();
		        		// get the name, might even add exec? and the parameters?
		        		// return type?
		        		event.data = p.getName() + " (" ;
                        try
                        {
                            for(int i=0; i<p.getChildren().length; i++){
                                ArgumentNode a = (ArgumentNode) p.getChildren()[i];
                                if(i==0){
                                    event.data = event.data + a.getName();
                                }else{
                                    event.data = event.data + ", " + a.getName();
                                }
                            }
                        }catch(NotConnectedException e){
                        }catch(SQLException ex){
                        }
                        event.data = event.data + ")";
		        	}else if(ss.getFirstElement() instanceof ArgumentNode){
		        	    ArgumentNode a = (ArgumentNode) ss.getFirstElement();
                        event.data = a.getName();
                    }
	        	}
	        	if(TemplateTransfer.getInstance().isSupportedType(event.dataType)){
		        	StructuredSelection ss = (StructuredSelection) treeViewer.getSelection();
	        		event.data = ss.getFirstElement();
	        	}
	        }

	   });


	}
	
	
    private void fillActionBars() {
        Action enableTableSizes = new Action() {
        	public void run() {
        		labelProvider.getLabelDecorationInstructions().setSizeVisible(isChecked());
                treeViewer.refresh();
        	}
        };
        enableTableSizes.setText(Messages.getString("BookmarkView.ShowTableSizes")); //$NON-NLS-1$
        enableTableSizes.setChecked(false);

        IActionBars actionBars = getViewSite().getActionBars();
        actionBars.getMenuManager().add(enableTableSizes);
        
        Action showDatabaseData = new Action() {
            public void run() {
                labelProvider.getLabelDecorationInstructions().setDatabaseDataVisible(isChecked());
                treeViewer.refresh();
            }
        };
        showDatabaseData.setText(Messages.getString("BookmarkView.ShowDatabaseData")); //$NON-NLS-1$
        showDatabaseData.setChecked(false);
        actionBars.getMenuManager().add(showDatabaseData);

        this.actionGroup.fillActionBars(actionBars);
    }
    
    private void initializePopUpMenu() {
        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager mgr) {
                fillContextMenu(mgr);
            }
        });
        Menu fTextContextMenu =
            manager.createContextMenu(treeViewer.getControl());
        treeViewer.getControl().setMenu(fTextContextMenu);
        // register the menu to the site so that we can allow 
        // actions to be plugged in
        getSite().registerContextMenu(manager, this.treeViewer);
    }
	
	public void initActions() {
        
        this.actionGroup = new BookmarkViewActionGroup(this, this.treeViewer);
        

		extensionVector = new Vector();
		try {
			ProcessServiceMembers.process(this, extensionVector);
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}

	}
	
	public void dispose(){
		super.dispose();
        BookmarkListNode.getInstance().removePropertyChangeListener(this);
	}

	public Shell getShell() {
		return getSite().getShell();
	}

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if ("bookmarks".equals(event.getPropertyName())) {
            refresh();
        } else if (IQuantumConstants.NAME_PROPERTY.equals(event.getPropertyName()) && 
            event.getSource() instanceof BookmarkNode) {
            refresh();
        } else if ("connected".equals(event.getPropertyName())) {
            treeViewer.refresh(event.getSource());
            if (Boolean.TRUE.equals(event.getNewValue())) {
                treeViewer.setExpandedState(event.getSource(), true);
                IWorkbenchPage page = getSite().getPage();
                IEditorReference[] editors = page.getEditorReferences();
                try
                {
                    for(int i=0; i < editors.length; i++)
                    {
                        if(editors[i].getEditor(true).getClass().equals(EntityRelationEditor.class)){
                            EntityRelationEditor e = (EntityRelationEditor) editors[i].getEditor(false);
                            e.addPaletteEntries();
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                // jhvdv: and take it away here.
                IWorkbenchPage page = getSite().getPage();
                IEditorReference[] editors = page.getEditorReferences();
                try
                {
                    for(int i=0; i < editors.length; i++)
                    {
                        if(editors[i].getEditor(false).getClass().equals(EntityRelationEditor.class)){
                            EntityRelationEditor e = (EntityRelationEditor) editors[i].getEditor(false);
                            e.removePaletteEntries();
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } else {
            treeViewer.refresh(event.getSource());
        }
    }
    
    private void fillContextMenu(IMenuManager mgr) {
    
	 IStructuredSelection selection = getSelection();
        ActionContext context = new ActionContext(selection);
        this.actionGroup.setContext(context);
        this.actionGroup.fillContextMenu(mgr);
        
        Object sel = getCurrent();
        // If selection is a BookmarkNode
        if (sel instanceof EntityNode) {
            EntityNode entityNode = (EntityNode) sel;
            if (!entityNode.isSequence()) {
            	MenuManager subMenuExtension = new MenuManager("Extensions"); 
				for (int i = 0; i < extensionVector.size(); i++) {
					ExtensionAction extensionAction = (ExtensionAction) extensionVector.get(i);
					subMenuExtension.add(extensionAction);
				}
				if (extensionVector.size() > 0) mgr.add(subMenuExtension);
            }
        }
    }
    /**
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    public void setFocus() {
    }
}
