package routines.system;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SystemDBConnection {

    private static Map<Integer, Connection> insDBConnections = new HashMap<Integer, java.sql.Connection>();

    public static java.sql.Connection getDBConnection(String dbDriver, String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        if (null == insDBConnections.get(new Integer((url + userName + password).hashCode()))
                || insDBConnections.get(new Integer((url + userName + password).hashCode())).isClosed()) {
            java.lang.Class.forName(dbDriver);
            synchronized (insDBConnections) {
                insDBConnections.put(new Integer((url + userName + password).hashCode()), java.sql.DriverManager.getConnection(
                        url, userName, password));
            }
        }
        return insDBConnections.get(new Integer((url + userName + password).hashCode()));
    }

}
