package com.quantum.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basically this Factory is a Singleton that is used to return the proper adapter
 * @author root
 */
public class AdapterFactory {
	
    static class ComparatorImpl implements Comparator {

		public int compare(Object arg0, Object arg1) {
			DatabaseAdapter adapter0 = (DatabaseAdapter) arg0;
			DatabaseAdapter adapter1 = (DatabaseAdapter) arg1;
			
			if (adapter0 == null && adapter0 != null) {
				return -1;
			} else if (adapter0 != null && adapter1 == null) {
				return 1;
			} else if (adapter0 == null && adapter1 == null) {
				return 0;
			} else {
				return adapter0.getDisplayName().compareTo(adapter1.getDisplayName());
			}
		}
	}
	
	static class DriverInfo {
		private final String type;
		private final String urlPattern;

		DriverInfo(String type) {
			this(type, null);
		}
		DriverInfo(String type, String urlPattern) {
			this.type = type;
			this.urlPattern = urlPattern;
			
		}
		public String getType() {
			return this.type;
		}
		public String getURLPattern() {
			return this.urlPattern;
		}
	}
	
	public static final String GENERIC = "GENERIC"; //$NON-NLS-1$
	public static final String HSQLDB = "HSQLDB"; //$NON-NLS-1$
    public static final String ORACLE = "ORACLE"; //$NON-NLS-1$
    public static final String POSTGRES = "POSTGRES"; //$NON-NLS-1$
    public static final String MYSQL = "MYSQL"; //$NON-NLS-1$
    public static final String DB2 = "DB2"; //$NON-NLS-1$
    public static final String DB2OS390 = "DB2OS390"; //$NON-NLS-1$
    public static final String DB2AS400 = "DB2AS400"; //$NON-NLS-1$
	public static final String ADABASD = "ADABASD"; //$NON-NLS-1$
    public static final String INFORMIX = "INFORMIX"; //$NON-NLS-1$
	public static final String REDBRICK = "REDBRICK"; //$NON-NLS-1$
	public static final String POINTBASE = "POINTBASE"; //$NON-NLS-1$
    public static final String SYBASE = "SYBASE"; //$NON-NLS-1$
    public static final String JDBC_ODBC_BRIDGE = "JDBC_ODBC_BRIDGE"; //$NON-NLS-1$
    public static final String MS_SQL_SERVER = "MS_SQL_SERVER"; //$NON-NLS-1$
    public static final String DERBY = "DERBY"; //$NON-NLS-1$
    public static final String FIREBIRD = "FIREBIRD"; //$NON-NLS-1$

	private static final Map DRIVER_MAP = Collections.synchronizedMap(new HashMap());
	
