package com.quantum.adapters;


public class AdabasDAdapter extends DatabaseAdapter {
    /**
	 * @param type
	 */
	protected AdabasDAdapter() {
		super(AdapterFactory.ADABASD);
	}
	public String getShowTableQuery(String qualifier) {
        return "SELECT OWNER, TABLENAME FROM TABLES WHERE OWNER = '" + qualifier.toUpperCase() + "'"; //$NON-NLS-1$
    }
    public String getShowViewQuery(String qualifier) {
        return "SELECT OWNER, VIEWNAME FROM VIEWS WHERE OWNER = '" + qualifier.toUpperCase() + "'"; //$NON-NLS-1$
    }
}
