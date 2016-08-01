package routines.system;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TalendDataSource {

    private final javax.sql.DataSource ds;

    private Set<Connection> conns = new HashSet<>();

    public TalendDataSource(javax.sql.DataSource ds) {
        this.ds = ds;
    }

    public java.sql.Connection getConnection() throws SQLException {
        Connection conn = ds.getConnection();
        conns.add(conn);
        return conn;
    }

    public javax.sql.DataSource getRawDataSource() {
        return ds;
    }

    public void close() throws SQLException {
        for (Connection conn : conns) {
            conn.close();
        }
        conns.clear();
    }
}
