package com.quantum.editors.graphical;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import com.quantum.ImageStore;
import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Table;
import com.quantum.editors.graphical.parts.ColumnPart;
import com.quantum.editors.graphical.parts.EntityRelationDiagramEditPart;
import com.quantum.editors.graphical.parts.TablePart;

/**
 * @author JHvdV
 */
public class ERContentOutlinePage extends ContentOutlinePage {
	
	class LabelProviderImpl implements ILabelProvider {
		public Image getImage(Object element) {
            if (element != null && element instanceof OutlineTable) {
                return ImageStore.getImage(ImageStore.TABLE);
            }else if (element != null && element instanceof OutlineDisplayColumn){ // least general first
                Column c = ((OutlineDisplayColumn)element).getColumn();
                if(c.isPrimaryKey()){
                    return ImageStore.getImage(ImageStore.KEYCOLUMNSELECTED);
                }else if(c.isForeignKey()){
                    return ImageStore.getImage(ImageStore.FOREIGNKEYSELECTED);
                }else{
                    return ImageStore.getImage(ImageStore.COLUMNSELECTED);
                }
            }else if (element != null && element instanceof OutlineColumn){
                Column c = ((OutlineColumn)element).getColumn();
                if(c.isPrimaryKey()){
                    return ImageStore.getImage(ImageStore.KEYCOLUMN);
                }else if(c.isForeignKey()){
                    return ImageStore.getImage(ImageStore.FOREIGNKEY);
                }else{
                    return ImageStore.getImage(ImageStore.COLUMN);
                }
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
            List ts = editor.getModel().getTables();
            ArrayList list = new ArrayList(ts.size());
            for(int i = 0; i < ts.size(); i++)
            {
                Table t = (Table) ts.get(i);
                list.add(new OutlineTable(t));
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
                List tcs = t.getTable().getModelColumns();
                ArrayList list = new ArrayList(tcs.size());
                for(int i = 0; i < tcs.size(); i++)
                {
                    Column c = (Column) tcs.get(i);
                    if(c.isSelected()){
                        list.add(new OutlineDisplayColumn(c, t));
                    }else if(c.isForeignKey() || c.isPrimaryKey()){
                        list.add(new OutlineColumn(c, t));
                    }
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
        Table t;
        
        OutlineTable(Table t)
        {
            super(t.getName());
            this.t = t;
        }
        
        public Table getTable(){
            return t;
        }
    };

    class OutlineColumn extends OutlineElement
    {
        OutlineTable parent = null;
        Column c;
        
        OutlineColumn(Column c, OutlineTable t)
        {
            super(c.getName());
            this.c = c;
            parent = t;
        }
        
        public OutlineTable getParent()
        {
            return parent;
        }
        
        public Column getColumn(){
            return c;
        }
    }

    class OutlineDisplayColumn extends OutlineColumn
    {
        OutlineDisplayColumn(Column c, OutlineTable t)
        {
            super(c, t);
        }
        
        public Column getColumn(){
            return super.getColumn();
        }
    }

	private IFileEditorInput editorInput;

    private EntityRelationEditor editor;
    
    protected Object fInput;
	/**
	 * @param documentProvider
	 * @param editor
	 */
	public ERContentOutlinePage(EntityRelationEditor editor) {
		super();
		this.editor = editor;
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
    }

    public void selectionChanged(SelectionChangedEvent event)
    {
        super.selectionChanged(event);
        ISelection selection = event.getSelection();
        if (!selection.isEmpty()){
            if(((IStructuredSelection) selection).getFirstElement() instanceof OutlineElement){
                // put the focus on the diagram window
                editor.setFocus();
                OutlineElement element = (OutlineElement) ((IStructuredSelection) selection).getFirstElement();
                GraphicalViewer v = (GraphicalViewer) editor.getGraphicalViewer();
                v.deselectAll(); // does not seem to work....Yes, but only if you use v.select() to select the stuff
                List l = v.getRootEditPart().getChildren();
                EntityRelationDiagramEditPart erdep = (EntityRelationDiagramEditPart) l.get(0);
                l = erdep.getChildren();
                if(element instanceof OutlineTable){
                    Table t = ((OutlineTable)element).getTable();
                    // we would want to select this in the diagram
                    for(int i=0; i<l.size(); i++){
                        TablePart tp = (TablePart) l.get(i);
                        Table tToSelect = (Table) tp.getModel();
                        if(tToSelect.getTableName().equals(t.getTableName())){
                            // alias...
                            // this is the element we want to select
                            v.select(tp);
                        }
                    }
                }else if(element instanceof OutlineColumn || element instanceof OutlineDisplayColumn){
                    OutlineColumn oc = (OutlineColumn)element; 
                    Column c = oc.getColumn();
                    // our list contains the tables. We now need to loop over those
                    // to find the columns
                    for(int i=0; i<l.size(); i++){
                        TablePart tp = (TablePart) l.get(i);
                        Table toSelect = (Table) tp.getModel();
                        Table t = (Table) oc.getParent().getTable();
                        if(toSelect.getTableName().equals(t.getTableName())){
                            List cs = tp.getChildren();
                            for(int j=0; j<cs.size(); j++){
                                ColumnPart cp = (ColumnPart) cs.get(j);
                                Column cToSelect = (Column) cp.getModel();
                                if(cToSelect.getName().equals(c.getName())){
                                    v.select(cp);
                                }
                            }
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
    
    public void refresh(){
        // this is called from the saving of the diagram
        TreeViewer tree = getTreeViewer();
        if(tree != null)tree.refresh();
    }
}
