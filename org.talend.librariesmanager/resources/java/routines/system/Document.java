// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package routines.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Node;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class Document {

    private org.dom4j.Document doc = null;

    public void setDocument(org.dom4j.Document doc) {
        this.doc = doc;
    }

    public org.dom4j.Document getDocument() {
        return this.doc;
    }
	
	public String toString() {
		if(this.doc==null)
			return null;
			
		return this.doc.asXML();
	}
	
	/**
	 * lookup document action
	 * @param doc
	 * @param loopPath
	 * @param lookupInfo
	 * @param xpathOfResults
	 * @return
	 */
	public List<Map<String,Object>> LookupDocument(String loopPath, Map<String,Object> lookupInfo,List<String> xpathOfResults) {
		if(doc == null || lookupInfo == null) {
			return null;
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		String loopXpath = loopPath.substring(loopPath.indexOf(":") + 1);
		org.dom4j.Document document = doc.getDocument();
		org.dom4j.XPath xpathObjectForDoc = document.createXPath(loopXpath);
		java.util.List<org.dom4j.tree.AbstractNode> nodes = xpathObjectForDoc.selectNodes(document);
		for(org.dom4j.tree.AbstractNode node : nodes) {
			boolean reject = false;
			//lookup action
			for(String path : lookupInfo.keySet()) {
				Object lookupValue = lookupInfo.get(path);
				String xpath = path.substring(path.indexOf(":") + 1);
				org.dom4j.XPath xpathObjectForLookup = node.createXPath(getXPathByLoopXPath(loopXpath,xpath));
				Node nodeOfLookup = xpathObjectForLookup.selectSingleNode(node);
				if(nodeOfLookup==null || lookupValue==null || !lookupValue.equals(nodeOfLookup.getText())) {
					reject = true;
					break;
				}
			}
			//generate result action
			if(reject) {
				//do nothing
			} else {
				Map<String,Object> row = new HashMap<String,Object>();
				for(String path : xpathOfResults) {
					String xpath = path.substring(path.indexOf(":") + 1);
					org.dom4j.XPath xpathObjectForResult = node.createXPath(getXPathByLoopXPath(loopXpath,xpath));
					Node nodeOfResult = xpathObjectForResult.selectSingleNode(node);
					row.put(path, nodeOfResult == null ? null : nodeOfResult.getText());
					result.add(row);
				}
			}
			
		}
		return result;
	}
	
	private String getXPathByLoopXPath(String loopXpath,String xpath) {
		String result = "";
		loopXpath = loopXpath + "/";
		result = xpath.replace(loopXpath, "");
		return result;
	}
	
	public static void main(String[] args) {
		Document doc = new Document();
		System.out.println(doc.getXPathByLoopXPath("/root/students/student","/root/students/student/name"));
		System.out.println(doc.getXPathByLoopXPath("/root/students/student","/root/info/school"));
	}
	
}
