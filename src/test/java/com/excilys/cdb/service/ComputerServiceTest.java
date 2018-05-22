package test.java.com.excilys.cdb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import main.java.com.excilys.cdb.config.AppConfig;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.ComputerService;

/**
 * Test for ComputerService.
 * @author Aurelien Denoize
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ComputerServiceTest {

    @Autowired
    private ComputerService computerService;

    private Computer computer;

    private static Company badCompany;

    /**
     * Initialization.
     */
    @BeforeClass
    public static void beforeClass() {
        badCompany = new Company();
        badCompany.setId(-1L);
        badCompany.setName("BadName");
    }

    /**
     * Mock of computer.
     */
    @Before
    public void before() {
        computer = mock(Computer.class);

    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.ComputerService#save(main.java.com.excilys.cdb.model.Computer)}.
     * @throws Exception Exception
     */
    @Test(expected = DatabaseException.class)
    public void testSave() throws Exception {

        Mockito.when(computer.getName()).thenReturn("test");
        Mockito.when(computer.getIntroduced()).thenReturn(LocalDate.of(2000, 1, 1));
        Mockito.when(computer.getDiscontinued()).thenReturn(LocalDate.of(2000, 1, 1));
        Mockito.when(computer.getCompany()).thenReturn(badCompany);

        computerService.save(computer);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.ComputerService#update(main.java.com.excilys.cdb.model.Computer)}.
     * @throws Exception Exception
     */
    @Test(expected = DatabaseException.class)
    public void testUpdate() throws Exception {

        Mockito.when(computer.getId()).thenReturn(1L);
        Mockito.when(computer.getName()).thenReturn("test");
        Mockito.when(computer.getIntroduced()).thenReturn(LocalDate.of(2000, 1, 1));
        Mockito.when(computer.getDiscontinued()).thenReturn(LocalDate.of(2000, 1, 1));
        Mockito.when(computer.getCompany()).thenReturn(badCompany);

        computerService.update(computer);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.ComputerService#removeById(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test(expected = DatabaseException.class)
    public void testRemoveById() throws Exception {

        computerService.removeById(-1L);
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.ComputerService#getPage(int)}.
     */
    @Test
    public void testGetPage() {
        Page<Computer> page = computerService.getPage(1);

        assertEquals(10, page.getPage().size());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.ComputerService#getPage(int)}.
     */
    @Test
    public void testGetPageMax() {
        Page<Computer> page = computerService.getPage(Integer.MAX_VALUE / 2);

        assertEquals(0, page.getPage().size());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.ComputerService#findById(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testFindById() throws Exception {
        Computer computer = computerService.findById(1L);

        assertNotNull(computer);
        assertEquals(1, computer.getId().longValue());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.ComputerService#findById(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test(expected = DatabaseException.class)
    public void testFindByBadId() throws Exception {
        computerService.findById(-1L);
    }

}
