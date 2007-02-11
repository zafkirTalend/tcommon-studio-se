package com.quantum.editors.graphical;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.PrintAction;
import org.eclipse.gef.ui.actions.RedoAction;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.actions.StackAction;
import org.eclipse.gef.ui.actions.UndoAction;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.properties.UndoablePropertySheetEntry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

import com.quantum.ImageStore;
import com.quantum.QuantumPlugin;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.actions.MarkForOutputAction;
import com.quantum.editors.SQLContentOutlinePage;
import com.quantum.editors.graphical.actions.EntityRelationContextMenuProvider;
import com.quantum.editors.graphical.directedit.StatusLineValidationMessageHandler;
import com.quantum.editors.graphical.dnd.DataEditDropTargetListener;
import com.quantum.editors.graphical.dnd.DataElementFactory;
import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.ColumnType;
import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.parts.EntityRelationEditPartFactory;
import com.quantum.model.Bookmark;
import com.quantum.model.DataType;
import com.quantum.model.ISQLSyntaxModel;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;

public class EntityRelationEditor extends GraphicalEditorWithFlyoutPalette
implements ISelectionListener, CommandStackListener
{
    
    /** This is the root of the editor's model. */
	private EntityRelationDiagram diagram;
    /**
     * The palette.
     */
    protected static PaletteRoot palette = null;
  
	/** the list of action ids that are editor actions */
	private List editorActionIDs = new ArrayList();
	/** the list of action ids that are to EditPart actions */
	private List editPartActionIDs = new ArrayList();
	/** the list of action ids that are to CommandStack actions */
	private List stackActionIDs = new ArrayList();
	/** the dirty state */
	private boolean isDirty;
	/** the graphical viewer */
	private GraphicalViewer graphicalViewer;
	/** the <code>EditDomain</code> */
	private DefaultEditDomain editDomain;
	/** the editor's action registry */
	private ActionRegistry actionRegistry;

    private ERContentOutlinePage outlinePage = null;
    
    private PropertySheetPage undoablePropertySheetPage = null;
    
	/** Create a new EntityRelationEditor instance. This is called by the Workspace. */
	public EntityRelationEditor() {
        super();
		editDomain = new DefaultEditDomain(this);
		setEditDomain(editDomain);
        getCommandStack().setUndoLimit(-1);
	}

    public EntityRelationEditor(IFile f, String query, ISQLSyntaxModel model) {
        super();
        // the IFile does not exist yet, why is that?
        // because the fileformat is determined by this class.
//        InputStream source = new StringBufferInputStream(query);
//        queryFile.create(source, IResource.NONE, null);

        
        editDomain = new DefaultEditDomain(this);
        setEditDomain(editDomain);
        getCommandStack().setUndoLimit(-1);
        
    }

    /**
     * The <code>CommandStackListener</code> that listens for
     * <code>CommandStack </code> changes.
     */
    public void commandStackChanged(EventObject event)
    {
        updateActions(stackActionIDs);
        setDirty(getCommandStack().isDirty());
    }
    
    protected void createGraphicalViewer(Composite parent) {

        IEditorSite editorSite = getEditorSite();
        StatusLineValidationMessageHandler validationMessageHandler = new StatusLineValidationMessageHandler(
                editorSite);
        GraphicalViewer viewer = new ValidationEnabledGraphicalViewer(
                validationMessageHandler);
        viewer.createControl(parent);


        // hook the viewer into the EditDomain
        getEditDomain().addViewer(viewer);

        // acticate the viewer as selection provider for Eclipse
        getSite().setSelectionProvider(viewer);

        viewer.setEditPartFactory(new EntityRelationEditPartFactory());
        ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();
        viewer.setRootEditPart(root);
        viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));

        IAction action = new ZoomInAction(root.getZoomManager());
        getActionRegistry().registerAction(action);
        getSite().getKeyBindingService().registerAction(action);
        action = new ZoomOutAction(root.getZoomManager());
        getActionRegistry().registerAction(action);
        getSite().getKeyBindingService().registerAction(action);
        
        ContextMenuProvider cmProvider = new EntityRelationContextMenuProvider(viewer, getActionRegistry());
        viewer.setContextMenu(cmProvider);
        getSite().registerContextMenu(cmProvider, viewer);

        viewer.setContents(diagram);

        this.graphicalViewer = viewer;

        DropTarget target = new DropTarget(viewer.getControl(), DND.DROP_COPY | DND.DROP_DEFAULT);
        target.setTransfer(new Transfer[] { TemplateTransfer.getInstance() });
        target.addDropListener(new DataEditDropTargetListener(this));
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
     */
    protected void setInput(IEditorInput input) {
        super.setInput(input);
        try {
            IFile file = ((IFileEditorInput) input).getFile();
            ObjectInputStream in = new ObjectInputStream(file.getContents());
            diagram = (EntityRelationDiagram) in.readObject();
            in.close();
            setPartName(file.getName());
            
            CombinedTemplateCreationEntry component;
            PaletteRoot root = getPaletteRoot();
            if(root.getChildren().size()<4)
            {
                PaletteDrawer numericDrawer = new PaletteDrawer("Numbers");
                PaletteDrawer textDrawer = new PaletteDrawer("Text");
                PaletteDrawer restDrawer = new PaletteDrawer("Various");
    
                Bookmark bookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
                DataType[] types = null;
                try {
                    types = bookmark.getDataTypes();
                } catch (NotConnectedException e) {
                    return ;
                } catch (SQLException e) {
                    return ;
                }
                ColumnType.clear();
                for(int i=0; i<types.length; i++)
                {
                    final DataType type = types[i];
                    new ColumnType(types[i].getDatabaseTypeName());
                    // this only works with Point and Click, not with Drag and Drop 
                    DataElementFactory factory = new DataElementFactory(this, Column.class);
                    factory.setColumnType(type.getDatabaseTypeName());
                    factory.setJavaColumnType(type.getJavaNameType());
                    component = new CombinedTemplateCreationEntry(
                            type.getDatabaseTypeName(),
                            "Add " + type.getDatabaseTypeName() + " (java type: " + type.getJavaNameType() + ")", 
                            Column.class,
                            factory,
                            ImageStore.getImageDescriptor(ImageStore.COLUMN),
                            ImageStore.getImageDescriptor(ImageStore.COLUMN)
                            );
                    if(TypesHelper.isNumeric(type.getJavaType())){
                        numericDrawer.add(component);
                    }else if(TypesHelper.isText(type.getJavaType())){
                        textDrawer.add(component);
                    }else{
                        restDrawer.add(component);
                    }
                }
                root.add(numericDrawer);
                root.add(textDrawer);
                root.add(restDrawer);
            }
        } catch (IOException e) { 
            handleLoadException(e); 
        } catch (CoreException e) { 
            handleLoadException(e); 
        } catch (ClassNotFoundException e) { 
            handleLoadException(e); 
        }
    }
    
    public void removePaletteEntries()
    {
        PaletteRoot root = getPaletteRoot();
        if (root.getChildren().size() >= 4) {
            List kiddies = root.getChildren();
            List remove = new ArrayList();
            for(int i = 0; i < kiddies.size(); i++)
            {
                try
                {
                    PaletteDrawer drawer = (PaletteDrawer) kiddies.get(i);
                    if(drawer.getLabel().equals("Numbers") ||
                       drawer.getLabel().equals("Text") ||
                       drawer.getLabel().equals("Various"))
                    {
                        // add it to the list of items to remove, because otherwise the kiddies.size() fools us
                        remove.add(drawer);
                    }
                }catch(Exception e){}
            }
            for(int i = 0; i < remove.size(); i++)
            {
                root.remove((PaletteEntry) remove.get(i));
            }
        }
    }
    
    public void addPaletteEntries()
    {
        CombinedTemplateCreationEntry component;
        PaletteRoot root = getPaletteRoot();
        if (root.getChildren().size() < 4) {
            PaletteDrawer numericDrawer = new PaletteDrawer("Numbers");
            PaletteDrawer textDrawer = new PaletteDrawer("Text");
            PaletteDrawer restDrawer = new PaletteDrawer("Various");

            Bookmark bookmark = BookmarkSelectionUtil.getInstance()
                    .getLastUsedBookmark();
            DataType[] types = null;
            try {
                types = bookmark.getDataTypes();
            } catch (NotConnectedException e) {
                return;
            } catch (SQLException e) {
                return;
            }
            ColumnType.clear();
            String sorted[]=new String[types.length];
            HashMap javaTypes = new HashMap();
            HashMap javaTypesInt = new HashMap();
            for (int i = 0; i < types.length; i++) {
                sorted[i] = types[i].getDatabaseTypeName();
                javaTypes.put(sorted[i], types[i].getJavaNameType());
                javaTypesInt.put(sorted[i], new Integer(types[i].getJavaType()));
                new ColumnType(types[i].getDatabaseTypeName());
            }
            Arrays.sort(sorted);
            for( int i = 0; i < types.length; i++)
            {
                // this only works with Point and Click, not with Drag and Drop
                DataElementFactory factory = new DataElementFactory(this,
                        Column.class);
                factory.setColumnType(sorted[i]);
                factory.setJavaColumnType((String)javaTypes.get(sorted[i]));
                component = new CombinedTemplateCreationEntry(sorted[i]
                        , "Add "
                        + sorted[i] + " (java type: "
                        + (String)javaTypes.get(sorted[i]) + ")", Column.class, factory,
                        ImageStore.getImageDescriptor(ImageStore.COLUMN),
                        ImageStore.getImageDescriptor(ImageStore.COLUMN));
                if (TypesHelper.isNumeric(((Integer)javaTypesInt.get(sorted[i])).intValue())) {
                    numericDrawer.add(component);
                } else if (TypesHelper.isText(((Integer)javaTypesInt.get(sorted[i])).intValue())) {
                    textDrawer.add(component);
                } else {
                    restDrawer.add(component);
                }
            }
            root.add(numericDrawer);
            root.add(textDrawer);
            root.add(restDrawer);
        }
    }
    protected FlyoutPreferences getPalettePreferences() {
        return EntityRelationEditorPaletteFactory.createPalettePreferences();    
    }
    
    protected PaletteRoot getPaletteRoot() {
        if (palette == null)
        {
            palette = EntityRelationEditorPaletteFactory.createPalette();
        }
        return palette;
    }
    /* (non-Javadoc)
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
     */
    protected PaletteViewerProvider createPaletteViewerProvider() {
        return new PaletteViewerProvider(getEditDomain()) {
            protected void configurePaletteViewer(PaletteViewer viewer) {
                super.configurePaletteViewer(viewer);
                // this enables the palette to act as drag source
                viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
            }
        };
    }

    public EntityRelationDiagram getModel() {
        return diagram;
    }
    
    private void handleLoadException(Exception e) {
        diagram = new EntityRelationDiagram("Empty diagram");
    }
  
	/** the selection listener implementation */
	public void selectionChanged(IWorkbenchPart part, ISelection selection)
	{
		updateActions(editPartActionIDs);
	}

	/**
	 * Sets the dirty state of this editor.
	 * 
	 * <p>
	 * An event will be fired immediately if the new state is different than the
	 * current one.
	 * 
	 * @param dirty
	 *            the new dirty state to set
	 */
	protected void setDirty(boolean dirty)
	{
		if (isDirty != dirty)
		{
			isDirty = dirty;
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	}
	/**
	 * Indicates if the editor has unsaved changes.
	 * 
	 * @see EditorPart#isDirty
	 */
	public boolean isDirty()
	{
		return isDirty;
	}

    /**
	 * Returns the <code>GraphicalViewer</code> of this editor.
	 * 
	 * @return the <code>GraphicalViewer</code>
	 */
	public GraphicalViewer getGraphicalViewer()
	{
		return graphicalViewer;
	}

    public CommandStack getMeTheCommandStack()
    {
        return getCommandStack();
    }
    
	public void dispose()
	{
		// remove CommandStackListener
		getCommandStack().removeCommandStackListener(this);
		// remove selection listener
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
		// dispos3 the ActionRegistry (will dispose all actions)
		getActionRegistry().dispose();
		// important: always call super implementation of dispose
		super.dispose();
	}
	/**
	 * Adaptable implementation for Editor
	 */
	public Object getAdapter(Class adapter)
	{
		// we need to handle common GEF elements we created
		if (adapter == GraphicalViewer.class || adapter == EditPartViewer.class)
			return getGraphicalViewer();
		else if (adapter == CommandStack.class)
			return getCommandStack();
		else if (adapter == EditDomain.class)
			return getEditDomain();
		else if (adapter == ActionRegistry.class)
			return getActionRegistry();
		else if (adapter == IPropertySheetPage.class)
			return getPropertySheetPage();
        else if (adapter == ZoomManager.class)
            return ((ScalableFreeformRootEditPart)getGraphicalViewer().getRootEditPart()).getZoomManager();
		else if (adapter == IContentOutlinePage.class){
            if (IContentOutlinePage.class.equals(adapter)) {
                if (outlinePage == null) {
                    outlinePage= new ERContentOutlinePage(this);
                    if (getEditorInput() != null)
                    {
                        outlinePage.setInput(getEditorInput());
                    }
                }
                return outlinePage;
            }
      
        }
		// the super implementation handles the rest
		return super.getAdapter(adapter);
	}

    /**
	 * Returns the undoable <code>PropertySheetPage</code> for this editor.
	 * 
	 * @return the undoable <code>PropertySheetPage</code>
	 */
	protected PropertySheetPage getPropertySheetPage()
	{
		if (null == undoablePropertySheetPage)
		{
			undoablePropertySheetPage = new PropertySheetPage();
			undoablePropertySheetPage.setRootEntry(new UndoablePropertySheetEntry(getCommandStack()));
		}
		return undoablePropertySheetPage;
	}

    /**
	 * Saves the schema model to the file
	 * 
	 * @see EditorPart#doSave
	 */
	public void doSave(IProgressMonitor monitor)
	{
        IFile file = null;
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			objectOut.writeObject(diagram);
			objectOut.close();
			file = ((IFileEditorInput) getEditorInput()).getFile();
			file.setContents(new ByteArrayInputStream(out.toByteArray()), true, false, monitor);
			out.close();
		}
		catch (Exception e)
		{
            ExceptionDisplayDialog.openError(this.getSite().getShell(), "Error during save.", "Please check your resources.", e);
            return;
		}
		getCommandStack().markSaveLocation();
        // update the outline page...
        ERContentOutlinePage opage = (ERContentOutlinePage) getAdapter(IContentOutlinePage.class);
        if(opage != null)opage.refresh();
        // I want to show this in the QueryDefView
        // So users can filter the output
        String query = diagram.createQueryStatement();
        String ddl = diagram.createDDLStatement();
        Bookmark bookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject project = root.getProject(bookmark.getName());
        IFolder folder = project.getFolder("Queries");
        IFile queryFile = folder.getFile(file.getName() + ".sql");
        if(!query.equals(""))
        {
            try {
                if (!project.exists())project.create(null);
                if (!project.isOpen())project.open(null);
                if (!folder.exists())folder.create(IResource.NONE, false, null);
                if(queryFile.exists())queryFile.delete(true, null);
                if (!queryFile.exists()) {
//                    InputStream source = new StringBufferInputStream(query);
//                    queryFile.create(source, IResource.NONE, null);
                    FileOutputStream source=null;
                    try {
                        source = new FileOutputStream(queryFile.getName());
                    } catch (FileNotFoundException e) {
                    }
                    try {
                        source.write(query.getBytes());
                        source.flush();
                        source.close();
                    } catch (IOException e) {
                    }
                    FileInputStream dest = null;
                    try {
                        dest = new FileInputStream(queryFile.getName());
                    } catch (FileNotFoundException e) {
                    }
                    queryFile.create(dest, IResource.NONE, null);
                }
            } catch (CoreException e) {
                ExceptionDisplayDialog edd = new ExceptionDisplayDialog(this.getSite().getShell(), e);
                edd.open();
            }
            try {
                IDE.openEditor(page, queryFile, true);
            } catch (PartInitException e) {
                ExceptionDisplayDialog edd = new ExceptionDisplayDialog(this.getSite().getShell(), e);
                edd.open();
            }
        }
        if(!ddl.equals(""))
        {
            folder = project.getFolder("DDL");
            queryFile = folder.getFile(file.getName() + ".ddl");
            try {
                if (!project.exists())project.create(null);
                if (!project.isOpen())project.open(null);
                if (!folder.exists())folder.create(IResource.NONE, false, null);
                if(queryFile.exists())queryFile.delete(true, null);
                if (!queryFile.exists()) {
//                    InputStream source = new StringBufferInputStream(ddl);
//                    queryFile.create(source, IResource.NONE, null);
                    FileOutputStream source=null;
                    try {
                        source = new FileOutputStream(queryFile.getName());
                    } catch (FileNotFoundException e) {
                    }
                    try {
                        source.write(ddl.getBytes());
                        source.flush();
                        source.close();
                    } catch (IOException e) {
                    }
                    FileInputStream dest = null;
                    try {
                        dest = new FileInputStream(queryFile.getName());
                    } catch (FileNotFoundException e) {
                    }
                    queryFile.create(dest, IResource.NONE, null);
                }
            } catch (CoreException e) {
                ExceptionDisplayDialog edd = new ExceptionDisplayDialog(this.getSite().getShell(), e);
                edd.open();
            }
            try {
                IDE.openEditor(page, queryFile, true);
            } catch (PartInitException e) {
                ExceptionDisplayDialog edd = new ExceptionDisplayDialog(this.getSite().getShell(), e);
                edd.open();
            }
        }
	}
	
	/**
	 * Save as not allowed
	 */
	public void doSaveAs()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Save as not allowed
	 */
	public boolean isSaveAsAllowed()
	{
		return false;
	}

	protected KeyHandler getCommonKeyHandler()
	{

		KeyHandler sharedKeyHandler = new KeyHandler();
		sharedKeyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));

		return sharedKeyHandler;
	}
	/**
	 * Creates actions and registers them to the ActionRegistry.
	 */
	protected void createActions()
	{
		addStackAction(new UndoAction(this));
		addStackAction(new RedoAction(this));
        addEditPartAction(new DeleteAction((IWorkbenchPart) this));
        addEditPartAction(new MarkForOutputAction((IWorkbenchPart) this));
		addEditorAction(new SaveAction(this));
		addEditorAction(new PrintAction(this));
//        addRetargetAction(new ZoomInRetargetAction());
//        addRetargetAction(new ZoomOutRetargetAction());
	}
	/**
	 * Adds an <code>EditPart</code> action to this editor.
	 * 
	 * <p>
	 * <code>EditPart</code> actions are actions that depend and work on the
	 * selected <code>EditPart</code>s.
	 * 
	 * @param action
	 *            the <code>EditPart</code> action
	 */
	protected void addEditPartAction(SelectionAction action)
	{
		getActionRegistry().registerAction(action);
		editPartActionIDs.add(action.getId());
	}

	/**
	 * Adds an <code>CommandStack</code> action to this editor.
	 * 
	 * <p>
	 * <code>CommandStack</code> actions are actions that depend and work on
	 * the <code>CommandStack</code>.
	 * 
	 * @param action
	 *            the <code>CommandStack</code> action
	 */
	protected void addStackAction(StackAction action)
	{
		getActionRegistry().registerAction(action);
		stackActionIDs.add(action.getId());
	}

	/**
	 * Adds an editor action to this editor.
	 * 
	 * <p>
	 * <Editor actions are actions that depend and work on the editor.
	 * 
	 * @param action
	 *            the editor action
	 */
	protected void addEditorAction(WorkbenchPartAction action)
	{
		getActionRegistry().registerAction(action);
		editorActionIDs.add(action.getId());
	}

	/**
	 * Adds an action to this editor's <code>ActionRegistry</code>. (This is
	 * a helper method.)
	 * 
	 * @param action
	 *            the action to add.
	 */
	protected void addAction(IAction action)
	{
		getActionRegistry().registerAction(action);
	}

	/**
	 * Updates the specified actions.
	 * 
	 * @param actionIds
	 *            the list of ids of actions to update
	 */
	protected void updateActions(List actionIds)
	{
		for (Iterator ids = actionIds.iterator(); ids.hasNext();)
		{
			IAction action = getActionRegistry().getAction(ids.next());
			if (null != action && action instanceof UpdateAction)
				((UpdateAction) action).update();

		}
	}

	/**
	 * Returns the action registry of this editor.
	 * 
	 * @return the action registry
	 */
	protected ActionRegistry getActionRegistry()
	{
		if (actionRegistry == null)
			actionRegistry = new ActionRegistry();

		return actionRegistry;
	}
	/*
	 */
	protected void firePropertyChange(int propertyId)
	{
		super.firePropertyChange(propertyId);
		updateActions(editorActionIDs);
	}
}

