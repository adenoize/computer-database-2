package test.java.com.excilys.cdb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import main.java.com.excilys.cdb.config.WebAppConfig;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.CompanyService;

/**
 * @author Aurelien Denoize
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

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
    @Test(expected = NoSuchElementException.class)
    public void testFindByBadId() throws Exception {
        companyService.findById(-1L);
    }

}
