package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_C3P0 {
    private static DataSource ds = null;

    static{
        ds = new ComboPooledDataSource("web");
    }

    public static Connection get_connection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection c, ResultSet rs, Statement s) {
        try {
            if (c != null) {
                c.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (s != null) {
                s.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
