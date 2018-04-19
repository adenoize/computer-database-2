/**
 * 
 */
package com.excilys.cdb.test.java.dao;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.cdb.main.java.dao.JdbcTool;

/**
 * @author aurel
 *
 */
class JdbcToolTest {
	
	private JdbcTool jdbcTool = JdbcTool.INSTANCE;

	/**
	 * Test method for {@link com.excilys.cdb.main.java.dao.JdbcTool#newConnection()}.
	 */
	@Test
	void testNewConnection() throws Exception{
		Connection connection = jdbcTool.newConnection();
		
		Assert.assertNotNull("Connection should not be null", connection);
		
		connection.close();
		
		Assert.assertTrue("Connection should be close",connection.isClosed());
	}

	/**
	 * Test method for {@link com.excilys.cdb.main.java.dao.JdbcTool#close(java.sql.Connection)}.
	 */
	@Test
	void testClose() throws Exception{
		
		Connection connection = jdbcTool.newConnection();
		Assert.assertNotNull("Connection should not be null", connection);
		
		jdbcTool.close(connection);
		
		Assert.assertTrue("Connection should be close",connection.isClosed());
		
	}

}
