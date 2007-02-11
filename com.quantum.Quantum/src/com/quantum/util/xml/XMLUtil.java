/*
 * Created on 15/04/2003
 *
 */
package com.quantum.util.xml;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.quantum.util.StringMatrix;
import com.sun.org.apache.xpath.internal.NodeSet;


/**
 * @author Julen
 * Handles interface between an ObjectMetaData and XML storage.
 */
public class XMLUtil {


	/**
	 * Adds a StringMatrix to an XML document
	 * @param matrix The StringMatrix to add
	 * @param doc The XmlDocument to which it will be added
	 * @param root An element of the previous document under which the info will be added
	 * @param sub A key under which each row of the StringMatrix will be added
	 */
	public static void stringMatrixToXML(StringMatrix matrix, Document doc, Element root, String sub) {
		for (int i = 0; i < matrix.size(); i++) {
			Element localRoot = (Element) root.appendChild(doc.createElement(sub));
			for (int j = 0; j < matrix.getNumColumns(); j++) {
				String key = matrix.getHeaderColumn(j);
				Element tableName = (Element) localRoot.appendChild(doc.createElement(key));
				String value = matrix.get(key, i);
				if (value != null)
					tableName.appendChild(doc.createTextNode(value));
			}
		}
	}

	/**
	* Fills a StringMatrix with the data from XML, usually extracted with the StringMatrixToXML function
	* @param matrix The matrix to fill up, usually empty.
	* @param root The Element with all the data in XML DOM
	* @param sub The String to select the Nodes which interest us. Only the selected nodes will be added
	* to the StringMatrix.
	*/
	public static void xmlToStringMatrix(StringMatrix matrix, Element root, String sub) {
		NodeList columns = root.getElementsByTagName(sub);
		for (int i = 0; i < columns.getLength(); i++) {
			Node column = columns.item(i);
			NodeList columnList = column.getChildNodes();
			for (int j = 0; j < columnList.getLength(); j++) {
				Node node = columnList.item(j);
				String header = node.getNodeName();
				if (header.equals("#text")) //$NON-NLS-1$
					continue;
				String value = null;
				if (node != null && node.hasChildNodes()) {
					Node valueNode = node.getFirstChild();
					if (valueNode instanceof Text) {
						value = valueNode.getNodeValue();
					}
				}
				if (!matrix.contains(header))
					matrix.addHeader(header);
				matrix.addAt(header, value, i);
			}
		}
	}
	/**
	 * Creates a new Element with a text value
	 * @param root
	 * @param key
	 * @param value
	 * @return
	 */
	public static Element createElementText(Element root, String key, String value){
		//	get the XmlDocument for use as a factory
		Document doc = root.getOwnerDocument();
		Element newElement = doc.createElement(key);
	
		root.appendChild(newElement);
		if (value != null && value.length() > 0)
		{
		   Text valueText = doc.createTextNode(value);
		   newElement.appendChild(valueText);
		}
		return newElement;
	}
	/**
	 * Creates a new Element with a CDATA section
	 * @param root
	 * @param key
	 * @param value
	 * @return
	 */
	public static Element createCDATA(Element root, String key, String value){
		//	get the XmlDocument for use as a factory
		Document doc = root.getOwnerDocument();
		Element newElement = doc.createElement(key);
	
		root.appendChild(newElement);
		if (value != null && value.length() > 0)
		{
		   Text valueText = doc.createCDATASection(value);
		   newElement.appendChild(valueText);
		}
		return newElement;
	}
      
	public static String getElementText(Element root, String key){
		return getElementText(root, key, "");
	}
	/**
	 * gets the text value from an element or a child of it. 
	 * If several possible results, returns the first one
	 * 
	 * @param root element root
	 * @param key key to search
	 * @param defValue default string value if not found
	 * @return attribute value, default value if not found
	 */
	public static String getElementText(Element root, String key, String defValue){
	//	get the XmlDocument for use as a factory
		String value = defValue;
		if (root.getNodeName().equals(key)){
			value = extractText(root, defValue);
		} else {
			NodeList children = root.getElementsByTagName(key);
			if (children.getLength() > 0) {
				Element column = (Element) children.item(0);
				value = extractText(column, defValue);
			}
		}
		return value;
	}

	/**
	 * gets the attribute value from an element or a child of it
	 * If several possible results, returns the first one
	 * 
	 * @param root element root
	 * @param key key to search
	 * @param attribute attribute to search in that key
	 * @return attribute value, empty string if not found
	 */
	public static String getElementAtribute(Element root, String key, String attribute){
		String value = "";
		if (root.getNodeName().equals(key)){
			value = root.getAttribute(attribute);
		} else {
			NodeList children = root.getElementsByTagName(key);
			if (children.getLength() > 0) {
				Element column = (Element) children.item(0);
				value = column.getAttribute(attribute);
			}
		}
		return value;
	}
	public static String extractText(Element node, String defValue){
		String value = defValue;
		if (node != null && node.hasChildNodes()) {
			Node valueNode = node.getFirstChild();
			if (valueNode instanceof Text) {
				value = valueNode.getNodeValue();
			}
		}
		return value;
	}
	
	/**
	 * Gets a Vector with the String values of the keys
	 *  that are children of 'root' and that match 'key'
	 * @param root
	 * @param key
	 * @return
	 */
	public static Vector getVectorText(Element root, String key) {
		Vector result = new Vector();
		NodeList children = root.getElementsByTagName(key);
		for (int i = 0; i < children.getLength(); i++) {
			Element column = (Element) children.item(i);
			result.add(extractText(column, ""));
		}
		return result;
	}
	
	public static NodeList selectNodesOnAttributeValue(NodeList nodes, String attribute, String value){
		NodeSet set = new NodeSet();
		set.setShouldCacheNodes(true);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) node;
				if (element.getAttribute(attribute).equals(value)) {
					set.addElement(element);
				}
			}
		}
		return set;
	}
}
