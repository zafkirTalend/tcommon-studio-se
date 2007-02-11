package com.quantum.view.subset;

import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.QuantumPlugin;
import com.quantum.actions.CustomCopyAction;
import com.quantum.actions.DeleteColumnAction;
import com.quantum.actions.DeleteObjectAction;
import com.quantum.actions.DeleteSubsetAction;
import com.quantum.actions.NewSubsetAction;
import com.quantum.actions.ViewTableAction;
import com.quantum.view.bookmark.ColumnNode;
import com.quantum.view.bookmark.TreeNode;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author panic
 *
 * View for subsets.
 * TODO: Reimplement it following the new structure. Now it's never called as
 * 	it's no longer an extension of the Eclipse views.
 * */
public class SubsetView extends ViewPart {
	
	private SubsetContentProvider provider = SubsetContentProvider.getInstance();
	private NewSubsetAction newSubsetAction;
	private DeleteSubsetAction deleteSubsetAction;
	private DeleteObjectAction deleteObjectAction;
	private DeleteColumnAction deleteColumnAction;
	private CustomCopyAction customCopyAction;

	private ViewTableAction viewTableAction;
	//private ExportXMLAction exportXMLAction;
	private static SubsetView instance = null;
	private TreeViewer treeViewer;
	private Label status;
	public synchronized static SubsetView getInstance() {
		return instance;
	}
	/**
	 * Returns the current selected object in the tree. If it's a multiple selection, return the first.
	 * @return
	 */
	public Object getCurrent() {
		if (treeViewer == null) return null;
		return ((StructuredSelection) treeViewer.getSelection())
					.getFirstElement();
	}
	/**
	 * Returns the current selected objects in the tree, in the form of a StructuredSelection.
	 * @return
	 */
	public StructuredSelection getSelection() {
		if (treeViewer == null) return null;
		return ((StructuredSelection) treeViewer.getSelection());
	}
	
	/** 
	 * Navigates the tree to get the current subset (root) of the selected element.
	 * If it's a multiple selection, it takes the first one.
	 * @return
	 */
	public SubsetNode getCurrentSubset() {
		TreeNode current = (TreeNode) getCurrent();
		
		return getRoot(current);
	}

	/**
	 * Navigates a given TreeNode till finds a SubsetNode
	 * @param node
	 * @return
	 */
	private static SubsetNode getRoot(TreeNode node){
		while (!( node instanceof SubsetNode))
		{
			node = node.getParent();
		}
		return (SubsetNode) node;

	}

 
	/**
	 * Deletes the current node (first selected) in the tree
	 */
	public void deleteCurrent() {
		provider.removeSubset(getCurrentSubset());
		treeViewer.refresh();
	}
	
	public void refreshSubsetData(){
		//if (treeViewer == null) return;
		//SubsetContentProvider provider = (SubsetContentProvider) treeViewer.getContentProvider();
		//Object[] objects = provider.getElements(Root.ROOT);
		//for (int i = 0; i < objects.length; i++) {
			//SubsetNode current = (SubsetNode) objects[i];
			
		//}
	}
	
	public void expandCurrent(SubsetNode node) {
		treeViewer.setExpandedState(node, true);
		treeViewer.refresh(node, false);
	}
	public void refresh() {
		treeViewer.refresh();
	}
	public void disconnect() {
	}
	
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
		instance = this;
		initActions();
		Composite main = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		main.setLayout(layout);

	
		treeViewer = new TreeViewer(main);
		treeViewer.setContentProvider(provider);
		treeViewer.setLabelProvider(new SubsetLabelProvider());
		treeViewer.setInput(SubsetRoot.ROOT);
		MenuManager manager = new MenuManager();
		manager.setRemoveAllWhenShown(true);
		Menu fTextContextMenu =
			manager.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(fTextContextMenu);
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				Object sel = getCurrent();
				if (sel instanceof ObjectNode) {
					viewTableAction.run();
				}
			}
		});

		manager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				Object sel = getCurrent();
				if (sel instanceof SubsetNode) {
					mgr.add(deleteSubsetAction);
					deleteSubsetAction.setText(Messages.getString("SubsetView.Delete")); //$NON-NLS-1$
					deleteSubsetAction.setImageDescriptor(
							ImageStore.getImageDescriptor(ImageStore.DELETE));
					mgr.add(pasteAction);
					pasteAction.setText(Messages.getString("SubsetView.Paste")); //$NON-NLS-1$
					pasteAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.PASTE));
//					mgr.add(exportXMLAction);
//					exportXMLAction.setText(Messages.getString("bookmarkview.exportXML")); //$NON-NLS-1$
//					exportXMLAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.XML));
                
				} else 	if (sel instanceof ObjectNode) {
					mgr.add(deleteObjectAction);
					deleteObjectAction.setText(Messages.getString("SubsetView.Delete")); //$NON-NLS-1$
					deleteObjectAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.DELETE));
					mgr.add(viewTableAction);
					viewTableAction.setText(Messages.getString("bookmarkview.viewTable")); //$NON-NLS-1$
					viewTableAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.OPEN_TABLE));
