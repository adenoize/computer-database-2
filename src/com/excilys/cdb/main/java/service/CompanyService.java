package com.excilys.cdb.main.java.service;

import com.excilys.cdb.main.java.constante.Constante;
import com.excilys.cdb.main.java.dao.CompanyDao;
import com.excilys.cdb.main.java.model.Company;
import com.excilys.cdb.main.java.model.Page;

public class CompanyService {
	
	
	private CompanyDao companyDao = CompanyDao.INSTANCE;
	
	
	/**
	 * retrieve all companies.
	 */
	public Page<Company> getPage(int page){
		
		int offset = Constante.LIMIT_PAGE * page;
		
		return companyDao.getPage(offset);	
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
