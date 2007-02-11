package com.quantum.adapters;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BC Holmes
 */
public class InformixAdapter extends GenericAdapter {

	/**
	 * @param type
	 */
	protected InformixAdapter() {
		super(AdapterFactory.INFORMIX);
	}

	/**
	 * Get the default connection properties for Informix.  These defaults assume that 
	 * the database is located on the user's local machine.  Informix doesn't allow 
	 * for "localhost".
	 */
	public Map getDefaultConnectionParameters() {
		Map map = new HashMap();
		map.put("hostname", getHostName());
		map.put("informixserver", "ol_" + getHostName());
		map.put("port", "1526");
		return map;
	}

	/**
	 * @return
	 */
	private String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "localhost";
		}
	}
}
