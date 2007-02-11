/*
 * Created on 20/02/2005
 *
 */
package com.quantum.model.xml;

import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeFilter;

/**
 * @author Julen
 *
 */
public class XMLMetadataIterator implements Iterator{
	
	private String[] acceptedNames = null;
	
	class MetadataFilter implements NodeFilter
	{
		public short acceptNode(Node n) {
			if (n instanceof Element) {
				Element e = (Element)n;
				for (int i = 0; i < acceptedNames.length; i++) {
					String name = acceptedNames[i];
					if (e.getNodeName().equals(name)) {
						return FILTER_ACCEPT;
					}
				}
				return FILTER_SKIP;
			}
			return FILTER_SKIP;
		}
	}
	
	NodeFilter filter;
	NodeList list;
	int position = 0;
	int lastPosition = 0;
	
	/**
	 * @param doc	The document to iterate over
	 * @param type	The type of filter, -1 if all nodes are to be iterated, 0 if only tables and views.
	 * 				1 if tables, views and sequences 
	 * 				The options will be perhaps extended
	 */
	public XMLMetadataIterator(Document doc, int type) { 
		
		this.list = doc.getDocumentElement().getChildNodes();
		this.position = 0;
		if (type == 0) {
			acceptedNames = new String[] {"table", "view" };
		} else {
			acceptedNames = new String[] {"table", "view", "sequence" };
		}
			
		if (type < 0)
			this.filter = null;
		else
			this.filter= new MetadataFilter();
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		if (position >= list.getLength())
			return false;
		else return (next(false) != null);
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public Object next() {
		return next(true);
	}
	
	private Object next(boolean update) {
		Node node = null;
		if (position >= list.getLength()) 
			return null;
		if (this.filter == null) {
			node = list.item(position);
			if (update) position++;
			return node;
		} else {
			int tempPosition = position;
			boolean accepted = false;
			do {
				node = list.item(tempPosition);
				accepted = (this.filter.acceptNode(node) == NodeFilter.FILTER_ACCEPT);
				tempPosition++;
			} while ( tempPosition < list.getLength() && !accepted );
			
			if (update)
				this.position = tempPosition;
			
			if (!accepted) {
				return null;
			} else {
				return node;
			}
		}
	}

	/*  
	 * Does nothing, we cannot remove
	 */
	public void remove() {
		// do nothing, we cannot remove
	}

}
