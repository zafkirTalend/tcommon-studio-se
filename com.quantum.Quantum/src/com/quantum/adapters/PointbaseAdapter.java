package com.quantum.adapters;

import java.util.HashMap;
import java.util.Map;


/**
 * @author BC
 */
public class PointbaseAdapter extends GenericAdapter {

	/**
	 * @param type
	 */
	protected PointbaseAdapter() {
		super(AdapterFactory.POINTBASE);
	}

	public Map getDefaultConnectionParameters() {
		Map map = new HashMap();
		map.put("port", "9093");
		map.put("hostname", "localhost");
		return map;
	}
}
