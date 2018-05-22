package test.java.com.excilys.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import main.java.com.excilys.cdb.config.AppConfig;
import main.java.com.excilys.cdb.dao.CompanyDao;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

/**
 * @author Aurelien Denoize
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CompanyDaoTest {

    @Autowired
    private CompanyDao companyDao;

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
        Optional<Company> company = companyDao.findById(20L);
        if (company.isPresent()) {
            assertTrue("Name should be Atari", company.get().getName().equals("Atari"));
        } else {
            fail();
        }
    }

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findById(java.lang.Long)}.
     * @throws Exception An Exception
     */
    @Test
    public void testFindByIdFail() throws Exception {
        Optional<Company> company = companyDao.findById(-1L);
        assertFalse("Company should be false", company.isPresent());
    }

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findAll()}.
     * @throws Exception An Exception
     */
    @Test
    public void testFindAll() throws Exception {
        List<Company> company = companyDao.findAll();
        if (company.isEmpty()) {
            fail();
        }
    }

    /**
     * Test method for
     * {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#removeById(java.lang.Long)}.
     * @throws Exception An Exception
     */
    @Test
    public void testRemoveById() throws Exception {
        companyDao.removeById(20L);
    }
}
