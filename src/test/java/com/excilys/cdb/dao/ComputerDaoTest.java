package test.java.com.excilys.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import main.java.com.excilys.cdb.config.AppConfig;
import main.java.com.excilys.cdb.dao.ComputerDao;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

/**
 * @author Aurelien Denoize
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ComputerDaoTest {

    @Autowired
    private static ComputerDao computerDao;

    private static Company goodCompany;
    private static Company badCompany;

    /**
     * Initialization.
     */
    @BeforeClass
    public static void beforeClass() {
        goodCompany = new Company();
        goodCompany.setId(1L);
        goodCompany.setName("Apple Inc.");

        badCompany = new Company();
        badCompany.setId(-1L);
        badCompany.setName("BadName");
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreate() {

        Computer computer = new Computer("computer test");
        Optional<Long> id = computerDao.create(computer);
        assertNotEquals(Optional.empty(), id);

    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateWithBadCompany() {

        Computer computer = new Computer("computer test", null, null, badCompany);
        Optional<Long> id = computerDao.create(computer);
        assertEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateWithDate1() {

        Computer computer = new Computer("computer test", null, LocalDate.of(2000, 1, 2), badCompany);
        Optional<Long> id = computerDao.create(computer);
        assertEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateWithDate2() {

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), null, badCompany);
        Optional<Long> id = computerDao.create(computer);
        assertEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateWithDate3() {

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), badCompany);
        Optional<Long> id = computerDao.create(computer);
        assertEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateAllInfo() {

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), goodCompany);
        Optional<Long> id = computerDao.create(computer);
        assertNotEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#update(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testUpdate() {
        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), goodCompany);
        computer.setId(1L);
        boolean result = computerDao.update(computer);
        assertTrue(result);
        assertTrue(computer.getName().equals("computer test"));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#update(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testUpdateBadId() {
        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), goodCompany);
        computer.setId(-1L);
        boolean result = computerDao.update(computer);
        assertFalse(result);
        assertFalse("Name should not be 'computer test'", !computer.getName().equals("computer test"));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testUpdateWithDate1() {

        Computer computer = new Computer("computer test", null, LocalDate.of(2000, 1, 2), badCompany);
        computer.setId(1L);
        boolean result = computerDao.update(computer);
        assertFalse(result);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testUpdateWithDate2() {

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), null, badCompany);
        computer.setId(1L);
        boolean result = computerDao.update(computer);
        assertFalse(result);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testUpdateWithDate3() {

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), badCompany);
        computer.setId(1L);
        boolean result = computerDao.update(computer);
        assertFalse(result);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#update(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testUpdateNull() {
        Computer computer = new Computer("computer test", null, null, null);
        computer.setId(2L);
        boolean result = computerDao.update(computer);
        assertTrue(result);
        assertTrue(computer.getName().equals("computer test"));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#removeById(java.lang.Long)}.
     */
    @Test
    public void testRemoveById() {

        boolean result = computerDao.removeById(100L);

        assertTrue(result);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#removeById(java.lang.Long)}.
     */
    @Test
    public void testRemoveByIdBadId() {
        boolean result = computerDao.removeById(-1L);

        assertFalse(result);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int)}.
     */
    @Test
    public void testGetPage() {
        Page<Computer> page = computerDao.getPage(0);
        assertEquals("Number of computers should be 10", 10, page.getPage().size());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPageBad() {
        computerDao.getPage(-1);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int)}.
     */
    @Test
    public void testGetPageLimit1() {
        Page<Computer> page = computerDao.getPage(0, 20);
        assertEquals("Number of computers should be 20", 20, page.getPage().size());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPageBadLimit2() {
        computerDao.getPage(-1, 1);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPageBadLimit3() {
        computerDao.getPage(1, -1);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPageBadLimit4() {
        computerDao.getPage(-1, -1);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int,int,String)}.
     */
    @Test
    public void testGetPageSearch() {
        Page<Computer> page = computerDao.getPage(0, 10, "ASCI Thors Hammer");
        assertEquals("Number of computers should be 1", 1, page.getPage().size());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int,int,String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPageSearchBadLimit() {
        computerDao.getPage(1, -1, "apple");
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int,int,String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPageSearchBadOffset() {
        computerDao.getPage(-1, 10, "apple");
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#getPage(int,int,String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPageSearchBadOffsetAndLimit() {
        computerDao.getPage(-1, -1, "apple");
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        Optional<Computer> computer = computerDao.findById(10L);

        assertNotEquals(Optional.empty(), computer);
        assertEquals(10, computer.get().getId().longValue());
        assertTrue(computer.get().getName().equals("Apple IIc Plus"));
    }

//    /**
//     * Test method for
//     * {@link main.java.com.excilys.cdb.dao.ComputerDao#removeById(java.lang.Long)}.
//     * @throws SQLException SQLException
//     */
//    @Test
//    public void testRemoveByIdConnection() throws SQLException {
//
//        Connection connection = DataSource.getConnection();
//        connection.setAutoCommit(false);
//        boolean result = computerDao.removeById(100L, connection);
//        connection.rollback();
//        connection.close();
//        assertTrue(result);
//    }
//
//    /**
//     * Test method for
//     * {@link main.java.com.excilys.cdb.dao.ComputerDao#removeById(java.lang.Long)}.
//     * @throws SQLException SQLException
//     */
//    @Test
//    public void testRemoveByIdBadIdConnection1() throws SQLException {
//        Connection connection = DataSource.getConnection();
//        connection.setAutoCommit(false);
//        boolean result = computerDao.removeById(-1L, connection);
//        connection.rollback();
//        connection.close();
//        assertFalse(result);
//    }
//
//    /**
//     * Test method for
//     * {@link main.java.com.excilys.cdb.dao.ComputerDao#removeById(java.lang.Long)}.
//     * @throws SQLException SQLException
//     */
//    @Test
//    public void testRemoveByIdBadIdConnection2() throws SQLException {
//        Connection connection = DataSource.getConnection();
//        connection.setAutoCommit(false);
//        boolean result = computerDao.removeById(10000000000L, connection);
//        connection.rollback();
//        connection.close();
//        assertFalse(result);
//    }

}
