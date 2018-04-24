package test.java.com.excilys.cdb.dao;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.excilys.cdb.dao.JdbcTool;

/**
 * @author aurel
 *
 */
public class JdbcToolTest {

    private JdbcTool jdbcTool = JdbcTool.INSTANCE;

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.JdbcTool#newConnection()}.
     * @throws Exception An Exception
     */
    @Test
    public void testNewConnection() throws Exception {
        Connection connection = jdbcTool.newConnection();

        Assert.assertNotNull("Connection should not be null", connection);

        connection.close();

        Assert.assertTrue("Connection should be close", connection.isClosed());
    }

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.JdbcTool#close(java.sql.Connection)}.
     * @throws Exception An Exception
     */
    @Test
    public void testClose() throws Exception {

        Connection connection = jdbcTool.newConnection();
        Assert.assertNotNull("Connection should not be null", connection);

        jdbcTool.close(connection);

        Assert.assertTrue("Connection should be close", connection.isClosed());

    }

}
