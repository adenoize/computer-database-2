package main.java.com.excilys.cdb.service;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.dao.CompanyDao;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

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
