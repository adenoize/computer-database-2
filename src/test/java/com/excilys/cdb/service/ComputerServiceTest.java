package test.java.com.excilys.cdb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.ComputerService;
import main.java.com.excilys.cdb.service.DatabaseException;

/**
 * Test for ComputerService.
 * @author Aurelien Denoize
 */
@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest {

    ComputerService computerService = new ComputerService();

    @Mock
    Computer computer;

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
        Mockito.when(computer.getCompany()).thenReturn(-1L);

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
        Mockito.when(computer.getCompany()).thenReturn(-1L);

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