//					mgr.add(exportXMLAction);
//					exportXMLAction.setText(Messages.getString("bookmarkview.exportXML")); //$NON-NLS-1$
//					exportXMLAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.XML));
                    customCopyAction.selectionChanged(
                        (IStructuredSelection) treeViewer.getSelection());
					mgr.add(customCopyAction);
				} else if (sel instanceof ColumnNode){
					mgr.add(deleteColumnAction);
					deleteColumnAction.setText(Messages.getString("SubsetView.Delete")); //$NON-NLS-1$
					deleteColumnAction.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.DELETE));
                    customCopyAction.selectionChanged(
                        (IStructuredSelection) treeViewer.getSelection());
					mgr.add(customCopyAction);
                				
				} else {
					mgr.add(newSubsetAction);
				}
			}
		});

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		treeViewer.getControl().setLayoutData(gridData);
		status = new Label(main, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		status.setLayoutData(gridData);
		
		IActionBars bars = getViewSite().getActionBars();
		bars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
		bars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteSubsetAction);

		IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();
		toolBar.add(newSubsetAction);
		
		status.setText(Messages.getString("bookmarkview.done")); //$NON-NLS-1$
	}
	public void initActions() {
		newSubsetAction = new NewSubsetAction();
		newSubsetAction.setText("New Subset"); //$NON-NLS-1$
		newSubsetAction.setToolTipText(Messages.getString("SubsetView.CreatesANewEmptySubset")); //$NON-NLS-1$
		newSubsetAction.setImageDescriptor(
				ImageStore.getImageDescriptor(ImageStore.SUBSET));
		newSubsetAction.init(this);
		deleteColumnAction = new DeleteColumnAction();
		deleteColumnAction.setText("Delete Column"); //$NON-NLS-1$
		deleteColumnAction.setToolTipText(Messages.getString("SubsetView.DeletesTheSelectedColumns")); //$NON-NLS-1$
		deleteColumnAction.setImageDescriptor(
				ImageStore.getImageDescriptor(ImageStore.DELETE));
		deleteColumnAction.init(this);
		deleteObjectAction = new DeleteObjectAction();
		deleteObjectAction.setText("Delete Object"); //$NON-NLS-1$
		deleteObjectAction.setToolTipText(Messages.getString("SubsetView.DeletesTheSelectedObject")); //$NON-NLS-1$
		deleteObjectAction.setImageDescriptor(
				ImageStore.getImageDescriptor(ImageStore.DELETE));
		deleteObjectAction.init(this);
		deleteSubsetAction = new DeleteSubsetAction();
		deleteSubsetAction.setText("Delete Subset"); //$NON-NLS-1$
		deleteSubsetAction.setToolTipText(Messages.getString("SubsetView.DeletesTheSelectedSubset")); //$NON-NLS-1$
		deleteSubsetAction.setImageDescriptor(
				ImageStore.getImageDescriptor(ImageStore.DELETE));
		deleteSubsetAction.init(this);
//		viewTableAction = new ViewTableAction();
//		viewTableAction.setText(Messages.getString("bookmarkview.viewTable")); //$NON-NLS-1$
//		viewTableAction.setImageDescriptor(
//			QuantumPlugin.getImageDescriptor("table.gif")); //$NON-NLS-1$
//		viewTableAction.init(this);
//		exportXMLAction = new ExportXMLAction();
//		exportXMLAction.setText(Messages.getString("bookmarkview.exportXML")); //$NON-NLS-1$
//		exportXMLAction.setImageDescriptor(
//				ImageStore.getImageDescriptor(ImageStore.XML));
//		exportXMLAction.init(this);

		this.customCopyAction = new CustomCopyAction(1); // 1 is unused, just in case more custom copies are defined        
		this.customCopyAction.setText(Messages.getString("bookmarkview.customCopyAction")); //$NON-NLS-1$
		this.customCopyAction.setImageDescriptor(
				ImageStore.getImageDescriptor(ImageStore.COPY));


		
	}
	public void setFocus() {
	}
	
	private Action pasteAction = new Action() {
		public void run() {
			Object sel = getCurrent();
			if (sel == null) return;
			if (!(sel instanceof SubsetNode)) return;
			
			SubsetNode subset = (SubsetNode) sel;
			
			TextTransfer transfer = TextTransfer.getInstance();
			String xmlMetaData = (String) QuantumPlugin.getDefault().getSysClip().getContents(transfer);
			StringReader text = new StringReader(xmlMetaData);
			InputSource source = new InputSource(text);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 			DocumentBuilder parser;
            Document doc; 
			try {
				parser = factory.newDocumentBuilder();
				doc = parser.parse(source);
 			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				return;
			} catch (SAXException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
 			Element root = doc.getDocumentElement();
 			subset.importXML(root, false);
			treeViewer.refresh();
			provider.setHasChanged(true);

		}
	};
	
	public void addNewSubset(SubsetNode subset) {
		provider.addSubset(subset);
		treeViewer.refresh();
	}
	public void setStatus(String text) {
		status.setText(text);
	}
	public void dispose(){
		super.dispose();
	}

	/**
	 * Returs a Vector with all the elements of the treeViewer
	 * @return
	 */
	public Vector getElements(){
		Vector result = new Vector();
		if (treeViewer == null) return result;
		SubsetContentProvider provider = (SubsetContentProvider) treeViewer.getContentProvider();
		Object[] objects = provider.getElements(SubsetRoot.ROOT);
		for (int i = 0; i < objects.length; i++) {
			SubsetNode current = (SubsetNode) objects[i];
			result.add(current);
		}
		return result;
	}
	
	public void deleteColumn (ColumnNode column){
		provider.deleteColumn(column);
		treeViewer.refresh();
	}

	public void deleteObject (ObjectNode object){
		provider.deleteObject(object);
		treeViewer.refresh();
	}


}
