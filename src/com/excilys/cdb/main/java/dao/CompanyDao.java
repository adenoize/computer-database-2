/**
 * 
 */
package com.excilys.cdb.main.java.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.main.java.model.Company;

/**
 * 
 * @author aurel
 *
 */
public interface CompanyDao {
	
	/**
	 * retrieve all companies.
	 * @return
	 */
	public List<Company> findAll() throws SQLException;
	
	/**
	 * retrieve the company with the given id.
	 * @param id
	 * @return
	 */
	public Company findById(Long id) throws SQLException;
	
}
