package com.quantum.adapters;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author G&aacute;bor Lipt&aacute;k
 */
public class DB2OS390Adapter extends DatabaseAdapter {
    
    private DB2OS390StatementExplainer explainer = new DB2OS390StatementExplainer();

    protected DB2OS390Adapter() {
		super(AdapterFactory.DB2OS390);
	}
	public String getShowTableQuery(String qualifier) {
        return "SELECT creator, NAME FROM SYSIBM.SYSTABLES WHERE creator = '" + qualifier.toUpperCase() + "' AND TYPE='T'"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    public String getShowViewQuery(String qualifier) {
        return "SELECT creator, NAME FROM SYSIBM.SYSTABLES WHERE creator = '" + qualifier.toUpperCase() + "' AND TYPE='V'"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    public String getShowSequenceQuery(String qualifier) {
        return "SELECT schema, NAME FROM sysibm.syssequences WHERE schema = '" + qualifier.toUpperCase() + "'"; //$NON-NLS-1$ //$NON-NLS-2$
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
			//+ "where VIEWSCHEMA = '" + schema.toUpperCase() + "'"
			+ "and NAME = '" + viewName.toUpperCase() + "'" ;
	}

	public StatementExplainer getStatementExplainer() {
	    return this.explainer;
	}
}