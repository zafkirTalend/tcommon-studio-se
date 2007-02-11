package com.quantum.view.subset;

import java.sql.SQLException;
import java.util.Vector;

import com.quantum.Messages;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.xml.XMLUtil;
import com.quantum.view.bookmark.ColumnNode;
import com.quantum.view.bookmark.TreeNode;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SubsetContentProvider implements ITreeContentProvider {
	private Vector subsets = new Vector();
	private static SubsetContentProvider instance = null;
	private boolean hasChanged = false;

   /**
    * Singleton accessor
    */
	public static synchronized SubsetContentProvider getInstance() {
		if (instance == null) {
			instance = new SubsetContentProvider();
		}
		return instance;
	}

	private SubsetContentProvider() {
	}

	public void exportXML(Element root) {
		System.out.println("Subsets: Saving to Element"); //$NON-NLS-1$
		Element subsetRoot = XMLUtil.createElementText(root,Messages.getString("ExportXMLAction.Subsets"), ""); //$NON-NLS-1$ //$NON-NLS-2$
		for (int i = 0; i < subsets.size(); i++) {
			SubsetNode current = (SubsetNode) subsets.get(i);
			current.exportXML(subsetRoot);
		}
	}
	
	/**
	 * Imports the data from an XML file to the tree. Does not set the tree to
	 * changed because it's usually invoked from the initial import routine.
	 * @param root
	 */
	public void importXML(Element root){
		System.out.println("Bookmarks: Loading from Element"); //$NON-NLS-1$
		Vector newSubsets = new Vector();
		NodeList nodes = root.getElementsByTagName(Messages.getString("ExportXMLAction.Subset")); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			SubsetNode subset = new SubsetNode();
			Element node = (Element) nodes.item(i);
			subset.importXML(node,true);
			newSubsets.add(subset);
		}
		subsets = newSubsets;
	}
	

	public Object[] getChildren(Object parentElement) {
		if (parentElement.equals(SubsetRoot.ROOT)) {
			return subsets.toArray();
		} else if (parentElement instanceof TreeNode) {
			TreeNode node = (TreeNode) parentElement;
			try {
				return node.getChildren();
			} catch (NotConnectedException e) {
			} catch (SQLException e) {
			}
		}
		return SubsetRoot.EMPTY_ARRAY;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public Object getParent(Object element) {
		if (element.equals(SubsetRoot.ROOT)) {
			return null;
		} else if (element instanceof TreeNode) {
			TreeNode node = (TreeNode) element;
			return node.getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element.equals(SubsetRoot.ROOT)) {
			return true;
		} else if (element instanceof TreeNode) {
			TreeNode node = (TreeNode) element;
			return node.hasChildren();
		}
		return false;
	}

    public void setChildren(SubsetNode b, Vector tables) {
    	b.setChildren(tables);
    }

	public void addSubset(SubsetNode b) {
		hasChanged = true;
		if (!subsets.contains(b)) {
			subsets.addElement(b);
		}
	}
	public void removeSubset(SubsetNode b) {
		hasChanged = true;
		if (subsets.contains(b)) {
			subsets.removeElement(b);
		}
	}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
	
	public void dispose() {
	}
	
	public boolean hasChanged() {
		return hasChanged;
	}
	
	public int getSize() {
		return subsets.size();
	}
	
	/**
	 * @param b
	 */
	public void setHasChanged(boolean b) {
		hasChanged = b;
	}

	/**
	 * @param column
	 */
	public void deleteColumn(ColumnNode column) {
        // TODO: reinstate this
//		TreeNode node = (TreeNode) column.getParent();
//		if (!(node instanceof HasMetaData)) return;
//		ObjectMetaData metadata = ((HasMetaData)node).getMetaData();
//		if (metadata == null) return;
//		metadata.dropColumn(column.getName());
//		setHasChanged(true);
//		
	}

	/**
	 * @param object
	 */
	public void deleteObject(ObjectNode object) {
		SubsetNode node = (SubsetNode) object.getParent();
		if (!(node instanceof SubsetNode)) return;
		node.remove(object);
		setHasChanged(true);
		
	}

}
