package main.java.com.excilys.cdb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class JDBCTool This class help to connect to DB.
 * @author Aurelien Denoize
 *
 */
public enum JdbcTool {
    INSTANCE;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private boolean init;


    /**
     * Load the driver.
     * @throws ClassNotFoundException Exception if driver not found
     */
    private void loadDriver() throws ClassNotFoundException {
        Class.forName(driverName);
    }

    /**
     * Create a new connection to database.
     * @return Connection to database
     * @throws SQLException Exception if connection is refused
     */
    public Connection newConnection() throws SQLException {

        if (!init) {
            init();
            init = true;
        }
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * Initialize the JDBC Tool.
     */
    public void init() {

        ClassLoader classLoader = getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream("datasource.properties");

        Properties prop = new Properties();

        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
        driverName = prop.getProperty("driverName");

        try {
            loadDriver();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the given connection.
     * @param connection connection to close
     */
    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
