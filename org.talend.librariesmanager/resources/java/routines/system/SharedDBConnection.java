package routines.system;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SharedDBConnection {

    private static Map<Integer, Connection> insDBConnections = new HashMap<Integer, java.sql.Connection>();

    public static java.sql.Connection getDBConnection(String dbDriver, String url, String userName, String password,
            String dbConnectionName) throws ClassNotFoundException, SQLException {
        if (null == insDBConnections.get((url + userName + password + dbConnectionName).hashCode())
                || insDBConnections.get((url + userName + password + dbConnectionName).hashCode()).isClosed()) {
            java.lang.Class.forName(dbDriver);
            synchronized (insDBConnections) {
                insDBConnections.put((url + userName + password + dbConnectionName).hashCode(), java.sql.DriverManager
                        .getConnection(url, userName, password));
            }
        }
        return insDBConnections.get((url + userName + password + dbConnectionName).hashCode());
    }
}
