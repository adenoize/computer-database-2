/**
 * 
 */
package com.excilys.cdb.test.java.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.cdb.main.java.dao.CompanyDao;
import com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl;
import com.excilys.cdb.main.java.model.Company;

/**
 * @author Aurelien Denoize
 *
 */
class CompanyDaoImplTest {
	
	private static CompanyDao companyDao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		companyDao = new CompanyDaoImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl#findAll()}.
	 * @throws Exception 
	 */
	@Test
	void testFindAll() throws Exception {
		
		List<Company> companies = companyDao.findAll();
		assertEquals("Number of companies should be 42",42,companies.size());
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
