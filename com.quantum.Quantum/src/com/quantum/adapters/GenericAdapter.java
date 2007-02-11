package com.quantum.adapters;




public class GenericAdapter extends DatabaseAdapter {

	/**
	 * @param type
	 */
	protected GenericAdapter(String type) {
		super(type);
	}
	public String getShowTableQuery(String qualifier) {
		return null;
	}
    public String getShowViewQuery(String qualifier) {
		return null;
    }
    
    public String getShowSequenceQuery(String qualifier) {
        return null;
    }
}