	static {
		DRIVER_MAP.put("com.ddtek.jdbc.informix.InformixDriver", new DriverInfo(INFORMIX));
		DRIVER_MAP.put("com.ddtek.jdbc.db2.DB2Driver", new DriverInfo(DB2, "jdbc:datadirect:db2://${hostname}:${port};DatabaseName=${dbname}"));
		DRIVER_MAP.put("com.ddtek.jdbc.oracle.OracleDriver", new DriverInfo(ORACLE ,"jdbc:oracle:thin:@{hostname}:{port}:{dbname}"));
		DRIVER_MAP.put("com.ddtek.jdbc.sqlserver.SQLServerDriver", new DriverInfo(GENERIC));
		DRIVER_MAP.put("com.ddtek.jdbc.sybase.SybaseDriver", new DriverInfo(SYBASE));
		DRIVER_MAP.put("com.ibm.as400.access.AS400JDBCDriver", new DriverInfo(DB2AS400));
		DRIVER_MAP.put("COM.ibm.db2.jdbc.app.DB2Driver", new DriverInfo(DB2, "jdbc:db2:{dbname}"));
		DRIVER_MAP.put("COM.ibm.db2.jdbc.net.DB2Driver", new DriverInfo(DB2, "jdbc:db2://{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("com.ibm.db2.jcc.DB2Driver", new DriverInfo(DB2, "jdbc:db2://{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver", new DriverInfo(DB2, "jdbc:db2os390sqlj:{dbname}"));
		DRIVER_MAP.put("com.inet.ora.OraDriver", new DriverInfo(ORACLE ,"jdbc:oracle:thin:@{hostname}:{port}:{dbname}"));
		DRIVER_MAP.put("com.inet.drda.DRDADriver", new DriverInfo(DB2, "jdbc:inetdb2:{hostname}:{port}?database={dbname}"));
		DRIVER_MAP.put("com.inet.syb.SybDriver", new DriverInfo(SYBASE));
		DRIVER_MAP.put("com.inet.tds.TdsDriver", new DriverInfo(MS_SQL_SERVER));
		DRIVER_MAP.put("com.informix.jdbc.IfxDriver", new DriverInfo(INFORMIX, "jdbc:informix-sqli://{hostname}:{port}/{dbname}:INFORMIXSERVER={informixserver}"));
        DRIVER_MAP.put("com.microsoft.jdbc.sqlserver.SQLServerDriver", new DriverInfo(MS_SQL_SERVER, "jdbc:microsoft:sqlserver://{hostname}:{port};DatabaseName={dbname}"));
        DRIVER_MAP.put("com.microsoft.sqlserver.jdbc.SQLServerDriver", new DriverInfo(MS_SQL_SERVER, "jdbc:sqlserver://{hostname}:{port};databaseName={dbname}"));
		DRIVER_MAP.put("com.mysql.jdbc.Driver", new DriverInfo(MYSQL, "jdbc:mysql://{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("com.pointbase.jdbc.jdbcUniversalDriver", new DriverInfo(POINTBASE, "jdbc:pointbase:server://{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("com.sybase.jdbc.SybDriver", new DriverInfo(SYBASE, "jdbc:sybase:Tds:{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("com.sybase.jdbc2.jdbc.SybDriver", new DriverInfo(SYBASE, "jdbc:sybase:Tds:{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("com.sybase.jdbcx.SybDriver", new DriverInfo(SYBASE, "jdbc:sybase:Tds:{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("net.sourceforge.jtds.jdbc.Driver", new DriverInfo(MS_SQL_SERVER));
		DRIVER_MAP.put("oracle.jdbc.driver.OracleDriver", new DriverInfo(ORACLE ,"jdbc:oracle:thin:@{hostname}:{port}:{dbname}"));
		DRIVER_MAP.put("oracle.jdbc.OracleDriver", new DriverInfo(ORACLE ,"jdbc:oracle:thin:@{hostname}:{port}:{dbname}"));
		DRIVER_MAP.put("org.gjt.mm.mysql.Driver", new DriverInfo(MYSQL, "jdbc:mysql://{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("org.hsqldb.jdbcDriver", new DriverInfo(HSQLDB));
		DRIVER_MAP.put("org.postgresql.Driver", new DriverInfo(POSTGRES, "jdbc:postgresql://{hostname}:{port}/{dbname}"));
		DRIVER_MAP.put("sun.jdbc.odbc.JdbcOdbcDriver", new DriverInfo(JDBC_ODBC_BRIDGE, "jdbc:odbc:{datasource}"));
		DRIVER_MAP.put("weblogic.jdbc.informix.InformixDriver", new DriverInfo(INFORMIX));
		DRIVER_MAP.put("weblogic.jdbc.sqlserver.SybaseDriver", new DriverInfo(SYBASE));
		DRIVER_MAP.put("org.apache.derby.jdbc.Driver20", new DriverInfo(DERBY, "jdbc:derby:{dbname}"));
		DRIVER_MAP.put("org.apache.derby.jdbc.Driver30", new DriverInfo(DERBY, "jdbc:derby:{dbname}"));
		DRIVER_MAP.put("org.apache.derby.jdbc.EmbeddedDriver", new DriverInfo(DERBY, "jdbc:derby:{dbname}"));
		DRIVER_MAP.put("org.firebirdsql.jdbc.FBDriver", new DriverInfo(FIREBIRD, "jdbc:firebirdsql:{hostname}/{port}:{dbname}"));
		
	}
	
    private static AdapterFactory instance;
    
    private Map adapters = Collections.synchronizedMap(new HashMap());
    
    private AdapterFactory() {
    	addAdapter(new GenericAdapter(GENERIC));
    	addAdapter(new HsqldbAdapter());
    	addAdapter(new OracleAdapter());
    	addAdapter(new DB2Adapter());
    	addAdapter(new DB2AS400Adapter());
    	addAdapter(new DB2OS390Adapter());
    	addAdapter(new PostgresAdapter());
    	addAdapter(new MySQLAdapter());
    	addAdapter(new AdabasDAdapter());
    	addAdapter(new InformixAdapter());
    	addAdapter(new RedBrickAdapter());
    	addAdapter(new GenericAdapter(SYBASE));
    	addAdapter(new GenericAdapter(POINTBASE));
    	addAdapter(new GenericAdapter(JDBC_ODBC_BRIDGE));
    	addAdapter(new MSSQLServerAdapter());
    	addAdapter(new DerbyAdapter());
    	addAdapter(new FirebirdAdapter());
    }
    private void addAdapter(DatabaseAdapter adapter) {
    	this.adapters.put(adapter.getType(), adapter);
    }
    
    public static synchronized AdapterFactory getInstance() {
    	if (instance == null) {
    		instance = new AdapterFactory();
    	}
    	return instance;
    }

    public DatabaseAdapter getAdapter(String type){
    	DatabaseAdapter result = (DatabaseAdapter) this.adapters.get(type);
    	if (result == null) {
    		result = (DatabaseAdapter) this.adapters.get(GENERIC);
    	}
    	return result == null ? null : result;
    }

    
    
    public DatabaseAdapter[] getDriverList() {
        List list = new ArrayList(this.adapters.values());
        Collections.sort(list, new ComparatorImpl());
    	return (DatabaseAdapter[]) list.toArray(new DatabaseAdapter[list.size()]);
    }
    
    /**
     * 
     * @param driverClassName
     * @return the adapter type, or null if the adapter type is not known
     */
    public String getAdapterType(String driverClassName) {
    	DriverInfo driverInfo = (DriverInfo) DRIVER_MAP.get(driverClassName);
		return driverInfo == null ? null : driverInfo.getType();
    }
	/**
	 * @param type
	 * @return
	 */
	public String getURLPattern(String driverClassName) {
    	DriverInfo driverInfo = (DriverInfo) DRIVER_MAP.get(driverClassName);
		return driverInfo == null ? null : driverInfo.getURLPattern();
	}
}