package routines.system;

import java.util.Map;

import org.dom4j.Element;

/**
 * dom4j Document helper
 * @author Administrator
 *
 */
public class DocumentHelper {
	

	/**
	 * 
	 * @param element : current element
	 * @return the path of current element
	 */
	public	static String getPath(Element element,Map<String,String> defaultNamespaceUriTOPrefix) {
		Element parent = element.getParent();
		
		if (parent == null) {
            return "/" + getXPathNameStep(element,defaultNamespaceUriTOPrefix);
        }
		
		return getPath(parent,defaultNamespaceUriTOPrefix) + "/" + getXPathNameStep(element,defaultNamespaceUriTOPrefix);
	}
	
	
	private	static String getXPathNameStep(Element element,Map<String,String> defaultNamespaceUriTOPrefix) {
		String uri = element.getNamespaceURI();

        if ((uri == null) || (uri.length() == 0)) {
            return element.getName();
        }

        String prefix = element.getNamespacePrefix();

        if ((prefix == null) || (prefix.length() == 0)) {
        	String defaultNamespacePrefix = defaultNamespaceUriTOPrefix.get(uri);
            return defaultNamespacePrefix + ":" + element.getName();
        }

        return element.getQName().getQualifiedName();
	}
	
	
}
