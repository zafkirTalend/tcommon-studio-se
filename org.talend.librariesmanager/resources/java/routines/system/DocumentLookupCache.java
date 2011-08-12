package routines.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * now only for unique match result
 * for tXMLMap
 * @author Administrator
 *
 */
public class DocumentLookupCache {
	
	private Map<String,Object> currentValue;
	
	//for unique result
	private Map<List<Object>,Map<String,Object>> keyToValue = new HashMap<List<Object>,Map<String,Object>>();
	
	//for more than one result
	//List<Map<String,Object>> valueList = new  ArrayList<Map<String,Object>>();
	
	//for more than on result
	//Map<List<Object>,List<Map<String,Object>>> keyToValues = new HashMap<List<Object>,List<Map<String,Object>>>();

	private boolean hasNext = false;
	
	public void put(List<Object> key,Map<String,Object> value) {
		keyToValue.put(key,value);
	}

	public void lookup(List<Object> key) {
		currentValue = keyToValue.get(key);
		hasNext = currentValue != null ? true : false;
	}
	
	public boolean hasNext() {
		return hasNext;
	}
	
	public Map<String,Object> next() {
		return currentValue;
	}
	
}
