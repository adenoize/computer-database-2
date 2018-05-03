package main.java.com.excilys.cdb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class JDBCTool This class help to connect to DB.
 * @author Aurelien Denoize
 */
public enum JdbcTool {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDao.class);

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
    public Connection newConnection() {

        if (!init) {
            init();
            init = true;
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {

            LOGGER.error(e.getMessage());
        }
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
