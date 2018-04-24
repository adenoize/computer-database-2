package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

        ResourceBundle bundle = ResourceBundle.getBundle("datasource");
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");
        driverName = bundle.getString("driverName");

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

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * @param driverName
     *            the driverName to set
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

}
