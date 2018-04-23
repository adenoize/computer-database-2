package main.java.com.excilys.cdb.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class JDBCTool;
 * This class help to connect to DB
 * @author Aurelien Denoize
 *
 */
public enum JdbcTool {
	INSTANCE;

	private String filename = "datasource.properties";
	private String driverName;
	private String url;
	private String user;       
	private String password;
	private boolean init;
	
	
	private JdbcTool() {

	}

	/**
	 * Load the driver.
	 * @throws ClassNotFoundException
	 */
	private void loadDriver() throws ClassNotFoundException {
		 Class.forName(driverName);
	}

	/**
	 * Create a new connection to database.
	 * @return
	 * @throws SQLException
	 */
	public Connection newConnection() throws SQLException {
	
		if(!init) {
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

		Properties prop = new Properties();
		InputStream input = null;

		try {

			
    		prop.load(new FileInputStream("resources/"+filename));
	
    		
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			driverName = prop.getProperty("driverName");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			loadDriver();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * Close the given connection.
	 * @param connection
	 */
	public void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
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
	 * @param url the url to set
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
	 * @param user the user to set
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
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	
	
	

}
