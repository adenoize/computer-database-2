/**
 * 
 */
package com.excilys.cdb.test.java.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.excilys.cdb.main.java.dao.CompanyDao;
import com.excilys.cdb.main.java.model.Company;
import com.excilys.cdb.main.java.model.Page;

/**
 * @author Aurelien Denoize
 *
 */
class CompanyDaoImplTest {
	
	private static CompanyDao companyDao = CompanyDao.INSTANCE;


	/**
	 * Test method for {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findAll()}.
	 * @throws Exception 
	 */
	@Test
	void testFindAll() throws Exception {
		
		Page<Company> page = companyDao.getPage(0);
		assertEquals("Number of companies should be 10",10,page.getPage().size());
	}

	/**
	 * Test method for {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findById(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	void testFindById() throws Exception {
				
		Company company = companyDao.findById(20L);
		assertNotNull(company);
		assertTrue(company.getName().equals("Atari"),"Name should be Atari but it was " + company.getName());
		
		
	}

}
