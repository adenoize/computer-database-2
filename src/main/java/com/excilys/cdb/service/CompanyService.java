package main.java.com.excilys.cdb.service;

import java.util.List;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.dao.CompanyDao;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

public class CompanyService {

    private CompanyDao companyDao = CompanyDao.INSTANCE;

    /**
     * Retrieve all companies.
     * @param page number of the page
     * @return the page
     */
    public Page<Company> getPage(int page) {

        int offset = Constante.LIMIT_PAGE * page;

        return companyDao.getPage(offset);
    }

    /**
     * Retrieve the company with the given id.
     * @param id the id of the company
     * @return the company
     * @throws DatabaseException DatabaseException
     */
    public Company findById(Long id) throws DatabaseException {
        Company company = companyDao.findById(id);

        if (company == null) {
            throw new DatabaseException();
        }

        return company;
    }

    /**
     * Retrieve the company with the given id.
     * @return the company
     * @throws DatabaseException DatabaseException
     */
    public List<Company> findAll() throws DatabaseException {
        List<Company> companies = companyDao.findAll();

        if (companies == null) {
            throw new DatabaseException();
        }
        return companies;
    }

}
