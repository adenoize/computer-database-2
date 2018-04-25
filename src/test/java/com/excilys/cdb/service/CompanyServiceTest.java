package test.java.com.excilys.cdb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.DatabaseException;

/**
 * @author Aurelien Denoize
 */
public class CompanyServiceTest {

    CompanyService companyService = new CompanyService();

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.CompanyService#getPage(int)}.
     */
    @Test
    public void testGetPage() {
        Page<Company> page = companyService.getPage(1);

        assertEquals(10, page.getPage().size());

    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.CompanyService#getPage(int)}.
     */
    @Test
    public void testGetPageMAx() {
        Page<Company> page = companyService.getPage(Integer.MAX_VALUE / 2);

        assertEquals(0, page.getPage().size());

    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.CompanyService#findById(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testFindById() throws Exception {
        Company company = companyService.findById(1L);

        assertNotNull(company);
        assertEquals(1, company.getId().longValue());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.service.CompanyService#findById(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test(expected = DatabaseException.class)
    public void testFindByBadId() throws Exception {
        companyService.findById(-1L);
    }

}
