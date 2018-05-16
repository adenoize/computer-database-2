package test.java.com.excilys.cdb.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.ResultSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import main.java.com.excilys.cdb.mapper.ComputerMapper;
import main.java.com.excilys.cdb.model.Computer;

/**
 * Test for ComputerMapper.
 * @author Aurelien Denoize
 */
@RunWith(MockitoJUnitRunner.class)
public class ComputerMapperTest {

    @Mock
    ResultSet resultSet;

    ComputerMapper computerMapper = new ComputerMapper();

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.mapper.ComputerMapper#map(java.sql.ResultSet)}.
     * @throws Exception Exception
     */
    @Test
    public void testMap() throws Exception {

        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("name")).thenReturn("test");
        Mockito.when(resultSet.getDate("introduced")).thenReturn(new Date(20000101));
        Mockito.when(resultSet.getDate("discontinued")).thenReturn(new Date(20000101));
        Mockito.when(resultSet.getLong("company_id")).thenReturn(1L);

        Computer computer = computerMapper.map(resultSet);

        assertEquals(1, computer.getId().longValue());
        assertTrue(computer.getName().equals("test"));
        assertTrue(computer.getIntroduced().equals(new Date(20000101).toLocalDate()));
        assertTrue(computer.getDiscontinued().equals(new Date(20000101).toLocalDate()));
        assertEquals(1, computer.getCompany().getId().longValue());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.mapper.ComputerMapper#map(java.sql.ResultSet)}.
     * @throws Exception Exception
     */
    @Test
    public void testMap2() throws Exception {

        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("name")).thenReturn("test");
        Mockito.when(resultSet.getDate("introduced")).thenReturn(null);
        Mockito.when(resultSet.getDate("discontinued")).thenReturn(new Date(20000101));
        Mockito.when(resultSet.getLong("company_id")).thenReturn(1L);

        Computer computer = computerMapper.map(resultSet);

        assertEquals(1, computer.getId().longValue());
        assertTrue(computer.getName().equals("test"));
        assertFalse(new Date(20000101).toLocalDate().equals(computer.getIntroduced()));
        assertTrue(new Date(20000101).toLocalDate().equals(computer.getDiscontinued()));
        assertEquals(1, computer.getCompany().getId().longValue());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.mapper.ComputerMapper#map(java.sql.ResultSet)}.
     * @throws Exception Exception
     */
    @Test
    public void testMap3() throws Exception {

        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("name")).thenReturn("test");
        Mockito.when(resultSet.getDate("introduced")).thenReturn(new Date(20000101));
        Mockito.when(resultSet.getDate("discontinued")).thenReturn(null);
        Mockito.when(resultSet.getLong("company_id")).thenReturn(1L);

        Computer computer = computerMapper.map(resultSet);

        assertEquals(1, computer.getId().longValue());
        assertTrue(computer.getName().equals("test"));
        assertTrue(new Date(20000101).toLocalDate().equals(computer.getIntroduced()));
        assertFalse(new Date(20000101).toLocalDate().equals(computer.getDiscontinued()));
        assertEquals(1, computer.getCompany().getId().longValue());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.mapper.ComputerMapper#map(java.sql.ResultSet)}.
     * @throws Exception Exception
     */
    @Test
    public void testMap4() throws Exception {

        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("name")).thenReturn("test");
        Mockito.when(resultSet.getDate("introduced")).thenReturn(null);
        Mockito.when(resultSet.getDate("discontinued")).thenReturn(null);
        Mockito.when(resultSet.getLong("company_id")).thenReturn(1L);

        Computer computer = computerMapper.map(resultSet);

        assertEquals(1, computer.getId().longValue());
        assertTrue(computer.getName().equals("test"));
        assertFalse(new Date(20000101).toLocalDate().equals(computer.getIntroduced()));
        assertFalse(new Date(20000101).toLocalDate().equals(computer.getDiscontinued()));
        assertEquals(1, computer.getCompany().getId().longValue());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.mapper.ComputerMapper#map(java.sql.ResultSet)}.
     * @throws Exception Exception
     */
    @Test
    public void testMap5() throws Exception {

        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("name")).thenReturn("test");
        Mockito.when(resultSet.getDate("introduced")).thenReturn(null);
        Mockito.when(resultSet.getDate("discontinued")).thenReturn(null);
        Mockito.when(resultSet.getLong("company_id")).thenReturn(1L);

        Computer computer = computerMapper.map(resultSet);

        assertEquals(1, computer.getId().longValue());
        assertTrue(computer.getName().equals("test"));
        assertFalse(new Date(20000101).toLocalDate().equals(computer.getIntroduced()));
        assertFalse(new Date(20000101).toLocalDate().equals(computer.getDiscontinued()));
        assertEquals(1, computer.getCompany().getId().longValue());
    }
}
