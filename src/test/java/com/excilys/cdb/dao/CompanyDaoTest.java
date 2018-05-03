package test.java.com.excilys.cdb.dao;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.excilys.cdb.dao.CompanyDao;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

/**
 * @author Aurelien Denoize
 */
public class CompanyDaoTest {

    private static CompanyDao companyDao = CompanyDao.INSTANCE;

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findAll()}.
     * @throws Exception An Exception
     */
    @Test
    public void testGetPage() throws Exception {

        Page<Company> page = companyDao.getPage(1);
        assertEquals("Number of companies should be 10", 10, page.getPage().size());
    }

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findAll()}.
     * @throws Exception An Exception
     */
    @Test
    public void testGetPageFail() throws Exception {

        Page<Company> page = companyDao.getPage(-1);
        assertEquals("Number of companies should be 10", 10, page.getPage().size());
    }

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findById(java.lang.Long)}.
     * @throws Exception An Exception
     */
    @Test
    public void testFindById() throws Exception {
        Company company = companyDao.findById(20L);
        assertNotNull(company);
        assertTrue("Name should be Atari", company.getName().equals("Atari"));
    }

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findById(java.lang.Long)}.
     * @throws Exception An Exception
     */
    @Test
    public void testFindByIdFail() throws Exception {
        Company company = companyDao.findById(-1L);
        assertNull(company);
    }


}
