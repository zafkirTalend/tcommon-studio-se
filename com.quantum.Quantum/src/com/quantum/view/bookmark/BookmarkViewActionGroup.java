package com.quantum.view.bookmark;

import java.util.Iterator;
import java.util.Vector;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.actions.AddToQuickListAction;
import com.quantum.actions.ConnectAction;
import com.quantum.actions.CustomCopyAction;
import com.quantum.actions.DeleteAllRowsAction;
import com.quantum.actions.DisconnectAction;
import com.quantum.actions.NewBookmarkAction;
import com.quantum.actions.NextSequenceAction;
import com.quantum.actions.OpenQueryAction;
import com.quantum.actions.PrevSequenceAction;
import com.quantum.actions.RefreshBookmarkAction;
import com.quantum.actions.RemoveFromQuickListAction;
import com.quantum.actions.StructureCopyFullAction;
import com.quantum.actions.StructurePasteAction;
import com.quantum.actions.StructureWriteToFileAction;
import com.quantum.actions.ViewTableAction;
import com.quantum.actions.ViewTableDetailsAction;
import com.quantum.actions.SearchEntityAction;
import com.quantum.actions.ShowProcedureBodyAction;
import com.quantum.model.Bookmark;
import com.quantum.util.versioning.VersioningHelper;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.ExportResourcesAction;
import org.eclipse.ui.actions.ImportResourcesAction;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.ui.actions.SelectionProviderAction;
import org.eclipse.ui.dialogs.PropertyDialogAction;

/**
 * This class manages the list of actions for the bookmark view.
 * 
 * @author BC Holmes
 */
