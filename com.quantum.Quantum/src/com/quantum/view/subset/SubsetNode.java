package com.quantum.view.subset;

import java.util.Collections;
import java.util.Vector;

import com.quantum.Messages;
import com.quantum.util.xml.XMLUtil;
import com.quantum.view.bookmark.TreeNode;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SubsetNode extends TreeNode {
	private String name = null;
    private Vector children = new Vector();

	public SubsetNode(SubsetNode param) {
        super(param.getParent());
		name = param.name;		
		children.addAll(param.children);
	}
	
    public SubsetNode() {
        super(SubsetRoot.ROOT);
    }

    public SubsetNode(String name) {
        super(SubsetRoot.ROOT);
    	this.name = name;
    }
    
	public void setName(String name) {
		this.name = name;
	}
	
    public String getName(){
    	return name;
    }

	public Object[] getChildren() {
		return children.toArray();
	}

	public boolean hasChildren() {
		if (children != null && children.size() > 0) {
			return true;
		}
		return false;
	}

	public void setChildren(Vector children) {
		for (int i = 0; i <  children.size(); i++) {
			Object obj = children.elementAt(i);
			isValid(obj);
		}
		Collections.sort(children);
		this.children = children;
	}
	
	public void isValid(Object child) {
		boolean valid = false;
		if (child instanceof ObjectNode ) {
			valid = true;
		}
		if (!valid) {
			throw new RuntimeException("Invalid SubsetNode child: " + child.getClass().getName()); //$NON-NLS-1$
		}
	}
	
	
	/**
	 * Finds a child of the SubsetNode with the said name 
	 * @param name
	 * @return the TreeNode found. null if none
	 */
	public TreeNode find(String name){
		for (int i = 0; i <  children.size(); i++) {
			Object obj = children.elementAt(i);
			if (obj instanceof TreeNode){
				TreeNode node = (TreeNode) obj;
				if (name.equals(node.getName())) return node;			
			}
		}
		return null;
	}
	
	public boolean add(ObjectNode object){
		if (children.indexOf(object) >= 0) return false;
		children.add(object);
		return true;
	}

	public boolean addReplace(ObjectNode object){
		children.remove(object);
		return add(object);
	}
	
	public boolean remove(ObjectNode object){
		return children.remove(object);
	}
	
	public void exportXML(Element root) {
		Element sub = XMLUtil.createElementText(root,Messages.getString("ExportXMLAction.Subset"), ""); //$NON-NLS-1$ //$NON-NLS-2$
		XMLUtil.createElementText(sub,Messages.getString("ExportXMLAction.SubsetName"), getName()); //$NON-NLS-1$
		for (int i = 0; i <  children.size(); i++) {
			Object obj = children.elementAt(i);
			if (obj instanceof ObjectNode){
//				((ObjectNode) obj).exportXML(sub);	
			}
		}
		
	}

	/**
	 * Imports a set of XML tags (parsed into a XMLDocument) into a Subset
	 * @param root	Document to get the data from
	 * @param replace	True if you want to replace already-existing elements with the same name, false if not
	 */
	public void importXML(Element root, boolean replace) {
		if (replace) {
			String name = XMLUtil.getElementText(root, Messages.getString("ExportXMLAction.SubsetName")); //$NON-NLS-1$
			if (name == "") return;	 //$NON-NLS-1$
			setName(name);
		}
		//importElementXML(root, replace, Messages.getString("ExportXMLAction.Table")); //$NON-NLS-1$
		importElementXML(root, replace, "table"); //$NON-NLS-1$
		importElementXML(root, replace, "view"); //$NON-NLS-1$

	}


	/**
	 * 
	 * Imports one type of element from a XMLDocument (possibly a part of one) into a Subset
	 * @param root Document to get the data from
	 * @param replace True if you want to replace already-existing elements with the same name, false if not
	 * @param type The type of element to import, has to correspond with the XML tag
	 */
	private void importElementXML(Element root, boolean replace, String type) {
		// We get all the tags named as the type
		NodeList tables = root.getElementsByTagName(type); 
		for (int i = 0; i < tables.getLength(); i++) {
			Element table = (Element) tables.item(i);
//			ObjectNode objectNode = ObjectNode.importXML(table, this);
//			if (replace)addReplace(objectNode);
//			else add(objectNode);	
		}
	}

    /* (non-Javadoc)
     * @see com.quantum.view.bookmark.TreeNode#getImageName()
     */
    protected String getImageName() {
        return "subset.gif";
    }

    /* (non-Javadoc)
     * @see com.quantum.view.bookmark.TreeNode#initializeChildren()
     */
    protected void initializeChildren() {
    }

}
