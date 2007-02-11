package com.quantum.adapters;

import java.util.HashMap;
import java.util.Map;


public class DB2Adapter extends DatabaseAdapter {
    
    private DB2StatementExplainer explainer = new DB2StatementExplainer();
    /**
	 * @param type
	 */
	protected DB2Adapter() {
		super(AdapterFactory.DB2);
	}
	public String getShowTableQuery(String qualifier) {
        return "SELECT tabschema, TABNAME FROM syscat.tables WHERE tabschema = '" + qualifier.toUpperCase() + "' AND TYPE='T'"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    public String getShowViewQuery(String qualifier) {
        return "SELECT tabschema, TABNAME FROM syscat.tables WHERE tabschema = '" + qualifier.toUpperCase() + "' AND TYPE='V'"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    public String getShowSequenceQuery(String qualifier) {
        return "SELECT seqschema, SEQNAME FROM sysibm.syssequences WHERE seqschema = '" + qualifier.toUpperCase() + "'"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    public String getNextValue(String sequence, String owner) {
		return "VALUES NEXTVAL FOR " + getQualifiedName(owner, sequence); //$NON-NLS-1$
    }
	public String getPrevValue(String sequence, String owner) {
		return "VALUES PREVVAL FOR " + getQualifiedName(owner, sequence); //$NON-NLS-1$
	}
	public Map getDefaultConnectionParameters() {
		Map map = new HashMap();
		// the standard driver doesn't use these values, but other drivers might...
		map.put("port", "50000");  
		map.put("hostname", "localhost");
		return map;
	}
	public String getCreateStatement(String schema, String viewName) {
		return "select TEXT from SYSCAT.VIEWS "
			+ "where VIEWSCHEMA = '" + schema.toUpperCase() + "'"
			+ "and VIEWNAME = '" + viewName.toUpperCase() + "'" ;
	}

    public StatementExplainer getStatementExplainer() {
        return this.explainer;
    }
}