public class BookmarkViewActionGroup extends ActionGroup 
    implements BookmarkClipboard {
	
	class SQLAction extends Action implements IMenuCreator {
		public SQLAction() {
			setText(Messages.getString(BookmarkViewActionGroup.class, "sqlAction.text"));
			setMenuCreator(this);
		}
		public void dispose() {
		}
		public Menu getMenu(Control parent) {
			return null;
		}
		public Menu getMenu(Menu parent) {
			Menu menu = new Menu(parent);
			/**
			 * Add listener to repopulate the menu each time
			 * it is shown because the list of bookmarks may have changed.
			 */
			menu.addMenuListener(new MenuAdapter() {
				public void menuShown(MenuEvent event) {
					Menu menu = (Menu) event.widget;
					MenuItem[] items = menu.getItems();
					for (int i=0; i < items.length; i++) {
						items[i].dispose();
					}
					fillSQLMenu(menu);
				}
			});
			return menu;
		}
	}
	
	/**
	 * @author Julen
	 *	The sub menu with the group of Custom copy actions defined by the user
	 */
	class CustomCopyGroupAction extends Action implements IMenuCreator {
	
		public CustomCopyGroupAction() {
			setText(Messages.getString("bookmarkview.customCopyAction"));
			setMenuCreator(this);
		}
		public void dispose() {
		}
		public Menu getMenu(Control parent) {
			return null;
		}
		public Menu getMenu(Menu parent) {
			Menu menu = new Menu(parent);
			menu.addMenuListener(new MenuAdapter() {
				public void menuShown(MenuEvent event) {
					Menu menu = (Menu) event.widget;
					MenuItem[] items = menu.getItems();
					for (int i=0; i < items.length; i++) {
						items[i].dispose();
					}
					for (int i = 0; i < customCopyActions.size(); i++) {
						CustomCopyAction action = (CustomCopyAction) customCopyActions.get(i);
						addToMenu(menu, action);
					}
				}
			});
			return menu;
		}
	}
	
	/**
	 * @author Julen
	 *	The sub menu with the group of Custom copy actions defined by the user
	 */
	class StructureGroupAction extends Action implements IMenuCreator {
	
		public StructureGroupAction() {
			setText(Messages.getString("bookmarkview.structureAction"));
			setMenuCreator(this);
		}
		public void dispose() {
		}
		public Menu getMenu(Control parent) {
			return null;
		}
		public Menu getMenu(Menu parent) {
			Menu menu = new Menu(parent);
			menu.addMenuListener(new MenuAdapter() {
				public void menuShown(MenuEvent event) {
					Menu menu = (Menu) event.widget;
					MenuItem[] items = menu.getItems();
					for (int i=0; i < items.length; i++) {
						items[i].dispose();
					}
					addToMenu(menu, structureCopyFullAction);
					addToMenu(menu, structurePasteAction);
					addToMenu(menu, structureWriteToFileAction);
					
				}
			});
			return menu;
		}
	}
	
	class keyListener implements KeyListener {
	    	public void keyReleased(KeyEvent event) {
	    	}
	    	
	    	public void keyPressed(KeyEvent event) {
	    		if (event.keyCode == SWT.F5) {
	    			refreshAction.run();
	    		}
	    	}
	}
	
    private Bookmark bookmarkClipboard;
    
    private Action newBookmarkAction;
    private Action sqlAction;
    private Action collapseAllAction;
    
    // bookmark node actions
    private SelectionListenerAction connectAction;
    private SelectionListenerAction disconnectAction;
    private SelectionListenerAction deleteAction;

    // Query node actions
    private SelectionListenerAction openQueryAction;

    // Entity node actions
    private SelectionListenerAction addToQuickListAction;
    private SelectionListenerAction removeFromQuickListAction;
    private SelectionListenerAction viewTableAction;
    private SelectionListenerAction viewTableDetailsAction;

    private SelectionListenerAction searchEntityAction;
    
    private SelectionListenerAction nextSequenceAction;
    private SelectionListenerAction previousSequenceAction;
    

    private SelectionListenerAction dropAction;
    
    // Procedure node actions
    private SelectionListenerAction showProcedureBodyAction;
    
    // other actions
    private SelectionListenerAction refreshAction;
    private SelectionListenerAction renameAction;

    private ExportResourcesAction exportAction;
    private ImportResourcesAction importAction;

    
    private SelectionProviderAction propertiesAction;
    
    private SelectionListenerAction copyAction;
    private PasteAction pasteAction;
    private SelectionListenerAction deleteAllRowsAction;
	
	private StructureWriteToFileAction structureWriteToFileAction;
	private StructureCopyFullAction structureCopyFullAction;
	private StructurePasteAction structurePasteAction;
	
	// Custom copy action group
	private CustomCopyGroupAction customCopyGroupAction;
	// Custom copy actions will be created into this vector, if any
	private Vector customCopyActions;
    
	// "Structure" operations will be created in this group
	private StructureGroupAction structureGroupAction;
	
    private IViewPart viewPart;
    
	
    public BookmarkViewActionGroup(
        IViewPart viewPart, ISelectionProvider selectionProvider) {
        this.viewPart = viewPart;
        
        this.newBookmarkAction = new NewBookmarkAction(this.viewPart);
        this.collapseAllAction = new CollapseAllAction((AbstractTreeViewer) selectionProvider);
        
        // bookmark actions
        this.connectAction = new ConnectAction(this.viewPart);
        this.disconnectAction = new DisconnectAction(this.viewPart);
        
        this.sqlAction = new SQLAction();
		this.structureGroupAction = new StructureGroupAction();

        // entity actions
        this.previousSequenceAction = new PrevSequenceAction(this.viewPart);
        this.nextSequenceAction = new NextSequenceAction(this.viewPart);
        this.addToQuickListAction = new AddToQuickListAction(this.viewPart);
        this.removeFromQuickListAction = new RemoveFromQuickListAction(this.viewPart);
        this.deleteAllRowsAction = new DeleteAllRowsAction(this.viewPart);
        this.viewTableDetailsAction = new ViewTableDetailsAction(this.viewPart);
		this.searchEntityAction = new SearchEntityAction(this.viewPart);
		this.structureWriteToFileAction = new StructureWriteToFileAction(this.viewPart);
		this.structureCopyFullAction = new StructureCopyFullAction(this.viewPart);
		this.structurePasteAction = new StructurePasteAction(this.viewPart);
		
        this.openQueryAction = new OpenQueryAction(this.viewPart);
        
        // procedure actions
        this.showProcedureBodyAction = new ShowProcedureBodyAction(this.viewPart);
        this.showProcedureBodyAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.PROCEDURE));
        
        this.viewTableAction = new ViewTableAction(this.viewPart);
        this.refreshAction = new RefreshBookmarkAction(this.viewPart);
        this.renameAction = new RenameAction(this.viewPart);
        this.copyAction = new CopyAction(this.viewPart, this, selectionProvider);
        this.pasteAction = new PasteAction(this.viewPart, this, selectionProvider);
        this.deleteAction = new DeleteAction(this.viewPart, selectionProvider);
        this.exportAction = VersioningHelper.createExportResourcesAction(
        		this.viewPart.getViewSite().getWorkbenchWindow());
        this.exportAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.EXPORT));
        this.importAction = VersioningHelper.createImportResourcesAction(
        		this.viewPart.getViewSite().getWorkbenchWindow());
        this.importAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.IMPORT));
        
        this.dropAction = new DropEntityAction(this.viewPart);
        
        this.propertiesAction = new PropertyDialogAction(
            this.viewPart.getSite().getShell(), selectionProvider);
		
        ((TreeViewer)selectionProvider).getControl().addKeyListener(new keyListener());        
   }

    
    /**
     * Add all the appropriate actions to the popup menu. This method is 
     * called whenever someone right-clicks on an object in the bookmark view.
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void fillContextMenu(IMenuManager menu) {
        
        menu.add(this.newBookmarkAction);
        menu.add(new Separator());
        
        if (getStructuredSelection().size() > 0 && 
            isEverySelectionInstanceof(BookmarkNode.class)) {
                
            addToMenu(menu, this.connectAction);
            addToMenu(menu, this.disconnectAction);
			 menu.add(new Separator());
	    }

        menu.add(this.copyAction);
        // TODO: paste needs to change enablement whenever something is added
        //       to the clipboard
        addToMenu(menu, this.pasteAction);
        addToMenu(menu, this.deleteAction);
		
		// The custom copy definitions can change, so we have to refresh them every time
		// the menu is shown
		initCustomCopyActions();

		if (getStructuredSelection().size() > 0 && 
				( getStructuredSelection().getFirstElement() instanceof EntityNode ||
				  getStructuredSelection().getFirstElement() instanceof ColumnNode ) ) {
			if (customCopyActions.size() > 0) {
				this.customCopyGroupAction = new CustomCopyGroupAction();
				menu.add(this.customCopyGroupAction);
			}
		}
		       
		if (getStructuredSelection().size() == 1 && 
            isEverySelectionInstanceof(BookmarkNode.class)) {

            addToMenu(menu, this.renameAction);
        }

        menu.add(new Separator());

        // NOTE: In Eclipse 3.0, Export is no longer a sub-class of 
        //       SelectionListenerAction.
        if (this.exportAction != null) {
	        this.exportAction.selectionChanged(getStructuredSelection());
	        menu.add(this.exportAction);
        }
        if (this.importAction != null) {
	        this.importAction.selectionChanged(getStructuredSelection());
        		menu.add(this.importAction);
        }

        
        if (isEverySelectionInstanceof(QueryNode.class)) {
            if (getStructuredSelection().size() == 1) {
                addToMenu(menu, this.openQueryAction);
            }
        }

        if (isEverySelectionInstanceof(ProcedureNode.class)) {
            if (getStructuredSelection().size() == 1) {
                addToMenu(menu, this.showProcedureBodyAction);
            }
        }

        if (getStructuredSelection().size() > 0 && 
            isEverySelectionInstanceof(EntityNode.class)) {
                
            menu.add(new Separator());
                
            if (getStructuredSelection().size() == 1) {
                if (((EntityNode) getStructuredSelection().getFirstElement()).isSequence()) {
                    addToMenu(menu, this.nextSequenceAction);
                    addToMenu(menu, this.previousSequenceAction);
                } else {
                    addToMenu(menu, this.viewTableAction);
                    addToMenu(menu, this.viewTableDetailsAction);
                }
            }
            
            addToMenu(menu, this.addToQuickListAction);
            addToMenu(menu, this.removeFromQuickListAction);
            
            if (getStructuredSelection().size() == 1) {
            	menu.add(new Separator());
            	menu.add(this.sqlAction);
            }
       }
		
		menu.add(structureGroupAction);
		         
        if (getStructuredSelection().size() == 1) {
            menu.add(new Separator());
            addToMenu(menu, this.refreshAction);
        }
        menu.add(new Separator());
        addToMenu(menu, this.searchEntityAction);
        this.searchEntityAction.setEnabled(true); // always active, even if disconnected from database?
        
        createMarkerForActionsProvidedByOtherPlugins(menu);
        
        if (getStructuredSelection().size() == 1) {
            addToMenu(menu, this.propertiesAction);
        }
    }
    
    protected void fillSQLMenu(Menu parent) {
        if (getStructuredSelection().size() > 0 && 
                isEverySelectionInstanceof(EntityNode.class)) {
			addToMenu(parent, this.deleteAllRowsAction);
            addToMenu(parent, this.dropAction);
        }
    }

    private void addToMenu(Menu menu, SelectionListenerAction action) {
        action.selectionChanged(getStructuredSelection());
		ActionContributionItem item = new ActionContributionItem(action);
		item.fill(menu, -1);
    }
    
    private void addToMenu(IMenuManager menu, SelectionListenerAction action) {
        action.selectionChanged(getStructuredSelection());
        menu.add(action);
    }
    
    private void addToMenu(IMenuManager menu, SelectionProviderAction action) {
        action.selectionChanged(getStructuredSelection());
        menu.add(action);
    }
    
    
    private IStructuredSelection getStructuredSelection() {
        return (IStructuredSelection) getContext().getSelection();
    }
    
    private boolean isEverySelectionInstanceof(Class selectedClass) {
        boolean result = true;
        IStructuredSelection selection = getStructuredSelection();
        for (Iterator i = selection.iterator(); result && i.hasNext(); ) {
            Object object = i.next();
            result &= selectedClass.isAssignableFrom(object.getClass());
        }
        
        return result;
    }
    public IAction getOpenAction() {
        if (isEverySelectionInstanceof(BookmarkNode.class)) {
            this.connectAction.selectionChanged(getStructuredSelection());
            return this.connectAction.isEnabled() ? this.connectAction : null; 
        } else if (isEverySelectionInstanceof(EntityNode.class)) {
            this.viewTableAction.selectionChanged(getStructuredSelection());
            return this.viewTableAction.isEnabled() ? this.viewTableAction : null; 
        } else if (isEverySelectionInstanceof(QueryNode.class)) {
            this.openQueryAction.selectionChanged(getStructuredSelection());
            return this.openQueryAction.isEnabled() ? this.openQueryAction : null;
        } else if (isEverySelectionInstanceof(ProcedureNode.class)) {
        	this.showProcedureBodyAction.selectionChanged(getStructuredSelection());
            return this.showProcedureBodyAction.isEnabled() ? this.showProcedureBodyAction : null;
        } else {
            return null;
        }
    }

    private void createMarkerForActionsProvidedByOtherPlugins(IMenuManager mgr) {
        mgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        mgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end")); //$NON-NLS-1$
        mgr.add(new Separator());
    }
    public void fillActionBars(IActionBars actionBars) {
        IToolBarManager toolBar = actionBars.getToolBarManager();
        toolBar.add(this.newBookmarkAction);
        toolBar.add(this.collapseAllAction);
        
        actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), this.copyAction);
        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), this.deleteAction);
    }


    /* (non-Javadoc)
     * @see com.quantum.view.bookmark.BookmarkClipboard#setBookmark(com.quantum.model.Bookmark)
     */
    public void setBookmark(Bookmark bookmark) {
        this.bookmarkClipboard = bookmark;
    }


    /* (non-Javadoc)
     * @see com.quantum.view.bookmark.BookmarkClipboard#getBookmark()
     */
    public Bookmark getBookmark() {
        return this.bookmarkClipboard;
    }
	
	/**
	 * Fill the customCopyActions vector with the needed actions to reflect the user-defined custom copies
	 */
	private void initCustomCopyActions() {
		customCopyActions = new Vector();
		IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
		// Ask to the preferece store for which custom copies are defined, and store them in the Vector
		for (int i = 1; i <= CustomCopyAction.NUMBER_OF_ACTIONS; i++) {
			String name = store.getString(CustomCopyAction.CUSTOM_COPY_NAME_PREFIX + Integer.toString(i));
			if (name != null && name.trim().length() > 0) {
				CustomCopyAction newCustomCopyAction = new CustomCopyAction(i);
				
				customCopyActions.add(newCustomCopyAction);
			}
		}
	}
	
}
