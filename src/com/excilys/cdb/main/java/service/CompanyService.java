package com.excilys.cdb.main.java.service;

import java.util.List;

import com.excilys.cdb.main.java.dao.CompanyDao;
import com.excilys.cdb.main.java.dao.impl.CompanyDaoImpl;
import com.excilys.cdb.main.java.model.Company;

public class CompanyService {
	
	
	private CompanyDao companyDao;
	
	
	public CompanyService() {
		companyDao = new CompanyDaoImpl();
		
	}
	
	/**
	 * retrieve all companies.
	 */
	public List<Company> findAll(){
		return companyDao.findAll();	
	}
	
	/**
	 * retrieve the company with the given id.
	 * @param id
	 * @return
	 */
	public Company findById(Long id) {
		return companyDao.findById(id);
	}

}
