package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

    private static HikariConfig config = new HikariConfig("/datasource.properties");
    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource(config);
    }

    /**
     * Constructor.
     */
    private DataSource() {
    }

    /**
     * Retrieve a Connection to the Database.
     * @return A connection
     * @throws SQLException Connection error
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}