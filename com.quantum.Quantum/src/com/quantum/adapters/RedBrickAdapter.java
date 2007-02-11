package com.quantum.adapters;


/**
 * @author bcholmes
 */
public class RedBrickAdapter extends DatabaseAdapter {

	/**
	 * @param type
	 */
	protected RedBrickAdapter() {
		super(AdapterFactory.REDBRICK);
	}

	public String getShowTableQuery(String qualifier) {
		return "select name from rbw_tables where type = 'TABLE'";
	}

	public String getShowViewQuery(String qualifier) {
		return "select name from rbw_tables where type = 'VIEW'";
	}
    /**
     * @see com.quantum.adapters.DatabaseAdapter#getDefaultSchema(java.lang.String)
     */
    public String getDefaultSchema(String userid) {
        return null;
    }

}
