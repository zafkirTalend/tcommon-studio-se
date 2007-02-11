package com.quantum.adapters;

import com.quantum.util.QuantumUtil;


public class DB2AS400Adapter extends DatabaseAdapter {
	protected DB2AS400Adapter() {
		super(AdapterFactory.DB2AS400);
	}
	
	public String getShowTableQuery(String schema) {
        return "SELECT table_schema, TABLE_NAME FROM QSYS2.SYSTABLES " +
        		"WHERE table_schema  = '" + 
        		schema.toUpperCase() + 
				"' AND TABLE_TYPE IN ('T', 'P')"; 
    }
	
    public String getShowViewQuery(String schema) {
        return "SELECT table_schema, TABLE_NAME FROM QSYS2.SYSTABLES " +
        		 "WHERE table_schema  = '" +
        		 schema.toUpperCase() +
        		 "' AND TABLE_TYPE IN ('V', 'L')";
    }
    
	public String getCreateStatement(String schema, String viewName) {
		return "SELECT view_definition FROM QSYS2.SYSVIEWS " +
			    "WHERE table_schema = '" + schema.toUpperCase() + "'" +
			    "and table_name = '" + viewName.toUpperCase() + "'" ;
	}

	
	public String getShowSequenceQuery(String schema) {
        return "SELECT SEQUENCE_SCHEMA, SEQUENCE_NAME form QSYS2.SYSSEQUENCES " +
				"WHERE SEQUENCE_SCHEMA = '" + schema + "'";
	}

    
	public String getNextValue(String sequence, String schema) {
        return "select previous value for " + sequence + " + INCREMENT " +
				"from QSYS2.SYSSEQUENCES  where SEQUENCE_SCHEMA = '" + schema +
				"' and SEQUENCE_NAME = '" + sequence + "'";
	}

	
	public String getPrevValue(String sequence, String schema) {
        return "select previous value for " + sequence +
				" from QSYS2.SYSSEQUENCES where SEQUENCE_SCHEMA = '" + schema +
				"' and SEQUENCE_NAME = '" + sequence + "'";
	}
	
	
	public String getRemarksQuery(String tableName, String columnName) {
		String query = "select LONG_COMMENT from QSYS2.SYSCOLUMNS " +
					   "where TABLE_NAME = '" + QuantumUtil.getTableName(tableName) +
					   "' and COLUMN_NAME = '" + columnName + "'";
		if (!(QuantumUtil.getSchemaName(tableName).equals("")))
			query += " and TABLE_SCHEMA = '" + QuantumUtil.getSchemaName(tableName) + "'";
		return query;
	}

    public String getShowAliasQuery(String schema) {
        return "select TABLE_NAME from QSYS2.SYSTABLES " +
        		"where TABLE_SCHEMA = '" + schema + "' and TABLE_TYPE = 'A'"; 
    }

	public String getShowPackagesQuery(String schema) {
		return "select PACKAGE_NAME from QSYS2.SYSPACKAGE where PACKAGE_SCHEMA = '" + schema + "'";
	}
	
	public String getProceduresQuery( String schema, String pack ) {
	    return "select SPECIFIC_NAME from QSYS2.SYSPROCS where SPECIFIC_SCHEMA = '" + schema + "'";
	}

	public String getProcedureArgumentsQuery( String schema, String pack, String proc, int overload ) {
	    return "select PARAMETER_NAME, ORDINAL_POSITION, DATA_TYPE from QSYS2.SYSPARMS " +
	    		"where SPECIFIC_SCHEMA = '" + schema + "' and SPECIFIC_NAME = '" + proc + "'"; 
	}
	
	public String getSequenceMetadataQuery(String schema, String sequenceName) {
		return "select MINIMUM_VALUE, MAXIMUM_VALUE, START, INCREMENT, " +
				"case CYCLE_OPTION when 'YES' then 'TRUE' else 'FALSE' end, " +
				"case ORDER when 'YES' then 'TURE' else 'FALSE' end " +
				"from QSYS2.SYSSEQUENCES when SEQUENCE_SCHEMA = '" + schema + "' and SEQUENCE_NAME = '" + sequenceName + "'";
	}

	
	public String getTriggersStatement(String schema, String objectName) {
		return "select TRIGGER_NAME, ACTION_TIMING, EVENT_MANIPULATION, '', ACTION_REFERENCE_OLD_ROW, ''," +
				"ACTION_CONDITION, ENABLED, TRIGGER_PROGRAM_NAME, ACTION_STATEMENT from QSYS2.SYSTRIGGERS " +
				"where TRIGGER_SCHEMA = '" + schema + "' and TRIGGER_NAME = '" + objectName +"'";
	}
	

	public String getChecksStatement(String schema, String objectName) {
		return "select t1.CONSTRAINT_NAME, t2.CHECK_CLAUSE from QSYS2.SYSCST t1 " +
				"join QSYS2.SYSCHKCST t2 on t1.CONSTRAINT_NAME = t2.CONSTRAINT_NAME and t1.CONSTRAINT_SCHEMA = t2.CONSTRAINT_SCHEMA" +
				"where t1.CONSTRAINT_TYPE = 'CHECK' and t1.CONSTRAINT_SCHEMA ='" + schema + "' and t1.TABLE_NAME = '" + objectName + "'";
	}
	
}
