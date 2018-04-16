package com.excilys.cdb.main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class JDBCTool;
 * This class help to connect to DB
 * @author Aurelien Denoize
 *
 */
public class JdbcTool {
	
	private String url        = "jdbc:mysql://localhost/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user       = "admincdb";
	private String password   = "qwerty1234";
	private String driverName = "com.mysql.cj.jdbc.Driver";

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
	    Connection connection = DriverManager.getConnection(url, user, password);
	    return connection;
	}
	
	/**
	 * Initialize the JDBC Tool.
	 */
	public void init() {
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
