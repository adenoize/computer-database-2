package test.java.com.excilys.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import main.java.com.excilys.cdb.dao.ComputerDao;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

/**
 * @author Aurelien Denoize
 */
public class ComputerDaoTest {

    private static ComputerDao computerDao = ComputerDao.INSTANCE;

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

        Computer computer = new Computer("computer test", null, null, -1L);
        Optional<Long> id = computerDao.create(computer);
        assertEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateWithDate1() {

        Computer computer = new Computer("computer test", null, LocalDate.of(2000, 1, 2), -1L);
        Optional<Long> id = computerDao.create(computer);
        assertEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateWithDate2() {

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), null, -1L);
        Optional<Long> id = computerDao.create(computer);
        assertEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateWithDate3() {

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), -1L);
        Optional<Long> id = computerDao.create(computer);
        assertEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#create(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testCreateAllInfo() {

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), 1L);
        Optional<Long> id = computerDao.create(computer);
        assertNotEquals(Optional.empty(), id);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.dao.ComputerDao#update(main.java.com.excilys.cdb.model.Computer)}.
     */
    @Test
    public void testUpdate() {
        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), 1L);
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
        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), 1L);
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

        Computer computer = new Computer("computer test", null, LocalDate.of(2000, 1, 2), -1L);
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

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), null, -1L);
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

        Computer computer = new Computer("computer test", LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), -1L);
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
        Page<Computer> page = computerDao.getPage(1);
        assertEquals("Number of computers should be 10", 10, page.getPage().size());
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

}
