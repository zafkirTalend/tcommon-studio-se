package com.quantum.editors;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import com.quantum.ImageStore;
import com.quantum.model.ISQLSyntaxModel;
import com.quantum.sql.grammar.Sql92ParserTokenTypes;

/**
 * @author BC/JHvdV
 */
public class SQLContentOutlinePage extends ContentOutlinePage {
	
	class LabelProviderImpl implements ILabelProvider {
		public Image getImage(Object element) {
            if (element != null && element instanceof OutlineTable) {
                return ImageStore.getImage(ImageStore.TABLE);
            }else if (element != null && element instanceof OutlineDisplayColumn){ // least general first
                return ImageStore.getImage(ImageStore.COLUMNSELECTED);
            }else if (element != null && element instanceof OutlineColumn){
                return ImageStore.getImage(ImageStore.COLUMN);
            }else{
                return ImageStore.getImage(ImageStore.DELETE);
            }
		}

		public String getText(Object element) {
            if (element != null && element instanceof OutlineElement) {
                return ((OutlineElement) element).getName();
            }else if (element != null && element instanceof String) {
				return (String)element;
			}else{
				return element == null ? "" : element.toString();
			}
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}
	}
	
	class ContentProviderImpl implements ITreeContentProvider {
		
		public Object[] getElements(Object inputElement) {
			if(!(inputElement instanceof IFileEditorInput)) {
				return new Object[0];
			}
			String[] ts = editor.getConfig().getModel().getTables();
            ArrayList list = new ArrayList(ts.length);
            for(int i = 0; i < ts.length; i++)
            {
                list.add(new OutlineTable(ts[i]));
            }
            return list.toArray(new OutlineTable[list.size()]);
		}
		
		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof OutlineTable)
			{
				OutlineTable t = (OutlineTable)parentElement;
                String[] tcs = editor.getConfig().getModel().getTargetColumns(t.getName());
                ArrayList list = new ArrayList(tcs.length);
                for(int i = 0; i < tcs.length; i++)
                {
                    list.add(new OutlineDisplayColumn(tcs[i], t));
                }
                
				String[] cs = editor.getConfig().getModel().getColumns(t.getName());
                for(int i = 0; i < cs.length; i++)
                {
                    boolean bFound = false;
                    for(int j = 0; j < list.size() && bFound == false ; j++)
                    {
                        OutlineColumn c = (OutlineColumn) list.get(j);
                        if(c.getName().equalsIgnoreCase(cs[i]))bFound = true;
                    }
                    if(!bFound)list.add(new OutlineColumn(cs[i], t));
                }
                return list.toArray(new OutlineColumn[list.size()]);
			}
			return null;
		}

		public Object getParent(Object element) {
            if(element instanceof OutlineColumn) {
                return ((OutlineColumn)element).getParent();
            }
			return null;
		}

		public boolean hasChildren(Object element) {
            if(element instanceof OutlineColumn)
                return false;
            return true;
		}
	}

    class OutlineElement
    {
        String name;
        
	    OutlineElement(String name)
        {
	        this.name = name;
        }
        
        String getName()
        {
            return name;
        }
    }

    class OutlineTable extends OutlineElement
    {
        OutlineTable(String name)
        {
            super(name);
        }
    };

    class OutlineColumn extends OutlineElement
    {
        OutlineTable parent = null;
        OutlineColumn(String name, OutlineTable t)
        {
            super(name);
            parent = t;
        }
        
        OutlineTable getParent()
        {
            return parent;
        }
    }

    class OutlineDisplayColumn extends OutlineColumn
    {
        OutlineDisplayColumn(String name, OutlineTable t)
        {
            super(name, t);
        }
    }

	private IFileEditorInput editorInput;

    private SQLEditor editor;
    
    protected Object fInput;
	/**
	 * @param documentProvider
	 * @param editor
	 */
	public SQLContentOutlinePage(SQLEditor editor) {
		super();
		this.editor = editor;
	}
	
	public void refresh()
	{
		// this is called from the CheckSyntaxAction
		TreeViewer tree = getTreeViewer();
		if(tree != null)tree.refresh();
	}

	public void createControl(Composite parent) {
		super.createControl(parent);

		final TreeViewer treeViewer = getTreeViewer();
		
		treeViewer.setLabelProvider(new LabelProviderImpl());
		treeViewer.setContentProvider(new ContentProviderImpl());
        treeViewer.addSelectionChangedListener(this);
        if(editorInput != null)
        {
            treeViewer.setInput(editorInput);
        }
	}
	/**
	 * @param editorInput
	 */
	public void setInput(IEditorInput editorInput) {
		this.editorInput = (IFileEditorInput)editorInput;
//        getTreeViewer().setInput(this.editorInput);
    }

    public void selectionChanged(SelectionChangedEvent event)
    {
        super.selectionChanged(event);
        ISelection selection = event.getSelection();
        if (selection.isEmpty())
            editor.resetHighlightRange();
        else {
            OutlineElement element = (OutlineElement) ((IStructuredSelection) selection).getFirstElement();
            String find = element.getName();
            if(find.indexOf("(") != -1)
            {
                find = find.substring(0, find.indexOf("("));
            }
            // Highlight every instance of this text element
            ISQLSyntaxModel model = editor.getConfig().getModel();
            QToken[] qs = model.getTokens();

            try {
                model.getResource().deleteMarkers(NewSearchUI.SEARCH_MARKER, true, IResource.DEPTH_INFINITE);
                model.getResource().deleteMarkers(IMarker.BOOKMARK, true, IResource.DEPTH_INFINITE);
            } catch (CoreException e) {
                e.printStackTrace();
            }
            
            for(int i=0; i < qs.length; i++)
            {
                QToken q = qs[i];
                if(q.getType() != Sql92ParserTokenTypes.EOF)
                {
                    if(q.getText().equalsIgnoreCase(find))
                    {
                        // we have a match
                        try {
                            HashMap attributes= new HashMap(4);
                            // do we have the same table as source?
                            if(element instanceof OutlineColumn)
                            {
                                OutlineColumn oc = (OutlineColumn) element;
                                String tableName = oc.getParent().getName();
                                String aliasName = null;
                                if(tableName.indexOf("(") != -1)
                                {
                                    // alias(table)
                                    aliasName = tableName.substring(0, tableName.indexOf("("));
                                    tableName = tableName.substring(tableName.indexOf("("), tableName.length() -1 );
                                }
                                if(i>1)
                                {
                                    if(qs[i-1].getType() == Sql92ParserTokenTypes.DOT)
                                    {
                                        if(qs[i-2].getType() == Sql92ParserTokenTypes.ALIAS_IDENTIFIER ||
                                           qs[i-2].getType() == Sql92ParserTokenTypes.TABLE_IDENTIFIER ||
                                           qs[i-2].getType() == Sql92ParserTokenTypes.QuotedIdentifier ||
                                           qs[i-2].getType() == Sql92ParserTokenTypes.NonQuotedIdentifier)
                                        {
                                            if(qs[i-2].getText().equalsIgnoreCase(aliasName)) // or the alias of course.
                                            {
                                                // this is a good one
                                                attributes.put(IMarker.CHAR_START, new Integer(q.getOffset() - 1));
                                                attributes.put(IMarker.CHAR_END, new Integer(q.getOffset() - 1 + q.getText().length()));
                                                MarkerUtilities.createMarker(model.getResource(), attributes, NewSearchUI.SEARCH_MARKER);
                                            }else{
                                                boolean bFound = false;
                                                String[] aliases = model.getAliasesForTable(tableName);
                                                for(int j = 0; j < aliases.length; j++)
                                                {
                                                    if(aliases[j].equalsIgnoreCase(qs[i-2].getText()))
                                                    {
                                                        // this is a good one.
                                                        bFound = true;
                                                        attributes.put(IMarker.CHAR_START, new Integer(q.getOffset() - 1));
                                                        attributes.put(IMarker.CHAR_END, new Integer(q.getOffset() - 1 + q.getText().length()));
                                                        MarkerUtilities.createMarker(model.getResource(), attributes, NewSearchUI.SEARCH_MARKER);
                                                    }
                                                }
                                                if(!bFound)
                                                {
                                                    // this might be an oversight
                                                    attributes.put(IMarker.CHAR_START, new Integer(q.getOffset() - 1));
                                                    attributes.put(IMarker.CHAR_END, new Integer(q.getOffset() - 1 + q.getText().length()));
                                                    attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
                                                    MarkerUtilities.createMarker(model.getResource(), attributes, IMarker.BOOKMARK);
                                                }
                                            }
                                        }else{
                                            // the token before the dot has not been matched to a table?
//                                            System.out.println("ContentOutline: " + qs[i-2].getText() + " " + qs[i-2].getType());
                                        }
                                    }else{
                                        attributes.put(IMarker.CHAR_START, new Integer(q.getOffset() - 1));
                                        attributes.put(IMarker.CHAR_END, new Integer(q.getOffset() - 1 + q.getText().length()));
                                        MarkerUtilities.createMarker(model.getResource(), attributes, NewSearchUI.SEARCH_MARKER);
                                    }
                                }
                            }else{
                                attributes.put(IMarker.CHAR_START, new Integer(q.getOffset() - 1));
                                attributes.put(IMarker.CHAR_END, new Integer(q.getOffset() - 1 + q.getText().length()));
                                MarkerUtilities.createMarker(model.getResource(), attributes, NewSearchUI.SEARCH_MARKER);
                            }
                        } catch (PartInitException pie){
                            pie.printStackTrace(); //$NON-NLS-1$
                        } catch (CoreException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * Sets the input of the outline page
     */
    public void setInput(Object input)
    {
       fInput = input;
       update();
    }

    /**
     * Updates the outline page.
     */
    public void update()
    {
       TreeViewer viewer = getTreeViewer();

       if(viewer != null)
       {
          Control control = viewer.getControl();
          if(control != null && !control.isDisposed())
          {
             control.setRedraw(false);
             viewer.setInput(fInput);
             viewer.expandAll();
             control.setRedraw(true);
          }
       }
    }

	public void dispose() {
		super.dispose();
	}
}
